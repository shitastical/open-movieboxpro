package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdo;

/* JADX INFO: loaded from: classes4.dex */
final class zzf extends zzdo {
    private final /* synthetic */ String zzae;
    private final /* synthetic */ String zzaf;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzf(Cast.CastApi.zza zzaVar, GoogleApiClient googleApiClient, String str, String str2) {
        super(googleApiClient);
        this.zzae = str;
        this.zzaf = str2;
    }

    @Override // com.google.android.gms.internal.cast.zzdo
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzdd zzddVar) throws RemoteException {
        try {
            zzddVar.zza(this.zzae, this.zzaf, this);
        } catch (IllegalArgumentException | IllegalStateException unused) {
            zzp(2001);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdo, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzdd) anyClient);
    }
}
