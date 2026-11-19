package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzm extends zza implements zzl {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
    }

    @Override // com.google.android.gms.internal.cast.zzl
    public final void zza(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zza(parcelZza, bundle);
        zzb(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzl
    public final void zzb(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zza(parcelZza, bundle);
        zzb(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzl
    public final void zzc(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zza(parcelZza, bundle);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzl
    public final void zzd(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zza(parcelZza, bundle);
        zzb(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzl
    public final void zza(String str, Bundle bundle, int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zza(parcelZza, bundle);
        parcelZza.writeInt(i);
        zzb(6, parcelZza);
    }
}
