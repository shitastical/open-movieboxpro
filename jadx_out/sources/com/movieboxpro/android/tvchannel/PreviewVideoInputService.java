package com.movieboxpro.android.tvchannel;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.util.Log;
import android.view.Surface;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public class PreviewVideoInputService extends TvInputService {
    private static final String TAG = "PreviewVideoInput";

    @Override // android.media.tv.TvInputService
    public TvInputService.Session onCreateSession(String inputId) {
        Log.d(TAG, inputId);
        return new PreviewSession(this, this);
    }

    private class PreviewSession extends TvInputService.Session {
        private final SampleClipApi.GetClipByIdListener mGetClipByIdListener;
        private MediaPlayer mPlayer;

        @Override // android.media.tv.TvInputService.Session
        public void onSetCaptionEnabled(boolean enabled) {
        }

        PreviewSession(final PreviewVideoInputService this$0, Context context) {
            super(context);
            this.mPlayer = new MediaPlayer();
            this.mGetClipByIdListener = new SampleClipApi.GetClipByIdListener() { // from class: com.movieboxpro.android.tvchannel.PreviewVideoInputService.PreviewSession.1
                @Override // com.movieboxpro.android.tvchannel.SampleClipApi.GetClipByIdListener
                public void onGetClipById(Clip clip) {
                    if (clip != null) {
                        try {
                            PreviewSession.this.mPlayer.setDataSource(clip.getPreviewVideoUrl());
                            PreviewSession.this.mPlayer.prepare();
                            PreviewSession.this.mPlayer.start();
                            PreviewSession.this.notifyVideoAvailable();
                        } catch (IOException e) {
                            Log.e(PreviewVideoInputService.TAG, "Could not prepare media mPlayer", e);
                            PreviewSession.this.notifyVideoUnavailable(0);
                        }
                    }
                }
            };
        }

        @Override // android.media.tv.TvInputService.Session
        public boolean onTune(Uri channelUri) {
            notifyVideoUnavailable(1);
            SampleClipApi.getClipById(channelUri.getLastPathSegment(), this.mGetClipByIdListener);
            return true;
        }

        @Override // android.media.tv.TvInputService.Session
        public boolean onSetSurface(Surface surface) {
            MediaPlayer mediaPlayer = this.mPlayer;
            if (mediaPlayer == null) {
                return true;
            }
            mediaPlayer.setSurface(surface);
            return true;
        }

        @Override // android.media.tv.TvInputService.Session
        public void onRelease() {
            SampleClipApi.cancelGetClipById(this.mGetClipByIdListener);
            MediaPlayer mediaPlayer = this.mPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.release();
            }
            this.mPlayer = null;
        }

        @Override // android.media.tv.TvInputService.Session
        public void onSetStreamVolume(float volume) {
            MediaPlayer mediaPlayer = this.mPlayer;
            if (mediaPlayer != null) {
                mediaPlayer.setVolume(volume, volume);
            }
        }
    }
}
