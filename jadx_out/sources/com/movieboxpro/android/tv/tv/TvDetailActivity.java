package com.movieboxpro.android.tv.tv;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.CommonBaseAdapter;
import com.movieboxpro.android.base.mvp.BaseMvpActivity;
import com.movieboxpro.android.db.dao.PlayRecodeDao;
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.OnPlayerFinishEvent;
import com.movieboxpro.android.event.RefreshFavoriteEvent;
import com.movieboxpro.android.event.RefreshMovieListEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.ApiService3;
import com.movieboxpro.android.listener.AddWatchPlanListener;
import com.movieboxpro.android.listener.CommonItemClickListener;
import com.movieboxpro.android.model.Actor;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.DetailMetaInfoBinder;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.HttpHelperKt;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ScreenUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.click.DoubleClickHelper;
import com.movieboxpro.android.view.activity.ActorDetailActivity;
import com.movieboxpro.android.view.activity.ChooseTvDownloadActivity;
import com.movieboxpro.android.view.dialog.AddToDialog;
import com.movieboxpro.android.view.dialog.AddToFavoriteDialogFragment;
import com.movieboxpro.android.view.dialog.AddWatchPlanDialog;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.ChooseLanguageDialogFragment;
import com.movieboxpro.android.view.dialog.ChoosePlayerDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment;
import com.movieboxpro.android.view.dialog.ChooseSeasonDialogFragment;
import com.movieboxpro.android.view.dialog.ChooseTranslateLanguageDialog;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.EpisodeSeasonMapper;
import com.movieboxpro.android.view.dialog.LowQualityHintDialog;
import com.movieboxpro.android.view.dialog.ReleasedHintDialog;
import com.movieboxpro.android.view.dialog.VideoInfoDialogFragment;
import com.movieboxpro.android.view.dialog.WatchPlanProgressDialog;
import com.movieboxpro.android.view.fragment.account.ChoosePlayFragment;
import com.movieboxpro.android.view.fragment.account.OpenChildModeDialog;
import com.movieboxpro.android.view.widget.LinearItemDecoration;
import com.movieboxpro.android.view.widget.UserAvatarView;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityTvDetail2Binding;
import com.movieboxpro.androidtv.databinding.LayoutDetailMetaRowBinding;
import com.owen.focus.FocusBorder;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: TvDetailActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000Ú\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 |2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u00042\u00020\u0005:\u0001|B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010-\u001a\u00020.H\u0014J\u0010\u0010/\u001a\u00020.2\u0006\u00100\u001a\u000201H\u0002J\u0010\u00102\u001a\u0002012\u0006\u00103\u001a\u00020\rH\u0002J\u0010\u00104\u001a\u00020.2\u0006\u00100\u001a\u000201H\u0002J\u0016\u00105\u001a\u00020.2\f\u00106\u001a\b\u0012\u0004\u0012\u00020!07H\u0016J\u0010\u00108\u001a\u00020.2\u0006\u00109\u001a\u00020\u0011H\u0016J\b\u0010:\u001a\u00020.H\u0002J\b\u0010;\u001a\u00020.H\u0002J\b\u0010<\u001a\u00020.H\u0002J\b\u0010=\u001a\u00020.H\u0002J\u0018\u0010>\u001a\u00020.2\u0006\u00109\u001a\u00020\u00112\u0006\u0010?\u001a\u00020\rH\u0016J\u0010\u0010@\u001a\u00020.2\u0006\u0010A\u001a\u000201H\u0016J\b\u0010B\u001a\u00020.H\u0014J\u0010\u0010C\u001a\u00020.2\u0006\u0010D\u001a\u00020EH\u0007J\u0018\u0010F\u001a\u00020.2\u0006\u0010G\u001a\u00020H2\u0006\u0010\b\u001a\u00020\tH\u0002J!\u0010I\u001a\u00020.2\u0012\u0010J\u001a\n\u0012\u0006\b\u0001\u0012\u00020H0K\"\u00020HH\u0002¢\u0006\u0002\u0010LJ\b\u0010M\u001a\u00020.H\u0014J2\u0010N\u001a\u00020.2\b\u0010O\u001a\u0004\u0018\u00010P2\u0006\u0010Q\u001a\u0002012\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u00100\u001a\u000201H\u0016J\u0012\u0010R\u001a\u00020.2\b\u0010S\u001a\u0004\u0018\u00010\rH\u0002J\u0010\u0010T\u001a\u00020.2\u0006\u0010U\u001a\u00020\u0011H\u0002J2\u0010V\u001a\u00020.2\b\u0010O\u001a\u0004\u0018\u00010P2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010Q\u001a\u0002012\u0006\u00100\u001a\u000201H\u0002J\u0018\u0010W\u001a\u0002012\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J'\u0010X\u001a\u00020.2\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010Y\u001a\u00020\u0011H\u0002¢\u0006\u0002\u0010ZJ\u0010\u0010[\u001a\u00020.2\u0006\u0010D\u001a\u00020\\H\u0007J\u001a\u0010^\u001a\u00020.2\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010_\u001a\u000201H\u0016J\u0016\u0010`\u001a\u00020.2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020$07H\u0016J\u001e\u0010`\u001a\u00020.2\u0006\u0010&\u001a\u00020\u00112\f\u0010a\u001a\b\u0012\u0004\u0012\u00020$07H\u0016J\u001e\u0010b\u001a\u00020.2\u0006\u0010'\u001a\u00020\u00112\f\u0010a\u001a\b\u0012\u0004\u0012\u00020$07H\u0016J\b\u0010c\u001a\u00020.H\u0016J\b\u0010d\u001a\u00020.H\u0016J\b\u0010e\u001a\u00020.H\u0016JT\u0010f\u001a\u00020.2\u0016\u00106\u001a\u0012\u0012\u0004\u0012\u00020h0gj\b\u0012\u0004\u0012\u00020h`i2\b\u0010j\u001a\u0004\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010,\u001a\u00020\u00112\u0006\u0010+\u001a\u00020\u00112\u0006\u0010Q\u001a\u0002012\u0006\u0010k\u001a\u000201H\u0016J\b\u0010l\u001a\u00020.H\u0016J\u0016\u0010m\u001a\u00020.2\f\u00106\u001a\b\u0012\u0004\u0012\u00020n07H\u0016J\b\u0010o\u001a\u00020.H\u0002J\b\u0010p\u001a\u00020.H\u0014J\b\u0010q\u001a\u00020.H\u0002J\b\u0010r\u001a\u000201H\u0014J\b\u0010s\u001a\u000201H\u0014J\"\u0010t\u001a\u00020.2\u0006\u0010u\u001a\u00020\u00112\u0006\u0010v\u001a\u00020\u00112\b\u0010w\u001a\u0004\u0018\u00010xH\u0014J\b\u0010y\u001a\u00020.H\u0014J\b\u0010z\u001a\u00020\u0011H\u0014J\b\u0010{\u001a\u00020\u0002H\u0014R\u000e\u0010\b\u001a\u00020\tX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n\u0012\u0004\u0012\u00020!\u0018\u00010 X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020%0#X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010]\u001a\u000201X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006}"}, d2 = {"Lcom/movieboxpro/android/tv/tv/TvDetailActivity;", "Lcom/movieboxpro/android/base/mvp/BaseMvpActivity;", "Lcom/movieboxpro/android/tv/tv/TvDetailPresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivityTvDetail2Binding;", "Lcom/movieboxpro/android/tv/tv/TvDetailContract$View;", "Lcom/movieboxpro/android/listener/CommonItemClickListener;", "<init>", "()V", "scale", "", "mFocusBorder", "Lcom/owen/focus/FocusBorder;", "poster", "", "id", "lang", DealWatchNextService.EPISODE, "", DealWatchNextService.SEASON, "totalSeason", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "selectLanguage", "chooseLanguageDialog", "Lcom/movieboxpro/android/view/dialog/ChooseLanguageDialogFragment;", "videoInfoDialog", "Lcom/movieboxpro/android/view/dialog/VideoInfoDialogFragment;", "chooseSeasonDialog", "Lcom/movieboxpro/android/view/dialog/ChooseSeasonDialogFragment;", "addToFavoriteDialog", "Lcom/movieboxpro/android/view/dialog/AddToFavoriteDialogFragment;", "playList", "", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "adapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "selectSeason", "selectYear", "selectPos", "handle", "Landroid/os/Handler;", "currEpisode", "currSeason", "initListener", "", "dealPlayClick", "playImmediately", "", "checkChildMode", "contentRating", "getTvPath", "showPlayList", "list", "", "addToPlayListSuccess", "position", "showAddToFavorite", "showChooseSeason", "showVideoInfoDialog", "showChooseLanguage", "onItemClicked", "type", "addFavoriteSuccess", "isFavorite", "initView", "onLogin", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LoginEvent;", "focusScale", "v", "Landroid/view/View;", "setViewFocusListener", "args", "", "([Landroid/view/View;)V", "initData", "goVideoPlayer", "baseMediaModel", "Lcom/movieboxpro/android/model/BaseMediaModel;", "isNext", "playerWithOtherPlayer", "url", "initWatchedStatus", "watched", "startPlay", "shouldRestartWatchedEpisode", "syncEpisodeWatched", "over", "(ILjava/lang/Integer;I)V", "onPlayerFinish", "Lcom/movieboxpro/android/event/OnPlayerFinishEvent;", "isFirst", "showVideoInfo", "refreshList", "showSeasons", "seasons", "showYear", "loadInfoError", "showPlayButtonLoading", "hidePlayButtonLoading", "showScreenManage", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "isContinue", "hideLoadView", "showActors", "Lcom/movieboxpro/android/model/Actor;", "setBlurBackground", "requestData", "noRefreshList", "isNeedLoadData", "enableEventBus", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onDestroy", "getLayoutResId", "bindPresenter", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvDetailActivity extends BaseMvpActivity<TvDetailPresenter, ActivityTvDetail2Binding> implements TvDetailContract.View, CommonItemClickListener {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> adapter;
    private AddToFavoriteDialogFragment addToFavoriteDialog;
    private ChooseLanguageDialogFragment chooseLanguageDialog;
    private ChooseSeasonDialogFragment chooseSeasonDialog;
    private int currEpisode;
    private int currSeason;
    private FocusBorder mFocusBorder;
    private List<MovieListModel.MovieListItem> playList;
    private TvDetail tvDetail;
    private VideoInfoDialogFragment videoInfoDialog;
    private final float scale = 1.05f;
    private String poster = "";
    private String id = "";
    private String lang = "";
    private int episode = 1;
    private int season = 1;
    private int totalSeason = 1;
    private String selectLanguage = "";
    private String selectSeason = "";
    private String selectYear = "";
    private int selectPos = -1;
    private final Handler handle = new Handler();
    private boolean isFirst = true;

    private final void setBlurBackground() {
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean enableEventBus() {
        return true;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected boolean isNeedLoadData() {
        return false;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initListener() {
        ActivityTvDetail2Binding activityTvDetail2Binding = (ActivityTvDetail2Binding) this.binding;
        LinearLayout llTvInfo = activityTvDetail2Binding.llTvInfo;
        Intrinsics.checkNotNullExpressionValue(llTvInfo, "llTvInfo");
        ConstraintLayout llTvName = activityTvDetail2Binding.llTvName;
        Intrinsics.checkNotNullExpressionValue(llTvName, "llTvName");
        ImageView ivAdd = activityTvDetail2Binding.ivAdd;
        Intrinsics.checkNotNullExpressionValue(ivAdd, "ivAdd");
        FrameLayout rlPlay = activityTvDetail2Binding.rlPlay;
        Intrinsics.checkNotNullExpressionValue(rlPlay, "rlPlay");
        TextView tvSeason = activityTvDetail2Binding.tvSeason;
        Intrinsics.checkNotNullExpressionValue(tvSeason, "tvSeason");
        ImageView ivDownload = activityTvDetail2Binding.ivDownload;
        Intrinsics.checkNotNullExpressionValue(ivDownload, "ivDownload");
        ImageView ivWatchPlan = activityTvDetail2Binding.ivWatchPlan;
        Intrinsics.checkNotNullExpressionValue(ivWatchPlan, "ivWatchPlan");
        ImageView ivWatchPlanProgress = activityTvDetail2Binding.ivWatchPlanProgress;
        Intrinsics.checkNotNullExpressionValue(ivWatchPlanProgress, "ivWatchPlanProgress");
        setViewFocusListener(llTvInfo, llTvName, ivAdd, rlPlay, tvSeason, ivDownload, ivWatchPlan, ivWatchPlanProgress);
        ((ActivityTvDetail2Binding) this.binding).recyclerView.setOnItemListener(new AnonymousClass2());
        ((ActivityTvDetail2Binding) this.binding).recyclerView.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda18
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                TvDetailActivity.initListener$lambda$1(this.f$0, view, z);
            }
        });
        ((ActivityTvDetail2Binding) this.binding).rvActors.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity.initListener.4
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
            }

            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
            public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                Intrinsics.checkNotNullParameter(parent, "parent");
                Intrinsics.checkNotNullParameter(itemView, "itemView");
                TvDetailActivity.this.focusScale(itemView, 1.15f);
                TvDetailActivity.this.selectPos = position;
            }
        });
        ((ActivityTvDetail2Binding) this.binding).ivWatchPlanProgress.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda19
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.initListener$lambda$2(this.f$0, view);
            }
        });
        ((ActivityTvDetail2Binding) this.binding).ivWatchPlan.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda20
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.initListener$lambda$3(this.f$0, view);
            }
        });
        ((ActivityTvDetail2Binding) this.binding).ivDownload.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda21
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.initListener$lambda$5(this.f$0, view);
            }
        });
        ((ActivityTvDetail2Binding) this.binding).llTvName.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda22
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.initListener$lambda$6(this.f$0, view);
            }
        });
        ((ActivityTvDetail2Binding) this.binding).llTvInfo.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.initListener$lambda$7(this.f$0, view);
            }
        });
        DoubleClickHelper.click(((ActivityTvDetail2Binding) this.binding).tvSeason, new Runnable() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.showChooseSeason();
            }
        });
        ((ActivityTvDetail2Binding) this.binding).ivAdd.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.initListener$lambda$9(this.f$0, view);
            }
        });
        DoubleClickHelper.click(((ActivityTvDetail2Binding) this.binding).rlPlay, new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.initListener$lambda$13(this.f$0, view);
            }
        });
        final Ref.LongRef longRef = new Ref.LongRef();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ((ActivityTvDetail2Binding) this.binding).rlPlay.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvDetailActivity.initListener$lambda$16(objectRef, longRef, this, view);
            }
        });
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.tv.tv.TvDetailActivity$initListener$2, reason: invalid class name */
    /* JADX INFO: compiled from: TvDetailActivity.kt */
    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"com/movieboxpro/android/tv/tv/TvDetailActivity$initListener$2", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView$OnItemListener;", "onItemPreSelected", "", "parent", "Lcom/owen/tvrecyclerview/widget/TvRecyclerView;", "itemView", "Landroid/view/View;", "position", "", "onItemSelected", "onItemClick", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class AnonymousClass2 implements TvRecyclerView.OnItemListener {
        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(itemView, "itemView");
        }

        AnonymousClass2() {
        }

        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
        public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            TvDetailActivity.this.focusScale(itemView, 1.15f);
            TvDetailActivity.this.selectPos = position;
        }

        /* JADX WARN: Code duplicated, block: B:16:0x0059  */
        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
        public void onItemClick(TvRecyclerView parent, View itemView, int position) {
            boolean z;
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            if (TvDetailActivity.this.isLogin()) {
                BaseQuickAdapter baseQuickAdapter = TvDetailActivity.this.adapter;
                BaseQuickAdapter baseQuickAdapter2 = null;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter = null;
                }
                TvDetail.SeasonDetail seasonDetail = (TvDetail.SeasonDetail) baseQuickAdapter.getItem(position);
                if (seasonDetail.code_file == 1) {
                    BaseQuickAdapter baseQuickAdapter3 = TvDetailActivity.this.adapter;
                    if (baseQuickAdapter3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        baseQuickAdapter2 = baseQuickAdapter3;
                    }
                    TvDetail.SeasonDetail seasonDetail2 = (TvDetail.SeasonDetail) baseQuickAdapter2.getItem(position);
                    TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) TvDetailActivity.this.mPresenter;
                    int i = seasonDetail2.episode;
                    int i2 = seasonDetail2.season;
                    String tid = seasonDetail2.tid;
                    Intrinsics.checkNotNullExpressionValue(tid, "tid");
                    tvDetailPresenter.getVideoPath(i, i2, tid, false, false, false);
                    return;
                }
                long j = 1000;
                if (seasonDetail.released_timestamp > System.currentTimeMillis() / j) {
                    if (TvDetailActivity.this.tvDetail != null) {
                        TvDetail tvDetail = TvDetailActivity.this.tvDetail;
                        Intrinsics.checkNotNull(tvDetail);
                        if (tvDetail.is_collect == 1) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                    ReleasedHintDialog releasedHintDialogNewInstance$default = ReleasedHintDialog.Companion.newInstance$default(ReleasedHintDialog.INSTANCE, z, 1, false, 4, null);
                    final TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                    releasedHintDialogNewInstance$default.setClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$initListener$2$$ExternalSyntheticLambda0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            TvDetailActivity.AnonymousClass2.onItemClick$lambda$0(tvDetailActivity, view);
                        }
                    });
                    releasedHintDialogNewInstance$default.show(TvDetailActivity.this.getSupportFragmentManager(), "ReleasedHintDialog");
                    return;
                }
                if (seasonDetail.released_timestamp == 0) {
                    TvDetail tvDetail2 = TvDetailActivity.this.tvDetail;
                    Intrinsics.checkNotNull(tvDetail2);
                    ReleasedHintDialog releasedHintDialogNewInstance = ReleasedHintDialog.INSTANCE.newInstance(tvDetail2.is_collect == 1, 1, true);
                    final TvDetailActivity tvDetailActivity2 = TvDetailActivity.this;
                    releasedHintDialogNewInstance.setClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$initListener$2$$ExternalSyntheticLambda1
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            TvDetailActivity.AnonymousClass2.onItemClick$lambda$1(tvDetailActivity2, view);
                        }
                    });
                    releasedHintDialogNewInstance.show(TvDetailActivity.this.getSupportFragmentManager(), "ReleasedHintDialog");
                    return;
                }
                TvDetail tvDetail3 = TvDetailActivity.this.tvDetail;
                Intrinsics.checkNotNull(tvDetail3);
                if (tvDetail3.released_timestamp > 0) {
                    TvDetail tvDetail4 = TvDetailActivity.this.tvDetail;
                    Intrinsics.checkNotNull(tvDetail4);
                    if (tvDetail4.released_timestamp < System.currentTimeMillis() / j) {
                        ToastUtils.showShort("Not Available", new Object[0]);
                        return;
                    }
                }
                ToastUtils.showShort("Not Available", new Object[0]);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onItemClick$lambda$0(TvDetailActivity tvDetailActivity, View view) {
            TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) tvDetailActivity.mPresenter;
            TvDetail tvDetail = tvDetailActivity.tvDetail;
            Intrinsics.checkNotNull(tvDetail);
            String id = tvDetail.id;
            Intrinsics.checkNotNullExpressionValue(id, "id");
            String uid_v2 = App.getUserData().uid_v2;
            Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
            tvDetailPresenter.addToFavorite(id, uid_v2, false);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onItemClick$lambda$1(TvDetailActivity tvDetailActivity, View view) {
            TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) tvDetailActivity.mPresenter;
            TvDetail tvDetail = tvDetailActivity.tvDetail;
            Intrinsics.checkNotNull(tvDetail);
            String id = tvDetail.id;
            Intrinsics.checkNotNullExpressionValue(id, "id");
            String uid_v2 = App.getUserData().uid_v2;
            Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
            tvDetailPresenter.addToFavorite(id, uid_v2, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(TvDetailActivity tvDetailActivity, View view, boolean z) {
        if (z) {
            return;
        }
        FocusBorder focusBorder = tvDetailActivity.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$2(final TvDetailActivity tvDetailActivity, View view) {
        String str;
        if (tvDetailActivity.tvDetail == null) {
            return;
        }
        WatchPlanProgressDialog.Companion companion = WatchPlanProgressDialog.INSTANCE;
        TvDetail tvDetail = tvDetailActivity.tvDetail;
        if (tvDetail == null || (str = tvDetail.id) == null) {
            str = "";
        }
        String str2 = str;
        TvDetail tvDetail2 = tvDetailActivity.tvDetail;
        String str3 = tvDetail2 != null ? tvDetail2.title : null;
        TvDetail tvDetail3 = tvDetailActivity.tvDetail;
        String str4 = tvDetail3 != null ? tvDetail3.poster : null;
        int i = tvDetailActivity.season;
        TvDetail tvDetail4 = tvDetailActivity.tvDetail;
        WatchPlanProgressDialog watchPlanProgressDialogNewInstance = companion.newInstance(str2, str3, str4, i, tvDetail4 != null ? tvDetail4.plan_watched : -1);
        watchPlanProgressDialogNewInstance.setListener(new AddWatchPlanListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$initListener$5$1
            @Override // com.movieboxpro.android.listener.AddWatchPlanListener
            public void onWatchedChange(int watched) {
                TvDetail tvDetail5 = this.this$0.tvDetail;
                if (tvDetail5 != null) {
                    tvDetail5.plan_watched = watched;
                }
                this.this$0.initWatchedStatus(watched);
            }

            @Override // com.movieboxpro.android.listener.AddWatchPlanListener
            public void onEpisodeOverChanged(int season, Integer episode, int over) {
                this.this$0.syncEpisodeWatched(season, episode, over);
            }
        });
        watchPlanProgressDialogNewInstance.show(tvDetailActivity.getSupportFragmentManager(), "WatchPlanProgressDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$3(final TvDetailActivity tvDetailActivity, View view) {
        String str;
        if (tvDetailActivity.tvDetail == null) {
            return;
        }
        AddWatchPlanDialog.Companion companion = AddWatchPlanDialog.INSTANCE;
        TvDetail tvDetail = tvDetailActivity.tvDetail;
        int i = tvDetail != null ? tvDetail.plan_watched : -1;
        TvDetail tvDetail2 = tvDetailActivity.tvDetail;
        if (tvDetail2 == null || (str = tvDetail2.id) == null) {
            str = "";
        }
        AddWatchPlanDialog addWatchPlanDialogNewInstance = companion.newInstance(2, i, str);
        addWatchPlanDialogNewInstance.setListener(new AddWatchPlanListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$initListener$6$1
            @Override // com.movieboxpro.android.listener.AddWatchPlanListener
            public void onEpisodeOverChanged(int i2, Integer num, int i3) {
                AddWatchPlanListener.CC.$default$onEpisodeOverChanged(this, i2, num, i3);
            }

            @Override // com.movieboxpro.android.listener.AddWatchPlanListener
            public void onWatchedChange(int watched) {
                TvDetail tvDetail3 = this.this$0.tvDetail;
                if (tvDetail3 != null) {
                    tvDetail3.plan_watched = watched;
                }
                this.this$0.initWatchedStatus(watched);
            }
        });
        addWatchPlanDialogNewInstance.show(tvDetailActivity.getSupportFragmentManager(), "AddWatchPlanDialog");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$5(TvDetailActivity tvDetailActivity, View view) {
        String[] strArr;
        TvDetail tvDetail = tvDetailActivity.tvDetail;
        if (tvDetail == null || tvDetail == null) {
            return;
        }
        ChooseTvDownloadActivity.Companion companion = ChooseTvDownloadActivity.INSTANCE;
        TvDetailActivity tvDetailActivity2 = tvDetailActivity;
        String str = tvDetailActivity.id;
        int i = tvDetailActivity.season;
        int i2 = tvDetailActivity.totalSeason;
        TvDetail tvDetail2 = tvDetailActivity.tvDetail;
        if (tvDetail2 == null || (strArr = tvDetail2.seasonList) == null) {
            strArr = new String[0];
        }
        TvDetail tvDetail3 = tvDetailActivity.tvDetail;
        Intrinsics.checkNotNull(tvDetail3);
        companion.start(tvDetailActivity2, str, i, i2, strArr, tvDetail3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$6(TvDetailActivity tvDetailActivity, View view) {
        if (tvDetailActivity.tvDetail == null) {
            return;
        }
        tvDetailActivity.showChooseLanguage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$7(TvDetailActivity tvDetailActivity, View view) {
        if (tvDetailActivity.tvDetail == null) {
            return;
        }
        tvDetailActivity.showVideoInfoDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$9(TvDetailActivity tvDetailActivity, View view) {
        if (tvDetailActivity.tvDetail != null && tvDetailActivity.isLogin()) {
            tvDetailActivity.showAddToFavorite();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:18:0x003a  */
    public static final void initListener$lambda$13(final TvDetailActivity tvDetailActivity, View view) {
        String str;
        boolean z;
        if (tvDetailActivity.tvDetail != null && tvDetailActivity.isLogin()) {
            TvDetail tvDetail = tvDetailActivity.tvDetail;
            if (tvDetail != null) {
                Intrinsics.checkNotNull(tvDetail);
                if (tvDetail.code_file != 1) {
                    TvDetail tvDetail2 = tvDetailActivity.tvDetail;
                    Intrinsics.checkNotNull(tvDetail2);
                    long j = 1000;
                    if (tvDetail2.released_timestamp > System.currentTimeMillis() / j) {
                        TvDetail tvDetail3 = tvDetailActivity.tvDetail;
                        if (tvDetail3 != null) {
                            Intrinsics.checkNotNull(tvDetail3);
                            if (tvDetail3.is_collect == 1) {
                                z = true;
                            } else {
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                        ReleasedHintDialog releasedHintDialogNewInstance$default = ReleasedHintDialog.Companion.newInstance$default(ReleasedHintDialog.INSTANCE, z, 1, false, 4, null);
                        releasedHintDialogNewInstance$default.setClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                TvDetailActivity.initListener$lambda$13$lambda$10(this.f$0, view2);
                            }
                        });
                        releasedHintDialogNewInstance$default.show(tvDetailActivity.getSupportFragmentManager(), "ReleasedHintDialog");
                        return;
                    }
                    TvDetail tvDetail4 = tvDetailActivity.tvDetail;
                    Intrinsics.checkNotNull(tvDetail4);
                    if (tvDetail4.released_timestamp == 0) {
                        TvDetail tvDetail5 = tvDetailActivity.tvDetail;
                        Intrinsics.checkNotNull(tvDetail5);
                        ReleasedHintDialog releasedHintDialogNewInstance = ReleasedHintDialog.INSTANCE.newInstance(tvDetail5.is_collect == 1, 1, true);
                        releasedHintDialogNewInstance.setClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda11
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view2) {
                                TvDetailActivity.initListener$lambda$13$lambda$11(this.f$0, view2);
                            }
                        });
                        releasedHintDialogNewInstance.show(tvDetailActivity.getSupportFragmentManager(), "ReleasedHintDialog");
                        return;
                    }
                    TvDetail tvDetail6 = tvDetailActivity.tvDetail;
                    Intrinsics.checkNotNull(tvDetail6);
                    if (tvDetail6.released_timestamp > 0) {
                        TvDetail tvDetail7 = tvDetailActivity.tvDetail;
                        Intrinsics.checkNotNull(tvDetail7);
                        if (tvDetail7.released_timestamp < System.currentTimeMillis() / j) {
                            ToastUtils.showShort("Not Available", new Object[0]);
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            TvDetail tvDetail8 = tvDetailActivity.tvDetail;
            if (tvDetail8 == null || (str = tvDetail8.content_rating) == null) {
                str = "";
            }
            if (tvDetailActivity.checkChildMode(str)) {
                return;
            }
            TvDetail tvDetail9 = tvDetailActivity.tvDetail;
            if (StringsKt.equals("tc", tvDetail9 != null ? tvDetail9.quality_tag_new : null, true)) {
                final LowQualityHintDialog lowQualityHintDialog = new LowQualityHintDialog();
                lowQualityHintDialog.setContinueClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda15
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view2) {
                        TvDetailActivity.initListener$lambda$13$lambda$12(this.f$0, lowQualityHintDialog, view2);
                    }
                });
                lowQualityHintDialog.show(tvDetailActivity.getSupportFragmentManager(), "LowQualityHintDialog");
                return;
            }
            tvDetailActivity.getTvPath(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$13$lambda$10(TvDetailActivity tvDetailActivity, View view) {
        TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) tvDetailActivity.mPresenter;
        TvDetail tvDetail = tvDetailActivity.tvDetail;
        Intrinsics.checkNotNull(tvDetail);
        String id = tvDetail.id;
        Intrinsics.checkNotNullExpressionValue(id, "id");
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        tvDetailPresenter.addToFavorite(id, uid_v2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$13$lambda$11(TvDetailActivity tvDetailActivity, View view) {
        TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) tvDetailActivity.mPresenter;
        TvDetail tvDetail = tvDetailActivity.tvDetail;
        Intrinsics.checkNotNull(tvDetail);
        String id = tvDetail.id;
        Intrinsics.checkNotNullExpressionValue(id, "id");
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        tvDetailPresenter.addToFavorite(id, uid_v2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$13$lambda$12(TvDetailActivity tvDetailActivity, LowQualityHintDialog lowQualityHintDialog, View view) {
        tvDetailActivity.getTvPath(true);
        lowQualityHintDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$16(final Ref.ObjectRef objectRef, Ref.LongRef longRef, final TvDetailActivity tvDetailActivity, View view) {
        Disposable disposable = (Disposable) objectRef.element;
        if (disposable != null) {
            disposable.dispose();
        }
        if (System.currentTimeMillis() - longRef.element < 500) {
            tvDetailActivity.dealPlayClick(true);
        } else {
            Object objAs = Observable.timer(600L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(tvDetailActivity));
            Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.initListener$lambda$16$lambda$14(objectRef, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.initListener$lambda$16$lambda$15(this.f$0, (Long) obj);
                }
            }, 11, null);
        }
        longRef.element = System.currentTimeMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$16$lambda$15(TvDetailActivity tvDetailActivity, Long l) {
        tvDetailActivity.dealPlayClick(false);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit initListener$lambda$16$lambda$14(Ref.ObjectRef objectRef, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        objectRef.element = it;
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0035  */
    private final void dealPlayClick(final boolean playImmediately) {
        String str;
        boolean z;
        if (isLogin()) {
            TvDetail tvDetail = this.tvDetail;
            if (tvDetail != null) {
                Intrinsics.checkNotNull(tvDetail);
                if (tvDetail.code_file != 1) {
                    TvDetail tvDetail2 = this.tvDetail;
                    Intrinsics.checkNotNull(tvDetail2);
                    long j = 1000;
                    if (tvDetail2.released_timestamp > System.currentTimeMillis() / j) {
                        TvDetail tvDetail3 = this.tvDetail;
                        if (tvDetail3 != null) {
                            Intrinsics.checkNotNull(tvDetail3);
                            if (tvDetail3.is_collect == 1) {
                                z = true;
                            } else {
                                z = false;
                            }
                        } else {
                            z = false;
                        }
                        ReleasedHintDialog releasedHintDialogNewInstance$default = ReleasedHintDialog.Companion.newInstance$default(ReleasedHintDialog.INSTANCE, z, 1, false, 4, null);
                        releasedHintDialogNewInstance$default.setClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda6
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                TvDetailActivity.dealPlayClick$lambda$17(this.f$0, view);
                            }
                        });
                        releasedHintDialogNewInstance$default.show(getSupportFragmentManager(), "ReleasedHintDialog");
                        return;
                    }
                    TvDetail tvDetail4 = this.tvDetail;
                    Intrinsics.checkNotNull(tvDetail4);
                    if (tvDetail4.released_timestamp == 0) {
                        TvDetail tvDetail5 = this.tvDetail;
                        Intrinsics.checkNotNull(tvDetail5);
                        ReleasedHintDialog releasedHintDialogNewInstance = ReleasedHintDialog.INSTANCE.newInstance(tvDetail5.is_collect == 1, 1, true);
                        releasedHintDialogNewInstance.setClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda7
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view) {
                                TvDetailActivity.dealPlayClick$lambda$18(this.f$0, view);
                            }
                        });
                        releasedHintDialogNewInstance.show(getSupportFragmentManager(), "ReleasedHintDialog");
                        return;
                    }
                    TvDetail tvDetail6 = this.tvDetail;
                    Intrinsics.checkNotNull(tvDetail6);
                    if (tvDetail6.released_timestamp > 0) {
                        TvDetail tvDetail7 = this.tvDetail;
                        Intrinsics.checkNotNull(tvDetail7);
                        if (tvDetail7.released_timestamp < System.currentTimeMillis() / j) {
                            ToastUtils.showShort("Not Available", new Object[0]);
                            return;
                        }
                        return;
                    }
                    return;
                }
            }
            TvDetail tvDetail8 = this.tvDetail;
            if (tvDetail8 == null || (str = tvDetail8.content_rating) == null) {
                str = "";
            }
            if (checkChildMode(str)) {
                return;
            }
            TvDetail tvDetail9 = this.tvDetail;
            if (StringsKt.equals("tc", tvDetail9 != null ? tvDetail9.quality_tag_new : null, true)) {
                final LowQualityHintDialog lowQualityHintDialog = new LowQualityHintDialog();
                lowQualityHintDialog.setContinueClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda8
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TvDetailActivity.dealPlayClick$lambda$19(this.f$0, playImmediately, lowQualityHintDialog, view);
                    }
                });
                lowQualityHintDialog.show(getSupportFragmentManager(), "LowQualityHintDialog");
                return;
            }
            getTvPath(playImmediately);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dealPlayClick$lambda$17(TvDetailActivity tvDetailActivity, View view) {
        TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) tvDetailActivity.mPresenter;
        TvDetail tvDetail = tvDetailActivity.tvDetail;
        Intrinsics.checkNotNull(tvDetail);
        String id = tvDetail.id;
        Intrinsics.checkNotNullExpressionValue(id, "id");
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        tvDetailPresenter.addToFavorite(id, uid_v2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dealPlayClick$lambda$18(TvDetailActivity tvDetailActivity, View view) {
        TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) tvDetailActivity.mPresenter;
        TvDetail tvDetail = tvDetailActivity.tvDetail;
        Intrinsics.checkNotNull(tvDetail);
        String id = tvDetail.id;
        Intrinsics.checkNotNullExpressionValue(id, "id");
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        tvDetailPresenter.addToFavorite(id, uid_v2, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void dealPlayClick$lambda$19(TvDetailActivity tvDetailActivity, boolean z, LowQualityHintDialog lowQualityHintDialog, View view) {
        tvDetailActivity.getTvPath(z);
        lowQualityHintDialog.dismiss();
    }

    private final boolean checkChildMode(String contentRating) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE) || !CommonUtils.isChildModeVideo(contentRating)) {
            return false;
        }
        ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(this);
        childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda9
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                TvDetailActivity.checkChildMode$lambda$20(this.f$0);
            }
        });
        childModeHintDialog.show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkChildMode$lambda$20(TvDetailActivity tvDetailActivity) {
        new OpenChildModeDialog().show(tvDetailActivity.getSupportFragmentManager(), "OpenChildModeDialog");
    }

    private final void getTvPath(boolean playImmediately) {
        String str;
        String str2;
        String str3;
        List<TvDetail.SeasonDetail> list;
        String str4;
        TvDetail tvDetail = this.tvDetail;
        if (tvDetail != null && (list = tvDetail.episodeList) != null && list.size() == this.episode) {
            TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) this.mPresenter;
            int i = this.episode;
            int i2 = this.season;
            TvDetail tvDetail2 = this.tvDetail;
            tvDetailPresenter.getVideoPath(i, i2, (tvDetail2 == null || (str4 = tvDetail2.id) == null) ? "" : str4, playImmediately, false, true);
            return;
        }
        TvDetail tvDetail3 = this.tvDetail;
        if (!TextUtils.isEmpty(tvDetail3 != null ? tvDetail3.history : null)) {
            TvDetail tvDetail4 = this.tvDetail;
            if (!TextUtils.equals(tvDetail4 != null ? tvDetail4.history : null, _UrlKt.PATH_SEGMENT_ENCODE_SET_URI)) {
                TvDetail tvDetail5 = this.tvDetail;
                Object obj = JSONObject.parse(tvDetail5 != null ? tvDetail5.history : null);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
                JSONObject jSONObject = (JSONObject) obj;
                Integer integer = jSONObject.getInteger(DealWatchNextService.EPISODE);
                Intrinsics.checkNotNull(integer);
                int iIntValue = integer.intValue();
                Integer integer2 = jSONObject.getInteger(DealWatchNextService.SEASON);
                Intrinsics.checkNotNull(integer2);
                int iIntValue2 = integer2.intValue();
                PlayRecodeDao playRecodeDao = App.getDB().playRecodeDao();
                TvDetail tvDetail6 = this.tvDetail;
                PlayRecode playRecodeFindByEpisode = playRecodeDao.findByEpisode(2, tvDetail6 != null ? tvDetail6.id : null, iIntValue2, iIntValue);
                if (playRecodeFindByEpisode != null) {
                    Integer integer3 = jSONObject.getInteger("seconds");
                    Intrinsics.checkNotNull(integer3);
                    playRecodeFindByEpisode.setStart_time(integer3.intValue() * 1000);
                    App.getDB().playRecodeDao().update(playRecodeFindByEpisode);
                }
                Integer integer4 = jSONObject.getInteger("over");
                if (integer4 != null && integer4.intValue() == 1) {
                    TvDetailPresenter tvDetailPresenter2 = (TvDetailPresenter) this.mPresenter;
                    int i3 = this.episode + 1;
                    int i4 = this.season;
                    TvDetail tvDetail7 = this.tvDetail;
                    tvDetailPresenter2.getVideoPath(i3, i4, (tvDetail7 == null || (str3 = tvDetail7.id) == null) ? "" : str3, playImmediately, true, true);
                    return;
                }
                TvDetailPresenter tvDetailPresenter3 = (TvDetailPresenter) this.mPresenter;
                int i5 = this.episode;
                int i6 = this.season;
                TvDetail tvDetail8 = this.tvDetail;
                tvDetailPresenter3.getVideoPath(i5, i6, (tvDetail8 == null || (str2 = tvDetail8.id) == null) ? "" : str2, playImmediately, false, true);
                return;
            }
        }
        TvDetailPresenter tvDetailPresenter4 = (TvDetailPresenter) this.mPresenter;
        int i7 = this.episode;
        int i8 = this.season;
        TvDetail tvDetail9 = this.tvDetail;
        tvDetailPresenter4.getVideoPath(i7, i8, (tvDetail9 == null || (str = tvDetail9.id) == null) ? "" : str, playImmediately, false, true);
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void showPlayList(List<? extends MovieListModel.MovieListItem> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        AddToDialog.Companion companion = AddToDialog.INSTANCE;
        TvDetail tvDetail = this.tvDetail;
        int i = tvDetail != null ? tvDetail.plan_watched : -1;
        TvDetail tvDetail2 = this.tvDetail;
        int i2 = tvDetail2 != null ? tvDetail2.is_collect : 0;
        TvDetail tvDetail3 = this.tvDetail;
        AddToDialog addToDialogNewInstance = companion.newInstance(2, i, i2, tvDetail3 != null ? tvDetail3.id : null);
        addToDialogNewInstance.setMovieList(list);
        addToDialogNewInstance.setListener(new AddToDialog.AddToListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity.showPlayList.1
            @Override // com.movieboxpro.android.view.dialog.AddToDialog.AddToListener
            public void download() {
            }

            @Override // com.movieboxpro.android.view.dialog.AddToDialog.AddToListener
            public void onWatchedChange(int watched) {
                TvDetail tvDetail4 = TvDetailActivity.this.tvDetail;
                if (tvDetail4 != null) {
                    tvDetail4.plan_watched = watched;
                }
            }

            @Override // com.movieboxpro.android.view.dialog.AddToDialog.AddToListener
            public void onFavorite(int favorite) {
                TvDetail tvDetail4 = TvDetailActivity.this.tvDetail;
                if (tvDetail4 != null) {
                    tvDetail4.is_collect = favorite;
                }
            }
        });
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
        addToDialogNewInstance.showAllowingStateLoss(supportFragmentManager, "AddToDialog");
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void addToPlayListSuccess(int position) {
        AddToFavoriteDialogFragment addToFavoriteDialogFragment = this.addToFavoriteDialog;
        if (addToFavoriteDialogFragment != null) {
            addToFavoriteDialogFragment.addPlayListNum(position);
        }
        EventBus.getDefault().post(new RefreshMovieListEvent());
        ToastUtils.showShort("Add successfully", new Object[0]);
    }

    private final void showAddToFavorite() {
        TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) this.mPresenter;
        String uid_v2 = App.getUserData().uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        tvDetailPresenter.requestPlayList(uid_v2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showChooseSeason() {
        if (this.chooseSeasonDialog == null) {
            ChooseSeasonDialogFragment.Companion companion = ChooseSeasonDialogFragment.INSTANCE;
            TvDetail tvDetail = this.tvDetail;
            String[] strArr = tvDetail != null ? tvDetail.seasonList : null;
            TvDetail tvDetail2 = this.tvDetail;
            this.chooseSeasonDialog = companion.newInstance(strArr, tvDetail2 != null ? tvDetail2.years : null);
        }
        ChooseSeasonDialogFragment chooseSeasonDialogFragment = this.chooseSeasonDialog;
        Intrinsics.checkNotNull(chooseSeasonDialogFragment);
        chooseSeasonDialogFragment.setSeasonYear(this.selectSeason, this.selectYear);
        ChooseSeasonDialogFragment chooseSeasonDialogFragment2 = this.chooseSeasonDialog;
        if (chooseSeasonDialogFragment2 != null) {
            FragmentManager supportFragmentManager = getSupportFragmentManager();
            Intrinsics.checkNotNullExpressionValue(supportFragmentManager, "getSupportFragmentManager(...)");
            chooseSeasonDialogFragment2.showAllowingStateLoss(supportFragmentManager, "ChooseSeasonDialogFragment");
        }
    }

    private final void showVideoInfoDialog() {
        VideoInfoDialogFragment.INSTANCE.newInstance(this.tvDetail, ((ActivityTvDetail2Binding) this.binding).tvDesc.getText().toString()).show(getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(VideoInfoDialogFragment.class).getSimpleName());
    }

    private final void showChooseLanguage() {
        ChooseLanguageDialogFragment chooseLanguageDialogFragment = new ChooseLanguageDialogFragment();
        TvDetail tvDetail = this.tvDetail;
        if (tvDetail != null) {
            BaseMediaModel.MoreLanguage moreLanguage = new BaseMediaModel.MoreLanguage();
            moreLanguage.lang = "";
            moreLanguage.title = "Translate";
            tvDetail.language.add(moreLanguage);
            List<BaseMediaModel.MoreLanguage> language = tvDetail.language;
            Intrinsics.checkNotNullExpressionValue(language, "language");
            chooseLanguageDialogFragment.setData(language);
        }
        chooseLanguageDialogFragment.show(getSupportFragmentManager(), "ChooseLanguageDialogFragment");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.movieboxpro.android.listener.CommonItemClickListener
    public void onItemClicked(int position, String type) {
        String str;
        String str2;
        List<BaseMediaModel.MoreLanguage> list;
        BaseMediaModel.MoreLanguage moreLanguage;
        List<BaseMediaModel.MoreLanguage> list2;
        BaseMediaModel.MoreLanguage moreLanguage2;
        String str3;
        List<BaseMediaModel.MoreLanguage> list3;
        BaseMediaModel.MoreLanguage moreLanguage3;
        String[] strArr;
        String str4;
        String[] strArr2;
        String str5;
        String[] strArr3;
        String str6;
        String str7;
        String str8;
        Intrinsics.checkNotNullParameter(type, "type");
        boolean z = false;
        int i = 1;
        String str9 = "";
        switch (type.hashCode()) {
            case -954125649:
                if (type.equals(ChooseLanguageDialogFragment.TYPE)) {
                    TvDetail tvDetail = this.tvDetail;
                    if (tvDetail == null || (list3 = tvDetail.language) == null || (moreLanguage3 = list3.get(position)) == null || (str = moreLanguage3.lang) == null) {
                        str = "";
                    }
                    if (str.length() != 0) {
                        TvDetail tvDetail2 = this.tvDetail;
                        if (tvDetail2 != null && (list2 = tvDetail2.language) != null && (moreLanguage2 = list2.get(position)) != null && (str3 = moreLanguage2.lang) != null) {
                            str9 = str3;
                        }
                        this.selectLanguage = str9;
                        TvDetailPresenter tvDetailPresenter = (TvDetailPresenter) this.mPresenter;
                        String str10 = this.id;
                        TvDetail tvDetail3 = this.tvDetail;
                        if (tvDetail3 == null || (list = tvDetail3.language) == null || (moreLanguage = list.get(position)) == null || (str2 = moreLanguage.lang) == null) {
                            str2 = "default";
                        }
                        tvDetailPresenter.requestDetailInfo(str10, str2, true);
                    } else {
                        ChooseTranslateLanguageDialog chooseTranslateLanguageDialog = new ChooseTranslateLanguageDialog();
                        chooseTranslateLanguageDialog.setListener(new C01421());
                        chooseTranslateLanguageDialog.show(getSupportFragmentManager(), Reflection.getOrCreateKotlinClass(ChooseTranslateLanguageDialog.class).getSimpleName());
                    }
                }
                break;
            case -767101958:
                if (type.equals(ChooseSeasonDialogFragment.SEASON)) {
                    TvDetail tvDetail4 = this.tvDetail;
                    if (tvDetail4 != null && (strArr = tvDetail4.seasonList) != null && (str4 = strArr[position]) != null) {
                        i = Integer.parseInt(str4);
                    }
                    this.season = i;
                    this.selectSeason = String.valueOf(i);
                    this.selectYear = "";
                    TvDetailPresenter tvDetailPresenter2 = (TvDetailPresenter) this.mPresenter;
                    String str11 = this.id;
                    int i2 = this.season;
                    String uid_v2 = App.getUserData().uid_v2;
                    Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
                    tvDetailPresenter2.requestSelectSeasons(str11, i2, 0, uid_v2);
                    break;
                }
                break;
            case -277714156:
                if (type.equals(ChooseSeasonDialogFragment.YEAR)) {
                    this.selectSeason = "";
                    TvDetail tvDetail5 = this.tvDetail;
                    if (tvDetail5 != null && (strArr3 = tvDetail5.years) != null && (str6 = strArr3[position]) != null) {
                        str9 = str6;
                    }
                    this.selectYear = str9;
                    TvDetailPresenter tvDetailPresenter3 = (TvDetailPresenter) this.mPresenter;
                    String str12 = this.id;
                    TvDetail tvDetail6 = this.tvDetail;
                    int i3 = (tvDetail6 == null || (strArr2 = tvDetail6.years) == null || (str5 = strArr2[position]) == null) ? -1 : Integer.parseInt(str5);
                    String uid_v3 = App.getUserData().uid_v2;
                    Intrinsics.checkNotNullExpressionValue(uid_v3, "uid_v2");
                    tvDetailPresenter3.requestSelectYear(str12, i3, uid_v3);
                    break;
                }
                break;
            case 1341644400:
                if (type.equals(AddToFavoriteDialogFragment.TYPE)) {
                    if (position != 0) {
                        List<MovieListModel.MovieListItem> list4 = this.playList;
                        MovieListModel.MovieListItem movieListItem = list4 != null ? list4.get(position) : null;
                        if (movieListItem != null) {
                            TvDetailPresenter tvDetailPresenter4 = (TvDetailPresenter) this.mPresenter;
                            String lid = movieListItem.getLid();
                            Intrinsics.checkNotNullExpressionValue(lid, "getLid(...)");
                            TvDetail tvDetail7 = this.tvDetail;
                            String str13 = (tvDetail7 == null || (str7 = tvDetail7.id) == null) ? "" : str7;
                            String uid_v4 = App.getUserData().uid_v2;
                            Intrinsics.checkNotNullExpressionValue(uid_v4, "uid_v2");
                            TvDetail tvDetail8 = this.tvDetail;
                            tvDetailPresenter4.addToPlayList(lid, str13, uid_v4, tvDetail8 != null ? tvDetail8.getBoxType() : 0, position);
                        }
                    } else {
                        TvDetailPresenter tvDetailPresenter5 = (TvDetailPresenter) this.mPresenter;
                        TvDetail tvDetail9 = this.tvDetail;
                        if (tvDetail9 == null || (str8 = tvDetail9.id) == null) {
                            str8 = "";
                        }
                        String str14 = App.getUserData().uid_v2;
                        str9 = str14 != null ? str14 : "";
                        TvDetail tvDetail10 = this.tvDetail;
                        if (tvDetail10 != null && tvDetail10.is_collect == 1) {
                            z = true;
                        }
                        tvDetailPresenter5.addToFavorite(str8, str9, z);
                    }
                    break;
                }
                break;
        }
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: TvDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/movieboxpro/android/tv/tv/TvDetailActivity$onItemClicked$1", "Lcom/movieboxpro/android/view/dialog/ChooseTranslateLanguageDialog$OnLanguageListener;", "onLanguageSelected", "", "lang", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class C01421 implements ChooseTranslateLanguageDialog.OnLanguageListener {
        /* JADX INFO: Access modifiers changed from: private */
        public static final String onLanguageSelected$lambda$0(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onLanguageSelected$lambda$5(String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return it;
        }

        C01421() {
        }

        @Override // com.movieboxpro.android.view.dialog.ChooseTranslateLanguageDialog.OnLanguageListener
        public void onLanguageSelected(String lang) {
            Intrinsics.checkNotNullParameter(lang, "lang");
            ApiService3 apiService = HttpHelperKt.getApiService();
            TvDetail tvDetail = TvDetailActivity.this.tvDetail;
            Intrinsics.checkNotNull(tvDetail);
            Observable<String> observableTranslate = apiService.translate("https://srt.shegu.net/api/srttrans/text/", "auto", lang, tvDetail.description);
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$0((String) obj);
                }
            };
            Observable<R> map = observableTranslate.map(new Function() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$1(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            ObservableSubscribeProxy observableSubscribeProxyTransform = RxSubscribersKt.transform(map, TvDetailActivity.this);
            final TvDetailActivity tvDetailActivity = TvDetailActivity.this;
            Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$2(tvDetailActivity, (ApiException) obj);
                }
            };
            final TvDetailActivity tvDetailActivity2 = TvDetailActivity.this;
            Function1 function3 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$3(tvDetailActivity2, (Disposable) obj);
                }
            };
            final TvDetailActivity tvDetailActivity3 = TvDetailActivity.this;
            RxSubscribersKt.subscribeTo$default(observableSubscribeProxyTransform, function2, null, function3, null, new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$4(tvDetailActivity3, (String) obj);
                }
            }, 10, null);
            ApiService3 apiService2 = HttpHelperKt.getApiService();
            TvDetail tvDetail2 = TvDetailActivity.this.tvDetail;
            Intrinsics.checkNotNull(tvDetail2);
            Observable<String> observableTranslate2 = apiService2.translate("https://srt.shegu.net/api/srttrans/text/", "auto", lang, tvDetail2.title);
            final Function1 function4 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$5((String) obj);
                }
            };
            Observable<R> map2 = observableTranslate2.map(new Function() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda6
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$6(function4, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map2, "map(...)");
            ObservableSubscribeProxy observableSubscribeProxyTransform2 = RxSubscribersKt.transform(map2, TvDetailActivity.this);
            final TvDetailActivity tvDetailActivity4 = TvDetailActivity.this;
            Function1 function5 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$7(tvDetailActivity4, (ApiException) obj);
                }
            };
            final TvDetailActivity tvDetailActivity5 = TvDetailActivity.this;
            Function1 function6 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$8(tvDetailActivity5, (Disposable) obj);
                }
            };
            final TvDetailActivity tvDetailActivity6 = TvDetailActivity.this;
            RxSubscribersKt.subscribeTo$default(observableSubscribeProxyTransform2, function5, null, function6, null, new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$onItemClicked$1$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvDetailActivity.C01421.onLanguageSelected$lambda$9(tvDetailActivity6, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onLanguageSelected$lambda$1(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (String) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$3(TvDetailActivity tvDetailActivity, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            tvDetailActivity.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$4(TvDetailActivity tvDetailActivity, String str) {
            tvDetailActivity.hideLoadingView();
            ((ActivityTvDetail2Binding) tvDetailActivity.binding).tvDesc.setText(str);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$2(TvDetailActivity tvDetailActivity, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            tvDetailActivity.hideLoadingView();
            ToastUtils.showShort("Translate failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String onLanguageSelected$lambda$6(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (String) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$8(TvDetailActivity tvDetailActivity, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            tvDetailActivity.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$9(TvDetailActivity tvDetailActivity, String str) {
            tvDetailActivity.hideLoadingView();
            ((ActivityTvDetail2Binding) tvDetailActivity.binding).tvName.setText(str);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onLanguageSelected$lambda$7(TvDetailActivity tvDetailActivity, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            tvDetailActivity.hideLoadingView();
            ToastUtils.showShort("Translate failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void addFavoriteSuccess(boolean isFavorite) {
        if (isFavorite) {
            ToastUtils.showShort("Add successfully", new Object[0]);
            TvDetail tvDetail = this.tvDetail;
            if (tvDetail != null) {
                tvDetail.is_collect = 1;
            }
        } else {
            TvDetail tvDetail2 = this.tvDetail;
            if (tvDetail2 != null) {
                tvDetail2.is_collect = 0;
            }
        }
        EventBus.getDefault().post(new RefreshFavoriteEvent());
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initView() {
        FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderColorRes(R.color.transparent).borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().noBreathing().build(this);
        Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
        this.mFocusBorder = focusBorderBuild;
        this.adapter = new BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder>(R.layout.adapter_tv_season_item, new ArrayList()) { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity.initView.1
            {
                ArrayList<TvDetail.SeasonDetail> arrayList = arrayList;
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, TvDetail.SeasonDetail item) {
                Integer num;
                Integer num2;
                Integer num3;
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                if (item.released_timestamp > System.currentTimeMillis() / ((long) 1000)) {
                    GlideUtils.load((Activity) TvDetailActivity.this, R.mipmap.ic_unreleased, (ImageView) helper.getView(R.id.imageView));
                } else {
                    GlideUtils.loadCornerLandHolder(TvDetailActivity.this, item.thumbs, (ImageView) helper.getView(R.id.imageView), 8);
                }
                int i = R.id.tvName;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format("%s. %s", Arrays.copyOf(new Object[]{Integer.valueOf(item.episode), item.title}, 2));
                Intrinsics.checkNotNullExpressionValue(str, "format(...)");
                helper.setText(i, str);
                helper.setText(R.id.tvInfo, item.released + " " + item.runtime + "min");
                int color = ContextCompat.getColor(TvDetailActivity.this, R.color.alpha_gray);
                int color2 = ContextCompat.getColor(TvDetailActivity.this, R.color.white);
                TextView textView = (TextView) helper.getView(R.id.tvName);
                TextView textView2 = (TextView) helper.getView(R.id.tvInfo);
                TextView textView3 = (TextView) helper.getView(R.id.tvPlayRecord);
                LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.llPlayRecord);
                HashMap<String, Integer> map = item.play_progress;
                ConstraintLayout constraintLayout = (ConstraintLayout) helper.getView(R.id.constraintLayout);
                ImageView imageView = (ImageView) helper.getView(R.id.ivLastPlay);
                if (item.episode == TvDetailActivity.this.currEpisode && item.season == TvDetailActivity.this.currSeason) {
                    CommonExtKt.visible(imageView);
                } else {
                    CommonExtKt.gone(imageView);
                }
                if (map != null && (num3 = map.get("over")) != null && num3.intValue() == 1) {
                    textView.setTextColor(color);
                    CommonExtKt.visible(textView2);
                    CommonExtKt.gone(linearLayout);
                    textView2.setTextColor(color);
                    constraintLayout.setAlpha(0.5f);
                    return;
                }
                constraintLayout.setAlpha(1.0f);
                textView.setTextColor(color2);
                textView2.setTextColor(color2);
                if (((map == null || (num2 = map.get("seconds")) == null) ? 0 : num2.intValue()) > 0) {
                    CommonExtKt.visible(linearLayout);
                    CommonExtKt.gone(textView2);
                    textView3.setTextColor(color2);
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String str2 = String.format("%s / %s:00", Arrays.copyOf(new Object[]{TimeUtils.INSTANCE.getTime((map == null || (num = map.get("seconds")) == null) ? 0 : num.intValue()), item.runtime}, 2));
                    Intrinsics.checkNotNullExpressionValue(str2, "format(...)");
                    textView3.setText(str2);
                    return;
                }
                CommonExtKt.gone(linearLayout);
                CommonExtKt.visible(textView2);
            }
        };
        ((ActivityTvDetail2Binding) this.binding).recyclerView.setSelectedItemAtCentered(true);
        ((ActivityTvDetail2Binding) this.binding).recyclerView.setSpacingWithMargins(0, DensityUtils.dp2px(this, 15.0f));
        TvRecyclerView tvRecyclerView = ((ActivityTvDetail2Binding) this.binding).recyclerView;
        BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLogin(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        requestData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void focusScale(View v, float scale) {
        FocusBorder focusBorder = this.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.onFocus(v, FocusBorder.OptionsFactory.get(scale, scale, 0.0f));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void setViewFocusListener$lambda$24$lambda$23(TvDetailActivity tvDetailActivity, View view, boolean z) {
        if (z) {
            Intrinsics.checkNotNull(view);
            tvDetailActivity.focusScale(view, tvDetailActivity.scale);
            ((ActivityTvDetail2Binding) tvDetailActivity.binding).scrollView.scrollTo(0, 0);
        }
        FocusBorder focusBorder = tvDetailActivity.mFocusBorder;
        if (focusBorder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("mFocusBorder");
            focusBorder = null;
        }
        focusBorder.setVisible(z);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void initData() {
        FrameLayout flTop = ((ActivityTvDetail2Binding) this.binding).flTop;
        Intrinsics.checkNotNullExpressionValue(flTop, "flTop");
        FrameLayout frameLayout = flTop;
        ViewGroup.LayoutParams layoutParams = frameLayout.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = ScreenUtils.getScreenHeight() - ScreenUtils.dip2px(this, 100.0f);
            frameLayout.setLayoutParams(layoutParams);
            String stringExtra = getIntent().getStringExtra("id");
            if (stringExtra == null) {
                stringExtra = "";
            }
            this.id = stringExtra;
            String stringExtra2 = getIntent().getStringExtra("poster");
            if (stringExtra2 == null) {
                stringExtra2 = "";
            }
            this.poster = stringExtra2;
            String str = App.deviceLang;
            this.lang = str != null ? str : "";
            requestData();
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_DOWNLOAD)) {
                ImageView ivDownload = ((ActivityTvDetail2Binding) this.binding).ivDownload;
                Intrinsics.checkNotNullExpressionValue(ivDownload, "ivDownload");
                CommonExtKt.gone(ivDownload);
                return;
            }
            return;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void goVideoPlayer(final BaseMediaModel baseMediaModel, final boolean isNext, final int season, final int episode, final boolean playImmediately) {
        List<BaseMediaModel.DownloadFile> list = baseMediaModel != null ? baseMediaModel.list : null;
        if (list == null || list.isEmpty()) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("Episode %s is not available", Arrays.copyOf(new Object[]{Integer.valueOf(episode)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(...)");
            ToastUtils.showShort(str, new Object[0]);
            return;
        }
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
            ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
            choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity.goVideoPlayer.1
                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void playWithOther() {
                    List<BaseMediaModel.DownloadFile> list2;
                    ChoosePlayerQualityFragment.Companion companion = ChoosePlayerQualityFragment.INSTANCE;
                    BaseMediaModel baseMediaModel2 = baseMediaModel;
                    ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = companion.newInstance(new ArrayList<>((baseMediaModel2 == null || (list2 = baseMediaModel2.list) == null) ? new ArrayList() : list2));
                    final TvDetailActivity tvDetailActivity = this;
                    choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$goVideoPlayer$1$playWithOther$1
                        @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                        public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position) {
                            Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                            tvDetailActivity.playerWithOtherPlayer(downloadUrl.path);
                        }
                    });
                    choosePlayerQualityFragmentNewInstance.show(this.getSupportFragmentManager(), "ChoosePlayerQualityFragment");
                }

                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void play() {
                    this.startPlay(baseMediaModel, season, episode, isNext, playImmediately);
                }
            });
            choosePlayerDialog.show(getSupportFragmentManager(), "ChoosePlayerDialog");
            return;
        }
        startPlay(baseMediaModel, season, episode, isNext, playImmediately);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playerWithOtherPlayer(String url) {
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setAction("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(url), "video/mp4");
            startActivity(intent);
            Intent.createChooser(intent, "Choose a player to play!");
        } catch (Exception unused) {
            ToastUtils.showShort("No player available", new Object[0]);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initWatchedStatus(int watched) {
        if (watched == -1) {
            ((ActivityTvDetail2Binding) this.binding).ivWatchPlan.setImageResource(R.mipmap.ic_watch_plan_default);
        } else if (watched == 0) {
            ((ActivityTvDetail2Binding) this.binding).ivWatchPlan.setImageResource(R.mipmap.ic_waiting_added);
        } else {
            if (watched != 1) {
                return;
            }
            ((ActivityTvDetail2Binding) this.binding).ivWatchPlan.setImageResource(R.mipmap.ic_watched_added);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void startPlay(BaseMediaModel baseMediaModel, final int season, final int episode, final boolean isNext, boolean playImmediately) {
        TvDetail tvDetail = this.tvDetail;
        if (tvDetail != null) {
            tvDetail.addDonwload(baseMediaModel);
        }
        final ArrayList arrayList = new ArrayList();
        if (shouldRestartWatchedEpisode(season, episode)) {
            TvDetail tvDetail2 = this.tvDetail;
            if (tvDetail2 != null) {
                tvDetail2.seconds = 0;
            }
            VideoPlayer.start(this, this.tvDetail, season, episode, isNext, arrayList, true, false);
            return;
        }
        if (playImmediately) {
            VideoPlayer.start(this, this.tvDetail, season, episode, isNext, arrayList);
            return;
        }
        if ((baseMediaModel != null ? baseMediaModel.seconds : 0) > 300) {
            ChoosePlayFragment choosePlayFragmentNewInstance = ChoosePlayFragment.INSTANCE.newInstance(2, false);
            choosePlayFragmentNewInstance.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity.startPlay.1
                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void delete() {
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void goDetail() {
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void markWatched() {
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseResume() {
                    TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                    VideoPlayer.start(tvDetailActivity, tvDetailActivity.tvDetail, season, episode, isNext, arrayList);
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseRestart() {
                    TvDetailActivity tvDetailActivity = TvDetailActivity.this;
                    VideoPlayer.start(tvDetailActivity, tvDetailActivity.tvDetail, season, episode, isNext, arrayList, true, false);
                }
            });
            choosePlayFragmentNewInstance.show(getSupportFragmentManager(), "ChoosePlayFragment");
            return;
        }
        VideoPlayer.start(this, this.tvDetail, season, episode, isNext, arrayList);
    }

    private final boolean shouldRestartWatchedEpisode(int season, int episode) {
        List<TvDetail.SeasonDetail> data;
        BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter != null) {
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter = null;
            }
            data = baseQuickAdapter.getData();
        } else {
            data = null;
        }
        if (EpisodeSeasonMapper.INSTANCE.shouldRestartFromStart(data, season, episode)) {
            return true;
        }
        EpisodeSeasonMapper episodeSeasonMapper = EpisodeSeasonMapper.INSTANCE;
        TvDetail tvDetail = this.tvDetail;
        return episodeSeasonMapper.shouldRestartFromStart(tvDetail != null ? tvDetail.episodeList : null, season, episode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void syncEpisodeWatched(int season, Integer episode, int over) {
        if (this.adapter != null) {
            EpisodeSeasonMapper episodeSeasonMapper = EpisodeSeasonMapper.INSTANCE;
            BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter = this.adapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter = null;
            }
            episodeSeasonMapper.applyOver(baseQuickAdapter.getData(), season, episode, over);
            BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter2 = this.adapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter2 = null;
            }
            baseQuickAdapter2.notifyDataSetChanged();
        }
        EpisodeSeasonMapper episodeSeasonMapper2 = EpisodeSeasonMapper.INSTANCE;
        TvDetail tvDetail = this.tvDetail;
        episodeSeasonMapper2.applyOver(tvDetail != null ? tvDetail.episodeList : null, season, episode, over);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onPlayerFinish(OnPlayerFinishEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        Object objAs = Observable.timer(1L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, new Function0() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return TvDetailActivity.onPlayerFinish$lambda$26(this.f$0);
            }
        }, null, null, null, 29, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onPlayerFinish$lambda$26(TvDetailActivity tvDetailActivity) {
        tvDetailActivity.requestData();
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:120:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:129:0x02d5  */
    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void showVideoInfo(TvDetail tvDetail, boolean refreshList) {
        String[] strArr;
        boolean z;
        String str;
        String[] strArr2;
        String[] strArr3;
        String str2;
        List<String> listSplit;
        List listEmptyList;
        List<TvDetail.SeasonDetail> list;
        String deviceLang;
        List<BaseMediaModel.MoreLanguage> list2;
        List<BaseMediaModel.MoreLanguage> list3;
        initWatchedStatus(tvDetail != null ? tvDetail.plan_watched : -1);
        this.tvDetail = tvDetail;
        ((ActivityTvDetail2Binding) this.binding).tvName.setText(tvDetail != null ? tvDetail.title : null);
        if (this.selectLanguage.length() == 0) {
            if (tvDetail == null || (list3 = tvDetail.language) == null) {
                deviceLang = "en";
                break;
            }
            Iterator<T> it = list3.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (Intrinsics.areEqual(((BaseMediaModel.MoreLanguage) it.next()).lang, App.deviceLang)) {
                        deviceLang = App.deviceLang;
                        Intrinsics.checkNotNullExpressionValue(deviceLang, "deviceLang");
                        break;
                    }
                } else {
                    deviceLang = "en";
                    break;
                }
            }
            if (((tvDetail == null || (list2 = tvDetail.language) == null) ? 0 : list2.size()) > 1) {
                TextView tvLanguage = ((ActivityTvDetail2Binding) this.binding).tvLanguage;
                Intrinsics.checkNotNullExpressionValue(tvLanguage, "tvLanguage");
                CommonExtKt.visible(tvLanguage);
                ((ActivityTvDetail2Binding) this.binding).tvLanguage.setText(deviceLang);
            } else {
                TextView tvLanguage2 = ((ActivityTvDetail2Binding) this.binding).tvLanguage;
                Intrinsics.checkNotNullExpressionValue(tvLanguage2, "tvLanguage");
                CommonExtKt.gone(tvLanguage2);
            }
        } else {
            ((ActivityTvDetail2Binding) this.binding).tvLanguage.setText(this.selectLanguage);
        }
        if (!TextUtils.isEmpty(tvDetail != null ? tvDetail.history : null)) {
            if (!TextUtils.equals(tvDetail != null ? tvDetail.history : null, _UrlKt.PATH_SEGMENT_ENCODE_SET_URI)) {
                Object obj = JSONObject.parse(tvDetail != null ? tvDetail.history : null);
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
                JSONObject jSONObject = (JSONObject) obj;
                Integer integer = jSONObject.getInteger(DealWatchNextService.EPISODE);
                Intrinsics.checkNotNull(integer);
                this.episode = integer.intValue();
                Integer integer2 = jSONObject.getInteger(DealWatchNextService.SEASON);
                Intrinsics.checkNotNull(integer2);
                int iIntValue = integer2.intValue();
                this.season = iIntValue;
                this.currEpisode = this.episode;
                this.currSeason = iIntValue;
                Integer integer3 = jSONObject.getInteger("seconds");
                Integer integer4 = jSONObject.getInteger("over");
                if (integer4 != null && integer4.intValue() == 1) {
                    if ((tvDetail == null || (list = tvDetail.episodeList) == null || list.size() != this.episode) ? false : true) {
                        TextView textView = ((ActivityTvDetail2Binding) this.binding).tvPlay;
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String str3 = String.format("LAST PLAY S%s:E%s", Arrays.copyOf(new Object[]{Integer.valueOf(this.season), Integer.valueOf(this.episode)}, 2));
                        Intrinsics.checkNotNullExpressionValue(str3, "format(...)");
                        textView.setText(str3);
                    } else {
                        TextView textView2 = ((ActivityTvDetail2Binding) this.binding).tvPlay;
                        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                        String str4 = String.format("CONTINUE PLAY S%s:E%s", Arrays.copyOf(new Object[]{Integer.valueOf(this.season), Integer.valueOf(this.episode + 1)}, 2));
                        Intrinsics.checkNotNullExpressionValue(str4, "format(...)");
                        textView2.setText(str4);
                    }
                } else {
                    TextView textView3 = ((ActivityTvDetail2Binding) this.binding).tvPlay;
                    StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                    Integer numValueOf = Integer.valueOf(this.season);
                    Integer numValueOf2 = Integer.valueOf(this.episode);
                    TimeUtils timeUtils = TimeUtils.INSTANCE;
                    Intrinsics.checkNotNull(integer3);
                    String str5 = String.format("RESUME S%s:E%s  %s", Arrays.copyOf(new Object[]{numValueOf, numValueOf2, timeUtils.getTime(integer3.intValue())}, 3));
                    Intrinsics.checkNotNullExpressionValue(str5, "format(...)");
                    textView3.setText(str5);
                }
            }
        }
        if (refreshList) {
            List<TvDetail.SeasonDetail> list4 = tvDetail != null ? tvDetail.episodeList : null;
            if (!(list4 == null || list4.isEmpty())) {
                TvRecyclerView recyclerView = ((ActivityTvDetail2Binding) this.binding).recyclerView;
                Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
                CommonExtKt.visible(recyclerView);
                BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter = this.adapter;
                if (baseQuickAdapter == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter = null;
                }
                baseQuickAdapter.setList(tvDetail != null ? tvDetail.episodeList : null);
            } else {
                TvRecyclerView recyclerView2 = ((ActivityTvDetail2Binding) this.binding).recyclerView;
                Intrinsics.checkNotNullExpressionValue(recyclerView2, "recyclerView");
                CommonExtKt.gone(recyclerView2);
                BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter2 = this.adapter;
                if (baseQuickAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    baseQuickAdapter2 = null;
                }
                baseQuickAdapter2.setList(tvDetail != null ? tvDetail.episodeList : null);
            }
        }
        ((ActivityTvDetail2Binding) this.binding).tvDesc.setText(tvDetail != null ? tvDetail.description : null);
        TextView textView4 = ((ActivityTvDetail2Binding) this.binding).tvDirector;
        StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
        String str6 = String.format("Directors: %s", Arrays.copyOf(new Object[]{tvDetail != null ? tvDetail.director : null}, 1));
        Intrinsics.checkNotNullExpressionValue(str6, "format(...)");
        textView4.setText(str6);
        TextView textView5 = ((ActivityTvDetail2Binding) this.binding).tvStars;
        StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
        String str7 = String.format("Stars: %s", Arrays.copyOf(new Object[]{tvDetail != null ? tvDetail.actors : null}, 1));
        Intrinsics.checkNotNullExpressionValue(str7, "format(...)");
        textView5.setText(str7);
        if (tvDetail == null || (str2 = tvDetail.cats) == null || (listSplit = new Regex(",").split(str2, 0)) == null) {
            strArr = null;
        } else {
            if (!listSplit.isEmpty()) {
                ListIterator<String> listIterator = listSplit.listIterator(listSplit.size());
                while (true) {
                    if (listIterator.hasPrevious()) {
                        if (!(listIterator.previous().length() == 0)) {
                            listEmptyList = CollectionsKt.take(listSplit, listIterator.nextIndex() + 1);
                            break;
                        }
                    } else {
                        listEmptyList = CollectionsKt.emptyList();
                        break;
                    }
                }
            } else {
                listEmptyList = CollectionsKt.emptyList();
                break;
            }
            if (listEmptyList != null) {
                strArr = (String[]) listEmptyList.toArray(new String[0]);
            } else {
                strArr = null;
            }
        }
        if (strArr == null) {
            z = false;
        } else {
            if (!(strArr.length == 0)) {
                z = true;
            } else {
                z = false;
            }
        }
        String str8 = "";
        String str9 = z ? strArr[0] : "";
        if (str9.length() > 0) {
            TextView textView6 = ((ActivityTvDetail2Binding) this.binding).tvGenre;
            StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
            String upperCase = str9.toUpperCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            String str10 = String.format("Genre: %s", Arrays.copyOf(new Object[]{upperCase}, 1));
            Intrinsics.checkNotNullExpressionValue(str10, "format(...)");
            textView6.setText(str10);
        }
        if (tvDetail != null) {
            DetailMetaInfoBinder detailMetaInfoBinder = DetailMetaInfoBinder.INSTANCE;
            LayoutDetailMetaRowBinding metaInfoRow = ((ActivityTvDetail2Binding) this.binding).metaInfoRow;
            Intrinsics.checkNotNullExpressionValue(metaInfoRow, "metaInfoRow");
            detailMetaInfoBinder.bindTvDetail(metaInfoRow, tvDetail);
        }
        this.totalSeason = (tvDetail == null || (strArr3 = tvDetail.seasonList) == null) ? 1 : strArr3.length;
        TextView textView7 = ((ActivityTvDetail2Binding) this.binding).tvSeason;
        StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
        String str11 = String.format("SEASON %s/%s", Arrays.copyOf(new Object[]{Integer.valueOf(this.season), (tvDetail == null || (strArr2 = tvDetail.seasonList) == null) ? null : Integer.valueOf(strArr2.length)}, 2));
        Intrinsics.checkNotNullExpressionValue(str11, "format(...)");
        textView7.setText(str11);
        if (this.poster.length() == 0) {
            if (tvDetail != null && (str = tvDetail.poster) != null) {
                str8 = str;
            }
            this.poster = str8;
        }
        GlideUtils.load((Activity) this, tvDetail != null ? tvDetail.banner_mini : null, ((ActivityTvDetail2Binding) this.binding).ivBackground);
        ((ActivityTvDetail2Binding) this.binding).llTvName.setFocusable(true);
        ((ActivityTvDetail2Binding) this.binding).llTvInfo.setFocusable(true);
        ((ActivityTvDetail2Binding) this.binding).rlPlay.requestFocus();
        this.isFirst = false;
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void showSeasons(List<? extends TvDetail.SeasonDetail> seasons) {
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        List<? extends TvDetail.SeasonDetail> list = seasons;
        BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter = null;
        if (!list.isEmpty()) {
            TvRecyclerView recyclerView = ((ActivityTvDetail2Binding) this.binding).recyclerView;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
            CommonExtKt.visible(recyclerView);
            BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter2 = this.adapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                baseQuickAdapter = baseQuickAdapter2;
            }
            baseQuickAdapter.setList(list);
            return;
        }
        TvRecyclerView recyclerView2 = ((ActivityTvDetail2Binding) this.binding).recyclerView;
        Intrinsics.checkNotNullExpressionValue(recyclerView2, "recyclerView");
        CommonExtKt.gone(recyclerView2);
        BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter3 = this.adapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            baseQuickAdapter = baseQuickAdapter3;
        }
        baseQuickAdapter.setList(list);
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void showSeasons(int selectSeason, List<? extends TvDetail.SeasonDetail> seasons) {
        String[] strArr;
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        List<? extends TvDetail.SeasonDetail> list = seasons;
        Integer numValueOf = null;
        if (!list.isEmpty()) {
            TvRecyclerView recyclerView = ((ActivityTvDetail2Binding) this.binding).recyclerView;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
            CommonExtKt.visible(recyclerView);
            BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter = this.adapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter = null;
            }
            baseQuickAdapter.setList(list);
        } else {
            TvRecyclerView recyclerView2 = ((ActivityTvDetail2Binding) this.binding).recyclerView;
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "recyclerView");
            CommonExtKt.gone(recyclerView2);
            BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter2 = this.adapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter2 = null;
            }
            baseQuickAdapter2.setList(list);
        }
        TextView textView = ((ActivityTvDetail2Binding) this.binding).tvSeason;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        Integer numValueOf2 = Integer.valueOf(selectSeason);
        TvDetail tvDetail = this.tvDetail;
        if (tvDetail != null && (strArr = tvDetail.seasonList) != null) {
            numValueOf = Integer.valueOf(strArr.length);
        }
        String str = String.format("SEASON %s/%s", Arrays.copyOf(new Object[]{numValueOf2, numValueOf}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void showYear(int selectYear, List<? extends TvDetail.SeasonDetail> seasons) {
        String[] strArr;
        Intrinsics.checkNotNullParameter(seasons, "seasons");
        List<? extends TvDetail.SeasonDetail> list = seasons;
        Integer numValueOf = null;
        if (!list.isEmpty()) {
            TvRecyclerView recyclerView = ((ActivityTvDetail2Binding) this.binding).recyclerView;
            Intrinsics.checkNotNullExpressionValue(recyclerView, "recyclerView");
            CommonExtKt.visible(recyclerView);
            BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter = this.adapter;
            if (baseQuickAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter = null;
            }
            baseQuickAdapter.setList(list);
        } else {
            TvRecyclerView recyclerView2 = ((ActivityTvDetail2Binding) this.binding).recyclerView;
            Intrinsics.checkNotNullExpressionValue(recyclerView2, "recyclerView");
            CommonExtKt.gone(recyclerView2);
            BaseQuickAdapter<TvDetail.SeasonDetail, BaseViewHolder> baseQuickAdapter2 = this.adapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                baseQuickAdapter2 = null;
            }
            baseQuickAdapter2.setList(list);
        }
        if (selectYear == -1) {
            ((ActivityTvDetail2Binding) this.binding).tvSeason.setText("Released");
            return;
        }
        TextView textView = ((ActivityTvDetail2Binding) this.binding).tvSeason;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String strValueOf = String.valueOf(selectYear);
        TvDetail tvDetail = this.tvDetail;
        if (tvDetail != null && (strArr = tvDetail.seasonList) != null) {
            numValueOf = Integer.valueOf(strArr.length);
        }
        String str = String.format("YEAR %s", Arrays.copyOf(new Object[]{strValueOf, numValueOf}, 2));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        textView.setText(str);
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void loadInfoError() {
        LinearLayout llLoading = ((ActivityTvDetail2Binding) this.binding).llLoading;
        Intrinsics.checkNotNullExpressionValue(llLoading, "llLoading");
        CommonExtKt.visible(llLoading);
        ProgressBar progressBar = ((ActivityTvDetail2Binding) this.binding).progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
        LinearLayout llResume = ((ActivityTvDetail2Binding) this.binding).llResume;
        Intrinsics.checkNotNullExpressionValue(llResume, "llResume");
        CommonExtKt.gone(llResume);
        ((ActivityTvDetail2Binding) this.binding).tvLoading.setText("load error");
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void showPlayButtonLoading() {
        LinearLayout llLoading = ((ActivityTvDetail2Binding) this.binding).llLoading;
        Intrinsics.checkNotNullExpressionValue(llLoading, "llLoading");
        CommonExtKt.visible(llLoading);
        LinearLayout llResume = ((ActivityTvDetail2Binding) this.binding).llResume;
        Intrinsics.checkNotNullExpressionValue(llResume, "llResume");
        CommonExtKt.gone(llResume);
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void hidePlayButtonLoading() {
        LinearLayout llLoading = ((ActivityTvDetail2Binding) this.binding).llLoading;
        Intrinsics.checkNotNullExpressionValue(llLoading, "llLoading");
        CommonExtKt.gone(llLoading);
        LinearLayout llResume = ((ActivityTvDetail2Binding) this.binding).llResume;
        Intrinsics.checkNotNullExpressionValue(llResume, "llResume");
        CommonExtKt.visible(llResume);
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id, int currSeason, int currEpisode, boolean isNext, boolean isContinue) {
        Intrinsics.checkNotNullParameter(list, "list");
        ScreenManageActivity.INSTANCE.start(this, list, 100, msg, 1, id, currSeason, currEpisode, isNext, isContinue);
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void hideLoadView() {
        runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.hideLoadingView();
            }
        });
    }

    /* JADX WARN: Type inference failed for: r1v2, types: [com.movieboxpro.android.tv.tv.TvDetailActivity$showActors$adapter$1] */
    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.View
    public void showActors(List<Actor> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        List<Actor> list2 = list;
        if (list2.isEmpty()) {
            return;
        }
        TvRecyclerView rvActors = ((ActivityTvDetail2Binding) this.binding).rvActors;
        Intrinsics.checkNotNullExpressionValue(rvActors, "rvActors");
        CommonExtKt.visible(rvActors);
        TextView tvActors = ((ActivityTvDetail2Binding) this.binding).tvActors;
        Intrinsics.checkNotNullExpressionValue(tvActors, "tvActors");
        CommonExtKt.visible(tvActors);
        final int i = R.layout.adapter_actor_item;
        final List mutableList = CollectionsKt.toMutableList((Collection) list2);
        final ?? r1 = new CommonBaseAdapter<Actor>(i, mutableList) { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$showActors$adapter$1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder holder, Actor item) {
                Intrinsics.checkNotNullParameter(holder, "holder");
                Intrinsics.checkNotNullParameter(item, "item");
                ConstraintLayout constraintLayout = (ConstraintLayout) holder.getView(R.id.clContainer);
                ViewGroup.LayoutParams layoutParams = constraintLayout.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = DensityUtils.dp2px(140.0f);
                    constraintLayout.setLayoutParams(layoutParams);
                    ((UserAvatarView) holder.getView(R.id.avatarView)).setAvatar(item.getAvatar(), item.getName());
                    holder.setText(R.id.tvName, item.getName());
                    return;
                }
                throw new NullPointerException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
            }
        };
        ((ActivityTvDetail2Binding) this.binding).rvActors.setAdapter((RecyclerView.Adapter) r1);
        ((ActivityTvDetail2Binding) this.binding).rvActors.addItemDecoration(new LinearItemDecoration(0, 16, false));
        r1.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda10
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i2) {
                TvDetailActivity.showActors$lambda$32(this.f$0, r1, baseQuickAdapter, view, i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showActors$lambda$32(TvDetailActivity tvDetailActivity, TvDetailActivity$showActors$adapter$1 tvDetailActivity$showActors$adapter$1, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
        Intrinsics.checkNotNullParameter(view, "<unused var>");
        ActorDetailActivity.Companion companion = ActorDetailActivity.INSTANCE;
        TvDetailActivity tvDetailActivity2 = tvDetailActivity;
        String actor_id = tvDetailActivity$showActors$adapter$1.getItem(i).getActor_id();
        if (actor_id == null) {
            actor_id = "";
        }
        companion.start(tvDetailActivity2, actor_id);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected void requestData() {
        ((TvDetailPresenter) this.mPresenter).requestDetailInfo(this.id, this.lang, true);
        ((TvDetailPresenter) this.mPresenter).getActors(this.id);
    }

    private final void noRefreshList() {
        ((TvDetailPresenter) this.mPresenter).requestDetailInfo(this.id, this.lang, false);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 100 && data != null) {
            data.getStringExtra("id");
            data.getIntExtra(DealWatchNextService.SEASON, 1);
            data.getIntExtra(DealWatchNextService.EPISODE, 1);
            data.getBooleanExtra("isNext", false);
            data.getBooleanExtra("isContinue", false);
            requestData();
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        this.handle.removeCallbacksAndMessages(null);
        ((ActivityTvDetail2Binding) this.binding).ivBackground.setImageDrawable(null);
        ((ActivityTvDetail2Binding) this.binding).backgroundLayout.setBackground(null);
        super.onDestroy();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    protected int getLayoutResId() {
        return R.layout.activity_tv_detail2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpActivity
    public TvDetailPresenter bindPresenter() {
        return new TvDetailPresenter(this);
    }

    /* JADX INFO: compiled from: TvDetailActivity.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/tv/tv/TvDetailActivity$Companion;", "", "<init>", "()V", "starter", "", "context", "Landroid/content/Context;", "id", "", "poster", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void starter(Context context, String id, String poster) {
            Intrinsics.checkNotNullParameter(id, "id");
            Intrinsics.checkNotNullParameter(poster, "poster");
            Intent intent = new Intent(context, (Class<?>) TvDetailActivity.class);
            intent.putExtra("id", id);
            intent.putExtra("poster", poster);
            if (context != null) {
                context.startActivity(intent);
            }
        }
    }

    private final void setViewFocusListener(View... args) {
        for (View view : args) {
            view.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.movieboxpro.android.tv.tv.TvDetailActivity$$ExternalSyntheticLambda14
                @Override // android.view.View.OnFocusChangeListener
                public final void onFocusChange(View view2, boolean z) {
                    TvDetailActivity.setViewFocusListener$lambda$24$lambda$23(this.f$0, view2, z);
                }
            });
        }
    }
}
