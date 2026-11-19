package com.movieboxpro.android.player;

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
import androidx.core.view.MotionEventCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListenerAdapter;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.media3.common.C;
import androidx.media3.exoplayer.audio.SilenceSkippingAudioProcessor;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.MpegAudioUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.dl7.player.danmaku.BaseDanmakuConverter;
import com.dl7.player.danmaku.BiliDanmukuParser;
import com.dl7.player.danmaku.OnDanmakuListener;
import com.dl7.player.media.MediaPlayerParams;
import com.dl7.player.model.SRTModel;
import com.dl7.player.model.TrackModel;
import com.dl7.player.model.languageModel;
import com.dl7.player.utils.AnimHelper;
import com.dl7.player.utils.MotionEventUtils;
import com.dl7.player.utils.NavUtils;
import com.dl7.player.utils.NetWorkUtils;
import com.dl7.player.utils.SRT;
import com.dl7.player.utils.SoftInputUtils;
import com.dl7.player.utils.StringUtils;
import com.dl7.player.utils.WindowUtils;
import com.dl7.player.widgets.MyListView;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.event.OnPlayStatusEvent;
import com.movieboxpro.android.event.SwitchTestSpeedHintEvent;
import com.movieboxpro.android.listener.PlayStatusListener;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ShellUtil;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleActivity;
import com.movieboxpro.androidtv.R;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.io.InputStream;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
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
import org.greenrobot.eventbus.EventBus;
import org.joda.time.DateTimeConstants;
import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.misc.IMediaFormat;
import tv.danmaku.ijk.media.player.misc.ITrackInfo;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;

/* JADX INFO: loaded from: classes5.dex */
public class IjkPlayerView2 extends FrameLayout implements View.OnClickListener {
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
    private static final int[] QUALITY_DRAWABLE_RES = {R.mipmap.ic_media_quality_smooth, R.mipmap.ic_media_quality_medium, R.mipmap.ic_media_quality_high, R.mipmap.ic_media_quality_super, com.dl7.player.R.mipmap.ic_media_quality_bd};
    private static final String TAG = "IjkPlayerView";
    private List<TrackModel> ListTracks;
    private ListView MediaTrackList;
    private OnVipClickListener QualityListener;
    private long bitStream;
    private int boxType;
    private SRTModel.subTitles currSelectServerSubtitle;
    private String currSubtitleLanguage;
    List<SRTModel.subTitles> getstr;
    public boolean isFrist;
    private List<languageModel> languages;
    private OnDownloadClickListener listener;
    private LinearLayout llVideoSubtitle;
    private TextView ll_srt_average;
    private ImageView ll_srt_close;
    private RelativeLayout ll_srt_size;
    private RelativeLayout ll_srt_speed;
    private ListView lrcView;
    private AdapterSrtlist mAdapter;
    private int mAspectOptionsHeight;
    private RadioGroup mAspectRatioOptions;
    private AppCompatActivity mAttachActivity;
    private AudioManager mAudioManager;
    private int mBasicOptionsWidth;
    protected ImageView mBottomCCSize;
    protected ImageView mBottomCCSpeed;
    protected ImageView mBottomCCclose;
    protected ImageView mBottomCClast;
    protected ImageView mBottomTranslate;
    private AdapterMediaQuality mCCAdapter;
    private MyListView mCCCountrySelect;
    private AdapterSRTLanguage mCCLanguageAdapter;
    private MyListView mCCListSelect;
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
    private View mEditDanmakuLayout;
    private EditText mEtDanmakuContent;
    private long mExitTime;
    private View mFlCCSlect;
    private View mFlMediaQuality;
    private ImageView mFlMediaTracks;
    private View mFlReload;
    private FrameLayout mFlTouchLayout;
    private View mFlTrack;
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
    private boolean mIsSeeking;
    private boolean mIsShowBar;
    private boolean mIsShowCC;
    private boolean mIsShowLrc;
    private boolean mIsShowQuality;
    private boolean mIsShowTracker;
    private ImageView mIvCancelSend;
    private ImageView mIvCancelSkip;
    private ImageView mIvDanmakuControl;
    private ImageView mIvDoSend;
    private ImageView mIvFullscreen;
    private ImageView mIvMediaQuality;
    private ImageView mIvPlay;
    private ImageView mIvPlayCircle;
    private ImageView mIvPlayerLock;
    private List<SRT> mList;
    private LinearLayout mLlBottomBar;
    private View mLlSkipLayout;
    private ProgressBar mLoadingView;
    private View mLrcController;
    private ListView mLvMediaQuality;
    private int mMaxVolume;
    private String[] mMediaQualityDesc;
    private int mMoreOptionsWidth;
    private OrientationEventListener mOrientationListener;
    private IMediaPlayer.OnInfoListener mOutsideInfoListener;
    private GestureDetector.OnGestureListener mPlayerGestureListener;
    private SeekBar mPlayerSeek;
    public ImageView mPlayerThumb;
    private View.OnTouchListener mPlayerTouchListener;
    private AdapterMediaQualitys mQualityAdapter;
    private List<MediaQualityInfo> mQualityData;
    private Matrix mSaveMatrix;
    private int mScreenUiVisibility;
    private final SeekBar.OnSeekBarChangeListener mSeekListener;
    private int mSkipPosition;
    private LinearLayout mSrtController;
    private ImageView mSrtSelect;
    private int mSrtSlelct;
    private TextView mSrtText;
    protected ImageView mSrtsizeAdd;
    protected ImageView mSrtsizeClose;
    protected TextView mSrtsizeText;
    protected ImageView mSrtsizesubstate;
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
    private TextView mTvSkipTime;
    private TextView mTvTimeSeparator;
    private TextView mTvVolume;
    private Matrix mVideoMatrix;
    private SparseArray<String> mVideoSource;
    private int mVideoStatus;
    private IjkVideoView mVideoView;
    private int mWidthPixels;
    private String openSubtitle;
    private PlayStatusListener playStatusListener;
    private PlayerViewCallback playerViewCallback;
    private SharedPreferences prefs;
    private TvRecyclerView rvLanguage;
    private TvRecyclerView rvSubtitle;
    private Runnable seekToPositionRunnable;
    private List<SRT> selectSubtiles;
    private List<Long> speeds;
    private ArrayList<SRT> srtList;
    private ImageView srtadd;
    private int srtsize;
    private int srtspeed;
    private ImageView srtsubstate;
    private TextView srttext;
    private HashMap<String, List<SRTModel.subTitles>> stringListHashMap;
    private String subtitleContent;
    private int subtitleType;
    private Disposable tcpSpeedDisposable;
    private TextView tvTcpSpeed;

    @Target({ElementType.FIELD, ElementType.PARAMETER})
    @Retention(RetentionPolicy.SOURCE)
    public @interface DanmakuTag {
    }

    public interface OnDownloadClickListener {
        void onClick(String url, String language, String sid);
    }

    public interface OnVipClickListener {
        void onClick();
    }

    interface PlayerViewCallback {
        void onOpenSubtitleClicked();
    }

    private void _showAspectRatioOptions(boolean isShow) {
    }

    public void hideNavKey() {
    }

    public IjkPlayerView2 setTitle(String title) {
        return this;
    }

    public void showLoading() {
    }

    public void showNavKey() {
    }

    public IjkPlayerView2(Context context) {
        this(context, null);
    }

