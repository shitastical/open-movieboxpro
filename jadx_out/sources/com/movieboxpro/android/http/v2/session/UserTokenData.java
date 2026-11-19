package com.movieboxpro.android.http.v2.session;

import kotlin.Metadata;

/* JADX INFO: compiled from: UserTokenResponse.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\t\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\n\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0001X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0018\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/UserTokenData;", "", "<init>", "()V", "uid", "", "getUid", "()Ljava/lang/String;", "setUid", "(Ljava/lang/String;)V", ApiSessionStore.KEY_SESSION_KEY, "getSession_key", "setSession_key", ApiSessionStore.KEY_SK_VER, "getSk_ver", "()Ljava/lang/Object;", "setSk_ver", "(Ljava/lang/Object;)V", ApiSessionStore.KEY_UID_V2_EXP, "", "getUid_v2_exp", "()Ljava/lang/Long;", "setUid_v2_exp", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UserTokenData {
    private String session_key;
    private Object sk_ver;
    private String uid;
    private Long uid_v2_exp;

    public final String getUid() {
        return this.uid;
    }

    public final void setUid(String str) {
        this.uid = str;
    }

    public final String getSession_key() {
        return this.session_key;
    }

    public final void setSession_key(String str) {
        this.session_key = str;
    }

    public final Object getSk_ver() {
        return this.sk_ver;
    }

    public final void setSk_ver(Object obj) {
        this.sk_ver = obj;
    }

    public final Long getUid_v2_exp() {
        return this.uid_v2_exp;
    }

    public final void setUid_v2_exp(Long l) {
        this.uid_v2_exp = l;
    }
}
