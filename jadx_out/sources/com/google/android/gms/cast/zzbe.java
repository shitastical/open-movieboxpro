package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbe extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int[] zzfx;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbe(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int[] iArr, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
        this.zzfx = iArr;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzfl.zzff.zza(this.zzgm, this.zzfx, this.zzfs);
    }
}
