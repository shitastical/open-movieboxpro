package com.movieboxpro.android.utils.download;

import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: classes5.dex */
public final class OkHttpProvider {
    private OkHttpProvider() {
    }

    private static class Holder {
        private static final OkHttpClient INSTANCE = OkHttpProvider.create();

        private Holder() {
        }
    }

    public static OkHttpClient get() {
        return Holder.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static OkHttpClient create() {
        return new OkHttpClient.Builder().connectionPool(new ConnectionPool(8, 5L, TimeUnit.MINUTES)).connectTimeout(15L, TimeUnit.SECONDS).readTimeout(30L, TimeUnit.SECONDS).retryOnConnectionFailure(true).addInterceptor(new RetryInterceptor(3)).build();
    }
}
