package com.movieboxpro.android.view.fragment.home;

import androidx.core.app.NotificationCompat;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.DeviceModelResponse;
import com.movieboxpro.android.model.FeaturedDataModel;
import com.movieboxpro.android.model.common.Homelist;
import com.movieboxpro.android.model.movie.MovieDetail;
import com.movieboxpro.android.model.tv.TvDetail;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: FeaturedContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/FeaturedContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface FeaturedContract {

    /* JADX INFO: compiled from: FeaturedContract.kt */
    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\u0018\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\f\u001a\u00020\rH&J\u0016\u0010\u0011\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H&J\b\u0010\u0015\u001a\u00020\u0003H&JL\u0010\u0016\u001a\u00020\u00032\u0016\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\u00180\u0017j\b\u0012\u0004\u0012\u00020\u0018`\u00192\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\u001a\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u001e\u001a\u00020\u001cH&J\b\u0010\u001f\u001a\u00020\u0003H&J\u0016\u0010 \u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H&¨\u0006!À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/FeaturedContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showVideo", "", "featuredDataModel", "Lcom/movieboxpro/android/model/FeaturedDataModel;", "loadVideoFailed", NotificationCompat.CATEGORY_MESSAGE, "", "goTvPlayer", "tvDetail", "Lcom/movieboxpro/android/model/tv/TvDetail;", "playImmediately", "", "goMoviePlayer", "movieDetail", "Lcom/movieboxpro/android/model/movie/MovieDetail;", "showContinueList", "list", "", "Lcom/movieboxpro/android/model/common/Homelist$Typelist;", "hideLoadView", "showScreenManage", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/model/DeviceModelResponse$DeviceModel;", "Lkotlin/collections/ArrayList;", "id", "boxType", "", "currSeason", "currEpisode", "deleteComplete", "showMaybeLikeList", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void deleteComplete();

        void goMoviePlayer(MovieDetail movieDetail, boolean playImmediately);

        void goTvPlayer(TvDetail tvDetail, boolean playImmediately);

        void hideLoadView();

        void loadVideoFailed(String msg);

        void showContinueList(List<? extends Homelist.Typelist> list);

        void showMaybeLikeList(List<? extends Homelist.Typelist> list);

        void showScreenManage(ArrayList<DeviceModelResponse.DeviceModel> list, String msg, String id, int boxType, int currSeason, int currEpisode);

        void showVideo(FeaturedDataModel featuredDataModel);
    }

    /* JADX INFO: compiled from: FeaturedContract.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H&J\\\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010\u0011\u001a\u00020\n2\b\b\u0002\u0010\u0012\u001a\u00020\nH&J\u0012\u0010\u0013\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0006H&J\b\u0010\u0014\u001a\u00020\u0004H&J\b\u0010\u0015\u001a\u00020\u0004H&¨\u0006\u0016À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/home/FeaturedContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/fragment/home/FeaturedContract$View;", "requestData", "", "uid", "", "getPlayPath", "id", "boxType", "", "currSeason", "currEpisode", "playImmediately", "", TvContractCompat.ProgramColumns.COLUMN_TITLE, "poster", "historyOver", "watchSeconds", "advNotify", "getContinue", "getMaybeLike", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void advNotify(String id);

        void getContinue();

        void getMaybeLike();

        void getPlayPath(String id, int boxType, int currSeason, int currEpisode, boolean playImmediately, String title, String poster, int historyOver, int watchSeconds);

        void requestData(String uid);

        /* JADX INFO: renamed from: com.movieboxpro.android.view.fragment.home.FeaturedContract$Presenter$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: FeaturedContract.kt */
        public final /* synthetic */ class CC {
            public static /* synthetic */ void getPlayPath$default(Presenter presenter, String str, int i, int i2, int i3, boolean z, String str2, String str3, int i4, int i5, int i6, Object obj) {
                if (obj != null) {
                    throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getPlayPath");
                }
                presenter.getPlayPath(str, i, i2, i3, z, (i6 & 32) != 0 ? null : str2, (i6 & 64) != 0 ? null : str3, (i6 & 128) != 0 ? 0 : i4, (i6 & 256) != 0 ? 0 : i5);
            }
        }

        /* JADX INFO: compiled from: FeaturedContract.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
        }
    }
}