    public IjkPlayerView2(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.srtList = new ArrayList<>();
        this.mHandler = new Handler() { // from class: com.movieboxpro.android.player.IjkPlayerView2.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == IjkPlayerView2.MSG_UPDATE_SEEK) {
                    IjkPlayerView2.this._setProgress();
                    if (!IjkPlayerView2.this.mIsSeeking && IjkPlayerView2.this.mIsShowBar && IjkPlayerView2.this.mVideoView.isPlaying()) {
                        sendMessageDelayed(obtainMessage(IjkPlayerView2.MSG_UPDATE_SEEK), 1000L);
                        return;
                    }
                    return;
                }
                if (msg.what == IjkPlayerView2.MSG_ENABLE_ORIENTATION) {
                    if (IjkPlayerView2.this.mOrientationListener != null) {
                        IjkPlayerView2.this.mOrientationListener.enable();
                    }
                } else if (msg.what == IjkPlayerView2.MSG_TRY_RELOAD) {
                    if (IjkPlayerView2.this.mIsNetConnected) {
                        IjkPlayerView2.this.reload();
                    }
                    sendMessageDelayed(obtainMessage(IjkPlayerView2.MSG_TRY_RELOAD), C.DEFAULT_MAX_SEEK_TO_PREVIOUS_POSITION_MS);
                } else if (msg.what == IjkPlayerView2.PARSE_SRT_CN) {
                    IjkPlayerView2.this.setToPosition(SrtParser.showSRT2(IjkPlayerView2.this.mVideoView, IjkPlayerView2.this.mSrtText, IjkPlayerView2.this.srtsize, IjkPlayerView2.this.srtspeed, IjkPlayerView2.this.srtList));
                    IjkPlayerView2.this.mHandler.sendEmptyMessageDelayed(IjkPlayerView2.PARSE_SRT_CN, 500L);
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
        this.mSeekListener = new SeekBar.OnSeekBarChangeListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.4
            private long curPosition;

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar bar) {
                Log.d(IjkPlayerView2.TAG, "onStartTrackingTouch--->");
                IjkPlayerView2.this.mIsSeeking = true;
                IjkPlayerView2.this._showControlBar(DateTimeConstants.MILLIS_PER_HOUR);
                IjkPlayerView2.this.mHandler.removeMessages(IjkPlayerView2.MSG_UPDATE_SEEK);
                this.curPosition = IjkPlayerView2.this.mVideoView.getCurrentPosition();
                IjkPlayerView2.this.mHandler.removeCallbacks(IjkPlayerView2.this.seekToPositionRunnable);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar bar, int progress, boolean fromUser) {
                IjkPlayerView2.this.mTargetPosition = (((long) IjkPlayerView2.this.mVideoView.getDuration()) * ((long) progress)) / 1000;
                long j = (IjkPlayerView2.this.mTargetPosition - this.curPosition) / 1000;
                IjkPlayerView2.this.mTvCurTime.setText(StringUtils.generateTime(IjkPlayerView2.this.mTargetPosition));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar bar) {
                Log.d(IjkPlayerView2.TAG, "onStopTrackingTouch--->");
                IjkPlayerView2.this._hideTouchView();
                IjkPlayerView2.this.mIsSeeking = false;
            }
        };
        this.mHideBarRunnable = new Runnable() { // from class: com.movieboxpro.android.player.IjkPlayerView2.5
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView2.this._hideAllView(false);
            }
        };
        this.mPlayerGestureListener = new GestureDetector.SimpleOnGestureListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.6
            private boolean isDownTouch;
            private boolean isLandscape;
            private boolean isRecoverFromDanmaku;
            private boolean isVolume;

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent e) {
                this.isDownTouch = true;
                this.isRecoverFromDanmaku = IjkPlayerView2.this.recoverFromEditVideo();
                return super.onDown(e);
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                if (!IjkPlayerView2.this.mIsForbidTouch && !IjkPlayerView2.this.mIsNeverPlay) {
                    float x = e1.getX();
                    float y = e1.getY() - e2.getY();
                    float x2 = x - e2.getX();
                    if (this.isDownTouch) {
                        this.isLandscape = Math.abs(distanceX) >= Math.abs(distanceY);
                        this.isVolume = x > ((float) IjkPlayerView2.this.getResources().getDisplayMetrics().widthPixels) * 0.5f;
                        this.isDownTouch = false;
                    }
                    StringBuilder sb = new StringBuilder("onScroll--->");
                    float f = -x2;
                    sb.append(f / IjkPlayerView2.this.mVideoView.getWidth());
                    Log.d(IjkPlayerView2.TAG, sb.toString());
                    if (this.isLandscape) {
                        IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                        ijkPlayerView2._onProgressSlide(f / ijkPlayerView2.mVideoView.getWidth());
                    } else {
                        float height = y / IjkPlayerView2.this.mVideoView.getHeight();
                        if (this.isVolume) {
                            IjkPlayerView2.this._onVolumeSlide(height);
                        } else {
                            IjkPlayerView2.this._onBrightnessSlide(height);
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
                if (IjkPlayerView2.this.mIsShowCC) {
                    IjkPlayerView2.this._toggleCCSelect();
                }
                if (IjkPlayerView2.this.mIsShowTracker) {
                    IjkPlayerView2.this._toggleTrackSelect();
                }
                if (IjkPlayerView2.this.mIsShowLrc) {
                    IjkPlayerView2.this._toggleSrtSelect();
                }
                if (IjkPlayerView2.this.mIsShowQuality) {
                    IjkPlayerView2.this._toggleMediaQuality();
                } else {
                    IjkPlayerView2.this._toggleControlBar();
                }
                return true;
            }

            @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
            public boolean onDoubleTap(MotionEvent e) {
                if (!IjkPlayerView2.this.mIsNeverPlay && !this.isRecoverFromDanmaku && !IjkPlayerView2.this.mIsForbidTouch) {
                    IjkPlayerView2.this._refreshHideRunnable();
                    IjkPlayerView2.this._togglePlayStatus();
                }
                return true;
            }
        };
        this.mHideTouchViewRunnable = new Runnable() { // from class: com.movieboxpro.android.player.IjkPlayerView2.7
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView2.this._hideTouchView();
            }
        };
        this.mPlayerTouchListener = new View.OnTouchListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.8
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
                    IjkPlayerView2.this.mHandler.removeCallbacks(IjkPlayerView2.this.mHideBarRunnable);
                } else if (actionMasked != 2) {
                    if (actionMasked != 5) {
                        if (actionMasked == 6) {
                            if (this.mode == 3) {
                                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                                ijkPlayerView2.mIsNeedRecoverScreen = ijkPlayerView2.mVideoView.adjustVideoView(this.scale);
                                if (IjkPlayerView2.this.mIsNeedRecoverScreen && IjkPlayerView2.this.mIsShowBar) {
                                    IjkPlayerView2.this.mTvRecoverScreen.setVisibility(0);
                                }
                            }
                            this.mode = 2;
                        }
                    } else if (event.getPointerCount() == 3 && IjkPlayerView2.this.mIsFullscreen) {
                        IjkPlayerView2.this._hideTouchView();
                        this.mode = 3;
                        MotionEventUtils.midPoint(this.midPoint, event);
                        int iCalcFingerFlag = MotionEventUtils.calcFingerFlag(event);
                        this.fingerFlag = iCalcFingerFlag;
                        this.degree = MotionEventUtils.rotation(event, iCalcFingerFlag);
                        this.oldDist = MotionEventUtils.calcSpacing(event, this.fingerFlag);
                        IjkPlayerView2 ijkPlayerView3 = IjkPlayerView2.this;
                        ijkPlayerView3.mSaveMatrix = ijkPlayerView3.mVideoView.getVideoTransform();
                    } else {
                        this.mode = 2;
                    }
                } else if (this.mode == 3) {
                    IjkPlayerView2.this.mVideoView.setVideoRotation((int) (MotionEventUtils.rotation(event, this.fingerFlag) - this.degree));
                    IjkPlayerView2.this.mVideoMatrix.set(IjkPlayerView2.this.mSaveMatrix);
                    this.scale = MotionEventUtils.calcSpacing(event, this.fingerFlag) / this.oldDist;
                    Matrix matrix = IjkPlayerView2.this.mVideoMatrix;
                    float f = this.scale;
                    matrix.postScale(f, f, this.midPoint.x, this.midPoint.y);
                    IjkPlayerView2.this.mVideoView.setVideoTransform(IjkPlayerView2.this.mVideoMatrix);
                }
                if (this.mode == 1) {
                    if (IjkPlayerView2.this.mGestureDetector.onTouchEvent(event)) {
                        return true;
                    }
                    if (MotionEventCompat.getActionMasked(event) == 1) {
                        IjkPlayerView2.this._endGesture();
                    }
                }
                return false;
            }
        };
        this.mIsRenderingStart = false;
        this.mIsBufferingStart = false;
        this.mInfoListener = new IMediaPlayer.OnInfoListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.9
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnInfoListener
            public boolean onInfo(IMediaPlayer iMediaPlayer, int status, int extra) {
                IjkPlayerView2.this._switchStatus(status);
                if (IjkPlayerView2.this.mOutsideInfoListener == null) {
                    return true;
                }
                IjkPlayerView2.this.mOutsideInfoListener.onInfo(iMediaPlayer, status, extra);
                return true;
            }
        };
        this.speeds = new ArrayList();
        this.seekToPositionRunnable = new Runnable() { // from class: com.movieboxpro.android.player.IjkPlayerView2.12
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.seekTo(ijkPlayerView2.mPlayerSeek.getProgress());
                IjkPlayerView2.this.mHandler.sendEmptyMessage(IjkPlayerView2.MSG_UPDATE_SEEK);
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
        this.mHideSkipTipRunnable = new Runnable() { // from class: com.movieboxpro.android.player.IjkPlayerView2.40
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView2.this._hideSkipTip();
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
        _initView(context);
    }

    public void setPlayerViewCallback(PlayerViewCallback playerViewCallback) {
        this.playerViewCallback = playerViewCallback;
    }

    public IjkVideoView getVideoView() {
        return this.mVideoView;
    }

    private void _initView(Context context) {
        if (context instanceof AppCompatActivity) {
            this.mAttachActivity = (AppCompatActivity) context;
            View.inflate(context, R.layout.layout_player_view2, this);
            this.mVideoView = (IjkVideoView) findViewById(R.id.video_view);
            this.mPlayerThumb = (ImageView) findViewById(R.id.iv_thumb);
            this.mLoadingView = (ProgressBar) findViewById(R.id.pb_loading);
            this.mTvVolume = (TextView) findViewById(com.dl7.player.R.id.tv_volume);
            this.mTvBrightness = (TextView) findViewById(com.dl7.player.R.id.tv_brightness);
            this.mTvFastForward = (TextView) findViewById(com.dl7.player.R.id.tv_fast_forward);
            this.mFlTouchLayout = (FrameLayout) findViewById(com.dl7.player.R.id.fl_touch_layout);
            this.mIvPlay = (ImageView) findViewById(R.id.ic_iv_play);
            this.mTvCurTime = (TextView) findViewById(R.id.tv_cur_time);
            this.mPlayerSeek = (SeekBar) findViewById(R.id.player_seek);
            this.mTvEndTime = (TextView) findViewById(R.id.tv_end_time);
            this.mIvFullscreen = (ImageView) findViewById(com.dl7.player.R.id.iv_fullscreen);
            this.mLlBottomBar = (LinearLayout) findViewById(R.id.ll_bottom_bar);
            this.tvTcpSpeed = (TextView) findViewById(R.id.tvTcpSpeed);
            FrameLayout frameLayout = (FrameLayout) findViewById(R.id.fl_video_box);
            this.mFlVideoBox = frameLayout;
            frameLayout.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.2
                @Override // android.view.View.OnKeyListener
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    Log.d(IjkPlayerView2.TAG, "mIsShowCC--->" + i);
                    if (i != 4) {
                        if (i != 66) {
                            if (i == 82) {
                                if (keyEvent.getAction() == 0) {
                                    if (IjkPlayerView2.this.mIsShowCC) {
                                        Log.d(IjkPlayerView2.TAG, "mIsShowCC--->");
                                        IjkPlayerView2.this._toggleCCSelect();
                                        return true;
                                    }
                                    if (IjkPlayerView2.this.mIsShowQuality) {
                                        Log.d(IjkPlayerView2.TAG, "mIsShowQuality--->");
                                        IjkPlayerView2.this._toggleMediaQuality();
                                        return true;
                                    }
                                    if (IjkPlayerView2.this.mIsShowTracker) {
                                        IjkPlayerView2.this._toggleTrackSelect();
                                        return true;
                                    }
                                    if (IjkPlayerView2.this.mIsShowLrc) {
                                        IjkPlayerView2.this._toggleSrtSelect();
                                        return true;
                                    }
                                    if (!IjkPlayerView2.this.mIsShowBar) {
                                        Log.d(IjkPlayerView2.TAG, "mIsShowBar--->" + IjkPlayerView2.this.mSrtController.getVisibility());
                                        IjkPlayerView2.this._toggleControlBar();
                                        IjkPlayerView2.this.RequestmPlayerSeek();
                                        IjkPlayerView2.this.mSrtSelect.requestFocus();
                                    }
                                }
                                return true;
                            }
                            switch (i) {
                                case 19:
                                case 20:
                                case 21:
                                case 22:
                                    Log.d(IjkPlayerView2.TAG, "right--->");
                                    if (keyEvent.getAction() == 0) {
                                        if (IjkPlayerView2.this.mIsShowCC) {
                                            Log.d(IjkPlayerView2.TAG, "mIsShowCC--->");
                                            IjkPlayerView2.this._toggleCCSelect();
                                            return true;
                                        }
                                        if (IjkPlayerView2.this.mIsShowQuality) {
                                            Log.d(IjkPlayerView2.TAG, "mIsShowQuality--->");
                                            IjkPlayerView2.this._toggleMediaQuality();
                                            return true;
                                        }
                                        if (IjkPlayerView2.this.mIsShowTracker) {
                                            IjkPlayerView2.this._toggleTrackSelect();
                                            return true;
                                        }
                                        if (IjkPlayerView2.this.mIsShowLrc) {
                                            IjkPlayerView2.this._toggleSrtSelect();
                                            return true;
                                        }
                                        if (IjkPlayerView2.this.mIsShowBar) {
                                            Log.d(IjkPlayerView2.TAG, "mIsShowBar--->true");
                                        } else {
                                            Log.d(IjkPlayerView2.TAG, "mIsShowBar--->false");
                                        }
                                        if (!IjkPlayerView2.this.mIsShowBar) {
                                            Log.d(IjkPlayerView2.TAG, "mIsShowBar--->" + IjkPlayerView2.this.mSrtController.getVisibility());
                                            IjkPlayerView2.this._toggleControlBar();
                                            IjkPlayerView2.this.RequestmPlayerSeek();
                                            return true;
                                        }
                                    }
                                    return false;
                                case 23:
                                    break;
                                default:
                                    return false;
                            }
                        }
                        Log.d(IjkPlayerView2.TAG, "enter--->");
                        if (keyEvent.getAction() == 0) {
                            if (IjkPlayerView2.this.mIsShowCC) {
                                Log.d(IjkPlayerView2.TAG, "mIsShowCC--->");
                                IjkPlayerView2.this._toggleCCSelect();
                                return true;
                            }
                            if (IjkPlayerView2.this.mIsShowQuality) {
                                Log.d(IjkPlayerView2.TAG, "mIsShowQuality--->");
                                IjkPlayerView2.this._toggleMediaQuality();
                                return true;
                            }
                            if (IjkPlayerView2.this.mIsShowTracker) {
                                Log.d(IjkPlayerView2.TAG, "mIsShowTracker--->");
                                IjkPlayerView2.this._toggleTrackSelect();
                                return true;
                            }
                            if (IjkPlayerView2.this.mIsShowLrc) {
                                Log.d(IjkPlayerView2.TAG, "mIsShowTracker--->");
                                IjkPlayerView2.this._toggleSrtSelect();
                                return true;
                            }
                            if (!IjkPlayerView2.this.mIsShowBar) {
                                Log.d(IjkPlayerView2.TAG, "mIsShowBar--->");
                                IjkPlayerView2.this._toggleControlBar();
                                IjkPlayerView2.this._togglePlayStatus();
                                return true;
                            }
                        }
                        return false;
                    }
                    Log.d(IjkPlayerView2.TAG, "back--->");
                    return false;
                }
            });
            ImageView imageView = (ImageView) findViewById(R.id.iv_player_lock);
            this.mIvPlayerLock = imageView;
            imageView.setFocusable(true);
            this.mIvPlayerLock.setFocusableInTouchMode(true);
            this.mIvPlayCircle = (ImageView) findViewById(R.id.iv_play_circle);
            this.mTvRecoverScreen = (TextView) findViewById(R.id.tv_recover_screen);
            this.mTvReload = (TextView) findViewById(R.id.tv_reload);
            this.mFlReload = findViewById(R.id.fl_reload_layout);
            this.mSrtText = (TextView) findViewById(R.id.video_text);
            _initMediaQuality();
            _initVideoSkip();
            _initCCSlect();
            _initMediaTrack();
            this.mIvPlay.setOnClickListener(this);
            this.mIvFullscreen.setOnClickListener(this);
            this.mIvPlayerLock.setOnClickListener(this);
            this.mIvPlayCircle.setOnClickListener(this);
            this.mTvRecoverScreen.setOnClickListener(this);
            this.mTvReload.setOnClickListener(this);
            return;
        }
        throw new IllegalArgumentException("Context must be AppCompatActivity");
    }

    private void _initMediaPlayer() {
        AudioManager audioManager = (AudioManager) this.mAttachActivity.getSystemService("audio");
        this.mAudioManager = audioManager;
        this.mMaxVolume = audioManager.getStreamMaxVolume(3);
        this.mAttachActivity.getWindow().getDecorView().setSystemUiVisibility(5894);
        this.mAttachActivity.getWindow().addFlags(1024);
        this.mVideoView.setOnInfoListener(this.mInfoListener);
        this.mGestureDetector = new GestureDetector(this.mAttachActivity, this.mPlayerGestureListener);
        RequestFlVideoBox();
        OrientationEventListener orientationEventListener = new OrientationEventListener(this.mAttachActivity) { // from class: com.movieboxpro.android.player.IjkPlayerView2.3
            @Override // android.view.OrientationEventListener
            public void onOrientationChanged(int orientation) {
                IjkPlayerView2.this._handleOrientation(orientation);
            }
        };
        this.mOrientationListener = orientationEventListener;
        if (this.mIsForbidOrientation) {
            orientationEventListener.disable();
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
        this.mVideoView.setRender(2);
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
        _pauseDanmaku();
    }

    public int onStop() {
        return this.mVideoView.getCurrentPosition();
    }

    public int onDestroy() {
        int currentPosition = this.mVideoView.getCurrentPosition();
        Disposable disposable = this.tcpSpeedDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        this.mVideoView.destroy();
        IDanmakuView iDanmakuView = this.mDanmakuView;
        if (iDanmakuView != null) {
            iDanmakuView.release();
            this.mDanmakuView = null;
        }
        this.mHandler.removeMessages(MSG_TRY_RELOAD);
        this.mHandler.removeMessages(MSG_UPDATE_SEEK);
        this.mHandler.removeMessages(PARSE_SRT_CN);
        this.mHandler.removeCallbacksAndMessages(null);
        this.mAttachActivity.getWindow().clearFlags(128);
        return currentPosition;
    }

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

    public boolean onBackPressed() {
        Log.d("VIEWDOPLAY", "mIsShowCC  " + this.mIsShowCC + "  mIsShowQuality  " + this.mIsShowQuality + "  mVideoStatus  " + this.mVideoStatus + "  mIsFullscreen  " + this.mIsFullscreen + "  mIsFullscreen  " + this.mIsFullscreen);
        if (this.mIsShowCC) {
            _toggleCCSelect();
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

    public IjkPlayerView2 init(int boxType) {
        this.boxType = boxType;
        _initMediaPlayer();
        return this;
    }

    public void setPlayStatusListener(PlayStatusListener listener) {
        this.playStatusListener = listener;
    }

    public IjkPlayerView2 switchVideoPath(String url) {
        return switchVideoPath(Uri.parse(url));
    }

    public IjkPlayerView2 switchVideoPath(Uri uri) {
        List<MediaQualityInfo> list = this.mQualityData;
        if (list != null) {
            list.clear();
            this.mQualityData = null;
        }
        reset();
        return setVideoPath(uri);
    }

    public IjkPlayerView2 setVideoPath(String url) {
        if (url == null || TextUtils.isEmpty(url)) {
            return null;
        }
        String realUrl = getRealUrl(url);
        Log.d("播放链接", realUrl);
        return setVideoPath(Uri.parse(realUrl));
    }

    public void resetToAutoPlayUrl() {
        this.mLoadingView.setVisibility(0);
        setVideoPath(this.mVideoSource.get(this.mCurSelectQuality));
    }

    public void switchDomainPlayUrl(String url) {
        this.mLoadingView.setVisibility(0);
        setVideoPath(url);
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

    public IjkPlayerView2 setVideoPath(Uri uri) {
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

    public IjkPlayerView2 alwaysFullScreen() {
        this.mIsAlwaysFullScreen = true;
        _setFullScreen(true);
        this.mIvFullscreen.setVisibility(8);
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
        if (this.mIsNeverPlay) {
            this.mIsNeverPlay = false;
            this.mIvPlayCircle.setVisibility(8);
            this.tvTcpSpeed.setVisibility(0);
            this.mLoadingView.setVisibility(0);
            this.mIsShowBar = false;
        }
        this.mAttachActivity.getWindow().addFlags(128);
    }

    public void reload() {
        this.mFlReload.setVisibility(8);
        this.tvTcpSpeed.setVisibility(0);
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
        this.isFrist = true;
        if (this.mVideoView.isPlaying()) {
            this.mVideoView.pause();
        }
        _pauseDanmaku();
        this.mAttachActivity.getWindow().clearFlags(128);
    }

    public void seekTo(int position) {
        this.mVideoView.seekTo(position);
        this.mDanmakuTargetPosition = position;
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
    public void _hideAllView(boolean isTouchLock) {
        this.mFlTouchLayout.setVisibility(8);
        this.mLlBottomBar.setVisibility(8);
        _showAspectRatioOptions(false);
        if (!isTouchLock) {
            this.mIvPlayerLock.setVisibility(8);
            this.mIsShowBar = false;
        }
        if (this.mIsEnableDanmaku) {
            this.mDanmakuPlayerSeek.setVisibility(8);
        }
        if (this.mIsNeedRecoverScreen) {
            this.mTvRecoverScreen.setVisibility(8);
        }
        if (this.mIsShowQuality || this.mIsShowCC || this.mIsShowTracker) {
            return;
        }
        RequestFlVideoBox();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _setControlBarVisible(boolean isShowBar) {
        if (this.mIsNeverPlay) {
            this.mIvPlayCircle.setVisibility(isShowBar ? 0 : 8);
            return;
        }
        if (this.mIsForbidTouch) {
            this.mIvPlayerLock.setVisibility(8);
            return;
        }
        this.mLlBottomBar.setVisibility(isShowBar ? 0 : 8);
        if (!isShowBar) {
            _showAspectRatioOptions(false);
        }
        if (this.mIsFullscreen) {
            this.mIvPlayerLock.setVisibility(8);
            if (this.mIsEnableDanmaku) {
                this.mDanmakuPlayerSeek.setVisibility(isShowBar ? 0 : 8);
            }
            if (this.mIsNeedRecoverScreen) {
                this.mTvRecoverScreen.setVisibility(isShowBar ? 0 : 8);
            }
        } else {
            this.mIvPlayerLock.setVisibility(8);
            if (this.mIsEnableDanmaku) {
                this.mDanmakuPlayerSeek.setVisibility(8);
            }
            if (this.mIsNeedRecoverScreen) {
                this.mTvRecoverScreen.setVisibility(8);
            }
        }
        if (isShowBar) {
            showNavKey();
        } else {
            hideNavKey();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleControlBar() {
        boolean z = this.mIsShowBar;
        this.mIsShowBar = !z;
        if (!z) {
            Log.d(TAG, "toggleControlBar true");
        }
        _setControlBarVisible(this.mIsShowBar);
        if (this.mIsShowBar) {
            this.mHandler.postDelayed(this.mHideBarRunnable, 5000L);
            this.mHandler.sendEmptyMessage(MSG_UPDATE_SEEK);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
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

    /* JADX INFO: Access modifiers changed from: private */
    public void _togglePlayStatus() {
        RequestmIvPlay();
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
            this.mIsShowBar = false;
            RequestFlVideoBox();
            return;
        }
        ViewCompat.animate(this.mFlMediaQuality).translationX(0.0f).setDuration(300L);
        this.mFlMediaQuality.setVisibility(0);
        this.mIsShowQuality = true;
        RequestmLvMediaQuality();
        _setControlBarVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleCCSelect() {
        if (this.mFlCCSlect.getVisibility() == 8) {
            this.mFlCCSlect.setVisibility(0);
        }
        if (this.mIsShowCC) {
            this.mIsShowCC = false;
            ViewCompat.animate(this.mFlCCSlect).translationX(this.mFlCCSlect.getWidth()).setDuration(300L);
            RequestFlVideoBox();
            this.mFlCCSlect.setVisibility(8);
            this.mIsShowBar = false;
            start();
            return;
        }
        ViewCompat.animate(this.mFlCCSlect).translationX(0.0f).setDuration(300L);
        RequestmCCCountrySelect();
        this.mIsShowCC = true;
        _setControlBarVisible(false);
        pause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleTrackSelect() {
        if (this.mFlTrack.getVisibility() == 8) {
            this.mFlTrack.setVisibility(0);
        }
        if (this.mIsShowTracker) {
            ViewCompat.animate(this.mFlTrack).translationX(this.mFlTrack.getWidth()).setDuration(300L);
            this.mFlTrack.setVisibility(8);
            RequestFlVideoBox();
            this.mIsShowTracker = false;
            this.mIsShowBar = false;
            start();
            return;
        }
        ViewCompat.animate(this.mFlTrack).translationX(0.0f).setDuration(300L);
        this.mIsShowTracker = true;
        RequestmTrackSelect();
        _setControlBarVisible(false);
        pause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _toggleSrtSelect() {
        Log.d("_toggleSrtSelect", "_toggleSrtSelect" + this.mIsShowLrc);
        if (this.mLrcController.getVisibility() == 8) {
            this.mLrcController.setVisibility(0);
        }
        if (this.mIsShowLrc) {
            ViewCompat.animate(this.mLrcController).translationX(this.mLrcController.getWidth()).setDuration(300L);
            this.mLrcController.setVisibility(8);
            RequestFlVideoBox();
            this.mHandler.postDelayed(this.mHideBarRunnable, 200L);
            this.mIsShowLrc = false;
            this.mIsShowBar = false;
            start();
            return;
        }
        ViewCompat.animate(this.mLrcController).translationX(0.0f).setDuration(300L);
        this.mIsShowLrc = true;
        RequestSrt();
        _setControlBarVisible(false);
        this.mHandler.removeCallbacks(this.mHideBarRunnable);
        pause();
    }

    public long getTcpSpeed() {
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView != null) {
            return ijkVideoView.getTcpSpeed();
        }
        return 0L;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        _refreshHideRunnable();
        int id = v.getId();
        if (id == R.id.ic_iv_play || id == R.id.iv_play_circle) {
            _togglePlayStatus();
            return;
        }
        if (id == com.dl7.player.R.id.iv_fullscreen) {
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
        if (id == com.dl7.player.R.id.iv_cancel_skip) {
            this.mHandler.removeCallbacks(this.mHideSkipTipRunnable);
            _hideSkipTip();
            return;
        }
        if (id == com.dl7.player.R.id.tv_do_skip) {
            this.mLoadingView.setVisibility(0);
            this.tvTcpSpeed.setVisibility(0);
            seekTo(this.mSkipPosition);
            this.mHandler.removeCallbacks(this.mHideSkipTipRunnable);
            _hideSkipTip();
            _setProgress();
            return;
        }
        if (id == com.dl7.player.R.id.iv_danmaku_control) {
            _toggleDanmakuShow();
            return;
        }
        if (id == com.dl7.player.R.id.tv_open_edit_danmaku) {
            OnDanmakuListener onDanmakuListener = this.mDanmakuListener;
            if (onDanmakuListener == null || onDanmakuListener.isValid()) {
                editVideo();
                this.mEditDanmakuLayout.setVisibility(0);
                SoftInputUtils.setEditFocusable(this.mAttachActivity, this.mEtDanmakuContent);
                return;
            }
            return;
        }
        if (id == com.dl7.player.R.id.iv_cancel_send) {
            recoverFromEditVideo();
            return;
        }
        if (id == com.dl7.player.R.id.iv_do_send) {
            recoverFromEditVideo();
            sendDanmaku(this.mEtDanmakuContent.getText().toString(), false);
            this.mEtDanmakuContent.setText("");
            return;
        }
        if (id == com.dl7.player.R.id.input_options_more) {
            _toggleMoreColorOptions();
            return;
        }
        if (id == com.dl7.player.R.id.iv_screenshot) {
            return;
        }
        if (id == R.id.tv_recover_screen) {
            this.mVideoView.resetVideoView(true);
            this.mIsNeedRecoverScreen = false;
            this.mTvRecoverScreen.setVisibility(8);
            return;
        }
        if (id == com.dl7.player.R.id.tv_settings) {
            _showAspectRatioOptions(true);
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
            if (id != R.id.tv_meidaTrack || this.mIsShowTracker) {
                return;
            }
            _toggleTrackSelect();
        }
    }

    public IjkPlayerView2 enableOrientation() {
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
        _toggleDanmakuView(isFullscreen);
        _handleActionBar(isFullscreen);
        this.mIvFullscreen.setSelected(isFullscreen);
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
        if (this.mIsShowLrc && !isFullscreen) {
            _toggleSrtSelect();
        }
        if (this.mIsNeedRecoverScreen) {
            if (isFullscreen) {
                this.mVideoView.adjustVideoView(1.0f);
                this.mTvRecoverScreen.setVisibility(this.mIsShowBar ? 0 : 8);
            } else {
                this.mVideoView.resetVideoView(false);
                this.mTvRecoverScreen.setVisibility(8);
            }
        }
        if (isFullscreen) {
            return;
        }
        _showAspectRatioOptions(false);
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
        IjkVideoView ijkVideoView = this.mVideoView;
        if (ijkVideoView == null || this.mIsSeeking) {
            return 0;
        }
        int iMax = Math.max(ijkVideoView.getCurrentPosition(), this.mInterruptPosition);
        int duration = this.mVideoView.getDuration();
        if (duration > 0) {
            this.mPlayerSeek.setProgress(this.mVideoView.getCurrentPosition());
            if (this.mIsEnableDanmaku) {
                this.mDanmakuPlayerSeek.setProgress(iMax);
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

    private void _setFastForward(String time) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvFastForward.getVisibility() == 8) {
            this.mTvFastForward.setVisibility(0);
        }
        this.mTvFastForward.setText(time);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void _hideTouchView() {
        if (this.mFlTouchLayout.getVisibility() == 0) {
            this.mTvFastForward.setVisibility(8);
            this.mTvVolume.setVisibility(8);
            this.mTvBrightness.setVisibility(8);
            this.mFlTouchLayout.setVisibility(8);
        }
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

    private void _setVolumeInfo(int volume) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvVolume.getVisibility() == 8) {
            this.mTvVolume.setVisibility(0);
        }
        this.mTvVolume.setText(((volume * 100) / this.mMaxVolume) + "%");
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

    private void _setBrightnessInfo(float brightness) {
        if (this.mFlTouchLayout.getVisibility() == 8) {
            this.mFlTouchLayout.setVisibility(0);
        }
        if (this.mTvBrightness.getVisibility() == 8) {
            this.mTvBrightness.setVisibility(0);
        }
        this.mTvBrightness.setText(Math.ceil(brightness * 100.0f) + "%");
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
    public void _switchStatus(int status) {
        Log.i(TAG, "status " + status);
        if (status != 3) {
            if (status != 336) {
                if (status == 701) {
                    this.mIsBufferingStart = true;
                    _pauseDanmaku();
                    if (!this.mIsNeverPlay) {
                        this.tvTcpSpeed.setVisibility(0);
                        this.mLoadingView.setVisibility(0);
                    }
                    this.mHandler.removeMessages(MSG_TRY_RELOAD);
                } else if (status != 702) {
                    switch (status) {
                        case MediaPlayerParams.STATE_ERROR /* 331 */:
                            stopShowTcpSpeed();
                            this.mInterruptPosition = Math.max(this.mVideoView.getInterruptPosition(), this.mInterruptPosition);
                            pause();
                            if (this.mVideoView.getDuration() == -1 && !this.mIsReady) {
                                this.mLoadingView.setVisibility(8);
                                this.mLoadingView.setVisibility(8);
                                this.mPlayerThumb.setVisibility(8);
                                this.mIvPlayCircle.setVisibility(8);
                                this.mFlReload.setVisibility(0);
                            } else {
                                this.mLoadingView.setVisibility(0);
                                this.mLoadingView.setVisibility(0);
                                this.mHandler.sendEmptyMessage(MSG_TRY_RELOAD);
                            }
                            stopShowTcpSpeed();
                            break;
                        case MediaPlayerParams.STATE_PREPARED /* 333 */:
                            EventBus.getDefault().post(new OnPlayStatusEvent(MediaPlayerParams.STATE_PREPARED));
                            this.mIsReady = true;
                            break;
                        case MediaPlayerParams.STATE_PLAYING /* 334 */:
                            EventBus.getDefault().post(new OnPlayStatusEvent(MediaPlayerParams.STATE_PLAYING));
                            this.mHandler.removeMessages(MSG_TRY_RELOAD);
                            if (this.mIsRenderingStart && !this.mIsBufferingStart) {
                                this.mVideoView.getCurrentPosition();
                            }
                            stopShowTcpSpeed();
                            this.mLoadingView.setVisibility(8);
                            this.mLoadingView.setVisibility(8);
                            initSeekBar();
                            break;
                    }
                }
                startShowTcpSpeed();
                EventBus.getDefault().post(new OnPlayStatusEvent(MediaPlayerParams.STATE_PREPARING));
                return;
            }
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
        this.mIsRenderingStart = true;
        this.mIsBufferingStart = false;
        this.tvTcpSpeed.setVisibility(8);
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

    private void startShowTcpSpeed() {
        if (this.tcpSpeedDisposable != null) {
            this.speeds.clear();
            this.tcpSpeedDisposable.dispose();
        }
        TextView textView = this.tvTcpSpeed;
        if (textView != null) {
            textView.setText("");
        }
        Observable.interval(1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.player.IjkPlayerView2.10
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                IjkPlayerView2.this.tcpSpeedDisposable = d;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long aLong) {
                if (IjkPlayerView2.this.tvTcpSpeed == null || IjkPlayerView2.this.mVideoView == null) {
                    return;
                }
                if (IjkPlayerView2.this.tvTcpSpeed.getVisibility() != 0) {
                    IjkPlayerView2.this.tvTcpSpeed.setVisibility(0);
                }
                long tcpSpeed = IjkPlayerView2.this.mVideoView.getTcpSpeed();
                if (tcpSpeed > 0) {
                    IjkPlayerView2.this.tvTcpSpeed.setText(String.format("%sKB/S", Long.valueOf(tcpSpeed / 1024)));
                    if (aLong.longValue() <= 6) {
                        IjkPlayerView2.this.speeds.add(Long.valueOf(tcpSpeed));
                    }
                    if (aLong.longValue() != 7) {
                        if (aLong.longValue() == 25) {
                            EventBus.getDefault().post(new SwitchTestSpeedHintEvent(true));
                            return;
                        }
                        return;
                    }
                    Iterator it = IjkPlayerView2.this.speeds.iterator();
                    long jLongValue = 0;
                    while (it.hasNext()) {
                        jLongValue += ((Long) it.next()).longValue();
                    }
                    if ((jLongValue / ((long) IjkPlayerView2.this.speeds.size())) / 1024 < (IjkPlayerView2.this.bitStream > 0 ? IjkPlayerView2.this.bitStream : 128L)) {
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

    private void showTestSpeedHint() {
        EventBus.getDefault().post(new SwitchTestSpeedHintEvent(true));
    }

    private void hideTestSpeedHint() {
        EventBus.getDefault().post(new SwitchTestSpeedHintEvent(false));
    }

    private void initSeekBar() {
        this.mPlayerSeek.setMax(this.mVideoView.getDuration());
        this.mPlayerSeek.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.11
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode != 21) {
                    if (keyCode != 22) {
                        return false;
                    }
                } else {
                    if (event.getAction() == 0) {
                        IjkPlayerView2.this.mHandler.removeMessages(IjkPlayerView2.MSG_UPDATE_SEEK);
                        IjkPlayerView2.this.mHandler.removeCallbacks(IjkPlayerView2.this.seekToPositionRunnable);
                        IjkPlayerView2.this.mHandler.removeCallbacks(IjkPlayerView2.this.mHideBarRunnable);
                        int repeatCount = event.getRepeatCount();
                        if (repeatCount == 0) {
                            IjkPlayerView2.this.mPlayerSeek.setProgress(IjkPlayerView2.this.mPlayerSeek.getProgress() - 10000);
                        } else {
                            int progress = IjkPlayerView2.this.mPlayerSeek.getProgress();
                            if (repeatCount < 40) {
                                IjkPlayerView2.this.mPlayerSeek.setProgress(progress - 10000);
                            } else if (repeatCount < 70) {
                                IjkPlayerView2.this.mPlayerSeek.setProgress(progress - 20000);
                            } else {
                                IjkPlayerView2.this.mPlayerSeek.setProgress(progress - MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND);
                            }
                        }
                        IjkPlayerView2.this.mTvCurTime.setText(StringUtils.generateTime(IjkPlayerView2.this.mPlayerSeek.getProgress()));
                        IjkPlayerView2.this.mHandler.removeMessages(IjkPlayerView2.MSG_UPDATE_SEEK);
                        IjkPlayerView2.this.mHandler.postDelayed(IjkPlayerView2.this.seekToPositionRunnable, 500L);
                        IjkPlayerView2.this.mHandler.postDelayed(IjkPlayerView2.this.mHideBarRunnable, 5000L);
                        return true;
                    }
                    if (event.getAction() == 1) {
                        return true;
                    }
                }
                if (event.getAction() != 0) {
                    return event.getAction() == 1;
                }
                IjkPlayerView2.this.mHandler.removeMessages(IjkPlayerView2.MSG_UPDATE_SEEK);
                IjkPlayerView2.this.mHandler.removeCallbacks(IjkPlayerView2.this.seekToPositionRunnable);
                IjkPlayerView2.this.mHandler.removeCallbacks(IjkPlayerView2.this.mHideBarRunnable);
                int repeatCount2 = event.getRepeatCount();
                if (repeatCount2 == 0) {
                    IjkPlayerView2.this.mPlayerSeek.setProgress(IjkPlayerView2.this.mPlayerSeek.getProgress() + 10000);
                } else {
                    int progress2 = IjkPlayerView2.this.mPlayerSeek.getProgress();
                    if (repeatCount2 < 40) {
                        IjkPlayerView2.this.mPlayerSeek.setProgress(progress2 + 10000);
                    } else if (repeatCount2 < 70) {
                        IjkPlayerView2.this.mPlayerSeek.setProgress(progress2 + AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_MAX_LENGTH);
                    } else {
                        IjkPlayerView2.this.mPlayerSeek.setProgress(progress2 + MpegAudioUtil.MAX_RATE_BYTES_PER_SECOND);
                    }
                }
                IjkPlayerView2.this.mTvCurTime.setText(StringUtils.generateTime(IjkPlayerView2.this.mPlayerSeek.getProgress()));
                IjkPlayerView2.this.mHandler.removeMessages(IjkPlayerView2.MSG_UPDATE_SEEK);
                IjkPlayerView2.this.mHandler.postDelayed(IjkPlayerView2.this.seekToPositionRunnable, 500L);
                IjkPlayerView2.this.mHandler.postDelayed(IjkPlayerView2.this.mHideBarRunnable, 5000L);
                return true;
            }
        });
    }

    public void setOnPreparedListener(IMediaPlayer.OnPreparedListener l) {
        this.mVideoView.setOnPreparedListener(l);
    }

    public void setOnCompletionListener(IMediaPlayer.OnCompletionListener l) {
        this.mCompletionListener = l;
    }

    public void setOnErrorListener(IMediaPlayer.OnErrorListener l) {
        this.mVideoView.setOnErrorListener(l);
    }

    public void setOnInfoListener(IMediaPlayer.OnInfoListener l) {
        this.mOutsideInfoListener = l;
    }

    public IjkPlayerView2 setDanmakuListener(OnDanmakuListener danmakuListener) {
        this.mDanmakuListener = danmakuListener;
        return this;
    }

    private void _initCCSlect() {
        this.mFlCCSlect = findViewById(com.dl7.player.R.id.fl_cc_select);
        _init_cc_controller();
        _init_cc_lrclist();
        _init_cc_speed();
        _init_cc_size();
        _init_cc_list();
    }

    private void _init_cc_controller() {
        this.mSrtController = (LinearLayout) findViewById(R.id.ll_bottom_srt_controller);
        this.mBottomCClast = (ImageView) findViewById(R.id.ll_bottom_controller_lastside);
        this.mBottomCCSpeed = (ImageView) findViewById(R.id.ll_bottom_controller_size);
        this.mBottomCCSize = (ImageView) findViewById(R.id.ll_bottom_controller_more);
        this.mBottomCCclose = (ImageView) findViewById(R.id.ll_bottom_controller_close);
        this.mBottomTranslate = (ImageView) findViewById(R.id.ll_bottom_controller_translate);
        setFocusable(this.mBottomCClast);
        setFocusable(this.mBottomCCSpeed);
        setFocusable(this.mBottomCCSize);
        setFocusable(this.mBottomCCclose);
        this.mBottomCClast.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.13
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (IjkPlayerView2.this.mIsShowLrc) {
                    return;
                }
                Log.d(IjkPlayerView2.TAG, "mIsShowTracker--->");
                IjkPlayerView2.this._toggleSrtSelect();
            }
        });
        this.mBottomCCSpeed.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.14
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                IjkPlayerView2.this.ll_srt_speed.setVisibility(0);
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.setNoFocusable(ijkPlayerView2.srtsubstate);
                IjkPlayerView2 ijkPlayerView3 = IjkPlayerView2.this;
                ijkPlayerView3.requestFocusable(ijkPlayerView3.srtsubstate);
                IjkPlayerView2.this.mSrtController.setVisibility(8);
                IjkPlayerView2.this.mHandler.removeCallbacks(IjkPlayerView2.this.mHideBarRunnable);
            }
        });
        this.mBottomCCSize.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.15
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                IjkPlayerView2.this.ll_srt_size.setVisibility(0);
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.setFocusable(ijkPlayerView2.mSrtsizesubstate);
                IjkPlayerView2 ijkPlayerView3 = IjkPlayerView2.this;
                ijkPlayerView3.requestFocusable(ijkPlayerView3.mSrtsizesubstate);
                IjkPlayerView2.this.mSrtController.setVisibility(8);
            }
        });
        this.mBottomCCclose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.16
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.removeSRTlist(ijkPlayerView2.mSrtSlelct);
                IjkPlayerView2.this.mHandler.removeMessages(IjkPlayerView2.PARSE_SRT_CN);
                IjkPlayerView2.this.mSrtText.setText("");
                IjkPlayerView2.this.mSrtController.setVisibility(8);
                IjkPlayerView2.this._setControlBarVisible(false);
                IjkPlayerView2.this.RequestFlVideoBox();
                IjkPlayerView2.this.mIsShowBar = false;
            }
        });
        this.mBottomTranslate.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.17
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (App.getUserData().getIsvip() != 1) {
                    if (IjkPlayerView2.this.QualityListener != null) {
                        IjkPlayerView2.this.QualityListener.onClick();
                    }
                } else if (IjkPlayerView2.this.mAttachActivity != null) {
                    TranslateSubtitleActivity.INSTANCE.start(IjkPlayerView2.this.mAttachActivity, IjkPlayerView2.this.currSubtitleLanguage, "", IjkPlayerView2.this.boxType);
                }
            }
        });
    }

    private void _init_cc_lrclist() {
        this.mLrcController = findViewById(com.dl7.player.R.id.fl_srt_select);
        ListView listView = (ListView) findViewById(com.dl7.player.R.id.lv_srt);
        this.lrcView = listView;
        listView.setFocusable(true);
        this.lrcView.setClickable(true);
        AdapterSrtlist adapterSrtlist = new AdapterSrtlist(this.mAttachActivity);
        this.mAdapter = adapterSrtlist;
        this.lrcView.setAdapter((ListAdapter) adapterSrtlist);
        this.lrcView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.18
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.srtspeed = (((SRT) ijkPlayerView2.mList.get(position)).getBeginTime() - IjkPlayerView2.this.mVideoView.getCurrentPosition()) / 1000;
                TextView textView = IjkPlayerView2.this.srttext;
                StringBuilder sb = new StringBuilder();
                sb.append(IjkPlayerView2.this.srtspeed);
                textView.setText(sb);
                IjkPlayerView2.this._toggleSrtSelect();
            }
        });
    }

    private void _init_cc_list() {
        ImageView imageView = (ImageView) findViewById(R.id.tv_srtcelsect);
        this.mSrtSelect = imageView;
        imageView.setOnClickListener(this);
        setFocusable(this.mSrtSelect);
        this.mCCCountrySelect = (MyListView) findViewById(com.dl7.player.R.id.lv_cc_country);
        MyListView myListView = (MyListView) findViewById(com.dl7.player.R.id.lv_cc_quality);
        this.mCCListSelect = myListView;
        myListView.setFocusable(true);
        this.mCCListSelect.setClickable(true);
        this.mCCCountrySelect.setFocusable(true);
        this.mCCCountrySelect.setClickable(true);
        this.mCCAdapter = new AdapterMediaQuality(this.mAttachActivity);
        AdapterSRTLanguage adapterSRTLanguage = new AdapterSRTLanguage(this.mAttachActivity);
        this.mCCLanguageAdapter = adapterSRTLanguage;
        this.mCCCountrySelect.setAdapter((ListAdapter) adapterSRTLanguage);
        this.mCCListSelect.setAdapter((ListAdapter) this.mCCAdapter);
        this.mCCListSelect.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.19
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (IjkPlayerView2.this.getstr.get(position).isSelect()) {
                    IjkPlayerView2.this.removeSRTlist(position);
                    IjkPlayerView2.this.mHandler.removeMessages(IjkPlayerView2.PARSE_SRT_CN);
                    IjkPlayerView2.this.mSrtText.setText("");
                    IjkPlayerView2.this.ll_srt_size.setVisibility(8);
                    IjkPlayerView2.this.ll_srt_speed.setVisibility(8);
                    IjkPlayerView2.this.mSrtController.setVisibility(8);
                    IjkPlayerView2.this.currSelectServerSubtitle = null;
                } else {
                    IjkPlayerView2.this.listener.onClick(IjkPlayerView2.this.getstr.get(position).file_path, IjkPlayerView2.this.getstr.get(position).getLang(), IjkPlayerView2.this.getstr.get(position).sid);
                    IjkPlayerView2.this.setSRTlist(position);
                    IjkPlayerView2.this.ll_srt_size.setVisibility(8);
                    IjkPlayerView2.this.ll_srt_speed.setVisibility(8);
                    IjkPlayerView2.this.mSrtController.setVisibility(0);
                    IjkPlayerView2.this.mBottomTranslate.setImageResource(R.mipmap.ic_translate_normal);
                    IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                    ijkPlayerView2.currSelectServerSubtitle = ijkPlayerView2.getstr.get(position);
                }
                IjkPlayerView2.this._toggleCCSelect();
            }
        });
        this.mCCCountrySelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.20
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == IjkPlayerView2.this.languages.size() - 1) {
                    IjkPlayerView2.this.mCCListSelect.setVisibility(4);
                    return;
                }
                IjkPlayerView2.this.mCCListSelect.setVisibility(0);
                IjkPlayerView2.this.getstr.clear();
                IjkPlayerView2.this.mCorrentLanguage = position;
                IjkPlayerView2.this.getstr.addAll((Collection) IjkPlayerView2.this.stringListHashMap.get(((languageModel) IjkPlayerView2.this.languages.get(position)).language));
                for (int i = 0; i < IjkPlayerView2.this.getstr.size(); i++) {
                    IjkPlayerView2.this.getstr.get(i).setSelect(false);
                    if (IjkPlayerView2.this.mCurSelectSRT == IjkPlayerView2.this.mCorrentLanguage && i == IjkPlayerView2.this.mSrtSlelct) {
                        IjkPlayerView2.this.getstr.get(IjkPlayerView2.this.mSrtSlelct).setSelect(true);
                    }
                }
                IjkPlayerView2.this.mCCLanguageAdapter.setMediaLanguage(IjkPlayerView2.this.mCorrentLanguage);
                IjkPlayerView2.this.mCCAdapter.updateItems(IjkPlayerView2.this.getstr);
                parent.setDescendantFocusability(131072);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setDescendantFocusability(131072);
            }
        });
        this.mCCCountrySelect.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.21
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (!IjkPlayerView2.this.openSubtitle.equals(((languageModel) IjkPlayerView2.this.languages.get(position)).language) || IjkPlayerView2.this.playerViewCallback == null) {
                    return;
                }
                IjkPlayerView2.this.playerViewCallback.onOpenSubtitleClicked();
            }
        });
        this.mCCListSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(this) { // from class: com.movieboxpro.android.player.IjkPlayerView2.22
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.setDescendantFocusability(131072);
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
                adapterView.setDescendantFocusability(131072);
            }
        });
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
        this.ll_srt_close.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.23
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                IjkPlayerView2.this.mSrtController.setVisibility(8);
                IjkPlayerView2.this.ll_srt_speed.setVisibility(8);
                IjkPlayerView2.this.mSrtController.setVisibility(0);
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.requestFocusable(ijkPlayerView2.mBottomCCSpeed);
            }
        });
        this.srttext.setText(this.srtspeed + "");
        this.srtadd.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.24
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 23 && event.getAction() == 0) {
                    IjkPlayerView2.this.srtspeed++;
                    IjkPlayerView2.this.srttext.setText(String.format("%s s", Integer.valueOf(IjkPlayerView2.this.srtspeed)));
                }
                return false;
            }
        });
        this.srtsubstate.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.25
            @Override // android.view.View.OnKeyListener
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 23 && event.getAction() == 0) {
                    IjkPlayerView2.this.srtspeed--;
                    IjkPlayerView2.this.srttext.setText(String.format("%s s", Integer.valueOf(IjkPlayerView2.this.srtspeed)));
                }
                return false;
            }
        });
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
        setFocusable(this.mSrtsizeAdd);
        setFocusable(this.mSrtsizesubstate);
        int sizeValue = getSizeValue();
        this.srtsize = sizeValue;
        this.mSrtText.setTextSize(sizeValue);
        TextView textView2 = this.mSrtsizeText;
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.srtsize);
        textView2.setText(sb2);
        this.mSrtsizeAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.26
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                IjkPlayerView2.this.srtsize++;
                TextView textView3 = IjkPlayerView2.this.mSrtsizeText;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(IjkPlayerView2.this.srtsize);
                textView3.setText(sb3);
                IjkPlayerView2.this.mSrtText.setTextSize(IjkPlayerView2.this.srtsize);
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.saveSizeValue(ijkPlayerView2.srtsize);
            }
        });
        this.mSrtsizesubstate.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.27
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                IjkPlayerView2.this.srtsize--;
                TextView textView3 = IjkPlayerView2.this.mSrtsizeText;
                StringBuilder sb3 = new StringBuilder();
                sb3.append(IjkPlayerView2.this.srtsize);
                textView3.setText(sb3);
                IjkPlayerView2.this.mSrtText.setTextSize(IjkPlayerView2.this.srtsize);
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.saveSizeValue(ijkPlayerView2.srtsize);
            }
        });
        this.mSrtsizeClose.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.28
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                IjkPlayerView2.this.ll_srt_size.setVisibility(8);
                IjkPlayerView2.this.mSrtController.setVisibility(0);
                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                ijkPlayerView2.requestFocusable(ijkPlayerView2.mBottomCCSize);
            }
        });
    }

    public void setVideoSubtitle(List<SRTModel> srtModels, final OnDownloadClickListener listener) {
        final BaseQuickAdapter<SRTModel, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<SRTModel, BaseViewHolder>(this, R.layout.adapter_language_item, srtModels) { // from class: com.movieboxpro.android.player.IjkPlayerView2.29
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, SRTModel item) {
                helper.setText(R.id.tvLanguage, item.language);
            }
        };
        this.rvLanguage.setSelectedItemAtCentered(true);
        this.rvSubtitle.setSelectedItemAtCentered(true);
        this.rvLanguage.setAdapter(baseQuickAdapter);
        List arrayList = new ArrayList();
        if (srtModels != null && srtModels.size() > 0) {
            arrayList = srtModels.get(0).subtitles;
        }
        final BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder> baseQuickAdapter2 = new BaseQuickAdapter<SRTModel.subTitles, BaseViewHolder>(this, R.layout.adapter_video_subtitle_item, arrayList) { // from class: com.movieboxpro.android.player.IjkPlayerView2.30
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, SRTModel.subTitles item) {
                helper.setText(R.id.tvSubtitleName, item.getFile_path().substring(item.getFile_path().lastIndexOf("/") + 1));
                helper.setText(R.id.tvSelectedNum, String.format("%s Selected", Integer.valueOf(item.count)));
                ImageView imageView = (ImageView) helper.getView(R.id.ivSelected);
                if (item.isSelect()) {
                    imageView.setVisibility(0);
                } else {
                    imageView.setVisibility(8);
                }
            }
        };
        this.rvSubtitle.setAdapter(baseQuickAdapter2);
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.31
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public void onItemClick(BaseQuickAdapter<?, ?> adapter, View view, int position) {
                SRTModel.subTitles subtitles = (SRTModel.subTitles) baseQuickAdapter2.getData().get(position);
                if (subtitles.isSelect()) {
                    return;
                }
                listener.onClick(subtitles.getFile_path(), subtitles.getLang(), subtitles.sid);
                IjkPlayerView2.this.srtspeed = (int) (subtitles.delay * 1000.0f);
                IjkPlayerView2.this.ll_srt_average.setText(String.format("Most users choose %s s", Integer.valueOf(IjkPlayerView2.this.srtspeed)));
                IjkPlayerView2.this.srttext.setText(String.valueOf(IjkPlayerView2.this.srtspeed));
                IjkPlayerView2.this.ll_srt_size.setVisibility(8);
                IjkPlayerView2.this.ll_srt_speed.setVisibility(8);
                IjkPlayerView2.this.mSrtController.setVisibility(0);
                IjkPlayerView2.this._toggleCCSelect();
            }
        });
        this.rvLanguage.setOnItemListener(new TvRecyclerView.OnItemListener(this) { // from class: com.movieboxpro.android.player.IjkPlayerView2.32
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                baseQuickAdapter2.setList(((SRTModel) baseQuickAdapter.getData().get(position)).subtitles);
            }
        });
    }

    public IjkPlayerView2 setSRTList(LinkedHashMap<String, List<SRTModel.subTitles>> srtList, OnDownloadClickListener listener) {
        this.stringListHashMap.clear();
        this.languages.clear();
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
        this.mCCLanguageAdapter.updateItems(this.languages);
        return this;
    }

    public void startSRT(final ArrayList<SRT> list, String content, String language) {
        if (list != null) {
            this.srtList.clear();
            this.srtList.addAll(list);
            this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.player.IjkPlayerView2.33
                @Override // java.lang.Runnable
                public void run() {
                    IjkPlayerView2.this.setDataList(list);
                }
            });
            this.mHandler.sendEmptyMessageDelayed(PARSE_SRT_CN, 500L);
            this.mSrtController.setVisibility(0);
            this.subtitleContent = content;
            this.currSubtitleLanguage = language;
            this.subtitleType = 1;
        }
    }

    public void showTranslateSubtitle(List<SRT> srts) {
        this.mBottomTranslate.setImageResource(R.mipmap.ic_translate_selected);
        this.srtList.clear();
        this.srtList.addAll(srts);
        AdapterSrtlist adapterSrtlist = this.mAdapter;
        if (adapterSrtlist != null) {
            adapterSrtlist.updateItems(this.srtList);
        }
    }

    private void switchTansLateVisibility(boolean visible) {
        if (visible) {
            this.mBottomTranslate.setVisibility(0);
            this.mBottomCCSize.setNextFocusRightId(R.id.ll_bottom_controller_translate);
            this.mBottomCCclose.setNextFocusLeftId(R.id.ll_bottom_controller_translate);
        } else {
            this.mBottomTranslate.setVisibility(8);
            this.mBottomCCSize.setNextFocusRightId(R.id.ll_bottom_controller_close);
            this.mBottomCCclose.setNextFocusLeftId(R.id.ll_bottom_controller_more);
        }
    }

    public void startSRT2(final ArrayList<SRT> list, String content, String language) {
        this.srtList.clear();
        this.srtList.addAll(list);
        this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.player.IjkPlayerView2.34
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView2.this.setDataList(list);
            }
        });
        this.mHandler.sendEmptyMessageDelayed(PARSE_SRT_CN, 500L);
        this.mSrtController.setVisibility(0);
        this.mHandler.post(new Runnable() { // from class: com.movieboxpro.android.player.IjkPlayerView2.35
            @Override // java.lang.Runnable
            public void run() {
                IjkPlayerView2.this._toggleCCSelect();
            }
        });
        this.subtitleType = 2;
        this.subtitleContent = content;
        this.currSubtitleLanguage = language;
        this.mBottomTranslate.setImageResource(R.mipmap.ic_translate_normal);
    }

    public void setSRTlist(int quality) {
        int i = this.mCurSelectSRT;
        int i2 = this.mCorrentLanguage;
        if (i == i2 && this.mSrtSlelct == quality) {
            return;
        }
        this.mSrtSlelct = quality;
        this.mCurSelectSRT = i2;
        this.srtspeed = (int) (this.mCCAdapter.getDely(quality) * 1000.0f);
        this.ll_srt_average.setText("Most users choose " + this.srtspeed + CmcdData.STREAMING_FORMAT_SS);
        this.srttext.setText(this.srtspeed + "");
        this.mCCAdapter.setMediaQuality(quality);
    }

    public void removeSRTlist(int quality) {
        Log.d("AAAAAA", "复杂" + this.mCurSelectSRT + this.mCorrentLanguage + this.mSrtSlelct + quality);
        if (this.mCurSelectSRT == this.mCorrentLanguage && this.mSrtSlelct == quality) {
            this.mSrtSlelct = -1;
            this.mCCAdapter._cleanSelected();
            this.mCCAdapter.notifyDataSetChanged();
        }
    }

    public void setToPosition(int position) {
        if (this.mList == null || this.isFrist) {
            return;
        }
        this.lrcView.setSelection(position);
        this.mAdapter.setMediaSrt(position);
    }

    public void setDataList(List<SRT> srtPraseModels) {
        this.mList.clear();
        this.mList.addAll(srtPraseModels);
        this.isFrist = false;
        AdapterSrtlist adapterSrtlist = this.mAdapter;
        if (adapterSrtlist != null) {
            adapterSrtlist.updateItems(this.mList);
        }
    }

    public void _initMediaTrack() {
        ImageView imageView = (ImageView) findViewById(R.id.tv_meidaTrack);
        this.mFlMediaTracks = imageView;
        imageView.setOnClickListener(this);
        this.mFlTrack = findViewById(com.dl7.player.R.id.fl_media_track);
        this.MediaTrackList = (ListView) findViewById(com.dl7.player.R.id.lv_media_track);
        this.mVideoView.getInfo(new IjkVideoView.VideogetStartInfo() { // from class: com.movieboxpro.android.player.IjkPlayerView2.36
            @Override // com.movieboxpro.android.player.IjkVideoView.VideogetStartInfo
            public void getVideoStart() {
                IjkPlayerView2.this.getTrackInfo();
            }
        });
        AdapterMediaTrack adapterMediaTrack = new AdapterMediaTrack(this.mAttachActivity);
        this.mTrackAdapter = adapterMediaTrack;
        this.MediaTrackList.setAdapter((ListAdapter) adapterMediaTrack);
        this.MediaTrackList.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.37
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selectedTrack = IjkPlayerView2.this.mVideoView.getSelectedTrack(2);
                if (selectedTrack != ((TrackModel) IjkPlayerView2.this.ListTracks.get(position)).getTrackModel()) {
                    IjkPlayerView2.this.mVideoView.deselectTrack(selectedTrack);
                    IjkPlayerView2.this.mVideoView.selectTrack(((TrackModel) IjkPlayerView2.this.ListTracks.get(position)).getTrackModel());
                    IjkPlayerView2.this.mTrackAdapter.setExtrSrt(position);
                }
                IjkPlayerView2.this._toggleTrackSelect();
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
        this.mMediaQualityDesc = getResources().getStringArray(com.dl7.player.R.array.media_quality);
        this.mFlMediaQuality = findViewById(com.dl7.player.R.id.fl_media_quality);
        ImageView imageView = (ImageView) findViewById(R.id.iv_media_quality);
        this.mIvMediaQuality = imageView;
        imageView.setOnClickListener(this);
        setFocusable(this.mIvMediaQuality);
        ListView listView = (ListView) findViewById(com.dl7.player.R.id.lv_media_quality);
        this.mLvMediaQuality = listView;
        listView.setFocusable(true);
        this.mLvMediaQuality.setClickable(true);
        AdapterMediaQualitys adapterMediaQualitys = new AdapterMediaQualitys(this.mAttachActivity);
        this.mQualityAdapter = adapterMediaQualitys;
        this.mLvMediaQuality.setAdapter((ListAdapter) adapterMediaQualitys);
        this.mLvMediaQuality.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.38
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d(IjkPlayerView2.TAG, "onItemClick--->" + position);
                MediaQualityInfo item = IjkPlayerView2.this.mQualityAdapter.getItem(position);
                if (item != null) {
                    IjkPlayerView2.this.calBitStream(item.getBitstream());
                    if (App.getUserData().getIsvip() == 1) {
                        if (IjkPlayerView2.this.mCurSelectQuality != item.getIndex()) {
                            if (item.getPath() != null) {
                                IjkPlayerView2.this.changeMediaQuality(item.getIndex());
                                IjkPlayerView2.this.mLoadingView.setVisibility(0);
                                IjkPlayerView2.this.tvTcpSpeed.setVisibility(0);
                                IjkPlayerView2.this.start();
                            } else {
                                ToastUtils.showShort("Not Available");
                            }
                        }
                    } else if (IjkPlayerView2.this.QualityListener != null) {
                        IjkPlayerView2.this.QualityListener.onClick();
                    }
                }
                IjkPlayerView2.this._toggleMediaQuality();
            }
        });
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

    public IjkPlayerView2 switchVideoSource(String mediaSmooth, String mediaMedium, String mediaHigh, String mediaSuper, String mediaBd) {
        reset();
        return setVideoSource(mediaSmooth, mediaMedium, mediaHigh, mediaSuper, mediaBd);
    }

    public IjkPlayerView2 setVideoSource(String mediaSmooth, String mediaMedium, String mediaHigh, String mediaSuper, String mediaBd) {
        boolean z;
        this.mQualityData = new ArrayList();
        boolean z2 = false;
        if (mediaSmooth != null) {
            this.mVideoSource.put(0, mediaSmooth);
            this.mQualityData.add(new MediaQualityInfo(0, this.mMediaQualityDesc[0], true));
            this.mCurSelectQuality = 0;
            z = false;
        } else {
            z = true;
        }
        if (mediaMedium != null) {
            this.mVideoSource.put(1, mediaMedium);
            this.mQualityData.add(new MediaQualityInfo(1, this.mMediaQualityDesc[1], z));
            if (z) {
                this.mCurSelectQuality = 1;
            }
            z = false;
        }
        if (mediaHigh != null) {
            this.mVideoSource.put(2, mediaHigh);
            this.mQualityData.add(new MediaQualityInfo(2, this.mMediaQualityDesc[2], z));
            if (z) {
                this.mCurSelectQuality = 2;
            }
            z = false;
        }
        if (mediaSuper != null) {
            this.mVideoSource.put(3, mediaSuper);
            this.mQualityData.add(new MediaQualityInfo(3, this.mMediaQualityDesc[3], z));
            if (z) {
                this.mCurSelectQuality = 3;
            }
        } else {
            z2 = z;
        }
        if (mediaBd != null) {
            this.mVideoSource.put(4, mediaBd);
            this.mQualityData.add(new MediaQualityInfo(4, this.mMediaQualityDesc[4], z2));
            if (z2) {
                this.mCurSelectQuality = 4;
            }
        }
        this.mQualityAdapter.updateItems(this.mQualityData);
        setVideoPath(this.mVideoSource.get(this.mCurSelectQuality));
        return this;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:42:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:43:0x00da  */
    /* JADX WARN: Code duplicated, block: B:46:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:49:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:50:0x00ea  */
    /* JADX WARN: Code duplicated, block: B:53:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:54:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:57:0x00fa  */
    /* JADX WARN: Code duplicated, block: B:58:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:61:0x0103  */
    /* JADX WARN: Code duplicated, block: B:64:0x0108  */
    /* JADX WARN: Code duplicated, block: B:65:0x0112  */
    /* JADX WARN: Code duplicated, block: B:66:0x011c  */
    /* JADX WARN: Code duplicated, block: B:67:0x0126  */
    /* JADX WARN: Code duplicated, block: B:68:0x012e  */
    /* JADX WARN: Code duplicated, block: B:71:0x013d  */
    /* JADX WARN: Code duplicated, block: B:9:0x0037  */
    public IjkPlayerView2 setVideoSource(List<MediaQualityInfo> mediaMedium, OnVipClickListener listener) {
        String real_quality;
        if (mediaMedium == null) {
            return this;
        }
        this.QualityListener = listener;
        this.mQualityData = new ArrayList();
        int i = 0;
        while (true) {
            if (i < mediaMedium.size()) {
                String real_quality2 = mediaMedium.get(i).getReal_quality();
                real_quality2.hashCode();
                switch (real_quality2) {
                    case "4K":
                    case "org":
                    case "360p":
                    case "720p":
                    case "1080p":
                        this.mVideoSource.put(i, mediaMedium.get(i).getPath());
                        mediaMedium.get(i).setIndex(i);
                        mediaMedium.get(i).setSelect(false);
                        this.mQualityData.add(mediaMedium.get(i));
                        break;
                }
                i++;
            } else {
                for (int i2 = 0; i2 < mediaMedium.size(); i2++) {
                    if (!TextUtils.isEmpty(mediaMedium.get(i2).getPath())) {
                        this.mCurSelectQuality = i2;
                        this.mQualityAdapter.updateItems(this.mQualityData);
                        this.mQualityAdapter.setMediaQuality(this.mCurSelectQuality);
                        real_quality = mediaMedium.get(this.mCurSelectQuality).getReal_quality();
                        real_quality.hashCode();
                        switch (real_quality.hashCode()) {
                            case 1687:
                                if (real_quality.equals("4K")) {
                                }
                                break;
                            case 110308:
                                if (real_quality.equals("org")) {
                                }
                                break;
                            case 1572835:
                                if (real_quality.equals("360p")) {
                                }
                                break;
                            case 1688155:
                                if (real_quality.equals("720p")) {
                                }
                                break;
                            case 46737913:
                                if (!real_quality.equals("1080p")) {
                                }
                                break;
                            default:
                                break;
                        }
                        /*  JADX ERROR: Method code generation error
                            java.lang.NullPointerException: Switch insn not found in header
                            	at java.base/java.util.Objects.requireNonNull(Objects.java:259)
                            	at jadx.core.codegen.RegionGen.makeSwitch(RegionGen.java:246)
                            	at jadx.core.dex.regions.SwitchRegion.generate(SwitchRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:126)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:195)
                            	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:173)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:140)
                            	at jadx.core.dex.regions.conditions.IfRegion.generate(IfRegion.java:90)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:83)
                            	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:175)
                            	at jadx.core.dex.regions.loops.LoopRegion.generate(LoopRegion.java:173)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.dex.regions.Region.generate(Region.java:35)
                            	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                            	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:291)
                            	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:270)
                            	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:420)
                            	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:345)
                            	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:299)
                            	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                            	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                            	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                            	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                            */
                        /*
                            Method dump skipped, instruction units count: 434
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.movieboxpro.android.player.IjkPlayerView2.setVideoSource(java.util.List, com.movieboxpro.android.player.IjkPlayerView2$OnVipClickListener):com.movieboxpro.android.player.IjkPlayerView2");
                    }

                    public IjkPlayerView2 changeMediaQuality(int quality) {
                        List<MediaQualityInfo> list;
                        if (this.mCurSelectQuality != quality && this.mVideoSource.get(quality) != null && (list = this.mQualityData) != null && list.size() >= quality) {
                            this.mQualityAdapter.setMediaQuality(quality);
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
                            if (this.mVideoView.isPlaying()) {
                                this.mCurPosition = this.mVideoView.getCurrentPosition();
                                this.mVideoView.release(false);
                            }
                            this.mVideoView.setRender(2);
                            setVideoPath(this.mVideoSource.get(quality));
                        }
                        return this;
                    }

                    public IjkPlayerView2 setMediaQuality(int quality) {
                        List<MediaQualityInfo> list;
                        if (this.mCurSelectQuality != quality && this.mVideoSource.get(quality) != null && (list = this.mQualityData) != null && list.size() >= quality) {
                            this.mQualityAdapter.setMediaQuality(quality);
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

                    public int getCurPosition() {
                        return this.mVideoView.getCurrentPosition();
                    }

                    public int getDurarion() {
                        return this.mVideoView.getDuration();
                    }

                    public IjkPlayerView2 setSkipTip(int targetPosition) {
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
                        this.mHandler.postDelayed(this.mHideSkipTipRunnable, C.DEFAULT_SEEK_FORWARD_INCREMENT_MS);
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public void _hideSkipTip() {
                        if (this.mLlSkipLayout.getVisibility() == 8) {
                            return;
                        }
                        ViewCompat.animate(this.mLlSkipLayout).translationX(-this.mLlSkipLayout.getWidth()).alpha(0.0f).setDuration(500L).setListener(new ViewPropertyAnimatorListenerAdapter() { // from class: com.movieboxpro.android.player.IjkPlayerView2.39
                            @Override // androidx.core.view.ViewPropertyAnimatorListenerAdapter, androidx.core.view.ViewPropertyAnimatorListener
                            public void onAnimationEnd(View view) {
                                IjkPlayerView2.this.mLlSkipLayout.setVisibility(8);
                            }
                        }).start();
                        this.mSkipPosition = -1;
                    }

                    private void _initDanmaku() {
                        this.mDanmakuView = (IDanmakuView) findViewById(R.id.sv_danmaku);
                        this.mIvDanmakuControl = (ImageView) findViewById(com.dl7.player.R.id.iv_danmaku_control);
                        this.mTvOpenEditDanmaku = (TextView) findViewById(com.dl7.player.R.id.tv_open_edit_danmaku);
                        this.mTvTimeSeparator = (TextView) findViewById(com.dl7.player.R.id.tv_separator);
                        this.mEditDanmakuLayout = findViewById(com.dl7.player.R.id.ll_edit_danmaku);
                        this.mEtDanmakuContent = (EditText) findViewById(com.dl7.player.R.id.et_danmaku_content);
                        this.mIvCancelSend = (ImageView) findViewById(com.dl7.player.R.id.iv_cancel_send);
                        this.mIvDoSend = (ImageView) findViewById(com.dl7.player.R.id.iv_do_send);
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
                        this.mMoreOptionsWidth = getResources().getDimensionPixelOffset(com.dl7.player.R.dimen.danmaku_input_options_color_radio_btn_size) * 12;
                        this.mDanmakuOptionsBasic = findViewById(com.dl7.player.R.id.input_options_basic);
                        View viewFindViewById = findViewById(com.dl7.player.R.id.input_options_more);
                        this.mDanmakuMoreOptions = viewFindViewById;
                        viewFindViewById.setOnClickListener(this);
                        this.mDanmakuCurColor = (RadioButton) findViewById(com.dl7.player.R.id.input_options_color_current);
                        this.mDanmakuMoreColorIcon = (ImageView) findViewById(com.dl7.player.R.id.input_options_color_more_icon);
                        this.mDanmakuTextSizeOptions = (RadioGroup) findViewById(com.dl7.player.R.id.input_options_group_textsize);
                        this.mDanmakuTypeOptions = (RadioGroup) findViewById(com.dl7.player.R.id.input_options_group_type);
                        this.mDanmakuColorOptions = (RadioGroup) findViewById(com.dl7.player.R.id.input_options_color_group);
                        this.mDanmakuTextSizeOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.41
                            @Override // android.widget.RadioGroup.OnCheckedChangeListener
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == com.dl7.player.R.id.input_options_small_textsize) {
                                    IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                                    ijkPlayerView2.mDanmakuTextSize = (ijkPlayerView2.mDanmakuParser.getDisplayer().getDensity() - 0.6f) * 25.0f * 0.7f;
                                } else if (checkedId == com.dl7.player.R.id.input_options_medium_textsize) {
                                    IjkPlayerView2 ijkPlayerView3 = IjkPlayerView2.this;
                                    ijkPlayerView3.mDanmakuTextSize = (ijkPlayerView3.mDanmakuParser.getDisplayer().getDensity() - 0.6f) * 25.0f;
                                }
                            }
                        });
                        this.mDanmakuTypeOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.42
                            @Override // android.widget.RadioGroup.OnCheckedChangeListener
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                if (checkedId == com.dl7.player.R.id.input_options_rl_type) {
                                    IjkPlayerView2.this.mDanmakuType = 1;
                                } else if (checkedId == com.dl7.player.R.id.input_options_top_type) {
                                    IjkPlayerView2.this.mDanmakuType = 5;
                                } else if (checkedId == com.dl7.player.R.id.input_options_bottom_type) {
                                    IjkPlayerView2.this.mDanmakuType = 4;
                                }
                            }
                        });
                        this.mDanmakuColorOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.43
                            @Override // android.widget.RadioGroup.OnCheckedChangeListener
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                String str = (String) IjkPlayerView2.this.findViewById(checkedId).getTag();
                                IjkPlayerView2.this.mDanmakuTextColor = Color.parseColor(str);
                                IjkPlayerView2.this.mDanmakuCurColor.setBackgroundColor(IjkPlayerView2.this.mDanmakuTextColor);
                            }
                        });
                    }

                    private void _loadDanmaku() {
                        if (this.mIsEnableDanmaku) {
                            this.mDanmakuContext = DanmakuContext.create();
                            if (this.mDanmakuParser == null) {
                                this.mDanmakuParser = new BaseDanmakuParser(this) { // from class: com.movieboxpro.android.player.IjkPlayerView2.44
                                    /* JADX INFO: Access modifiers changed from: protected */
                                    @Override // master.flame.danmaku.danmaku.parser.BaseDanmakuParser
                                    public Danmakus parse() {
                                        return new Danmakus();
                                    }
                                };
                            }
                            this.mDanmakuView.setCallback(new DrawHandler.Callback() { // from class: com.movieboxpro.android.player.IjkPlayerView2.45
                                @Override // master.flame.danmaku.controller.DrawHandler.Callback
                                public void danmakuShown(BaseDanmaku danmaku) {
                                }

                                @Override // master.flame.danmaku.controller.DrawHandler.Callback
                                public void drawingFinished() {
                                }

                                @Override // master.flame.danmaku.controller.DrawHandler.Callback
                                public void updateTimer(DanmakuTimer timer) {
                                }

                                @Override // master.flame.danmaku.controller.DrawHandler.Callback
                                public void prepared() {
                                    if (!IjkPlayerView2.this.mVideoView.isPlaying() || IjkPlayerView2.this.mIsBufferingStart) {
                                        return;
                                    }
                                    IjkPlayerView2.this.mDanmakuView.start();
                                }
                            });
                            this.mDanmakuView.enableDanmakuDrawingCache(true);
                            this.mDanmakuView.prepare(this.mDanmakuParser, this.mDanmakuContext);
                        }
                    }

                    public IjkPlayerView2 enableDanmaku() {
                        this.mIsEnableDanmaku = true;
                        _initDanmaku();
                        if (this.mIsAlwaysFullScreen) {
                            _toggleDanmakuView(true);
                        }
                        return this;
                    }

                    public IjkPlayerView2 enableDanmaku(boolean isEnable) {
                        this.mIsEnableDanmaku = isEnable;
                        if (isEnable) {
                            _initDanmaku();
                            if (this.mIsAlwaysFullScreen) {
                                _toggleDanmakuView(true);
                            }
                        }
                        return this;
                    }

                    public IjkPlayerView2 setDanmakuSource(InputStream stream) {
                        if (stream == null) {
                            return this;
                        }
                        if (!this.mIsEnableDanmaku) {
                            throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
                        }
                        if (this.mDanmakuLoader == null) {
                            this.mDanmakuLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
                        }
                        try {
                            this.mDanmakuLoader.load(stream);
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

                    public IjkPlayerView2 setDanmakuSource(String uri) {
                        if (TextUtils.isEmpty(uri)) {
                            return this;
                        }
                        if (!this.mIsEnableDanmaku) {
                            throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
                        }
                        if (this.mDanmakuLoader == null) {
                            this.mDanmakuLoader = DanmakuLoaderFactory.create(DanmakuLoaderFactory.TAG_BILI);
                        }
                        try {
                            this.mDanmakuLoader.load(uri);
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

                    public IjkPlayerView2 setDanmakuCustomParser(BaseDanmakuParser parser, ILoader loader, BaseDanmakuConverter converter) {
                        this.mDanmakuParser = parser;
                        this.mDanmakuLoader = loader;
                        this.mDanmakuConverter = converter;
                        return this;
                    }

                    public IjkPlayerView2 showOrHideDanmaku(boolean isShow) {
                        if (isShow) {
                            this.mIvDanmakuControl.setSelected(false);
                            this.mDanmakuView.show();
                        } else {
                            this.mIvDanmakuControl.setSelected(true);
                            this.mDanmakuView.hide();
                        }
                        return this;
                    }

                    public void sendDanmaku(String text, boolean isLive) {
                        if (!this.mIsEnableDanmaku) {
                            throw new RuntimeException("Danmaku is disable, use enableDanmaku() first");
                        }
                        if (TextUtils.isEmpty(text)) {
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
                        baseDanmakuCreateDanmaku.text = text;
                        baseDanmakuCreateDanmaku.padding = 5;
                        baseDanmakuCreateDanmaku.isLive = isLive;
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
                        Log.d("VIEWDOPLAY", "mIsShowCC" + this.mIsShowCC + "mIsShowQuality" + this.mIsShowQuality + "mVideoStatus" + this.mVideoStatus);
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

                    private void _toggleDanmakuView(boolean isShow) {
                        if (this.mIsEnableDanmaku) {
                            if (isShow) {
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

                    public class NetBroadcastReceiver extends BroadcastReceiver {
                        public NetBroadcastReceiver() {
                        }

                        @Override // android.content.BroadcastReceiver
                        public void onReceive(Context context, Intent intent) {
                            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
                                IjkPlayerView2 ijkPlayerView2 = IjkPlayerView2.this;
                                ijkPlayerView2.mIsNetConnected = NetWorkUtils.isNetworkAvailable(ijkPlayerView2.mAttachActivity);
                            }
                        }
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public void RequestFlVideoBox() {
                        this.mFlVideoBox.setFocusableInTouchMode(true);
                        this.mFlVideoBox.requestFocus();
                    }

                    private void RequestmCCListSelect() {
                        this.mCCListSelect.setFocusableInTouchMode(true);
                        this.mCCListSelect.requestFocus();
                    }

                    private void RequestmCCCountrySelect() {
                        this.mCCCountrySelect.setFocusableInTouchMode(true);
                        this.mCCCountrySelect.requestFocus();
                    }

                    private void RequestmTrackSelect() {
                        this.MediaTrackList.setFocusableInTouchMode(true);
                        this.MediaTrackList.requestFocus();
                    }

                    private void RequestmLvMediaQuality() {
                        this.mLvMediaQuality.setFocusableInTouchMode(true);
                        this.mLvMediaQuality.requestFocus();
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

                    private void RequestSrt() {
                        this.lrcView.setFocusableInTouchMode(true);
                        this.lrcView.requestFocus();
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
                        view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.46
                            @Override // android.view.View.OnFocusChangeListener
                            public void onFocusChange(View v, boolean hasFocus) {
                                if (IjkPlayerView2.this.mIsShowBar && hasFocus) {
                                    IjkPlayerView2.this._showControlBar(5000);
                                }
                            }
                        });
                    }

                    /* JADX INFO: Access modifiers changed from: private */
                    public void setNoFocusable(View view) {
                        view.setFocusable(true);
                        view.setClickable(true);
                        view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.player.IjkPlayerView2.47
                            @Override // android.view.View.OnFocusChangeListener
                            public void onFocusChange(View v, boolean hasFocus) {
                                IjkPlayerView2.this.mHandler.removeCallbacks(IjkPlayerView2.this.mHideBarRunnable);
                            }
                        });
                    }
                }
