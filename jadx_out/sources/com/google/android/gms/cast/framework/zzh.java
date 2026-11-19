package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;

/* JADX INFO: loaded from: classes4.dex */
public interface zzh extends IInterface {
    void zza(String str, LaunchOptions launchOptions) throws RemoteException;

    void zza(String str, String str2) throws RemoteException;

    void zzh(int i) throws RemoteException;

    void zzj(String str) throws RemoteException;

    int zzs() throws RemoteException;
}
