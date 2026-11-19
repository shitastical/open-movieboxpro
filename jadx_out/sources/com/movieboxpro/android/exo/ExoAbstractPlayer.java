package com.movieboxpro.android.exo;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.Tracks;
import androidx.media3.exoplayer.ExoPlayer;
import com.dueeeke.videoplayer.listener.PlayerEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.RendererItem;
import org.videolan.libvlc.util.VLCVideoLayout;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ExoAbstractPlayer {
    public abstract int addAudioTrack(String url);

    public abstract void addSubtitle(String url, boolean select);

    public void attachView(VLCVideoLayout vlcVideoLayout, Activity activity) {
    }

    public void bindVideoView(PlayerEventListener playerEventListener) {
    }

    public void clearVideoTrackOverrides() {
    }

    public abstract long getAudioDelay();

    public abstract int getBufferedPercentage();

    public abstract int getCurrAudioIndex();

    public Tracks.Group getCurrentAudioTrack() {
        return null;
    }

    public abstract long getCurrentPosition();

    public abstract long getDuration();

    public abstract ExoPlayer getMediaPlayer();

    public abstract long getTcpSpeed();

    public abstract Tracks getTrackInfo();

    public abstract int[] getVideoSize();

    public abstract TrackGroup getVideoTrack();

    public abstract int getVolume();

    public abstract void initPlayer();

    public abstract boolean isPlaying();

    public abstract void mute(boolean mute);

    public abstract void pause();

    public boolean pinVideoQuality(String quality) {
        return false;
    }

    public abstract void prepareAsync();

    public abstract void registerListener(PlayListener listener);

    public abstract void release();

    public abstract void releaseView();

    public abstract void reset();

    public abstract void seekTo(long time);

    public abstract void setAudioDelay(long audioDelay);

    public abstract void setAudioTrack(TrackGroup trackGroup);

    public abstract void setAudioTrack(TrackSelectionOverride trackGroup);

    public abstract void setDataSource(AssetFileDescriptor fd);

    public abstract void setDataSource(Uri uri);

    public abstract void setDataSource(String path, long startTime);

    public abstract void setDataSource(String path, Map<String, String> headers);

    public abstract void setDisplay(SurfaceHolder holder);

    public abstract void setEnableHardCodec(boolean enableHardCodec);

    public abstract void setEnableMediaCodec(boolean isEnable);

    public abstract void setLooping(boolean isLooping);

    public void setOnVideoPinFailed(OnVideoPinFailed listener) {
    }

    public abstract void setOptions();

    public void setPendingVideoQuality(String quality) {
    }

    public void setPlayListener(MediaPlayer.EventListener eventListener) {
    }

    public abstract void setRenderer(RendererItem renderer);

    public abstract void setSpeed(float speed);

    public abstract void setSurface(Surface surface);

    public void setTextureView(TextureView textureView) {
    }

    public abstract void setTrackInfo(int SelecttrackInfo);

    public abstract void setVideoInfo(String title, String poster, String fid, int boxType);

    public abstract void setVideoScale(int scale);

    public abstract void setVolume(float v1, float v2);

    public abstract void setVolume(int volume);

    public abstract void start();

    public abstract void stop();

    public abstract void unRegisterListener(PlayListener listener);

    public abstract void updateVideoView(VLCVideoLayout vlcVideoLayout, Activity activity);

    public List<Tracks.Group> getAudioTracks() {
        return new ArrayList();
    }

    public ArrayList<Integer> getTrackIndices() {
        return new ArrayList<>();
    }
}
