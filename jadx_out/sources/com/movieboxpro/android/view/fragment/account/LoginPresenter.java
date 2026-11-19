package com.movieboxpro.android.view.fragment.account;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Log;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.v2.LoginResponseParser;
import com.movieboxpro.android.http.v2.session.LoginCommitFailure;
import com.movieboxpro.android.http.v2.session.LoginCommitResult;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.QRCodeUtil;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LoginPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\tH\u0016J \u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\tH\u0002J\b\u0010\u0014\u001a\u00020\rH\u0002J\u0010\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/LoginPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/fragment/account/LoginContract$View;", "Lcom/movieboxpro/android/view/fragment/account/LoginContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "loginInProgress", "", "pendingCoordinator", "Lcom/movieboxpro/android/view/fragment/account/PendingLoginCoordinator;", "checkScanResult", "", "id", "", "addAccount", "beginDeviceBinding", "model", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "handleDuplicateScannedAccount", "addId", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {
    private boolean loginInProgress;
    private PendingLoginCoordinator pendingCoordinator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoginPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.fragment.account.LoginContract.Presenter
    public void checkScanResult(final String id, final boolean addAccount) {
        Intrinsics.checkNotNullParameter(id, "id");
        if (this.loginInProgress) {
            return;
        }
        ((ObservableSubscribeProxy) Http.getService().Add_qrcode(API.Common.LOGIN_QRCODE, id, BuildConfig.VERSION_NAME).compose(LoginResponseParser.rxTranslate2LoginUserData()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<UserModel.UserData>() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter.checkScanResult.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(UserModel.UserData model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (LoginPresenter.this.loginInProgress) {
                    return;
                }
                boolean z = addAccount;
                UserModel.UserData userData = App.getUserData();
                Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (AccountListHelperKt.shouldSkipScannedAccountLogin(model, z, userData, string, App.isLogin())) {
                    LoginPresenter.this.handleDuplicateScannedAccount();
                } else {
                    LoginPresenter.this.beginDeviceBinding(id, model, addAccount);
                }
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                Log.d("onError", "onError: ");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void beginDeviceBinding(final String id, final UserModel.UserData model, final boolean addAccount) {
        UserModel.UserData userDataCaptureAccountWithSession;
        this.loginInProgress = true;
        getView().stopPolling();
        if (addAccount) {
            UserModel.UserData userData = App.getUserData();
            Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
            userDataCaptureAccountWithSession = AccountListHelperKt.captureAccountWithSession(userData);
        } else {
            userDataCaptureAccountWithSession = null;
        }
        final UserModel.UserData userData2 = userDataCaptureAccountWithSession;
        final UserModel userModel = new UserModel();
        userModel.member = model;
        final PendingLoginCoordinator pendingLoginCoordinator = new PendingLoginCoordinator(new Function0() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoginPresenter.beginDeviceBinding$lambda$1(userModel);
            }
        }, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoginPresenter.beginDeviceBinding$lambda$2();
            }
        });
        this.pendingCoordinator = pendingLoginCoordinator;
        pendingLoginCoordinator.start(new Function0() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return LoginPresenter.beginDeviceBinding$lambda$4(model, id, this, pendingLoginCoordinator, addAccount, userData2);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LoginPresenter.beginDeviceBinding$lambda$5(this.f$0, (LoginCommitFailure) obj);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LoginPresenter.beginDeviceBinding$lambda$6(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LoginCommitResult beginDeviceBinding$lambda$1(UserModel userModel) {
        LoginCommitResult loginCommitResultLoginResult = App.loginResult(userModel);
        Intrinsics.checkNotNullExpressionValue(loginCommitResultLoginResult, "loginResult(...)");
        return loginCommitResultLoginResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit beginDeviceBinding$lambda$2() {
        App.logout();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit beginDeviceBinding$lambda$4(UserModel.UserData userData, String str, LoginPresenter loginPresenter, final PendingLoginCoordinator pendingLoginCoordinator, boolean z, UserModel.UserData userData2) {
        ((ObservableSubscribeProxy) Http.getService().Device(API.USER.DEVICE, "", userData.uid_v2, Build.BRAND, Build.MODEL, "", "", str, BuildConfig.APPLICATION_ID, String.valueOf(TimeUtils.getCurrentTime() / ((long) 1000)), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).doFinally(new Action() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Action
            public final void run() {
                pendingLoginCoordinator.cancel();
            }
        }).as(RxUtils.bindLifecycleOwner(loginPresenter.mLifecycleOwner))).subscribe(new LoginPresenter$beginDeviceBinding$1$2(loginPresenter, pendingLoginCoordinator, z, userData2, userData));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit beginDeviceBinding$lambda$5(LoginPresenter loginPresenter, LoginCommitFailure reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        loginPresenter.pendingCoordinator = null;
        loginPresenter.loginInProgress = false;
        loginPresenter.getView().hideLoadingView();
        ToastUtils.showShort(PendingLoginCoordinatorKt.loginCouldNotBeSavedMessage(reason), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit beginDeviceBinding$lambda$6(LoginPresenter loginPresenter, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        loginPresenter.pendingCoordinator = null;
        loginPresenter.loginInProgress = false;
        loginPresenter.getView().hideLoadingView();
        loginPresenter.getView().addIdFailed();
        ToastUtils.showShort("Login failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDuplicateScannedAccount() {
        getView().stopPolling();
        ToastUtils.showShort("This account is already logged in", new Object[0]);
        getView().onDuplicateAccountLogin();
    }

    @Override // com.movieboxpro.android.view.fragment.account.LoginContract.Presenter
    public void addId(final String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Observable<R> observableCompose = Http.getService().Add_qrcode(API.Common.ADD_QRCODE, id, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg());
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LoginPresenter.addId$lambda$7(id, (String) obj);
            }
        };
        ((ObservableSubscribeProxy) observableCompose.map(new Function() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter$$ExternalSyntheticLambda7
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return LoginPresenter.addId$lambda$8(function1, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<Bitmap>() { // from class: com.movieboxpro.android.view.fragment.account.LoginPresenter.addId.2
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
                LoginPresenter.this.getView().showLoadingView();
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(Bitmap bitmap) {
                LoginPresenter.this.getView().hideLoadingView();
                LoginPresenter.this.getView().addIdSuccess(bitmap);
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                LoginPresenter.this.getView().hideLoadingView();
                LoginPresenter.this.getView().addIdFailed();
                ToastUtils.showShort("Load failed:" + (e != null ? e.getMessage() : null), new Object[0]);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap addId$lambda$8(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Bitmap) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Bitmap addId$lambda$7(String str, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return QRCodeUtil.createQRCodeBitmap(str, 480, 480);
    }
}
