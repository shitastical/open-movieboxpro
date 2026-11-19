package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzau extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ RemoteMediaClient zzph;
    private final /* synthetic */ double zzps;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzau(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, double d, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzph = remoteMediaClient;
        this.zzps = d;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zzb(this.zzgm, this.zzps, this.zzfs);
    }
}
