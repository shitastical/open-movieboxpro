package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;

/* JADX INFO: loaded from: classes4.dex */
public interface zzdp extends IInterface {
    void disconnect() throws RemoteException;

    void requestStatus() throws RemoteException;

    void zza(double d, double d2, boolean z) throws RemoteException;

    void zza(String str, String str2, long j) throws RemoteException;

    void zza(String str, String str2, com.google.android.gms.cast.zzag zzagVar) throws RemoteException;

    void zza(boolean z, double d, boolean z2) throws RemoteException;

    void zzb(String str, LaunchOptions launchOptions) throws RemoteException;

    void zzfa() throws RemoteException;

    void zzj(String str) throws RemoteException;

    void zzs(String str) throws RemoteException;

    void zzt(String str) throws RemoteException;
}
