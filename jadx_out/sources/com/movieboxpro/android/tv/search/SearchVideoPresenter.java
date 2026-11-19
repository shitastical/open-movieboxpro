package com.movieboxpro.android.tv.search;

import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.search.SearchWordList;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SearchVideoPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\f\u001a\u00020\tH\u0016¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/tv/search/SearchVideoPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/tv/search/SearchVideoContract$View;", "Lcom/movieboxpro/android/tv/search/SearchVideoContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "requestHintKeyword", "", "keyword", "", "requestHistoryHot", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SearchVideoPresenter extends BasePresenter<SearchVideoContract.View> implements SearchVideoContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SearchVideoPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.tv.search.SearchVideoContract.Presenter
    public void requestHintKeyword(String keyword) {
        ((ObservableSubscribeProxy) Http.getService().Autocomplate(API.Search.AUTOCOMPLATE, keyword, "15").compose(RxUtils.rxTranslate2List(String.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends String>>() { // from class: com.movieboxpro.android.tv.search.SearchVideoPresenter.requestHintKeyword.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public /* bridge */ /* synthetic */ void onSuccess(List<? extends String> list) {
                onSuccess2((List<String>) list);
            }

            /* JADX INFO: renamed from: onSuccess, reason: avoid collision after fix types in other method */
            public void onSuccess2(List<String> model) {
                SearchVideoPresenter.this.getView().showHintKeyword(model);
            }
        });
    }

    @Override // com.movieboxpro.android.tv.search.SearchVideoContract.Presenter
    public void requestHistoryHot() {
        Observable<R> observableCompose = Http.getService().MySearch_Record(API.Search.MY_SEARCH_RECORD, "get", App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "20", "", BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(SearchWordList.class));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.search.SearchVideoPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SearchVideoPresenter.requestHistoryHot$lambda$1((List) obj);
            }
        };
        ((ObservableSubscribeProxy) Observable.zip(observableCompose.map(new Function() { // from class: com.movieboxpro.android.tv.search.SearchVideoPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return SearchVideoPresenter.requestHistoryHot$lambda$2(function1, obj);
            }
        }), Http.getService().Search_Hot(API.Search.SEARCH_HOT, BuildConfig.CHANNEL, "6").compose(RxUtils.rxTranslate2List(String.class)), new BiFunction() { // from class: com.movieboxpro.android.tv.search.SearchVideoPresenter$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return SearchVideoPresenter.requestHistoryHot$lambda$3((List) obj, (List) obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<HashMap<String, List<? extends String>>>() { // from class: com.movieboxpro.android.tv.search.SearchVideoPresenter.requestHistoryHot.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public /* bridge */ /* synthetic */ void onSuccess(HashMap<String, List<? extends String>> map) {
                onSuccess2((HashMap<String, List<String>>) map);
            }

            /* JADX INFO: renamed from: onSuccess, reason: avoid collision after fix types in other method */
            public void onSuccess2(HashMap<String, List<String>> model) {
                SearchVideoPresenter.this.getView().showKeyWord(model != null ? model.get("history") : null, model != null ? model.get("hot") : null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList requestHistoryHot$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ArrayList) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList requestHistoryHot$lambda$1(List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        ArrayList arrayList = new ArrayList();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            arrayList.add(((SearchWordList) it2.next()).keyword);
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final HashMap requestHistoryHot$lambda$3(List t1, List t2) {
        Intrinsics.checkNotNullParameter(t1, "t1");
        Intrinsics.checkNotNullParameter(t2, "t2");
        HashMap map = new HashMap();
        HashMap map2 = map;
        map2.put("history", t1);
        map2.put("hot", t2);
        return map;
    }
}
