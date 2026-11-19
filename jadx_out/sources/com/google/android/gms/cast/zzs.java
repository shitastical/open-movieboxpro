package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.cast.zzeq;
import com.google.android.gms.internal.cast.zzev;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes4.dex */
final class zzs extends TaskApiCall<zzeq, Void> {
    final /* synthetic */ CastRemoteDisplayClient zzbk;

    zzs(CastRemoteDisplayClient castRemoteDisplayClient) {
        this.zzbk = castRemoteDisplayClient;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzev) ((zzeq) anyClient).getService()).zza(new zzt(this, taskCompletionSource));
    }
}
