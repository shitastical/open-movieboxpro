package com.movieboxpro.android.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentManager;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingActivity;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.livedata.RefreshWatchingLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteResponse;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.player.VideoPlayerActivity;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.ActivityDataBindingDelegate;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.fragment.account.ChoosePlayFragment;
import com.movieboxpro.android.view.fragment.account.OpenChildModeDialog;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivityListLayoutBinding;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MoreWatchingActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u000f"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreWatchingActivity;", "Lcom/movieboxpro/android/base/BaseBindingActivity;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/ActivityListLayoutBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/ActivityListLayoutBinding;", "binding$delegate", "Lcom/movieboxpro/android/utils/ActivityDataBindingDelegate;", "initListener", "", "initData", "initView", "MoreWatchingListFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MoreWatchingActivity extends BaseBindingActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(MoreWatchingActivity.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/ActivityListLayoutBinding;", 0))};

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final ActivityDataBindingDelegate binding = new ActivityDataBindingDelegate(ActivityListLayoutBinding.class, this);

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initListener() {
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initView() {
    }

    private final ActivityListLayoutBinding getBinding() {
        return (ActivityListLayoutBinding) this.binding.getValue2((Activity) this, $$delegatedProperties[0]);
    }

    @Override // com.movieboxpro.android.base.BaseBindingActivity
    public void initData() {
        FrameLayout frameLayout = getBinding().frameLayout;
        FragmentUtils.add(getSupportFragmentManager(), new MoreWatchingListFragment(), R.id.frameLayout);
    }

    /* JADX INFO: compiled from: MoreWatchingActivity.kt */
    @Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0014J\b\u0010\t\u001a\u00020\nH\u0014J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\f2\u0006\u0010\r\u001a\u00020\u0003H\u0014J\b\u0010\u0014\u001a\u00020\u0015H\u0014J8\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\nH\u0002J \u0010\u001f\u001a\u00020\u00172\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002JL\u0010#\u001a\u00020\u00172\u0016\u0010$\u001a\u0012\u0012\u0004\u0012\u00020&0%j\b\u0012\u0004\u0012\u00020&`'2\b\u0010(\u001a\u0004\u0018\u00010\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\nH\u0002J\u0012\u0010)\u001a\u00020\u00172\b\u0010*\u001a\u0004\u0018\u00010\bH\u0002J \u0010+\u001a\u00020\u00172\u0006\u0010,\u001a\u00020-2\u0006\u0010\"\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\"\u0010.\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\nH\u0002J*\u0010/\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u00100\u001a\u00020\b2\u0006\u00101\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\nH\u0002J\"\u00102\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\nH\u0002J\u0010\u00103\u001a\u00020\u001d2\u0006\u00104\u001a\u00020\bH\u0002J\b\u00105\u001a\u00020\nH\u0014J\b\u00106\u001a\u000207H\u0014J\u0012\u00108\u001a\u00020\u00172\b\u00109\u001a\u0004\u0018\u00010:H\u0014J\u0018\u0010;\u001a\u00020\u00172\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0002H\u0014R\u000e\u0010\u000e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/movieboxpro/android/view/activity/MoreWatchingActivity$MoreWatchingListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/FavoriteItem;", "Lcom/movieboxpro/android/model/FavoriteResponse;", "<init>", "()V", "getServiceData", "Lio/reactivex/Observable;", "", "initItemLayout", "", "getData", "", "model", "lastId", "lastBoxType", "lastClickTime", "", "lastDisposable", "Lio/reactivex/disposables/Disposable;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "getPlayPath", "", "id", "boxType", "currSeason", "currEpisode", "playImmediately", "", "itemPos", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "position", "showScreenManage", "list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "playWithOtherPlayer", "url", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "markWatched", "markEpisode", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "cancelWatch", "checkChildMode", "contentRating", "gridLayoutSpan", "onLongClick", "Lcom/chad/library/adapter/base/listener/OnItemLongClickListener;", "getBundle", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class MoreWatchingListFragment extends BaseListFragment<FavoriteItem, FavoriteResponse> {
        private long lastClickTime;
        private Disposable lastDisposable;
        private String lastId = "";
        private int lastBoxType = -1;

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            return 6;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.INSTANCE.post("User_watched_plan_list_v2").params(Api.INSTANCE.getCollectList("0", "", "", "", "0", "", "0", this.mCurrentPage, this.mPageSize, 0)).asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_waiting_item;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<FavoriteItem> getData(FavoriteResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ArrayList<FavoriteItem> list = model.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda19
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    MoreWatchingActivity.MoreWatchingListFragment.onItemClick$lambda$2(this.f$0, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public static final void onItemClick$lambda$2(final MoreWatchingListFragment moreWatchingListFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            final FavoriteItem favoriteItem = (FavoriteItem) moreWatchingListFragment.mAdapter.getItem(i);
            Disposable disposable = moreWatchingListFragment.lastDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            if (System.currentTimeMillis() - moreWatchingListFragment.lastClickTime < 500 && Intrinsics.areEqual(favoriteItem.getId(), moreWatchingListFragment.lastId) && favoriteItem.getBox_type() == moreWatchingListFragment.lastBoxType) {
                String content_rating = favoriteItem.getContent_rating();
                if (content_rating == null) {
                    content_rating = "";
                }
                if (!moreWatchingListFragment.checkChildMode(content_rating)) {
                    String id = favoriteItem.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    int box_type = favoriteItem.getBox_type();
                    FavoriteItem.LastEpisode last_episode = favoriteItem.getLast_episode();
                    int season = last_episode != null ? last_episode.getSeason() : 0;
                    FavoriteItem.LastEpisode last_episode2 = favoriteItem.getLast_episode();
                    moreWatchingListFragment.getPlayPath(id, box_type, season, last_episode2 != null ? last_episode2.getEpisode() : 0, true, i);
                }
            } else {
                Object objAs = Observable.timer(600L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(moreWatchingListFragment));
                Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MoreWatchingActivity.MoreWatchingListFragment.onItemClick$lambda$2$lambda$0(this.f$0, (Disposable) obj);
                    }
                }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda5
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MoreWatchingActivity.MoreWatchingListFragment.onItemClick$lambda$2$lambda$1(this.f$0, favoriteItem, i, (Long) obj);
                    }
                }, 11, null);
            }
            moreWatchingListFragment.lastClickTime = System.currentTimeMillis();
            String id2 = favoriteItem.getId();
            moreWatchingListFragment.lastId = id2 != null ? id2 : "";
            moreWatchingListFragment.lastBoxType = favoriteItem.getBox_type();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onItemClick$lambda$2$lambda$1(MoreWatchingListFragment moreWatchingListFragment, FavoriteItem favoriteItem, int i, Long l) {
            String content_rating = favoriteItem.getContent_rating();
            if (content_rating == null) {
                content_rating = "";
            }
            if (!moreWatchingListFragment.checkChildMode(content_rating)) {
                String id = favoriteItem.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                int box_type = favoriteItem.getBox_type();
                FavoriteItem.LastEpisode last_episode = favoriteItem.getLast_episode();
                int season = last_episode != null ? last_episode.getSeason() : 0;
                FavoriteItem.LastEpisode last_episode2 = favoriteItem.getLast_episode();
                moreWatchingListFragment.getPlayPath(id, box_type, season, last_episode2 != null ? last_episode2.getEpisode() : 0, false, i);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onItemClick$lambda$2$lambda$0(MoreWatchingListFragment moreWatchingListFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.lastDisposable = it;
            return Unit.INSTANCE;
        }

        private final void getPlayPath(final String id, final int boxType, final int currSeason, final int currEpisode, final boolean playImmediately, final int itemPos) {
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = 1;
            Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, id, SystemUtils.getUniqueId(App.getContext()), boxType, currSeason, currEpisode, Build.BRAND, Build.MODEL);
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.getPlayPath$lambda$3(this.f$0, id, boxType, currSeason, currEpisode, (String) obj);
                }
            };
            Observable observableOnErrorResumeNext = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda7
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.getPlayPath$lambda$4(function1, obj);
                }
            }).onErrorResumeNext((Function<? super Throwable, ? extends ObservableSource<? extends R>>) new Function() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda8
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.getPlayPath$lambda$5((Throwable) obj);
                }
            });
            final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.getPlayPath$lambda$7(boxType, id, intRef, currEpisode, currSeason, (String) obj);
                }
            };
            ((ObservableSubscribeProxy) observableOnErrorResumeNext.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda10
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.getPlayPath$lambda$8(function2, obj);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<? extends BaseMediaModel, ? extends BaseMediaModel>>() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$4
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable disposable) {
                    Intrinsics.checkNotNullParameter(disposable, "disposable");
                    this.this$0.showLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(Pair<? extends BaseMediaModel, ? extends BaseMediaModel> model) {
                    TvDetail.SeasonDetail seasonDetail;
                    Intrinsics.checkNotNullParameter(model, "model");
                    if (model.getFirst() instanceof TvDetail) {
                        BaseMediaModel first = model.getFirst();
                        Intrinsics.checkNotNull(first, "null cannot be cast to non-null type com.movieboxpro.android.model.tv.TvDetail");
                        TvDetail tvDetail = (TvDetail) first;
                        tvDetail.addDonwload(model.getSecond());
                        List<BaseMediaModel.DownloadFile> list = tvDetail.list;
                        if (list != null && !list.isEmpty()) {
                            Ref.IntRef intRef2 = intRef;
                            List<TvDetail.SeasonDetail> episodeList = tvDetail.episodeList;
                            Intrinsics.checkNotNullExpressionValue(episodeList, "episodeList");
                            Iterator<T> it = episodeList.iterator();
                            do {
                                if (!it.hasNext()) {
                                    seasonDetail = null;
                                    break;
                                }
                                seasonDetail = (TvDetail.SeasonDetail) it.next();
                            } while (seasonDetail.episode != intRef2.element);
                            if (seasonDetail != null) {
                                tvDetail.seasonDetail = seasonDetail;
                                this.this$0.goTvPlayer(tvDetail, itemPos, playImmediately);
                            } else {
                                ToastUtils.showShort("Episode " + intRef.element + ":not available", new Object[0]);
                            }
                        } else {
                            ToastUtils.showShort("Not Available", new Object[0]);
                        }
                    } else if (model.getFirst() instanceof MovieDetail) {
                        BaseMediaModel first2 = model.getFirst();
                        Intrinsics.checkNotNull(first2, "null cannot be cast to non-null type com.movieboxpro.android.model.movie.MovieDetail");
                        MovieDetail movieDetail = (MovieDetail) first2;
                        movieDetail.addDonwload(model.getSecond());
                        List<BaseMediaModel.DownloadFile> list2 = movieDetail.list;
                        if (list2 != null && !list2.isEmpty()) {
                            this.this$0.goMoviePlayer(movieDetail, itemPos, playImmediately);
                        } else {
                            ToastUtils.showShort("Not Available", new Object[0]);
                        }
                    }
                    this.this$0.hideLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onError(ApiException e) {
                    ToastUtils.showShort("Load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
                    this.this$0.hideLoadingView();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ObservableSource getPlayPath$lambda$4(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (ObservableSource) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ObservableSource getPlayPath$lambda$3(MoreWatchingListFragment moreWatchingListFragment, String str, int i, int i2, int i3, String it) {
            Observable observableJust;
            Intrinsics.checkNotNullParameter(it, "it");
            Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
            Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
            BaseResponse baseResponse = (BaseResponse) object;
            if (baseResponse.getCode() == -88) {
                moreWatchingListFragment.hideLoadingView();
                List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
                moreWatchingListFragment.showScreenManage(new ArrayList<>(device_list != null ? device_list : new ArrayList()), baseResponse.getMsg(), str, i, i2, i3);
                observableJust = Observable.empty();
            } else {
                observableJust = Observable.just("");
            }
            return observableJust;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Observable getPlayPath$lambda$5(Throwable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return Observable.just("");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ObservableSource getPlayPath$lambda$8(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (ObservableSource) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ObservableSource getPlayPath$lambda$7(int i, String str, Ref.IntRef intRef, int i2, int i3, String it) {
            Observable observableCompose;
            String str2;
            String str3;
            Intrinsics.checkNotNullParameter(it, "it");
            if (i == 1) {
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
                    str3 = "";
                    str2 = str3;
                } else {
                    str2 = string;
                    str3 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                }
                observableCompose = Http.getService().Movie_detail(API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", str, "", str3, str2).compose(RxUtils.rxTranslate2Bean(MovieDetail.class));
            } else {
                observableCompose = Http.getService().Tv_detail(API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", str, "", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class));
            }
            final MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1 moreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1 = new MoreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1(intRef, i2, i3);
            return observableCompose.flatMap(new Function() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda18
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.getPlayPath$lambda$7$lambda$6(moreWatchingActivity$MoreWatchingListFragment$getPlayPath$3$1, obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ObservableSource getPlayPath$lambda$7$lambda$6(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (ObservableSource) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void goMoviePlayer(final MovieDetail movieDetail, final int position, boolean playImmediately) {
            if (playImmediately) {
                Context context = getContext();
                if (context != null) {
                    VideoPlayerActivity.start(context, movieDetail, movieDetail.id);
                    return;
                }
                return;
            }
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
                ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
                choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$goMoviePlayer$2
                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void playWithOther() {
                        ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = ChoosePlayerQualityFragment.INSTANCE.newInstance(new ArrayList<>(movieDetail.list));
                        final MoreWatchingActivity.MoreWatchingListFragment moreWatchingListFragment = this;
                        choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$goMoviePlayer$2$playWithOther$1
                            @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                            public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position2) {
                                Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                                moreWatchingListFragment.playWithOtherPlayer(downloadUrl.path);
                            }
                        });
                        FragmentManager childFragmentManager = this.getChildFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                        choosePlayerQualityFragmentNewInstance.showAllowingStateLoss(childFragmentManager, "ChoosePlayerQualityFragment");
                    }

                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void play() {
                        ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 1, false, 2, null);
                        final MoreWatchingActivity.MoreWatchingListFragment moreWatchingListFragment = this;
                        final MovieDetail movieDetail2 = movieDetail;
                        final int i = position;
                        choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$goMoviePlayer$2$play$1
                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void chooseResume() {
                                Context context2 = moreWatchingListFragment.getContext();
                                if (context2 != null) {
                                    MovieDetail movieDetail3 = movieDetail2;
                                    VideoPlayerActivity.start(context2, movieDetail3, movieDetail3.id);
                                }
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void chooseRestart() {
                                Context context2 = moreWatchingListFragment.getContext();
                                if (context2 != null) {
                                    MovieDetail movieDetail3 = movieDetail2;
                                    VideoPlayerActivity.start(context2, movieDetail3, movieDetail3.id, true);
                                }
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void goDetail() {
                                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                                Context context2 = moreWatchingListFragment.getContext();
                                String id = movieDetail2.id;
                                Intrinsics.checkNotNullExpressionValue(id, "id");
                                companion.starter(context2, id, movieDetail2.poster);
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void delete() {
                                moreWatchingListFragment.cancelWatch(movieDetail2.id, 1, i);
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void markWatched() {
                                moreWatchingListFragment.markWatched(movieDetail2.id, 1, i);
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
            ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 1, false, 2, null);
            choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$goMoviePlayer$3
                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseResume() {
                    Context context2 = this.this$0.getContext();
                    if (context2 != null) {
                        MovieDetail movieDetail2 = movieDetail;
                        VideoPlayerActivity.start(context2, movieDetail2, movieDetail2.id);
                    }
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseRestart() {
                    Context context2 = this.this$0.getContext();
                    if (context2 != null) {
                        MovieDetail movieDetail2 = movieDetail;
                        VideoPlayerActivity.start(context2, movieDetail2, movieDetail2.id, true);
                    }
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void goDetail() {
                    MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                    Context context2 = this.this$0.getContext();
                    String id = movieDetail.id;
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    companion.starter(context2, id, movieDetail.poster);
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void delete() {
                    this.this$0.cancelWatch(movieDetail.id, 1, position);
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void markWatched() {
                    this.this$0.markWatched(movieDetail.id, 1, position);
                }
            });
            FragmentManager childFragmentManager2 = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "getChildFragmentManager(...)");
            choosePlayFragmentNewInstance$default.showAllowingStateLoss(childFragmentManager2, "ChoosePlayFragment");
        }

        private final void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id, int boxType, int currSeason, int currEpisode) {
            ScreenManageActivity.INSTANCE.start(this, list, 100, msg, boxType, id, currSeason, currEpisode);
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

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v0, types: [T, java.util.ArrayList] */
        public final void goTvPlayer(final TvDetail tvDetail, final int position, boolean playImmediately) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = new ArrayList();
            if (tvDetail.episodeList.size() < 100) {
                List<TvDetail.SeasonDetail> episodeList = tvDetail.episodeList;
                Intrinsics.checkNotNullExpressionValue(episodeList, "episodeList");
                for (TvDetail.SeasonDetail seasonDetail : episodeList) {
                    TvSeasonList tvSeasonList = new TvSeasonList();
                    tvSeasonList.setEpisode(seasonDetail.episode);
                    tvSeasonList.setImdbId(seasonDetail.imdb_id);
                    tvSeasonList.setSeason(seasonDetail.season);
                    tvSeasonList.setTid(seasonDetail.tid);
                    tvSeasonList.setId(seasonDetail.id);
                    Integer num = seasonDetail.play_progress.get("over");
                    tvSeasonList.setOver(num != null ? num.intValue() : 0);
                    Integer num2 = seasonDetail.play_progress.get("seconds");
                    tvSeasonList.setSeconds(num2 != null ? num2.intValue() : 0);
                    ArrayList arrayList = (ArrayList) objectRef.element;
                    if (arrayList != null) {
                        arrayList.add(tvSeasonList);
                    }
                }
            } else {
                objectRef.element = null;
            }
            if (playImmediately) {
                Context context = getContext();
                if (context != null) {
                    VideoPlayer.start(context, tvDetail, tvDetail.seasonDetail.season, tvDetail.seasonDetail.episode, false, (ArrayList) objectRef.element, false, true);
                    return;
                }
                return;
            }
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
                ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
                choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$goTvPlayer$3
                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void playWithOther() {
                        ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = ChoosePlayerQualityFragment.INSTANCE.newInstance(new ArrayList<>(tvDetail.list));
                        final MoreWatchingActivity.MoreWatchingListFragment moreWatchingListFragment = this;
                        choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$goTvPlayer$3$playWithOther$1
                            @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                            public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position2) {
                                Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                                moreWatchingListFragment.playWithOtherPlayer(downloadUrl.path);
                            }
                        });
                        FragmentManager childFragmentManager = this.getChildFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                        choosePlayerQualityFragmentNewInstance.showAllowingStateLoss(childFragmentManager, "ChoosePlayerQualityFragment");
                    }

                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void play() {
                        ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 2, false, 2, null);
                        final MoreWatchingActivity.MoreWatchingListFragment moreWatchingListFragment = this;
                        final TvDetail tvDetail2 = tvDetail;
                        final Ref.ObjectRef<ArrayList<TvSeasonList>> objectRef2 = objectRef;
                        final int i = position;
                        choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$goTvPlayer$3$play$1
                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void chooseResume() {
                                Context context2 = moreWatchingListFragment.getContext();
                                if (context2 != null) {
                                    TvDetail tvDetail3 = tvDetail2;
                                    VideoPlayer.start(context2, tvDetail3, tvDetail3.seasonDetail.season, tvDetail3.seasonDetail.episode, false, objectRef2.element, false, true);
                                }
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void chooseRestart() {
                                Context context2 = moreWatchingListFragment.getContext();
                                if (context2 != null) {
                                    TvDetail tvDetail3 = tvDetail2;
                                    VideoPlayer.start(context2, tvDetail3, tvDetail3.seasonDetail.season, tvDetail3.seasonDetail.episode, false, objectRef2.element, true, true);
                                }
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void goDetail() {
                                TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
                                Context context2 = moreWatchingListFragment.getContext();
                                String id = tvDetail2.id;
                                Intrinsics.checkNotNullExpressionValue(id, "id");
                                String poster = tvDetail2.poster;
                                Intrinsics.checkNotNullExpressionValue(poster, "poster");
                                companion.starter(context2, id, poster);
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void delete() {
                                moreWatchingListFragment.cancelWatch(tvDetail2.id, 2, i);
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void markWatched() {
                                FavoriteItem favoriteItem = (FavoriteItem) moreWatchingListFragment.mAdapter.getItem(i);
                                if (favoriteItem.getLast_episode() == null) {
                                    moreWatchingListFragment.markWatched(tvDetail2.id, 2, i);
                                } else {
                                    moreWatchingListFragment.markEpisode(tvDetail2.id, String.valueOf(favoriteItem.getLast_episode().getSeason()), String.valueOf(favoriteItem.getLast_episode().getEpisode()), i);
                                }
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
            ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 2, false, 2, null);
            choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$goTvPlayer$4
                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseResume() {
                    Context context2 = this.this$0.getContext();
                    if (context2 != null) {
                        TvDetail tvDetail2 = tvDetail;
                        VideoPlayer.start(context2, tvDetail2, tvDetail2.seasonDetail.season, tvDetail2.seasonDetail.episode, false, objectRef.element, false, true);
                    }
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseRestart() {
                    Context context2 = this.this$0.getContext();
                    if (context2 != null) {
                        TvDetail tvDetail2 = tvDetail;
                        VideoPlayer.start(context2, tvDetail2, tvDetail2.seasonDetail.season, tvDetail2.seasonDetail.episode, false, objectRef.element, true, true);
                    }
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void goDetail() {
                    TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
                    Context context2 = this.this$0.getContext();
                    String id = tvDetail.id;
                    Intrinsics.checkNotNullExpressionValue(id, "id");
                    String poster = tvDetail.poster;
                    Intrinsics.checkNotNullExpressionValue(poster, "poster");
                    companion.starter(context2, id, poster);
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void delete() {
                    this.this$0.cancelWatch(tvDetail.id, 2, position);
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void markWatched() {
                    FavoriteItem favoriteItem = (FavoriteItem) this.this$0.mAdapter.getItem(position);
                    if (favoriteItem.getLast_episode() == null) {
                        this.this$0.markWatched(tvDetail.id, 2, position);
                    } else {
                        this.this$0.markEpisode(tvDetail.id, String.valueOf(favoriteItem.getLast_episode().getSeason()), String.valueOf(favoriteItem.getLast_episode().getEpisode()), position);
                    }
                }
            });
            FragmentManager childFragmentManager2 = getChildFragmentManager();
            Intrinsics.checkNotNullExpressionValue(childFragmentManager2, "getChildFragmentManager(...)");
            choosePlayFragmentNewInstance$default.showAllowingStateLoss(childFragmentManager2, "ChoosePlayFragment");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markWatched(String id, int boxType, final int position) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_mark").param("box_type", Integer.valueOf(boxType)).param("mid", id).param("watched", (Object) 1).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.markWatched$lambda$12(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.markWatched$lambda$13(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.markWatched$lambda$14(this.f$0, position, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markWatched$lambda$13(MoreWatchingListFragment moreWatchingListFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public static final Unit markWatched$lambda$14(MoreWatchingListFragment moreWatchingListFragment, int i, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.hideLoadingView();
            RefreshWatchingLiveData.INSTANCE.get().setValue(true);
            RefreshWatchedLiveData.INSTANCE.get().setValue(true);
            moreWatchingListFragment.mAdapter.removeAt(i);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markWatched$lambda$12(MoreWatchingListFragment moreWatchingListFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.hideLoadingView();
            ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markEpisode(String id, String season, String episode, final int position) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("TV_over_v2").param("over", (Object) 1).param("tid", id).param(DealWatchNextService.SEASON, season).param(DealWatchNextService.EPISODE, episode).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.markEpisode$lambda$15(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.markEpisode$lambda$16(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.markEpisode$lambda$17(this.f$0, position, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markEpisode$lambda$16(MoreWatchingListFragment moreWatchingListFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public static final Unit markEpisode$lambda$17(MoreWatchingListFragment moreWatchingListFragment, int i, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.hideLoadingView();
            RefreshWatchingLiveData.INSTANCE.get().setValue(true);
            RefreshWatchedLiveData.INSTANCE.get().setValue(true);
            moreWatchingListFragment.mAdapter.removeAt(i);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markEpisode$lambda$15(MoreWatchingListFragment moreWatchingListFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.hideLoadingView();
            ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void cancelWatch(String id, int boxType, final int position) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_del").param("mid", id).param("box_type", Integer.valueOf(boxType)).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.cancelWatch$lambda$18(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.cancelWatch$lambda$19(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoreWatchingActivity.MoreWatchingListFragment.cancelWatch$lambda$20(this.f$0, position, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit cancelWatch$lambda$19(MoreWatchingListFragment moreWatchingListFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public static final Unit cancelWatch$lambda$20(MoreWatchingListFragment moreWatchingListFragment, int i, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.hideLoadingView();
            RefreshWatchingLiveData.INSTANCE.get().setValue(true);
            moreWatchingListFragment.mAdapter.removeAt(i);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit cancelWatch$lambda$18(MoreWatchingListFragment moreWatchingListFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            moreWatchingListFragment.hideLoadingView();
            ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        private final boolean checkChildMode(String contentRating) {
            if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE) || !CommonUtils.isChildModeVideo(contentRating)) {
                return false;
            }
            ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(getContext());
            childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda0
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    MoreWatchingActivity.MoreWatchingListFragment.checkChildMode$lambda$22(this.f$0);
                }
            });
            childModeHintDialog.show();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void checkChildMode$lambda$22(MoreWatchingListFragment moreWatchingListFragment) {
            if (moreWatchingListFragment.getActivity() != null) {
                new OpenChildModeDialog().show(moreWatchingListFragment.getChildFragmentManager(), "OpenChildModeDialog");
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemLongClickListener onLongClick() {
            return new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.activity.MoreWatchingActivity$MoreWatchingListFragment$$ExternalSyntheticLambda11
                @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
                public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    return MoreWatchingActivity.MoreWatchingListFragment.onLongClick$lambda$23(this.f$0, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        public static final boolean onLongClick$lambda$23(MoreWatchingListFragment moreWatchingListFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            FavoriteItem favoriteItem = (FavoriteItem) moreWatchingListFragment.mAdapter.getItem(i);
            if (favoriteItem.getBox_type() == 1) {
                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                Context context = moreWatchingListFragment.getContext();
                String id = favoriteItem.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                companion.starter(context, id, favoriteItem.getPoster());
            } else {
                TvDetailActivity.Companion companion2 = TvDetailActivity.INSTANCE;
                Context context2 = moreWatchingListFragment.getContext();
                String id2 = favoriteItem.getId();
                Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
                String poster = favoriteItem.getPoster();
                Intrinsics.checkNotNullExpressionValue(poster, "getPoster(...)");
                companion2.starter(context2, id2, poster);
            }
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            this.mClass = FavoriteItem.class;
            this.mPageClass = FavoriteResponse.class;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, FavoriteItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            TextView textView = (TextView) helper.getView(R.id.tvSeasonEpisode);
            TextView textView2 = (TextView) helper.getView(R.id.tvTomatoMeter);
            ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
            ImageView imageView2 = (ImageView) helper.getView(R.id.ivTomato);
            TextView textView3 = (TextView) helper.getView(R.id.tvUpdateCount);
            ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progressBar);
            ImageView imageView3 = (ImageView) helper.getView(R.id.ivPoster);
            TextView textView4 = (TextView) helper.getView(R.id.tvTitle);
            CommonExtKt.gone(textView2);
            CommonExtKt.gone(imageView2);
            if (item.getBox_type() == 1) {
                CommonExtKt.gone(textView);
                CommonExtKt.gone(textView3);
                CommonExtKt.visible(imageView);
                imageView.setImageResource(CommonUtils.getMovieTag(item.getQuality_tag_new()));
                if (item.getWaiting() == 1) {
                    CommonExtKt.gone(progressBar);
                } else {
                    progressBar.setMax(item.getRuntime() * 60);
                    progressBar.setProgress(item.getSeconds());
                    CommonExtKt.visible(progressBar);
                }
            } else {
                CommonExtKt.gone(textView);
                TextView textView5 = textView3;
                CommonExtKt.visible(textView5);
                CommonExtKt.gone(imageView);
                if (item.getUpdateCount() == 0) {
                    CommonExtKt.gone(textView5);
                } else {
                    CommonExtKt.visible(textView5);
                    textView3.setText(String.valueOf(item.getUpdateCount()));
                }
                CommonExtKt.gone(progressBar);
                FavoriteItem.LastEpisode last_episode = item.getLast_episode();
                progressBar.setMax((last_episode != null ? last_episode.getRuntime() : 0) * 60);
                FavoriteItem.LastEpisode last_episode2 = item.getLast_episode();
                progressBar.setProgress(last_episode2 != null ? last_episode2.getSeconds() : 0);
            }
            if (item.getLast_episode() != null) {
                textView4.setText(ExifInterface.LATITUDE_SOUTH + item.getLast_episode().getSeason() + ExifInterface.LONGITUDE_EAST + item.getLast_episode().getEpisode() + "-" + item.getLast_episode().getTitle());
            } else {
                textView4.setText(item.getTitle());
            }
            GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), imageView3, 8);
            helper.setGone(R.id.ivStar, true);
        }
    }
}
