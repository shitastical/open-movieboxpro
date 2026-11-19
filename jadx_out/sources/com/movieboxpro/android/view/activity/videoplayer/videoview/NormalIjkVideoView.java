package com.movieboxpro.android.view.activity.videoplayer.videoview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import com.dueeeke.model.MediaQualityInfo;
import com.dueeeke.videoplayer.player.IjkVideoView;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter;
import java.util.List;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;

/* JADX INFO: loaded from: classes5.dex */
public class NormalIjkVideoView extends IjkVideoView implements NormalPlayerPresenter {
    public CallBack mCallBack;
    private int mCurrentDefinition;
    private List<MediaQualityInfo> mDefinitionMap;

    public interface CallBack {
        void onSeekComplete(long currPosition, long duration);

        void setQuality(MediaQualityInfo mediaQualityInfo);
    }

    public NormalIjkVideoView(Context context) {
        super(context);
        this.mCurrentDefinition = -1;
    }

    public NormalIjkVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCurrentDefinition = -1;
    }

    public NormalIjkVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mCurrentDefinition = -1;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public List<MediaQualityInfo> getDefinitionData() {
        return this.mDefinitionMap;
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void switchDefinition(int definition) {
        String path = this.mDefinitionMap.get(definition).getPath();
        if (definition == this.mCurrentDefinition) {
            return;
        }
        this.mCurrentUrl = path;
        setUrl();
        addDisplay();
        getCurrentPosition();
        startPrepare(true);
        this.mCurrentDefinition = definition;
        if (this.mCallBack == null && definition > this.mDefinitionMap.size() && this.mDefinitionMap.size() == 0) {
            return;
        }
        this.mCallBack.setQuality(this.mDefinitionMap.get(definition));
    }

    public void resetDefinitionVideosAndPlay(List<MediaQualityInfo> mediaMedium, PlayRecode playRecode) {
        addDisplay();
        setDefinitionVideos(mediaMedium, playRecode);
        getCurrentPosition();
        startPrepare(true);
        if (this.mCallBack == null && this.mCurrentDefinition > this.mDefinitionMap.size() && this.mDefinitionMap.size() == 0) {
            return;
        }
        this.mCallBack.setQuality(this.mDefinitionMap.get(this.mCurrentDefinition));
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public void switchDefaultDefinition(boolean open) {
        setPlayerConfig(new PlayerConfig.Builder().enableMediaCodec(open).autoRotate().build());
        addDisplay();
        startPrepare(true);
    }

    @Override // com.movieboxpro.android.view.activity.videoplayer.presenter.NormalPlayerPresenter
    public int getDefinition() {
        return this.mCurrentDefinition;
    }

    public void setPlayUrl(String url) {
        this.mCurrentUrl2 = url;
        this.mCurrentUrl = url;
    }

    public void setDefinitionVideos(List<MediaQualityInfo> mediaMedium, PlayRecode playRecode) {
        CallBack callBack;
        if (mediaMedium == null) {
            return;
        }
        this.mDefinitionMap = mediaMedium;
        this.mCurrentDefinition = choose();
        if (playRecode != null) {
            int iChoose = (playRecode.getQuality() >= mediaMedium.size() || playRecode.getQuality() <= 0) ? choose() : playRecode.getQuality();
            this.mCurrentDefinition = iChoose;
            restChoose(iChoose);
            seekTo(playRecode.getStart_time());
        } else {
            seekTo(0L);
            this.mCurrentDefinition = choose();
        }
        this.mCurrentUrl = getValueFromLinkedMap(mediaMedium, this.mCurrentDefinition);
        this.mCurrentUrl2 = this.mCurrentUrl;
        setUrl();
        if ((this.mCurrentDefinition <= mediaMedium.size() || mediaMedium.size() != 0) && (callBack = this.mCallBack) != null) {
            callBack.setQuality(mediaMedium.get(this.mCurrentDefinition));
        }
    }

    public void setErrorVideos() {
        String error;
        if (this.mCurrentDefinition == -1 || this.mMediaPlayer == null || (error = this.mDefinitionMap.get(this.mCurrentDefinition).getError()) == null) {
            return;
        }
        this.mCurrentUrl = error;
        addDisplay();
        getCurrentPosition();
        startPrepare(true);
    }

    public void setOtherPath() {
        setUrl();
        addDisplay();
        getCurrentPosition();
        startPrepare(true);
    }

    public void setUrl() {
        Log.d("tag", "截取链接1 : " + this.mCurrentUrl.startsWith("http://"));
        if (this.mCurrentUrl.startsWith("http://") || this.mCurrentUrl.startsWith("https://")) {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "Error");
            if (!string.equals("Error")) {
                Log.d("tag", "截取链接1 : " + this.mCurrentUrl);
                String[] strArrSplit = this.mCurrentUrl.split("/");
                if (strArrSplit.length > 2) {
                    strArrSplit[2] = string;
                    String str = "";
                    for (int i = 2; i < strArrSplit.length; i++) {
                        str = i != strArrSplit.length - 1 ? str + strArrSplit[i] + "/" : str + strArrSplit[i];
                    }
                    this.mCurrentUrl = str;
                    return;
                }
                return;
            }
            PrefsUtils.getInstance().remove(Constant.Prefs.NETWORK_STATE);
        }
    }

    public int choose() {
        if (this.mDefinitionMap == null) {
            return 0;
        }
        for (int i = 0; i < this.mDefinitionMap.size(); i++) {
            if (this.mDefinitionMap.get(i).getPath() != null && !TextUtils.isEmpty(this.mDefinitionMap.get(i).getPath()) && !"org".equals(this.mDefinitionMap.get(i).getQuality())) {
                this.mDefinitionMap.get(i).setSelect(true);
                return i;
            }
        }
        return 0;
    }

    public void restChoose(int choose) {
        for (int i = 0; i < this.mDefinitionMap.size(); i++) {
            if (choose == i) {
                this.mDefinitionMap.get(i).setSelect(true);
            } else {
                this.mDefinitionMap.get(i).setSelect(false);
            }
        }
    }

    public static String getValueFromLinkedMap(List<MediaQualityInfo> mediaMedium, int index) {
        if (mediaMedium == null || mediaMedium.size() <= index) {
            return null;
        }
        return mediaMedium.get(index).getPath();
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView, com.dueeeke.videoplayer.controller.MediaPlayerControl
    public IjkTrackInfo[] getTrackInfo() {
        if (this.mVideoController != null) {
            this.mVideoController.getTrackInfo(super.getTrackInfo());
        }
        return super.getTrackInfo();
    }

    @Override // com.dueeeke.videoplayer.player.IjkVideoView, com.dueeeke.videoplayer.player.BaseIjkVideoView
    public void release() {
        super.release();
        this.mCallBack = null;
    }

    @Override // com.dueeeke.videoplayer.player.IjkVideoView, com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onSeekComplete(long currentPosition, long duration) {
        CallBack callBack = this.mCallBack;
        if (callBack != null) {
            callBack.onSeekComplete(currentPosition, duration);
        }
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView, com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void setTrackInfo(int setTrackInfo) {
        super.setTrackInfo(setTrackInfo);
    }

    public void setCallBack(CallBack callBack) {
        this.mCallBack = callBack;
    }
}
