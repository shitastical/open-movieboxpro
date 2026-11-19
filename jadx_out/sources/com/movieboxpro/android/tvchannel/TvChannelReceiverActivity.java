package com.movieboxpro.android.tvchannel;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.TvSeasonList;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.player.VideoPlayer;
import com.movieboxpro.android.player.VideoPlayerActivity;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.tv.movie.MovieDetailActivity;
import com.movieboxpro.android.tv.tv.TvDetailActivity;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

/* JADX INFO: compiled from: TvChannelReceiverActivity.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0014J\u0018\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0002J\u0010\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/tvchannel/TvChannelReceiverActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "progressBar", "Landroid/widget/ProgressBar;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "requestData", "id", "", "boxType", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class TvChannelReceiverActivity extends AppCompatActivity {
    private ProgressBar progressBar;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_channel_receiver);
        View viewFindViewById = findViewById(R.id.progressBar);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(...)");
        this.progressBar = (ProgressBar) viewFindViewById;
        String strDecodeVideoId = SampleTvProvider.decodeVideoId(getIntent().getData());
        Intrinsics.checkNotNull(strDecodeVideoId);
        List listSplit$default = StringsKt.split$default((CharSequence) strDecodeVideoId, new String[]{"-"}, false, 0, 6, (Object) null);
        if (listSplit$default.size() == 2) {
            String str = (String) listSplit$default.get(0);
            if (Intrinsics.areEqual((String) listSplit$default.get(1), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
                MovieDetailActivity.INSTANCE.starter(this, str, "");
            } else {
                TvDetailActivity.INSTANCE.starter(this, str, "");
            }
            finish();
            return;
        }
        if (listSplit$default.size() == 3) {
            requestData((String) listSplit$default.get(0), (String) listSplit$default.get(1));
        } else {
            ToastUtils.showShort("Can't play video", new Object[0]);
            finish();
        }
    }

    private final void requestData(String id, String boxType) {
        Observable observableCompose;
        String str;
        String str2;
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = 1;
        if (Intrinsics.areEqual(boxType, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE)) {
            String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
            if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
                str2 = "";
                str = str2;
            } else {
                str = string;
                str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
            }
            Observable<String> observableMovie_detail = Http.getService().Movie_detail(API.Movie.MOVE_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, "", str2, str);
            Intrinsics.checkNotNullExpressionValue(observableMovie_detail, "Movie_detail(...)");
            observableCompose = observableMovie_detail.compose(RxUtils.rxTranslate2Bean(MovieDetail.class));
            Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        } else {
            Observable<String> observableTv_detail = Http.getService().Tv_detail(API.Tv.TV_DETAIL, App.isLogin() ? App.getUserData().uid_v2 : "", id, "", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            Intrinsics.checkNotNullExpressionValue(observableTv_detail, "Tv_detail(...)");
            observableCompose = observableTv_detail.compose(RxUtils.rxTranslate2Bean(TvDetail.class));
            Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        }
        final AnonymousClass1 anonymousClass1 = new AnonymousClass1(intRef);
        Observable observableFlatMap = observableCompose.flatMap(new Function() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TvChannelReceiverActivity.requestData$lambda$0(anonymousClass1, obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(observableFlatMap, "flatMap(...)");
        RxSubscribersKt.transform(observableFlatMap, this).subscribe(new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new Function1<Pair<? extends BaseMediaModel, ? extends BaseMediaModel>, Unit>() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$$inlined$transformSubscribe$default$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Pair<? extends BaseMediaModel, ? extends BaseMediaModel> pair) {
                m1486invoke(pair);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m1486invoke(Pair<? extends BaseMediaModel, ? extends BaseMediaModel> pair) {
                TvDetail.SeasonDetail seasonDetail;
                Intrinsics.checkNotNull(pair);
                Pair<? extends BaseMediaModel, ? extends BaseMediaModel> pair2 = pair;
                ProgressBar progressBar = null;
                if (pair2.getFirst() instanceof TvDetail) {
                    BaseMediaModel first = pair2.getFirst();
                    Intrinsics.checkNotNull(first, "null cannot be cast to non-null type com.movieboxpro.android.model.tv.TvDetail");
                    TvDetail tvDetail = (TvDetail) first;
                    tvDetail.addDonwload(pair2.getSecond());
                    List<BaseMediaModel.DownloadFile> list = tvDetail.list;
                    if (list != null && !list.isEmpty()) {
                        List<TvDetail.SeasonDetail> episodeList = tvDetail.episodeList;
                        Intrinsics.checkNotNullExpressionValue(episodeList, "episodeList");
                        Iterator<T> it = episodeList.iterator();
                        do {
                            if (!it.hasNext()) {
                                seasonDetail = null;
                                break;
                            }
                            seasonDetail = (TvDetail.SeasonDetail) it.next();
                        } while (seasonDetail.episode != intRef.element);
                        if (seasonDetail != null) {
                            tvDetail.seasonDetail = seasonDetail;
                            this.this$0.goTvPlayer(tvDetail);
                        } else {
                            ToastUtils.showShort("Episode " + intRef.element + ":not available", new Object[0]);
                        }
                    } else {
                        ToastUtils.showShort("Not Available", new Object[0]);
                    }
                } else if (pair2.getFirst() instanceof MovieDetail) {
                    BaseMediaModel first2 = pair2.getFirst();
                    Intrinsics.checkNotNull(first2, "null cannot be cast to non-null type com.movieboxpro.android.model.movie.MovieDetail");
                    MovieDetail movieDetail = (MovieDetail) first2;
                    movieDetail.addDonwload(pair2.getSecond());
                    List<BaseMediaModel.DownloadFile> list2 = movieDetail.list;
                    if (list2 != null && !list2.isEmpty()) {
                        this.this$0.goMoviePlayer(movieDetail);
                    } else {
                        ToastUtils.showShort("Not Available", new Object[0]);
                    }
                }
                ProgressBar progressBar2 = this.this$0.progressBar;
                if (progressBar2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                } else {
                    progressBar = progressBar2;
                }
                CommonExtKt.gone(progressBar);
                this.this$0.finish();
            }
        }), new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new Function1<Throwable, Unit>() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$$inlined$transformSubscribe$default$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Throwable th) {
                invoke2(th);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Throwable th) {
                ApiException apiExceptionHandleException = ApiException.handleException(th);
                Intrinsics.checkNotNullExpressionValue(apiExceptionHandleException, "handleException(...)");
                ToastUtils.showShort("Load failed:" + apiExceptionHandleException.getMessage(), new Object[0]);
                ProgressBar progressBar = this.this$0.progressBar;
                if (progressBar == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("progressBar");
                    progressBar = null;
                }
                CommonExtKt.gone(progressBar);
                this.this$0.finish();
                if (th instanceof ServerException) {
                }
            }
        }), new Action() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$$inlined$transformSubscribe$default$3
            @Override // io.reactivex.functions.Action
            public final void run() {
            }
        }, new RxSubscribersKt$sam$i$io_reactivex_functions_Consumer$0(new Function1<Disposable, Unit>() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$$inlined$transformSubscribe$default$4
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource requestData$lambda$0(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$1, reason: invalid class name */
    /* JADX INFO: compiled from: TvChannelReceiverActivity.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final class AnonymousClass1 implements Function1<?, ObservableSource<? extends Pair<? extends BaseMediaModel, ? extends BaseMediaModel>>> {
        final /* synthetic */ Ref.IntRef $position;

        AnonymousClass1(Ref.IntRef intRef) {
            this.$position = intRef;
        }

        @Override // kotlin.jvm.functions.Function1
        public final ObservableSource<? extends Pair<BaseMediaModel, BaseMediaModel>> invoke(final BaseMediaModel model) {
            int iIntValue;
            int iIntValue2;
            String str;
            String str2;
            Observable map;
            String str3;
            String str4;
            Intrinsics.checkNotNullParameter(model, "model");
            if (model instanceof MovieDetail) {
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
                    str4 = "";
                    str3 = str4;
                } else {
                    str3 = string;
                    str4 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                }
                Observable<R> observableCompose = Http.getService().Movie_detail(API.Movie.MOVE_DOWNLAOD, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, "", str4, str3).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
                final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$1$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return TvChannelReceiverActivity.AnonymousClass1.invoke$lambda$0(model, (BaseMediaModel) obj);
                    }
                };
                map = observableCompose.map(new Function() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$1$$ExternalSyntheticLambda1
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj) {
                        return TvChannelReceiverActivity.AnonymousClass1.invoke$lambda$1(function1, obj);
                    }
                });
                Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            } else {
                TvDetail tvDetail = (TvDetail) model;
                String str5 = tvDetail.history;
                if (str5 == null || str5.length() == 0 || Intrinsics.areEqual(_UrlKt.PATH_SEGMENT_ENCODE_SET_URI, tvDetail.history)) {
                    iIntValue = 1;
                    iIntValue2 = 1;
                } else {
                    Object obj = JSONObject.parse(tvDetail.history);
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type com.alibaba.fastjson.JSONObject");
                    JSONObject jSONObject = (JSONObject) obj;
                    Integer integer = jSONObject.getInteger(DealWatchNextService.SEASON);
                    Intrinsics.checkNotNullExpressionValue(integer, "getInteger(...)");
                    iIntValue2 = integer.intValue();
                    Integer integer2 = jSONObject.getInteger(DealWatchNextService.EPISODE);
                    Intrinsics.checkNotNullExpressionValue(integer2, "getInteger(...)");
                    iIntValue = integer2.intValue();
                }
                this.$position.element = iIntValue;
                String string2 = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
                if (!TextUtils.isEmpty(string2) && StringsKt.equals("0", string2, true)) {
                    str2 = "";
                    str = str2;
                } else {
                    str = string2;
                    str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
                }
                Observable<R> observableCompose2 = Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAODURL, App.isLogin() ? App.getUserData().uid_v2 : "", model.id, String.valueOf(iIntValue2), String.valueOf(iIntValue), str2, str).compose(DownloadUrlTransformer.applyPlayUrlsParsing());
                final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$1$$ExternalSyntheticLambda2
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj2) {
                        return TvChannelReceiverActivity.AnonymousClass1.invoke$lambda$2(model, (BaseMediaModel) obj2);
                    }
                };
                map = observableCompose2.map(new Function() { // from class: com.movieboxpro.android.tvchannel.TvChannelReceiverActivity$requestData$1$$ExternalSyntheticLambda3
                    @Override // io.reactivex.functions.Function
                    public final Object apply(Object obj2) {
                        return TvChannelReceiverActivity.AnonymousClass1.invoke$lambda$3(function2, obj2);
                    }
                });
                Intrinsics.checkNotNullExpressionValue(map, "map(...)");
            }
            return map;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Pair invoke$lambda$1(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (Pair) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Pair invoke$lambda$0(BaseMediaModel baseMediaModel, BaseMediaModel it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return new Pair(baseMediaModel, it);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Pair invoke$lambda$3(Function1 function1, Object p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return (Pair) function1.invoke(p0);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Pair invoke$lambda$2(BaseMediaModel baseMediaModel, BaseMediaModel it) {
            Intrinsics.checkNotNullParameter(it, "it");
            return new Pair(baseMediaModel, it);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goMoviePlayer(MovieDetail movieDetail) {
        VideoPlayerActivity.start(this, movieDetail, movieDetail.id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void goTvPlayer(TvDetail tvDetail) {
        ArrayList arrayList = new ArrayList();
        if (tvDetail.episodeList.size() < 100) {
            List<TvDetail.SeasonDetail> episodeList = tvDetail.episodeList;
            Intrinsics.checkNotNullExpressionValue(episodeList, "episodeList");
            for (TvDetail.SeasonDetail seasonDetail : episodeList) {
                TvSeasonList tvSeasonList = new TvSeasonList();
                tvSeasonList.setImdbId(seasonDetail.imdb_id);
                tvSeasonList.setEpisode(seasonDetail.episode);
                tvSeasonList.setSeason(seasonDetail.season);
                tvSeasonList.setTid(seasonDetail.tid);
                tvSeasonList.setId(seasonDetail.id);
                tvSeasonList.setSeconds(tvDetail.seconds);
                arrayList.add(tvSeasonList);
            }
        } else {
            arrayList = null;
        }
        VideoPlayer.start(this, tvDetail, tvDetail.seasonDetail.season, tvDetail.seasonDetail.episode, false, arrayList);
    }
}
