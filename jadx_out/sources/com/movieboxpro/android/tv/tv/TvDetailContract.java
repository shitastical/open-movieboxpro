package com.movieboxpro.android.tv.tv;

import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.Actor;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.movie.MovieListModel;
import com.movieboxpro.android.model.tv.TvDetail;
import com.movieboxpro.android.service.DealWatchNextService;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: TvDetailContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/tv/TvDetailContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface TvDetailContract {

    /* JADX INFO: compiled from: TvDetailContract.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nH&J(\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0006H&J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0006H&J \u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\nH&J0\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\rH&J8\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\nH&¨\u0006\u001cÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/tv/TvDetailContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/tv/tv/TvDetailContract$View;", "getActors", "", "id", "", "requestDetailInfo", "lang", "refreshList", "", "requestSeasons", DealWatchNextService.SEASON, "", "year", "uid", "requestPlayList", "addToFavorite", "isFavorite", "addToPlayList", "lid", "boxType", "position", "getVideoPath", DealWatchNextService.EPISODE, "playImmediately", "isNext", "isContinue", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void addToFavorite(String id, String uid, boolean isFavorite);

        void addToPlayList(String lid, String id, String uid, int boxType, int position);

        void getActors(String id);

        void getVideoPath(int episode, int season, String id, boolean playImmediately, boolean isNext, boolean isContinue);

        void requestDetailInfo(String id, String lang, boolean refreshList);

        void requestPlayList(String uid);

        void requestSeasons(String id, int season, int year, String uid);
    }

    /* JADX INFO: compiled from: TvDetailContract.kt */
    @Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0016\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\u001e\u0010\b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\u001e\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\r2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH&J\u0016\u0010\u0010\u001a\u00020\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\nH&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0007H&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\rH&J2\u0010\u0017\u001a\u00020\u00032\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\r2\u0006\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0007H&J\b\u0010\u001e\u001a\u00020\u0003H&J\b\u0010\u001f\u001a\u00020\u0003H&J\b\u0010 \u001a\u00020\u0003H&JT\u0010!\u001a\u00020\u00032\u0016\u0010\u0011\u001a\u0012\u0012\u0004\u0012\u00020#0\"j\b\u0012\u0004\u0012\u00020#`$2\b\u0010%\u001a\u0004\u0018\u00010&2\b\u0010'\u001a\u0004\u0018\u00010&2\u0006\u0010(\u001a\u00020\r2\u0006\u0010)\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u0007H&J\b\u0010+\u001a\u00020\u0003H&J\u0016\u0010,\u001a\u00020\u00032\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020-0\nH&¨\u0006.À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/tv/TvDetailContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showVideoInfo", "", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "refreshList", "", "showSeasons", "seasons", "", "Lcom/movieboxpro/android/model/tv/TvDetail$SeasonDetail;", "selectSeason", "", "showYear", "selectYear", "showPlayList", "list", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "addFavoriteSuccess", "isFavorite", "addToPlayListSuccess", "position", "goVideoPlayer", "baseMediaModel", "Lcom/movieboxpro/android/model/BaseMediaModel;", "isNext", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "playImmediately", "loadInfoError", "showPlayButtonLoading", "hidePlayButtonLoading", "showScreenManage", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "", "id", "currSeason", "currEpisode", "isContinue", "hideLoadView", "showActors", "Lcom/movieboxpro/android/model/Actor;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void addFavoriteSuccess(boolean isFavorite);

        void addToPlayListSuccess(int position);

        void goVideoPlayer(BaseMediaModel baseMediaModel, boolean isNext, int season, int episode, boolean playImmediately);

        void hideLoadView();

        void hidePlayButtonLoading();

        void loadInfoError();

        void showActors(List<Actor> list);

        void showPlayButtonLoading();

        void showPlayList(List<? extends MovieListModel.MovieListItem> list);

        void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id, int currSeason, int currEpisode, boolean isNext, boolean isContinue);

        void showSeasons(int selectSeason, List<? extends TvDetail.SeasonDetail> seasons);

        void showSeasons(List<? extends TvDetail.SeasonDetail> seasons);

        void showVideoInfo(TvDetail tvDetail, boolean refreshList);

        void showYear(int selectYear, List<? extends TvDetail.SeasonDetail> seasons);
    }
}
