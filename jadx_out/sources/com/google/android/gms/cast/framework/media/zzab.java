package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzab extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int[] zzfx;
    private final /* synthetic */ RemoteMediaClient zzph;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzab(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int[] iArr, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzph = remoteMediaClient;
        this.zzfx = iArr;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zza(this.zzgm, this.zzfx, this.zzfs);
    }
}
