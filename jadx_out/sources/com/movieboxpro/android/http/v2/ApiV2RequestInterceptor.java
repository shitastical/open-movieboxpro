package com.movieboxpro.android.http.v2;

import com.movieboxpro.android.http.v2.session.AccountSessionLock;
import com.movieboxpro.android.http.v2.session.ApiSessionStore;
import com.movieboxpro.android.http.v2.session.ApiV2RequestAttempt;
import com.movieboxpro.android.http.v2.session.SessionCredentials;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: ApiV2RequestInterceptor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 %2\u00020\u0001:\u0001%B\u0091\u0001\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\t\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0014\b\u0002\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000e\u0012&\b\u0002\u0010\u0011\u001a \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012\u0012\u0004\u0012\u00020\u00140\u000e\u0012\u0010\b\u0002\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\t¢\u0006\u0004\b\u0017\u0010\u0018J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u001c\u0010\u001d\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140 H\u0002J*\u0010!\u001a\u00020\u001e2\u0012\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140 2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u0011\u001a \u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012\u0012\u0004\u0012\u00020\u00140\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;", "Lokhttp3/Interceptor;", "bodyReader", "Lcom/movieboxpro/android/http/v2/ApiV2RequestBodyReader;", "payloadBuilder", "Lcom/movieboxpro/android/http/v2/ApiV2PayloadBuilder;", "environment", "Lcom/movieboxpro/android/http/v2/ApiV2Environment;", "sessionReader", "Lkotlin/Function0;", "Lcom/movieboxpro/android/http/v2/session/SessionCredentials;", "encryptor", "Lcom/movieboxpro/android/http/v2/ApiEncryptV2;", "endpointMatcher", "Lkotlin/Function1;", "Lokhttp3/HttpUrl;", "", "withAppSecrets", "Lkotlin/Function2;", "", "", "attemptReader", "Lcom/movieboxpro/android/http/v2/session/ApiV2RequestAttempt;", "<init>", "(Lcom/movieboxpro/android/http/v2/ApiV2RequestBodyReader;Lcom/movieboxpro/android/http/v2/ApiV2PayloadBuilder;Lcom/movieboxpro/android/http/v2/ApiV2Environment;Lkotlin/jvm/functions/Function0;Lcom/movieboxpro/android/http/v2/ApiEncryptV2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "formBody", "Lokhttp3/RequestBody;", "fields", "", "multipartBody", "fileParts", "", "Lokhttp3/MultipartBody$Part;", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiV2RequestInterceptor implements Interceptor {

    @Deprecated
    public static final String APP_ID = "27";

    @Deprecated
    public static final String APP_MODE = "app";
    private static final Companion Companion = new Companion(null);

    @Deprecated
    public static final String MODULE = "module";

    @Deprecated
    public static final String PLATFORM = "android_tv";

    @Deprecated
    public static final String SESSION_MODE = "session";
    private final Function0<ApiV2RequestAttempt> attemptReader;
    private final ApiV2RequestBodyReader bodyReader;
    private final ApiEncryptV2 encryptor;
    private final Function1<HttpUrl, Boolean> endpointMatcher;
    private final ApiV2Environment environment;
    private final ApiV2PayloadBuilder payloadBuilder;
    private final Function0<SessionCredentials> sessionReader;
    private final Function1<Function2<? super byte[], ? super byte[], String>, String> withAppSecrets;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiV2RequestInterceptor(ApiV2RequestBodyReader bodyReader, ApiV2PayloadBuilder payloadBuilder, ApiV2Environment environment, Function0<SessionCredentials> sessionReader) {
        this(bodyReader, payloadBuilder, environment, sessionReader, null, null, null, null, 240, null);
        Intrinsics.checkNotNullParameter(bodyReader, "bodyReader");
        Intrinsics.checkNotNullParameter(payloadBuilder, "payloadBuilder");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(sessionReader, "sessionReader");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiV2RequestInterceptor(ApiV2RequestBodyReader bodyReader, ApiV2PayloadBuilder payloadBuilder, ApiV2Environment environment, Function0<SessionCredentials> sessionReader, ApiEncryptV2 encryptor) {
        this(bodyReader, payloadBuilder, environment, sessionReader, encryptor, null, null, null, 224, null);
        Intrinsics.checkNotNullParameter(bodyReader, "bodyReader");
        Intrinsics.checkNotNullParameter(payloadBuilder, "payloadBuilder");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(sessionReader, "sessionReader");
        Intrinsics.checkNotNullParameter(encryptor, "encryptor");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiV2RequestInterceptor(ApiV2RequestBodyReader bodyReader, ApiV2PayloadBuilder payloadBuilder, ApiV2Environment environment, Function0<SessionCredentials> sessionReader, ApiEncryptV2 encryptor, Function1<? super HttpUrl, Boolean> endpointMatcher) {
        this(bodyReader, payloadBuilder, environment, sessionReader, encryptor, endpointMatcher, null, null, 192, null);
        Intrinsics.checkNotNullParameter(bodyReader, "bodyReader");
        Intrinsics.checkNotNullParameter(payloadBuilder, "payloadBuilder");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(sessionReader, "sessionReader");
        Intrinsics.checkNotNullParameter(encryptor, "encryptor");
        Intrinsics.checkNotNullParameter(endpointMatcher, "endpointMatcher");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiV2RequestInterceptor(ApiV2RequestBodyReader bodyReader, ApiV2PayloadBuilder payloadBuilder, ApiV2Environment environment, Function0<SessionCredentials> sessionReader, ApiEncryptV2 encryptor, Function1<? super HttpUrl, Boolean> endpointMatcher, Function1<? super Function2<? super byte[], ? super byte[], String>, String> withAppSecrets) {
        this(bodyReader, payloadBuilder, environment, sessionReader, encryptor, endpointMatcher, withAppSecrets, null, 128, null);
        Intrinsics.checkNotNullParameter(bodyReader, "bodyReader");
        Intrinsics.checkNotNullParameter(payloadBuilder, "payloadBuilder");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(sessionReader, "sessionReader");
        Intrinsics.checkNotNullParameter(encryptor, "encryptor");
        Intrinsics.checkNotNullParameter(endpointMatcher, "endpointMatcher");
        Intrinsics.checkNotNullParameter(withAppSecrets, "withAppSecrets");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiV2RequestInterceptor(ApiV2RequestBodyReader bodyReader, ApiV2PayloadBuilder payloadBuilder, Function0<SessionCredentials> sessionReader) {
        this(bodyReader, payloadBuilder, null, sessionReader, null, null, null, null, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, null);
        Intrinsics.checkNotNullParameter(bodyReader, "bodyReader");
        Intrinsics.checkNotNullParameter(payloadBuilder, "payloadBuilder");
        Intrinsics.checkNotNullParameter(sessionReader, "sessionReader");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiV2RequestInterceptor(ApiV2RequestBodyReader bodyReader, Function0<SessionCredentials> sessionReader) {
        this(bodyReader, null, null, sessionReader, null, null, null, null, 246, null);
        Intrinsics.checkNotNullParameter(bodyReader, "bodyReader");
        Intrinsics.checkNotNullParameter(sessionReader, "sessionReader");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ApiV2RequestInterceptor(Function0<SessionCredentials> sessionReader) {
        this(null, null, null, sessionReader, null, null, null, null, 247, null);
        Intrinsics.checkNotNullParameter(sessionReader, "sessionReader");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ApiV2RequestInterceptor(ApiV2RequestBodyReader bodyReader, ApiV2PayloadBuilder payloadBuilder, ApiV2Environment environment, Function0<SessionCredentials> sessionReader, ApiEncryptV2 encryptor, Function1<? super HttpUrl, Boolean> endpointMatcher, Function1<? super Function2<? super byte[], ? super byte[], String>, String> withAppSecrets, Function0<ApiV2RequestAttempt> function0) {
        Intrinsics.checkNotNullParameter(bodyReader, "bodyReader");
        Intrinsics.checkNotNullParameter(payloadBuilder, "payloadBuilder");
        Intrinsics.checkNotNullParameter(environment, "environment");
        Intrinsics.checkNotNullParameter(sessionReader, "sessionReader");
        Intrinsics.checkNotNullParameter(encryptor, "encryptor");
        Intrinsics.checkNotNullParameter(endpointMatcher, "endpointMatcher");
        Intrinsics.checkNotNullParameter(withAppSecrets, "withAppSecrets");
        this.bodyReader = bodyReader;
        this.payloadBuilder = payloadBuilder;
        this.environment = environment;
        this.sessionReader = sessionReader;
        this.encryptor = encryptor;
        this.endpointMatcher = endpointMatcher;
        this.withAppSecrets = withAppSecrets;
        this.attemptReader = function0;
    }

    public /* synthetic */ ApiV2RequestInterceptor(ApiV2RequestBodyReader apiV2RequestBodyReader, ApiV2PayloadBuilder apiV2PayloadBuilder, ApiV2Environment apiV2Environment, Function0 function0, ApiEncryptV2 apiEncryptV2, Function1 function1, Function1 function2, Function0 function3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ApiV2RequestBodyReader() : apiV2RequestBodyReader, (i & 2) != 0 ? new ApiV2PayloadBuilder(null, null, 3, null) : apiV2PayloadBuilder, (i & 4) != 0 ? TvApiV2Environment.INSTANCE : apiV2Environment, function0, (i & 16) != 0 ? new ApiEncryptV2(null, null, null, 7, null) : apiEncryptV2, (i & 32) != 0 ? new AnonymousClass1(ApiV2EndpointConfig.INSTANCE) : function1, (i & 64) != 0 ? new Function1() { // from class: com.movieboxpro.android.http.v2.ApiV2RequestInterceptor$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return ApiV2RequestInterceptor._init_$lambda$0((Function2) obj);
            }
        } : function2, (i & 128) != 0 ? null : function3);
    }

    /* JADX INFO: renamed from: com.movieboxpro.android.http.v2.ApiV2RequestInterceptor$1, reason: invalid class name */
    /* JADX INFO: compiled from: ApiV2RequestInterceptor.kt */
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

    /* JADX INFO: Access modifiers changed from: private */
    public static final String _init_$lambda$0(Function2 block) {
        Intrinsics.checkNotNullParameter(block, "block");
        return (String) NativeApiSecrets.INSTANCE.withAppSecrets(block);
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        byte[] sessionKey;
        final SessionCredentials sessionCredentials;
        RequestBody requestBodyFormBody;
        String numericUid;
        byte[] sessionKey2;
        ApiV2RequestAttempt apiV2RequestAttempt;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        if (!this.endpointMatcher.invoke(request.url()).booleanValue()) {
            return chain.proceed(request);
        }
        String str = (String) CollectionsKt.getOrNull(request.url().pathSegments(), 1);
        String str2 = str;
        if (str2 == null || StringsKt.isBlank(str2) || Intrinsics.areEqual(str, ApiV2EndpointConfig.RESERVED_ENCRYPT_MODULE)) {
            throw new IOException("Movie API module must be a URL path segment");
        }
        ParsedApiBody parsedApiBody = this.bodyReader.read(request.body());
        parsedApiBody.getFields().remove(MODULE);
        ApiV2RequestAttempt apiV2RequestAttemptInvoke = (ApiV2RequestAttempt) request.tag(ApiV2RequestAttempt.class);
        if (apiV2RequestAttemptInvoke == null) {
            Function0<ApiV2RequestAttempt> function0 = this.attemptReader;
            apiV2RequestAttemptInvoke = function0 != null ? function0.invoke() : null;
            if (apiV2RequestAttemptInvoke == null) {
                synchronized (AccountSessionLock.monitor) {
                    SessionCredentials sessionCredentialsInvoke = this.sessionReader.invoke();
                    try {
                        apiV2RequestAttempt = new ApiV2RequestAttempt(sessionCredentialsInvoke, this.environment.snapshot(), 0L);
                    } catch (RuntimeException e) {
                        if (sessionCredentialsInvoke != null && (sessionKey2 = sessionCredentialsInvoke.getSessionKey()) != null) {
                            ArraysKt.fill$default(sessionKey2, (byte) 0, 0, 0, 6, (Object) null);
                        }
                        throw e;
                    }
                }
                apiV2RequestAttemptInvoke = apiV2RequestAttempt;
            }
        }
        SessionCredentials session = apiV2RequestAttemptInvoke.getSession();
        if (session != null) {
            try {
                sessionCredentials = session.getSessionKey().length == 0 ? null : session;
            } finally {
                if (session != null && (sessionKey = session.getSessionKey()) != null) {
                    ArraysKt.fill$default(sessionKey, (byte) 0, 0, 0, 6, (Object) null);
                }
            }
        }
        ApiV2EnvironmentSnapshot environment = apiV2RequestAttemptInvoke.getEnvironment();
        final boolean z = (ApiV2Modules.INSTANCE.usesAppCrypto(str) || sessionCredentials == null) ? false : true;
        String str3 = z ? SESSION_MODE : APP_MODE;
        final LinkedHashMap<String, Object> linkedHashMapBuild = this.payloadBuilder.build(parsedApiBody.getFields(), str, environment);
        LinkedHashMap linkedHashMapLinkedMapOf = MapsKt.linkedMapOf(TuplesKt.to("appid", APP_ID), TuplesKt.to("crypto", str3), TuplesKt.to("data", this.withAppSecrets.invoke(new Function2() { // from class: com.movieboxpro.android.http.v2.ApiV2RequestInterceptor$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return ApiV2RequestInterceptor.intercept$lambda$3(this.f$0, linkedHashMapBuild, z, sessionCredentials, (byte[]) obj, (byte[]) obj2);
            }
        })), TuplesKt.to("platform", PLATFORM), TuplesKt.to("app_version", environment.getAppVersion()));
        if (environment.getLoggedIn() && (numericUid = environment.getNumericUid()) != null && numericUid.length() != 0) {
            linkedHashMapLinkedMapOf.put("uid", environment.getNumericUid());
        }
        if (z) {
            linkedHashMapLinkedMapOf.put("open_udid", environment.getOpenUdid());
            Intrinsics.checkNotNull(sessionCredentials);
            String skVer = sessionCredentials.getSkVer();
            if (skVer != null) {
                String str4 = skVer.length() > 0 ? skVer : null;
                if (str4 != null) {
                    linkedHashMapLinkedMapOf.put(ApiSessionStore.KEY_SK_VER, str4);
                }
            }
        }
        if (parsedApiBody.getMultipart()) {
            requestBodyFormBody = multipartBody(linkedHashMapLinkedMapOf, parsedApiBody.getFileParts());
        } else {
            requestBodyFormBody = formBody(linkedHashMapLinkedMapOf);
        }
        return chain.proceed(request.newBuilder().post(requestBodyFormBody).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String intercept$lambda$3(ApiV2RequestInterceptor apiV2RequestInterceptor, LinkedHashMap linkedHashMap, boolean z, SessionCredentials sessionCredentials, byte[] appKey, byte[] appSkey) {
        Intrinsics.checkNotNullParameter(appKey, "appKey");
        Intrinsics.checkNotNullParameter(appSkey, "appSkey");
        ApiEncryptV2 apiEncryptV2 = apiV2RequestInterceptor.encryptor;
        LinkedHashMap linkedHashMap2 = linkedHashMap;
        if (z) {
            Intrinsics.checkNotNull(sessionCredentials);
            appSkey = sessionCredentials.getSessionKey();
        }
        return apiEncryptV2.encrypt(linkedHashMap2, appSkey, appKey);
    }

    private final RequestBody formBody(Map<String, String> fields) {
        FormBody.Builder builder = new FormBody.Builder(null, 1, null);
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    private final RequestBody multipartBody(Map<String, String> fields, List<MultipartBody.Part> fileParts) {
        MultipartBody.Builder type = new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            type.addFormDataPart(entry.getKey(), entry.getValue());
        }
        Iterator<T> it = fileParts.iterator();
        while (it.hasNext()) {
            type.addPart((MultipartBody.Part) it.next());
        }
        return type.build();
    }

    /* JADX INFO: compiled from: ApiV2RequestInterceptor.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor$Companion;", "", "<init>", "()V", "APP_ID", "", "PLATFORM", "APP_MODE", "SESSION_MODE", "MODULE", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
