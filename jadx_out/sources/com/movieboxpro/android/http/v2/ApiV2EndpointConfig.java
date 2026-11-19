package com.movieboxpro.android.http.v2;

import com.movieboxpro.android.http.API;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: ApiV2EndpointConfig.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0007J\u0010\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0007J\u0012\u0010\u000b\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\u0005H\u0007J\u000e\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/movieboxpro/android/http/v2/ApiV2EndpointConfig;", "", "<init>", "()V", "DEFAULT_BASE_URL", "", "RESERVED_ENCRYPT_MODULE", "resolveBaseUrl", "raw", "moduleUrl", ApiV2RequestInterceptor.MODULE, "updateFromBaseUrl", "", "isMovieUrl", "url", "Lokhttp3/HttpUrl;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ApiV2EndpointConfig {
    public static final String DEFAULT_BASE_URL = "https://mbpssl.shegu.net";
    public static final ApiV2EndpointConfig INSTANCE = new ApiV2EndpointConfig();
    public static final String RESERVED_ENCRYPT_MODULE = "api_client_ssl_encrypt";

    private ApiV2EndpointConfig() {
    }

    @JvmStatic
    public static final String resolveBaseUrl(String raw) {
        String string;
        HttpUrl httpUrl;
        if (raw != null && (string = StringsKt.trim((CharSequence) raw).toString()) != null) {
            if (string.length() <= 0) {
                string = null;
            }
            return (string == null || (httpUrl = HttpUrl.INSTANCE.parse(string)) == null || !Intrinsics.areEqual(httpUrl.scheme(), IDataSource.SCHEME_HTTPS_TAG)) ? DEFAULT_BASE_URL : StringsKt.trimEnd(httpUrl.newBuilder().encodedPath("/").query(null).fragment(null).build().getUrl(), '/');
        }
        return DEFAULT_BASE_URL;
    }

    @JvmStatic
    public static final String moduleUrl(String module) {
        Intrinsics.checkNotNullParameter(module, "module");
        String string = StringsKt.trim((CharSequence) module).toString();
        if (string.length() <= 0 || Intrinsics.areEqual(string, RESERVED_ENCRYPT_MODULE)) {
            throw new IllegalArgumentException("Movie API module must be a non-empty path segment".toString());
        }
        String MOVIE_HOST = API.MOVIE_HOST;
        Intrinsics.checkNotNullExpressionValue(MOVIE_HOST, "MOVIE_HOST");
        return StringsKt.trimEnd(MOVIE_HOST, '/') + "/api/" + string;
    }

    @JvmStatic
    public static final boolean updateFromBaseUrl(String raw) {
        String strResolveBaseUrl = resolveBaseUrl(raw);
        boolean z = !Intrinsics.areEqual(API.MOVIE_HOST, strResolveBaseUrl);
        API.MOVIE_HOST = strResolveBaseUrl;
        API.REAL_MOVIE_HOST = "";
        API.TRANSLATE_V3_URL = strResolveBaseUrl + "/api/srttransv3/index/";
        return z;
    }

    public final boolean isMovieUrl(HttpUrl url) {
        Intrinsics.checkNotNullParameter(url, "url");
        HttpUrl.Companion companion = HttpUrl.INSTANCE;
        String MOVIE_HOST = API.MOVIE_HOST;
        Intrinsics.checkNotNullExpressionValue(MOVIE_HOST, "MOVIE_HOST");
        HttpUrl httpUrl = companion.parse(StringsKt.trimEnd(MOVIE_HOST, '/') + "/");
        if (httpUrl == null || !Intrinsics.areEqual(url.scheme(), httpUrl.scheme()) || !Intrinsics.areEqual(url.host(), httpUrl.host()) || url.port() != httpUrl.port()) {
            return false;
        }
        List<String> listPathSegments = url.pathSegments();
        return listPathSegments.size() == 2 && Intrinsics.areEqual(listPathSegments.get(0), "api");
    }
}
