package com.movieboxpro.android.http;

import com.movieboxpro.android.http.v2.ApiV2EndpointConfig;
import kotlin.jvm.functions.Function1;
import okhttp3.HttpUrl;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes5.dex */
public final /* synthetic */ class Http$$ExternalSyntheticLambda3 implements Function1 {
    public final /* synthetic */ ApiV2EndpointConfig f$0;

    public /* synthetic */ Http$$ExternalSyntheticLambda3(ApiV2EndpointConfig apiV2EndpointConfig) {
        this.f$0 = apiV2EndpointConfig;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object obj) {
        return Boolean.valueOf(this.f$0.isMovieUrl((HttpUrl) obj));
    }
}
