package com.movieboxpro.android.http.v2.session;

import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.movieboxpro.android.http.v2.ApiV2Environment;
import com.movieboxpro.android.model.user.UserModel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: ApiSessionManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u00002\u00020\u0001B#\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\nJ\u000e\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0014J&\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0013\u001a\u00020\u0014J0\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u001e2\u0006\u0010!\u001a\u00020\fJ2\u0010\"\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010\u001e2\u0006\u0010!\u001a\u00020\fH\u0002J\u0016\u0010#\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u0014J\b\u0010%\u001a\u0004\u0018\u00010&J\u000e\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020\fJ\u0006\u0010,\u001a\u00020\u000eJ\b\u0010-\u001a\u00020\nH\u0002J\n\u0010.\u001a\u0004\u0018\u00010&H\u0002J\u0010\u0010/\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u00100\u001a\u00020\u000e2\u0006\u00101\u001a\u00020&H\u0002J\u001a\u00102\u001a\u00020\u000e2\b\u00103\u001a\u0004\u0018\u00010&2\u0006\u0010\u0019\u001a\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;", "", "store", "Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;", "identityStore", "Lcom/movieboxpro/android/http/v2/session/UserIdentityStore;", "monitor", "<init>", "(Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;Lcom/movieboxpro/android/http/v2/session/UserIdentityStore;Ljava/lang/Object;)V", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "", "sessionGeneration", "", "onLogin", "", "userData", "Lcom/movieboxpro/android/model/user/UserModel$UserData;", "hasPersistedSession", "restoreLogin", "publishIdentity", "Ljava/lang/Runnable;", "commitLogin", "Lcom/movieboxpro/android/http/v2/session/LoginCommitResult;", "persistNewIdentity", "Lcom/movieboxpro/android/http/v2/session/AccountIdentityWrite;", "restorePreviousIdentity", "replaceFromRefresh", "Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult;", "expectedGeneration", "uidV2", "", "sessionKey", "skVer", "uidV2Exp", "replaceFromRefreshLocked", "runIfGenerationCurrent", "action", "current", "Lcom/movieboxpro/android/http/v2/session/SessionCredentials;", "captureRequestAttempt", "Lcom/movieboxpro/android/http/v2/session/ApiV2RequestAttempt;", "environment", "Lcom/movieboxpro/android/http/v2/ApiV2Environment;", "generation", "clear", "deactivateAndClear", "currentLocked", "installLoginLocked", "restoreSessionLocked", "credentials", "rollbackIdentityLocked", "previous", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiSessionManager {
    private boolean active;
    private final UserIdentityStore identityStore;
    private final Object monitor;
    private long sessionGeneration;
    private final ApiSessionStore store;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiSessionManager(ApiSessionStore store, UserIdentityStore identityStore) {
        this(store, identityStore, null, 4, null);
        Intrinsics.checkNotNullParameter(store, "store");
        Intrinsics.checkNotNullParameter(identityStore, "identityStore");
    }

    public ApiSessionManager(ApiSessionStore store, UserIdentityStore identityStore, Object monitor) {
        Intrinsics.checkNotNullParameter(store, "store");
        Intrinsics.checkNotNullParameter(identityStore, "identityStore");
        Intrinsics.checkNotNullParameter(monitor, "monitor");
        this.store = store;
        this.identityStore = identityStore;
        this.monitor = monitor;
        boolean zHasSession = store.hasSession();
        this.active = zHasSession;
        this.sessionGeneration = zHasSession ? 1L : 0L;
    }

    public /* synthetic */ ApiSessionManager(ApiSessionStore apiSessionStore, UserIdentityStore userIdentityStore, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(apiSessionStore, userIdentityStore, (i & 4) != 0 ? AccountSessionLock.monitor : obj);
    }

    public final void onLogin(UserModel.UserData userData) {
        Intrinsics.checkNotNullParameter(userData, "userData");
        synchronized (this.monitor) {
            installLoginLocked(userData);
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean hasPersistedSession() {
        return this.store.hasSession();
    }

    public final void restoreLogin(Runnable publishIdentity) {
        Intrinsics.checkNotNullParameter(publishIdentity, "publishIdentity");
        synchronized (this.monitor) {
            publishIdentity.run();
            Unit unit = Unit.INSTANCE;
        }
    }

    public final LoginCommitResult commitLogin(UserModel.UserData userData, AccountIdentityWrite persistNewIdentity, AccountIdentityWrite restorePreviousIdentity, Runnable publishIdentity) {
        byte[] sessionKey;
        boolean zRun;
        LoginCommitResult.Failure failure;
        byte[] sessionKey2;
        byte[] sessionKey3;
        byte[] sessionKey4;
        byte[] sessionKey5;
        Intrinsics.checkNotNullParameter(userData, "userData");
        Intrinsics.checkNotNullParameter(persistNewIdentity, "persistNewIdentity");
        Intrinsics.checkNotNullParameter(restorePreviousIdentity, "restorePreviousIdentity");
        Intrinsics.checkNotNullParameter(publishIdentity, "publishIdentity");
        synchronized (this.monitor) {
            SessionCredentials sessionCredentialsCurrentLocked = currentLocked();
            try {
                if (deactivateAndClear()) {
                    try {
                        zRun = persistNewIdentity.run();
                    } catch (RuntimeException unused) {
                        zRun = false;
                    }
                    if (zRun) {
                        try {
                            installLoginLocked(userData);
                            if (this.active) {
                                publishIdentity.run();
                                failure = LoginCommitResult.Success.INSTANCE;
                                if (sessionCredentialsCurrentLocked != null && (sessionKey2 = sessionCredentialsCurrentLocked.getSessionKey()) != null) {
                                    ArraysKt.fill$default(sessionKey2, (byte) 0, 0, 0, 6, (Object) null);
                                }
                            } else {
                                rollbackIdentityLocked(sessionCredentialsCurrentLocked, restorePreviousIdentity);
                                failure = new LoginCommitResult.Failure(LoginCommitFailure.BLANK_SESSION_KEY);
                                if (sessionCredentialsCurrentLocked != null && (sessionKey3 = sessionCredentialsCurrentLocked.getSessionKey()) != null) {
                                    ArraysKt.fill$default(sessionKey3, (byte) 0, 0, 0, 6, (Object) null);
                                }
                            }
                        } catch (RuntimeException unused2) {
                            deactivateAndClear();
                            rollbackIdentityLocked(sessionCredentialsCurrentLocked, restorePreviousIdentity);
                            failure = new LoginCommitResult.Failure(LoginCommitFailure.SESSION_INSTALL_FAILED);
                        }
                    } else {
                        rollbackIdentityLocked(sessionCredentialsCurrentLocked, restorePreviousIdentity);
                        failure = new LoginCommitResult.Failure(LoginCommitFailure.IDENTITY_PERSIST_FAILED);
                        if (sessionCredentialsCurrentLocked != null && (sessionKey4 = sessionCredentialsCurrentLocked.getSessionKey()) != null) {
                            ArraysKt.fill$default(sessionKey4, (byte) 0, 0, 0, 6, (Object) null);
                        }
                    }
                } else {
                    failure = new LoginCommitResult.Failure(LoginCommitFailure.SESSION_CLEAR_FAILED);
                    if (sessionCredentialsCurrentLocked != null && (sessionKey5 = sessionCredentialsCurrentLocked.getSessionKey()) != null) {
                        ArraysKt.fill$default(sessionKey5, (byte) 0, 0, 0, 6, (Object) null);
                    }
                }
            } catch (Throwable th) {
                if (sessionCredentialsCurrentLocked != null && (sessionKey = sessionCredentialsCurrentLocked.getSessionKey()) != null) {
                    ArraysKt.fill$default(sessionKey, (byte) 0, 0, 0, 6, (Object) null);
                }
                throw th;
            }
        }
        return failure;
    }

    public final RefreshCommitResult replaceFromRefresh(long expectedGeneration, String uidV2, String sessionKey, String skVer, long uidV2Exp) {
        RefreshCommitResult refreshCommitResultReplaceFromRefreshLocked;
        Intrinsics.checkNotNullParameter(uidV2, "uidV2");
        Intrinsics.checkNotNullParameter(sessionKey, "sessionKey");
        synchronized (this.monitor) {
            refreshCommitResultReplaceFromRefreshLocked = replaceFromRefreshLocked(expectedGeneration, uidV2, sessionKey, skVer, uidV2Exp);
        }
        return refreshCommitResultReplaceFromRefreshLocked;
    }

    private final RefreshCommitResult replaceFromRefreshLocked(long expectedGeneration, String uidV2, String sessionKey, String skVer, long uidV2Exp) {
        boolean zUpdateUidV2;
        if (!this.active || expectedGeneration != this.sessionGeneration) {
            return RefreshCommitResult.StaleGeneration.INSTANCE;
        }
        if (!deactivateAndClear()) {
            return new RefreshCommitResult.LocalCommitFailure(this.sessionGeneration);
        }
        try {
            zUpdateUidV2 = this.identityStore.updateUidV2(uidV2);
        } catch (RuntimeException unused) {
            zUpdateUidV2 = false;
        }
        if (!zUpdateUidV2) {
            return new RefreshCommitResult.LocalCommitFailure(this.sessionGeneration);
        }
        byte[] bytes = sessionKey.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        try {
            try {
                this.store.replace(new SessionCredentials(bytes, skVer, uidV2Exp));
                ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                this.active = true;
                return RefreshCommitResult.Success.INSTANCE;
            } catch (RuntimeException unused2) {
                RefreshCommitResult.LocalCommitFailure localCommitFailure = new RefreshCommitResult.LocalCommitFailure(this.sessionGeneration);
                ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                return localCommitFailure;
            }
        } catch (Throwable th) {
            ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    public final boolean runIfGenerationCurrent(long expectedGeneration, Runnable action) {
        boolean z;
        Intrinsics.checkNotNullParameter(action, "action");
        synchronized (this.monitor) {
            if (this.sessionGeneration != expectedGeneration) {
                z = false;
            } else {
                action.run();
                z = true;
            }
        }
        return z;
    }

    public final SessionCredentials current() {
        SessionCredentials sessionCredentialsCurrentLocked;
        synchronized (this.monitor) {
            sessionCredentialsCurrentLocked = currentLocked();
        }
        return sessionCredentialsCurrentLocked;
    }

    public final ApiV2RequestAttempt captureRequestAttempt(ApiV2Environment environment) {
        byte[] sessionKey;
        ApiV2RequestAttempt apiV2RequestAttempt;
        Intrinsics.checkNotNullParameter(environment, "environment");
        synchronized (this.monitor) {
            SessionCredentials sessionCredentialsCurrentLocked = currentLocked();
            try {
                apiV2RequestAttempt = new ApiV2RequestAttempt(sessionCredentialsCurrentLocked, environment.snapshot(), this.sessionGeneration);
            } catch (RuntimeException e) {
                if (sessionCredentialsCurrentLocked != null && (sessionKey = sessionCredentialsCurrentLocked.getSessionKey()) != null) {
                    ArraysKt.fill$default(sessionKey, (byte) 0, 0, 0, 6, (Object) null);
                }
                throw e;
            }
        }
        return apiV2RequestAttempt;
    }

    public final long generation() {
        long j;
        synchronized (this.monitor) {
            j = this.sessionGeneration;
        }
        return j;
    }

    public final void clear() {
        synchronized (this.monitor) {
            deactivateAndClear();
        }
    }

    private final boolean deactivateAndClear() {
        this.active = false;
        this.sessionGeneration++;
        try {
            this.store.clear();
            return true;
        } catch (RuntimeException unused) {
            return false;
        }
    }

    private final SessionCredentials currentLocked() {
        if (this.active) {
            return this.store.read();
        }
        return null;
    }

    private final void installLoginLocked(UserModel.UserData userData) {
        String str = userData.session_key;
        userData.session_key = null;
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2)) {
            deactivateAndClear();
            return;
        }
        byte[] bytes = str.getBytes(Charsets.UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
        try {
            this.store.replace(new SessionCredentials(bytes, userData.sk_ver, userData.uid_v2_exp));
            this.active = true;
            this.sessionGeneration++;
        } finally {
            ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
        }
    }

    private final void restoreSessionLocked(SessionCredentials credentials) {
        this.store.replace(credentials);
        this.active = true;
        this.sessionGeneration++;
    }

    private final void rollbackIdentityLocked(SessionCredentials previous, AccountIdentityWrite restorePreviousIdentity) {
        boolean zRun;
        try {
            zRun = restorePreviousIdentity.run();
        } catch (RuntimeException unused) {
            zRun = false;
        }
        if (!zRun || previous == null) {
            return;
        }
        restoreSessionLocked(previous);
    }
}
