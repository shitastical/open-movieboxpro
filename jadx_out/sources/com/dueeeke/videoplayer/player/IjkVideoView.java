package com.dueeeke.videoplayer.player;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.view.ViewCompat;
import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener;
import com.dueeeke.videoplayer.util.PlayerUtils;
import com.dueeeke.videoplayer.widget.ResizeSurfaceView;
import com.dueeeke.videoplayer.widget.ResizeTextureView;
import java.util.Iterator;

/* JADX INFO: loaded from: classes4.dex */
public class IjkVideoView extends BaseIjkVideoView {
    private static final int FULLSCREEN_FLAGS = 4098;
    public static final int SCREEN_SCALE_16_9 = 1;
    public static final int SCREEN_SCALE_4_3 = 2;
    public static final int SCREEN_SCALE_CENTER_CROP = 5;
    public static final int SCREEN_SCALE_DEFAULT = 0;
    public static final int SCREEN_SCALE_MATCH_PARENT = 3;
    public static final int SCREEN_SCALE_MATCH_WIDTH_PARENT = 6;
    public static final int SCREEN_SCALE_ORIGINAL = 4;
    protected int mCurrentScreenScale;
    protected View mHideNavBarView;
    protected boolean mIsFullScreen;
    protected FrameLayout mPlayerContainer;
    protected SurfaceTexture mSurfaceTexture;
    protected ResizeSurfaceView mSurfaceView;
    protected ResizeTextureView mTextureView;
    protected int[] mVideoSize;

    @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onSeekComplete(long j, long j2) {
    }

    public IjkVideoView(Context context) {
        this(context, null);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentScreenScale = 0;
        this.mVideoSize = new int[]{0, 0};
        initView();
    }

