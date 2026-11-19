package com.movieboxpro.android.utils.download;

import android.util.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* JADX INFO: loaded from: classes5.dex */
public class RetryInterceptor implements Interceptor {
    private final int maxRetryCount;

    public RetryInterceptor(int maxRetryCount) {
        this.maxRetryCount = maxRetryCount;
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        IOException e = null;
        int i = 0;
        while (i < this.maxRetryCount) {
            try {
                Response responseProceed = chain.proceed(request);
                if (responseProceed.getIsSuccessful()) {
                    return responseProceed;
                }
                responseProceed.close();
            } catch (IOException e2) {
                e = e2;
                Log.d("RetryInterceptor", "Attempt " + (i + 1) + " failed.");
            }
            i++;
            try {
                Thread.sleep(((long) i) * 500);
            } catch (InterruptedException unused) {
            }
        }
        if (e != null) {
            throw e;
        }
        throw new IOException("Unknown network error");
    }
}
