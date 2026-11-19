package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes4.dex */
final class zzek extends zzem {
    zzek(zzeh zzehVar, GoogleApiClient googleApiClient) {
        super(zzehVar, googleApiClient);
    }

    @Override // com.google.android.gms.internal.cast.zzem
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzer zzerVar) throws RemoteException {
        zzerVar.zza(new zzeo(this));
    }

    @Override // com.google.android.gms.internal.cast.zzem, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzer) anyClient);
    }
}
