package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: loaded from: classes4.dex */
public interface zzet extends IInterface {
    void onDisconnected() throws RemoteException;

    void onError(int i) throws RemoteException;

    void zza(int i, int i2, Surface surface) throws RemoteException;

    void zzd() throws RemoteException;
}
