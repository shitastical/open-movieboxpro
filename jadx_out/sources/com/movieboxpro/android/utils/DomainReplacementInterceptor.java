package com.movieboxpro.android.utils;

import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes5.dex */
public class DomainReplacementInterceptor implements Interceptor {
    private final String originalDomain;
    private final String thumbDomain;

    public DomainReplacementInterceptor(String newDomain, String thumbDomain) {
        this.originalDomain = newDomain;
        this.thumbDomain = thumbDomain;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl httpUrlUrl = request.url();
        if (httpUrlUrl.host().startsWith("images.")) {
            if (TextUtils.isEmpty(this.originalDomain)) {
                Log.d("DomainReplacement", request.url().host());
                return chain.proceed(request);
            }
            Request requestBuild = request.newBuilder().url(httpUrlUrl.newBuilder().host(this.originalDomain).build()).build();
            Log.d("DomainReplacement", requestBuild.url().host());
            return chain.proceed(requestBuild);
        }
        if (httpUrlUrl.host().startsWith("thumb.")) {
            if (TextUtils.isEmpty(this.thumbDomain)) {
                Log.d("DomainReplacement", request.url().host());
                return chain.proceed(request);
            }
            Request requestBuild2 = request.newBuilder().url(httpUrlUrl.newBuilder().host(this.thumbDomain).build()).build();
            Log.d("DomainReplacement", requestBuild2.url().host());
            return chain.proceed(requestBuild2);
        }
        Log.d("DomainReplacement", request.url().host());
        return chain.proceed(request);
    }
}
