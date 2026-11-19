package com.movieboxpro.android.view.fragment.account;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.PrefsUtils;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AccountSignOutCoordinator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001c\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0000\u001a\u0016\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0003H\u0000\u001a\u0010\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0000¨\u0006\u000e"}, d2 = {"isMultiAccount", "", "accountListJson", "", "remainingAccountsAfterDeleteCurrent", "", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "accountList", "parseAccountList", "restoreAccountList", "", "previousAccountListJson", "shouldLogoutAfterSwitchFailure", "loginCommitted", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AccountSignOutCoordinatorKt {
    public static final boolean shouldLogoutAfterSwitchFailure(boolean z) {
        return z;
    }

    public static final boolean isMultiAccount(String accountListJson) {
        Intrinsics.checkNotNullParameter(accountListJson, "accountListJson");
        return !StringsKt.isBlank(accountListJson);
    }

    public static final List<UserModel.UserData> remainingAccountsAfterDeleteCurrent(List<? extends UserModel.UserData> accountList) {
        Intrinsics.checkNotNullParameter(accountList, "accountList");
        return accountList.isEmpty() ? CollectionsKt.emptyList() : CollectionsKt.drop(accountList, 1);
    }

    public static final List<UserModel.UserData> parseAccountList(String accountListJson) {
        Intrinsics.checkNotNullParameter(accountListJson, "accountListJson");
        try {
            List<UserModel.UserData> array = JSONObject.parseArray(accountListJson, UserModel.UserData.class);
            return array == null ? CollectionsKt.emptyList() : array;
        } catch (Exception unused) {
            return CollectionsKt.emptyList();
        }
    }

    public static final void restoreAccountList(String previousAccountListJson) {
        Intrinsics.checkNotNullParameter(previousAccountListJson, "previousAccountListJson");
        PrefsUtils.getInstance().putString(Constant.Prefs.ACCOUNT_LIST, previousAccountListJson);
    }
}
