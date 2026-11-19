package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class zzk extends com.google.android.gms.internal.cast.zza implements zzj {
    zzk(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ICastContext");
    }

    @Override // com.google.android.gms.cast.framework.zzj
    public final Bundle zzab() throws RemoteException {
        Parcel parcelZza = zza(1, zza());
        Bundle bundle = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcelZza, Bundle.CREATOR);
        parcelZza.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.cast.framework.zzj
    public final boolean isAppVisible() throws RemoteException {
        Parcel parcelZza = zza(2, zza());
        boolean zZza = com.google.android.gms.internal.cast.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.cast.framework.zzj
    public final void zza(zzf zzfVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, zzfVar);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzj
    public final void zzb(zzf zzfVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, zzfVar);
        zzb(4, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzj
    public final zzv zzac() throws RemoteException {
        zzv zzwVar;
        Parcel parcelZza = zza(5, zza());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzwVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.ISessionManager");
            if (iInterfaceQueryLocalInterface instanceof zzv) {
                zzwVar = (zzv) iInterfaceQueryLocalInterface;
            } else {
                zzwVar = new zzw(strongBinder);
            }
        }
        parcelZza.recycle();
        return zzwVar;
    }

    @Override // com.google.android.gms.cast.framework.zzj
    public final zzp zzad() throws RemoteException {
        zzp zzqVar;
        Parcel parcelZza = zza(6, zza());
        IBinder strongBinder = parcelZza.readStrongBinder();
        if (strongBinder == null) {
            zzqVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.IDiscoveryManager");
            if (iInterfaceQueryLocalInterface instanceof zzp) {
                zzqVar = (zzp) iInterfaceQueryLocalInterface;
            } else {
                zzqVar = new zzq(strongBinder);
            }
        }
        parcelZza.recycle();
        return zzqVar;
    }

    @Override // com.google.android.gms.cast.framework.zzj
    public final void zza(String str, Map map) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeMap(map);
        zzb(11, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzj
    public final boolean zzw() throws RemoteException {
        Parcel parcelZza = zza(12, zza());
        boolean zZza = com.google.android.gms.internal.cast.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }
}