    protected void initView() {
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.mPlayerContainer = frameLayout;
        frameLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        addView(this.mPlayerContainer, new FrameLayout.LayoutParams(-1, -1));
        View view = new View(getContext());
        this.mHideNavBarView = view;
        view.setSystemUiVisibility(4098);
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    protected void initPlayer() {
        super.initPlayer();
        addDisplay();
    }

    public void addDisplay() {
        if (this.mPlayerConfig.usingSurfaceView) {
            addSurfaceView();
        } else {
            addTextureView();
        }
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    protected void setPlayState(int i) {
        this.mCurrentPlayState = i;
        if (this.mVideoController != null) {
            this.mVideoController.setPlayState(i);
        }
        if (this.mOnVideoViewStateChangeListeners != null) {
            Iterator<OnVideoViewStateChangeListener> it = this.mOnVideoViewStateChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onPlayStateChanged(i);
            }
        }
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    protected void setPlayerState(int i) {
        this.mCurrentPlayerState = i;
        if (this.mVideoController != null) {
            this.mVideoController.setPlayerState(i);
        }
        if (this.mOnVideoViewStateChangeListeners != null) {
            Iterator<OnVideoViewStateChangeListener> it = this.mOnVideoViewStateChangeListeners.iterator();
            while (it.hasNext()) {
                it.next().onPlayerStateChanged(i);
            }
        }
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    protected void startPlay() {
        if (this.mPlayerConfig.addToPlayerManager) {
            VideoViewManager.instance().releaseVideoPlayer();
            VideoViewManager.instance().setCurrentVideoPlayer(this);
        }
        if (checkNetwork()) {
            return;
        }
        super.startPlay();
    }

    private void addSurfaceView() {
        this.mPlayerContainer.removeView(this.mSurfaceView);
        ResizeSurfaceView resizeSurfaceView = new ResizeSurfaceView(getContext());
        this.mSurfaceView = resizeSurfaceView;
        SurfaceHolder holder = resizeSurfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() { // from class: com.dueeeke.videoplayer.player.IjkVideoView.1
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                if (IjkVideoView.this.mMediaPlayer != null) {
                    IjkVideoView.this.mMediaPlayer.setDisplay(surfaceHolder);
                }
            }
        });
        holder.setFormat(1);
        this.mPlayerContainer.addView(this.mSurfaceView, 0, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    private void addTextureView() {
        this.mPlayerContainer.removeView(this.mTextureView);
        this.mSurfaceTexture = null;
        ResizeTextureView resizeTextureView = new ResizeTextureView(getContext());
        this.mTextureView = resizeTextureView;
        resizeTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() { // from class: com.dueeeke.videoplayer.player.IjkVideoView.2
            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
                if (IjkVideoView.this.mSurfaceTexture != null) {
                    IjkVideoView.this.mTextureView.setSurfaceTexture(IjkVideoView.this.mSurfaceTexture);
                } else {
                    IjkVideoView.this.mSurfaceTexture = surfaceTexture;
                    IjkVideoView.this.mMediaPlayer.setSurface(new Surface(surfaceTexture));
                }
            }

            @Override // android.view.TextureView.SurfaceTextureListener
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                return IjkVideoView.this.mSurfaceTexture == null;
            }
        });
        this.mPlayerContainer.addView(this.mTextureView, 0, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    protected boolean checkNetwork() {
        if (PlayerUtils.getNetworkType(getContext()) != 4 || IS_PLAY_ON_MOBILE_NETWORK) {
            return false;
        }
        if (this.mVideoController == null) {
            return true;
        }
        this.mVideoController.showStatusView();
        return true;
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView
    public void release() {
        super.release();
        this.mPlayerContainer.removeView(this.mTextureView);
        this.mPlayerContainer.removeView(this.mSurfaceView);
        SurfaceTexture surfaceTexture = this.mSurfaceTexture;
        if (surfaceTexture != null) {
            surfaceTexture.release();
            this.mSurfaceTexture = null;
        }
        this.mCurrentScreenScale = 0;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void startFullScreen() {
        Activity activityScanForActivity;
        if (this.mVideoController == null || (activityScanForActivity = PlayerUtils.scanForActivity(this.mVideoController.getContext())) == null || this.mIsFullScreen) {
            return;
        }
        PlayerUtils.hideActionBar(this.mVideoController.getContext());
        removeView(this.mHideNavBarView);
        addView(this.mHideNavBarView);
        activityScanForActivity.getWindow().setFlags(1024, 1024);
        this.mOrientationEventListener.enable();
        this.mIsFullScreen = true;
        setPlayerState(11);
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void stopFullScreen() {
        Activity activityScanForActivity;
        if (this.mVideoController == null || (activityScanForActivity = PlayerUtils.scanForActivity(this.mVideoController.getContext())) == null || !this.mIsFullScreen) {
            return;
        }
        if (!this.mPlayerConfig.mAutoRotate) {
            this.mOrientationEventListener.disable();
        }
        PlayerUtils.hideActionBar(this.mVideoController.getContext());
        removeView(this.mHideNavBarView);
        addView(this.mHideNavBarView);
        setSystemUiVisibility(5894);
        activityScanForActivity.getWindow().setFlags(1024, 1024);
        requestFocus();
        this.mIsFullScreen = false;
        setPlayerState(10);
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public boolean isFullScreen() {
        return this.mIsFullScreen;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void retry() {
        addDisplay();
        startPrepare(true);
    }

    @Override // com.dueeeke.videoplayer.player.BaseIjkVideoView, com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onInfo(int i, int i2) {
        ResizeTextureView resizeTextureView;
        super.onInfo(i, i2);
        if (i == 10001 && (resizeTextureView = this.mTextureView) != null) {
            resizeTextureView.setRotation(i2);
        }
    }

    @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onVideoSizeChanged(int i, int i2) {
        int[] iArr = this.mVideoSize;
        iArr[0] = i;
        iArr[1] = i2;
        if (this.mPlayerConfig.usingSurfaceView) {
            this.mSurfaceView.setScreenScale(this.mCurrentScreenScale);
            this.mSurfaceView.setVideoSize(i, i2);
        } else {
            this.mTextureView.setScreenScale(this.mCurrentScreenScale);
            this.mTextureView.setVideoSize(i, i2);
        }
    }

    @Override // android.view.View
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            this.mHideNavBarView.setSystemUiVisibility(4098);
        }
        if (isInPlaybackState()) {
            if (this.mPlayerConfig.mAutoRotate || this.mIsFullScreen) {
                if (z) {
                    postDelayed(new Runnable() { // from class: com.dueeeke.videoplayer.player.IjkVideoView.3
                        @Override // java.lang.Runnable
                        public void run() {
                            IjkVideoView.this.mOrientationEventListener.enable();
                        }
                    }, 800L);
                } else {
                    this.mOrientationEventListener.disable();
                }
            }
        }
    }

    public void setVideoController(BaseVideoController baseVideoController) {
        this.mPlayerContainer.removeView(this.mVideoController);
        this.mVideoController = baseVideoController;
        if (baseVideoController != null) {
            baseVideoController.setMediaPlayer(this);
            this.mPlayerContainer.addView(this.mVideoController, new FrameLayout.LayoutParams(-1, -1));
        }
    }

    public boolean onBackPressed() {
        return this.mVideoController != null && this.mVideoController.onBackPressed();
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void setScreenScale(int i) {
        this.mCurrentScreenScale = i;
        ResizeSurfaceView resizeSurfaceView = this.mSurfaceView;
        if (resizeSurfaceView != null) {
            resizeSurfaceView.setScreenScale(i);
            return;
        }
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            resizeTextureView.setScreenScale(i);
        }
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void setMirrorRotation(boolean z) {
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            resizeTextureView.setScaleX(z ? -1.0f : 1.0f);
        }
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public Bitmap doScreenShot() {
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            return resizeTextureView.getBitmap();
        }
        return null;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public int[] getVideoSize() {
        return this.mVideoSize;
    }

    @Override // android.view.View, com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void setRotation(float f) {
        ResizeTextureView resizeTextureView = this.mTextureView;
        if (resizeTextureView != null) {
            resizeTextureView.setRotation(f);
            this.mTextureView.requestLayout();
        }
        ResizeSurfaceView resizeSurfaceView = this.mSurfaceView;
        if (resizeSurfaceView != null) {
            resizeSurfaceView.setRotation(f);
            this.mSurfaceView.requestLayout();
        }
    }
}
