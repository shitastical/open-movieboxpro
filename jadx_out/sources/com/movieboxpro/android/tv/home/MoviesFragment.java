package com.movieboxpro.android.tv.home;

import android.animation.Animator;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

/* JADX INFO: compiled from: MoviesFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u000e\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J&\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u001a\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001c2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\b\u0010&\u001a\u00020$H\u0002J\u0006\u0010'\u001a\u00020$J\b\u0010(\u001a\u00020$H\u0002J\b\u0010)\u001a\u00020$H\u0002J\b\u0010*\u001a\u00020$H\u0002J\b\u0010+\u001a\u00020$H\u0016J\b\u0010,\u001a\u00020$H\u0016J\b\u0010/\u001a\u00020$H\u0002J\b\u00100\u001a\u00020$H\u0002J\u0018\u00101\u001a\u00020$2\u0006\u00102\u001a\u00020.2\u0006\u00103\u001a\u000204H\u0016JH\u00105\u001a\u00020$2\u0006\u00106\u001a\u0002042\u0006\u00107\u001a\u0002042\u0006\u00108\u001a\u0002042\u0006\u00109\u001a\u0002042\u0006\u0010:\u001a\u0002042\u0006\u0010;\u001a\u0002042\u0006\u0010<\u001a\u0002042\u0006\u0010=\u001a\u000204H\u0016J\b\u0010>\u001a\u00020$H\u0002J\b\u0010?\u001a\u00020$H\u0002J\b\u0010@\u001a\u00020$H\u0002J\b\u0010A\u001a\u00020$H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u00020\nX\u0084.¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020\u00130\u0012j\b\u0012\u0004\u0012\u00020\u0013`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0015\u001a\u0012\u0012\u0004\u0012\u00020\u00160\u0012j\b\u0012\u0004\u0012\u00020\u0016`\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020.X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006B"}, d2 = {"Lcom/movieboxpro/android/tv/home/MoviesFragment;", "Lcom/movieboxpro/android/base/BaseLazyFragment;", "Lcom/movieboxpro/android/view/dialog/FilterDialogFragment$OnFilterDoneListener;", "Lcom/movieboxpro/android/tv/home/SpecialFilterFragment$OnTopFilterClickListener;", "Lcom/movieboxpro/android/listener/OnVideoListFocusListener;", "<init>", "()V", "fragment", "Lcom/movieboxpro/android/tv/home/MoviesListFragment;", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentMoviesBinding;", "getBinding", "()Lcom/movieboxpro/androidtv/databinding/FragmentMoviesBinding;", "setBinding", "(Lcom/movieboxpro/androidtv/databinding/FragmentMoviesBinding;)V", "hud", "Lcom/kaopiz/kprogresshud/KProgressHUD;", "geners", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/common/Gener;", "Lkotlin/collections/ArrayList;", "countryList", "Lcom/movieboxpro/android/model/FilterCountry;", "filterFragment", "Lcom/movieboxpro/android/view/dialog/FilterDialogFragment;", "specialFilterFragment", "Lcom/movieboxpro/android/tv/home/SpecialFilterFragment;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "initData", "refreshData", "requestCountry", "initListener", "openFilter", "onFocusUp", "onFocus", "isAnimation", "", "showTopFilter", "hideTopFilter", "onFilterClicked", "selected", "id", "", "onFilterDone", "sort", "year", "gener", "rating", "quality", "country", "imdbRating", "tomatoMeter", "requestGener", "showLoading", "hideLoading", "initView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MoviesFragment extends BaseLazyFragment implements FilterDialogFragment.OnFilterDoneListener, SpecialFilterFragment.OnTopFilterClickListener, OnVideoListFocusListener {
    protected FragmentMoviesBinding binding;
    private FilterDialogFragment filterFragment;
    private MoviesListFragment fragment;
    private KProgressHUD hud;
    private boolean isAnimation;
    private SpecialFilterFragment specialFilterFragment;
    private ArrayList<Gener> geners = new ArrayList<>();
    private ArrayList<FilterCountry> countryList = new ArrayList<>();

    protected final FragmentMoviesBinding getBinding() {
        FragmentMoviesBinding fragmentMoviesBinding = this.binding;
        if (fragmentMoviesBinding != null) {
            return fragmentMoviesBinding;
        }
        Intrinsics.throwUninitializedPropertyAccessException("binding");
        return null;
    }

    protected final void setBinding(FragmentMoviesBinding fragmentMoviesBinding) {
        Intrinsics.checkNotNullParameter(fragmentMoviesBinding, "<set-?>");
        this.binding = fragmentMoviesBinding;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMoviesBinding fragmentMoviesBindingInflate = FragmentMoviesBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentMoviesBindingInflate, "inflate(...)");
        setBinding(fragmentMoviesBindingInflate);
        return getBinding().getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initListener();
    }

    private final void initData() {
        requestGener();
    }

    public final void refreshData() {
        MoviesListFragment moviesListFragment = this.fragment;
        if (moviesListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            moviesListFragment = null;
        }
        moviesListFragment.startRefresh();
    }

    private final void requestCountry() {
        final String string = PrefsUtils.getInstance().getString(Constant.Prefs.MOVIE_COUNTRY);
        if (string != null) {
            if (this.countryList.isEmpty()) {
                Observable<R> observableCompose = HttpRequest.INSTANCE.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
                Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
                final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda13
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MoviesFragment.requestCountry$lambda$5((String) obj);
                    }
                };
                Object objAs = observableCompose.map(new Function() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda1
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return MoviesFragment.requestCountry$lambda$6(function1, obj);
                    }
                }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MoviesFragment.requestCountry$lambda$7((PrefsUtils) obj);
                    }
                }, 15, null);
            }
            Observable observableJust = Observable.just(string);
            final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoviesFragment.requestCountry$lambda$8(this.f$0, string, (String) obj);
                }
            };
            Object objAs2 = observableJust.map(new Function() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda4
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return MoviesFragment.requestCountry$lambda$9(function2, obj);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(objAs2, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs2, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return MoviesFragment.requestCountry$lambda$10(this.f$0, (Unit) obj);
                }
            }, 15, null);
            return;
        }
        Observable<R> observableCompose2 = HttpRequest.INSTANCE.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose2, "compose(...)");
        final Function1 function3 = new Function1() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoviesFragment.requestCountry$lambda$0((String) obj);
            }
        };
        Object objAs3 = observableCompose2.map(new Function() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda9
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MoviesFragment.requestCountry$lambda$1(function3, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs3, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs3, new Function1() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoviesFragment.requestCountry$lambda$2(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoviesFragment.requestCountry$lambda$3(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MoviesFragment.requestCountry$lambda$4(this.f$0, (CountryResponse) obj);
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
    public static final Unit requestCountry$lambda$3(MoviesFragment moviesFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        moviesFragment.showLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$4(MoviesFragment moviesFragment, CountryResponse countryResponse) {
        moviesFragment.hideLoading();
        ArrayList<FilterCountry> country_list = countryResponse.getCountry_list();
        Intrinsics.checkNotNullExpressionValue(country_list, "getCountry_list(...)");
        moviesFragment.countryList = country_list;
        moviesFragment.openFilter();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$2(MoviesFragment moviesFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        moviesFragment.hideLoading();
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
    public static final Unit requestCountry$lambda$8(MoviesFragment moviesFragment, String str, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList<FilterCountry> country_list = ((CountryResponse) JSONObject.parseObject(str, CountryResponse.class)).getCountry_list();
        Intrinsics.checkNotNullExpressionValue(country_list, "getCountry_list(...)");
        moviesFragment.countryList = country_list;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$10(MoviesFragment moviesFragment, Unit unit) {
        moviesFragment.openFilter();
        return Unit.INSTANCE;
    }

    private final void initListener() {
        getBinding().clFilter.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return MoviesFragment.initListener$lambda$11(view, i, keyEvent);
            }
        });
        getBinding().clFilter.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MoviesFragment.initListener$lambda$12(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$11(View view, int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 1 || i != 19) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$12(MoviesFragment moviesFragment, View view) {
        if (moviesFragment.countryList.isEmpty()) {
            moviesFragment.requestCountry();
        } else {
            moviesFragment.openFilter();
        }
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
        FilterDialogFragment filterDialogFragmentNewInstance = FilterDialogFragment.INSTANCE.newInstance(true);
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
        if (this.isAnimation || getBinding().llTopFilter.getVisibility() == 0) {
            return;
        }
        LinearLayout llTopFilter = getBinding().llTopFilter;
        Intrinsics.checkNotNullExpressionValue(llTopFilter, "llTopFilter");
        CommonExtKt.visible(llTopFilter);
        getBinding().container.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.tv.home.MoviesFragment.showTopFilter.1
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
                MoviesFragment.this.isAnimation = true;
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                ViewGroup.LayoutParams layoutParams = MoviesFragment.this.getBinding().container.getLayoutParams();
                layoutParams.height = MoviesFragment.this.getBinding().container.getHeight() - MoviesFragment.this.getBinding().llTopFilter.getHeight();
                MoviesFragment.this.getBinding().container.setLayoutParams(layoutParams);
                MoviesFragment.this.isAnimation = false;
            }
        }).translationYBy(getBinding().llTopFilter.getHeight());
    }

    private final void hideTopFilter() {
        if (this.isAnimation || getBinding().llTopFilter.getVisibility() == 4) {
            return;
        }
        getBinding().container.animate().setInterpolator(new AccelerateDecelerateInterpolator()).setListener(new Animator.AnimatorListener() { // from class: com.movieboxpro.android.tv.home.MoviesFragment.hideTopFilter.1
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
                MoviesFragment.this.isAnimation = true;
                ViewGroup.LayoutParams layoutParams = MoviesFragment.this.getBinding().container.getLayoutParams();
                layoutParams.height = MoviesFragment.this.getBinding().container.getHeight() + MoviesFragment.this.getBinding().llTopFilter.getHeight();
                MoviesFragment.this.getBinding().container.setLayoutParams(layoutParams);
            }

            @Override // android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                LinearLayout llTopFilter = MoviesFragment.this.getBinding().llTopFilter;
                Intrinsics.checkNotNullExpressionValue(llTopFilter, "llTopFilter");
                CommonExtKt.invisible(llTopFilter);
                MoviesFragment.this.isAnimation = false;
            }
        }).translationYBy(-getBinding().llTopFilter.getHeight());
    }

    @Override // com.movieboxpro.android.tv.home.SpecialFilterFragment.OnTopFilterClickListener
    public void onFilterClicked(boolean selected, String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        MoviesListFragment moviesListFragment = this.fragment;
        if (moviesListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            moviesListFragment = null;
        }
        moviesListFragment.refreshData(selected, id);
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
        MoviesListFragment moviesListFragment = this.fragment;
        if (moviesListFragment == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            moviesListFragment = null;
        }
        moviesListFragment.refreshData(sort, year, gener, rating, quality, country, imdbRating, tomatoMeter);
        SpecialFilterFragment specialFilterFragment = this.specialFilterFragment;
        if (specialFilterFragment != null) {
            specialFilterFragment.resetFilter();
        }
        if (StringsKt.isBlank(year) && StringsKt.isBlank(gener) && StringsKt.isBlank(rating) && StringsKt.isBlank(quality) && StringsKt.isBlank(country) && Intrinsics.areEqual(sort, "released")) {
            getBinding().imageView6.setImageResource(R.mipmap.ic_filter);
        } else {
            getBinding().imageView6.setImageResource(R.mipmap.ic_filter_select);
        }
    }

    private final void requestGener() {
        ((ObservableSubscribeProxy) Observable.zip(Http.getService().Top_list("Top_list", 1).compose(RxUtils.rxTranslate2List(SpecialFilterModel.class)), Http.getService().Cat_list("Cat_list").compose(RxUtils.rxTranslate2List(Gener.class)), new BiFunction() { // from class: com.movieboxpro.android.tv.home.MoviesFragment$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return MoviesFragment.requestGener$lambda$13((List) obj, (List) obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this))).subscribe(new HttpResponseObserver<Pair<? extends List<? extends SpecialFilterModel>, ? extends List<? extends Gener>>>() { // from class: com.movieboxpro.android.tv.home.MoviesFragment.requestGener.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                MoviesFragment.this.showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Pair<? extends List<? extends SpecialFilterModel>, ? extends List<? extends Gener>> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                MoviesFragment.this.geners.addAll(model.getSecond());
                MoviesFragment.this.specialFilterFragment = new SpecialFilterFragment();
                SpecialFilterFragment specialFilterFragment = MoviesFragment.this.specialFilterFragment;
                Intrinsics.checkNotNull(specialFilterFragment);
                specialFilterFragment.setTopFilterClickListener(MoviesFragment.this);
                SpecialFilterFragment specialFilterFragment2 = MoviesFragment.this.specialFilterFragment;
                Intrinsics.checkNotNull(specialFilterFragment2);
                specialFilterFragment2.setData(model.getFirst());
                FragmentManager childFragmentManager = MoviesFragment.this.getChildFragmentManager();
                SpecialFilterFragment specialFilterFragment3 = MoviesFragment.this.specialFilterFragment;
                Intrinsics.checkNotNull(specialFilterFragment3);
                FragmentUtils.add(childFragmentManager, specialFilterFragment3, "SpecialFilterFragment", R.id.specialFrameLayout);
                MoviesFragment.this.hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                MoviesFragment.this.hideLoading();
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
        FrameLayout flLoading = getBinding().flLoading;
        Intrinsics.checkNotNullExpressionValue(flLoading, "flLoading");
        CommonExtKt.visible(flLoading);
        ProgressBar progressBar = getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.visible(progressBar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void hideLoading() {
        FrameLayout flLoading = getBinding().flLoading;
        Intrinsics.checkNotNullExpressionValue(flLoading, "flLoading");
        CommonExtKt.gone(flLoading);
        ProgressBar progressBar = getBinding().progressBar;
        Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
        CommonExtKt.gone(progressBar);
    }

    private final void initView() {
        this.hud = KProgressHUD.create(requireContext()).setStyle(KProgressHUD.Style.SPIN_INDETERMINATE).setCancellable(true).setAnimationSpeed(1).setDimAmount(0.5f);
        MoviesListFragment moviesListFragment = new MoviesListFragment();
        this.fragment = moviesListFragment;
        moviesListFragment.setFocusUpListener(this);
        FragmentManager childFragmentManager = getChildFragmentManager();
        MoviesListFragment moviesListFragment2 = this.fragment;
        if (moviesListFragment2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fragment");
            moviesListFragment2 = null;
        }
        FragmentUtils.add(childFragmentManager, moviesListFragment2, R.id.frameLayout);
    }
}
