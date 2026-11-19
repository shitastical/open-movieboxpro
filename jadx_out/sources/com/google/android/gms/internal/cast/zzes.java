package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzes extends zzey {
    private final /* synthetic */ zzex zzabf;
    private final /* synthetic */ zzer zzabg;

    zzes(zzer zzerVar, zzex zzexVar) {
        this.zzabg = zzerVar;
        this.zzabf = zzexVar;
    }

    @Override // com.google.android.gms.internal.cast.zzex
    public final void zzw(int i) throws RemoteException {
        zzer.zzbf.d("onRemoteDisplayEnded", new Object[0]);
        zzex zzexVar = this.zzabf;
        if (zzexVar != null) {
            zzexVar.zzw(i);
        }
        if (this.zzabg.zzabd != null) {
            this.zzabg.zzabd.onRemoteDisplayEnded(new Status(i));
        }
    }
}
