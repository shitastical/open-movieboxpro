package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbc extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzfu;
    private final /* synthetic */ MediaQueueItem zzfv;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbc(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem mediaQueueItem, int i, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
        this.zzfv = mediaQueueItem;
        this.zzfu = i;
        this.zzfr = j;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzfl.zzff.zza(this.zzgm, new MediaQueueItem[]{this.zzfv}, this.zzfu, 0, 0, this.zzfr, this.zzfs);
    }
}
