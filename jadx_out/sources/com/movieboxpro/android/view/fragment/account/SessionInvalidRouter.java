package com.movieboxpro.android.view.fragment.account;

import android.content.Context;
import android.content.Intent;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.tv.LoginActivity;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionInvalidRouter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/movieboxpro/android/view/fragment/account/SessionInvalidRouter;", "", "<init>", "()V", "EXTRA_SHOW_ACCOUNT_MANAGER", "", "isMultiAccountSessionInvalidRoute", "", "accountListJson", "startAfterForcedLogout", "", "context", "Landroid/content/Context;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionInvalidRouter {
    public static final String EXTRA_SHOW_ACCOUNT_MANAGER = "show_account_manager";
    public static final SessionInvalidRouter INSTANCE = new SessionInvalidRouter();

    private SessionInvalidRouter() {
    }

    public final boolean isMultiAccountSessionInvalidRoute(String accountListJson) {
        Intrinsics.checkNotNullParameter(accountListJson, "accountListJson");
        return AccountSignOutCoordinatorKt.isMultiAccount(accountListJson);
    }

    @JvmStatic
    public static final void startAfterForcedLogout(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
        SessionInvalidRouter sessionInvalidRouter = INSTANCE;
        Intrinsics.checkNotNull(string);
        if (sessionInvalidRouter.isMultiAccountSessionInvalidRoute(string)) {
            Intent intent = new Intent(context, (Class<?>) MainActivity.class);
            intent.putExtra(EXTRA_SHOW_ACCOUNT_MANAGER, true);
            intent.addFlags(268468224);
            context.startActivity(intent);
            return;
        }
        Intent intent2 = new Intent(context, (Class<?>) LoginActivity.class);
        intent2.addFlags(268468224);
        context.startActivity(intent2);
    }
}
