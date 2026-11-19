package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzz extends RemoteMediaClient.zzc {
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzfu;
    private final /* synthetic */ MediaQueueItem zzfv;
    private final /* synthetic */ RemoteMediaClient zzph;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzz(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzph = remoteMediaClient;
        this.zzfv = mediaQueueItem;
        this.zzfu = i;
        this.zzfr = j;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zza(this.zzgm, new MediaQueueItem[]{this.zzfv}, this.zzfu, 0, 0, this.zzfr, this.zzfs);
    }
}
