package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: loaded from: classes4.dex */
public final class zzm extends com.google.android.gms.internal.cast.zza implements zzl {
    zzm(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ICastSession");
    }

    @Override // com.google.android.gms.cast.framework.zzl
    public final void onConnected(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, bundle);
        zzb(1, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzl
    public final void onConnectionSuspended(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzb(2, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzl
    public final void onConnectionFailed(ConnectionResult connectionResult) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, connectionResult);
        zzb(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzl
    public final void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, applicationMetadata);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.cast.zzc.writeBoolean(parcelZza, z);
        zzb(4, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzl
    public final void zzi(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzb(5, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzl
    public final void zza(boolean z, int i) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.writeBoolean(parcelZza, z);
        parcelZza.writeInt(0);
        zzb(6, parcelZza);
    }
}
