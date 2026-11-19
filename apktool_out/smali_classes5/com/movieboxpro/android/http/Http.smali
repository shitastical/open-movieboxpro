.class public Lcom/movieboxpro/android/http/Http;
.super Ljava/lang/Object;
.source "Http.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/movieboxpro/android/http/Http$V2ClientProvider;,
        Lcom/movieboxpro/android/http/Http$DynamicTimeoutInterceptor;,
        Lcom/movieboxpro/android/http/Http$V2Clients;,
        Lcom/movieboxpro/android/http/Http$V2ClientFactory;
    }
.end annotation


# static fields
.field private static final LOGIN_INVALID_HANDLER:Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;

.field private static final MOVIE_CLIENTS:Lcom/movieboxpro/android/http/Http$V2ClientProvider;

.field private static final SERVICE_LOCK:Ljava/lang/Object;

.field private static final SESSION_STORE_LOCK:Ljava/lang/Object;

.field private static volatile apiSessionManager:Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;

.field private static volatile apiSessionStore:Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;

.field private static mHandler:Landroid/os/Handler;

.field private static volatile service:Lcom/movieboxpro/android/http/APIService;


# direct methods
.method public static synthetic $r8$lambda$dpi6YQ1e8vsbCMIVo9BeCuRs85E()Lcom/movieboxpro/android/http/Http$V2Clients;
    .locals 1

    invoke-static {}, Lcom/movieboxpro/android/http/Http;->buildV2Clients()Lcom/movieboxpro/android/http/Http$V2Clients;

    move-result-object v0

    return-object v0
.end method

.method static bridge synthetic -$$Nest$smmainHandler()Landroid/os/Handler;
    .locals 1

    invoke-static {}, Lcom/movieboxpro/android/http/Http;->mainHandler()Landroid/os/Handler;

    move-result-object v0

    return-object v0
.end method

