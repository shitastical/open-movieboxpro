package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public interface zzv extends IInterface {
    int getCastState() throws RemoteException;

    void zza(zzn zznVar) throws RemoteException;

    void zza(zzx zzxVar) throws RemoteException;

    void zza(boolean z, boolean z2) throws RemoteException;

    IObjectWrapper zzae() throws RemoteException;

    IObjectWrapper zzag() throws RemoteException;

    void zzb(zzn zznVar) throws RemoteException;

    void zzb(zzx zzxVar) throws RemoteException;

    void zzc(Bundle bundle) throws RemoteException;
}
