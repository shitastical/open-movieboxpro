package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes4.dex */
public interface zzl extends IInterface {
    void onConnected(Bundle bundle) throws RemoteException;

    void onConnectionFailed(ConnectionResult connectionResult) throws RemoteException;

    void onConnectionSuspended(int i) throws RemoteException;

    void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException;

    void zza(boolean z, int i) throws RemoteException;

    void zzi(int i) throws RemoteException;

    public static abstract class zza extends com.google.android.gms.internal.cast.zzb implements zzl {
        public static zzl zzb(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.ICastSession");
            if (iInterfaceQueryLocalInterface instanceof zzl) {
                return (zzl) iInterfaceQueryLocalInterface;
            }
            return new zzm(iBinder);
        }
    }
}
