package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;

/* JADX INFO: loaded from: classes4.dex */
final class zzad extends RemoteMediaClient.zzc {
    private final /* synthetic */ MediaInfo zzgb;
    private final /* synthetic */ RemoteMediaClient zzph;
    private final /* synthetic */ com.google.android.gms.cast.zzam zzpi = null;
    private final /* synthetic */ MediaLoadOptions zzpk;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzad(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, MediaInfo mediaInfo, com.google.android.gms.cast.zzam zzamVar, MediaLoadOptions mediaLoadOptions) {
        super(remoteMediaClient, googleApiClient);
        this.zzph = remoteMediaClient;
        this.zzgb = mediaInfo;
        this.zzpk = mediaLoadOptions;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zza(this.zzgm, this.zzgb, this.zzpi, this.zzpk);
    }
}
