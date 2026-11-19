package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzba extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ MediaQueueItem[] zzfo;
    private final /* synthetic */ int zzfp;
    private final /* synthetic */ int zzfq;
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzba(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, int i, int i2, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
        this.zzfo = mediaQueueItemArr;
        this.zzfp = i;
        this.zzfq = i2;
        this.zzfr = j;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) {
        this.zzfl.zzff.zza(this.zzgm, this.zzfo, (zzam) null, new zzar().zzb(this.zzfp).zzc(this.zzfq).zzb(this.zzfr).zzg(this.zzfs).zzl());
    }
}
