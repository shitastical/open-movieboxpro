package com.movieboxpro.android.http.v2.session;

import kotlin.Metadata;

/* JADX INFO: compiled from: UserTokenResponse.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001e\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\n\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/UserTokenResponse;", "", "<init>", "()V", "code", "", "getCode", "()Ljava/lang/Integer;", "setCode", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "data", "Lcom/movieboxpro/android/http/v2/session/UserTokenData;", "getData", "()Lcom/movieboxpro/android/http/v2/session/UserTokenData;", "setData", "(Lcom/movieboxpro/android/http/v2/session/UserTokenData;)V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UserTokenResponse {
    private Integer code;
    private UserTokenData data;

    public final Integer getCode() {
        return this.code;
    }

    public final void setCode(Integer num) {
        this.code = num;
    }

    public final UserTokenData getData() {
        return this.data;
    }

    public final void setData(UserTokenData userTokenData) {
        this.data = userTokenData;
    }
}
