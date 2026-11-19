package com.movieboxpro.android.player.vlc;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import androidx.core.view.ViewCompat;
import com.alibaba.fastjson.asm.Opcodes;
import com.dl7.player.media.IMediaController;
import com.dl7.player.media.IRenderView;
import com.dl7.player.media.MediaPlayerCompat;
import com.dl7.player.media.MediaPlayerParams;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.listener.PlayListener;
import com.movieboxpro.android.player.IjkPlayerView;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.VlcPlayer;
import com.movieboxpro.android.utils.VlcPlayerManager;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.util.VLCVideoLayout;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* JADX INFO: loaded from: classes5.dex */
public class VlcVideoView extends FrameLayout implements MediaController.MediaPlayerControl {
    public static final int RENDER_NONE = 0;
    public static final int RENDER_SURFACE_VIEW = 1;
    public static final int RENDER_TEXTURE_VIEW = 2;
    private static final int[] s_allAspectRatio = {0, 1, 2, 4, 5};
    private String TAG;
    private boolean firstStart;
    private Context mAppContext;
    private boolean mCanPause;
    private boolean mCanSeekBack;
    private boolean mCanSeekForward;
    private int mCurrentAspectRatio;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private Map<String, String> mHeaders;
    private boolean mIsMediaCodecHandleResolutionChange;
    private boolean mIsNormalScreen;
    private boolean mIsUsingMediaCodec;
    private boolean mIsUsingMediaCodecAutoRotate;
    private boolean mIsUsingMediaDataSource;
    private boolean mIsUsingOpenSLES;
    private IMediaController mMediaController;
    private VlcPlayer mMediaPlayer;
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    private Matrix mOriginalMatrix;
    private String mPixelFormat;
    private FrameLayout mPlayerContainer;
    private long mPrepareEndTime;
    private long mPrepareStartTime;
    private IRenderView mRenderView;
    private Matrix mSaveMatrix;
    private int mScreenOrHeight;
    private int mScreenOrWidth;
    private long mSeekEndTime;
    private long mSeekStartTime;
    private int mSeekWhenPrepared;
    private int mSurfaceHeight;
    private IRenderView.ISurfaceHolder mSurfaceHolder;
    protected SurfaceTexture mSurfaceTexture;
    private int mSurfaceWidth;
    private int mTargetState;
    private TextureView mTextureView;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private float mVideoScale;
    private int mVideoTargetRotationDegree;
    private int mVideoWidth;
    private PlayListener playListener;
    private VideogetStartInfo videogetStartInfo;
    protected VLCVideoLayout vlcVideoLayout;

    public interface VideogetStartInfo {
        void getVideoStart();
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    public VlcVideoView(Context context) {
        super(context);
        this.TAG = "TTAG";
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mIsUsingMediaCodec = false;
        this.mPixelFormat = "";
        this.mVideoScale = 1.0f;
        this.mIsNormalScreen = true;
        this.mPrepareStartTime = 0L;
        this.mPrepareEndTime = 0L;
        this.mSeekStartTime = 0L;
        this.mSeekEndTime = 0L;
        this.firstStart = true;
        this.mCurrentAspectRatio = 0;
        initVideoView(context);
    }

    public VlcVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.TAG = "TTAG";
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mIsUsingMediaCodec = false;
        this.mPixelFormat = "";
        this.mVideoScale = 1.0f;
        this.mIsNormalScreen = true;
        this.mPrepareStartTime = 0L;
        this.mPrepareEndTime = 0L;
        this.mSeekStartTime = 0L;
        this.mSeekEndTime = 0L;
        this.firstStart = true;
        this.mCurrentAspectRatio = 0;
        initVideoView(context);
    }

