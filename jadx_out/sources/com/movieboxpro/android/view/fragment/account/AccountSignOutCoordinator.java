package com.movieboxpro.android.view.fragment.account;

import android.content.Context;
import androidx.lifecycle.LifecycleOwner;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.tv.LoginActivity;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.utils.ToastUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AccountSignOutCoordinator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JB\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bJ\u0012\u0010\u000e\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0002JL\u0010\u000f\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bH\u0002¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/AccountSignOutCoordinator;", "", "<init>", "()V", "signOut", "", "context", "Landroid/content/Context;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "showLoading", "Lkotlin/Function0;", "hideLoading", "onMultiAccountComplete", "signOutSingleAccount", "signOutMultiAccount", "accountListJson", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AccountSignOutCoordinator {
    public static final AccountSignOutCoordinator INSTANCE = new AccountSignOutCoordinator();

    private AccountSignOutCoordinator() {
    }

    public final void signOut(Context context, LifecycleOwner lifecycleOwner, Function0<Unit> showLoading, Function0<Unit> hideLoading, Function0<Unit> onMultiAccountComplete) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(showLoading, "showLoading");
        Intrinsics.checkNotNullParameter(hideLoading, "hideLoading");
        Intrinsics.checkNotNullParameter(onMultiAccountComplete, "onMultiAccountComplete");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
        AccountSwitchHelperKt.requestLoginOut(context);
        Intrinsics.checkNotNull(string);
        if (!AccountSignOutCoordinatorKt.isMultiAccount(string)) {
            signOutSingleAccount(context);
        } else {
            signOutMultiAccount(context, lifecycleOwner, string, showLoading, hideLoading, onMultiAccountComplete);
        }
    }

    private final void signOutSingleAccount(Context context) {
        boolean z = PrefsUtils.getInstance().getBoolean(Constant.Prefs.CHILD_MODE, false);
        App.logout();
        PrefsUtils.getInstance().putBoolean(Constant.Prefs.CHILD_MODE, z);
        LoginActivity.INSTANCE.startAfterSignOut(context);
    }

    private final void signOutMultiAccount(final Context context, LifecycleOwner lifecycleOwner, final String accountListJson, Function0<Unit> showLoading, final Function0<Unit> hideLoading, final Function0<Unit> onMultiAccountComplete) {
        List<UserModel.UserData> accountList = AccountSignOutCoordinatorKt.parseAccountList(accountListJson);
        if (accountList.isEmpty()) {
            signOutSingleAccount(context);
        } else {
            AccountSwitchHelperKt.performAccountSwitch(lifecycleOwner, context, accountList.get(0), AccountSignOutCoordinatorKt.remainingAccountsAfterDeleteCurrent(accountList), showLoading, new Function0() { // from class: com.movieboxpro.android.view.fragment.account.AccountSignOutCoordinator$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return AccountSignOutCoordinator.signOutMultiAccount$lambda$0(hideLoading, onMultiAccountComplete);
                }
            }, new Function2() { // from class: com.movieboxpro.android.view.fragment.account.AccountSignOutCoordinator$$ExternalSyntheticLambda1
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return AccountSignOutCoordinator.signOutMultiAccount$lambda$1(hideLoading, accountListJson, context, (Throwable) obj, ((Boolean) obj2).booleanValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit signOutMultiAccount$lambda$0(Function0 function0, Function0 function1) {
        function0.invoke();
        function1.invoke();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit signOutMultiAccount$lambda$1(Function0 function0, String str, Context context, Throwable error, boolean z) {
        Intrinsics.checkNotNullParameter(error, "error");
        function0.invoke();
        AccountSignOutCoordinatorKt.restoreAccountList(str);
        if (AccountSignOutCoordinatorKt.shouldLogoutAfterSwitchFailure(z)) {
            App.logout();
            LoginActivity.INSTANCE.startAfterSignOut(context);
        }
        ToastUtils.showShort(AccountSwitchHelperKt.switchAccountErrorMessage(error), new Object[0]);
        return Unit.INSTANCE;
    }
}
