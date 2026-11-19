package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;

/* JADX INFO: loaded from: classes4.dex */
final class zzx extends RemoteMediaClient.zzc {
    private final /* synthetic */ MediaQueueItem[] zzfo;
    private final /* synthetic */ RemoteMediaClient zzph;
    private final /* synthetic */ com.google.android.gms.cast.zzam zzpi = null;
    private final /* synthetic */ com.google.android.gms.cast.zzap zzpj;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzx(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, MediaQueueItem[] mediaQueueItemArr, com.google.android.gms.cast.zzam zzamVar, com.google.android.gms.cast.zzap zzapVar) {
        super(remoteMediaClient, googleApiClient);
        this.zzph = remoteMediaClient;
        this.zzfo = mediaQueueItemArr;
        this.zzpj = zzapVar;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zza(this.zzgm, this.zzfo, this.zzpi, this.zzpj);
    }
}
