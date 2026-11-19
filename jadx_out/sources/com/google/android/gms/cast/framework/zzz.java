package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public interface zzz extends IInterface {
    String getCategory() throws RemoteException;

    boolean isSessionRecoverable() throws RemoteException;

    IObjectWrapper zzk(String str) throws RemoteException;

    int zzs() throws RemoteException;
}
