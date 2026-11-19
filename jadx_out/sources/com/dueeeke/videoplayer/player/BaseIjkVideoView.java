package com.dueeeke.videoplayer.player;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.OrientationEventListener;
import android.widget.FrameLayout;
import com.dueeeke.videoplayer.controller.BaseVideoController;
import com.dueeeke.videoplayer.controller.MediaPlayerControl;
import com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener;
import com.dueeeke.videoplayer.listener.PlayerEventListener;
import com.dueeeke.videoplayer.util.PlayerUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IjkTrackInfo;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseIjkVideoView extends FrameLayout implements MediaPlayerControl, PlayerEventListener {
    public static boolean IS_PLAY_ON_MOBILE_NETWORK = false;
    protected static final int LANDSCAPE = 2;
    public static final int PLAYER_FULL_SCREEN = 11;
    public static final int PLAYER_NORMAL = 10;
    protected static final int PORTRAIT = 1;
    protected static final int REVERSE_LANDSCAPE = 3;
    public static final int STATE_BUFFERED = 7;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_ERROR = -1;
    public static final int STATE_IDLE = 0;
    public static final int STATE_PAUSED = 4;
    public static final int STATE_PLAYBACK_COMPLETED = 5;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_PREPARED = 2;
    public static final int STATE_PREPARING = 1;
    private static final String TAG = "BaseIjkVideoView";
    protected AssetFileDescriptor mAssetFileDescriptor;
    protected AudioFocusHelper mAudioFocusHelper;
    protected AudioManager mAudioManager;
    protected int mCurrentOrientation;
    protected int mCurrentPlayState;
    protected int mCurrentPlayerState;
    protected long mCurrentPosition;
    protected String mCurrentTitle;
    protected String mCurrentUrl;
    protected String mCurrentUrl2;
    protected Map<String, String> mHeaders;
    protected boolean mIsLockFullScreen;
    protected boolean mIsMute;
    protected AbstractPlayer mMediaPlayer;
    protected List<OnVideoViewStateChangeListener> mOnVideoViewStateChangeListeners;
    protected OrientationEventListener mOrientationEventListener;
    protected PlayerConfig mPlayerConfig;
    protected long mSeekWhenPrepared;
    protected BaseVideoController mVideoController;

    protected abstract void setPlayState(int i);

    protected abstract void setPlayerState(int i);

    protected void onOrientationPortrait(Activity activity) {
        int i;
        if (this.mIsLockFullScreen || !this.mPlayerConfig.mAutoRotate || (i = this.mCurrentOrientation) == 1) {
            return;
        }
        if ((i == 2 || i == 3) && !isFullScreen()) {
            this.mCurrentOrientation = 1;
            return;
        }
        this.mCurrentOrientation = 1;
        activity.setRequestedOrientation(1);
        stopFullScreen();
    }

    protected void onOrientationLandscape(Activity activity) {
        int i = this.mCurrentOrientation;
        if (i == 2) {
            return;
        }
        if (i == 1 && activity.getRequestedOrientation() != 8 && isFullScreen()) {
            this.mCurrentOrientation = 2;
            return;
        }
        this.mCurrentOrientation = 2;
        if (!isFullScreen()) {
            startFullScreen();
        }
        activity.setRequestedOrientation(0);
    }

    protected void onOrientationReverseLandscape(Activity activity) {
        int i = this.mCurrentOrientation;
        if (i == 3) {
            return;
        }
        if (i == 1 && activity.getRequestedOrientation() != 0 && isFullScreen()) {
            this.mCurrentOrientation = 3;
            return;
        }
        this.mCurrentOrientation = 3;
        if (!isFullScreen()) {
            startFullScreen();
        }
        activity.setRequestedOrientation(8);
    }

    public BaseIjkVideoView(Context context) {
        this(context, null);
    }

    public BaseIjkVideoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseIjkVideoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentTitle = "";
        this.mCurrentPlayState = 0;
        this.mCurrentPlayerState = 10;
        this.mCurrentOrientation = 0;
        this.mOrientationEventListener = new OrientationEventListener(getContext()) { // from class: com.dueeeke.videoplayer.player.BaseIjkVideoView.1
            private long mLastTime;

            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i2) {
                Activity activityScanForActivity;
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.mLastTime < 300 || BaseIjkVideoView.this.mVideoController == null || (activityScanForActivity = PlayerUtils.scanForActivity(BaseIjkVideoView.this.mVideoController.getContext())) == null) {
                    return;
                }
                if (i2 >= 340) {
                    BaseIjkVideoView.this.onOrientationPortrait(activityScanForActivity);
                } else if (i2 >= 260 && i2 <= 280) {
                    BaseIjkVideoView.this.onOrientationLandscape(activityScanForActivity);
                } else if (i2 >= 70 && i2 <= 90) {
                    BaseIjkVideoView.this.onOrientationReverseLandscape(activityScanForActivity);
                }
                this.mLastTime = jCurrentTimeMillis;
            }
        };
        this.mPlayerConfig = new PlayerConfig.Builder().build();
    }

    protected void initPlayer() {
        if (this.mMediaPlayer == null) {
            if (this.mPlayerConfig.mAbstractPlayer != null) {
                this.mMediaPlayer = this.mPlayerConfig.mAbstractPlayer;
            } else {
                this.mMediaPlayer = new IjkPlayer(getContext());
            }
            Log.d(TAG, "SSSSSSend");
            this.mMediaPlayer.bindVideoView(this);
            this.mMediaPlayer.initPlayer();
            this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
            this.mMediaPlayer.setLooping(this.mPlayerConfig.isLooping);
            return;
        }
        this.mMediaPlayer = null;
        if (this.mPlayerConfig.mAbstractPlayer != null) {
            this.mMediaPlayer = this.mPlayerConfig.mAbstractPlayer;
        } else {
            this.mMediaPlayer = new IjkPlayer(getContext());
        }
        Log.d(TAG, "SSSSSSend");
        this.mMediaPlayer.bindVideoView(this);
        this.mMediaPlayer.initPlayer();
        this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
        this.mMediaPlayer.setLooping(this.mPlayerConfig.isLooping);
    }

    protected void startPrepare(boolean z) {
        if (TextUtils.isEmpty(this.mCurrentUrl) && this.mAssetFileDescriptor == null) {
            return;
        }
        if (z) {
            this.mMediaPlayer.reset();
        }
        this.mMediaPlayer.setEnableMediaCodec(this.mPlayerConfig.enableMediaCodec);
        AssetFileDescriptor assetFileDescriptor = this.mAssetFileDescriptor;
        if (assetFileDescriptor != null) {
            this.mMediaPlayer.setDataSource(assetFileDescriptor);
        } else {
            this.mMediaPlayer.setDataSource(this.mCurrentUrl, this.mHeaders);
        }
        this.mMediaPlayer.prepareAsync();
        setPlayState(1);
        setPlayerState(isFullScreen() ? 11 : 10);
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void start() {
        Log.d(TAG, "SSSSSS" + this.mCurrentPlayState);
        if (this.mCurrentPlayState == 0) {
            startPlay();
        } else if (isInPlaybackState()) {
            startInPlaybackState();
        }
        setKeepScreenOn(true);
        AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
        if (audioFocusHelper != null) {
            audioFocusHelper.requestFocus();
        }
    }

    protected void startPlay() {
        if (!this.mPlayerConfig.disableAudioFocus) {
            this.mAudioManager = (AudioManager) getContext().getApplicationContext().getSystemService("audio");
            this.mAudioFocusHelper = new AudioFocusHelper();
        }
        long j = this.mSeekWhenPrepared;
        if (j != 0) {
            this.mCurrentPosition = j;
        }
        if (this.mPlayerConfig.mAutoRotate) {
            this.mOrientationEventListener.enable();
        }
        initPlayer();
        startPrepare(false);
    }

    protected void startInPlaybackState() {
        this.mMediaPlayer.start();
        setPlayState(3);
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void pause() {
        if (isPlaying()) {
            this.mMediaPlayer.pause();
            setPlayState(4);
            setKeepScreenOn(false);
            AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
            if (audioFocusHelper != null) {
                audioFocusHelper.abandonFocus();
            }
        }
    }

    public void resume() {
        if (!isInPlaybackState() || this.mMediaPlayer.isPlaying()) {
            return;
        }
        this.mMediaPlayer.start();
        setPlayState(3);
        AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
        if (audioFocusHelper != null) {
            audioFocusHelper.requestFocus();
        }
        setKeepScreenOn(true);
    }

    public void stopPlayback() {
        AbstractPlayer abstractPlayer;
        if (this.mPlayerConfig.savingProgress && isInPlaybackState() && (abstractPlayer = this.mMediaPlayer) != null) {
            abstractPlayer.stop();
            setPlayState(0);
            AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
            if (audioFocusHelper != null) {
                audioFocusHelper.abandonFocus();
            }
            setKeepScreenOn(false);
        }
        onPlayStopped();
    }

    public void release() {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            try {
                abstractPlayer.reset();
                this.mMediaPlayer.release();
                this.mMediaPlayer = null;
                setPlayState(0);
                AudioFocusHelper audioFocusHelper = this.mAudioFocusHelper;
                if (audioFocusHelper != null) {
                    audioFocusHelper.abandonFocus();
                }
                setKeepScreenOn(false);
            } catch (NullPointerException | Exception unused) {
            }
        }
        onPlayStopped();
    }

    private void onPlayStopped() {
        BaseVideoController baseVideoController = this.mVideoController;
        if (baseVideoController != null) {
            baseVideoController.hideStatusView();
        }
        this.mOrientationEventListener.disable();
        this.mIsLockFullScreen = false;
        this.mCurrentPosition = 0L;
    }

    public void addOnVideoViewStateChangeListener(OnVideoViewStateChangeListener onVideoViewStateChangeListener) {
        if (this.mOnVideoViewStateChangeListeners == null) {
            this.mOnVideoViewStateChangeListeners = new ArrayList();
        }
        this.mOnVideoViewStateChangeListeners.add(onVideoViewStateChangeListener);
    }

    public void removeOnVideoViewStateChangeListener(OnVideoViewStateChangeListener onVideoViewStateChangeListener) {
        List<OnVideoViewStateChangeListener> list = this.mOnVideoViewStateChangeListeners;
        if (list != null) {
            list.remove(onVideoViewStateChangeListener);
        }
    }

    public void clearOnVideoViewStateChangeListeners() {
        List<OnVideoViewStateChangeListener> list = this.mOnVideoViewStateChangeListeners;
        if (list != null) {
            list.clear();
        }
    }

    protected boolean isInPlaybackState() {
        int i;
        return (this.mMediaPlayer == null || (i = this.mCurrentPlayState) == -1 || i == 0 || i == 1 || i == 5) ? false : true;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public long getDuration() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getDuration();
        }
        return 0L;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public long getCurrentPosition() {
        if (!isInPlaybackState()) {
            return 0L;
        }
        long currentPosition = this.mMediaPlayer.getCurrentPosition();
        this.mCurrentPosition = currentPosition;
        return currentPosition;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void seekTo(long j) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.seekTo(j);
            this.mSeekWhenPrepared = 0L;
        } else {
            this.mSeekWhenPrepared = j;
        }
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public boolean isPlaying() {
        return isInPlaybackState() && this.mMediaPlayer.isPlaying();
    }

    public boolean isPause() {
        return this.mCurrentPlayState == 4;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public int getBufferedPercentage() {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            return abstractPlayer.getBufferedPercentage();
        }
        return 0;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void setMute(boolean z) {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            this.mIsMute = z;
            float f = z ? 0.0f : 1.0f;
            abstractPlayer.setVolume(f, f);
        }
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public boolean isMute() {
        return this.mIsMute;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void setLock(boolean z) {
        this.mIsLockFullScreen = z;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public String getTitle() {
        return this.mCurrentTitle;
    }

    @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onError() {
        setPlayState(-1);
    }

    @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onCompletion() {
        setPlayState(5);
        setKeepScreenOn(false);
        this.mCurrentPosition = 0L;
    }

    @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onInfo(int i, int i2) {
        if (i == 3) {
            setPlayState(3);
            Log.d(TAG, "MEDIA_INFO_VIDEO_RENDERING_START:" + getWindowVisibility());
            getTrackInfo();
            return;
        }
        if (i == 901) {
            Log.d(TAG, "MEDIA_INFO_UNSUPPORTED_SUBTITLE:");
            return;
        }
        if (i == 902) {
            Log.d(TAG, "MEDIA_INFO_SUBTITLE_TIMED_OUT:");
            return;
        }
        if (i == 10001) {
            Log.d(TAG, "MEDIA_INFO_VIDEO_ROTATION_CHANGED: ");
            return;
        }
        if (i != 10002) {
            switch (i) {
                case 700:
                    Log.d(TAG, "MEDIA_INFO_VIDEO_TRACK_LAGGING:");
                    break;
                case 701:
                    Log.d(TAG, "MEDIA_INFO_BUFFERING_START:");
                    setPlayState(6);
                    break;
                case 702:
                    Log.d(TAG, "MEDIA_INFO_BUFFERING_END:");
                    setPlayState(7);
                    break;
                case IMediaPlayer.MEDIA_INFO_NETWORK_BANDWIDTH /* 703 */:
                    Log.d(TAG, "MEDIA_INFO_NETWORK_BANDWIDTH: ");
                    break;
                default:
                    switch (i) {
                        case 800:
                            Log.d(TAG, "MEDIA_INFO_BAD_INTERLEAVING:");
                            break;
                        case 801:
                            Log.d(TAG, "MEDIA_INFO_NOT_SEEKABLE:");
                            break;
                        case 802:
                            Log.d(TAG, "MEDIA_INFO_METADATA_UPDATE:");
                            break;
                    }
                    break;
            }
            return;
        }
        Log.d(TAG, "MEDIA_INFO_AUDIO_RENDERING_START:");
    }

    @Override // com.dueeeke.videoplayer.listener.PlayerEventListener
    public void onPrepared() {
        setPlayState(2);
        long j = this.mCurrentPosition;
        if (j > 0) {
            seekTo(j);
        }
    }

    public void setPlayerConfig(PlayerConfig playerConfig) {
        this.mPlayerConfig = playerConfig;
    }

    public int getCurrentPlayerState() {
        return this.mCurrentPlayerState;
    }

    public int getCurrentPlayState() {
        return this.mCurrentPlayState;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public long getTcpSpeed() {
        return this.mMediaPlayer.getTcpSpeed();
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void setSpeed(float f) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.setSpeed(f);
        }
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public IjkTrackInfo[] getTrackInfo() {
        if (isInPlaybackState()) {
            return this.mMediaPlayer.getTrackInfo();
        }
        return null;
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void setTrackInfo(int i) {
        if (isInPlaybackState()) {
            this.mMediaPlayer.setTrackInfo(i);
        }
    }

    @Override // com.dueeeke.videoplayer.controller.MediaPlayerControl
    public void refresh() {
        this.mCurrentPosition = 0L;
        retry();
    }

    public void setUrl(String str) {
        this.mCurrentUrl = str;
    }

    public void setUrl(String str, Map<String, String> map) {
        this.mCurrentUrl = str;
        this.mHeaders = map;
    }

    public void setAssetFileDescriptor(AssetFileDescriptor assetFileDescriptor) {
        this.mAssetFileDescriptor = assetFileDescriptor;
    }

    public void skipPositionWhenPlay(int i) {
        this.mCurrentPosition = i;
    }

    public void setTitle(String str) {
        if (str != null) {
            this.mCurrentTitle = str;
        }
    }

    public void setVolume(float f, float f2) {
        AbstractPlayer abstractPlayer = this.mMediaPlayer;
        if (abstractPlayer != null) {
            abstractPlayer.setVolume(f, f2);
        }
    }

    private class AudioFocusHelper implements AudioManager.OnAudioFocusChangeListener {
        private int currentFocus;
        private boolean pausedForLoss;
        private boolean startRequested;

        private AudioFocusHelper() {
            this.startRequested = false;
            this.pausedForLoss = false;
            this.currentFocus = 0;
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            if (this.currentFocus == i) {
                return;
            }
            this.currentFocus = i;
            if (i == -3) {
                if (BaseIjkVideoView.this.mMediaPlayer == null || !BaseIjkVideoView.this.isPlaying() || BaseIjkVideoView.this.mIsMute) {
                    return;
                }
                BaseIjkVideoView.this.mMediaPlayer.setVolume(0.1f, 0.1f);
                return;
            }
            if (i == -2 || i == -1) {
                if (BaseIjkVideoView.this.isPlaying()) {
                    this.pausedForLoss = true;
                }
            } else if (i == 1 || i == 2) {
                if (this.startRequested || this.pausedForLoss) {
                    BaseIjkVideoView.this.start();
                    this.startRequested = false;
                    this.pausedForLoss = false;
                }
                if (BaseIjkVideoView.this.mMediaPlayer == null || BaseIjkVideoView.this.mIsMute) {
                    return;
                }
                BaseIjkVideoView.this.mMediaPlayer.setVolume(1.0f, 1.0f);
            }
        }

        boolean requestFocus() {
            if (this.currentFocus == 1) {
                return true;
            }
            if (BaseIjkVideoView.this.mAudioManager == null) {
                return false;
            }
            if (1 == BaseIjkVideoView.this.mAudioManager.requestAudioFocus(this, 3, 1)) {
                this.currentFocus = 1;
                return true;
            }
            this.startRequested = true;
            return false;
        }

        boolean abandonFocus() {
            if (BaseIjkVideoView.this.mAudioManager == null) {
                return false;
            }
            this.startRequested = false;
            return 1 == BaseIjkVideoView.this.mAudioManager.abandonAudioFocus(this);
        }
    }
}
