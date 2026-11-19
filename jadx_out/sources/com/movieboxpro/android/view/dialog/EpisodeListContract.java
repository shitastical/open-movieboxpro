package com.movieboxpro.android.view.dialog;

import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: EpisodeListContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/EpisodeListContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface EpisodeListContract {

    /* JADX INFO: compiled from: EpisodeListContract.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J,\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u0003H&¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/EpisodeListContract$Presenter;", "", "loadSeasonMeta", "", "tvId", "", "loadEpisodes", DealWatchNextService.SEASON, "year", "forceNetwork", "", "attachView", "view", "Lcom/movieboxpro/android/view/dialog/EpisodeListContract$View;", "detachView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter {
        void attachView(View view);

        void detachView();

        void loadEpisodes(String tvId, String season, String year, boolean forceNetwork);

        void loadSeasonMeta(String tvId);
    }

    /* JADX INFO: compiled from: EpisodeListContract.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&J)\u0010\u0005\u001a\u00020\u00032\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H&¢\u0006\u0002\u0010\nJ\b\u0010\u000b\u001a\u00020\u0003H&J\u0016\u0010\f\u001a\u00020\u00032\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eH&J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\bH&¨\u0006\u0012À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/dialog/EpisodeListContract$View;", "", "showLoadingView", "", "hideLoadingView", "showSeasonMeta", "seasons", "", "", "years", "([Ljava/lang/String;[Ljava/lang/String;)V", "showSeasonMetaFailed", "showEpisodes", "list", "", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "showEpisodeLoadFailed", "message", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View {
        void hideLoadingView();

        void showEpisodeLoadFailed(String message);

        void showEpisodes(List<TvDetail.SeasonDetail> list);

        void showLoadingView();

        void showSeasonMeta(String[] seasons, String[] years);

        void showSeasonMetaFailed();
    }
}
