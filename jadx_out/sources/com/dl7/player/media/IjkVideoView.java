package com.dl7.player.media;

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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.MediaController;
import com.alibaba.fastjson.asm.Opcodes;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.videolan.libvlc.MediaPlayer;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;

/* JADX INFO: loaded from: classes4.dex */
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
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.dl7.player.media.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
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
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.dl7.player.media.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_PREPARED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                int i = IjkVideoView.this.mSeekWhenPrepared;
                if (i != 0) {
                    IjkVideoView.this.seekTo(i);
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 334) {
                        IjkVideoView.this.start();
                        return;
                    }
                    return;
                }
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
                        }
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.dl7.player.media.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
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
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.dl7.player.media.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
                }
                if (i == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (IjkVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    IjkVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (i == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (i == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (i == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = i2;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i2);
                    return true;
                }
                if (i != 10002) {
                    switch (i) {
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
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                            break;
                        default:
                            switch (i) {
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
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.dl7.player.media.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("TTAG", "Error: " + i + "," + i2);
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnErrorListener != null) {
                    IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i, i2);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.dl7.player.media.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                IjkVideoView.this.mCurrentBufferPercentage = i;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.dl7.player.media.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.dl7.player.media.IjkVideoView.9
            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = i2;
                IjkVideoView.this.mSurfaceHeight = i3;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i2 || IjkVideoView.this.mVideoHeight != i3)) {
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
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (IjkVideoView.this.mMediaPlayer == null) {
                    IjkVideoView.this.openVideo();
                } else {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.mCurrentAspectRatio = s_allAspectRatio[0];
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
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
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.dl7.player.media.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i2, int i3, int i4) {
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
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
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.dl7.player.media.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_PREPARED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                int i = IjkVideoView.this.mSeekWhenPrepared;
                if (i != 0) {
                    IjkVideoView.this.seekTo(i);
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 334) {
                        IjkVideoView.this.start();
                        return;
                    }
                    return;
                }
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
                        }
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.dl7.player.media.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
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
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.dl7.player.media.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i, i2);
                }
                if (i == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (IjkVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    IjkVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (i == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (i == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (i == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = i2;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i2);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i2);
                    return true;
                }
                if (i != 10002) {
                    switch (i) {
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
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i2);
                            break;
                        default:
                            switch (i) {
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
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.dl7.player.media.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i, int i2) {
                Log.d("TTAG", "Error: " + i + "," + i2);
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnErrorListener != null) {
                    IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i, i2);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.dl7.player.media.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i) {
                IjkVideoView.this.mCurrentBufferPercentage = i;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.dl7.player.media.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.dl7.player.media.IjkVideoView.9
            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = i2;
                IjkVideoView.this.mSurfaceHeight = i3;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i2 || IjkVideoView.this.mVideoHeight != i3)) {
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
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i, int i2) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (IjkVideoView.this.mMediaPlayer == null) {
                    IjkVideoView.this.openVideo();
                } else {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.mCurrentAspectRatio = s_allAspectRatio[0];
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
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
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.dl7.player.media.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i2, int i3, int i4, int i5) {
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
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
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.dl7.player.media.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_PREPARED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                int i2 = IjkVideoView.this.mSeekWhenPrepared;
                if (i2 != 0) {
                    IjkVideoView.this.seekTo(i2);
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 334) {
                        IjkVideoView.this.start();
                        return;
                    }
                    return;
                }
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
                        if ((i2 != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.dl7.player.media.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
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
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.dl7.player.media.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i2, int i3) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i2, i3);
                }
                if (i2 == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (IjkVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    IjkVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (i2 == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (i2 == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (i2 == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = i3;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i3);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i3);
                    return true;
                }
                if (i2 != 10002) {
                    switch (i2) {
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
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i3);
                            break;
                        default:
                            switch (i2) {
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
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.dl7.player.media.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i2, int i3) {
                Log.d("TTAG", "Error: " + i2 + "," + i3);
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnErrorListener != null) {
                    IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i2, i3);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.dl7.player.media.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i2) {
                IjkVideoView.this.mCurrentBufferPercentage = i2;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.dl7.player.media.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.dl7.player.media.IjkVideoView.9
            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3, int i4) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = i3;
                IjkVideoView.this.mSurfaceHeight = i4;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i3 || IjkVideoView.this.mVideoHeight != i4)) {
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
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i2, int i3) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (IjkVideoView.this.mMediaPlayer == null) {
                    IjkVideoView.this.openVideo();
                } else {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.mCurrentAspectRatio = s_allAspectRatio[0];
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    public IjkVideoView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
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
        this.mSizeChangedListener = new IMediaPlayer.OnVideoSizeChangedListener() { // from class: com.dl7.player.media.IjkVideoView.2
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnVideoSizeChangedListener
            public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i3, int i4, int i5, int i6) {
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                IjkVideoView.this.mVideoSarNum = iMediaPlayer.getVideoSarNum();
                IjkVideoView.this.mVideoSarDen = iMediaPlayer.getVideoSarDen();
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
        this.mPreparedListener = new IMediaPlayer.OnPreparedListener() { // from class: com.dl7.player.media.IjkVideoView.3
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnPreparedListener
            public void onPrepared(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mPrepareEndTime = System.currentTimeMillis();
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_PREPARED;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mOnPreparedListener != null) {
                    IjkVideoView.this.mOnPreparedListener.onPrepared(IjkVideoView.this.mMediaPlayer);
                }
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.setEnabled(true);
                }
                IjkVideoView.this.mVideoWidth = iMediaPlayer.getVideoWidth();
                IjkVideoView.this.mVideoHeight = iMediaPlayer.getVideoHeight();
                int i3 = IjkVideoView.this.mSeekWhenPrepared;
                if (i3 != 0) {
                    IjkVideoView.this.seekTo(i3);
                }
                if (IjkVideoView.this.mVideoWidth == 0 || IjkVideoView.this.mVideoHeight == 0) {
                    if (IjkVideoView.this.mTargetState == 334) {
                        IjkVideoView.this.start();
                        return;
                    }
                    return;
                }
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
                        if ((i3 != 0 || IjkVideoView.this.getCurrentPosition() > 0) && IjkVideoView.this.mMediaController != null) {
                            IjkVideoView.this.mMediaController.show(0);
                        }
                    }
                }
            }
        };
        this.mCompletionListener = new IMediaPlayer.OnCompletionListener() { // from class: com.dl7.player.media.IjkVideoView.4
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
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
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.dl7.player.media.IjkVideoView.5
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i3, int i4) {
                if (IjkVideoView.this.mOnInfoListener != null) {
                    IjkVideoView.this.mOnInfoListener.onInfo(iMediaPlayer, i3, i4);
                }
                if (i3 == 3) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_RENDERING_START:");
                    if (IjkVideoView.this.videogetStartInfo == null) {
                        return true;
                    }
                    IjkVideoView.this.videogetStartInfo.getVideoStart();
                    return true;
                }
                if (i3 == 901) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
                    return true;
                }
                if (i3 == 902) {
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
                    return true;
                }
                if (i3 == 10001) {
                    IjkVideoView.this.mVideoRotationDegree = i4;
                    Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: " + i4);
                    if (IjkVideoView.this.mRenderView == null) {
                        return true;
                    }
                    IjkVideoView.this.mRenderView.setVideoRotation(i4);
                    return true;
                }
                if (i3 != 10002) {
                    switch (i3) {
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
                            Log.d(IjkVideoView.this.TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: " + i4);
                            break;
                        default:
                            switch (i3) {
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
        this.mErrorListener = new IMediaPlayer.OnErrorListener() { // from class: com.dl7.player.media.IjkVideoView.6
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
            public boolean onError(IMediaPlayer iMediaPlayer, int i3, int i4) {
                Log.d("TTAG", "Error: " + i3 + "," + i4);
                IjkVideoView.this.mCurrentState = MediaPlayerParams.STATE_ERROR;
                IjkVideoView.this.mTargetState = MediaPlayerParams.STATE_ERROR;
                IjkVideoView.this._notifyMediaStatus();
                if (IjkVideoView.this.mMediaController != null) {
                    IjkVideoView.this.mMediaController.hide();
                }
                if (IjkVideoView.this.mOnErrorListener != null) {
                    IjkVideoView.this.mOnErrorListener.onError(IjkVideoView.this.mMediaPlayer, i3, i4);
                }
                return true;
            }
        };
        this.mBufferingUpdateListener = new IMediaPlayer.OnBufferingUpdateListener() { // from class: com.dl7.player.media.IjkVideoView.7
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnBufferingUpdateListener
            public void onBufferingUpdate(IMediaPlayer iMediaPlayer, int i3) {
                IjkVideoView.this.mCurrentBufferPercentage = i3;
            }
        };
        this.mSeekCompleteListener = new IMediaPlayer.OnSeekCompleteListener() { // from class: com.dl7.player.media.IjkVideoView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnSeekCompleteListener
            public void onSeekComplete(IMediaPlayer iMediaPlayer) {
                IjkVideoView.this.mSeekEndTime = System.currentTimeMillis();
            }
        };
        this.mSHCallback = new IRenderView.IRenderCallback() { // from class: com.dl7.player.media.IjkVideoView.9
            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceChanged(IRenderView.ISurfaceHolder iSurfaceHolder, int i3, int i4, int i5) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceChanged: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceWidth = i4;
                IjkVideoView.this.mSurfaceHeight = i5;
                boolean z = true;
                boolean z2 = IjkVideoView.this.mTargetState == 334;
                if (IjkVideoView.this.mRenderView.shouldWaitForResize() && (IjkVideoView.this.mVideoWidth != i4 || IjkVideoView.this.mVideoHeight != i5)) {
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
            public void onSurfaceCreated(IRenderView.ISurfaceHolder iSurfaceHolder, int i3, int i4) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceCreated: unmatched render callback\n");
                    return;
                }
                IjkVideoView.this.mSurfaceHolder = iSurfaceHolder;
                if (IjkVideoView.this.mMediaPlayer == null) {
                    IjkVideoView.this.openVideo();
                } else {
                    IjkVideoView ijkVideoView = IjkVideoView.this;
                    ijkVideoView.bindSurfaceHolder(ijkVideoView.mMediaPlayer, iSurfaceHolder);
                }
            }

            @Override // com.dl7.player.media.IRenderView.IRenderCallback
            public void onSurfaceDestroyed(IRenderView.ISurfaceHolder iSurfaceHolder) {
                if (iSurfaceHolder.getRenderView() != IjkVideoView.this.mRenderView) {
                    Log.e(IjkVideoView.this.TAG, "onSurfaceDestroyed: unmatched render callback\n");
                } else {
                    IjkVideoView.this.mSurfaceHolder = null;
                    IjkVideoView.this.releaseWithoutStop();
                }
            }
        };
        this.mCurrentAspectRatio = s_allAspectRatio[0];
        this.mEnableBackgroundPlay = false;
        initVideoView(context);
    }

    private void initVideoView(Context context) {
        this.mAppContext = context.getApplicationContext();
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

    public void setRenderView(IRenderView iRenderView) {
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
        if (iRenderView == null) {
            return;
        }
        this.mRenderView = iRenderView;
        iRenderView.setAspectRatio(this.mCurrentAspectRatio);
        int i3 = this.mVideoWidth;
        if (i3 > 0 && (i2 = this.mVideoHeight) > 0) {
            iRenderView.setVideoSize(i3, i2);
        }
        int i4 = this.mVideoSarNum;
        if (i4 > 0 && (i = this.mVideoSarDen) > 0) {
            iRenderView.setVideoSampleAspectRatio(i4, i);
        }
        View view2 = this.mRenderView.getView();
        view2.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 17));
        addView(view2);
        this.mRenderView.addRenderCallback(this.mSHCallback);
        this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
    }

    public void setVideoRotation(int i) {
        int i2 = this.mVideoRotationDegree + i;
        this.mVideoTargetRotationDegree = i2;
        this.mRenderView.setVideoRotation(i2);
    }

    public Matrix getVideoTransform() {
        if (this.mOriginalMatrix == null) {
            this.mOriginalMatrix = this.mRenderView.getTransform();
        }
        return this.mRenderView.getTransform();
    }

    public void setVideoTransform(Matrix matrix) {
        this.mRenderView.setTransform(matrix);
    }

    public boolean adjustVideoView(float f) {
        float f2 = this.mVideoScale * f;
        this.mVideoScale = f2;
        int i = this.mVideoTargetRotationDegree;
        int i2 = (i + 360) % 360;
        if (f2 == 1.0f && i2 == 0) {
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
            float f3 = this.mVideoScale;
            videoTransform.preScale(f3, f3);
            float f4 = this.mScreenOrWidth;
            float f5 = this.mVideoScale;
            videoTransform.postTranslate((f4 * (1.0f - f5)) / 2.0f, (this.mScreenOrHeight * (1.0f - f5)) / 2.0f);
            this.mRenderView.setTransform(videoTransform);
            this.mIsNormalScreen = true;
        } else {
            float[] fArr = new float[2];
            videoTransform.mapPoints(fArr);
            float f6 = this.mScreenOrWidth;
            float f7 = this.mVideoScale;
            final float f8 = ((f6 * (1.0f - f7)) / 2.0f) - fArr[0];
            final float f9 = ((this.mScreenOrHeight * (1.0f - f7)) / 2.0f) - fArr[1];
            if (this.mSaveMatrix == null) {
                this.mSaveMatrix = new Matrix();
            }
            ValueAnimator duration = ValueAnimator.ofFloat(0.0f, 1.0f).setDuration(300L);
            duration.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.dl7.player.media.IjkVideoView.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    IjkVideoView.this.mSaveMatrix.set(videoTransform);
                    IjkVideoView.this.mSaveMatrix.postTranslate(f8 * fFloatValue, f9 * fFloatValue);
                    IjkVideoView.this.mRenderView.setTransform(IjkVideoView.this.mSaveMatrix);
                    IjkVideoView.this.mRenderView.setVideoRotation((int) (IjkVideoView.this.mVideoRotationDegree - (i4 * (1.0f - fFloatValue))));
                }
            });
            duration.start();
        }
        return true;
    }

    public void resetVideoView(boolean z) {
        this.mIsNormalScreen = z;
        this.mVideoRotationDegree = 0;
        if (z) {
            this.mVideoTargetRotationDegree = 0;
            this.mVideoScale = 1.0f;
        }
        this.mRenderView.setTransform(this.mOriginalMatrix);
        this.mRenderView.setVideoRotation(this.mVideoRotationDegree);
    }

    public void setRender(int i) {
        if (i == 0) {
            setRenderView(null);
            return;
        }
        if (i == 1) {
            setRenderView(new SurfaceRenderView(getContext()));
            return;
        }
        if (i == 2) {
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
        Log.e(this.TAG, String.format(Locale.getDefault(), "invalid render %d\n", Integer.valueOf(i)));
    }

    public void setVideoPath(String str) {
        setVideoURI(Uri.parse(str));
    }

    public void setVideoURI(Uri uri) {
        setVideoURI(uri, null);
    }

    public void setErrorURI(Uri uri) {
        setVideoURI(uri, null);
    }

    private void setVideoURI(Uri uri, Map<String, String> map) {
        this.mUri = uri;
        this.mHeaders = map;
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
                this.mMediaPlayer.setScreenOnWhilePlaying(true);
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

    public void setMediaController(IMediaController iMediaController) {
        IMediaController iMediaController2 = this.mMediaController;
        if (iMediaController2 != null) {
            iMediaController2.hide();
        }
        this.mMediaController = iMediaController;
        attachMediaController();
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

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.mOnPreparedListener = onPreparedListener;
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.mOnCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.mOnErrorListener = onErrorListener;
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.mOnInfoListener = onInfoListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindSurfaceHolder(IMediaPlayer iMediaPlayer, IRenderView.ISurfaceHolder iSurfaceHolder) {
        if (iMediaPlayer == null) {
            return;
        }
        if (iSurfaceHolder == null) {
            iMediaPlayer.setDisplay(null);
        } else {
            iSurfaceHolder.bindToMediaPlayer(iMediaPlayer);
        }
    }

    public void releaseWithoutStop() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.setDisplay(null);
        }
    }

    public void release(boolean z) {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.reset();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mCurrentState = MediaPlayerParams.STATE_IDLE;
            _notifyMediaStatus();
            if (z) {
                this.mTargetState = MediaPlayerParams.STATE_IDLE;
            }
            ((AudioManager) this.mAppContext.getSystemService("audio")).abandonAudioFocus(null);
        }
    }

    public void destroy() {
        release(true);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisibility();
        return false;
    }

    @Override // android.view.View
    public boolean onTrackballEvent(MotionEvent motionEvent) {
        if (!isInPlaybackState() || this.mMediaController == null) {
            return false;
        }
        toggleMediaControlsVisibility();
        return false;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = (i == 4 || i == 24 || i == 25 || i == 164 || i == 82 || i == 5 || i == 6) ? false : true;
        if (isInPlaybackState() && z && this.mMediaController != null) {
            if (i == 79 || i == 85) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                } else {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            }
            if (i == 126) {
                if (!this.mMediaPlayer.isPlaying()) {
                    start();
                    this.mMediaController.hide();
                }
                return true;
            }
            if (i == 86 || i == 127) {
                if (this.mMediaPlayer.isPlaying()) {
                    pause();
                    this.mMediaController.show();
                }
                return true;
            }
            toggleMediaControlsVisibility();
        }
        return super.onKeyDown(i, keyEvent);
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

    public int getInterruptPosition() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer != null) {
            return (int) ijkMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public int getCurrentPosition() {
        if (isInPlaybackState()) {
            return (int) this.mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    @Override // android.widget.MediaController.MediaPlayerControl
    public void seekTo(int i) {
        if (isInPlaybackState()) {
            this.mSeekStartTime = System.currentTimeMillis();
            this.mMediaPlayer.seekTo(i);
            this.mSeekWhenPrepared = 0;
            return;
        }
        this.mSeekWhenPrepared = i;
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

    public void setAspectRatio(int i) {
        this.mCurrentAspectRatio = i;
        IRenderView iRenderView = this.mRenderView;
        if (iRenderView != null) {
            iRenderView.setAspectRatio(i);
        }
    }

    private void initRenders() {
        setRender(2);
    }

    public IjkMediaPlayer createPlayer(int i) {
        IjkMediaPlayer ijkMediaPlayer;
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    ijkMediaPlayer = new IjkMediaPlayer();
                    ijkMediaPlayer.setOption(1, "analyzemaxduration", 100L);
                    ijkMediaPlayer.setOption(1, "flush_packets", 1L);
                    ijkMediaPlayer.setOption(4, "packet-buffering", 0L);
                    ijkMediaPlayer.setOption(1, "reconnect", 1L);
                    ijkMediaPlayer.setOption(4, "framedrop", 5L);
                    ijkMediaPlayer.setOption(4, "mediacodec", 1L);
                    ijkMediaPlayer.setOption(4, "soundtouch", 1L);
                    if (this.mIsUsingMediaCodec) {
                        ijkMediaPlayer.setOption(4, "mediacodec-hevc", 1L);
                    } else {
                        ijkMediaPlayer.setOption(4, "mediacodec-hevc", 0L);
                    }
                    ijkMediaPlayer.setOption(4, "mediacodec-vp9", 1L);
                    ijkMediaPlayer.setOption(4, "mediacodec-auto-rotate", 1L);
                    ijkMediaPlayer.setOption(4, "mediacodec-handle-resolution-change", 1L);
                    ijkMediaPlayer.setOption(2, "skip_loop_filter", 48L);
                    ijkMediaPlayer.setOption(4, "framedrop", 12L);
                    ijkMediaPlayer.setOption(4, "min-frames", 2L);
                } else if (this.mUri != null) {
                    ijkMediaPlayer = new IjkMediaPlayer();
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
            Log.d(this.TAG, "kaishi");
        }
        return null;
    }

    public boolean isBackgroundPlayEnabled() {
        return this.mEnableBackgroundPlay;
    }

    private String buildTimeMilli(long j) {
        long j2 = j / 1000;
        long j3 = j2 / 3600;
        long j4 = (j2 % 3600) / 60;
        long j5 = j2 % 60;
        if (j <= 0) {
            return "--:--";
        }
        if (j3 >= 100) {
            return String.format(Locale.US, "%d:%02d:%02d", Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5));
        }
        return j3 > 0 ? String.format(Locale.US, "%02d:%02d:%02d", Long.valueOf(j3), Long.valueOf(j4), Long.valueOf(j5)) : String.format(Locale.US, "%02d:%02d", Long.valueOf(j4), Long.valueOf(j5));
    }

    public ITrackInfo[] getTrackInfo() {
        IjkMediaPlayer ijkMediaPlayer = this.mMediaPlayer;
        if (ijkMediaPlayer == null) {
            return null;
        }
        return ijkMediaPlayer.getTrackInfo();
    }

    public void setSpeed(float f) {
        this.mMediaPlayer.setSpeed(f);
    }

    public void setDecode(boolean z) {
        this.mIsUsingMediaCodec = z;
        if (z) {
            this.mMediaPlayer.setOption(4, "mediacodec-hevc", 1L);
        } else {
            this.mMediaPlayer.setOption(4, "mediacodec-hevc", 0L);
        }
    }

    public void selectTrack(int i) {
        MediaPlayerCompat.selectTrack(this.mMediaPlayer, i);
    }

    public void deselectTrack(int i) {
        MediaPlayerCompat.deselectTrack(this.mMediaPlayer, i);
    }

    public int getSelectedTrack(int i) {
        return MediaPlayerCompat.getSelectedTrack(this.mMediaPlayer, i);
    }
}
