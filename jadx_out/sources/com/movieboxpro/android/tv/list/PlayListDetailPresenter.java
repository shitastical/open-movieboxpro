package com.movieboxpro.android.tv.list;

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
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListDetailModel;
import com.movieboxpro.android.model.tv.TvDetail;
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
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: PlayListDetailPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J&\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\rJ \u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u000bH\u0016J\u0018\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u000bH\u0016¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListDetailPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/tv/list/PlayListDetailContract$View;", "Lcom/movieboxpro/android/tv/list/PlayListDetailContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "getPlayPath", "", "id", "", "boxType", "", "currSeason", "currEpisode", "addCollect", "lid", "uid", "type", "requestInfo", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class PlayListDetailPresenter extends BasePresenter<PlayListDetailContract.View> implements PlayListDetailContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PlayListDetailPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    public final void getPlayPath(final String id, final int boxType, final int currSeason, final int currEpisode) {
        Intrinsics.checkNotNullParameter(id, "id");
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 1;
        Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, id, SystemUtils.getUniqueId(App.getContext()), boxType, currSeason, currEpisode, Build.BRAND, Build.MODEL);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PlayListDetailPresenter.getPlayPath$lambda$0(this.f$0, id, boxType, currSeason, currEpisode, (String) obj);
            }
        };
        Observable observableOnErrorResumeNext = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PlayListDetailPresenter.getPlayPath$lambda$1(function1, obj);
            }
        }).onErrorResumeNext((Function<? super Throwable, ? extends ObservableSource<? extends R>>) new Function() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$$ExternalSyntheticLambda2
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PlayListDetailPresenter.getPlayPath$lambda$2((Throwable) obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return PlayListDetailPresenter.getPlayPath$lambda$4(boxType, id, intRef, (String) obj);
            }
        };
        ((ObservableSubscribeProxy) observableOnErrorResumeNext.flatMap(new Function() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PlayListDetailPresenter.getPlayPath$lambda$5(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<Pair<? extends BaseMediaModel, ? extends BaseMediaModel>>() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter.getPlayPath.4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                PlayListDetailPresenter.this.getView().showLoadingView();
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
                            PlayListDetailPresenter.this.getView().goTvPlayer(tvDetail);
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
                        PlayListDetailPresenter.this.getView().goMoviePlayer(movieDetail);
                    } else {
                        ToastUtils.showShort("Not Available", new Object[0]);
                    }
                }
                PlayListDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                ToastUtils.showShort("Load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
                PlayListDetailPresenter.this.getView().hideLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getPlayPath$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getPlayPath$lambda$0(PlayListDetailPresenter playListDetailPresenter, String str, int i, int i2, int i3, String it) {
        Observable observableJust;
        Intrinsics.checkNotNullParameter(it, "it");
        Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        BaseResponse baseResponse = (BaseResponse) object;
        if (baseResponse.getCode() == -88) {
            PlayListDetailContract.View view = playListDetailPresenter.getView();
            List<DeviceModelResponse.DeviceModel> device_list = ((DeviceModelResponse) baseResponse.getData()).getDevice_list();
            view.showScreenManage(new ArrayList<>(device_list != null ? device_list : new ArrayList()), baseResponse.getMsg(), str, i, i2, i3);
            observableJust = Observable.empty();
        } else {
            observableJust = Observable.just("");
        }
        return observableJust;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Observable getPlayPath$lambda$2(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Observable.just("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getPlayPath$lambda$5(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getPlayPath$lambda$4(int i, String str, Ref.IntRef intRef, String it) {
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
        final PlayListDetailPresenter$getPlayPath$3$1 playListDetailPresenter$getPlayPath$3$1 = new PlayListDetailPresenter$getPlayPath$3$1(intRef);
        return observableCompose.flatMap(new Function() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return PlayListDetailPresenter.getPlayPath$lambda$4$lambda$3(playListDetailPresenter$getPlayPath$3$1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getPlayPath$lambda$4$lambda$3(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    @Override // com.movieboxpro.android.tv.list.PlayListDetailContract.Presenter
    public void addCollect(String lid, String uid, String type) {
        Intrinsics.checkNotNullParameter(lid, "lid");
        Intrinsics.checkNotNullParameter(uid, "uid");
        Intrinsics.checkNotNullParameter(type, "type");
        ((ObservableSubscribeProxy) Http.getService().Playlists_collect("Playlists_collect", uid, lid, type, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter.addCollect.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                PlayListDetailPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
                PlayListDetailPresenter.this.getView().doCollectComplete(true);
                PlayListDetailPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                PlayListDetailPresenter.this.getView().doCollectComplete(false);
                PlayListDetailPresenter.this.getView().hideLoadingView();
            }
        });
    }

    @Override // com.movieboxpro.android.tv.list.PlayListDetailContract.Presenter
    public void requestInfo(String lid, String uid) {
        Intrinsics.checkNotNullParameter(lid, "lid");
        Intrinsics.checkNotNullParameter(uid, "uid");
        ((ObservableSubscribeProxy) Http.getService().Playlists_get("Playlists_get", uid, lid, 1, 5, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2Bean(MovieListDetailModel.class)).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<MovieListDetailModel>() { // from class: com.movieboxpro.android.tv.list.PlayListDetailPresenter.requestInfo.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                PlayListDetailPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(MovieListDetailModel model) {
                PlayListDetailPresenter.this.getView().showInfo(model);
                PlayListDetailPresenter.this.getView().hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                PlayListDetailPresenter.this.getView().hideLoading();
            }
        });
    }
}
