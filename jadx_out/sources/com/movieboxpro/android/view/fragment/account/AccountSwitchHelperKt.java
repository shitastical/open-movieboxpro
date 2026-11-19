package com.movieboxpro.android.view.fragment.account;

import android.content.Context;
import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LifecycleOwner;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.base.HttpResponseObserver;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiException;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.v2.session.LoginCommitResult;
import com.movieboxpro.android.livedata.SwitchAccountLiveData;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.RxSubscribersKt;
import com.movieboxpro.android.utils.RxUtils;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.utils.TimeUtils;
import com.movieboxpro.androidtv.BuildConfig;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: AccountSwitchHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0012\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001au\u0010\b\u001a\u00020\u00012\u0006\u0010\t\u001a\u00020\n2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u00102'\u0010\u0012\u001a#\u0012\u0004\u0012\u00020\u0007\u0012\u0013\u0012\u00110\u0014¢\u0006\f\b\u0015\u0012\b\b\u0016\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00010\u0013H\u0000¨\u0006\u0018"}, d2 = {"requestLoginOut", "", "context", "Landroid/content/Context;", "switchAccountErrorMessage", "", "error", "", "performAccountSwitch", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", TypedValues.AttributesType.S_TARGET, "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "updatedAccountList", "", "onStart", "Lkotlin/Function0;", "onSuccess", "onError", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "loginCommitted", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AccountSwitchHelperKt {
    public static final void requestLoginOut(Context context) {
        Http.getService().login_out(API.USER.LOGIN_OUT, SystemUtils.getUniqueId(context), BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg()).compose(RxUtils.rxSchedulerHelper()).subscribe(new HttpResponseObserver<String>() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt.requestLoginOut.1
            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onError(ApiException e) {
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onStart(Disposable d) {
                Intrinsics.checkNotNullParameter(d, "d");
            }

            @Override // com.movieboxpro.android.base.HttpResponseObserver
            public void onSuccess(String model) {
            }
        });
    }

    public static final String switchAccountErrorMessage(Throwable error) {
        Intrinsics.checkNotNullParameter(error, "error");
        if (error instanceof MissingAccountSessionException) {
            String message = error.getMessage();
            return message == null ? "Session expired" : message;
        }
        if (error instanceof ApiException) {
            return "Login failed:" + ((ApiException) error).getMessage();
        }
        return "Login failed:" + ApiException.handleException(error).getMessage();
    }

    public static final void performAccountSwitch(LifecycleOwner lifecycleOwner, final Context context, UserModel.UserData target, final List<? extends UserModel.UserData> updatedAccountList, final Function0<Unit> onStart, final Function0<Unit> onSuccess, final Function2<? super Throwable, ? super Boolean, Unit> onError) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(target, "target");
        Intrinsics.checkNotNullParameter(updatedAccountList, "updatedAccountList");
        Intrinsics.checkNotNullParameter(onStart, "onStart");
        Intrinsics.checkNotNullParameter(onSuccess, "onSuccess");
        Intrinsics.checkNotNullParameter(onError, "onError");
        if (!AccountListHelperKt.hasStoredSessionCredentials(target)) {
            onError.invoke(new MissingAccountSessionException(), false);
            return;
        }
        final UserModel.UserData userDataCloneAccountSnapshot = AccountListHelperKt.cloneAccountSnapshot(target);
        UserModel.UserData userData = App.getUserData();
        Intrinsics.checkNotNullExpressionValue(userData, "getUserData(...)");
        final UserModel.UserData userDataCaptureAccountWithSession = AccountListHelperKt.captureAccountWithSession(userData);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        AccountSwitchGuard.INSTANCE.enter();
        Observable observableJust = Observable.just(userDataCloneAccountSnapshot);
        final Function1 function1 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSwitchHelperKt.performAccountSwitch$lambda$1(booleanRef, (UserModel.UserData) obj);
            }
        };
        Observable map = observableJust.map(new Function() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AccountSwitchHelperKt.performAccountSwitch$lambda$2(function1, obj);
            }
        });
        final Function1 function2 = new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSwitchHelperKt.performAccountSwitch$lambda$3(userDataCloneAccountSnapshot, context, (Unit) obj);
            }
        };
        Object objAs = map.flatMap(new Function() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt$$ExternalSyntheticLambda3
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AccountSwitchHelperKt.performAccountSwitch$lambda$4(function2, obj);
            }
        }).compose(RxUtils.rxSchedulerHelper()).doFinally(new Action() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt$$ExternalSyntheticLambda4
            @Override // io.reactivex.functions.Action
            public final void run() {
                AccountSwitchHelperKt.performAccountSwitch$lambda$5();
            }
        }).as(RxUtils.bindLifecycleOwner(lifecycleOwner));
        Intrinsics.checkNotNullExpressionValue(objAs, "as(...)");
        RxSubscribersKt.subscribeTo$default((ObservableSubscribeProxy) objAs, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSwitchHelperKt.performAccountSwitch$lambda$7(booleanRef, onError, userDataCaptureAccountWithSession, (ApiException) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSwitchHelperKt.performAccountSwitch$lambda$8(onStart, (Disposable) obj);
            }
        }, null, new Function1() { // from class: com.movieboxpro.android.view.fragment.account.AccountSwitchHelperKt$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AccountSwitchHelperKt.performAccountSwitch$lambda$9(updatedAccountList, onSuccess, (String) obj);
            }
        }, 10, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit performAccountSwitch$lambda$2(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (Unit) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit performAccountSwitch$lambda$1(Ref.BooleanRef booleanRef, UserModel.UserData it) {
        Intrinsics.checkNotNullParameter(it, "it");
        UserModel userModel = new UserModel();
        userModel.member = it;
        LoginCommitResult loginCommitResultLoginResult = App.loginResult(userModel);
        Intrinsics.checkNotNullExpressionValue(loginCommitResultLoginResult, "loginResult(...)");
        if (!(loginCommitResultLoginResult instanceof LoginCommitResult.Success)) {
            if (!(loginCommitResultLoginResult instanceof LoginCommitResult.Failure)) {
                throw new NoWhenBranchMatchedException();
            }
            throw new IllegalStateException(PendingLoginCoordinatorKt.loginCouldNotBeSavedMessage(((LoginCommitResult.Failure) loginCommitResultLoginResult).getReason()));
        }
        booleanRef.element = true;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource performAccountSwitch$lambda$4(Function1 function1, Object p0) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        return (ObservableSource) function1.invoke(p0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ObservableSource performAccountSwitch$lambda$3(UserModel.UserData userData, Context context, Unit it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return Http.getService().Device(API.USER.DEVICE, "", userData.uid_v2, Build.BRAND, Build.MODEL, "", "", SystemUtils.getUniqueId(context), BuildConfig.APPLICATION_ID, String.valueOf(TimeUtils.getCurrentTime() / ((long) 1000)), IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE, BuildConfig.VERSION_NAME).compose(RxUtils.rxTranslateMsg());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void performAccountSwitch$lambda$5() {
        AccountSwitchGuard.INSTANCE.exit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit performAccountSwitch$lambda$8(Function0 function0, Disposable it) {
        Intrinsics.checkNotNullParameter(it, "it");
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit performAccountSwitch$lambda$9(List list, Function0 function0, String str) {
        AccountListHelperKt.persistAccountList(list);
        SwitchAccountLiveData.INSTANCE.get().setValue(true);
        function0.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit performAccountSwitch$lambda$7(Ref.BooleanRef booleanRef, Function2 function2, UserModel.UserData userData, ApiException error) {
        Intrinsics.checkNotNullParameter(error, "error");
        if (booleanRef.element) {
            UserModel userModel = new UserModel();
            userModel.member = AccountListHelperKt.cloneAccountSnapshot(userData);
            App.login(userModel);
        }
        function2.invoke(error, Boolean.valueOf(booleanRef.element));
        return Unit.INSTANCE;
    }
}
