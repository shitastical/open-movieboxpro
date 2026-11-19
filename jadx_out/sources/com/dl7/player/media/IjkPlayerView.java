package com.dl7.player.media;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.media3.common.C;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.mediarouter.app.MediaRouteButton;
import com.dl7.player.R;
import com.dl7.player.adapter.AdapterMediaQuality;
import com.dl7.player.adapter.AdapterMediaQualitys;
import com.dl7.player.adapter.AdapterMediaTrack;
import com.dl7.player.adapter.AdapterSRTExtra;
import com.dl7.player.adapter.AdapterSRTLanguage;
import com.dl7.player.danmaku.BaseDanmakuConverter;
import com.dl7.player.danmaku.BiliDanmukuParser;
import com.dl7.player.danmaku.OnDanmakuListener;
import com.dl7.player.model.ExtrModel;
import com.dl7.player.model.MediaQualityInfo;
import com.dl7.player.model.SRTModel;
import com.dl7.player.model.TrackModel;
import com.dl7.player.model.languageModel;
import com.dl7.player.utils.AnimHelper;
import com.dl7.player.utils.MotionEventUtils;
import com.dl7.player.utils.NavUtils;
import com.dl7.player.utils.NetWorkUtils;
import com.dl7.player.utils.SDCardUtils;
import com.dl7.player.utils.SRT;
import com.dl7.player.utils.SoftInputUtils;
import com.dl7.player.utils.SrtParser;
import com.dl7.player.utils.StringUtils;
import com.dl7.player.utils.WindowUtils;
import com.dl7.player.widgets.MarqueeTextView;
import com.dl7.player.widgets.ShareDialog;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.controller.IDanmakuView;
import master.flame.danmaku.danmaku.loader.ILoader;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.loader.android.DanmakuLoaderFactory;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.IDataSource;
import org.joda.time.DateTimeConstants;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;

/* JADX INFO: loaded from: classes4.dex */
public class IjkPlayerView extends FrameLayout implements View.OnClickListener {
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
    private static final int PARSE_SRT_CN = 9999;
    private static final int[] QUALITY_DRAWABLE_RES = {R.mipmap.ic_media_quality_smooth, R.mipmap.ic_media_quality_medium, R.mipmap.ic_media_quality_high, R.mipmap.ic_media_quality_super, R.mipmap.ic_media_quality_medium};
    private List<TrackModel> ListTracks;
    private List<ExtrModel> ListexrtSrt;
    private ListView MediaTrackList;
    private OnVipClickListener QualityListener;
    private ListView exrtSrt;
    List<SRTModel.subTitles> getstr;
    private List<languageModel> languages;
    private OnDownloadClickListener listener;
    private TextView ll_srt_average;
    private ImageView ll_srt_close;
    private ImageView ll_srt_extr;
    private int mAspectOptionsHeight;
    private RadioGroup mAspectRatioOptions;
    private AppCompatActivity mAttachActivity;
    private AudioManager mAudioManager;
    private int mBasicOptionsWidth;
    private BatteryBroadcastReceiver mBatteryReceiver;
    private AdapterMediaQuality mCCAdapter;
    private ListView mCCCountrySelect;
    private AdapterSRTLanguage mCCLanguageAdapter;
    private ListView mCCListSelect;
    private IMediaPlayer.OnCompletionListener mCompletionListener;
    private int mCorrentLanguage;
    private float mCurBrightness;
    private int mCurPosition;
    public int mCurSelectQuality;
    private int mCurSelectSRT;
    private int mCurVolume;
    private RadioGroup mDanmakuColorOptions;
    private DanmakuContext mDanmakuContext;
    private BaseDanmakuConverter mDanmakuConverter;
    private RadioButton mDanmakuCurColor;
    private OnDanmakuListener mDanmakuListener;
    private ILoader mDanmakuLoader;
    private ImageView mDanmakuMoreColorIcon;
    private View mDanmakuMoreOptions;
    private View mDanmakuOptionsBasic;
    private BaseDanmakuParser mDanmakuParser;
    private SeekBar mDanmakuPlayerSeek;
    private int mDanmakuTag;
    private long mDanmakuTargetPosition;
    private int mDanmakuTextColor;
    private float mDanmakuTextSize;
    private RadioGroup mDanmakuTextSizeOptions;
    private int mDanmakuType;
    private RadioGroup mDanmakuTypeOptions;
    private IDanmakuView mDanmakuView;
    private RadioGroup mDecode;
    private ShareDialog.OnDialogClickListener mDialogClickListener;
    private ShareDialog.OnDialogDismissListener mDialogDismissListener;
    private View mEditDanmakuLayout;
    private EditText mEtDanmakuContent;
    private long mExitTime;
    private AdapterSRTExtra mExtraLanguageAdapter;
    private View mFlCCSlect;
    private View mFlExtraCC;
    private View mFlMediaQuality;
    private ImageView mFlMediaTracks;
    private View mFlReload;
    private RelativeLayout mFlSettings;
    private FrameLayout mFlTouchLayout;
    private View mFlTrack;
    private FrameLayout mFlVideoBox;
    private LinearLayout mFullscreenTopBar;
    private GestureDetector mGestureDetector;
    private Handler mHandler;
    private Runnable mHideBarRunnable;
    private Runnable mHideSkipTipRunnable;
    private Runnable mHideTouchViewRunnable;
    private IMediaPlayer.OnInfoListener mInfoListener;
    private int mInitHeight;
    private ShareDialog.OnDialogClickListener mInsideDialogClickListener;
    private int mInterruptPosition;
    private boolean mIsAlwaysFullScreen;
    private boolean mIsBufferingStart;
    private boolean mIsEnableDanmaku;
    private boolean mIsForbidOrientation;
    private boolean mIsForbidTouch;
    private boolean mIsFullscreen;
    private boolean mIsNeedRecoverScreen;
    private boolean mIsNetConnected;
    private boolean mIsNeverPlay;
    private boolean mIsPlayComplete;
    private boolean mIsReady;
    private boolean mIsRenderingStart;
    private boolean mIsScreenLocked;
    private boolean mIsSeeking;
    private boolean mIsShowBar;
    private boolean mIsShowCC;
    private boolean mIsShowExCC;
    private boolean mIsShowQuality;
    private boolean mIsShowST;
    private boolean mIsShowTracker;
    private ImageView mIvBack;
    private ImageView mIvBackWindow;
    private ImageView mIvBackWindow2;
    private ImageView mIvCancelSend;
    private ImageView mIvCancelSkip;
    private ImageView mIvDanmakuControl;
    private ImageView mIvDoSend;
    private ImageView mIvFullscreen;
    private TextView mIvMediaQuality;
    private ImageView mIvPlay;
    private ImageView mIvPlayCircle;
    private ImageView mIvPlayerLock;
    private ImageView mIvScreenshot;
    private LinearLayout mLlBottomBar;
    private View mLlSkipLayout;
    private ProgressBar mLoadingView;
    private ListView mLvMediaQuality;
    private int mMaxVolume;
    private String[] mMediaQualityDesc;
    private int mMoreOptionsWidth;
    private NetBroadcastReceiver mNetReceiver;
    private OrientationEventListener mOrientationListener;
    private IMediaPlayer.OnInfoListener mOutsideInfoListener;
    private ProgressBar mPbBatteryLevel;
    private GestureDetector.OnGestureListener mPlayerGestureListener;
    private SeekBar mPlayerSeek;
    public ImageView mPlayerThumb;
    private View.OnTouchListener mPlayerTouchListener;
    private AdapterMediaQualitys mQualityAdapter;
    private List<MediaQualityInfo> mQualityData;
    private File mSaveDir;
    private Matrix mSaveMatrix;
    private ScreenBroadcastReceiver mScreenReceiver;
    private int mScreenUiVisibility;
    private final SeekBar.OnSeekBarChangeListener mSeekListener;
    private ShareDialog mShareDialog;
    private int mSkipPosition;
    private RadioGroup mSpeedRatioOptions;
    private RelativeLayout mSrtController;
    private ImageView mSrtSelect;
    private int mSrtSlelct;
    private TextView mSrtText;
    private long mTargetPosition;
    private AdapterMediaTrack mTrackAdapter;
    private TextView mTvBrightness;
    private TextView mTvCurTime;
    private TextView mTvDoSkip;
    private TextView mTvEndTime;
    private TextView mTvFastForward;
    private TextView mTvOpenEditDanmaku;
    private TextView mTvRecoverScreen;
    private TextView mTvReload;
    private ImageView mTvSettings;
    private TextView mTvSkipTime;
    private TextView mTvSystemTime;
    private TextView mTvTimeSeparator;
    private MarqueeTextView mTvTitle;
    private TextView mTvVolume;
    private Matrix mVideoMatrix;
    private SparseArray<String> mVideoSource;
    private int mVideoStatus;
    private IjkVideoView mVideoView;
    private int mWidthPixels;
    private FrameLayout mWindowTopBar;
    private MediaRouteButton mediaRouteButton;
    private OnOpenExtraDownload onOpenExtraDownload;
    private ArrayList<SRT> srtList;
    private ImageView srtadd;
    private int srtspeed;
    private ImageView srtsubstate;
    private TextView srttext;
    private HashMap<String, List<SRTModel.subTitles>> stringListHashMap;

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DanmakuTag {
    }

    public interface OnDownloadClickListener {
        void onClick(String str);
    }

    public interface OnOpenExtraDownload {
        void download(String str);

        void onExtra(String str);
    }

    public interface OnVipClickListener {
        boolean onClick(String str);
    }

    public void hideNavKey() {
    }

    public void showNavKey() {
    }

    static /* synthetic */ int access$708(IjkPlayerView ijkPlayerView) {
        int i = ijkPlayerView.srtspeed;
        ijkPlayerView.srtspeed = i + 1;
        return i;
    }

    static /* synthetic */ int access$710(IjkPlayerView ijkPlayerView) {
        int i = ijkPlayerView.srtspeed;
        ijkPlayerView.srtspeed = i - 1;
        return i;
    }

    public IjkPlayerView(Context context) {
        this(context, null);
    }

