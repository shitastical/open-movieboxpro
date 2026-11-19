package com.movieboxpro.android.view.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.fragment.app.FragmentManager;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.mvp.BaseMvpFragment;
import com.movieboxpro.android.event.ListFocusUpEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.livedata.PrivateModeChangedLiveData;
import com.movieboxpro.android.livedata.SearchFilterLiveData;
import com.movieboxpro.android.model.CountryResponse;
import com.movieboxpro.android.model.FilterCountry;
import com.movieboxpro.android.model.common.Gener;
import com.movieboxpro.android.tv.search.SearchVideoContract;
import com.movieboxpro.android.tv.search.SearchVideoPresenter;
import com.movieboxpro.android.tv.search.SearchVideoResultFragment;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.FragmentUtils;
import com.movieboxpro.android.utils.InputMethodUtils;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.android.utils.tool.SPStaticUtils;
import com.movieboxpro.android.view.dialog.FilterSearchDialogFragment;
import com.movieboxpro.androidtv.R;
import com.movieboxpro.androidtv.databinding.ActivitySearchVideoBinding;
import com.owen.tvrecyclerview.widget.TvRecyclerView;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: SearchFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u00020\u0004B\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010\u001a\u001a\u00020\u0002H\u0014J\b\u0010\u001b\u001a\u00020\u001cH\u0014J\b\u0010\u001d\u001a\u00020\u0019H\u0002J\b\u0010\u001e\u001a\u00020\u0019H\u0002J\b\u0010\u001f\u001a\u00020\u0019H\u0014J\u0010\u0010 \u001a\u00020\u00192\u0006\u0010!\u001a\u00020\tH\u0002J\b\u0010\"\u001a\u00020\u0019H\u0014J\b\u0010#\u001a\u00020\u0019H\u0014J\b\u0010$\u001a\u00020\u0019H\u0014J(\u0010%\u001a\u00020\u00192\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010'2\u000e\u0010(\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010'H\u0016J\u0006\u0010)\u001a\u00020*J\u0018\u0010+\u001a\u00020\u00192\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010'H\u0016R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00140\u0013j\b\u0012\u0004\u0012\u00020\u0014`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0012\u0012\u0004\u0012\u00020\u00170\u0013j\b\u0012\u0004\u0012\u00020\u0017`\u0015X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/movieboxpro/android/view/fragment/SearchFragment;", "Lcom/movieboxpro/android/base/mvp/BaseMvpFragment;", "Lcom/movieboxpro/android/tv/search/SearchVideoPresenter;", "Lcom/movieboxpro/androidtv/databinding/ActivitySearchVideoBinding;", "Lcom/movieboxpro/android/tv/search/SearchVideoContract$View;", "<init>", "()V", "historyAdapter", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "hotSearchAdapter", "hintKeywordAdapter", "searResultFragment", "Lcom/movieboxpro/android/tv/search/SearchVideoResultFragment;", "currKeyword", "lastChangedTime", "", "geners", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/common/Gener;", "Lkotlin/collections/ArrayList;", "countryList", "Lcom/movieboxpro/android/model/FilterCountry;", "requestGener", "", "bindPresenter", "bindLayout", "", "openFilter", "requestCountry", "initListener", "searchVideo", "keyword", "initData", "initView", "loadData", "showKeyWord", "history", "", "hotSearch", "needCheckBackPressed", "", "showHintKeyword", "keywords", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SearchFragment extends BaseMvpFragment<SearchVideoPresenter, ActivitySearchVideoBinding> implements SearchVideoContract.View {
    private BaseQuickAdapter<String, BaseViewHolder> hintKeywordAdapter;
    private BaseQuickAdapter<String, BaseViewHolder> historyAdapter;
    private BaseQuickAdapter<String, BaseViewHolder> hotSearchAdapter;
    private long lastChangedTime;
    private SearchVideoResultFragment searResultFragment;
    private String currKeyword = "";
    private ArrayList<Gener> geners = new ArrayList<>();
    private ArrayList<FilterCountry> countryList = new ArrayList<>();

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$17(int i, View view) {
        return i == 130;
    }

    private final void requestGener() {
        Object objAs = Http.getService().Cat_list("Cat_list").compose(RxUtils.rxTranslate2List(Gener.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.requestGener$lambda$0(this.f$0, (List) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestGener$lambda$0(SearchFragment searchFragment, List list) {
        searchFragment.geners.addAll(list);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    public SearchVideoPresenter bindPresenter() {
        return new SearchVideoPresenter(this);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected int bindLayout() {
        return R.layout.activity_search_video;
    }

    private final void openFilter() {
        SearchVideoResultFragment searchVideoResultFragment = this.searResultFragment;
        if (searchVideoResultFragment == null || searchVideoResultFragment.getTabSelectPos() != 3) {
            SearchVideoResultFragment searchVideoResultFragment2 = this.searResultFragment;
            boolean z = searchVideoResultFragment2 != null && searchVideoResultFragment2.getTabSelectPos() == 1;
            SearchVideoResultFragment searchVideoResultFragment3 = this.searResultFragment;
            FilterSearchDialogFragment filterSearchDialogFragmentNewInstance = FilterSearchDialogFragment.INSTANCE.newInstance(z, searchVideoResultFragment3 != null && searchVideoResultFragment3.getTabSelectPos() == 0);
            filterSearchDialogFragmentNewInstance.setGener(this.geners);
            filterSearchDialogFragmentNewInstance.setCountry(this.countryList);
            filterSearchDialogFragmentNewInstance.setOnListener(new FilterSearchDialogFragment.OnFilterDoneListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.openFilter.1
                @Override // com.movieboxpro.android.view.dialog.FilterSearchDialogFragment.OnFilterDoneListener
                public void onFilterDone(String sort, String year, String gener, String rating, String quality, String country) {
                    Intrinsics.checkNotNullParameter(sort, "sort");
                    Intrinsics.checkNotNullParameter(year, "year");
                    Intrinsics.checkNotNullParameter(gener, "gener");
                    Intrinsics.checkNotNullParameter(rating, "rating");
                    Intrinsics.checkNotNullParameter(quality, "quality");
                    Intrinsics.checkNotNullParameter(country, "country");
                    HashMap map = new HashMap();
                    HashMap map2 = map;
                    map2.put("year", year);
                    map2.put(TvContractCompat.PreviewProgramColumns.COLUMN_GENRE, gener);
                    map2.put("sort", sort);
                    map2.put("rating", rating);
                    map2.put("quality", quality);
                    map2.put("country", country);
                    String jSONString = JSONObject.toJSONString(map);
                    SearchVideoResultFragment searchVideoResultFragment4 = SearchFragment.this.searResultFragment;
                    if (searchVideoResultFragment4 == null || searchVideoResultFragment4.getTabSelectPos() != 0) {
                        SearchVideoResultFragment searchVideoResultFragment5 = SearchFragment.this.searResultFragment;
                        if (searchVideoResultFragment5 == null || searchVideoResultFragment5.getTabSelectPos() != 1) {
                            SearchVideoResultFragment searchVideoResultFragment6 = SearchFragment.this.searResultFragment;
                            if (searchVideoResultFragment6 != null && searchVideoResultFragment6.getTabSelectPos() == 2) {
                                SPStaticUtils.put(Constant.Prefs.SEARCH_FILTER_TV, jSONString);
                            }
                        } else {
                            SPStaticUtils.put(Constant.Prefs.SEARCH_FILTER_MOVIE, jSONString);
                        }
                    } else {
                        SPStaticUtils.put(Constant.Prefs.SEARCH_FILTER_ALL, jSONString);
                    }
                    SearchVideoResultFragment searchVideoResultFragment7 = SearchFragment.this.searResultFragment;
                    if (searchVideoResultFragment7 != null) {
                        SearchVideoResultFragment searchVideoResultFragment8 = SearchFragment.this.searResultFragment;
                        searchVideoResultFragment7.setFilter(searchVideoResultFragment8 != null ? searchVideoResultFragment8.getTabSelectPos() : 0, year, gener, sort, rating, quality, country);
                    }
                }
            });
            filterSearchDialogFragmentNewInstance.show(getChildFragmentManager(), "FilterDialogFragment");
        }
    }

    private final void requestCountry() {
        final String string = PrefsUtils.getInstance().getString(Constant.Prefs.MOVIE_COUNTRY);
        if (string != null) {
            if (this.countryList.isEmpty()) {
                Observable<R> observableCompose = HttpRequest.INSTANCE.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
                Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
                final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchFragment.requestCountry$lambda$6((String) obj);
                    }
                };
                Object objAs = observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda8
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return SearchFragment.requestCountry$lambda$7(function1, obj);
                    }
                }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
                Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
                RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SearchFragment.requestCountry$lambda$8((PrefsUtils) obj);
                    }
                }, 15, null);
            }
            Observable observableJust = Observable.just(string);
            final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SearchFragment.requestCountry$lambda$9(this.f$0, string, (String) obj);
                }
            };
            Object objAs2 = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda12
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return SearchFragment.requestCountry$lambda$10(function2, obj);
                }
            }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
            Intrinsics.checkNotNullExpressionValue(objAs2, "as(...)");
            RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs2, null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return SearchFragment.requestCountry$lambda$11(this.f$0, (Unit) obj);
                }
            }, 15, null);
            return;
        }
        Observable<R> observableCompose2 = HttpRequest.INSTANCE.post("Movie_country_list").param("box_type", (Object) 1).asRequest().compose(RxUtils.rxTranslate2Bean(String.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose2, "compose(...)");
        final Function1 function3 = new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.requestCountry$lambda$1((String) obj);
            }
        };
        Object objAs3 = observableCompose2.map(new Function() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return SearchFragment.requestCountry$lambda$2(function3, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this));
        Intrinsics.checkNotNullExpressionValue(objAs3, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs3, new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.requestCountry$lambda$3(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.requestCountry$lambda$4(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.requestCountry$lambda$5(this.f$0, (CountryResponse) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CountryResponse requestCountry$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (CountryResponse) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CountryResponse requestCountry$lambda$1(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        PrefsUtils.getInstance().putString(Constant.Prefs.MOVIE_COUNTRY, it);
        return (CountryResponse) JSONObject.parseObject(it, CountryResponse.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$4(SearchFragment searchFragment, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        searchFragment.showLoading();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$5(SearchFragment searchFragment, CountryResponse countryResponse) {
        searchFragment.hideLoading();
        ArrayList<FilterCountry> country_list = countryResponse.getCountry_list();
        Intrinsics.checkNotNullExpressionValue(country_list, "getCountry_list(...)");
        searchFragment.countryList = country_list;
        searchFragment.openFilter();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$3(SearchFragment searchFragment, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        searchFragment.hideLoading();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PrefsUtils requestCountry$lambda$7(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (PrefsUtils) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final PrefsUtils requestCountry$lambda$6(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return PrefsUtils.getInstance().putString(Constant.Prefs.MOVIE_COUNTRY, it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$8(PrefsUtils prefsUtils) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$10(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$9(SearchFragment searchFragment, String str, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList<FilterCountry> country_list = ((CountryResponse) JSONObject.parseObject(str, CountryResponse.class)).getCountry_list();
        Intrinsics.checkNotNullExpressionValue(country_list, "getCountry_list(...)");
        searchFragment.countryList = country_list;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit requestCountry$lambda$11(SearchFragment searchFragment, Unit unit) {
        searchFragment.openFilter();
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initListener() {
        ((ActivitySearchVideoBinding) this.binding).ivFilter.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchFragment.initListener$lambda$12(this.f$0, view);
            }
        });
        ((ActivitySearchVideoBinding) this.binding).etSearch.addTextChangedListener(new TextWatcher() { // from class: com.movieboxpro.android.view.fragment.SearchFragment.initListener.2
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                String string = s != null ? s.toString() : null;
                String str = string;
                if (str == null || str.length() == 0) {
                    TvRecyclerView rvKeyWord = ((ActivitySearchVideoBinding) SearchFragment.this.binding).rvKeyWord;
                    Intrinsics.checkNotNullExpressionValue(rvKeyWord, "rvKeyWord");
                    CommonExtKt.gone(rvKeyWord);
                    SearchFragment.this.lastChangedTime = System.currentTimeMillis();
                    return;
                }
                if (System.currentTimeMillis() - SearchFragment.this.lastChangedTime < 500) {
                    SearchFragment.this.lastChangedTime = System.currentTimeMillis();
                } else {
                    SearchFragment.this.lastChangedTime = System.currentTimeMillis();
                    ((SearchVideoPresenter) SearchFragment.this.mPresenter).requestHintKeyword(string);
                }
            }
        });
        ((ActivitySearchVideoBinding) this.binding).etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda11
            @Override // android.widget.TextView.OnEditorActionListener
            public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                return SearchFragment.initListener$lambda$13(this.f$0, textView, i, keyEvent);
            }
        });
        ((ActivitySearchVideoBinding) this.binding).tvDoSearch.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda16
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return SearchFragment.initListener$lambda$14(view, i, keyEvent);
            }
        });
        ((ActivitySearchVideoBinding) this.binding).ivFilter.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda17
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return SearchFragment.initListener$lambda$15(view, i, keyEvent);
            }
        });
        ((ActivitySearchVideoBinding) this.binding).etSearch.setOnKeyListener(new View.OnKeyListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda18
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view, int i, KeyEvent keyEvent) {
                return SearchFragment.initListener$lambda$16(view, i, keyEvent);
            }
        });
        ((ActivitySearchVideoBinding) this.binding).rvKeyWord.setOnInBorderKeyEventListener(new TvRecyclerView.OnInBorderKeyEventListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda19
            @Override // com.owen.tvrecyclerview.widget.TvRecyclerView.OnInBorderKeyEventListener
            public final boolean onInBorderKeyEvent(int i, View view) {
                return SearchFragment.initListener$lambda$17(i, view);
            }
        });
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = this.hintKeywordAdapter;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda20
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                SearchFragment.initListener$lambda$18(this.f$0, baseQuickAdapter3, view, i);
            }
        });
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter3 = this.historyAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            baseQuickAdapter3 = null;
        }
        baseQuickAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda21
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter4, View view, int i) {
                SearchFragment.initListener$lambda$19(this.f$0, baseQuickAdapter4, view, i);
            }
        });
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter4 = this.hotSearchAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hotSearchAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        baseQuickAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda22
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter5, View view, int i) {
                SearchFragment.initListener$lambda$20(this.f$0, baseQuickAdapter5, view, i);
            }
        });
        ((ActivitySearchVideoBinding) this.binding).tvDoSearch.setOnClickListener(new View.OnClickListener() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda23
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SearchFragment.initListener$lambda$21(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$12(SearchFragment searchFragment, View view) {
        if (searchFragment.countryList.isEmpty()) {
            searchFragment.requestCountry();
        } else {
            searchFragment.openFilter();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$13(SearchFragment searchFragment, TextView textView, int i, KeyEvent keyEvent) {
        if (i != 6) {
            return false;
        }
        InputMethodUtils.hideSoftInput(((ActivitySearchVideoBinding) searchFragment.binding).etSearch);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$14(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$15(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean initListener$lambda$16(View view, int i, KeyEvent keyEvent) {
        if (i != 19 || keyEvent.getAction() != 0) {
            return false;
        }
        EventBus.getDefault().post(new ListFocusUpEvent());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$18(SearchFragment searchFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        TvRecyclerView rvKeyWord = ((ActivitySearchVideoBinding) searchFragment.binding).rvKeyWord;
        Intrinsics.checkNotNullExpressionValue(rvKeyWord, "rvKeyWord");
        CommonExtKt.gone(rvKeyWord);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = searchFragment.hintKeywordAdapter;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
            baseQuickAdapter = null;
        }
        String item = baseQuickAdapter.getItem(i);
        if (item == null) {
            item = "";
        }
        searchFragment.currKeyword = item;
        ((ActivitySearchVideoBinding) searchFragment.binding).etSearch.setText(searchFragment.currKeyword);
        ((ActivitySearchVideoBinding) searchFragment.binding).etSearch.setSelection(searchFragment.currKeyword.length());
        searchFragment.searchVideo(searchFragment.currKeyword);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter3 = searchFragment.hintKeywordAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter3;
        }
        baseQuickAdapter2.setList(new ArrayList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$19(SearchFragment searchFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = searchFragment.historyAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            baseQuickAdapter = null;
        }
        String item = baseQuickAdapter.getItem(i);
        if (item == null) {
            item = "";
        }
        searchFragment.currKeyword = item;
        ((ActivitySearchVideoBinding) searchFragment.binding).etSearch.requestFocus();
        ((ActivitySearchVideoBinding) searchFragment.binding).etSearch.setText(searchFragment.currKeyword);
        ((ActivitySearchVideoBinding) searchFragment.binding).etSearch.setSelection(searchFragment.currKeyword.length());
        searchFragment.searchVideo(searchFragment.currKeyword);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$20(SearchFragment searchFragment, BaseQuickAdapter adapter, View view, int i) {
        Intrinsics.checkNotNullParameter(adapter, "adapter");
        Intrinsics.checkNotNullParameter(view, "view");
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = searchFragment.hotSearchAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hotSearchAdapter");
            baseQuickAdapter = null;
        }
        String item = baseQuickAdapter.getItem(i);
        if (item == null) {
            item = "";
        }
        searchFragment.currKeyword = item;
        ((ActivitySearchVideoBinding) searchFragment.binding).etSearch.setText(searchFragment.currKeyword);
        ((ActivitySearchVideoBinding) searchFragment.binding).etSearch.setSelection(searchFragment.currKeyword.length());
        searchFragment.searchVideo(searchFragment.currKeyword);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void initListener$lambda$21(SearchFragment searchFragment, View view) {
        if (((ActivitySearchVideoBinding) searchFragment.binding).etSearch.getText().toString().length() == 0) {
            return;
        }
        String string = ((ActivitySearchVideoBinding) searchFragment.binding).etSearch.getText().toString();
        searchFragment.currKeyword = string;
        searchFragment.searchVideo(string);
    }

    private final void searchVideo(String keyword) {
        ((ActivitySearchVideoBinding) this.binding).etSearch.clearFocus();
        FrameLayout frameLayout = ((ActivitySearchVideoBinding) this.binding).frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
        CommonExtKt.visible(frameLayout);
        ((ActivitySearchVideoBinding) this.binding).tvDoSearch.requestFocus();
        SearchVideoResultFragment searchVideoResultFragment = this.searResultFragment;
        if (searchVideoResultFragment == null) {
            this.searResultFragment = SearchVideoResultFragment.INSTANCE.newInstance(keyword);
            FragmentManager childFragmentManager = getChildFragmentManager();
            SearchVideoResultFragment searchVideoResultFragment2 = this.searResultFragment;
            Intrinsics.checkNotNull(searchVideoResultFragment2);
            FragmentUtils.add(childFragmentManager, searchVideoResultFragment2, R.id.frameLayout);
        } else if (searchVideoResultFragment != null) {
            searchVideoResultFragment.refreshData(keyword);
        }
        ImageView ivFilter = ((ActivitySearchVideoBinding) this.binding).ivFilter;
        Intrinsics.checkNotNullExpressionValue(ivFilter, "ivFilter");
        CommonExtKt.visible(ivFilter);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initData() {
        this.historyAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_search_history_item) { // from class: com.movieboxpro.android.view.fragment.SearchFragment.initData.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, String item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvKeyword, item);
            }
        };
        this.hotSearchAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_hot_search_item) { // from class: com.movieboxpro.android.view.fragment.SearchFragment.initData.2
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, String item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvKeyword, item);
            }
        };
        this.hintKeywordAdapter = new BaseQuickAdapter<String, BaseViewHolder>(R.layout.adapter_search_hint_item) { // from class: com.movieboxpro.android.view.fragment.SearchFragment.initData.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.chad.library.adapter.base.BaseQuickAdapter
            public void convert(BaseViewHolder helper, String item) {
                Intrinsics.checkNotNullParameter(helper, "helper");
                Intrinsics.checkNotNullParameter(item, "item");
                helper.setText(R.id.tvKeyword, item);
            }
        };
        TvRecyclerView tvRecyclerView = ((ActivitySearchVideoBinding) this.binding).rvHistory;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = this.historyAdapter;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = null;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
            baseQuickAdapter = null;
        }
        tvRecyclerView.setAdapter(baseQuickAdapter);
        TvRecyclerView tvRecyclerView2 = ((ActivitySearchVideoBinding) this.binding).rvHotSearch;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter3 = this.hotSearchAdapter;
        if (baseQuickAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hotSearchAdapter");
            baseQuickAdapter3 = null;
        }
        tvRecyclerView2.setAdapter(baseQuickAdapter3);
        TvRecyclerView tvRecyclerView3 = ((ActivitySearchVideoBinding) this.binding).rvKeyWord;
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter4 = this.hintKeywordAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
        } else {
            baseQuickAdapter2 = baseQuickAdapter4;
        }
        tvRecyclerView3.setAdapter(baseQuickAdapter2);
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void initView() {
        requestGener();
        SearchFragment searchFragment = this;
        SearchFilterLiveData.INSTANCE.get().observeInFragment(searchFragment, new SearchFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.initView$lambda$22(this.f$0, (Boolean) obj);
            }
        }));
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false)) {
            TvRecyclerView rvHistory = ((ActivitySearchVideoBinding) this.binding).rvHistory;
            Intrinsics.checkNotNullExpressionValue(rvHistory, "rvHistory");
            CommonExtKt.gone(rvHistory);
        } else {
            TvRecyclerView rvHistory2 = ((ActivitySearchVideoBinding) this.binding).rvHistory;
            Intrinsics.checkNotNullExpressionValue(rvHistory2, "rvHistory");
            CommonExtKt.visible(rvHistory2);
        }
        PrivateModeChangedLiveData.INSTANCE.get().observeInFragment(searchFragment, new SearchFragment$sam$androidx_lifecycle_Observer$0(new Function1() { // from class: com.movieboxpro.android.view.fragment.SearchFragment$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchFragment.initView$lambda$23(this.f$0, (Boolean) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$22(SearchFragment searchFragment, Boolean bool) {
        if (bool.booleanValue()) {
            ((ActivitySearchVideoBinding) searchFragment.binding).ivFilter.setImageResource(R.mipmap.ic_filter);
        } else {
            ((ActivitySearchVideoBinding) searchFragment.binding).ivFilter.setImageResource(R.mipmap.ic_filter_select);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit initView$lambda$23(SearchFragment searchFragment, Boolean bool) {
        if (PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false)) {
            TvRecyclerView rvHistory = ((ActivitySearchVideoBinding) searchFragment.binding).rvHistory;
            Intrinsics.checkNotNullExpressionValue(rvHistory, "rvHistory");
            CommonExtKt.gone(rvHistory);
        } else {
            TvRecyclerView rvHistory2 = ((ActivitySearchVideoBinding) searchFragment.binding).rvHistory;
            Intrinsics.checkNotNullExpressionValue(rvHistory2, "rvHistory");
            CommonExtKt.visible(rvHistory2);
        }
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.mvp.BaseMvpFragment
    protected void loadData() {
        ((SearchVideoPresenter) this.mPresenter).requestHistoryHot();
    }

    @Override // com.movieboxpro.android.tv.search.SearchVideoContract.View
    public void showKeyWord(List<String> history, List<String> hotSearch) {
        List<String> list = history;
        if (list == null || list.isEmpty()) {
            TvRecyclerView rvHistory = ((ActivitySearchVideoBinding) this.binding).rvHistory;
            Intrinsics.checkNotNullExpressionValue(rvHistory, "rvHistory");
            CommonExtKt.gone(rvHistory);
        }
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = null;
        if ((history != null ? history.size() : 0) > 6) {
            BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter2 = this.historyAdapter;
            if (baseQuickAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
                baseQuickAdapter2 = null;
            }
            baseQuickAdapter2.setList(history != null ? history.subList(0, 6) : null);
        } else {
            BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter3 = this.historyAdapter;
            if (baseQuickAdapter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("historyAdapter");
                baseQuickAdapter3 = null;
            }
            baseQuickAdapter3.setList(list);
        }
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter4 = this.hotSearchAdapter;
        if (baseQuickAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hotSearchAdapter");
        } else {
            baseQuickAdapter = baseQuickAdapter4;
        }
        baseQuickAdapter.setList(hotSearch);
    }

    public final boolean needCheckBackPressed() {
        TvRecyclerView rvKeyWord = ((ActivitySearchVideoBinding) this.binding).rvKeyWord;
        Intrinsics.checkNotNullExpressionValue(rvKeyWord, "rvKeyWord");
        if (CommonExtKt.isVisible(rvKeyWord)) {
            TvRecyclerView rvKeyWord2 = ((ActivitySearchVideoBinding) this.binding).rvKeyWord;
            Intrinsics.checkNotNullExpressionValue(rvKeyWord2, "rvKeyWord");
            CommonExtKt.gone(rvKeyWord2);
            ((ActivitySearchVideoBinding) this.binding).etSearch.setText("");
            return true;
        }
        FrameLayout frameLayout = ((ActivitySearchVideoBinding) this.binding).frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
        if (!CommonExtKt.isVisible(frameLayout)) {
            return false;
        }
        FrameLayout frameLayout2 = ((ActivitySearchVideoBinding) this.binding).frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout2, "frameLayout");
        CommonExtKt.gone(frameLayout2);
        ((ActivitySearchVideoBinding) this.binding).frameLayout.clearFocus();
        ImageView ivFilter = ((ActivitySearchVideoBinding) this.binding).ivFilter;
        Intrinsics.checkNotNullExpressionValue(ivFilter, "ivFilter");
        CommonExtKt.gone(ivFilter);
        ((ActivitySearchVideoBinding) this.binding).etSearch.setText("");
        ((ActivitySearchVideoBinding) this.binding).etSearch.requestFocus();
        return true;
    }

    @Override // com.movieboxpro.android.tv.search.SearchVideoContract.View
    public void showHintKeyword(List<String> keywords) {
        if (Intrinsics.areEqual(((ActivitySearchVideoBinding) this.binding).etSearch.getText().toString(), this.currKeyword) || ((ActivitySearchVideoBinding) this.binding).etSearch.getText().toString().length() == 0) {
            return;
        }
        TvRecyclerView rvKeyWord = ((ActivitySearchVideoBinding) this.binding).rvKeyWord;
        Intrinsics.checkNotNullExpressionValue(rvKeyWord, "rvKeyWord");
        CommonExtKt.visible(rvKeyWord);
        FrameLayout frameLayout = ((ActivitySearchVideoBinding) this.binding).frameLayout;
        Intrinsics.checkNotNullExpressionValue(frameLayout, "frameLayout");
        CommonExtKt.gone(frameLayout);
        ImageView ivFilter = ((ActivitySearchVideoBinding) this.binding).ivFilter;
        Intrinsics.checkNotNullExpressionValue(ivFilter, "ivFilter");
        CommonExtKt.gone(ivFilter);
        BaseQuickAdapter<String, BaseViewHolder> baseQuickAdapter = this.hintKeywordAdapter;
        if (baseQuickAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("hintKeywordAdapter");
            baseQuickAdapter = null;
        }
        baseQuickAdapter.setList(keywords);
    }
}
