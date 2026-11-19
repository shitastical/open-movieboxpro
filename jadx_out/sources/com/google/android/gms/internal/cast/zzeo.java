package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class zzeo extends zzel {
    private final /* synthetic */ zzem zzabc;

    protected zzeo(zzem zzemVar) {
        this.zzabc = zzemVar;
    }

    @Override // com.google.android.gms.internal.cast.zzel, com.google.android.gms.internal.cast.zzet
    public final void onDisconnected() throws RemoteException {
        zzeh.zzbf.d("onDisconnected", new Object[0]);
        this.zzabc.zzaaz.zzc();
        this.zzabc.setResult(new zzep(Status.RESULT_SUCCESS));
    }

    @Override // com.google.android.gms.internal.cast.zzel, com.google.android.gms.internal.cast.zzet
    public final void onError(int i) throws RemoteException {
        zzeh.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzabc.zzaaz.zzc();
        this.zzabc.setResult(new zzep(Status.RESULT_INTERNAL_ERROR));
    }
}
