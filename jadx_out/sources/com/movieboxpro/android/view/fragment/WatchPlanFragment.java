package com.movieboxpro.android.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.delegate.BaseMultiTypeDelegate;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.listener.OnItemLongClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.adapter.WatchingAdapter;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseBindingFragment;
import com.movieboxpro.android.base.BaseListFragment;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.FocusUpListener;
import com.movieboxpro.android.livedata.FocusWatchingLiveData;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.livedata.RefreshWatchingLiveData;
import com.movieboxpro.android.livedata.SwitchAccountLiveData;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.BeanKt;
import com.movieboxpro.android.model.CountryResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.FavoriteItem;
import com.movieboxpro.android.model.FavoriteResponse;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.SpecialFilterModel;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.WatchedEpisode;
import com.movieboxpro.android.model.WatchedItem;
import com.movieboxpro.android.model.WatchedResponse;
import com.movieboxpro.android.model.WatchingItem;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.player.VideoPlayerActivity;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.tv.ScreenManageActivity;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.Api;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.GlideUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.activity.MoreWatchingActivity;
import com.movieboxpro.android.view.dialog.ChildModeHintDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerDialog;
import com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment;
import com.movieboxpro.android.view.fragment.account.ChoosePlayFragment;
import com.movieboxpro.android.view.fragment.account.OpenChildModeDialog;
import com.movieboxpro.android.view.fragment.favorite.FavoriteFragment;
import com.movieboxpro.android.view.widget.LinearItemDecoration;
import com.movieboxpro.android.view.widget.MyView.SlantedTextView;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentWatchPlanBinding;
import com.owen.focus.FocusBorder;
import com.owen.tab.TvTabLayout;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.owen.tvrecyclerview.widget.V7GridLayoutManager;
import com.owen.tvrecyclerview.widget.V7LinearLayoutManager;
import com.snowtop.diskpanda.utils.databinding.FragmentBindingDelegate;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: WatchPlanFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001:\u0003%&'B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u001d\u001a\u00020\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u001eH\u0016J\b\u0010!\u001a\u00020\u001eH\u0002J\b\u0010\"\u001a\u00020\u001eH\u0016J\b\u0010#\u001a\u00020\u001eH\u0016J\b\u0010$\u001a\u00020\u001eH\u0002R\u001b\u0010\u0004\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0015j\b\u0012\u0004\u0012\u00020\u0016`\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0018\u001a\u0012\u0012\u0004\u0012\u00020\u00190\u0015j\b\u0012\u0004\u0012\u00020\u0019`\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/movieboxpro/android/view/fragment/WatchPlanFragment;", "Lcom/movieboxpro/android/base/BaseBindingFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentWatchPlanBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/FragmentWatchPlanBinding;", "binding$delegate", "Lcom/snowtop/diskpanda/utils/databinding/FragmentBindingDelegate;", "waitingFragment", "Lcom/movieboxpro/android/view/fragment/WatchPlanFragment$WaitingFragment;", "watchedFragment", "Lcom/movieboxpro/android/view/fragment/WatchPlanFragment$WatchedFragment;", "favoriteFragment", "Lcom/movieboxpro/android/view/fragment/favorite/FavoriteFragment;", "recommendFragment", "Lcom/movieboxpro/android/view/fragment/WatchPlanFragment$RecommendListFragment;", "lastSaveSelectPos", "", "geners", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/common/Gener;", "Lkotlin/collections/ArrayList;", "countryList", "Lcom/movieboxpro/android/model/FilterCountry;", "filterFragment", "Lcom/movieboxpro/android/view/dialog/FilterFavoriteDialogFragment;", "watchFilterFragment", "openFilter", "", "requestCountry", "initListener", "hideAllFragment", "initView", "initData", "requestGener", "RecommendListFragment", "WaitingFragment", "WatchedFragment", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WatchPlanFragment extends BaseBindingFragment {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(WatchPlanFragment.class, "binding", "getBinding()Lcom/movieboxpro/androidtv/databinding/FragmentWatchPlanBinding;", 0))};

    /* JADX INFO: renamed from: binding$delegate, reason: from kotlin metadata */
    private final FragmentBindingDelegate binding;
    private ArrayList<FilterCountry> countryList;
    private FavoriteFragment favoriteFragment;
    private FilterFavoriteDialogFragment filterFragment;
    private ArrayList<Gener> geners;
    private int lastSaveSelectPos;
    private RecommendListFragment recommendFragment;
    private WaitingFragment waitingFragment;
    private FilterFavoriteDialogFragment watchFilterFragment;
    private WatchedFragment watchedFragment;

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initView() {
    }

    public WatchPlanFragment() {
        super(R.layout.fragment_watch_plan);
        this.binding = new FragmentBindingDelegate(FragmentWatchPlanBinding.class, this);
        this.geners = new ArrayList<>();
        this.countryList = new ArrayList<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentWatchPlanBinding getBinding() {
        return (FragmentWatchPlanBinding) this.binding.getValue2((Fragment) this, $$delegatedProperties[0]);
    }

    private final void openFilter() {
        if (getBinding().tabLayout.getSelectedTabPosition() == 0) {
            FilterFavoriteDialogFragment filterFavoriteDialogFragment = this.watchFilterFragment;
            if (filterFavoriteDialogFragment != null) {
                if (filterFavoriteDialogFragment != null) {
                    filterFavoriteDialogFragment.show(getChildFragmentManager(), "FilterDialogFragment");
                    return;
                }
                return;
            }
            FilterFavoriteDialogFragment filterFavoriteDialogFragment2 = new FilterFavoriteDialogFragment();
            this.watchFilterFragment = filterFavoriteDialogFragment2;
            filterFavoriteDialogFragment2.setGener(this.geners);
            FilterFavoriteDialogFragment filterFavoriteDialogFragment3 = this.watchFilterFragment;
            Intrinsics.checkNotNull(filterFavoriteDialogFragment3);
            filterFavoriteDialogFragment3.setCountry(this.countryList);
            FilterFavoriteDialogFragment filterFavoriteDialogFragment4 = this.watchFilterFragment;
            if (filterFavoriteDialogFragment4 != null) {
                filterFavoriteDialogFragment4.setOnListener(new FilterFavoriteDialogFragment.OnFilterDoneListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment.openFilter.1
                    @Override // com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.OnFilterDoneListener
                    public void onFilterDone(String boxType, String sort, String year, String gener, String rating, String quality, String country) {
                        Intrinsics.checkNotNullParameter(boxType, "boxType");
                        Intrinsics.checkNotNullParameter(sort, "sort");
                        Intrinsics.checkNotNullParameter(year, "year");
                        Intrinsics.checkNotNullParameter(gener, "gener");
                        Intrinsics.checkNotNullParameter(rating, "rating");
                        Intrinsics.checkNotNullParameter(quality, "quality");
                        Intrinsics.checkNotNullParameter(country, "country");
                        WaitingFragment waitingFragment = WatchPlanFragment.this.waitingFragment;
                        if (waitingFragment != null) {
                            waitingFragment.setFilter(boxType, year, gener, sort, rating, quality, country);
                        }
                    }
                });
            }
            FilterFavoriteDialogFragment filterFavoriteDialogFragment5 = this.watchFilterFragment;
            if (filterFavoriteDialogFragment5 != null) {
                filterFavoriteDialogFragment5.show(getChildFragmentManager(), "FilterDialogFragment");
                return;
            }
            return;
        }
        if (getBinding().tabLayout.getSelectedTabPosition() == 2) {
            FilterFavoriteDialogFragment filterFavoriteDialogFragment6 = this.filterFragment;
            if (filterFavoriteDialogFragment6 != null) {
                if (filterFavoriteDialogFragment6 != null) {
                    filterFavoriteDialogFragment6.show(getChildFragmentManager(), "FilterDialogFragment");
                    return;
                }
                return;
            }
            FilterFavoriteDialogFragment filterFavoriteDialogFragment7 = new FilterFavoriteDialogFragment();
            this.filterFragment = filterFavoriteDialogFragment7;
            filterFavoriteDialogFragment7.setGener(this.geners);
            FilterFavoriteDialogFragment filterFavoriteDialogFragment8 = this.filterFragment;
            Intrinsics.checkNotNull(filterFavoriteDialogFragment8);
            filterFavoriteDialogFragment8.setCountry(this.countryList);
            FilterFavoriteDialogFragment filterFavoriteDialogFragment9 = this.filterFragment;
            if (filterFavoriteDialogFragment9 != null) {
                filterFavoriteDialogFragment9.setOnListener(new FilterFavoriteDialogFragment.OnFilterDoneListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment.openFilter.2
                    @Override // com.movieboxpro.android.view.dialog.FilterFavoriteDialogFragment.OnFilterDoneListener
                    public void onFilterDone(String boxType, String sort, String year, String gener, String rating, String quality, String country) {
                        Intrinsics.checkNotNullParameter(boxType, "boxType");
                        Intrinsics.checkNotNullParameter(sort, "sort");
                        Intrinsics.checkNotNullParameter(year, "year");
                        Intrinsics.checkNotNullParameter(gener, "gener");
                        Intrinsics.checkNotNullParameter(rating, "rating");
                        Intrinsics.checkNotNullParameter(quality, "quality");
                        Intrinsics.checkNotNullParameter(country, "country");
                        FavoriteFragment favoriteFragment = WatchPlanFragment.this.favoriteFragment;
                        if (favoriteFragment != null) {
                            favoriteFragment.setFilter(boxType, year, gener, sort, rating, quality, country);
                        }
                    }
                });
            }
            FilterFavoriteDialogFragment filterFavoriteDialogFragment10 = this.filterFragment;
            if (filterFavoriteDialogFragment10 != null) {
                filterFavoriteDialogFragment10.show(getChildFragmentManager(), "FilterDialogFragment");
            }
        }
    }

    private final void requestCountry() {
        final String string = PrefsUtils.getInstance().getString(Constant.Prefs.MOVIE_COUNTRY);
        if (string != null) {
            if (this.countryList.isEmpty()) {
                Observable<R> observableCompose = HttpRequest.INSTANCE.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
                Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
                final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WatchPlanFragment.requestCountry$lambda$5((String) obj);
                    }
                };
                Object objAs = observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda12
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return WatchPlanFragment.requestCountry$lambda$6(function1, obj);
                    }
                }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WatchPlanFragment.requestCountry$lambda$7((PrefsUtils) obj);
                    }
                }, 15, null);
            }
            Observable observableJust = Observable.just(string);
            final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.requestCountry$lambda$8(this.f$0, string, (String) obj);
                }
            };
            Object objAs2 = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return WatchPlanFragment.requestCountry$lambda$9(function2, obj);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(objAs2, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs2, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.requestCountry$lambda$10(this.f$0, (Unit) obj);
                }
            }, 15, null);
            return;
        }
        Observable<R> observableCompose2 = HttpRequest.INSTANCE.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose2, "compose(...)");
        final Function1 function3 = new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanFragment.requestCountry$lambda$0((String) obj);
            }
        };
        Object objAs3 = observableCompose2.map(new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return WatchPlanFragment.requestCountry$lambda$1(function3, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs3, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs3, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanFragment.requestCountry$lambda$2(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanFragment.requestCountry$lambda$3(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return WatchPlanFragment.requestCountry$lambda$4(this.f$0, (CountryResponse) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CountryResponse requestCountry$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (CountryResponse) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CountryResponse requestCountry$lambda$0(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        PrefsUtils.getInstance().putString(Constant.Prefs.MOVIE_COUNTRY, it);
        return (CountryResponse) JSONObject.parseObject(it, CountryResponse.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$3(WatchPlanFragment watchPlanFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanFragment.showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$4(WatchPlanFragment watchPlanFragment, CountryResponse countryResponse) {
        watchPlanFragment.hideLoadingView();
        ArrayList<FilterCountry> country_list = countryResponse.getCountry_list();
        Intrinsics.checkNotNullExpressionValue(country_list, "getCountry_list(...)");
        watchPlanFragment.countryList = country_list;
        watchPlanFragment.openFilter();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$2(WatchPlanFragment watchPlanFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        watchPlanFragment.hideLoadingView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PrefsUtils requestCountry$lambda$6(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (PrefsUtils) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PrefsUtils requestCountry$lambda$5(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrefsUtils.getInstance().putString(Constant.Prefs.MOVIE_COUNTRY, it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$7(PrefsUtils prefsUtils) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$9(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$8(WatchPlanFragment watchPlanFragment, String str, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList<FilterCountry> country_list = ((CountryResponse) JSONObject.parseObject(str, CountryResponse.class)).getCountry_list();
        Intrinsics.checkNotNullExpressionValue(country_list, "getCountry_list(...)");
        watchPlanFragment.countryList = country_list;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$10(WatchPlanFragment watchPlanFragment, Unit unit) {
        watchPlanFragment.openFilter();
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initListener() {
        getBinding().clFilter.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchPlanFragment.initListener$lambda$11(this.f$0, view);
            }
        });
        getBinding().tabLayout.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return WatchPlanFragment.initListener$lambda$12(this.f$0, view, i, keyEvent);
            }
        });
        getBinding().clFilter.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return WatchPlanFragment.initListener$lambda$13(this.f$0, view, i, keyEvent);
            }
        });
        getBinding().tabLayout.addOnTabSelectedListener(new TvTabLayout.OnTabSelectedListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment.initListener.4
            @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
            public void onTabReselected(TvTabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
            public void onTabUnselected(TvTabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
            }

            @Override // com.owen.tab.TvTabLayout.OnTabSelectedListener
            public void onTabSelected(TvTabLayout.Tab tab) {
                Intrinsics.checkNotNullParameter(tab, "tab");
                if (tab.getPosition() == 2 || tab.getPosition() == 0) {
                    ConstraintLayout clFilter = WatchPlanFragment.this.getBinding().clFilter;
                    Intrinsics.checkNotNullExpressionValue(clFilter, "clFilter");
                    CommonExtKt.visible(clFilter);
                } else {
                    ConstraintLayout clFilter2 = WatchPlanFragment.this.getBinding().clFilter;
                    Intrinsics.checkNotNullExpressionValue(clFilter2, "clFilter");
                    CommonExtKt.gone(clFilter2);
                }
                if (tab.getPosition() == 0) {
                    WatchPlanFragment.this.hideAllFragment();
                    FragmentTransaction fragmentTransactionBeginTransaction = WatchPlanFragment.this.getChildFragmentManager().beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
                    if (WatchPlanFragment.this.waitingFragment == null) {
                        WatchPlanFragment.this.waitingFragment = new WaitingFragment();
                        WaitingFragment waitingFragment = WatchPlanFragment.this.waitingFragment;
                        Intrinsics.checkNotNull(waitingFragment);
                        final WatchPlanFragment watchPlanFragment = WatchPlanFragment.this;
                        waitingFragment.setListener(new FocusUpListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$initListener$4$onTabSelected$1
                            @Override // com.movieboxpro.android.listener.FocusUpListener
                            public void onFocusUp() {
                                watchPlanFragment.getBinding().tabLayout.requestFocus();
                            }
                        });
                        int i = R.id.flContainer;
                        WaitingFragment waitingFragment2 = WatchPlanFragment.this.waitingFragment;
                        Intrinsics.checkNotNull(waitingFragment2);
                        fragmentTransactionBeginTransaction.add(i, waitingFragment2, "WaitingFragment");
                    }
                    WaitingFragment waitingFragment3 = WatchPlanFragment.this.waitingFragment;
                    Intrinsics.checkNotNull(waitingFragment3);
                    fragmentTransactionBeginTransaction.show(waitingFragment3);
                    fragmentTransactionBeginTransaction.commitAllowingStateLoss();
                    return;
                }
                if (tab.getPosition() == 1) {
                    WatchPlanFragment.this.hideAllFragment();
                    FragmentTransaction fragmentTransactionBeginTransaction2 = WatchPlanFragment.this.getChildFragmentManager().beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction2, "beginTransaction(...)");
                    if (WatchPlanFragment.this.watchedFragment == null) {
                        WatchPlanFragment.this.watchedFragment = new WatchedFragment();
                        WatchedFragment watchedFragment = WatchPlanFragment.this.watchedFragment;
                        Intrinsics.checkNotNull(watchedFragment);
                        final WatchPlanFragment watchPlanFragment2 = WatchPlanFragment.this;
                        watchedFragment.setListener(new FocusUpListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$initListener$4$onTabSelected$2
                            @Override // com.movieboxpro.android.listener.FocusUpListener
                            public void onFocusUp() {
                                watchPlanFragment2.getBinding().tabLayout.requestFocus();
                            }
                        });
                        int i2 = R.id.flContainer;
                        WatchedFragment watchedFragment2 = WatchPlanFragment.this.watchedFragment;
                        Intrinsics.checkNotNull(watchedFragment2);
                        fragmentTransactionBeginTransaction2.add(i2, watchedFragment2, "WatchedFragment");
                    }
                    WatchedFragment watchedFragment3 = WatchPlanFragment.this.watchedFragment;
                    Intrinsics.checkNotNull(watchedFragment3);
                    fragmentTransactionBeginTransaction2.show(watchedFragment3);
                    fragmentTransactionBeginTransaction2.commitAllowingStateLoss();
                    return;
                }
                if (tab.getPosition() == 2) {
                    WatchPlanFragment.this.hideAllFragment();
                    FragmentTransaction fragmentTransactionBeginTransaction3 = WatchPlanFragment.this.getChildFragmentManager().beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction3, "beginTransaction(...)");
                    if (WatchPlanFragment.this.favoriteFragment == null) {
                        WatchPlanFragment.this.favoriteFragment = new FavoriteFragment();
                        FavoriteFragment favoriteFragment = WatchPlanFragment.this.favoriteFragment;
                        Intrinsics.checkNotNull(favoriteFragment);
                        final WatchPlanFragment watchPlanFragment3 = WatchPlanFragment.this;
                        favoriteFragment.setListener(new FocusUpListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$initListener$4$onTabSelected$3
                            @Override // com.movieboxpro.android.listener.FocusUpListener
                            public void onFocusUp() {
                                watchPlanFragment3.getBinding().tabLayout.requestFocus();
                            }
                        });
                        int i3 = R.id.flContainer;
                        FavoriteFragment favoriteFragment2 = WatchPlanFragment.this.favoriteFragment;
                        Intrinsics.checkNotNull(favoriteFragment2);
                        fragmentTransactionBeginTransaction3.add(i3, favoriteFragment2, "FavoriteFragment");
                    }
                    FavoriteFragment favoriteFragment3 = WatchPlanFragment.this.favoriteFragment;
                    Intrinsics.checkNotNull(favoriteFragment3);
                    fragmentTransactionBeginTransaction3.show(favoriteFragment3);
                    fragmentTransactionBeginTransaction3.commitAllowingStateLoss();
                    return;
                }
                if (tab.getPosition() == 3) {
                    WatchPlanFragment.this.hideAllFragment();
                    FragmentTransaction fragmentTransactionBeginTransaction4 = WatchPlanFragment.this.getChildFragmentManager().beginTransaction();
                    Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction4, "beginTransaction(...)");
                    if (WatchPlanFragment.this.recommendFragment == null) {
                        WatchPlanFragment.this.recommendFragment = new RecommendListFragment();
                        RecommendListFragment recommendListFragment = WatchPlanFragment.this.recommendFragment;
                        Intrinsics.checkNotNull(recommendListFragment);
                        final WatchPlanFragment watchPlanFragment4 = WatchPlanFragment.this;
                        recommendListFragment.setListener(new FocusUpListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$initListener$4$onTabSelected$4
                            @Override // com.movieboxpro.android.listener.FocusUpListener
                            public void onFocusUp() {
                                watchPlanFragment4.getBinding().tabLayout.requestFocus();
                            }
                        });
                        int i4 = R.id.flContainer;
                        RecommendListFragment recommendListFragment2 = WatchPlanFragment.this.recommendFragment;
                        Intrinsics.checkNotNull(recommendListFragment2);
                        fragmentTransactionBeginTransaction4.add(i4, recommendListFragment2, "RecommendListFragment");
                    }
                    RecommendListFragment recommendListFragment3 = WatchPlanFragment.this.recommendFragment;
                    Intrinsics.checkNotNull(recommendListFragment3);
                    fragmentTransactionBeginTransaction4.show(recommendListFragment3);
                    fragmentTransactionBeginTransaction4.commitAllowingStateLoss();
                }
            }
        });
        int i = 0;
        for (Object obj : CollectionsKt.arrayListOf("WAITING", "WATCHED", "FAVORITES", "RECOMMEND")) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            String str = (String) obj;
            if (getBinding().tabLayout != null) {
                TvTabLayout.Tab text = getBinding().tabLayout.newTab().setText(str);
                Intrinsics.checkNotNullExpressionValue(text, "setText(...)");
                if (this.lastSaveSelectPos == i) {
                    getBinding().tabLayout.addTab(text, true);
                } else {
                    getBinding().tabLayout.addTab(text);
                }
            }
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$11(WatchPlanFragment watchPlanFragment, View view) {
        if (watchPlanFragment.countryList.isEmpty()) {
            watchPlanFragment.requestCountry();
        } else {
            watchPlanFragment.openFilter();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$12(WatchPlanFragment watchPlanFragment, View view, int i, KeyEvent keyEvent) {
        if (i == 19 && keyEvent.getAction() == 0) {
            EventBus.getDefault().post(new ListFocusUpEvent());
            return true;
        }
        if (i != 20 || keyEvent.getAction() != 0 || watchPlanFragment.getBinding().tabLayout.getSelectedTab().getPosition() != 0) {
            return false;
        }
        watchPlanFragment.getBinding().clFilter.requestFocus();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$13(WatchPlanFragment watchPlanFragment, View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() != 0 || i != 20 || watchPlanFragment.getBinding().tabLayout.getSelectedTabPosition() != 0) {
            return false;
        }
        FocusWatchingLiveData.INSTANCE.get().setValue(true);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideAllFragment() {
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "beginTransaction(...)");
        WaitingFragment waitingFragment = this.waitingFragment;
        if (waitingFragment != null) {
            fragmentTransactionBeginTransaction.hide(waitingFragment);
        }
        WatchedFragment watchedFragment = this.watchedFragment;
        if (watchedFragment != null) {
            fragmentTransactionBeginTransaction.hide(watchedFragment);
        }
        FavoriteFragment favoriteFragment = this.favoriteFragment;
        if (favoriteFragment != null) {
            fragmentTransactionBeginTransaction.hide(favoriteFragment);
        }
        RecommendListFragment recommendListFragment = this.recommendFragment;
        if (recommendListFragment != null) {
            fragmentTransactionBeginTransaction.hide(recommendListFragment);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    @Override // com.movieboxpro.android.base.BaseBindingFragment
    public void initData() {
        requestGener();
    }

    private final void requestGener() {
        ((ObservableSubscribeProxy) Observable.zip(Http.getService().Top_list("Top_list", 1).compose(RxUtils.rxTranslate2List(SpecialFilterModel.class)), Http.getService().Cat_list("Cat_list").compose(RxUtils.rxTranslate2List(Gener.class)), new BiFunction() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return WatchPlanFragment.requestGener$lambda$19((List) obj, (List) obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<? extends List<? extends SpecialFilterModel>, ? extends List<? extends Gener>>>() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment.requestGener.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<? extends List<? extends SpecialFilterModel>, ? extends List<? extends Gener>> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                WatchPlanFragment.this.geners.addAll(model.getSecond());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair requestGener$lambda$19(List t1, List t2) {
        Intrinsics.checkNotNullParameter(t1, "t1");
        Intrinsics.checkNotNullParameter(t2, "t2");
        return new Pair(t1, t2);
    }

    /* JADX INFO: compiled from: WatchPlanFragment.kt */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\n\u001a\u00020\tH\u0014J\u0010\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0014J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0014J\b\u0010\u000f\u001a\u00020\u0010H\u0014J\u000e\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0014J\b\u0010\u0014\u001a\u00020\rH\u0014J\u0016\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0003H\u0014J\u0012\u0010\u0018\u001a\u00020\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\u0018\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0002H\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/view/fragment/WatchPlanFragment$RecommendListFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/WatchedItem;", "Lcom/movieboxpro/android/model/WatchedResponse;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/FocusUpListener;", "setListener", "", "initFocusUpListener", "onFocus", "position", "", "onLastFocus", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "getServiceData", "Lio/reactivex/Observable;", "", "initItemLayout", "getData", "", "model", "getBundle", "arguments", "Landroid/os/Bundle;", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class RecommendListFragment extends BaseListFragment<WatchedItem, WatchedResponse> {
        private FocusUpListener listener;

        public final void setListener(FocusUpListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.listener = listener;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void initFocusUpListener() {
            this.mRecyclerView.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$RecommendListFragment$$ExternalSyntheticLambda2
                @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
                public final boolean onInBorderKeyEvent(int i, View view) {
                    return WatchPlanFragment.RecommendListFragment.initFocusUpListener$lambda$0(this.f$0, i, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean initFocusUpListener$lambda$0(RecommendListFragment recommendListFragment, int i, View view) {
            if (i != 33) {
                return false;
            }
            FocusUpListener focusUpListener = recommendListFragment.listener;
            if (focusUpListener != null) {
                focusUpListener.onFocusUp();
            }
            return true;
        }

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
        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void onFocus(int position) {
            View viewByPosition = this.mAdapter.getViewByPosition(position, R.id.tvTitle);
            if (viewByPosition != null) {
                CommonExtKt.visible(viewByPosition);
            }
        }

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
        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void onLastFocus(int position) {
            View viewByPosition = this.mAdapter.getViewByPosition(position, R.id.tvTitle);
            if (viewByPosition != null) {
                CommonExtKt.invisible(viewByPosition);
            }
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$RecommendListFragment$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    WatchPlanFragment.RecommendListFragment.onItemClick$lambda$3(this.f$0, baseQuickAdapter, view, i);
                }
            };
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void onItemClick$lambda$3(RecommendListFragment recommendListFragment, BaseQuickAdapter adapter, View view, int i) {
            Context context;
            Intrinsics.checkNotNullParameter(adapter, "adapter");
            Intrinsics.checkNotNullParameter(view, "view");
            Object item = adapter.getItem(i);
            Intrinsics.checkNotNull(item, "null cannot be cast to non-null type com.movieboxpro.android.model.WatchedItem");
            WatchedItem watchedItem = (WatchedItem) item;
            if (watchedItem.getBox_type() == 1) {
                Context context2 = recommendListFragment.getContext();
                if (context2 != null) {
                    MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                    String id = watchedItem.getId();
                    companion.starter(context2, id != null ? id : "", watchedItem.getPoster());
                    return;
                }
                return;
            }
            if (watchedItem.getBox_type() != 2 || (context = recommendListFragment.getContext()) == null) {
                return;
            }
            TvDetailActivity.Companion companion2 = TvDetailActivity.INSTANCE;
            String id2 = watchedItem.getId();
            if (id2 == null) {
                id2 = "";
            }
            String poster = watchedItem.getPoster();
            companion2.starter(context, id2, poster != null ? poster : "");
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.INSTANCE.post("User_recommend_list").params(Api.INSTANCE.getRecommendList(this.mCurrentPage, this.mPageSize)).asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_favorite_item;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<WatchedItem> getData(WatchedResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ArrayList<WatchedItem> list = model.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            return list;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            this.mClass = WatchedItem.class;
            this.mPageClass = WatchedResponse.class;
            SwitchAccountLiveData.INSTANCE.get().observeInFragment(this, new WatchPlanFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$RecommendListFragment$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.RecommendListFragment.getBundle$lambda$4(this.f$0, (Boolean) obj);
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit getBundle$lambda$4(RecommendListFragment recommendListFragment, Boolean bool) {
            recommendListFragment.startRefresh();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, WatchedItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            TextView textView = (TextView) helper.getView(R.id.tvUpdateCount);
            if (item.getUpdateCount() == 0) {
                CommonExtKt.gone(textView);
            } else {
                CommonExtKt.visible(textView);
                if (item.getUpdateCount() > 99) {
                    textView.setText("99+");
                } else {
                    textView.setText(String.valueOf(item.getUpdateCount()));
                }
            }
            helper.setText(R.id.tvTitle, item.getTitle());
            ImageView imageView = (ImageView) helper.getView(R.id.favorite_poster);
            TextView textView2 = (TextView) helper.getView(R.id.favorite_poster_describ);
            SlantedTextView slantedTextView = (SlantedTextView) helper.getView(R.id.slv_right_tri);
            ImageView imageView2 = (ImageView) helper.getView(R.id.favorite_desc);
            ImageView imageView3 = (ImageView) helper.getView(R.id.ivStar);
            TextView textView3 = (TextView) helper.getView(R.id.tvImdbRating);
            if (Intrinsics.areEqual(item.getImdb_rating(), "0")) {
                CommonExtKt.gone(imageView3);
                CommonExtKt.gone(textView3);
            } else {
                CommonExtKt.visible(imageView3);
                CommonExtKt.visible(textView3);
                textView3.setText(String.valueOf(item.getImdb_rating()));
            }
            ImageView imageView4 = (ImageView) helper.getView(R.id.ivTomato);
            TextView textView4 = (TextView) helper.getView(R.id.tvTomatoMeter);
            imageView4.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
            if (item.getTomato_meter() == 0) {
                CommonExtKt.gone(textView4);
                CommonExtKt.gone(imageView4);
            } else {
                CommonExtKt.visible(textView4);
                CommonExtKt.visible(imageView4);
                CommonExtKt.textShadow$default(textView4, item.getTomato_meter() + "%", 0, 0, 6, null);
            }
            int box_type = item.getBox_type();
            if (box_type == 1) {
                CommonExtKt.gone(textView2);
                CommonExtKt.gone(slantedTextView);
                CommonExtKt.visible(imageView2);
                GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), imageView, 8);
                GlideUtils.load(getContext(), CommonUtils.getMovieTag(item.getQuality_tag()), imageView2);
                return;
            }
            if (box_type != 2) {
                return;
            }
            CommonExtKt.visible(textView2);
            CommonExtKt.gone(imageView2);
            GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), imageView, 8);
            String update_title = item.getUpdate_title();
            if (update_title == null || update_title.length() == 0) {
                CommonExtKt.gone(slantedTextView);
            } else {
                CommonExtKt.visible(slantedTextView);
                slantedTextView.setText(item.getUpdate_title());
            }
            textView2.setText(item.getSeason_episode());
        }
    }

    /* JADX INFO: compiled from: WatchPlanFragment.kt */
    @Metadata(d1 = {"\u0000¼\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 g2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001gB\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0006\u001a\u00020\u0007J>\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0010J\b\u0010\u001d\u001a\u00020\u001aH\u0014J\u0010\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020\u0010H\u0002J@\u0010 \u001a\u00020\u001a2\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u00022\u0006\u0010'\u001a\u00020\"H\u0002J\u0016\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010!\u001a\u00020\u0010H\u0002J6\u0010+\u001a\b\u0012\u0004\u0012\u00020*0)2\u0006\u0010!\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0002H\u0002J \u0010,\u001a\u00020\u001a2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020\"2\u0006\u0010%\u001a\u00020\tH\u0002J(\u00100\u001a\u00020\u001a2\u0006\u00101\u001a\u0002022\u0006\u0010/\u001a\u00020\"2\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\u0002H\u0002J*\u00103\u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\u00102\u0006\u00104\u001a\u00020\u00102\u0006\u00105\u001a\u00020\u00102\u0006\u0010/\u001a\u00020\"H\u0002J\"\u00106\u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\"2\u0006\u0010/\u001a\u00020\"H\u0002J\"\u00107\u001a\u00020\u001a2\b\u0010!\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\"2\u0006\u0010/\u001a\u00020\"H\u0002J\u0012\u00108\u001a\u00020\u001a2\b\u00109\u001a\u0004\u0018\u00010\u0010H\u0002JL\u0010:\u001a\u00020\u001a2\u0016\u0010;\u001a\u0012\u0012\u0004\u0012\u00020=0<j\b\u0012\u0004\u0012\u00020=`>2\b\u0010?\u001a\u0004\u0018\u00010\u00102\b\u0010!\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u000f\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\"H\u0002J\b\u0010F\u001a\u00020GH\u0014J\b\u0010H\u001a\u00020IH\u0014J\u000e\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00100)H\u0014J\b\u0010K\u001a\u00020\tH\u0014J\b\u0010L\u001a\u00020\tH\u0014J\u0010\u0010M\u001a\u00020\u001a2\u0006\u0010N\u001a\u00020OH\u0014J\b\u0010P\u001a\u00020\"H\u0014J\b\u0010Q\u001a\u00020\"H\u0014J\b\u0010R\u001a\u00020SH\u0014J\u0016\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00020V2\u0006\u0010W\u001a\u00020\u0003H\u0014J\u0012\u0010X\u001a\u00020\u001a2\b\b\u0002\u0010Y\u001a\u00020\tH\u0002J\u0012\u0010Z\u001a\u00020\u001a2\b\u0010[\u001a\u0004\u0018\u00010\\H\u0014J\b\u0010]\u001a\u00020\tH\u0014J\u0010\u0010^\u001a\u00020\"2\u0006\u0010W\u001a\u00020\u0002H\u0014J\u0016\u0010_\u001a\u00020\u001a2\f\u0010`\u001a\b\u0012\u0004\u0012\u00020\u00020aH\u0014J\u0018\u0010c\u001a\u00020\u001a2\u0006\u0010d\u001a\u00020e2\u0006\u0010f\u001a\u00020\u0002H\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010A\u001a\u00020\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010B\u001a\u00020CX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010D\u001a\u0004\u0018\u00010EX\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010T\u001a\u0016\u0012\u0004\u0012\u00020\u0002\u0018\u00010<j\n\u0012\u0004\u0012\u00020\u0002\u0018\u0001`>X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006h"}, d2 = {"Lcom/movieboxpro/android/view/fragment/WatchPlanFragment$WaitingFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/FavoriteItem;", "Lcom/movieboxpro/android/model/FavoriteResponse;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/FocusUpListener;", "watchingAdded", "", "waitingAdded", "watchingItem", "Lcom/movieboxpro/android/model/WatchingItem;", "focusBorder", "Lcom/owen/focus/FocusBorder;", "boxType", "", "sort", "gener", "year", "rating", "country", "quality", "allDefault", "isAll", "setListener", "", "setFilter", TvContractCompat.PreviewProgramColumns.COLUMN_GENRE, "initFocusUpListener", "checkChildMode", "contentRating", "getPlayPath", "id", "", "currSeason", "currEpisode", "playImmediately", "favoriteItem", "listPosition", "loadMoviePlayPath", "Lio/reactivex/Observable;", "Lcom/movieboxpro/android/model/BaseMediaModel;", "loadTvPlayPath", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "position", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "markEpisode", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "markWatched", "cancelWatch", "playWithOtherPlayer", "url", "showScreenManage", "list", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "lastId", "lastBoxType", "lastClickTime", "", "lastDisposable", "Lio/reactivex/disposables/Disposable;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "onLongClick", "Lcom/chad/library/adapter/base/listener/OnItemLongClickListener;", "getServiceData", "isNeedLogin", "isNeedListenerFocusUp", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "initItemLayout", "gridLayoutSpan", "initScaleValue", "", "watchingList", "getData", "", "model", "getWatchingList", "refresh", "getBundle", "arguments", "Landroid/os/Bundle;", "enableMultiAdapter", "initItemType", "registerItemType", "multiTypeDelegate", "Lcom/chad/library/adapter/base/delegate/BaseMultiTypeDelegate;", "added", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class WaitingFragment extends BaseListFragment<FavoriteItem, FavoriteResponse> {
        private boolean added;
        private FocusBorder focusBorder;
        private boolean isAll;
        private long lastClickTime;
        private Disposable lastDisposable;
        private FocusUpListener listener;
        private boolean waitingAdded;
        private boolean watchingAdded;
        private ArrayList<FavoriteItem> watchingList;
        private static final int TITLE_TYPE = 100;
        private static final int ITEM_TYPE = 200;
        private static final int WATCHING_TYPE = 300;
        private final WatchingItem watchingItem = new WatchingItem();
        private String boxType = "0";
        private String sort = "";
        private String gener = "0";
        private String year = "0";
        private String rating = "";
        private String country = "";
        private String quality = "";
        private boolean allDefault = true;
        private String lastId = "";
        private int lastBoxType = -1;

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean enableMultiAdapter() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            return 4;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected float initScaleValue() {
            return 1.1f;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isNeedListenerFocusUp() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isNeedLogin() {
            return true;
        }

        public final void setListener(FocusUpListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.listener = listener;
        }

        public final void setFilter(String boxType, String year, String genre, String sort, String rating, String quality, String country) {
            Intrinsics.checkNotNullParameter(boxType, "boxType");
            Intrinsics.checkNotNullParameter(year, "year");
            Intrinsics.checkNotNullParameter(genre, "genre");
            Intrinsics.checkNotNullParameter(sort, "sort");
            Intrinsics.checkNotNullParameter(rating, "rating");
            Intrinsics.checkNotNullParameter(quality, "quality");
            Intrinsics.checkNotNullParameter(country, "country");
            this.mCurrentPage = 1;
            this.boxType = boxType;
            this.year = year;
            this.gener = genre;
            this.sort = sort;
            this.rating = rating;
            this.quality = quality;
            this.country = country;
            this.isAll = Intrinsics.areEqual(boxType, "0");
            this.allDefault = Intrinsics.areEqual(year, "0") && Intrinsics.areEqual(genre, "0") && Intrinsics.areEqual(sort, "") && StringsKt.isBlank(country) && Intrinsics.areEqual(boxType, "0");
            this.watchingList = null;
            startRefresh();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void initFocusUpListener() {
            this.mRecyclerView.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda31
                @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
                public final boolean onInBorderKeyEvent(int i, View view) {
                    return WatchPlanFragment.WaitingFragment.initFocusUpListener$lambda$0(this.f$0, i, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean initFocusUpListener$lambda$0(WaitingFragment waitingFragment, int i, View view) {
            if (i != 33) {
                return false;
            }
            FocusUpListener focusUpListener = waitingFragment.listener;
            if (focusUpListener != null) {
                focusUpListener.onFocusUp();
            }
            return true;
        }

        private final boolean checkChildMode(String contentRating) {
            if (!PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE) || !CommonUtils.isChildModeVideo(contentRating)) {
                return false;
            }
            ChildModeHintDialog childModeHintDialog = new ChildModeHintDialog(getContext());
            childModeHintDialog.setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda26
                @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
                public final void onClick() {
                    WatchPlanFragment.WaitingFragment.checkChildMode$lambda$2(this.f$0);
                }
            });
            childModeHintDialog.show();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void checkChildMode$lambda$2(WaitingFragment waitingFragment) {
            if (waitingFragment.getActivity() != null) {
                new OpenChildModeDialog().show(waitingFragment.getChildFragmentManager(), "OpenChildModeDialog");
            }
        }

        private final void getPlayPath(final String id, final int boxType, final int currSeason, final int currEpisode, final boolean playImmediately, final FavoriteItem favoriteItem, final int listPosition) {
            Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, id, SystemUtils.getUniqueId(App.getContext()), boxType, currSeason, currEpisode, Build.BRAND, Build.MODEL);
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda34
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.getPlayPath$lambda$3(this.f$0, id, boxType, currSeason, currEpisode, (String) obj);
                }
            };
            Observable observableOnErrorResumeNext = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda35
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return WatchPlanFragment.WaitingFragment.getPlayPath$lambda$4(function1, obj);
                }
            }).onErrorResumeNext((Function<? super Throwable, ? extends ObservableSource<? extends R>>) new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return WatchPlanFragment.WaitingFragment.getPlayPath$lambda$5((Throwable) obj);
                }
            });
            final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.getPlayPath$lambda$6(boxType, this, id, currSeason, currEpisode, favoriteItem, (String) obj);
                }
            };
            ((ObservableSubscribeProxy) observableOnErrorResumeNext.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda3
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return WatchPlanFragment.WaitingFragment.getPlayPath$lambda$7(function2, obj);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<BaseMediaModel>() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$getPlayPath$4
                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onStart(Disposable disposable) {
                    Intrinsics.checkNotNullParameter(disposable, "disposable");
                    this.this$0.showLoadingView();
                }

                @Override // com.movieboxpro.android.base.HttpResponseObserver
                public void onSuccess(BaseMediaModel model) {
                    Intrinsics.checkNotNullParameter(model, "model");
                    if (model instanceof TvDetail) {
                        List<BaseMediaModel.DownloadFile> list = model.list;
                        if (list != null && !list.isEmpty()) {
                            this.this$0.goTvPlayer((TvDetail) model, listPosition, playImmediately, favoriteItem);
                        } else {
                            ToastUtils.showShort("Not Available", new Object[0]);
                        }
                    } else if (model instanceof MovieDetail) {
                        List<BaseMediaModel.DownloadFile> list2 = model.list;
                        if (list2 != null && !list2.isEmpty()) {
                            this.this$0.goMoviePlayer((MovieDetail) model, listPosition, playImmediately);
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
        public static final ObservableSource getPlayPath$lambda$3(WaitingFragment waitingFragment, String str, int i, int i2, int i3, String it) {
            Observable observableJust;
            Intrinsics.checkNotNullParameter(it, "it");
            Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
            Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
            BaseResponse baseResponse = (BaseResponse) object;
            if (baseResponse.getCode() == -88) {
                waitingFragment.hideLoadingView();
                List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
                waitingFragment.showScreenManage(new ArrayList<>(device_list != null ? device_list : new ArrayList()), baseResponse.getMsg(), str, i, i2, i3);
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
        public static final ObservableSource getPlayPath$lambda$7(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (ObservableSource) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ObservableSource getPlayPath$lambda$6(int i, WaitingFragment waitingFragment, String str, int i2, int i3, FavoriteItem favoriteItem, String it) {
            Observable<BaseMediaModel> observableLoadTvPlayPath;
            Intrinsics.checkNotNullParameter(it, "it");
            if (i == 1) {
                observableLoadTvPlayPath = waitingFragment.loadMoviePlayPath(str);
            } else {
                observableLoadTvPlayPath = waitingFragment.loadTvPlayPath(str, i, i2, i3, favoriteItem);
            }
            return observableLoadTvPlayPath;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.String] */
        private final Observable<BaseMediaModel> loadMoviePlayPath(String id) {
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            objectRef2.element = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (TextUtils.isEmpty((CharSequence) objectRef2.element)) {
                objectRef.element = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            } else if (StringsKt.equals("0", (String) objectRef2.element, true)) {
                objectRef.element = "";
                objectRef2.element = "";
            }
            Observable<R> observableCompose = Http.getService().Movie_detail(API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, "", (String) objectRef.element, (String) objectRef2.element).compose(RxUtils.rxTranslate2Bean(MovieDetail.class));
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda27
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.loadMoviePlayPath$lambda$10(objectRef, objectRef2, (MovieDetail) obj);
                }
            };
            Observable<BaseMediaModel> observableFlatMap = observableCompose.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda28
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return WatchPlanFragment.WaitingFragment.loadMoviePlayPath$lambda$11(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
            return observableFlatMap;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final ObservableSource loadMoviePlayPath$lambda$11(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (ObservableSource) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        public static final ObservableSource loadMoviePlayPath$lambda$10(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, final MovieDetail model) {
            Intrinsics.checkNotNullParameter(model, "model");
            Observable<R> observableCompose = Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, "", (String) objectRef.element, (String) objectRef2.element).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.loadMoviePlayPath$lambda$10$lambda$8(model, (BaseMediaModel) obj);
                }
            };
            return observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda12
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return WatchPlanFragment.WaitingFragment.loadMoviePlayPath$lambda$10$lambda$9(function1, obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BaseMediaModel loadMoviePlayPath$lambda$10$lambda$9(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (BaseMediaModel) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BaseMediaModel loadMoviePlayPath$lambda$10$lambda$8(MovieDetail movieDetail, BaseMediaModel it) {
            Intrinsics.checkNotNullParameter(it, "it");
            movieDetail.addDonwload(it);
            Intrinsics.checkNotNull(movieDetail, "null cannot be cast to non-null type com.movieboxpro.android.model.BaseMediaModel");
            return movieDetail;
        }

        private final Observable<BaseMediaModel> loadTvPlayPath(final String id, final int boxType, int currSeason, int currEpisode, final FavoriteItem favoriteItem) {
            String str;
            String str2;
            final int i = currSeason > 0 ? currSeason : 1;
            final int i2 = currEpisode > 0 ? currEpisode : 1;
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
                str2 = "";
                str = str2;
            } else {
                str = string;
                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            Observable<R> observableCompose = Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", id, String.valueOf(i), String.valueOf(i2), str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
            final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.loadTvPlayPath$lambda$12(id, i, i2, favoriteItem, boxType, (BaseMediaModel) obj);
                }
            };
            Observable<BaseMediaModel> map = observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda18
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return WatchPlanFragment.WaitingFragment.loadTvPlayPath$lambda$13(function1, obj);
                }
            });
            Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BaseMediaModel loadTvPlayPath$lambda$13(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (BaseMediaModel) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final BaseMediaModel loadTvPlayPath$lambda$12(String str, int i, int i2, FavoriteItem favoriteItem, int i3, BaseMediaModel downloadModel) {
            Intrinsics.checkNotNullParameter(downloadModel, "downloadModel");
            DownloadFile downloadFileFindDownloadFileByTidSeasonEpisode = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(str, i, i2);
            if (downloadFileFindDownloadFileByTidSeasonEpisode != null) {
                downloadModel.list.add(0, BeanKt.toMediaDownloadFile(downloadFileFindDownloadFileByTidSeasonEpisode));
            }
            TvDetail tvDetail = new TvDetail();
            tvDetail.id = str;
            String title = favoriteItem.getTitle();
            if (title == null) {
                title = downloadModel.title;
            }
            tvDetail.title = title;
            String poster = favoriteItem.getPoster();
            if (poster == null) {
                poster = downloadModel.poster;
            }
            tvDetail.poster = poster;
            tvDetail.box_type = i3;
            tvDetail.imdb_id = downloadModel.imdb_id;
            FavoriteItem.LastEpisode last_episode = favoriteItem.getLast_episode();
            int seconds = last_episode != null ? last_episode.getSeconds() : 0;
            if (seconds <= 0) {
                seconds = downloadModel.seconds;
            }
            tvDetail.seconds = seconds;
            tvDetail.seasonDetail.season = i;
            tvDetail.seasonDetail.episode = i2;
            tvDetail.seasonDetail.tid = str;
            tvDetail.episodeList = new ArrayList();
            tvDetail.addDonwload(downloadModel);
            return tvDetail;
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
                choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$goMoviePlayer$2
                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void playWithOther() {
                        ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = ChoosePlayerQualityFragment.INSTANCE.newInstance(new ArrayList<>(movieDetail.list));
                        final WatchPlanFragment.WaitingFragment waitingFragment = this;
                        choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$goMoviePlayer$2$playWithOther$1
                            @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                            public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position2) {
                                Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                                waitingFragment.playWithOtherPlayer(downloadUrl.path);
                            }
                        });
                        FragmentManager childFragmentManager = this.getChildFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                        choosePlayerQualityFragmentNewInstance.showAllowingStateLoss(childFragmentManager, "ChoosePlayerQualityFragment");
                    }

                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void play() {
                        ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 1, false, 2, null);
                        final WatchPlanFragment.WaitingFragment waitingFragment = this;
                        final MovieDetail movieDetail2 = movieDetail;
                        final int i = position;
                        choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$goMoviePlayer$2$play$1
                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void chooseResume() {
                                Context context2 = waitingFragment.getContext();
                                if (context2 != null) {
                                    MovieDetail movieDetail3 = movieDetail2;
                                    VideoPlayerActivity.start(context2, movieDetail3, movieDetail3.id);
                                }
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void chooseRestart() {
                                Context context2 = waitingFragment.getContext();
                                if (context2 != null) {
                                    MovieDetail movieDetail3 = movieDetail2;
                                    VideoPlayerActivity.start(context2, movieDetail3, movieDetail3.id, true);
                                }
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void goDetail() {
                                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                                Context context2 = waitingFragment.getContext();
                                String id = movieDetail2.id;
                                Intrinsics.checkNotNullExpressionValue(id, "id");
                                companion.starter(context2, id, movieDetail2.poster);
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void delete() {
                                waitingFragment.cancelWatch(movieDetail2.id, 1, i);
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void markWatched() {
                                waitingFragment.markWatched(movieDetail2.id, 1, i);
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
            choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$goMoviePlayer$3
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

        /* JADX INFO: Access modifiers changed from: private */
        public final void goTvPlayer(final TvDetail tvDetail, final int position, boolean playImmediately, final FavoriteItem favoriteItem) {
            final ArrayList arrayList = new ArrayList();
            if (playImmediately) {
                Context context = getContext();
                if (context != null) {
                    VideoPlayer.start(context, tvDetail, tvDetail.seasonDetail.season, tvDetail.seasonDetail.episode, false, arrayList, false, true);
                    return;
                }
                return;
            }
            if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PLAY_WITH_OTHER_PLAYER, false)) {
                ChoosePlayerDialog choosePlayerDialog = new ChoosePlayerDialog();
                choosePlayerDialog.setListener(new ChoosePlayerDialog.ChoosePlayerListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$goTvPlayer$2
                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void playWithOther() {
                        ChoosePlayerQualityFragment choosePlayerQualityFragmentNewInstance = ChoosePlayerQualityFragment.INSTANCE.newInstance(new ArrayList<>(tvDetail.list));
                        final WatchPlanFragment.WaitingFragment waitingFragment = this;
                        choosePlayerQualityFragmentNewInstance.setCallback(new ChoosePlayerQualityFragment.ChooseDownloadQualityListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$goTvPlayer$2$playWithOther$1
                            @Override // com.movieboxpro.android.view.dialog.ChoosePlayerQualityFragment.ChooseDownloadQualityListener
                            public void onChooseDownload(BaseMediaModel.DownloadFile downloadUrl, int position2) {
                                Intrinsics.checkNotNullParameter(downloadUrl, "downloadUrl");
                                waitingFragment.playWithOtherPlayer(downloadUrl.path);
                            }
                        });
                        FragmentManager childFragmentManager = this.getChildFragmentManager();
                        Intrinsics.checkNotNullExpressionValue(childFragmentManager, "getChildFragmentManager(...)");
                        choosePlayerQualityFragmentNewInstance.showAllowingStateLoss(childFragmentManager, "ChoosePlayerQualityFragment");
                    }

                    @Override // com.movieboxpro.android.view.dialog.ChoosePlayerDialog.ChoosePlayerListener
                    public void play() {
                        ChoosePlayFragment choosePlayFragmentNewInstance$default = ChoosePlayFragment.Companion.newInstance$default(ChoosePlayFragment.INSTANCE, 2, false, 2, null);
                        final WatchPlanFragment.WaitingFragment waitingFragment = this;
                        final TvDetail tvDetail2 = tvDetail;
                        final ArrayList<TvSeasonList> arrayList2 = arrayList;
                        final int i = position;
                        choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$goTvPlayer$2$play$1
                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void chooseResume() {
                                Context context2 = waitingFragment.getContext();
                                if (context2 != null) {
                                    TvDetail tvDetail3 = tvDetail2;
                                    VideoPlayer.start(context2, tvDetail3, tvDetail3.seasonDetail.season, tvDetail3.seasonDetail.episode, false, arrayList2, false, true);
                                }
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void chooseRestart() {
                                Context context2 = waitingFragment.getContext();
                                if (context2 != null) {
                                    TvDetail tvDetail3 = tvDetail2;
                                    VideoPlayer.start(context2, tvDetail3, tvDetail3.seasonDetail.season, tvDetail3.seasonDetail.episode, false, arrayList2, true, true);
                                }
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void goDetail() {
                                TvDetailActivity.Companion companion = TvDetailActivity.INSTANCE;
                                Context context2 = waitingFragment.getContext();
                                String id = tvDetail2.id;
                                Intrinsics.checkNotNullExpressionValue(id, "id");
                                String poster = tvDetail2.poster;
                                Intrinsics.checkNotNullExpressionValue(poster, "poster");
                                companion.starter(context2, id, poster);
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void delete() {
                                waitingFragment.cancelWatch(tvDetail2.id, 2, i);
                            }

                            @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                            public void markWatched() {
                                FavoriteItem favoriteItem2 = (FavoriteItem) waitingFragment.mAdapter.getItem(i);
                                if (favoriteItem2.getLast_episode() == null) {
                                    waitingFragment.markWatched(tvDetail2.id, 2, i);
                                } else {
                                    waitingFragment.markEpisode(tvDetail2.id, String.valueOf(favoriteItem2.getLast_episode().getSeason()), String.valueOf(favoriteItem2.getLast_episode().getEpisode()), i);
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
            choosePlayFragmentNewInstance$default.setChooseListener(new ChoosePlayFragment.ChooseListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$goTvPlayer$3
                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseResume() {
                    Context context2 = this.this$0.getContext();
                    if (context2 != null) {
                        TvDetail tvDetail2 = tvDetail;
                        VideoPlayer.start(context2, tvDetail2, tvDetail2.seasonDetail.season, tvDetail2.seasonDetail.episode, false, arrayList, false, true);
                    }
                }

                @Override // com.movieboxpro.android.view.fragment.account.ChoosePlayFragment.ChooseListener
                public void chooseRestart() {
                    Context context2 = this.this$0.getContext();
                    if (context2 != null) {
                        TvDetail tvDetail2 = tvDetail;
                        VideoPlayer.start(context2, tvDetail2, tvDetail2.seasonDetail.season, tvDetail2.seasonDetail.episode, false, arrayList, true, true);
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
        public final void markEpisode(String id, String season, String episode, int position) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("TV_over_v2").param("over", (Object) 1).param("tid", id).param(DealWatchNextService.SEASON, season).param(DealWatchNextService.EPISODE, episode).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda23
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.markEpisode$lambda$16(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.markEpisode$lambda$17(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.markEpisode$lambda$18(this.f$0, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markEpisode$lambda$17(WaitingFragment waitingFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markEpisode$lambda$18(WaitingFragment waitingFragment, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.hideLoadingView();
            RefreshWatchingLiveData.INSTANCE.get().setValue(true);
            RefreshWatchedLiveData.INSTANCE.get().setValue(true);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markEpisode$lambda$16(WaitingFragment waitingFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.hideLoadingView();
            ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void markWatched(String id, int boxType, int position) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_mark").param("box_type", Integer.valueOf(boxType)).param("mid", id).param("watched", (Object) 1).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda19
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.markWatched$lambda$19(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda20
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.markWatched$lambda$20(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.markWatched$lambda$21(this.f$0, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markWatched$lambda$20(WaitingFragment waitingFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markWatched$lambda$21(WaitingFragment waitingFragment, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.hideLoadingView();
            RefreshWatchingLiveData.INSTANCE.get().setValue(true);
            RefreshWatchedLiveData.INSTANCE.get().setValue(true);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit markWatched$lambda$19(WaitingFragment waitingFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.hideLoadingView();
            ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void cancelWatch(String id, int boxType, int position) {
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(HttpRequest.INSTANCE.post("User_watch_plan_del").param("mid", id).param("box_type", Integer.valueOf(boxType)).asRequest(), this), new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.cancelWatch$lambda$22(this.f$0, (ApiException) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.cancelWatch$lambda$23(this.f$0, (Disposable) obj);
                }
            }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.cancelWatch$lambda$24(this.f$0, (String) obj);
                }
            }, 10, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit cancelWatch$lambda$23(WaitingFragment waitingFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.showLoadingView();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit cancelWatch$lambda$24(WaitingFragment waitingFragment, String it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.hideLoadingView();
            RefreshWatchingLiveData.INSTANCE.get().setValue(true);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit cancelWatch$lambda$22(WaitingFragment waitingFragment, ApiException it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.hideLoadingView();
            ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
            return Unit.INSTANCE;
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

        private final void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id, int boxType, int currSeason, int currEpisode) {
            ScreenManageActivity.INSTANCE.start(this, list, 100, msg, boxType, id, currSeason, currEpisode);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda13
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    WatchPlanFragment.WaitingFragment.onItemClick$lambda$27(this.f$0, baseQuickAdapter, view, i);
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
        public static final void onItemClick$lambda$27(final WaitingFragment waitingFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            final FavoriteItem favoriteItem = (FavoriteItem) waitingFragment.mAdapter.getItem(i);
            Disposable disposable = waitingFragment.lastDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            if (System.currentTimeMillis() - waitingFragment.lastClickTime < 500 && Intrinsics.areEqual(favoriteItem.getId(), waitingFragment.lastId) && favoriteItem.getBox_type() == waitingFragment.lastBoxType) {
                String content_rating = favoriteItem.getContent_rating();
                if (content_rating == null) {
                    content_rating = "";
                }
                if (!waitingFragment.checkChildMode(content_rating)) {
                    String id = favoriteItem.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    int box_type = favoriteItem.getBox_type();
                    FavoriteItem.LastEpisode last_episode = favoriteItem.getLast_episode();
                    int season = last_episode != null ? last_episode.getSeason() : 0;
                    FavoriteItem.LastEpisode last_episode2 = favoriteItem.getLast_episode();
                    int episode = last_episode2 != null ? last_episode2.getEpisode() : 0;
                    Intrinsics.checkNotNull(favoriteItem);
                    waitingFragment.getPlayPath(id, box_type, season, episode, true, favoriteItem, i);
                }
            } else {
                Object objAs = Observable.timer(600L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(waitingFragment));
                Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda8
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WatchPlanFragment.WaitingFragment.onItemClick$lambda$27$lambda$25(this.f$0, (Disposable) obj);
                    }
                }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WatchPlanFragment.WaitingFragment.onItemClick$lambda$27$lambda$26(this.f$0, favoriteItem, i, (Long) obj);
                    }
                }, 11, null);
            }
            waitingFragment.lastClickTime = System.currentTimeMillis();
            String id2 = favoriteItem.getId();
            waitingFragment.lastId = id2 != null ? id2 : "";
            waitingFragment.lastBoxType = favoriteItem.getBox_type();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onItemClick$lambda$27$lambda$26(WaitingFragment waitingFragment, FavoriteItem favoriteItem, int i, Long l) {
            String content_rating = favoriteItem.getContent_rating();
            if (content_rating == null) {
                content_rating = "";
            }
            if (!waitingFragment.checkChildMode(content_rating)) {
                String id = favoriteItem.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                int box_type = favoriteItem.getBox_type();
                FavoriteItem.LastEpisode last_episode = favoriteItem.getLast_episode();
                int season = last_episode != null ? last_episode.getSeason() : 0;
                FavoriteItem.LastEpisode last_episode2 = favoriteItem.getLast_episode();
                int episode = last_episode2 != null ? last_episode2.getEpisode() : 0;
                Intrinsics.checkNotNull(favoriteItem);
                waitingFragment.getPlayPath(id, box_type, season, episode, false, favoriteItem, i);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit onItemClick$lambda$27$lambda$25(WaitingFragment waitingFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.lastDisposable = it;
            return Unit.INSTANCE;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemLongClickListener onLongClick() {
            return new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda7
                @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
                public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    return WatchPlanFragment.WaitingFragment.onLongClick$lambda$28(this.f$0, baseQuickAdapter, view, i);
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
        public static final boolean onLongClick$lambda$28(WaitingFragment waitingFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            FavoriteItem favoriteItem = (FavoriteItem) waitingFragment.mAdapter.getItem(i);
            if (favoriteItem.getBox_type() == 1) {
                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                Context context = waitingFragment.getContext();
                String id = favoriteItem.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                companion.starter(context, id, favoriteItem.getPoster());
            } else {
                TvDetailActivity.Companion companion2 = TvDetailActivity.INSTANCE;
                Context context2 = waitingFragment.getContext();
                String id2 = favoriteItem.getId();
                Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
                String poster = favoriteItem.getPoster();
                Intrinsics.checkNotNullExpressionValue(poster, "getPoster(...)");
                companion2.starter(context2, id2, poster);
            }
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            if (this.mCurrentPage == 1) {
                this.waitingAdded = false;
                this.watchingAdded = false;
            }
            return HttpRequest.INSTANCE.post("User_watched_plan_list_v2").params(Api.INSTANCE.getCollectList(this.boxType, this.quality, this.sort, this.country, this.year, this.rating, this.gener, this.mCurrentPage, this.mPageSize, 1)).asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 6);
            v7GridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$initRecyclerView$1
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int position) {
                    FavoriteItem favoriteItem = (FavoriteItem) this.this$0.mAdapter.getItem(position);
                    if (favoriteItem.getViewType() == WatchPlanFragment.WaitingFragment.TITLE_TYPE || favoriteItem.getViewType() == WatchPlanFragment.WaitingFragment.WATCHING_TYPE) {
                        return 6;
                    }
                    return (favoriteItem.getBox_type() == 1 || favoriteItem.getLast_episode() == null) ? 1 : 2;
                }
            });
            this.mRecyclerView.setSelectedItemAtCentered(true);
            recyclerView.setLayoutManager(v7GridLayoutManager);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_waiting_item;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<FavoriteItem> getData(FavoriteResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ArrayList arrayList = new ArrayList();
            ArrayList<FavoriteItem> list = model.getList();
            if (list != null) {
                for (FavoriteItem favoriteItem : list) {
                    if (favoriteItem.getBox_type() == 1) {
                        favoriteItem.setViewType(1);
                    } else if (favoriteItem.getLast_episode() == null) {
                        favoriteItem.setViewType(1);
                    } else {
                        favoriteItem.setViewType(2);
                    }
                }
            }
            if (this.mCurrentPage == 1) {
                WatchingItem watchingItem = this.watchingItem;
                ArrayList<FavoriteItem> list2 = model.getList();
                watchingItem.setHaveWaiting(!(list2 == null || list2.isEmpty()));
                FavoriteItem favoriteItem2 = new FavoriteItem();
                favoriteItem2.setViewType(WATCHING_TYPE);
                favoriteItem2.setWatchingItem(this.watchingItem);
                if (this.watchingList != null) {
                    favoriteItem2.getWatchingItem().setList(this.watchingList);
                } else {
                    getWatchingList$default(this, false, 1, null);
                }
                arrayList.add(favoriteItem2);
            }
            ArrayList<FavoriteItem> list3 = model.getList();
            if (list3 == null) {
                list3 = new ArrayList<>();
            }
            arrayList.addAll(list3);
            return arrayList;
        }

        static /* synthetic */ void getWatchingList$default(WaitingFragment waitingFragment, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = false;
            }
            waitingFragment.getWatchingList(z);
        }

        private final void getWatchingList(final boolean refresh) {
            ObservableSource observableSourceCompose = HttpRequest.INSTANCE.post("User_watching_update_count").asRequest().compose(RxUtils.rxTranslate2Bean(HashMap.class));
            Intrinsics.checkNotNullExpressionValue(observableSourceCompose, "compose(...)");
            ObservableSource observableSourceCompose2 = HttpRequest.INSTANCE.post("User_watched_plan_list_v2").params(Api.INSTANCE.getCollectList(this.boxType, this.quality, this.sort, this.country, this.year, this.rating, this.gener, 1, 11, 0)).asRequest().compose(RxUtils.rxTranslate2Bean(FavoriteResponse.class));
            Intrinsics.checkNotNullExpressionValue(observableSourceCompose2, "compose(...)");
            final Function2 function2 = new Function2() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda22
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return WatchPlanFragment.WaitingFragment.getWatchingList$lambda$30(this.f$0, (HashMap) obj, (FavoriteResponse) obj2);
                }
            };
            Observable observableZip = Observable.zip(observableSourceCompose, observableSourceCompose2, new BiFunction() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda29
                @Override // io.reactivex.functions.BiFunction
                public final Object apply(Object obj, Object obj2) {
                    return WatchPlanFragment.WaitingFragment.getWatchingList$lambda$31(function2, obj, obj2);
                }
            });
            Intrinsics.checkNotNullExpressionValue(observableZip, "zip(...)");
            RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableZip, this), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda30
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.getWatchingList$lambda$32(this.f$0, refresh, (String) obj);
                }
            }, 15, null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String getWatchingList$lambda$31(Function2 function2, Object p0, Object p1) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            Intrinsics.checkNotNullParameter(p1, "p1");
            return (String) function2.invoke(p0, p1);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final String getWatchingList$lambda$30(WaitingFragment waitingFragment, HashMap t1, FavoriteResponse t2) {
            Intrinsics.checkNotNullParameter(t1, "t1");
            Intrinsics.checkNotNullParameter(t2, "t2");
            waitingFragment.watchingList = t2.getList();
            WatchingItem watchingItem = waitingFragment.watchingItem;
            ArrayList<FavoriteItem> list = t2.getList();
            if (list == null) {
                list = new ArrayList<>();
            }
            watchingItem.setList(list);
            Integer num = (Integer) t1.get("watching_upload_count");
            Integer num2 = (Integer) t1.get("waiting_update_count");
            waitingFragment.watchingItem.setWatchingNum(num != null ? num.intValue() : 0);
            waitingFragment.watchingItem.setWaitingNum(num2 != null ? num2.intValue() : 0);
            return "";
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference incomplete: some casts might be missing */
        public static final Unit getWatchingList$lambda$32(WaitingFragment waitingFragment, boolean z, String str) {
            FavoriteItem favoriteItem = (FavoriteItem) CollectionsKt.getOrNull(waitingFragment.mAdapter.getData(), 0);
            if (favoriteItem != null) {
                if (favoriteItem.getWatchingItem() != null) {
                    waitingFragment.mAdapter.notifyItemChanged(0);
                } else {
                    favoriteItem.setWatchingItem(waitingFragment.watchingItem);
                    waitingFragment.mAdapter.notifyItemChanged(0);
                }
            } else {
                FavoriteItem favoriteItem2 = new FavoriteItem();
                favoriteItem2.setViewType(WATCHING_TYPE);
                favoriteItem2.setWatchingItem(waitingFragment.watchingItem);
                waitingFragment.mAdapter.addData(0, (T) favoriteItem2);
            }
            if (z) {
                waitingFragment.startRefresh();
            }
            return Unit.INSTANCE;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            this.mClass = FavoriteItem.class;
            this.mPageClass = FavoriteResponse.class;
            WaitingFragment waitingFragment = this;
            RefreshWatchingLiveData.INSTANCE.get().observeInFragment(waitingFragment, new WatchPlanFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda32
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.getBundle$lambda$33(this.f$0, (Boolean) obj);
                }
            }));
            FocusBorder focusBorderBuild = new FocusBorder.Builder().asColor().borderWidth(1, 0.1f).shadowColorRes(R.color.transparent).shadowWidth(1, 0.1f).noShimmer().build(waitingFragment);
            Intrinsics.checkNotNullExpressionValue(focusBorderBuild, "build(...)");
            this.focusBorder = focusBorderBuild;
            SwitchAccountLiveData.INSTANCE.get().observeInFragment(waitingFragment, new WatchPlanFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda33
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WaitingFragment.getBundle$lambda$34(this.f$0, (Boolean) obj);
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit getBundle$lambda$33(WaitingFragment waitingFragment, Boolean bool) {
            waitingFragment.getWatchingList(true);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit getBundle$lambda$34(WaitingFragment waitingFragment, Boolean bool) {
            waitingFragment.getWatchingList(true);
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public int initItemType(FavoriteItem model) {
            Intrinsics.checkNotNullParameter(model, "model");
            return model.getViewType();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void registerItemType(BaseMultiTypeDelegate<FavoriteItem> multiTypeDelegate) {
            Intrinsics.checkNotNullParameter(multiTypeDelegate, "multiTypeDelegate");
            multiTypeDelegate.addItemType(1, R.layout.adapter_waiting_item);
            multiTypeDelegate.addItemType(2, R.layout.adapter_waiting_tv_item);
            multiTypeDelegate.addItemType(TITLE_TYPE, R.layout.adapter_title_item);
            multiTypeDelegate.addItemType(WATCHING_TYPE, R.layout.adapter_watching_item);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, FavoriteItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            if (item.getViewType() == 1) {
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
                textView4.setText(item.getTitle());
                GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), imageView3, 8);
                helper.setGone(R.id.ivStar, true);
                return;
            }
            if (item.getViewType() == 2) {
                TextView textView6 = (TextView) helper.getView(R.id.tvTomatoMeter);
                ImageView imageView4 = (ImageView) helper.getView(R.id.ivTomato);
                imageView4.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
                if (item.getTomato_meter() == 0) {
                    CommonExtKt.gone(textView6);
                    CommonExtKt.gone(imageView4);
                } else {
                    CommonExtKt.visible(textView6);
                    CommonExtKt.visible(imageView4);
                    CommonExtKt.textShadow$default(textView6, item.getTomato_meter() + "%", 0, 0, 6, null);
                }
                ImageView imageView5 = (ImageView) helper.getView(R.id.ivStar);
                TextView textView7 = (TextView) helper.getView(R.id.tvImdbRating);
                if (Intrinsics.areEqual(item.getImdb_rating(), "0")) {
                    CommonExtKt.gone(imageView5);
                    CommonExtKt.gone(textView7);
                } else {
                    CommonExtKt.visible(imageView5);
                    CommonExtKt.visible(textView7);
                    CommonExtKt.textShadow$default(textView7, item.getImdb_rating().toString(), 0, 0, 6, null);
                }
                ProgressBar progressBar2 = (ProgressBar) helper.getView(R.id.progressBar);
                ImageView imageView6 = (ImageView) helper.getView(R.id.ivPoster);
                TextView textView8 = (TextView) helper.getView(R.id.tvTitle);
                if (item.getWaiting() == 1) {
                    CommonExtKt.gone(progressBar2);
                } else {
                    CommonExtKt.visible(progressBar2);
                    progressBar2.setMax(item.getLast_episode().getRuntime() * 60);
                    progressBar2.setProgress(item.getLast_episode().getSeconds());
                }
                textView8.setText(ExifInterface.LATITUDE_SOUTH + CommonExtKt.format2(item.getLast_episode().getSeason()) + ExifInterface.LONGITUDE_EAST + item.getLast_episode().getEpisode() + " " + item.getLast_episode().getTitle());
                GlideUtils.loadWithCorner(getContext(), item.getLast_episode().getThumbs(), imageView6, CommonExtKt.dp2Px(8));
                GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), (ImageView) helper.getView(R.id.ivTvPoster), 8);
                helper.setGone(R.id.tvEpisodeImdbRating, true);
                helper.setGone(R.id.ivEpisodeStar, true);
                return;
            }
            if (item.getViewType() == TITLE_TYPE) {
                View view = helper.getView(R.id.view);
                TextView textView9 = (TextView) helper.getView(R.id.tvTitle);
                if (item.getFavoriteTitle().isWaiting()) {
                    textView9.setText("WAITING TO WATCH");
                    textView9.setTextColor(CommonExtKt.colorInt(R.color.white_50alpha));
                    CommonExtKt.gone(view);
                } else {
                    textView9.setText("WATCHING");
                    textView9.setTextColor(CommonExtKt.colorInt(R.color.white_87alpha));
                    CommonExtKt.visible(view);
                }
                textView9.setTextSize(20.0f);
                return;
            }
            if (item.getViewType() == WATCHING_TYPE) {
                final WatchingItem watchingItem = item.getWatchingItem();
                final TvRecyclerView tvRecyclerView = (TvRecyclerView) helper.getView(R.id.recyclerView);
                if (!this.added) {
                    tvRecyclerView.addItemDecoration(new LinearItemDecoration(0, 8, false));
                    ArrayList<FavoriteItem> list = watchingItem.getList();
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    watchingItem.setAdapter(new WatchingAdapter(list));
                    watchingItem.getAdapter().setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda4
                        @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                        public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                            WatchPlanFragment.WaitingFragment.initHolder$lambda$37(watchingItem, this, baseQuickAdapter, view2, i);
                        }
                    });
                    watchingItem.getAdapter().setOnItemLongClickListener(new OnItemLongClickListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda5
                        @Override // com.chad.library.adapter.base.listener.OnItemLongClickListener
                        public final boolean onItemLongClick(BaseQuickAdapter baseQuickAdapter, View view2, int i) {
                            return WatchPlanFragment.WaitingFragment.initHolder$lambda$38(watchingItem, this, baseQuickAdapter, view2, i);
                        }
                    });
                    tvRecyclerView.setOnItemListener(new TvRecyclerView.OnItemListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$initHolder$3
                        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
                        public void onItemClick(TvRecyclerView parent, View itemView, int position) {
                        }

                        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
                        public void onItemPreSelected(TvRecyclerView parent, View itemView, int position) {
                        }

                        @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnItemListener
                        public void onItemSelected(TvRecyclerView parent, View itemView, int position) {
                            Intrinsics.checkNotNullParameter(itemView, "itemView");
                            FocusBorder focusBorder = this.this$0.focusBorder;
                            if (focusBorder == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("focusBorder");
                                focusBorder = null;
                            }
                            focusBorder.onFocus(itemView, FocusBorder.OptionsFactory.get(1.1f, 1.1f, 0.0f));
                        }
                    });
                    tvRecyclerView.setTag("added");
                    this.added = true;
                    FocusWatchingLiveData.INSTANCE.get().observeInFragment(this, new WatchPlanFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda6
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return WatchPlanFragment.WaitingFragment.initHolder$lambda$39(watchingItem, tvRecyclerView, this, (Boolean) obj);
                        }
                    }));
                }
                tvRecyclerView.setLayoutManager(new V7LinearLayoutManager(getContext(), 0, false));
                tvRecyclerView.setAdapter(watchingItem.getAdapter());
                WatchingAdapter adapter = watchingItem.getAdapter();
                ArrayList<FavoriteItem> list2 = watchingItem.getList();
                if (list2 == null) {
                    list2 = new ArrayList<>();
                }
                adapter.setNewInstance(list2);
                LinearLayout linearLayout = (LinearLayout) helper.getView(R.id.llWatching);
                LinearLayout linearLayout2 = (LinearLayout) helper.getView(R.id.llWaiting);
                TextView textView10 = (TextView) helper.getView(R.id.tvWatchingCount);
                TextView textView11 = (TextView) helper.getView(R.id.tvWaitingCount);
                ArrayList<FavoriteItem> list3 = watchingItem.getList();
                if (list3 == null || list3.isEmpty()) {
                    CommonExtKt.gone(linearLayout);
                    CommonExtKt.gone(tvRecyclerView);
                } else {
                    CommonExtKt.visible(linearLayout);
                    CommonExtKt.visible(tvRecyclerView);
                }
                if (watchingItem.isHaveWaiting()) {
                    CommonExtKt.visible(linearLayout2);
                } else {
                    CommonExtKt.gone(linearLayout2);
                }
                if (watchingItem.getWaitingNum() == 0) {
                    textView11.setText("");
                } else {
                    textView11.setText("（" + watchingItem.getWaitingNum() + "）");
                }
                if (watchingItem.getWatchingNum() == 0) {
                    textView10.setText("");
                    return;
                }
                textView10.setText("（" + watchingItem.getWatchingNum() + "）");
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void initHolder$lambda$37(WatchingItem watchingItem, final WaitingFragment waitingFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            final FavoriteItem item = watchingItem.getAdapter().getItem(i);
            if (i == 10) {
                Context context = waitingFragment.getContext();
                if (context != null) {
                    context.startActivity(new Intent(waitingFragment.getContext(), (Class<?>) MoreWatchingActivity.class));
                    return;
                }
                return;
            }
            Disposable disposable = waitingFragment.lastDisposable;
            if (disposable != null) {
                disposable.dispose();
            }
            if (System.currentTimeMillis() - waitingFragment.lastClickTime < 500 && Intrinsics.areEqual(item.getId(), waitingFragment.lastId) && item.getBox_type() == waitingFragment.lastBoxType) {
                String content_rating = item.getContent_rating();
                if (content_rating == null) {
                    content_rating = "";
                }
                if (!waitingFragment.checkChildMode(content_rating)) {
                    String id = item.getId();
                    Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                    int box_type = item.getBox_type();
                    FavoriteItem.LastEpisode last_episode = item.getLast_episode();
                    int season = last_episode != null ? last_episode.getSeason() : 0;
                    FavoriteItem.LastEpisode last_episode2 = item.getLast_episode();
                    waitingFragment.getPlayPath(id, box_type, season, last_episode2 != null ? last_episode2.getEpisode() : 0, true, item, i);
                }
            } else {
                Object objAs = Observable.timer(600L, TimeUnit.MILLISECONDS).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(waitingFragment));
                Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WatchPlanFragment.WaitingFragment.initHolder$lambda$37$lambda$35(this.f$0, (Disposable) obj);
                    }
                }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WaitingFragment$$ExternalSyntheticLambda11
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return WatchPlanFragment.WaitingFragment.initHolder$lambda$37$lambda$36(this.f$0, item, i, (Long) obj);
                    }
                }, 11, null);
            }
            waitingFragment.lastClickTime = System.currentTimeMillis();
            String id2 = item.getId();
            waitingFragment.lastId = id2 != null ? id2 : "";
            waitingFragment.lastBoxType = item.getBox_type();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit initHolder$lambda$37$lambda$36(WaitingFragment waitingFragment, FavoriteItem favoriteItem, int i, Long l) {
            String content_rating = favoriteItem.getContent_rating();
            if (content_rating == null) {
                content_rating = "";
            }
            if (!waitingFragment.checkChildMode(content_rating)) {
                String id = favoriteItem.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                int box_type = favoriteItem.getBox_type();
                FavoriteItem.LastEpisode last_episode = favoriteItem.getLast_episode();
                int season = last_episode != null ? last_episode.getSeason() : 0;
                FavoriteItem.LastEpisode last_episode2 = favoriteItem.getLast_episode();
                waitingFragment.getPlayPath(id, box_type, season, last_episode2 != null ? last_episode2.getEpisode() : 0, false, favoriteItem, i);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit initHolder$lambda$37$lambda$35(WaitingFragment waitingFragment, Disposable it) {
            Intrinsics.checkNotNullParameter(it, "it");
            waitingFragment.lastDisposable = it;
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean initHolder$lambda$38(WatchingItem watchingItem, WaitingFragment waitingFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            FavoriteItem item = watchingItem.getAdapter().getItem(i);
            if (item.getBox_type() == 1) {
                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                Context context = waitingFragment.getContext();
                String id = item.getId();
                Intrinsics.checkNotNullExpressionValue(id, "getId(...)");
                companion.starter(context, id, item.getPoster());
            } else {
                TvDetailActivity.Companion companion2 = TvDetailActivity.INSTANCE;
                Context context2 = waitingFragment.getContext();
                String id2 = item.getId();
                Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
                String poster = item.getPoster();
                Intrinsics.checkNotNullExpressionValue(poster, "getPoster(...)");
                companion2.starter(context2, id2, poster);
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit initHolder$lambda$39(WatchingItem watchingItem, TvRecyclerView tvRecyclerView, WaitingFragment waitingFragment, Boolean bool) {
            ArrayList<FavoriteItem> list = watchingItem.getList();
            if (list != null && !list.isEmpty()) {
                tvRecyclerView.requestFocus();
            } else {
                waitingFragment.mRecyclerView.requestFocus();
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: compiled from: WatchPlanFragment.kt */
    @Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 +2\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001+B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u0007J\b\u0010\n\u001a\u00020\tH\u0014J\u000e\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0014J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014J\b\u0010\u0011\u001a\u00020\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0014J\u0010\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u0002H\u0014J\u0016\u0010\u0019\u001a\u00020\t2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00020\u001bH\u0014J\u0012\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\b\u0010\u001f\u001a\u00020\u0016H\u0014J\b\u0010 \u001a\u00020\u0016H\u0014J\b\u0010!\u001a\u00020\u0014H\u0014J\b\u0010\"\u001a\u00020#H\u0014J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00020&2\u0006\u0010\u0018\u001a\u00020\u0003H\u0014J\u0018\u0010'\u001a\u00020\t2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020\u0002H\u0014R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lcom/movieboxpro/android/view/fragment/WatchPlanFragment$WatchedFragment;", "Lcom/movieboxpro/android/base/BaseListFragment;", "Lcom/movieboxpro/android/model/WatchedItem;", "Lcom/movieboxpro/android/model/WatchedResponse;", "<init>", "()V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/listener/FocusUpListener;", "setListener", "", "initFocusUpListener", "getServiceData", "Lio/reactivex/Observable;", "", "initRecyclerView", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "onItemClick", "Lcom/chad/library/adapter/base/listener/OnItemClickListener;", "initItemLayout", "", "enableMultiAdapter", "", "initItemType", "model", "registerItemType", "multiTypeDelegate", "Lcom/chad/library/adapter/base/delegate/BaseMultiTypeDelegate;", "getBundle", "arguments", "Landroid/os/Bundle;", "isNeedLogin", "isNeedListenerFocusUp", "gridLayoutSpan", "initScaleValue", "", "lastTime", "getData", "", "initHolder", "helper", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "item", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class WatchedFragment extends BaseListFragment<WatchedItem, WatchedResponse> {
        private static final int TITLE_TYPE = 100;
        private String lastTime = "";
        private FocusUpListener listener;

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean enableMultiAdapter() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int gridLayoutSpan() {
            return 4;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected float initScaleValue() {
            return 1.1f;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isNeedListenerFocusUp() {
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected boolean isNeedLogin() {
            return true;
        }

        public final void setListener(FocusUpListener listener) {
            Intrinsics.checkNotNullParameter(listener, "listener");
            this.listener = listener;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void initFocusUpListener() {
            this.mRecyclerView.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WatchedFragment$$ExternalSyntheticLambda0
                @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
                public final boolean onInBorderKeyEvent(int i, View view) {
                    return WatchPlanFragment.WatchedFragment.initFocusUpListener$lambda$0(this.f$0, i, view);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean initFocusUpListener$lambda$0(WatchedFragment watchedFragment, int i, View view) {
            if (i != 33) {
                return false;
            }
            FocusUpListener focusUpListener = watchedFragment.listener;
            if (focusUpListener != null) {
                focusUpListener.onFocusUp();
            }
            return true;
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected Observable<String> getServiceData() {
            return HttpRequest.INSTANCE.post("User_watched_list").params(Api.INSTANCE.getWatchedList(this.mCurrentPage, this.mPageSize)).asRequest();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void initRecyclerView(RecyclerView recyclerView) {
            Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
            V7GridLayoutManager v7GridLayoutManager = new V7GridLayoutManager(getContext(), 6);
            v7GridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WatchedFragment$initRecyclerView$1
                @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
                public int getSpanSize(int position) {
                    WatchedItem watchedItem = (WatchedItem) this.this$0.mAdapter.getItem(position);
                    if (watchedItem.getViewType() == 100) {
                        return 6;
                    }
                    return (watchedItem.getBox_type() == 1 || watchedItem.getEpisode_info() == null) ? 1 : 2;
                }
            });
            this.mRecyclerView.setSelectedItemAtCentered(true);
            recyclerView.setLayoutManager(v7GridLayoutManager);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected OnItemClickListener onItemClick() {
            return new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WatchedFragment$$ExternalSyntheticLambda1
                @Override // com.chad.library.adapter.base.listener.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    WatchPlanFragment.WatchedFragment.onItemClick$lambda$1(this.f$0, baseQuickAdapter, view, i);
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
        public static final void onItemClick$lambda$1(WatchedFragment watchedFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
            Intrinsics.checkNotNullParameter(baseQuickAdapter, "<unused var>");
            Intrinsics.checkNotNullParameter(view, "<unused var>");
            WatchedItem watchedItem = (WatchedItem) watchedFragment.mAdapter.getItem(i);
            if (watchedItem.getBox_type() == 1) {
                MovieDetailActivity.Companion companion = MovieDetailActivity.INSTANCE;
                Context context = watchedFragment.getContext();
                String id = watchedItem.getId();
                companion.starter(context, id != null ? id : "", watchedItem.getPoster());
                return;
            }
            TvDetailActivity.Companion companion2 = TvDetailActivity.INSTANCE;
            Context context2 = watchedFragment.getContext();
            String id2 = watchedItem.getId();
            if (id2 == null) {
                id2 = "";
            }
            String poster = watchedItem.getPoster();
            companion2.starter(context2, id2, poster != null ? poster : "");
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected int initItemLayout() {
            return R.layout.adapter_waiting_item;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public int initItemType(WatchedItem model) {
            Intrinsics.checkNotNullParameter(model, "model");
            return model.getViewType();
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void registerItemType(BaseMultiTypeDelegate<WatchedItem> multiTypeDelegate) {
            Intrinsics.checkNotNullParameter(multiTypeDelegate, "multiTypeDelegate");
            multiTypeDelegate.addItemType(1, R.layout.adapter_waiting_item);
            multiTypeDelegate.addItemType(2, R.layout.adapter_waiting_tv_item);
            multiTypeDelegate.addItemType(100, R.layout.adapter_title_item);
        }

        @Override // com.movieboxpro.android.base.BaseListFragment
        protected void getBundle(Bundle arguments) {
            this.mClass = WatchedItem.class;
            this.mPageClass = WatchedResponse.class;
            WatchedFragment watchedFragment = this;
            RefreshWatchedLiveData.INSTANCE.get().observeInFragment(watchedFragment, new WatchPlanFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WatchedFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WatchedFragment.getBundle$lambda$2(this.f$0, (Boolean) obj);
                }
            }));
            SwitchAccountLiveData.INSTANCE.get().observeInFragment(watchedFragment, new WatchPlanFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.WatchPlanFragment$WatchedFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return WatchPlanFragment.WatchedFragment.getBundle$lambda$3(this.f$0, (Boolean) obj);
                }
            }));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit getBundle$lambda$2(WatchedFragment watchedFragment, Boolean bool) {
            watchedFragment.startRefresh();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit getBundle$lambda$3(WatchedFragment watchedFragment, Boolean bool) {
            watchedFragment.startRefresh();
            return Unit.INSTANCE;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public List<WatchedItem> getData(WatchedResponse model) {
            Intrinsics.checkNotNullParameter(model, "model");
            ArrayList<WatchedItem> list = model.getList();
            if (list != null) {
                for (WatchedItem watchedItem : list) {
                    if (watchedItem.getBox_type() == 1) {
                        watchedItem.setViewType(1);
                    } else if (watchedItem.getEpisode_info() == null) {
                        watchedItem.setViewType(1);
                    } else {
                        watchedItem.setViewType(2);
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            ArrayList<WatchedItem> list2 = model.getList();
            if (list2 != null) {
                for (WatchedItem watchedItem2 : list2) {
                    String recentFileTime = TimeUtils.INSTANCE.formatRecentFileTime(watchedItem2.getLast_play_time() * ((long) 1000));
                    if (!Intrinsics.areEqual(recentFileTime, this.lastTime)) {
                        this.lastTime = recentFileTime;
                        WatchedItem watchedItem3 = new WatchedItem(0, null, null, null, 0L, null, 0, 0, 0, 0, null, null, null, null, null, null, null, null, 0, 0, 0, 0, null, 8388607, null);
                        watchedItem3.setViewType(100);
                        watchedItem3.setLast_play_time(watchedItem2.getLast_play_time());
                        arrayList.add(watchedItem3);
                        arrayList.add(watchedItem2);
                    } else {
                        arrayList.add(watchedItem2);
                    }
                }
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.movieboxpro.android.base.BaseListFragment
        public void initHolder(BaseViewHolder helper, WatchedItem item) {
            Intrinsics.checkNotNullParameter(helper, "helper");
            Intrinsics.checkNotNullParameter(item, "item");
            if (item.getViewType() == 1) {
                TextView textView = (TextView) helper.getView(R.id.tvSeasonEpisode);
                ImageView imageView = (ImageView) helper.getView(R.id.ivTag);
                TextView textView2 = (TextView) helper.getView(R.id.tvUpdateCount);
                ProgressBar progressBar = (ProgressBar) helper.getView(R.id.progressBar);
                ImageView imageView2 = (ImageView) helper.getView(R.id.ivPoster);
                TextView textView3 = (TextView) helper.getView(R.id.tvTitle);
                if (item.getBox_type() == 1) {
                    CommonExtKt.gone(textView);
                    CommonExtKt.gone(textView2);
                    CommonExtKt.visible(imageView);
                    imageView.setImageResource(CommonUtils.getMovieTag(item.getQuality_tag_new()));
                    CommonExtKt.gone(progressBar);
                } else {
                    CommonExtKt.gone(textView);
                    TextView textView4 = textView2;
                    CommonExtKt.visible(textView4);
                    CommonExtKt.gone(imageView);
                    if (item.getUpdateCount() == 0) {
                        CommonExtKt.gone(textView4);
                    } else {
                        CommonExtKt.visible(textView4);
                        textView2.setText(String.valueOf(item.getUpdateCount()));
                    }
                    CommonExtKt.gone(progressBar);
                }
                TextView textView5 = (TextView) helper.getView(R.id.tvTomatoMeter);
                ImageView imageView3 = (ImageView) helper.getView(R.id.ivTomato);
                imageView3.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
                if (item.getTomato_meter() == 0) {
                    CommonExtKt.gone(textView5);
                    CommonExtKt.gone(imageView3);
                } else {
                    CommonExtKt.visible(textView5);
                    CommonExtKt.visible(imageView3);
                    CommonExtKt.textShadow$default(textView5, item.getTomato_meter() + "%", 0, 0, 6, null);
                }
                ImageView imageView4 = (ImageView) helper.getView(R.id.ivStar);
                TextView textView6 = (TextView) helper.getView(R.id.tvImdbRating);
                if (Intrinsics.areEqual(item.getImdb_rating(), "0")) {
                    CommonExtKt.gone(imageView4);
                    CommonExtKt.gone(textView6);
                } else {
                    CommonExtKt.visible(imageView4);
                    CommonExtKt.visible(textView6);
                    CommonExtKt.textShadow$default(textView6, String.valueOf(item.getImdb_rating()), 0, 0, 6, null);
                }
                textView3.setText(item.getTitle());
                GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), imageView2, 8);
                helper.setGone(R.id.ivContinue, true);
                return;
            }
            if (item.getViewType() == 2) {
                ProgressBar progressBar2 = (ProgressBar) helper.getView(R.id.progressBar);
                ImageView imageView5 = (ImageView) helper.getView(R.id.ivPoster);
                TextView textView7 = (TextView) helper.getView(R.id.tvTitle);
                CommonExtKt.gone(progressBar2);
                WatchedEpisode episode_info = item.getEpisode_info();
                Intrinsics.checkNotNull(episode_info);
                String str = CommonExtKt.format2(episode_info.getSeason());
                WatchedEpisode episode_info2 = item.getEpisode_info();
                Intrinsics.checkNotNull(episode_info2);
                int episode = episode_info2.getEpisode();
                WatchedEpisode episode_info3 = item.getEpisode_info();
                Intrinsics.checkNotNull(episode_info3);
                textView7.setText(ExifInterface.LATITUDE_SOUTH + str + ExifInterface.LONGITUDE_EAST + episode + " " + episode_info3.getTitle());
                GlideUtils.loadWithCorner(getContext(), item.getEpisode_info().getThumbs(), imageView5, CommonExtKt.dp2Px(8));
                GlideUtils.loadCornerPortraitHolder(getContext(), item.getPoster(), (ImageView) helper.getView(R.id.ivTvPoster), 8);
                helper.setGone(R.id.ivContinue, true);
                TextView textView8 = (TextView) helper.getView(R.id.tvTomatoMeter);
                ImageView imageView6 = (ImageView) helper.getView(R.id.ivTomato);
                imageView6.setImageResource(CommonUtils.getTomatoImg(item.getTomato_meter()));
                if (item.getTomato_meter() == 0) {
                    CommonExtKt.gone(textView8);
                    CommonExtKt.gone(imageView6);
                } else {
                    CommonExtKt.visible(textView8);
                    CommonExtKt.visible(imageView6);
                    CommonExtKt.textShadow$default(textView8, item.getTomato_meter() + "%", 0, 0, 6, null);
                }
                ImageView imageView7 = (ImageView) helper.getView(R.id.ivStar);
                TextView textView9 = (TextView) helper.getView(R.id.tvImdbRating);
                if (Intrinsics.areEqual(item.getImdb_rating(), "0")) {
                    CommonExtKt.gone(imageView7);
                    CommonExtKt.gone(textView9);
                } else {
                    CommonExtKt.visible(imageView7);
                    CommonExtKt.visible(textView9);
                    CommonExtKt.textShadow$default(textView9, String.valueOf(item.getImdb_rating()), 0, 0, 6, null);
                }
                ImageView imageView8 = (ImageView) helper.getView(R.id.ivEpisodeStar);
                TextView textView10 = (TextView) helper.getView(R.id.tvEpisodeImdbRating);
                if (Intrinsics.areEqual(item.getEpisode_info().getImdb_rating(), "0")) {
                    CommonExtKt.gone(imageView8);
                    CommonExtKt.gone(textView10);
                    return;
                } else {
                    CommonExtKt.visible(imageView8);
                    CommonExtKt.visible(textView10);
                    CommonExtKt.textShadow$default(textView10, String.valueOf(item.getEpisode_info().getImdb_rating()), 0, 0, 6, null);
                    return;
                }
            }
            if (item.getViewType() == 100) {
                CommonExtKt.gone(helper.getView(R.id.view));
                TextView textView11 = (TextView) helper.getView(R.id.tvTitle);
                textView11.setTextColor(CommonExtKt.colorInt(R.color.white_87alpha));
                textView11.setText(TimeUtils.INSTANCE.formatRecentFileTime(item.getLast_play_time() * ((long) 1000)));
            }
        }
    }
}
