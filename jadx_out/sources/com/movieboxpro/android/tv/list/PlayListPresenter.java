package com.movieboxpro.android.tv.list;

import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function3;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayListPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/tv/list/PlayListContract$View;", "Lcom/movieboxpro/android/tv/list/PlayListContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "requestPlayList", "", "uid", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListPresenter extends BasePresenter<PlayListContract.View> implements PlayListContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayListPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.tv.list.PlayListContract.Presenter
    public void requestPlayList(String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        ((ObservableSubscribeProxy) Observable.zip(Http.getService().Playlists_list(API.Common.PLAY_LIST, uid, "mine", 1, 10, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(MovieListModel.MovieListItem.class)), Http.getService().Playlists_list(API.Common.PLAY_LIST, uid, "collect", 1, 10, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(MovieListModel.MovieListItem.class)), Http.getService().Playlists_list(API.Common.PLAY_LIST, uid, TtmlNode.COMBINE_ALL, 1, 10, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(MovieListModel.class)), new Function3() { // from class: com.movieboxpro.android.tv.list.PlayListPresenter$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Function3
            public final Object apply(Object obj, Object obj2, Object obj3) {
                return PlayListPresenter.requestPlayList$lambda$1((List) obj, (List) obj2, (List) obj3);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<ArrayList<MovieListModel>>() { // from class: com.movieboxpro.android.tv.list.PlayListPresenter.requestPlayList.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                PlayListPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(ArrayList<MovieListModel> model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (!model.isEmpty()) {
                    ArrayList<MovieListModel> arrayList = model;
                    if (Intrinsics.areEqual(((MovieListModel) CollectionsKt.last((List) arrayList)).getType(), "actor")) {
                        model.remove(CollectionsKt.getLastIndex(arrayList));
                    }
                }
                PlayListPresenter.this.getView().showPlayList(model);
                PlayListPresenter.this.getView().hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                String message;
                PlayListPresenter.this.getView().hideLoading();
                PlayListContract.View view = PlayListPresenter.this.getView();
                if (e == null || (message = e.getMessage()) == null) {
                    message = "";
                }
                view.loadFailed(message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrayList requestPlayList$lambda$1(List mine, List collect, List all) {
        Intrinsics.checkNotNullParameter(mine, "mine");
        Intrinsics.checkNotNullParameter(collect, "collect");
        Intrinsics.checkNotNullParameter(all, "all");
        ArrayList arrayList = new ArrayList();
        Iterator it = all.iterator();
        while (it.hasNext()) {
            arrayList.add((MovieListModel) it.next());
        }
        MovieListModel movieListModel = new MovieListModel();
        movieListModel.setList(mine);
        movieListModel.setName("Mine");
        movieListModel.setType("mine");
        if (mine.size() >= 10) {
            movieListModel.setIsmore(1);
        } else {
            movieListModel.setIsmore(0);
        }
        if (arrayList.size() >= 1) {
            arrayList.add(1, movieListModel);
        }
        MovieListModel movieListModel2 = new MovieListModel();
        movieListModel2.setList(collect);
        movieListModel2.setName("My Collect");
        movieListModel2.setType("collect");
        if (collect.size() >= 10) {
            movieListModel2.setIsmore(1);
        } else {
            movieListModel2.setIsmore(0);
        }
        if (arrayList.size() >= 2) {
            arrayList.add(2, movieListModel2);
        }
        return arrayList;
    }
}
