package com.movieboxpro.android.player;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.util.Rational;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.exoplayer.upstream.CmcdData;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.bumptech.glide.Glide;
import com.dl7.player.model.SRTModel;
import com.dl7.player.utils.SRT;
import com.dueeeke.model.ResponseSubtitleRecord2;
import com.google.common.net.HttpHeaders;
import com.jeffmony.downloader.m3u8.M3U8Constants;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.App$$ExternalSyntheticApiModelOutline0;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.app.RxManager;
import com.movieboxpro.android.base.BaseActivity;
import com.movieboxpro.android.base.BaseObserver;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.event.EnterPicModeEvent;
import com.movieboxpro.android.event.OnPlayStatusEvent;
import com.movieboxpro.android.event.OnPlayerFinishEvent;
import com.movieboxpro.android.event.OpenSubtitleSelectEvent;
import com.movieboxpro.android.event.PlayNextEpisodeEvent;
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
import com.movieboxpro.android.livedata.OnPlayerFinishLiveData;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.livedata.RefreshWatchingLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.OpenSubtitleModel;
import com.movieboxpro.android.model.SkipTimeResponse;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.player.vlc.VlcPlayerView;
import com.movieboxpro.android.presenter.setting.SRTPresenter;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.service.MyDownloadService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.DownloadFileUtils;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.FileUtils;
import com.movieboxpro.android.utils.GroupUtils;
import com.movieboxpro.android.utils.HttpHelper;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.Network;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ReaderUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SettingManager;
import com.movieboxpro.android.utils.SrtParser;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.android.view.dialog.ChoosePlayerEngineDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.EpisodeSeasonMapper;
import com.movieboxpro.android.view.dialog.OpenSubtitleDialog;
import com.movieboxpro.android.view.dialog.PlayNextEpisodeDialog;
import com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog;
import com.movieboxpro.android.view.dialog.PlayerEpisodeListStore;
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
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.ResponseBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import retrofit2.Response;
import tv.danmaku.ijk.media.player.IMediaPlayer;

