package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public final class zzu extends com.google.android.gms.internal.cast.zza implements zzt {
    zzu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISession");
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final IObjectWrapper zzaf() throws RemoteException {
        Parcel parcelZza = zza(1, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final String getCategory() throws RemoteException {
        Parcel parcelZza = zza(2, zza());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final String getSessionId() throws RemoteException {
        Parcel parcelZza = zza(3, zza());
        String string = parcelZza.readString();
        parcelZza.recycle();
        return string;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final boolean isConnected() throws RemoteException {
        Parcel parcelZza = zza(5, zza());
        boolean zZza = com.google.android.gms.internal.cast.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final boolean isConnecting() throws RemoteException {
        Parcel parcelZza = zza(6, zza());
        boolean zZza = com.google.android.gms.internal.cast.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final boolean isDisconnecting() throws RemoteException {
        Parcel parcelZza = zza(7, zza());
        boolean zZza = com.google.android.gms.internal.cast.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final boolean isDisconnected() throws RemoteException {
        Parcel parcelZza = zza(8, zza());
        boolean zZza = com.google.android.gms.internal.cast.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final boolean isResuming() throws RemoteException {
        Parcel parcelZza = zza(9, zza());
        boolean zZza = com.google.android.gms.internal.cast.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final boolean isSuspended() throws RemoteException {
        Parcel parcelZza = zza(10, zza());
        boolean zZza = com.google.android.gms.internal.cast.zzc.zza(parcelZza);
        parcelZza.recycle();
        return zZza;
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final void notifySessionStarted(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzb(11, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final void notifyFailedToStartSession(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzb(12, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final void notifySessionEnded(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzb(13, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final void notifySessionResumed(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.writeBoolean(parcelZza, z);
        zzb(14, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final void notifyFailedToResumeSession(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzb(15, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzt
    public final void notifySessionSuspended(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzb(16, parcelZza);
    }
}
