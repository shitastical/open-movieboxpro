package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;

/* JADX INFO: loaded from: classes4.dex */
final class zzay extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ long[] zzfm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzay(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, long[] jArr) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
        this.zzfm = jArr;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzfl.zzff.zza(this.zzgm, this.zzfm);
    }
}
