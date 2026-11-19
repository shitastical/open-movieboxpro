package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbi extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ boolean zzfz;
    private final /* synthetic */ long[] zzga;
    private final /* synthetic */ MediaInfo zzgb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbi(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, boolean z, long j, long[] jArr, JSONObject jSONObject, MediaInfo mediaInfo) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
        this.zzfz = z;
        this.zzfr = j;
        this.zzga = jArr;
        this.zzfs = jSONObject;
        this.zzgb = mediaInfo;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) {
        synchronized (this.zzfl.lock) {
            this.zzfl.zzff.zza(this.zzgm, this.zzgb, (zzam) null, new MediaLoadOptions.Builder().setAutoplay(this.zzfz).setPlayPosition(this.zzfr).setActiveTrackIds(this.zzga).setCustomData(this.zzfs).build());
        }
    }
}
