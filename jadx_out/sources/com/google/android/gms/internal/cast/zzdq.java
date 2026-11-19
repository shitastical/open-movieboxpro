package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdq extends zza implements zzdp {
    zzdq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastDeviceController");
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void disconnect() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zzfa() throws RemoteException {
        zzc(4, zza());
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zzj(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void requestStatus() throws RemoteException {
        zzc(6, zza());
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zza(double d, double d2, boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeDouble(d);
        parcelZza.writeDouble(d2);
        zzc.writeBoolean(parcelZza, z);
        zzc(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zza(boolean z, double d, boolean z2) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.writeBoolean(parcelZza, z);
        parcelZza.writeDouble(d);
        zzc.writeBoolean(parcelZza, z2);
        zzc(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zza(String str, String str2, long j) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        parcelZza.writeLong(j);
        zzc(9, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zzs(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(11, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zzt(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(12, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zzb(String str, LaunchOptions launchOptions) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zza(parcelZza, launchOptions);
        zzc(13, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzdp
    public final void zza(String str, String str2, com.google.android.gms.cast.zzag zzagVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zza(parcelZza, zzagVar);
        zzc(14, parcelZza);
    }
}
