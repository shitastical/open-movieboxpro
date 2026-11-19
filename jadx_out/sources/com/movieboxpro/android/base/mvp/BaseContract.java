package com.movieboxpro.android.base.mvp;

/* JADX INFO: loaded from: classes5.dex */
public class BaseContract {

    public interface BasePresenter<T extends BaseView> {
        void attachView(T view);

        void detachView();

        T getView();

        boolean isViewAttached();
    }

    public interface BaseView {
        void hideLoading();

        void hideLoadingView();

        void showEmptyState();

        void showError(String message);

        void showErrorPage();

        void showFinish(String msg);

        void showLoading();

        void showLoadingView();
    }
}
