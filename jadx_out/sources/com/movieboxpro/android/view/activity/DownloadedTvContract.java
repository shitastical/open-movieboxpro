package com.movieboxpro.android.view.activity;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.db.entity.DownloadFile;
import java.util.List;
import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadedTvContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadedTvContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface DownloadedTvContract {

    /* JADX INFO: compiled from: DownloadedTvContract.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H&J\u0016\u0010\u0007\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadedTvContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/activity/DownloadedTvContract$View;", "getDownloadedTv", "", "id", "", "deleteDownloadedTv", "list", "", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void deleteDownloadedTv(List<? extends DownloadFile> list);

        void getDownloadedTv(String id);
    }

    /* JADX INFO: compiled from: DownloadedTvContract.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\bH&¨\u0006\tÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/activity/DownloadedTvContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showTv", "", "list", "", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "deleteComplete", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void deleteComplete(List<? extends DownloadFile> list);

        void showTv(List<DownloadFile> list);
    }
}
