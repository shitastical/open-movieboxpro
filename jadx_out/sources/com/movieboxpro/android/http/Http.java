package com.movieboxpro.android.http;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Pair;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;
import com.google.common.net.HttpHeaders;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.http.converter.FastJsonConverterFactory;
import com.movieboxpro.android.http.converter.ToStringConverterFactory;
import com.movieboxpro.android.http.v2.ApiEncryptV2;
import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import com.movieboxpro.android.http.v2.ApiV2PayloadBuilder;
import com.movieboxpro.android.http.v2.ApiV2RequestBodyReader;
import com.movieboxpro.android.http.v2.ApiV2RequestInterceptor;
import com.movieboxpro.android.http.v2.NativeApiSecrets;
import com.movieboxpro.android.http.v2.TvApiV2Environment;
import com.movieboxpro.android.http.v2.session.AndroidSessionKeyCipher;
import com.movieboxpro.android.http.v2.session.ApiSessionManager;
import com.movieboxpro.android.http.v2.session.ApiSessionStore;
import com.movieboxpro.android.http.v2.session.FallbackSessionKeyCipher;
import com.movieboxpro.android.http.v2.session.LoginInvalidHandler;
import com.movieboxpro.android.http.v2.session.SessionCipherFailureHandler;
import com.movieboxpro.android.http.v2.session.SessionRefreshCoordinator;
import com.movieboxpro.android.http.v2.session.SessionRetryInterceptor;
import com.movieboxpro.android.http.v2.session.SharedPrefsSessionPreferences;
import com.movieboxpro.android.http.v2.session.SoftwareSessionKeyCipher;
import com.movieboxpro.android.http.v2.session.UserIdentityStore;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleRequest;
import com.movieboxpro.android.view.fragment.account.AccountSwitchGuard;
import com.movieboxpro.android.view.fragment.account.SessionInvalidRouter;
import j$.util.Objects;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/* JADX INFO: loaded from: classes5.dex */
public class Http {
    private static volatile ApiSessionManager apiSessionManager;
    private static volatile ApiSessionStore apiSessionStore;
    private static Handler mHandler;
    private static volatile APIService service;
    private static final Object SERVICE_LOCK = new Object();
    private static final Object SESSION_STORE_LOCK = new Object();
    private static final LoginInvalidHandler LOGIN_INVALID_HANDLER = new LoginInvalidHandler(new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Http.lambda$static$0();
        }
    }, new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda10
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Http.lambda$static$1();
        }
    }, new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda11
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return Boolean.valueOf(AccountSwitchGuard.isActive());
        }
    });
    private static final V2ClientProvider MOVIE_CLIENTS = new V2ClientProvider(new V2ClientFactory() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda12
        @Override // com.movieboxpro.android.http.Http.V2ClientFactory
        public final Http.V2Clients create() {
            return Http.buildV2Clients();
        }
    });

    interface V2ClientFactory {
        V2Clients create();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static synchronized Handler mainHandler() {
        if (mHandler == null) {
            mHandler = new Handler(Looper.getMainLooper());
        }
        return mHandler;
    }

    static /* synthetic */ Unit lambda$static$0() {
        App.logout();
        return Unit.INSTANCE;
    }

    static /* synthetic */ Unit lambda$static$1() {
        SessionInvalidRouter.startAfterForcedLogout(App.getContext());
        return Unit.INSTANCE;
    }

    public static APIService getService() {
        APIService aPIServiceCreateService = service;
        if (aPIServiceCreateService == null) {
            synchronized (SERVICE_LOCK) {
                aPIServiceCreateService = service;
                if (aPIServiceCreateService == null) {
                    aPIServiceCreateService = createService(getOkHttpClient());
                    service = aPIServiceCreateService;
                }
            }
        }
        return aPIServiceCreateService;
    }

    public static void baseUrlChanged() {
        synchronized (SERVICE_LOCK) {
            service = createService(getOkHttpClient());
        }
    }

    private static APIService createService(OkHttpClient movieClient) {
        return (APIService) new Retrofit.Builder().client(movieClient).baseUrl(API.MOVIE_HOST + "/").addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(new ToStringConverterFactory()).addConverterFactory(FastJsonConverterFactory.create()).build().create(APIService.class);
    }

    public static OkHttpClient getOkHttpClient() {
        return MOVIE_CLIENTS.main();
    }

    public static OkHttpClient getRefreshOkHttpClient() {
        return MOVIE_CLIENTS.refresh();
    }

    public static SessionRefreshCoordinator getSessionRefreshCoordinator() {
        return MOVIE_CLIENTS.coordinator();
    }

    public static LoginInvalidHandler getLoginInvalidHandler() {
        return MOVIE_CLIENTS.invalidHandler();
    }

    public static void refreshApiSessionIfExpiringAsync() {
        getSessionRefreshCoordinator().refreshIfExpiringAsync();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static V2Clients buildV2Clients() {
        OkHttpClient okHttpClientCreateTransportBaseClient = createTransportBaseClient();
        ApiV2RequestInterceptor apiV2RequestInterceptorCreateApiV2RequestInterceptor = createApiV2RequestInterceptor();
        ArrayList arrayList = new ArrayList();
        arrayList.add(createHeaderInterceptor());
        arrayList.add(new DynamicTimeoutInterceptor());
        if (App.isDebug) {
            arrayList.add(createLoggingInterceptor());
        }
        OkHttpClient okHttpClientCreateV2Client = createV2Client(okHttpClientCreateTransportBaseClient, null, apiV2RequestInterceptorCreateApiV2RequestInterceptor, arrayList);
        final LoginInvalidHandler loginInvalidHandler = LOGIN_INVALID_HANDLER;
        SessionRefreshCoordinator sessionRefreshCoordinator = new SessionRefreshCoordinator(getApiSessionManager(), okHttpClientCreateV2Client, new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ApiV2EndpointConfig.moduleUrl("User_token");
            }
        }, TvApiV2Environment.INSTANCE, new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Long.valueOf(System.currentTimeMillis() / 1000);
            }
        }, AsyncTask.THREAD_POOL_EXECUTOR, new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Http.lambda$buildV2Clients$4(loginInvalidHandler);
            }
        });
        ApiV2EndpointConfig apiV2EndpointConfig = ApiV2EndpointConfig.INSTANCE;
        Objects.requireNonNull(apiV2EndpointConfig);
        return new V2Clients(createV2Client(okHttpClientCreateTransportBaseClient, new SessionRetryInterceptor(sessionRefreshCoordinator, new Http$$ExternalSyntheticLambda3(apiV2EndpointConfig), new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Http.lambda$buildV2Clients$5(loginInvalidHandler);
            }
        }), apiV2RequestInterceptorCreateApiV2RequestInterceptor, arrayList), okHttpClientCreateV2Client, sessionRefreshCoordinator, loginInvalidHandler);
    }

    static /* synthetic */ Unit lambda$buildV2Clients$4(LoginInvalidHandler loginInvalidHandler) {
        loginInvalidHandler.handle();
        return Unit.INSTANCE;
    }

    static /* synthetic */ Unit lambda$buildV2Clients$5(LoginInvalidHandler loginInvalidHandler) {
        loginInvalidHandler.handle();
        return Unit.INSTANCE;
    }

    private static OkHttpClient createTransportBaseClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        Pair<SSLSocketFactory, X509TrustManager> upClient = setUpClient();
        if (upClient != null) {
            builder.sslSocketFactory((SSLSocketFactory) upClient.first, (X509TrustManager) upClient.second);
        }
        setCookieJar(builder);
        setCacheDirectory(builder);
        setTimeout(builder);
        return builder.build();
    }

    private static ApiV2RequestInterceptor createApiV2RequestInterceptor() {
        ApiV2RequestBodyReader apiV2RequestBodyReader = new ApiV2RequestBodyReader();
        ApiV2PayloadBuilder apiV2PayloadBuilder = new ApiV2PayloadBuilder();
        TvApiV2Environment tvApiV2Environment = TvApiV2Environment.INSTANCE;
        Function0 function0 = new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Http.getApiSessionManager().current();
            }
        };
        ApiEncryptV2 apiEncryptV2 = new ApiEncryptV2();
        ApiV2EndpointConfig apiV2EndpointConfig = ApiV2EndpointConfig.INSTANCE;
        Objects.requireNonNull(apiV2EndpointConfig);
        Http$$ExternalSyntheticLambda3 http$$ExternalSyntheticLambda3 = new Http$$ExternalSyntheticLambda3(apiV2EndpointConfig);
        final NativeApiSecrets nativeApiSecrets = NativeApiSecrets.INSTANCE;
        Objects.requireNonNull(nativeApiSecrets);
        return new ApiV2RequestInterceptor(apiV2RequestBodyReader, apiV2PayloadBuilder, tvApiV2Environment, function0, apiEncryptV2, http$$ExternalSyntheticLambda3, new Function1() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda6
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return (String) nativeApiSecrets.withAppSecrets((Function2) obj);
            }
        }, new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda7
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Http.getApiSessionManager().captureRequestAttempt(TvApiV2Environment.INSTANCE);
            }
        });
    }

    private static ApiSessionStore getApiSessionStore() {
        ApiSessionStore apiSessionStore2 = apiSessionStore;
        if (apiSessionStore2 == null) {
            synchronized (SESSION_STORE_LOCK) {
                apiSessionStore2 = apiSessionStore;
                if (apiSessionStore2 == null) {
                    final AndroidSessionKeyCipher androidSessionKeyCipher = new AndroidSessionKeyCipher(App.getContext());
                    ApiSessionStore apiSessionStore3 = new ApiSessionStore(new SharedPrefsSessionPreferences(App.getContext()), new FallbackSessionKeyCipher(androidSessionKeyCipher, SoftwareSessionKeyCipher.fromContext(App.getContext()), new Runnable() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda16
                        @Override // java.lang.Runnable
                        public final void run() {
                            androidSessionKeyCipher.evictKeys();
                        }
                    }, new SessionCipherFailureHandler() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda17
                        @Override // com.movieboxpro.android.http.v2.session.SessionCipherFailureHandler
                        public final void onFailure(Throwable th) {
                            MLog.e("ApiSession", "Keystore session wrap failed: " + th.getClass().getName());
                        }
                    }));
                    apiSessionStore = apiSessionStore3;
                    apiSessionStore2 = apiSessionStore3;
                }
            }
        }
        return apiSessionStore2;
    }

    public static ApiSessionManager getApiSessionManager() {
        ApiSessionManager apiSessionManager2 = apiSessionManager;
        if (apiSessionManager2 == null) {
            synchronized (SESSION_STORE_LOCK) {
                apiSessionManager2 = apiSessionManager;
                if (apiSessionManager2 == null) {
                    apiSessionManager2 = new ApiSessionManager(getApiSessionStore(), new UserIdentityStore() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda8
                        @Override // com.movieboxpro.android.http.v2.session.UserIdentityStore
                        public final boolean updateUidV2(String str) {
                            return App.updateUidV2(str);
                        }
                    });
                    apiSessionManager = apiSessionManager2;
                }
            }
        }
        return apiSessionManager2;
    }

    static V2Clients createV2Clients(OkHttpClient transportBase, ApiV2RequestInterceptor requestInterceptor, List<Interceptor> transportInterceptors, SessionRefreshCoordinator coordinator, final LoginInvalidHandler invalidHandler) {
        return new V2Clients(createV2Client(transportBase, new SessionRetryInterceptor(coordinator, new Function1() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Http.lambda$createV2Clients$9((HttpUrl) obj);
            }
        }, new Function0() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Http.lambda$createV2Clients$10(invalidHandler);
            }
        }), requestInterceptor, transportInterceptors), createV2Client(transportBase, null, requestInterceptor, transportInterceptors), coordinator, invalidHandler);
    }

    static /* synthetic */ Boolean lambda$createV2Clients$9(HttpUrl httpUrl) {
        return false;
    }

    static /* synthetic */ Unit lambda$createV2Clients$10(LoginInvalidHandler loginInvalidHandler) {
        loginInvalidHandler.handle();
        return Unit.INSTANCE;
    }

    private static OkHttpClient createV2Client(OkHttpClient transportBase, Interceptor leadingInterceptor, ApiV2RequestInterceptor requestInterceptor, List<Interceptor> transportInterceptors) {
        OkHttpClient.Builder builderNewBuilder = transportBase.newBuilder();
        if (leadingInterceptor != null) {
            builderNewBuilder.addInterceptor(leadingInterceptor);
        }
        builderNewBuilder.addInterceptor(requestInterceptor);
        Iterator<Interceptor> it = transportInterceptors.iterator();
        while (it.hasNext()) {
            builderNewBuilder.addInterceptor(it.next());
        }
        return builderNewBuilder.build();
    }

    static final class V2Clients {
        final SessionRefreshCoordinator coordinator;
        final LoginInvalidHandler invalidHandler;
        final OkHttpClient main;
        final OkHttpClient refresh;

        V2Clients(OkHttpClient main, OkHttpClient refresh, SessionRefreshCoordinator coordinator, LoginInvalidHandler invalidHandler) {
            this.main = main;
            this.refresh = refresh;
            this.coordinator = coordinator;
            this.invalidHandler = invalidHandler;
        }
    }

    static final class V2ClientProvider {
        private volatile V2Clients clients;
        private final V2ClientFactory factory;

        V2ClientProvider(V2ClientFactory factory) {
            this.factory = factory;
        }

        OkHttpClient main() {
            return clients().main;
        }

        OkHttpClient refresh() {
            return clients().refresh;
        }

        SessionRefreshCoordinator coordinator() {
            return clients().coordinator;
        }

        LoginInvalidHandler invalidHandler() {
            return clients().invalidHandler;
        }

        V2Clients clients() {
            V2Clients v2ClientsCreate = this.clients;
            if (v2ClientsCreate == null) {
                synchronized (this) {
                    v2ClientsCreate = this.clients;
                    if (v2ClientsCreate == null) {
                        v2ClientsCreate = this.factory.create();
                        this.clients = v2ClientsCreate;
                    }
                }
            }
            return v2ClientsCreate;
        }
    }

    private static Pair<SSLSocketFactory, X509TrustManager> setUpClient() {
        try {
            return (Pair) NativeApiSecrets.INSTANCE.withClientTlsPem(new Function2() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return Http.lambda$setUpClient$11((byte[]) obj, (byte[]) obj2);
                }
            });
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (cause != null) {
                cause.printStackTrace();
                return null;
            }
            e.printStackTrace();
            return null;
        }
    }

    static /* synthetic */ Pair lambda$setUpClient$11(byte[] bArr, byte[] bArr2) {
        try {
            return createSslClient(bArr, bArr2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Pair<SSLSocketFactory, X509TrustManager> createSslClient(byte[] keyPem, byte[] certPem) throws InvalidKeySpecException, NoSuchAlgorithmException, UnrecoverableKeyException, IOException, CertificateException, KeyStoreException, KeyManagementException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        byte[] bArrDecode = Base64.decode(new String(keyPem, StandardCharsets.UTF_8).replace("-----BEGIN PRIVATE KEY-----", "").replace("-----END PRIVATE KEY-----", "").replaceAll("\\s", ""), 0);
        KeyFactory keyFactory = KeyFactory.getInstance(AndroidSessionKeyCipher.RSA_ALGORITHM);
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(bArrDecode);
        Certificate certificateGenerateCertificate = certificateFactory.generateCertificate(new ByteArrayInputStream(certPem));
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null, "secret".toCharArray());
        keyStore.setKeyEntry("client", keyFactory.generatePrivate(pKCS8EncodedKeySpec), "secret".toCharArray(), new Certificate[]{certificateGenerateCertificate});
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init((KeyStore) null);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, "secret".toCharArray());
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
        SSLContext sSLContext = SSLContext.getInstance("TLS");
        sSLContext.init(keyManagers, trustManagers, new SecureRandom());
        return new Pair<>(sSLContext.getSocketFactory(), (X509TrustManager) trustManagers[0]);
    }

    private static Interceptor createLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    private static void setLoggingInterceptor(OkHttpClient.Builder builder) {
        if (builder != null) {
            builder.addInterceptor(createLoggingInterceptor());
        }
    }

    private static void setTimeout(OkHttpClient.Builder builder) {
        builder.connectTimeout(40L, TimeUnit.SECONDS);
        builder.readTimeout(40L, TimeUnit.SECONDS);
        builder.writeTimeout(40L, TimeUnit.SECONDS);
        builder.retryOnConnectionFailure(true);
        builder.followRedirects(true);
        builder.followSslRedirects(true);
    }

    private static void setCacheDirectory(OkHttpClient.Builder builder) {
        builder.cache(new Cache(new File(App.getContext().getCacheDir().getPath(), "responses"), 10485760L));
    }

    private static void setCookieJar(OkHttpClient.Builder builder) {
        builder.cookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getContext())));
    }

    private static Interceptor createHeaderInterceptor() {
        return new Interceptor() { // from class: com.movieboxpro.android.http.Http$$ExternalSyntheticLambda9
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                return Http.lambda$createHeaderInterceptor$12(chain);
            }
        };
    }

    static /* synthetic */ Response lambda$createHeaderInterceptor$12(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request.newBuilder().header("Platform", ApiV2RequestInterceptor.PLATFORM).header(HttpHeaders.ACCEPT, "text/plain; charset=utf-8").method(request.method(), request.body()).build());
    }

    public static void get(String url, Map<String, String> params, final RequestCallback<String> callback) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (App.isDebug) {
            setLoggingInterceptor(builder);
        }
        OkHttpClient okHttpClientBuild = builder.build();
        HttpUrl httpUrl = HttpUrl.parse(url);
        if (httpUrl == null) {
            if (callback != null) {
                mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.2
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onFailure(-2, "url错误");
                    }
                });
                return;
            }
            return;
        }
        HttpUrl.Builder builderNewBuilder = httpUrl.newBuilder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builderNewBuilder.addQueryParameter(entry.getKey(), entry.getValue());
            }
        }
        okHttpClientBuild.newCall(new Request.Builder().url(builderNewBuilder.build()).cacheControl(CacheControl.FORCE_NETWORK).get().build()).enqueue(new Callback() { // from class: com.movieboxpro.android.http.Http.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                if (callback != null) {
                    final String localizedMessage = e.getLocalizedMessage();
                    Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            callback.onFailure(-1, localizedMessage);
                        }
                    });
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (response.getIsSuccessful()) {
                    ResponseBody responseBodyBody = response.body();
                    if (responseBodyBody != null) {
                        final String strString = responseBodyBody.string();
                        if (callback != null) {
                            Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.1.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    callback.onSuccess(strString);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if (callback != null) {
                        Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.1.3
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onFailure(-1, "无响应数据");
                            }
                        });
                        return;
                    }
                    return;
                }
                final int iCode = response.code();
                final String strMessage = response.message();
                if (callback != null) {
                    Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.1.4
                        @Override // java.lang.Runnable
                        public void run() {
                            callback.onFailure(iCode, strMessage);
                        }
                    });
                }
            }
        });
    }

    public static void post(String url, Map<String, String> params, final RequestCallback<String> callback) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (App.isDebug) {
            setLoggingInterceptor(builder);
        }
        OkHttpClient okHttpClientBuild = builder.build();
        FormBody.Builder builder2 = new FormBody.Builder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder2.add(entry.getKey(), entry.getValue());
            }
        }
        okHttpClientBuild.newCall(new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(url).post(builder2.build()).build()).enqueue(new Callback() { // from class: com.movieboxpro.android.http.Http.3
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                if (callback != null) {
                    final String localizedMessage = e.getLocalizedMessage();
                    Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            callback.onFailure(-1, localizedMessage);
                        }
                    });
                }
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                if (response.getIsSuccessful()) {
                    ResponseBody responseBodyBody = response.body();
                    if (responseBodyBody != null) {
                        final String strString = responseBodyBody.string();
                        if (callback != null) {
                            Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.3.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    callback.onSuccess(strString);
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if (callback != null) {
                        Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.3.3
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onFailure(-1, "无响应数据");
                            }
                        });
                        return;
                    }
                    return;
                }
                if (callback != null) {
                    final int iCode = response.code();
                    final String strMessage = response.message();
                    Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.3.4
                        @Override // java.lang.Runnable
                        public void run() {
                            callback.onFailure(iCode, strMessage);
                        }
                    });
                }
            }
        });
    }

    public static void uploadAsync(String url, Map<String, String> params, Map<String, String> fileParams, final RequestCallback<String> callback) {
        String str;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (App.isDebug) {
            setLoggingInterceptor(builder);
        }
        OkHttpClient okHttpClientBuild = builder.build();
        MultipartBody.Builder builder2 = new MultipartBody.Builder();
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder2.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        if (fileParams == null || fileParams.isEmpty()) {
            if (callback != null) {
                mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.6
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onFailure(-1, "文件路径未指定");
                    }
                });
                return;
            }
            return;
        }
        String str2 = fileParams.containsKey("filePath") ? fileParams.get("filePath") : null;
        if (str2 == null) {
            if (callback != null) {
                mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.5
                    @Override // java.lang.Runnable
                    public void run() {
                        callback.onFailure(-1, "文件路径未指定");
                    }
                });
                return;
            }
            return;
        }
        if (!fileParams.containsKey("mimeType")) {
            str = "image/*";
        } else {
            str = fileParams.get("mimeType");
        }
        File file = new File(str2);
        if (file.exists() && file.isFile()) {
            builder2.addFormDataPart("Filedata", file.getName(), MultipartBody.create(MediaType.parse(str), file));
            okHttpClientBuild.newCall(new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(url).post(builder2.build()).build()).enqueue(new Callback() { // from class: com.movieboxpro.android.http.Http.7
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException e) {
                    if (callback != null) {
                        final String localizedMessage = e.getLocalizedMessage();
                        Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.7.1
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onFailure(-1, localizedMessage);
                            }
                        });
                    }
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.getIsSuccessful()) {
                        ResponseBody responseBodyBody = response.body();
                        if (responseBodyBody != null) {
                            final String strString = responseBodyBody.string();
                            if (callback != null) {
                                Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.7.2
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        callback.onSuccess(strString);
                                    }
                                });
                                return;
                            }
                            return;
                        }
                        if (callback != null) {
                            Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.7.3
                                @Override // java.lang.Runnable
                                public void run() {
                                    callback.onFailure(-1, "无响应数据");
                                }
                            });
                            return;
                        }
                        return;
                    }
                    if (callback != null) {
                        final int iCode = response.code();
                        final String strMessage = response.message();
                        Http.mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.7.4
                            @Override // java.lang.Runnable
                            public void run() {
                                callback.onFailure(iCode, strMessage);
                            }
                        });
                    }
                }
            });
        } else if (callback != null) {
            mainHandler().post(new Runnable() { // from class: com.movieboxpro.android.http.Http.4
                @Override // java.lang.Runnable
                public void run() {
                    callback.onFailure(-1, "文件不存在");
                }
            });
        }
    }

    public static Call uploadSync(String url, Map<String, String> params, Map<String, String> fileParams) {
        String str;
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (App.isDebug) {
            setLoggingInterceptor(builder);
        }
        OkHttpClient okHttpClientBuild = builder.build();
        MultipartBody.Builder builder2 = new MultipartBody.Builder();
        builder2.setType(MediaType.parse("multipart/form-data"));
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder2.addFormDataPart(entry.getKey(), entry.getValue());
            }
        }
        if (fileParams != null && !fileParams.isEmpty()) {
            String str2 = fileParams.containsKey("filePath") ? fileParams.get("filePath") : null;
            if (str2 != null) {
                if (!fileParams.containsKey("mimeType")) {
                    str = "image/*";
                } else {
                    str = fileParams.get("mimeType");
                }
                File file = new File(str2);
                if (file.exists() && file.isFile()) {
                    builder2.addFormDataPart("Filedata", file.getName(), MultipartBody.create(MediaType.parse(str), file));
                    return okHttpClientBuild.newCall(new Request.Builder().cacheControl(CacheControl.FORCE_NETWORK).url(url).post(builder2.build()).build());
                }
            }
        }
        return null;
    }

    static class DynamicTimeoutInterceptor implements Interceptor {
        DynamicTimeoutInterceptor() {
        }

        @Override // okhttp3.Interceptor
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            if (TranslateSubtitleRequest.isLongTimeoutUrl(request.url().getUrl())) {
                return chain.withConnectTimeout(60, TimeUnit.SECONDS).withReadTimeout(60, TimeUnit.SECONDS).withWriteTimeout(60, TimeUnit.SECONDS).proceed(request);
            }
            return chain.withConnectTimeout(30, TimeUnit.SECONDS).withReadTimeout(30, TimeUnit.SECONDS).withWriteTimeout(30, TimeUnit.SECONDS).proceed(request);
        }
    }
}
