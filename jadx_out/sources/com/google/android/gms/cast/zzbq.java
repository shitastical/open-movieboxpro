package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbq extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ long zzge;
    private final /* synthetic */ int zzgf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbq(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, long j, int i, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
        this.zzge = j;
        this.zzgf = i;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzfl.zzff.zza(this.zzgm, new zzau().zzc(this.zzge).zzd(this.zzgf).zzh(this.zzfs).zzo());
    }
}