/* JADX INFO: loaded from: classes5.dex */
public class VideoPlayer extends BaseActivity implements SRTPresenter, TestNetSpeedFragment.OnSelectServerListener {
    public static final String SEASON_TV_LIST = "season_tv_list";
    public static final int SHOW_TEST_NET_DELAY = 10;
    private Disposable disposable;
    private boolean enterPicMode;
    private int episode;
    private ArrayList<TvSeasonList> episodeListRollbackSeasonList;
    private Disposable feedbackDisposable;
    FrameLayout flContainer;
    private boolean isRestart;
    private boolean localFile;
    private Disposable mDisposable;
    private IPlayerView mPlayerView;
    private BaseMediaModel movieDownload;
    private OpenSubtitleDialog openSubtitleDialogFragment;
    private boolean playNextDismiss;
    private PlayNextEpisodeDialog playNextEpisodeDialog;
    private SharedPreferences prefs;
    private Disposable progressDisposable;
    private Disposable saveFeedbackDisposable;
    private Disposable saveProgressDisposable;
    private int season;
    private List<TvDetail.SeasonDetail> seasonDetails;
    private boolean showFlag;
    private Disposable showTestNetDisposable;
    private SkipTimeResponse skipTimeResponse;
    private TestNetHintDialog testNetHintDialog;
    private TestNetSpeedFragment testNetSpeedFragment;
    private TvDetail tvDetail;
    private int currPlayingPosition = -1;
    private List<TvSeasonList> tvSeasonListList = new ArrayList();
    private final PlayerEpisodeListStore episodeListStore = new PlayerEpisodeListStore();
    private int episodeListRollbackPosition = -1;
    private boolean episodeListSwitchPending = false;
    private int checkNextEpisodeTime = 180000;
    LinkedHashMap<String, List<SRTModel.subTitles>> srtHash = new LinkedHashMap<>();
    List<MediaQualityInfo> list = new ArrayList();
    List<BaseMediaModel.DownloadFile> hlsAudioTracks = new ArrayList();
    private List<OpenSubtitleModel> openSubtitleModels = new ArrayList();
    private String lastServerDomain = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_STATE, "");
    private boolean isComplete = false;
    OnVipClickListener onVipClickListener = new OnVipClickListener() { // from class: com.movieboxpro.android.player.VideoPlayer.1
        @Override // com.movieboxpro.android.listener.OnVipClickListener
        public void onClick() {
            if (App.getUserData().dead_time != 0) {
                new VipExpireDialog.Builder(VideoPlayer.this).setCancelable(false).setTitle("Your VIP has expired.").setContent(String.format("Expire: %s", TimeUtils.formatTime4(App.getUserData().dead_time * 1000))).setNegativeText("Close").setPositiveText("Renew").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayer.1.2
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        new VipChargeGuideDialog().show(VideoPlayer.this.getSupportFragmentManager(), "VipChargeGuideDialog");
                    }
                }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayer.1.1
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        VideoPlayer.this.getUserInfo();
                    }
                }).create().show();
            } else {
                new VipExpireDialog.Builder(VideoPlayer.this).setCancelable(false).setTitle("You're not VIP yet").setContent("").setNegativeText("Close").setPositiveText("Subscribe").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayer.1.4
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        new VipChargeGuideDialog().show(VideoPlayer.this.getSupportFragmentManager(), "VipChargeGuideDialog");
                    }
                }).setCancelListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayer.1.3
                    @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                    public void onClick() {
                        VideoPlayer.this.getUserInfo();
                    }
                }).create().show();
            }
        }
    };
    OnDownloadClickListener onDownloadClickListener = new OnDownloadClickListener() { // from class: com.movieboxpro.android.player.VideoPlayer.2
        @Override // com.movieboxpro.android.listener.OnDownloadClickListener
        public void onClick(String url, String language, String uid) {
            VideoPlayer.this.Movie_srt_select(uid);
            VideoPlayer.this.getSRT(url, language);
        }
    };
    private boolean firstPlay = true;

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isRequireNetWork() {
        return false;
    }

    @Override // com.movieboxpro.android.base.BaseActivity
    protected boolean isShowWarning() {
        return false;
    }

    private static TvDetail leanTvDetailForIntent(TvDetail source) {
        if (source == null) {
            return null;
        }
        TvDetail tvDetail = new TvDetail();
        tvDetail.id = source.id;
        tvDetail.mb_id = source.mb_id;
        tvDetail.title = source.title;
        tvDetail.vip_only = source.vip_only;
        tvDetail.display = source.display;
        tvDetail.state = source.state;
        tvDetail.code_file = source.code_file;
        tvDetail.director = source.director;
        tvDetail.add_time = source.add_time;
        tvDetail.poster = source.poster;
        tvDetail.description = source.description;
        tvDetail.imdb_id = source.imdb_id;
        tvDetail.cats = source.cats;
        tvDetail.year = source.year;
        tvDetail.collect = source.collect;
        tvDetail.view = source.view;
        tvDetail.download = source.download;
        tvDetail.update_time = source.update_time;
        tvDetail.released = source.released;
        tvDetail.released_timestamp = source.released_timestamp;
        tvDetail.trailer_url = source.trailer_url;
        tvDetail.is_collect = source.is_collect;
        tvDetail.box_type = source.box_type;
        tvDetail.quality_tag_new = source.quality_tag_new;
        tvDetail.quality_tags = source.quality_tags;
        tvDetail.audio_lang = source.audio_lang;
        tvDetail.imdb_link = source.imdb_link;
        tvDetail.imdb_rating = source.imdb_rating;
        tvDetail.language = source.language;
        tvDetail.season = source.season;
        tvDetail.episode = source.episode;
        tvDetail.tomato_meter = source.tomato_meter;
        tvDetail.audience_score = source.audience_score;
        tvDetail.plan_watched = source.plan_watched;
        tvDetail.thumb = source.thumb;
        tvDetail.episodeName = source.episodeName;
        tvDetail.banner = source.banner;
        tvDetail.privateId = source.privateId;
        tvDetail.path = source.path;
        tvDetail.qualitys = source.qualitys;
        tvDetail.fileLength = source.fileLength;
        tvDetail.format = source.format;
        tvDetail.size = source.size;
        tvDetail.count = source.count;
        tvDetail.dateline = source.dateline;
        tvDetail.fileName = source.fileName;
        tvDetail.tmfid = source.tmfid;
        tvDetail.speed = source.speed;
        tvDetail.statue = source.statue;
        tvDetail.progress = source.progress;
        tvDetail.seconds = source.seconds;
        tvDetail.quality = source.quality;
        tvDetail.list = source.list;
        tvDetail.actors = source.actors;
        tvDetail.banner_mini = source.banner_mini;
        tvDetail.seasonList = source.seasonList;
        tvDetail.years = source.years;
        tvDetail.history = source.history;
        tvDetail.year_year = source.year_year;
        tvDetail.content_rating = source.content_rating;
        tvDetail.season_episode = source.season_episode;
        tvDetail.seasonDetail = source.seasonDetail;
        tvDetail.episodeList = new ArrayList();
        return tvDetail;
    }

    private static void putTvPlayerExtras(Intent starter, TvDetail tvDetail, int season, int episode, boolean isNext) {
        starter.putExtra("videoplayer_params", leanTvDetailForIntent(tvDetail));
        starter.putExtra(VideoPlayerActivity.VIDEO_ID, season);
        starter.putExtra("videoplayer_episode", episode);
        starter.putExtra(VideoPlayerActivity.IS_NEXT, isNext);
        starter.putParcelableArrayListExtra(SEASON_TV_LIST, new ArrayList<>());
    }

    public static void start(Context context, TvDetail tvDetail, int season, int episode, boolean isNext, ArrayList<TvSeasonList> seasonDetailList, boolean isRestart, boolean isFromFeatured) {
        Intent intent = new Intent(context, (Class<?>) VideoPlayer.class);
        putTvPlayerExtras(intent, tvDetail, season, episode, isNext);
        intent.putExtra(VideoPlayerActivity.IS_RESTART, isRestart);
        intent.putExtra(VideoPlayerActivity.IS_FROM_FEATURED, isFromFeatured);
        context.startActivity(intent);
    }

    public static void start(Context context) {
        context.startActivity(new Intent(context, (Class<?>) VideoPlayer.class));
    }

    public static void start(Context context, TvDetail tvDetail, int season, int episode, boolean isNext, ArrayList<TvSeasonList> seasonDetailList) {
        Intent intent = new Intent(context, (Class<?>) VideoPlayer.class);
        putTvPlayerExtras(intent, tvDetail, season, episode, isNext);
        context.startActivity(intent);
    }

    public static void start(Context context, TvDetail tvDetail, int season, int episode, boolean isNext, ArrayList<TvSeasonList> seasonDetailList, boolean isLocalFile) {
        Intent intent = new Intent(context, (Class<?>) VideoPlayer.class);
        putTvPlayerExtras(intent, tvDetail, season, episode, isNext);
        intent.putExtra("isLocalFile", isLocalFile);
        context.startActivity(intent);
    }

    public static void startForRecult(Activity context, TvDetail tvDetail, int season, int episode, boolean isNext, ArrayList<TvSeasonList> seasonDetailList) {
        Intent intent = new Intent(context, (Class<?>) VideoPlayer.class);
        putTvPlayerExtras(intent, tvDetail, season, episode, isNext);
        context.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getUserInfo() {
        ((ObservableSubscribeProxy) Http.getService().Userinfo2(API.USER.USERINFO, App.getUserData().uid_v2, SystemUtils.getUniqueId(this), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(UserModel.UserData.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<UserModel.UserData>(this) { // from class: com.movieboxpro.android.player.VideoPlayer.3
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

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initView() {
        setTitleBar(false);
        this.flContainer = (FrameLayout) findViewById(R.id.flContainer);
    }

    private void stopShowTestTimer() {
        Disposable disposable = this.showTestNetDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
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
        if (this.currPlayingPosition < this.tvSeasonListList.size() && this.currPlayingPosition >= 0) {
            this.mPlayerView.showLoading();
            requestUrls(this.tvSeasonListList.get(this.currPlayingPosition), 0, true);
        }
        this.lastServerDomain = string;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefreshUrl(RefreshPlayUrlEvent event) {
        int i;
        if (this.currPlayingPosition >= this.tvSeasonListList.size() || (i = this.currPlayingPosition) < 0) {
            return;
        }
        requestUrls(this.tvSeasonListList.get(i), 0, true);
    }

    private void timerSaveProgress() {
        IPlayerView iPlayerView;
        if (this.firstPlay && (iPlayerView = this.mPlayerView) != null) {
            Movie_play(iPlayerView.getVideoView().getCurrentPosition());
        }
        Disposable disposable = this.saveProgressDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        ((ObservableSubscribeProxy) Observable.interval(5L, TimeUnit.MINUTES).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.player.VideoPlayer.4
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                VideoPlayer.this.saveProgressDisposable = d;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long aLong) {
                if (VideoPlayer.this.mPlayerView != null) {
                    VideoPlayer videoPlayer = VideoPlayer.this;
                    videoPlayer.Movie_play(videoPlayer.mPlayerView.getVideoView().getCurrentPosition());
                }
            }
        });
    }

    private void getSaveSkipTime() {
        ((ObservableSubscribeProxy) HttpRequest.post(this, "TV_episode_title_get").params(Api.INSTANCE.getSkipTime(this.movieDownload.id, this.season, this.episode)).asRequest().compose(RxUtils.rxTranslate2Bean(SkipTimeResponse.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<SkipTimeResponse>() { // from class: com.movieboxpro.android.player.VideoPlayer.5
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(SkipTimeResponse model) {
                VideoPlayer.this.skipTimeResponse = model;
                if (VideoPlayer.this.firstPlay) {
                    return;
                }
                VideoPlayer.this.checkSkipTimeHint();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkSkipTimeHint() {
        if ("my".equals(this.skipTimeResponse.getStart_type())) {
            if (this.mPlayerView.getCurPosition() < ((long) this.skipTimeResponse.getStart()) * 1000) {
                this.mPlayerView.seekTo(this.skipTimeResponse.getStart() * 1000);
                this.mPlayerView.showSkippedTimeHint("Skipped Opening " + this.skipTimeResponse.getStart() + CmcdData.STREAMING_FORMAT_SS, "RESTART.", true);
            }
        } else if (Video.TypeInfo.OTHER.equals(this.skipTimeResponse.getStart_type()) && this.mPlayerView.getCurPosition() < ((long) this.skipTimeResponse.getStart()) * 1000) {
            this.mPlayerView.showSkippedTimeHint("Skipped Recommend Opening " + this.skipTimeResponse.getStart() + CmcdData.STREAMING_FORMAT_SS, "RESTART.", true);
            this.mPlayerView.seekTo(this.skipTimeResponse.getStart() * 1000);
        }
        if ("my".equals(this.skipTimeResponse.getEnd_type()) || Video.TypeInfo.OTHER.equals(this.skipTimeResponse.getEnd_type())) {
            startProgressTimer();
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onPlayStatus(OnPlayStatusEvent event) {
        SkipTimeResponse skipTimeResponse;
        if (event.getStatus() == 334) {
            updateEpisodeNavButtons();
            int durarion = this.mPlayerView.getDurarion();
            if (durarion < 0) {
                return;
            }
            if (durarion <= 1500000) {
                this.checkNextEpisodeTime = 120000;
            } else if (durarion < 2400000) {
                this.checkNextEpisodeTime = 150000;
            }
            TestNetHintDialog testNetHintDialog = this.testNetHintDialog;
            if (testNetHintDialog == null || !testNetHintDialog.isShowing()) {
                return;
            }
            this.testNetHintDialog.dismiss();
            return;
        }
        if (event.getStatus() == 335) {
            Disposable disposable = this.saveProgressDisposable;
            if (disposable != null) {
                disposable.dispose();
                return;
            }
            return;
        }
        if (event.getStatus() == 400 && this.firstPlay) {
            timerSaveProgress();
            this.firstPlay = false;
            boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true);
            if (z && this.skipTimeResponse != null) {
                checkSkipTimeHint();
            }
            if (!z || (skipTimeResponse = this.skipTimeResponse) == null || ((long) skipTimeResponse.getStart()) * 1000 < this.mPlayerView.getCurPosition()) {
                this.mPlayerView.showContinueText();
            }
            checkAutoDownload();
        }
    }

    private void checkAutoDownload() {
        Observable observableCompose;
        if (SettingManager.INSTANCE.getSmartDownload()) {
            List<TvDetail.SeasonDetail> list = this.seasonDetails;
            if (list != null) {
                observableCompose = Observable.just(list);
            } else {
                observableCompose = Http.getService().TV_episode(API.Tv.TV_EPISODE, this.movieDownload.getTid(), this.season + "", "", App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class));
            }
            ((ObservableSubscribeProxy) observableCompose.compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<List<TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.player.VideoPlayer.6
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
                public void onNext(List<TvDetail.SeasonDetail> model) {
                    String str;
                    String str2;
                    VideoPlayer.this.seasonDetails = model;
                    for (final TvDetail.SeasonDetail seasonDetail : model) {
                        if (seasonDetail.season == VideoPlayer.this.season && seasonDetail.episode == VideoPlayer.this.episode + 1 && DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(seasonDetail.tid, seasonDetail.season, seasonDetail.episode) == null) {
                            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                            if (!TextUtils.isEmpty(string) && "0".equalsIgnoreCase(string)) {
                                str2 = "";
                                str = str2;
                            } else {
                                str = string;
                                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                            }
                            ((ObservableSubscribeProxy) Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAOD_URL, App.getUserData().uid_v2, VideoPlayer.this.movieDownload.getTid(), seasonDetail.season + "", seasonDetail.episode + "", str2, str).compose(DownloadUrlTransformer.applyDownloadUrlsParsing()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(VideoPlayer.this))).subscribe(new Observer<List<DownloadUrl>>() { // from class: com.movieboxpro.android.player.VideoPlayer.6.1
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
                                public void onNext(List<DownloadUrl> baseMediaModel) {
                                    VideoPlayer.this.checkDownload(baseMediaModel, seasonDetail);
                                }
                            });
                            return;
                        }
                    }
                }
            });
        }
    }

    private DownloadUrl getAutoDownloadUrl(List<DownloadUrl> list) {
        String[] strArrSplit = SettingManager.INSTANCE.getDownloadRule().split(",");
        String currDownloadRule = SettingManager.INSTANCE.getCurrDownloadRule();
        if (list.size() == 1) {
            return list.get(0);
        }
        for (String str : strArrSplit) {
            for (int i = 0; i < list.size(); i++) {
                DownloadUrl downloadUrl = list.get(i);
                if ("ORG".equalsIgnoreCase(currDownloadRule)) {
                    if (!TextUtils.isEmpty(downloadUrl.getPath()) && downloadUrl.getOriginal().intValue() == 1) {
                        return downloadUrl;
                    }
                } else if (downloadUrl.getOriginal().intValue() != 1 && !TextUtils.isEmpty(downloadUrl.getPath()) && str.equalsIgnoreCase(downloadUrl.getReal_quality())) {
                    return downloadUrl;
                }
            }
        }
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDownload(List<DownloadUrl> list, final TvDetail.SeasonDetail item) {
        final DownloadUrl autoDownloadUrl = getAutoDownloadUrl(list);
        if (autoDownloadUrl == null) {
            return;
        }
        ((ObservableSubscribeProxy) Observable.just(autoDownloadUrl).map(new Function<DownloadUrl, Boolean>(this) { // from class: com.movieboxpro.android.player.VideoPlayer.8
            @Override // io.reactivex.functions.Function
            public Boolean apply(DownloadUrl downloadFile) throws Exception {
                if (downloadFile.getHls()) {
                    return true;
                }
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(downloadFile.getPath()).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(10000);
                httpURLConnection.setRequestProperty(HttpHeaders.ACCEPT_ENCODING, M3U8Constants.KEYFORMAT_IDENTITY);
                httpURLConnection.connect();
                long contentLength = httpURLConnection.getContentLength();
                if (contentLength < 0) {
                    contentLength = Long.parseLong(httpURLConnection.getHeaderField("Content-Length"));
                }
                httpURLConnection.disconnect();
                if (FileUtils.getFsAvailableSize(Constant.DIR_DOWNLOAD) > contentLength) {
                    return true;
                }
                ToastUtils.showShort("Not enough space to download next episode");
                return false;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayer.7
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
            }

            @Override // io.reactivex.Observer
            public void onNext(Boolean aBoolean) {
                if (aBoolean.booleanValue()) {
                    BaseMediaModel baseMediaModel = new BaseMediaModel();
                    baseMediaModel.thumb = item.thumbs;
                    baseMediaModel.season = item.season;
                    baseMediaModel.episode = item.episode;
                    baseMediaModel.imdb_id = VideoPlayer.this.movieDownload.imdb_id;
                    baseMediaModel.id = VideoPlayer.this.movieDownload.id;
                    baseMediaModel.title = VideoPlayer.this.movieDownload.title;
                    baseMediaModel.poster = VideoPlayer.this.movieDownload.poster;
                    baseMediaModel.box_type = VideoPlayer.this.movieDownload.box_type;
                    if (TextUtils.isEmpty(autoDownloadUrl.getTmfid())) {
                        item.downloadId = autoDownloadUrl.getMmfid();
                    } else {
                        item.downloadId = autoDownloadUrl.getTmfid();
                    }
                    baseMediaModel.episodeName = item.title;
                    DownloadService.INSTANCE.startDownload(VideoPlayer.this, autoDownloadUrl, baseMediaModel);
                }
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                String message = e.getMessage();
                if (message != null) {
                    Log.d(VideoPlayer.this.TAG, message);
                }
            }
        });
    }

    private void startProgressTimer() {
        if (this.mPlayerView == null) {
            return;
        }
        Disposable disposable = this.progressDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        final long durarion = this.mPlayerView.getDurarion();
        if (durarion <= 0) {
            return;
        }
        final boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.SKIP_OPENING_ENDING, true);
        ((ObservableSubscribeProxy) Observable.interval(0L, 2L, TimeUnit.SECONDS).map(new Function() { // from class: com.movieboxpro.android.player.VideoPlayer$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$startProgressTimer$0(z, durarion, (Long) obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Object>() { // from class: com.movieboxpro.android.player.VideoPlayer.9
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable2) {
                VideoPlayer.this.progressDisposable = disposable2;
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Object model) {
                if (!(VideoPlayer.this.mPlayerView instanceof ExoPlayerView) || VideoPlayer.this.currPlayingPosition == VideoPlayer.this.tvSeasonListList.size() - 1) {
                    return;
                }
                long curPosition = VideoPlayer.this.mPlayerView.getCurPosition();
                if (VideoPlayer.this.skipTimeResponse == null || !z) {
                    return;
                }
                if (("my".equals(VideoPlayer.this.skipTimeResponse.getEnd_type()) || Video.TypeInfo.OTHER.equals(VideoPlayer.this.skipTimeResponse.getEnd_type())) && durarion - curPosition <= ((long) VideoPlayer.this.skipTimeResponse.getEnd()) * 1000) {
                    if (VideoPlayer.this.showFlag) {
                        VideoPlayer.this.showFlag = false;
                        VideoPlayer.this.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.player.VideoPlayer.9.1
                            @Override // java.lang.Runnable
                            public void run() {
                                if (VideoPlayer.this.mPlayerView != null) {
                                    VideoPlayer.this.mPlayerView.showSkippedTimeHint("Ignore Ending " + VideoPlayer.this.skipTimeResponse.getEnd() + CmcdData.STREAMING_FORMAT_SS, "NEXT", false);
                                }
                            }
                        });
                    } else {
                        VideoPlayer.this.showFlag = true;
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Boolean lambda$startProgressTimer$0(boolean z, long j, Long l) throws Exception {
        if (!(this.mPlayerView instanceof ExoPlayerView)) {
            if (this.currPlayingPosition == this.tvSeasonListList.size() - 1) {
                return false;
            }
            long curPosition = this.mPlayerView.getCurPosition();
            SkipTimeResponse skipTimeResponse = this.skipTimeResponse;
            if (skipTimeResponse != null && z && (("my".equals(skipTimeResponse.getEnd_type()) || Video.TypeInfo.OTHER.equals(this.skipTimeResponse.getEnd_type())) && j - curPosition <= ((long) this.skipTimeResponse.getEnd()) * 1000)) {
                if (this.showFlag) {
                    this.showFlag = false;
                    runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.player.VideoPlayer.10
                        @Override // java.lang.Runnable
                        public void run() {
                            if (VideoPlayer.this.mPlayerView != null) {
                                VideoPlayer.this.mPlayerView.showSkippedTimeHint("Ignore Ending " + VideoPlayer.this.skipTimeResponse.getEnd() + "s,", "NEXT", false);
                            }
                        }
                    });
                } else {
                    this.showFlag = true;
                }
            }
        }
        return true;
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onPlayNextEpisode(PlayNextEpisodeEvent episodeEvent) {
        playNextEpisode();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSwitchTestSpeed(SwitchTestSpeedHintEvent event) {
        if (event.isShow()) {
            TestNetHintDialog testNetHintDialogCreate = new TestNetHintDialog.Builder(this).setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.player.VideoPlayer.11
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public void onClick() {
                    VideoPlayer.this.testNetSpeedFragment = new TestNetSpeedFragment();
                    if (VideoPlayer.this.testNetSpeedFragment.isAdded()) {
                        return;
                    }
                    VideoPlayer.this.testNetSpeedFragment.show(VideoPlayer.this.getSupportFragmentManager(), "TestNetSpeedFragment");
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
    public void onOpenSubtitleSelect(final OpenSubtitleSelectEvent event) {
        OpenSubtitleDialog openSubtitleDialog = this.openSubtitleDialogFragment;
        if (openSubtitleDialog != null && openSubtitleDialog.isVisible()) {
            this.openSubtitleDialogFragment.dismissAllowingStateLoss();
        }
        Observable.just(new ArrayList(event.getSrts())).map(new Function<ArrayList<SRT>, String>() { // from class: com.movieboxpro.android.player.VideoPlayer.13
            @Override // io.reactivex.functions.Function
            public String apply(ArrayList<SRT> srts) {
                if (VideoPlayer.this.mPlayerView != null) {
                    VideoPlayer.this.mPlayerView.startSRT2(srts, event.getContent(), event.getLanguage(), event.getSid());
                    return "";
                }
                return "";
            }
        }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>(this) { // from class: com.movieboxpro.android.player.VideoPlayer.12
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

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onTranslateSubtitle(TranslateSubtitleEvent event) {
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.showTranslateSubtitle(event.getSubtitles());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playNextEpisode() {
        int i;
        int i2;
        this.firstPlay = true;
        this.mPlayerView.releaseAudioPlay();
        BaseMediaModel baseMediaModel = this.movieDownload;
        if ((baseMediaModel != null ? String.valueOf(baseMediaModel.list.get(this.mPlayerView.getCurrQuality()).tmfid) : null) != null) {
            Movie_play(this.mPlayerView.getVideoView().getCurrentPosition());
        }
        this.isComplete = false;
        saveInDao(this.mPlayerView.getVideoView().getCurrentPosition(), this.mPlayerView.getCurrQuality(), true);
        int i3 = this.currPlayingPosition + 1;
        this.currPlayingPosition = i3;
        if (this.localFile) {
            checkDeleteDownloadFile();
            DownloadFile downloadFileFindDownloadFileByTidSeasonEpisode = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(this.movieDownload.getTid(), this.season, this.episode + 1);
            if (downloadFileFindDownloadFileByTidSeasonEpisode != null && downloadFileFindDownloadFileByTidSeasonEpisode.getStatus() == 1) {
                Disposable disposable = this.progressDisposable;
                if (disposable != null) {
                    disposable.dispose();
                }
                PlayNextEpisodeDialog playNextEpisodeDialog = this.playNextEpisodeDialog;
                if (playNextEpisodeDialog != null && playNextEpisodeDialog.isShowing()) {
                    this.playNextEpisodeDialog.dismiss();
                }
                IPlayerView iPlayerView = this.mPlayerView;
                if (iPlayerView != null) {
                    iPlayerView.clearSubtitle();
                }
                this.list.clear();
                deleteWatchNext();
                TvDetail tvDetail = new TvDetail();
                tvDetail.id = downloadFileFindDownloadFileByTidSeasonEpisode.getId();
                tvDetail.title = downloadFileFindDownloadFileByTidSeasonEpisode.getVideoName();
                tvDetail.box_type = downloadFileFindDownloadFileByTidSeasonEpisode.getBoxType();
                tvDetail.imdb_id = downloadFileFindDownloadFileByTidSeasonEpisode.getImdbId();
                tvDetail.poster = downloadFileFindDownloadFileByTidSeasonEpisode.getPoster();
                ArrayList arrayList = new ArrayList();
                arrayList.add(DownloadFileUtils.toMediaDownloadFile(downloadFileFindDownloadFileByTidSeasonEpisode));
                tvDetail.list = arrayList;
                this.movieDownload.list = arrayList;
                this.movieDownload.episode = this.episode + 1;
                this.episode++;
                excuetSer(false, -1, -1, false, null);
                Disposable disposable2 = this.disposable;
                if (disposable2 != null) {
                    disposable2.dispose();
                }
                this.playNextDismiss = false;
                this.mPlayerView.setVideoTitle(this.movieDownload.title + " S" + this.season + " E" + this.episode);
                getSaveSkipTime();
                updateEpisodeNavButtons();
                return;
            }
            if (this.currPlayingPosition < this.tvSeasonListList.size() && (i2 = this.currPlayingPosition) >= 0) {
                requestUrls(this.tvSeasonListList.get(i2), -1, false);
                return;
            } else {
                finish();
                return;
            }
        }
        if (i3 < this.tvSeasonListList.size() && (i = this.currPlayingPosition) >= 0) {
            requestUrls(this.tvSeasonListList.get(i), -1, false);
        } else {
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playLastEpisode() {
        int i;
        BaseMediaModel baseMediaModel = this.movieDownload;
        if ((baseMediaModel != null ? String.valueOf(baseMediaModel.list.get(this.mPlayerView.getCurrQuality()).tmfid) : null) != null) {
            Movie_play(this.mPlayerView.getVideoView().getCurrentPosition());
        }
        this.isComplete = false;
        saveInDao(this.mPlayerView.getVideoView().getCurrentPosition(), this.mPlayerView.getCurrQuality(), true);
        int i2 = this.currPlayingPosition - 1;
        this.currPlayingPosition = i2;
        if (i2 < this.tvSeasonListList.size() && (i = this.currPlayingPosition) >= 0) {
            requestUrls(this.tvSeasonListList.get(i), 1, false);
        } else {
            ToastUtils.showShort("No more episode");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showEpisodeListDialog() {
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.setKeepControlBarVisible(true);
        }
        TvDetail tvDetail = this.tvDetail;
        if (tvDetail != null) {
            this.episodeListStore.setSeasonMeta(tvDetail.seasonList != null ? this.tvDetail.seasonList : new String[0], this.tvDetail.years != null ? this.tvDetail.years : new String[0]);
        }
        PlayerEpisodeListDialog playerEpisodeListDialogNewInstance = PlayerEpisodeListDialog.newInstance(this.movieDownload.id, this.season, this.episode);
        playerEpisodeListDialogNewInstance.setStore(this.episodeListStore);
        playerEpisodeListDialogNewInstance.setCallback(new PlayerEpisodeListDialog.OnSelectEpisodeCallback() { // from class: com.movieboxpro.android.player.VideoPlayer$$ExternalSyntheticLambda4
            @Override // com.movieboxpro.android.view.dialog.PlayerEpisodeListDialog.OnSelectEpisodeCallback
            public final void onSelectEpisode(TvDetail.SeasonDetail seasonDetail, List list) {
                this.f$0.playSelectedEpisode(seasonDetail, list);
            }
        });
        playerEpisodeListDialogNewInstance.setOnDialogDismissed(new Function0() { // from class: com.movieboxpro.android.player.VideoPlayer$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return this.f$0.lambda$showEpisodeListDialog$1();
            }
        });
        playerEpisodeListDialogNewInstance.showAllowingStateLoss(getSupportFragmentManager(), "PlayerEpisodeListDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$showEpisodeListDialog$1() {
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.setKeepControlBarVisible(false);
            this.mPlayerView.requestEpisodeListFocus();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playSelectedEpisode(TvDetail.SeasonDetail item, List<TvDetail.SeasonDetail> seasonEpisodes) {
        if (item == null || seasonEpisodes == null || seasonEpisodes.isEmpty()) {
            return;
        }
        if (item.season == this.season && item.episode == this.episode) {
            return;
        }
        if (!Network.isConnected(this)) {
            DownloadFile downloadFileFindDownloadFileByTidSeasonEpisode = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(TextUtils.isEmpty(item.tid) ? this.movieDownload.id : item.tid, item.season, item.episode);
            if (downloadFileFindDownloadFileByTidSeasonEpisode == null || downloadFileFindDownloadFileByTidSeasonEpisode.getStatus() != 1) {
                ToastUtils.showShort("Episode " + item.episode + " is not downloaded");
                return;
            }
        }
        this.firstPlay = true;
        this.mPlayerView.releaseAudioPlay();
        if (((this.movieDownload == null || this.mPlayerView.getCurrQuality() < 0 || this.mPlayerView.getCurrQuality() >= this.movieDownload.list.size()) ? null : String.valueOf(this.movieDownload.list.get(this.mPlayerView.getCurrQuality()).tmfid)) != null) {
            Movie_play(this.mPlayerView.getVideoView().getCurrentPosition());
        }
        this.isComplete = false;
        saveInDao(this.mPlayerView.getVideoView().getCurrentPosition(), this.mPlayerView.getCurrQuality(), true);
        this.episodeListRollbackPosition = this.currPlayingPosition;
        this.episodeListRollbackSeasonList = this.tvSeasonListList != null ? new ArrayList<>(this.tvSeasonListList) : null;
        this.episodeListSwitchPending = true;
        this.tvSeasonListList = EpisodeSeasonMapper.INSTANCE.toTvSeasonList(this.movieDownload.id, seasonEpisodes);
        int iIndexOfEpisode = EpisodeSeasonMapper.INSTANCE.indexOfEpisode(seasonEpisodes, item.season, item.episode);
        if (iIndexOfEpisode < 0) {
            rollbackEpisodeListSelection();
            return;
        }
        this.currPlayingPosition = iIndexOfEpisode;
        TvSeasonList tvSeasonList = this.tvSeasonListList.get(iIndexOfEpisode);
        DownloadFile downloadFileFindDownloadFileByTidSeasonEpisode2 = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(tvSeasonList.getTid(), tvSeasonList.getSeason(), tvSeasonList.getEpisode());
        if (downloadFileFindDownloadFileByTidSeasonEpisode2 != null && downloadFileFindDownloadFileByTidSeasonEpisode2.getStatus() == 1) {
            clearEpisodeListRollback();
            Disposable disposable = this.progressDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            PlayNextEpisodeDialog playNextEpisodeDialog = this.playNextEpisodeDialog;
            if (playNextEpisodeDialog != null && playNextEpisodeDialog.isShowing()) {
                this.playNextEpisodeDialog.dismiss();
            }
            IPlayerView iPlayerView = this.mPlayerView;
            if (iPlayerView != null) {
                iPlayerView.clearSubtitle();
            }
            this.list.clear();
            deleteWatchNext();
            TvDetail tvDetail = new TvDetail();
            tvDetail.id = downloadFileFindDownloadFileByTidSeasonEpisode2.getId();
            tvDetail.title = downloadFileFindDownloadFileByTidSeasonEpisode2.getVideoName();
            tvDetail.box_type = downloadFileFindDownloadFileByTidSeasonEpisode2.getBoxType();
            tvDetail.imdb_id = downloadFileFindDownloadFileByTidSeasonEpisode2.getImdbId();
            tvDetail.poster = downloadFileFindDownloadFileByTidSeasonEpisode2.getPoster();
            ArrayList arrayList = new ArrayList();
            arrayList.add(DownloadFileUtils.toMediaDownloadFile(downloadFileFindDownloadFileByTidSeasonEpisode2));
            tvDetail.list = arrayList;
            this.movieDownload.list = arrayList;
            this.movieDownload.season = tvSeasonList.getSeason();
            this.movieDownload.episode = tvSeasonList.getEpisode();
            this.season = tvSeasonList.getSeason();
            this.episode = tvSeasonList.getEpisode();
            excuetSer(false, -1, -1, false, null);
            Disposable disposable2 = this.disposable;
            if (disposable2 != null) {
                disposable2.dispose();
            }
            this.playNextDismiss = false;
            this.mPlayerView.setVideoTitle(this.movieDownload.title + " S" + this.season + " E" + this.episode);
            getSaveSkipTime();
            updateEpisodeNavButtons();
            return;
        }
        requestUrls(tvSeasonList, 0, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void rollbackEpisodeListSelection() {
        ArrayList<TvSeasonList> arrayList = this.episodeListRollbackSeasonList;
        if (arrayList != null) {
            this.tvSeasonListList = arrayList;
        }
        int i = this.episodeListRollbackPosition;
        if (i >= 0) {
            this.currPlayingPosition = i;
        }
        clearEpisodeListRollback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearEpisodeListRollback() {
        this.episodeListRollbackPosition = -1;
        this.episodeListRollbackSeasonList = null;
        this.episodeListSwitchPending = false;
    }

    private void requestUrls(final TvSeasonList tvSeasonList, final int failPos, final boolean isContinue) {
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
        ((ObservableSubscribeProxy) Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", tvSeasonList.getTid(), String.valueOf(tvSeasonList.getSeason()), String.valueOf(tvSeasonList.getEpisode()), str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<BaseMediaModel>() { // from class: com.movieboxpro.android.player.VideoPlayer.14
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
                VideoPlayer.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(BaseMediaModel tvDetail) {
                super.onNext(tvDetail);
                if (tvDetail != null && tvDetail.list.size() > 0) {
                    VideoPlayer.this.resetVideoUrl(tvDetail, isContinue);
                    VideoPlayer.this.clearEpisodeListRollback();
                    return;
                }
                if (VideoPlayer.this.episodeListSwitchPending) {
                    VideoPlayer.this.showToast(String.format("Episode %s is not available", Integer.valueOf(tvSeasonList.getEpisode())));
                    VideoPlayer.this.rollbackEpisodeListSelection();
                } else {
                    if (VideoPlayer.this.currPlayingPosition == VideoPlayer.this.tvSeasonListList.size() - 1) {
                        VideoPlayer.this.showToast(String.format("Episode %s is not available", Integer.valueOf(tvSeasonList.getEpisode())));
                        return;
                    }
                    VideoPlayer.this.showToast(String.format("Episode %s is not available,press again to play Episode %s", Integer.valueOf(tvSeasonList.getEpisode()), Integer.valueOf(tvSeasonList.getEpisode() + 1)));
                    if (VideoPlayer.this.playNextEpisodeDialog != null) {
                        VideoPlayer.this.playNextEpisodeDialog.show();
                    }
                }
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
                VideoPlayer.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                if (VideoPlayer.this.episodeListSwitchPending) {
                    ToastUtils.showShort("Load failed:" + e.getMessage());
                    VideoPlayer.this.hideLoading();
                    VideoPlayer.this.rollbackEpisodeListSelection();
                    return;
                }
                ToastUtils.showShort("Load failed:" + e.getMessage());
                VideoPlayer.this.hideLoading();
                VideoPlayer.this.currPlayingPosition += failPos;
                VideoPlayer.this.finish();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetVideoUrl(BaseMediaModel tvDetail, boolean isContinue) {
        Disposable disposable = this.progressDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        PlayNextEpisodeDialog playNextEpisodeDialog = this.playNextEpisodeDialog;
        if (playNextEpisodeDialog != null && playNextEpisodeDialog.isShowing()) {
            this.playNextEpisodeDialog.dismiss();
        }
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            iPlayerView.clearSubtitle();
        }
        this.list.clear();
        deleteWatchNext();
        this.movieDownload.list = tvDetail.list;
        this.movieDownload.quality = tvDetail.quality;
        this.movieDownload.seconds = tvDetail.seconds;
        TvSeasonList tvSeasonList = this.tvSeasonListList.get(this.currPlayingPosition);
        this.movieDownload.id = tvSeasonList.getTid();
        this.movieDownload.season = tvSeasonList.getSeason();
        this.movieDownload.episode = tvSeasonList.getEpisode();
        this.season = tvSeasonList.getSeason();
        this.episode = tvSeasonList.getEpisode();
        excuetSer(isContinue, -1, -1, false, null);
        Disposable disposable2 = this.disposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        this.playNextDismiss = false;
        this.mPlayerView.setVideoTitle(this.movieDownload.title + " S" + this.season + " E" + this.episode);
        getSaveSkipTime();
        updateEpisodeNavButtons();
    }

    private void deleteWatchNext() {
        Intent intent = new Intent(this, (Class<?>) DealWatchNextService.class);
        intent.setAction("delete");
        intent.putExtra("id", this.movieDownload.id);
    }

    @Override // com.movieboxpro.android.view.listener.IViewController
    public void initData() {
        this.movieDownload = (BaseMediaModel) getSerializable("videoplayer_params", new BaseMediaModel());
        this.tvDetail = (TvDetail) getSerializable("videoplayer_params", new TvDetail());
        this.season = getIntParam(VideoPlayerActivity.VIDEO_ID, 1);
        this.episode = getIntParam("videoplayer_episode", 1);
        this.isRestart = getBooleanParam(VideoPlayerActivity.IS_RESTART, false);
        this.movieDownload.season = this.season;
        this.movieDownload.episode = this.episode;
        if (getBooleanParam(VideoPlayerActivity.IS_NEXT, false)) {
            ToastUtils.showShort("Play next Episode Automatically");
        }
        MLog.d(this.TAG, "SSSS" + this.season + "  " + this.episode);
        initPlayList();
        excuetSer(true, -1, -1, true, null);
        getSaveSkipTime();
    }

    private void initPlayList() {
        ArrayList parcelableArrayListExtra = getIntent().getParcelableArrayListExtra(SEASON_TV_LIST);
        this.tvSeasonListList = parcelableArrayListExtra;
        if (parcelableArrayListExtra == null) {
            this.tvSeasonListList = new ArrayList();
        }
        if (this.tvSeasonListList.size() == 0) {
            requestSeasonsInfo(this.season, this.movieDownload.id);
        } else {
            initCurrPlayPosition();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCurrPlayPosition() {
        if (this.tvSeasonListList != null) {
            for (int i = 0; i < this.tvSeasonListList.size(); i++) {
                if (this.tvSeasonListList.get(i).getEpisode() == this.episode) {
                    this.currPlayingPosition = i;
                    return;
                }
            }
        }
    }

    private void requestSeasonsInfo(final int season, String id) {
        ((ObservableSubscribeProxy) Http.getService().TV_episode(API.Tv.TV_EPISODE, id, season + "", "", App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new BaseObserver<List<TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.player.VideoPlayer.15
            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                super.onSubscribe(d);
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onNext(List<TvDetail.SeasonDetail> seasonDetails) {
                super.onNext(seasonDetails);
                if (seasonDetails != null) {
                    VideoPlayer.this.episodeListStore.putEpisodes(season + "", "", seasonDetails);
                }
                if (VideoPlayer.this.tvSeasonListList == null) {
                    VideoPlayer.this.tvSeasonListList = new ArrayList();
                }
                for (TvDetail.SeasonDetail seasonDetail : seasonDetails) {
                    TvSeasonList tvSeasonList = new TvSeasonList();
                    tvSeasonList.setImdbId(seasonDetail.imdb_id);
                    tvSeasonList.setEpisode(seasonDetail.episode);
                    tvSeasonList.setSeason(seasonDetail.season);
                    tvSeasonList.setTid(VideoPlayer.this.movieDownload.id);
                    tvSeasonList.setId(seasonDetail.id);
                    if (seasonDetail.play_progress != null) {
                        Integer num = seasonDetail.play_progress.get("over");
                        Integer num2 = seasonDetail.play_progress.get("seconds");
                        tvSeasonList.setOver(num != null ? num.intValue() : 0);
                        tvSeasonList.setSeconds(num2 != null ? num2.intValue() : 0);
                    }
                    VideoPlayer.this.tvSeasonListList.add(tvSeasonList);
                }
                VideoPlayer.this.initCurrPlayPosition();
                VideoPlayer.this.updateEpisodeNavButtons();
            }

            @Override // com.movieboxpro.android.base.BaseObserver, io.reactivex.Observer
            public void onComplete() {
                super.onComplete();
            }

            @Override // com.movieboxpro.android.base.BaseObserver
            public void onError(ApiException e) {
                VideoPlayer.this.hideLoading();
            }
        });
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
        }
        this.mPlayerView.init(2).alwaysFullScreen().enableOrientation().setOnCompletionListener(new IMediaPlayer.OnCompletionListener() { // from class: com.movieboxpro.android.player.VideoPlayer.16
            @Override // tv.danmaku.ijk.media.player.IMediaPlayer.OnCompletionListener
            public void onCompletion(IMediaPlayer iMediaPlayer) {
                VideoPlayer.this.checkDeleteDownloadFile();
                VideoPlayer.this.isComplete = true;
                if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_PLAY_NEXT, false)) {
                    VideoPlayer.this.Movie_play(0);
                    VideoPlayer.this.playNextEpisode();
                } else {
                    VideoPlayer.this.finish();
                }
            }
        });
        this.mPlayerView.setMovieDownload(this.movieDownload);
        this.mPlayerView.setFragmentManager(getSupportFragmentManager());
        this.mPlayerView.setPlayerViewCallback(new PlayerViewCallback() { // from class: com.movieboxpro.android.player.VideoPlayer.17
            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void onOpenSubtitleClicked() {
                VideoPlayer.this.showOpenSubtitle();
                VideoPlayer.this.mPlayerView.doPause();
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void lastVideo() {
                VideoPlayer.this.playLastEpisode();
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void nextVideo() {
                VideoPlayer.this.playNextEpisode();
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void showEpisodeList() {
                VideoPlayer.this.showEpisodeListDialog();
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void disSelectSubtitle(String sid) {
                VideoPlayer.this.disSelectSubtitle(sid);
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void switchQuality(int pos, MediaQualityInfo qualityInfo) {
                VideoPlayer videoPlayer = VideoPlayer.this;
                videoPlayer.excuetSer(true, pos, videoPlayer.mPlayerView.getCurPosition(), true, null);
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void onOnlineQuality(List<MediaQualityInfo> list, List<BaseMediaModel.DownloadFile> files) {
                VideoPlayer.this.hlsAudioTracks.clear();
                VideoPlayer.this.list.clear();
                VideoPlayer.this.movieDownload.list.clear();
                VideoPlayer.this.movieDownload.list.addAll(files);
                for (BaseMediaModel.DownloadFile downloadFile : VideoPlayer.this.movieDownload.list) {
                    if ("video".equals(downloadFile.type) || downloadFile.type == null || TextUtils.isEmpty(downloadFile.type)) {
                        VideoPlayer.this.list.add(new MediaQualityInfo(0, downloadFile.h265, downloadFile.path, downloadFile.real_quality, downloadFile.size, TimeUtils.formatTime4(downloadFile.dateline * 1000), downloadFile.count, downloadFile.vip_only + "", downloadFile.filename, false, downloadFile.bitstream, downloadFile.original, downloadFile.hdr, downloadFile.colorbit, downloadFile.filename, downloadFile.hls, downloadFile.mmfid, downloadFile.tmfid));
                    } else if ("audio".equals(downloadFile.type)) {
                        VideoPlayer.this.hlsAudioTracks.add(downloadFile);
                    }
                }
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void saveProgress() {
                VideoPlayer videoPlayer = VideoPlayer.this;
                videoPlayer.saveInDao(videoPlayer.mPlayerView.getVideoView().getCurrentPosition(), VideoPlayer.this.mPlayerView.getCurrQuality(), false);
                VideoPlayer videoPlayer2 = VideoPlayer.this;
                videoPlayer2.Movie_play(videoPlayer2.mPlayerView.getVideoView().getCurrentPosition());
            }

            @Override // com.movieboxpro.android.listener.PlayerViewCallback
            public void showPlayerEngine() {
                ChoosePlayerEngineDialog choosePlayerEngineDialogNewInstance = ChoosePlayerEngineDialog.INSTANCE.newInstance(VideoPlayer.this.isHls());
                choosePlayerEngineDialogNewInstance.setChoosePlayerEngineListener(new ChoosePlayerEngineDialog.ChoosePlayerEngineListener() { // from class: com.movieboxpro.android.player.VideoPlayer.17.1
                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerEngineDialog.ChoosePlayerEngineListener
                    public void onChoosePlayerEngine(String engine2) {
                        PrefsUtils.getInstance().putString(Constant.Prefs.PLAYER_ENGINE, engine2);
                        VideoPlayer.this.excuetSer(true, VideoPlayer.this.mPlayerView.getCurrQuality(), VideoPlayer.this.mPlayerView.getCurPosition(), false, engine2);
                    }
                });
                choosePlayerEngineDialogNewInstance.show(VideoPlayer.this.getSupportFragmentManager(), "ChoosePlayerEngineDialog");
            }
        });
        Glide.with((FragmentActivity) this).load(Integer.valueOf(R.drawable.ic_black_video)).into(this.mPlayerView.getImageThumb());
        updateEpisodeNavButtons();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEpisodeNavButtons() {
        if (this.mPlayerView == null) {
            return;
        }
        List<TvSeasonList> list = this.tvSeasonListList;
        if (list == null || list.isEmpty() || this.tvSeasonListList.size() <= 1) {
            this.mPlayerView.hideNextVideo();
            this.mPlayerView.hideLastVideo();
            return;
        }
        if (this.currPlayingPosition > 0) {
            this.mPlayerView.showLastVideo();
        } else {
            this.mPlayerView.hideLastVideo();
        }
        int i = this.currPlayingPosition;
        if (i >= 0 && i < this.tvSeasonListList.size() - 1) {
            this.mPlayerView.showNextVideo();
        } else {
            this.mPlayerView.hideNextVideo();
        }
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
        OpenSubtitleDialog openSubtitleDialogNewInstance = OpenSubtitleDialog.newInstance(this.tvSeasonListList.get(this.currPlayingPosition).getImdbId(), this.movieDownload.id, this.season, this.episode);
        this.openSubtitleDialogFragment = openSubtitleDialogNewInstance;
        openSubtitleDialogNewInstance.setListener(new OpenSubtitleDialog.OnOpenSubtitleListener() { // from class: com.movieboxpro.android.player.VideoPlayer.18
            @Override // com.movieboxpro.android.view.dialog.OpenSubtitleDialog.OnOpenSubtitleListener
            public void onSelectLanguage() {
                VideoPlayer.this.showOpenSubtitle();
            }
        });
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.add(this.openSubtitleDialogFragment, "OpenSubtitleDialog");
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        fragmentTransactionBeginTransaction.show(this.openSubtitleDialogFragment);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkDeleteDownloadFile() {
        if (SettingManager.INSTANCE.getSmartDownload() && SettingManager.INSTANCE.getAutoDeleteWatched()) {
            Observable.just("").map(new Function<String, Object>() { // from class: com.movieboxpro.android.player.VideoPlayer.20
                @Override // io.reactivex.functions.Function
                public Object apply(String s) throws Exception {
                    DownloadFile downloadFileFindDownloadFileByTidSeasonEpisode = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(VideoPlayer.this.movieDownload.getTid(), VideoPlayer.this.season, VideoPlayer.this.episode);
                    if (downloadFileFindDownloadFileByTidSeasonEpisode != null) {
                        DownloadFileHelper.INSTANCE.getInstance().deleteDownloadFileByDownloadId(downloadFileFindDownloadFileByTidSeasonEpisode.getDownloadId());
                        if (downloadFileFindDownloadFileByTidSeasonEpisode.isHls()) {
                            androidx.media3.exoplayer.offline.DownloadService.sendRemoveDownload(App.getContext(), MyDownloadService.class, downloadFileFindDownloadFileByTidSeasonEpisode.getDownloadId(), false);
                            return "";
                        }
                        try {
                            DocumentFile documentFileFromSingleUri = DocumentFile.fromSingleUri(App.getContext(), Uri.parse(downloadFileFindDownloadFileByTidSeasonEpisode.getPath()));
                            if (downloadFileFindDownloadFileByTidSeasonEpisode != null) {
                                documentFileFromSingleUri.delete();
                                return "";
                            }
                            return "";
                        } catch (Exception unused) {
                            return "";
                        }
                    }
                    return "";
                }
            }).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Object>(this) { // from class: com.movieboxpro.android.player.VideoPlayer.19
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                }

                @Override // io.reactivex.Observer
                public void onNext(Object o) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:119:0x026c  */
    /* JADX WARN: Code duplicated, block: B:121:0x026f  */
    /* JADX WARN: Code duplicated, block: B:123:0x0272  */
    /* JADX WARN: Code duplicated, block: B:126:0x027b  */
    /* JADX WARN: Code duplicated, block: B:128:0x028f  */
    /* JADX WARN: Code duplicated, block: B:132:0x02a6 A[EDGE_INSN: B:132:0x02a6->B:133:0x02a7 BREAK  A[LOOP:4: B:124:0x0273->B:131:0x02a3]] */
    /* JADX WARN: Code duplicated, block: B:135:0x02bd  */
    /* JADX WARN: Code duplicated, block: B:139:0x02d9  */
    /* JADX WARN: Code duplicated, block: B:166:0x02a6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:167:0x02a3 A[ADDED_TO_REGION, REMOVE, SYNTHETIC] */
    public void excuetSer(boolean isContinue, int pos, int startS, boolean initPlayer, String selectEngine) {
        int i;
        int seconds;
        int start_time;
        int i2;
        int videoSource;
        IPlayerView iPlayerView;
        int i3;
        this.hlsAudioTracks.clear();
        this.list.clear();
        BaseMediaModel baseMediaModel = this.movieDownload;
        if (baseMediaModel == null || baseMediaModel.list.size() == 0) {
            ToastUtils.showShort("Not Available");
            finish();
            return;
        }
        for (BaseMediaModel.DownloadFile downloadFile : this.movieDownload.list) {
            if ("video".equals(downloadFile.type) || downloadFile.type == null || TextUtils.isEmpty(downloadFile.type)) {
                this.list.add(new MediaQualityInfo(0, downloadFile.h265, downloadFile.path, downloadFile.real_quality, downloadFile.size, TimeUtils.formatTime4(downloadFile.dateline * 1000), downloadFile.count, downloadFile.vip_only + "", downloadFile.filename, false, downloadFile.bitstream, downloadFile.original, downloadFile.hdr, downloadFile.colorbit, downloadFile.filename, downloadFile.hls, downloadFile.mmfid, downloadFile.tmfid));
            } else if ("audio".equals(downloadFile.type)) {
                this.hlsAudioTracks.add(downloadFile);
            }
            if (downloadFile.local) {
                this.localFile = true;
            }
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
            PlayRecode playRecodeFindByEpisode = App.getDB().playRecodeDao().findByEpisode(2, this.movieDownload.id, this.season, this.episode);
            if (this.isRestart) {
                this.isRestart = false;
            } else {
                int i6 = this.currPlayingPosition;
                if (i6 >= 0 && i6 < this.tvSeasonListList.size()) {
                    if (this.tvSeasonListList.get(this.currPlayingPosition).getOver() != 1) {
                        seconds = this.tvSeasonListList.get(this.currPlayingPosition).getSeconds();
                    }
                    if (!isContinue) {
                        start_time = 0;
                    }
                    if (startS != -1) {
                        start_time = startS;
                    }
                    if (pos == -1) {
                        i2 = i;
                        break;
                    }
                    i3 = 0;
                    while (true) {
                        if (i3 < this.list.size()) {
                            i2 = i;
                            break;
                        } else {
                            if (this.list.get(i3).getPath().startsWith(IDataSource.SCHEME_HTTP_TAG)) {
                            }
                            i3++;
                        }
                    }
                    this.mPlayerView.setHlsTracks(this.hlsAudioTracks);
                    videoSource = this.mPlayerView.setVideoSource(this.list, this.onVipClickListener, start_time, isContinue, i2);
                    if (videoSource >= 0) {
                        int i7 = this.movieDownload.list.get(videoSource).fid;
                        iPlayerView = this.mPlayerView;
                        if (iPlayerView instanceof IjkPlayerView) {
                            iPlayerView.seekTo(start_time);
                            this.isRestart = false;
                        }
                        setData(i7, this.movieDownload.list.get(videoSource).filename);
                    }
                    this.mPlayerView.setVideoTitle(this.movieDownload.title + " S" + this.season + " E" + this.episode);
                    return;
                }
                if (playRecodeFindByEpisode != null) {
                    start_time = playRecodeFindByEpisode.getStart_time();
                } else {
                    seconds = this.movieDownload.seconds;
                }
                if (!isContinue) {
                    start_time = 0;
                }
                if (startS != -1) {
                    start_time = startS;
                }
                if (pos == -1) {
                    i2 = i;
                    break;
                }
                i3 = 0;
                while (true) {
                    if (i3 < this.list.size()) {
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
                this.mPlayerView.setHlsTracks(this.hlsAudioTracks);
                videoSource = this.mPlayerView.setVideoSource(this.list, this.onVipClickListener, start_time, isContinue, i2);
                if (videoSource >= 0 && videoSource < this.movieDownload.list.size()) {
                    int i8 = this.movieDownload.list.get(videoSource).fid;
                    iPlayerView = this.mPlayerView;
                    if ((iPlayerView instanceof IjkPlayerView) && !this.isRestart) {
                        iPlayerView.seekTo(start_time);
                        this.isRestart = false;
                    }
                    setData(i8, this.movieDownload.list.get(videoSource).filename);
                }
                this.mPlayerView.setVideoTitle(this.movieDownload.title + " S" + this.season + " E" + this.episode);
                return;
                start_time = seconds * 1000;
                if (!isContinue) {
                    start_time = 0;
                }
                if (startS != -1) {
                    start_time = startS;
                }
                if (pos == -1) {
                    i2 = i;
                    break;
                }
                i3 = 0;
                while (true) {
                    if (i3 < this.list.size()) {
                        i2 = i;
                        break;
                    } else {
                        if (this.list.get(i3).getPath().startsWith(IDataSource.SCHEME_HTTP_TAG)) {
                        }
                        i3++;
                    }
                }
                this.mPlayerView.setHlsTracks(this.hlsAudioTracks);
                videoSource = this.mPlayerView.setVideoSource(this.list, this.onVipClickListener, start_time, isContinue, i2);
                if (videoSource >= 0) {
                    int i9 = this.movieDownload.list.get(videoSource).fid;
                    iPlayerView = this.mPlayerView;
                    if (iPlayerView instanceof IjkPlayerView) {
                        iPlayerView.seekTo(start_time);
                        this.isRestart = false;
                    }
                    setData(i9, this.movieDownload.list.get(videoSource).filename);
                }
                this.mPlayerView.setVideoTitle(this.movieDownload.title + " S" + this.season + " E" + this.episode);
                return;
            }
            start_time = 0;
            if (!isContinue) {
                start_time = 0;
            }
            if (startS != -1) {
                start_time = startS;
            }
            if (pos == -1) {
                i2 = i;
                break;
            }
            i3 = 0;
            while (true) {
                if (i3 < this.list.size()) {
                    i2 = i;
                    break;
                } else {
                    if (this.list.get(i3).getPath().startsWith(IDataSource.SCHEME_HTTP_TAG)) {
                    }
                    i3++;
                }
            }
            this.mPlayerView.setHlsTracks(this.hlsAudioTracks);
            videoSource = this.mPlayerView.setVideoSource(this.list, this.onVipClickListener, start_time, isContinue, i2);
            if (videoSource >= 0) {
                int i10 = this.movieDownload.list.get(videoSource).fid;
                iPlayerView = this.mPlayerView;
                if (iPlayerView instanceof IjkPlayerView) {
                    iPlayerView.seekTo(start_time);
                    this.isRestart = false;
                }
                setData(i10, this.movieDownload.list.get(videoSource).filename);
            }
            this.mPlayerView.setVideoTitle(this.movieDownload.title + " S" + this.season + " E" + this.episode);
            return;
        }
        ToastUtils.showShort("No available source");
    }

    public void setData(int fid, final String filename) {
        if (Network.isConnected(this.context)) {
            APIService aPIService = this.service;
            String str = App.isLogin() ? App.getUserData().uid_v2 : "";
            aPIService.TV_srt_list_v2(API.Tv.TV_SRTLIST_V2, str, this.movieDownload.id, fid + "", this.season + "", this.episode + "", App.deviceLang, BuildConfig.VERSION_NAME).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.player.VideoPlayer.21
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable e) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable d) {
                    RxManager.addDisposable(VideoPlayer.this.TAG, d);
                }

                @Override // io.reactivex.Observer
                public void onNext(String s) {
                    ResponseSubtitleRecord2 responseSubtitleRecord2;
                    SRTModel.subTitles subtitlesFindNameSameSubtitle;
                    int iIntValue = ((JSONObject) JSONObject.parse(s)).getInteger("code").intValue();
                    VideoPlayer.this.srtHash = new LinkedHashMap<>();
                    if (iIntValue != 1 || (responseSubtitleRecord2 = (ResponseSubtitleRecord2) ((BaseResponse) JSON.parseObject(s, RxUtils.buildType(BaseResponse.class, ResponseSubtitleRecord2.class), new Feature[0])).getData()) == null) {
                        return;
                    }
                    if (responseSubtitleRecord2.getSelect() == null && PrefsUtils.getInstance().getBoolean(Constant.Prefs.AUTO_SELECT_SUBTITLE, false)) {
                        String lowerCase = App.deviceLang.toLowerCase(Locale.getDefault());
                        for (SRTModel sRTModel : responseSubtitleRecord2.getList()) {
                            if (sRTModel.subtitles != null && sRTModel.subtitles.size() > 0 && sRTModel.subtitles.get(0).lang.contains(lowerCase)) {
                                try {
                                    subtitlesFindNameSameSubtitle = VideoPlayer.this.findNameSameSubtitle(sRTModel.subtitles, filename);
                                } catch (Exception unused) {
                                    subtitlesFindNameSameSubtitle = null;
                                }
                                if (subtitlesFindNameSameSubtitle != null) {
                                    responseSubtitleRecord2.setSelect(subtitlesFindNameSameSubtitle);
                                    subtitlesFindNameSameSubtitle.setMyselect(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                                    subtitlesFindNameSameSubtitle.setSelect(true);
                                    break;
                                } else {
                                    responseSubtitleRecord2.setSelect(sRTModel.subtitles.get(0));
                                    sRTModel.subtitles.get(0).setMyselect(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
                                    sRTModel.subtitles.get(0).setSelect(true);
                                    break;
                                }
                            }
                        }
                    }
                    for (SRTModel sRTModel2 : responseSubtitleRecord2.getList()) {
                        VideoPlayer.this.srtHash.put(sRTModel2.language, sRTModel2.subtitles);
                    }
                    if (VideoPlayer.this.mPlayerView != null) {
                        VideoPlayer.this.mPlayerView.setSRTList(VideoPlayer.this.srtHash, VideoPlayer.this.onDownloadClickListener, responseSubtitleRecord2.getSelect());
                    }
                    if (responseSubtitleRecord2.getSelect() != null) {
                        VideoPlayer.this.loadSubtitleNoProgress(responseSubtitleRecord2.getSelect());
                    }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void loadSubtitleNoProgress(final SRTModel.subTitles select) {
        final String file_path = select.getFile_path();
        ((ObservableSubscribeProxy) HttpHelper.getApiService().downloadString(file_path).map(new Function<Response<String>, Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayer.23
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
                    if (VideoPlayer.this.mPlayerView != null) {
                        VideoPlayer.this.mPlayerView.startSRT(arrayList, response.body(), select.getLang(), select.getFile_path(), select);
                    }
                    return true;
                }
                return false;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayer.22
            @Override // io.reactivex.Observer
            public void onNext(Boolean success) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                VideoPlayer.this.showLoading();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
                VideoPlayer.this.hideLoading();
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                VideoPlayer.this.hideLoading();
            }
        });
    }

    public void getSRT(final String url, final String language) {
        ((ObservableSubscribeProxy) HttpHelper.getApiService().download(url).map(new Function<ResponseBody, Pair<ArrayList<SRT>, String>>(this) { // from class: com.movieboxpro.android.player.VideoPlayer.25
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
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<ArrayList<SRT>, String>>() { // from class: com.movieboxpro.android.player.VideoPlayer.24
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                VideoPlayer.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<ArrayList<SRT>, String> model) {
                if (VideoPlayer.this.mPlayerView != null) {
                    VideoPlayer.this.mPlayerView.startSRT(model.getFirst(), model.getSecond(), language, url, null);
                }
                VideoPlayer.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                ToastUtils.showShort("Subtitle load failed:" + e.getMessage());
                VideoPlayer.this.hideLoading();
            }
        });
    }

    public void disSelectSubtitle(String sid) {
        APIService aPIService = this.service;
        String str = App.isLogin() ? App.getUserData().uid_v2 : "";
        aPIService.Tv_srt_select(API.Tv.TV_SRTSELECT, str, this.movieDownload.id, this.season + "", this.episode + "", sid, "del", BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>(this) { // from class: com.movieboxpro.android.player.VideoPlayer.26
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

    public void Movie_srt_select(String sid) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE)) {
            return;
        }
        APIService aPIService = this.service;
        String str = App.isLogin() ? App.getUserData().uid_v2 : "";
        aPIService.Tv_srt_select(API.Tv.TV_SRTSELECT, str, this.movieDownload.id, this.season + "", this.episode + "", sid, null, BuildConfig.APPLICATION_ID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>(this) { // from class: com.movieboxpro.android.player.VideoPlayer.27
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

    public int choose() {
        for (int i = 0; i < this.movieDownload.list.size(); i++) {
            if (this.movieDownload.list.get(i).path != null && !TextUtils.isEmpty(this.movieDownload.list.get(i).path)) {
                return i;
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
        IPlayerView iPlayerView;
        if (!this.enterPicMode && (iPlayerView = this.mPlayerView) != null) {
            Movie_play(iPlayerView.getVideoView().getCurrentPosition());
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
        ((ObservableSubscribeProxy) Observable.interval(60L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.player.VideoPlayer.28
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
                VideoPlayer.this.feedbackDisposable = d;
            }

            @Override // io.reactivex.Observer
            public void onNext(Long aLong) {
                if (Network.isConnected(VideoPlayer.this)) {
                    VideoPlayer.this.savePlayingFeedback();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void savePlayingFeedback() {
        ((ObservableSubscribeProxy) Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, this.movieDownload.id, SystemUtils.getUniqueId(this), this.movieDownload.box_type, this.movieDownload.season, this.movieDownload.episode, Build.BRAND, Build.MODEL).map(new Function<String, Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayer.30
            @Override // io.reactivex.functions.Function
            public Boolean apply(String s) throws Exception {
                BaseResponse baseResponse = (BaseResponse) JSON.parseObject(s, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
                if (baseResponse.getCode() == -88) {
                    VideoPlayer.this.showScreenManage(baseResponse);
                    return true;
                }
                return false;
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Boolean>() { // from class: com.movieboxpro.android.player.VideoPlayer.29
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                VideoPlayer.this.saveFeedbackDisposable = d;
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Boolean model) {
                if (!model.booleanValue() || VideoPlayer.this.mPlayerView == null) {
                    return;
                }
                VideoPlayer.this.mPlayerView.doPause();
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

    private void deletePlayingStatus() {
        Disposable disposable = this.saveFeedbackDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Disposable disposable2 = this.feedbackDisposable;
        if (disposable2 != null) {
            disposable2.dispose();
        }
        Http.getService().deletePlayingStatus(API.Common.DELETE_PLAYING_STATUS, SystemUtils.getUniqueId(this), App.getUserData().uid_v2).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<String>(this) { // from class: com.movieboxpro.android.player.VideoPlayer.31
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
                    intent.putExtra(DealWatchNextService.MEDIA_DATA, this.tvDetail);
                    intent.putExtra(DealWatchNextService.SEASON, this.season);
                    intent.putExtra(DealWatchNextService.EPISODE, this.episode);
                    intent.putExtra("duration", this.mPlayerView.getDurarion());
                    intent.putExtra("progress", this.mPlayerView.getCurPosition());
                }
                startService(intent);
            }
        }
    }

    @Override // com.movieboxpro.android.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        stopShowTestTimer();
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            int currQuality = iPlayerView.getCurrQuality();
            int iOnDestroy = this.mPlayerView.onDestroy();
            MLog.d(this.TAG, "sdadsklajd : " + iOnDestroy);
            saveInDao(iOnDestroy, currQuality, this.isComplete);
        }
        RxManager.remove(this.TAG);
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
        CallManager.cancelAll(getClass().getSimpleName());
        cancel();
        if (!getIntent().getBooleanExtra("isLocalFile", false) && !PrefsUtils.getInstance().getBoolean(Constant.Prefs.SUPER_CHILD_MODE)) {
            if (Utils.isActivityInList(MainActivity.class)) {
                TvDetailActivity.INSTANCE.starter(this, this.movieDownload.id, this.movieDownload.poster);
            } else {
                MainActivity.INSTANCE.start(this);
                if (getIntent().getBooleanExtra(VideoPlayerActivity.IS_FROM_FEATURED, false)) {
                    TvDetailActivity.INSTANCE.starter(this, this.movieDownload.id, this.movieDownload.poster);
                }
            }
        }
        RefreshWatchedLiveData.INSTANCE.get().setValue(true);
        RefreshWatchingLiveData.INSTANCE.get().setValue(true);
        super.onDestroy();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode, Configuration newConfig) {
        this.enterPicMode = true;
        IPlayerView iPlayerView = this.mPlayerView;
        if (iPlayerView != null) {
            if (isInPictureInPictureMode) {
                iPlayerView._toggleControlBar();
                this.mPlayerView.setSubtitleSize(8);
                this.mPlayerView.setSubtitleMarginBottom(DensityUtils.dp2px(this, 5.0f));
            } else {
                iPlayerView.setSubtitleSize(getSizeValue());
                this.mPlayerView.setSubtitleMarginBottom(DensityUtils.dp2px(this, 15.0f));
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

    public void saveInDao(int position, int quality, boolean isComplete) {
        MLog.d(this.TAG, "SSSS" + position + this.movieDownload.id + "sss" + this.movieDownload.box_type);
        PlayRecode playRecodeFindByEpisode = App.getDB().playRecodeDao().findByEpisode(this.movieDownload.box_type, this.movieDownload.id, this.season, this.episode);
        if (playRecodeFindByEpisode == null) {
            PlayRecode playRecode = new PlayRecode();
            playRecode.setMovieId(this.movieDownload.id);
            playRecode.setBox_type(this.movieDownload.box_type);
            playRecode.setImdb_id(this.movieDownload.imdb_id);
            playRecode.setTitle(this.movieDownload.title);
            playRecode.setStart_time(position);
            playRecode.setQuality(quality);
            playRecode.setSeason(this.season);
            playRecode.setEpisode(this.episode);
            if (isComplete) {
                playRecode.setStart_time(0);
            }
            App.getDB().playRecodeDao().insert(playRecode);
            return;
        }
        playRecodeFindByEpisode.setStart_time(position);
        playRecodeFindByEpisode.setQuality(quality);
        playRecodeFindByEpisode.setSeason(this.season);
        playRecodeFindByEpisode.setEpisode(this.episode);
        if (isComplete) {
            playRecodeFindByEpisode.setStart_time(0);
        }
        App.getDB().playRecodeDao().update(playRecodeFindByEpisode);
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.movieboxpro.android.http.APIService] */
    /* JADX WARN: Type inference failed for: r25v0 */
    /* JADX WARN: Type inference failed for: r25v1, types: [int] */
    /* JADX WARN: Type inference failed for: r25v2 */
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
        if (this.season > 1000) {
            this.season = 1;
        }
        boolean z = this.isComplete;
        if (this.mPlayerView.getCurrQuality() < 0 || this.mPlayerView.getCurrQuality() >= this.movieDownload.list.size()) {
            return;
        }
        BaseMediaModel.DownloadFile downloadFile = this.movieDownload.list.get(this.mPlayerView.getCurrQuality());
        if (downloadFile.hls) {
            i = downloadFile.fid;
        } else {
            i = downloadFile.fid;
        }
        APIService aPIService = this.service;
        String str = App.isLogin() ? App.getUserData().uid_v2 : "";
        String str2 = this.movieDownload.id;
        String str3 = this.season + "";
        String str4 = this.episode + "";
        StringBuilder sb = new StringBuilder();
        int i2 = position / 1000;
        sb.append(i2);
        sb.append("");
        Observable<String> observableTv_play = aPIService.Tv_play(API.Tv.TV_PLAY, str, str2, str3, str4, sb.toString(), String.valueOf(i), BuildConfig.VERSION_NAME, SystemUtils.getUniqueId(this));
        IPlayerView iPlayerView = this.mPlayerView;
        ?? r25 = (iPlayerView == null || iPlayerView.getVideoView().getDuration() == 0 || position == 0 || this.mPlayerView.getVideoView().getDuration() - position > 180000) ? z : 1;
        ?? r1 = this.service;
        String str5 = App.isLogin() ? App.getUserData().uid_v2 : "";
        Observable.merge(observableTv_play, r1.TV_play_progress(API.Tv.TV_PLAY_PROGRESS, str5, this.movieDownload.id, String.valueOf(i), this.season + "", this.episode + "", i2 + "", r25, BuildConfig.VERSION_NAME)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.movieboxpro.android.player.VideoPlayer.32
            @Override // io.reactivex.Observer
            public void onError(Throwable throwable) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(String s) {
                Log.d(VideoPlayer.this.TAG, "onNext: " + s);
            }

            @Override // io.reactivex.Observer
            public void onComplete() {
                OnPlayerFinishLiveData.INSTANCE.get().setValue(true);
                EventBus.getDefault().post(new OnPlayerFinishEvent());
            }
        });
    }

    public void cancel() {
        Disposable disposable = this.mDisposable;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.mDisposable.dispose();
    }
}
