package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: classes4.dex */
class zzem extends BaseImplementation.ApiMethodImpl<CastRemoteDisplay.CastRemoteDisplaySessionResult, zzer> {
    final /* synthetic */ zzeh zzaaz;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzem(zzeh zzehVar, GoogleApiClient googleApiClient) {
        super((Api<?>) zzehVar.zzaax, googleApiClient);
        this.zzaaz = zzehVar;
    }

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public void doExecute(zzer zzerVar) throws RemoteException {
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    protected /* synthetic */ Result createFailedResult(Status status) {
        return new zzep(status);
    }
}
