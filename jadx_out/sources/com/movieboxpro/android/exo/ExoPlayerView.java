package com.movieboxpro.android.exo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.FragmentManager;
import androidx.media3.common.C;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.Tracks;
import androidx.media3.exoplayer.ExoPlayer;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.MpegAudioUtil;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.avery.subtitle.OnMediaStatusListener;
import com.avery.subtitle.model.Subtitle;
import com.avery.subtitle.model.Time;
import com.avery.subtitle.widget.SimpleSubtitleView2;
import com.dl7.player.media.MediaPlayerParams;
import com.dl7.player.model.SRTModel;
import com.dl7.player.model.TrackModel;
import com.dl7.player.model.languageModel;
import com.dl7.player.utils.AnimHelper;
import com.dl7.player.utils.MotionEventUtils;
import com.dl7.player.utils.NetWorkUtils;
import com.dl7.player.utils.SRT;
import com.dl7.player.utils.StringUtils;
import com.dl7.player.utils.WindowUtils;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.EnterPicModeEvent;
import com.movieboxpro.android.event.OnPlayStatusEvent;
import com.movieboxpro.android.event.PlayNextEpisodeEvent;
import com.movieboxpro.android.event.SwitchTestSpeedHintEvent;
import com.movieboxpro.android.listener.OnDownloadClickListener;
import com.movieboxpro.android.listener.OnVipClickListener;
import com.movieboxpro.android.listener.PlayerViewCallback;
import com.movieboxpro.android.listener.VideoPreviewListener;
import com.movieboxpro.android.livedata.SubtitleContentLiveData;
import com.movieboxpro.android.model.AudioTrackModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.player.IPlayerView;
import com.movieboxpro.android.player.MediaQualityInfo;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.GroupUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.tool.SPStaticUtils;
import com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity;
import com.movieboxpro.android.view.dialog.ChangeVolumeDialog;
import com.movieboxpro.android.view.dialog.ChoosePlaySpeedDialog;
import com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment;
import com.movieboxpro.android.view.dialog.LastSaidFragment;
import com.movieboxpro.android.view.dialog.SubtitleColorDialog;
import com.movieboxpro.android.view.dialog.SubtitleDialogFragment;
import com.movieboxpro.android.view.dialog.VideoQualityDialogFragment;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import master.flame.danmaku.danmaku.model.android.DanmakuFactory;
import me.jingbin.web.WebProgress;
import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTimeConstants;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes5.dex */
public class ExoPlayerView extends FrameLayout implements View.OnClickListener, IPlayerView {
    private static final int DANMAKU_TAG_ACFUN = 702;
    private static final int DANMAKU_TAG_BILI = 701;
    private static final int DANMAKU_TAG_CUSTOM = 703;
    private static final int DEFAULT_HIDE_TIMEOUT = 5000;
    private static final int DEFAULT_QUALITY_TIME = 300;
    private static final int INTERRUPT_WHEN_PAUSE = 503;
    private static final int INTERRUPT_WHEN_PLAY = 502;
    private static final int INTERVAL_TIME = 1000;
    private static final int INVALID_VALUE = -1;
    private static final int MAX_VIDEO_SEEK = 1000;
    public static final int MEDIA_QUALITY_BD = 4;
    public static final int MEDIA_QUALITY_HIGH = 2;
    public static final int MEDIA_QUALITY_MEDIUM = 1;
    public static final int MEDIA_QUALITY_SMOOTH = 0;
    public static final int MEDIA_QUALITY_SUPER = 3;
    private static final int MSG_ENABLE_ORIENTATION = 10087;
    private static final int MSG_TRY_RELOAD = 10088;
    private static final int MSG_UPDATE_SEEK = 10086;
    private static final int NORMAL_STATUS = 501;
    private static final String TAG = "IjkPlayerView";
    private static final int TEST_SPEED_TIME = 10000;
    private List<TrackModel> ListTracks;
    private OnVipClickListener QualityListener;
    private String audioOssFid;
    private ExoAudioPlayHelper audioPlayHelper;
    private String audioTrackId;
    private ArrayList<AudioTrackModel> audioTrackModels;
    private ExoAudioTracksDialogFragment audioTracksDialogFragment;
    private long bitStream;
    private int boxType;
    private ConstraintLayout clControl;
    private String currOriginalSid;
    private SRTModel.subTitles currSelectServerSubtitle;
    private String currSid;
    private String currSubtitleLanguage;
    private int currVolume;
    private int defaultScreenSize;
    private boolean firstPlaying;
    private FrameLayout flLastNext;
    private FrameLayout flSubtitleSetting;
    private FragmentManager fragmentManager;
    List<SRTModel.subTitles> getstr;
    private Runnable hideContinueText;
    private Runnable hidePlayPauseRunnable;
    private final Runnable hideQualityHintRunnable;
    private Runnable hideSeekBarRunnable;
    private List<BaseMediaModel.DownloadFile> hlsAudioTacks;
    private boolean isAnimation;
    public boolean isFrist;
    private boolean isOpenSubtitleBg;
    private ImageView ivHwDecoder;
    private ImageView ivScreenSize;
    private ImageView ivSkipImage;
    private ImageView ivSubtitleBg;
    private ImageView ivSubtitleDown;
    private ImageView ivSubtitlePos;
    private ImageView ivSubtitlePosClose;
    private ImageView ivSubtitleUp;
    private boolean keepControlBarVisible;
    private List<languageModel> languages;
    private int lastQualityPos;
    private LastSaidFragment lastSaidFragment;
    private SRTModel.subTitles lastSelectedSubtitle;
    private boolean leftBtnHint;
    private OnDownloadClickListener listener;
    private LinearLayout llContinue;
    private LinearLayout llEpisodeList;
    private LinearLayout llLastEpisode;
    private LinearLayout llNextEpisode;
    private LinearLayout llSeek;
    private LinearLayout llSubtitle;
    private TextView ll_srt_average;
    private ImageView ll_srt_close;
    private RelativeLayout ll_srt_size;
    private RelativeLayout ll_srt_speed;
    private int mAspectOptionsHeight;
    private AppCompatActivity mAttachActivity;
    private AudioManager mAudioManager;
    protected ImageView mBottomCCSize;
    protected ImageView mBottomCCSpeed;
    protected ImageView mBottomCCclose;
    protected ImageView mBottomCClast;
    protected ImageView mBottomSubtitleColor;
    protected ImageView mBottomTranslate;
    private IMediaPlayer.OnCompletionListener mCompletionListener;
    private int mCorrentLanguage;
    private float mCurBrightness;
    private int mCurPosition;
    public int mCurSelectQuality;
    private int mCurSelectSRT;
    private int mCurVolume;
    private long mExitTime;
    private ImageView mFlMediaTracks;
    private View mFlReload;
    private FrameLayout mFlVideoBox;
    private GestureDetector mGestureDetector;
    private Handler mHandler;
    private Runnable mHideBarRunnable;
    private Runnable mHideSkipTipRunnable;
    private Runnable mHideTouchViewRunnable;
    private IMediaPlayer.OnInfoListener mInfoListener;
    private int mInitHeight;
    private int mInterruptPosition;
    private boolean mIsAlwaysFullScreen;
    private boolean mIsBufferingStart;
    private boolean mIsForbidOrientation;
    private boolean mIsForbidTouch;
    private boolean mIsFullscreen;
    private boolean mIsNeedRecoverScreen;
    private boolean mIsNetConnected;
    private boolean mIsNeverPlay;
    private boolean mIsPlayComplete;
    private boolean mIsReady;
    private boolean mIsRenderingStart;
    private boolean mIsSeeking;
    private boolean mIsShowBar;
    private boolean mIsShowCC;
    private boolean mIsShowLrc;
    private boolean mIsShowQuality;
    private boolean mIsShowTracker;
    private ImageView mIvCancelSkip;
    private ImageView mIvMediaQuality;
    private ImageView mIvPlay;
    private ImageView mIvPlayCircle;
    private ImageView mIvPlayerLock;
    private List<SRT> mList;
    private LinearLayout mLlBottomBar;
    private View mLlSkipLayout;
    private ProgressBar mLoadingView;
    private int mMaxVolume;
    private String[] mMediaQualityDesc;
    private OrientationEventListener mOrientationListener;
    private IMediaPlayer.OnInfoListener mOutsideInfoListener;
    private GestureDetector.OnGestureListener mPlayerGestureListener;
    private SeekBar mPlayerSeek;
    public ImageView mPlayerThumb;
    private View.OnTouchListener mPlayerTouchListener;
    private List<MediaQualityInfo> mQualityData;
    private Matrix mSaveMatrix;
    private int mScreenUiVisibility;
    private final SeekBar.OnSeekBarChangeListener mSeekListener;
    private int mSkipPosition;
    private LinearLayout mSrtController;
    private ImageView mSrtSelect;
    private int mSrtSlelct;
    protected ImageView mSrtsizeAdd;
    protected ImageView mSrtsizeClose;
    protected TextView mSrtsizeText;
    protected ImageView mSrtsizesubstate;
    private long mTargetPosition;
    private TextView mTvCurTime;
    private TextView mTvDoSkip;
    private TextView mTvEndTime;
    private TextView mTvReload;
    private TextView mTvSkipTime;
    private Matrix mVideoMatrix;
    private SparseArray<String> mVideoSource;
    private int mVideoStatus;
    private ExoVideoView mVideoView;
    private int mWidthPixels;
    private List<Tracks.Group> mediaAudioTracks;
    private MediaQualityInfo mediaQualityInfo;
    private BaseMediaModel movieDownload;
    private String openSubtitle;
    private String pendingQualityHint;
    private AppCompatSeekBar playerSeekBar;
    private PlayerViewCallback playerViewCallback;
    private SharedPreferences prefs;
    private int qualityHintGeneration;
    private final Runnable qualitySwitchTimeoutRunnable;
    private int retryCount;
    private LinearLayout rlSubtitlePos;
    private int[] scaleImgRes;
    private Runnable seekToPositionRunnable;
    private Runnable seekToPositionRunnable2;
    private Runnable showContinueText;
    private boolean showOrg;
    private SimpleSubtitleView2 simpleSubtitleView;
    private List<Long> speeds;
    private ArrayList<SRT> srtList;
    private ImageView srtadd;
    private int srtsize;
    private int srtspeed;
    private ImageView srtsubstate;
    private TextView srttext;
    private HashMap<String, List<SRTModel.subTitles>> stringListHashMap;
    private String subtitleContent;
    private SubtitleDialogFragment subtitleDialogFragment;
    private View subtitleLine;
    private Disposable tcpSpeedDisposable;
    private Runnable testSpeedRunnable;
    private ImageView tvChangeVolume;
    private TextView tvContinue;
    private TextView tvQualityHint;
    private View tvSeekBarBottom;
    private View tvSeekBarTop;
    private TextView tvSkipText;
    private TextView tvSpeed;
    private TextView tvTcpSpeed;
    private TextView tvVideoTitle;
    private VideoPreviewListener videoPreviewListener;
    private VideoQualityDialogFragment videoQualityDialogFragment;
    public static int[] screenScales = {0, 3, 1};
    private static final int[] QUALITY_DRAWABLE_RES = {R.mipmap.ic_media_quality_smooth, R.mipmap.ic_media_quality_medium, R.mipmap.ic_media_quality_high, R.mipmap.ic_media_quality_super, com.dl7.player.R.mipmap.ic_media_quality_bd};

    private void RequestSrt() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideTouchView() {
    }

    private void _init_cc_lrclist() {
    }

    private void _setBrightnessInfo(float brightness) {
    }

    private void _setFastForward(String time) {
    }

    private void _setVolumeInfo(int volume) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleCCSelect() {
    }

    public ExoPlayerView setTitle(String title) {
        return this;
    }

