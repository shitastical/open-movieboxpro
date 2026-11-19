package com.avery.subtitle;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.util.Pair;
import com.avery.subtitle.cache.SubtitleCache;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.TimedTextObject;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultSubtitleEngine implements SubtitleEngine {
    private static final int MSG_REFRESH = 2184;
    private static final int REFRESH_INTERVAL = 100;
    private static final String TAG = "DefaultSubtitleEngine2";
    private long delay;
    private HandlerThread mHandlerThread;
    private SubtitleEngine.OnSubtitleChangeListener mOnSubtitleChangeListener;
    private SubtitleEngine.OnSubtitlePreparedListener mOnSubtitlePreparedListener;
    private List<Subtitle> mSubtitles;
    private UIRenderTask mUIRenderTask;
    private Handler mWorkHandler;
    private OnMediaStatusListener onMediaStatusListener;
    private int position = -1;
    private SubtitleCache mCache = new SubtitleCache();

    @Override // com.avery.subtitle.SubtitleEngine
    public void bindToMediaPlayer(MediaPlayer mediaPlayer) {
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setSubtitles(List<Subtitle> list) {
        pause();
        if (this.mHandlerThread == null) {
            createWorkThread();
        }
        List<Subtitle> list2 = this.mSubtitles;
        if (list2 != null) {
            list2.clear();
            if (list != null) {
                this.mSubtitles.addAll(list);
            }
        } else {
            ArrayList arrayList = new ArrayList();
            this.mSubtitles = arrayList;
            if (list != null) {
                arrayList.addAll(list);
            }
        }
        start();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public List<Subtitle> getSubtitles() {
        List<Subtitle> list = this.mSubtitles;
        return list == null ? new ArrayList() : list;
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void clearSubtitles() {
        List<Subtitle> list = this.mSubtitles;
        if (list != null) {
            list.clear();
        }
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public int getCurrSubtitlePos() {
        return this.position;
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public long setCurrSubtitle(Subtitle subtitle) {
        OnMediaStatusListener onMediaStatusListener = this.onMediaStatusListener;
        if (onMediaStatusListener != null && onMediaStatusListener.isPrepared()) {
            this.delay = ((long) subtitle.start.mseconds) - this.onMediaStatusListener.getCurrentPosition();
            notifyRefreshUI(subtitle);
        }
        return this.delay;
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setSubtitlePath(final String str) {
        OnMediaStatusListener onMediaStatusListener;
        doOnSubtitlePathSet();
        if (TextUtils.isEmpty(str)) {
            Log.w(TAG, "loadSubtitleFromRemote: path is null.");
            return;
        }
        List<Subtitle> list = this.mCache.get(str);
        this.mSubtitles = list;
        if (list != null && !list.isEmpty()) {
            Log.d(TAG, "from cache.");
            List<Subtitle> list2 = this.mSubtitles;
            if (list2 != null && list2.size() > 0 && (onMediaStatusListener = this.onMediaStatusListener) != null && onMediaStatusListener.isPrepared() && !this.onMediaStatusListener.isPlaying()) {
                refreshSubtitleAtOnce();
            }
            notifyPrepared();
            return;
        }
        SubtitleLoader.loadSubtitle(str, new SubtitleLoader.Callback() { // from class: com.avery.subtitle.DefaultSubtitleEngine.1
            @Override // com.avery.subtitle.SubtitleLoader.Callback
            public void onSuccess(TimedTextObject timedTextObject) {
                if (timedTextObject == null) {
                    Log.d(DefaultSubtitleEngine.TAG, "onSuccess: timedTextObject is null.");
                    return;
                }
                TreeMap<Integer, Subtitle> treeMap = timedTextObject.captions;
                if (treeMap == null) {
                    Log.d(DefaultSubtitleEngine.TAG, "onSuccess: captions is null.");
                    return;
                }
                if (DefaultSubtitleEngine.this.mSubtitles != null && DefaultSubtitleEngine.this.mSubtitles.size() > 0 && DefaultSubtitleEngine.this.onMediaStatusListener != null && DefaultSubtitleEngine.this.onMediaStatusListener.isPrepared() && !DefaultSubtitleEngine.this.onMediaStatusListener.isPlaying()) {
                    DefaultSubtitleEngine.this.refreshSubtitleAtOnce();
                }
                DefaultSubtitleEngine.this.mSubtitles = new ArrayList(treeMap.values());
                DefaultSubtitleEngine.this.notifyPrepared();
                DefaultSubtitleEngine.this.mCache.put(str, new ArrayList(treeMap.values()));
            }

            @Override // com.avery.subtitle.SubtitleLoader.Callback
            public void onError(Exception exc) {
                Log.e(DefaultSubtitleEngine.TAG, "onError: " + exc.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSubtitleAtOnce() {
        Subtitle subtitle;
        Pair<Integer, Subtitle> pairFind = SubtitleFinder.find(this.onMediaStatusListener.getCurrentPosition() + this.delay, this.mSubtitles);
        if (pairFind != null) {
            subtitle = pairFind.second;
            if (pairFind.first != null) {
                this.position = pairFind.first.intValue();
            }
        } else {
            subtitle = null;
        }
        notifyRefreshUI(subtitle);
    }

    private void doOnSubtitlePathSet() {
        reset();
        createWorkThread();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void reset() {
        stopWorkThread();
        this.mSubtitles = null;
        this.mUIRenderTask = null;
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void start() {
        String str = TAG;
        Log.d(str, "start: ");
        OnMediaStatusListener onMediaStatusListener = this.onMediaStatusListener;
        if (onMediaStatusListener == null) {
            return;
        }
        if (!onMediaStatusListener.isPrepared()) {
            Log.w(str, "MediaPlayer is not bind, You must bind MediaPlayer to SubtitleEngine before start() method be called, you can do this by call bindToMediaPlayer(MediaPlayer mediaPlayer) method.");
            return;
        }
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeMessages(MSG_REFRESH);
            this.mWorkHandler.sendEmptyMessageDelayed(MSG_REFRESH, 100L);
        }
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void pause() {
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeMessages(MSG_REFRESH);
        }
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void resume() {
        start();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void stop() {
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeMessages(MSG_REFRESH);
        }
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void destroy() {
        Log.d(TAG, "destroy: ");
        stopWorkThread();
        this.mSubtitles = null;
        this.mUIRenderTask = null;
        this.onMediaStatusListener = null;
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void bindOnMediaStatusListener(OnMediaStatusListener onMediaStatusListener) {
        this.onMediaStatusListener = onMediaStatusListener;
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setDelay(int i) {
        this.delay = i;
        refreshSubtitle();
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void refreshSubtitle() {
        Subtitle subtitle;
        OnMediaStatusListener onMediaStatusListener = this.onMediaStatusListener;
        if (onMediaStatusListener == null || !onMediaStatusListener.isPrepared()) {
            return;
        }
        Pair<Integer, Subtitle> pairFind = SubtitleFinder.find(this.onMediaStatusListener.getCurrentPosition() + this.delay, this.mSubtitles);
        if (pairFind != null) {
            subtitle = pairFind.second;
            if (pairFind.first != null) {
                this.position = pairFind.first.intValue();
            }
        } else {
            subtitle = null;
        }
        notifyRefreshUI(subtitle);
    }

    private void createWorkThread() {
        HandlerThread handlerThread = new HandlerThread("SubtitleFindThread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mWorkHandler = new Handler(this.mHandlerThread.getLooper(), new Handler.Callback() { // from class: com.avery.subtitle.DefaultSubtitleEngine.2
            /* JADX WARN: Code duplicated, block: B:18:0x0066  */
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                long j;
                Subtitle subtitle;
                try {
                    if (DefaultSubtitleEngine.this.onMediaStatusListener != null && DefaultSubtitleEngine.this.onMediaStatusListener.isPrepared() && DefaultSubtitleEngine.this.onMediaStatusListener.isPlaying()) {
                        long currentPosition = DefaultSubtitleEngine.this.onMediaStatusListener.getCurrentPosition() + DefaultSubtitleEngine.this.delay;
                        Pair<Integer, Subtitle> pairFind = SubtitleFinder.find(currentPosition, DefaultSubtitleEngine.this.mSubtitles);
                        if (pairFind != null) {
                            subtitle = pairFind.second;
                            if (pairFind.first != null) {
                                DefaultSubtitleEngine.this.position = pairFind.first.intValue();
                            }
                        } else {
                            subtitle = null;
                        }
                        DefaultSubtitleEngine.this.notifyRefreshUI(subtitle);
                        if (subtitle != null) {
                            j = ((long) subtitle.end.mseconds) - currentPosition;
                        } else {
                            j = 100;
                        }
                    } else {
                        j = 100;
                    }
                    if (DefaultSubtitleEngine.this.mWorkHandler == null) {
                        return true;
                    }
                    DefaultSubtitleEngine.this.mWorkHandler.sendEmptyMessageDelayed(DefaultSubtitleEngine.MSG_REFRESH, j);
                    return true;
                } catch (Exception unused) {
                    if (DefaultSubtitleEngine.this.mWorkHandler == null) {
                        return true;
                    }
                    DefaultSubtitleEngine.this.mWorkHandler.sendEmptyMessageDelayed(DefaultSubtitleEngine.MSG_REFRESH, 100L);
                    return true;
                }
            }
        });
    }

    private void stopWorkThread() {
        HandlerThread handlerThread = this.mHandlerThread;
        if (handlerThread != null) {
            handlerThread.quit();
            this.mHandlerThread = null;
        }
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.mWorkHandler = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRefreshUI(Subtitle subtitle) {
        if (this.mUIRenderTask == null) {
            this.mUIRenderTask = new UIRenderTask(this.mOnSubtitleChangeListener);
        }
        this.mUIRenderTask.execute(subtitle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPrepared() {
        SubtitleEngine.OnSubtitlePreparedListener onSubtitlePreparedListener = this.mOnSubtitlePreparedListener;
        if (onSubtitlePreparedListener != null) {
            onSubtitlePreparedListener.onSubtitlePrepared(this.mSubtitles);
        }
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setOnSubtitlePreparedListener(SubtitleEngine.OnSubtitlePreparedListener onSubtitlePreparedListener) {
        this.mOnSubtitlePreparedListener = onSubtitlePreparedListener;
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void setOnSubtitleChangeListener(SubtitleEngine.OnSubtitleChangeListener onSubtitleChangeListener) {
        this.mOnSubtitleChangeListener = onSubtitleChangeListener;
    }
}
