package com.movieboxpro.android.http.v2.session;

import com.movieboxpro.android.timroes.base64.Base64;
import com.movieboxpro.android.utils.ShellUtil;
import java.util.Arrays;
import java.util.Set;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ApiSessionStore.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\n\u001a\u00020\u000bJ\b\u0010\f\u001a\u0004\u0018\u00010\tJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tJ\u0006\u0010\u0010\u001a\u00020\u000eJ\b\u0010\u0011\u001a\u00020\u000eH\u0002J\f\u0010\u0012\u001a\u00020\t*\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;", "", "preferences", "Lcom/movieboxpro/android/http/v2/session/SessionPreferences;", "cipher", "Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;", "<init>", "(Lcom/movieboxpro/android/http/v2/session/SessionPreferences;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;)V", "cachedCredentials", "Lcom/movieboxpro/android/http/v2/session/SessionCredentials;", "hasSession", "", "read", "replace", "", "credentials", "clear", "clearCachedCredentials", "copyWithClonedKey", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiSessionStore {
    private SessionCredentials cachedCredentials;
    private final SessionKeyCipher cipher;
    private final SessionPreferences preferences;
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String KEY_SESSION_KEY = "session_key";

    @Deprecated
    public static final String KEY_SK_VER = "sk_ver";

    @Deprecated
    public static final String KEY_UID_V2_EXP = "uid_v2_exp";
    private static final Set<String> ALL_KEYS = SetsKt.setOf((Object[]) new String[]{KEY_SESSION_KEY, KEY_SK_VER, KEY_UID_V2_EXP});

    public ApiSessionStore(SessionPreferences preferences, SessionKeyCipher cipher) {
        Intrinsics.checkNotNullParameter(preferences, "preferences");
        Intrinsics.checkNotNullParameter(cipher, "cipher");
        this.preferences = preferences;
        this.cipher = cipher;
    }

    public final synchronized boolean hasSession() {
        if (this.cachedCredentials != null) {
            return true;
        }
        SessionCredentials sessionCredentials = read();
        if (sessionCredentials == null) {
            return false;
        }
        ArraysKt.fill$default(sessionCredentials.getSessionKey(), (byte) 0, 0, 0, 6, (Object) null);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:47:0x00a1  */
    public final synchronized SessionCredentials read() {
        byte[] bArr;
        Long longOrNull;
        SessionCredentials sessionCredentials = this.cachedCredentials;
        if (sessionCredentials != null) {
            return copyWithClonedKey(sessionCredentials);
        }
        String str = this.preferences.get(KEY_SESSION_KEY);
        byte[] bArr2 = null;
        sessionCredentialsCopyWithClonedKey = null;
        SessionCredentials sessionCredentialsCopyWithClonedKey = null;
        if (str == null) {
            return null;
        }
        try {
            String str2 = this.preferences.get(KEY_UID_V2_EXP);
            if (str2 == null || (longOrNull = StringsKt.toLongOrNull(str2)) == null) {
                throw new IllegalArgumentException("Invalid session expiry");
            }
            long jLongValue = longOrNull.longValue();
            SessionKeyCipher sessionKeyCipher = this.cipher;
            byte[] bArrDecode = Base64.decode(str);
            Intrinsics.checkNotNullExpressionValue(bArrDecode, "decode(...)");
            byte[] bArrDecrypt = sessionKeyCipher.decrypt(bArrDecode);
            try {
                if (bArrDecrypt.length == 0) {
                    throw new IllegalArgumentException("Empty session key".toString());
                }
                byte[] bArrCopyOf = Arrays.copyOf(bArrDecrypt, bArrDecrypt.length);
                Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
                SessionCredentials sessionCredentials2 = new SessionCredentials(bArrCopyOf, this.preferences.get(KEY_SK_VER), jLongValue);
                this.cachedCredentials = sessionCredentials2;
                sessionCredentialsCopyWithClonedKey = copyWithClonedKey(sessionCredentials2);
                if (bArrDecrypt != null) {
                    ArraysKt.fill$default(bArrDecrypt, (byte) 0, 0, 0, 6, (Object) null);
                }
                return sessionCredentialsCopyWithClonedKey;
            } catch (Exception unused) {
                bArr = bArrDecrypt;
                try {
                    this.preferences.clear(ALL_KEYS);
                    clearCachedCredentials();
                    if (bArr != null) {
                        ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                    }
                } catch (Throwable th) {
                    th = th;
                    bArr2 = bArr;
                    if (bArr2 != null) {
                        ArraysKt.fill$default(bArr2, (byte) 0, 0, 0, 6, (Object) null);
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                bArr2 = bArrDecrypt;
                if (bArr2 != null) {
                    ArraysKt.fill$default(bArr2, (byte) 0, 0, 0, 6, (Object) null);
                }
                throw th;
            }
        } catch (Exception unused2) {
            bArr = null;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    public final synchronized void replace(SessionCredentials credentials) {
        Intrinsics.checkNotNullParameter(credentials, "credentials");
        byte[] sessionKey = credentials.getSessionKey();
        byte[] bArrCopyOf = Arrays.copyOf(sessionKey, sessionKey.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        try {
            byte[] bArrEncrypt = this.cipher.encrypt(bArrCopyOf);
            ArraysKt.fill$default(bArrCopyOf, (byte) 0, 0, 0, 6, (Object) null);
            String strEncode = Base64.encode(bArrEncrypt);
            Intrinsics.checkNotNullExpressionValue(strEncode, "encode(...)");
            this.preferences.replace(MapsKt.mapOf(TuplesKt.to(KEY_SESSION_KEY, StringsKt.replace$default(strEncode, ShellUtil.COMMAND_LINE_END, "", false, 4, (Object) null)), TuplesKt.to(KEY_UID_V2_EXP, String.valueOf(credentials.getUidV2Exp())), TuplesKt.to(KEY_SK_VER, credentials.getSkVer())));
            clearCachedCredentials();
            this.cachedCredentials = copyWithClonedKey(credentials);
        } catch (Throwable th) {
            ArraysKt.fill$default(bArrCopyOf, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    public final synchronized void clear() {
        try {
            this.preferences.clear(ALL_KEYS);
            clearCachedCredentials();
        } catch (Throwable th) {
            clearCachedCredentials();
            throw th;
        }
    }

    private final void clearCachedCredentials() {
        byte[] sessionKey;
        SessionCredentials sessionCredentials = this.cachedCredentials;
        if (sessionCredentials != null && (sessionKey = sessionCredentials.getSessionKey()) != null) {
            ArraysKt.fill$default(sessionKey, (byte) 0, 0, 0, 6, (Object) null);
        }
        this.cachedCredentials = null;
    }

    private final SessionCredentials copyWithClonedKey(SessionCredentials sessionCredentials) {
        byte[] sessionKey = sessionCredentials.getSessionKey();
        byte[] bArrCopyOf = Arrays.copyOf(sessionKey, sessionKey.length);
        Intrinsics.checkNotNullExpressionValue(bArrCopyOf, "copyOf(...)");
        return SessionCredentials.copy$default(sessionCredentials, bArrCopyOf, null, 0L, 6, null);
    }

    /* JADX INFO: compiled from: ApiSessionStore.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/ApiSessionStore$Companion;", "", "<init>", "()V", "KEY_SESSION_KEY", "", "KEY_SK_VER", "KEY_UID_V2_EXP", "ALL_KEYS", "", "getALL_KEYS", "()Ljava/util/Set;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<String> getALL_KEYS() {
            return ApiSessionStore.ALL_KEYS;
        }
    }
}
