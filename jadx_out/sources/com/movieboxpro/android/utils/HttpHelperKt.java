package com.movieboxpro.android.utils;

import com.google.common.net.HttpHeaders;
import com.movieboxpro.android.http.API;
import com.movieboxpro.android.http.ApiService3;
import com.movieboxpro.android.http.Http;
import com.movieboxpro.android.http.converter.FastJsonConverterFactory;
import com.movieboxpro.android.http.converter.ToStringConverterFactory;
import com.movieboxpro.android.view.activity.videoplayer.TranslateSubtitleRequest;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/* JADX INFO: compiled from: HttpHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u001a\f\u0010\u000e\u001a\u00020\u000f*\u00020\u000fH\u0000\u001a\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0000\"\u001b\u0010\u0000\u001a\u00020\u00018FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0002\u0010\u0003\"\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u0005\u001a\u0004\b\b\u0010\t\"\u001b\u0010\u000b\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u0005\u001a\u0004\b\f\u0010\t¨\u0006\u0013"}, d2 = {"okHttpClient", "Lokhttp3/OkHttpClient;", "getOkHttpClient", "()Lokhttp3/OkHttpClient;", "okHttpClient$delegate", "Lkotlin/Lazy;", "apiService", "Lcom/movieboxpro/android/http/ApiService3;", "getApiService", "()Lcom/movieboxpro/android/http/ApiService3;", "apiService$delegate", "translateStreamApi", "getTranslateStreamApi", "translateStreamApi$delegate", "removeHttpLoggingInterceptors", "Lokhttp3/OkHttpClient$Builder;", "retrofitBaseUrlForAbsoluteRequests", "", "rawUrl", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class HttpHelperKt {
    private static final Lazy okHttpClient$delegate = LazyKt.lazy(new Function0() { // from class: com.movieboxpro.android.utils.HttpHelperKt$$ExternalSyntheticLambda3
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HttpHelperKt.okHttpClient_delegate$lambda$2();
        }
    });
    private static final Lazy apiService$delegate = LazyKt.lazy(new Function0() { // from class: com.movieboxpro.android.utils.HttpHelperKt$$ExternalSyntheticLambda4
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HttpHelperKt.apiService_delegate$lambda$3();
        }
    });
    private static final Lazy translateStreamApi$delegate = LazyKt.lazy(new Function0() { // from class: com.movieboxpro.android.utils.HttpHelperKt$$ExternalSyntheticLambda5
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return HttpHelperKt.translateStreamApi_delegate$lambda$5();
        }
    });

    public static final OkHttpClient getOkHttpClient() {
        return (OkHttpClient) okHttpClient$delegate.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final OkHttpClient okHttpClient_delegate$lambda$2() {
        OkHttpClient.Builder builderFollowRedirects = new OkHttpClient.Builder().connectTimeout(15L, TimeUnit.SECONDS).writeTimeout(15L, TimeUnit.SECONDS).readTimeout(15L, TimeUnit.SECONDS).callTimeout(60L, TimeUnit.SECONDS).retryOnConnectionFailure(true).followRedirects(true);
        SSLSocketFactory sSLSocketFactory = SSLSocketClient.getSSLSocketFactory();
        Intrinsics.checkNotNullExpressionValue(sSLSocketFactory, "getSSLSocketFactory(...)");
        OkHttpClient.Builder builderSslSocketFactory = builderFollowRedirects.sslSocketFactory(sSLSocketFactory, new X509TrustManager() { // from class: com.movieboxpro.android.utils.HttpHelperKt$okHttpClient$2$builder$1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Intrinsics.checkNotNullParameter(authType, "authType");
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] chain, String authType) {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Intrinsics.checkNotNullParameter(authType, "authType");
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        });
        HostnameVerifier hostnameVerifier = SSLSocketClient.getHostnameVerifier();
        Intrinsics.checkNotNullExpressionValue(hostnameVerifier, "getHostnameVerifier(...)");
        return builderSslSocketFactory.hostnameVerifier(hostnameVerifier).followSslRedirects(true).addInterceptor(new Interceptor() { // from class: com.movieboxpro.android.utils.HttpHelperKt$okHttpClient_delegate$lambda$2$$inlined$-addInterceptor$1
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Request request = chain.request();
                if (TranslateSubtitleRequest.isLongTimeoutUrl(request.url().getUrl())) {
                    return chain.withConnectTimeout(60, TimeUnit.SECONDS).withReadTimeout(60, TimeUnit.SECONDS).withWriteTimeout(60, TimeUnit.SECONDS).proceed(request);
                }
                return chain.withConnectTimeout(30, TimeUnit.SECONDS).withReadTimeout(30, TimeUnit.SECONDS).withWriteTimeout(30, TimeUnit.SECONDS).proceed(request);
            }
        }).addInterceptor(new Interceptor() { // from class: com.movieboxpro.android.utils.HttpHelperKt$$ExternalSyntheticLambda0
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                return HttpHelperKt.okHttpClient_delegate$lambda$2$lambda$1(chain);
            }
        }).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Response okHttpClient_delegate$lambda$2$lambda$1(Interceptor.Chain chain) {
        Intrinsics.checkNotNullParameter(chain, "chain");
        return chain.proceed(chain.request().newBuilder().build());
    }

    public static final ApiService3 getApiService() {
        Object value = apiService$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ApiService3) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ApiService3 apiService_delegate$lambda$3() {
        return (ApiService3) new Retrofit.Builder().client(getOkHttpClient()).baseUrl(retrofitBaseUrlForAbsoluteRequests(API.MOVIE_HOST + "/")).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(new ToStringConverterFactory()).addConverterFactory(FastJsonConverterFactory.create()).build().create(ApiService3.class);
    }

    public static final ApiService3 getTranslateStreamApi() {
        Object value = translateStreamApi$delegate.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "getValue(...)");
        return (ApiService3) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ApiService3 translateStreamApi_delegate$lambda$5() {
        OkHttpClient.Builder builderCache = Http.getOkHttpClient().newBuilder().callTimeout(0L, TimeUnit.MILLISECONDS).cache(null);
        removeHttpLoggingInterceptors(builderCache);
        builderCache.addInterceptor(new Interceptor() { // from class: com.movieboxpro.android.utils.HttpHelperKt$translateStreamApi_delegate$lambda$5$$inlined$-addInterceptor$1
            @Override // okhttp3.Interceptor
            public final Response intercept(Interceptor.Chain chain) {
                Intrinsics.checkNotNullParameter(chain, "chain");
                Request request = chain.request();
                if (TranslateSubtitleRequest.isLongTimeoutUrl(request.url().getUrl())) {
                    return chain.proceed(request.newBuilder().header(HttpHeaders.ACCEPT, "*/*").header("User-Agent", HttpHelper.INSTANCE.getUserAgent()).build());
                }
                return chain.proceed(request);
            }
        });
        return (ApiService3) new Retrofit.Builder().client(builderCache.build()).baseUrl(retrofitBaseUrlForAbsoluteRequests(API.MOVIE_HOST + "/")).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(new ToStringConverterFactory()).addConverterFactory(FastJsonConverterFactory.create()).build().create(ApiService3.class);
    }

    public static final OkHttpClient.Builder removeHttpLoggingInterceptors(OkHttpClient.Builder builder) {
        Intrinsics.checkNotNullParameter(builder, "<this>");
        CollectionsKt.removeAll((List) builder.interceptors(), new Function1() { // from class: com.movieboxpro.android.utils.HttpHelperKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HttpHelperKt.removeHttpLoggingInterceptors$lambda$6((Interceptor) obj));
            }
        });
        CollectionsKt.removeAll((List) builder.networkInterceptors(), new Function1() { // from class: com.movieboxpro.android.utils.HttpHelperKt$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(HttpHelperKt.removeHttpLoggingInterceptors$lambda$7((Interceptor) obj));
            }
        });
        return builder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeHttpLoggingInterceptors$lambda$6(Interceptor it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it instanceof HttpLoggingInterceptor;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean removeHttpLoggingInterceptors$lambda$7(Interceptor it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it instanceof HttpLoggingInterceptor;
    }

    public static final String retrofitBaseUrlForAbsoluteRequests(String rawUrl) {
        String str;
        Intrinsics.checkNotNullParameter(rawUrl, "rawUrl");
        String string = StringsKt.trim((CharSequence) rawUrl).toString();
        if (StringsKt.endsWith$default(string, "/", false, 2, (Object) null)) {
            return string;
        }
        HttpUrl httpUrl = HttpUrl.INSTANCE.parse(string);
        if (httpUrl == null) {
            return "https://mbpssl.shegu.net/";
        }
        if (httpUrl.port() == HttpUrl.INSTANCE.defaultPort(httpUrl.scheme())) {
            str = "";
        } else {
            str = ":" + httpUrl.port();
        }
        return httpUrl.scheme() + "://" + httpUrl.host() + str + "/";
    }
}
