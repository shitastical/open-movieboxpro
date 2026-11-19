package com.movieboxpro.android.tv.movie;

import android.os.Build;
import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.BeanKt;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: MovieDetailPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J0\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0016J \u0010\u0015\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\rH\u0016J\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u000bH\u0016J\u0018\u0010\u0018\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u000bH\u0016¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/tv/movie/MovieDetailContract$View;", "Lcom/movieboxpro/android/tv/movie/MovieDetailContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "getVideoPath", "", "id", "", "playImmediately", "", "getDownloadUrls", "addToPlayList", "lid", "uid", "boxType", "", "position", "addToFavorite", "isFavorite", "requestPlayList", "requestMovieInfo", "lang", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class MovieDetailPresenter extends BasePresenter<MovieDetailContract.View> implements MovieDetailContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MovieDetailPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.Presenter
    public void getVideoPath(final String id, final boolean playImmediately) {
        Intrinsics.checkNotNullParameter(id, "id");
        Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, id, SystemUtils.getUniqueId(App.getContext()), 1, 0, 0, Build.BRAND, Build.MODEL);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailPresenter.getVideoPath$lambda$0(this.f$0, id, (String) obj);
            }
        };
        Observable observableOnErrorResumeNext = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MovieDetailPresenter.getVideoPath$lambda$1(function1, obj);
            }
        }).onErrorResumeNext((Function<? super Throwable, ? extends ObservableSource<? extends R>>) new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MovieDetailPresenter.getVideoPath$lambda$2((Throwable) obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailPresenter.getVideoPath$lambda$5(id, (String) obj);
            }
        };
        ((ObservableSubscribeProxy) observableOnErrorResumeNext.flatMap(new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter$$ExternalSyntheticLambda6
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MovieDetailPresenter.getVideoPath$lambda$6(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<BaseMediaModel>() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter.getVideoPath.4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                MovieDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BaseMediaModel model) {
                MovieDetailPresenter.this.getView().goVideoPlayer(model, playImmediately);
                MovieDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                ToastUtils.showShort("Load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
                MovieDetailPresenter.this.getView().hideLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getVideoPath$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getVideoPath$lambda$0(MovieDetailPresenter movieDetailPresenter, String str, String it) {
        Observable observableJust;
        Intrinsics.checkNotNullParameter(it, "it");
        Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        BaseResponse baseResponse = (BaseResponse) object;
        if (baseResponse.getCode() == -88) {
            MovieDetailContract.View view = movieDetailPresenter.getView();
            List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
            view.showScreenManage(new ArrayList<>(device_list != null ? device_list : new ArrayList()), baseResponse.getMsg(), str);
            movieDetailPresenter.getView().hideLoadView();
            observableJust = Observable.empty();
        } else {
            observableJust = Observable.just("");
        }
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Observable getVideoPath$lambda$2(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.just("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getVideoPath$lambda$6(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getVideoPath$lambda$5(final String str, String it) {
        String str2;
        String str3;
        Intrinsics.checkNotNullParameter(it, "it");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
            str3 = "";
            str2 = str3;
        } else {
            str2 = string;
            str3 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        Observable<R> observableCompose = Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", str, "", str3, str2).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MovieDetailPresenter.getVideoPath$lambda$5$lambda$3(str, (BaseMediaModel) obj);
            }
        };
        return observableCompose.map(new Function() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return MovieDetailPresenter.getVideoPath$lambda$5$lambda$4(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel getVideoPath$lambda$5$lambda$4(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (BaseMediaModel) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel getVideoPath$lambda$5$lambda$3(String str, BaseMediaModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        DownloadFile downloadFileFindDownloadedMovie = DownloadFileHelper.INSTANCE.getInstance().findDownloadedMovie(str);
        if (downloadFileFindDownloadedMovie != null && downloadFileFindDownloadedMovie.getStatus() == 1) {
            it.list.add(0, BeanKt.toMediaDownloadFile(downloadFileFindDownloadedMovie));
        }
        return it;
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.Presenter
    public void getDownloadUrls(String id) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(id, "id");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        ((ObservableSubscribeProxy) Http.getService().Movie_detail(API.Movie.MOVIE_DOWNLAOD_URL, App.isLogin() ? App.getUserData().uid_v2 : "", id, "", str2, str).compose(DownloadUrlTransformer.applyDownloadUrlsParsing()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends DownloadUrl>>() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter.getDownloadUrls.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public /* bridge */ /* synthetic */ void onSuccess(List<? extends DownloadUrl> list) {
                onSuccess2((List<DownloadUrl>) list);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                MovieDetailPresenter.this.getView().showLoadingView();
            }

            /* JADX INFO: renamed from: onSuccess, reason: avoid collision after fix types in other method */
            public void onSuccess2(List<DownloadUrl> list) {
                Intrinsics.checkNotNullParameter(list, "list");
                MovieDetailPresenter.this.getView().hideLoadingView();
                if (list.isEmpty()) {
                    ToastUtils.showShort("Not Available", new Object[0]);
                } else {
                    MovieDetailPresenter.this.getView().showDownloadDialog(list);
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                ToastUtils.showShort("Load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
                MovieDetailPresenter.this.getView().hideLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.Presenter
    public void addToPlayList(String lid, String id, String uid, int boxType, final int position) {
        Intrinsics.checkNotNullParameter(lid, "lid");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(uid, "uid");
        ((ObservableSubscribeProxy) Http.getService().Playlists_video_add("Playlists_video_add", App.getUserData().uid_v2, lid, boxType, id).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter.addToPlayList.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                MovieDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                MovieDetailPresenter.this.getView().addToPlayListSuccess(position);
                MovieDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                MovieDetailPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("Add failed:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.Presenter
    public void addToFavorite(String id, String uid, final boolean isFavorite) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(uid, "uid");
        ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(id);
        HttpRequest.Companion companion = HttpRequest.INSTANCE;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        companion.post(mLifecycleOwner, API.Movie.MOVE_COLLECT).param("mids", arrayListArrayListOf).param("tids", (String) null).param("type", isFavorite ? "del" : DealWatchNextService.ADD_ACTION).asMsg().subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter.addToFavorite.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                MovieDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                MovieDetailPresenter.this.getView().addFavoriteSuccess(!isFavorite);
                MovieDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                MovieDetailPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("failed:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.Presenter
    public void requestPlayList(String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        ((ObservableSubscribeProxy) Http.getService().Playlists_list(API.Common.PLAY_LIST, uid, "mine", 0, 0, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(MovieListModel.MovieListItem.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends MovieListModel.MovieListItem>>() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter.requestPlayList.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                MovieDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends MovieListModel.MovieListItem> list) {
                MovieDetailContract.View view = MovieDetailPresenter.this.getView();
                if (list == null) {
                    list = new ArrayList();
                }
                view.showPlayList(list);
                MovieDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                MovieDetailPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.tv.movie.MovieDetailContract.Presenter
    public void requestMovieInfo(String id, String lang) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(lang, "lang");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        ((ObservableSubscribeProxy) Http.getService().Movie_detail(API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, lang, str2, str).compose(RxUtils.rxTranslate2Bean(MovieDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<MovieDetail>() { // from class: com.movieboxpro.android.tv.movie.MovieDetailPresenter.requestMovieInfo.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                MovieDetailPresenter.this.getView().showPlayButtonLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(MovieDetail model) {
                MovieDetailPresenter.this.getView().showMovieInfo(model);
                MovieDetailPresenter.this.getView().hidePlayButtonLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                MovieDetailPresenter.this.getView().hidePlayButtonLoading();
                MovieDetailPresenter.this.getView().loadInfoError();
                ToastUtils.showShort("load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }
}
