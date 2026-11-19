package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public interface zzx extends IInterface {
    void zza(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    void zza(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zzb(IObjectWrapper iObjectWrapper, String str) throws RemoteException;

    void zzc(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    void zzd(IObjectWrapper iObjectWrapper, int i) throws RemoteException;

    int zzs() throws RemoteException;

    IObjectWrapper zzt() throws RemoteException;
}
