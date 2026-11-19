package com.movieboxpro.android.tv.home;

import android.animation.Animator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import androidx.fragment.app.FragmentManager;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.alibaba.fastjson.JSONObject;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.BaseLazyFragment;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.listener.OnVideoListFocusListener;
import com.movieboxpro.android.model.CountryResponse;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.SpecialFilterModel;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.view.dialog.FilterDialogFragment;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.FragmentMoviesBinding;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: TvShowsFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000f\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J&\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u001a\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0006\u0010\"\u001a\u00020 J\b\u0010#\u001a\u00020 H\u0002J\b\u0010$\u001a\u00020 H\u0002J\b\u0010%\u001a\u00020 H\u0002J\b\u0010&\u001a\u00020 H\u0002J\b\u0010'\u001a\u00020 H\u0016J\b\u0010(\u001a\u00020 H\u0016J\b\u0010+\u001a\u00020 H\u0002J\b\u0010,\u001a\u00020 H\u0002JH\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020/2\u0006\u00102\u001a\u00020/2\u0006\u00103\u001a\u00020/2\u0006\u00104\u001a\u00020/2\u0006\u00105\u001a\u00020/2\u0006\u00106\u001a\u00020/H\u0016J\u0018\u00107\u001a\u00020 2\u0006\u00108\u001a\u00020*2\u0006\u00109\u001a\u00020/H\u0016J\b\u0010:\u001a\u00020 H\u0002J\b\u0010;\u001a\u00020 H\u0002J\b\u0010<\u001a\u00020 H\u0002J\b\u0010=\u001a\u00020 H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00120\fj\b\u0012\u0004\u0012\u00020\u0012`\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020*X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006>"}, d2 = {"Lcom/movieboxpro/android/tv/home/TvShowsFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "Lcom/movieboxpro/android/view/dialog/FilterDialogFragment$OnFilterDoneListener;", "Lcom/movieboxpro/android/tv/home/SpecialFilterFragment$OnTopFilterClickListener;", "Lcom/movieboxpro/android/listener/OnVideoListFocusListener;", "<init>", "()V", "fragment", "Lcom/movieboxpro/android/tv/home/TvShowsListFragment;", "hud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "geners", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/common/Gener;", "Lkotlin/collections/ArrayList;", "filterFragment", "Lcom/movieboxpro/android/view/dialog/FilterDialogFragment;", "countryList", "Lcom/movieboxpro/android/model/FilterCountry;", "specialFilterFragment", "Lcom/movieboxpro/android/tv/home/SpecialFilterFragment;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentMoviesBinding;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "refreshData", "initData", "initListener", "requestCountry", "openFilter", "onFocusUp", "onFocus", "isAnimation", "", "showTopFilter", "hideTopFilter", "onFilterDone", "sort", "", "year", "gener", "rating", "quality", "country", "imdbRating", "tomatoMeter", "onFilterClicked", "selected", "id", "requestGener", "showLoading", "hideLoading", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvShowsFragment extends BaseLazyFragment implements FilterDialogFragment.OnFilterDoneListener, SpecialFilterFragment.OnTopFilterClickListener, OnVideoListFocusListener {
    private FragmentMoviesBinding binding;
    private FilterDialogFragment filterFragment;
    private TvShowsListFragment fragment;
    private KProgressHUD hud;
    private boolean isAnimation;
    private SpecialFilterFragment specialFilterFragment;
    private ArrayList<Gener> geners = new ArrayList<>();
    private ArrayList<FilterCountry> countryList = new ArrayList<>();

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMoviesBinding fragmentMoviesBindingInflate = FragmentMoviesBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentMoviesBindingInflate, "inflate(...)");
        this.binding = fragmentMoviesBindingInflate;
        if (fragmentMoviesBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBindingInflate = null;
        }
        return fragmentMoviesBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    public final void refreshData() {
        TvShowsListFragment tvShowsListFragment = this.fragment;
        if (tvShowsListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            tvShowsListFragment = null;
        }
        tvShowsListFragment.startRefresh();
    }

    private final void initData() {
        requestGener();
    }

    private final void initListener() {
        FragmentMoviesBinding fragmentMoviesBinding = this.binding;
        FragmentMoviesBinding fragmentMoviesBinding2 = null;
        if (fragmentMoviesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBinding = null;
        }
        fragmentMoviesBinding.clFilter.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return TvShowsFragment.initListener$lambda$0(view, i, keyEvent);
            }
        });
        FragmentMoviesBinding fragmentMoviesBinding3 = this.binding;
        if (fragmentMoviesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMoviesBinding2 = fragmentMoviesBinding3;
        }
        fragmentMoviesBinding2.clFilter.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TvShowsFragment.initListener$lambda$1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$0(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 || i != 19) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$1(TvShowsFragment tvShowsFragment, View view) {
        if (tvShowsFragment.countryList.isEmpty()) {
            tvShowsFragment.requestCountry();
        } else {
            tvShowsFragment.openFilter();
        }
    }

    private final void requestCountry() {
        final String string = PrefsUtils.getInstance().getString(Constant.Prefs.MOVIE_COUNTRY);
        if (string != null) {
            if (this.countryList.isEmpty()) {
                Observable<R> observableCompose = HttpRequest.INSTANCE.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
                Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
                final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TvShowsFragment.requestCountry$lambda$7((String) obj);
                    }
                };
                Object objAs = observableCompose.map(new Function() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda1
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return TvShowsFragment.requestCountry$lambda$8(function1, obj);
                    }
                }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TvShowsFragment.requestCountry$lambda$9((PrefsUtils) obj);
                    }
                }, 15, null);
            }
            Observable observableJust = Observable.just(string);
            final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvShowsFragment.requestCountry$lambda$10(this.f$0, string, (String) obj);
                }
            };
            Object objAs2 = observableJust.map(new Function() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda4
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return TvShowsFragment.requestCountry$lambda$11(function2, obj);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(objAs2, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs2, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return TvShowsFragment.requestCountry$lambda$12(this.f$0, (Unit) obj);
                }
            }, 15, null);
            return;
        }
        Observable<R> observableCompose2 = HttpRequest.INSTANCE.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose2, "compose(...)");
        final Function1 function3 = new Function1() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TvShowsFragment.requestCountry$lambda$2((String) obj);
            }
        };
        Object objAs3 = observableCompose2.map(new Function() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TvShowsFragment.requestCountry$lambda$3(function3, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs3, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs3, new Function1() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TvShowsFragment.requestCountry$lambda$4(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TvShowsFragment.requestCountry$lambda$5(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TvShowsFragment.requestCountry$lambda$6(this.f$0, (CountryResponse) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CountryResponse requestCountry$lambda$3(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (CountryResponse) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CountryResponse requestCountry$lambda$2(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        PrefsUtils.getInstance().putString(Constant.Prefs.MOVIE_COUNTRY, it);
        return (CountryResponse) JSONObject.parseObject(it, CountryResponse.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$5(TvShowsFragment tvShowsFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        tvShowsFragment.showLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$6(TvShowsFragment tvShowsFragment, CountryResponse countryResponse) {
        tvShowsFragment.hideLoading();
        ArrayList<FilterCountry> country_list = countryResponse.getCountry_list();
        Intrinsics.checkNotNullExpressionValue(country_list, "getCountry_list(...)");
        tvShowsFragment.countryList = country_list;
        tvShowsFragment.openFilter();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$4(TvShowsFragment tvShowsFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        tvShowsFragment.hideLoading();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PrefsUtils requestCountry$lambda$8(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (PrefsUtils) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PrefsUtils requestCountry$lambda$7(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrefsUtils.getInstance().putString(Constant.Prefs.MOVIE_COUNTRY, it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$9(PrefsUtils prefsUtils) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$11(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$10(TvShowsFragment tvShowsFragment, String str, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList<FilterCountry> country_list = ((CountryResponse) JSONObject.parseObject(str, CountryResponse.class)).getCountry_list();
        Intrinsics.checkNotNullExpressionValue(country_list, "getCountry_list(...)");
        tvShowsFragment.countryList = country_list;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$12(TvShowsFragment tvShowsFragment, Unit unit) {
        tvShowsFragment.openFilter();
        return Unit.INSTANCE;
    }

    private final void openFilter() {
        FilterDialogFragment filterDialogFragment = this.filterFragment;
        if (filterDialogFragment != null) {
            if (filterDialogFragment != null) {
                filterDialogFragment.show(getChildFragmentManager(), "FilterDialogFragment");
                return;
            }
            return;
        }
        FilterDialogFragment filterDialogFragmentNewInstance = FilterDialogFragment.INSTANCE.newInstance(false);
        this.filterFragment = filterDialogFragmentNewInstance;
        if (filterDialogFragmentNewInstance != null) {
            filterDialogFragmentNewInstance.setGener(this.geners);
        }
        FilterDialogFragment filterDialogFragment2 = this.filterFragment;
        Intrinsics.checkNotNull(filterDialogFragment2);
        filterDialogFragment2.setCountry(this.countryList);
        FilterDialogFragment filterDialogFragment3 = this.filterFragment;
        if (filterDialogFragment3 != null) {
            filterDialogFragment3.setOnListener(this);
        }
        FilterDialogFragment filterDialogFragment4 = this.filterFragment;
        if (filterDialogFragment4 != null) {
            filterDialogFragment4.show(getChildFragmentManager(), "FilterDialogFragment");
        }
    }

    @Override // com.movieboxpro.android.listener.OnVideoListFocusListener
    public void onFocusUp() {
        showTopFilter();
        SpecialFilterFragment specialFilterFragment = this.specialFilterFragment;
        if (specialFilterFragment != null) {
            specialFilterFragment.requestFocus();
        }
    }

    @Override // com.movieboxpro.android.listener.OnVideoListFocusListener
    public void onFocus() {
        hideTopFilter();
    }

    private final void showTopFilter() {
        if (this.isAnimation) {
            return;
        }
        FragmentMoviesBinding fragmentMoviesBinding = this.binding;
        FragmentMoviesBinding fragmentMoviesBinding2 = null;
        if (fragmentMoviesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBinding = null;
        }
        if (fragmentMoviesBinding.llTopFilter.getVisibility() == 0) {
            return;
        }
        FragmentMoviesBinding fragmentMoviesBinding3 = this.binding;
        if (fragmentMoviesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBinding3 = null;
        }
        LinearLayout llTopFilter = fragmentMoviesBinding3.llTopFilter;
        Intrinsics.checkNotNullExpressionValue(llTopFilter, "llTopFilter");
        CommonExtKt.visible(llTopFilter);
        FragmentMoviesBinding fragmentMoviesBinding4 = this.binding;
        if (fragmentMoviesBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBinding4 = null;
        }
        ViewPropertyAnimator listener = fragmentMoviesBinding4.container.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment.showTopFilter.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                TvShowsFragment.this.isAnimation = true;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                FragmentMoviesBinding fragmentMoviesBinding5 = TvShowsFragment.this.binding;
                FragmentMoviesBinding fragmentMoviesBinding6 = null;
                if (fragmentMoviesBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMoviesBinding5 = null;
                }
                ViewGroup.LayoutParams layoutParams = fragmentMoviesBinding5.container.getLayoutParams();
                FragmentMoviesBinding fragmentMoviesBinding7 = TvShowsFragment.this.binding;
                if (fragmentMoviesBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMoviesBinding7 = null;
                }
                int height = fragmentMoviesBinding7.container.getHeight();
                FragmentMoviesBinding fragmentMoviesBinding8 = TvShowsFragment.this.binding;
                if (fragmentMoviesBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMoviesBinding8 = null;
                }
                layoutParams.height = height - fragmentMoviesBinding8.llTopFilter.getHeight();
                FragmentMoviesBinding fragmentMoviesBinding9 = TvShowsFragment.this.binding;
                if (fragmentMoviesBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentMoviesBinding6 = fragmentMoviesBinding9;
                }
                fragmentMoviesBinding6.container.setLayoutParams(layoutParams);
                TvShowsFragment.this.isAnimation = false;
            }
        });
        FragmentMoviesBinding fragmentMoviesBinding5 = this.binding;
        if (fragmentMoviesBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMoviesBinding2 = fragmentMoviesBinding5;
        }
        listener.translationYBy(fragmentMoviesBinding2.llTopFilter.getHeight());
    }

    private final void hideTopFilter() {
        if (this.isAnimation) {
            return;
        }
        FragmentMoviesBinding fragmentMoviesBinding = this.binding;
        FragmentMoviesBinding fragmentMoviesBinding2 = null;
        if (fragmentMoviesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBinding = null;
        }
        if (fragmentMoviesBinding.llTopFilter.getVisibility() == 4) {
            return;
        }
        FragmentMoviesBinding fragmentMoviesBinding3 = this.binding;
        if (fragmentMoviesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBinding3 = null;
        }
        ViewPropertyAnimator listener = fragmentMoviesBinding3.container.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment.hideTopFilter.1
            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationRepeat(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                TvShowsFragment.this.isAnimation = true;
                FragmentMoviesBinding fragmentMoviesBinding4 = TvShowsFragment.this.binding;
                FragmentMoviesBinding fragmentMoviesBinding5 = null;
                if (fragmentMoviesBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMoviesBinding4 = null;
                }
                ViewGroup.LayoutParams layoutParams = fragmentMoviesBinding4.container.getLayoutParams();
                FragmentMoviesBinding fragmentMoviesBinding6 = TvShowsFragment.this.binding;
                if (fragmentMoviesBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMoviesBinding6 = null;
                }
                int height = fragmentMoviesBinding6.container.getHeight();
                FragmentMoviesBinding fragmentMoviesBinding7 = TvShowsFragment.this.binding;
                if (fragmentMoviesBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMoviesBinding7 = null;
                }
                layoutParams.height = height + fragmentMoviesBinding7.llTopFilter.getHeight();
                FragmentMoviesBinding fragmentMoviesBinding8 = TvShowsFragment.this.binding;
                if (fragmentMoviesBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentMoviesBinding5 = fragmentMoviesBinding8;
                }
                fragmentMoviesBinding5.container.setLayoutParams(layoutParams);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                FragmentMoviesBinding fragmentMoviesBinding4 = TvShowsFragment.this.binding;
                if (fragmentMoviesBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMoviesBinding4 = null;
                }
                LinearLayout llTopFilter = fragmentMoviesBinding4.llTopFilter;
                Intrinsics.checkNotNullExpressionValue(llTopFilter, "llTopFilter");
                CommonExtKt.invisible(llTopFilter);
                TvShowsFragment.this.isAnimation = false;
            }
        });
        FragmentMoviesBinding fragmentMoviesBinding4 = this.binding;
        if (fragmentMoviesBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMoviesBinding2 = fragmentMoviesBinding4;
        }
        listener.translationYBy(-fragmentMoviesBinding2.llTopFilter.getHeight());
    }

    @Override // com.movieboxpro.android.view.dialog.FilterDialogFragment.OnFilterDoneListener
    public void onFilterDone(String sort, String year, String gener, String rating, String quality, String country, String imdbRating, String tomatoMeter) {
        Intrinsics.checkNotNullParameter(sort, "sort");
        Intrinsics.checkNotNullParameter(year, "year");
        Intrinsics.checkNotNullParameter(gener, "gener");
        Intrinsics.checkNotNullParameter(rating, "rating");
        Intrinsics.checkNotNullParameter(quality, "quality");
        Intrinsics.checkNotNullParameter(country, "country");
        Intrinsics.checkNotNullParameter(imdbRating, "imdbRating");
        Intrinsics.checkNotNullParameter(tomatoMeter, "tomatoMeter");
        TvShowsListFragment tvShowsListFragment = this.fragment;
        FragmentMoviesBinding fragmentMoviesBinding = null;
        if (tvShowsListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            tvShowsListFragment = null;
        }
        tvShowsListFragment.refreshData(sort, year, gener, rating, country, imdbRating, tomatoMeter);
        SpecialFilterFragment specialFilterFragment = this.specialFilterFragment;
        if (specialFilterFragment != null) {
            specialFilterFragment.resetFilter();
        }
        if (StringsKt.isBlank(year) && StringsKt.isBlank(gener) && StringsKt.isBlank(rating) && StringsKt.isBlank(quality) && StringsKt.isBlank(country) && Intrinsics.areEqual(sort, "released")) {
            FragmentMoviesBinding fragmentMoviesBinding2 = this.binding;
            if (fragmentMoviesBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentMoviesBinding = fragmentMoviesBinding2;
            }
            fragmentMoviesBinding.imageView6.setImageResource(R.mipmap.ic_filter);
            return;
        }
        FragmentMoviesBinding fragmentMoviesBinding3 = this.binding;
        if (fragmentMoviesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMoviesBinding = fragmentMoviesBinding3;
        }
        fragmentMoviesBinding.imageView6.setImageResource(R.mipmap.ic_filter_select);
    }

    @Override // com.movieboxpro.android.tv.home.SpecialFilterFragment.OnTopFilterClickListener
    public void onFilterClicked(boolean selected, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        TvShowsListFragment tvShowsListFragment = this.fragment;
        if (tvShowsListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            tvShowsListFragment = null;
        }
        tvShowsListFragment.refreshData(selected, id);
    }

    private final void requestGener() {
        ((ObservableSubscribeProxy) Observable.zip(Http.getService().Top_list("Top_list", 2).compose(RxUtils.rxTranslate2List(SpecialFilterModel.class)), Http.getService().Cat_list("Cat_list").compose(RxUtils.rxTranslate2List(Gener.class)), new BiFunction() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return TvShowsFragment.requestGener$lambda$13((List) obj, (List) obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<? extends List<? extends SpecialFilterModel>, ? extends List<? extends Gener>>>() { // from class: com.movieboxpro.android.tv.home.TvShowsFragment.requestGener.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                TvShowsFragment.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<? extends List<? extends SpecialFilterModel>, ? extends List<? extends Gener>> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                TvShowsFragment.this.geners.addAll(model.getSecond());
                TvShowsFragment.this.specialFilterFragment = new SpecialFilterFragment();
                SpecialFilterFragment specialFilterFragment = TvShowsFragment.this.specialFilterFragment;
                Intrinsics.checkNotNull(specialFilterFragment);
                specialFilterFragment.setTopFilterClickListener(TvShowsFragment.this);
                SpecialFilterFragment specialFilterFragment2 = TvShowsFragment.this.specialFilterFragment;
                Intrinsics.checkNotNull(specialFilterFragment2);
                specialFilterFragment2.setData(model.getFirst());
                FragmentManager childFragmentManager = TvShowsFragment.this.getChildFragmentManager();
                SpecialFilterFragment specialFilterFragment3 = TvShowsFragment.this.specialFilterFragment;
                Intrinsics.checkNotNull(specialFilterFragment3);
                FragmentUtils.add(childFragmentManager, specialFilterFragment3, R.id.specialFrameLayout);
                TvShowsFragment.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                TvShowsFragment.this.hideLoading();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Pair requestGener$lambda$13(List t1, List t2) {
        Intrinsics.checkNotNullParameter(t1, "t1");
        Intrinsics.checkNotNullParameter(t2, "t2");
        return new Pair(t1, t2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLoading() {
        FragmentMoviesBinding fragmentMoviesBinding = this.binding;
        FragmentMoviesBinding fragmentMoviesBinding2 = null;
        if (fragmentMoviesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBinding = null;
        }
        FrameLayout flLoading = fragmentMoviesBinding.flLoading;
        Intrinsics.checkNotNullExpressionValue(flLoading, "flLoading");
        CommonExtKt.visible(flLoading);
        FragmentMoviesBinding fragmentMoviesBinding3 = this.binding;
        if (fragmentMoviesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMoviesBinding2 = fragmentMoviesBinding3;
        }
        ProgressBar progressBar = fragmentMoviesBinding2.progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoading() {
        FragmentMoviesBinding fragmentMoviesBinding = this.binding;
        FragmentMoviesBinding fragmentMoviesBinding2 = null;
        if (fragmentMoviesBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMoviesBinding = null;
        }
        FrameLayout flLoading = fragmentMoviesBinding.flLoading;
        Intrinsics.checkNotNullExpressionValue(flLoading, "flLoading");
        CommonExtKt.gone(flLoading);
        FragmentMoviesBinding fragmentMoviesBinding3 = this.binding;
        if (fragmentMoviesBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMoviesBinding2 = fragmentMoviesBinding3;
        }
        ProgressBar progressBar = fragmentMoviesBinding2.progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
    }

    private final void initView() {
        this.hud = KProgressHUD.create(requireContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f);
        TvShowsListFragment tvShowsListFragment = new TvShowsListFragment();
        this.fragment = tvShowsListFragment;
        tvShowsListFragment.setFocusUpListener(this);
        FragmentManager childFragmentManager = getChildFragmentManager();
        TvShowsListFragment tvShowsListFragment2 = this.fragment;
        if (tvShowsListFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            tvShowsListFragment2 = null;
        }
        FragmentUtils.add(childFragmentManager, tvShowsListFragment2, R.id.frameLayout);
    }
}
