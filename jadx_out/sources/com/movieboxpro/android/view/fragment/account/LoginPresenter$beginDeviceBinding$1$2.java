package com.movieboxpro.android.view.fragment.account;

import androidx.media3.exoplayer.upstream.CmcdData;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.event.LoginEvent;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.ToastUtils;
import io.reactivex.disposables.Disposable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: LoginPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"com/movieboxpro/android/view/fragment/account/LoginPresenter$beginDeviceBinding$1$2", "Lcom/movieboxpro/android/base/HttpResponseObserver;", "", "onStart", "", "d", "Lio/reactivex/disposables/Disposable;", "onSuccess", CmcdData.STREAMING_FORMAT_SS, "onError", "e", "Lcom/movieboxpro/android/http/ApiException;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoginPresenter$beginDeviceBinding$1$2 extends HttpResponseObserver<String> {
    final /* synthetic */ boolean $addAccount;
    final /* synthetic */ PendingLoginCoordinator $coordinator;
    final /* synthetic */ UserModel.UserData $model;
    final /* synthetic */ UserModel.UserData $previousAccount;
    final /* synthetic */ LoginPresenter this$0;

    LoginPresenter$beginDeviceBinding$1$2(LoginPresenter loginPresenter, PendingLoginCoordinator pendingLoginCoordinator, boolean z, UserModel.UserData userData, UserModel.UserData userData2) {
        this.this$0 = loginPresenter;
        this.$coordinator = pendingLoginCoordinator;
        this.$addAccount = z;
        this.$previousAccount = userData;
        this.$model = userData2;
    }

    @Override // com.movieboxpro.android.base.HttpResponseObserver
    public void onStart(Disposable d) {
        Intrinsics.checkNotNullParameter(d, "d");
        this.this$0.getView().showLoadingView();
    }

    @Override // com.movieboxpro.android.base.HttpResponseObserver
    public void onSuccess(String s) {
        PendingLoginCoordinator pendingLoginCoordinator = this.$coordinator;
        final LoginPresenter loginPresenter = this.this$0;
        final boolean z = this.$addAccount;
        final UserModel.UserData userData = this.$previousAccount;
        final UserModel.UserData userData2 = this.$model;
        pendingLoginCoordinator.commit(new Function0() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$beginDeviceBinding$1$2$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoginPresenter$beginDeviceBinding$1$2.onSuccess$lambda$0(loginPresenter, z, userData, userData2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onSuccess$lambda$0(LoginPresenter loginPresenter, boolean z, UserModel.UserData userData, UserModel.UserData userData2) {
        loginPresenter.pendingCoordinator = null;
        loginPresenter.getView().hideLoadingView();
        if (z && userData != null) {
            AccountListHelperKt.appendSavedAccount(userData);
        }
        EventBus.getDefault().post(new LoginEvent());
        loginPresenter.getView().loginSuccess(userData2);
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.base.HttpResponseObserver
    public void onError(final ApiException e) {
        PendingLoginCoordinator pendingLoginCoordinator = this.$coordinator;
        final LoginPresenter loginPresenter = this.this$0;
        pendingLoginCoordinator.fail(new Function0() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$beginDeviceBinding$1$2$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoginPresenter$beginDeviceBinding$1$2.onError$lambda$1(loginPresenter, e);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onError$lambda$1(LoginPresenter loginPresenter, ApiException apiException) {
        loginPresenter.pendingCoordinator = null;
        loginPresenter.loginInProgress = false;
        loginPresenter.getView().hideLoadingView();
        loginPresenter.getView().addIdFailed();
        ToastUtils.showShort("Login failed:" + (apiException != null ? apiException.getMessage() : null), new Object[0]);
        return Unit.INSTANCE;
    }
}
