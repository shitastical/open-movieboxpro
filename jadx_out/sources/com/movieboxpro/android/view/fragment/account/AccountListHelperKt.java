package com.movieboxpro.android.view.fragment.account;

import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.v2.session.ApiSessionStore;
import com.movieboxpro.android.http.v2.session.SessionCredentials;
import com.movieboxpro.android.model.user.UserModel;
import com.movieboxpro.android.utils.PrefsUtils;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import ly.count.android.sdk.messaging.ModulePush;

/* JADX INFO: compiled from: AccountListHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u0001H\u0000\u001a\u0016\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0000\u001a\u0016\u0010\u000b\u001a\u00020\f2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0000\u001a\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u0001H\u0000\u001a\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0012\u001a\u00020\bH\u0000\u001a$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0015\u001a\u00020\u0001H\u0000\u001a\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u0001H\u0000\u001a\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\bH\u0000\u001a \u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\bH\u0000\u001a0\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\u000eH\u0000\u001a\u0010\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u0002\u001a\u00020\u0001H\u0000¨\u0006\u001f"}, d2 = {"cloneUserData", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "user", "cloneAccountSnapshot", "captureAccountWithSession", "accountToJsonObject", "Lcom/alibaba/fastjson/JSONObject;", "serializeAccountList", "", "accounts", "", "persistAccountList", "", "isSameAccount", "", "a", ModulePush.PUSH_EVENT_ACTION_INDEX_KEY, "parseSavedAccountList", "savedJson", "appendAccountToList", "saved", "account", "appendSavedAccount", "mergeDisplayAccounts", "current", "isAccountAlreadyKnown", "candidate", "shouldSkipScannedAccountLogin", "addAccount", "isLoggedIn", "hasStoredSessionCredentials", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class AccountListHelperKt {
    public static final UserModel.UserData cloneUserData(UserModel.UserData user) {
        Intrinsics.checkNotNullParameter(user, "user");
        Object object = JSONObject.parseObject(JSONObject.toJSONString(user), (Class<Object>) UserModel.UserData.class);
        Intrinsics.checkNotNullExpressionValue(object, "parseObject(...)");
        return (UserModel.UserData) object;
    }

    public static final UserModel.UserData cloneAccountSnapshot(UserModel.UserData user) {
        Intrinsics.checkNotNullParameter(user, "user");
        UserModel.UserData userDataCloneUserData = cloneUserData(user);
        userDataCloneUserData.session_key = user.session_key;
        userDataCloneUserData.sk_ver = user.sk_ver;
        userDataCloneUserData.uid_v2_exp = user.uid_v2_exp;
        return userDataCloneUserData;
    }

    public static final UserModel.UserData captureAccountWithSession(UserModel.UserData user) {
        Intrinsics.checkNotNullParameter(user, "user");
        UserModel.UserData userDataCloneUserData = cloneUserData(user);
        SessionCredentials sessionCredentialsCurrent = Http.getApiSessionManager().current();
        if (sessionCredentialsCurrent == null) {
            return userDataCloneUserData;
        }
        byte[] sessionKey = sessionCredentialsCurrent.getSessionKey();
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        userDataCloneUserData.session_key = new String(sessionKey, UTF_8);
        userDataCloneUserData.sk_ver = sessionCredentialsCurrent.getSkVer();
        userDataCloneUserData.uid_v2_exp = sessionCredentialsCurrent.getUidV2Exp();
        return userDataCloneUserData;
    }

    public static final JSONObject accountToJsonObject(UserModel.UserData user) {
        Intrinsics.checkNotNullParameter(user, "user");
        JSONObject object = JSONObject.parseObject(JSONObject.toJSONString(user));
        String str = user.session_key;
        if (str != null) {
            if (StringsKt.isBlank(str)) {
                str = null;
            }
            if (str != null) {
                Intrinsics.checkNotNull(object);
                object.put(ApiSessionStore.KEY_SESSION_KEY, str);
            }
        }
        String str2 = user.sk_ver;
        if (str2 != null) {
            String str3 = StringsKt.isBlank(str2) ? null : str2;
            if (str3 != null) {
                Intrinsics.checkNotNull(object);
                object.put(ApiSessionStore.KEY_SK_VER, str3);
            }
        }
        if (user.uid_v2_exp > 0) {
            Intrinsics.checkNotNull(object);
            object.put(ApiSessionStore.KEY_UID_V2_EXP, Long.valueOf(user.uid_v2_exp));
        }
        Intrinsics.checkNotNull(object);
        return object;
    }

    public static final String serializeAccountList(List<? extends UserModel.UserData> accounts) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        List<? extends UserModel.UserData> list = accounts;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(accountToJsonObject((UserModel.UserData) it.next()));
        }
        String jSONString = JSONObject.toJSONString(arrayList);
        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(...)");
        return jSONString;
    }

    public static final void persistAccountList(List<? extends UserModel.UserData> accounts) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        PrefsUtils.getInstance().putString(Constant.Prefs.ACCOUNT_LIST, serializeAccountList(accounts));
    }

    public static final boolean isSameAccount(UserModel.UserData a, UserModel.UserData b) {
        Intrinsics.checkNotNullParameter(a, "a");
        Intrinsics.checkNotNullParameter(b, "b");
        String uid = a.uid;
        Intrinsics.checkNotNullExpressionValue(uid, "uid");
        if (!StringsKt.isBlank(uid) && Intrinsics.areEqual(a.uid, b.uid)) {
            return true;
        }
        String uid_v2 = a.uid_v2;
        Intrinsics.checkNotNullExpressionValue(uid_v2, "uid_v2");
        return !StringsKt.isBlank(uid_v2) && Intrinsics.areEqual(a.uid_v2, b.uid_v2);
    }

    public static final List<UserModel.UserData> parseSavedAccountList(String savedJson) {
        Intrinsics.checkNotNullParameter(savedJson, "savedJson");
        if (StringsKt.isBlank(savedJson)) {
            return CollectionsKt.emptyList();
        }
        try {
            List<UserModel.UserData> array = JSONObject.parseArray(savedJson, UserModel.UserData.class);
            return array == null ? CollectionsKt.emptyList() : array;
        } catch (Exception unused) {
            return CollectionsKt.emptyList();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final List<UserModel.UserData> appendAccountToList(List<? extends UserModel.UserData> saved, UserModel.UserData account) {
        Intrinsics.checkNotNullParameter(saved, "saved");
        Intrinsics.checkNotNullParameter(account, "account");
        List<? extends UserModel.UserData> list = saved;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                if (isSameAccount((UserModel.UserData) it.next(), account)) {
                    return saved;
                }
            }
        }
        return CollectionsKt.plus((Collection<? extends UserModel.UserData>) saved, account);
    }

    public static final void appendSavedAccount(UserModel.UserData account) {
        Intrinsics.checkNotNullParameter(account, "account");
        String string = PrefsUtils.getInstance().getString(Constant.Prefs.ACCOUNT_LIST, "");
        Intrinsics.checkNotNull(string);
        persistAccountList(appendAccountToList(parseSavedAccountList(string), account));
    }

    public static final List<UserModel.UserData> mergeDisplayAccounts(UserModel.UserData current, String savedJson) {
        Intrinsics.checkNotNullParameter(current, "current");
        Intrinsics.checkNotNullParameter(savedJson, "savedJson");
        ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf(current);
        for (UserModel.UserData userData : parseSavedAccountList(savedJson)) {
            if (!isSameAccount(userData, current)) {
                arrayListArrayListOf.add(userData);
            }
        }
        return arrayListArrayListOf;
    }

    public static final boolean isAccountAlreadyKnown(UserModel.UserData candidate, UserModel.UserData current, String savedJson) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(current, "current");
        Intrinsics.checkNotNullParameter(savedJson, "savedJson");
        if (isSameAccount(current, candidate)) {
            return true;
        }
        List<UserModel.UserData> savedAccountList = parseSavedAccountList(savedJson);
        if (!(savedAccountList instanceof Collection) || !savedAccountList.isEmpty()) {
            Iterator<T> it = savedAccountList.iterator();
            while (it.hasNext()) {
                if (isSameAccount((UserModel.UserData) it.next(), candidate)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean shouldSkipScannedAccountLogin(UserModel.UserData candidate, boolean z, UserModel.UserData current, String savedJson, boolean z2) {
        Intrinsics.checkNotNullParameter(candidate, "candidate");
        Intrinsics.checkNotNullParameter(current, "current");
        Intrinsics.checkNotNullParameter(savedJson, "savedJson");
        if (z) {
            return isAccountAlreadyKnown(candidate, current, savedJson);
        }
        return z2 && isSameAccount(current, candidate);
    }

    public static final boolean hasStoredSessionCredentials(UserModel.UserData user) {
        Intrinsics.checkNotNullParameter(user, "user");
        String str = user.session_key;
        return !(str == null || StringsKt.isBlank(str));
    }
}
