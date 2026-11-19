package com.movieboxpro.android.http.v2.session;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.media3.exoplayer.RendererCapabilities;
import com.alibaba.fastjson.JSON;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import com.movieboxpro.android.http.v2.ApiV2Environment;
import com.movieboxpro.android.http.v2.ApiV2EnvironmentSnapshot;
import com.movieboxpro.android.http.v2.TvApiV2Environment;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* JADX INFO: compiled from: SessionRefreshCoordinator.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 -2\u00020\u0001:\u0003+,-B}\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\u000e\b\u0002\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007\u0012\u0010\b\u0002\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0007\u0012\u000e\b\u0002\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007¢\u0006\u0004\b\u0014\u0010\u0015J\u0006\u0010\u001c\u001a\u00020\fJ\u0006\u0010\u001d\u001a\u00020\u001eJ\u001c\u0010\u001f\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007J\u0016\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\fJ\u0006\u0010%\u001a\u00020\u0010J\u0006\u0010&\u001a\u00020'J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\fJ\u0010\u0010)\u001a\u00020'2\u0006\u0010*\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00190\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;", "", "sessionManager", "Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;", "refreshClient", "Lokhttp3/Call$Factory;", "endpointUrl", "Lkotlin/Function0;", "", "environment", "Lcom/movieboxpro/android/http/v2/ApiV2Environment;", "clockSeconds", "", "proactiveExecutor", "Ljava/util/concurrent/Executor;", "invalidSessionHandler", "", "credentialsReader", "Lcom/movieboxpro/android/http/v2/session/SessionCredentials;", "onFollowerJoined", "<init>", "(Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;Lokhttp3/Call$Factory;Lkotlin/jvm/functions/Function0;Lcom/movieboxpro/android/http/v2/ApiV2Environment;Lkotlin/jvm/functions/Function0;Ljava/util/concurrent/Executor;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V", "monitor", "activeFlights", "", "Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator$Flight;", "lastSuccessfulTransition", "Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator$SuccessfulTransition;", "generation", "captureRequestAttempt", "Lcom/movieboxpro/android/http/v2/session/ApiV2RequestAttempt;", "runIfGenerationCurrent", "", "action", "shouldPreRefresh", "now", "expires", "refreshIfExpiringAsync", "refreshBlocking", "Lcom/movieboxpro/android/http/v2/session/RefreshResult;", "observedGeneration", "performRefresh", "expectedGeneration", "Flight", "SuccessfulTransition", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionRefreshCoordinator {

    @Deprecated
    public static final long DEFAULT_EXPIRY_SECONDS = 2592000;

    @Deprecated
    public static final long PRE_REFRESH_WINDOW_SECONDS = 604800;

    @Deprecated
    public static final int SUCCESS_CODE = 1;
    private final Map<Long, Flight> activeFlights;
    private final Function0<Long> clockSeconds;
    private final Function0<SessionCredentials> credentialsReader;
    private final Function0<String> endpointUrl;
    private final ApiV2Environment environment;
    private final Function0<Unit> invalidSessionHandler;
    private SuccessfulTransition lastSuccessfulTransition;
    private final Object monitor;
    private final Function0<Unit> onFollowerJoined;
    private final Executor proactiveExecutor;
    private final Call.Factory refreshClient;
    private final ApiSessionManager sessionManager;
    private static final Companion Companion = new Companion(null);
    private static final Set<Integer> FORCED_LOGIN_INVALID_CODES = SetsKt.setOf((Object[]) new Integer[]{-10001, -10022});
    private static final Executor DEFAULT_PROACTIVE_EXECUTOR = new Executor() { // from class: com.movieboxpro.android.http.v2.session.SessionRefreshCoordinator$$ExternalSyntheticLambda5
        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            SessionRefreshCoordinator.DEFAULT_PROACTIVE_EXECUTOR$lambda$13(runnable);
        }
    };
    private static final MediaType JSON_MEDIA_TYPE = MediaType.INSTANCE.get("application/json; charset=utf-8");

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRefreshCoordinator(ApiSessionManager sessionManager, Call.Factory refreshClient, Function0<String> endpointUrl) {
        this(sessionManager, refreshClient, endpointUrl, null, null, null, null, null, null, TypedValues.PositionType.TYPE_PERCENT_HEIGHT, null);
        Intrinsics.checkNotNullParameter(sessionManager, "sessionManager");
        Intrinsics.checkNotNullParameter(refreshClient, "refreshClient");
        Intrinsics.checkNotNullParameter(endpointUrl, "endpointUrl");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRefreshCoordinator(ApiSessionManager sessionManager, Call.Factory refreshClient, Function0<String> endpointUrl, ApiV2Environment environment) {
        this(sessionManager, refreshClient, endpointUrl, environment, null, null, null, null, null, 496, null);
        Intrinsics.checkNotNullParameter(sessionManager, "sessionManager");
        Intrinsics.checkNotNullParameter(refreshClient, "refreshClient");
        Intrinsics.checkNotNullParameter(endpointUrl, "endpointUrl");
        Intrinsics.checkNotNullParameter(environment, "environment");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRefreshCoordinator(ApiSessionManager sessionManager, Call.Factory refreshClient, Function0<String> endpointUrl, ApiV2Environment environment, Function0<Long> clockSeconds) {
        this(sessionManager, refreshClient, endpointUrl, environment, clockSeconds, null, null, null, null, 480, null);
        Intrinsics.checkNotNullParameter(sessionManager, "sessionManager");
        Intrinsics.checkNotNullParameter(refreshClient, "refreshClient");
        Intrinsics.checkNotNullParameter(endpointUrl, "endpointUrl");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(clockSeconds, "clockSeconds");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRefreshCoordinator(ApiSessionManager sessionManager, Call.Factory refreshClient, Function0<String> endpointUrl, ApiV2Environment environment, Function0<Long> clockSeconds, Executor proactiveExecutor) {
        this(sessionManager, refreshClient, endpointUrl, environment, clockSeconds, proactiveExecutor, null, null, null, 448, null);
        Intrinsics.checkNotNullParameter(sessionManager, "sessionManager");
        Intrinsics.checkNotNullParameter(refreshClient, "refreshClient");
        Intrinsics.checkNotNullParameter(endpointUrl, "endpointUrl");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(clockSeconds, "clockSeconds");
        Intrinsics.checkNotNullParameter(proactiveExecutor, "proactiveExecutor");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRefreshCoordinator(ApiSessionManager sessionManager, Call.Factory refreshClient, Function0<String> endpointUrl, ApiV2Environment environment, Function0<Long> clockSeconds, Executor proactiveExecutor, Function0<Unit> invalidSessionHandler) {
        this(sessionManager, refreshClient, endpointUrl, environment, clockSeconds, proactiveExecutor, invalidSessionHandler, null, null, RendererCapabilities.DECODER_SUPPORT_MASK, null);
        Intrinsics.checkNotNullParameter(sessionManager, "sessionManager");
        Intrinsics.checkNotNullParameter(refreshClient, "refreshClient");
        Intrinsics.checkNotNullParameter(endpointUrl, "endpointUrl");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(clockSeconds, "clockSeconds");
        Intrinsics.checkNotNullParameter(proactiveExecutor, "proactiveExecutor");
        Intrinsics.checkNotNullParameter(invalidSessionHandler, "invalidSessionHandler");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRefreshCoordinator(ApiSessionManager sessionManager, Call.Factory refreshClient, Function0<String> endpointUrl, ApiV2Environment environment, Function0<Long> clockSeconds, Executor proactiveExecutor, Function0<Unit> invalidSessionHandler, Function0<SessionCredentials> credentialsReader) {
        this(sessionManager, refreshClient, endpointUrl, environment, clockSeconds, proactiveExecutor, invalidSessionHandler, credentialsReader, null, 256, null);
        Intrinsics.checkNotNullParameter(sessionManager, "sessionManager");
        Intrinsics.checkNotNullParameter(refreshClient, "refreshClient");
        Intrinsics.checkNotNullParameter(endpointUrl, "endpointUrl");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(clockSeconds, "clockSeconds");
        Intrinsics.checkNotNullParameter(proactiveExecutor, "proactiveExecutor");
        Intrinsics.checkNotNullParameter(invalidSessionHandler, "invalidSessionHandler");
        Intrinsics.checkNotNullParameter(credentialsReader, "credentialsReader");
    }

    public final boolean shouldPreRefresh(long now, long expires) {
        return expires > 0 && expires <= now + PRE_REFRESH_WINDOW_SECONDS;
    }

    public SessionRefreshCoordinator(ApiSessionManager sessionManager, Call.Factory refreshClient, Function0<String> endpointUrl, ApiV2Environment environment, Function0<Long> clockSeconds, Executor proactiveExecutor, Function0<Unit> invalidSessionHandler, Function0<SessionCredentials> credentialsReader, Function0<Unit> onFollowerJoined) {
        Intrinsics.checkNotNullParameter(sessionManager, "sessionManager");
        Intrinsics.checkNotNullParameter(refreshClient, "refreshClient");
        Intrinsics.checkNotNullParameter(endpointUrl, "endpointUrl");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(clockSeconds, "clockSeconds");
        Intrinsics.checkNotNullParameter(proactiveExecutor, "proactiveExecutor");
        Intrinsics.checkNotNullParameter(invalidSessionHandler, "invalidSessionHandler");
        Intrinsics.checkNotNullParameter(credentialsReader, "credentialsReader");
        Intrinsics.checkNotNullParameter(onFollowerJoined, "onFollowerJoined");
        this.sessionManager = sessionManager;
        this.refreshClient = refreshClient;
        this.endpointUrl = endpointUrl;
        this.environment = environment;
        this.clockSeconds = clockSeconds;
        this.proactiveExecutor = proactiveExecutor;
        this.invalidSessionHandler = invalidSessionHandler;
        this.credentialsReader = credentialsReader;
        this.onFollowerJoined = onFollowerJoined;
        this.monitor = new Object();
        this.activeFlights = new LinkedHashMap();
    }

    public /* synthetic */ SessionRefreshCoordinator(ApiSessionManager apiSessionManager, Call.Factory factory, Function0 function0, ApiV2Environment apiV2Environment, Function0 function1, Executor executor, Function0 function2, Function0 function3, Function0 function4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(apiSessionManager, factory, function0, (i & 8) != 0 ? TvApiV2Environment.INSTANCE : apiV2Environment, (i & 16) != 0 ? new Function0() { // from class: com.movieboxpro.android.http.v2.session.SessionRefreshCoordinator$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(SessionRefreshCoordinator._init_$lambda$0());
            }
        } : function1, (i & 32) != 0 ? DEFAULT_PROACTIVE_EXECUTOR : executor, (i & 64) != 0 ? new Function0() { // from class: com.movieboxpro.android.http.v2.session.SessionRefreshCoordinator$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        } : function2, (i & 128) != 0 ? new AnonymousClass3(apiSessionManager) : function3, (i & 256) != 0 ? new Function0() { // from class: com.movieboxpro.android.http.v2.session.SessionRefreshCoordinator$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        } : function4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long _init_$lambda$0() {
        return System.currentTimeMillis() / 1000;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.http.v2.session.SessionRefreshCoordinator$3, reason: invalid class name */
    /* JADX INFO: compiled from: SessionRefreshCoordinator.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass3 extends FunctionReferenceImpl implements Function0<SessionCredentials> {
        AnonymousClass3(Object obj) {
            super(0, obj, ApiSessionManager.class, "current", "current()Lcom/movieboxpro/android/http/v2/session/SessionCredentials;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final SessionCredentials invoke() {
            return ((ApiSessionManager) this.receiver).current();
        }
    }

    public final long generation() {
        return this.sessionManager.generation();
    }

    public final ApiV2RequestAttempt captureRequestAttempt() {
        return this.sessionManager.captureRequestAttempt(this.environment);
    }

    public final boolean runIfGenerationCurrent(long generation, final Function0<Unit> action) {
        Intrinsics.checkNotNullParameter(action, "action");
        return this.sessionManager.runIfGenerationCurrent(generation, new Runnable() { // from class: com.movieboxpro.android.http.v2.session.SessionRefreshCoordinator$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                action.invoke();
            }
        });
    }

    public final void refreshIfExpiringAsync() {
        final long jGeneration = this.sessionManager.generation();
        SessionCredentials sessionCredentialsInvoke = this.credentialsReader.invoke();
        if (sessionCredentialsInvoke == null) {
            return;
        }
        try {
            long uidV2Exp = sessionCredentialsInvoke.getUidV2Exp();
            ArraysKt.fill$default(sessionCredentialsInvoke.getSessionKey(), (byte) 0, 0, 0, 6, (Object) null);
            if (shouldPreRefresh(this.clockSeconds.invoke().longValue(), uidV2Exp) && this.sessionManager.generation() == jGeneration) {
                try {
                    this.proactiveExecutor.execute(new Runnable() { // from class: com.movieboxpro.android.http.v2.session.SessionRefreshCoordinator$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.refreshBlocking(jGeneration);
                        }
                    });
                } catch (RejectedExecutionException unused) {
                }
            }
        } catch (Throwable th) {
            ArraysKt.fill$default(sessionCredentialsInvoke.getSessionKey(), (byte) 0, 0, 0, 6, (Object) null);
            throw th;
        }
    }

    public final RefreshResult refreshBlocking() {
        return refreshBlocking(this.sessionManager.generation());
    }

    public final RefreshResult refreshBlocking(long observedGeneration) {
        boolean z;
        RefreshResult refreshResultPerformRefresh;
        Object obj;
        synchronized (this.monitor) {
            Flight flight = this.activeFlights.get(Long.valueOf(observedGeneration));
            if (flight != null) {
                z = false;
            } else {
                long jGeneration = this.sessionManager.generation();
                if (jGeneration != observedGeneration) {
                    SuccessfulTransition successfulTransition = this.lastSuccessfulTransition;
                    return (successfulTransition != null && successfulTransition.getFrom() == observedGeneration && successfulTransition.getTo() == jGeneration) ? RefreshResult.SUCCESS : RefreshResult.STALE_SESSION;
                }
                flight = new Flight(observedGeneration);
                this.activeFlights.put(Long.valueOf(observedGeneration), flight);
                z = true;
            }
            Unit unit = Unit.INSTANCE;
            if (!z) {
                this.onFollowerJoined.invoke();
                try {
                    flight.getDone().await();
                    return flight.getResult();
                } catch (InterruptedException unused) {
                    Thread.currentThread().interrupt();
                    return RefreshResult.FAILURE;
                }
            }
            RefreshResult refreshResult = RefreshResult.FAILURE;
            try {
                try {
                    refreshResultPerformRefresh = performRefresh(flight.getExpectedGeneration());
                    if (refreshResultPerformRefresh == RefreshResult.SUCCESS && this.sessionManager.generation() != flight.getExpectedGeneration() + 1) {
                        refreshResultPerformRefresh = RefreshResult.STALE_SESSION;
                    }
                    obj = this.monitor;
                    synchronized (obj) {
                        if (refreshResultPerformRefresh == RefreshResult.SUCCESS) {
                            this.lastSuccessfulTransition = new SuccessfulTransition(flight.getExpectedGeneration(), flight.getExpectedGeneration() + 1);
                        }
                        flight.setResult(refreshResultPerformRefresh);
                        if (this.activeFlights.get(Long.valueOf(flight.getExpectedGeneration())) == flight) {
                            this.activeFlights.remove(Long.valueOf(flight.getExpectedGeneration()));
                        }
                        flight.getDone().countDown();
                        Unit unit2 = Unit.INSTANCE;
                        return refreshResultPerformRefresh;
                    }
                } catch (RuntimeException unused2) {
                    refreshResultPerformRefresh = RefreshResult.FAILURE;
                    obj = this.monitor;
                    synchronized (obj) {
                        if (refreshResultPerformRefresh == RefreshResult.SUCCESS) {
                            this.lastSuccessfulTransition = new SuccessfulTransition(flight.getExpectedGeneration(), flight.getExpectedGeneration() + 1);
                        }
                        RefreshResult refreshResult2 = refreshResultPerformRefresh;
                        flight.setResult(refreshResultPerformRefresh);
                        if (this.activeFlights.get(Long.valueOf(flight.getExpectedGeneration())) == flight) {
                            this.activeFlights.remove(Long.valueOf(flight.getExpectedGeneration()));
                        }
                        flight.getDone().countDown();
                        Unit unit3 = Unit.INSTANCE;
                    }
                }
            } catch (Throwable th) {
                synchronized (this.monitor) {
                    if (refreshResult == RefreshResult.SUCCESS) {
                        this.lastSuccessfulTransition = new SuccessfulTransition(flight.getExpectedGeneration(), flight.getExpectedGeneration() + 1);
                    }
                    flight.setResult(refreshResult);
                    if (this.activeFlights.get(Long.valueOf(flight.getExpectedGeneration())) == flight) {
                        this.activeFlights.remove(Long.valueOf(flight.getExpectedGeneration()));
                    }
                    flight.getDone().countDown();
                    Unit unit4 = Unit.INSTANCE;
                    throw th;
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:94:0x018c A[Catch: all -> 0x0294, TryCatch #1 {all -> 0x0294, blocks: (B:21:0x00a0, B:23:0x00a9, B:31:0x00c5, B:33:0x00cb, B:36:0x00d3, B:38:0x00e1, B:40:0x00e7, B:42:0x00ed, B:51:0x0110, B:54:0x0118, B:57:0x0120, B:60:0x0128, B:62:0x012e, B:68:0x013e, B:70:0x0144, B:76:0x0154, B:78:0x015a, B:80:0x0160, B:86:0x0170, B:88:0x0176, B:93:0x0187, B:95:0x019c, B:97:0x01ac, B:98:0x01af, B:100:0x01b7, B:101:0x01ba, B:103:0x01be, B:111:0x01e6, B:112:0x01eb, B:94:0x018c, B:113:0x01ec, B:121:0x0208, B:129:0x0224, B:137:0x0240, B:145:0x025c, B:153:0x0278), top: B:196:0x00a0 }] */
    private final RefreshResult performRefresh(long expectedGeneration) {
        RefreshResult refreshResult;
        byte[] sessionKey;
        byte[] sessionKey2;
        byte[] sessionKey3;
        byte[] sessionKey4;
        byte[] sessionKey5;
        String strString;
        byte[] sessionKey6;
        Integer code;
        byte[] sessionKey7;
        UserTokenData data;
        byte[] sessionKey8;
        byte[] sessionKey9;
        byte[] sessionKey10;
        String string;
        long jLongValue;
        byte[] sessionKey11;
        byte[] sessionKey12;
        byte[] sessionKey13;
        byte[] sessionKey14;
        ApiV2RequestAttempt apiV2RequestAttemptCaptureRequestAttempt = this.sessionManager.captureRequestAttempt(this.environment);
        try {
            try {
                if (apiV2RequestAttemptCaptureRequestAttempt.getGeneration() != expectedGeneration) {
                    RefreshResult refreshResult2 = RefreshResult.STALE_SESSION;
                    SessionCredentials session = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                    if (session != null && (sessionKey14 = session.getSessionKey()) != null) {
                        ArraysKt.fill$default(sessionKey14, (byte) 0, 0, 0, 6, (Object) null);
                        Unit unit = Unit.INSTANCE;
                    }
                    return refreshResult2;
                }
                ApiV2EnvironmentSnapshot environment = apiV2RequestAttemptCaptureRequestAttempt.getEnvironment();
                String uidV2 = environment.getUidV2();
                if (uidV2 != null) {
                    if (StringsKt.isBlank(uidV2)) {
                        uidV2 = null;
                    }
                    if (uidV2 != null) {
                        LinkedHashMap linkedHashMapLinkedMapOf = MapsKt.linkedMapOf(TuplesKt.to("uid", uidV2), TuplesKt.to("open_udid", environment.getOpenUdid()));
                        Request.Builder builderTag = new Request.Builder().url(this.endpointUrl.invoke()).tag((Class<? super ApiV2RequestAttempt>) ApiV2RequestAttempt.class, apiV2RequestAttemptCaptureRequestAttempt);
                        RequestBody.Companion companion = RequestBody.INSTANCE;
                        MediaType mediaType = JSON_MEDIA_TYPE;
                        String jSONString = JSON.toJSONString(linkedHashMapLinkedMapOf);
                        Intrinsics.checkNotNullExpressionValue(jSONString, "toJSONString(...)");
                        Response responseExecute = this.refreshClient.newCall(builderTag.post(companion.create(mediaType, jSONString)).build()).execute();
                        try {
                            Response response = responseExecute;
                            if (!response.getIsSuccessful()) {
                                RefreshResult refreshResult3 = RefreshResult.FAILURE;
                                CloseableKt.closeFinally(responseExecute, null);
                                SessionCredentials session2 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                                if (session2 != null && (sessionKey13 = session2.getSessionKey()) != null) {
                                    ArraysKt.fill$default(sessionKey13, (byte) 0, 0, 0, 6, (Object) null);
                                    Unit unit2 = Unit.INSTANCE;
                                }
                                return refreshResult3;
                            }
                            ResponseBody responseBodyBody = response.body();
                            if (responseBodyBody != null && (strString = responseBodyBody.string()) != null) {
                                UserTokenResponse userTokenResponse = (UserTokenResponse) JSON.parseObject(strString, UserTokenResponse.class);
                                if (CollectionsKt.contains(FORCED_LOGIN_INVALID_CODES, userTokenResponse != null ? userTokenResponse.getCode() : null)) {
                                    runIfGenerationCurrent(expectedGeneration, this.invalidSessionHandler);
                                    RefreshResult refreshResult4 = RefreshResult.FAILURE;
                                    CloseableKt.closeFinally(responseExecute, null);
                                    SessionCredentials session3 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                                    if (session3 != null && (sessionKey12 = session3.getSessionKey()) != null) {
                                        ArraysKt.fill$default(sessionKey12, (byte) 0, 0, 0, 6, (Object) null);
                                        Unit unit3 = Unit.INSTANCE;
                                    }
                                    return refreshResult4;
                                }
                                if (userTokenResponse != null && (code = userTokenResponse.getCode()) != null && code.intValue() == 1) {
                                    if (userTokenResponse != null && (data = userTokenResponse.getData()) != null) {
                                        String uid = data.getUid();
                                        if (uid != null) {
                                            String str = !StringsKt.isBlank(uid) ? uid : null;
                                            if (str != null) {
                                                String session_key = data.getSession_key();
                                                if (session_key != null) {
                                                    String str2 = !StringsKt.isBlank(session_key) ? session_key : null;
                                                    if (str2 != null) {
                                                        Object sk_ver = data.getSk_ver();
                                                        if (sk_ver != null && (string = sk_ver.toString()) != null) {
                                                            String str3 = !StringsKt.isBlank(string) ? string : null;
                                                            if (str3 != null) {
                                                                Long uid_v2_exp = data.getUid_v2_exp();
                                                                if (uid_v2_exp == null) {
                                                                    jLongValue = this.clockSeconds.invoke().longValue() + DEFAULT_EXPIRY_SECONDS;
                                                                } else {
                                                                    if (uid_v2_exp.longValue() <= 0) {
                                                                        uid_v2_exp = null;
                                                                    }
                                                                    if (uid_v2_exp != null) {
                                                                        jLongValue = uid_v2_exp.longValue();
                                                                    } else {
                                                                        jLongValue = this.clockSeconds.invoke().longValue() + DEFAULT_EXPIRY_SECONDS;
                                                                    }
                                                                }
                                                                RefreshCommitResult refreshCommitResultReplaceFromRefresh = this.sessionManager.replaceFromRefresh(expectedGeneration, str, str2, str3, jLongValue);
                                                                if (Intrinsics.areEqual(refreshCommitResultReplaceFromRefresh, RefreshCommitResult.Success.INSTANCE)) {
                                                                    refreshResult = RefreshResult.SUCCESS;
                                                                } else if (Intrinsics.areEqual(refreshCommitResultReplaceFromRefresh, RefreshCommitResult.StaleGeneration.INSTANCE)) {
                                                                    refreshResult = RefreshResult.STALE_SESSION;
                                                                } else {
                                                                    if (!(refreshCommitResultReplaceFromRefresh instanceof RefreshCommitResult.LocalCommitFailure)) {
                                                                        throw new NoWhenBranchMatchedException();
                                                                    }
                                                                    runIfGenerationCurrent(((RefreshCommitResult.LocalCommitFailure) refreshCommitResultReplaceFromRefresh).getGeneration(), this.invalidSessionHandler);
                                                                    refreshResult = RefreshResult.FAILURE;
                                                                }
                                                                CloseableKt.closeFinally(responseExecute, null);
                                                                SessionCredentials session4 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                                                                if (session4 != null && (sessionKey11 = session4.getSessionKey()) != null) {
                                                                    ArraysKt.fill$default(sessionKey11, (byte) 0, 0, 0, 6, (Object) null);
                                                                    Unit unit4 = Unit.INSTANCE;
                                                                }
                                                                return refreshResult;
                                                            }
                                                        }
                                                        RefreshResult refreshResult5 = RefreshResult.FAILURE;
                                                        CloseableKt.closeFinally(responseExecute, null);
                                                        SessionCredentials session5 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                                                        if (session5 != null && (sessionKey10 = session5.getSessionKey()) != null) {
                                                            ArraysKt.fill$default(sessionKey10, (byte) 0, 0, 0, 6, (Object) null);
                                                            Unit unit5 = Unit.INSTANCE;
                                                        }
                                                        return refreshResult5;
                                                    }
                                                }
                                                RefreshResult refreshResult6 = RefreshResult.FAILURE;
                                                CloseableKt.closeFinally(responseExecute, null);
                                                SessionCredentials session6 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                                                if (session6 != null && (sessionKey9 = session6.getSessionKey()) != null) {
                                                    ArraysKt.fill$default(sessionKey9, (byte) 0, 0, 0, 6, (Object) null);
                                                    Unit unit6 = Unit.INSTANCE;
                                                }
                                                return refreshResult6;
                                            }
                                        }
                                        RefreshResult refreshResult7 = RefreshResult.FAILURE;
                                        CloseableKt.closeFinally(responseExecute, null);
                                        SessionCredentials session7 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                                        if (session7 != null && (sessionKey8 = session7.getSessionKey()) != null) {
                                            ArraysKt.fill$default(sessionKey8, (byte) 0, 0, 0, 6, (Object) null);
                                            Unit unit7 = Unit.INSTANCE;
                                        }
                                        return refreshResult7;
                                    }
                                    RefreshResult refreshResult8 = RefreshResult.FAILURE;
                                    CloseableKt.closeFinally(responseExecute, null);
                                    SessionCredentials session8 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                                    if (session8 != null && (sessionKey7 = session8.getSessionKey()) != null) {
                                        ArraysKt.fill$default(sessionKey7, (byte) 0, 0, 0, 6, (Object) null);
                                        Unit unit8 = Unit.INSTANCE;
                                    }
                                    return refreshResult8;
                                }
                                RefreshResult refreshResult9 = RefreshResult.FAILURE;
                                CloseableKt.closeFinally(responseExecute, null);
                                SessionCredentials session9 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                                if (session9 != null && (sessionKey6 = session9.getSessionKey()) != null) {
                                    ArraysKt.fill$default(sessionKey6, (byte) 0, 0, 0, 6, (Object) null);
                                    Unit unit9 = Unit.INSTANCE;
                                }
                                return refreshResult9;
                            }
                            RefreshResult refreshResult10 = RefreshResult.FAILURE;
                            CloseableKt.closeFinally(responseExecute, null);
                            SessionCredentials session10 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                            if (session10 != null && (sessionKey5 = session10.getSessionKey()) != null) {
                                ArraysKt.fill$default(sessionKey5, (byte) 0, 0, 0, 6, (Object) null);
                                Unit unit10 = Unit.INSTANCE;
                            }
                            return refreshResult10;
                        } catch (Throwable th) {
                            try {
                                throw th;
                            } catch (Throwable th2) {
                                CloseableKt.closeFinally(responseExecute, th);
                                throw th2;
                            }
                        }
                    }
                }
                RefreshResult refreshResult11 = RefreshResult.FAILURE;
                SessionCredentials session11 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                if (session11 != null && (sessionKey4 = session11.getSessionKey()) != null) {
                    ArraysKt.fill$default(sessionKey4, (byte) 0, 0, 0, 6, (Object) null);
                    Unit unit11 = Unit.INSTANCE;
                }
                return refreshResult11;
            } catch (Throwable th3) {
                SessionCredentials session12 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
                if (session12 != null && (sessionKey3 = session12.getSessionKey()) != null) {
                    ArraysKt.fill$default(sessionKey3, (byte) 0, 0, 0, 6, (Object) null);
                    Unit unit12 = Unit.INSTANCE;
                }
                throw th3;
            }
        } catch (IOException unused) {
            refreshResult = RefreshResult.FAILURE;
            SessionCredentials session13 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
            if (session13 != null && (sessionKey2 = session13.getSessionKey()) != null) {
                ArraysKt.fill$default(sessionKey2, (byte) 0, 0, 0, 6, (Object) null);
            }
        } catch (RuntimeException unused2) {
            refreshResult = RefreshResult.FAILURE;
            SessionCredentials session14 = apiV2RequestAttemptCaptureRequestAttempt.getSession();
            if (session14 != null && (sessionKey = session14.getSessionKey()) != null) {
                ArraysKt.fill$default(sessionKey, (byte) 0, 0, 0, 6, (Object) null);
            }
        }
    }

    /* JADX INFO: compiled from: SessionRefreshCoordinator.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator$Flight;", "", "expectedGeneration", "", "<init>", "(J)V", "getExpectedGeneration", "()J", "done", "Ljava/util/concurrent/CountDownLatch;", "getDone", "()Ljava/util/concurrent/CountDownLatch;", "result", "Lcom/movieboxpro/android/http/v2/session/RefreshResult;", "getResult", "()Lcom/movieboxpro/android/http/v2/session/RefreshResult;", "setResult", "(Lcom/movieboxpro/android/http/v2/session/RefreshResult;)V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Flight {
        private final long expectedGeneration;
        private final CountDownLatch done = new CountDownLatch(1);
        private volatile RefreshResult result = RefreshResult.FAILURE;

        public Flight(long j) {
            this.expectedGeneration = j;
        }

        public final long getExpectedGeneration() {
            return this.expectedGeneration;
        }

        public final CountDownLatch getDone() {
            return this.done;
        }

        public final RefreshResult getResult() {
            return this.result;
        }

        public final void setResult(RefreshResult refreshResult) {
            Intrinsics.checkNotNullParameter(refreshResult, "<set-?>");
            this.result = refreshResult;
        }
    }

    /* JADX INFO: compiled from: SessionRefreshCoordinator.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator$SuccessfulTransition;", "", TypedValues.TransitionType.S_FROM, "", TypedValues.TransitionType.S_TO, "<init>", "(JJ)V", "getFrom", "()J", "getTo", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class SuccessfulTransition {
        private final long from;
        private final long to;

        public static /* synthetic */ SuccessfulTransition copy$default(SuccessfulTransition successfulTransition, long j, long j2, int i, Object obj) {
            if ((i & 1) != 0) {
                j = successfulTransition.from;
            }
            if ((i & 2) != 0) {
                j2 = successfulTransition.to;
            }
            return successfulTransition.copy(j, j2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getFrom() {
            return this.from;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getTo() {
            return this.to;
        }

        public final SuccessfulTransition copy(long from, long to) {
            return new SuccessfulTransition(from, to);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof SuccessfulTransition)) {
                return false;
            }
            SuccessfulTransition successfulTransition = (SuccessfulTransition) other;
            return this.from == successfulTransition.from && this.to == successfulTransition.to;
        }

        public int hashCode() {
            return (HlsDownload$$ExternalSyntheticBackport0.m(this.from) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.to);
        }

        public String toString() {
            return "SuccessfulTransition(from=" + this.from + ", to=" + this.to + ")";
        }

        public SuccessfulTransition(long j, long j2) {
            this.from = j;
            this.to = j2;
        }

        public final long getFrom() {
            return this.from;
        }

        public final long getTo() {
            return this.to;
        }
    }

    /* JADX INFO: compiled from: SessionRefreshCoordinator.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator$Companion;", "", "<init>", "()V", "SUCCESS_CODE", "", "DEFAULT_EXPIRY_SECONDS", "", "PRE_REFRESH_WINDOW_SECONDS", "FORCED_LOGIN_INVALID_CODES", "", "getFORCED_LOGIN_INVALID_CODES", "()Ljava/util/Set;", "DEFAULT_PROACTIVE_EXECUTOR", "Ljava/util/concurrent/Executor;", "getDEFAULT_PROACTIVE_EXECUTOR", "()Ljava/util/concurrent/Executor;", "JSON_MEDIA_TYPE", "Lokhttp3/MediaType;", "getJSON_MEDIA_TYPE", "()Lokhttp3/MediaType;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<Integer> getFORCED_LOGIN_INVALID_CODES() {
            return SessionRefreshCoordinator.FORCED_LOGIN_INVALID_CODES;
        }

        public final Executor getDEFAULT_PROACTIVE_EXECUTOR() {
            return SessionRefreshCoordinator.DEFAULT_PROACTIVE_EXECUTOR;
        }

        public final MediaType getJSON_MEDIA_TYPE() {
            return SessionRefreshCoordinator.JSON_MEDIA_TYPE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DEFAULT_PROACTIVE_EXECUTOR$lambda$13(Runnable runnable) {
        Thread thread = new Thread(runnable, "api-v2-proactive-refresh");
        thread.setDaemon(true);
        thread.start();
    }
}
