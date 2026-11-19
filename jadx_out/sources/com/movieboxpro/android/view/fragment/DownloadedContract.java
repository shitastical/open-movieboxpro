package com.movieboxpro.android.view.fragment;

import com.movieboxpro.android.base.mvp.BaseContract;
import kotlin.Metadata;

/* JADX INFO: compiled from: DownloadedContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/DownloadedContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface DownloadedContract {

    /* JADX INFO: compiled from: DownloadedContract.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/DownloadedContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/fragment/DownloadedContract$View;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
    }

    /* JADX INFO: compiled from: DownloadedContract.kt */
    @Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001¨\u0006\u0002À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/DownloadedContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
    }
}
