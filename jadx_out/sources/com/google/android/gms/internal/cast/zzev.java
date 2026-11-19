package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface zzev extends IInterface {
    void disconnect() throws RemoteException;

    void zza(zzet zzetVar) throws RemoteException;

    void zza(zzet zzetVar, int i) throws RemoteException;

    void zza(zzet zzetVar, PendingIntent pendingIntent, String str, String str2, Bundle bundle) throws RemoteException;

    void zza(zzet zzetVar, zzex zzexVar, String str, String str2, Bundle bundle) throws RemoteException;
}
