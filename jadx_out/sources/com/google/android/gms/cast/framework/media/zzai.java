package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzai extends RemoteMediaClient.zzc {
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzgc;
    private final /* synthetic */ RemoteMediaClient zzph;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzai(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int i, long j, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzph = remoteMediaClient;
        this.zzgc = i;
        this.zzfr = j;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zza(this.zzgm, this.zzgc, this.zzfr, (MediaQueueItem[]) null, 0, (Integer) null, this.zzfs);
    }
}
