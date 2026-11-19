package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface zzr extends IInterface {
    IBinder onBind(Intent intent) throws RemoteException;

    void onCreate() throws RemoteException;

    void onDestroy() throws RemoteException;

    int onStartCommand(Intent intent, int i, int i2) throws RemoteException;

    public static abstract class zza extends com.google.android.gms.internal.cast.zzb implements zzr {
        public static zzr zzc(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.IReconnectionService");
            if (iInterfaceQueryLocalInterface instanceof zzr) {
                return (zzr) iInterfaceQueryLocalInterface;
            }
            return new zzs(iBinder);
        }
    }
}
