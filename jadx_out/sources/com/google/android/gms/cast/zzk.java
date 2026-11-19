package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdo;

/* JADX INFO: loaded from: classes4.dex */
final class zzk extends zzdo {
    zzk(Cast.CastApi.zza zzaVar, GoogleApiClient googleApiClient) {
        super(googleApiClient);
    }

    @Override // com.google.android.gms.internal.cast.zzdo
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzdd zzddVar) throws RemoteException {
        try {
            zzddVar.zza("", this);
        } catch (IllegalStateException unused) {
            zzp(2001);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdo, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzdd) anyClient);
    }
}
