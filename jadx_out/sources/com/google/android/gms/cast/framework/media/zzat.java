package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzat extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ RemoteMediaClient zzph;
    private final /* synthetic */ boolean zzpr;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzat(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, boolean z, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzph = remoteMediaClient;
        this.zzpr = z;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zza(this.zzgm, this.zzpr, this.zzfs);
    }
}
