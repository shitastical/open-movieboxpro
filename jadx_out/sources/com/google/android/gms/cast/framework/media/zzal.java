package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;

/* JADX INFO: loaded from: classes4.dex */
final class zzal extends RemoteMediaClient.zzc {
    private final /* synthetic */ RemoteMediaClient zzph;
    private final /* synthetic */ int[] zzpl;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzal(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, boolean z, int[] iArr) {
        super(googleApiClient, true);
        this.zzph = remoteMediaClient;
        this.zzpl = iArr;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zza(this.zzgm, this.zzpl);
    }
}
