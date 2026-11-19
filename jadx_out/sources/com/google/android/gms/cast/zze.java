package com.google.android.gms.cast;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdd;

/* JADX INFO: loaded from: classes4.dex */
final class zze extends Api.AbstractClientBuilder<zzdd, Cast.CastOptions> {
    zze() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Cast.CastOptions castOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        Cast.CastOptions castOptions2 = castOptions;
        Preconditions.checkNotNull(castOptions2, "Setting the API options is required.");
        return new zzdd(context, looper, clientSettings, castOptions2.zzaj, castOptions2.zzal, castOptions2.zzak, castOptions2.extras, connectionCallbacks, onConnectionFailedListener);
    }
}