.method static constructor <clinit>()V
    .locals 4

    .line 108
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/movieboxpro/android/http/Http;->SERVICE_LOCK:Ljava/lang/Object;

    .line 109
    new-instance v0, Ljava/lang/Object;

    invoke-direct {v0}, Ljava/lang/Object;-><init>()V

    sput-object v0, Lcom/movieboxpro/android/http/Http;->SESSION_STORE_LOCK:Ljava/lang/Object;

    .line 110
    new-instance v0, Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;

    new-instance v1, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda0;

    invoke-direct {v1}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda0;-><init>()V

    new-instance v2, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda10;

    invoke-direct {v2}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda10;-><init>()V

    new-instance v3, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda11;

    invoke-direct {v3}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda11;-><init>()V

    invoke-direct {v0, v1, v2, v3}, Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;-><init>(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)V

    sput-object v0, Lcom/movieboxpro/android/http/Http;->LOGIN_INVALID_HANDLER:Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;

    .line 115
    new-instance v0, Lcom/movieboxpro/android/http/Http$V2ClientProvider;

    new-instance v1, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda12;

    invoke-direct {v1}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda12;-><init>()V

    invoke-direct {v0, v1}, Lcom/movieboxpro/android/http/Http$V2ClientProvider;-><init>(Lcom/movieboxpro/android/http/Http$V2ClientFactory;)V

    sput-object v0, Lcom/movieboxpro/android/http/Http;->MOVIE_CLIENTS:Lcom/movieboxpro/android/http/Http$V2ClientProvider;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 94
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static baseUrlChanged()V
    .locals 2

    .line 133
    sget-object v0, Lcom/movieboxpro/android/http/Http;->SERVICE_LOCK:Ljava/lang/Object;

    monitor-enter v0

    .line 134
    :try_start_0
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getOkHttpClient()Lokhttp3/OkHttpClient;

    move-result-object v1

    invoke-static {v1}, Lcom/movieboxpro/android/http/Http;->createService(Lokhttp3/OkHttpClient;)Lcom/movieboxpro/android/http/APIService;

    move-result-object v1

    sput-object v1, Lcom/movieboxpro/android/http/Http;->service:Lcom/movieboxpro/android/http/APIService;

    .line 135
    monitor-exit v0

    return-void

    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method private static buildV2Clients()Lcom/movieboxpro/android/http/Http$V2Clients;
    .locals 14

    .line 171
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->createTransportBaseClient()Lokhttp3/OkHttpClient;

    move-result-object v0

    .line 172
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->createApiV2RequestInterceptor()Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;

    move-result-object v1

    .line 173
    new-instance v2, Ljava/util/ArrayList;

    invoke-direct {v2}, Ljava/util/ArrayList;-><init>()V

    .line 174
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->createHeaderInterceptor()Lokhttp3/Interceptor;

    move-result-object v3

    invoke-interface {v2, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 175
    new-instance v3, Lcom/movieboxpro/android/http/Http$DynamicTimeoutInterceptor;

    invoke-direct {v3}, Lcom/movieboxpro/android/http/Http$DynamicTimeoutInterceptor;-><init>()V

    invoke-interface {v2, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 176
    sget-boolean v3, Lcom/movieboxpro/android/app/App;->isDebug:Z

    if-eqz v3, :cond_0

    .line 177
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->createLoggingInterceptor()Lokhttp3/Interceptor;

    move-result-object v3

    invoke-interface {v2, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    :cond_0
    const/4 v3, 0x0

    .line 179
    invoke-static {v0, v3, v1, v2}, Lcom/movieboxpro/android/http/Http;->createV2Client(Lokhttp3/OkHttpClient;Lokhttp3/Interceptor;Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;Ljava/util/List;)Lokhttp3/OkHttpClient;

    move-result-object v3

    .line 182
    sget-object v12, Lcom/movieboxpro/android/http/Http;->LOGIN_INVALID_HANDLER:Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;

    .line 183
    new-instance v13, Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;

    .line 184
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getApiSessionManager()Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;

    move-result-object v5

    new-instance v7, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda18;

    invoke-direct {v7}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda18;-><init>()V

    sget-object v8, Lcom/movieboxpro/android/http/v2/TvApiV2Environment;->INSTANCE:Lcom/movieboxpro/android/http/v2/TvApiV2Environment;

    new-instance v9, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda1;

    invoke-direct {v9}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda1;-><init>()V

    sget-object v10, Landroid/os/AsyncTask;->THREAD_POOL_EXECUTOR:Ljava/util/concurrent/Executor;

    new-instance v11, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda2;

    invoke-direct {v11, v12}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda2;-><init>(Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)V

    move-object v4, v13

    move-object v6, v3

    invoke-direct/range {v4 .. v11}, Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;-><init>(Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;Lokhttp3/Call$Factory;Lkotlin/jvm/functions/Function0;Lcom/movieboxpro/android/http/v2/ApiV2Environment;Lkotlin/jvm/functions/Function0;Ljava/util/concurrent/Executor;Lkotlin/jvm/functions/Function0;)V

    .line 192
    new-instance v4, Lcom/movieboxpro/android/http/v2/session/SessionRetryInterceptor;

    sget-object v5, Lcom/movieboxpro/android/http/v2/ApiV2EndpointConfig;->INSTANCE:Lcom/movieboxpro/android/http/v2/ApiV2EndpointConfig;

    .line 194
    invoke-static {v5}, Lj$/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v6, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda3;

    invoke-direct {v6, v5}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda3;-><init>(Lcom/movieboxpro/android/http/v2/ApiV2EndpointConfig;)V

    new-instance v5, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda4;

    invoke-direct {v5, v12}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda4;-><init>(Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)V

    invoke-direct {v4, v13, v6, v5}, Lcom/movieboxpro/android/http/v2/session/SessionRetryInterceptor;-><init>(Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V

    .line 197
    invoke-static {v0, v4, v1, v2}, Lcom/movieboxpro/android/http/Http;->createV2Client(Lokhttp3/OkHttpClient;Lokhttp3/Interceptor;Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;Ljava/util/List;)Lokhttp3/OkHttpClient;

    move-result-object v0

    .line 200
    new-instance v1, Lcom/movieboxpro/android/http/Http$V2Clients;

    invoke-direct {v1, v0, v3, v13, v12}, Lcom/movieboxpro/android/http/Http$V2Clients;-><init>(Lokhttp3/OkHttpClient;Lokhttp3/OkHttpClient;Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)V

    return-object v1
.end method

.method private static createApiV2RequestInterceptor()Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;
    .locals 10

    .line 216
    new-instance v9, Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;

    new-instance v1, Lcom/movieboxpro/android/http/v2/ApiV2RequestBodyReader;

    invoke-direct {v1}, Lcom/movieboxpro/android/http/v2/ApiV2RequestBodyReader;-><init>()V

    new-instance v2, Lcom/movieboxpro/android/http/v2/ApiV2PayloadBuilder;

    invoke-direct {v2}, Lcom/movieboxpro/android/http/v2/ApiV2PayloadBuilder;-><init>()V

    sget-object v3, Lcom/movieboxpro/android/http/v2/TvApiV2Environment;->INSTANCE:Lcom/movieboxpro/android/http/v2/TvApiV2Environment;

    new-instance v4, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda5;

    invoke-direct {v4}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda5;-><init>()V

    new-instance v5, Lcom/movieboxpro/android/http/v2/ApiEncryptV2;

    invoke-direct {v5}, Lcom/movieboxpro/android/http/v2/ApiEncryptV2;-><init>()V

    sget-object v0, Lcom/movieboxpro/android/http/v2/ApiV2EndpointConfig;->INSTANCE:Lcom/movieboxpro/android/http/v2/ApiV2EndpointConfig;

    .line 222
    invoke-static {v0}, Lj$/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v6, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda3;

    invoke-direct {v6, v0}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda3;-><init>(Lcom/movieboxpro/android/http/v2/ApiV2EndpointConfig;)V

    sget-object v0, Lcom/movieboxpro/android/http/v2/NativeApiSecrets;->INSTANCE:Lcom/movieboxpro/android/http/v2/NativeApiSecrets;

    .line 223
    invoke-static {v0}, Lj$/util/Objects;->requireNonNull(Ljava/lang/Object;)Ljava/lang/Object;

    new-instance v7, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda6;

    invoke-direct {v7, v0}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda6;-><init>(Lcom/movieboxpro/android/http/v2/NativeApiSecrets;)V

    new-instance v8, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda7;

    invoke-direct {v8}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda7;-><init>()V

    move-object v0, v9

    invoke-direct/range {v0 .. v8}, Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;-><init>(Lcom/movieboxpro/android/http/v2/ApiV2RequestBodyReader;Lcom/movieboxpro/android/http/v2/ApiV2PayloadBuilder;Lcom/movieboxpro/android/http/v2/ApiV2Environment;Lkotlin/jvm/functions/Function0;Lcom/movieboxpro/android/http/v2/ApiEncryptV2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V

    return-object v9
.end method

.method private static createHeaderInterceptor()Lokhttp3/Interceptor;
    .locals 1

    .line 487
    new-instance v0, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda9;

    invoke-direct {v0}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda9;-><init>()V

    return-object v0
.end method

.method private static createLoggingInterceptor()Lokhttp3/Interceptor;
    .locals 2

    .line 428
    new-instance v0, Lokhttp3/logging/HttpLoggingInterceptor;

    invoke-direct {v0}, Lokhttp3/logging/HttpLoggingInterceptor;-><init>()V

    .line 429
    sget-object v1, Lokhttp3/logging/HttpLoggingInterceptor$Level;->BODY:Lokhttp3/logging/HttpLoggingInterceptor$Level;

    invoke-virtual {v0, v1}, Lokhttp3/logging/HttpLoggingInterceptor;->setLevel(Lokhttp3/logging/HttpLoggingInterceptor$Level;)Lokhttp3/logging/HttpLoggingInterceptor;

    return-object v0
.end method

.method private static createService(Lokhttp3/OkHttpClient;)Lcom/movieboxpro/android/http/APIService;
    .locals 2
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "movieClient"
        }
    .end annotation

    .line 140
    new-instance v0, Lretrofit2/Retrofit$Builder;

    invoke-direct {v0}, Lretrofit2/Retrofit$Builder;-><init>()V

    .line 141
    invoke-virtual {v0, p0}, Lretrofit2/Retrofit$Builder;->client(Lokhttp3/OkHttpClient;)Lretrofit2/Retrofit$Builder;

    move-result-object p0

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v1, Lcom/movieboxpro/android/http/API;->MOVIE_HOST:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string v1, "/"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 142
    invoke-virtual {p0, v0}, Lretrofit2/Retrofit$Builder;->baseUrl(Ljava/lang/String;)Lretrofit2/Retrofit$Builder;

    move-result-object p0

    .line 143
    invoke-static {}, Lretrofit2/adapter/rxjava2/RxJava2CallAdapterFactory;->create()Lretrofit2/adapter/rxjava2/RxJava2CallAdapterFactory;

    move-result-object v0

    invoke-virtual {p0, v0}, Lretrofit2/Retrofit$Builder;->addCallAdapterFactory(Lretrofit2/CallAdapter$Factory;)Lretrofit2/Retrofit$Builder;

    move-result-object p0

    new-instance v0, Lcom/movieboxpro/android/http/converter/ToStringConverterFactory;

    invoke-direct {v0}, Lcom/movieboxpro/android/http/converter/ToStringConverterFactory;-><init>()V

    .line 144
    invoke-virtual {p0, v0}, Lretrofit2/Retrofit$Builder;->addConverterFactory(Lretrofit2/Converter$Factory;)Lretrofit2/Retrofit$Builder;

    move-result-object p0

    .line 145
    invoke-static {}, Lcom/movieboxpro/android/http/converter/FastJsonConverterFactory;->create()Lcom/movieboxpro/android/http/converter/FastJsonConverterFactory;

    move-result-object v0

    invoke-virtual {p0, v0}, Lretrofit2/Retrofit$Builder;->addConverterFactory(Lretrofit2/Converter$Factory;)Lretrofit2/Retrofit$Builder;

    move-result-object p0

    .line 146
    invoke-virtual {p0}, Lretrofit2/Retrofit$Builder;->build()Lretrofit2/Retrofit;

    move-result-object p0

    .line 147
    const-class v0, Lcom/movieboxpro/android/http/APIService;

    invoke-virtual {p0, v0}, Lretrofit2/Retrofit;->create(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object p0

    check-cast p0, Lcom/movieboxpro/android/http/APIService;

    return-object p0
.end method

.method private static createSslClient([B[B)Landroid/util/Pair;
    .locals 6
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0
        }
        names = {
            "keyPem",
            "certPem"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "([B[B)",
            "Landroid/util/Pair<",
            "Ljavax/net/ssl/SSLSocketFactory;",
            "Ljavax/net/ssl/X509TrustManager;",
            ">;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/security/cert/CertificateException;,
            Ljava/io/IOException;,
            Ljava/security/NoSuchAlgorithmException;,
            Ljava/security/KeyStoreException;,
            Ljava/security/UnrecoverableKeyException;,
            Ljava/security/KeyManagementException;,
            Ljava/security/spec/InvalidKeySpecException;
        }
    .end annotation

    .line 388
    const-string v0, "X.509"

    invoke-static {v0}, Ljava/security/cert/CertificateFactory;->getInstance(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;

    move-result-object v0

    .line 390
    new-instance v1, Ljava/lang/String;

    sget-object v2, Ljava/nio/charset/StandardCharsets;->UTF_8:Ljava/nio/charset/Charset;

    invoke-direct {v1, p0, v2}, Ljava/lang/String;-><init>([BLjava/nio/charset/Charset;)V

    .line 391
    const-string p0, "-----BEGIN PRIVATE KEY-----"

    const-string v2, ""

    invoke-virtual {v1, p0, v2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    const-string v1, "-----END PRIVATE KEY-----"

    .line 392
    invoke-virtual {p0, v1, v2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object p0

    const-string v1, "\\s"

    .line 393
    invoke-virtual {p0, v1, v2}, Ljava/lang/String;->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    const/4 v1, 0x0

    .line 395
    invoke-static {p0, v1}, Landroid/util/Base64;->decode(Ljava/lang/String;I)[B

    move-result-object p0

    .line 396
    const-string v2, "RSA"

    invoke-static {v2}, Ljava/security/KeyFactory;->getInstance(Ljava/lang/String;)Ljava/security/KeyFactory;

    move-result-object v2

    .line 397
    new-instance v3, Ljava/security/spec/PKCS8EncodedKeySpec;

    invoke-direct {v3, p0}, Ljava/security/spec/PKCS8EncodedKeySpec;-><init>([B)V

    .line 398
    new-instance p0, Ljava/io/ByteArrayInputStream;

    invoke-direct {p0, p1}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    invoke-virtual {v0, p0}, Ljava/security/cert/CertificateFactory;->generateCertificate(Ljava/io/InputStream;)Ljava/security/cert/Certificate;

    move-result-object p0

    .line 400
    invoke-static {}, Ljava/security/KeyStore;->getDefaultType()Ljava/lang/String;

    move-result-object p1

    invoke-static {p1}, Ljava/security/KeyStore;->getInstance(Ljava/lang/String;)Ljava/security/KeyStore;

    move-result-object p1

    .line 401
    const-string v0, "secret"

    invoke-virtual {v0}, Ljava/lang/String;->toCharArray()[C

    move-result-object v4

    const/4 v5, 0x0

    invoke-virtual {p1, v5, v4}, Ljava/security/KeyStore;->load(Ljava/io/InputStream;[C)V

    .line 404
    invoke-virtual {v2, v3}, Ljava/security/KeyFactory;->generatePrivate(Ljava/security/spec/KeySpec;)Ljava/security/PrivateKey;

    move-result-object v2

    .line 405
    invoke-virtual {v0}, Ljava/lang/String;->toCharArray()[C

    move-result-object v3

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/security/cert/Certificate;

    aput-object p0, v4, v1

    .line 402
    const-string p0, "client"

    invoke-virtual {p1, p0, v2, v3, v4}, Ljava/security/KeyStore;->setKeyEntry(Ljava/lang/String;Ljava/security/Key;[C[Ljava/security/cert/Certificate;)V

    .line 409
    invoke-static {}, Ljavax/net/ssl/TrustManagerFactory;->getDefaultAlgorithm()Ljava/lang/String;

    move-result-object p0

    .line 408
    invoke-static {p0}, Ljavax/net/ssl/TrustManagerFactory;->getInstance(Ljava/lang/String;)Ljavax/net/ssl/TrustManagerFactory;

    move-result-object p0

    .line 410
    move-object v2, v5

    check-cast v2, Ljava/security/KeyStore;

    invoke-virtual {p0, v5}, Ljavax/net/ssl/TrustManagerFactory;->init(Ljava/security/KeyStore;)V

    .line 411
    invoke-virtual {p0}, Ljavax/net/ssl/TrustManagerFactory;->getTrustManagers()[Ljavax/net/ssl/TrustManager;

    move-result-object p0

    .line 414
    invoke-static {}, Ljavax/net/ssl/KeyManagerFactory;->getDefaultAlgorithm()Ljava/lang/String;

    move-result-object v2

    .line 413
    invoke-static {v2}, Ljavax/net/ssl/KeyManagerFactory;->getInstance(Ljava/lang/String;)Ljavax/net/ssl/KeyManagerFactory;

    move-result-object v2

    .line 415
    invoke-virtual {v0}, Ljava/lang/String;->toCharArray()[C

    move-result-object v0

    invoke-virtual {v2, p1, v0}, Ljavax/net/ssl/KeyManagerFactory;->init(Ljava/security/KeyStore;[C)V

    .line 416
    invoke-virtual {v2}, Ljavax/net/ssl/KeyManagerFactory;->getKeyManagers()[Ljavax/net/ssl/KeyManager;

    move-result-object p1

    .line 418
    const-string v0, "TLS"

    invoke-static {v0}, Ljavax/net/ssl/SSLContext;->getInstance(Ljava/lang/String;)Ljavax/net/ssl/SSLContext;

    move-result-object v0

    .line 419
    new-instance v2, Ljava/security/SecureRandom;

    invoke-direct {v2}, Ljava/security/SecureRandom;-><init>()V

    invoke-virtual {v0, p1, p0, v2}, Ljavax/net/ssl/SSLContext;->init([Ljavax/net/ssl/KeyManager;[Ljavax/net/ssl/TrustManager;Ljava/security/SecureRandom;)V

    .line 420
    new-instance p1, Landroid/util/Pair;

    invoke-virtual {v0}, Ljavax/net/ssl/SSLContext;->getSocketFactory()Ljavax/net/ssl/SSLSocketFactory;

    move-result-object v0

    aget-object p0, p0, v1

    check-cast p0, Ljavax/net/ssl/X509TrustManager;

    invoke-direct {p1, v0, p0}, Landroid/util/Pair;-><init>(Ljava/lang/Object;Ljava/lang/Object;)V

    return-object p1
.end method

.method private static createTransportBaseClient()Lokhttp3/OkHttpClient;
    .locals 3

    .line 204
    new-instance v0, Lokhttp3/OkHttpClient$Builder;

    invoke-direct {v0}, Lokhttp3/OkHttpClient$Builder;-><init>()V

    .line 205
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->setUpClient()Landroid/util/Pair;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 207
    iget-object v2, v1, Landroid/util/Pair;->first:Ljava/lang/Object;

    check-cast v2, Ljavax/net/ssl/SSLSocketFactory;

    iget-object v1, v1, Landroid/util/Pair;->second:Ljava/lang/Object;

    check-cast v1, Ljavax/net/ssl/X509TrustManager;

    invoke-virtual {v0, v2, v1}, Lokhttp3/OkHttpClient$Builder;->sslSocketFactory(Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/X509TrustManager;)Lokhttp3/OkHttpClient$Builder;

    .line 209
    :cond_0
    invoke-static {v0}, Lcom/movieboxpro/android/http/Http;->setCookieJar(Lokhttp3/OkHttpClient$Builder;)V

    .line 210
    invoke-static {v0}, Lcom/movieboxpro/android/http/Http;->setCacheDirectory(Lokhttp3/OkHttpClient$Builder;)V

    .line 211
    invoke-static {v0}, Lcom/movieboxpro/android/http/Http;->setTimeout(Lokhttp3/OkHttpClient$Builder;)V

    .line 212
    invoke-virtual {v0}, Lokhttp3/OkHttpClient$Builder;->build()Lokhttp3/OkHttpClient;

    move-result-object v0

    return-object v0
.end method

.method private static createV2Client(Lokhttp3/OkHttpClient;Lokhttp3/Interceptor;Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;Ljava/util/List;)Lokhttp3/OkHttpClient;
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0,
            0x0
        }
        names = {
            "transportBase",
            "leadingInterceptor",
            "requestInterceptor",
            "transportInterceptors"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp3/OkHttpClient;",
            "Lokhttp3/Interceptor;",
            "Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;",
            "Ljava/util/List<",
            "Lokhttp3/Interceptor;",
            ">;)",
            "Lokhttp3/OkHttpClient;"
        }
    .end annotation

    .line 295
    invoke-virtual {p0}, Lokhttp3/OkHttpClient;->newBuilder()Lokhttp3/OkHttpClient$Builder;

    move-result-object p0

    if-eqz p1, :cond_0

    .line 297
    invoke-virtual {p0, p1}, Lokhttp3/OkHttpClient$Builder;->addInterceptor(Lokhttp3/Interceptor;)Lokhttp3/OkHttpClient$Builder;

    .line 299
    :cond_0
    invoke-virtual {p0, p2}, Lokhttp3/OkHttpClient$Builder;->addInterceptor(Lokhttp3/Interceptor;)Lokhttp3/OkHttpClient$Builder;

    .line 300
    invoke-interface {p3}, Ljava/util/List;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result p2

    if-eqz p2, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Lokhttp3/Interceptor;

    .line 301
    invoke-virtual {p0, p2}, Lokhttp3/OkHttpClient$Builder;->addInterceptor(Lokhttp3/Interceptor;)Lokhttp3/OkHttpClient$Builder;

    goto :goto_0

    .line 303
    :cond_1
    invoke-virtual {p0}, Lokhttp3/OkHttpClient$Builder;->build()Lokhttp3/OkHttpClient;

    move-result-object p0

    return-object p0
.end method

.method static createV2Clients(Lokhttp3/OkHttpClient;Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;Ljava/util/List;Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)Lcom/movieboxpro/android/http/Http$V2Clients;
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0,
            0x0,
            0x0
        }
        names = {
            "transportBase",
            "requestInterceptor",
            "transportInterceptors",
            "coordinator",
            "invalidHandler"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp3/OkHttpClient;",
            "Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;",
            "Ljava/util/List<",
            "Lokhttp3/Interceptor;",
            ">;",
            "Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;",
            "Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;",
            ")",
            "Lcom/movieboxpro/android/http/Http$V2Clients;"
        }
    .end annotation

    .line 276
    new-instance v0, Lcom/movieboxpro/android/http/v2/session/SessionRetryInterceptor;

    new-instance v1, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda13;

    invoke-direct {v1}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda13;-><init>()V

    new-instance v2, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda14;

    invoke-direct {v2, p4}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda14;-><init>(Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)V

    invoke-direct {v0, p3, v1, v2}, Lcom/movieboxpro/android/http/v2/session/SessionRetryInterceptor;-><init>(Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V

    .line 281
    new-instance v1, Lcom/movieboxpro/android/http/Http$V2Clients;

    .line 282
    invoke-static {p0, v0, p1, p2}, Lcom/movieboxpro/android/http/Http;->createV2Client(Lokhttp3/OkHttpClient;Lokhttp3/Interceptor;Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;Ljava/util/List;)Lokhttp3/OkHttpClient;

    move-result-object v0

    const/4 v2, 0x0

    .line 283
    invoke-static {p0, v2, p1, p2}, Lcom/movieboxpro/android/http/Http;->createV2Client(Lokhttp3/OkHttpClient;Lokhttp3/Interceptor;Lcom/movieboxpro/android/http/v2/ApiV2RequestInterceptor;Ljava/util/List;)Lokhttp3/OkHttpClient;

    move-result-object p0

    invoke-direct {v1, v0, p0, p3, p4}, Lcom/movieboxpro/android/http/Http$V2Clients;-><init>(Lokhttp3/OkHttpClient;Lokhttp3/OkHttpClient;Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)V

    return-object v1
.end method

.method public static get(Ljava/lang/String;Ljava/util/Map;Lcom/movieboxpro/android/http/RequestCallback;)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x10
        }
        names = {
            "url",
            "params",
            "callback"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;",
            "Lcom/movieboxpro/android/http/RequestCallback<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 507
    new-instance v0, Lokhttp3/OkHttpClient$Builder;

    invoke-direct {v0}, Lokhttp3/OkHttpClient$Builder;-><init>()V

    .line 508
    sget-boolean v1, Lcom/movieboxpro/android/app/App;->isDebug:Z

    if-eqz v1, :cond_0

    .line 510
    invoke-static {v0}, Lcom/movieboxpro/android/http/Http;->setLoggingInterceptor(Lokhttp3/OkHttpClient$Builder;)V

    .line 512
    :cond_0
    invoke-virtual {v0}, Lokhttp3/OkHttpClient$Builder;->build()Lokhttp3/OkHttpClient;

    move-result-object v0

    .line 513
    invoke-static {p0}, Lokhttp3/HttpUrl;->parse(Ljava/lang/String;)Lokhttp3/HttpUrl;

    move-result-object p0

    if-eqz p0, :cond_2

    .line 515
    invoke-virtual {p0}, Lokhttp3/HttpUrl;->newBuilder()Lokhttp3/HttpUrl$Builder;

    move-result-object p0

    if-eqz p1, :cond_1

    .line 516
    invoke-interface {p1}, Ljava/util/Map;->isEmpty()Z

    move-result v1

    if-nez v1, :cond_1

    .line 517
    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v1

    if-eqz v1, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/util/Map$Entry;

    .line 518
    invoke-interface {v1}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 519
    invoke-interface {v1}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    .line 520
    invoke-virtual {p0, v2, v1}, Lokhttp3/HttpUrl$Builder;->addQueryParameter(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/HttpUrl$Builder;

    goto :goto_0

    .line 523
    :cond_1
    invoke-virtual {p0}, Lokhttp3/HttpUrl$Builder;->build()Lokhttp3/HttpUrl;

    move-result-object p0

    .line 524
    new-instance p1, Lokhttp3/Request$Builder;

    invoke-direct {p1}, Lokhttp3/Request$Builder;-><init>()V

    .line 525
    invoke-virtual {p1, p0}, Lokhttp3/Request$Builder;->url(Lokhttp3/HttpUrl;)Lokhttp3/Request$Builder;

    move-result-object p0

    sget-object p1, Lokhttp3/CacheControl;->FORCE_NETWORK:Lokhttp3/CacheControl;

    .line 526
    invoke-virtual {p0, p1}, Lokhttp3/Request$Builder;->cacheControl(Lokhttp3/CacheControl;)Lokhttp3/Request$Builder;

    move-result-object p0

    .line 527
    invoke-virtual {p0}, Lokhttp3/Request$Builder;->get()Lokhttp3/Request$Builder;

    move-result-object p0

    .line 528
    invoke-virtual {p0}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object p0

    .line 529
    invoke-virtual {v0, p0}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object p0

    .line 530
    new-instance p1, Lcom/movieboxpro/android/http/Http$1;

    invoke-direct {p1, p2}, Lcom/movieboxpro/android/http/Http$1;-><init>(Lcom/movieboxpro/android/http/RequestCallback;)V

    invoke-interface {p0, p1}, Lokhttp3/Call;->enqueue(Lokhttp3/Callback;)V

    goto :goto_1

    :cond_2
    if-eqz p2, :cond_3

    .line 585
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->mainHandler()Landroid/os/Handler;

    move-result-object p0

    new-instance p1, Lcom/movieboxpro/android/http/Http$2;

    invoke-direct {p1, p2}, Lcom/movieboxpro/android/http/Http$2;-><init>(Lcom/movieboxpro/android/http/RequestCallback;)V

    invoke-virtual {p0, p1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_3
    :goto_1
    return-void
.end method

.method public static getApiSessionManager()Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;
    .locals 4

    .line 256
    sget-object v0, Lcom/movieboxpro/android/http/Http;->apiSessionManager:Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;

    if-nez v0, :cond_1

    .line 258
    sget-object v1, Lcom/movieboxpro/android/http/Http;->SESSION_STORE_LOCK:Ljava/lang/Object;

    monitor-enter v1

    .line 259
    :try_start_0
    sget-object v0, Lcom/movieboxpro/android/http/Http;->apiSessionManager:Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;

    if-nez v0, :cond_0

    .line 261
    new-instance v0, Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;

    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getApiSessionStore()Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;

    move-result-object v2

    new-instance v3, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda8;

    invoke-direct {v3}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda8;-><init>()V

    invoke-direct {v0, v2, v3}, Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;-><init>(Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;Lcom/movieboxpro/android/http/v2/session/UserIdentityStore;)V

    .line 262
    sput-object v0, Lcom/movieboxpro/android/http/Http;->apiSessionManager:Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;

    .line 264
    :cond_0
    monitor-exit v1

    goto :goto_0

    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_1
    :goto_0
    return-object v0
.end method

.method private static getApiSessionStore()Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;
    .locals 8

    .line 229
    sget-object v0, Lcom/movieboxpro/android/http/Http;->apiSessionStore:Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;

    if-nez v0, :cond_1

    .line 231
    sget-object v1, Lcom/movieboxpro/android/http/Http;->SESSION_STORE_LOCK:Ljava/lang/Object;

    monitor-enter v1

    .line 232
    :try_start_0
    sget-object v0, Lcom/movieboxpro/android/http/Http;->apiSessionStore:Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;

    if-nez v0, :cond_0

    .line 234
    new-instance v0, Lcom/movieboxpro/android/http/v2/session/AndroidSessionKeyCipher;

    .line 235
    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v2

    invoke-direct {v0, v2}, Lcom/movieboxpro/android/http/v2/session/AndroidSessionKeyCipher;-><init>(Landroid/content/Context;)V

    .line 236
    new-instance v2, Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;

    new-instance v3, Lcom/movieboxpro/android/http/v2/session/SharedPrefsSessionPreferences;

    .line 237
    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v4

    invoke-direct {v3, v4}, Lcom/movieboxpro/android/http/v2/session/SharedPrefsSessionPreferences;-><init>(Landroid/content/Context;)V

    new-instance v4, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;

    .line 240
    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v5

    invoke-static {v5}, Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;->fromContext(Landroid/content/Context;)Lcom/movieboxpro/android/http/v2/session/SoftwareSessionKeyCipher;

    move-result-object v5

    .line 241
    new-instance v6, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda16;

    invoke-direct {v6, v0}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda16;-><init>(Lcom/movieboxpro/android/http/v2/session/AndroidSessionKeyCipher;)V

    new-instance v7, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda17;

    invoke-direct {v7}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda17;-><init>()V

    invoke-direct {v4, v0, v5, v6, v7}, Lcom/movieboxpro/android/http/v2/session/FallbackSessionKeyCipher;-><init>(Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;Ljava/lang/Runnable;Lcom/movieboxpro/android/http/v2/session/SessionCipherFailureHandler;)V

    invoke-direct {v2, v3, v4}, Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;-><init>(Lcom/movieboxpro/android/http/v2/session/SessionPreferences;Lcom/movieboxpro/android/http/v2/session/SessionKeyCipher;)V

    .line 248
    sput-object v2, Lcom/movieboxpro/android/http/Http;->apiSessionStore:Lcom/movieboxpro/android/http/v2/session/ApiSessionStore;

    move-object v0, v2

    .line 250
    :cond_0
    monitor-exit v1

    goto :goto_0

    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_1
    :goto_0
    return-object v0
.end method

.method public static getLoginInvalidHandler()Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;
    .locals 1

    .line 163
    sget-object v0, Lcom/movieboxpro/android/http/Http;->MOVIE_CLIENTS:Lcom/movieboxpro/android/http/Http$V2ClientProvider;

    invoke-virtual {v0}, Lcom/movieboxpro/android/http/Http$V2ClientProvider;->invalidHandler()Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;

    move-result-object v0

    return-object v0
.end method

.method public static getOkHttpClient()Lokhttp3/OkHttpClient;
    .locals 1

    .line 151
    sget-object v0, Lcom/movieboxpro/android/http/Http;->MOVIE_CLIENTS:Lcom/movieboxpro/android/http/Http$V2ClientProvider;

    invoke-virtual {v0}, Lcom/movieboxpro/android/http/Http$V2ClientProvider;->main()Lokhttp3/OkHttpClient;

    move-result-object v0

    return-object v0
.end method

.method public static getRefreshOkHttpClient()Lokhttp3/OkHttpClient;
    .locals 1

    .line 155
    sget-object v0, Lcom/movieboxpro/android/http/Http;->MOVIE_CLIENTS:Lcom/movieboxpro/android/http/Http$V2ClientProvider;

    invoke-virtual {v0}, Lcom/movieboxpro/android/http/Http$V2ClientProvider;->refresh()Lokhttp3/OkHttpClient;

    move-result-object v0

    return-object v0
.end method

.method public static getService()Lcom/movieboxpro/android/http/APIService;
    .locals 2

    .line 119
    sget-object v0, Lcom/movieboxpro/android/http/Http;->service:Lcom/movieboxpro/android/http/APIService;

    if-nez v0, :cond_1

    .line 121
    sget-object v1, Lcom/movieboxpro/android/http/Http;->SERVICE_LOCK:Ljava/lang/Object;

    monitor-enter v1

    .line 122
    :try_start_0
    sget-object v0, Lcom/movieboxpro/android/http/Http;->service:Lcom/movieboxpro/android/http/APIService;

    if-nez v0, :cond_0

    .line 124
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getOkHttpClient()Lokhttp3/OkHttpClient;

    move-result-object v0

    invoke-static {v0}, Lcom/movieboxpro/android/http/Http;->createService(Lokhttp3/OkHttpClient;)Lcom/movieboxpro/android/http/APIService;

    move-result-object v0

    .line 125
    sput-object v0, Lcom/movieboxpro/android/http/Http;->service:Lcom/movieboxpro/android/http/APIService;

    .line 127
    :cond_0
    monitor-exit v1

    goto :goto_0

    :catchall_0
    move-exception v0

    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v0

    :cond_1
    :goto_0
    return-object v0
.end method

.method public static getSessionRefreshCoordinator()Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;
    .locals 1

    .line 159
    sget-object v0, Lcom/movieboxpro/android/http/Http;->MOVIE_CLIENTS:Lcom/movieboxpro/android/http/Http$V2ClientProvider;

    invoke-virtual {v0}, Lcom/movieboxpro/android/http/Http$V2ClientProvider;->coordinator()Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;

    move-result-object v0

    return-object v0
.end method

.method static synthetic lambda$buildV2Clients$2()Ljava/lang/String;
    .locals 1

    .line 186
    const-string v0, "User_token"

    invoke-static {v0}, Lcom/movieboxpro/android/http/v2/ApiV2EndpointConfig;->moduleUrl(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method static synthetic lambda$buildV2Clients$3()Ljava/lang/Long;
    .locals 4

    .line 188
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v0

    const-wide/16 v2, 0x3e8

    div-long/2addr v0, v2

    invoke-static {v0, v1}, Ljava/lang/Long;->valueOf(J)Ljava/lang/Long;

    move-result-object v0

    return-object v0
.end method

.method static synthetic lambda$buildV2Clients$4(Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)Lkotlin/Unit;
    .locals 0

    .line 190
    invoke-virtual {p0}, Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;->handle()V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method static synthetic lambda$buildV2Clients$5(Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)Lkotlin/Unit;
    .locals 0

    .line 195
    invoke-virtual {p0}, Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;->handle()V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method static synthetic lambda$createApiV2RequestInterceptor$6()Lcom/movieboxpro/android/http/v2/session/SessionCredentials;
    .locals 1

    .line 220
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getApiSessionManager()Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;

    move-result-object v0

    invoke-virtual {v0}, Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;->current()Lcom/movieboxpro/android/http/v2/session/SessionCredentials;

    move-result-object v0

    return-object v0
.end method

.method static synthetic lambda$createApiV2RequestInterceptor$7()Lcom/movieboxpro/android/http/v2/session/ApiV2RequestAttempt;
    .locals 2

    .line 224
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getApiSessionManager()Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;

    move-result-object v0

    sget-object v1, Lcom/movieboxpro/android/http/v2/TvApiV2Environment;->INSTANCE:Lcom/movieboxpro/android/http/v2/TvApiV2Environment;

    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/http/v2/session/ApiSessionManager;->captureRequestAttempt(Lcom/movieboxpro/android/http/v2/ApiV2Environment;)Lcom/movieboxpro/android/http/v2/session/ApiV2RequestAttempt;

    move-result-object v0

    return-object v0
.end method

.method static synthetic lambda$createHeaderInterceptor$12(Lokhttp3/Interceptor$Chain;)Lokhttp3/Response;
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 488
    invoke-interface {p0}, Lokhttp3/Interceptor$Chain;->request()Lokhttp3/Request;

    move-result-object v0

    .line 489
    invoke-virtual {v0}, Lokhttp3/Request;->newBuilder()Lokhttp3/Request$Builder;

    move-result-object v1

    const-string v2, "Platform"

    const-string v3, "android_tv"

    .line 490
    invoke-virtual {v1, v2, v3}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object v1

    const-string v2, "Accept"

    const-string v3, "text/plain; charset=utf-8"

    .line 491
    invoke-virtual {v1, v2, v3}, Lokhttp3/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object v1

    .line 492
    invoke-virtual {v0}, Lokhttp3/Request;->method()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0}, Lokhttp3/Request;->body()Lokhttp3/RequestBody;

    move-result-object v0

    invoke-virtual {v1, v2, v0}, Lokhttp3/Request$Builder;->method(Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;

    move-result-object v0

    .line 493
    invoke-virtual {v0}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object v0

    .line 494
    invoke-interface {p0, v0}, Lokhttp3/Interceptor$Chain;->proceed(Lokhttp3/Request;)Lokhttp3/Response;

    move-result-object p0

    return-object p0
.end method

.method static synthetic lambda$createV2Clients$10(Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;)Lkotlin/Unit;
    .locals 0

    .line 279
    invoke-virtual {p0}, Lcom/movieboxpro/android/http/v2/session/LoginInvalidHandler;->handle()V

    sget-object p0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object p0
.end method

.method static synthetic lambda$createV2Clients$9(Lokhttp3/HttpUrl;)Ljava/lang/Boolean;
    .locals 0

    const/4 p0, 0x0

    .line 278
    invoke-static {p0}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method

.method static synthetic lambda$getApiSessionStore$8(Ljava/lang/Throwable;)V
    .locals 2

    .line 242
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v1, "Keystore session wrap failed: "

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    .line 244
    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object p0

    invoke-virtual {v0, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    .line 242
    const-string v0, "ApiSession"

    invoke-static {v0, p0}, Lcom/movieboxpro/android/utils/MLog;->e(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic lambda$setUpClient$11([B[B)Landroid/util/Pair;
    .locals 0

    .line 368
    :try_start_0
    invoke-static {p0, p1}, Lcom/movieboxpro/android/http/Http;->createSslClient([B[B)Landroid/util/Pair;

    move-result-object p0
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object p0

    :catch_0
    move-exception p0

    .line 370
    new-instance p1, Ljava/lang/RuntimeException;

    invoke-direct {p1, p0}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw p1
.end method

.method static synthetic lambda$static$0()Lkotlin/Unit;
    .locals 1

    .line 111
    invoke-static {}, Lcom/movieboxpro/android/app/App;->logout()V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method

.method static synthetic lambda$static$1()Lkotlin/Unit;
    .locals 1

    .line 112
    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v0

    invoke-static {v0}, Lcom/movieboxpro/android/view/fragment/account/SessionInvalidRouter;->startAfterForcedLogout(Landroid/content/Context;)V

    sget-object v0, Lkotlin/Unit;->INSTANCE:Lkotlin/Unit;

    return-object v0
.end method

.method private static declared-synchronized mainHandler()Landroid/os/Handler;
    .locals 3

    const-class v0, Lcom/movieboxpro/android/http/Http;

    monitor-enter v0

    .line 99
    :try_start_0
    sget-object v1, Lcom/movieboxpro/android/http/Http;->mHandler:Landroid/os/Handler;

    if-nez v1, :cond_0

    .line 100
    new-instance v1, Landroid/os/Handler;

    invoke-static {}, Landroid/os/Looper;->getMainLooper()Landroid/os/Looper;

    move-result-object v2

    invoke-direct {v1, v2}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    sput-object v1, Lcom/movieboxpro/android/http/Http;->mHandler:Landroid/os/Handler;

    .line 102
    :cond_0
    sget-object v1, Lcom/movieboxpro/android/http/Http;->mHandler:Landroid/os/Handler;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    monitor-exit v0

    return-object v1

    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method public static post(Ljava/lang/String;Ljava/util/Map;Lcom/movieboxpro/android/http/RequestCallback;)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x10
        }
        names = {
            "url",
            "params",
            "callback"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;",
            "Lcom/movieboxpro/android/http/RequestCallback<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 603
    new-instance v0, Lokhttp3/OkHttpClient$Builder;

    invoke-direct {v0}, Lokhttp3/OkHttpClient$Builder;-><init>()V

    .line 604
    sget-boolean v1, Lcom/movieboxpro/android/app/App;->isDebug:Z

    if-eqz v1, :cond_0

    .line 606
    invoke-static {v0}, Lcom/movieboxpro/android/http/Http;->setLoggingInterceptor(Lokhttp3/OkHttpClient$Builder;)V

    .line 608
    :cond_0
    invoke-virtual {v0}, Lokhttp3/OkHttpClient$Builder;->build()Lokhttp3/OkHttpClient;

    move-result-object v0

    .line 610
    new-instance v1, Lokhttp3/FormBody$Builder;

    invoke-direct {v1}, Lokhttp3/FormBody$Builder;-><init>()V

    if-eqz p1, :cond_1

    .line 611
    invoke-interface {p1}, Ljava/util/Map;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_1

    .line 612
    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 613
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 614
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 615
    invoke-virtual {v1, v3, v2}, Lokhttp3/FormBody$Builder;->add(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/FormBody$Builder;

    goto :goto_0

    .line 618
    :cond_1
    invoke-virtual {v1}, Lokhttp3/FormBody$Builder;->build()Lokhttp3/FormBody;

    move-result-object p1

    .line 620
    new-instance v1, Lokhttp3/Request$Builder;

    invoke-direct {v1}, Lokhttp3/Request$Builder;-><init>()V

    sget-object v2, Lokhttp3/CacheControl;->FORCE_NETWORK:Lokhttp3/CacheControl;

    .line 621
    invoke-virtual {v1, v2}, Lokhttp3/Request$Builder;->cacheControl(Lokhttp3/CacheControl;)Lokhttp3/Request$Builder;

    move-result-object v1

    .line 622
    invoke-virtual {v1, p0}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p0

    .line 623
    invoke-virtual {p0, p1}, Lokhttp3/Request$Builder;->post(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;

    move-result-object p0

    .line 624
    invoke-virtual {p0}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object p0

    .line 625
    invoke-virtual {v0, p0}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object p0

    .line 627
    new-instance p1, Lcom/movieboxpro/android/http/Http$3;

    invoke-direct {p1, p2}, Lcom/movieboxpro/android/http/Http$3;-><init>(Lcom/movieboxpro/android/http/RequestCallback;)V

    invoke-interface {p0, p1}, Lokhttp3/Call;->enqueue(Lokhttp3/Callback;)V

    return-void
.end method

.method public static refreshApiSessionIfExpiringAsync()V
    .locals 1

    .line 167
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->getSessionRefreshCoordinator()Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;

    move-result-object v0

    invoke-virtual {v0}, Lcom/movieboxpro/android/http/v2/session/SessionRefreshCoordinator;->refreshIfExpiringAsync()V

    return-void
.end method

.method private static setCacheDirectory(Lokhttp3/OkHttpClient$Builder;)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "builder"
        }
    .end annotation

    .line 463
    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 464
    invoke-virtual {v0}, Landroid/content/Context;->getCacheDir()Ljava/io/File;

    move-result-object v0

    invoke-virtual {v0}, Ljava/io/File;->getPath()Ljava/lang/String;

    move-result-object v0

    .line 465
    new-instance v1, Ljava/io/File;

    const-string v2, "responses"

    invoke-direct {v1, v0, v2}, Ljava/io/File;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    .line 466
    new-instance v0, Lokhttp3/Cache;

    const-wide/32 v2, 0xa00000

    invoke-direct {v0, v1, v2, v3}, Lokhttp3/Cache;-><init>(Ljava/io/File;J)V

    .line 467
    invoke-virtual {p0, v0}, Lokhttp3/OkHttpClient$Builder;->cache(Lokhttp3/Cache;)Lokhttp3/OkHttpClient$Builder;

    return-void
.end method

.method private static setCookieJar(Lokhttp3/OkHttpClient$Builder;)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "builder"
        }
    .end annotation

    .line 476
    invoke-static {}, Lcom/movieboxpro/android/app/App;->getContext()Landroid/content/Context;

    move-result-object v0

    .line 477
    new-instance v1, Lcom/franmontiel/persistentcookiejar/PersistentCookieJar;

    new-instance v2, Lcom/franmontiel/persistentcookiejar/cache/SetCookieCache;

    invoke-direct {v2}, Lcom/franmontiel/persistentcookiejar/cache/SetCookieCache;-><init>()V

    new-instance v3, Lcom/franmontiel/persistentcookiejar/persistence/SharedPrefsCookiePersistor;

    invoke-direct {v3, v0}, Lcom/franmontiel/persistentcookiejar/persistence/SharedPrefsCookiePersistor;-><init>(Landroid/content/Context;)V

    invoke-direct {v1, v2, v3}, Lcom/franmontiel/persistentcookiejar/PersistentCookieJar;-><init>(Lcom/franmontiel/persistentcookiejar/cache/CookieCache;Lcom/franmontiel/persistentcookiejar/persistence/CookiePersistor;)V

    .line 480
    invoke-virtual {p0, v1}, Lokhttp3/OkHttpClient$Builder;->cookieJar(Lokhttp3/CookieJar;)Lokhttp3/OkHttpClient$Builder;

    return-void
.end method

.method private static setLoggingInterceptor(Lokhttp3/OkHttpClient$Builder;)V
    .locals 1
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "builder"
        }
    .end annotation

    if-eqz p0, :cond_0

    .line 435
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->createLoggingInterceptor()Lokhttp3/Interceptor;

    move-result-object v0

    invoke-virtual {p0, v0}, Lokhttp3/OkHttpClient$Builder;->addInterceptor(Lokhttp3/Interceptor;)Lokhttp3/OkHttpClient$Builder;

    :cond_0
    return-void
.end method

.method private static setTimeout(Lokhttp3/OkHttpClient$Builder;)V
    .locals 3
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0
        }
        names = {
            "builder"
        }
    .end annotation

    .line 446
    sget-object v0, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    const-wide/16 v1, 0x28

    invoke-virtual {p0, v1, v2, v0}, Lokhttp3/OkHttpClient$Builder;->connectTimeout(JLjava/util/concurrent/TimeUnit;)Lokhttp3/OkHttpClient$Builder;

    .line 447
    sget-object v0, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {p0, v1, v2, v0}, Lokhttp3/OkHttpClient$Builder;->readTimeout(JLjava/util/concurrent/TimeUnit;)Lokhttp3/OkHttpClient$Builder;

    .line 448
    sget-object v0, Ljava/util/concurrent/TimeUnit;->SECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {p0, v1, v2, v0}, Lokhttp3/OkHttpClient$Builder;->writeTimeout(JLjava/util/concurrent/TimeUnit;)Lokhttp3/OkHttpClient$Builder;

    const/4 v0, 0x1

    .line 450
    invoke-virtual {p0, v0}, Lokhttp3/OkHttpClient$Builder;->retryOnConnectionFailure(Z)Lokhttp3/OkHttpClient$Builder;

    .line 452
    invoke-virtual {p0, v0}, Lokhttp3/OkHttpClient$Builder;->followRedirects(Z)Lokhttp3/OkHttpClient$Builder;

    .line 453
    invoke-virtual {p0, v0}, Lokhttp3/OkHttpClient$Builder;->followSslRedirects(Z)Lokhttp3/OkHttpClient$Builder;

    return-void
.end method

.method private static setUpClient()Landroid/util/Pair;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Landroid/util/Pair<",
            "Ljavax/net/ssl/SSLSocketFactory;",
            "Ljavax/net/ssl/X509TrustManager;",
            ">;"
        }
    .end annotation

    .line 366
    :try_start_0
    sget-object v0, Lcom/movieboxpro/android/http/v2/NativeApiSecrets;->INSTANCE:Lcom/movieboxpro/android/http/v2/NativeApiSecrets;

    new-instance v1, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda15;

    invoke-direct {v1}, Lcom/movieboxpro/android/http/Http$$ExternalSyntheticLambda15;-><init>()V

    invoke-virtual {v0, v1}, Lcom/movieboxpro/android/http/v2/NativeApiSecrets;->withClientTlsPem(Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/util/Pair;
    :try_end_0
    .catch Ljava/lang/RuntimeException; {:try_start_0 .. :try_end_0} :catch_0

    return-object v0

    :catch_0
    move-exception v0

    .line 374
    invoke-virtual {v0}, Ljava/lang/RuntimeException;->getCause()Ljava/lang/Throwable;

    move-result-object v1

    if-eqz v1, :cond_0

    .line 376
    invoke-virtual {v1}, Ljava/lang/Throwable;->printStackTrace()V

    goto :goto_0

    .line 378
    :cond_0
    invoke-virtual {v0}, Ljava/lang/RuntimeException;->printStackTrace()V

    :goto_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public static uploadAsync(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;Lcom/movieboxpro/android/http/RequestCallback;)V
    .locals 4
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0,
            0x10
        }
        names = {
            "url",
            "params",
            "fileParams",
            "callback"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;",
            "Lcom/movieboxpro/android/http/RequestCallback<",
            "Ljava/lang/String;",
            ">;)V"
        }
    .end annotation

    .line 692
    new-instance v0, Lokhttp3/OkHttpClient$Builder;

    invoke-direct {v0}, Lokhttp3/OkHttpClient$Builder;-><init>()V

    .line 693
    sget-boolean v1, Lcom/movieboxpro/android/app/App;->isDebug:Z

    if-eqz v1, :cond_0

    .line 695
    invoke-static {v0}, Lcom/movieboxpro/android/http/Http;->setLoggingInterceptor(Lokhttp3/OkHttpClient$Builder;)V

    .line 697
    :cond_0
    invoke-virtual {v0}, Lokhttp3/OkHttpClient$Builder;->build()Lokhttp3/OkHttpClient;

    move-result-object v0

    .line 699
    new-instance v1, Lokhttp3/MultipartBody$Builder;

    invoke-direct {v1}, Lokhttp3/MultipartBody$Builder;-><init>()V

    if-eqz p1, :cond_1

    .line 701
    invoke-interface {p1}, Ljava/util/Map;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_1

    .line 702
    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 703
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 704
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 705
    invoke-virtual {v1, v3, v2}, Lokhttp3/MultipartBody$Builder;->addFormDataPart(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;

    goto :goto_0

    :cond_1
    if-eqz p2, :cond_8

    .line 709
    invoke-interface {p2}, Ljava/util/Map;->isEmpty()Z

    move-result p1

    if-nez p1, :cond_8

    .line 711
    const-string p1, "filePath"

    invoke-interface {p2, p1}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 712
    invoke-interface {p2, p1}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p1

    check-cast p1, Ljava/lang/String;

    goto :goto_1

    :cond_2
    const/4 p1, 0x0

    :goto_1
    if-eqz p1, :cond_6

    .line 717
    const-string v2, "mimeType"

    invoke-interface {p2, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 718
    invoke-interface {p2, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    goto :goto_2

    .line 717
    :cond_3
    const-string p2, "image/*"

    .line 721
    :goto_2
    new-instance v2, Ljava/io/File;

    invoke-direct {v2, p1}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 722
    invoke-virtual {v2}, Ljava/io/File;->exists()Z

    move-result p1

    if-eqz p1, :cond_4

    invoke-virtual {v2}, Ljava/io/File;->isFile()Z

    move-result p1

    if-eqz p1, :cond_4

    .line 723
    invoke-virtual {v2}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p1

    .line 724
    invoke-static {p2}, Lokhttp3/MediaType;->parse(Ljava/lang/String;)Lokhttp3/MediaType;

    move-result-object p2

    invoke-static {p2, v2}, Lokhttp3/MultipartBody;->create(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;

    move-result-object p2

    .line 725
    const-string v2, "Filedata"

    invoke-virtual {v1, v2, p1, p2}, Lokhttp3/MultipartBody$Builder;->addFormDataPart(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;

    .line 763
    invoke-virtual {v1}, Lokhttp3/MultipartBody$Builder;->build()Lokhttp3/MultipartBody;

    move-result-object p1

    .line 765
    new-instance p2, Lokhttp3/Request$Builder;

    invoke-direct {p2}, Lokhttp3/Request$Builder;-><init>()V

    sget-object v1, Lokhttp3/CacheControl;->FORCE_NETWORK:Lokhttp3/CacheControl;

    .line 766
    invoke-virtual {p2, v1}, Lokhttp3/Request$Builder;->cacheControl(Lokhttp3/CacheControl;)Lokhttp3/Request$Builder;

    move-result-object p2

    .line 767
    invoke-virtual {p2, p0}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p0

    .line 768
    invoke-virtual {p0, p1}, Lokhttp3/Request$Builder;->post(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;

    move-result-object p0

    .line 769
    invoke-virtual {p0}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object p0

    .line 770
    invoke-virtual {v0, p0}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object p0

    .line 772
    new-instance p1, Lcom/movieboxpro/android/http/Http$7;

    invoke-direct {p1, p3}, Lcom/movieboxpro/android/http/Http$7;-><init>(Lcom/movieboxpro/android/http/RequestCallback;)V

    invoke-interface {p0, p1}, Lokhttp3/Call;->enqueue(Lokhttp3/Callback;)V

    return-void

    :cond_4
    if-eqz p3, :cond_5

    .line 729
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->mainHandler()Landroid/os/Handler;

    move-result-object p0

    new-instance p1, Lcom/movieboxpro/android/http/Http$4;

    invoke-direct {p1, p3}, Lcom/movieboxpro/android/http/Http$4;-><init>(Lcom/movieboxpro/android/http/RequestCallback;)V

    invoke-virtual {p0, p1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_5
    return-void

    :cond_6
    if-eqz p3, :cond_7

    .line 741
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->mainHandler()Landroid/os/Handler;

    move-result-object p0

    new-instance p1, Lcom/movieboxpro/android/http/Http$5;

    invoke-direct {p1, p3}, Lcom/movieboxpro/android/http/Http$5;-><init>(Lcom/movieboxpro/android/http/RequestCallback;)V

    invoke-virtual {p0, p1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_7
    return-void

    :cond_8
    if-eqz p3, :cond_9

    .line 753
    invoke-static {}, Lcom/movieboxpro/android/http/Http;->mainHandler()Landroid/os/Handler;

    move-result-object p0

    new-instance p1, Lcom/movieboxpro/android/http/Http$6;

    invoke-direct {p1, p3}, Lcom/movieboxpro/android/http/Http$6;-><init>(Lcom/movieboxpro/android/http/RequestCallback;)V

    invoke-virtual {p0, p1}, Landroid/os/Handler;->post(Ljava/lang/Runnable;)Z

    :cond_9
    return-void
.end method

.method public static uploadSync(Ljava/lang/String;Ljava/util/Map;Ljava/util/Map;)Lokhttp3/Call;
    .locals 5
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x0,
            0x0,
            0x0
        }
        names = {
            "url",
            "params",
            "fileParams"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;",
            "Ljava/util/Map<",
            "Ljava/lang/String;",
            "Ljava/lang/String;",
            ">;)",
            "Lokhttp3/Call;"
        }
    .end annotation

    .line 829
    new-instance v0, Lokhttp3/OkHttpClient$Builder;

    invoke-direct {v0}, Lokhttp3/OkHttpClient$Builder;-><init>()V

    .line 830
    sget-boolean v1, Lcom/movieboxpro/android/app/App;->isDebug:Z

    if-eqz v1, :cond_0

    .line 832
    invoke-static {v0}, Lcom/movieboxpro/android/http/Http;->setLoggingInterceptor(Lokhttp3/OkHttpClient$Builder;)V

    .line 834
    :cond_0
    invoke-virtual {v0}, Lokhttp3/OkHttpClient$Builder;->build()Lokhttp3/OkHttpClient;

    move-result-object v0

    .line 836
    new-instance v1, Lokhttp3/MultipartBody$Builder;

    invoke-direct {v1}, Lokhttp3/MultipartBody$Builder;-><init>()V

    .line 837
    const-string v2, "multipart/form-data"

    invoke-static {v2}, Lokhttp3/MediaType;->parse(Ljava/lang/String;)Lokhttp3/MediaType;

    move-result-object v2

    invoke-virtual {v1, v2}, Lokhttp3/MultipartBody$Builder;->setType(Lokhttp3/MediaType;)Lokhttp3/MultipartBody$Builder;

    if-eqz p1, :cond_1

    .line 839
    invoke-interface {p1}, Ljava/util/Map;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_1

    .line 840
    invoke-interface {p1}, Ljava/util/Map;->entrySet()Ljava/util/Set;

    move-result-object p1

    invoke-interface {p1}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object p1

    :goto_0
    invoke-interface {p1}, Ljava/util/Iterator;->hasNext()Z

    move-result v2

    if-eqz v2, :cond_1

    invoke-interface {p1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/util/Map$Entry;

    .line 841
    invoke-interface {v2}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 842
    invoke-interface {v2}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    .line 843
    invoke-virtual {v1, v3, v2}, Lokhttp3/MultipartBody$Builder;->addFormDataPart(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/MultipartBody$Builder;

    goto :goto_0

    :cond_1
    const/4 p1, 0x0

    if-eqz p2, :cond_4

    .line 847
    invoke-interface {p2}, Ljava/util/Map;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_4

    .line 849
    const-string v2, "filePath"

    invoke-interface {p2, v2}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 850
    invoke-interface {p2, v2}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v2

    check-cast v2, Ljava/lang/String;

    goto :goto_1

    :cond_2
    move-object v2, p1

    :goto_1
    if-eqz v2, :cond_4

    .line 855
    const-string v3, "mimeType"

    invoke-interface {p2, v3}, Ljava/util/Map;->containsKey(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 856
    invoke-interface {p2, v3}, Ljava/util/Map;->get(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object p2

    check-cast p2, Ljava/lang/String;

    goto :goto_2

    .line 855
    :cond_3
    const-string p2, "image/*"

    .line 859
    :goto_2
    new-instance v3, Ljava/io/File;

    invoke-direct {v3, v2}, Ljava/io/File;-><init>(Ljava/lang/String;)V

    .line 860
    invoke-virtual {v3}, Ljava/io/File;->exists()Z

    move-result v2

    if-eqz v2, :cond_4

    invoke-virtual {v3}, Ljava/io/File;->isFile()Z

    move-result v2

    if-eqz v2, :cond_4

    .line 861
    invoke-virtual {v3}, Ljava/io/File;->getName()Ljava/lang/String;

    move-result-object p1

    .line 862
    invoke-static {p2}, Lokhttp3/MediaType;->parse(Ljava/lang/String;)Lokhttp3/MediaType;

    move-result-object p2

    invoke-static {p2, v3}, Lokhttp3/MultipartBody;->create(Lokhttp3/MediaType;Ljava/io/File;)Lokhttp3/RequestBody;

    move-result-object p2

    .line 863
    const-string v2, "Filedata"

    invoke-virtual {v1, v2, p1, p2}, Lokhttp3/MultipartBody$Builder;->addFormDataPart(Ljava/lang/String;Ljava/lang/String;Lokhttp3/RequestBody;)Lokhttp3/MultipartBody$Builder;

    .line 874
    invoke-virtual {v1}, Lokhttp3/MultipartBody$Builder;->build()Lokhttp3/MultipartBody;

    move-result-object p1

    .line 876
    new-instance p2, Lokhttp3/Request$Builder;

    invoke-direct {p2}, Lokhttp3/Request$Builder;-><init>()V

    sget-object v1, Lokhttp3/CacheControl;->FORCE_NETWORK:Lokhttp3/CacheControl;

    .line 877
    invoke-virtual {p2, v1}, Lokhttp3/Request$Builder;->cacheControl(Lokhttp3/CacheControl;)Lokhttp3/Request$Builder;

    move-result-object p2

    .line 878
    invoke-virtual {p2, p0}, Lokhttp3/Request$Builder;->url(Ljava/lang/String;)Lokhttp3/Request$Builder;

    move-result-object p0

    .line 879
    invoke-virtual {p0, p1}, Lokhttp3/Request$Builder;->post(Lokhttp3/RequestBody;)Lokhttp3/Request$Builder;

    move-result-object p0

    .line 880
    invoke-virtual {p0}, Lokhttp3/Request$Builder;->build()Lokhttp3/Request;

    move-result-object p0

    .line 881
    invoke-virtual {v0, p0}, Lokhttp3/OkHttpClient;->newCall(Lokhttp3/Request;)Lokhttp3/Call;

    move-result-object p0

    return-object p0

    :cond_4
    return-object p1
.end method
