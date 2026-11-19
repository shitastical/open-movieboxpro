package com.movieboxpro.android.player;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import com.alibaba.fastjson.asm.Opcodes;
import com.dl7.player.media.FileMediaDataSource;
import com.dl7.player.media.IMediaController;
import com.dl7.player.media.IRenderView;
import com.dl7.player.media.MediaPlayerCompat;
import com.dl7.player.media.MediaPlayerParams;
import com.dl7.player.media.SurfaceRenderView;
import com.dl7.player.media.TextureRenderView;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.RefreshPlayUrlEvent;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.greenrobot.eventbus.EventBus;
import org.videolan.libvlc.MediaPlayer;
import tv.danmaku.ijk.media.player.AndroidMediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* JADX INFO: loaded from: classes5.dex */
public class IjkVideoView extends FrameLayout implements MediaController.MediaPlayerControl {
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
    private IMediaPlayer.OnCompletionListener mCompletionListener;
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
    private IjkMediaPlayer mMediaPlayer;
    private IMediaPlayer.OnCompletionListener mOnCompletionListener;
    private IMediaPlayer.OnErrorListener mOnErrorListener;
    private IMediaPlayer.OnInfoListener mOnInfoListener;
    private IMediaPlayer.OnPreparedListener mOnPreparedListener;
    private Matrix mOriginalMatrix;
    private String mPixelFormat;
    private long mPrepareEndTime;
    private long mPrepareStartTime;
    IMediaPlayer.OnPreparedListener mPreparedListener;
    private IRenderView mRenderView;
    IRenderView.IRenderCallback mSHCallback;
    private Matrix mSaveMatrix;
    private int mScreenOrHeight;
    private int mScreenOrWidth;
    private IMediaPlayer.OnSeekCompleteListener mSeekCompleteListener;
    private long mSeekEndTime;
    private long mSeekStartTime;
    private int mSeekWhenPrepared;
    IMediaPlayer.OnVideoSizeChangedListener mSizeChangedListener;
    private int mSurfaceHeight;
    private IRenderView.ISurfaceHolder mSurfaceHolder;
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
    private VideogetStartInfo videogetStartInfo;

    public interface VideogetStartInfo {
        void getVideoStart();
    }

    private void initBackground() {
    }

