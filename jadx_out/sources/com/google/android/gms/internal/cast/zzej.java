package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;

/* JADX INFO: loaded from: classes4.dex */
final class zzej extends zzem {
    private final /* synthetic */ zzeh zzaaz;
    private final /* synthetic */ String zzaba;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzej(zzeh zzehVar, GoogleApiClient googleApiClient, String str) {
        super(zzehVar, googleApiClient);
        this.zzaaz = zzehVar;
        this.zzaba = str;
    }

    @Override // com.google.android.gms.internal.cast.zzem
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzer zzerVar) throws RemoteException {
        zzerVar.zza(new zzen(this, zzerVar), this.zzaaz.zzaay, this.zzaba);
    }

    @Override // com.google.android.gms.internal.cast.zzem, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzer) anyClient);
    }
}
