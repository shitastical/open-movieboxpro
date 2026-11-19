package com.movieboxpro.android.http.v2.session;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import java.io.IOException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

/* JADX INFO: compiled from: SessionRetryInterceptor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017BQ\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0006\u0012\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003¢\u0006\u0004\b\r\u0010\u000eB7\b\u0017\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0006\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0003¢\u0006\u0004\b\r\u0010\u0012J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionRetryInterceptor;", "Lokhttp3/Interceptor;", "generationReader", "Lkotlin/Function0;", "", "onForcedLogout", "Lkotlin/Function1;", "", "endpointMatcher", "Lokhttp3/HttpUrl;", "", "attemptReader", "Lcom/movieboxpro/android/http/v2/session/ApiV2RequestAttempt;", "<init>", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "coordinator", "Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;", "invalidAfterRetry", "(Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SessionRetryInterceptor implements Interceptor {
    private static final Companion Companion = new Companion(null);
    private static final Set<Integer> FORCED_LOGIN_INVALID_CODES = SetsKt.setOf((Object[]) new Integer[]{-10001, -10022});

    @Deprecated
    public static final long MAX_INSPECTABLE_BODY_BYTES = 1048576;
    private final Function0<ApiV2RequestAttempt> attemptReader;
    private final Function1<HttpUrl, Boolean> endpointMatcher;
    private final Function0<Long> generationReader;
    private final Function1<Long, Unit> onForcedLogout;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRetryInterceptor(SessionRefreshCoordinator coordinator) {
        this(coordinator, null, null, 6, null);
        Intrinsics.checkNotNullParameter(coordinator, "coordinator");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRetryInterceptor(SessionRefreshCoordinator coordinator, Function1<? super HttpUrl, Boolean> endpointMatcher) {
        this(coordinator, endpointMatcher, null, 4, null);
        Intrinsics.checkNotNullParameter(coordinator, "coordinator");
        Intrinsics.checkNotNullParameter(endpointMatcher, "endpointMatcher");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public SessionRetryInterceptor(Function0<Long> generationReader, Function1<? super Long, Unit> onForcedLogout, Function1<? super HttpUrl, Boolean> endpointMatcher, Function0<ApiV2RequestAttempt> function0) {
        Intrinsics.checkNotNullParameter(generationReader, "generationReader");
        Intrinsics.checkNotNullParameter(onForcedLogout, "onForcedLogout");
        Intrinsics.checkNotNullParameter(endpointMatcher, "endpointMatcher");
        this.generationReader = generationReader;
        this.onForcedLogout = onForcedLogout;
        this.endpointMatcher = endpointMatcher;
        this.attemptReader = function0;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.http.v2.session.SessionRetryInterceptor$1, reason: invalid class name */
    /* JADX INFO: compiled from: SessionRetryInterceptor.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<HttpUrl, Boolean> {
        AnonymousClass1(Object obj) {
            super(1, obj, ApiV2EndpointConfig.class, "isMovieUrl", "isMovieUrl(Lokhttp3/HttpUrl;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(HttpUrl p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Boolean.valueOf(((ApiV2EndpointConfig) this.receiver).isMovieUrl(p0));
        }
    }

    public /* synthetic */ SessionRetryInterceptor(Function0 function0, Function1 function1, AnonymousClass1 anonymousClass1, Function0 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(function0, function1, (i & 4) != 0 ? new AnonymousClass1(ApiV2EndpointConfig.INSTANCE) : anonymousClass1, (i & 8) != 0 ? null : function2);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.http.v2.session.SessionRetryInterceptor$2, reason: invalid class name */
    /* JADX INFO: compiled from: SessionRetryInterceptor.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass2 extends FunctionReferenceImpl implements Function1<HttpUrl, Boolean> {
        AnonymousClass2(Object obj) {
            super(1, obj, ApiV2EndpointConfig.class, "isMovieUrl", "isMovieUrl(Lokhttp3/HttpUrl;)Z", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(HttpUrl p0) {
            Intrinsics.checkNotNullParameter(p0, "p0");
            return Boolean.valueOf(((ApiV2EndpointConfig) this.receiver).isMovieUrl(p0));
        }
    }

    public /* synthetic */ SessionRetryInterceptor(SessionRefreshCoordinator sessionRefreshCoordinator, AnonymousClass2 anonymousClass2, Function0 function0, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(sessionRefreshCoordinator, (i & 2) != 0 ? new AnonymousClass2(ApiV2EndpointConfig.INSTANCE) : anonymousClass2, (i & 4) != 0 ? new Function0() { // from class: com.movieboxpro.android.http.v2.session.SessionRetryInterceptor$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Unit.INSTANCE;
            }
        } : function0);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.http.v2.session.SessionRetryInterceptor$4, reason: invalid class name */
    /* JADX INFO: compiled from: SessionRetryInterceptor.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass4 extends FunctionReferenceImpl implements Function0<Long> {
        AnonymousClass4(Object obj) {
            super(0, obj, SessionRefreshCoordinator.class, "generation", "generation()J", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Long invoke() {
            return Long.valueOf(((SessionRefreshCoordinator) this.receiver).generation());
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public SessionRetryInterceptor(final SessionRefreshCoordinator coordinator, Function1<? super HttpUrl, Boolean> endpointMatcher, final Function0<Unit> invalidAfterRetry) {
        this(new AnonymousClass4(coordinator), new Function1() { // from class: com.movieboxpro.android.http.v2.session.SessionRetryInterceptor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SessionRetryInterceptor._init_$lambda$1(coordinator, invalidAfterRetry, ((Long) obj).longValue());
            }
        }, endpointMatcher, new AnonymousClass6(coordinator));
        Intrinsics.checkNotNullParameter(coordinator, "coordinator");
        Intrinsics.checkNotNullParameter(endpointMatcher, "endpointMatcher");
        Intrinsics.checkNotNullParameter(invalidAfterRetry, "invalidAfterRetry");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$1(SessionRefreshCoordinator sessionRefreshCoordinator, Function0 function0, long j) {
        sessionRefreshCoordinator.runIfGenerationCurrent(j, function0);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.http.v2.session.SessionRetryInterceptor$6, reason: invalid class name */
    /* JADX INFO: compiled from: SessionRetryInterceptor.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    static final /* synthetic */ class AnonymousClass6 extends FunctionReferenceImpl implements Function0<ApiV2RequestAttempt> {
        AnonymousClass6(Object obj) {
            super(0, obj, SessionRefreshCoordinator.class, "captureRequestAttempt", "captureRequestAttempt()Lcom/movieboxpro/android/http/v2/session/ApiV2RequestAttempt;", 0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ApiV2RequestAttempt invoke() {
            return ((SessionRefreshCoordinator) this.receiver).captureRequestAttempt();
        }
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        SessionCredentials session;
        byte[] sessionKey;
        JSONObject object;
        SessionCredentials session2;
        byte[] sessionKey2;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        if (!this.endpointMatcher.invoke(request.url()).booleanValue()) {
            return chain.proceed(request);
        }
        ApiV2RequestAttempt apiV2RequestAttemptInvoke = (ApiV2RequestAttempt) request.tag(ApiV2RequestAttempt.class);
        if (apiV2RequestAttemptInvoke == null) {
            Function0<ApiV2RequestAttempt> function0 = this.attemptReader;
            apiV2RequestAttemptInvoke = function0 != null ? function0.invoke() : null;
        }
        long generation = apiV2RequestAttemptInvoke != null ? apiV2RequestAttemptInvoke.getGeneration() : this.generationReader.invoke().longValue();
        if (apiV2RequestAttemptInvoke != null) {
            request = request.newBuilder().tag((Class<? super ApiV2RequestAttempt>) ApiV2RequestAttempt.class, apiV2RequestAttemptInvoke).build();
        }
        try {
            Response responseProceed = chain.proceed(request);
            if (apiV2RequestAttemptInvoke != null && (session2 = apiV2RequestAttemptInvoke.getSession()) != null && (sessionKey2 = session2.getSessionKey()) != null) {
                ArraysKt.fill$default(sessionKey2, (byte) 0, 0, 0, 6, (Object) null);
            }
            ResponseBody responseBodyBody = responseProceed.body();
            if (responseBodyBody == null) {
                return responseProceed;
            }
            long contentLength = responseBodyBody.getContentLength();
            if (contentLength >= 0 && contentLength <= 1048576) {
                BufferedSource bodySource = responseBodyBody.getBodySource();
                bodySource.request(1048577L);
                if (bodySource.getBuffer().size() > 1048576) {
                    return responseProceed;
                }
                MediaType mediaType = responseBodyBody.getMediaType();
                byte[] bArrBytes = responseBodyBody.bytes();
                responseProceed = responseProceed.newBuilder().body(ResponseBody.INSTANCE.create(mediaType, bArrBytes)).build();
                try {
                    object = JSON.parseObject(new String(bArrBytes, Charsets.UTF_8));
                } catch (RuntimeException unused) {
                    object = null;
                }
                if (CollectionsKt.contains(FORCED_LOGIN_INVALID_CODES, object != null ? object.getInteger("code") : null)) {
                    this.onForcedLogout.invoke(Long.valueOf(generation));
                }
            }
            return responseProceed;
        } catch (Throwable th) {
            if (apiV2RequestAttemptInvoke != null && (session = apiV2RequestAttemptInvoke.getSession()) != null && (sessionKey = session.getSessionKey()) != null) {
                ArraysKt.fill$default(sessionKey, (byte) 0, 0, 0, 6, (Object) null);
            }
            throw th;
        }
    }

    /* JADX INFO: compiled from: SessionRetryInterceptor.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionRetryInterceptor$Companion;", "", "<init>", "()V", "MAX_INSPECTABLE_BODY_BYTES", "", "FORCED_LOGIN_INVALID_CODES", "", "", "getFORCED_LOGIN_INVALID_CODES", "()Ljava/util/Set;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Set<Integer> getFORCED_LOGIN_INVALID_CODES() {
            return SessionRetryInterceptor.FORCED_LOGIN_INVALID_CODES;
        }
    }
}
