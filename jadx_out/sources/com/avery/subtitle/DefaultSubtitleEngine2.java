package com.avery.subtitle;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
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
public class DefaultSubtitleEngine2 implements SubtitleEngine {
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
    private Handler mainThreadHandler;
    private OnMediaStatusListener onMediaStatusListener;
    private int position = -1;
    private long playerPosition = 0;
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
        SubtitleLoader.loadSubtitle(str, new SubtitleLoader.Callback() { // from class: com.avery.subtitle.DefaultSubtitleEngine2.1
            @Override // com.avery.subtitle.SubtitleLoader.Callback
            public void onSuccess(TimedTextObject timedTextObject) {
                if (timedTextObject == null) {
                    Log.d(DefaultSubtitleEngine2.TAG, "onSuccess: timedTextObject is null.");
                    return;
                }
                TreeMap<Integer, Subtitle> treeMap = timedTextObject.captions;
                if (treeMap == null) {
                    Log.d(DefaultSubtitleEngine2.TAG, "onSuccess: captions is null.");
                    return;
                }
                if (DefaultSubtitleEngine2.this.mSubtitles != null && DefaultSubtitleEngine2.this.mSubtitles.size() > 0 && DefaultSubtitleEngine2.this.onMediaStatusListener != null && DefaultSubtitleEngine2.this.onMediaStatusListener.isPrepared() && !DefaultSubtitleEngine2.this.onMediaStatusListener.isPlaying()) {
                    DefaultSubtitleEngine2.this.refreshSubtitleAtOnce();
                }
                DefaultSubtitleEngine2.this.mSubtitles = new ArrayList(treeMap.values());
                DefaultSubtitleEngine2.this.notifyPrepared();
                DefaultSubtitleEngine2.this.mCache.put(str, new ArrayList(treeMap.values()));
            }

            @Override // com.avery.subtitle.SubtitleLoader.Callback
            public void onError(Exception exc) {
                Log.e(DefaultSubtitleEngine2.TAG, "onError: " + exc.getMessage());
            }
        });
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
            this.mainThreadHandler.removeMessages(MSG_REFRESH);
            this.mainThreadHandler.sendEmptyMessageDelayed(MSG_REFRESH, 100L);
        }
    }

    @Override // com.avery.subtitle.SubtitleEngine
    public void pause() {
        Handler handler = this.mWorkHandler;
        if (handler != null) {
            handler.removeMessages(MSG_REFRESH);
            this.mainThreadHandler.removeMessages(MSG_REFRESH);
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
            this.mainThreadHandler.removeMessages(MSG_REFRESH);
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
        this.mWorkHandler = new Handler(this.mHandlerThread.getLooper(), new Handler.Callback() { // from class: com.avery.subtitle.DefaultSubtitleEngine2.2
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                Subtitle subtitle;
                try {
                    Pair<Integer, Subtitle> pairFind = SubtitleFinder.find(DefaultSubtitleEngine2.this.playerPosition, DefaultSubtitleEngine2.this.mSubtitles);
                    if (pairFind != null) {
                        subtitle = pairFind.second;
                        if (pairFind.first != null) {
                            DefaultSubtitleEngine2.this.position = pairFind.first.intValue();
                        }
                    } else {
                        subtitle = null;
                    }
                    DefaultSubtitleEngine2.this.notifyRefreshUI(subtitle);
                    long j = subtitle != null ? ((long) subtitle.end.mseconds) - DefaultSubtitleEngine2.this.playerPosition : 100L;
                    if (DefaultSubtitleEngine2.this.mWorkHandler == null) {
                        return true;
                    }
                    DefaultSubtitleEngine2.this.mainThreadHandler.sendEmptyMessageDelayed(DefaultSubtitleEngine2.MSG_REFRESH, j);
                    return true;
                } catch (Exception unused) {
                    if (DefaultSubtitleEngine2.this.mWorkHandler == null) {
                        return true;
                    }
                    DefaultSubtitleEngine2.this.mainThreadHandler.sendEmptyMessageDelayed(DefaultSubtitleEngine2.MSG_REFRESH, 100L);
                    return true;
                }
            }
        });
        this.mainThreadHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.avery.subtitle.DefaultSubtitleEngine2.3
            @Override // android.os.Handler.Callback
            public boolean handleMessage(Message message) {
                if (DefaultSubtitleEngine2.this.onMediaStatusListener == null) {
                    return true;
                }
                try {
                    if (!DefaultSubtitleEngine2.this.onMediaStatusListener.isPrepared() || !DefaultSubtitleEngine2.this.onMediaStatusListener.isPlaying()) {
                        if (DefaultSubtitleEngine2.this.mWorkHandler != null) {
                            DefaultSubtitleEngine2.this.mWorkHandler.sendEmptyMessage(DefaultSubtitleEngine2.MSG_REFRESH);
                        }
                    } else {
                        DefaultSubtitleEngine2 defaultSubtitleEngine2 = DefaultSubtitleEngine2.this;
                        defaultSubtitleEngine2.playerPosition = defaultSubtitleEngine2.onMediaStatusListener.getCurrentPosition() + DefaultSubtitleEngine2.this.delay;
                        if (DefaultSubtitleEngine2.this.mWorkHandler != null) {
                            DefaultSubtitleEngine2.this.mWorkHandler.sendEmptyMessage(DefaultSubtitleEngine2.MSG_REFRESH);
                        }
                    }
                    return true;
                } catch (Exception unused) {
                    if (DefaultSubtitleEngine2.this.mWorkHandler == null) {
                        return true;
                    }
                    DefaultSubtitleEngine2.this.mWorkHandler.sendEmptyMessage(DefaultSubtitleEngine2.MSG_REFRESH);
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
        Handler handler2 = this.mainThreadHandler;
        if (handler2 != null) {
            handler2.removeCallbacksAndMessages(null);
            this.mainThreadHandler = null;
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
