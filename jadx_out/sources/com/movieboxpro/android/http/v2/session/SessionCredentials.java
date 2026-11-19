package com.movieboxpro.android.http.v2.session;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SessionCredentials.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionCredentials;", "", "sessionKey", "", "skVer", "", "uidV2Exp", "", "<init>", "([BLjava/lang/String;J)V", "getSessionKey", "()[B", "getSkVer", "()Ljava/lang/String;", "getUidV2Exp", "()J", "component1", "component2", "component3", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SessionCredentials {
    private final byte[] sessionKey;
    private final String skVer;
    private final long uidV2Exp;

    public static /* synthetic */ SessionCredentials copy$default(SessionCredentials sessionCredentials, byte[] bArr, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            bArr = sessionCredentials.sessionKey;
        }
        if ((i & 2) != 0) {
            str = sessionCredentials.skVer;
        }
        if ((i & 4) != 0) {
            j = sessionCredentials.uidV2Exp;
        }
        return sessionCredentials.copy(bArr, str, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final byte[] getSessionKey() {
        return this.sessionKey;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final String getSkVer() {
        return this.skVer;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getUidV2Exp() {
        return this.uidV2Exp;
    }

    public final SessionCredentials copy(byte[] sessionKey, String skVer, long uidV2Exp) {
        Intrinsics.checkNotNullParameter(sessionKey, "sessionKey");
        return new SessionCredentials(sessionKey, skVer, uidV2Exp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionCredentials)) {
            return false;
        }
        SessionCredentials sessionCredentials = (SessionCredentials) other;
        return Intrinsics.areEqual(this.sessionKey, sessionCredentials.sessionKey) && Intrinsics.areEqual(this.skVer, sessionCredentials.skVer) && this.uidV2Exp == sessionCredentials.uidV2Exp;
    }

    public int hashCode() {
        int iHashCode = Arrays.hashCode(this.sessionKey) * 31;
        String str = this.skVer;
        return ((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.uidV2Exp);
    }

    public String toString() {
        return "SessionCredentials(sessionKey=" + Arrays.toString(this.sessionKey) + ", skVer=" + this.skVer + ", uidV2Exp=" + this.uidV2Exp + ")";
    }

    public SessionCredentials(byte[] sessionKey, String str, long j) {
        Intrinsics.checkNotNullParameter(sessionKey, "sessionKey");
        this.sessionKey = sessionKey;
        this.skVer = str;
        this.uidV2Exp = j;
    }

    public final byte[] getSessionKey() {
        return this.sessionKey;
    }

    public final String getSkVer() {
        return this.skVer;
    }

    public final long getUidV2Exp() {
        return this.uidV2Exp;
    }
}
