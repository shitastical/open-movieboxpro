package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbl extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ long zzfr;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzgc;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbl(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, long j, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
        this.zzgc = i;
        this.zzfr = j;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) throws zzea {
        if (this.zzfl.zzf(this.zzgc) == -1) {
            setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(0)));
        } else {
            this.zzfl.zzff.zza(this.zzgm, this.zzgc, this.zzfr, (MediaQueueItem[]) null, 0, (Integer) null, this.zzfs);
        }
    }
}
