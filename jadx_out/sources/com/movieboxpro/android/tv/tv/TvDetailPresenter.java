package com.movieboxpro.android.tv.tv;

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
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.HttpRequest;
import com.movieboxpro.android.model.Actor;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.BeanKt;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
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
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: TvDetailPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J8\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0010H\u0016J0\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u000bH\u0016J \u0010\u0018\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0010H\u0016J\u0010\u0010\u001a\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J(\u0010\u001b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000eH\u0016J&\u0010\u001d\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000eJ\u001e\u0010\u001e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u000eJ\u0010\u0010\u001f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010 \u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010!\u001a\u00020\u000e2\u0006\u0010\"\u001a\u00020\u0010H\u0016¨\u0006#"}, d2 = {"Lcom/movieboxpro/android/tv/tv/TvDetailPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/tv/tv/TvDetailContract$View;", "Lcom/movieboxpro/android/tv/tv/TvDetailContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "getVideoPath", "", DealWatchNextService.EPISODE, "", DealWatchNextService.SEASON, "id", "", "playImmediately", "", "isNext", "isContinue", "addToPlayList", "lid", "uid", "boxType", "position", "addToFavorite", "isFavorite", "requestPlayList", "requestSeasons", "year", "requestSelectSeasons", "requestSelectYear", "getActors", "requestDetailInfo", "lang", "refreshList", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvDetailPresenter extends BasePresenter<TvDetailContract.View> implements TvDetailContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TvDetailPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.Presenter
    public void getVideoPath(final int episode, final int season, final String id, final boolean playImmediately, final boolean isNext, final boolean isContinue) {
        Intrinsics.checkNotNullParameter(id, "id");
        Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, id, SystemUtils.getUniqueId(App.getContext()), 1, 0, 0, Build.BRAND, Build.MODEL);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TvDetailPresenter.getVideoPath$lambda$0(this.f$0, id, season, episode, isNext, isContinue, (String) obj);
            }
        };
        Observable observableOnErrorResumeNext = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TvDetailPresenter.getVideoPath$lambda$1(function1, obj);
            }
        }).onErrorResumeNext((Function<? super Throwable, ? extends ObservableSource<? extends R>>) new Function() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TvDetailPresenter.getVideoPath$lambda$2((Throwable) obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TvDetailPresenter.getVideoPath$lambda$5(id, season, episode, (String) obj);
            }
        };
        ((ObservableSubscribeProxy) observableOnErrorResumeNext.flatMap(new Function() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TvDetailPresenter.getVideoPath$lambda$6(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<BaseMediaModel>() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter.getVideoPath.4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TvDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BaseMediaModel model) {
                TvDetailPresenter.this.getView().goVideoPlayer(model, isNext, season, episode, playImmediately);
                TvDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                ToastUtils.showShort("Load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
                TvDetailPresenter.this.getView().hideLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getVideoPath$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getVideoPath$lambda$0(TvDetailPresenter tvDetailPresenter, String str, int i, int i2, boolean z, boolean z2, String it) {
        Observable observableJust;
        Intrinsics.checkNotNullParameter(it, "it");
        Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        BaseResponse baseResponse = (BaseResponse) object;
        if (baseResponse.getCode() == -88) {
            TvDetailContract.View view = tvDetailPresenter.getView();
            List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
            view.showScreenManage(new ArrayList<>(device_list != null ? device_list : new ArrayList()), baseResponse.getMsg(), str, i, i2, z, z2);
            tvDetailPresenter.getView().hideLoadView();
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
    public static final ObservableSource getVideoPath$lambda$5(final String str, final int i, final int i2, String it) {
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
        Observable<R> observableCompose = Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", str, String.valueOf(i), String.valueOf(i2), str3, str2).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TvDetailPresenter.getVideoPath$lambda$5$lambda$3(str, i, i2, (BaseMediaModel) obj);
            }
        };
        return observableCompose.map(new Function() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TvDetailPresenter.getVideoPath$lambda$5$lambda$4(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel getVideoPath$lambda$5$lambda$4(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (BaseMediaModel) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel getVideoPath$lambda$5$lambda$3(String str, int i, int i2, BaseMediaModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        DownloadFile downloadFileFindDownloadFileByTidSeasonEpisode = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(str, i, i2);
        if (downloadFileFindDownloadFileByTidSeasonEpisode != null && downloadFileFindDownloadFileByTidSeasonEpisode.getStatus() == 1) {
            it.list.add(0, BeanKt.toMediaDownloadFile(downloadFileFindDownloadFileByTidSeasonEpisode));
        }
        return it;
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.Presenter
    public void addToPlayList(String lid, String id, String uid, int boxType, final int position) {
        Intrinsics.checkNotNullParameter(lid, "lid");
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(uid, "uid");
        ((ObservableSubscribeProxy) Http.getService().Playlists_video_add("Playlists_video_add", App.getUserData().uid_v2, lid, boxType, id).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter.addToPlayList.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TvDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                TvDetailPresenter.this.getView().addToPlayListSuccess(position);
                TvDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                TvDetailPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("Add failed:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.Presenter
    public void addToFavorite(String id, String uid, final boolean isFavorite) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(uid, "uid");
        ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(id);
        HttpRequest.Companion companion = HttpRequest.INSTANCE;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        companion.post(mLifecycleOwner, API.Movie.MOVE_COLLECT).param("mids", (String) null).param("tids", arrayListArrayListOf).param("type", isFavorite ? "del" : DealWatchNextService.ADD_ACTION).asMsg().subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter.addToFavorite.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TvDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                TvDetailPresenter.this.getView().addFavoriteSuccess(!isFavorite);
                TvDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                TvDetailPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("failed:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.Presenter
    public void requestPlayList(String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        ((ObservableSubscribeProxy) Http.getService().Playlists_list(API.Common.PLAY_LIST, uid, "mine", 0, 0, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(MovieListModel.MovieListItem.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends MovieListModel.MovieListItem>>() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter.requestPlayList.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TvDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends MovieListModel.MovieListItem> list) {
                TvDetailContract.View view = TvDetailPresenter.this.getView();
                if (list == null) {
                    list = new ArrayList();
                }
                view.showPlayList(list);
                TvDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                TvDetailPresenter.this.getView().hideLoadingView();
                ToastUtils.showShort("load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.Presenter
    public void requestSeasons(String id, int season, int year, String uid) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(uid, "uid");
        APIService service = Http.getService();
        StringBuilder sb = new StringBuilder();
        sb.append(season);
        ((ObservableSubscribeProxy) service.TV_episode(API.Tv.TV_EPISODE, id, sb.toString(), String.valueOf(year), uid, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter.requestSeasons.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TvDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends TvDetail.SeasonDetail> model) {
                TvDetailPresenter.this.getView().hideLoadingView();
                TvDetailContract.View view = TvDetailPresenter.this.getView();
                if (model == null) {
                    model = new ArrayList();
                }
                view.showSeasons(model);
            }
        });
    }

    public final void requestSelectSeasons(String id, final int season, int year, String uid) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(uid, "uid");
        APIService service = Http.getService();
        StringBuilder sb = new StringBuilder();
        sb.append(season);
        ((ObservableSubscribeProxy) service.TV_episode(API.Tv.TV_EPISODE, id, sb.toString(), String.valueOf(year), uid, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter.requestSelectSeasons.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TvDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends TvDetail.SeasonDetail> model) {
                TvDetailPresenter.this.getView().hideLoadingView();
                TvDetailContract.View view = TvDetailPresenter.this.getView();
                int i = season;
                if (model == null) {
                    model = new ArrayList();
                }
                view.showSeasons(i, model);
            }
        });
    }

    public final void requestSelectYear(String id, final int year, String uid) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(uid, "uid");
        ((ObservableSubscribeProxy) Http.getService().TV_episode(API.Tv.TV_EPISODE, id, "", String.valueOf(year), uid, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends TvDetail.SeasonDetail>>() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter.requestSelectYear.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TvDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(List<? extends TvDetail.SeasonDetail> model) {
                TvDetailPresenter.this.getView().hideLoadingView();
                TvDetailContract.View view = TvDetailPresenter.this.getView();
                int i = year;
                if (model == null) {
                    model = new ArrayList();
                }
                view.showYear(i, model);
            }
        });
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.Presenter
    public void getActors(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Observable<String> observableTv_actor = Http.getService().Tv_actor("TV_actor", id, 1, 10);
        Intrinsics.checkNotNullExpressionValue(observableTv_actor, "Tv_actor(...)");
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<R> observableCompose = observableTv_actor.compose(RxUtils.rxTranslate2List(Actor.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, mLifecycleOwner), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return TvDetailPresenter.getActors$lambda$7(this.f$0, (List) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getActors$lambda$7(TvDetailPresenter tvDetailPresenter, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        tvDetailPresenter.getView().showActors(it);
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.tv.tv.TvDetailContract.Presenter
    public void requestDetailInfo(String id, String lang, final boolean refreshList) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(lang, "lang");
        ((ObservableSubscribeProxy) Http.getService().Tv_detail(API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, lang, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2Bean(TvDetail.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<TvDetail>() { // from class: com.movieboxpro.android.tv.tv.TvDetailPresenter.requestDetailInfo.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                TvDetailPresenter.this.getView().showPlayButtonLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(TvDetail model) {
                TvDetailPresenter.this.getView().showVideoInfo(model, refreshList);
                TvDetailPresenter.this.getView().hidePlayButtonLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                TvDetailPresenter.this.getView().hidePlayButtonLoading();
                ToastUtils.showShort("load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
                TvDetailPresenter.this.getView().loadInfoError();
            }
        });
    }
}
