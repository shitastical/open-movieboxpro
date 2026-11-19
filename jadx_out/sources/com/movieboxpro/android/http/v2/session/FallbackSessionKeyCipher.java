package com.movieboxpro.android.http.v2.session;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FallbackSessionKeyCipher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\u0018\u00002\u00020\u0001B-\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;", "Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;", "keystore", "software", "evictKeystore", "Ljava/lang/Runnable;", "onKeystoreFailure", "Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;", "<init>", "(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;)V", "keystoreUnavailable", "", "encrypt", "", "value", "decrypt", "encryptWithSoftware", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FallbackSessionKeyCipher implements SessionKeyCipher {
    private final Runnable evictKeystore;
    private final SessionKeyCipher keystore;
    private volatile boolean keystoreUnavailable;
    private final SessionCipherFailureHandler onKeystoreFailure;
    private final SessionKeyCipher software;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FallbackSessionKeyCipher(SessionKeyCipher keystore, SessionKeyCipher software) {
        this(keystore, software, null, null, 12, null);
        Intrinsics.checkNotNullParameter(keystore, "keystore");
        Intrinsics.checkNotNullParameter(software, "software");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FallbackSessionKeyCipher(SessionKeyCipher keystore, SessionKeyCipher software, Runnable evictKeystore) {
        this(keystore, software, evictKeystore, null, 8, null);
        Intrinsics.checkNotNullParameter(keystore, "keystore");
        Intrinsics.checkNotNullParameter(software, "software");
        Intrinsics.checkNotNullParameter(evictKeystore, "evictKeystore");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void _init_$lambda$0() {
    }

    public FallbackSessionKeyCipher(SessionKeyCipher keystore, SessionKeyCipher software, Runnable evictKeystore, SessionCipherFailureHandler onKeystoreFailure) {
        Intrinsics.checkNotNullParameter(keystore, "keystore");
        Intrinsics.checkNotNullParameter(software, "software");
        Intrinsics.checkNotNullParameter(evictKeystore, "evictKeystore");
        Intrinsics.checkNotNullParameter(onKeystoreFailure, "onKeystoreFailure");
        this.keystore = keystore;
        this.software = software;
        this.evictKeystore = evictKeystore;
        this.onKeystoreFailure = onKeystoreFailure;
    }

    public /* synthetic */ FallbackSessionKeyCipher(SessionKeyCipher sessionKeyCipher, SessionKeyCipher sessionKeyCipher2, Runnable runnable, SessionCipherFailureHandler sessionCipherFailureHandler, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sessionKeyCipher, sessionKeyCipher2, (i & 4) != 0 ? new Runnable() { // from class: com.movieboxpro.android.http.v2.session.FallbackSessionKeyCipher$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                FallbackSessionKeyCipher._init_$lambda$0();
            }
        } : runnable, (i & 8) != 0 ? new SessionCipherFailureHandler() { // from class: com.movieboxpro.android.http.v2.session.FallbackSessionKeyCipher$$ExternalSyntheticLambda1
            @Override // com.movieboxpro.android.http.v2.session.SessionCipherFailureHandler
            public final void onFailure(Throwable th) {
                Intrinsics.checkNotNullParameter(th, "it");
            }
        } : sessionCipherFailureHandler);
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionKeyCipher
    public byte[] encrypt(byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!this.keystoreUnavailable) {
            try {
                return this.keystore.encrypt(value);
            } catch (Exception e) {
                this.onKeystoreFailure.onFailure(e);
                try {
                    this.evictKeystore.run();
                } catch (Exception e2) {
                    this.onKeystoreFailure.onFailure(e2);
                }
                try {
                    return this.keystore.encrypt(value);
                } catch (Exception e3) {
                    this.onKeystoreFailure.onFailure(e3);
                    this.keystoreUnavailable = true;
                }
            }
        }
        return encryptWithSoftware(value);
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionKeyCipher
    public byte[] decrypt(byte[] value) {
        Intrinsics.checkNotNullParameter(value, "value");
        if (!(value.length == 0) && value[0] == 2) {
            try {
                return this.software.decrypt(value);
            } catch (Exception unused) {
                return this.keystore.decrypt(value);
            }
        }
        return this.keystore.decrypt(value);
    }

    private final byte[] encryptWithSoftware(byte[] value) {
        try {
            return this.software.encrypt(value);
        } catch (RuntimeException e) {
            throw e;
        } catch (Exception e2) {
            throw new IllegalStateException("Software session wrap failed", e2);
        }
    }
}
