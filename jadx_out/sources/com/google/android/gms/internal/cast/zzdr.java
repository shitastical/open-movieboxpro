package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

/* JADX INFO: loaded from: classes4.dex */
public interface zzdr extends IInterface {
    void onApplicationDisconnected(int i) throws RemoteException;

    void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException;

    void zza(String str, double d, boolean z) throws RemoteException;

    void zza(String str, long j) throws RemoteException;

    void zza(String str, long j, int i) throws RemoteException;

    void zza(String str, byte[] bArr) throws RemoteException;

    void zzb(zzct zzctVar) throws RemoteException;

    void zzb(zzdl zzdlVar) throws RemoteException;

    void zzb(String str, String str2) throws RemoteException;

    void zzi(int i) throws RemoteException;

    void zzs(int i) throws RemoteException;

    void zzt(int i) throws RemoteException;

    void zzu(int i) throws RemoteException;
}