    public void setToPosition(int position) {
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void showLoading() {
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public /* bridge */ /* synthetic */ IPlayerView setSRTList(LinkedHashMap srtList, OnDownloadClickListener listener, SRTModel.subTitles selectSubtitle) {
        return setSRTList((LinkedHashMap<String, List<SRTModel.subTitles>>) srtList, listener, selectSubtitle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0() {
        if (this.pendingQualityHint != null) {
            confirmQualitySwitchHint();
        }
    }

    public ExoPlayerView(Context context) {
        this(context, null);
    }

    public ExoPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.hideQualityHintRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.hideQualityHint();
            }
        };
        this.qualitySwitchTimeoutRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$new$0();
            }
        };
        this.srtList = new ArrayList<>();
        this.mHandler = new Handler() { // from class: com.movieboxpro.android.exo.ExoPlayerView.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == ExoPlayerView.MSG_UPDATE_SEEK) {
                    ExoPlayerView.this._setProgress();
                    if (!ExoPlayerView.this.mIsSeeking && ExoPlayerView.this.mIsShowBar && ExoPlayerView.this.mVideoView.isPlaying()) {
                        sendMessageDelayed(obtainMessage(ExoPlayerView.MSG_UPDATE_SEEK), 1000L);
                        return;
                    }
                    return;
                }
                if (msg.what == ExoPlayerView.MSG_ENABLE_ORIENTATION) {
                    if (ExoPlayerView.this.mOrientationListener != null) {
                        ExoPlayerView.this.mOrientationListener.enable();
                    }
                } else if (msg.what == ExoPlayerView.MSG_TRY_RELOAD) {
                    if (ExoPlayerView.this.mIsNetConnected) {
                        ExoPlayerView.this.reload();
                    }
                    sendMessageDelayed(obtainMessage(ExoPlayerView.MSG_TRY_RELOAD), C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                }
            }
        };
        this.mIsForbidTouch = false;
        this.mIsShowBar = true;
        this.mIsPlayComplete = false;
        this.mTargetPosition = -1L;
        this.mCurPosition = -1;
        this.mCurVolume = -1;
        this.mCurBrightness = -1.0f;
        this.mIsNeverPlay = true;
        this.mIsForbidOrientation = true;
        this.mIsAlwaysFullScreen = false;
        this.mExitTime = 0L;
        this.mVideoMatrix = new Matrix();
        this.mSaveMatrix = new Matrix();
        this.mIsNeedRecoverScreen = false;
        this.mIsReady = false;
        this.openSubtitle = "OpenSubtitle";
        this.mediaAudioTracks = new ArrayList();
        this.currVolume = 100;
        this.hideSeekBarRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.4
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView.this.llSeek.setVisibility(8);
                ExoPlayerView.this.RequestFlVideoBox();
            }
        };
        this.seekToPositionRunnable2 = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.5
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.seekTo(exoPlayerView.playerSeekBar.getProgress());
                ExoPlayerView.this.mHandler.sendEmptyMessage(ExoPlayerView.MSG_UPDATE_SEEK);
            }
        };
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.8
            private long curPosition;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar bar) {
                Log.d(ExoPlayerView.TAG, "onStartTrackingTouch--->");
                ExoPlayerView.this.mIsSeeking = true;
                ExoPlayerView.this._showControlBar(DateTimeConstants.MILLIS_PER_HOUR);
                ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                this.curPosition = ExoPlayerView.this.mVideoView.getCurrentPosition();
                ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                ExoPlayerView.this.mTargetPosition = (((long) ExoPlayerView.this.mVideoView.getDuration()) * ((long) progress)) / 1000;
                long j = (ExoPlayerView.this.mTargetPosition - this.curPosition) / 1000;
                ExoPlayerView.this.mTvCurTime.setText(StringUtils.generateTime(ExoPlayerView.this.mTargetPosition));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar bar) {
                Log.d(ExoPlayerView.TAG, "onStopTrackingTouch--->");
                ExoPlayerView.this._hideTouchView();
                ExoPlayerView.this.mIsSeeking = false;
            }
        };
        this.mHideBarRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.9
            @Override // java.lang.Runnable
            public void run() {
                if (ExoPlayerView.this.keepControlBarVisible) {
                    return;
                }
                ExoPlayerView.this._hideAllView(false);
            }
        };
        this.hidePlayPauseRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.12
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView.this.mIvPlayCircle.setVisibility(8);
            }
        };
        this.lastQualityPos = 0;
        this.audioTrackId = "";
        this.audioOssFid = "";
        this.defaultScreenSize = PrefsUtils.getInstance().getInt(Constant.Prefs.SCREEN_SCALE, 0);
        this.scaleImgRes = new int[]{R.mipmap.ic_screen_adapt, R.mipmap.ic_screen_stretch, R.mipmap.ic_screen_fill};
        this.mPlayerGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.18
            private boolean isDownTouch;
            private boolean isLandscape;
            private boolean isRecoverFromDanmaku;
            private boolean isVolume;

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e) {
                this.isDownTouch = true;
                this.isRecoverFromDanmaku = ExoPlayerView.this.recoverFromEditVideo();
                return super.onDown(e);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (!ExoPlayerView.this.mIsForbidTouch && !ExoPlayerView.this.mIsNeverPlay) {
                    float x = e1.getX();
                    float y = e1.getY() - e2.getY();
                    float x2 = x - e2.getX();
                    if (this.isDownTouch) {
                        this.isLandscape = Math.abs(distanceX) >= Math.abs(distanceY);
                        this.isVolume = x > ((float) ExoPlayerView.this.getResources().getDisplayMetrics().widthPixels) * 0.5f;
                        this.isDownTouch = false;
                    }
                    StringBuilder sb = new StringBuilder("onScroll--->");
                    float f = -x2;
                    sb.append(f / ExoPlayerView.this.mVideoView.getWidth());
                    Log.d(ExoPlayerView.TAG, sb.toString());
                    if (this.isLandscape) {
                        ExoPlayerView exoPlayerView = ExoPlayerView.this;
                        exoPlayerView._onProgressSlide(f / exoPlayerView.mVideoView.getWidth());
                    } else {
                        float height = y / ExoPlayerView.this.mVideoView.getHeight();
                        if (this.isVolume) {
                            ExoPlayerView.this._onVolumeSlide(height);
                        } else {
                            ExoPlayerView.this._onBrightnessSlide(height);
                        }
                    }
                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (this.isRecoverFromDanmaku) {
                    return true;
                }
                if (ExoPlayerView.this.mIsShowCC) {
                    ExoPlayerView.this._toggleCCSelect();
                }
                if (ExoPlayerView.this.mIsShowTracker) {
                    ExoPlayerView.this._toggleTrackSelect();
                }
                if (ExoPlayerView.this.mIsShowLrc) {
                    ExoPlayerView.this._toggleSrtSelect();
                }
                if (ExoPlayerView.this.mIsShowQuality) {
                    ExoPlayerView.this._toggleMediaQuality();
                } else {
                    ExoPlayerView.this._toggleControlBar();
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent e) {
                if (!ExoPlayerView.this.mIsNeverPlay && !this.isRecoverFromDanmaku && !ExoPlayerView.this.mIsForbidTouch) {
                    ExoPlayerView.this._refreshHideRunnable();
                    ExoPlayerView.this._togglePlayStatus(true);
                }
                return true;
            }
        };
        this.mHideTouchViewRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.19
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView.this._hideTouchView();
            }
        };
        this.mPlayerTouchListener = new View.OnTouchListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.20
            private static final int INVALID_POINTER = 2;
            private static final int NORMAL = 1;
            private static final int ZOOM_AND_ROTATE = 3;
            private float oldDist;
            private float scale;
            private int mode = 1;
            private PointF midPoint = new PointF(0.0f, 0.0f);
            private float degree = 0.0f;
            private int fingerFlag = -1;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int actionMasked = MotionEventCompat.getActionMasked(event);
                if (actionMasked == 0) {
                    this.mode = 1;
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                } else if (actionMasked != 2) {
                    if (actionMasked != 5) {
                        if (actionMasked == 6) {
                            if (this.mode == 3) {
                                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                                exoPlayerView.mIsNeedRecoverScreen = exoPlayerView.mVideoView.adjustVideoView(this.scale);
                            }
                            this.mode = 2;
                        }
                    } else if (event.getPointerCount() == 3 && ExoPlayerView.this.mIsFullscreen) {
                        ExoPlayerView.this._hideTouchView();
                        this.mode = 3;
                        MotionEventUtils.midPoint(this.midPoint, event);
                        int iCalcFingerFlag = MotionEventUtils.calcFingerFlag(event);
                        this.fingerFlag = iCalcFingerFlag;
                        this.degree = MotionEventUtils.rotation(event, iCalcFingerFlag);
                        this.oldDist = MotionEventUtils.calcSpacing(event, this.fingerFlag);
                        ExoPlayerView exoPlayerView2 = ExoPlayerView.this;
                        exoPlayerView2.mSaveMatrix = exoPlayerView2.mVideoView.getVideoTransform();
                    } else {
                        this.mode = 2;
                    }
                } else if (this.mode == 3) {
                    ExoPlayerView.this.mVideoView.setVideoRotation((int) (MotionEventUtils.rotation(event, this.fingerFlag) - this.degree));
                    ExoPlayerView.this.mVideoMatrix.set(ExoPlayerView.this.mSaveMatrix);
                    this.scale = MotionEventUtils.calcSpacing(event, this.fingerFlag) / this.oldDist;
                    Matrix matrix = ExoPlayerView.this.mVideoMatrix;
                    float f = this.scale;
                    matrix.postScale(f, f, this.midPoint.x, this.midPoint.y);
                    ExoPlayerView.this.mVideoView.setVideoTransform(ExoPlayerView.this.mVideoMatrix);
                }
                if (this.mode != 1) {
                    return false;
                }
                if (ExoPlayerView.this.mGestureDetector.onTouchEvent(event)) {
                    return true;
                }
                if (MotionEventCompat.getActionMasked(event) != 1) {
                    return false;
                }
                ExoPlayerView.this._endGesture();
                return false;
            }
        };
        this.mIsRenderingStart = false;
        this.mIsBufferingStart = false;
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.21
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int status, int extra) {
                ExoPlayerView.this._switchStatus(status, extra);
                if (ExoPlayerView.this.mOutsideInfoListener == null) {
                    return true;
                }
                ExoPlayerView.this.mOutsideInfoListener.onInfo(iMediaPlayer, status, extra);
                return true;
            }
        };
        this.showContinueText = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.22
            @Override // java.lang.Runnable
            public void run() {
                if (ExoPlayerView.this.mVideoView.getCurrentPosition() > 3000) {
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.hideContinueText);
                    ExoPlayerView.this.llContinue.setVisibility(0);
                    ExoPlayerView.this.llContinue.requestFocus();
                    ExoPlayerView.this.tvContinue.setText(String.format("Continue %s,", StringUtils.generateTime(ExoPlayerView.this.mVideoView.getCurrentPosition())));
                    ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.hideContinueText, DanmakuFactory.MIN_DANMAKU_DURATION);
                }
            }
        };
        this.hideContinueText = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.23
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView.this.llContinue.setVisibility(8);
                if (ExoPlayerView.this.ivSkipImage != null) {
                    ExoPlayerView.this.ivSkipImage.setVisibility(0);
                }
                if (ExoPlayerView.this.tvSkipText != null) {
                    ExoPlayerView.this.tvSkipText.setVisibility(0);
                }
            }
        };
        this.firstPlaying = true;
        this.speeds = new ArrayList();
        this.testSpeedRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.25
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView.this.showTestSpeedHint();
            }
        };
        this.seekToPositionRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.28
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.seekTo(exoPlayerView.mPlayerSeek.getProgress());
                ExoPlayerView.this.mHandler.sendEmptyMessage(ExoPlayerView.MSG_UPDATE_SEEK);
            }
        };
        this.mIsShowCC = false;
        this.languages = new ArrayList();
        this.stringListHashMap = new HashMap<>();
        this.getstr = new ArrayList();
        this.mSrtSlelct = -1;
        this.mCorrentLanguage = -1;
        this.mCurSelectSRT = -1;
        this.srtsize = 16;
        this.mList = new ArrayList();
        this.mIsShowLrc = false;
        this.isFrist = false;
        this.srtspeed = 0;
        this.ListTracks = new ArrayList();
        this.mIsShowTracker = false;
        this.mVideoSource = new SparseArray<>();
        this.mIsShowQuality = false;
        this.mCurSelectQuality = 0;
        this.bitStream = 0L;
        this.mSkipPosition = -1;
        this.mHideSkipTipRunnable = new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.47
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView.this._hideSkipTip();
            }
        };
        this.mVideoStatus = 501;
        this.leftBtnHint = true;
        _initView(context);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setPlayerViewCallback(PlayerViewCallback playerViewCallback) {
        this.playerViewCallback = playerViewCallback;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void doPause() {
        pause();
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setFragmentManager(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public ExoVideoView getVideoView() {
        return this.mVideoView;
    }

    private void _initView(Context context) {
        if (context instanceof AppCompatActivity) {
            this.mAttachActivity = (AppCompatActivity) context;
            View.inflate(context, R.layout.new_layout_exo_player_view, this);
            this.mVideoView = (ExoVideoView) findViewById(R.id.video_view);
            this.mPlayerThumb = (ImageView) findViewById(R.id.iv_thumb);
            this.mLoadingView = (ProgressBar) findViewById(R.id.pb_loading);
            this.clControl = (ConstraintLayout) findViewById(R.id.clControl);
            this.tvSeekBarTop = findViewById(R.id.tvSeekBarTop);
            this.tvSeekBarBottom = findViewById(R.id.tvSeekBarBottom);
            this.mIvPlay = (ImageView) findViewById(R.id.ic_iv_play);
            this.flSubtitleSetting = (FrameLayout) findViewById(R.id.flSubtitleSetting);
            this.mTvCurTime = (TextView) findViewById(R.id.tv_cur_time);
            this.mPlayerSeek = (SeekBar) findViewById(R.id.player_seek);
            this.mTvEndTime = (TextView) findViewById(R.id.tv_end_time);
            this.mLlBottomBar = (LinearLayout) findViewById(R.id.ll_bottom_bar);
            this.tvTcpSpeed = (TextView) findViewById(R.id.tvTcpSpeed);
            this.llSeek = (LinearLayout) findViewById(R.id.llSeek);
            this.playerSeekBar = (AppCompatSeekBar) findViewById(R.id.playerSeekBar);
            this.simpleSubtitleView = (SimpleSubtitleView2) findViewById(R.id.simpleSubtitleView);
            ImageView imageView = (ImageView) findViewById(R.id.tv_screen_size);
            this.ivScreenSize = imageView;
            int i = this.defaultScreenSize;
            int[] iArr = this.scaleImgRes;
            if (i >= iArr.length) {
                this.defaultScreenSize = 0;
                imageView.setImageResource(iArr[0]);
            }
            this.ivScreenSize.setOnClickListener(this);
            this.ivSubtitleBg = (ImageView) findViewById(R.id.ll_bottom_controller_bg);
            ImageView imageView2 = (ImageView) findViewById(R.id.ivHwDecoder);
            this.ivHwDecoder = imageView2;
            imageView2.setOnClickListener(this);
            this.tvVideoTitle = (TextView) findViewById(R.id.tvVideoTitle);
            this.flLastNext = (FrameLayout) findViewById(R.id.flLastNext);
            LinearLayout linearLayout = (LinearLayout) findViewById(R.id.llLastEpisode);
            this.llLastEpisode = linearLayout;
            linearLayout.setOnClickListener(this);
            LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.llNextEpisode);
            this.llNextEpisode = linearLayout2;
            linearLayout2.setOnClickListener(this);
            LinearLayout linearLayout3 = (LinearLayout) findViewById(R.id.llEpisodeList);
            this.llEpisodeList = linearLayout3;
            linearLayout3.setOnClickListener(this);
            findViewById(R.id.tvChangeVolume).setOnClickListener(this);
            setFocusable(this.mPlayerSeek);
            this.llContinue = (LinearLayout) findViewById(R.id.llContinue);
            this.tvContinue = (TextView) findViewById(R.id.tvContinue);
            this.tvSkipText = (TextView) findViewById(R.id.tvSkipText);
            this.ivSkipImage = (ImageView) findViewById(R.id.ivSkipImage);
            this.tvQualityHint = (TextView) findViewById(R.id.tvQualityHint);
            TextView textView = (TextView) findViewById(R.id.tvSpeed);
            this.tvSpeed = textView;
            textView.setOnClickListener(this);
            this.tvChangeVolume = (ImageView) findViewById(R.id.tvChangeVolume);
            this.ivHwDecoder.setVisibility(8);
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.HW_DECODER, true)) {
                this.ivHwDecoder.setImageResource(R.mipmap.ic_hw_decoder_enable);
            } else {
                this.ivHwDecoder.setImageResource(R.mipmap.ic_hw_decoder_disable);
            }
            ImageView imageView3 = (ImageView) findViewById(R.id.tv_pic_mode);
            if (Build.VERSION.SDK_INT >= 26) {
                imageView3.setOnClickListener(this);
                imageView3.setVisibility(0);
            } else {
                imageView3.setVisibility(8);
            }
            ((FrameLayout) findViewById(R.id.flPlayerEngine)).setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda9
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$_initView$1(view);
                }
            });
            ((TextView) findViewById(R.id.tvPlayerEngine)).setText("EXO");
            boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.OPEN_SUBTITLE_BG, false);
            this.isOpenSubtitleBg = z;
            if (z) {
                this.ivSubtitleBg.setImageResource(R.mipmap.ic_cc_bg_open);
                this.simpleSubtitleView.setBackgroundColor(Color.parseColor("#B3000000"));
            } else {
                this.ivSubtitleBg.setImageResource(R.mipmap.ic_cc_bg);
                this.simpleSubtitleView.setBackgroundColor(0);
            }
            this.simpleSubtitleView.changeColor(Color.parseColor("#" + SPStaticUtils.getString(Constant.Prefs.SUBTITLE_COLOR, "FFFFFF")));
            this.ivSubtitleBg.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda10
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$_initView$2(view);
                }
            });
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_video_box);
            this.mFlVideoBox = frameLayout;
            frameLayout.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.2
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int i2, KeyEvent keyEvent) {
                    Log.d(ExoPlayerView.TAG, "mIsShowCC--->" + i2);
                    if (i2 != 4) {
                        if (i2 != 66) {
                            if (i2 == 82) {
                                if (keyEvent.getAction() == 0) {
                                    if (ExoPlayerView.this.mIsShowCC) {
                                        Log.d(ExoPlayerView.TAG, "mIsShowCC--->");
                                        ExoPlayerView.this._toggleCCSelect();
                                        return true;
                                    }
                                    if (ExoPlayerView.this.mIsShowQuality) {
                                        Log.d(ExoPlayerView.TAG, "mIsShowQuality--->");
                                        ExoPlayerView.this._toggleMediaQuality();
                                        return true;
                                    }
                                    if (ExoPlayerView.this.mIsShowTracker) {
                                        ExoPlayerView.this._toggleTrackSelect();
                                        return true;
                                    }
                                    if (ExoPlayerView.this.mIsShowLrc) {
                                        ExoPlayerView.this._toggleSrtSelect();
                                        return true;
                                    }
                                    if (ExoPlayerView.this.mIsShowBar || ExoPlayerView.this.isAnimation) {
                                        return true;
                                    }
                                    Log.d(ExoPlayerView.TAG, "mIsShowBar--->" + ExoPlayerView.this.mSrtController.getVisibility());
                                    ExoPlayerView.this._toggleControlBar();
                                    ExoPlayerView.this.RequestmPlayerSeek();
                                    ExoPlayerView.this.mSrtSelect.requestFocus();
                                }
                                return true;
                            }
                            if (i2 == 85) {
                                if (keyEvent.getAction() != 0) {
                                    return false;
                                }
                                ExoPlayerView.this._togglePlayStatus(false);
                                return true;
                            }
                            if (i2 == 89) {
                                if (keyEvent.getAction() != 0) {
                                    return false;
                                }
                                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                                exoPlayerView.seekTo(exoPlayerView.mVideoView.getCurrentPosition() - 10000);
                                return true;
                            }
                            if (i2 == 90) {
                                if (keyEvent.getAction() != 0) {
                                    return false;
                                }
                                if (ExoPlayerView.this.mVideoView != null) {
                                    ExoPlayerView exoPlayerView2 = ExoPlayerView.this;
                                    exoPlayerView2.seekTo(exoPlayerView2.mVideoView.getCurrentPosition() + 10000);
                                }
                                return true;
                            }
                            switch (i2) {
                                case 19:
                                case 20:
                                    Log.d(ExoPlayerView.TAG, "right--->");
                                    if (keyEvent.getAction() == 0) {
                                        if (ExoPlayerView.this.mIsShowCC) {
                                            Log.d(ExoPlayerView.TAG, "mIsShowCC--->");
                                            ExoPlayerView.this._toggleCCSelect();
                                            return true;
                                        }
                                        if (ExoPlayerView.this.mIsShowQuality) {
                                            Log.d(ExoPlayerView.TAG, "mIsShowQuality--->");
                                            ExoPlayerView.this._toggleMediaQuality();
                                            return true;
                                        }
                                        if (ExoPlayerView.this.mIsShowTracker) {
                                            ExoPlayerView.this._toggleTrackSelect();
                                            return true;
                                        }
                                        if (ExoPlayerView.this.mIsShowLrc) {
                                            ExoPlayerView.this._toggleSrtSelect();
                                            return true;
                                        }
                                        if (ExoPlayerView.this.mIsShowBar) {
                                            Log.d(ExoPlayerView.TAG, "mIsShowBar--->true");
                                        } else {
                                            Log.d(ExoPlayerView.TAG, "mIsShowBar--->false");
                                        }
                                        if (!ExoPlayerView.this.mIsShowBar) {
                                            if (ExoPlayerView.this.isAnimation) {
                                                return true;
                                            }
                                            Log.d(ExoPlayerView.TAG, "mIsShowBar--->" + ExoPlayerView.this.mSrtController.getVisibility());
                                            ExoPlayerView.this.clControl.setVisibility(0);
                                            ExoPlayerView.this.flSubtitleSetting.setVisibility(0);
                                            ExoPlayerView.this._toggleControlBar();
                                            ExoPlayerView.this.mSrtSelect.requestFocus();
                                            ExoPlayerView.this.mSrtSelect.setFocusableInTouchMode(true);
                                            return true;
                                        }
                                    }
                                    break;
                                case 21:
                                case 22:
                                    break;
                                case 23:
                                    break;
                                default:
                                    return false;
                            }
                            if (ExoPlayerView.this.mIsShowBar) {
                                return false;
                            }
                            if (keyEvent.getAction() == 0) {
                                if (ExoPlayerView.this.llContinue.getVisibility() != 0) {
                                    if (ExoPlayerView.this.isAnimation) {
                                        return true;
                                    }
                                    ExoPlayerView.this.llSeek.setVisibility(0);
                                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.hideSeekBarRunnable);
                                    ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.hideSeekBarRunnable, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                                    ExoPlayerView.this.playerSeekBar.requestFocus();
                                    ExoPlayerView.this.playerSeekBar.setFocusableInTouchMode(true);
                                    if (i2 == 21) {
                                        ExoPlayerView.this.playerSeekBar.setProgress(ExoPlayerView.this.mVideoView.getCurrentPosition() - 10000);
                                    } else {
                                        ExoPlayerView.this.playerSeekBar.setProgress(ExoPlayerView.this.mVideoView.getCurrentPosition() + 10000);
                                    }
                                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable2);
                                    ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.seekToPositionRunnable2, 1000L);
                                } else if (i2 == 21) {
                                    ExoPlayerView.this.mVideoView.seekTo(0);
                                    ExoPlayerView.this.llContinue.setVisibility(8);
                                } else if (i2 == 22 && !ExoPlayerView.this.leftBtnHint) {
                                    EventBus.getDefault().post(new PlayNextEpisodeEvent());
                                    ExoPlayerView.this.llContinue.setVisibility(8);
                                    ExoPlayerView.this._hideAllView(false);
                                }
                            }
                            return true;
                        }
                        Log.d(ExoPlayerView.TAG, "enter--->");
                        if (keyEvent.getAction() == 0) {
                            if (ExoPlayerView.this.mIsShowCC) {
                                Log.d(ExoPlayerView.TAG, "mIsShowCC--->");
                                ExoPlayerView.this._toggleCCSelect();
                                return true;
                            }
                            if (ExoPlayerView.this.mIsShowQuality) {
                                Log.d(ExoPlayerView.TAG, "mIsShowQuality--->");
                                ExoPlayerView.this._toggleMediaQuality();
                                return true;
                            }
                            if (ExoPlayerView.this.mIsShowTracker) {
                                Log.d(ExoPlayerView.TAG, "mIsShowTracker--->");
                                ExoPlayerView.this._toggleTrackSelect();
                                return true;
                            }
                            if (ExoPlayerView.this.mIsShowLrc) {
                                Log.d(ExoPlayerView.TAG, "mIsShowTracker--->");
                                ExoPlayerView.this._toggleSrtSelect();
                                return true;
                            }
                            if (!ExoPlayerView.this.mIsShowBar) {
                                if (ExoPlayerView.this.isAnimation) {
                                    return true;
                                }
                                Log.d(ExoPlayerView.TAG, "mIsShowBar--->");
                                ExoPlayerView.this._togglePlayStatus(false);
                                return true;
                            }
                        }
                        return false;
                    }
                    Log.d(ExoPlayerView.TAG, "back--->");
                    return false;
                }
            });
            this.playerSeekBar.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda11
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$_initView$3(view);
                }
            });
            this.tvSeekBarTop.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda12
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z2) {
                    this.f$0.lambda$_initView$4(view, z2);
                }
            });
            this.tvSeekBarBottom.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda13
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view, boolean z2) {
                    this.f$0.lambda$_initView$5(view, z2);
                }
            });
            ImageView imageView4 = (ImageView) findViewById(R.id.iv_player_lock);
            this.mIvPlayerLock = imageView4;
            imageView4.setFocusable(true);
            this.mIvPlayerLock.setFocusableInTouchMode(true);
            this.mIvPlayCircle = (ImageView) findViewById(R.id.iv_play_circle);
            this.mTvReload = (TextView) findViewById(R.id.tv_reload);
            this.mFlReload = findViewById(R.id.fl_reload_layout);
            _initMediaQuality();
            _initVideoSkip();
            _initCCSlect();
            _initMediaTrack();
            this.mIvPlay.setOnClickListener(this);
            this.mIvPlayerLock.setOnClickListener(this);
            this.mIvPlayCircle.setOnClickListener(this);
            this.mTvReload.setOnClickListener(this);
            this.mVideoView.setOnErrorListener(new IMediaPlayer.OnErrorListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.3
                @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnErrorListener
                public boolean onError(IMediaPlayer iMediaPlayer, int errorCode, int i1) {
                    if (errorCode == -10000 && ExoPlayerView.this.retryCount <= 3) {
                        ExoPlayerView.this.reload();
                        ExoPlayerView.this.retryCount++;
                    }
                    return true;
                }
            });
            return;
        }
        throw new IllegalArgumentException("Context must be AppCompatActivity");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_initView$1(View view) {
        if (this.mediaQualityInfo.isHls()) {
            PlayerViewCallback playerViewCallback = this.playerViewCallback;
            if (playerViewCallback != null) {
                playerViewCallback.showPlayerEngine();
                return;
            }
            return;
        }
        PlayerViewCallback playerViewCallback2 = this.playerViewCallback;
        if (playerViewCallback2 != null) {
            playerViewCallback2.showPlayerEngine();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_initView$2(View view) {
        boolean z = this.isOpenSubtitleBg;
        this.isOpenSubtitleBg = !z;
        if (!z) {
            this.simpleSubtitleView.setBackgroundColor(Color.parseColor("#B3000000"));
            this.ivSubtitleBg.setImageResource(R.mipmap.ic_cc_bg_open);
        } else {
            this.ivSubtitleBg.setImageResource(R.mipmap.ic_cc_bg);
            this.simpleSubtitleView.setBackgroundColor(0);
        }
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.OPEN_SUBTITLE_BG, this.isOpenSubtitleBg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_initView$3(View view) {
        _togglePlayStatus(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_initView$4(View view, boolean z) {
        if (!z || this.isAnimation) {
            return;
        }
        this.mHandler.removeCallbacks(this.hideSeekBarRunnable);
        this.llSeek.setVisibility(8);
        this.clControl.setVisibility(0);
        this.flSubtitleSetting.setVisibility(0);
        _toggleControlBar();
        this.mSrtSelect.requestFocus();
        this.mSrtSelect.setFocusableInTouchMode(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_initView$5(View view, boolean z) {
        if (!z || this.isAnimation) {
            return;
        }
        this.mHandler.removeCallbacks(this.hideSeekBarRunnable);
        this.llSeek.setVisibility(8);
        this.clControl.setVisibility(0);
        this.flSubtitleSetting.setVisibility(0);
        _toggleControlBar();
        this.mSrtSelect.requestFocus();
        this.mSrtSelect.setFocusableInTouchMode(true);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setMovieDownload(BaseMediaModel baseMediaModel) {
        this.movieDownload = baseMediaModel;
    }

    private void initSeekBar2() {
        this.playerSeekBar.setMax(this.mVideoView.getDuration());
        this.playerSeekBar.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.6
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode != 21) {
                    if (keyCode != 22) {
                        return false;
                    }
                } else {
                    if (event.getAction() == 0) {
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.hideSeekBarRunnable);
                        ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable2);
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                        int repeatCount = event.getRepeatCount();
                        if (repeatCount == 0) {
                            ExoPlayerView.this.playerSeekBar.setProgress(ExoPlayerView.this.playerSeekBar.getProgress() - 10000);
                            if (ExoPlayerView.this.videoPreviewListener != null) {
                                ExoPlayerView.this.videoPreviewListener.startDrag();
                            }
                        } else {
                            ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable2);
                            int progress = ExoPlayerView.this.playerSeekBar.getProgress();
                            if (repeatCount < 40) {
                                ExoPlayerView.this.playerSeekBar.setProgress(progress - 10000);
                            } else if (repeatCount < 70) {
                                ExoPlayerView.this.playerSeekBar.setProgress(progress - 20000);
                            } else {
                                ExoPlayerView.this.playerSeekBar.setProgress(progress - MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND);
                            }
                        }
                        ExoPlayerView.this.mTvCurTime.setText(StringUtils.generateTime(ExoPlayerView.this.playerSeekBar.getProgress()));
                        ExoPlayerView.this.mTvEndTime.setText(StringUtils.generateTime(ExoPlayerView.this.mVideoView.getDuration() - ExoPlayerView.this.playerSeekBar.getProgress()));
                        ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable2);
                        ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.seekToPositionRunnable2, 1000L);
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                        ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.mHideBarRunnable, 5000L);
                        return true;
                    }
                    if (event.getAction() == 1) {
                        ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.hideSeekBarRunnable, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                        if (ExoPlayerView.this.videoPreviewListener != null) {
                            ExoPlayerView.this.videoPreviewListener.stopDrag();
                        }
                        return true;
                    }
                }
                if (event.getAction() == 0) {
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.hideSeekBarRunnable);
                    ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable2);
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                    int repeatCount2 = event.getRepeatCount();
                    if (repeatCount2 == 0) {
                        if (ExoPlayerView.this.videoPreviewListener != null) {
                            ExoPlayerView.this.videoPreviewListener.startDrag();
                        }
                        ExoPlayerView.this.playerSeekBar.setProgress(ExoPlayerView.this.playerSeekBar.getProgress() + 10000);
                    } else {
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable2);
                        int progress2 = ExoPlayerView.this.playerSeekBar.getProgress();
                        if (repeatCount2 < 40) {
                            ExoPlayerView.this.playerSeekBar.setProgress(progress2 + 10000);
                        } else if (repeatCount2 < 70) {
                            ExoPlayerView.this.playerSeekBar.setProgress(progress2 + AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
                        } else {
                            ExoPlayerView.this.playerSeekBar.setProgress(progress2 + MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND);
                        }
                    }
                    ExoPlayerView.this.mTvCurTime.setText(StringUtils.generateTime(ExoPlayerView.this.playerSeekBar.getProgress()));
                    ExoPlayerView.this.mTvEndTime.setText(StringUtils.generateTime(ExoPlayerView.this.mVideoView.getDuration() - ExoPlayerView.this.playerSeekBar.getProgress()));
                    ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable2);
                    ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.seekToPositionRunnable2, 1000L);
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                    ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.mHideBarRunnable, 5000L);
                    return true;
                }
                if (event.getAction() != 1) {
                    return false;
                }
                if (ExoPlayerView.this.videoPreviewListener != null) {
                    ExoPlayerView.this.videoPreviewListener.stopDrag();
                }
                ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.hideSeekBarRunnable, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
                return true;
            }
        });
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void releaseAudioPlay() {
        this.mVideoView.getMediaPlayer().setVolume(1.0f, 1.0f);
        this.audioOssFid = "";
        ExoAudioPlayHelper exoAudioPlayHelper = this.audioPlayHelper;
        if (exoAudioPlayHelper != null) {
            exoAudioPlayHelper.release();
        }
    }

    private void _initMediaPlayer() {
        AudioManager audioManager = (AudioManager) this.mAttachActivity.getSystemService("audio");
        this.mAudioManager = audioManager;
        this.mMaxVolume = audioManager.getStreamMaxVolume(3);
        this.mAttachActivity.getWindow().getDecorView().setSystemUiVisibility(5894);
        this.mAttachActivity.getWindow().addFlags(1024);
        this.mVideoView.setOnInfoListener(this.mInfoListener);
        this.mVideoView.setOnVideoPinFailed(new OnVideoPinFailed() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda0
            @Override // com.movieboxpro.android.exo.OnVideoPinFailed
            public final void onVideoPinFailed(String str) {
                this.f$0.lambda$_initMediaPlayer$6(str);
            }
        });
        this.mVideoView.setOnHlsVideoSizeChanged(new ExoVideoView.OnHlsVideoSizeChanged() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda5
            @Override // com.movieboxpro.android.exo.ExoVideoView.OnHlsVideoSizeChanged
            public final void onHlsVideoSizeChanged(int i, int i2) {
                this.f$0.lambda$_initMediaPlayer$7(i, i2);
            }
        });
        this.mGestureDetector = new GestureDetector(this.mAttachActivity, this.mPlayerGestureListener);
        RequestFlVideoBox();
        OrientationEventListener orientationEventListener = new OrientationEventListener(this.mAttachActivity) { // from class: com.movieboxpro.android.exo.ExoPlayerView.7
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int orientation) {
                ExoPlayerView.this._handleOrientation(orientation);
            }
        };
        this.mOrientationListener = orientationEventListener;
        if (this.mIsForbidOrientation) {
            orientationEventListener.disable();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_initMediaPlayer$6(String str) {
        cancelQualityHintCallbacks();
        this.pendingQualityHint = null;
        hideQualityHint();
        List<MediaQualityInfo> list = this.mQualityData;
        if (list == null) {
            return;
        }
        int i = this.mCurSelectQuality;
        MediaQualityInfo mediaQualityInfo = (i < 0 || i >= list.size()) ? null : this.mQualityData.get(this.mCurSelectQuality);
        if (mediaQualityInfo == null || TextUtils.isEmpty(mediaQualityInfo.getPath())) {
            return;
        }
        this.mVideoView.setPlayingHlsMaster(false);
        this.mVideoView.setPendingVideoQuality(null);
        tearDownExtraAudioForReload();
        this.mLoadingView.setVisibility(0);
        changeQuality(mediaQualityInfo.getPath(), this.mVideoView.getCurrentPosition(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_initMediaPlayer$7(int i, int i2) {
        if (HlsVideoTrackSwitcher.shouldConfirmSwitchHint(i, i2, this.pendingQualityHint)) {
            confirmQualitySwitchHint();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (this.mInitHeight == 0) {
            this.mInitHeight = getHeight();
            this.mWidthPixels = getResources().getDisplayMetrics().widthPixels;
        }
    }

    public void onResume() {
        this.mVideoView.resume();
        if (!this.mIsForbidTouch && !this.mIsForbidOrientation) {
            this.mOrientationListener.enable();
        }
        int i = this.mCurPosition;
        if (i != -1) {
            seekTo(i);
            this.mCurPosition = -1;
        }
        this.mIvPlay.setSelected(true);
    }

    public void onPause() {
        this.mCurPosition = this.mVideoView.getCurrentPosition();
        this.mVideoView.pause();
        this.mIvPlay.setSelected(false);
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public int onStop() {
        return this.mVideoView.getCurrentPosition();
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public int onDestroy() {
        int currentPosition = this.mVideoView.getCurrentPosition();
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            simpleSubtitleView2.destroy();
        }
        Disposable disposable = this.tcpSpeedDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.mVideoView.destroy();
        this.mHandler.removeMessages(MSG_TRY_RELOAD);
        this.mHandler.removeMessages(MSG_UPDATE_SEEK);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mAttachActivity.getWindow().clearFlags(128);
        ExoAudioPlayHelper exoAudioPlayHelper = this.audioPlayHelper;
        if (exoAudioPlayHelper != null) {
            exoAudioPlayHelper.release();
        }
        return currentPosition;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public boolean handleVolumeKey(int keyCode) {
        if (keyCode == 24) {
            _setVolume(true);
            return true;
        }
        if (keyCode != 25) {
            return false;
        }
        _setVolume(false);
        return true;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public boolean onBackPressed() {
        Log.d("VIEWDOPLAY", "mIsShowCC  " + this.mIsShowCC + "  mIsShowQuality  " + this.mIsShowQuality + "  mVideoStatus  " + this.mVideoStatus + "  mIsFullscreen  " + this.mIsFullscreen + "  mIsFullscreen  " + this.mIsFullscreen);
        if (this.mIsShowBar) {
            this.mHandler.removeCallbacks(this.mHideBarRunnable);
            _toggleControlBar();
            RequestFlVideoBox();
            return true;
        }
        if (this.llSeek.getVisibility() == 0) {
            this.mHandler.removeCallbacks(this.hideSeekBarRunnable);
            this.llSeek.setVisibility(8);
            RequestFlVideoBox();
            return true;
        }
        if (this.mIsShowCC) {
            _toggleCCSelect();
            RequestFlVideoBox();
            return true;
        }
        if (this.mIsShowQuality) {
            _toggleMediaQuality();
            return true;
        }
        if (this.mIsShowTracker) {
            _toggleTrackSelect();
            return true;
        }
        if (this.mIsShowLrc) {
            _toggleSrtSelect();
            return true;
        }
        if (this.mIsAlwaysFullScreen) {
            _exit();
            return true;
        }
        if (!this.mIsFullscreen) {
            return false;
        }
        this.mAttachActivity.setRequestedOrientation(1);
        if (this.mIsForbidTouch) {
            this.mIsForbidTouch = false;
            this.mIvPlayerLock.setSelected(false);
            _setControlBarVisible(this.mIsShowBar);
        }
        return true;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public ExoPlayerView init(int boxType) {
        this.boxType = boxType;
        _initMediaPlayer();
        return this;
    }

    public ExoPlayerView switchVideoPath(Uri uri) {
        List<MediaQualityInfo> list = this.mQualityData;
        if (list != null) {
            list.clear();
            this.mQualityData = null;
        }
        return setVideoPath(uri, 0);
    }

    private void changeQuality(String url, int startSeconds, boolean userSurfaceView) {
        if (url == null || TextUtils.isEmpty(url)) {
            return;
        }
        Log.d("播放链接", getRealUrl(url));
        ExoVideoView exoVideoView = this.mVideoView;
        if (exoVideoView != null) {
            exoVideoView.switchQuality(url, startSeconds);
        }
    }

    public ExoPlayerView setVideoPath(String url, int startSeconds) {
        if (url == null || TextUtils.isEmpty(url)) {
            return null;
        }
        String realUrl = getRealUrl(url);
        Log.d("播放链接", realUrl);
        return setVideoPath(Uri.parse(realUrl), startSeconds);
    }

    public String getRealUrl(String url) {
        if ("0".equalsIgnoreCase(PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "")) && (url.startsWith("http://") || url.startsWith("https://"))) {
            String string = PrefsUtils.getInstance().getString(Constant.SELECT_SERVER_URL, "");
            if (TextUtils.isEmpty(string)) {
                return url;
            }
            String[] strArrSplit = url.split("/");
            StringBuilder sb = new StringBuilder();
            if (strArrSplit.length > 2) {
                strArrSplit[2] = string;
                for (int i = 2; i < strArrSplit.length; i++) {
                    if (i != strArrSplit.length - 1) {
                        sb.append(strArrSplit[i]);
                        sb.append("/");
                    } else {
                        sb.append(strArrSplit[i]);
                    }
                }
                return sb.toString();
            }
        }
        Log.d("NormalIjkVideoView", "播放链接 : " + url + "groupId:" + PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, ""));
        return url;
    }

    public ExoPlayerView setVideoPath(Uri uri, int startSeconds) {
        this.mVideoView.setVideoURI(uri, startSeconds);
        return this;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public ExoPlayerView alwaysFullScreen() {
        this.mIsAlwaysFullScreen = true;
        _setFullScreen(true);
        this.mAttachActivity.setRequestedOrientation(0);
        _setUiLayoutFullscreen();
        return this;
    }

    public void resume() {
        this.mVideoView.start();
    }

    public void start() {
        this.isFrist = false;
        if (this.mIsPlayComplete) {
            this.mIsPlayComplete = false;
        }
        if (!this.mVideoView.isPlaying()) {
            this.mIvPlay.setSelected(true);
            this.mVideoView.start();
            this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        }
        this.mAttachActivity.getWindow().addFlags(128);
        ExoAudioPlayHelper exoAudioPlayHelper = this.audioPlayHelper;
        if (exoAudioPlayHelper != null) {
            exoAudioPlayHelper.start(this.mVideoView.getMediaPlayer());
        }
    }

    public void reload() {
        this.mFlReload.setVisibility(8);
        this.mLoadingView.setVisibility(0);
        if (this.mIsReady) {
            if (NetWorkUtils.isNetworkAvailable(this.mAttachActivity)) {
                this.mVideoView.reload();
                this.mVideoView.start();
                int i = this.mInterruptPosition;
                if (i > 0) {
                    seekTo(i);
                    this.mInterruptPosition = 0;
                }
            }
        } else {
            this.mVideoView.release(false);
            start();
        }
        this.mHandler.removeMessages(MSG_UPDATE_SEEK);
        this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
    }

    public boolean isPlaying() {
        return this.mVideoView.isPlaying();
    }

    public void pause() {
        this.mIvPlay.setSelected(false);
        this.isFrist = true;
        if (this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
        }
        this.mAttachActivity.getWindow().clearFlags(128);
        ExoAudioPlayHelper exoAudioPlayHelper = this.audioPlayHelper;
        if (exoAudioPlayHelper != null) {
            exoAudioPlayHelper.pause();
        }
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setVideoTitle(String title) {
        TextView textView = this.tvVideoTitle;
        if (textView != null) {
            textView.setText(title);
        }
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void seekTo(int position) {
        this.mVideoView.seekTo(position);
        ExoAudioPlayHelper exoAudioPlayHelper = this.audioPlayHelper;
        if (exoAudioPlayHelper != null) {
            exoAudioPlayHelper.seekTo(position, this.mVideoView.getMediaPlayer());
        }
    }

    public void stop() {
        pause();
        this.mVideoView.stopPlayback();
    }

    public void reset() {
        this.mIsNeverPlay = true;
        this.mCurPosition = 0;
        stop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideAllView(boolean isTouchLock) {
        startAnimation(false);
        toggleTopAnimation(false);
        if (!isTouchLock) {
            this.mIvPlayerLock.setVisibility(8);
            this.mIsShowBar = false;
        }
        if (this.mIsShowQuality || this.mIsShowCC || this.mIsShowTracker) {
            return;
        }
        RequestFlVideoBox();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _setControlBarVisible(boolean isShowBar) {
        startAnimation(isShowBar);
        toggleTopAnimation(isShowBar);
    }

    private void toggleTopAnimation(boolean show) {
        TranslateAnimation translateAnimation;
        if (this.boxType == 1) {
            return;
        }
        if (show) {
            if (this.flLastNext.getVisibility() == 0) {
                return;
            }
            this.flLastNext.setVisibility(0);
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, -1.0f, 1, 0.0f);
        } else {
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, -1.0f);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.10
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    ExoPlayerView.this.flLastNext.setVisibility(8);
                }
            });
        }
        translateAnimation.setDuration(200L);
        this.flLastNext.startAnimation(translateAnimation);
    }

    private void startAnimation(boolean isShowBar) {
        TranslateAnimation translateAnimation;
        if (isShowBar) {
            this.tvVideoTitle.setVisibility(0);
        } else {
            this.tvVideoTitle.setVisibility(8);
        }
        if (isShowBar) {
            if (this.mLlBottomBar.getVisibility() == 0) {
                return;
            }
            this.mLlBottomBar.setVisibility(0);
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        } else {
            translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 0.0f, 1, 1.0f);
            translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.11
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    ExoPlayerView.this.isAnimation = true;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    ExoPlayerView.this.isAnimation = false;
                    ExoPlayerView.this.mLlBottomBar.setVisibility(8);
                }
            });
        }
        translateAnimation.setDuration(200L);
        this.mLlBottomBar.startAnimation(translateAnimation);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void _toggleControlBar() {
        boolean z = this.mIsShowBar;
        this.mIsShowBar = !z;
        if (!z) {
            Log.d(TAG, "toggleControlBar true");
        }
        if (this.mIsShowBar) {
            this.mHandler.removeCallbacks(this.mHideBarRunnable);
            this.mHandler.postDelayed(this.mHideBarRunnable, 5000L);
            this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        }
        _setControlBarVisible(this.mIsShowBar);
    }

    public boolean isControllerShow() {
        return this.mIsShowBar;
    }

    public void _showControlBar(int timeout) {
        if (!this.mIsShowBar) {
            _setProgress();
            this.mIsShowBar = true;
            Log.d(TAG, "SetmIsShowBar true");
        }
        _setControlBarVisible(true);
        this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        this.mHandler.removeCallbacks(this.mHideBarRunnable);
        if (timeout != 0) {
            this.mHandler.postDelayed(this.mHideBarRunnable, timeout);
        }
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void _togglePlayStatus(boolean focus) {
        if (this.mIsShowBar) {
            this.mIvPlayCircle.setVisibility(8);
        } else {
            this.mIvPlayCircle.setVisibility(0);
            this.mHandler.removeCallbacks(this.hidePlayPauseRunnable);
            this.mHandler.postDelayed(this.hidePlayPauseRunnable, 1500L);
        }
        if (focus) {
            RequestmIvPlay();
        }
        if (this.mVideoView.isPlaying()) {
            pause();
            this.mIvPlayCircle.setImageResource(com.dl7.player.R.mipmap.ic_video_pause);
            this.simpleSubtitleView.pause();
        } else {
            start();
            this.mIvPlayCircle.setImageResource(com.dl7.player.R.mipmap.ic_video_play);
            this.simpleSubtitleView.resume();
        }
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setSubtitleSize(int size) {
        SimpleSubtitleView2 simpleSubtitleView2 = this.simpleSubtitleView;
        if (simpleSubtitleView2 != null) {
            simpleSubtitleView2.changeSize(size);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _refreshHideRunnable() {
        this.mHandler.removeCallbacks(this.mHideBarRunnable);
        this.mHandler.postDelayed(this.mHideBarRunnable, 5000L);
    }

    private void _togglePlayerLock() {
        boolean z = !this.mIsForbidTouch;
        this.mIsForbidTouch = z;
        this.mIvPlayerLock.setSelected(z);
        if (this.mIsForbidTouch) {
            this.mOrientationListener.disable();
            _hideAllView(true);
        } else {
            if (!this.mIsForbidOrientation) {
                this.mOrientationListener.enable();
            }
            this.mLlBottomBar.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleMediaQuality() {
        this.videoQualityDialogFragment = null;
        VideoQualityDialogFragment videoQualityDialogFragmentNewInstance = VideoQualityDialogFragment.INSTANCE.newInstance(this.mQualityData, this.lastQualityPos, this.movieDownload, this.showOrg);
        this.videoQualityDialogFragment = videoQualityDialogFragmentNewInstance;
        videoQualityDialogFragmentNewInstance.setCallback(new VideoQualityDialogFragment.SwitchDefinitionCallback() { // from class: com.movieboxpro.android.exo.ExoPlayerView.13
            @Override // com.movieboxpro.android.view.dialog.VideoQualityDialogFragment.SwitchDefinitionCallback
            public void onShowOrg() {
                ExoPlayerView.this.showOrg = true;
            }

            @Override // com.movieboxpro.android.view.dialog.VideoQualityDialogFragment.SwitchDefinitionCallback
            public void onOnlineQuality(List<MediaQualityInfo> list, List<? extends BaseMediaModel.DownloadFile> files) {
                ExoPlayerView.this.mQualityData.clear();
                ExoPlayerView.this.mQualityData.addAll(list);
                if (ExoPlayerView.this.playerViewCallback != null) {
                    ExoPlayerView.this.playerViewCallback.onOnlineQuality(list, files);
                }
            }

            @Override // com.movieboxpro.android.view.dialog.VideoQualityDialogFragment.SwitchDefinitionCallback
            public void onSwitchDefinition(MediaQualityInfo qualityInfo, int position) {
                ExoPlayerView.this.lastQualityPos = position;
                ExoPlayerView.this.mediaQualityInfo = qualityInfo;
                if (qualityInfo != null) {
                    ExoPlayerView.this.calBitStream(qualityInfo.getBitstream());
                    if (App.getUserData().getIsvip() == 1) {
                        if (ExoPlayerView.this.mCurSelectQuality != position) {
                            if (qualityInfo.getPath() != null) {
                                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_SELECTED_ORG, false) || qualityInfo.getOriginal() != 1) {
                                    PrefsUtils.getInstance().putString(Constant.Prefs.LAST_SELECT_QUA, qualityInfo.getReal_quality());
                                    PrefsUtils.getInstance().putInt(Constant.Prefs.IS_CHOOSE_ORIGIN, qualityInfo.getOriginal());
                                }
                                ExoPlayerView.this.changeMediaQuality(position);
                            } else {
                                ToastUtils.showShort("Not Available");
                            }
                        }
                    } else if (IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE.equals(qualityInfo.getIsVip())) {
                        if (ExoPlayerView.this.QualityListener != null) {
                            ExoPlayerView.this.QualityListener.onClick();
                        }
                    } else if (ExoPlayerView.this.mCurSelectQuality != position) {
                        if (qualityInfo.getPath() != null) {
                            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_SELECTED_ORG, false) || qualityInfo.getOriginal() != 1) {
                                PrefsUtils.getInstance().putString(Constant.Prefs.LAST_SELECT_QUA, qualityInfo.getReal_quality());
                                PrefsUtils.getInstance().putInt(Constant.Prefs.IS_CHOOSE_ORIGIN, qualityInfo.getOriginal());
                            }
                            ExoPlayerView.this.changeMediaQuality(position);
                        } else {
                            ToastUtils.showShort("Not Available");
                        }
                    }
                }
                if (ExoPlayerView.this.videoQualityDialogFragment != null) {
                    ExoPlayerView.this.videoQualityDialogFragment.dismissAllowingStateLoss();
                }
            }
        });
        this.videoQualityDialogFragment.show(this.fragmentManager, "VideoQualityDialogFragment");
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public ImageView getImageThumb() {
        return this.mPlayerThumb;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleTrackSelect() {
        this.audioTracksDialogFragment = null;
        if (this.movieDownload == null || this.mVideoView == null) {
            return;
        }
        ExoAudioPlayHelper exoAudioPlayHelper = this.audioPlayHelper;
        if (exoAudioPlayHelper != null) {
            exoAudioPlayHelper.getCustomDelay();
        }
        ExoAudioTracksDialogFragment exoAudioTracksDialogFragmentNewInstance = ExoAudioTracksDialogFragment.INSTANCE.newInstance(this.audioOssFid, this.mediaQualityInfo, new ArrayList<>(this.mVideoView.getTrackInfo()), this.hlsAudioTacks, this.mVideoView.getCurrentAudioTrack(), this.mVideoView.getTrackIndices(), this.movieDownload.id, this.movieDownload.box_type, this.movieDownload.season, this.movieDownload.episode, true);
        this.audioTracksDialogFragment = exoAudioTracksDialogFragmentNewInstance;
        exoAudioTracksDialogFragmentNewInstance.setListener(new ExoAudioTracksDialogFragment.OnAudioTrackListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.14
            @Override // com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment.OnAudioTrackListener
            public void onAdjustAudioDelay(String ossId) {
            }

            @Override // com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment.OnAudioTrackListener
            public void subDelay() {
                if (ExoPlayerView.this.audioPlayHelper != null) {
                    ExoPlayerView.this.audioPlayHelper.setAudioDelay(100, ExoPlayerView.this.mVideoView.getMediaPlayer());
                    ExoPlayerView.this.audioTracksDialogFragment.setDelayText(String.format("%.1f", Float.valueOf((-ExoPlayerView.this.audioPlayHelper.getCustomDelay()) / 1000.0f)) + CmcdData.STREAMING_FORMAT_SS);
                }
            }

            @Override // com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment.OnAudioTrackListener
            public void addDelay() {
                if (ExoPlayerView.this.audioPlayHelper != null) {
                    ExoPlayerView.this.audioPlayHelper.setAudioDelay(-100, ExoPlayerView.this.mVideoView.getMediaPlayer());
                    ExoPlayerView.this.audioTracksDialogFragment.setDelayText(String.format("%.1f", Float.valueOf((-ExoPlayerView.this.audioPlayHelper.getCustomDelay()) / 1000.0f)) + CmcdData.STREAMING_FORMAT_SS);
                }
            }

            @Override // com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment.OnAudioTrackListener
            public void onOnlineAudios(ArrayList<AudioTrackModel> audioTracks) {
                ExoPlayerView.this.audioTrackModels = audioTracks;
            }

            @Override // com.movieboxpro.android.view.dialog.ExoAudioTracksDialogFragment.OnAudioTrackListener
            public void onAudioClick(AudioTrackModel item) {
                if (TextUtils.isEmpty(item.getDownload_url())) {
                    if (ExoPlayerView.this.mVideoView != null) {
                        ExoPlayerView.this.audioOssFid = "";
                        Tracks.Group group = null;
                        if (ExoPlayerView.this.audioPlayHelper != null) {
                            ExoPlayerView.this.audioPlayHelper.release();
                            if (!ExoPlayerView.this.mVideoView.isPlaying()) {
                                ExoPlayerView.this.mVideoView.start();
                            }
                            ExoPlayerView.this.audioPlayHelper = null;
                        }
                        ExoPlayerView.this.tvChangeVolume.setVisibility(8);
                        CustomExoPlayer mediaPlayer = ExoPlayerView.this.mVideoView.getMediaPlayer();
                        String str = Tracks.Group.fromBundle(item.getExoAudioTrackInfo()).getMediaTrackGroup().id;
                        for (Tracks.Group group2 : ExoPlayerView.this.mVideoView.getTrackInfo()) {
                            if (group2.getMediaTrackGroup().id.equals(str)) {
                                group = group2;
                                break;
                            }
                        }
                        if (group != null) {
                            if (item.getTracks() == null || item.getTracks().isEmpty()) {
                                mediaPlayer.setAudioTrack(new TrackSelectionOverride(group.getMediaTrackGroup(), item.getIndex().intValue()));
                            } else {
                                ArrayList arrayList = new ArrayList();
                                for (int i = 0; i < group.length; i++) {
                                    arrayList.add(Integer.valueOf(i));
                                }
                                mediaPlayer.setAudioTrack(new TrackSelectionOverride(group.getMediaTrackGroup(), arrayList));
                            }
                        }
                    }
                } else {
                    ExoPlayerView.this.audioOssFid = item.getOss_fid();
                    if (ExoPlayerView.this.mVideoView != null) {
                        ExoPlayerView.this.mVideoView.getMediaPlayer();
                        if (ExoPlayerView.this.audioPlayHelper == null) {
                            ExoPlayerView.this.audioPlayHelper = new ExoAudioPlayHelper();
                            ExoPlayerView.this.audioPlayHelper.play(item.getDownload_url(), ExoPlayerView.this.mVideoView.getMediaPlayer());
                        } else {
                            ExoPlayerView.this.audioPlayHelper.changeUrl(item.getDownload_url(), ExoPlayerView.this.mVideoView.getMediaPlayer());
                        }
                    }
                }
                ExoPlayerView.this.audioTracksDialogFragment.dismissAllowingStateLoss();
            }
        });
        this.audioTracksDialogFragment.show(this.fragmentManager, "AudioTracksDialogFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleSrtSelect() {
        Log.d("_toggleSrtSelect", "_toggleSrtSelect" + this.mIsShowLrc);
    }

    public long getTcpSpeed() {
        ExoVideoView exoVideoView = this.mVideoView;
        if (exoVideoView != null) {
            return exoVideoView.getTcpSpeed();
        }
        return 0L;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        _refreshHideRunnable();
        int id = v.getId();
        if (id == R.id.ic_iv_play || id == R.id.iv_play_circle) {
            _togglePlayStatus(true);
            return;
        }
        if (id == R.id.iv_player_lock) {
            _togglePlayerLock();
            return;
        }
        if (id == R.id.iv_media_quality) {
            _toggleMediaQuality();
            return;
        }
        if (id == com.dl7.player.R.id.iv_cancel_skip) {
            this.mHandler.removeCallbacks(this.mHideSkipTipRunnable);
            _hideSkipTip();
            return;
        }
        if (id == com.dl7.player.R.id.tv_do_skip) {
            this.mLoadingView.setVisibility(0);
            seekTo(this.mSkipPosition);
            this.mHandler.removeCallbacks(this.mHideSkipTipRunnable);
            _hideSkipTip();
            _setProgress();
            return;
        }
        if (id == com.dl7.player.R.id.iv_cancel_send) {
            recoverFromEditVideo();
            return;
        }
        if (id == com.dl7.player.R.id.iv_do_send) {
            recoverFromEditVideo();
            return;
        }
        if (id == com.dl7.player.R.id.input_options_more || id == com.dl7.player.R.id.iv_screenshot) {
            return;
        }
        if (id == R.id.tv_recover_screen) {
            this.mVideoView.resetVideoView(true);
            this.mIsNeedRecoverScreen = false;
            return;
        }
        if (id == com.dl7.player.R.id.tv_settings) {
            return;
        }
        if (id == R.id.tv_reload) {
            reload();
            return;
        }
        if (id == R.id.tv_srtcelsect) {
            if (this.stringListHashMap.isEmpty()) {
                ToastUtils.showShort("No subtitles");
                PlayerViewCallback playerViewCallback = this.playerViewCallback;
                if (playerViewCallback != null) {
                    playerViewCallback.onOpenSubtitleClicked();
                    return;
                }
                return;
            }
            this.subtitleDialogFragment = null;
            SubtitleDialogFragment subtitleDialogFragment = new SubtitleDialogFragment();
            this.subtitleDialogFragment = subtitleDialogFragment;
            subtitleDialogFragment.setSubtitles(this.stringListHashMap, this.lastSelectedSubtitle, this.mediaQualityInfo.getFilename());
            this.subtitleDialogFragment.setListener(new SubtitleDialogFragment.OnSubtitleSelectListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.15
                @Override // com.movieboxpro.android.view.dialog.SubtitleDialogFragment.OnSubtitleSelectListener
                public void onSubtitleSelect(SRTModel.subTitles subtitle) {
                    if (ExoPlayerView.this.currSid != null && ExoPlayerView.this.currSid.equals(subtitle.sid)) {
                        ExoPlayerView exoPlayerView = ExoPlayerView.this;
                        exoPlayerView.removeSRTlist(exoPlayerView.mSrtSlelct);
                        ExoPlayerView.this.simpleSubtitleView.stop();
                        ExoPlayerView.this.mSrtController.setVisibility(8);
                        ExoPlayerView.this._setControlBarVisible(false);
                        ExoPlayerView.this.RequestFlVideoBox();
                        ExoPlayerView.this.mIsShowBar = false;
                        ExoPlayerView.this.lastSelectedSubtitle = null;
                        ExoPlayerView.this.currSid = "";
                        if (ExoPlayerView.this.playerViewCallback != null) {
                            ExoPlayerView.this.playerViewCallback.disSelectSubtitle(subtitle.sid);
                            return;
                        }
                        return;
                    }
                    ExoPlayerView.this.currSid = subtitle.sid;
                    if (subtitle.getOrg_subtitle() != null) {
                        ExoPlayerView.this.currOriginalSid = subtitle.getOrg_subtitle().getSid();
                    }
                    ExoPlayerView.this.listener.onClick(subtitle.file_path, subtitle.getLang(), ExoPlayerView.this.currSid);
                    ExoPlayerView.this.ll_srt_size.setVisibility(8);
                    ExoPlayerView.this.ll_srt_speed.setVisibility(8);
                    ExoPlayerView.this.mSrtController.setVisibility(0);
                    ExoPlayerView.this.mBottomTranslate.setImageResource(R.mipmap.ic_translate_normal);
                    ExoPlayerView.this.currSelectServerSubtitle = subtitle;
                    ExoPlayerView.this.lastSelectedSubtitle = subtitle;
                    ExoPlayerView.this.srtspeed = (int) (subtitle.delay * 1000.0f);
                    ExoPlayerView.this.simpleSubtitleView.setDelay(ExoPlayerView.this.srtspeed);
                    TextView textView = ExoPlayerView.this.ll_srt_average;
                    StringBuilder sb = new StringBuilder("Most users choose ");
                    ExoPlayerView exoPlayerView2 = ExoPlayerView.this;
                    sb.append(exoPlayerView2.formatDelaySecond(exoPlayerView2.srtspeed));
                    sb.append(CmcdData.STREAMING_FORMAT_SS);
                    textView.setText(sb.toString());
                    TextView textView2 = ExoPlayerView.this.srttext;
                    ExoPlayerView exoPlayerView3 = ExoPlayerView.this;
                    textView2.setText(exoPlayerView3.formatDelaySecond(exoPlayerView3.srtspeed));
                }

                @Override // com.movieboxpro.android.view.dialog.SubtitleDialogFragment.OnSubtitleSelectListener
                public void onOpenSubtitle() {
                    if (ExoPlayerView.this.playerViewCallback != null) {
                        ExoPlayerView.this.playerViewCallback.onOpenSubtitleClicked();
                    }
                }
            });
            this.subtitleDialogFragment.show(this.fragmentManager, "SubtitleDialogFragment");
            return;
        }
        if (id == R.id.tv_meidaTrack) {
            if (this.mIsShowTracker) {
                return;
            }
            _toggleTrackSelect();
            return;
        }
        if (id == R.id.tv_screen_size) {
            switchScreenSize();
            return;
        }
        if (id == R.id.tvChangeVolume) {
            ChangeVolumeDialog changeVolumeDialogNewInstance = ChangeVolumeDialog.INSTANCE.newInstance(this.currVolume);
            changeVolumeDialogNewInstance.setListener(new ChangeVolumeDialog.OnVolumeChangeListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.16
                @Override // com.movieboxpro.android.view.dialog.ChangeVolumeDialog.OnVolumeChangeListener
                public void onAddVolume() {
                    ExoPlayerView.this.currVolume += 10;
                    if (ExoPlayerView.this.audioPlayHelper != null) {
                        ExoPlayerView.this.audioPlayHelper.setVolume(ExoPlayerView.this.currVolume);
                    }
                }

                @Override // com.movieboxpro.android.view.dialog.ChangeVolumeDialog.OnVolumeChangeListener
                public void onSubVolume() {
                    ExoPlayerView.this.currVolume -= 10;
                    if (ExoPlayerView.this.audioPlayHelper != null) {
                        ExoPlayerView.this.audioPlayHelper.setVolume(ExoPlayerView.this.currVolume);
                    }
                }
            });
            changeVolumeDialogNewInstance.show(this.fragmentManager, "ChangeVolumeDialog");
            return;
        }
        if (id == R.id.tv_pic_mode) {
            EventBus.getDefault().post(new EnterPicModeEvent());
            return;
        }
        if (id == R.id.ivHwDecoder) {
            boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.HW_DECODER, true);
            PrefsUtils.getInstance().putBoolean(Constant.Prefs.HW_DECODER, !z);
            if (!z) {
                this.ivHwDecoder.setImageResource(R.mipmap.ic_hw_decoder_enable);
            } else {
                this.ivHwDecoder.setImageResource(R.mipmap.ic_hw_decoder_disable);
            }
            changeMediaQuality(this.mCurSelectQuality);
            return;
        }
        if (id == R.id.llLastEpisode) {
            PlayerViewCallback playerViewCallback2 = this.playerViewCallback;
            if (playerViewCallback2 != null) {
                playerViewCallback2.lastVideo();
            }
            this.audioTrackModels = null;
            return;
        }
        if (id == R.id.llNextEpisode) {
            PlayerViewCallback playerViewCallback3 = this.playerViewCallback;
            if (playerViewCallback3 != null) {
                playerViewCallback3.nextVideo();
            }
            this.audioTrackModels = null;
            return;
        }
        if (id == R.id.llEpisodeList) {
            PlayerViewCallback playerViewCallback4 = this.playerViewCallback;
            if (playerViewCallback4 != null) {
                playerViewCallback4.showEpisodeList();
                return;
            }
            return;
        }
        if (id == R.id.tvSpeed) {
            ChoosePlaySpeedDialog choosePlaySpeedDialog = new ChoosePlaySpeedDialog();
            choosePlaySpeedDialog.setListener(new ChoosePlaySpeedDialog.ChoosePlaySpeedListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.17
                @Override // com.movieboxpro.android.view.dialog.ChoosePlaySpeedDialog.ChoosePlaySpeedListener
                public void onChooseSpeed(float speed) {
                    ExoPlayerView.this.mVideoView.setSpeed(speed);
                    ExoPlayerView.this.tvSpeed.setText(speed + "x");
                    if (ExoPlayerView.this.audioPlayHelper != null) {
                        ExoPlayerView.this.audioPlayHelper.setPlaySpeed(speed, ExoPlayerView.this.mVideoView.getMediaPlayer());
                    }
                }
            });
            choosePlaySpeedDialog.show(this.fragmentManager, "ChoosePlaySpeedDialog");
        }
    }

    private void switchScreenSize() {
        int i = this.defaultScreenSize;
        int[] iArr = screenScales;
        if (i == iArr.length - 1) {
            this.defaultScreenSize = 0;
        } else {
            this.defaultScreenSize = i + 1;
        }
        int i2 = this.defaultScreenSize;
        if (i2 >= 0 && i2 < iArr.length) {
            this.ivScreenSize.setImageResource(this.scaleImgRes[i2]);
            this.mVideoView.setAspectRatio(screenScales[this.defaultScreenSize]);
        } else {
            this.defaultScreenSize = 0;
            this.ivScreenSize.setImageResource(this.scaleImgRes[0]);
            this.mVideoView.setAspectRatio(screenScales[this.defaultScreenSize]);
        }
        PrefsUtils.getInstance().putInt(Constant.Prefs.SCREEN_SCALE, this.defaultScreenSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String formatDelaySecond(int delay) {
        return String.format(Locale.ENGLISH, "%.1f", Float.valueOf(delay / 1000.0f));
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public ExoPlayerView enableOrientation() {
        this.mIsForbidOrientation = false;
        this.mOrientationListener.enable();
        return this;
    }

    private void _toggleFullScreen() {
        if (WindowUtils.getScreenOrientation(this.mAttachActivity) == 0) {
            this.mAttachActivity.setRequestedOrientation(1);
        } else {
            this.mAttachActivity.setRequestedOrientation(0);
        }
    }

    private void _setFullScreen(boolean isFullscreen) {
        this.mIsFullscreen = isFullscreen;
        _handleActionBar(isFullscreen);
        this.mHandler.post(this.mHideBarRunnable);
        this.mIvMediaQuality.setVisibility(isFullscreen ? 0 : 8);
        if (this.mIsShowQuality && !isFullscreen) {
            _toggleMediaQuality();
        }
        if (this.mIsShowCC && !isFullscreen) {
            _toggleCCSelect();
        }
        if (this.mIsShowTracker && !isFullscreen) {
            _toggleTrackSelect();
        }
        if (!this.mIsShowLrc || isFullscreen) {
            return;
        }
        _toggleSrtSelect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _handleOrientation(int orientation) {
        if (this.mIsNeverPlay) {
            return;
        }
        if (this.mIsFullscreen && !this.mIsAlwaysFullScreen) {
            if ((orientation < 0 || orientation > 30) && orientation < 330) {
                return;
            }
            this.mAttachActivity.setRequestedOrientation(1);
            return;
        }
        if (orientation >= 60 && orientation <= 120) {
            this.mAttachActivity.setRequestedOrientation(8);
        } else {
            if (orientation < 240 || orientation > 300) {
                return;
            }
            this.mAttachActivity.setRequestedOrientation(0);
        }
    }

    private void _refreshOrientationEnable() {
        if (this.mIsForbidOrientation) {
            return;
        }
        this.mOrientationListener.disable();
        this.mHandler.removeMessages(MSG_ENABLE_ORIENTATION);
        this.mHandler.sendEmptyMessageDelayed(MSG_ENABLE_ORIENTATION, C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
    }

    private void _handleActionBar(boolean isFullscreen) {
        ActionBar supportActionBar = this.mAttachActivity.getSupportActionBar();
        if (supportActionBar != null) {
            if (isFullscreen) {
                supportActionBar.hide();
            } else {
                supportActionBar.show();
            }
        }
    }

    private void _changeHeight(boolean isFullscreen) {
        if (this.mIsAlwaysFullScreen) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (isFullscreen) {
            layoutParams.height = this.mWidthPixels;
        } else {
            layoutParams.height = this.mInitHeight;
        }
        setLayoutParams(layoutParams);
    }

    private void _setUiLayoutFullscreen() {
        this.mAttachActivity.getWindow().getDecorView().setSystemUiVisibility(5894);
        this.mAttachActivity.getWindow().addFlags(1024);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void configurationChanged(Configuration newConfig) {
        _refreshOrientationEnable();
        if (newConfig.orientation == 2) {
            View decorView = this.mAttachActivity.getWindow().getDecorView();
            this.mScreenUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(5894);
            _setFullScreen(true);
            this.mAttachActivity.getWindow().addFlags(1024);
            return;
        }
        if (newConfig.orientation == 1) {
            this.mAttachActivity.getWindow().getDecorView().setSystemUiVisibility(5894);
            this.mAttachActivity.getWindow().addFlags(1024);
            _setFullScreen(true);
        }
    }

    private void _exit() {
        this.mAttachActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int _setProgress() {
        ExoVideoView exoVideoView = this.mVideoView;
        if (exoVideoView == null || this.mIsSeeking) {
            return 0;
        }
        int iMax = Math.max(exoVideoView.getCurrentPosition(), this.mInterruptPosition);
        int duration = this.mVideoView.getDuration();
        if (duration > 0) {
            this.mPlayerSeek.setProgress(this.mVideoView.getCurrentPosition());
        }
        this.mPlayerSeek.setSecondaryProgress(this.mVideoView.getBufferPercentage() * 10);
        this.mTvCurTime.setText(StringUtils.generateTime(iMax));
        this.mTvEndTime.setText(StringUtils.generateTime(duration - iMax));
        return iMax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onProgressSlide(float percent) {
        String str;
        int currentPosition = this.mVideoView.getCurrentPosition();
        long duration = this.mVideoView.getDuration();
        long j = currentPosition;
        long jMin = ((long) (Math.min(SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US, duration / 2) * percent)) + j;
        this.mTargetPosition = jMin;
        if (jMin > duration) {
            this.mTargetPosition = duration;
        } else if (jMin <= 0) {
            this.mTargetPosition = 0L;
        }
        long j2 = this.mTargetPosition;
        int i = (int) ((j2 - j) / 1000);
        if (j2 > j) {
            str = StringUtils.generateTime(this.mTargetPosition) + "/" + StringUtils.generateTime(duration) + "\n+" + i + "秒";
        } else {
            str = StringUtils.generateTime(this.mTargetPosition) + "/" + StringUtils.generateTime(duration) + ShellUtil.COMMAND_LINE_END + i + "秒";
        }
        _setFastForward(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onVolumeSlide(float percent) {
        if (this.mCurVolume == -1) {
            int streamVolume = this.mAudioManager.getStreamVolume(3);
            this.mCurVolume = streamVolume;
            if (streamVolume < 0) {
                this.mCurVolume = 0;
            }
        }
        int i = this.mMaxVolume;
        int i2 = ((int) (percent * i)) + this.mCurVolume;
        if (i2 <= i) {
            i = i2 < 0 ? 0 : i2;
        }
        this.mAudioManager.setStreamVolume(3, i, 0);
        _setVolumeInfo(i);
    }

    private void _setVolume(boolean isIncrease) {
        int i;
        int streamVolume = this.mAudioManager.getStreamVolume(3);
        if (isIncrease) {
            i = streamVolume + (this.mMaxVolume / 15);
        } else {
            i = streamVolume - (this.mMaxVolume / 15);
        }
        int i2 = this.mMaxVolume;
        if (i > i2) {
            i = i2;
        } else if (i < 0) {
            i = 0;
        }
        this.mAudioManager.setStreamVolume(3, i, 0);
        _setVolumeInfo(i);
        this.mHandler.removeCallbacks(this.mHideTouchViewRunnable);
        this.mHandler.postDelayed(this.mHideTouchViewRunnable, 1000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onBrightnessSlide(float percent) {
        if (this.mCurBrightness < 0.0f) {
            float f = this.mAttachActivity.getWindow().getAttributes().screenBrightness;
            this.mCurBrightness = f;
            if (f < 0.0f) {
                this.mCurBrightness = 0.5f;
            } else if (f < 0.01f) {
                this.mCurBrightness = 0.01f;
            }
        }
        WindowManager.LayoutParams attributes = this.mAttachActivity.getWindow().getAttributes();
        attributes.screenBrightness = this.mCurBrightness + percent;
        if (attributes.screenBrightness > 1.0f) {
            attributes.screenBrightness = 1.0f;
        } else if (attributes.screenBrightness < 0.01f) {
            attributes.screenBrightness = 0.01f;
        }
        _setBrightnessInfo(attributes.screenBrightness);
        this.mAttachActivity.getWindow().setAttributes(attributes);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _endGesture() {
        long j = this.mTargetPosition;
        if (j >= 0 && j != this.mVideoView.getCurrentPosition()) {
            seekTo((int) this.mTargetPosition);
            this.mPlayerSeek.setProgress((int) ((this.mTargetPosition * 1000) / ((long) this.mVideoView.getDuration())));
            this.mTargetPosition = -1L;
        }
        _hideTouchView();
        _refreshHideRunnable();
        this.mCurVolume = -1;
        this.mCurBrightness = -1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _switchStatus(int status, int extra) {
        Log.i(TAG, "status " + status);
        if (status != 3) {
            if (status != 331) {
                if (status != 332) {
                    if (status != 701) {
                        if (status != 702) {
                            switch (status) {
                                case MediaPlayerParams.STATE_PAUSED /* 335 */:
                                    EventBus.getDefault().post(new OnPlayStatusEvent(MediaPlayerParams.STATE_PAUSED));
                                    break;
                                case MediaPlayerParams.STATE_COMPLETED /* 336 */:
                                    pause();
                                    if (this.mVideoView.getDuration() == -1 || this.mVideoView.getInterruptPosition() + 1000 < this.mVideoView.getDuration()) {
                                        this.mInterruptPosition = Math.max(this.mVideoView.getInterruptPosition(), this.mInterruptPosition);
                                        ToastUtils.showShort("Playback failed");
                                    } else {
                                        this.mIsPlayComplete = true;
                                        IMediaPlayer.OnCompletionListener onCompletionListener = this.mCompletionListener;
                                        if (onCompletionListener != null) {
                                            onCompletionListener.onCompletion(null);
                                        }
                                    }
                                    break;
                                case MediaPlayerParams.STATE_BUFFER /* 337 */:
                                    if (extra > 0) {
                                        this.mLoadingView.setVisibility(0);
                                    }
                                    this.mHandler.postDelayed(this.testSpeedRunnable, 10000L);
                                    break;
                            }
                        }
                    } else {
                        this.mIsBufferingStart = true;
                        this.mFlReload.setVisibility(8);
                        this.mHandler.removeMessages(MSG_TRY_RELOAD);
                    }
                }
                EventBus.getDefault().post(new OnPlayStatusEvent(MediaPlayerParams.STATE_PREPARING));
                return;
            }
            this.mInterruptPosition = Math.max(this.mVideoView.getInterruptPosition(), this.mInterruptPosition);
            pause();
            if (this.mVideoView.getDuration() == -1 && !this.mIsReady) {
                this.mLoadingView.setVisibility(8);
                this.tvTcpSpeed.setVisibility(8);
                this.mPlayerThumb.setVisibility(8);
                this.mIvPlayCircle.setVisibility(8);
                this.mFlReload.setVisibility(0);
                return;
            }
            this.mLoadingView.setVisibility(0);
            this.mHandler.sendEmptyMessage(MSG_TRY_RELOAD);
            return;
        }
        this.mIsRenderingStart = true;
        EventBus.getDefault().post(new OnPlayStatusEvent(MediaPlayerParams.STATE_PREPARED));
        EventBus.getDefault().post(new OnPlayStatusEvent(MediaPlayerParams.STATE_PLAYING_START));
        this.simpleSubtitleView.start();
        this.mIsReady = true;
        this.mIsBufferingStart = false;
        this.tvTcpSpeed.setVisibility(8);
        this.mLoadingView.setVisibility(8);
        this.mPlayerThumb.setVisibility(8);
        this.mHandler.removeMessages(MSG_UPDATE_SEEK);
        this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        if (this.mSkipPosition != -1) {
            _showSkipTip();
        }
        this.mIvPlay.setSelected(true);
        if (this.mVideoView.isPlaying() && this.mIsNetConnected) {
            this.mInterruptPosition = 0;
            if (!this.mIvPlay.isSelected()) {
                this.mVideoView.start();
                this.mIvPlay.setSelected(true);
            }
        }
        EventBus.getDefault().post(new OnPlayStatusEvent(MediaPlayerParams.STATE_PLAYING));
        if (this.mVideoView != null && this.mediaAudioTracks.size() == 0) {
            this.mediaAudioTracks = this.mVideoView.getTrackInfo();
        }
        this.mHandler.removeMessages(MSG_TRY_RELOAD);
        this.mHandler.removeCallbacks(this.testSpeedRunnable);
        hideTestSpeedHint();
        this.retryCount = 0;
        this.tvTcpSpeed.setVisibility(8);
        initSeekBar();
        initSeekBar2();
    }

    private void startShowTcpSpeed() {
        if (this.tcpSpeedDisposable != null) {
            this.speeds.clear();
            this.tcpSpeedDisposable.dispose();
        }
        if (this.mLoadingView.getVisibility() == 8) {
            this.tvTcpSpeed.setVisibility(8);
            return;
        }
        TextView textView = this.tvTcpSpeed;
        if (textView != null) {
            textView.setText("");
        }
        Observable.interval(1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.exo.ExoPlayerView.24
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                ExoPlayerView.this.tcpSpeedDisposable = d;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long aLong) {
                if (ExoPlayerView.this.mLoadingView.getVisibility() == 8) {
                    ExoPlayerView.this.tvTcpSpeed.setVisibility(8);
                    if (ExoPlayerView.this.tcpSpeedDisposable != null) {
                        ExoPlayerView.this.speeds.clear();
                        ExoPlayerView.this.tcpSpeedDisposable.dispose();
                        return;
                    }
                    return;
                }
                if (ExoPlayerView.this.tvTcpSpeed == null || ExoPlayerView.this.mVideoView == null) {
                    return;
                }
                if (ExoPlayerView.this.tvTcpSpeed.getVisibility() != 0) {
                    ExoPlayerView.this.tvTcpSpeed.setVisibility(0);
                }
                long tcpSpeed = ExoPlayerView.this.mVideoView.getTcpSpeed();
                if (tcpSpeed > 0) {
                    ExoPlayerView.this.tvTcpSpeed.setText(String.format("%sKB/S", Long.valueOf(tcpSpeed / 1024)));
                    if (aLong.longValue() <= 6) {
                        ExoPlayerView.this.speeds.add(Long.valueOf(tcpSpeed));
                    }
                    if (aLong.longValue() != 7) {
                        if (aLong.longValue() == 25) {
                            EventBus.getDefault().post(new SwitchTestSpeedHintEvent(true));
                            return;
                        }
                        return;
                    }
                    Iterator it = ExoPlayerView.this.speeds.iterator();
                    long jLongValue = 0;
                    while (it.hasNext()) {
                        jLongValue += ((Long) it.next()).longValue();
                    }
                    int size = ExoPlayerView.this.speeds.size();
                    if (size == 0) {
                        size = 1;
                    }
                    if ((jLongValue / ((long) size)) / 1024 < (ExoPlayerView.this.bitStream > 0 ? ExoPlayerView.this.bitStream : 128L)) {
                        EventBus.getDefault().post(new SwitchTestSpeedHintEvent(true));
                    }
                }
            }
        });
    }

    private void stopShowTcpSpeed() {
        Disposable disposable = this.tcpSpeedDisposable;
        if (disposable != null) {
            disposable.dispose();
            this.speeds.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showTestSpeedHint() {
        EventBus.getDefault().post(new SwitchTestSpeedHintEvent(true));
    }

    private void hideTestSpeedHint() {
        EventBus.getDefault().post(new SwitchTestSpeedHintEvent(false));
    }

    private void initSeekBar() {
        this.mPlayerSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.26
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b) {
                    ExoPlayerView exoPlayerView = ExoPlayerView.this;
                    exoPlayerView.seekTo(exoPlayerView.mPlayerSeek.getProgress());
                }
            }
        });
        this.mPlayerSeek.setMax(this.mVideoView.getDuration());
        this.mPlayerSeek.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.27
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode != 21) {
                    if (keyCode != 22) {
                        return false;
                    }
                } else {
                    if (event.getAction() == 0) {
                        ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable);
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                        int repeatCount = event.getRepeatCount();
                        if (repeatCount == 0) {
                            ExoPlayerView.this.mPlayerSeek.setProgress(ExoPlayerView.this.mPlayerSeek.getProgress() - 10000);
                            if (ExoPlayerView.this.videoPreviewListener != null) {
                                ExoPlayerView.this.videoPreviewListener.startDrag();
                            }
                        } else {
                            ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable);
                            int progress = ExoPlayerView.this.mPlayerSeek.getProgress();
                            if (repeatCount < 40) {
                                ExoPlayerView.this.mPlayerSeek.setProgress(progress - 10000);
                            } else if (repeatCount < 70) {
                                ExoPlayerView.this.mPlayerSeek.setProgress(progress - 20000);
                            } else {
                                ExoPlayerView.this.mPlayerSeek.setProgress(progress - MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND);
                            }
                            if (ExoPlayerView.this.videoPreviewListener != null) {
                                ExoPlayerView.this.videoPreviewListener.onProgressChanged(ExoPlayerView.this.mPlayerSeek, ExoPlayerView.this.mPlayerSeek.getProgress(), ExoPlayerView.this.mPlayerSeek.getProgress());
                            }
                        }
                        ExoPlayerView.this.mTvCurTime.setText(StringUtils.generateTime(ExoPlayerView.this.mPlayerSeek.getProgress()));
                        ExoPlayerView.this.mTvEndTime.setText(StringUtils.generateTime(ExoPlayerView.this.mVideoView.getDuration() - ExoPlayerView.this.mPlayerSeek.getProgress()));
                        ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable);
                        ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.seekToPositionRunnable, 1000L);
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                        ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.mHideBarRunnable, 5000L);
                        return true;
                    }
                    if (event.getAction() == 1) {
                        if (ExoPlayerView.this.videoPreviewListener != null) {
                            ExoPlayerView.this.videoPreviewListener.stopDrag();
                        }
                        return true;
                    }
                }
                if (event.getAction() == 0) {
                    ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable);
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                    int repeatCount2 = event.getRepeatCount();
                    if (repeatCount2 == 0) {
                        ExoPlayerView.this.mPlayerSeek.setProgress(ExoPlayerView.this.mPlayerSeek.getProgress() + 10000);
                        if (ExoPlayerView.this.videoPreviewListener != null) {
                            ExoPlayerView.this.videoPreviewListener.startDrag();
                        }
                    } else {
                        ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable);
                        int progress2 = ExoPlayerView.this.mPlayerSeek.getProgress();
                        if (repeatCount2 < 40) {
                            ExoPlayerView.this.mPlayerSeek.setProgress(progress2 + 10000);
                        } else if (repeatCount2 < 70) {
                            ExoPlayerView.this.mPlayerSeek.setProgress(progress2 + AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
                        } else {
                            ExoPlayerView.this.mPlayerSeek.setProgress(progress2 + MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND);
                        }
                        if (ExoPlayerView.this.videoPreviewListener != null) {
                            ExoPlayerView.this.videoPreviewListener.onProgressChanged(ExoPlayerView.this.mPlayerSeek, ExoPlayerView.this.mPlayerSeek.getProgress(), ExoPlayerView.this.mPlayerSeek.getProgress());
                        }
                    }
                    ExoPlayerView.this.mTvCurTime.setText(StringUtils.generateTime(ExoPlayerView.this.mPlayerSeek.getProgress()));
                    ExoPlayerView.this.mTvEndTime.setText(StringUtils.generateTime(ExoPlayerView.this.mVideoView.getDuration() - ExoPlayerView.this.mPlayerSeek.getProgress()));
                    ExoPlayerView.this.mHandler.removeMessages(ExoPlayerView.MSG_UPDATE_SEEK);
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.seekToPositionRunnable);
                    ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.seekToPositionRunnable, 1000L);
                    ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                    ExoPlayerView.this.mHandler.postDelayed(ExoPlayerView.this.mHideBarRunnable, 5000L);
                    return true;
                }
                if (event.getAction() != 1) {
                    return false;
                }
                if (ExoPlayerView.this.videoPreviewListener != null) {
                    ExoPlayerView.this.videoPreviewListener.stopDrag();
                }
                return true;
            }
        });
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener l) {
        this.mVideoView.setOnPreparedListener(l);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener l) {
        this.mCompletionListener = l;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener l) {
        this.mVideoView.setOnErrorListener(l);
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener l) {
        this.mOutsideInfoListener = l;
    }

    private void _initCCSlect() {
        _init_cc_controller();
        _init_cc_lrclist();
        _init_cc_speed();
        _init_cc_size();
        _init_cc_list();
        initSubtitlePos();
    }

    private void _init_cc_controller() {
        this.mSrtController = (LinearLayout) findViewById(R.id.ll_bottom_srt_controller);
        this.mBottomCClast = (ImageView) findViewById(R.id.ll_bottom_controller_lastside);
        this.mBottomCCSpeed = (ImageView) findViewById(R.id.ll_bottom_controller_size);
        this.mBottomCCSize = (ImageView) findViewById(R.id.ll_bottom_controller_more);
        this.mBottomCCclose = (ImageView) findViewById(R.id.ll_bottom_controller_close);
        this.mBottomTranslate = (ImageView) findViewById(R.id.ll_bottom_controller_translate);
        this.mBottomSubtitleColor = (ImageView) findViewById(R.id.ll_bottom_controller_subtitle_color);
        setFocusable(this.mBottomCClast);
        setFocusable(this.mBottomCCSpeed);
        setFocusable(this.mBottomCCSize);
        setFocusable(this.mBottomCCclose);
        setFocusable(this.mBottomTranslate);
        setFocusable(this.mIvPlay);
        this.mBottomCClast.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$_init_cc_controller$9(view);
            }
        });
        this.mBottomCCSpeed.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.29
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ExoPlayerView.this.ll_srt_speed.setVisibility(0);
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.setNoFocusable(exoPlayerView.srtsubstate);
                ExoPlayerView exoPlayerView2 = ExoPlayerView.this;
                exoPlayerView2.requestFocusable(exoPlayerView2.srtsubstate);
                ExoPlayerView.this.mSrtController.setVisibility(8);
                ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
            }
        });
        this.mBottomCCSize.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.30
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ExoPlayerView.this.ll_srt_size.setVisibility(0);
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.setFocusable(exoPlayerView.mSrtsizesubstate);
                ExoPlayerView exoPlayerView2 = ExoPlayerView.this;
                exoPlayerView2.requestFocusable(exoPlayerView2.mSrtsizesubstate);
                ExoPlayerView.this.mSrtController.setVisibility(8);
            }
        });
        this.mBottomCCclose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.31
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.removeSRTlist(exoPlayerView.mSrtSlelct);
                ExoPlayerView.this.simpleSubtitleView.stop();
                ExoPlayerView.this.mSrtController.setVisibility(8);
                ExoPlayerView.this._setControlBarVisible(false);
                ExoPlayerView.this.RequestFlVideoBox();
                ExoPlayerView.this.mIsShowBar = false;
                if (ExoPlayerView.this.playerViewCallback != null) {
                    ExoPlayerView.this.playerViewCallback.disSelectSubtitle(ExoPlayerView.this.currSid);
                }
            }
        });
        this.mBottomTranslate.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.32
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                String str;
                if (App.getUserData().getIsvip() != 1) {
                    if (ExoPlayerView.this.QualityListener != null) {
                        ExoPlayerView.this.QualityListener.onClick();
                    }
                } else if (ExoPlayerView.this.mAttachActivity != null) {
                    if (ExoPlayerView.this.simpleSubtitleView.getSubtitles() != null) {
                        SubtitleContentLiveData.INSTANCE.get().setValue(ExoPlayerView.this.simpleSubtitleView.getSubtitles());
                    }
                    if (TextUtils.isEmpty(ExoPlayerView.this.currOriginalSid)) {
                        str = ExoPlayerView.this.currSid;
                    } else {
                        str = ExoPlayerView.this.currOriginalSid;
                    }
                    TranslateSubtitleActivity.INSTANCE.start(ExoPlayerView.this.mAttachActivity, ExoPlayerView.this.currSubtitleLanguage, str, ExoPlayerView.this.boxType);
                }
            }
        });
        this.mBottomSubtitleColor.setOnClickListener(new AnonymousClass33());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_init_cc_controller$9(View view) {
        LastSaidFragment lastSaidFragment = new LastSaidFragment();
        this.lastSaidFragment = lastSaidFragment;
        lastSaidFragment.setOnItemClickListener(new LastSaidFragment.OnLastSaidListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda4
            @Override // com.movieboxpro.android.view.dialog.LastSaidFragment.OnLastSaidListener
            public final void onLastSaidClick(Subtitle subtitle) {
                this.f$0.lambda$_init_cc_controller$8(subtitle);
            }
        });
        if (this.simpleSubtitleView.getSubtitles() != null) {
            this.lastSaidFragment.setSubtitleData(this.simpleSubtitleView.getSubtitles());
        } else {
            this.lastSaidFragment.setSubtitleData(new ArrayList());
        }
        this.lastSaidFragment.setSelectPos(this.simpleSubtitleView.getCurrSubtitlePos());
        this.lastSaidFragment.show(this.fragmentManager, "LastSaidFragment");
        pause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_init_cc_controller$8(Subtitle subtitle) {
        int iFloor = (int) ((Math.floor((this.simpleSubtitleView.setCurrSubtitle(subtitle) / 1000.0f) * 2.0f) / 2.0d) * 1000.0d);
        this.srtspeed = iFloor;
        this.srttext.setText(formatDelaySecond(iFloor));
        start();
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.exo.ExoPlayerView$33, reason: invalid class name */
    class AnonymousClass33 implements View.OnClickListener {
        AnonymousClass33() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            SubtitleColorDialog subtitleColorDialog = new SubtitleColorDialog();
            subtitleColorDialog.setListener(new SubtitleColorDialog.ChooseSubtitleColorListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$33$$ExternalSyntheticLambda0
                @Override // com.movieboxpro.android.view.dialog.SubtitleColorDialog.ChooseSubtitleColorListener
                public final void onChooseColor(int i) {
                    this.f$0.lambda$onClick$0(i);
                }
            });
            subtitleColorDialog.show(ExoPlayerView.this.fragmentManager, "SubtitleColorDialog");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$onClick$0(int i) {
            ExoPlayerView.this.simpleSubtitleView.changeColor(i);
        }
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public int getVideoHeight() {
        ExoVideoView exoVideoView = this.mVideoView;
        if (exoVideoView != null) {
            return exoVideoView.getVideoHeight();
        }
        return 0;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public int getVideoWidth() {
        ExoVideoView exoVideoView = this.mVideoView;
        if (exoVideoView != null) {
            return exoVideoView.getVideoWidth();
        }
        return 0;
    }

    private void _init_cc_list() {
        ImageView imageView = (ImageView) findViewById(R.id.tv_srtcelsect);
        this.mSrtSelect = imageView;
        imageView.setOnClickListener(this);
        setFocusable(this.mSrtSelect);
    }

    private void initSubtitlePos() {
        this.rlSubtitlePos = (LinearLayout) findViewById(R.id.rlSubtitlePos);
        this.ivSubtitlePos = (ImageView) findViewById(R.id.ll_bottom_controller_position);
        this.ivSubtitleDown = (ImageView) findViewById(R.id.ivSubtitleDown);
        this.ivSubtitleUp = (ImageView) findViewById(R.id.ivSubtitleUp);
        this.ivSubtitlePosClose = (ImageView) findViewById(R.id.ivSubtitlePosClose);
        this.llSubtitle = (LinearLayout) findViewById(R.id.llSubtitle);
        this.subtitleLine = findViewById(R.id.subtitleLine);
        this.ivSubtitlePos.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.34
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ExoPlayerView.this.rlSubtitlePos.setVisibility(0);
                ExoPlayerView.this._hideAllView(false);
                ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.requestFocusable(exoPlayerView.ivSubtitleUp);
                ExoPlayerView.this.subtitleLine.setVisibility(0);
            }
        });
        this.ivSubtitlePosClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initSubtitlePos$10(view);
            }
        });
        this.ivSubtitleDown.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.35
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DensityUtils.addMarginPx(ExoPlayerView.this.llSubtitle, 0, 0, 0, -10);
                if (((ViewGroup.MarginLayoutParams) ExoPlayerView.this.llSubtitle.getLayoutParams()).bottomMargin <= 0) {
                    DensityUtils.setMarginPx(ExoPlayerView.this.llSubtitle, 0, 0, 0, 0);
                }
                PrefsUtils.getInstance().putInt(Constant.Prefs.SUBTITLE_POS, ((ViewGroup.MarginLayoutParams) ExoPlayerView.this.llSubtitle.getLayoutParams()).bottomMargin);
            }
        });
        this.ivSubtitleUp.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.36
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                DensityUtils.addMarginPx(ExoPlayerView.this.llSubtitle, 0, 0, 0, 10);
                if (((ViewGroup.MarginLayoutParams) ExoPlayerView.this.llSubtitle.getLayoutParams()).bottomMargin >= DensityUtils.getScreenHeight() - ExoPlayerView.this.llSubtitle.getHeight()) {
                    DensityUtils.setMarginPx(ExoPlayerView.this.llSubtitle, 0, 0, 0, DensityUtils.getScreenHeight() - ExoPlayerView.this.llSubtitle.getHeight());
                }
                PrefsUtils.getInstance().putInt(Constant.Prefs.SUBTITLE_POS, ((ViewGroup.MarginLayoutParams) ExoPlayerView.this.llSubtitle.getLayoutParams()).bottomMargin);
            }
        });
        DensityUtils.setMarginPx(this.llSubtitle, 0, 0, 0, PrefsUtils.getInstance().getInt(Constant.Prefs.SUBTITLE_POS, DensityUtils.dp2px(App.getContext(), 10.0f)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initSubtitlePos$10(View view) {
        this.rlSubtitlePos.setVisibility(8);
        this.subtitleLine.setVisibility(8);
        RequestFlVideoBox();
    }

    private void _init_cc_speed() {
        this.ll_srt_speed = (RelativeLayout) findViewById(R.id.ll_bottom_srt);
        this.srtsubstate = (ImageView) findViewById(R.id.ll_srt_subtract);
        this.srtadd = (ImageView) findViewById(R.id.ll_srt_add);
        this.srttext = (TextView) findViewById(R.id.ll_srt_speed);
        this.ll_srt_average = (TextView) findViewById(R.id.ll_srt_average);
        ImageView imageView = (ImageView) findViewById(R.id.ll_srt_close);
        this.ll_srt_close = imageView;
        setFocusable(imageView);
        setNoFocusable(this.srtadd);
        setNoFocusable(this.srtsubstate);
        this.ll_srt_close.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.37
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ExoPlayerView.this.mSrtController.setVisibility(8);
                ExoPlayerView.this.ll_srt_speed.setVisibility(8);
                ExoPlayerView.this.mSrtController.setVisibility(0);
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.requestFocusable(exoPlayerView.mBottomCCSpeed);
            }
        });
        this.srttext.setText(formatDelaySecond(this.srtspeed));
        this.srtadd.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.38
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == 23 || keyCode == 66) && event.getAction() == 0) {
                    ExoPlayerView.this.srtspeed += WebProgress.DO_END_PROGRESS_DURATION;
                    ExoPlayerView.this.simpleSubtitleView.setDelay(ExoPlayerView.this.srtspeed);
                    TextView textView = ExoPlayerView.this.srttext;
                    ExoPlayerView exoPlayerView = ExoPlayerView.this;
                    textView.setText(String.format("%s s", exoPlayerView.formatDelaySecond(exoPlayerView.srtspeed)));
                }
                return false;
            }
        });
        this.srtsubstate.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.39
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == 23 || keyCode == 66) && event.getAction() == 0) {
                    ExoPlayerView.this.srtspeed -= 500;
                    ExoPlayerView.this.simpleSubtitleView.setDelay(ExoPlayerView.this.srtspeed);
                    TextView textView = ExoPlayerView.this.srttext;
                    ExoPlayerView exoPlayerView = ExoPlayerView.this;
                    textView.setText(String.format("%s s", exoPlayerView.formatDelaySecond(exoPlayerView.srtspeed)));
                }
                return false;
            }
        });
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void clearSubtitle() {
        this.ll_srt_size.setVisibility(8);
        this.ll_srt_speed.setVisibility(8);
        this.mSrtController.setVisibility(8);
        this.simpleSubtitleView.clearSubtitles();
        this.currSelectServerSubtitle = null;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setSubtitleMarginBottom(int bottom) {
        DensityUtils.setMarginPx(this.simpleSubtitleView, 0, 0, 0, bottom);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setVideoPreviewListener(VideoPreviewListener videoPreviewListener) {
        this.videoPreviewListener = videoPreviewListener;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void hideLastVideo() {
        this.llLastEpisode.setVisibility(8);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void showLastVideo() {
        this.llLastEpisode.setVisibility(0);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void hideNextVideo() {
        this.llNextEpisode.setVisibility(8);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void showNextVideo() {
        this.llNextEpisode.setVisibility(0);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setKeepControlBarVisible(boolean keep) {
        this.keepControlBarVisible = keep;
        if (keep) {
            _showControlBar(0);
        } else {
            _refreshHideRunnable();
        }
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void requestEpisodeListFocus() {
        LinearLayout linearLayout = this.llEpisodeList;
        if (linearLayout == null || linearLayout.getVisibility() != 0) {
            return;
        }
        this.llEpisodeList.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveSizeValue(int value) {
        if (this.prefs == null) {
            this.prefs = this.mAttachActivity.getSharedPreferences(Constant.Prefs.SETTING, 0);
        }
        this.prefs.edit().putInt("srt_size_value", value).apply();
    }

    private int getSizeValue() {
        if (this.prefs == null) {
            this.prefs = this.mAttachActivity.getSharedPreferences(Constant.Prefs.SETTING, 0);
        }
        return this.prefs.getInt("srt_size_value", 24);
    }

    private void _init_cc_size() {
        this.ll_srt_size = (RelativeLayout) findViewById(R.id.ll_bottom_srtsize);
        this.mSrtsizeAdd = (ImageView) findViewById(R.id.ll_srtsize_add);
        this.mSrtsizesubstate = (ImageView) findViewById(R.id.ll_srtsize_subtract);
        this.mSrtsizeClose = (ImageView) findViewById(R.id.ll_srtsize_close);
        TextView textView = (TextView) findViewById(R.id.ll_srtsize_speed);
        this.mSrtsizeText = textView;
        StringBuilder sb = new StringBuilder();
        sb.append(this.srtsize);
        textView.setText(sb);
        setFocusable(this.mSrtsizeClose);
        int sizeValue = getSizeValue();
        this.srtsize = sizeValue;
        this.simpleSubtitleView.changeSize(sizeValue);
        this.simpleSubtitleView.bindOnMediaStatusListener(new OnMediaStatusListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.40
            @Override // com.avery.subtitle.OnMediaStatusListener
            public boolean isPrepared() {
                return ExoPlayerView.this.mVideoView != null;
            }

            @Override // com.avery.subtitle.OnMediaStatusListener
            public boolean isPlaying() {
                if (ExoPlayerView.this.mVideoView != null) {
                    return ExoPlayerView.this.mVideoView.isPlaying();
                }
                return false;
            }

            @Override // com.avery.subtitle.OnMediaStatusListener
            public long getCurrentPosition() {
                if (ExoPlayerView.this.mVideoView != null) {
                    return ExoPlayerView.this.mVideoView.getCurrentPosition();
                }
                return 0L;
            }
        });
        TextView textView2 = this.mSrtsizeText;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.srtsize);
        textView2.setText(sb2);
        this.mSrtsizeAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.41
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                ExoPlayerView.this.srtsize++;
                TextView textView3 = ExoPlayerView.this.mSrtsizeText;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(ExoPlayerView.this.srtsize);
                textView3.setText(sb3);
                ExoPlayerView.this.simpleSubtitleView.changeSize(ExoPlayerView.this.srtsize);
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.saveSizeValue(exoPlayerView.srtsize);
            }
        });
        this.mSrtsizesubstate.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$_init_cc_size$11(view);
            }
        });
        this.mSrtsizeClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$_init_cc_size$12(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_init_cc_size$11(View view) {
        this.srtsize--;
        TextView textView = this.mSrtsizeText;
        StringBuilder sb = new StringBuilder();
        sb.append(this.srtsize);
        textView.setText(sb);
        this.simpleSubtitleView.changeSize(this.srtsize);
        saveSizeValue(this.srtsize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$_init_cc_size$12(View view) {
        this.ll_srt_size.setVisibility(8);
        this.mSrtController.setVisibility(0);
        requestFocusable(this.mBottomCCSize);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public ExoPlayerView setSRTList(LinkedHashMap<String, List<SRTModel.subTitles>> srtList, OnDownloadClickListener listener, SRTModel.subTitles selectSubtitle) {
        this.stringListHashMap.clear();
        this.languages.clear();
        this.lastSelectedSubtitle = selectSubtitle;
        this.stringListHashMap = srtList;
        this.listener = listener;
        for (Map.Entry<String, List<SRTModel.subTitles>> entry : srtList.entrySet()) {
            languageModel languagemodel = new languageModel();
            languagemodel.setLanguage(entry.getKey());
            languagemodel.setSelected(false);
            this.languages.add(languagemodel);
        }
        languageModel languagemodel2 = new languageModel();
        languagemodel2.language = this.openSubtitle;
        this.languages.add(languagemodel2);
        return this;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void startSRT(final ArrayList<SRT> list, final String content, final String language, final String subtitlePath, final SRTModel.subTitles subTitles) {
        this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.42
            @Override // java.lang.Runnable
            public void run() {
                SRTModel.subTitles subtitles = subTitles;
                if (subtitles != null) {
                    ExoPlayerView.this.currSid = subtitles.getSid();
                    if (subTitles.getOrg_subtitle() != null) {
                        ExoPlayerView.this.currOriginalSid = subTitles.getOrg_subtitle().getSid();
                    }
                    ExoPlayerView.this.srtspeed = (int) (subTitles.delay * 1000.0f);
                    ExoPlayerView.this.simpleSubtitleView.setDelay(ExoPlayerView.this.srtspeed);
                    TextView textView = ExoPlayerView.this.srttext;
                    ExoPlayerView exoPlayerView = ExoPlayerView.this;
                    textView.setText(exoPlayerView.formatDelaySecond(exoPlayerView.srtspeed));
                }
                ExoPlayerView.this.simpleSubtitleView.setSubtitlePath(subtitlePath);
                if (list != null) {
                    ExoPlayerView.this.srtList.clear();
                    ExoPlayerView.this.srtList.addAll(list);
                    ExoPlayerView.this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.42.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ExoPlayerView.this.setDataList(list);
                            ExoPlayerView.this.mSrtController.setVisibility(0);
                        }
                    });
                    ExoPlayerView.this.subtitleContent = content;
                    ExoPlayerView.this.currSubtitleLanguage = language;
                }
            }
        });
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void showTranslateSubtitle(List<SRT> srts) {
        setSimpleSubtitles(srts);
        this.mBottomTranslate.setImageResource(R.mipmap.ic_translate_selected);
        this.srtList.clear();
        this.srtList.addAll(srts);
    }

    public void setSimpleSubtitles(List<SRT> list) {
        ArrayList arrayList = new ArrayList();
        for (SRT srt : list) {
            Subtitle subtitle = new Subtitle();
            subtitle.content = srt.getSrtBody();
            subtitle.start = new Time(srt.getBeginTime());
            subtitle.end = new Time(srt.getEndTime());
            arrayList.add(subtitle);
        }
        this.simpleSubtitleView.setSubtitles(arrayList);
        this.simpleSubtitleView.refreshSubtitle();
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void startSRT2(final ArrayList<SRT> list, String content, String language, String sid) {
        setSimpleSubtitles(list);
        this.currSid = sid;
        this.srtList.clear();
        this.srtList.addAll(list);
        this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.43
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView.this.setDataList(list);
            }
        });
        this.mSrtController.setVisibility(0);
        this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.exo.ExoPlayerView.44
            @Override // java.lang.Runnable
            public void run() {
                ExoPlayerView.this._toggleCCSelect();
            }
        });
        this.subtitleContent = content;
        this.currSubtitleLanguage = language;
        this.mBottomTranslate.setImageResource(R.mipmap.ic_translate_normal);
    }

    public void removeSRTlist(int quality) {
        Log.d("AAAAAA", "复杂" + this.mCurSelectSRT + this.mCorrentLanguage + this.mSrtSlelct + quality);
        if (this.mCurSelectSRT == this.mCorrentLanguage && this.mSrtSlelct == quality) {
            this.mSrtSlelct = -1;
        }
    }

    public void setDataList(List<SRT> srtPraseModels) {
        this.mList.clear();
        this.mList.addAll(srtPraseModels);
        this.isFrist = false;
    }

    public void _initMediaTrack() {
        ImageView imageView = (ImageView) findViewById(R.id.tv_meidaTrack);
        this.mFlMediaTracks = imageView;
        setFocusable(imageView);
        this.mFlMediaTracks.setOnClickListener(this);
    }

    private void _initMediaQuality() {
        this.mMediaQualityDesc = getResources().getStringArray(com.dl7.player.R.array.media_quality);
        ImageView imageView = (ImageView) findViewById(R.id.iv_media_quality);
        this.mIvMediaQuality = imageView;
        imageView.setOnClickListener(this);
        setFocusable(this.mIvMediaQuality);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void calBitStream(String bitstream) {
        if (!TextUtils.isEmpty(bitstream)) {
            if (bitstream.contains("MB/s")) {
                try {
                    this.bitStream = (long) ((Double.parseDouble(bitstream.replace("MB/s", "")) / 8.0d) * 1024.0d * 0.6d);
                    return;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        this.bitStream = 0L;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public int getCurrQuality() {
        return this.mCurSelectQuality;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void doStart() {
        start();
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public int setVideoSource(List<MediaQualityInfo> mediaMedium, OnVipClickListener listener, int startSeconds, boolean isContinue, int pos) {
        MediaQualityInfo mediaQualityInfoFindAutoItem;
        if (mediaMedium == null || mediaMedium.size() == 0) {
            return 0;
        }
        ArrayList<MediaQualityInfo> arrayListGroupQualityList = GroupUtils.groupQualityList(mediaMedium);
        mediaMedium.clear();
        mediaMedium.addAll(arrayListGroupQualityList);
        ArrayList<AudioTrackModel> arrayList = this.audioTrackModels;
        if (arrayList != null) {
            arrayList.clear();
        }
        this.QualityListener = listener;
        this.mQualityData = new ArrayList();
        for (int i = 0; i < mediaMedium.size(); i++) {
            mediaMedium.get(i).setIndex(i);
            mediaMedium.get(i).setSelect(false);
            this.mQualityData.add(mediaMedium.get(i));
        }
        this.mCurSelectQuality = pos;
        if (pos >= mediaMedium.size()) {
            this.mCurSelectQuality = 0;
        }
        String lowerCase = mediaMedium.get(this.mCurSelectQuality).getReal_quality().toLowerCase();
        lowerCase.hashCode();
        switch (lowerCase) {
            case "4k":
                this.mIvMediaQuality.setImageResource(com.dl7.player.R.mipmap.ic_media_quality_4k);
                break;
            case "org":
                this.mIvMediaQuality.setImageResource(R.mipmap.ic_media_quality_origin);
                break;
            case "360p":
                this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[0]);
                break;
            case "720p":
                this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[1]);
                break;
            case "auto":
                this.mIvMediaQuality.setImageResource(R.mipmap.ic_media_quality_auto);
                break;
            case "1080p":
                this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[2]);
                break;
        }
        this.mCurPosition = startSeconds;
        MediaQualityInfo mediaQualityInfo = mediaMedium.get(this.mCurSelectQuality);
        this.mediaQualityInfo = mediaQualityInfo;
        String path = mediaQualityInfo.getPath();
        this.mVideoView.setPlayingHlsMaster(false);
        this.mVideoView.setPendingVideoQuality(null);
        if (HlsVideoTrackSwitcher.shouldStartOnAutoMaster(this.mQualityData, this.mediaQualityInfo) && (mediaQualityInfoFindAutoItem = HlsVideoTrackSwitcher.findAutoItem(this.mQualityData, this.mediaQualityInfo)) != null && !TextUtils.isEmpty(mediaQualityInfoFindAutoItem.getPath())) {
            path = mediaQualityInfoFindAutoItem.getPath();
            this.mVideoView.setPlayingHlsMaster(true);
            this.mVideoView.setPendingVideoQuality(this.mediaQualityInfo.getReal_quality());
        }
        if (this.mediaQualityInfo.getHdr() != 1) {
            "4K".equalsIgnoreCase(this.mediaQualityInfo.getReal_quality());
        }
        setVideoPath(path, startSeconds);
        this.lastQualityPos = this.mCurSelectQuality;
        this.mLoadingView.setVisibility(0);
        return this.mCurSelectQuality;
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void setHlsTracks(List<BaseMediaModel.DownloadFile> tracks) {
        this.hlsAudioTacks = tracks;
    }

    public ExoPlayerView changeMediaQuality(int quality) {
        MediaQualityInfo mediaQualityInfoFindAutoItem;
        MediaQualityInfo mediaQualityInfoFindAutoItem2;
        boolean zPinVideoQuality;
        ArrayList<AudioTrackModel> arrayList = this.audioTrackModels;
        if (arrayList != null) {
            arrayList.clear();
        }
        List<MediaQualityInfo> list = this.mQualityData;
        if (list != null && list.size() >= quality) {
            String lowerCase = this.mQualityData.get(quality).getReal_quality().toLowerCase();
            lowerCase.hashCode();
            boolean z = true;
            switch (lowerCase) {
                case "4k":
                    this.mIvMediaQuality.setImageResource(com.dl7.player.R.mipmap.ic_media_quality_4k);
                    break;
                case "8k":
                    this.mIvMediaQuality.setImageResource(com.dl7.player.R.mipmap.ic_media_quality_8k);
                    break;
                case "org":
                    this.mIvMediaQuality.setImageResource(R.mipmap.ic_media_quality_origin);
                    break;
                case "360p":
                    this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[0]);
                    break;
                case "720p":
                    this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[1]);
                    break;
                case "auto":
                    this.mIvMediaQuality.setImageResource(R.mipmap.ic_media_quality_auto);
                    break;
                case "1080p":
                    this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[2]);
                    break;
            }
            int i = this.mCurSelectQuality;
            MediaQualityInfo mediaQualityInfo = (i < 0 || i >= this.mQualityData.size()) ? null : this.mQualityData.get(i);
            MediaQualityInfo mediaQualityInfo2 = this.mQualityData.get(quality);
            boolean z2 = mediaQualityInfo != mediaQualityInfo2;
            boolean z3 = quality == i;
            PrefsUtils.getInstance().putString(Constant.Prefs.LAST_SELECT_QUA, mediaQualityInfo2.getReal_quality());
            this.mCurSelectQuality = quality;
            this.mCurPosition = this.mVideoView.getCurrentPosition();
            if (!z3 && HlsVideoTrackSwitcher.canSwitchInPlace(this.mVideoView.isPlayingHlsMaster(), mediaQualityInfo, mediaQualityInfo2)) {
                if (HlsVideoTrackSwitcher.isAutoLabel(mediaQualityInfo2.getReal_quality())) {
                    this.mVideoView.clearVideoTrackOverrides();
                    zPinVideoQuality = true;
                } else {
                    zPinVideoQuality = this.mVideoView.pinVideoQuality(mediaQualityInfo2.getReal_quality());
                }
                if (zPinVideoQuality) {
                    this.lastQualityPos = quality;
                    this.mediaQualityInfo = mediaQualityInfo2;
                    if (z2) {
                        startQualitySwitchHint(mediaQualityInfo2.getReal_quality());
                    }
                    return this;
                }
            } else if (!z3 && HlsVideoTrackSwitcher.shouldStartOnAutoMaster(this.mQualityData, mediaQualityInfo2) && (mediaQualityInfoFindAutoItem = HlsVideoTrackSwitcher.findAutoItem(this.mQualityData, mediaQualityInfo2)) != null && !TextUtils.isEmpty(mediaQualityInfoFindAutoItem.getPath())) {
                this.mVideoView.setPlayingHlsMaster(true);
                this.mVideoView.setPendingVideoQuality(mediaQualityInfo2.getReal_quality());
                tearDownExtraAudioForReload();
                this.mLoadingView.setVisibility(0);
                if (z2) {
                    startQualitySwitchHint(mediaQualityInfo2.getReal_quality());
                }
                changeQuality(mediaQualityInfoFindAutoItem.getPath(), this.mCurPosition, false);
                this.lastQualityPos = quality;
                this.mediaQualityInfo = mediaQualityInfo2;
                return this;
            }
            if (z3 && this.mVideoView.isPlayingHlsMaster() && (mediaQualityInfoFindAutoItem2 = HlsVideoTrackSwitcher.findAutoItem(this.mQualityData, mediaQualityInfo2)) != null && !TextUtils.isEmpty(mediaQualityInfoFindAutoItem2.getPath())) {
                this.mVideoView.setPlayingHlsMaster(true);
                this.mVideoView.setPendingVideoQuality(mediaQualityInfo2.getReal_quality());
                tearDownExtraAudioForReload();
                this.mLoadingView.setVisibility(0);
                changeQuality(mediaQualityInfoFindAutoItem2.getPath(), this.mCurPosition, false);
                this.lastQualityPos = quality;
                this.mediaQualityInfo = mediaQualityInfo2;
                return this;
            }
            this.mVideoView.setPlayingHlsMaster(false);
            this.mVideoView.setPendingVideoQuality(null);
            tearDownExtraAudioForReload();
            this.mLoadingView.setVisibility(0);
            if (z2) {
                startQualitySwitchHint(mediaQualityInfo2.getReal_quality());
            }
            if (mediaQualityInfo2.getOriginal() != 1 && !"4K".equalsIgnoreCase(mediaQualityInfo2.getReal_quality())) {
                z = false;
            }
            changeQuality(mediaQualityInfo2.getPath(), this.mCurPosition, z);
            this.lastQualityPos = quality;
            this.mediaQualityInfo = mediaQualityInfo2;
        }
        return this;
    }

    private void tearDownExtraAudioForReload() {
        ExoAudioPlayHelper exoAudioPlayHelper = this.audioPlayHelper;
        if (exoAudioPlayHelper != null) {
            exoAudioPlayHelper.release();
        }
        CustomExoPlayer mediaPlayer = this.mVideoView.getMediaPlayer();
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(1.0f, 1.0f);
        }
        this.audioOssFid = "";
    }

    private void startQualitySwitchHint(String quality) {
        cancelQualityHintCallbacks();
        this.pendingQualityHint = quality;
        showQualityHint(HlsVideoTrackSwitcher.switchingHint(quality), false);
        if (HlsVideoTrackSwitcher.isAutoLabel(quality)) {
            confirmQualitySwitchHint();
            return;
        }
        ExoVideoView exoVideoView = this.mVideoView;
        if (exoVideoView != null && HlsVideoTrackSwitcher.shouldConfirmSwitchHint(exoVideoView.getVideoWidth(), this.mVideoView.getVideoHeight(), quality)) {
            confirmQualitySwitchHint();
        } else {
            this.mHandler.postDelayed(this.qualitySwitchTimeoutRunnable, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS);
        }
    }

    private void confirmQualitySwitchHint() {
        String str = this.pendingQualityHint;
        if (str == null) {
            return;
        }
        this.pendingQualityHint = null;
        this.mHandler.removeCallbacks(this.qualitySwitchTimeoutRunnable);
        showQualityHint(HlsVideoTrackSwitcher.switchedHint(str), true);
    }

    private void cancelQualityHintCallbacks() {
        this.mHandler.removeCallbacks(this.hideQualityHintRunnable);
        this.mHandler.removeCallbacks(this.qualitySwitchTimeoutRunnable);
    }

    private void showQualityHint(String text, boolean autoHide) {
        if (this.tvQualityHint == null) {
            return;
        }
        this.mHandler.removeCallbacks(this.hideQualityHintRunnable);
        this.tvQualityHint.clearAnimation();
        this.qualityHintGeneration++;
        this.tvQualityHint.setText(text);
        boolean z = this.tvQualityHint.getVisibility() != 0;
        this.tvQualityHint.setVisibility(0);
        if (z) {
            TranslateAnimation translateAnimation = new TranslateAnimation(1, -1.0f, 1, 0.0f, 1, 0.0f, 1, 0.0f);
            translateAnimation.setDuration(300L);
            this.tvQualityHint.startAnimation(translateAnimation);
        }
        if (autoHide) {
            this.mHandler.postDelayed(this.hideQualityHintRunnable, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideQualityHint() {
        TextView textView = this.tvQualityHint;
        if (textView == null || textView.getVisibility() != 0) {
            return;
        }
        this.tvQualityHint.clearAnimation();
        final int i = this.qualityHintGeneration;
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, -1.0f, 1, 0.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.45
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (ExoPlayerView.this.tvQualityHint == null || !HlsVideoTrackSwitcher.shouldCommitQualityHintHide(i, ExoPlayerView.this.qualityHintGeneration)) {
                    return;
                }
                ExoPlayerView.this.tvQualityHint.setVisibility(8);
            }
        });
        this.tvQualityHint.startAnimation(translateAnimation);
    }

    public ExoPlayerView setMediaQuality(int quality) {
        List<MediaQualityInfo> list;
        if (this.mCurSelectQuality != quality && this.mVideoSource.get(quality) != null && (list = this.mQualityData) != null && list.size() >= quality) {
            String real_quality = this.mQualityData.get(quality).getReal_quality();
            real_quality.hashCode();
            switch (real_quality) {
                case "4K":
                    this.mIvMediaQuality.setImageResource(com.dl7.player.R.mipmap.ic_media_quality_4k);
                    break;
                case "org":
                    this.mIvMediaQuality.setImageResource(R.mipmap.ic_media_quality_origin);
                    break;
                case "360p":
                    this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[0]);
                    break;
                case "720p":
                    this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[1]);
                    break;
                case "1080p":
                    this.mIvMediaQuality.setImageResource(QUALITY_DRAWABLE_RES[2]);
                    break;
            }
            this.mCurSelectQuality = quality;
        }
        return this;
    }

    private void _initVideoSkip() {
        this.mLlSkipLayout = findViewById(com.dl7.player.R.id.ll_skip_layout);
        this.mIvCancelSkip = (ImageView) findViewById(com.dl7.player.R.id.iv_cancel_skip);
        this.mTvSkipTime = (TextView) findViewById(com.dl7.player.R.id.tv_skip_time);
        this.mTvDoSkip = (TextView) findViewById(com.dl7.player.R.id.tv_do_skip);
        this.mIvCancelSkip.setOnClickListener(this);
        this.mTvDoSkip.setOnClickListener(this);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public int getCurPosition() {
        return this.mVideoView.getCurrentPosition();
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public int getDurarion() {
        return this.mVideoView.getDuration();
    }

    public ExoPlayerView setSkipTip(int targetPosition) {
        this.mSkipPosition = targetPosition;
        return this;
    }

    private void _showSkipTip() {
        if (this.mSkipPosition == -1 || this.mLlSkipLayout.getVisibility() != 8) {
            return;
        }
        this.mLlSkipLayout.setVisibility(0);
        this.mTvSkipTime.setText(StringUtils.generateTime(this.mSkipPosition));
        AnimHelper.doSlideRightIn(this.mLlSkipLayout, this.mWidthPixels, 0, 800);
        this.mHandler.removeCallbacks(this.mHideSkipTipRunnable);
        this.mHandler.postDelayed(this.mHideSkipTipRunnable, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideSkipTip() {
        if (this.mLlSkipLayout.getVisibility() == 8) {
            return;
        }
        ViewCompat.animate(this.mLlSkipLayout).translationX(-this.mLlSkipLayout.getWidth()).alpha(0.0f).setDuration(500L).setListener(new ViewPropertyAnimatorListenerAdapter() { // from class: com.movieboxpro.android.exo.ExoPlayerView.46
            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view) {
                ExoPlayerView.this.mLlSkipLayout.setVisibility(8);
            }
        }).start();
        this.mSkipPosition = -1;
    }

    public boolean recoverFromEditVideo() {
        Log.d("VIEWDOPLAY", "mIsShowCC" + this.mIsShowCC + "mIsShowQuality" + this.mIsShowQuality + "mVideoStatus" + this.mVideoStatus);
        int i = this.mVideoStatus;
        if (i == 501) {
            return false;
        }
        if (i == 502) {
            start();
        }
        this.mVideoStatus = 501;
        return true;
    }

    public class NetBroadcastReceiver extends BroadcastReceiver {
        public NetBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                ExoPlayerView exoPlayerView = ExoPlayerView.this;
                exoPlayerView.mIsNetConnected = NetWorkUtils.isNetworkAvailable(exoPlayerView.mAttachActivity);
            }
        }
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void requestRootFocues() {
        RequestFlVideoBox();
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void showSkippedTimeHint(String firstText, String secondText, boolean startType) {
        cancelQualityHintCallbacks();
        this.pendingQualityHint = null;
        TextView textView = this.tvQualityHint;
        if (textView != null) {
            textView.clearAnimation();
            this.qualityHintGeneration++;
            this.tvQualityHint.setVisibility(8);
        }
        this.mHandler.removeCallbacks(this.hideContinueText);
        this.mHandler.removeCallbacks(this.showContinueText);
        ImageView imageView = this.ivSkipImage;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        TextView textView2 = this.tvSkipText;
        if (textView2 != null) {
            textView2.setVisibility(0);
        }
        this.llContinue.setVisibility(0);
        if (startType) {
            this.ivSkipImage.setImageResource(R.mipmap.ic_restart_hint);
            this.leftBtnHint = true;
        } else {
            this.ivSkipImage.setImageResource(R.mipmap.ic_right_press_hint);
            this.leftBtnHint = false;
        }
        this.tvContinue.setText(firstText);
        this.tvSkipText.setText(secondText);
        this.mHandler.postDelayed(this.hideContinueText, DanmakuFactory.MIN_DANMAKU_DURATION);
    }

    @Override // com.movieboxpro.android.player.IPlayerView
    public void showContinueText() {
        this.mHandler.postDelayed(this.showContinueText, ExoPlayer.DEFAULT_DETACH_SURFACE_TIMEOUT_MS);
    }

    public void RequestFlVideoBox() {
        this.mFlVideoBox.setFocusableInTouchMode(true);
        this.mFlVideoBox.requestFocus();
    }

    private void RequestmIvPlay() {
        this.mIvPlay.setFocusableInTouchMode(true);
        this.mIvPlay.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void RequestmPlayerSeek() {
        this.mPlayerSeek.setFocusableInTouchMode(true);
        this.mPlayerSeek.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestFocusable(View view) {
        view.setFocusableInTouchMode(true);
        view.requestFocus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFocusable(View view) {
        view.setFocusable(true);
        view.setClickable(true);
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.48
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                if (ExoPlayerView.this.mIsShowBar && hasFocus) {
                    ExoPlayerView.this._showControlBar(5000);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setNoFocusable(View view) {
        view.setFocusable(true);
        view.setClickable(true);
        view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.exo.ExoPlayerView.49
            @Override // android.view.View.OnFocusChangeListener
            public void onFocusChange(View v, boolean hasFocus) {
                ExoPlayerView.this.mHandler.removeCallbacks(ExoPlayerView.this.mHideBarRunnable);
            }
        });
    }
}