    public IjkPlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.srtList = new ArrayList<>();
        this.mHandler = new Handler() { // from class: com.dl7.player.media.IjkPlayerView.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message.what == IjkPlayerView.MSG_UPDATE_SEEK) {
                    int i_setProgress = IjkPlayerView.this._setProgress();
                    if (!IjkPlayerView.this.mIsSeeking && IjkPlayerView.this.mIsShowBar && IjkPlayerView.this.mVideoView.isPlaying()) {
                        sendMessageDelayed(obtainMessage(IjkPlayerView.MSG_UPDATE_SEEK), 1000 - (i_setProgress % 1000));
                        return;
                    }
                    return;
                }
                if (message.what == IjkPlayerView.MSG_ENABLE_ORIENTATION) {
                    if (IjkPlayerView.this.mOrientationListener != null) {
                        IjkPlayerView.this.mOrientationListener.enable();
                    }
                } else if (message.what == IjkPlayerView.MSG_TRY_RELOAD) {
                    if (IjkPlayerView.this.mIsNetConnected) {
                        IjkPlayerView.this.reload();
                    }
                    sendMessageDelayed(obtainMessage(IjkPlayerView.MSG_TRY_RELOAD), C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                } else if (message.what == IjkPlayerView.PARSE_SRT_CN) {
                    SrtParser.showSRT(IjkPlayerView.this.mVideoView, IjkPlayerView.this.mSrtText, IjkPlayerView.this.mSrtText, IjkPlayerView.this.srtspeed, IjkPlayerView.this.srtList);
                    IjkPlayerView.this.mHandler.sendEmptyMessageDelayed(IjkPlayerView.PARSE_SRT_CN, 500L);
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
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.dl7.player.media.IjkPlayerView.3
            private long curPosition;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                IjkPlayerView.this.mIsSeeking = true;
                IjkPlayerView.this._showControlBar(DateTimeConstants.MILLIS_PER_HOUR);
                IjkPlayerView.this.mHandler.removeMessages(IjkPlayerView.MSG_UPDATE_SEEK);
                this.curPosition = IjkPlayerView.this.mVideoView.getCurrentPosition();
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                String str;
                if (z) {
                    long duration = IjkPlayerView.this.mVideoView.getDuration();
                    IjkPlayerView.this.mTargetPosition = (((long) i) * duration) / 1000;
                    int i2 = (int) ((IjkPlayerView.this.mTargetPosition - this.curPosition) / 1000);
                    if (IjkPlayerView.this.mTargetPosition > this.curPosition) {
                        str = StringUtils.generateTime(IjkPlayerView.this.mTargetPosition) + "/" + StringUtils.generateTime(duration) + "\n+" + i2 + "秒";
                    } else {
                        str = StringUtils.generateTime(IjkPlayerView.this.mTargetPosition) + "/" + StringUtils.generateTime(duration) + ShellUtil.COMMAND_LINE_END + i2 + "秒";
                    }
                    IjkPlayerView.this._setFastForward(str);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                IjkPlayerView.this._hideTouchView();
                IjkPlayerView.this.mIsSeeking = false;
                IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                ijkPlayerView.seekTo((int) ijkPlayerView.mTargetPosition);
                IjkPlayerView.this.mTargetPosition = -1L;
                IjkPlayerView.this._setProgress();
                IjkPlayerView.this._showControlBar(5000);
            }
        };
        this.mHideBarRunnable = new Runnable() { // from class: com.dl7.player.media.IjkPlayerView.4
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView.this._hideAllView(false);
            }
        };
        this.mPlayerGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.dl7.player.media.IjkPlayerView.5
            private boolean isDownTouch;
            private boolean isLandscape;
            private boolean isRecoverFromDanmaku;
            private boolean isVolume;

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                this.isDownTouch = true;
                return super.onDown(motionEvent);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (!IjkPlayerView.this.mIsForbidTouch && !IjkPlayerView.this.mIsNeverPlay) {
                    float x = motionEvent.getX();
                    float y = motionEvent.getY() - motionEvent2.getY();
                    float x2 = x - motionEvent2.getX();
                    if (this.isDownTouch) {
                        this.isLandscape = Math.abs(f) >= Math.abs(f2);
                        this.isVolume = x > ((float) IjkPlayerView.this.getResources().getDisplayMetrics().widthPixels) * 0.5f;
                        this.isDownTouch = false;
                    }
                    if (!this.isLandscape) {
                        float height = y / IjkPlayerView.this.mVideoView.getHeight();
                        if (this.isVolume) {
                            IjkPlayerView.this._onVolumeSlide(height);
                        } else {
                            IjkPlayerView.this._onBrightnessSlide(height);
                        }
                    } else {
                        IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                        ijkPlayerView._onProgressSlide((-x2) / ijkPlayerView.mVideoView.getWidth());
                    }
                }
                return super.onScroll(motionEvent, motionEvent2, f, f2);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (this.isRecoverFromDanmaku) {
                    return true;
                }
                if (IjkPlayerView.this.mIsShowCC) {
                    IjkPlayerView.this._toggleCCSelect();
                    return true;
                }
                if (IjkPlayerView.this.mIsShowTracker) {
                    IjkPlayerView.this._toggleTrackSelect();
                    return true;
                }
                if (IjkPlayerView.this.mIsShowST) {
                    IjkPlayerView.this._showAspectRatioOptions();
                    return true;
                }
                if (IjkPlayerView.this.mIsShowQuality) {
                    IjkPlayerView.this._toggleMediaQuality();
                } else {
                    IjkPlayerView.this._toggleControlBar();
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (!IjkPlayerView.this.mIsNeverPlay && !this.isRecoverFromDanmaku && !IjkPlayerView.this.mIsForbidTouch) {
                    IjkPlayerView.this._refreshHideRunnable();
                    IjkPlayerView.this._togglePlayStatus();
                }
                return true;
            }
        };
        this.mHideTouchViewRunnable = new Runnable() { // from class: com.dl7.player.media.IjkPlayerView.6
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView.this._hideTouchView();
            }
        };
        this.mPlayerTouchListener = new View.OnTouchListener() { // from class: com.dl7.player.media.IjkPlayerView.7
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
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                if (actionMasked == 0) {
                    this.mode = 1;
                    IjkPlayerView.this.mHandler.removeCallbacks(IjkPlayerView.this.mHideBarRunnable);
                } else if (actionMasked != 2) {
                    if (actionMasked != 5) {
                        if (actionMasked == 6) {
                            if (this.mode == 3) {
                                IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                                ijkPlayerView.mIsNeedRecoverScreen = ijkPlayerView.mVideoView.adjustVideoView(this.scale);
                                if (IjkPlayerView.this.mIsNeedRecoverScreen && IjkPlayerView.this.mIsShowBar) {
                                    IjkPlayerView.this.mTvRecoverScreen.setVisibility(0);
                                }
                            }
                            this.mode = 2;
                        }
                    } else if (motionEvent.getPointerCount() == 3 && IjkPlayerView.this.mIsFullscreen) {
                        IjkPlayerView.this._hideTouchView();
                        this.mode = 3;
                        MotionEventUtils.midPoint(this.midPoint, motionEvent);
                        int iCalcFingerFlag = MotionEventUtils.calcFingerFlag(motionEvent);
                        this.fingerFlag = iCalcFingerFlag;
                        this.degree = MotionEventUtils.rotation(motionEvent, iCalcFingerFlag);
                        this.oldDist = MotionEventUtils.calcSpacing(motionEvent, this.fingerFlag);
                        IjkPlayerView ijkPlayerView2 = IjkPlayerView.this;
                        ijkPlayerView2.mSaveMatrix = ijkPlayerView2.mVideoView.getVideoTransform();
                    } else {
                        this.mode = 2;
                    }
                } else if (this.mode == 3) {
                    IjkPlayerView.this.mVideoView.setVideoRotation((int) (MotionEventUtils.rotation(motionEvent, this.fingerFlag) - this.degree));
                    IjkPlayerView.this.mVideoMatrix.set(IjkPlayerView.this.mSaveMatrix);
                    this.scale = MotionEventUtils.calcSpacing(motionEvent, this.fingerFlag) / this.oldDist;
                    Matrix matrix = IjkPlayerView.this.mVideoMatrix;
                    float f = this.scale;
                    matrix.postScale(f, f, this.midPoint.x, this.midPoint.y);
                    IjkPlayerView.this.mVideoView.setVideoTransform(IjkPlayerView.this.mVideoMatrix);
                }
                if (this.mode == 1) {
                    if (IjkPlayerView.this.mGestureDetector.onTouchEvent(motionEvent)) {
                        return true;
                    }
                    if (MotionEventCompat.getActionMasked(motionEvent) == 1) {
                        IjkPlayerView.this._endGesture();
                    }
                }
                return false;
            }
        };
        this.mIsRenderingStart = false;
        this.mIsBufferingStart = false;
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.dl7.player.media.IjkPlayerView.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int i, int i2) {
                IjkPlayerView.this._switchStatus(i);
                if (IjkPlayerView.this.mOutsideInfoListener == null) {
                    return true;
                }
                IjkPlayerView.this.mOutsideInfoListener.onInfo(iMediaPlayer, i, i2);
                return true;
            }
        };
        this.mIsShowST = false;
        this.mIsShowCC = false;
        this.languages = new ArrayList();
        this.stringListHashMap = new HashMap<>();
        this.getstr = new ArrayList();
        this.mSrtSlelct = -1;
        this.mCorrentLanguage = -1;
        this.mCurSelectSRT = -1;
        this.srtspeed = 0;
        this.mIsShowExCC = false;
        this.ListTracks = new ArrayList();
        this.mIsShowTracker = false;
        this.mVideoSource = new SparseArray<>();
        this.mIsShowQuality = false;
        this.mCurSelectQuality = 0;
        this.mSkipPosition = -1;
        this.mHideSkipTipRunnable = new Runnable() { // from class: com.dl7.player.media.IjkPlayerView.22
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView.this._hideSkipTip();
            }
        };
        this.mVideoStatus = 501;
        this.mDanmakuTag = 701;
        this.mIsEnableDanmaku = false;
        this.mDanmakuTextColor = -1;
        this.mDanmakuTextSize = -1.0f;
        this.mDanmakuType = 1;
        this.mBasicOptionsWidth = -1;
        this.mMoreOptionsWidth = -1;
        this.mDanmakuTargetPosition = -1L;
        this.mIsScreenLocked = false;
        this.mInsideDialogClickListener = new ShareDialog.OnDialogClickListener() { // from class: com.dl7.player.media.IjkPlayerView.28
            @Override // com.dl7.player.widgets.ShareDialog.OnDialogClickListener
            public void onShare(Bitmap bitmap, Uri uri) {
                if (IjkPlayerView.this.mDialogClickListener != null) {
                    IjkPlayerView.this.mDialogClickListener.onShare(bitmap, IjkPlayerView.this.mVideoView.getUri());
                }
                File file = new File(IjkPlayerView.this.mSaveDir, System.currentTimeMillis() + ".jpg");
                try {
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    Toast.makeText(IjkPlayerView.this.mAttachActivity, "保存成功，路径为:" + file.getAbsolutePath(), 0).show();
                } catch (IOException unused) {
                    Toast.makeText(IjkPlayerView.this.mAttachActivity, "保存本地失败", 0).show();
                }
            }
        };
        this.mDialogDismissListener = new ShareDialog.OnDialogDismissListener() { // from class: com.dl7.player.media.IjkPlayerView.29
            @Override // com.dl7.player.widgets.ShareDialog.OnDialogDismissListener
            public void onDismiss() {
                IjkPlayerView.this.recoverFromEditVideo();
            }
        };
        _initView(context);
    }

    private void _initView(Context context) {
        if (context instanceof AppCompatActivity) {
            this.mAttachActivity = (AppCompatActivity) context;
            View.inflate(context, R.layout.layout_player_view, this);
            this.mVideoView = (IjkVideoView) findViewById(R.id.video_view);
            this.mPlayerThumb = (ImageView) findViewById(R.id.iv_thumb);
            this.mLoadingView = (ProgressBar) findViewById(R.id.pb_loading);
            this.mTvVolume = (TextView) findViewById(R.id.tv_volume);
            this.mTvBrightness = (TextView) findViewById(R.id.tv_brightness);
            this.mTvFastForward = (TextView) findViewById(R.id.tv_fast_forward);
            this.mFlTouchLayout = (FrameLayout) findViewById(R.id.fl_touch_layout);
            this.mIvBack = (ImageView) findViewById(R.id.iv_back);
            this.mTvTitle = (MarqueeTextView) findViewById(R.id.tv_title);
            this.mFullscreenTopBar = (LinearLayout) findViewById(R.id.fullscreen_top_bar);
            this.mIvBackWindow = (ImageView) findViewById(R.id.iv_back_window);
            this.mWindowTopBar = (FrameLayout) findViewById(R.id.window_top_bar);
            this.mIvPlay = (ImageView) findViewById(R.id.iv_play);
            this.mTvCurTime = (TextView) findViewById(R.id.tv_cur_time);
            this.mPlayerSeek = (SeekBar) findViewById(R.id.player_seek);
            this.mTvEndTime = (TextView) findViewById(R.id.tv_end_time);
            this.mIvFullscreen = (ImageView) findViewById(R.id.iv_fullscreen);
            this.mLlBottomBar = (LinearLayout) findViewById(R.id.ll_bottom_bar);
            this.mFlVideoBox = (FrameLayout) findViewById(R.id.fl_video_box);
            this.mIvPlayerLock = (ImageView) findViewById(R.id.iv_player_lock);
            MediaRouteButton mediaRouteButton = (MediaRouteButton) findViewById(R.id.media_route_button);
            this.mediaRouteButton = mediaRouteButton;
            mediaRouteButton.setRemoteIndicatorDrawable(this.mAttachActivity.getResources().getDrawable(androidx.mediarouter.R.drawable.mr_button_light));
            this.mIvPlayCircle = (ImageView) findViewById(R.id.iv_play_circle);
            this.mTvRecoverScreen = (TextView) findViewById(R.id.tv_recover_screen);
            this.mTvReload = (TextView) findViewById(R.id.tv_reload);
            this.mFlReload = findViewById(R.id.fl_reload_layout);
            this.mSrtText = (TextView) findViewById(R.id.video_text);
            _initSetting();
            _initMediaQuality();
            _initVideoSkip();
            _initReceiver();
            _initCCSlect();
            _initExrtSRT();
            _initMediaTrack();
            this.mIvPlay.setOnClickListener(this);
            this.mIvBack.setOnClickListener(this);
            this.mIvFullscreen.setOnClickListener(this);
            this.mIvBackWindow.setOnClickListener(this);
            this.mIvPlayerLock.setOnClickListener(this);
            this.mIvPlayCircle.setOnClickListener(this);
            this.mTvRecoverScreen.setOnClickListener(this);
            this.mTvSettings.setOnClickListener(this);
            this.mTvReload.setOnClickListener(this);
            return;
        }
        throw new IllegalArgumentException("Context must be AppCompatActivity");
    }

    public MediaRouteButton getMediaButton() {
        return this.mediaRouteButton;
    }

    private void _initMediaPlayer() {
        AudioManager audioManager = (AudioManager) this.mAttachActivity.getSystemService("audio");
        this.mAudioManager = audioManager;
        this.mMaxVolume = audioManager.getStreamMaxVolume(3);
        this.mAttachActivity.getWindow().getDecorView().setSystemUiVisibility(5894);
        this.mAttachActivity.getWindow().addFlags(1024);
        this.mPlayerSeek.setMax(1000);
        this.mPlayerSeek.setOnSeekBarChangeListener(this.mSeekListener);
        this.mVideoView.setOnInfoListener(this.mInfoListener);
        this.mGestureDetector = new GestureDetector(this.mAttachActivity, this.mPlayerGestureListener);
        this.mFlVideoBox.setClickable(true);
        this.mFlVideoBox.setOnTouchListener(this.mPlayerTouchListener);
        OrientationEventListener orientationEventListener = new OrientationEventListener(this.mAttachActivity) { // from class: com.dl7.player.media.IjkPlayerView.2
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int i) {
                IjkPlayerView.this._handleOrientation(i);
            }
        };
        this.mOrientationListener = orientationEventListener;
        if (this.mIsForbidOrientation) {
            orientationEventListener.disable();
        }
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.mInitHeight == 0) {
            this.mInitHeight = getHeight();
            this.mWidthPixels = getResources().getDisplayMetrics().widthPixels;
        }
    }

    public void onResume() {
        this.mVideoView.setRender(2);
        this.mIsScreenLocked = false;
        this.mVideoView.resume();
        if (!this.mIsForbidTouch && !this.mIsForbidOrientation) {
            this.mOrientationListener.enable();
        }
        int i = this.mCurPosition;
        if (i != -1) {
            seekTo(i);
            this.mCurPosition = -1;
        }
    }

    public void onPause() {
        this.mCurPosition = this.mVideoView.getCurrentPosition();
        this.mVideoView.pause();
        this.mIvPlay.setSelected(false);
        OrientationEventListener orientationEventListener = this.mOrientationListener;
        if (orientationEventListener != null) {
            orientationEventListener.disable();
        }
        _pauseDanmaku();
    }

    public int onStop() {
        return this.mVideoView.getCurrentPosition();
    }

    public int onDestroy() {
        int currentPosition = this.mVideoView.getCurrentPosition();
        this.mVideoView.destroy();
        IDanmakuView iDanmakuView = this.mDanmakuView;
        if (iDanmakuView != null) {
            iDanmakuView.release();
            this.mDanmakuView = null;
        }
        ShareDialog shareDialog = this.mShareDialog;
        if (shareDialog != null) {
            shareDialog.dismiss();
            this.mShareDialog = null;
        }
        this.mHandler.removeMessages(MSG_TRY_RELOAD);
        this.mHandler.removeMessages(MSG_UPDATE_SEEK);
        this.mHandler.removeMessages(PARSE_SRT_CN);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mAttachActivity.unregisterReceiver(this.mBatteryReceiver);
        this.mAttachActivity.unregisterReceiver(this.mScreenReceiver);
        this.mAttachActivity.unregisterReceiver(this.mNetReceiver);
        this.mAttachActivity.getWindow().clearFlags(128);
        return currentPosition;
    }

    public boolean handleVolumeKey(int i) {
        if (i == 24) {
            _setVolume(true);
            return true;
        }
        if (i != 25) {
            return false;
        }
        _setVolume(false);
        return true;
    }

    public boolean onBackPressed() {
        Log.d("VIEWDOPLAY", "mIsShowCC  " + this.mIsShowCC + "  mIsShowQuality  " + this.mIsShowQuality + "  mVideoStatus  " + this.mVideoStatus + "  mIsAlwaysFullScreen  " + this.mIsAlwaysFullScreen + "  mIsFullscreen  " + this.mIsFullscreen);
        if (this.mIsShowCC) {
            _toggleCCSelect();
            return true;
        }
        if (this.mIsShowQuality) {
            _toggleMediaQuality();
            return true;
        }
        if (this.mIsShowExCC) {
            _toggleExtraSelect();
            return true;
        }
        if (this.mIsShowTracker) {
            _toggleTrackSelect();
            return true;
        }
        if (this.mIsShowST) {
            _showAspectRatioOptions();
            return true;
        }
        _exit();
        return false;
    }

    public IjkPlayerView init() {
        _initMediaPlayer();
        return this;
    }

    public IjkPlayerView switchVideoPath(String str) {
        return switchVideoPath(Uri.parse(str));
    }

    public IjkPlayerView switchVideoPath(Uri uri) {
        List<MediaQualityInfo> list = this.mQualityData;
        if (list != null) {
            list.clear();
            this.mQualityData = null;
        }
        reset();
        return setVideoPath(uri);
    }

    public IjkPlayerView setVideoPath(String str) {
        if (str == null || TextUtils.isEmpty(str)) {
            return null;
        }
        return setVideoPath(Uri.parse(str));
    }

    public IjkPlayerView setVideoPath(Uri uri) {
        this.mVideoView.setVideoURI(uri);
        int i = this.mCurPosition;
        if (i != -1) {
            seekTo(i);
            this.mCurPosition = -1;
        } else {
            seekTo(0);
        }
        return this;
    }

    public IjkPlayerView setTitle(String str) {
        this.mTvTitle.setText(str);
        return this;
    }

    public IjkPlayerView alwaysFullScreen() {
        this.mIsAlwaysFullScreen = true;
        _setFullScreen(true);
        this.mIvFullscreen.setVisibility(8);
        this.mAttachActivity.setRequestedOrientation(0);
        _setUiLayoutFullscreen();
        return this;
    }

    public void start() {
        if (this.mIsPlayComplete) {
            IDanmakuView iDanmakuView = this.mDanmakuView;
            if (iDanmakuView != null && iDanmakuView.isPrepared()) {
                this.mDanmakuView.seekTo(0L);
                this.mDanmakuView.pause();
            }
            this.mIsPlayComplete = false;
        }
        if (!this.mVideoView.isPlaying()) {
            this.mIvPlay.setSelected(true);
            this.mVideoView.start();
            this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        }
        if (this.mIsNeverPlay) {
            this.mIsNeverPlay = false;
            this.mIvPlayCircle.setVisibility(8);
            this.mLoadingView.setVisibility(0);
            this.mIsShowBar = false;
            _loadDanmaku();
        }
        this.mAttachActivity.getWindow().addFlags(128);
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
            this.mVideoView.setRender(2);
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
        if (this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
        }
        _pauseDanmaku();
        this.mAttachActivity.getWindow().clearFlags(128);
    }

    public void seekTo(int i) {
        this.mVideoView.seekTo(i);
        this.mDanmakuTargetPosition = i;
    }

    public void stop() {
        pause();
        this.mVideoView.stopPlayback();
    }

    public void reset() {
        if (this.mIsEnableDanmaku && this.mDanmakuView != null) {
            _toggleDanmakuView(false);
            this.mDanmakuView.release();
            this.mDanmakuView = null;
            this.mIsEnableDanmaku = false;
        }
        this.mIsNeverPlay = true;
        this.mCurPosition = 0;
        stop();
        this.mVideoView.setRender(2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideAllView(boolean z) {
        this.mFlTouchLayout.setVisibility(8);
        this.mFullscreenTopBar.setVisibility(8);
        this.mWindowTopBar.setVisibility(8);
        this.mLlBottomBar.setVisibility(8);
        if (!z) {
            this.mIvPlayerLock.setVisibility(8);
            this.mIsShowBar = false;
        }
        if (this.mIsEnableDanmaku) {
            this.mDanmakuPlayerSeek.setVisibility(8);
        }
        if (this.mIsNeedRecoverScreen) {
            this.mTvRecoverScreen.setVisibility(8);
        }
    }

    private void _setControlBarVisible(boolean z) {
        if (this.mIsNeverPlay) {
            this.mIvPlayCircle.setVisibility(z ? 0 : 8);
            return;
        }
        if (this.mIsForbidTouch) {
            this.mIvPlayerLock.setVisibility(z ? 0 : 8);
            return;
        }
        this.mLlBottomBar.setVisibility(z ? 0 : 8);
        if (this.mIsFullscreen) {
            this.mTvSystemTime.setText(StringUtils.getCurFormatTime());
            this.mFullscreenTopBar.setVisibility(z ? 0 : 8);
            this.mWindowTopBar.setVisibility(8);
            this.mIvPlayerLock.setVisibility(z ? 0 : 8);
            if (this.mIsEnableDanmaku) {
                this.mDanmakuPlayerSeek.setVisibility(z ? 0 : 8);
            }
            if (this.mIsNeedRecoverScreen) {
                this.mTvRecoverScreen.setVisibility(z ? 0 : 8);
            }
        } else {
            this.mFullscreenTopBar.setVisibility(z ? 0 : 8);
            this.mIvPlayerLock.setVisibility(8);
            if (this.mIsEnableDanmaku) {
                this.mDanmakuPlayerSeek.setVisibility(8);
            }
            if (this.mIsNeedRecoverScreen) {
                this.mTvRecoverScreen.setVisibility(8);
            }
        }
        if (z) {
            showNavKey();
        } else {
            hideNavKey();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleControlBar() {
        boolean z = !this.mIsShowBar;
        this.mIsShowBar = z;
        _setControlBarVisible(z);
        if (this.mIsShowBar) {
            this.mHandler.postDelayed(this.mHideBarRunnable, 5000L);
            this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _showControlBar(int i) {
        if (!this.mIsShowBar) {
            _setProgress();
            this.mIsShowBar = true;
        }
        _setControlBarVisible(true);
        this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        this.mHandler.removeCallbacks(this.mHideBarRunnable);
        if (i != 0) {
            this.mHandler.postDelayed(this.mHideBarRunnable, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _togglePlayStatus() {
        if (this.mVideoView.isPlaying()) {
            pause();
        } else {
            start();
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
            return;
        }
        if (!this.mIsForbidOrientation) {
            this.mOrientationListener.enable();
        }
        this.mFullscreenTopBar.setVisibility(0);
        this.mLlBottomBar.setVisibility(0);
        if (this.mIsEnableDanmaku) {
            this.mDanmakuPlayerSeek.setVisibility(0);
        }
        if (this.mIsNeedRecoverScreen) {
            this.mTvRecoverScreen.setVisibility(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleMediaQuality() {
        if (this.mFlMediaQuality.getVisibility() == 8) {
            this.mFlMediaQuality.setVisibility(0);
        }
        if (this.mIsShowQuality) {
            ViewCompat.animate(this.mFlMediaQuality).translationX(this.mFlMediaQuality.getWidth()).setDuration(300L);
            this.mFlMediaQuality.setVisibility(8);
            this.mIsShowQuality = false;
        } else {
            ViewCompat.animate(this.mFlMediaQuality).translationX(0.0f).setDuration(300L);
            this.mFlMediaQuality.setVisibility(0);
            this.mIsShowQuality = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleCCSelect() {
        if (this.mFlCCSlect.getVisibility() == 8) {
            this.mFlCCSlect.setVisibility(0);
        }
        if (this.mIsShowCC) {
            ViewCompat.animate(this.mFlCCSlect).translationX(this.mFlCCSlect.getWidth()).setDuration(300L);
            this.mFlCCSlect.setVisibility(8);
            this.mIsShowCC = false;
        } else {
            ViewCompat.animate(this.mFlCCSlect).translationX(0.0f).setDuration(300L);
            this.mFlCCSlect.setVisibility(0);
            this.mIsShowCC = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleExtraSelect() {
        if (this.mFlExtraCC.getVisibility() == 8) {
            this.mFlExtraCC.setVisibility(0);
        }
        if (this.mIsShowCC) {
            ViewCompat.animate(this.mFlCCSlect).translationX(this.mFlCCSlect.getWidth()).setDuration(300L);
            this.mFlCCSlect.setVisibility(8);
            this.mIsShowCC = false;
        }
        if (this.mIsShowExCC) {
            ViewCompat.animate(this.mFlExtraCC).translationX(this.mFlExtraCC.getWidth()).setDuration(300L);
            this.mFlExtraCC.setVisibility(8);
            this.mIsShowExCC = false;
        } else {
            ViewCompat.animate(this.mFlExtraCC).translationX(0.0f).setDuration(300L);
            this.mFlExtraCC.setVisibility(0);
            this.mIsShowExCC = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleTrackSelect() {
        if (this.mFlTrack.getVisibility() == 8) {
            this.mFlTrack.setVisibility(0);
        }
        if (this.mIsShowTracker) {
            ViewCompat.animate(this.mFlTrack).translationX(this.mFlTrack.getWidth()).setDuration(300L);
            this.mFlTrack.setVisibility(8);
            this.mIsShowTracker = false;
        } else {
            ViewCompat.animate(this.mFlTrack).translationX(0.0f).setDuration(300L);
            this.mFlTrack.setVisibility(0);
            this.mIsShowTracker = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _showAspectRatioOptions() {
        if (this.mFlSettings.getVisibility() == 8) {
            this.mFlSettings.setVisibility(0);
        }
        if (this.mIsShowST) {
            ViewCompat.animate(this.mFlSettings).translationX(this.mFlSettings.getWidth()).setDuration(300L);
            this.mFlSettings.setVisibility(8);
            this.mIsShowST = false;
        } else {
            ViewCompat.animate(this.mFlSettings).translationX(0.0f).setDuration(300L);
            this.mFlSettings.setVisibility(0);
            this.mIsShowST = true;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        _refreshHideRunnable();
        int id = view.getId();
        if (id == R.id.iv_back) {
            _exit();
            return;
        }
        if (id == R.id.iv_back_window) {
            this.mAttachActivity.finish();
            return;
        }
        if (id == R.id.iv_play || id == R.id.iv_play_circle) {
            _togglePlayStatus();
            return;
        }
        if (id == R.id.iv_fullscreen) {
            _toggleFullScreen();
            return;
        }
        if (id == R.id.iv_player_lock) {
            _togglePlayerLock();
            return;
        }
        if (id == R.id.iv_media_quality) {
            if (this.mIsShowQuality) {
                return;
            }
            _toggleMediaQuality();
            return;
        }
        if (id == R.id.iv_cancel_skip) {
            this.mHandler.removeCallbacks(this.mHideSkipTipRunnable);
            _hideSkipTip();
            return;
        }
        if (id == R.id.tv_do_skip) {
            this.mLoadingView.setVisibility(0);
            seekTo(this.mSkipPosition);
            this.mHandler.removeCallbacks(this.mHideSkipTipRunnable);
            _hideSkipTip();
            _setProgress();
            return;
        }
        if (id == R.id.iv_danmaku_control) {
            _toggleDanmakuShow();
            return;
        }
        if (id == R.id.tv_open_edit_danmaku) {
            OnDanmakuListener onDanmakuListener = this.mDanmakuListener;
            if (onDanmakuListener == null || onDanmakuListener.isValid()) {
                editVideo();
                this.mEditDanmakuLayout.setVisibility(0);
                SoftInputUtils.setEditFocusable(this.mAttachActivity, this.mEtDanmakuContent);
                return;
            }
            return;
        }
        if (id == R.id.iv_cancel_send) {
            recoverFromEditVideo();
            return;
        }
        if (id == R.id.iv_do_send) {
            recoverFromEditVideo();
            sendDanmaku(this.mEtDanmakuContent.getText().toString(), false);
            this.mEtDanmakuContent.setText("");
            return;
        }
        if (id == R.id.input_options_more) {
            _toggleMoreColorOptions();
            return;
        }
        if (id == R.id.iv_screenshot) {
            _doScreenshot();
            return;
        }
        if (id == R.id.tv_recover_screen) {
            this.mVideoView.resetVideoView(true);
            this.mIsNeedRecoverScreen = false;
            this.mTvRecoverScreen.setVisibility(8);
            return;
        }
        if (id == R.id.tv_settings) {
            if (this.mIsShowST) {
                return;
            }
            _showAspectRatioOptions();
            return;
        }
        if (id == R.id.tv_reload) {
            reload();
            return;
        }
        if (id == R.id.tv_srtcelsect) {
            if (this.mIsShowCC) {
                return;
            }
            _toggleCCSelect();
        } else {
            if (id == R.id.iv_cc_extracc) {
                if (this.mIsShowExCC) {
                    return;
                }
                int i = this.mCorrentLanguage;
                if (i != -1 && i < this.stringListHashMap.size()) {
                    this.onOpenExtraDownload.onExtra(this.languages.get(this.mCorrentLanguage).language);
                    return;
                } else {
                    this.onOpenExtraDownload.onExtra(Locale.getDefault().getLanguage());
                    return;
                }
            }
            if (id != R.id.tv_meidaTrack || this.mIsShowTracker) {
                return;
            }
            _toggleTrackSelect();
        }
    }

    public IjkPlayerView enableOrientation() {
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

    private void _setFullScreen(boolean z) {
        this.mIsFullscreen = z;
        _toggleDanmakuView(z);
        _handleActionBar(z);
        this.mIvFullscreen.setSelected(z);
        this.mHandler.post(this.mHideBarRunnable);
        this.mIvMediaQuality.setVisibility(0);
        this.mLlBottomBar.setBackgroundResource(z ? R.color.bg_video_view : android.R.color.transparent);
        if (this.mIsShowQuality && !z) {
            _toggleMediaQuality();
        }
        if (this.mIsShowCC && !z) {
            _toggleCCSelect();
        }
        if (this.mIsShowTracker && !z) {
            _toggleTrackSelect();
        }
        if (this.mIsShowST && !z) {
            _showAspectRatioOptions();
        }
        if (this.mIsNeedRecoverScreen) {
            if (z) {
                this.mVideoView.adjustVideoView(1.0f);
                this.mTvRecoverScreen.setVisibility(this.mIsShowBar ? 0 : 8);
            } else {
                this.mVideoView.resetVideoView(false);
                this.mTvRecoverScreen.setVisibility(8);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _handleOrientation(int i) {
        if (this.mIsNeverPlay) {
            return;
        }
        if (this.mIsFullscreen && !this.mIsAlwaysFullScreen) {
            if ((i < 0 || i > 30) && i < 330) {
                return;
            }
            this.mAttachActivity.setRequestedOrientation(1);
            return;
        }
        if (i >= 60 && i <= 120) {
            this.mAttachActivity.setRequestedOrientation(8);
        } else {
            if (i < 240 || i > 300) {
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

    private void _handleActionBar(boolean z) {
        ActionBar supportActionBar = this.mAttachActivity.getSupportActionBar();
        if (supportActionBar != null) {
            if (z) {
                supportActionBar.hide();
            } else {
                supportActionBar.show();
            }
        }
    }

    private void _changeHeight(boolean z) {
        if (this.mIsAlwaysFullScreen) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (z) {
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

    public void configurationChanged(Configuration configuration) {
        _refreshOrientationEnable();
        if (configuration.orientation == 2) {
            View decorView = this.mAttachActivity.getWindow().getDecorView();
            this.mScreenUiVisibility = decorView.getSystemUiVisibility();
            decorView.setSystemUiVisibility(5894);
            _setFullScreen(true);
            this.mAttachActivity.getWindow().addFlags(1024);
            return;
        }
        if (configuration.orientation == 1) {
            this.mAttachActivity.getWindow().getDecorView().setSystemUiVisibility(5894);
            this.mAttachActivity.getWindow().addFlags(1024);
            _setFullScreen(false);
        }
    }

    private void _exit() {
        this.mAttachActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int _setProgress() {
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView == null || this.mIsSeeking) {
            return 0;
        }
        int iMax = Math.max(ijkVideoView.getCurrentPosition(), this.mInterruptPosition);
        int duration = this.mVideoView.getDuration();
        if (duration > 0) {
            int i = (int) ((((long) iMax) * 1000) / ((long) duration));
            this.mPlayerSeek.setProgress(i);
            if (this.mIsEnableDanmaku) {
                this.mDanmakuPlayerSeek.setProgress(i);
            }
        }
        int bufferPercentage = this.mVideoView.getBufferPercentage() * 10;
        this.mPlayerSeek.setSecondaryProgress(bufferPercentage);
        if (this.mIsEnableDanmaku) {
            this.mDanmakuPlayerSeek.setSecondaryProgress(bufferPercentage);
        }
        this.mTvCurTime.setText(StringUtils.generateTime(iMax));
        this.mTvEndTime.setText(StringUtils.generateTime(duration));
        return iMax;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _setFastForward(String str) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvFastForward.getVisibility() == 8) {
            this.mTvFastForward.setVisibility(0);
        }
        this.mTvFastForward.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideTouchView() {
        if (this.mFlTouchLayout.getVisibility() == 0) {
            this.mTvFastForward.setVisibility(8);
            this.mTvVolume.setVisibility(8);
            this.mTvBrightness.setVisibility(8);
            this.mFlTouchLayout.setVisibility(8);
            hideNavKey();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onProgressSlide(float f) {
        String str;
        int currentPosition = this.mVideoView.getCurrentPosition();
        long duration = this.mVideoView.getDuration();
        long j = currentPosition;
        long jMin = ((long) (Math.min(SilenceSkippingAudioProcessor.DEFAULT_MINIMUM_SILENCE_DURATION_US, duration / 2) * f)) + j;
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

    private void _setVolumeInfo(int i) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvVolume.getVisibility() == 8) {
            this.mTvVolume.setVisibility(0);
        }
        this.mTvVolume.setText(((i * 100) / this.mMaxVolume) + "%");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onVolumeSlide(float f) {
        if (this.mCurVolume == -1) {
            int streamVolume = this.mAudioManager.getStreamVolume(3);
            this.mCurVolume = streamVolume;
            if (streamVolume < 0) {
                this.mCurVolume = 0;
            }
        }
        int i = this.mMaxVolume;
        int i2 = ((int) (f * i)) + this.mCurVolume;
        if (i2 <= i) {
            i = i2 < 0 ? 0 : i2;
        }
        this.mAudioManager.setStreamVolume(3, i, 0);
        _setVolumeInfo(i);
    }

    private void _setVolume(boolean z) {
        int i;
        int streamVolume = this.mAudioManager.getStreamVolume(3);
        if (z) {
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

    private void _setBrightnessInfo(float f) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvBrightness.getVisibility() == 8) {
            this.mTvBrightness.setVisibility(0);
        }
        this.mTvBrightness.setText(Math.ceil(f * 100.0f) + "%");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _onBrightnessSlide(float f) {
        if (this.mCurBrightness < 0.0f) {
            float f2 = this.mAttachActivity.getWindow().getAttributes().screenBrightness;
            this.mCurBrightness = f2;
            if (f2 < 0.0f) {
                this.mCurBrightness = 0.5f;
            } else if (f2 < 0.01f) {
                this.mCurBrightness = 0.01f;
            }
        }
        WindowManager.LayoutParams attributes = this.mAttachActivity.getWindow().getAttributes();
        attributes.screenBrightness = this.mCurBrightness + f;
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
            if (this.mIsEnableDanmaku) {
                this.mDanmakuPlayerSeek.setProgress((int) ((this.mTargetPosition * 1000) / ((long) this.mVideoView.getDuration())));
            }
            this.mTargetPosition = -1L;
        }
        _hideTouchView();
        _refreshHideRunnable();
        this.mCurVolume = -1;
        this.mCurBrightness = -1.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _switchStatus(int i) {
        Log.i("IjkPlayerView", "status " + i);
        if (i == 3) {
            this.mIsRenderingStart = true;
        } else {
            if (i == 331) {
                this.mInterruptPosition = Math.max(this.mVideoView.getInterruptPosition(), this.mInterruptPosition);
                pause();
                if (this.mVideoView.getDuration() == -1 && !this.mIsReady) {
                    this.mLoadingView.setVisibility(8);
                    this.mPlayerThumb.setVisibility(8);
                    this.mIvPlayCircle.setVisibility(8);
                    this.mFlReload.setVisibility(0);
                    return;
                }
                this.mLoadingView.setVisibility(0);
                this.mHandler.sendEmptyMessage(MSG_TRY_RELOAD);
                return;
            }
            if (i == 336) {
                pause();
                if (this.mVideoView.getDuration() == -1 || this.mVideoView.getInterruptPosition() + 1000 < this.mVideoView.getDuration()) {
                    this.mInterruptPosition = Math.max(this.mVideoView.getInterruptPosition(), this.mInterruptPosition);
                    return;
                }
                this.mIsPlayComplete = true;
                IMediaPlayer.OnCompletionListener onCompletionListener = this.mCompletionListener;
                if (onCompletionListener != null) {
                    onCompletionListener.onCompletion(this.mVideoView.getMediaPlayer());
                    return;
                }
                return;
            }
            if (i == 333) {
                this.mIsReady = true;
                return;
            }
            if (i == 334) {
                this.mHandler.removeMessages(MSG_TRY_RELOAD);
                if (!this.mIsRenderingStart || this.mIsBufferingStart || this.mVideoView.getCurrentPosition() <= 0) {
                    return;
                }
                _resumeDanmaku();
                return;
            }
            if (i == 701) {
                this.mIsBufferingStart = true;
                _pauseDanmaku();
                if (!this.mIsNeverPlay) {
                    this.mLoadingView.setVisibility(0);
                }
                this.mHandler.removeMessages(MSG_TRY_RELOAD);
                return;
            }
            if (i != 702) {
                return;
            }
        }
        this.mIsBufferingStart = false;
        this.mLoadingView.setVisibility(8);
        this.mPlayerThumb.setVisibility(8);
        this.mHandler.removeMessages(MSG_UPDATE_SEEK);
        this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        if (this.mSkipPosition != -1) {
            _showSkipTip();
        }
        if (this.mVideoView.isPlaying() && this.mIsNetConnected) {
            this.mInterruptPosition = 0;
            _resumeDanmaku();
            if (this.mIvPlay.isSelected()) {
                return;
            }
            this.mVideoView.start();
            this.mIvPlay.setSelected(true);
        }
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener onPreparedListener) {
        this.mVideoView.setOnPreparedListener(onPreparedListener);
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener onCompletionListener) {
        this.mCompletionListener = onCompletionListener;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener onErrorListener) {
        this.mVideoView.setOnErrorListener(onErrorListener);
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener onInfoListener) {
        this.mOutsideInfoListener = onInfoListener;
    }

    public IjkPlayerView setDanmakuListener(OnDanmakuListener onDanmakuListener) {
        this.mDanmakuListener = onDanmakuListener;
        return this;
    }

    private void _initSetting() {
        this.mTvSettings = (ImageView) findViewById(R.id.tv_settings);
        this.mFlSettings = (RelativeLayout) findViewById(R.id.fl_setting_controller);
        this.mAspectRatioOptions = (RadioGroup) findViewById(R.id.aspect_ratio_group);
        this.mAspectOptionsHeight = getResources().getDimensionPixelSize(R.dimen.aspect_btn_size) * 4;
        this.mAspectRatioOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dl7.player.media.IjkPlayerView.9
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.aspect_fit_parent) {
                    IjkPlayerView.this.mVideoView.setAspectRatio(0);
                } else if (i == R.id.aspect_fit_screen) {
                    IjkPlayerView.this.mVideoView.setAspectRatio(1);
                } else if (i == R.id.aspect_16_and_9) {
                    IjkPlayerView.this.mVideoView.setAspectRatio(4);
                }
            }
        });
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.speed_ratio_group);
        this.mSpeedRatioOptions = radioGroup;
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dl7.player.media.IjkPlayerView.10
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup2, int i) {
                if (i == R.id.speed_1) {
                    IjkPlayerView.this.mVideoView.setSpeed(1.0f);
                    return;
                }
                if (i == R.id.speed_1_25) {
                    IjkPlayerView.this.mVideoView.setSpeed(1.25f);
                    return;
                }
                if (i == R.id.speed_1_5) {
                    IjkPlayerView.this.mVideoView.setSpeed(1.5f);
                } else if (i == R.id.speed_2) {
                    IjkPlayerView.this.mVideoView.setSpeed(2.0f);
                } else if (i == R.id.speed_0_5) {
                    IjkPlayerView.this.mVideoView.setSpeed(0.5f);
                }
            }
        });
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.medcode_ratio_group);
        this.mDecode = radioGroup2;
        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dl7.player.media.IjkPlayerView.11
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup3, int i) {
                if (i == R.id.medcode_open) {
                    IjkPlayerView.this.mVideoView.setDecode(true);
                    if (IjkPlayerView.this.mVideoView.isPlaying()) {
                        IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                        ijkPlayerView.mCurPosition = ijkPlayerView.mVideoView.getCurrentPosition();
                        IjkPlayerView.this.mVideoView.release(false);
                    }
                    IjkPlayerView.this.mVideoView.setRender(2);
                    IjkPlayerView ijkPlayerView2 = IjkPlayerView.this;
                    ijkPlayerView2.setVideoPath((String) ijkPlayerView2.mVideoSource.get(IjkPlayerView.this.mCurSelectQuality));
                    IjkPlayerView.this.start();
                    return;
                }
                if (i == R.id.medcode_close) {
                    IjkPlayerView.this.mVideoView.setDecode(false);
                    if (IjkPlayerView.this.mVideoView.isPlaying()) {
                        IjkPlayerView ijkPlayerView3 = IjkPlayerView.this;
                        ijkPlayerView3.mCurPosition = ijkPlayerView3.mVideoView.getCurrentPosition();
                        IjkPlayerView.this.mVideoView.release(false);
                    }
                    IjkPlayerView.this.mVideoView.setRender(2);
                    IjkPlayerView ijkPlayerView4 = IjkPlayerView.this;
                    ijkPlayerView4.setVideoPath((String) ijkPlayerView4.mVideoSource.get(IjkPlayerView.this.mCurSelectQuality));
                    IjkPlayerView.this.start();
                }
            }
        });
    }

    private void _initCCSlect() {
        this.mFlCCSlect = findViewById(R.id.fl_cc_select);
        this.srtsubstate = (ImageView) findViewById(R.id.ll_srt_subtract);
        this.srtadd = (ImageView) findViewById(R.id.ll_srt_add);
        ImageView imageView = (ImageView) findViewById(R.id.iv_cc_extracc);
        this.ll_srt_extr = imageView;
        imageView.setOnClickListener(this);
        this.srttext = (TextView) findViewById(R.id.ll_srt_speed);
        this.mSrtController = (RelativeLayout) findViewById(R.id.ll_bottom_srt);
        this.ll_srt_average = (TextView) findViewById(R.id.ll_srt_average);
        ImageView imageView2 = (ImageView) findViewById(R.id.ll_srt_close);
        this.ll_srt_close = imageView2;
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.dl7.player.media.IjkPlayerView.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                ijkPlayerView.removeSRTlist(ijkPlayerView.mSrtSlelct);
                IjkPlayerView.this.mHandler.removeMessages(IjkPlayerView.PARSE_SRT_CN);
                IjkPlayerView.this.mSrtText.setText("");
                IjkPlayerView.this.mSrtController.setVisibility(8);
            }
        });
        this.srttext.setText(this.srtspeed + "");
        this.srtadd.setOnClickListener(new View.OnClickListener() { // from class: com.dl7.player.media.IjkPlayerView.13
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IjkPlayerView.access$708(IjkPlayerView.this);
                IjkPlayerView.this._showControlBar(DateTimeConstants.MILLIS_PER_HOUR);
                IjkPlayerView.this.mHandler.removeMessages(IjkPlayerView.MSG_UPDATE_SEEK);
                IjkPlayerView.this.srttext.setText(IjkPlayerView.this.srtspeed + "");
            }
        });
        this.srtsubstate.setOnClickListener(new View.OnClickListener() { // from class: com.dl7.player.media.IjkPlayerView.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IjkPlayerView.access$710(IjkPlayerView.this);
                IjkPlayerView.this._showControlBar(DateTimeConstants.MILLIS_PER_HOUR);
                IjkPlayerView.this.mHandler.removeMessages(IjkPlayerView.MSG_UPDATE_SEEK);
                IjkPlayerView.this.srttext.setText(IjkPlayerView.this.srtspeed + "");
            }
        });
        ImageView imageView3 = (ImageView) findViewById(R.id.tv_srtcelsect);
        this.mSrtSelect = imageView3;
        imageView3.setOnClickListener(this);
        this.mCCCountrySelect = (ListView) findViewById(R.id.lv_cc_country);
        this.mCCListSelect = (ListView) findViewById(R.id.lv_cc_quality);
        this.mCCAdapter = new AdapterMediaQuality(this.mAttachActivity);
        AdapterSRTLanguage adapterSRTLanguage = new AdapterSRTLanguage(this.mAttachActivity);
        this.mCCLanguageAdapter = adapterSRTLanguage;
        this.mCCCountrySelect.setAdapter((ListAdapter) adapterSRTLanguage);
        this.mCCListSelect.setAdapter((ListAdapter) this.mCCAdapter);
        this.mCCCountrySelect.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.dl7.player.media.IjkPlayerView.15
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                IjkPlayerView.this.getstr.clear();
                IjkPlayerView.this.mCorrentLanguage = i;
                IjkPlayerView.this.getstr.addAll((Collection) IjkPlayerView.this.stringListHashMap.get(((languageModel) IjkPlayerView.this.languages.get(i)).language));
                for (int i2 = 0; i2 < IjkPlayerView.this.getstr.size(); i2++) {
                    IjkPlayerView.this.getstr.get(i2).setSelect(false);
                    if (IjkPlayerView.this.mCurSelectSRT == IjkPlayerView.this.mCorrentLanguage && i2 == IjkPlayerView.this.mSrtSlelct) {
                        IjkPlayerView.this.getstr.get(IjkPlayerView.this.mSrtSlelct).setSelect(true);
                    }
                }
                IjkPlayerView.this.mCCLanguageAdapter.setMediaLanguage(IjkPlayerView.this.mCorrentLanguage);
                IjkPlayerView.this.mCCAdapter.updateItems(IjkPlayerView.this.getstr);
            }
        });
        this.mCCListSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.dl7.player.media.IjkPlayerView.16
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (!IjkPlayerView.this.getstr.get(i).isSelect()) {
                    IjkPlayerView.this.listener.onClick(IjkPlayerView.this.getstr.get(i).file_path);
                    IjkPlayerView.this.setSRTlist(i);
                    IjkPlayerView.this.mSrtController.setVisibility(0);
                } else {
                    IjkPlayerView.this.removeSRTlist(i);
                    IjkPlayerView.this.mHandler.removeMessages(IjkPlayerView.PARSE_SRT_CN);
                    IjkPlayerView.this.mSrtText.setText("");
                    IjkPlayerView.this.mSrtController.setVisibility(8);
                }
                IjkPlayerView.this._toggleCCSelect();
            }
        });
    }

    public IjkPlayerView setSRTList(LinkedHashMap<String, List<SRTModel.subTitles>> linkedHashMap, OnDownloadClickListener onDownloadClickListener) {
        this.stringListHashMap.clear();
        this.languages.clear();
        this.stringListHashMap = linkedHashMap;
        this.listener = onDownloadClickListener;
        for (Map.Entry<String, List<SRTModel.subTitles>> entry : linkedHashMap.entrySet()) {
            languageModel languagemodel = new languageModel();
            languagemodel.setLanguage(entry.getKey());
            languagemodel.setSelected(false);
            this.languages.add(languagemodel);
        }
        if (linkedHashMap.size() == 0) {
            this.mSrtSelect.setVisibility(4);
        }
        this.mCCLanguageAdapter.updateItems(this.languages);
        return this;
    }

    public void startSRT(String str) {
        SrtParser.parseSrt(str, this.srtList);
        this.mHandler.sendEmptyMessageDelayed(PARSE_SRT_CN, 500L);
    }

    public void setSRTlist(int i) {
        int i2 = this.mCurSelectSRT;
        int i3 = this.mCorrentLanguage;
        if (i2 == i3 && this.mSrtSlelct == i) {
            return;
        }
        this.mSrtSlelct = i;
        this.mCurSelectSRT = i3;
        this.srtspeed = (int) (this.mCCAdapter.getDely(i) * 1000.0f);
        this.ll_srt_average.setText("Most users choose " + this.srtspeed + CmcdData.STREAMING_FORMAT_SS);
        this.srttext.setText(this.srtspeed + "");
        this.mCCAdapter.setMediaQuality(i);
    }

    public void removeSRTlist(int i) {
        Log.d("AAAAAA", "复杂" + this.mCurSelectSRT + this.mCorrentLanguage + this.mSrtSlelct + i);
        if (this.mCurSelectSRT == this.mCorrentLanguage && this.mSrtSlelct == i) {
            this.mSrtSlelct = -1;
            this.mCCAdapter._cleanSelected();
            this.mCCAdapter.notifyDataSetChanged();
        }
    }

    private void _initExrtSRT() {
        this.mFlExtraCC = findViewById(R.id.fl_srt_exrt);
        this.exrtSrt = (ListView) findViewById(R.id.lv_srt_exrt);
        AdapterSRTExtra adapterSRTExtra = new AdapterSRTExtra(this.mAttachActivity);
        this.mExtraLanguageAdapter = adapterSRTExtra;
        this.exrtSrt.setAdapter((ListAdapter) adapterSRTExtra);
        this.exrtSrt.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.dl7.player.media.IjkPlayerView.17
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                IjkPlayerView.this.onOpenExtraDownload.download(((ExtrModel) IjkPlayerView.this.ListexrtSrt.get(i)).getZipDownloadLink());
                IjkPlayerView.this._toggleExtraSelect();
            }
        });
    }

    public IjkPlayerView setExtraSrt(OnOpenExtraDownload onOpenExtraDownload) {
        this.onOpenExtraDownload = onOpenExtraDownload;
        this.ListexrtSrt = new ArrayList();
        return this;
    }

    public void shwoExtraSrt(List<ExtrModel> list) {
        this.ListexrtSrt = list;
        if (list.size() > 0) {
            this.mExtraLanguageAdapter.updateItems(list);
            _toggleExtraSelect();
        }
    }

    public void _initMediaTrack() {
        ImageView imageView = (ImageView) findViewById(R.id.tv_meidaTrack);
        this.mFlMediaTracks = imageView;
        imageView.setOnClickListener(this);
        this.mFlTrack = findViewById(R.id.fl_media_track);
        this.MediaTrackList = (ListView) findViewById(R.id.lv_media_track);
        this.mVideoView.getInfo(new IjkVideoView.VideogetStartInfo() { // from class: com.dl7.player.media.IjkPlayerView.18
            @Override // com.dl7.player.media.IjkVideoView.VideogetStartInfo
            public void getVideoStart() {
                IjkPlayerView.this.getTrackInfo();
            }
        });
        AdapterMediaTrack adapterMediaTrack = new AdapterMediaTrack(this.mAttachActivity);
        this.mTrackAdapter = adapterMediaTrack;
        this.MediaTrackList.setAdapter((ListAdapter) adapterMediaTrack);
        this.MediaTrackList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.dl7.player.media.IjkPlayerView.19
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                int selectedTrack = IjkPlayerView.this.mVideoView.getSelectedTrack(2);
                if (selectedTrack != ((TrackModel) IjkPlayerView.this.ListTracks.get(i)).getTrackModel()) {
                    IjkPlayerView.this.mVideoView.deselectTrack(selectedTrack);
                    IjkPlayerView.this.mVideoView.selectTrack(((TrackModel) IjkPlayerView.this.ListTracks.get(i)).getTrackModel());
                    IjkPlayerView.this.mTrackAdapter.setExtrSrt(i);
                }
                IjkPlayerView.this._toggleTrackSelect();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getTrackInfo() {
        this.ListTracks.clear();
        ITrackInfo[] trackInfo = this.mVideoView.getTrackInfo();
        if (trackInfo == null) {
            return;
        }
        for (int i = 0; i < trackInfo.length; i++) {
            Log.d("LivePreviewActivity", "TrackType:" + trackInfo[i].getTrackType());
            Log.d("LivePreviewActivity", "ITrackInfo.MEDIA_TRACK_TYPE_AUDIO:2");
            if (trackInfo[i].getTrackType() == 2) {
                Log.d("LivePreviewActivity", trackInfo[i].getInfoInline());
                if (trackInfo[i].getInfoInline().contains("kb/s")) {
                    TrackModel trackModel = new TrackModel();
                    IMediaFormat format = this.mVideoView.getTrackInfo()[i].getFormat();
                    if (format != null && (format instanceof IjkMediaFormat) && this.mVideoView.getTrackInfo()[i].getTrackType() == 2) {
                        trackModel.setTrackName(this.mVideoView.getTrackInfo()[i].getLanguage());
                        trackModel.setTrackType("Audio");
                        trackModel.setTrackInfo(this.mVideoView.getTrackInfo()[i].getInfoInline());
                        trackModel.setTrackModel(i);
                        trackModel.setSelect(false);
                    }
                    if (i == this.mVideoView.getSelectedTrack(2)) {
                        trackModel.setSelect(true);
                    }
                    this.ListTracks.add(trackModel);
                    Log.d("LivePreviewActivity", "------------");
                }
            }
        }
        this.mTrackAdapter.updateItems(this.ListTracks);
        List<TrackModel> list = this.ListTracks;
        if (list == null || list.size() <= 1) {
            return;
        }
        this.mFlMediaTracks.setVisibility(0);
        for (int i2 = 0; i2 < this.ListTracks.size(); i2++) {
            if (this.ListTracks.get(i2).getTrackName().equals("eng") && this.ListTracks.get(i2).getTrackModel() != this.mVideoView.getSelectedTrack(2)) {
                IjkVideoView ijkVideoView = this.mVideoView;
                ijkVideoView.deselectTrack(ijkVideoView.getSelectedTrack(2));
                this.mVideoView.selectTrack(this.ListTracks.get(i2).getTrackModel());
                this.mTrackAdapter.setExtrSrt(this.ListTracks.size() - 1);
            }
        }
    }

    private void _initMediaQuality() {
        this.mMediaQualityDesc = getResources().getStringArray(R.array.media_quality);
        this.mFlMediaQuality = findViewById(R.id.fl_media_quality);
        TextView textView = (TextView) findViewById(R.id.iv_media_quality);
        this.mIvMediaQuality = textView;
        textView.setOnClickListener(this);
        this.mLvMediaQuality = (ListView) findViewById(R.id.lv_media_quality);
        AdapterMediaQualitys adapterMediaQualitys = new AdapterMediaQualitys(this.mAttachActivity);
        this.mQualityAdapter = adapterMediaQualitys;
        this.mLvMediaQuality.setAdapter((ListAdapter) adapterMediaQualitys);
        this.mLvMediaQuality.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.dl7.player.media.IjkPlayerView.20
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (IjkPlayerView.this.mCurSelectQuality != IjkPlayerView.this.mQualityAdapter.getItem(i).getIndex()) {
                    if (IjkPlayerView.this.mQualityAdapter.getItem(i).getPath() != null) {
                        if (!IjkPlayerView.this.QualityListener.onClick(IjkPlayerView.this.mQualityAdapter.getItem(i).getIsVip())) {
                            IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                            ijkPlayerView.setMediaQuality(ijkPlayerView.mQualityAdapter.getItem(i).getIndex());
                            IjkPlayerView.this.mLoadingView.setVisibility(0);
                            IjkPlayerView.this.start();
                        }
                    } else {
                        IjkPlayerView ijkPlayerView2 = IjkPlayerView.this;
                        ijkPlayerView2.setMediaQuality(ijkPlayerView2.mQualityAdapter.getItem(i).getIndex());
                        IjkPlayerView.this.mLoadingView.setVisibility(0);
                        IjkPlayerView.this.start();
                    }
                }
                IjkPlayerView.this._toggleMediaQuality();
            }
        });
    }

    public IjkPlayerView switchVideoSource(String str, String str2, String str3, String str4, String str5) {
        reset();
        return setVideoSource(str, str2, str3, str4, str5);
    }

    public IjkPlayerView setVideoSource(String str, String str2, String str3, String str4, String str5) {
        boolean z;
        this.mQualityData = new ArrayList();
        boolean z2 = false;
        if (str != null) {
            this.mVideoSource.put(0, str);
            this.mQualityData.add(new MediaQualityInfo(0, this.mMediaQualityDesc[0], true));
            this.mCurSelectQuality = 0;
            z = false;
        } else {
            z = true;
        }
        if (str2 != null) {
            this.mVideoSource.put(1, str2);
            this.mQualityData.add(new MediaQualityInfo(1, this.mMediaQualityDesc[1], z));
            if (z) {
                this.mCurSelectQuality = 1;
            }
            z = false;
        }
        if (str3 != null) {
            this.mVideoSource.put(2, str3);
            this.mQualityData.add(new MediaQualityInfo(2, this.mMediaQualityDesc[2], z));
            if (z) {
                this.mCurSelectQuality = 2;
            }
            z = false;
        }
        if (str4 != null) {
            this.mVideoSource.put(3, str4);
            this.mQualityData.add(new MediaQualityInfo(3, this.mMediaQualityDesc[3], z));
            if (z) {
                this.mCurSelectQuality = 3;
            }
        } else {
            z2 = z;
        }
        if (str5 != null) {
            this.mVideoSource.put(4, str5);
            this.mQualityData.add(new MediaQualityInfo(4, this.mMediaQualityDesc[4], z2));
            if (z2) {
                this.mCurSelectQuality = 4;
            }
        }
        this.mQualityAdapter.updateItems(this.mQualityData);
        this.mIvMediaQuality.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(this.mAttachActivity, QUALITY_DRAWABLE_RES[this.mCurSelectQuality]), (Drawable) null, (Drawable) null);
        this.mIvMediaQuality.setText(this.mMediaQualityDesc[this.mCurSelectQuality]);
        setVideoPath(this.mVideoSource.get(this.mCurSelectQuality));
        return this;
    }

    public IjkPlayerView setVideoSource(List<MediaQualityInfo> list, OnVipClickListener onVipClickListener) {
        boolean z;
        if (list == null) {
            return this;
        }
        this.QualityListener = onVipClickListener;
        this.mQualityData = new ArrayList();
        if (list.size() <= 0 || list.get(0) == null) {
            z = true;
        } else {
            this.mVideoSource.put(0, list.get(0).getPath());
            list.get(0).setIndex(0);
            list.get(0).setSelect(true);
            this.mQualityData.add(list.get(0));
            this.mCurSelectQuality = 0;
            z = false;
        }
        if (list.size() > 1 && list.get(1) != null) {
            this.mVideoSource.put(1, list.get(1).getPath());
            list.get(1).setIndex(1);
            list.get(1).setSelect(z);
            this.mQualityData.add(list.get(1));
            if (z) {
                this.mCurSelectQuality = 1;
            }
            z = false;
        }
        if (list.size() > 2 && list.get(2) != null) {
            this.mVideoSource.put(2, list.get(2).getPath());
            list.get(2).setIndex(2);
            list.get(2).setSelect(z);
            this.mQualityData.add(list.get(2));
            if (z) {
                this.mCurSelectQuality = 2;
            }
            z = false;
        }
        if (list.size() > 3 && list.get(3) != null) {
            this.mVideoSource.put(3, list.get(3).getPath());
            list.get(3).setIndex(3);
            list.get(3).setSelect(z);
            this.mQualityData.add(list.get(3));
            if (z) {
                this.mCurSelectQuality = 3;
            }
            z = false;
        }
        if (list.size() > 4 && list.get(4) != null) {
            this.mVideoSource.put(4, list.get(4).getPath());
            list.get(4).setIndex(4);
            list.get(4).setSelect(z);
            this.mQualityData.add(list.get(4));
            if (z) {
                this.mCurSelectQuality = 4;
            }
        }
        this.mQualityAdapter.updateItems(this.mQualityData);
        String quality = list.get(this.mCurSelectQuality).getQuality();
        quality.hashCode();
        switch (quality) {
            case "360p":
                this.mIvMediaQuality.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(this.mAttachActivity, QUALITY_DRAWABLE_RES[0]), (Drawable) null, (Drawable) null);
                break;
            case "720p":
                this.mIvMediaQuality.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(this.mAttachActivity, QUALITY_DRAWABLE_RES[1]), (Drawable) null, (Drawable) null);
                break;
            case "1080p":
                this.mIvMediaQuality.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(this.mAttachActivity, QUALITY_DRAWABLE_RES[2]), (Drawable) null, (Drawable) null);
                break;
        }
        this.mIvMediaQuality.setText(list.get(this.mCurSelectQuality).getQuality());
        setVideoPath(this.mVideoSource.get(this.mCurSelectQuality));
        return this;
    }

    public IjkPlayerView setMediaQuality(int i) {
        List<MediaQualityInfo> list;
        if (this.mCurSelectQuality != i && this.mVideoSource.get(i) != null && (list = this.mQualityData) != null && list.size() >= i) {
            this.mQualityAdapter.setMediaQuality(i);
            String quality = this.mQualityData.get(i).getQuality();
            quality.hashCode();
            switch (quality) {
                case "360p":
                    this.mIvMediaQuality.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(this.mAttachActivity, QUALITY_DRAWABLE_RES[0]), (Drawable) null, (Drawable) null);
                    break;
                case "720p":
                    this.mIvMediaQuality.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(this.mAttachActivity, QUALITY_DRAWABLE_RES[1]), (Drawable) null, (Drawable) null);
                    break;
                case "1080p":
                    this.mIvMediaQuality.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, ContextCompat.getDrawable(this.mAttachActivity, QUALITY_DRAWABLE_RES[2]), (Drawable) null, (Drawable) null);
                    break;
            }
            this.mIvMediaQuality.setText(this.mQualityData.get(i).getQuality());
            this.mCurSelectQuality = i;
            if (this.mVideoView.isPlaying()) {
                this.mCurPosition = this.mVideoView.getCurrentPosition();
                this.mVideoView.release(false);
            }
            this.mVideoView.setRender(2);
            setVideoPath(this.mVideoSource.get(i));
        }
        return this;
    }

    private void _initVideoSkip() {
        this.mLlSkipLayout = findViewById(R.id.ll_skip_layout);
        this.mIvCancelSkip = (ImageView) findViewById(R.id.iv_cancel_skip);
        this.mTvSkipTime = (TextView) findViewById(R.id.tv_skip_time);
        this.mTvDoSkip = (TextView) findViewById(R.id.tv_do_skip);
        this.mIvCancelSkip.setOnClickListener(this);
        this.mTvDoSkip.setOnClickListener(this);
    }

    public int getCurPosition() {
        return this.mVideoView.getCurrentPosition();
    }

    public IjkPlayerView setSkipTip(int i) {
        this.mSkipPosition = i;
        return this;
    }

    private void _showSkipTip() {
        if (this.mSkipPosition == -1 || this.mLlSkipLayout.getVisibility() != 8) {
            return;
        }
        this.mLlSkipLayout.setVisibility(0);
        this.mTvSkipTime.setText(StringUtils.generateTime(this.mSkipPosition));
        AnimHelper.doSlideRightIn(this.mLlSkipLayout, this.mWidthPixels, 0, 800);
        this.mHandler.postDelayed(this.mHideSkipTipRunnable, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideSkipTip() {
        if (this.mLlSkipLayout.getVisibility() == 8) {
            return;
        }
        ViewCompat.animate(this.mLlSkipLayout).translationX(-this.mLlSkipLayout.getWidth()).alpha(0.0f).setDuration(500L).setListener(new ViewPropertyAnimatorListenerAdapter() { // from class: com.dl7.player.media.IjkPlayerView.21
            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
            public void onAnimationEnd(View view) {
                IjkPlayerView.this.mLlSkipLayout.setVisibility(8);
            }
        }).start();
        this.mSkipPosition = -1;
    }

    private void _initDanmaku() {
        this.mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
        this.mIvDanmakuControl = (ImageView) findViewById(R.id.iv_danmaku_control);
        this.mTvOpenEditDanmaku = (TextView) findViewById(R.id.tv_open_edit_danmaku);
        this.mTvTimeSeparator = (TextView) findViewById(R.id.tv_separator);
        this.mEditDanmakuLayout = findViewById(R.id.ll_edit_danmaku);
        this.mEtDanmakuContent = (EditText) findViewById(R.id.et_danmaku_content);
        this.mIvCancelSend = (ImageView) findViewById(R.id.iv_cancel_send);
        this.mIvDoSend = (ImageView) findViewById(R.id.iv_do_send);
        SeekBar seekBar = (SeekBar) findViewById(R.id.danmaku_player_seek);
        this.mDanmakuPlayerSeek = seekBar;
        seekBar.setMax(1000);
        this.mDanmakuPlayerSeek.setOnSeekBarChangeListener(this.mSeekListener);
        int navigationBarHeight = NavUtils.getNavigationBarHeight(this.mAttachActivity);
        if (navigationBarHeight > 0) {
            this.mEditDanmakuLayout.setPadding(0, 0, navigationBarHeight, 0);
        }
        this.mIvDanmakuControl.setOnClickListener(this);
        this.mTvOpenEditDanmaku.setOnClickListener(this);
        this.mIvCancelSend.setOnClickListener(this);
        this.mIvDoSend.setOnClickListener(this);
        this.mMoreOptionsWidth = getResources().getDimensionPixelOffset(R.dimen.danmaku_input_options_color_radio_btn_size) * 12;
        this.mDanmakuOptionsBasic = findViewById(R.id.input_options_basic);
        View viewFindViewById = findViewById(R.id.input_options_more);
        this.mDanmakuMoreOptions = viewFindViewById;
        viewFindViewById.setOnClickListener(this);
        this.mDanmakuCurColor = (RadioButton) findViewById(R.id.input_options_color_current);
        this.mDanmakuMoreColorIcon = (ImageView) findViewById(R.id.input_options_color_more_icon);
        this.mDanmakuTextSizeOptions = (RadioGroup) findViewById(R.id.input_options_group_textsize);
        this.mDanmakuTypeOptions = (RadioGroup) findViewById(R.id.input_options_group_type);
        this.mDanmakuColorOptions = (RadioGroup) findViewById(R.id.input_options_color_group);
        this.mDanmakuTextSizeOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dl7.player.media.IjkPlayerView.23
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.input_options_small_textsize) {
                    IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                    ijkPlayerView.mDanmakuTextSize = (ijkPlayerView.mDanmakuParser.getDisplayer().getDensity() - 0.6f) * 25.0f * 0.7f;
                } else if (i == R.id.input_options_medium_textsize) {
                    IjkPlayerView ijkPlayerView2 = IjkPlayerView.this;
                    ijkPlayerView2.mDanmakuTextSize = (ijkPlayerView2.mDanmakuParser.getDisplayer().getDensity() - 0.6f) * 25.0f;
                }
            }
        });
        this.mDanmakuTypeOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dl7.player.media.IjkPlayerView.24
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.input_options_rl_type) {
                    IjkPlayerView.this.mDanmakuType = 1;
                } else if (i == R.id.input_options_top_type) {
                    IjkPlayerView.this.mDanmakuType = 5;
                } else if (i == R.id.input_options_bottom_type) {
                    IjkPlayerView.this.mDanmakuType = 4;
                }
            }
        });
        this.mDanmakuColorOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.dl7.player.media.IjkPlayerView.25
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                String str = (String) IjkPlayerView.this.findViewById(i).getTag();
                IjkPlayerView.this.mDanmakuTextColor = Color.parseColor(str);
                IjkPlayerView.this.mDanmakuCurColor.setBackgroundColor(IjkPlayerView.this.mDanmakuTextColor);
            }
        });
    }

    private void _loadDanmaku() {
        if (this.mIsEnableDanmaku) {
            this.mDanmakuContext = DanmakuContext.create();
            if (this.mDanmakuParser == null) {
                this.mDanmakuParser = new BaseDanmakuParser() { // from class: com.dl7.player.media.IjkPlayerView.26
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
                    public Danmakus parse() {
                        return new Danmakus();
                    }
                };
            }
            this.mDanmakuView.setCallback(new DrawHandler.Callback() { // from class: com.dl7.player.media.IjkPlayerView.27
                @Override // master.flame.danmaku.controller.DrawHandler.Callback
                public void danmakuShown(BaseDanmaku baseDanmaku) {
                }

                @Override // master.flame.danmaku.controller.DrawHandler.Callback
                public void drawingFinished() {
                }

                @Override // master.flame.danmaku.controller.DrawHandler.Callback
                public void updateTimer(DanmakuTimer danmakuTimer) {
                }

                @Override // master.flame.danmaku.controller.DrawHandler.Callback
                public void prepared() {
                    if (!IjkPlayerView.this.mVideoView.isPlaying() || IjkPlayerView.this.mIsBufferingStart) {
                        return;
                    }
                    IjkPlayerView.this.mDanmakuView.start();
                }
            });
            this.mDanmakuView.enableDanmakuDrawingCache(true);
            this.mDanmakuView.prepare(this.mDanmakuParser, this.mDanmakuContext);
        }
    }

    public IjkPlayerView enableDanmaku() {
        this.mIsEnableDanmaku = true;
        _initDanmaku();
        if (this.mIsAlwaysFullScreen) {
            _toggleDanmakuView(true);
        }
        return this;
    }

    public IjkPlayerView enableDanmaku(boolean z) {
        this.mIsEnableDanmaku = z;
        if (z) {
            _initDanmaku();
            if (this.mIsAlwaysFullScreen) {
                _toggleDanmakuView(true);
            }
        }
        return this;
    }

    public IjkPlayerView setDanmakuSource(InputStream inputStream) {
        if (inputStream == null) {
            return this;
        }
        if (!this.mIsEnableDanmaku) {
            throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
        }
        if (this.mDanmakuLoader == null) {
            this.mDanmakuLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
        }
        try {
            this.mDanmakuLoader.load(inputStream);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        IDataSource<?> dataSource = this.mDanmakuLoader.getDataSource();
        if (this.mDanmakuParser == null) {
            this.mDanmakuParser = new BiliDanmukuParser();
        }
        this.mDanmakuParser.load(dataSource);
        return this;
    }

    public IjkPlayerView setDanmakuSource(String str) {
        if (TextUtils.isEmpty(str)) {
            return this;
        }
        if (!this.mIsEnableDanmaku) {
            throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
        }
        if (this.mDanmakuLoader == null) {
            this.mDanmakuLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
        }
        try {
            this.mDanmakuLoader.load(str);
        } catch (IllegalDataException e) {
            e.printStackTrace();
        }
        IDataSource<?> dataSource = this.mDanmakuLoader.getDataSource();
        if (this.mDanmakuParser == null) {
            this.mDanmakuParser = new BiliDanmukuParser();
        }
        this.mDanmakuParser.load(dataSource);
        return this;
    }

    public IjkPlayerView setDanmakuCustomParser(BaseDanmakuParser baseDanmakuParser, ILoader iLoader, BaseDanmakuConverter baseDanmakuConverter) {
        this.mDanmakuParser = baseDanmakuParser;
        this.mDanmakuLoader = iLoader;
        this.mDanmakuConverter = baseDanmakuConverter;
        return this;
    }

    public IjkPlayerView showOrHideDanmaku(boolean z) {
        if (z) {
            this.mIvDanmakuControl.setSelected(false);
            this.mDanmakuView.show();
        } else {
            this.mIvDanmakuControl.setSelected(true);
            this.mDanmakuView.hide();
        }
        return this;
    }

    public void sendDanmaku(String str, boolean z) {
        if (!this.mIsEnableDanmaku) {
            throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
        }
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(this.mAttachActivity, "内容为空", 0).show();
            return;
        }
        if (!this.mDanmakuView.isPrepared()) {
            Toast.makeText(this.mAttachActivity, "弹幕还没准备好", 0).show();
            return;
        }
        BaseDanmaku baseDanmakuCreateDanmaku = this.mDanmakuContext.mDanmakuFactory.createDanmaku(this.mDanmakuType);
        if (baseDanmakuCreateDanmaku == null || this.mDanmakuView == null) {
            return;
        }
        if (this.mDanmakuTextSize == -1.0f) {
            this.mDanmakuTextSize = (this.mDanmakuParser.getDisplayer().getDensity() - 0.6f) * 25.0f;
        }
        baseDanmakuCreateDanmaku.text = str;
        baseDanmakuCreateDanmaku.padding = 5;
        baseDanmakuCreateDanmaku.isLive = z;
        baseDanmakuCreateDanmaku.priority = (byte) 0;
        baseDanmakuCreateDanmaku.textSize = this.mDanmakuTextSize;
        baseDanmakuCreateDanmaku.textColor = this.mDanmakuTextColor;
        baseDanmakuCreateDanmaku.underlineColor = -16711936;
        baseDanmakuCreateDanmaku.setTime(this.mDanmakuView.getCurrentTime() + 500);
        this.mDanmakuView.addDanmaku(baseDanmakuCreateDanmaku);
        OnDanmakuListener onDanmakuListener = this.mDanmakuListener;
        if (onDanmakuListener != null) {
            BaseDanmakuConverter baseDanmakuConverter = this.mDanmakuConverter;
            if (baseDanmakuConverter != null) {
                onDanmakuListener.onDataObtain(baseDanmakuConverter.convertDanmaku(baseDanmakuCreateDanmaku));
            } else {
                onDanmakuListener.onDataObtain(baseDanmakuCreateDanmaku);
            }
        }
    }

    public void editVideo() {
        if (this.mVideoView.isPlaying()) {
            pause();
            this.mVideoStatus = 502;
        } else {
            this.mVideoStatus = 503;
        }
        _hideAllView(false);
    }

    public boolean recoverFromEditVideo() {
        StringBuilder sb = new StringBuilder("  mIsShowCC ：");
        sb.append(this.mIsShowCC);
        sb.append("  mIsShowQuality ： ");
        sb.append(this.mIsShowQuality);
        sb.append("  mVideoStatus ：");
        sb.append(this.mVideoStatus);
        sb.append(this.mVideoStatus == 501);
        Log.d("VIEWDOPLAY", sb.toString());
        if (this.mVideoStatus == 501) {
            return false;
        }
        if (this.mIsFullscreen) {
            _recoverScreen();
        }
        if (this.mVideoStatus == 502) {
            start();
        }
        this.mVideoStatus = 501;
        return true;
    }

    private void _resumeDanmaku() {
        IDanmakuView iDanmakuView = this.mDanmakuView;
        if (iDanmakuView != null && iDanmakuView.isPrepared() && this.mDanmakuView.isPaused()) {
            long j = this.mDanmakuTargetPosition;
            if (j != -1) {
                this.mDanmakuView.seekTo(Long.valueOf(j));
                this.mDanmakuTargetPosition = -1L;
            } else {
                this.mDanmakuView.resume();
            }
        }
    }

    private void _pauseDanmaku() {
        IDanmakuView iDanmakuView = this.mDanmakuView;
        if (iDanmakuView == null || !iDanmakuView.isPrepared()) {
            return;
        }
        this.mDanmakuView.pause();
    }

    private void _toggleDanmakuShow() {
        if (this.mIvDanmakuControl.isSelected()) {
            showOrHideDanmaku(true);
        } else {
            showOrHideDanmaku(false);
        }
    }

    private void _toggleDanmakuView(boolean z) {
        if (this.mIsEnableDanmaku) {
            if (z) {
                this.mIvDanmakuControl.setVisibility(0);
                this.mTvOpenEditDanmaku.setVisibility(0);
                this.mTvTimeSeparator.setVisibility(0);
                this.mDanmakuPlayerSeek.setVisibility(0);
                this.mPlayerSeek.setVisibility(8);
                return;
            }
            this.mIvDanmakuControl.setVisibility(8);
            this.mTvOpenEditDanmaku.setVisibility(8);
            this.mTvTimeSeparator.setVisibility(8);
            this.mDanmakuPlayerSeek.setVisibility(8);
            this.mPlayerSeek.setVisibility(0);
        }
    }

    private void _recoverScreen() {
        this.mEditDanmakuLayout.clearFocus();
        this.mEditDanmakuLayout.setVisibility(8);
        SoftInputUtils.closeSoftInput(this.mAttachActivity);
        _setUiLayoutFullscreen();
        if (this.mDanmakuColorOptions.getWidth() != 0) {
            _toggleMoreColorOptions();
        }
    }

    private void _toggleMoreColorOptions() {
        if (this.mBasicOptionsWidth == -1) {
            this.mBasicOptionsWidth = this.mDanmakuOptionsBasic.getWidth();
        }
        if (this.mDanmakuColorOptions.getWidth() == 0) {
            AnimHelper.doClipViewWidth(this.mDanmakuOptionsBasic, this.mBasicOptionsWidth, 0, 300);
            AnimHelper.doClipViewWidth(this.mDanmakuColorOptions, 0, this.mMoreOptionsWidth, 300);
            ViewCompat.animate(this.mDanmakuMoreColorIcon).rotation(180.0f).setDuration(150L).setStartDelay(250L).start();
        } else {
            AnimHelper.doClipViewWidth(this.mDanmakuOptionsBasic, 0, this.mBasicOptionsWidth, 300);
            AnimHelper.doClipViewWidth(this.mDanmakuColorOptions, this.mMoreOptionsWidth, 0, 300);
            ViewCompat.animate(this.mDanmakuMoreColorIcon).rotation(0.0f).setDuration(150L).setStartDelay(250L).start();
        }
    }

    private void _initReceiver() {
        this.mPbBatteryLevel = (ProgressBar) findViewById(R.id.pb_battery);
        TextView textView = (TextView) findViewById(R.id.tv_system_time);
        this.mTvSystemTime = textView;
        textView.setText(StringUtils.getCurFormatTime());
        this.mBatteryReceiver = new BatteryBroadcastReceiver();
        this.mScreenReceiver = new ScreenBroadcastReceiver();
        this.mNetReceiver = new NetBroadcastReceiver();
        this.mAttachActivity.registerReceiver(this.mBatteryReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        this.mAttachActivity.registerReceiver(this.mScreenReceiver, new IntentFilter("android.intent.action.SCREEN_OFF"));
        this.mAttachActivity.registerReceiver(this.mNetReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        ImageView imageView = (ImageView) findViewById(R.id.iv_screenshot);
        this.mIvScreenshot = imageView;
        imageView.setOnClickListener(this);
        if (SDCardUtils.isAvailable()) {
            _createSaveDir(SDCardUtils.getRootPath() + File.separator + "IjkPlayView");
        }
    }

    private void _doScreenshot() {
        editVideo();
        _showShareDialog(this.mVideoView.getScreenshot());
    }

    private void _showShareDialog(Bitmap bitmap) {
        if (this.mShareDialog == null) {
            ShareDialog shareDialog = new ShareDialog();
            this.mShareDialog = shareDialog;
            shareDialog.setClickListener(this.mInsideDialogClickListener);
            this.mShareDialog.setDismissListener(this.mDialogDismissListener);
            if (this.mDialogClickListener != null) {
                this.mShareDialog.setShareMode(true);
            }
        }
        this.mShareDialog.setScreenshotPhoto(bitmap);
        this.mShareDialog.show(this.mAttachActivity.getSupportFragmentManager(), FirebaseAnalytics.Event.SHARE);
    }

    public IjkPlayerView setDialogClickListener(ShareDialog.OnDialogClickListener onDialogClickListener) {
        this.mDialogClickListener = onDialogClickListener;
        ShareDialog shareDialog = this.mShareDialog;
        if (shareDialog != null) {
            shareDialog.setShareMode(true);
        }
        return this;
    }

    private void _createSaveDir(String str) {
        File file = new File(str);
        this.mSaveDir = file;
        if (!file.exists()) {
            this.mSaveDir.mkdirs();
        } else {
            if (this.mSaveDir.isDirectory()) {
                return;
            }
            this.mSaveDir.delete();
            this.mSaveDir.mkdirs();
        }
    }

    public IjkPlayerView setSaveDir(String str) {
        _createSaveDir(str);
        return this;
    }

    class BatteryBroadcastReceiver extends BroadcastReceiver {
        private static final int BATTERY_LOW_LEVEL = 15;

        BatteryBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                int intExtra = (intent.getIntExtra(FirebaseAnalytics.Param.LEVEL, 0) * 100) / intent.getIntExtra("scale", 100);
                if (intent.getIntExtra(NotificationCompat.CATEGORY_STATUS, 1) == 2) {
                    IjkPlayerView.this.mPbBatteryLevel.setSecondaryProgress(0);
                    IjkPlayerView.this.mPbBatteryLevel.setProgress(intExtra);
                    IjkPlayerView.this.mPbBatteryLevel.setBackgroundResource(R.mipmap.ic_battery_charging);
                } else if (intExtra < 15) {
                    IjkPlayerView.this.mPbBatteryLevel.setProgress(0);
                    IjkPlayerView.this.mPbBatteryLevel.setSecondaryProgress(intExtra);
                    IjkPlayerView.this.mPbBatteryLevel.setBackgroundResource(R.mipmap.ic_battery_red);
                } else {
                    IjkPlayerView.this.mPbBatteryLevel.setSecondaryProgress(0);
                    IjkPlayerView.this.mPbBatteryLevel.setProgress(intExtra);
                    IjkPlayerView.this.mPbBatteryLevel.setBackgroundResource(R.mipmap.ic_battery);
                }
            }
        }
    }

    private class ScreenBroadcastReceiver extends BroadcastReceiver {
        private ScreenBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                IjkPlayerView.this.mIsScreenLocked = true;
            }
        }
    }

    public class NetBroadcastReceiver extends BroadcastReceiver {
        public NetBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                IjkPlayerView ijkPlayerView = IjkPlayerView.this;
                ijkPlayerView.mIsNetConnected = NetWorkUtils.isNetworkAvailable(ijkPlayerView.mAttachActivity);
            }
        }
    }
}
