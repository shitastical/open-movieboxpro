package com.movieboxpro.android.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Rational;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.SeekBar;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.bumptech.glide.Glide;
import com.dl7.player.model.SRTModel;
import com.dl7.player.utils.SRT;
import com.dueeeke.model.ResponseSubtitleRecord;
import com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener;
import com.dueeeke.videoplayer.player.IjkPlayer;
import com.dueeeke.videoplayer.player.PlayerConfig;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.App$$ExternalSyntheticApiModelOutline0;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.event.EnterPicModeEvent;
import com.movieboxpro.android.event.OnPlayStatusEvent;
import com.movieboxpro.android.event.OnPlayerFinishEvent;
import com.movieboxpro.android.event.OpenSubtitleSelectEvent;
import com.movieboxpro.android.event.RefreshPlayUrlEvent;
import com.movieboxpro.android.event.SwitchTestSpeedHintEvent;
import com.movieboxpro.android.event.TranslateSubtitleEvent;
import com.movieboxpro.android.exo.ExoPlayerView;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.CallManager;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.OnDownloadClickListener;
import com.movieboxpro.android.listener.OnVipClickListener;
import com.movieboxpro.android.listener.PlayerViewCallback;
import com.movieboxpro.android.listener.VideoPreviewListener;
import com.movieboxpro.android.livedata.OnPlayerFinishLiveData;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.livedata.RefreshWatchingLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.MySubtitleResponse;
import com.movieboxpro.android.model.OpenSubtitleModel;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.player.vlc.VlcPlayerView;
import com.movieboxpro.android.presenter.setting.SRTPresenter;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.utils.CommUtils;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GroupUtils;
import com.movieboxpro.android.utils.HttpHelper;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ReaderUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.activity.videoplayer.videoview.NormalIjkVideoView;
import com.movieboxpro.android.view.dialog.ChoosePlayerEngineDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.OpenSubtitleDialog;
import com.movieboxpro.android.view.dialog.TestNetHintDialog;
import com.movieboxpro.android.view.dialog.TestNetSpeedFragment;
import com.movieboxpro.android.view.dialog.VipExpireDialog;
import com.movieboxpro.android.view.fragment.account.VipChargeGuideDialog;
import com.movieboxpro.androidtv.BuildConfig;
import com.movieboxpro.androidtv.R;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import retrofit2.Response;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes5.dex */
public class VideoPlayerActivity extends BaseActivity implements SRTPresenter, TestNetSpeedFragment.OnSelectServerListener {
    public static final String IS_FROM_FEATURED = "isFromFeatured";
    public static final String IS_NEXT = "is_next";
    public static final String IS_RESTART = "is_restart";
    public static final int SHOW_TEST_NET_DELAY = 10;
    public static final String VIDEO_EPISODE = "videoplayer_episode";
    public static final String VIDEO_ID = "videoplayer_id";
    public static final String VIDEO_PARAMS = "videoplayer_params";
    private boolean enterPicMode;
    private Disposable feedbackDisposable;
    FrameLayout flContainer;
    FrameLayout flPreview;
    private boolean isComplete;
    private boolean isFromPicIn;
    private long lastPos;
    private long lastTime;
    private Disposable mDisposable;
    private boolean mIsRestart;
    private IPlayerView mPlayerView;
    private BaseMediaModel movieDownload;
    private String movieid;
    private OpenSubtitleDialog openSubtitleDialogFragment;
    private String preViewUrl;
    private SharedPreferences prefs;
    protected NormalIjkVideoView previewIjkVideoView;
    protected boolean previewVideoPrepared;
    private Disposable saveFeedbackDisposable;
    private Disposable saveProgressDisposable;
    private Disposable showTestNetDisposable;
    private TestNetHintDialog testNetHintDialog;
    private TestNetSpeedFragment testNetSpeedFragment;
    LinkedHashMap<String, List<SRTModel.subTitles>> srtHash = new LinkedHashMap<>();
    List<MediaQualityInfo> list = new ArrayList();
    List<BaseMediaModel.DownloadFile> hlsAudioTracks = new ArrayList();
    private List<OpenSubtitleModel> openSubtitleModels = new ArrayList();
    private String lastServerDomain = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "");
    private Handler handler = new Handler(Looper.getMainLooper());
    OnVipClickListener onVipClickListener = new OnVipClickListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.1
        @Override // com.movieboxpro.android.listener.OnVipClickListener
        public void onClick() {
            if (App.getUserData().dead_time != 0) {
                new VipExpireDialog.Builder(VideoPlayerActivity.this).setCancelable(false).setTitle("Your VIP has expired.").setContent(String.format("Expire: %s", TimeUtils.formatTime4(App.getUserData().dead_time * 1000))).setPositiveText("Renew").setNegativeText("Close").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.1.2
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        new VipChargeGuideDialog().show(VideoPlayerActivity.this.getSupportFragmentManager(), "VipChargeGuideDialog");
                    }
                }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.1.1
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        VideoPlayerActivity.this.getUserInfo();
                    }
                }).create().show();
            } else {
                new VipExpireDialog.Builder(VideoPlayerActivity.this).setCancelable(false).setTitle("You're not VIP yet").setContent("").setPositiveText("Subscribe").setNegativeText("Close").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.1.4
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        new VipChargeGuideDialog().show(VideoPlayerActivity.this.getSupportFragmentManager(), "VipChargeGuideDialog");
                    }
                }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.1.3
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        VideoPlayerActivity.this.getUserInfo();
                    }
                }).create().show();
            }
        }
    };
    OnDownloadClickListener onDownloadClickListener = new OnDownloadClickListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.3
        @Override // com.movieboxpro.android.listener.OnDownloadClickListener
        public void onClick(String url, String language, String sid) {
            VideoPlayerActivity.this.Movie_srt_select(sid);
            VideoPlayerActivity.this.getSRT(url, language);
        }
    };
    private MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
    private boolean firstPreview = true;
    private final VideoPreviewListener videoPreviewListener = new VideoPreviewListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.6
        @Override // com.movieboxpro.android.listener.VideoPreviewListener
        public void onProgressChanged(SeekBar seekBar, int pos, int progress) {
            CommonUtils.calPercent((int) (((long) pos) - VideoPlayerActivity.this.lastPos), seekBar.getMax());
            VideoPlayerActivity.this.lastPos = progress;
            if (System.currentTimeMillis() - VideoPlayerActivity.this.lastTime > 1500) {
                VideoPlayerActivity.this.lastTime = System.currentTimeMillis();
                if (VideoPlayerActivity.this.firstPreview) {
                    VideoPlayerActivity.this.handler.postDelayed(VideoPlayerActivity.this.seekRunnable, 0L);
                } else {
                    VideoPlayerActivity.this.handler.postDelayed(VideoPlayerActivity.this.seekRunnable, 1000L);
                }
            }
            int width = (int) (((long) ((progress / 1000) * seekBar.getWidth())) / ((long) (seekBar.getMax() / 1000)));
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) VideoPlayerActivity.this.flPreview.getLayoutParams();
            layoutParams.leftMargin = width;
            VideoPlayerActivity.this.flPreview.setLayoutParams(layoutParams);
        }

        @Override // com.movieboxpro.android.listener.VideoPreviewListener
        public void startDrag() {
            VideoPlayerActivity.this.firstPreview = true;
            VideoPlayerActivity.this.handler.postDelayed(VideoPlayerActivity.this.showPreview, 500L);
            VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
            videoPlayerActivity.lastPos = videoPlayerActivity.mPlayerView.getCurPosition();
            VideoPlayerActivity.this.handler.postDelayed(VideoPlayerActivity.this.seekRunnable, 0L);
        }

        @Override // com.movieboxpro.android.listener.VideoPreviewListener
        public void stopDrag() {
            if (VideoPlayerActivity.this.handler == null || VideoPlayerActivity.this.flPreview == null) {
                return;
            }
            VideoPlayerActivity.this.handler.removeCallbacks(VideoPlayerActivity.this.showPreview);
            VideoPlayerActivity.this.flPreview.setVisibility(8);
        }
    };
    private final Runnable showPreview = new Runnable() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.7
        @Override // java.lang.Runnable
        public void run() {
            VideoPlayerActivity.this.flPreview.setVisibility(0);
        }
    };
    private boolean firstPlay = true;
    private boolean isFirstPlay = true;
    private final Runnable seekRunnable = new Runnable() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.31
        @Override // java.lang.Runnable
        public void run() {
            if (VideoPlayerActivity.this.previewVideoPrepared) {
                VideoPlayerActivity.this.previewIjkVideoView.seekTo(VideoPlayerActivity.this.lastPos);
                Log.d(VideoPlayerActivity.this.TAG, "onProgressChanged: progress-" + VideoPlayerActivity.this.lastPos);
            }
        }
    };

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isRequireNetWork() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isShowWarning() {
        return false;
    }

    public static void start(Context context, MovieDetail movieDetail, String id, boolean isRestart) {
        Intent intent = new Intent(context, (Class<?>) VideoPlayerActivity.class);
        intent.putExtra("videoplayer_params", movieDetail);
        intent.putExtra(VIDEO_ID, id);
        intent.putExtra(IS_RESTART, isRestart);
        context.startActivity(intent);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) VideoPlayerActivity.class));
    }

    public static void start(Context context, BaseMediaModel movieDetail, String id) {
        Intent intent = new Intent(context, (Class<?>) VideoPlayerActivity.class);
        intent.putExtra("videoplayer_params", movieDetail);
        intent.putExtra(VIDEO_ID, id);
        context.startActivity(intent);
    }

    public static void startForResult(Activity context, MovieDetail movieDetail, String id) {
        Intent intent = new Intent(context, (Class<?>) VideoPlayerActivity.class);
        intent.putExtra("videoplayer_params", movieDetail);
        intent.putExtra(VIDEO_ID, id);
        context.startActivityForResult(intent, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        ((ObservableSubscribeProxy) Http.getService().Userinfo2(API.USER.USERINFO, App.getUserData().uid_v2, SystemUtils.getUniqueId(this), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<UserModel.UserData>(this) { // from class: com.movieboxpro.android.player.VideoPlayerActivity.2
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
            public void onNext(UserModel.UserData userData) throws Throwable {
                App.updateUser(userData);
            }
        });
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public View loadView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.activity_videoplayer2, container, false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onOpenSubtitleSelect(OpenSubtitleSelectEvent event) {
        OpenSubtitleDialog openSubtitleDialog = this.openSubtitleDialogFragment;
        if (openSubtitleDialog != null && openSubtitleDialog.isVisible()) {
            this.openSubtitleDialogFragment.dismissAllowingStateLoss();
        }
        ((ObservableSubscribeProxy) Observable.just(event).map(new Function<OpenSubtitleSelectEvent, String>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.5
            @Override // io.reactivex.functions.Function
            public String apply(OpenSubtitleSelectEvent openSubtitleSelectEvent) {
                if (VideoPlayerActivity.this.mPlayerView != null) {
                    VideoPlayerActivity.this.mPlayerView.startSRT2(new ArrayList<>(openSubtitleSelectEvent.getSrts()), openSubtitleSelectEvent.getContent(), openSubtitleSelectEvent.getLanguage(), openSubtitleSelectEvent.getSid());
                    return "";
                }
                return "";
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.4
            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String s) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                if (VideoPlayerActivity.this.mPlayerView != null) {
                    VideoPlayerActivity.this.mPlayerView._togglePlayStatus(false);
                }
            }
        });
    }

    public Bitmap getFramePictures(String url, long timeUs) {
        if (this.mediaMetadataRetriever == null) {
            this.mediaMetadataRetriever = new MediaMetadataRetriever();
            HashMap map = new HashMap();
            map.put("User-Agent", "Mozilla/5.0 (Linux; U; Android 4.4.2; zh-CN; MW-KW-001 Build/JRO03C) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 UCBrowser/1.0.0.001 U4/0.8.0 Mobile Safari/533.1");
            this.mediaMetadataRetriever.setDataSource(url, map);
        }
        try {
            return this.mediaMetadataRetriever.getFrameAtTime(timeUs * 1000, 3);
        } catch (Exception unused) {
            Log.d("da", "dasda");
            return null;
        }
    }

    private void initPlayerEngine(String engine) {
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.onDestroy();
        }
        this.flContainer.removeAllViews();
        if ("vlc".equals(engine)) {
            VlcPlayerView vlcPlayerView = new VlcPlayerView(this);
            this.flContainer.addView(vlcPlayerView);
            this.mPlayerView = vlcPlayerView;
        } else if ("ijk".equals(engine)) {
            IjkPlayerView ijkPlayerView = new IjkPlayerView(this);
            this.flContainer.addView(ijkPlayerView);
            this.mPlayerView = ijkPlayerView;
        } else if ("exo".equals(engine)) {
            ExoPlayerView exoPlayerView = new ExoPlayerView(this);
            this.flContainer.addView(exoPlayerView);
            this.mPlayerView = exoPlayerView;
        } else {
            ExoPlayerView exoPlayerView2 = new ExoPlayerView(this);
            this.flContainer.addView(exoPlayerView2);
            this.mPlayerView = exoPlayerView2;
        }
        this.mPlayerView.requestRootFocues();
        this.mPlayerView.init(1).alwaysFullScreen().enableOrientation().setOnCompletionListener(new IMediaPlayer.OnCompletionListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.8
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                VideoPlayerActivity.this.isComplete = true;
                VideoPlayerActivity.this.finish();
            }
        });
        this.mPlayerView.setMovieDownload(this.movieDownload);
        this.mPlayerView.setFragmentManager(getSupportFragmentManager());
        this.mPlayerView.setPlayerViewCallback(new PlayerViewCallback() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.9
            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void lastVideo() {
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void nextVideo() {
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void showEpisodeList() {
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void onOpenSubtitleClicked() {
                VideoPlayerActivity.this.showOpenSubtitle();
                VideoPlayerActivity.this.mPlayerView.doPause();
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void disSelectSubtitle(String sid) {
                VideoPlayerActivity.this.disSelectSubtitle(sid);
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void switchQuality(int pos, MediaQualityInfo qualityInfo) {
                String str = qualityInfo.isHls() ? "exo" : null;
                VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
                videoPlayerActivity.excuetSer(true, pos, videoPlayerActivity.mPlayerView.getCurPosition(), true, str);
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void onOnlineQuality(List<MediaQualityInfo> list, List<BaseMediaModel.DownloadFile> files) {
                VideoPlayerActivity.this.hlsAudioTracks.clear();
                VideoPlayerActivity.this.list.clear();
                VideoPlayerActivity.this.movieDownload.list.clear();
                VideoPlayerActivity.this.movieDownload.list.addAll(files);
                for (BaseMediaModel.DownloadFile downloadFile : VideoPlayerActivity.this.movieDownload.list) {
                    if ("video".equals(downloadFile.type) || downloadFile.type == null || TextUtils.isEmpty(downloadFile.type)) {
                        VideoPlayerActivity.this.list.add(new MediaQualityInfo(0, downloadFile.h265, downloadFile.path, downloadFile.real_quality, downloadFile.size, TimeUtils.formatTime4(downloadFile.dateline * 1000), downloadFile.count, downloadFile.vip_only + "", downloadFile.filename, false, downloadFile.bitstream, downloadFile.original, downloadFile.hdr, downloadFile.colorbit, downloadFile.filename, downloadFile.hls, downloadFile.mmfid, downloadFile.tmfid));
                    } else if ("audio".equals(downloadFile.type)) {
                        VideoPlayerActivity.this.hlsAudioTracks.add(downloadFile);
                    }
                }
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void saveProgress() {
                VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
                videoPlayerActivity.saveInDao(videoPlayerActivity.mPlayerView.getVideoView().getCurrentPosition(), VideoPlayerActivity.this.mPlayerView.getCurrQuality());
                VideoPlayerActivity videoPlayerActivity2 = VideoPlayerActivity.this;
                videoPlayerActivity2.Movie_play(videoPlayerActivity2.mPlayerView.getVideoView().getCurrentPosition());
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void showPlayerEngine() {
                ChoosePlayerEngineDialog choosePlayerEngineDialogNewInstance = ChoosePlayerEngineDialog.INSTANCE.newInstance(VideoPlayerActivity.this.isHls());
                choosePlayerEngineDialogNewInstance.setChoosePlayerEngineListener(new ChoosePlayerEngineDialog.ChoosePlayerEngineListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.9.1
                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerEngineDialog.ChoosePlayerEngineListener
                    public void onChoosePlayerEngine(String engine2) {
                        PrefsUtils.getInstance().putString(Constant.Prefs.PLAYER_ENGINE, engine2);
                        VideoPlayerActivity.this.excuetSer(true, VideoPlayerActivity.this.mPlayerView.getCurrQuality(), VideoPlayerActivity.this.mPlayerView.getCurPosition(), false, engine2);
                    }
                });
                choosePlayerEngineDialogNewInstance.show(VideoPlayerActivity.this.getSupportFragmentManager(), "ChoosePlayerEngineDialog");
            }
        });
        Glide.with((FragmentActivity) this).load(Integer.valueOf(R.drawable.ic_black_video)).into(this.mPlayerView.getImageThumb());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHls() {
        if (this.mPlayerView.getCurrQuality() < 0 || this.mPlayerView.getCurrQuality() >= this.movieDownload.list.size()) {
            return false;
        }
        return this.movieDownload.list.get(this.mPlayerView.getCurrQuality()).hls;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOpenSubtitle() {
        OpenSubtitleDialog openSubtitleDialogNewInstance = OpenSubtitleDialog.newInstance(this.movieDownload.imdb_id, this.movieDownload.id, 0, 0);
        this.openSubtitleDialogFragment = openSubtitleDialogNewInstance;
        openSubtitleDialogNewInstance.setListener(new OpenSubtitleDialog.OnOpenSubtitleListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.10
            @Override // com.movieboxpro.android.view.dialog.OpenSubtitleDialog.OnOpenSubtitleListener
            public void onSelectLanguage() {
                VideoPlayerActivity.this.showOpenSubtitle();
            }
        });
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.add(this.openSubtitleDialogFragment, "OpenSubtitleDialog");
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        fragmentTransactionBeginTransaction.show(this.openSubtitleDialogFragment);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        this.flContainer = (FrameLayout) findViewById(R.id.flContainer);
        this.flPreview = (FrameLayout) findViewById(R.id.flPreview);
        setTitleBar(false);
        this.movieDownload = (BaseMediaModel) getSerializable("videoplayer_params", new BaseMediaModel());
        this.movieid = getStringParam(VIDEO_ID, "");
        boolean booleanParam = getBooleanParam(IS_RESTART, false);
        this.mIsRestart = booleanParam;
        if (booleanParam) {
            this.movieDownload.seconds = 0;
        }
    }

    private void stopShowTestTimer() {
        Disposable disposable = this.showTestNetDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshUrl(RefreshPlayUrlEvent event) {
        requestUrl();
    }

    @Override // com.movieboxpro.android.view.dialog.TestNetSpeedFragment.OnSelectServerListener
    public void onSelectServer() {
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "");
        if (this.lastServerDomain == null) {
            this.lastServerDomain = "";
        }
        if (this.lastServerDomain.equals(string)) {
            return;
        }
        requestUrl();
        this.lastServerDomain = string;
    }

    private void requestUrl() {
        String str;
        String str2;
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        ((ObservableSubscribeProxy) Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.getUserData().uid_v2, this.movieDownload.id, "", str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<BaseMediaModel>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.11
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                VideoPlayerActivity.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BaseMediaModel model) {
                VideoPlayerActivity.this.hideLoading();
                VideoPlayerActivity.this.movieDownload.list = model.list;
                VideoPlayerActivity.this.movieDownload.quality = model.quality;
                VideoPlayerActivity.this.list.clear();
                VideoPlayerActivity.this.excuetSer(true, -1, -1, false, null);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                VideoPlayerActivity.this.hideLoading();
                ToastUtils.showShort("Load failed:" + e.getMessage());
            }
        });
    }

    private void timerSaveProgress() {
        if (this.firstPlay) {
            this.firstPlay = false;
            IPlayerView iPlayerView = this.mPlayerView;
            if (iPlayerView != null) {
                Movie_play(iPlayerView.getVideoView().getCurrentPosition());
            }
        }
        Disposable disposable = this.saveProgressDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        ((ObservableSubscribeProxy) Observable.interval(5L, TimeUnit.MINUTES).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.12
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                VideoPlayerActivity.this.saveProgressDisposable = d;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long aLong) {
                if (VideoPlayerActivity.this.mPlayerView != null) {
                    VideoPlayerActivity videoPlayerActivity = VideoPlayerActivity.this;
                    videoPlayerActivity.Movie_play(videoPlayerActivity.mPlayerView.getVideoView().getCurrentPosition());
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayStatus(OnPlayStatusEvent event) {
        Disposable disposable;
        Log.d("onPlayStatus", event.getStatus() + "");
        if (event.getStatus() == 334) {
            timerSaveProgress();
            this.isFirstPlay = false;
            TestNetHintDialog testNetHintDialog = this.testNetHintDialog;
            if (testNetHintDialog == null || !testNetHintDialog.isShowing()) {
                return;
            }
            this.testNetHintDialog.dismiss();
            return;
        }
        if (event.getStatus() != 335 || (disposable = this.saveProgressDisposable) == null) {
            return;
        }
        disposable.dispose();
    }

    private void initPreviewPlayer() {
        int videoWidth = this.mPlayerView.getVideoWidth();
        int videoHeight = this.mPlayerView.getVideoHeight();
        if (videoWidth != 0) {
            ViewGroup.LayoutParams layoutParams = this.flPreview.getLayoutParams();
            int iDp2px = DensityUtils.dp2px(200.0f);
            layoutParams.height = (videoHeight * iDp2px) / videoWidth;
            layoutParams.width = iDp2px;
            this.flPreview.setLayoutParams(layoutParams);
        }
        this.previewIjkVideoView = new NormalIjkVideoView(this);
        final IjkPlayer ijkPlayer = new IjkPlayer(this, this) { // from class: com.movieboxpro.android.player.VideoPlayerActivity.13
            @Override // com.dueeeke.videoplayer.player.IjkPlayer, com.dueeeke.videoplayer.player.AbstractPlayer
            public void setOptions() {
                this.mMediaPlayer.setOption(1, "analyzemaxduration", 100L);
                this.mMediaPlayer.setOption(1, "flush_packets", 1L);
                this.mMediaPlayer.setOption(4, "packet-buffering", 0L);
                this.mMediaPlayer.setOption(4, "reconnect", 3L);
                this.mMediaPlayer.setOption(1, "fflags", "fastseek");
                this.mMediaPlayer.setOption(4, "framedrop", 5L);
                this.mMediaPlayer.setOption(4, "soundtouch", 1L);
                this.mMediaPlayer.setOption(2, "skip_loop_filter", 48L);
                this.mMediaPlayer.setOption(4, "framedrop", 12L);
                this.mMediaPlayer.setOption(4, "min-frames", 1L);
                this.mMediaPlayer.setOption(1, "dns_cache_clear", 1L);
                this.mMediaPlayer.setOption(1, "dns_cache_timeout", -1L);
            }
        };
        this.previewIjkVideoView.setPlayerConfig(new PlayerConfig.Builder().setCustomMediaPlayer(ijkPlayer).enableMediaCodec(PrefsUtils.getInstance().getBoolean(Constant.Prefs.HW_DECODER, true)).savingProgress().build());
        this.flPreview.addView(this.previewIjkVideoView);
        this.previewIjkVideoView.setPlayUrl(this.preViewUrl);
        this.previewIjkVideoView.start();
        this.previewIjkVideoView.setMute(true);
        this.previewIjkVideoView.addOnVideoViewStateChangeListener(new OnVideoViewStateChangeListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.14
            @Override // com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener
            public void onPlayerStateChanged(int playerState) {
            }

            @Override // com.dueeeke.videoplayer.listener.OnVideoViewStateChangeListener
            public void onPlayStateChanged(int playState) {
                if (playState == 3) {
                    VideoPlayerActivity.this.previewVideoPrepared = true;
                    VideoPlayerActivity.this.previewIjkVideoView.pause();
                    VideoPlayerActivity.this.lastPos = ijkPlayer.getCurrentPosition() * 1000;
                    VideoPlayerActivity.this.previewIjkVideoView.seekTo(VideoPlayerActivity.this.lastPos);
                }
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchTestSpeed(SwitchTestSpeedHintEvent event) {
        if (event.isShow()) {
            TestNetHintDialog testNetHintDialogCreate = new TestNetHintDialog.Builder(this).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.15
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public void onClick() {
                    VideoPlayerActivity.this.testNetSpeedFragment = new TestNetSpeedFragment();
                    if (VideoPlayerActivity.this.testNetSpeedFragment.isAdded()) {
                        return;
                    }
                    VideoPlayerActivity.this.testNetSpeedFragment.show(VideoPlayerActivity.this.getSupportFragmentManager(), "TestNetSpeedFragment");
                }
            }).create();
            this.testNetHintDialog = testNetHintDialogCreate;
            testNetHintDialogCreate.show();
        } else {
            TestNetHintDialog testNetHintDialog = this.testNetHintDialog;
            if (testNetHintDialog != null) {
                testNetHintDialog.dismiss();
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTranslateSubtitle(TranslateSubtitleEvent event) {
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.showTranslateSubtitle(event.getSubtitles());
        }
    }

    private void startTimerTestNet() {
        ((ObservableSubscribeProxy) Observable.interval(1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.16
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
            public void onNext(Long aLong) {
                IPlayerView unused = VideoPlayerActivity.this.mPlayerView;
            }
        });
    }

    /* JADX WARN: Code duplicated, block: B:107:0x0246  */
    /* JADX WARN: Code duplicated, block: B:109:0x0249  */
    /* JADX WARN: Code duplicated, block: B:112:0x0252  */
    /* JADX WARN: Code duplicated, block: B:114:0x0266  */
    /* JADX WARN: Code duplicated, block: B:118:0x027d A[EDGE_INSN: B:118:0x027d->B:119:0x027e BREAK  A[LOOP:4: B:110:0x024a->B:117:0x027a]] */
    /* JADX WARN: Code duplicated, block: B:121:0x0294  */
    /* JADX WARN: Code duplicated, block: B:125:0x02b0  */
    /* JADX WARN: Code duplicated, block: B:155:0x027d A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:156:0x027a A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    public void excuetSer(boolean isContinue, int pos, int startS, boolean initPlayer, String selectEngine) {
        int i;
        int start_time;
        int i2;
        int videoSource;
        IPlayerView iPlayerView;
        int i3;
        this.list.clear();
        this.hlsAudioTracks.clear();
        for (BaseMediaModel.DownloadFile downloadFile : this.movieDownload.list) {
            if ("video".equals(downloadFile.type) || downloadFile.type == null || TextUtils.isEmpty(downloadFile.type)) {
                this.list.add(new MediaQualityInfo(0, downloadFile.h265, downloadFile.path, downloadFile.real_quality, downloadFile.size, TimeUtils.formatTime4(downloadFile.dateline * 1000), downloadFile.count, downloadFile.vip_only + "", downloadFile.filename, false, downloadFile.bitstream, downloadFile.original, downloadFile.hdr, downloadFile.colorbit, downloadFile.filename, downloadFile.hls, downloadFile.mmfid, downloadFile.tmfid));
            } else if ("audio".equals(downloadFile.type)) {
                this.hlsAudioTracks.add(downloadFile);
            }
        }
        if (this.list.size() > 0) {
            List<MediaQualityInfo> list = this.list;
            this.preViewUrl = list.get(list.size() - 1).getPath();
        }
        ArrayList<MediaQualityInfo> arrayListGroupQualityList = GroupUtils.groupQualityList(this.list);
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.LAST_SELECT_QUA, "");
        PrefsUtils.getInstance().getInt(Constant.Prefs.IS_CHOOSE_ORIGIN, 0);
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.REMEMBER_SELECTED_ORG, false)) {
            i = 0;
            while (true) {
                if (i >= arrayListGroupQualityList.size()) {
                    i = -1;
                    break;
                }
                MediaQualityInfo mediaQualityInfo = arrayListGroupQualityList.get(i);
                if (!TextUtils.isEmpty(string)) {
                    if (1 != mediaQualityInfo.getOriginal() && !TextUtils.isEmpty(mediaQualityInfo.getPath()) && string.equals(mediaQualityInfo.getReal_quality())) {
                        break;
                    } else {
                        i++;
                    }
                } else if (!TextUtils.isEmpty(mediaQualityInfo.getPath()) && 1 != mediaQualityInfo.getOriginal()) {
                    break;
                } else {
                    i++;
                }
            }
        } else {
            i = 0;
            while (true) {
                if (i >= arrayListGroupQualityList.size()) {
                    i = -1;
                    break;
                }
                MediaQualityInfo mediaQualityInfo2 = arrayListGroupQualityList.get(i);
                if (!TextUtils.isEmpty(string)) {
                    if (!TextUtils.isEmpty(mediaQualityInfo2.getPath()) && string.equals(mediaQualityInfo2.getReal_quality())) {
                        break;
                    } else {
                        i++;
                    }
                } else if (!TextUtils.isEmpty(mediaQualityInfo2.getPath())) {
                    break;
                } else {
                    i++;
                }
            }
        }
        if (pos != -1) {
            i = pos;
        }
        if (i == -1) {
            for (int i4 = 0; i4 < arrayListGroupQualityList.size(); i4++) {
                if (!TextUtils.isEmpty(arrayListGroupQualityList.get(i4).getPath())) {
                    i = i4;
                    break;
                }
            }
        }
        if (i >= 0 && i < arrayListGroupQualityList.size()) {
            if (TextUtils.isEmpty(arrayListGroupQualityList.get(i).getPath())) {
                for (int i5 = 0; i5 < arrayListGroupQualityList.size(); i5++) {
                    if (!TextUtils.isEmpty(arrayListGroupQualityList.get(i5).getPath())) {
                        i = i5;
                        break;
                    }
                }
            }
            MediaQualityInfo mediaQualityInfo3 = arrayListGroupQualityList.get(i);
            if (mediaQualityInfo3.isHls()) {
                String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.PLAYER_ENGINE, "exo");
                if ("auto".equals(string2)) {
                    initPlayerEngine("exo");
                } else {
                    initPlayerEngine(string2);
                }
            } else if (!TextUtils.isEmpty(selectEngine)) {
                initPlayerEngine(selectEngine);
            } else if (initPlayer) {
                String string3 = PrefsUtils.getInstance().getString(Constant.Prefs.PLAYER_ENGINE, "auto");
                if ("auto".equals(string3)) {
                    if (mediaQualityInfo3.getHdr() == 1 || mediaQualityInfo3.getOriginal() == 1) {
                        initPlayerEngine("vlc");
                    } else {
                        initPlayerEngine("ijk");
                    }
                } else {
                    initPlayerEngine(string3);
                }
            }
            PlayRecode playRecodeFindByTypeid = App.getDB().playRecodeDao().findByTypeid(1, this.movieDownload.id);
            if (this.mIsRestart) {
                this.mIsRestart = false;
            } else {
                if (this.movieDownload.seconds != 0) {
                    start_time = this.movieDownload.seconds * 1000;
                } else {
                    if (playRecodeFindByTypeid != null) {
                        start_time = playRecodeFindByTypeid.getStart_time();
                    }
                    if (startS != -1) {
                        start_time = startS;
                    }
                    if (pos != -1) {
                        i2 = i;
                        break;
                    }
                    i3 = 0;
                    while (true) {
                        if (i3 >= this.list.size()) {
                            i2 = i;
                            break;
                        } else {
                            if (this.list.get(i3).getPath().startsWith(IDataSource.SCHEME_HTTP_TAG) && !TextUtils.isEmpty(this.list.get(i3).getPath())) {
                                i2 = i3;
                                break;
                            }
                            i3++;
                        }
                    }
                    videoSource = this.mPlayerView.setVideoSource(this.list, this.onVipClickListener, start_time, isContinue, i2);
                    this.mPlayerView.setHlsTracks(this.hlsAudioTracks);
                    if (videoSource >= 0 && videoSource < this.movieDownload.list.size()) {
                        int i6 = this.movieDownload.list.get(videoSource).fid;
                        iPlayerView = this.mPlayerView;
                        if ((iPlayerView instanceof IjkPlayerView) && !this.mIsRestart) {
                            iPlayerView.seekTo(start_time);
                            this.mIsRestart = false;
                        }
                        setData(i6, this.movieDownload.list.get(videoSource).filename);
                    }
                    this.mPlayerView.setVideoTitle(this.movieDownload.title);
                    return;
                }
                if (startS != -1) {
                    start_time = startS;
                }
                if (pos != -1) {
                    i2 = i;
                    break;
                }
                i3 = 0;
                while (true) {
                    if (i3 >= this.list.size()) {
                        i2 = i;
                        break;
                    } else {
                        if (this.list.get(i3).getPath().startsWith(IDataSource.SCHEME_HTTP_TAG)) {
                        }
                        i3++;
                    }
                }
                videoSource = this.mPlayerView.setVideoSource(this.list, this.onVipClickListener, start_time, isContinue, i2);
                this.mPlayerView.setHlsTracks(this.hlsAudioTracks);
                if (videoSource >= 0) {
                    int i7 = this.movieDownload.list.get(videoSource).fid;
                    iPlayerView = this.mPlayerView;
                    if (iPlayerView instanceof IjkPlayerView) {
                        iPlayerView.seekTo(start_time);
                        this.mIsRestart = false;
                    }
                    setData(i7, this.movieDownload.list.get(videoSource).filename);
                }
                this.mPlayerView.setVideoTitle(this.movieDownload.title);
                return;
            }
            start_time = 0;
            if (startS != -1) {
                start_time = startS;
            }
            if (pos != -1) {
                i2 = i;
                break;
            }
            i3 = 0;
            while (true) {
                if (i3 >= this.list.size()) {
                    i2 = i;
                    break;
                } else {
                    if (this.list.get(i3).getPath().startsWith(IDataSource.SCHEME_HTTP_TAG)) {
                    }
                    i3++;
                }
            }
            videoSource = this.mPlayerView.setVideoSource(this.list, this.onVipClickListener, start_time, isContinue, i2);
            this.mPlayerView.setHlsTracks(this.hlsAudioTracks);
            if (videoSource >= 0) {
                int i8 = this.movieDownload.list.get(videoSource).fid;
                iPlayerView = this.mPlayerView;
                if (iPlayerView instanceof IjkPlayerView) {
                    iPlayerView.seekTo(start_time);
                    this.mIsRestart = false;
                }
                setData(i8, this.movieDownload.list.get(videoSource).filename);
            }
            this.mPlayerView.setVideoTitle(this.movieDownload.title);
            return;
        }
        ToastUtils.showShort("Path is invalid:" + arrayListGroupQualityList.size() + ",position:" + i);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        excuetSer(false, -1, -1, true, null);
    }

    public void setData(int fid, final String filename) {
        if (Network.isConnected(this.context)) {
            APIService aPIService = this.service;
            String str = App.isLogin() ? App.getUserData().uid_v2 : "";
            ((ObservableSubscribeProxy) Observable.zip(aPIService.Movie_srt_list_v2(API.Movie.MOVIE_SRTLIST_V2, str, this.movieid, fid + "", App.deviceLang, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(ResponseSubtitleRecord.class)).subscribeOn(Schedulers.io()), HttpRequest.post("My_srt").param("mid", this.movieDownload.id).param("box_type", (Object) 1).param(DealWatchNextService.SEASON, (Object) 0).param(DealWatchNextService.EPISODE, (Object) 0).asRequest().compose(RxUtils.rxTranslate2Bean(MySubtitleResponse.class)).subscribeOn(Schedulers.io()), new BiFunction<ResponseSubtitleRecord, MySubtitleResponse, ResponseSubtitleRecord>(this) { // from class: com.movieboxpro.android.player.VideoPlayerActivity.18
                @Override // io.reactivex.functions.BiFunction
                public ResponseSubtitleRecord apply(ResponseSubtitleRecord subtitleRecord, MySubtitleResponse mySubtitleResponse) throws Exception {
                    if (mySubtitleResponse.getSrt_list() != null && !mySubtitleResponse.getSrt_list().isEmpty()) {
                        SRTModel sRTModel = new SRTModel();
                        sRTModel.language = "My Subtitles";
                        sRTModel.subtitles = mySubtitleResponse.getSrt_list();
                        subtitleRecord.getList().add(0, sRTModel);
                    }
                    return subtitleRecord;
                }
            }).observeOn(AndroidSchedulers.mainThread()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<ResponseSubtitleRecord>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.17
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                    RxManager.addDisposable(VideoPlayerActivity.this.TAG, d);
                }

                @Override // io.reactivex.Observer
                public void onNext(ResponseSubtitleRecord subtitleRecord) {
                    SRTModel.subTitles subtitlesFindNameSameSubtitle;
                    VideoPlayerActivity.this.srtHash.clear();
                    if (subtitleRecord != null) {
                        if (subtitleRecord.getSelect() == null && PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, false)) {
                            String lowerCase = App.deviceLang.toLowerCase(Locale.getDefault());
                            for (SRTModel sRTModel : subtitleRecord.getList()) {
                                if (sRTModel.subtitles != null && sRTModel.subtitles.size() > 0 && sRTModel.subtitles.get(0).lang.contains(lowerCase)) {
                                    try {
                                        subtitlesFindNameSameSubtitle = VideoPlayerActivity.this.findNameSameSubtitle(sRTModel.subtitles, filename);
                                    } catch (Exception unused) {
                                        subtitlesFindNameSameSubtitle = null;
                                    }
                                    if (subtitlesFindNameSameSubtitle != null) {
                                        subtitleRecord.setSelect(subtitlesFindNameSameSubtitle);
                                        subtitlesFindNameSameSubtitle.setMyselect(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                                        subtitlesFindNameSameSubtitle.setSelect(true);
                                        break;
                                    } else {
                                        subtitleRecord.setSelect(sRTModel.subtitles.get(0));
                                        sRTModel.subtitles.get(0).setMyselect(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                                        sRTModel.subtitles.get(0).setSelect(true);
                                        break;
                                    }
                                }
                            }
                        }
                        for (SRTModel sRTModel2 : subtitleRecord.getList()) {
                            VideoPlayerActivity.this.srtHash.put(sRTModel2.language, sRTModel2.subtitles);
                        }
                        if (VideoPlayerActivity.this.mPlayerView != null) {
                            VideoPlayerActivity.this.mPlayerView.setSRTList(VideoPlayerActivity.this.srtHash, VideoPlayerActivity.this.onDownloadClickListener, subtitleRecord.getSelect());
                        }
                        if (subtitleRecord.getSelect() != null) {
                            VideoPlayerActivity.this.loadSubtitleNoProgress(subtitleRecord.getSelect());
                        }
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                    Log.d(VideoPlayerActivity.this.TAG, "Load subtitle failed:" + e.getMessage());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SRTModel.subTitles findNameSameSubtitle(List<SRTModel.subTitles> list, String filename) {
        String realName = CommUtils.getRealName(FileUtils.getFileNameNoExtension(filename));
        for (SRTModel.subTitles subtitles : list) {
            if (subtitles.getFile_path().substring(subtitles.getFile_path().lastIndexOf("/") + 1).toLowerCase(Locale.ENGLISH).contains(realName.toLowerCase(Locale.ENGLISH))) {
                return subtitles;
            }
        }
        return null;
    }

    public void loadSubtitleNoProgress(final SRTModel.subTitles subTitles) {
        final String file_path = subTitles.getFile_path();
        ((ObservableSubscribeProxy) HttpHelper.getApiService().downloadString(file_path).map(new Function<Response<String>, Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.20
            @Override // io.reactivex.functions.Function
            public Boolean apply(Response<String> response) {
                if (response.isSuccessful()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(Constant.DIR_DOWNLOAD);
                    sb.append("/");
                    String str = file_path;
                    sb.append(str.substring(str.lastIndexOf("/") + 1));
                    String string = sb.toString();
                    ReaderUtils.writeFile(ReaderUtils.getChapterFile(string).getAbsolutePath(), response.body(), false);
                    ArrayList<SRT> arrayList = new ArrayList<>();
                    SrtParser.parseSrt(string, arrayList);
                    if (VideoPlayerActivity.this.mPlayerView != null) {
                        VideoPlayerActivity.this.mPlayerView.startSRT(arrayList, response.body(), subTitles.getLang(), subTitles.getFile_path(), subTitles);
                    }
                    return true;
                }
                return false;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.19
            @Override // io.reactivex.Observer
            public void onNext(Boolean success) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                VideoPlayerActivity.this.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                VideoPlayerActivity.this.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                VideoPlayerActivity.this.hideLoading();
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void enterPicMode(EnterPicModeEvent enterPicModeEvent) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.enterPicMode = true;
            int videoWidth = this.mPlayerView.getVideoWidth();
            int videoHeight = this.mPlayerView.getVideoHeight();
            if (videoWidth > ((double) videoHeight) * 2.39d) {
                videoWidth = 16;
                videoHeight = 9;
            }
            boolean zEnterPictureInPictureMode = enterPictureInPictureMode(App$$ExternalSyntheticApiModelOutline0.m().setAspectRatio(new Rational(videoWidth, videoHeight)).build());
            Log.d(this.TAG, "enterPicMode: " + zEnterPictureInPictureMode);
        }
    }

    public void getSRT(final String url, final String language) {
        ((ObservableSubscribeProxy) HttpHelper.getApiService().download(url).map(new Function<ResponseBody, Pair<ArrayList<SRT>, String>>(this) { // from class: com.movieboxpro.android.player.VideoPlayerActivity.22
            @Override // io.reactivex.functions.Function
            public Pair<ArrayList<SRT>, String> apply(ResponseBody responseBody) throws Exception {
                StringBuilder sb = new StringBuilder();
                sb.append(Constant.DIR_DOWNLOAD);
                sb.append("/");
                String str = url;
                sb.append(str.substring(str.lastIndexOf("/") + 1));
                String string = sb.toString();
                File chapterFile = ReaderUtils.getChapterFile(string);
                String strString = responseBody.string();
                ReaderUtils.writeFile(chapterFile.getAbsolutePath(), strString, false);
                ArrayList arrayList = new ArrayList();
                SrtParser.parseSrt(string, (ArrayList<SRT>) arrayList);
                return new Pair<>(arrayList, strString);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<ArrayList<SRT>, String>>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.21
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                VideoPlayerActivity.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<ArrayList<SRT>, String> model) {
                if (VideoPlayerActivity.this.mPlayerView != null) {
                    VideoPlayerActivity.this.mPlayerView.startSRT(model.getFirst(), model.getSecond(), language, url, null);
                }
                VideoPlayerActivity.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                VideoPlayerActivity.this.hideLoading();
            }
        });
    }

    public void Movie_srt_select(String sid) {
        APIService aPIService = this.service;
        String str = App.isLogin() ? App.getUserData().uid_v2 : "";
        BaseMediaModel baseMediaModel = this.movieDownload;
        aPIService.Movie_srt_select(API.Movie.MOVIE_SRTSELECT, str, sid, baseMediaModel != null ? baseMediaModel.id : "", null, BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>(this) { // from class: com.movieboxpro.android.player.VideoPlayerActivity.23
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String s) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
            }
        });
    }

    public void disSelectSubtitle(String sid) {
        APIService aPIService = this.service;
        String str = App.isLogin() ? App.getUserData().uid_v2 : "";
        BaseMediaModel baseMediaModel = this.movieDownload;
        aPIService.Movie_srt_select(API.Movie.MOVIE_SRTSELECT, str, sid, baseMediaModel != null ? baseMediaModel.id : "", "del", BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>(this) { // from class: com.movieboxpro.android.player.VideoPlayerActivity.24
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String s) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0, types: [com.movieboxpro.android.http.APIService] */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1, types: [int] */
    /* JADX WARN: Type inference failed for: r17v2 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void Movie_play(int position) {
        int i;
        int i2 = position;
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE) && this.movieDownload != null && this.mPlayerView.getCurrQuality() >= 0 && this.mPlayerView.getCurrQuality() < this.movieDownload.list.size()) {
            BaseMediaModel.DownloadFile downloadFile = this.movieDownload.list.get(this.mPlayerView.getCurrQuality());
            if (downloadFile.hls) {
                i = downloadFile.fid;
            } else {
                i = downloadFile.fid;
            }
            APIService aPIService = this.service;
            String str = App.isLogin() ? App.getUserData().uid_v2 : "";
            BaseMediaModel baseMediaModel = this.movieDownload;
            aPIService.Movie_play(API.Movie.MOVIE_PLAY, str, baseMediaModel != null ? baseMediaModel.id : "", (i2 / 1000) + "", String.valueOf(i), SystemUtils.getUniqueId(this)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.25
                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                    RxManager.addDisposable(VideoPlayerActivity.this.TAG, d);
                }

                @Override // io.reactivex.Observer
                public void onNext(String s) {
                    Log.d(VideoPlayerActivity.this.TAG, "save play record success");
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                    Log.d(VideoPlayerActivity.this.TAG, "save play record failed:" + e.getMessage());
                }

                @Override // io.reactivex.Observer
                public void onComplete() {
                    OnPlayerFinishLiveData.INSTANCE.get().setValue(true);
                    EventBus.getDefault().post(new OnPlayerFinishEvent());
                }
            });
            boolean z = this.isComplete;
            if (i2 < 0) {
                i2 = 0;
            }
            IPlayerView iPlayerView = this.mPlayerView;
            if (iPlayerView != null) {
                ?? r17 = (iPlayerView.getVideoView().getDuration() == 0 || i2 == 0 || this.mPlayerView.getVideoView().getDuration() - i2 > 180000) ? z : 1;
                ?? r11 = this.service;
                String str2 = App.isLogin() ? App.getUserData().uid_v2 : "";
                BaseMediaModel baseMediaModel2 = this.movieDownload;
                String str3 = baseMediaModel2 != null ? baseMediaModel2.id : "";
                r11.Movie_play_progress2(API.Movie.MOVIE_PLAY_PROGRESS, str2, str3, String.valueOf(i), (i2 / 1000) + "", r17, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>(this) { // from class: com.movieboxpro.android.player.VideoPlayerActivity.26
                    @Override // io.reactivex.Observer
                    public void onComplete() {
                    }

                    @Override // io.reactivex.Observer
                    public void onError(Throwable throwable) {
                    }

                    @Override // io.reactivex.Observer
                    public void onNext(String s) {
                    }

                    @Override // io.reactivex.Observer
                    public void onSubscribe(Disposable disposable) {
                    }
                });
            }
        }
    }

    public int choose() {
        int i = PrefsUtils.getInstance().getInt(Constant.Prefs.SHOW_H265, 1);
        for (int i2 = 0; i2 < this.movieDownload.list.size(); i2++) {
            if (i == this.movieDownload.list.get(i2).h265 && this.movieDownload.list.get(i2).path != null && !TextUtils.isEmpty(this.movieDownload.list.get(i2).path)) {
                return i2;
            }
        }
        for (int i3 = 0; i3 < this.movieDownload.list.size(); i3++) {
            if (this.movieDownload.list.get(i3).path != null && !TextUtils.isEmpty(this.movieDownload.list.get(i3).path)) {
                return i3;
            }
        }
        return 0;
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        startPlayingFeedbackTimer();
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.doStart();
        }
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null && !this.enterPicMode) {
            Movie_play(iPlayerView.getCurPosition());
            this.mPlayerView.doPause();
        }
        deletePlayingStatus();
        super.onPause();
    }

    private void startPlayingFeedbackTimer() {
        Disposable disposable = this.feedbackDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        savePlayingFeedback();
        ((ObservableSubscribeProxy) Observable.interval(60L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.27
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                VideoPlayerActivity.this.feedbackDisposable = d;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long aLong) {
                if (Network.isConnected(VideoPlayerActivity.this)) {
                    VideoPlayerActivity.this.savePlayingFeedback();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePlayingFeedback() {
        ((ObservableSubscribeProxy) Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, this.movieDownload.id, SystemUtils.getUniqueId(this), this.movieDownload.box_type, this.movieDownload.season, this.movieDownload.episode, Build.BRAND, Build.MODEL).map(new Function<String, Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.29
            @Override // io.reactivex.functions.Function
            public Boolean apply(String s) throws Exception {
                BaseResponse baseResponse = (BaseResponse) JSON.parseObject(s, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
                if (baseResponse.getCode() == -88) {
                    VideoPlayerActivity.this.showScreenManage(baseResponse);
                    return true;
                }
                return false;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayerActivity.28
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                VideoPlayerActivity.this.saveFeedbackDisposable = d;
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Boolean model) {
                if (!model.booleanValue() || VideoPlayerActivity.this.mPlayerView == null) {
                    return;
                }
                VideoPlayerActivity.this.mPlayerView.doPause();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showScreenManage(BaseResponse<DeviceModelResponse> response) {
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.doPause();
        }
        ScreenManageActivity.start(this, (ArrayList<DeviceModelResponse.DeviceModel>) new ArrayList(response.getData().getDevice_list()), 100, response.getMsg(), 2, "", 0, 0);
    }

    private void deletePlayingStatus() {
        Disposable disposable = this.saveFeedbackDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.feedbackDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        Http.getService().deletePlayingStatus(API.Common.DELETE_PLAYING_STATUS, SystemUtils.getUniqueId(this), App.getUserData().uid_v2).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>(this) { // from class: com.movieboxpro.android.player.VideoPlayerActivity.30
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String s) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
            }
        });
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            if (resultCode == -1) {
                savePlayingFeedback();
                IPlayerView iPlayerView = this.mPlayerView;
                if (iPlayerView != null) {
                    iPlayerView._togglePlayStatus(false);
                    return;
                }
                return;
            }
            finish();
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        this.enterPicMode = true;
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            if (isInPictureInPictureMode) {
                iPlayerView._toggleControlBar();
                this.mPlayerView.setSubtitleSize(8);
            } else {
                this.isFromPicIn = true;
                iPlayerView.setSubtitleSize(getSizeValue());
            }
        }
        super.onPictureInPictureModeChanged(isInPictureInPictureMode, newConfig);
    }

    private int getSizeValue() {
        if (this.prefs == null) {
            this.prefs = getSharedPreferences(Constant.Prefs.SETTING, 0);
        }
        return this.prefs.getInt("srt_size_value", 24);
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.onStop();
            if (Utils.isAppForeground()) {
                Intent intent = new Intent(this, (Class<?>) DealWatchNextService.class);
                if (this.mPlayerView.getDurarion() - this.mPlayerView.getCurPosition() < 180000) {
                    intent.setAction("delete");
                    intent.putExtra("id", this.movieDownload.id);
                } else {
                    intent.setAction(DealWatchNextService.ADD_ACTION);
                    intent.putExtra(DealWatchNextService.MEDIA_DATA, this.movieDownload);
                    intent.putExtra("duration", this.mPlayerView.getDurarion());
                    intent.putExtra("progress", this.mPlayerView.getCurPosition());
                }
                startService(intent);
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacksAndMessages(null);
        NormalIjkVideoView normalIjkVideoView = this.previewIjkVideoView;
        if (normalIjkVideoView != null) {
            normalIjkVideoView.release();
        }
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            saveInDao(this.mPlayerView.onDestroy(), iPlayerView.getCurrQuality());
        }
        RxManager.remove(this.TAG);
        CallManager.cancelAll(getClass().getSimpleName());
        cancel();
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE) && this.isFromPicIn) {
            MainActivity.INSTANCE.start(this);
        }
        RefreshWatchedLiveData.INSTANCE.get().postValue(true);
        RefreshWatchingLiveData.INSTANCE.get().postValue(true);
    }

    public void saveInDao(int position, int quality) {
        MLog.d(this.TAG, "SSSS" + position + this.movieDownload.id + "sss" + this.movieDownload.box_type);
        PlayRecode playRecodeFindByTypeid = App.getDB().playRecodeDao().findByTypeid(this.movieDownload.box_type, this.movieDownload.id);
        if (playRecodeFindByTypeid == null) {
            PlayRecode playRecode = new PlayRecode();
            playRecode.setMovieId(this.movieDownload.id);
            playRecode.setBox_type(this.movieDownload.box_type);
            playRecode.setImdb_id(this.movieDownload.imdb_id);
            playRecode.setTitle(this.movieDownload.title);
            playRecode.setStart_time(position);
            playRecode.setQuality(quality);
            playRecode.setSeason(1);
            playRecode.setEpisode(1);
            App.getDB().playRecodeDao().insert(playRecode);
            return;
        }
        playRecodeFindByTypeid.setStart_time(position);
        playRecodeFindByTypeid.setQuality(quality);
        App.getDB().playRecodeDao().update(playRecodeFindByTypeid);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView == null || !iPlayerView.onBackPressed()) {
            super.onBackPressed();
        }
    }

    @Override // com.movieboxpro.android.presenter.setting.SRTPresenter
    public LinkedHashMap<String, List<SRTModel.subTitles>> getSRTList() {
        return this.srtHash;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        this.mPlayerView.configurationChanged(newConfig);
    }

    public void cancel() {
        Disposable disposable = this.mDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.mDisposable.dispose();
    }
}
