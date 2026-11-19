package com.movieboxpro.android.view.activity;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.DownloadSeasonItem;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: ChooseTvDownloadContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/ChooseTvDownloadContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface ChooseTvDownloadContract {

    /* JADX INFO: compiled from: ChooseTvDownloadContract.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J(\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fH&¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/ChooseTvDownloadContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/ChooseTvDownloadContract$View;", "requestSeasons", "", "id", "", DealWatchNextService.SEASON, "", "getDownloadUrls", DealWatchNextService.EPISODE, "item", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void getDownloadUrls(int episode, int season, String id, TvDetail.SeasonDetail item);

        void requestSeasons(String id, int season);
    }

    /* JADX INFO: compiled from: ChooseTvDownloadContract.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001e\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&J\u001e\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00072\u0006\u0010\f\u001a\u00020\rH&¨\u0006\u000eÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/ChooseTvDownloadContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showSeasons", "", "selectSeason", "", "seasons", "", "Lcom/movieboxpro/android/model/DownloadSeasonItem;", "showDownloadDialog", "list", "Lcom/movieboxpro/android/model/DownloadUrl;", "item", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void showDownloadDialog(List<DownloadUrl> list, TvDetail.SeasonDetail item);

        void showSeasons(int selectSeason, List<DownloadSeasonItem> seasons);
    }
}
