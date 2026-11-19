package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class zzan extends RemoteMediaClient.zzc {
    private final /* synthetic */ RemoteMediaClient zzph;
    private final /* synthetic */ String zzpo;
    private final /* synthetic */ List zzpp;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzan(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, boolean z, String str, List list) {
        super(googleApiClient, true);
        this.zzph = remoteMediaClient;
        this.zzpo = str;
        this.zzpp = list;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        this.zzph.zzff.zzb(this.zzpo, this.zzpp);
    }
}
