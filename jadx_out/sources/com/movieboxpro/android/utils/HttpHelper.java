package com.movieboxpro.android.utils;

import android.os.Build;
import com.movieboxpro.android.http.ApiService3;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.OkHttpClient;

/* JADX INFO: compiled from: HttpHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0007J\b\u0010\u0006\u001a\u00020\u0007H\u0007J\u0006\u0010\b\u001a\u00020\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/utils/HttpHelper;", "", "<init>", "()V", "getApiService", "Lcom/movieboxpro/android/http/ApiService3;", "getOkHttpClient", "Lokhttp3/OkHttpClient;", "getUserAgent", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HttpHelper {
    public static final HttpHelper INSTANCE = new HttpHelper();

    private HttpHelper() {
    }

    @JvmStatic
    public static final ApiService3 getApiService() {
        return HttpHelperKt.getApiService();
    }

    @JvmStatic
    public static final OkHttpClient getOkHttpClient() {
        return HttpHelperKt.getOkHttpClient();
    }

    public final String getUserAgent() {
        return "Mozilla/5.0 (Linux; Android " + Build.VERSION.RELEASE + "; " + Build.BRAND + " " + Build.MODEL + ") AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Mobile Safari/537.36 com.movieboxpro.androidtv 13.4";
    }
}
