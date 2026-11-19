package com.movieboxpro.android.view.fragment.account;

import android.os.Build;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.base.mvp.BasePresenter;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.ServerException;
import com.movieboxpro.android.http.v2.LoginResponseParser;
import com.movieboxpro.android.http.v2.session.LoginCommitFailure;
import com.movieboxpro.android.http.v2.session.LoginCommitResult;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CodeLoginFragmentPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\f\u001a\u00020\rH\u0016J\"\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0012\u001a\u00020\tH\u0016J \u0010\u0013\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\tH\u0002J\b\u0010\u0016\u001a\u00020\rH\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentPresenter;", "Lcom/movieboxpro/android/base/mvp/BasePresenter;", "Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentContract$View;", "Lcom/movieboxpro/android/view/fragment/account/CodeLoginFragmentContract$Presenter;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "<init>", "(Landroidx/lifecycle/LifecycleOwner;)V", "loginInProgress", "", "pendingCoordinator", "Lcom/movieboxpro/android/view/fragment/account/PendingLoginCoordinator;", "getCode", "", "checkCode", "code", "", "id", "addAccount", "beginDeviceBinding", "model", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "handleDuplicateScannedAccount", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CodeLoginFragmentPresenter extends BasePresenter<CodeLoginFragmentContract.View> implements CodeLoginFragmentContract.Presenter {
    private boolean loginInProgress;
    private PendingLoginCoordinator pendingCoordinator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CodeLoginFragmentPresenter(LifecycleOwner lifecycleOwner) {
        super(lifecycleOwner);
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
    }

    @Override // com.movieboxpro.android.view.fragment.account.CodeLoginFragmentContract.Presenter
    public void getCode() {
        Observable<String> observableAdd_captcha = Http.getService().Add_captcha(API.Common.ADD_CAPTCHA);
        Intrinsics.checkNotNullExpressionValue(observableAdd_captcha, "Add_captcha(...)");
        LifecycleOwner mLifecycleOwner = this.mLifecycleOwner;
        Intrinsics.checkNotNullExpressionValue(mLifecycleOwner, "mLifecycleOwner");
        Observable<R> observableCompose = observableAdd_captcha.compose(RxUtils.rxTranslate2Bean(HashMap.class));
        Intrinsics.checkNotNullExpressionValue(observableCompose, "compose(...)");
        RxSubscribersKt.subscribeTo$default(RxSubscribersKt.transform(observableCompose, mLifecycleOwner), new Function1() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CodeLoginFragmentPresenter.getCode$lambda$0(this.f$0, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CodeLoginFragmentPresenter.getCode$lambda$1(this.f$0, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda8
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CodeLoginFragmentPresenter.getCode$lambda$2(this.f$0, (HashMap) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getCode$lambda$1(CodeLoginFragmentPresenter codeLoginFragmentPresenter, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        codeLoginFragmentPresenter.getView().showLoadingView();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getCode$lambda$2(CodeLoginFragmentPresenter codeLoginFragmentPresenter, HashMap it) {
        Intrinsics.checkNotNullParameter(it, "it");
        codeLoginFragmentPresenter.getView().hideLoadingView();
        codeLoginFragmentPresenter.loginInProgress = false;
        CodeLoginFragmentContract.View view = codeLoginFragmentPresenter.getView();
        String str = (String) it.get("code");
        if (str == null) {
            str = "";
        }
        view.showCode(str);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit getCode$lambda$0(CodeLoginFragmentPresenter codeLoginFragmentPresenter, ApiException it) {
        Intrinsics.checkNotNullParameter(it, "it");
        codeLoginFragmentPresenter.getView().hideLoadingView();
        codeLoginFragmentPresenter.getView().showTryView();
        ToastUtils.showShort("Load failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    @Override // com.movieboxpro.android.view.fragment.account.CodeLoginFragmentContract.Presenter
    public void checkCode(String code, final String id, final boolean addAccount) {
        Intrinsics.checkNotNullParameter(code, "code");
        if (this.loginInProgress) {
            return;
        }
        ((ObservableSubscribeProxy) Http.getService().checkCodeLogin(API.Common.CODE_LOGIN_CAPTCHA, id, code).compose(LoginResponseParser.rxTranslate2LoginUserData()).compose(RxUtils.rxSchedulerHelper()).as(RxUtils.bindLifecycleOwner(this.mLifecycleOwner))).subscribe(new HttpResponseObserver<UserModel.UserData>() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter.checkCode.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(UserModel.UserData model) {
                Intrinsics.checkNotNullParameter(model, "model");
                if (CodeLoginFragmentPresenter.this.loginInProgress) {
                    return;
                }
                boolean z = addAccount;
                UserModel.UserData userData = App.getUserData();
                Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
                String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                if (AccountListHelperKt.shouldSkipScannedAccountLogin(model, z, userData, string, App.isLogin())) {
                    CodeLoginFragmentPresenter.this.handleDuplicateScannedAccount();
                    return;
                }
                CodeLoginFragmentPresenter codeLoginFragmentPresenter = CodeLoginFragmentPresenter.this;
                String str = id;
                codeLoginFragmentPresenter.beginDeviceBinding(str != null ? str : "", model, addAccount);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
                if (!(e instanceof ServerException) || e.getCode() == -1) {
                    return;
                }
                ToastUtils.showShort(((ServerException) e).getMessage(), new Object[0]);
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
        final PendingLoginCoordinator pendingLoginCoordinator = new PendingLoginCoordinator(new Function0() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CodeLoginFragmentPresenter.beginDeviceBinding$lambda$4(userModel);
            }
        }, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CodeLoginFragmentPresenter.beginDeviceBinding$lambda$5();
            }
        });
        this.pendingCoordinator = pendingLoginCoordinator;
        pendingLoginCoordinator.start(new Function0() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return CodeLoginFragmentPresenter.beginDeviceBinding$lambda$7(model, id, this, pendingLoginCoordinator, addAccount, userData2);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CodeLoginFragmentPresenter.beginDeviceBinding$lambda$8(this.f$0, (LoginCommitFailure) obj);
            }
        }, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return CodeLoginFragmentPresenter.beginDeviceBinding$lambda$9(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LoginCommitResult beginDeviceBinding$lambda$4(UserModel userModel) {
        LoginCommitResult loginCommitResultLoginResult = App.loginResult(userModel);
        Intrinsics.checkNotNullExpressionValue(loginCommitResultLoginResult, "loginResult(...)");
        return loginCommitResultLoginResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit beginDeviceBinding$lambda$5() {
        App.logout();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit beginDeviceBinding$lambda$7(UserModel.UserData userData, String str, CodeLoginFragmentPresenter codeLoginFragmentPresenter, final PendingLoginCoordinator pendingLoginCoordinator, boolean z, UserModel.UserData userData2) {
        ((ObservableSubscribeProxy) Http.getService().Device(API.USER.DEVICE, "", userData.uid_v2, SystemUtils.getOSType(), Build.MODEL, "", "", str, BuildConfig.APPLICATION_ID, String.valueOf(TimeUtils.getCurrentTime() / ((long) 1000)), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).doFinally(new Action() { // from class: com.movieboxpro.android.view.fragment.account.CodeLoginFragmentPresenter$$ExternalSyntheticLambda5
            @Override // io.reactivex.functions.Action
            public final void run() {
                pendingLoginCoordinator.cancel();
            }
        }).as(RxUtils.bindLifecycleOwner(codeLoginFragmentPresenter.mLifecycleOwner))).subscribe(new CodeLoginFragmentPresenter$beginDeviceBinding$1$2(codeLoginFragmentPresenter, pendingLoginCoordinator, z, userData2, userData));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit beginDeviceBinding$lambda$8(CodeLoginFragmentPresenter codeLoginFragmentPresenter, LoginCommitFailure reason) {
        Intrinsics.checkNotNullParameter(reason, "reason");
        codeLoginFragmentPresenter.pendingCoordinator = null;
        codeLoginFragmentPresenter.loginInProgress = false;
        codeLoginFragmentPresenter.getView().hideLoadingView();
        codeLoginFragmentPresenter.getView().showTryView();
        ToastUtils.showShort(PendingLoginCoordinatorKt.loginCouldNotBeSavedMessage(reason), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit beginDeviceBinding$lambda$9(CodeLoginFragmentPresenter codeLoginFragmentPresenter, Throwable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        codeLoginFragmentPresenter.pendingCoordinator = null;
        codeLoginFragmentPresenter.loginInProgress = false;
        codeLoginFragmentPresenter.getView().hideLoadingView();
        codeLoginFragmentPresenter.getView().showTryView();
        ToastUtils.showShort("Login failed:" + it.getMessage(), new Object[0]);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleDuplicateScannedAccount() {
        getView().stopPolling();
        ToastUtils.showShort("This account is already logged in", new Object[0]);
        getView().onDuplicateAccountLogin();
    }
}
