package org.videolan.libvlc;

import android.app.Activity;
import android.os.Handler;
import android.util.Log;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.FrameLayout;
import org.videolan.R;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.interfaces.IVLCVout;
import org.videolan.libvlc.util.AndroidUtil;
import org.videolan.libvlc.util.DisplayManager;
import org.videolan.libvlc.util.VLCVideoLayout;

/* JADX INFO: loaded from: classes6.dex */
class VideoHelper implements IVLCVout.OnNewVideoLayoutListener {
    private static final String TAG = "LibVLC/VideoHelper";
    private DisplayManager mDisplayManager;
    private MediaPlayer mMediaPlayer;
    private FrameLayout mVideoSurfaceFrame;
    private MediaPlayer.ScaleType mCurrentScaleType = MediaPlayer.ScaleType.SURFACE_BEST_FIT;
    private int mVideoHeight = 0;
    private int mVideoWidth = 0;
    private int mVideoVisibleHeight = 0;
    private int mVideoVisibleWidth = 0;
    private int mVideoSarNum = 0;
    private int mVideoSarDen = 0;
    private SurfaceView mVideoSurface = null;
    private SurfaceView mSubtitlesSurface = null;
    private TextureView mVideoTexture = null;
    private final Handler mHandler = new Handler();
    private View.OnLayoutChangeListener mOnLayoutChangeListener = null;

    VideoHelper(MediaPlayer mediaPlayer, VLCVideoLayout vLCVideoLayout, DisplayManager displayManager, boolean z, boolean z2) {
        init(mediaPlayer, vLCVideoLayout, displayManager, z, !z2);
    }

    private void init(MediaPlayer mediaPlayer, VLCVideoLayout vLCVideoLayout, DisplayManager displayManager, boolean z, boolean z2) {
        this.mMediaPlayer = mediaPlayer;
        this.mDisplayManager = displayManager;
        if (displayManager == null || displayManager.isPrimary()) {
            FrameLayout frameLayout = (FrameLayout) vLCVideoLayout.findViewById(R.id.player_surface_frame);
            this.mVideoSurfaceFrame = frameLayout;
            if (z2) {
                ViewStub viewStub = (ViewStub) frameLayout.findViewById(R.id.surface_stub);
                this.mVideoSurface = (SurfaceView) (viewStub != null ? viewStub.inflate() : this.mVideoSurfaceFrame.findViewById(R.id.surface_video));
                if (z) {
                    ViewStub viewStub2 = (ViewStub) this.mVideoSurfaceFrame.findViewById(R.id.subtitles_surface_stub);
                    SurfaceView surfaceView = (SurfaceView) (viewStub2 != null ? viewStub2.inflate() : this.mVideoSurfaceFrame.findViewById(R.id.surface_subtitles));
                    this.mSubtitlesSurface = surfaceView;
                    surfaceView.setZOrderMediaOverlay(true);
                    this.mSubtitlesSurface.getHolder().setFormat(-3);
                    return;
                }
                return;
            }
            ViewStub viewStub3 = (ViewStub) frameLayout.findViewById(R.id.texture_stub);
            this.mVideoTexture = (TextureView) (viewStub3 != null ? viewStub3.inflate() : this.mVideoSurfaceFrame.findViewById(R.id.texture_video));
            return;
        }
        if (this.mDisplayManager.getPresentation() != null) {
            this.mVideoSurfaceFrame = this.mDisplayManager.getPresentation().getSurfaceFrame();
            this.mVideoSurface = this.mDisplayManager.getPresentation().getSurfaceView();
            this.mSubtitlesSurface = this.mDisplayManager.getPresentation().getSubtitlesSurfaceView();
        }
    }

    void release() {
        if (this.mMediaPlayer.getVLCVout().areViewsAttached()) {
            detachViews();
        }
        this.mMediaPlayer = null;
        this.mVideoSurfaceFrame = null;
        this.mHandler.removeCallbacks(null);
        this.mVideoSurface = null;
        this.mSubtitlesSurface = null;
        this.mVideoTexture = null;
    }

