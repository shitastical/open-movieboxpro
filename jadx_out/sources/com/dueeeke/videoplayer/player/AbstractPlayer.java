package com.dueeeke.videoplayer.player;

import android.content.res.AssetFileDescriptor;
import android.view.Surface;
import android.view.SurfaceHolder;
import com.dueeeke.videoplayer.listener.PlayerEventListener;
import java.util.Map;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;

/* JADX INFO: loaded from: classes4.dex */
public abstract class AbstractPlayer {
    protected PlayerEventListener mPlayerEventListener;

    public abstract int getBufferedPercentage();

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract long getTcpSpeed();

    public abstract IjkTrackInfo[] getTrackInfo();

    public abstract int getVideoHeight();

    public abstract int getVideoWidth();

    public abstract void initPlayer();

    public abstract boolean isPlaying();

    public abstract void pause();

    public abstract void prepareAsync();

    public abstract void release();

    public abstract void reset();

    public abstract void seekTo(long j);

    public abstract void setDataSource(AssetFileDescriptor assetFileDescriptor);

    public abstract void setDataSource(String str, Map<String, String> map);

    public abstract void setDisplay(SurfaceHolder surfaceHolder);

    public abstract void setEnableMediaCodec(boolean z);

    public abstract void setLooping(boolean z);

    public abstract void setOptions();

    public abstract void setSpeed(float f);

    public abstract void setSurface(Surface surface);

    public abstract void setTrackInfo(int i);

    public abstract void setVolume(float f, float f2);

    public abstract void start();

    public abstract void stop();

    public void bindVideoView(PlayerEventListener playerEventListener) {
        this.mPlayerEventListener = playerEventListener;
    }
}
