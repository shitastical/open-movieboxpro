package com.movieboxpro.android.exo;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import androidx.media3.common.Tracks;
import androidx.media3.ui.PlayerView;
import com.alibaba.fastjson.asm.Opcodes;
import com.dl7.player.media.IMediaController;
import com.dl7.player.media.IRenderView;
import com.dl7.player.media.MediaPlayerParams;
import com.dueeeke.videoplayer.widget.ResizeSurfaceView;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.RefreshPlayUrlEvent;
import com.movieboxpro.android.player.IjkPlayerView;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.greenrobot.eventbus.EventBus;
import org.videolan.libvlc.MediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes5.dex */
public class ExoVideoView extends FrameLayout implements MediaController.MediaPlayerControl {
    public static final int RENDER_NONE = 0;
    public static final int RENDER_SURFACE_VIEW = 1;
    public static final int RENDER_TEXTURE_VIEW = 2;
    private static final int[] s_allAspectRatio = {0, 1, 2, 4, 5};
    private String TAG;
    private Context mAppContext;
    private IMediaPlayer.OnBufferingUpdateListener mBufferingUpdateListener;
    private boolean mCanPause;
    private boolean mCanSeekBack;
    private boolean mCanSeekForward;
    private int mCurrentAspectRatio;
    private int mCurrentBufferPercentage;
    private int mCurrentState;
    private boolean mEnableBackgroundPlay;
    private IMediaPlayer.OnErrorListener mErrorListener;
    private Map<String, String> mHeaders;
    private IMediaPlayer.OnInfoListener mInfoListener;
    private boolean mIsMediaCodecHandleResolutionChange;
    private boolean mIsNormalScreen;
    private boolean mIsUsingMediaCodec;
    private boolean mIsUsingMediaCodecAutoRotate;
    private boolean mIsUsingMediaDataSource;
    private boolean mIsUsingOpenSLES;
    private IMediaController mMediaController;
    private CustomExoPlayer mMediaPlayer;
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    private Matrix mOriginalMatrix;
    private String mPixelFormat;
    private long mPrepareEndTime;
    private long mPrepareStartTime;
    private IRenderView mRenderView;
    private Matrix mSaveMatrix;
    private int mScreenOrHeight;
    private int mScreenOrWidth;
    private IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener;
    private long mSeekEndTime;
    private long mSeekStartTime;
    private int mSeekWhenPrepared;
    IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    private int mSurfaceHeight;
    private ResizeSurfaceView mSurfaceView;
    private int mSurfaceWidth;
    private int mTargetState;
    private Uri mUri;
    private int mVideoHeight;
    private int mVideoRotationDegree;
    private int mVideoSarDen;
    private int mVideoSarNum;
    private float mVideoScale;
    private int mVideoTargetRotationDegree;
    private int mVideoWidth;
    private OnHlsVideoSizeChanged onHlsVideoSizeChanged;
    private PlayListener playListener;
    private PlayerView playerView;
    private boolean playingHlsMaster;
    private VideogetStartInfo videogetStartInfo;

    public interface OnHlsVideoSizeChanged {
        void onHlsVideoSizeChanged(int width, int height);
    }

    public interface VideogetStartInfo {
        void getVideoStart();
    }

    private void initBackground() {
    }

    private void initRenders() {
    }

