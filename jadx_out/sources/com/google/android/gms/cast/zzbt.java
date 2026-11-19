package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;

/* JADX INFO: loaded from: classes4.dex */
final class zzbt extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbt(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) {
        this.zzfl.zzff.zzb(this.zzgm);
    }
}
