package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface zzj extends IInterface {
    boolean isAppVisible() throws RemoteException;

    void zza(zzf zzfVar) throws RemoteException;

    void zza(String str, Map map) throws RemoteException;

    Bundle zzab() throws RemoteException;

    zzv zzac() throws RemoteException;

    zzp zzad() throws RemoteException;

    void zzb(zzf zzfVar) throws RemoteException;

    boolean zzw() throws RemoteException;

    public static abstract class zza extends com.google.android.gms.internal.cast.zzb implements zzj {
        public static zzj zza(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.ICastContext");
            if (iInterfaceQueryLocalInterface instanceof zzj) {
                return (zzj) iInterfaceQueryLocalInterface;
            }
            return new zzk(iBinder);
        }
    }
}
