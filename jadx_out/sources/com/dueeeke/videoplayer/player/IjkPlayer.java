package com.dueeeke.videoplayer.player;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;

/* JADX INFO: loaded from: classes4.dex */
public class IjkPlayer extends AbstractPlayer {
    protected Context mAppContext;
    private int mBufferedPercent;
    private boolean mIsEnableMediaCodec;
    private boolean mIsLooping;
    protected IjkMediaPlayer mMediaPlayer;
    private IMediaPlayer.OnErrorListener onErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.dueeeke.videoplayer.player.IjkPlayer.1
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
        public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
            Log.d("mPlayerEventListener", "framework_err:" + i + "  impl_err:" + i2);
            IjkPlayer.this.mPlayerEventListener.onError();
            return true;
        }
    };
    private IMediaPlayer.OnCompletionListener onCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.dueeeke.videoplayer.player.IjkPlayer.2
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
        public void onCompletion(IMediaPlayer iMediaPlayer) {
            IjkPlayer.this.mPlayerEventListener.onCompletion();
        }
    };
    private IMediaPlayer.OnInfoListener onInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.dueeeke.videoplayer.player.IjkPlayer.3
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
        public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
            IjkPlayer.this.mPlayerEventListener.onInfo(i, i2);
            return true;
        }
    };
    private IMediaPlayer.OnBufferingUpdateListener onBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.dueeeke.videoplayer.player.IjkPlayer.4
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
        public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
            IjkPlayer.this.mBufferedPercent = i;
        }
    };
    private IMediaPlayer.OnPreparedListener onPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.dueeeke.videoplayer.player.IjkPlayer.5
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
        public void onPrepared(IMediaPlayer iMediaPlayer) {
            IjkPlayer.this.mPlayerEventListener.onPrepared();
        }
    };
    private IMediaPlayer.OnSeekCompleteListener onSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.dueeeke.videoplayer.player.IjkPlayer.6
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
        public void onSeekComplete(IMediaPlayer iMediaPlayer) {
            IjkPlayer.this.mPlayerEventListener.onSeekComplete(iMediaPlayer.getCurrentPosition(), iMediaPlayer.getDuration());
        }
    };
    private IMediaPlayer.OnVideoSizeChangedListener onVideoSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.dueeeke.videoplayer.player.IjkPlayer.7
        @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
            int videoWidth = iMediaPlayer.getVideoWidth();
            int videoHeight = iMediaPlayer.getVideoHeight();
            if (videoWidth == 0 || videoHeight == 0) {
                return;
            }
            IjkPlayer.this.mPlayerEventListener.onVideoSizeChanged(videoWidth, videoHeight);
        }
    };

    public IjkPlayer(Context context) {
        this.mAppContext = context.getApplicationContext();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void initPlayer() {
        this.mMediaPlayer = new IjkMediaPlayer();
        setOptions();
        this.mMediaPlayer.setAudioStreamType(3);
        this.mMediaPlayer.setOnErrorListener(this.onErrorListener);
        this.mMediaPlayer.setOnCompletionListener(this.onCompletionListener);
        this.mMediaPlayer.setOnInfoListener(this.onInfoListener);
        this.mMediaPlayer.setOnBufferingUpdateListener(this.onBufferingUpdateListener);
        this.mMediaPlayer.setOnPreparedListener(this.onPreparedListener);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.onVideoSizeChangedListener);
        this.mMediaPlayer.setOnSeekCompleteListener(this.onSeekCompleteListener);
        this.mMediaPlayer.setOnNativeInvokeListener(new IjkMediaPlayer.OnNativeInvokeListener() { // from class: com.dueeeke.videoplayer.player.IjkPlayer.8
            @Override // tv.danmaku.ijk.media.player.IjkMediaPlayer.OnNativeInvokeListener
            public boolean onNativeInvoke(int i, Bundle bundle) {
                return true;
            }
        });
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setDataSource(String str, Map<String, String> map) {
        try {
            Uri uri = Uri.parse(str);
            if ("android.resource".equals(uri.getScheme())) {
                this.mMediaPlayer.setDataSource(RawDataSourceProvider.create(this.mAppContext, uri));
            } else {
                this.mMediaPlayer.setDataSource(this.mAppContext, uri, map);
            }
        } catch (Exception unused) {
            this.mPlayerEventListener.onError();
        }
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setDataSource(AssetFileDescriptor assetFileDescriptor) {
        try {
            this.mMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor());
        } catch (Exception unused) {
            this.mPlayerEventListener.onError();
        }
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void start() {
        this.mMediaPlayer.start();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void pause() {
        try {
            this.mMediaPlayer.pause();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void stop() {
        try {
            this.mMediaPlayer.stop();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void prepareAsync() {
        try {
            this.mMediaPlayer.prepareAsync();
        } catch (Exception unused) {
            this.mPlayerEventListener.onError();
        }
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void reset() {
        this.mMediaPlayer.reset();
        this.mMediaPlayer.setOnVideoSizeChangedListener(this.onVideoSizeChangedListener);
        this.mMediaPlayer.setLooping(this.mIsLooping);
        setOptions();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public boolean isPlaying() {
        return this.mMediaPlayer.isPlaying();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void seekTo(long j) {
        try {
            this.mMediaPlayer.seekTo((int) j);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void release() {
        this.mMediaPlayer.release();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public long getCurrentPosition() {
        return this.mMediaPlayer.getCurrentPosition();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public long getDuration() {
        return this.mMediaPlayer.getDuration();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public int getBufferedPercentage() {
        return this.mBufferedPercent;
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setSurface(Surface surface) {
        this.mMediaPlayer.setSurface(surface);
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setDisplay(SurfaceHolder surfaceHolder) {
        this.mMediaPlayer.setDisplay(surfaceHolder);
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setVolume(float f, float f2) {
        this.mMediaPlayer.setVolume(f, f2);
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setLooping(boolean z) {
        this.mIsLooping = z;
        this.mMediaPlayer.setLooping(z);
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setEnableMediaCodec(boolean z) {
        this.mIsEnableMediaCodec = z;
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        long j = z ? 1L : 0L;
        ijkMediaPlayer.setOption(4, "mediacodec", j);
        this.mMediaPlayer.setOption(4, "mediacodec-auto-rotate", j);
        this.mMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", j);
        this.mMediaPlayer.setOption(4, "mediacodec-hevc", j);
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setOptions() {
        this.mMediaPlayer.setOption(2, "packet-buffering", 0L);
        this.mMediaPlayer.setOption(2, "infbuf", 0L);
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setSpeed(float f) {
        this.mMediaPlayer.setSpeed(f);
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public long getTcpSpeed() {
        return this.mMediaPlayer.getTcpSpeed();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public int getVideoHeight() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getVideoHeight();
        }
        return 0;
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public int getVideoWidth() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getVideoWidth();
        }
        return 0;
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public IjkTrackInfo[] getTrackInfo() {
        return this.mMediaPlayer.getTrackInfo();
    }

    @Override // com.dueeeke.videoplayer.player.AbstractPlayer
    public void setTrackInfo(int i) {
        if (this.mMediaPlayer.getSelectedTrack(2) != i) {
            IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
            ijkMediaPlayer.deselectTrack(ijkMediaPlayer.getSelectedTrack(2));
            this.mMediaPlayer.selectTrack(i);
        }
    }
}
