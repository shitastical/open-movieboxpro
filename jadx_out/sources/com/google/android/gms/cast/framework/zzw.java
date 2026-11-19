package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public final class zzw extends com.google.android.gms.internal.cast.zza implements zzv {
    zzw(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISessionManager");
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final IObjectWrapper zzag() throws RemoteException {
        Parcel parcelZza = zza(1, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final void zza(zzx zzxVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, zzxVar);
        zzb(2, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final void zzb(zzx zzxVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, zzxVar);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final void zza(zzn zznVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, zznVar);
        zzb(4, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final void zzb(zzn zznVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, zznVar);
        zzb(5, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final void zza(boolean z, boolean z2) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.writeBoolean(parcelZza, true);
        com.google.android.gms.internal.cast.zzc.writeBoolean(parcelZza, z2);
        zzb(6, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final IObjectWrapper zzae() throws RemoteException {
        Parcel parcelZza = zza(7, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final int getCastState() throws RemoteException {
        Parcel parcelZza = zza(8, zza());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.zzv
    public final void zzc(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, bundle);
        zzb(9, parcelZza);
    }
}
