package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public interface zzf extends IInterface {
    void onAppEnteredBackground() throws RemoteException;

    void onAppEnteredForeground() throws RemoteException;

    int zzs() throws RemoteException;

    IObjectWrapper zzt() throws RemoteException;
}
