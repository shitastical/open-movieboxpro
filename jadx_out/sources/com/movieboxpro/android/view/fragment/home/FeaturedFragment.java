package com.movieboxpro.android.view.fragment.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.BaseOnItemViewClickedListener;
import androidx.leanback.widget.BaseOnItemViewSelectedListener;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.ObjectAdapter;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.RowPresenter;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.asm.Opcodes;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseRowsMvpFragment;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.event.LogoutEvent;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.event.OnBackPressedEvent;
import com.movieboxpro.android.event.OnPlayerFinishEvent;
import com.movieboxpro.android.event.OpenHideMovieListEvent;
import com.movieboxpro.android.listener.RefreshListener;
import com.movieboxpro.android.livedata.OnPlayerFinishLiveData;
import com.movieboxpro.android.livedata.PrivateModeChangedLiveData;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.livedata.RefreshWatchingLiveData;
import com.movieboxpro.android.livedata.SelectWatchPlanWatchedLiveData;
import com.movieboxpro.android.model.AdvertisementModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.FeaturedDataModel;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.player.VideoPlayerActivity;
import com.movieboxpro.android.service.CreateChannelService;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.tv.MoreMovieActivity;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.tv.list.PlayListDetailActivity;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.tvchannel.Clip;
import com.movieboxpro.android.tvchannel.Playlist;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DensityUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.Utils;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.WebViewDialogFragment;
import com.movieboxpro.android.view.fragment.account.ChoosePlayFragment;
import com.movieboxpro.android.view.fragment.account.OpenChildModeDialog;
import com.movieboxpro.android.view.tvpresenter.FeaturedPresenterSelector;
import com.movieboxpro.android.view.tvview.BaseVideoCardView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: FeaturedFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0002H\u0014J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u001a\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000eH\u0014J\u0018\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u001b\u001a\u00020\nH\u0016J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0012\u0010\u001f\u001a\u00020\u000e2\b\u0010 \u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010$\u001a\u00020\u000eH\u0014J\b\u0010%\u001a\u00020\u000eH\u0014J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\nH\u0002J\u0010\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*H\u0007J\u0010\u0010+\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020,H\u0007J\u0010\u0010-\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020.H\u0007J\u0010\u0010/\u001a\u00020\u000e2\u0006\u0010)\u001a\u000200H\u0007J\u0006\u00101\u001a\u00020\u000eJ\b\u00102\u001a\u00020\u000eH\u0014J\u0010\u00103\u001a\u0002042\u0006\u00105\u001a\u00020\u0017H\u0002J\u0010\u00106\u001a\u00020\u000e2\u0006\u00107\u001a\u000208H\u0016J\u0016\u00109\u001a\u00020\u000e2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00170;H\u0016J\u0016\u0010<\u001a\u00020\u000e2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u00170;H\u0016J\b\u0010=\u001a\u00020\u000eH\u0016J\b\u0010>\u001a\u00020\u000eH\u0016J\b\u0010?\u001a\u00020\u000eH\u0016JL\u0010@\u001a\u00020\u000e2\u0016\u0010:\u001a\u0012\u0012\u0004\u0012\u00020B0Aj\b\u0012\u0004\u0012\u00020B`C2\b\u0010\u001b\u001a\u0004\u0018\u00010\n2\b\u0010D\u001a\u0004\u0018\u00010\n2\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020F2\u0006\u0010H\u001a\u00020FH\u0016J\b\u0010I\u001a\u00020\u000eH\u0016J\u0010\u0010J\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020KH\u0007J\u0010\u0010L\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020MH\u0007J\"\u0010N\u001a\u00020\u000e2\u0006\u0010O\u001a\u00020F2\u0006\u0010P\u001a\u00020F2\b\u0010Q\u001a\u0004\u0018\u00010RH\u0016J\b\u0010S\u001a\u00020\u000eH\u0016J\b\u0010T\u001a\u00020\u000eH\u0016J\b\u0010U\u001a\u00020\u000eH\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/FeaturedFragment;", "Lcom/movieboxpro/android/base/mvp/BaseRowsMvpFragment;", "Lcom/movieboxpro/android/view/fragment/home/FeaturedPresenter;", "Lcom/movieboxpro/android/view/fragment/home/FeaturedContract$View;", "<init>", "()V", "bindPresenter", "rowAdapter", "Landroidx/leanback/widget/ArrayObjectAdapter;", "channelListId", "", "refreshListener", "Lcom/movieboxpro/android/listener/RefreshListener;", "setRefreshListener", "", "onViewCreated", "view", "Landroid/view/View;", "savedInstanceState", "Landroid/os/Bundle;", "initListener", "dealItemClick", "model", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "playImmediately", "", "loadVideoFailed", NotificationCompat.CATEGORY_MESSAGE, "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "playWithOtherPlayer", "url", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "initData", "initView", "checkChildMode", "contentRating", "onLoginIn", NotificationCompat.CATEGORY_EVENT, "Lcom/movieboxpro/android/event/LoginEvent;", "onSignOut", "Lcom/movieboxpro/android/event/LogoutEvent;", "onOpenHideMovieList", "Lcom/movieboxpro/android/event/OpenHideMovieListEvent;", "onBackPressed", "Lcom/movieboxpro/android/event/OnBackPressedEvent;", "refreshData", "loadData", "buildClip", "Lcom/movieboxpro/android/tvchannel/Clip;", "item", "showVideo", "featuredDataModel", "Lcom/movieboxpro/android/model/FeaturedDataModel;", "showMaybeLikeList", "list", "", "showContinueList", "hideLoadView", "showLoading", "hideLoading", "showScreenManage", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", "id", "boxType", "", "currSeason", "currEpisode", "deleteComplete", "onPlayFinish", "Lcom/movieboxpro/android/event/OnPlayerFinishEvent;", "onLowMemory", "Lcom/movieboxpro/android/event/LowMemoryEvent;", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onFragmentResumeNoFirst", "onStart", "onDestroy", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FeaturedFragment extends BaseRowsMvpFragment<FeaturedPresenter> implements FeaturedContract.View {
    private String channelListId = "MovieBoxProTodayHotMovieChannelId";
    private RefreshListener refreshListener;
    private ArrayObjectAdapter rowAdapter;

    public FeaturedFragment() {
        ListRowPresenter listRowPresenter = new ListRowPresenter();
        listRowPresenter.setNumRows(1);
        ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(listRowPresenter);
        this.rowAdapter = arrayObjectAdapter;
        setAdapter(arrayObjectAdapter);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    public FeaturedPresenter bindPresenter() {
        return new FeaturedPresenter(this);
    }

    public final void setRefreshListener(RefreshListener refreshListener) {
        Intrinsics.checkNotNullParameter(refreshListener, "refreshListener");
        this.refreshListener = refreshListener;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment, androidx.leanback.app.RowsSupportFragment, androidx.leanback.app.BaseRowSupportFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initListener() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = "";
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = -1;
        final Ref.LongRef longRef = new Ref.LongRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        setOnItemViewClickedListener(new BaseOnItemViewClickedListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda5
            @Override // androidx.leanback.widget.BaseOnItemViewClickedListener
            public final void onItemClicked(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                FeaturedFragment.initListener$lambda$2(objectRef2, longRef, objectRef, intRef, this, viewHolder, obj, viewHolder2, obj2);
            }
        });
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        setOnItemViewSelectedListener(new BaseOnItemViewSelectedListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda6
            @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
            public final void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                FeaturedFragment.initListener$lambda$3(objectRef3, viewHolder, obj, viewHolder2, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void initListener$lambda$2(final Ref.ObjectRef objectRef, Ref.LongRef longRef, Ref.ObjectRef objectRef2, Ref.IntRef intRef, final FeaturedFragment featuredFragment, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.movieboxpro.android.model.common.Homelist.Typelist");
        final Homelist.Typelist typelist = (Homelist.Typelist) obj;
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Disposable disposable = (Disposable) objectRef.element;
        if (disposable != null) {
            disposable.dispose();
        }
        if (System.currentTimeMillis() - longRef.element < 500 && Intrinsics.areEqual(typelist.id, objectRef2.element) && typelist.viewType == intRef.element) {
            booleanRef.element = true;
            featuredFragment.dealItemClick(typelist, booleanRef.element);
        } else {
            booleanRef.element = false;
            Object objAs = Observable.timer(600L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(featuredFragment));
            Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return FeaturedFragment.initListener$lambda$2$lambda$0(objectRef, (Disposable) obj3);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj3) {
                    return FeaturedFragment.initListener$lambda$2$lambda$1(this.f$0, typelist, booleanRef, (Long) obj3);
                }
            }, 11, null);
        }
        longRef.element = System.currentTimeMillis();
        String str = typelist.id;
        T t = str;
        if (str == null) {
            t = "";
        }
        objectRef2.element = t;
        intRef.element = typelist.viewType;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initListener$lambda$2$lambda$1(FeaturedFragment featuredFragment, Homelist.Typelist typelist, Ref.BooleanRef booleanRef, Long l) {
        featuredFragment.dealItemClick(typelist, booleanRef.element);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit initListener$lambda$2$lambda$0(Ref.ObjectRef objectRef, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        objectRef.element = it;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.movieboxpro.android.view.tvview.BaseVideoCardView] */
    public static final void initListener$lambda$3(Ref.ObjectRef objectRef, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        BaseVideoCardView baseVideoCardView = (BaseVideoCardView) objectRef.element;
        if (baseVideoCardView != null) {
            baseVideoCardView.hideTitle();
        }
        if ((viewHolder != null ? viewHolder.view : null) != null) {
            View view = viewHolder.view;
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.movieboxpro.android.view.tvview.BaseVideoCardView<*>");
            ?? r1 = (BaseVideoCardView) view;
            r1.showTitle();
            objectRef.element = r1;
        }
    }

    private final void dealItemClick(Homelist.Typelist model, boolean playImmediately) {
        if (!Intrinsics.areEqual(model.id, "0")) {
            if (model.viewType == 0) {
                Context context = getContext();
                if (context != null) {
                    MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                    String id = model.id;
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    companion.starter(context, id, model.poster);
                }
            } else if (model.viewType == 1) {
                Context context2 = getContext();
                if (context2 != null) {
                    TvDetailActivity.Companion companion2 = TvDetailActivity.INSTANCE;
                    String id2 = model.id;
                    Intrinsics.checkNotNullExpressionValue(id2, "id");
                    String poster = model.poster;
                    Intrinsics.checkNotNullExpressionValue(poster, "poster");
                    companion2.starter(context2, id2, poster);
                }
            } else if (model.viewType != 2) {
                if (model.viewType == 4) {
                    Context context3 = getContext();
                    if (context3 != null) {
                        PlayListDetailActivity.INSTANCE.start(context3, model.getLid(), model.getUsername());
                    }
                } else if (model.viewType == 3) {
                    String content_rating = model.getContent_rating();
                    Intrinsics.checkNotNullExpressionValue(content_rating, "getContent_rating(...)");
                    if (!checkChildMode(content_rating)) {
                        FeaturedPresenter featuredPresenter = (FeaturedPresenter) this.mPresenter;
                        String id3 = model.id;
                        Intrinsics.checkNotNullExpressionValue(id3, "id");
                        int i = model.box_type;
                        Homelist.History history = model.getHistory();
                        int season = history != null ? history.getSeason() : 0;
                        Homelist.History history2 = model.getHistory();
                        int episode = history2 != null ? history2.getEpisode() : 0;
                        String str = model.title;
                        String str2 = model.poster;
                        Homelist.History history3 = model.getHistory();
                        int over = history3 != null ? history3.getOver() : 0;
                        Homelist.History history4 = model.getHistory();
                        featuredPresenter.getPlayPath(id3, i, season, episode, playImmediately, str, str2, over, history4 != null ? history4.getSeconds() : 0);
                    }
                } else if (model.viewType == 5) {
                    FeaturedPresenter featuredPresenter2 = (FeaturedPresenter) this.mPresenter;
                    if (featuredPresenter2 != null) {
                        featuredPresenter2.advNotify(model.id);
                    }
                    String type = model.getType();
                    if (type != null) {
                        switch (type.hashCode()) {
                            case 49:
                                if (type.equals(IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                                    WebViewDialogFragment.Companion companion3 = WebViewDialogFragment.INSTANCE;
                                    String url = model.getUrl();
                                    Intrinsics.checkNotNullExpressionValue(url, "getUrl(...)");
                                    WebViewDialogFragment webViewDialogFragmentNewInstance = companion3.newInstance(url);
                                    FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
                                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
                                    WebViewDialogFragment webViewDialogFragment = webViewDialogFragmentNewInstance;
                                    fragmentTransactionBeginTransaction.add(webViewDialogFragment, "WebViewDialogFragment");
                                    fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                                    Intrinsics.checkNotNull(fragmentTransactionBeginTransaction.show(webViewDialogFragment));
                                }
                                break;
                            case 50:
                                if (type.equals(ExifInterface.GPS_MEASUREMENT_2D)) {
                                    WebViewDialogFragment.Companion companion4 = WebViewDialogFragment.INSTANCE;
                                    String url2 = model.getUrl();
                                    Intrinsics.checkNotNullExpressionValue(url2, "getUrl(...)");
                                    WebViewDialogFragment webViewDialogFragmentNewInstance2 = companion4.newInstance(url2);
                                    FragmentTransaction fragmentTransactionBeginTransaction2 = getChildFragmentManager().beginTransaction();
                                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction2, "beginTransaction(...)");
                                    WebViewDialogFragment webViewDialogFragment2 = webViewDialogFragmentNewInstance2;
                                    fragmentTransactionBeginTransaction2.add(webViewDialogFragment2, "WebViewDialogFragment");
                                    fragmentTransactionBeginTransaction2.commitAllowingStateLoss();
                                    Intrinsics.checkNotNull(fragmentTransactionBeginTransaction2.show(webViewDialogFragment2));
                                }
                                break;
                            case ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_BASELINE_TO_BOTTOM_OF /* 53 */:
                                if (type.equals("5")) {
                                    MovieDetailActivity.Companion companion5 = MovieDetailActivity.INSTANCE;
                                    Context context4 = getContext();
                                    String url3 = model.getUrl();
                                    Intrinsics.checkNotNullExpressionValue(url3, "getUrl(...)");
                                    companion5.starter(context4, url3, "");
                                }
                                break;
                            case 54:
                                if (type.equals("6")) {
                                    TvDetailActivity.Companion companion6 = TvDetailActivity.INSTANCE;
                                    Context context5 = getContext();
                                    String url4 = model.getUrl();
                                    Intrinsics.checkNotNullExpressionValue(url4, "getUrl(...)");
                                    companion6.starter(context5, url4, "");
                                }
                                break;
                            case 55:
                                if (type.equals("7")) {
                                    PlayListDetailActivity.INSTANCE.start(getContext(), model.getUrl(), "");
                                }
                                break;
                            case Opcodes.FSTORE /* 56 */:
                                type.equals("8");
                                break;
                        }
                    }
                }
            } else if (model.box_type == 5) {
                WebViewDialogFragment.Companion companion7 = WebViewDialogFragment.INSTANCE;
                String url5 = model.getUrl();
                Intrinsics.checkNotNullExpressionValue(url5, "getUrl(...)");
                WebViewDialogFragment webViewDialogFragmentNewInstance3 = companion7.newInstance(url5);
                FragmentTransaction fragmentTransactionBeginTransaction3 = getChildFragmentManager().beginTransaction();
                Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction3, "beginTransaction(...)");
                WebViewDialogFragment webViewDialogFragment3 = webViewDialogFragmentNewInstance3;
                fragmentTransactionBeginTransaction3.add(webViewDialogFragment3, "WebViewDialogFragment");
                fragmentTransactionBeginTransaction3.commitAllowingStateLoss();
                fragmentTransactionBeginTransaction3.show(webViewDialogFragment3);
            } else {
                Context context6 = getContext();
                if (context6 != null) {
                    TvDetailActivity.Companion companion8 = TvDetailActivity.INSTANCE;
                    String id4 = model.id;
                    Intrinsics.checkNotNullExpressionValue(id4, "id");
                    String poster2 = model.poster;
                    Intrinsics.checkNotNullExpressionValue(poster2, "poster");
                    companion8.starter(context6, id4, poster2);
                }
            }
        } else if (Intrinsics.areEqual(model.getType(), "continue")) {
            SelectWatchPlanWatchedLiveData.INSTANCE.get().setValue(true);
        } else {
            Context context7 = getContext();
            if (context7 != null) {
                MoreMovieActivity.Companion companion9 = MoreMovieActivity.INSTANCE;
                String type2 = model.getType();
                Intrinsics.checkNotNullExpressionValue(type2, "getType(...)");
                companion9.start(context7, type2);
            }
        }
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        setOnItemViewSelectedListener(new BaseOnItemViewSelectedListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda3
            @Override // androidx.leanback.widget.BaseOnItemViewSelectedListener
            public final void onItemSelected(Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
                FeaturedFragment.dealItemClick$lambda$9(objectRef, viewHolder, obj, viewHolder2, obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v2, types: [T, com.movieboxpro.android.view.tvview.BaseVideoCardView] */
    public static final void dealItemClick$lambda$9(Ref.ObjectRef objectRef, Presenter.ViewHolder viewHolder, Object obj, RowPresenter.ViewHolder viewHolder2, Object obj2) {
        BaseVideoCardView baseVideoCardView = (BaseVideoCardView) objectRef.element;
        if (baseVideoCardView != null) {
            baseVideoCardView.hideTitle();
        }
        if ((viewHolder != null ? viewHolder.view : null) != null) {
            View view = viewHolder.view;
            Intrinsics.checkNotNull(view, "null cannot be cast to non-null type com.movieboxpro.android.view.tvview.BaseVideoCardView<*>");
            ?? r1 = (BaseVideoCardView) view;
            r1.showTitle();
            objectRef.element = r1;
        }
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void loadVideoFailed(String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        RefreshListener refreshListener = this.refreshListener;
        if (refreshListener != null) {
            refreshListener.showError(msg);
        }
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void goTvPlayer(final TvDetail tvDetail, final boolean playImmediately) {
        Intrinsics.checkNotNullParameter(tvDetail, "tvDetail");
        final ArrayList arrayList = new ArrayList();
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
            ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
            choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment.goTvPlayer.1
                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void playWithOther() {
                    ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = ChoosePlayerQualityFragment.INSTANCE.newInstance(new ArrayList<>(tvDetail.list));
                    final FeaturedFragment featuredFragment = this;
                    choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$goTvPlayer$1$playWithOther$1
                        @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                        public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position) {
                            Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                            featuredFragment.playWithOtherPlayer(downloadUrl.path);
                        }
                    });
                    FragmentManager childFragmentManager = this.getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                    choosePlayerQualityFragmentNewInstance.showAllowingStateLoss(childFragmentManager, "ChoosePlayerQualityFragment");
                }

                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void play() {
                    if (playImmediately) {
                        Context context = this.getContext();
                        if (context != null) {
                            TvDetail tvDetail2 = tvDetail;
                            VideoPlayer.start(context, tvDetail2, tvDetail2.seasonDetail.season, tvDetail2.seasonDetail.episode, false, arrayList, false, true);
                            return;
                        }
                        return;
                    }
                    ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 2, false, 2, null);
                    final FeaturedFragment featuredFragment = this;
                    final TvDetail tvDetail3 = tvDetail;
                    final ArrayList<TvSeasonList> arrayList2 = arrayList;
                    choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$goTvPlayer$1$play$2
                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void chooseResume() {
                            Context context2 = featuredFragment.getContext();
                            if (context2 != null) {
                                TvDetail tvDetail4 = tvDetail3;
                                VideoPlayer.start(context2, tvDetail4, tvDetail4.seasonDetail.season, tvDetail4.seasonDetail.episode, false, arrayList2, false, true);
                            }
                        }

                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void chooseRestart() {
                            Context context2 = featuredFragment.getContext();
                            if (context2 != null) {
                                TvDetail tvDetail4 = tvDetail3;
                                VideoPlayer.start(context2, tvDetail4, tvDetail4.seasonDetail.season, tvDetail4.seasonDetail.episode, false, arrayList2, true, true);
                            }
                        }

                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void goDetail() {
                            TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
                            Context context2 = featuredFragment.getContext();
                            String id = tvDetail3.id;
                            Intrinsics.checkNotNullExpressionValue(id, "id");
                            String poster = tvDetail3.poster;
                            Intrinsics.checkNotNullExpressionValue(poster, "poster");
                            companion.starter(context2, id, poster);
                        }

                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void delete() {
                            ((FeaturedPresenter) featuredFragment.mPresenter).deleteHistory(tvDetail3.id, 2);
                        }

                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void markWatched() {
                            ((FeaturedPresenter) featuredFragment.mPresenter).markWatched(tvDetail3.id, 2);
                        }
                    });
                    FragmentManager childFragmentManager = this.getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                    choosePlayFragmentNewInstance$default.showAllowingStateLoss(childFragmentManager, "ChoosePlayFragment");
                }
            });
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
            choosePlayerDialog.showAllowingStateLoss(childFragmentManager, "ChoosePlayerDialog");
            return;
        }
        if (playImmediately) {
            Context context = getContext();
            if (context != null) {
                VideoPlayer.start(context, tvDetail, tvDetail.seasonDetail.season, tvDetail.seasonDetail.episode, false, arrayList, false, true);
                return;
            }
            return;
        }
        ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 2, false, 2, null);
        choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment.goTvPlayer.3
            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void chooseResume() {
                Context context2 = FeaturedFragment.this.getContext();
                if (context2 != null) {
                    TvDetail tvDetail2 = tvDetail;
                    VideoPlayer.start(context2, tvDetail2, tvDetail2.seasonDetail.season, tvDetail2.seasonDetail.episode, false, arrayList, false, true);
                }
            }

            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void chooseRestart() {
                Context context2 = FeaturedFragment.this.getContext();
                if (context2 != null) {
                    TvDetail tvDetail2 = tvDetail;
                    VideoPlayer.start(context2, tvDetail2, tvDetail2.seasonDetail.season, tvDetail2.seasonDetail.episode, false, arrayList, true, true);
                }
            }

            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void goDetail() {
                TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
                Context context2 = FeaturedFragment.this.getContext();
                String id = tvDetail.id;
                Intrinsics.checkNotNullExpressionValue(id, "id");
                String poster = tvDetail.poster;
                Intrinsics.checkNotNullExpressionValue(poster, "poster");
                companion.starter(context2, id, poster);
            }

            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void delete() {
                ((FeaturedPresenter) FeaturedFragment.this.mPresenter).deleteHistory(tvDetail.id, 2);
            }

            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void markWatched() {
                ((FeaturedPresenter) FeaturedFragment.this.mPresenter).markWatched(tvDetail.id, 2);
            }
        });
        FragmentManager childFragmentManager2 = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "getChildFragmentManager(...)");
        choosePlayFragmentNewInstance$default.showAllowingStateLoss(childFragmentManager2, "ChoosePlayFragment");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void playWithOtherPlayer(String url) {
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

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void goMoviePlayer(final MovieDetail movieDetail, final boolean playImmediately) {
        Intrinsics.checkNotNullParameter(movieDetail, "movieDetail");
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
            ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
            choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment.goMoviePlayer.1
                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void playWithOther() {
                    ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = ChoosePlayerQualityFragment.INSTANCE.newInstance(new ArrayList<>(movieDetail.list));
                    final FeaturedFragment featuredFragment = this;
                    choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$goMoviePlayer$1$playWithOther$1
                        @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                        public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position) {
                            Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                            featuredFragment.playWithOtherPlayer(downloadUrl.path);
                        }
                    });
                    FragmentManager childFragmentManager = this.getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                    choosePlayerQualityFragmentNewInstance.showAllowingStateLoss(childFragmentManager, "ChoosePlayerQualityFragment");
                }

                @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                public void play() {
                    if (playImmediately) {
                        Context context = this.getContext();
                        if (context != null) {
                            MovieDetail movieDetail2 = movieDetail;
                            VideoPlayerActivity.start(context, movieDetail2, movieDetail2.id);
                            return;
                        }
                        return;
                    }
                    ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 1, false, 2, null);
                    final FeaturedFragment featuredFragment = this;
                    final MovieDetail movieDetail3 = movieDetail;
                    choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$goMoviePlayer$1$play$2
                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void chooseResume() {
                            Context context2 = featuredFragment.getContext();
                            if (context2 != null) {
                                MovieDetail movieDetail4 = movieDetail3;
                                VideoPlayerActivity.start(context2, movieDetail4, movieDetail4.id);
                            }
                        }

                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void chooseRestart() {
                            Context context2 = featuredFragment.getContext();
                            if (context2 != null) {
                                MovieDetail movieDetail4 = movieDetail3;
                                VideoPlayerActivity.start(context2, movieDetail4, movieDetail4.id, true);
                            }
                        }

                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void goDetail() {
                            MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                            Context context2 = featuredFragment.getContext();
                            String id = movieDetail3.id;
                            Intrinsics.checkNotNullExpressionValue(id, "id");
                            companion.starter(context2, id, movieDetail3.poster);
                        }

                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void delete() {
                            ((FeaturedPresenter) featuredFragment.mPresenter).deleteHistory(movieDetail3.id, 1);
                        }

                        @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                        public void markWatched() {
                            ((FeaturedPresenter) featuredFragment.mPresenter).markWatched(movieDetail3.id, 1);
                        }
                    });
                    FragmentManager childFragmentManager = this.getChildFragmentManager();
                    Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                    choosePlayFragmentNewInstance$default.showAllowingStateLoss(childFragmentManager, "ChoosePlayFragment");
                }
            });
            FragmentManager childFragmentManager = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
            choosePlayerDialog.showAllowingStateLoss(childFragmentManager, "ChoosePlayerDialog");
            return;
        }
        if (playImmediately) {
            Context context = getContext();
            if (context != null) {
                VideoPlayerActivity.start(context, movieDetail, movieDetail.id);
                return;
            }
            return;
        }
        ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 1, false, 2, null);
        choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment.goMoviePlayer.3
            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void chooseResume() {
                Context context2 = FeaturedFragment.this.getContext();
                if (context2 != null) {
                    MovieDetail movieDetail2 = movieDetail;
                    VideoPlayerActivity.start(context2, movieDetail2, movieDetail2.id);
                }
            }

            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void chooseRestart() {
                Context context2 = FeaturedFragment.this.getContext();
                if (context2 != null) {
                    MovieDetail movieDetail2 = movieDetail;
                    VideoPlayerActivity.start(context2, movieDetail2, movieDetail2.id, true);
                }
            }

            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void goDetail() {
                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                Context context2 = FeaturedFragment.this.getContext();
                String id = movieDetail.id;
                Intrinsics.checkNotNullExpressionValue(id, "id");
                companion.starter(context2, id, movieDetail.poster);
            }

            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void delete() {
                ((FeaturedPresenter) FeaturedFragment.this.mPresenter).deleteHistory(movieDetail.id, 1);
            }

            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
            public void markWatched() {
                ((FeaturedPresenter) FeaturedFragment.this.mPresenter).markWatched(movieDetail.id, 1);
            }
        });
        FragmentManager childFragmentManager2 = getChildFragmentManager();
        Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "getChildFragmentManager(...)");
        choosePlayFragmentNewInstance$default.showAllowingStateLoss(childFragmentManager2, "ChoosePlayFragment");
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initData() {
        FeaturedFragment featuredFragment = this;
        PrivateModeChangedLiveData.INSTANCE.get().observeInFragment(featuredFragment, new FeaturedFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedFragment.initData$lambda$12(this.f$0, (Boolean) obj);
            }
        }));
        OnPlayerFinishLiveData.INSTANCE.get().observeInFragment(featuredFragment, new FeaturedFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedFragment.initData$lambda$13(this.f$0, (Boolean) obj);
            }
        }));
        RefreshWatchingLiveData.INSTANCE.get().observeInFragment(featuredFragment, new FeaturedFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedFragment.initData$lambda$14(this.f$0, (Boolean) obj);
            }
        }));
        RefreshWatchedLiveData.INSTANCE.get().observeInFragment(featuredFragment, new FeaturedFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedFragment.initData$lambda$15(this.f$0, (Boolean) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$12(FeaturedFragment featuredFragment, Boolean bool) {
        featuredFragment.refreshData();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$13(FeaturedFragment featuredFragment, Boolean bool) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false)) {
            ((FeaturedPresenter) featuredFragment.mPresenter).getContinue();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$14(FeaturedFragment featuredFragment, Boolean bool) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false)) {
            ((FeaturedPresenter) featuredFragment.mPresenter).getContinue();
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initData$lambda$15(FeaturedFragment featuredFragment, Boolean bool) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false)) {
            ((FeaturedPresenter) featuredFragment.mPresenter).getContinue();
        }
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void initView() {
        Context context = getContext();
        int iDp2px = context != null ? DensityUtils.dp2px(context, 10.0f) : 0;
        getVerticalGridView().setPadding(iDp2px, iDp2px, iDp2px, iDp2px);
    }

    private final boolean checkChildMode(String contentRating) {
        if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE) || !CommonUtils.isChildModeVideo(contentRating)) {
            return false;
        }
        ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(getContext());
        childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda4
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                FeaturedFragment.checkChildMode$lambda$18(this.f$0);
            }
        });
        childModeHintDialog.show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkChildMode$lambda$18(FeaturedFragment featuredFragment) {
        if (featuredFragment.getActivity() != null) {
            new OpenChildModeDialog().show(featuredFragment.getChildFragmentManager(), "OpenChildModeDialog");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLoginIn(LoginEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, false);
        refreshData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onSignOut(LogoutEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        refreshData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onOpenHideMovieList(OpenHideMovieListEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        refreshData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onBackPressed(OnBackPressedEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (event.getType() == 1) {
            getVerticalGridView().smoothScrollToPosition(0);
        }
    }

    public final void refreshData() {
        RefreshListener refreshListener = this.refreshListener;
        if (refreshListener != null) {
            refreshListener.startRefresh();
        }
        loadData();
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    protected void loadData() {
        FeaturedPresenter featuredPresenter = (FeaturedPresenter) this.mPresenter;
        String str = App.getUserData().uid_v2;
        if (str == null) {
            str = "";
        }
        featuredPresenter.requestData(str);
    }

    private final Clip buildClip(Homelist.Typelist item) {
        return new Clip(item.title, "", "", item.poster, "", "", false, "", item.id, item.id, 0, item.box_type);
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void showVideo(FeaturedDataModel featuredDataModel) {
        String str;
        Intrinsics.checkNotNullParameter(featuredDataModel, "featuredDataModel");
        ((FeaturedPresenter) this.mPresenter).getContinue();
        ((FeaturedPresenter) this.mPresenter).getMaybeLike();
        this.rowAdapter.clear();
        List<Homelist> homelists = featuredDataModel.getHomelists();
        Intrinsics.checkNotNullExpressionValue(homelists, "getHomelists(...)");
        for (Homelist homelist : homelists) {
            if (Intrinsics.areEqual("dayhot", homelist.type) && Build.VERSION.SDK_INT >= 26 && App.isLogin() && Utils.isAppForeground()) {
                ArrayList arrayList = new ArrayList();
                List<Homelist.Typelist> list = homelist.list;
                Intrinsics.checkNotNullExpressionValue(list, "list");
                for (Homelist.Typelist typelist : list) {
                    Intrinsics.checkNotNull(typelist);
                    arrayList.add(buildClip(typelist));
                }
                Playlist playlist = new Playlist(homelist.name, arrayList, this.channelListId);
                if (getContext() != null) {
                    try {
                        Intent intent = new Intent(getContext(), (Class<?>) CreateChannelService.class);
                        intent.putExtra(DealWatchNextService.MEDIA_DATA, playlist);
                        FragmentActivity activity = getActivity();
                        if (activity != null) {
                            activity.startService(intent);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            Context context = getContext();
            FeaturedPresenterSelector featuredPresenterSelector = context != null ? new FeaturedPresenterSelector(context) : null;
            if (homelist.ismore == 1) {
                Homelist.Typelist typelist2 = new Homelist.Typelist();
                typelist2.box_type = homelist.box_type;
                typelist2.id = "0";
                homelist.list.add(typelist2);
            }
            if (homelist.box_type == 2 && ((str = homelist.name) == null || str.length() == 0)) {
                homelist.list.clear();
                homelist.box_type = 5;
                List<AdvertisementModel> advertisementModels = featuredDataModel.getAdvertisementModels();
                Intrinsics.checkNotNullExpressionValue(advertisementModels, "getAdvertisementModels(...)");
                for (AdvertisementModel advertisementModel : advertisementModels) {
                    Homelist.Typelist typelist3 = new Homelist.Typelist();
                    typelist3.poster = advertisementModel.getImg();
                    typelist3.setUrl(advertisementModel.getUrl());
                    typelist3.setType(String.valueOf(advertisementModel.getType()));
                    typelist3.box_type = 5;
                    typelist3.id = String.valueOf(advertisementModel.getId());
                    homelist.list.add(typelist3);
                }
            }
            ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(featuredPresenterSelector);
            List<Homelist.Typelist> list2 = homelist.list;
            Intrinsics.checkNotNullExpressionValue(list2, "list");
            for (Homelist.Typelist typelist4 : list2) {
                String str2 = homelist.name;
                if (str2 == null || str2.length() == 0) {
                    typelist4.viewType = 5;
                    arrayObjectAdapter.add(typelist4);
                } else {
                    typelist4.setType(homelist.type);
                    if (homelist.box_type == 1) {
                        typelist4.viewType = 0;
                        arrayObjectAdapter.add(typelist4);
                    } else if (homelist.box_type == 2) {
                        typelist4.viewType = 1;
                        arrayObjectAdapter.add(typelist4);
                    } else if (homelist.box_type == 4) {
                        typelist4.viewType = 3;
                        arrayObjectAdapter.add(typelist4);
                    } else if (Intrinsics.areEqual(homelist.type, "playlist")) {
                        typelist4.viewType = 4;
                        arrayObjectAdapter.add(typelist4);
                    }
                }
            }
            if (homelist.box_type == 5) {
                this.rowAdapter.add(new ListRow(arrayObjectAdapter));
            } else if (homelist.box_type == 1) {
                this.rowAdapter.add(new ListRow(new HeaderItem(homelist.name), arrayObjectAdapter));
            } else if (homelist.box_type == 2) {
                this.rowAdapter.add(new ListRow(new HeaderItem(homelist.name), arrayObjectAdapter));
            } else if (homelist.box_type == 4) {
                this.rowAdapter.add(new ListRow(new HeaderItem(3L, homelist.name), arrayObjectAdapter));
            } else if (Intrinsics.areEqual(homelist.type, "playlist") && !PrefsUtils.getInstance().getBoolean(Constant.Prefs.HIDE_MOVIE_LIST, false)) {
                this.rowAdapter.add(new ListRow(new HeaderItem(homelist.name), arrayObjectAdapter));
            }
        }
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void showMaybeLikeList(List<? extends Homelist.Typelist> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ArrayList arrayList = new ArrayList(list);
        ArrayList arrayList2 = arrayList;
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            ((Homelist.Typelist) it.next()).viewType = 0;
        }
        if (arrayList.size() >= 10) {
            Homelist.Typelist typelist = new Homelist.Typelist();
            typelist.id = "0";
            typelist.setType("maybe_like");
            typelist.box_type = 1;
            typelist.viewType = 0;
            arrayList.add(typelist);
        }
        int size = this.rowAdapter.size();
        int i = 0;
        while (true) {
            if (i >= size) {
                i = -1;
                break;
            }
            Object obj = this.rowAdapter.get(i);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.leanback.widget.ListRow");
            ObjectAdapter adapter = ((ListRow) obj).getAdapter();
            if (adapter.size() > 0) {
                Object obj2 = adapter.get(0);
                Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type com.movieboxpro.android.model.common.Homelist.Typelist");
                if (Intrinsics.areEqual(((Homelist.Typelist) obj2).getType(), "home_recommend_playlist")) {
                    break;
                }
            }
            i++;
        }
        if (i != -1) {
            HeaderItem headerItem = new HeaderItem("Movies You Might Like");
            Context context = getContext();
            ArrayObjectAdapter arrayObjectAdapter = new ArrayObjectAdapter(context != null ? new FeaturedPresenterSelector(context) : null);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                arrayObjectAdapter.add((Homelist.Typelist) it2.next());
            }
            this.rowAdapter.add(i, new ListRow(headerItem, arrayObjectAdapter));
        }
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void showContinueList(List<? extends Homelist.Typelist> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            ((Homelist.Typelist) it.next()).viewType = 3;
        }
        ArrayList arrayList = new ArrayList(list);
        if (arrayList.size() >= 10) {
            Homelist.Typelist typelist = new Homelist.Typelist();
            typelist.id = "0";
            typelist.setType("continue");
            typelist.viewType = 3;
            arrayList.add(typelist);
        }
        int size = this.rowAdapter.size();
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            Object obj = this.rowAdapter.get(i);
            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.leanback.widget.ListRow");
            ListRow listRow = (ListRow) obj;
            if (listRow.getHeaderItem() != null && listRow.getHeaderItem().getId() == 3) {
                ObjectAdapter adapter = listRow.getAdapter();
                Intrinsics.checkNotNull(adapter, "null cannot be cast to non-null type androidx.leanback.widget.ArrayObjectAdapter");
                ((ArrayObjectAdapter) adapter).setItems(arrayList, null);
            }
            if (i == size) {
                return;
            } else {
                i++;
            }
        }
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void hideLoadView() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedFragment$$ExternalSyntheticLambda7
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.hideLoadingView();
                }
            });
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void showLoading() {
        RefreshListener refreshListener = this.refreshListener;
        if (refreshListener != null) {
            refreshListener.startRefresh();
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment, com.movieboxpro.android.base.mvp.BaseContract.BaseView
    public void hideLoading() {
        RefreshListener refreshListener = this.refreshListener;
        if (refreshListener != null) {
            refreshListener.endRefresh();
        }
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id, int boxType, int currSeason, int currEpisode) {
        Intrinsics.checkNotNullParameter(list, "list");
        ScreenManageActivity.INSTANCE.start(this, list, 100, msg, boxType, id, currSeason, currEpisode);
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.View
    public void deleteComplete() {
        ((FeaturedPresenter) this.mPresenter).getContinue();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onPlayFinish(OnPlayerFinishEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onLowMemory(LowMemoryEvent event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (this.isVisibleToUser) {
            return;
        }
        this.rowAdapter.clear();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && requestCode == 100 && data != null) {
            String stringExtra = data.getStringExtra("id");
            if (stringExtra == null) {
                stringExtra = "";
            }
            ((FeaturedPresenter) this.mPresenter).getPlayPath(stringExtra, data.getIntExtra("boxType", 1), data.getIntExtra(DealWatchNextService.SEASON, 0), data.getIntExtra(DealWatchNextService.EPISODE, 0), false, null, null, 0, 0);
        }
    }

    @Override // com.movieboxpro.android.base.mvp.BaseRowsMvpFragment
    public void onFragmentResumeNoFirst() {
        super.onFragmentResumeNoFirst();
        if (this.rowAdapter.size() != 0 || this.hub.isShowing()) {
            return;
        }
        loadData();
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (EventBus.getDefault().isRegistered(this)) {
            return;
        }
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}
