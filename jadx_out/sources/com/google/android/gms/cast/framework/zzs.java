package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzs extends com.google.android.gms.internal.cast.zza implements zzr {
    zzs(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.IReconnectionService");
    }

    @Override // com.google.android.gms.cast.framework.zzr
    public final void onCreate() throws RemoteException {
        zzb(1, zza());
    }

    @Override // com.google.android.gms.cast.framework.zzr
    public final int onStartCommand(Intent intent, int i, int i2) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, intent);
        parcelZza.writeInt(i);
        parcelZza.writeInt(i2);
        Parcel parcelZza2 = zza(2, parcelZza);
        int i3 = parcelZza2.readInt();
        parcelZza2.recycle();
        return i3;
    }

    @Override // com.google.android.gms.cast.framework.zzr
    public final IBinder onBind(Intent intent) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, intent);
        Parcel parcelZza2 = zza(3, parcelZza);
        IBinder strongBinder = parcelZza2.readStrongBinder();
        parcelZza2.recycle();
        return strongBinder;
    }

    @Override // com.google.android.gms.cast.framework.zzr
    public final void onDestroy() throws RemoteException {
        zzb(4, zza());
    }
}