    void attachViews() {
        if (this.mVideoSurface == null && this.mVideoTexture == null) {
            return;
        }
        IVLCVout vLCVout = this.mMediaPlayer.getVLCVout();
        SurfaceView surfaceView = this.mVideoSurface;
        if (surfaceView != null) {
            vLCVout.setVideoView(surfaceView);
            SurfaceView surfaceView2 = this.mSubtitlesSurface;
            if (surfaceView2 != null) {
                vLCVout.setSubtitlesView(surfaceView2);
            }
        } else {
            TextureView textureView = this.mVideoTexture;
            if (textureView == null) {
                return;
            } else {
                vLCVout.setVideoView(textureView);
            }
        }
        vLCVout.attachViews(this);
        if (this.mOnLayoutChangeListener == null) {
            this.mOnLayoutChangeListener = new View.OnLayoutChangeListener() { // from class: org.videolan.libvlc.VideoHelper.1
                private final Runnable runnable = new Runnable() { // from class: org.videolan.libvlc.VideoHelper.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (VideoHelper.this.mVideoSurfaceFrame == null || VideoHelper.this.mOnLayoutChangeListener == null) {
                            return;
                        }
                        VideoHelper.this.updateVideoSurfaces();
                    }
                };

                @Override // android.view.View.OnLayoutChangeListener
                public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
                    if (i == i5 && i2 == i6 && i3 == i7 && i4 == i8) {
                        return;
                    }
                    VideoHelper.this.mHandler.removeCallbacks(this.runnable);
                    VideoHelper.this.mHandler.post(this.runnable);
                }
            };
        }
        this.mVideoSurfaceFrame.addOnLayoutChangeListener(this.mOnLayoutChangeListener);
        this.mMediaPlayer.setVideoTrackEnabled(true);
    }

    void detachViews() {
        FrameLayout frameLayout;
        View.OnLayoutChangeListener onLayoutChangeListener = this.mOnLayoutChangeListener;
        if (onLayoutChangeListener != null && (frameLayout = this.mVideoSurfaceFrame) != null) {
            frameLayout.removeOnLayoutChangeListener(onLayoutChangeListener);
            this.mOnLayoutChangeListener = null;
        }
        this.mMediaPlayer.setVideoTrackEnabled(false);
        this.mMediaPlayer.getVLCVout().detachViews();
    }

    private void changeMediaPlayerLayout(int i, int i2) {
        if (this.mMediaPlayer.isReleased()) {
        }
        switch (AnonymousClass2.$SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[this.mCurrentScaleType.ordinal()]) {
            case 1:
                this.mMediaPlayer.setAspectRatio(null);
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 2:
            case 3:
                IMedia.VideoTrack currentVideoTrack = this.mMediaPlayer.getCurrentVideoTrack();
                if (currentVideoTrack != null) {
                    boolean z = currentVideoTrack.orientation == 5 || currentVideoTrack.orientation == 6;
                    if (this.mCurrentScaleType == MediaPlayer.ScaleType.SURFACE_FIT_SCREEN) {
                        int i3 = currentVideoTrack.width;
                        int i4 = currentVideoTrack.height;
                        if (z) {
                            i4 = i3;
                            i3 = i4;
                        }
                        if (currentVideoTrack.sarNum != currentVideoTrack.sarDen) {
                            i3 = (i3 * currentVideoTrack.sarNum) / currentVideoTrack.sarDen;
                        }
                        float f = i3;
                        float f2 = i4;
                        float f3 = i;
                        float f4 = i2;
                        this.mMediaPlayer.setScale(f3 / f4 >= f / f2 ? f3 / f : f4 / f2);
                        this.mMediaPlayer.setAspectRatio(null);
                    } else {
                        this.mMediaPlayer.setScale(0.0f);
                        this.mMediaPlayer.setAspectRatio(z ? "" + i2 + ":" + i : "" + i + ":" + i2);
                    }
                    break;
                }
                break;
            case 4:
                this.mMediaPlayer.setAspectRatio("16:9");
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 5:
                this.mMediaPlayer.setAspectRatio("16:10");
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 6:
                this.mMediaPlayer.setAspectRatio("2:1");
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 7:
                this.mMediaPlayer.setAspectRatio("2.21:1");
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 8:
                this.mMediaPlayer.setAspectRatio("2.35:1");
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 9:
                this.mMediaPlayer.setAspectRatio("2.39:1");
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 10:
                this.mMediaPlayer.setAspectRatio("5:4");
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 11:
                this.mMediaPlayer.setAspectRatio("4:3");
                this.mMediaPlayer.setScale(0.0f);
                break;
            case 12:
                this.mMediaPlayer.setAspectRatio(null);
                this.mMediaPlayer.setScale(1.0f);
                break;
        }
    }

    /* JADX INFO: renamed from: org.videolan.libvlc.VideoHelper$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType;

        static {
            int[] iArr = new int[MediaPlayer.ScaleType.values().length];
            $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType = iArr;
            try {
                iArr[MediaPlayer.ScaleType.SURFACE_BEST_FIT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_FIT_SCREEN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_FILL.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_16_9.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_16_10.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_2_1.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_221_1.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_235_1.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_239_1.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_5_4.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_4_3.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[MediaPlayer.ScaleType.SURFACE_ORIGINAL.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:87:0x0155 A[PHI: r1
      0x0155: PHI (r1v33 double) = (r1v18 double), (r1v18 double), (r1v41 double) binds: [B:86:0x0153, B:83:0x014e, B:79:0x0144] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:88:0x0158 A[PHI: r1
      0x0158: PHI (r1v34 double) = (r1v18 double), (r1v18 double), (r1v41 double) binds: [B:86:0x0153, B:83:0x014e, B:79:0x0144] A[DONT_GENERATE, DONT_INLINE]] */
    void updateVideoSurfaces() {
        int width;
        int height;
        double d;
        double dFloatValue;
        MediaPlayer mediaPlayer = this.mMediaPlayer;
        if (mediaPlayer == null || mediaPlayer.isReleased() || !this.mMediaPlayer.getVLCVout().areViewsAttached()) {
            return;
        }
        DisplayManager displayManager = this.mDisplayManager;
        boolean z = false;
        boolean z2 = displayManager == null || displayManager.isPrimary();
        Activity activityResolveActivity = !z2 ? null : AndroidUtil.resolveActivity(this.mVideoSurfaceFrame.getContext());
        if (activityResolveActivity != null) {
            width = this.mVideoSurfaceFrame.getWidth();
            height = this.mVideoSurfaceFrame.getHeight();
        } else {
            DisplayManager displayManager2 = this.mDisplayManager;
            if (displayManager2 == null || displayManager2.getPresentation() == null || this.mDisplayManager.getPresentation().getWindow() == null) {
                return;
            }
            width = this.mDisplayManager.getPresentation().getWindow().getDecorView().getWidth();
            height = this.mDisplayManager.getPresentation().getWindow().getDecorView().getHeight();
        }
        if (width * height == 0) {
            Log.e(TAG, "Invalid surface size");
            return;
        }
        this.mMediaPlayer.getVLCVout().setWindowSize(width, height);
        View view = this.mVideoSurface;
        if (view == null) {
            view = this.mVideoTexture;
        }
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (this.mVideoWidth * this.mVideoHeight == 0 || (AndroidUtil.isNougatOrLater && activityResolveActivity != null && activityResolveActivity.isInPictureInPictureMode())) {
            layoutParams.width = -1;
            layoutParams.height = -1;
            view.setLayoutParams(layoutParams);
            SurfaceView surfaceView = this.mSubtitlesSurface;
            if (surfaceView != null) {
                surfaceView.setLayoutParams(layoutParams);
            }
            ViewGroup.LayoutParams layoutParams2 = this.mVideoSurfaceFrame.getLayoutParams();
            layoutParams2.width = -1;
            layoutParams2.height = -1;
            this.mVideoSurfaceFrame.setLayoutParams(layoutParams2);
            if (this.mVideoWidth * this.mVideoHeight == 0) {
                changeMediaPlayerLayout(width, height);
                return;
            }
            return;
        }
        if (layoutParams.width == layoutParams.height && layoutParams.width == -1) {
            this.mMediaPlayer.setAspectRatio(null);
            this.mMediaPlayer.setScale(0.0f);
        }
        double d2 = width;
        double d3 = height;
        boolean z3 = this.mVideoSurfaceFrame.getResources().getConfiguration().orientation == 1;
        if (this.mMediaPlayer.useOrientationFromBounds().booleanValue()) {
            z3 = height > width;
        }
        if (z2 && z3) {
            z = true;
        }
        if ((width > height && z) || (width < height && !z)) {
            d2 = d3;
            d3 = d2;
        }
        int i = this.mVideoSarDen;
        int i2 = this.mVideoSarNum;
        if (i == i2) {
            int i3 = this.mVideoVisibleWidth;
            d = i3;
            dFloatValue = ((double) i3) / ((double) this.mVideoVisibleHeight);
        } else {
            d = (((double) this.mVideoVisibleWidth) * ((double) i2)) / ((double) i);
            dFloatValue = d / ((double) this.mVideoVisibleHeight);
        }
        double d4 = d2 / d3;
        int i4 = AnonymousClass2.$SwitchMap$org$videolan$libvlc$MediaPlayer$ScaleType[this.mCurrentScaleType.ordinal()];
        if (i4 != 1) {
            if (i4 != 2) {
                if (i4 != 3) {
                    if (i4 == 12) {
                        d3 = this.mVideoVisibleHeight;
                        d2 = d;
                    } else {
                        dFloatValue = this.mCurrentScaleType.getRatio().floatValue();
                        if (d4 < dFloatValue) {
                            d3 = d2 / dFloatValue;
                        } else {
                            d2 = d3 * dFloatValue;
                        }
                    }
                }
            } else if (d4 >= dFloatValue) {
                d3 = d2 / dFloatValue;
            } else {
                d2 = d3 * dFloatValue;
            }
        } else if (d4 < dFloatValue) {
            d3 = d2 / dFloatValue;
        } else {
            d2 = d3 * dFloatValue;
        }
        layoutParams.width = (int) Math.ceil((d2 * ((double) this.mVideoWidth)) / ((double) this.mVideoVisibleWidth));
        layoutParams.height = (int) Math.ceil((d3 * ((double) this.mVideoHeight)) / ((double) this.mVideoVisibleHeight));
        view.setLayoutParams(layoutParams);
        SurfaceView surfaceView2 = this.mSubtitlesSurface;
        if (surfaceView2 != null) {
            surfaceView2.setLayoutParams(layoutParams);
        }
        view.invalidate();
        SurfaceView surfaceView3 = this.mSubtitlesSurface;
        if (surfaceView3 != null) {
            surfaceView3.invalidate();
        }
    }

    @Override // org.videolan.libvlc.interfaces.IVLCVout.OnNewVideoLayoutListener
    public void onNewVideoLayout(IVLCVout iVLCVout, int i, int i2, int i3, int i4, int i5, int i6) {
        this.mVideoWidth = i;
        this.mVideoHeight = i2;
        this.mVideoVisibleWidth = i3;
        this.mVideoVisibleHeight = i4;
        this.mVideoSarNum = i5;
        this.mVideoSarDen = i6;
        updateVideoSurfaces();
    }

    void setVideoScale(MediaPlayer.ScaleType scaleType) {
        this.mCurrentScaleType = scaleType;
        updateVideoSurfaces();
    }

    MediaPlayer.ScaleType getVideoScale() {
        return this.mCurrentScaleType;
    }
}
