package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes4.dex */
final class zzt extends CastRemoteDisplayClient.zza {
    private final /* synthetic */ TaskCompletionSource zzbl;
    private final /* synthetic */ zzs zzbo;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzt(zzs zzsVar, TaskCompletionSource taskCompletionSource) {
        super(null);
        this.zzbo = zzsVar;
        this.zzbl = taskCompletionSource;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayClient.zza, com.google.android.gms.internal.cast.zzet
    public final void onDisconnected() throws RemoteException {
        this.zzbo.zzbk.zzbf.d("onDisconnected", new Object[0]);
        this.zzbo.zzbk.zzc();
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, this.zzbl);
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplayClient.zza, com.google.android.gms.internal.cast.zzet
    public final void onError(int i) throws RemoteException {
        this.zzbo.zzbk.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzbo.zzbk.zzc();
        TaskUtil.setResultOrApiException(Status.RESULT_INTERNAL_ERROR, this.zzbl);
    }
}
