package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzew extends zza implements zzev {
    zzew(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
    }

    @Override // com.google.android.gms.internal.cast.zzev
    public final void disconnect() throws RemoteException {
        zzc(3, zza());
    }

    @Override // com.google.android.gms.internal.cast.zzev
    public final void zza(zzet zzetVar, zzex zzexVar, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzetVar);
        zzc.zza(parcelZza, zzexVar);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zza(parcelZza, bundle);
        zzc(7, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzev
    public final void zza(zzet zzetVar, PendingIntent pendingIntent, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzetVar);
        zzc.zza(parcelZza, pendingIntent);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zza(parcelZza, bundle);
        zzc(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzev
    public final void zza(zzet zzetVar, int i) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzetVar);
        parcelZza.writeInt(i);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzev
    public final void zza(zzet zzetVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, zzetVar);
        zzc(6, parcelZza);
    }
}
