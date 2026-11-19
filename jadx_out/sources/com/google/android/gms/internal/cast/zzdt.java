package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public interface zzdt extends IInterface {
    void zza(IStatusCallback iStatusCallback, String[] strArr, String str, List<com.google.android.gms.cast.zzbx> list) throws RemoteException;
}
