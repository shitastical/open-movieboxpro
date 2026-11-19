package com.movieboxpro.android.tv.movie;

import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.DownloadUrl;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.movie.MovieListModel;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: MovieDetailContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface MovieDetailContract {

    /* JADX INFO: compiled from: MovieDetailContract.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H&J \u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH&J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0006H&J0\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H&J\u0018\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u000bH&J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0015À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/tv/movie/MovieDetailContract$View;", "requestMovieInfo", "", "id", "", "lang", "addToFavorite", "uid", "isFavorite", "", "requestPlayList", "addToPlayList", "lid", "boxType", "", "position", "getVideoPath", "playImmediately", "getDownloadUrls", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void addToFavorite(String id, String uid, boolean isFavorite);

        void addToPlayList(String lid, String id, String uid, int boxType, int position);

        void getDownloadUrls(String id);

        void getVideoPath(String id, boolean playImmediately);

        void requestMovieInfo(String id, String lang);

        void requestPlayList(String uid);
    }

    /* JADX INFO: compiled from: MovieDetailContract.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0016\u0010\t\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH&J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000fH&J\u001a\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\bH&J\b\u0010\u0014\u001a\u00020\u0003H&J\u0016\u0010\u0015\u001a\u00020\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00160\u000bH&J\b\u0010\u0017\u001a\u00020\u0003H&J\b\u0010\u0018\u001a\u00020\u0003H&J4\u0010\u0019\u001a\u00020\u00032\u0016\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u001b0\u001aj\b\u0012\u0004\u0012\u00020\u001b`\u001c2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001eH&J\b\u0010 \u001a\u00020\u0003H&¨\u0006!À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/movie/MovieDetailContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showMovieInfo", "", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "addFavoriteSuccess", "isFavorite", "", "showPlayList", "list", "", "Lcom/movieboxpro/android/model/movie/MovieListModel$MovieListItem;", "addToPlayListSuccess", "position", "", "goVideoPlayer", "baseMediaModel", "Lcom/movieboxpro/android/model/BaseMediaModel;", "playImmediately", "loadInfoError", "showDownloadDialog", "Lcom/movieboxpro/android/model/DownloadUrl;", "showPlayButtonLoading", "hidePlayButtonLoading", "showScreenManage", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", NotificationCompat.CATEGORY_MESSAGE, "", "id", "hideLoadView", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void addFavoriteSuccess(boolean isFavorite);

        void addToPlayListSuccess(int position);

        void goVideoPlayer(BaseMediaModel baseMediaModel, boolean playImmediately);

        void hideLoadView();

        void hidePlayButtonLoading();

        void loadInfoError();

        void showDownloadDialog(List<DownloadUrl> list);

        void showMovieInfo(MovieDetail movieDetail);

        void showPlayButtonLoading();

        void showPlayList(List<? extends MovieListModel.MovieListItem> list);

        void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id);
    }
}
