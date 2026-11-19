package com.movieboxpro.android.tv.list;

import androidx.core.app.NotificationCompat;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.movie.MovieListModel;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: PlayListContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PlayListContract {

    /* JADX INFO: compiled from: PlayListContract.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/tv/list/PlayListContract$View;", "requestPlayList", "", "uid", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void requestPlayList(String uid);
    }

    /* JADX INFO: compiled from: PlayListContract.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/tv/list/PlayListContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showPlayList", "", "list", "", "Lcom/movieboxpro/android/model/movie/MovieListModel;", "loadFailed", NotificationCompat.CATEGORY_MESSAGE, "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void loadFailed(String msg);

        void showPlayList(List<? extends MovieListModel> list);
    }
}