    public void enterBackground() {
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    public void stopBackgroundPlay() {
    }

    public ExoVideoView(Context context) {
        super(context);
        this.TAG = "TTAG";
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
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
        this.playListener = new PlayListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.1
            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPause() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPositionChanged() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onStart() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mOnInfoListener.onInfo(null, 701, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPlay() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_PLAYING;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_PLAYING, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onBuffer(float percent) {
                if (ExoVideoView.this.mOnInfoListener == null || percent < 100.0f) {
                    return;
                }
                ExoVideoView.this.mCurrentState = 702;
                ExoVideoView.this.mOnInfoListener.onInfo(null, 702, 1);
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onComplete() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_COMPLETED;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_COMPLETED, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onError(String msg) {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_ERROR, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onVideoSizeChanged(int width, int height) {
                ExoVideoView.this.mVideoWidth = width;
                ExoVideoView.this.mVideoHeight = height;
                if (ExoVideoView.this.mVideoWidth != 0 && ExoVideoView.this.mVideoHeight != 0 && ExoVideoView.this.mSurfaceView != null) {
                    ExoVideoView.this.mSurfaceView.setScreenScale(ExoVideoView.this.mCurrentAspectRatio);
                    ExoVideoView.this.mSurfaceView.setVideoSize(ExoVideoView.this.mVideoWidth, ExoVideoView.this.mVideoHeight);
                }
                if (ExoVideoView.this.onHlsVideoSizeChanged != null) {
                    ExoVideoView.this.onHlsVideoSizeChanged.onHlsVideoSizeChanged(width, height);
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                ExoVideoView.this.mVideoWidth = mp.getVideoWidth();
                ExoVideoView.this.mVideoHeight = mp.getVideoHeight();
                ExoVideoView.this.mVideoSarNum = mp.getVideoSarNum();
                ExoVideoView.this.mVideoSarDen = mp.getVideoSarDen();
                if (ExoVideoView.this.mVideoWidth == 0 || ExoVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (ExoVideoView.this.mRenderView != null) {
                    ExoVideoView.this.mRenderView.setVideoSize(ExoVideoView.this.mVideoWidth, ExoVideoView.this.mVideoHeight);
                    ExoVideoView.this.mRenderView.setVideoSampleAspectRatio(ExoVideoView.this.mVideoSarNum, ExoVideoView.this.mVideoSarDen);
                }
                ExoVideoView.this.requestLayout();
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
                }
                if (arg1 == 3) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (ExoVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    ExoVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (arg1 == 901) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (arg1 == 902) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (arg1 == 10001) {
                    ExoVideoView.this.mVideoRotationDegree = arg2;
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (ExoVideoView.this.mRenderView == null) {
                        return true;
                    }
                    ExoVideoView.this.mRenderView.setVideoRotation(arg2);
                    return true;
                }
                if (arg1 != 10002) {
                    switch (arg1) {
                        case 700:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case 701:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case 702:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        default:
                            switch (arg1) {
                                case 800:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                    break;
                                case 801:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                    break;
                                case 802:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                    break;
                            }
                            break;
                    }
                    return true;
                }
                Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer mp, final int framework_err, final int impl_err) {
                if (mp.getDataSource() == null) {
                    return true;
                }
                Observable.just(mp.getDataSource()).map(new Function<String, Integer>(this) { // from class: com.movieboxpro.android.exo.ExoVideoView.6.2
                    @Override // io.reactivex.functions.Function
                    public Integer apply(String s) throws Exception {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(s).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        return Integer.valueOf(httpURLConnection.getResponseCode());
                    }
                }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Integer>() { // from class: com.movieboxpro.android.exo.ExoVideoView.6.1
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable d) {
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(Integer integer) {
                        if (integer.intValue() == 410) {
                            EventBus.getDefault().post(new RefreshPlayUrlEvent());
                            return;
                        }
                        ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this._notifyMediaStatus();
                        if (ExoVideoView.this.mMediaController != null) {
                            ExoVideoView.this.mMediaController.hide();
                        }
                        if (ExoVideoView.this.mOnErrorListener != null) {
                            ExoVideoView.this.mOnErrorListener.onError(null, framework_err, impl_err);
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable e) {
                        ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this._notifyMediaStatus();
                        if (ExoVideoView.this.mMediaController != null) {
                            ExoVideoView.this.mMediaController.hide();
                        }
                        if (ExoVideoView.this.mOnErrorListener != null) {
                            ExoVideoView.this.mOnErrorListener.onError(null, framework_err, impl_err);
                        }
                    }
                });
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                ExoVideoView.this.mCurrentBufferPercentage = percent;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer mp) {
                ExoVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mCurrentAspectRatio = 0;
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public ExoVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.TAG = "TTAG";
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
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
        this.playListener = new PlayListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.1
            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPause() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPositionChanged() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onStart() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mOnInfoListener.onInfo(null, 701, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPlay() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_PLAYING;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_PLAYING, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onBuffer(float percent) {
                if (ExoVideoView.this.mOnInfoListener == null || percent < 100.0f) {
                    return;
                }
                ExoVideoView.this.mCurrentState = 702;
                ExoVideoView.this.mOnInfoListener.onInfo(null, 702, 1);
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onComplete() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_COMPLETED;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_COMPLETED, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onError(String msg) {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_ERROR, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onVideoSizeChanged(int width, int height) {
                ExoVideoView.this.mVideoWidth = width;
                ExoVideoView.this.mVideoHeight = height;
                if (ExoVideoView.this.mVideoWidth != 0 && ExoVideoView.this.mVideoHeight != 0 && ExoVideoView.this.mSurfaceView != null) {
                    ExoVideoView.this.mSurfaceView.setScreenScale(ExoVideoView.this.mCurrentAspectRatio);
                    ExoVideoView.this.mSurfaceView.setVideoSize(ExoVideoView.this.mVideoWidth, ExoVideoView.this.mVideoHeight);
                }
                if (ExoVideoView.this.onHlsVideoSizeChanged != null) {
                    ExoVideoView.this.onHlsVideoSizeChanged.onHlsVideoSizeChanged(width, height);
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                ExoVideoView.this.mVideoWidth = mp.getVideoWidth();
                ExoVideoView.this.mVideoHeight = mp.getVideoHeight();
                ExoVideoView.this.mVideoSarNum = mp.getVideoSarNum();
                ExoVideoView.this.mVideoSarDen = mp.getVideoSarDen();
                if (ExoVideoView.this.mVideoWidth == 0 || ExoVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (ExoVideoView.this.mRenderView != null) {
                    ExoVideoView.this.mRenderView.setVideoSize(ExoVideoView.this.mVideoWidth, ExoVideoView.this.mVideoHeight);
                    ExoVideoView.this.mRenderView.setVideoSampleAspectRatio(ExoVideoView.this.mVideoSarNum, ExoVideoView.this.mVideoSarDen);
                }
                ExoVideoView.this.requestLayout();
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
                }
                if (arg1 == 3) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (ExoVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    ExoVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (arg1 == 901) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (arg1 == 902) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (arg1 == 10001) {
                    ExoVideoView.this.mVideoRotationDegree = arg2;
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (ExoVideoView.this.mRenderView == null) {
                        return true;
                    }
                    ExoVideoView.this.mRenderView.setVideoRotation(arg2);
                    return true;
                }
                if (arg1 != 10002) {
                    switch (arg1) {
                        case 700:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case 701:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case 702:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        default:
                            switch (arg1) {
                                case 800:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                    break;
                                case 801:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                    break;
                                case 802:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                    break;
                            }
                            break;
                    }
                    return true;
                }
                Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer mp, final int framework_err, final int impl_err) {
                if (mp.getDataSource() == null) {
                    return true;
                }
                Observable.just(mp.getDataSource()).map(new Function<String, Integer>(this) { // from class: com.movieboxpro.android.exo.ExoVideoView.6.2
                    @Override // io.reactivex.functions.Function
                    public Integer apply(String s) throws Exception {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(s).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        return Integer.valueOf(httpURLConnection.getResponseCode());
                    }
                }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Integer>() { // from class: com.movieboxpro.android.exo.ExoVideoView.6.1
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable d) {
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(Integer integer) {
                        if (integer.intValue() == 410) {
                            EventBus.getDefault().post(new RefreshPlayUrlEvent());
                            return;
                        }
                        ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this._notifyMediaStatus();
                        if (ExoVideoView.this.mMediaController != null) {
                            ExoVideoView.this.mMediaController.hide();
                        }
                        if (ExoVideoView.this.mOnErrorListener != null) {
                            ExoVideoView.this.mOnErrorListener.onError(null, framework_err, impl_err);
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable e) {
                        ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this._notifyMediaStatus();
                        if (ExoVideoView.this.mMediaController != null) {
                            ExoVideoView.this.mMediaController.hide();
                        }
                        if (ExoVideoView.this.mOnErrorListener != null) {
                            ExoVideoView.this.mOnErrorListener.onError(null, framework_err, impl_err);
                        }
                    }
                });
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                ExoVideoView.this.mCurrentBufferPercentage = percent;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer mp) {
                ExoVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mCurrentAspectRatio = 0;
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public ExoVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.TAG = "TTAG";
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
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
        this.playListener = new PlayListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.1
            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPause() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPositionChanged() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onStart() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mOnInfoListener.onInfo(null, 701, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPlay() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_PLAYING;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_PLAYING, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onBuffer(float percent) {
                if (ExoVideoView.this.mOnInfoListener == null || percent < 100.0f) {
                    return;
                }
                ExoVideoView.this.mCurrentState = 702;
                ExoVideoView.this.mOnInfoListener.onInfo(null, 702, 1);
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onComplete() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_COMPLETED;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_COMPLETED, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onError(String msg) {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_ERROR, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onVideoSizeChanged(int width, int height) {
                ExoVideoView.this.mVideoWidth = width;
                ExoVideoView.this.mVideoHeight = height;
                if (ExoVideoView.this.mVideoWidth != 0 && ExoVideoView.this.mVideoHeight != 0 && ExoVideoView.this.mSurfaceView != null) {
                    ExoVideoView.this.mSurfaceView.setScreenScale(ExoVideoView.this.mCurrentAspectRatio);
                    ExoVideoView.this.mSurfaceView.setVideoSize(ExoVideoView.this.mVideoWidth, ExoVideoView.this.mVideoHeight);
                }
                if (ExoVideoView.this.onHlsVideoSizeChanged != null) {
                    ExoVideoView.this.onHlsVideoSizeChanged.onHlsVideoSizeChanged(width, height);
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                ExoVideoView.this.mVideoWidth = mp.getVideoWidth();
                ExoVideoView.this.mVideoHeight = mp.getVideoHeight();
                ExoVideoView.this.mVideoSarNum = mp.getVideoSarNum();
                ExoVideoView.this.mVideoSarDen = mp.getVideoSarDen();
                if (ExoVideoView.this.mVideoWidth == 0 || ExoVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (ExoVideoView.this.mRenderView != null) {
                    ExoVideoView.this.mRenderView.setVideoSize(ExoVideoView.this.mVideoWidth, ExoVideoView.this.mVideoHeight);
                    ExoVideoView.this.mRenderView.setVideoSampleAspectRatio(ExoVideoView.this.mVideoSarNum, ExoVideoView.this.mVideoSarDen);
                }
                ExoVideoView.this.requestLayout();
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
                }
                if (arg1 == 3) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (ExoVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    ExoVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (arg1 == 901) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (arg1 == 902) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (arg1 == 10001) {
                    ExoVideoView.this.mVideoRotationDegree = arg2;
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (ExoVideoView.this.mRenderView == null) {
                        return true;
                    }
                    ExoVideoView.this.mRenderView.setVideoRotation(arg2);
                    return true;
                }
                if (arg1 != 10002) {
                    switch (arg1) {
                        case 700:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case 701:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case 702:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        default:
                            switch (arg1) {
                                case 800:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                    break;
                                case 801:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                    break;
                                case 802:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                    break;
                            }
                            break;
                    }
                    return true;
                }
                Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer mp, final int framework_err, final int impl_err) {
                if (mp.getDataSource() == null) {
                    return true;
                }
                Observable.just(mp.getDataSource()).map(new Function<String, Integer>(this) { // from class: com.movieboxpro.android.exo.ExoVideoView.6.2
                    @Override // io.reactivex.functions.Function
                    public Integer apply(String s) throws Exception {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(s).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        return Integer.valueOf(httpURLConnection.getResponseCode());
                    }
                }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Integer>() { // from class: com.movieboxpro.android.exo.ExoVideoView.6.1
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable d) {
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(Integer integer) {
                        if (integer.intValue() == 410) {
                            EventBus.getDefault().post(new RefreshPlayUrlEvent());
                            return;
                        }
                        ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this._notifyMediaStatus();
                        if (ExoVideoView.this.mMediaController != null) {
                            ExoVideoView.this.mMediaController.hide();
                        }
                        if (ExoVideoView.this.mOnErrorListener != null) {
                            ExoVideoView.this.mOnErrorListener.onError(null, framework_err, impl_err);
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable e) {
                        ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this._notifyMediaStatus();
                        if (ExoVideoView.this.mMediaController != null) {
                            ExoVideoView.this.mMediaController.hide();
                        }
                        if (ExoVideoView.this.mOnErrorListener != null) {
                            ExoVideoView.this.mOnErrorListener.onError(null, framework_err, impl_err);
                        }
                    }
                });
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                ExoVideoView.this.mCurrentBufferPercentage = percent;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer mp) {
                ExoVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mCurrentAspectRatio = 0;
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public ExoVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.TAG = "TTAG";
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
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
        this.playListener = new PlayListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.1
            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPause() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPositionChanged() {
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onStart() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mOnInfoListener.onInfo(null, 701, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onPlay() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_PLAYING;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_PLAYING, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onBuffer(float percent) {
                if (ExoVideoView.this.mOnInfoListener == null || percent < 100.0f) {
                    return;
                }
                ExoVideoView.this.mCurrentState = 702;
                ExoVideoView.this.mOnInfoListener.onInfo(null, 702, 1);
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onComplete() {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_COMPLETED;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_COMPLETED, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onError(String msg) {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                    ExoVideoView.this.mOnInfoListener.onInfo(null, MediaPlayerParams.STATE_ERROR, 1);
                }
            }

            @Override // com.movieboxpro.android.exo.PlayListener
            public void onVideoSizeChanged(int width, int height) {
                ExoVideoView.this.mVideoWidth = width;
                ExoVideoView.this.mVideoHeight = height;
                if (ExoVideoView.this.mVideoWidth != 0 && ExoVideoView.this.mVideoHeight != 0 && ExoVideoView.this.mSurfaceView != null) {
                    ExoVideoView.this.mSurfaceView.setScreenScale(ExoVideoView.this.mCurrentAspectRatio);
                    ExoVideoView.this.mSurfaceView.setVideoSize(ExoVideoView.this.mVideoWidth, ExoVideoView.this.mVideoHeight);
                }
                if (ExoVideoView.this.onHlsVideoSizeChanged != null) {
                    ExoVideoView.this.onHlsVideoSizeChanged.onHlsVideoSizeChanged(width, height);
                }
            }
        };
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                ExoVideoView.this.mVideoWidth = mp.getVideoWidth();
                ExoVideoView.this.mVideoHeight = mp.getVideoHeight();
                ExoVideoView.this.mVideoSarNum = mp.getVideoSarNum();
                ExoVideoView.this.mVideoSarDen = mp.getVideoSarDen();
                if (ExoVideoView.this.mVideoWidth == 0 || ExoVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (ExoVideoView.this.mRenderView != null) {
                    ExoVideoView.this.mRenderView.setVideoSize(ExoVideoView.this.mVideoWidth, ExoVideoView.this.mVideoHeight);
                    ExoVideoView.this.mRenderView.setVideoSampleAspectRatio(ExoVideoView.this.mVideoSarNum, ExoVideoView.this.mVideoSarDen);
                }
                ExoVideoView.this.requestLayout();
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                if (ExoVideoView.this.mOnInfoListener != null) {
                    ExoVideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
                }
                if (arg1 == 3) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (ExoVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    ExoVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (arg1 == 901) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (arg1 == 902) {
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (arg1 == 10001) {
                    ExoVideoView.this.mVideoRotationDegree = arg2;
                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (ExoVideoView.this.mRenderView == null) {
                        return true;
                    }
                    ExoVideoView.this.mRenderView.setVideoRotation(arg2);
                    return true;
                }
                if (arg1 != 10002) {
                    switch (arg1) {
                        case 700:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case 701:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case 702:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                            Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        default:
                            switch (arg1) {
                                case 800:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                    break;
                                case 801:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                    break;
                                case 802:
                                    Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                    break;
                            }
                            break;
                    }
                    return true;
                }
                Log.d(ExoVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer mp, final int framework_err, final int impl_err) {
                if (mp.getDataSource() == null) {
                    return true;
                }
                Observable.just(mp.getDataSource()).map(new Function<String, Integer>(this) { // from class: com.movieboxpro.android.exo.ExoVideoView.6.2
                    @Override // io.reactivex.functions.Function
                    public Integer apply(String s) throws Exception {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(s).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        return Integer.valueOf(httpURLConnection.getResponseCode());
                    }
                }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Integer>() { // from class: com.movieboxpro.android.exo.ExoVideoView.6.1
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable d) {
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(Integer integer) {
                        if (integer.intValue() == 410) {
                            EventBus.getDefault().post(new RefreshPlayUrlEvent());
                            return;
                        }
                        ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this._notifyMediaStatus();
                        if (ExoVideoView.this.mMediaController != null) {
                            ExoVideoView.this.mMediaController.hide();
                        }
                        if (ExoVideoView.this.mOnErrorListener != null) {
                            ExoVideoView.this.mOnErrorListener.onError(null, framework_err, impl_err);
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable e) {
                        ExoVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        ExoVideoView.this._notifyMediaStatus();
                        if (ExoVideoView.this.mMediaController != null) {
                            ExoVideoView.this.mMediaController.hide();
                        }
                        if (ExoVideoView.this.mOnErrorListener != null) {
                            ExoVideoView.this.mOnErrorListener.onError(null, framework_err, impl_err);
                        }
                    }
                });
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                ExoVideoView.this.mCurrentBufferPercentage = percent;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer mp) {
                ExoVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mCurrentAspectRatio = 0;
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        this.mMediaPlayer.unRegisterListener(this.playListener);
        super.onDetachedFromWindow();
    }

    private void initVideoView(Context context) {
        this.mAppContext = context.getApplicationContext();
        this.mCurrentAspectRatio = IjkPlayerView.screenScales[PrefsUtils.getInstance().getInt(Constant.Prefs.SCREEN_SCALE, 0)];
        CustomExoPlayer customExoPlayer = new CustomExoPlayer(context);
        this.mMediaPlayer = customExoPlayer;
        customExoPlayer.initPlayer();
        this.mMediaPlayer.registerListener(this.playListener);
        initBackground();
        initPlayerView();
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
        _notifyMediaStatus();
    }

    private void initPlayerView() {
        this.playerView = new PlayerView(getContext());
        addView(this.playerView, 0, new FrameLayout.LayoutParams(-1, -1, 17));
        this.playerView.setUseController(false);
        this.playerView.setPlayer(this.mMediaPlayer.getPlayer());
    }

    private void addSurfaceView() {
        ResizeSurfaceView resizeSurfaceView = this.mSurfaceView;
        if (resizeSurfaceView != null) {
            removeView(resizeSurfaceView);
        }
        ResizeSurfaceView resizeSurfaceView2 = new ResizeSurfaceView(getContext());
        this.mSurfaceView = resizeSurfaceView2;
        SurfaceHolder holder = resizeSurfaceView2.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() { // from class: com.movieboxpro.android.exo.ExoVideoView.2
            @Override // android.view.SurfaceHolder.Callback
            public void surfaceCreated(SurfaceHolder holder2) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceDestroyed(SurfaceHolder holder2) {
            }

            @Override // android.view.SurfaceHolder.Callback
            public void surfaceChanged(SurfaceHolder holder2, int format, int width, int height) {
                if (ExoVideoView.this.mMediaPlayer != null) {
                    ExoVideoView.this.mMediaPlayer.setDisplay(holder2);
                }
            }
        });
        holder.setFormat(1);
        addView(this.mSurfaceView, 0, new FrameLayout.LayoutParams(-1, -1, 17));
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
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.movieboxpro.android.exo.ExoVideoView.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    ExoVideoView.this.mSaveMatrix.set(videoTransform);
                    ExoVideoView.this.mSaveMatrix.postTranslate(f7 * fFloatValue, f8 * fFloatValue);
                    ExoVideoView.this.mRenderView.setTransform(ExoVideoView.this.mSaveMatrix);
                    ExoVideoView.this.mRenderView.setVideoRotation((int) (ExoVideoView.this.mVideoRotationDegree - (i4 * (1.0f - fFloatValue))));
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

    public void switchQuality(String url, int startTime) {
        this.mMediaPlayer.stop();
        this.mMediaPlayer.setDataSource(url, startTime);
    }

    public void setVideoPath(String path) {
        setVideoURI(Uri.parse(path), 0);
    }

    public void setVideoURI(Uri uri, int startSeconds) {
        this.mUri = uri;
        this.mSeekWhenPrepared = startSeconds;
        openVideo();
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

    public void setSpeed(float speed) {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            customExoPlayer.setSpeed(speed);
        }
    }

    public void stopPlayback() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            customExoPlayer.stop();
            this.mMediaPlayer.release();
            this.mCurrentState = MediaPlayerParams.STATE_IDLE;
            this.mTargetState = MediaPlayerParams.STATE_IDLE;
            _notifyMediaStatus();
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    private void openVideo() {
        reset();
        ((AudioManager) this.mAppContext.getSystemService("audio")).requestAudioFocus(null, 3, 1);
        getContext();
        this.mCurrentBufferPercentage = 0;
        this.mMediaPlayer.setDataSource(this.mUri.toString(), this.mSeekWhenPrepared);
        this.mPrepareStartTime = System.currentTimeMillis();
        this.mMediaPlayer.prepareAsync();
        this.mCurrentState = MediaPlayerParams.STATE_PREPARING;
        attachMediaController();
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
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            return customExoPlayer.getTcpSpeed();
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

    public CustomExoPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _notifyMediaStatus() {
        IMediaPlayer.OnInfoListener onInfoListener = this.mOnInfoListener;
        if (onInfoListener != null) {
            onInfoListener.onInfo(null, this.mCurrentState, -1);
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

    public void release(boolean cleartargetstate) {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            customExoPlayer.reset();
            this.mMediaPlayer.release();
            this.mCurrentState = MediaPlayerParams.STATE_IDLE;
            _notifyMediaStatus();
            if (cleartargetstate) {
                this.mTargetState = MediaPlayerParams.STATE_IDLE;
            }
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    public void reset() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            customExoPlayer.reset();
            this.mCurrentState = MediaPlayerParams.STATE_IDLE;
            _notifyMediaStatus();
            this.mTargetState = MediaPlayerParams.STATE_IDLE;
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
        if (isInPlaybackState()) {
            this.mMediaPlayer.start();
            this.mCurrentState = MediaPlayerParams.STATE_PLAYING;
            _notifyMediaStatus();
        }
        this.mTargetState = MediaPlayerParams.STATE_PLAYING;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void pause() {
        if (isInPlaybackState() && this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentState = MediaPlayerParams.STATE_PAUSED;
            _notifyMediaStatus();
        }
        this.mTargetState = MediaPlayerParams.STATE_PAUSED;
    }

    public void resume() {
        openVideo();
    }

    public void reload() {
        this.mCurrentState = MediaPlayerParams.STATE_PLAYING;
        this.mTargetState = MediaPlayerParams.STATE_PLAYING;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getDuration() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getDuration();
        }
        return -1;
    }

    public int getVideoHeight() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            return customExoPlayer.getVideoSize()[1];
        }
        return 0;
    }

    public int getVideoWidth() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            return customExoPlayer.getVideoSize()[0];
        }
        return 0;
    }

    public int getInterruptPosition() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            return (int) customExoPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (this.mCurrentState != 336 && isInPlaybackState()) {
            return (int) this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int msec) {
        if (isInPlaybackState()) {
            this.mSeekStartTime = System.currentTimeMillis();
            this.mMediaPlayer.seekTo(msec);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = msec;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
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
        ResizeSurfaceView resizeSurfaceView = this.mSurfaceView;
        if (resizeSurfaceView != null) {
            resizeSurfaceView.setScreenScale(aspectRatio);
        }
        PlayerView playerView = this.playerView;
        if (playerView != null) {
            if (aspectRatio == 0) {
                playerView.setResizeMode(0);
            } else if (aspectRatio == 3) {
                playerView.setResizeMode(3);
            } else if (aspectRatio == 1) {
                playerView.setResizeMode(4);
            }
        }
    }

    public boolean isBackgroundPlayEnabled() {
        return this.mEnableBackgroundPlay;
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

    public int getCurrAudioIndex() {
        return this.mMediaPlayer.getCurrSelectAudioIndex();
    }

    public List<Tracks.Group> getTrackInfo() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer == null) {
            return null;
        }
        return customExoPlayer.getAudioTracks();
    }

    public Tracks.Group getCurrentAudioTrack() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer == null) {
            return null;
        }
        return customExoPlayer.getCurrentAudioTrack();
    }

    public ArrayList<Integer> getTrackIndices() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer == null) {
            return null;
        }
        return customExoPlayer.getTrackIndices();
    }

    public boolean isPlayingHlsMaster() {
        return this.playingHlsMaster;
    }

    public void setPlayingHlsMaster(boolean playingHlsMaster) {
        this.playingHlsMaster = playingHlsMaster;
    }

    public boolean pinVideoQuality(String quality) {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer == null) {
            return false;
        }
        return customExoPlayer.pinVideoQuality(quality);
    }

    public void clearVideoTrackOverrides() {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            customExoPlayer.clearVideoTrackOverrides();
        }
    }

    public void setPendingVideoQuality(String quality) {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            customExoPlayer.setPendingVideoQuality(quality);
        }
    }

    public void setOnVideoPinFailed(OnVideoPinFailed listener) {
        CustomExoPlayer customExoPlayer = this.mMediaPlayer;
        if (customExoPlayer != null) {
            customExoPlayer.setOnVideoPinFailed(listener);
        }
    }

    public void setOnHlsVideoSizeChanged(OnHlsVideoSizeChanged listener) {
        this.onHlsVideoSizeChanged = listener;
    }
}
