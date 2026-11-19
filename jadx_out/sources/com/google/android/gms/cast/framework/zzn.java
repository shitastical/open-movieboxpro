package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public interface zzn extends IInterface {
    void onCastStateChanged(int i) throws RemoteException;

    int zzs() throws RemoteException;

    IObjectWrapper zzt() throws RemoteException;
}
