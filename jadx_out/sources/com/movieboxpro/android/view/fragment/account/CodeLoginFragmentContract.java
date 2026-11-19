package com.movieboxpro.android.view.fragment.account;

import com.movieboxpro.android.base.mvp.BaseContract;
import com.movieboxpro.android.model.user.UserModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: CodeLoginFragmentContract.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001:\u0002\u0002\u0003¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentContract;", "", "View", "Presenter", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CodeLoginFragmentContract {

    /* JADX INFO: compiled from: CodeLoginFragmentContract.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0004H&J\"\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentContract$Presenter;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BasePresenter;", "Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentContract$View;", "getCode", "", "checkCode", "code", "", "id", "addAccount", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Presenter extends BaseContract.BasePresenter<View> {
        void checkCode(String code, String id, boolean addAccount);

        void getCode();
    }

    /* JADX INFO: compiled from: CodeLoginFragmentContract.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&J\b\u0010\n\u001a\u00020\u0003H&J\b\u0010\u000b\u001a\u00020\u0003H&J\b\u0010\f\u001a\u00020\u0003H&¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentContract$View;", "Lcom/movieboxpro/android/base/mvp/BaseContract$BaseView;", "showCode", "", "code", "", "showTryView", "loginSuccess", "model", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "addAccountFailed", "onDuplicateAccountLogin", "stopPolling", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface View extends BaseContract.BaseView {
        void addAccountFailed();

        void loginSuccess(UserModel.UserData model);

        void onDuplicateAccountLogin();

        void showCode(String code);

        void showTryView();

        void stopPolling();
    }
}
