package com.google.android.gms.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.internal.cast.zzer;

/* JADX INFO: loaded from: classes4.dex */
final class zzo extends Api.AbstractClientBuilder<zzer, CastRemoteDisplay.CastRemoteDisplayOptions> {
    zzo() {
    }

    @Override // com.google.android.gms.common.api.Api.AbstractClientBuilder
    public final /* synthetic */ Api.Client buildClient(Context context, Looper looper, ClientSettings clientSettings, CastRemoteDisplay.CastRemoteDisplayOptions castRemoteDisplayOptions, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        CastRemoteDisplay.CastRemoteDisplayOptions castRemoteDisplayOptions2 = castRemoteDisplayOptions;
        Bundle bundle = new Bundle();
        bundle.putInt("configuration", castRemoteDisplayOptions2.zzbd);
        return new zzer(context, looper, clientSettings, castRemoteDisplayOptions2.zzaj, bundle, castRemoteDisplayOptions2.zzbc, connectionCallbacks, onConnectionFailedListener);
    }
}