    public VlcVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.TAG = "TTAG";
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mIsUsingMediaCodec = false;
        this.mPixelFormat = "";
        this.mVideoScale = 1.0f;
        this.mIsNormalScreen = true;
        this.mPrepareStartTime = 0L;
        this.mPrepareEndTime = 0L;
        this.mSeekStartTime = 0L;
        this.mSeekEndTime = 0L;
        this.firstStart = true;
        this.mCurrentAspectRatio = 0;
        initVideoView(context);
    }

    public VlcVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.TAG = "TTAG";
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
        this.mSurfaceHolder = null;
        this.mMediaPlayer = null;
        this.mCanPause = true;
        this.mCanSeekBack = true;
        this.mCanSeekForward = true;
        this.mIsUsingMediaCodec = false;
        this.mPixelFormat = "";
        this.mVideoScale = 1.0f;
        this.mIsNormalScreen = true;
        this.mPrepareStartTime = 0L;
        this.mPrepareEndTime = 0L;
        this.mSeekStartTime = 0L;
        this.mSeekEndTime = 0L;
        this.firstStart = true;
        this.mCurrentAspectRatio = 0;
        initVideoView(context);
    }

    public ArrayList<MediaPlayer.TrackDescription> getAudioTracks() {
        if (this.mMediaPlayer != null) {
            return new ArrayList<>(this.mMediaPlayer.getAudioTracks());
        }
        return new ArrayList<>();
    }

    public int getCurrAudioId() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            return vlcPlayer.getCurrAudioIndex();
        }
        return 0;
    }

    public void addAudioTrack(String url) {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.addAudioTrack(url);
        }
    }

    public void setAudioDelay(int time) {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.setAudioDelay(time);
        }
    }

    public long getAudioDelay() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            return vlcPlayer.getAudioDelay();
        }
        return 0L;
    }

    public void setAudioTrack(int id) {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.setTrackInfo(id);
        }
    }

    public void setVolume(int volume) {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.setVolume(volume);
        }
    }

    private void initVideoView(Context context) {
        this.playListener = new PlayListener() { // from class: com.movieboxpro.android.player.vlc.VlcVideoView.1
            @Override // com.movieboxpro.android.listener.PlayListener
            public void onPause() {
            }

            @Override // com.movieboxpro.android.listener.PlayListener
            public void onPositionChanged() {
            }

            @Override // com.movieboxpro.android.listener.PlayListener
            public void onStart() {
                VlcVideoView.this.firstStart = true;
                if (VlcVideoView.this.mOnInfoListener != null) {
                    VlcVideoView.this.mOnInfoListener.onInfo(null, 701, 1);
                }
            }

            @Override // com.movieboxpro.android.listener.PlayListener
            public void onPlay() {
                if (VlcVideoView.this.mOnInfoListener != null) {
                    VlcVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_PLAYING, 1);
                }
            }

            @Override // com.movieboxpro.android.listener.PlayListener
            public void onBuffer(float percent) {
                if (percent < 100.0f) {
                    if (VlcVideoView.this.mOnInfoListener != null) {
                        int i = (int) percent;
                        VlcVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_PREPARING, i);
                        VlcVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_BUFFER, i);
                        return;
                    }
                    return;
                }
                if (VlcVideoView.this.mOnInfoListener != null) {
                    VlcVideoView.this.mOnInfoListener.onInfo(null, 702, 1);
                    if (VlcVideoView.this.firstStart) {
                        VlcVideoView.this.firstStart = false;
                        long j = PrefsUtils.getInstance().getLong(Constant.Prefs.AUDIO_DELAY);
                        if (j != 0) {
                            VlcVideoView.this.mMediaPlayer.setAudioDelay(j);
                        }
                    }
                }
            }

            @Override // com.movieboxpro.android.listener.PlayListener
            public void onComplete() {
                if (VlcVideoView.this.mOnInfoListener != null) {
                    VlcVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_COMPLETED, 1);
                }
            }

            @Override // com.movieboxpro.android.listener.PlayListener
            public void onError() {
                if (VlcVideoView.this.mOnInfoListener != null) {
                    VlcVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_ERROR, 1);
                }
            }
        };
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.mPlayerContainer = frameLayout;
        frameLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        addView(this.mPlayerContainer, new FrameLayout.LayoutParams(-1, -1));
        VlcPlayer vlcPlayer = VlcPlayerManager.INSTANCE.get(getContext());
        this.mMediaPlayer = vlcPlayer;
        vlcPlayer.registerListener(this.playListener);
        this.mAppContext = context.getApplicationContext();
        this.mCurrentAspectRatio = IjkPlayerView.screenScales[PrefsUtils.getInstance().getInt(Constant.Prefs.SCREEN_SCALE, 0)];
        initRenders();
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
    }

    public void setEnableHardCodec(boolean enableHardCodec) {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.setEnableHardCodec(enableHardCodec);
        }
    }

    public void setRenderView() {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1, 17);
        VLCVideoLayout vLCVideoLayout = this.vlcVideoLayout;
        if (vLCVideoLayout != null) {
            this.mPlayerContainer.removeView(vLCVideoLayout);
        } else {
            this.vlcVideoLayout = new VLCVideoLayout(getContext());
        }
        this.mPlayerContainer.addView(this.vlcVideoLayout, 0, layoutParams);
        this.mMediaPlayer.attachView(this.vlcVideoLayout, (Activity) getContext());
    }

    public void setVideoRotation(int degree) {
        int i = this.mVideoRotationDegree + degree;
        this.mVideoTargetRotationDegree = i;
        this.mRenderView.setVideoRotation(i);
    }

    public Matrix getVideoTransform() {
        if (this.mOriginalMatrix == null) {
            this.mOriginalMatrix = this.mRenderView.getTransform();
        }
        return this.mRenderView.getTransform();
    }

    public void setVideoTransform(Matrix transform) {
        this.mRenderView.setTransform(transform);
    }

    public boolean adjustVideoView(float scale) {
        float f = this.mVideoScale * scale;
        this.mVideoScale = f;
        int i = this.mVideoTargetRotationDegree;
        int i2 = (i + 360) % 360;
        if (f == 1.0f && i2 == 0) {
            return false;
        }
        if (i2 > 315 || i2 <= 45) {
            this.mVideoRotationDegree = 0;
        } else if (i2 > 45 && i2 <= 135) {
            this.mVideoRotationDegree = 90;
        } else if (i2 > 135 && i2 <= 225) {
            this.mVideoRotationDegree = Opcodes.GETFIELD;
        } else if (i2 > 225 && i2 <= 315) {
            this.mVideoRotationDegree = MediaPlayer.Event.PausableChanged;
        } else {
            this.mVideoRotationDegree = 0;
        }
        int i3 = this.mVideoRotationDegree;
        final int i4 = i3 - i;
        this.mVideoTargetRotationDegree = i3;
        final Matrix videoTransform = getVideoTransform();
        if (this.mScreenOrWidth == 0 || this.mScreenOrHeight == 0) {
            this.mScreenOrWidth = this.mRenderView.getView().getWidth();
            this.mScreenOrHeight = this.mRenderView.getView().getHeight();
        }
        if (!this.mIsNormalScreen) {
            float f2 = this.mVideoScale;
            videoTransform.preScale(f2, f2);
            float f3 = this.mScreenOrWidth;
            float f4 = this.mVideoScale;
            videoTransform.postTranslate((f3 * (1.0f - f4)) / 2.0f, (this.mScreenOrHeight * (1.0f - f4)) / 2.0f);
            this.mRenderView.setTransform(videoTransform);
            this.mIsNormalScreen = true;
        } else {
            float[] fArr = new float[2];
            videoTransform.mapPoints(fArr);
            float f5 = this.mScreenOrWidth;
            float f6 = this.mVideoScale;
            final float f7 = ((f5 * (1.0f - f6)) / 2.0f) - fArr[0];
            final float f8 = ((this.mScreenOrHeight * (1.0f - f6)) / 2.0f) - fArr[1];
            if (this.mSaveMatrix == null) {
                this.mSaveMatrix = new Matrix();
            }
            ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(300L);
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.movieboxpro.android.player.vlc.VlcVideoView.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    VlcVideoView.this.mSaveMatrix.set(videoTransform);
                    VlcVideoView.this.mSaveMatrix.postTranslate(f7 * fFloatValue, f8 * fFloatValue);
                    VlcVideoView.this.mRenderView.setTransform(VlcVideoView.this.mSaveMatrix);
                    VlcVideoView.this.mRenderView.setVideoRotation((int) (VlcVideoView.this.mVideoRotationDegree - (i4 * (1.0f - fFloatValue))));
                }
            });
            duration.start();
        }
        return true;
    }

    public void resetVideoView(boolean isForever) {
        this.mIsNormalScreen = isForever;
        this.mVideoRotationDegree = 0;
        if (isForever) {
            this.mVideoTargetRotationDegree = 0;
            this.mVideoScale = 1.0f;
        }
        this.mRenderView.setTransform(this.mOriginalMatrix);
        this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
    }

    public void setRender() {
        setRenderView();
    }

    public void setVideoPath(String path) {
        setVideoURI(Uri.parse(path), 0, false);
    }

    public void setVideoURI(Uri uri, int startSeconds, boolean useSurfaceView) {
        setVideoURI(uri, null, startSeconds, useSurfaceView);
    }

    private void setVideoURI(Uri uri, Map<String, String> headers, int startSeconds, boolean useSurfaceView) {
        this.mUri = uri;
        this.mHeaders = headers;
        this.mSeekWhenPrepared = 0;
        openVideo(startSeconds, useSurfaceView);
        requestLayout();
        invalidate();
    }

    public Uri getUri() {
        return this.mUri;
    }

    public Bitmap getScreenshot() {
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            return iRenderView.getVideoScreenshot();
        }
        return null;
    }

    public void stopPlayback() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.stop();
            VlcPlayerManager.INSTANCE.releaseAndClear();
            this.mMediaPlayer = null;
            this.mCurrentState = MediaPlayerParams.STATE_IDLE;
            this.mTargetState = MediaPlayerParams.STATE_IDLE;
            _notifyMediaStatus();
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    public void switchQuality(final String url, final int startTime, final boolean useSurfaceView) {
        if (this.mMediaPlayer != null) {
            Observable.just("").map(new Function<String, String>() { // from class: com.movieboxpro.android.player.vlc.VlcVideoView.4
                @Override // io.reactivex.functions.Function
                public String apply(String s) throws Exception {
                    if (VlcVideoView.this.mMediaPlayer != null) {
                        VlcVideoView.this.mMediaPlayer.reset();
                        return "";
                    }
                    return "";
                }
            }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.player.vlc.VlcVideoView.3
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                }

                @Override // io.reactivex.Observer
                public void onNext(String s) {
                    if (VlcVideoView.this.mMediaPlayer != null) {
                        VlcVideoView.this.mMediaPlayer.setDataSource(url, startTime, useSurfaceView);
                    }
                }
            });
        }
    }

    private void openVideo(int startSeconds, boolean useSurfaceView) {
        this.mCurrentBufferPercentage = 0;
        this.mMediaPlayer.setDataSource(this.mUri.toString(), startSeconds, useSurfaceView);
        this.mPrepareStartTime = System.currentTimeMillis();
        this.mMediaPlayer.prepareAsync();
        this.mCurrentState = MediaPlayerParams.STATE_PREPARING;
        _notifyMediaStatus();
    }

    public void setMediaController(IMediaController controller) {
        IMediaController iMediaController = this.mMediaController;
        if (iMediaController != null) {
            iMediaController.hide();
        }
        this.mMediaController = controller;
        attachMediaController();
    }

    public long getTcpSpeed() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            return vlcPlayer.getTcpSpeed();
        }
        return 0L;
    }

    private void attachMediaController() {
        IMediaController iMediaController;
        if (this.mMediaPlayer == null || (iMediaController = this.mMediaController) == null) {
            return;
        }
        iMediaController.setMediaPlayer(this);
        this.mMediaController.setAnchorView(getParent() instanceof View ? (View) getParent() : this);
        this.mMediaController.setEnabled(isInPlaybackState());
    }

    public VlcPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    private void _notifyMediaStatus() {
        IMediaPlayer.OnInfoListener onInfoListener = this.mOnInfoListener;
        if (onInfoListener != null) {
            onInfoListener.onInfo(null, this.mCurrentState, -1);
        }
    }

    public void setSpeed(float speed) {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.setSpeed(speed);
        }
    }

    public void getInfo(VideogetStartInfo videogetStartInfo) {
        this.videogetStartInfo = videogetStartInfo;
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener l) {
        this.mOnPreparedListener = l;
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener l) {
        this.mOnCompletionListener = l;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener l) {
        this.mOnErrorListener = l;
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener l) {
        this.mOnInfoListener = l;
    }

    private void bindSurfaceHolder(IMediaPlayer mp, IRenderView.ISurfaceHolder holder) {
        if (mp == null) {
            return;
        }
        if (holder == null) {
            mp.setDisplay(null);
        } else {
            holder.bindToMediaPlayer(mp);
        }
    }

    public void releaseWithoutStop() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.setDisplay(null);
        }
    }

    public void release(boolean cleartargetstate) {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.unRegisterListener(this.playListener);
            this.playListener = null;
            VlcPlayerManager.INSTANCE.releaseAndClear();
            this.mMediaPlayer = null;
            this.mCurrentState = MediaPlayerParams.STATE_IDLE;
            _notifyMediaStatus();
            if (cleartargetstate) {
                this.mTargetState = MediaPlayerParams.STATE_IDLE;
            }
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    public void destroy() {
        release(true);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisibility();
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent ev) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisibility();
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent ev) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.dispatchTouchEvent(ev);
    }

    private void toggleMediaControlsVisibility() {
        if (this.mMediaController.isShowing()) {
            this.mMediaController.hide();
        } else {
            this.mMediaController.show();
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void start() {
        Log.e("TTAG", "start " + isInPlaybackState());
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.start();
            this.mCurrentState = MediaPlayerParams.STATE_PLAYING;
            _notifyMediaStatus();
        }
        this.mTargetState = MediaPlayerParams.STATE_PLAYING;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            vlcPlayer.pause();
            this.mCurrentState = MediaPlayerParams.STATE_PAUSED;
            _notifyMediaStatus();
        }
        this.mTargetState = MediaPlayerParams.STATE_PAUSED;
    }

    public void suspend() {
        release(false);
    }

    public void resume() {
        openVideo(0, false);
    }

    public void reload() {
        this.mCurrentState = MediaPlayerParams.STATE_PLAYING;
        this.mTargetState = MediaPlayerParams.STATE_PLAYING;
    }

    public void retryPlayback() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            this.mCurrentState = MediaPlayerParams.STATE_PREPARING;
            this.mTargetState = MediaPlayerParams.STATE_PLAYING;
            this.mCurrentBufferPercentage = 0;
            vlcPlayer.retryPlayback();
            _notifyMediaStatus();
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            return (int) vlcPlayer.getDuration();
        }
        return -1;
    }

    public int getInterruptPosition() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            return (int) vlcPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            return (int) vlcPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int msec) {
        if (this.mMediaPlayer != null) {
            this.mSeekStartTime = System.currentTimeMillis();
            this.mMediaPlayer.seekTo(msec);
            this.mSeekWhenPrepared = msec;
        }
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer != null) {
            return vlcPlayer.isPlaying();
        }
        return false;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getBufferPercentage() {
        if (this.mMediaPlayer != null) {
            return this.mCurrentBufferPercentage;
        }
        return 0;
    }

    private boolean isInPlaybackState() {
        int i;
        return (this.mMediaPlayer == null || (i = this.mCurrentState) == 331 || i == 330 || i == 332) ? false : true;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canPause() {
        return this.mCanPause;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekBackward() {
        return this.mCanSeekBack;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean canSeekForward() {
        return this.mCanSeekForward;
    }

    public void setAspectRatio(int aspectRatio) {
        this.mCurrentAspectRatio = aspectRatio;
        this.mMediaPlayer.setVideoScale(aspectRatio);
    }

    private void initRenders() {
        setRender();
    }

    private String buildTimeMilli(long duration) {
        long j = duration / 1000;
        long j2 = j / 3600;
        long j3 = (j % 3600) / 60;
        long j4 = j % 60;
        if (duration <= 0) {
            return "--:--";
        }
        if (j2 >= 100) {
            return String.format(Locale.US, "%d:%02d:%02d", Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4));
        }
        return j2 > 0 ? String.format(Locale.US, "%02d:%02d:%02d", Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4)) : String.format(Locale.US, "%02d:%02d", Long.valueOf(j3), Long.valueOf(j4));
    }

    public ITrackInfo[] getTrackInfo() {
        VlcPlayer vlcPlayer = this.mMediaPlayer;
        if (vlcPlayer == null) {
            return null;
        }
        return vlcPlayer.getTrackInfo();
    }

    public void selectTrack(int stream) {
        MediaPlayerCompat.selectTrack(null, stream);
    }

    public void deselectTrack(int stream) {
        MediaPlayerCompat.deselectTrack(null, stream);
    }

    public int getSelectedTrack(int trackType) {
        return MediaPlayerCompat.getSelectedTrack(null, trackType);
    }
}
