package com.movieboxpro.android.view.fragment.home;

import android.os.Build;
import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import androidx.media3.extractor.text.ttml.TtmlNode;
import androidx.tvprovider.media.tv.TvContractCompat;
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
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.livedata.RefreshWatchedLiveData;
import com.movieboxpro.android.livedata.RefreshWatchingLiveData;
import com.movieboxpro.android.model.AdvertisementModel;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BaseResponse;
import com.movieboxpro.android.model.BeanKt;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.FeaturedDataModel;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: FeaturedPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007JT\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0016\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\n\u001a\u00020\u000bH\u0002JR\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\b\u0010\u0012\u001a\u0004\u0018\u00010\u000b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0002J\u0018\u0010\u001a\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0018\u0010\u001b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\u000bH\u0016J\u0012\u0010\u001e\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\b\u0010\u001f\u001a\u00020\tH\u0016J\b\u0010\"\u001a\u00020\tH\u0016R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/FeaturedPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/fragment/home/FeaturedContract$View;", "Lcom/movieboxpro/android/view/fragment/home/FeaturedContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "getPlayPath", "", "id", "", "boxType", "", "currSeason", "currEpisode", "playImmediately", "", TvContractCompat.ProgramColumns.COLUMN_TITLE, "poster", "historyOver", "watchSeconds", "loadMoviePlayPath", "Lio/reactivex/Observable;", "Lcom/movieboxpro/android/model/BaseMediaModel;", "loadTvPlayPath", "markWatched", "deleteHistory", "requestData", "uid", "advNotify", "getContinue", "likeDisposable", "Lio/reactivex/disposables/Disposable;", "getMaybeLike", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FeaturedPresenter extends BasePresenter<FeaturedContract.View> implements FeaturedContract.Presenter {
    private Disposable likeDisposable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeaturedPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.Presenter
    public void getPlayPath(final String id, final int boxType, final int currSeason, final int currEpisode, final boolean playImmediately, final String title, final String poster, final int historyOver, final int watchSeconds) {
        Intrinsics.checkNotNullParameter(id, "id");
        Observable<String> observablePlayingFeedback = Http.getService().playingFeedback(API.Common.PLAYING_FEEDBACK, App.getUserData().uid_v2, id, SystemUtils.getUniqueId(App.getContext()), boxType, currSeason, currEpisode, Build.BRAND, Build.MODEL);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.getPlayPath$lambda$0(this.f$0, id, boxType, currSeason, currEpisode, (String) obj);
            }
        };
        Observable observableOnErrorResumeNext = observablePlayingFeedback.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda11
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FeaturedPresenter.getPlayPath$lambda$1(function1, obj);
            }
        }).onErrorResumeNext((Function<? super Throwable, ? extends ObservableSource<? extends R>>) new Function() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda15
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FeaturedPresenter.getPlayPath$lambda$2((Throwable) obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.getPlayPath$lambda$3(boxType, this, id, currSeason, currEpisode, title, poster, historyOver, watchSeconds, (String) obj);
            }
        };
        ((ObservableSubscribeProxy) observableOnErrorResumeNext.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda17
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FeaturedPresenter.getPlayPath$lambda$4(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<BaseMediaModel>() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter.getPlayPath.4
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                FeaturedPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(BaseMediaModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (model instanceof TvDetail) {
                    List<BaseMediaModel.DownloadFile> list = model.list;
                    if (list != null && !list.isEmpty()) {
                        FeaturedPresenter.this.getView().goTvPlayer((TvDetail) model, playImmediately);
                    } else {
                        ToastUtils.showShort("Not Available", new Object[0]);
                    }
                } else if (model instanceof MovieDetail) {
                    List<BaseMediaModel.DownloadFile> list2 = model.list;
                    if (list2 != null && !list2.isEmpty()) {
                        FeaturedPresenter.this.getView().goMoviePlayer((MovieDetail) model, playImmediately);
                    } else {
                        ToastUtils.showShort("Not Available", new Object[0]);
                    }
                }
                FeaturedPresenter.this.getView().hideLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                ToastUtils.showShort("Load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
                FeaturedPresenter.this.getView().hideLoadingView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getPlayPath$lambda$1(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getPlayPath$lambda$0(FeaturedPresenter featuredPresenter, String str, int i, int i2, int i3, String it) {
        Observable observableJust;
        Intrinsics.checkNotNullParameter(it, "it");
        Object object = JSON.parseObject(it, RxUtils.buildType(BaseResponse.class, DeviceModelResponse.class), new Feature[0]);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        BaseResponse baseResponse = (BaseResponse) object;
        if (baseResponse.getCode() == -88) {
            featuredPresenter.getView().hideLoadView();
            FeaturedContract.View view = featuredPresenter.getView();
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
    public static final ObservableSource getPlayPath$lambda$4(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource getPlayPath$lambda$3(int i, FeaturedPresenter featuredPresenter, String str, int i2, int i3, String str2, String str3, int i4, int i5, String it) {
        Observable<BaseMediaModel> observableLoadTvPlayPath;
        Intrinsics.checkNotNullParameter(it, "it");
        if (i == 1) {
            observableLoadTvPlayPath = featuredPresenter.loadMoviePlayPath(str);
        } else {
            observableLoadTvPlayPath = featuredPresenter.loadTvPlayPath(str, i, i2, i3, str2, str3, i4, i5);
        }
        return observableLoadTvPlayPath;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1, types: [T, java.lang.String] */
    private final Observable<BaseMediaModel> loadMoviePlayPath(final String id) {
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
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.loadMoviePlayPath$lambda$7(objectRef, objectRef2, id, (MovieDetail) obj);
            }
        };
        Observable<BaseMediaModel> observableFlatMap = observableCompose.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda8
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FeaturedPresenter.loadMoviePlayPath$lambda$8(function1, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        return observableFlatMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource loadMoviePlayPath$lambda$8(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final ObservableSource loadMoviePlayPath$lambda$7(Ref.ObjectRef objectRef, Ref.ObjectRef objectRef2, final String str, final MovieDetail model) {
        Intrinsics.checkNotNullParameter(model, "model");
        Observable<R> observableCompose = Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, "", (String) objectRef.element, (String) objectRef2.element).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.loadMoviePlayPath$lambda$7$lambda$5(str, model, (BaseMediaModel) obj);
            }
        };
        return observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FeaturedPresenter.loadMoviePlayPath$lambda$7$lambda$6(function1, obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel loadMoviePlayPath$lambda$7$lambda$6(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (BaseMediaModel) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel loadMoviePlayPath$lambda$7$lambda$5(String str, MovieDetail movieDetail, BaseMediaModel it) {
        Intrinsics.checkNotNullParameter(it, "it");
        DownloadFile downloadFileFindDownloadedMovie = DownloadFileHelper.INSTANCE.getInstance().findDownloadedMovie(str);
        if (downloadFileFindDownloadedMovie != null) {
            it.list.add(0, BeanKt.toMediaDownloadFile(downloadFileFindDownloadedMovie));
        }
        movieDetail.addDonwload(it);
        Intrinsics.checkNotNull(movieDetail, "null cannot be cast to non-null type com.movieboxpro.android.model.BaseMediaModel");
        return movieDetail;
    }

    private final Observable<BaseMediaModel> loadTvPlayPath(final String id, final int boxType, int currSeason, int currEpisode, final String title, final String poster, int historyOver, final int watchSeconds) {
        String str;
        String str2;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = currSeason > 0 ? currSeason : 1;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = currEpisode > 0 ? currEpisode : 1;
        if (historyOver == 1) {
            intRef2.element++;
        }
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        Observable<R> observableCompose = Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", id, String.valueOf(intRef.element), String.valueOf(intRef2.element), str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.loadTvPlayPath$lambda$9(id, intRef, intRef2, title, poster, boxType, watchSeconds, (BaseMediaModel) obj);
            }
        };
        Observable<BaseMediaModel> map = observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda10
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FeaturedPresenter.loadTvPlayPath$lambda$10(function1, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(map, "map(...)");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel loadTvPlayPath$lambda$10(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (BaseMediaModel) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BaseMediaModel loadTvPlayPath$lambda$9(String str, Ref.IntRef intRef, Ref.IntRef intRef2, String str2, String str3, int i, int i2, BaseMediaModel downloadModel) {
        Intrinsics.checkNotNullParameter(downloadModel, "downloadModel");
        DownloadFile downloadFileFindDownloadFileByTidSeasonEpisode = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(str, intRef.element, intRef2.element);
        if (downloadFileFindDownloadFileByTidSeasonEpisode != null) {
            downloadModel.list.add(0, BeanKt.toMediaDownloadFile(downloadFileFindDownloadFileByTidSeasonEpisode));
        }
        TvDetail tvDetail = new TvDetail();
        tvDetail.id = str;
        if (str2 == null) {
            str2 = downloadModel.title;
        }
        tvDetail.title = str2;
        if (str3 == null) {
            str3 = downloadModel.poster;
        }
        tvDetail.poster = str3;
        tvDetail.box_type = i;
        tvDetail.imdb_id = downloadModel.imdb_id;
        if (i2 <= 0) {
            i2 = downloadModel.seconds;
        }
        tvDetail.seconds = i2;
        tvDetail.seasonDetail.season = intRef.element;
        tvDetail.seasonDetail.episode = intRef2.element;
        tvDetail.seasonDetail.tid = str;
        tvDetail.episodeList = new ArrayList();
        tvDetail.addDonwload(downloadModel);
        return tvDetail;
    }

    public final void markWatched(String id, int boxType) {
        Observable<String> observableAsRequest = HttpRequest.INSTANCE.post("User_watch_plan_mark").param("box_type", Integer.valueOf(boxType)).param("mid", id).param("watched", (Object) 1).asRequest();
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transformMsg(observableAsRequest, mLifecycleOwner), new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.markWatched$lambda$11(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda19
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.markWatched$lambda$12(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda20
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.markWatched$lambda$13(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$12(FeaturedPresenter featuredPresenter, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.getView().showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$13(FeaturedPresenter featuredPresenter, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.getView().hideLoadingView();
        featuredPresenter.getView().deleteComplete();
        RefreshWatchedLiveData.INSTANCE.get().setValue(true);
        RefreshWatchingLiveData.INSTANCE.get().setValue(true);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit markWatched$lambda$11(FeaturedPresenter featuredPresenter, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.getView().hideLoadingView();
        ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    public final void deleteHistory(String id, int boxType) {
        HttpRequest.Companion companion = HttpRequest.INSTANCE;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(companion.post(mLifecycleOwner, "User_watch_plan_del").param("mid", id).param("box_type", Integer.valueOf(boxType)).asMsg(), new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda22
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.deleteHistory$lambda$14(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.deleteHistory$lambda$15(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.deleteHistory$lambda$16(this.f$0, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteHistory$lambda$15(FeaturedPresenter featuredPresenter, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.getView().showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteHistory$lambda$16(FeaturedPresenter featuredPresenter, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.getView().hideLoadingView();
        featuredPresenter.getView().deleteComplete();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit deleteHistory$lambda$14(FeaturedPresenter featuredPresenter, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.getView().hideLoadingView();
        ToastUtils.showShort("Delete failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.Presenter
    public void requestData(String uid) {
        Intrinsics.checkNotNullParameter(uid, "uid");
        Observable<R> observableCompose = Http.getService().Advertisement("Advertisement_v2", App.getUserData().uid_v2, "6").compose(RxUtils.rxTranslate2List(AdvertisementModel.class));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda12
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.requestData$lambda$17((Throwable) obj);
            }
        };
        ((ObservableSubscribeProxy) Observable.zip(observableCompose.onErrorReturn(new Function() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda13
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return FeaturedPresenter.requestData$lambda$18(function1, obj);
            }
        }), Http.getService().Home_list(API.Common.HOME_LIST, uid, TtmlNode.COMBINE_ALL, PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false) ? 1 : 0, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "10", BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslate2List(Homelist.class)), new BiFunction() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda14
            @Override // io.reactivex.functions.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return FeaturedPresenter.requestData$lambda$19((List) obj, (List) obj2);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<FeaturedDataModel>() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter.requestData.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                FeaturedPresenter.this.getView().showLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(FeaturedDataModel model) {
                Intrinsics.checkNotNullParameter(model, "model");
                FeaturedPresenter.this.getView().showVideo(model);
                FeaturedPresenter.this.getView().hideLoading();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                String message;
                FeaturedPresenter.this.getView().hideLoading();
                FeaturedContract.View view = FeaturedPresenter.this.getView();
                if (e == null || (message = e.getMessage()) == null) {
                    message = "";
                }
                view.loadVideoFailed(message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List requestData$lambda$18(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List requestData$lambda$17(Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final FeaturedDataModel requestData$lambda$19(List t1, List t2) {
        Intrinsics.checkNotNullParameter(t1, "t1");
        Intrinsics.checkNotNullParameter(t2, "t2");
        return new FeaturedDataModel(t1, t2);
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.Presenter
    public void advNotify(String id) {
        String str = id;
        if (str == null || StringsKt.isBlank(str)) {
            return;
        }
        Observable<String> observableAdvertisement_notify = Http.getService().Advertisement_notify("Advertisement_notify", id);
        Intrinsics.checkNotNullExpressionValue(observableAdvertisement_notify, "Advertisement_notify(...)");
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.transformMsg(observableAdvertisement_notify, mLifecycleOwner).subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new Function1<String, Unit>() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$advNotify$$inlined$subscribeToMsg$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(String str2) {
                m1838invoke(str2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1838invoke(String str2) {
                Intrinsics.checkNotNull(str2);
            }
        }), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new Function1<Throwable, Unit>() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$advNotify$$inlined$subscribeToMsg$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                Intrinsics.checkNotNullExpressionValue(ApiException.handleException(th), "handleException(...)");
                if (th instanceof ServerException) {
                }
            }
        }), new Action() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$advNotify$$inlined$subscribeToMsg$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new Function1<Disposable, Unit>() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$advNotify$$inlined$subscribeToMsg$default$4
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Disposable disposable) {
                invoke2(disposable);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Disposable disposable) {
                Intrinsics.checkNotNull(disposable);
            }
        }));
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.Presenter
    public void getContinue() {
        HttpRequest.Companion companion = HttpRequest.INSTANCE;
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(companion.post(mLifecycleOwner, API.Common.HOME_LIST).param("type", "continue").param("page", (Object) 1).param("pagelimit", (Object) 10).asList(Homelist.Typelist.class), null, null, null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda21
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.getContinue$lambda$20(this.f$0, (List) obj);
            }
        }, 15, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getContinue$lambda$20(FeaturedPresenter featuredPresenter, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.getView().showContinueList(it);
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.view.fragment.home.FeaturedContract.Presenter
    public void getMaybeLike() {
        Disposable disposable = this.likeDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        Observable<String> observableHome_list = Http.getService().Home_list(API.Common.HOME_LIST, App.getUserData().uid_v2, "maybe_like", PrefsUtils.getInstance().getBoolean(Constant.Prefs.PRIVATE_MODE, false) ? 1 : 0, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, "10", BuildConfig.VERSION_NAME);
        Intrinsics.checkNotNullExpressionValue(observableHome_list, "Home_list(...)");
        Observable<R> observableCompose = observableHome_list.compose(RxUtils.rxTranslate2List(Homelist.Typelist.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, mLifecycleOwner), null, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.getMaybeLike$lambda$21(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.home.FeaturedPresenter$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return FeaturedPresenter.getMaybeLike$lambda$22(this.f$0, (List) obj);
            }
        }, 11, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMaybeLike$lambda$21(FeaturedPresenter featuredPresenter, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.likeDisposable = it;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getMaybeLike$lambda$22(FeaturedPresenter featuredPresenter, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        featuredPresenter.getView().showMaybeLikeList(it);
        return Unit.INSTANCE;
    }
}
