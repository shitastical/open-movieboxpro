package com.movieboxpro.android.view.activity;

import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.APIService;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.model.DownloadSeasonItem;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.utils.CommonUtils;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.DownloadUrlTransformer;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ChooseTvDownloadPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J(\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/activity/ChooseTvDownloadPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/activity/ChooseTvDownloadContract$View;", "Lcom/movieboxpro/android/view/activity/ChooseTvDownloadContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "requestSeasons", "", "id", "", DealWatchNextService.SEASON, "", "getDownloadUrls", DealWatchNextService.EPISODE, "item", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ChooseTvDownloadPresenter extends BasePresenter<ChooseTvDownloadContract.View> implements ChooseTvDownloadContract.Presenter {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ChooseTvDownloadPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.activity.ChooseTvDownloadContract.Presenter
    public void requestSeasons(final String id, final int season) {
        Intrinsics.checkNotNullParameter(id, "id");
        APIService service = Http.getService();
        StringBuilder sb = new StringBuilder();
        sb.append(season);
        Observable<R> observableCompose = service.TV_episode(API.Tv.TV_EPISODE, id, sb.toString(), "0", App.getUserData().uid_v2, IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE).compose(RxUtils.rxTranslate2List(TvDetail.SeasonDetail.class));
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ChooseTvDownloadPresenter.requestSeasons$lambda$1(id, (List) obj);
            }
        };
        ((ObservableSubscribeProxy) observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadPresenter$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return ChooseTvDownloadPresenter.requestSeasons$lambda$2(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends DownloadSeasonItem>>() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadPresenter.requestSeasons.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public /* bridge */ /* synthetic */ void onSuccess(List<? extends DownloadSeasonItem> list) {
                onSuccess2((List<DownloadSeasonItem>) list);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                ChooseTvDownloadPresenter.this.getView().showLoadingView();
            }

            /* JADX INFO: renamed from: onSuccess, reason: avoid collision after fix types in other method */
            public void onSuccess2(List<DownloadSeasonItem> model) {
                ChooseTvDownloadPresenter.this.getView().hideLoadingView();
                ChooseTvDownloadContract.View view = ChooseTvDownloadPresenter.this.getView();
                int i = season;
                if (model == null) {
                    model = new ArrayList();
                }
                view.showSeasons(i, model);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                String message;
                ChooseTvDownloadPresenter.this.getView().showLoadingView();
                if (e == null || (message = e.getMessage()) == null) {
                    message = "";
                }
                ToastUtils.showShort("Load failed:" + message, new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List requestSeasons$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (List) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List requestSeasons$lambda$1(String str, List it) {
        Intrinsics.checkNotNullParameter(it, "it");
        List<TvDetail.SeasonDetail> list = it;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (TvDetail.SeasonDetail seasonDetail : list) {
            DownloadFile downloadFileFindDownloadFileByTidSeasonEpisode = DownloadFileHelper.INSTANCE.getInstance().findDownloadFileByTidSeasonEpisode(str, seasonDetail.season, seasonDetail.episode);
            if (downloadFileFindDownloadFileByTidSeasonEpisode != null) {
                String path = downloadFileFindDownloadFileByTidSeasonEpisode.getPath();
                if (path == null) {
                    path = "";
                }
                downloadFileFindDownloadFileByTidSeasonEpisode.setFfmpeg(CommonUtils.isFFmpegDownload(path));
            }
            arrayList.add(new DownloadSeasonItem(downloadFileFindDownloadFileByTidSeasonEpisode, seasonDetail));
        }
        return arrayList;
    }

    @Override // com.movieboxpro.android.view.activity.ChooseTvDownloadContract.Presenter
    public void getDownloadUrls(int episode, int season, String id, final TvDetail.SeasonDetail item) {
        String str;
        String str2;
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(item, "item");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.NETWORK_GROUP, "");
        if (!TextUtils.isEmpty(string) && StringsKt.equals("0", string, true)) {
            str2 = "";
            str = str2;
        } else {
            str = string;
            str2 = IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE;
        }
        ((ObservableSubscribeProxy) Http.getService().TV_downloadurl(API.Tv.TV_DOWNLAOD_URL, App.isLogin() ? App.getUserData().uid_v2 : "", id, String.valueOf(season), String.valueOf(episode), str2, str).compose(DownloadUrlTransformer.applyDownloadUrlsParsing()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<List<? extends DownloadUrl>>() { // from class: com.movieboxpro.android.view.activity.ChooseTvDownloadPresenter.getDownloadUrls.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public /* bridge */ /* synthetic */ void onSuccess(List<? extends DownloadUrl> list) {
                onSuccess2((List<DownloadUrl>) list);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable disposable) {
                Intrinsics.checkNotNullParameter(disposable, "disposable");
                ChooseTvDownloadPresenter.this.getView().showLoadingView();
            }

            /* JADX INFO: renamed from: onSuccess, reason: avoid collision after fix types in other method */
            public void onSuccess2(List<DownloadUrl> list) {
                Intrinsics.checkNotNullParameter(list, "list");
                ChooseTvDownloadPresenter.this.getView().hideLoadingView();
                if (list.isEmpty()) {
                    ToastUtils.showShort("Not Available", new Object[0]);
                } else {
                    ChooseTvDownloadPresenter.this.getView().showDownloadDialog(list, item);
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                ToastUtils.showShort("Load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
                ChooseTvDownloadPresenter.this.getView().hideLoadingView();
            }
        });
    }
}