    public void enterBackground() {
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getAudioSessionId() {
        return 0;
    }

    public void stopBackgroundPlay() {
    }

    public IjkVideoView(Context context) {
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
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.movieboxpro.android.player.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                IjkVideoView.this.mVideoWidth = mp.getVideoWidth();
                IjkVideoView.this.mVideoHeight = mp.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = mp.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = mp.getVideoSarDen();
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.movieboxpro.android.player.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer mp) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_PREPARED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = mp.getVideoWidth();
                IjkVideoView.this.mVideoHeight = mp.getVideoHeight();
                int i = IjkVideoView.this.mSeekWhenPrepared;
                if (i != 0) {
                    IjkVideoView.this.seekTo(i);
                }
                if (IjkVideoView.this.mVideoWidth != 0 && IjkVideoView.this.mVideoHeight != 0) {
                    if (IjkVideoView.this.mRenderView != null) {
                        IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                        IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                        if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                            if (IjkVideoView.this.mTargetState == 334) {
                                IjkVideoView.this.start();
                                if (IjkVideoView.this.mMediaController != null) {
                                    IjkVideoView.this.mMediaController.show();
                                    return;
                                }
                                return;
                            }
                            if (IjkVideoView.this.isPlaying()) {
                                return;
                            }
                            if ((i != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                                IjkVideoView.this.mMediaController.show(0);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (IjkVideoView.this.mTargetState == 334) {
                    IjkVideoView.this.start();
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.movieboxpro.android.player.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer mp) {
                Log.w(IjkVideoView.this.TAG, "OnCompletionListener:");
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_COMPLETED;
                IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_COMPLETED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnCompletionListener != null) {
                    IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.player.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
                }
                if (arg1 == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (IjkVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    IjkVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (arg1 == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (arg1 == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (arg1 == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = arg2;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(arg2);
                    return true;
                }
                if (arg1 != 10002) {
                    switch (arg1) {
                        case 700:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case 701:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case 702:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        default:
                            switch (arg1) {
                                case 800:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                    break;
                                case 801:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                    break;
                                case 802:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                    break;
                            }
                            break;
                    }
                    return true;
                }
                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.player.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer mp, final int framework_err, final int impl_err) {
                if (mp.getDataSource() == null) {
                    return true;
                }
                Observable.just(mp.getDataSource()).map(new Function<String, Integer>(this) { // from class: com.movieboxpro.android.player.IjkVideoView.6.2
                    @Override // io.reactivex.functions.Function
                    public Integer apply(String s) throws Exception {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(s).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        return Integer.valueOf(httpURLConnection.getResponseCode());
                    }
                }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Integer>() { // from class: com.movieboxpro.android.player.IjkVideoView.6.1
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
                        IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this._notifyMediaStatus();
                        if (IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.hide();
                        }
                        if (IjkVideoView.this.mOnErrorListener != null) {
                            IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, framework_err, impl_err);
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable e) {
                        IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this._notifyMediaStatus();
                        if (IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.hide();
                        }
                        if (IjkVideoView.this.mOnErrorListener != null) {
                            IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, framework_err, impl_err);
                        }
                    }
                });
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.movieboxpro.android.player.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                IjkVideoView.this.mCurrentBufferPercentage = percent;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.movieboxpro.android.player.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer mp) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.movieboxpro.android.player.IjkVideoView.9
            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder holder, int format, int w, int h) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = w;
                IjkVideoView.this.mSurfaceHeight = h;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != w || IjkVideoView.this.mVideoHeight != h)) {
                    z = false;
                }
                if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder holder, int width, int height) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = holder;
                if (IjkVideoView.this.mMediaPlayer != null) {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, holder);
                } else {
                    IjkVideoView.this.openVideo();
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder holder) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.mCurrentAspectRatio = 0;
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attrs) {
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
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.movieboxpro.android.player.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                IjkVideoView.this.mVideoWidth = mp.getVideoWidth();
                IjkVideoView.this.mVideoHeight = mp.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = mp.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = mp.getVideoSarDen();
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.movieboxpro.android.player.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer mp) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_PREPARED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = mp.getVideoWidth();
                IjkVideoView.this.mVideoHeight = mp.getVideoHeight();
                int i = IjkVideoView.this.mSeekWhenPrepared;
                if (i != 0) {
                    IjkVideoView.this.seekTo(i);
                }
                if (IjkVideoView.this.mVideoWidth != 0 && IjkVideoView.this.mVideoHeight != 0) {
                    if (IjkVideoView.this.mRenderView != null) {
                        IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                        IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                        if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                            if (IjkVideoView.this.mTargetState == 334) {
                                IjkVideoView.this.start();
                                if (IjkVideoView.this.mMediaController != null) {
                                    IjkVideoView.this.mMediaController.show();
                                    return;
                                }
                                return;
                            }
                            if (IjkVideoView.this.isPlaying()) {
                                return;
                            }
                            if ((i != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                                IjkVideoView.this.mMediaController.show(0);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (IjkVideoView.this.mTargetState == 334) {
                    IjkVideoView.this.start();
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.movieboxpro.android.player.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer mp) {
                Log.w(IjkVideoView.this.TAG, "OnCompletionListener:");
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_COMPLETED;
                IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_COMPLETED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnCompletionListener != null) {
                    IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.player.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
                }
                if (arg1 == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (IjkVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    IjkVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (arg1 == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (arg1 == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (arg1 == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = arg2;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(arg2);
                    return true;
                }
                if (arg1 != 10002) {
                    switch (arg1) {
                        case 700:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case 701:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case 702:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        default:
                            switch (arg1) {
                                case 800:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                    break;
                                case 801:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                    break;
                                case 802:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                    break;
                            }
                            break;
                    }
                    return true;
                }
                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.player.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer mp, final int framework_err, final int impl_err) {
                if (mp.getDataSource() == null) {
                    return true;
                }
                Observable.just(mp.getDataSource()).map(new Function<String, Integer>(this) { // from class: com.movieboxpro.android.player.IjkVideoView.6.2
                    @Override // io.reactivex.functions.Function
                    public Integer apply(String s) throws Exception {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(s).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        return Integer.valueOf(httpURLConnection.getResponseCode());
                    }
                }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Integer>() { // from class: com.movieboxpro.android.player.IjkVideoView.6.1
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
                        IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this._notifyMediaStatus();
                        if (IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.hide();
                        }
                        if (IjkVideoView.this.mOnErrorListener != null) {
                            IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, framework_err, impl_err);
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable e) {
                        IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this._notifyMediaStatus();
                        if (IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.hide();
                        }
                        if (IjkVideoView.this.mOnErrorListener != null) {
                            IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, framework_err, impl_err);
                        }
                    }
                });
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.movieboxpro.android.player.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                IjkVideoView.this.mCurrentBufferPercentage = percent;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.movieboxpro.android.player.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer mp) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.movieboxpro.android.player.IjkVideoView.9
            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder holder, int format, int w, int h) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = w;
                IjkVideoView.this.mSurfaceHeight = h;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != w || IjkVideoView.this.mVideoHeight != h)) {
                    z = false;
                }
                if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder holder, int width, int height) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = holder;
                if (IjkVideoView.this.mMediaPlayer != null) {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, holder);
                } else {
                    IjkVideoView.this.openVideo();
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder holder) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.mCurrentAspectRatio = 0;
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
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
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.movieboxpro.android.player.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                IjkVideoView.this.mVideoWidth = mp.getVideoWidth();
                IjkVideoView.this.mVideoHeight = mp.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = mp.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = mp.getVideoSarDen();
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.movieboxpro.android.player.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer mp) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_PREPARED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = mp.getVideoWidth();
                IjkVideoView.this.mVideoHeight = mp.getVideoHeight();
                int i = IjkVideoView.this.mSeekWhenPrepared;
                if (i != 0) {
                    IjkVideoView.this.seekTo(i);
                }
                if (IjkVideoView.this.mVideoWidth != 0 && IjkVideoView.this.mVideoHeight != 0) {
                    if (IjkVideoView.this.mRenderView != null) {
                        IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                        IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                        if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                            if (IjkVideoView.this.mTargetState == 334) {
                                IjkVideoView.this.start();
                                if (IjkVideoView.this.mMediaController != null) {
                                    IjkVideoView.this.mMediaController.show();
                                    return;
                                }
                                return;
                            }
                            if (IjkVideoView.this.isPlaying()) {
                                return;
                            }
                            if ((i != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                                IjkVideoView.this.mMediaController.show(0);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (IjkVideoView.this.mTargetState == 334) {
                    IjkVideoView.this.start();
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.movieboxpro.android.player.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer mp) {
                Log.w(IjkVideoView.this.TAG, "OnCompletionListener:");
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_COMPLETED;
                IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_COMPLETED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnCompletionListener != null) {
                    IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.player.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
                }
                if (arg1 == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (IjkVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    IjkVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (arg1 == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (arg1 == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (arg1 == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = arg2;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(arg2);
                    return true;
                }
                if (arg1 != 10002) {
                    switch (arg1) {
                        case 700:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case 701:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case 702:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        default:
                            switch (arg1) {
                                case 800:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                    break;
                                case 801:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                    break;
                                case 802:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                    break;
                            }
                            break;
                    }
                    return true;
                }
                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.player.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer mp, final int framework_err, final int impl_err) {
                if (mp.getDataSource() == null) {
                    return true;
                }
                Observable.just(mp.getDataSource()).map(new Function<String, Integer>(this) { // from class: com.movieboxpro.android.player.IjkVideoView.6.2
                    @Override // io.reactivex.functions.Function
                    public Integer apply(String s) throws Exception {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(s).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        return Integer.valueOf(httpURLConnection.getResponseCode());
                    }
                }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Integer>() { // from class: com.movieboxpro.android.player.IjkVideoView.6.1
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
                        IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this._notifyMediaStatus();
                        if (IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.hide();
                        }
                        if (IjkVideoView.this.mOnErrorListener != null) {
                            IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, framework_err, impl_err);
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable e) {
                        IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this._notifyMediaStatus();
                        if (IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.hide();
                        }
                        if (IjkVideoView.this.mOnErrorListener != null) {
                            IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, framework_err, impl_err);
                        }
                    }
                });
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.movieboxpro.android.player.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                IjkVideoView.this.mCurrentBufferPercentage = percent;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.movieboxpro.android.player.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer mp) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.movieboxpro.android.player.IjkVideoView.9
            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder holder, int format, int w, int h) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = w;
                IjkVideoView.this.mSurfaceHeight = h;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != w || IjkVideoView.this.mVideoHeight != h)) {
                    z = false;
                }
                if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder holder, int width, int height) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = holder;
                if (IjkVideoView.this.mMediaPlayer != null) {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, holder);
                } else {
                    IjkVideoView.this.openVideo();
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder holder) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.mCurrentAspectRatio = 0;
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
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
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.movieboxpro.android.player.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer mp, int width, int height, int sarNum, int sarDen) {
                IjkVideoView.this.mVideoWidth = mp.getVideoWidth();
                IjkVideoView.this.mVideoHeight = mp.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = mp.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = mp.getVideoSarDen();
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    return;
                }
                if (IjkVideoView.this.mRenderView != null) {
                    IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                    IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                }
                IjkVideoView.this.requestLayout();
            }
        };
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.movieboxpro.android.player.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer mp) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_PREPARED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = mp.getVideoWidth();
                IjkVideoView.this.mVideoHeight = mp.getVideoHeight();
                int i = IjkVideoView.this.mSeekWhenPrepared;
                if (i != 0) {
                    IjkVideoView.this.seekTo(i);
                }
                if (IjkVideoView.this.mVideoWidth != 0 && IjkVideoView.this.mVideoHeight != 0) {
                    if (IjkVideoView.this.mRenderView != null) {
                        IjkVideoView.this.mRenderView.setVideoSize(IjkVideoView.this.mVideoWidth, IjkVideoView.this.mVideoHeight);
                        IjkVideoView.this.mRenderView.setVideoSampleAspectRatio(IjkVideoView.this.mVideoSarNum, IjkVideoView.this.mVideoSarDen);
                        if (!IjkVideoView.this.mRenderView.shouldWaitForResize() || (IjkVideoView.this.mSurfaceWidth == IjkVideoView.this.mVideoWidth && IjkVideoView.this.mSurfaceHeight == IjkVideoView.this.mVideoHeight)) {
                            if (IjkVideoView.this.mTargetState == 334) {
                                IjkVideoView.this.start();
                                if (IjkVideoView.this.mMediaController != null) {
                                    IjkVideoView.this.mMediaController.show();
                                    return;
                                }
                                return;
                            }
                            if (IjkVideoView.this.isPlaying()) {
                                return;
                            }
                            if ((i != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                                IjkVideoView.this.mMediaController.show(0);
                                return;
                            }
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (IjkVideoView.this.mTargetState == 334) {
                    IjkVideoView.this.start();
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.movieboxpro.android.player.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer mp) {
                Log.w(IjkVideoView.this.TAG, "OnCompletionListener:");
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_COMPLETED;
                IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_COMPLETED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnCompletionListener != null) {
                    IjkVideoView.this.mOnCompletionListener.onCompletion(IjkVideoView.this.mMediaPlayer);
                }
            }
        };
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.player.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer mp, int arg1, int arg2) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(mp, arg1, arg2);
                }
                if (arg1 == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (IjkVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    IjkVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (arg1 == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (arg1 == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (arg1 == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = arg2;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + arg2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(arg2);
                    return true;
                }
                if (arg1 != 10002) {
                    switch (arg1) {
                        case 700:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                            break;
                        case 701:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_START:");
                            break;
                        case 702:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BUFFERING_END:");
                            break;
                        case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + arg2);
                            break;
                        default:
                            switch (arg1) {
                                case 800:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                                    break;
                                case 801:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                                    break;
                                case 802:
                                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_METADATA_UPDATE:");
                                    break;
                            }
                            break;
                    }
                    return true;
                }
                Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
                return true;
            }
        };
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.player.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer mp, final int framework_err, final int impl_err) {
                if (mp.getDataSource() == null) {
                    return true;
                }
                Observable.just(mp.getDataSource()).map(new Function<String, Integer>(this) { // from class: com.movieboxpro.android.player.IjkVideoView.6.2
                    @Override // io.reactivex.functions.Function
                    public Integer apply(String s) throws Exception {
                        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(s).openConnection();
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setConnectTimeout(10000);
                        httpURLConnection.connect();
                        return Integer.valueOf(httpURLConnection.getResponseCode());
                    }
                }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Integer>() { // from class: com.movieboxpro.android.player.IjkVideoView.6.1
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
                        IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this._notifyMediaStatus();
                        if (IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.hide();
                        }
                        if (IjkVideoView.this.mOnErrorListener != null) {
                            IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, framework_err, impl_err);
                        }
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable e) {
                        IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                        IjkVideoView.this._notifyMediaStatus();
                        if (IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.hide();
                        }
                        if (IjkVideoView.this.mOnErrorListener != null) {
                            IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, framework_err, impl_err);
                        }
                    }
                });
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.movieboxpro.android.player.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer mp, int percent) {
                IjkVideoView.this.mCurrentBufferPercentage = percent;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.movieboxpro.android.player.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer mp) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.movieboxpro.android.player.IjkVideoView.9
            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder holder, int format, int w, int h) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = w;
                IjkVideoView.this.mSurfaceHeight = h;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != w || IjkVideoView.this.mVideoHeight != h)) {
                    z = false;
                }
                if (IjkVideoView.this.mMediaPlayer != null && z2 && z) {
                    if (IjkVideoView.this.mSeekWhenPrepared != 0) {
                        IjkVideoView ijkVideoView = IjkVideoView.this;
                        ijkVideoView.seekTo(ijkVideoView.mSeekWhenPrepared);
                    }
                    IjkVideoView.this.start();
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceCreated(IRenderView.ISurfaceHolder holder, int width, int height) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = holder;
                if (IjkVideoView.this.mMediaPlayer != null) {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, holder);
                } else {
                    IjkVideoView.this.openVideo();
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder holder) {
                if (holder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.mCurrentAspectRatio = 0;
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    private void initVideoView(Context context) {
        this.mAppContext = context.getApplicationContext();
        this.mCurrentAspectRatio = IjkPlayerView.screenScales[PrefsUtils.getInstance().getInt(Constant.Prefs.SCREEN_SCALE, 0)];
        initBackground();
        initRenders();
        this.mVideoWidth = 0;
        this.mVideoHeight = 0;
        setFocusable(true);
        setFocusableInTouchMode(true);
        requestFocus();
        this.mCurrentState = MediaPlayerParams.STATE_IDLE;
        this.mTargetState = MediaPlayerParams.STATE_IDLE;
        _notifyMediaStatus();
    }

    public void setRenderView(IRenderView renderView) {
        int i;
        int i2;
        if (this.mRenderView != null) {
            IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
            if (ijkMediaPlayer != null) {
                ijkMediaPlayer.setDisplay(null);
            }
            View view = this.mRenderView.getView();
            this.mRenderView.removeRenderCallback(this.mSHCallback);
            this.mRenderView = null;
            removeView(view);
        }
        if (renderView == null) {
            return;
        }
        this.mRenderView = renderView;
        renderView.setAspectRatio(this.mCurrentAspectRatio);
        int i3 = this.mVideoWidth;
        if (i3 > 0 && (i2 = this.mVideoHeight) > 0) {
            renderView.setVideoSize(i3, i2);
        }
        int i4 = this.mVideoSarNum;
        if (i4 > 0 && (i = this.mVideoSarDen) > 0) {
            renderView.setVideoSampleAspectRatio(i4, i);
        }
        View view2 = this.mRenderView.getView();
        view2.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 17));
        addView(view2);
        this.mRenderView.addRenderCallback(this.mSHCallback);
        this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
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
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.movieboxpro.android.player.IjkVideoView.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    IjkVideoView.this.mSaveMatrix.set(videoTransform);
                    IjkVideoView.this.mSaveMatrix.postTranslate(f7 * fFloatValue, f8 * fFloatValue);
                    IjkVideoView.this.mRenderView.setTransform(IjkVideoView.this.mSaveMatrix);
                    IjkVideoView.this.mRenderView.setVideoRotation((int) (IjkVideoView.this.mVideoRotationDegree - (i4 * (1.0f - fFloatValue))));
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

    public void setRender(int render) {
        if (render == 0) {
            setRenderView(null);
            return;
        }
        if (render == 1) {
            setRenderView(new SurfaceRenderView(getContext()));
            return;
        }
        if (render == 2) {
            TextureRenderView textureRenderView = new TextureRenderView(getContext());
            if (this.mMediaPlayer != null) {
                textureRenderView.getSurfaceHolder().bindToMediaPlayer(this.mMediaPlayer);
                textureRenderView.setVideoSize(this.mMediaPlayer.getVideoWidth(), this.mMediaPlayer.getVideoHeight());
                textureRenderView.setVideoSampleAspectRatio(this.mMediaPlayer.getVideoSarNum(), this.mMediaPlayer.getVideoSarDen());
                textureRenderView.setAspectRatio(this.mCurrentAspectRatio);
            }
            setRenderView(textureRenderView);
            return;
        }
        Log.e(this.TAG, String.format(Locale.getDefault(), "invalid render %d\n", Integer.valueOf(render)));
    }

    public void setVideoPath(String path) {
        setVideoURI(Uri.parse(path));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    private void setVideoURI(Uri uri, Map<String, String> headers) {
        this.mUri = uri;
        this.mHeaders = headers;
        this.mSeekWhenPrepared = 0;
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
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.setSpeed(speed);
        }
    }

    public void stopPlayback() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = MediaPlayerParams.STATE_IDLE;
            this.mTargetState = MediaPlayerParams.STATE_IDLE;
            _notifyMediaStatus();
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void openVideo() {
        if (this.mUri == null || this.mSurfaceHolder == null) {
            return;
        }
        release(false);
        ((AudioManager) this.mAppContext.getSystemService("audio")).requestAudioFocus(null, 3, 1);
        try {
            try {
                this.mMediaPlayer = createPlayer(3);
                getContext();
                this.mMediaPlayer.setOnPreparedListener(this.mPreparedListener);
                this.mMediaPlayer.setOnVideoSizeChangedListener(this.mSizeChangedListener);
                this.mMediaPlayer.setOnCompletionListener(this.mCompletionListener);
                this.mMediaPlayer.setOnErrorListener(this.mErrorListener);
                this.mMediaPlayer.setOnInfoListener(this.mInfoListener);
                this.mMediaPlayer.setOnBufferingUpdateListener(this.mBufferingUpdateListener);
                this.mMediaPlayer.setOnSeekCompleteListener(this.mSeekCompleteListener);
                this.mCurrentBufferPercentage = 0;
                String scheme = this.mUri.getScheme();
                if (Build.VERSION.SDK_INT >= 23 && this.mIsUsingMediaDataSource && (TextUtils.isEmpty(scheme) || scheme.equalsIgnoreCase(IDataSource.SCHEME_FILE_TAG))) {
                    this.mMediaPlayer.setDataSource(new FileMediaDataSource(new File(this.mUri.toString())));
                } else {
                    this.mMediaPlayer.setDataSource(this.mAppContext, this.mUri, this.mHeaders);
                }
                bindSurfaceHolder(this.mMediaPlayer, this.mSurfaceHolder);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mPrepareStartTime = System.currentTimeMillis();
                this.mMediaPlayer.prepareAsync();
                this.mCurrentState = MediaPlayerParams.STATE_PREPARING;
                attachMediaController();
            } catch (IOException e) {
                Log.w(this.TAG, "Unable to open content: " + this.mUri, e);
                this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                this.mTargetState = MediaPlayerParams.STATE_ERROR;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            } catch (IllegalArgumentException e2) {
                Log.w(this.TAG, "Unable to open content: " + this.mUri, e2);
                this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                this.mTargetState = MediaPlayerParams.STATE_ERROR;
                this.mErrorListener.onError(this.mMediaPlayer, 1, 0);
            }
        } finally {
            _notifyMediaStatus();
        }
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
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getTcpSpeed();
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

    public IMediaPlayer getMediaPlayer() {
        return this.mMediaPlayer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _notifyMediaStatus() {
        IMediaPlayer.OnInfoListener onInfoListener = this.mOnInfoListener;
        if (onInfoListener != null) {
            onInfoListener.onInfo(this.mMediaPlayer, this.mCurrentState, -1);
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

    /* JADX INFO: Access modifiers changed from: private */
    public void bindSurfaceHolder(IMediaPlayer mp, IRenderView.ISurfaceHolder holder) {
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
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.setDisplay(null);
        }
    }

    public void release(boolean cleartargetstate) {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.reset();
            this.mMediaPlayer.release();
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

    public void suspend() {
        release(false);
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
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getVideoHeight();
        }
        return 0;
    }

    public int getVideoWidth() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            return ijkMediaPlayer.getVideoWidth();
        }
        return 0;
    }

    public int getInterruptPosition() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            return (int) ijkMediaPlayer.getCurrentPosition();
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
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            iRenderView.setAspectRatio(aspectRatio);
        }
    }

    private void initRenders() {
        setRender(2);
    }

    public IjkMediaPlayer createPlayer(int playerType) {
        IjkMediaPlayer ijkMediaPlayer;
        if (playerType != 1) {
            if (playerType != 2) {
                if (playerType == 3) {
                    Log.d(this.TAG, "kaishi");
                    ijkMediaPlayer = new IjkMediaPlayer();
                    ijkMediaPlayer.setOption(1, "analyzemaxduration", 100L);
                    ijkMediaPlayer.setOption(1, "flush_packets", 1L);
                    ijkMediaPlayer.setOption(4, "packet-buffering", 0L);
                    ijkMediaPlayer.setOption(1, "reconnect", 1L);
                    ijkMediaPlayer.setOption(4, "framedrop", 5L);
                    ijkMediaPlayer.setOption(4, "soundtouch", 1L);
                    ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 1L);
                    ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1L);
                    ijkMediaPlayer.setOption(2, "skip_loop_filter", 0L);
                    ijkMediaPlayer.setOption(4, "min-frames", 2L);
                    ijkMediaPlayer.setOption(4, "mediacodec", 1L);
                    ijkMediaPlayer.setOption(4, "mediacodec-hevc", 1L);
                    ijkMediaPlayer.setOption(1, "dns_cache_clear", 1L);
                    ijkMediaPlayer.setOption(1, "dns_cache_timeout", -1L);
                } else if (this.mUri != null) {
                    ijkMediaPlayer = new IjkMediaPlayer();
                    IjkMediaPlayer.native_setLogLevel(3);
                    if (this.mIsUsingMediaCodec) {
                        Log.d(this.TAG, "kaishi");
                        ijkMediaPlayer.setOption(4, "mediacodec", 1L);
                        if (this.mIsUsingMediaCodecAutoRotate) {
                            ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 1L);
                        } else {
                            ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 0L);
                        }
                        if (this.mIsMediaCodecHandleResolutionChange) {
                            ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1L);
                        } else {
                            ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 0L);
                        }
                    } else {
                        ijkMediaPlayer.setOption(4, "mediacodec", 0L);
                    }
                    if (this.mIsUsingOpenSLES) {
                        ijkMediaPlayer.setOption(4, "opensles", 1L);
                    } else {
                        ijkMediaPlayer.setOption(4, "opensles", 0L);
                    }
                    if (TextUtils.isEmpty(this.mPixelFormat)) {
                        ijkMediaPlayer.setOption(4, "overlay-format", 842225234L);
                    } else {
                        ijkMediaPlayer.setOption(4, "overlay-format", this.mPixelFormat);
                    }
                    ijkMediaPlayer.setOption(4, "framedrop", 1L);
                    ijkMediaPlayer.setOption(4, "start-on-prepared", 0L);
                    ijkMediaPlayer.setOption(1, "http-detect-range-support", 0L);
                    ijkMediaPlayer.setOption(2, "skip_loop_filter", 48L);
                }
                return ijkMediaPlayer;
            }
            new AndroidMediaPlayer();
        }
        return null;
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

    public ITrackInfo[] getTrackInfo() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer == null) {
            return null;
        }
        return ijkMediaPlayer.getTrackInfo();
    }

    public void selectTrack(int stream) {
        MediaPlayerCompat.selectTrack(this.mMediaPlayer, stream);
    }

    public void deselectTrack(int stream) {
        MediaPlayerCompat.deselectTrack(this.mMediaPlayer, stream);
    }

    public int getSelectedTrack(int trackType) {
        return MediaPlayerCompat.getSelectedTrack(this.mMediaPlayer, trackType);
    }
}
