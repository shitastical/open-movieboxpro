package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class zzi extends zza implements zzh {
    zzi(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
    }

    @Override // com.google.android.gms.internal.cast.zzh
    public final com.google.android.gms.cast.framework.zzj zza(IObjectWrapper iObjectWrapper, CastOptions castOptions, zzj zzjVar, Map map) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, castOptions);
        zzc.zza(parcelZza, zzjVar);
        parcelZza.writeMap(map);
        Parcel parcelZza2 = zza(1, parcelZza);
        com.google.android.gms.cast.framework.zzj zzjVarZza = com.google.android.gms.cast.framework.zzj.zza.zza(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return zzjVarZza;
    }

    @Override // com.google.android.gms.internal.cast.zzh
    public final com.google.android.gms.cast.framework.zzt zza(String str, String str2, com.google.android.gms.cast.framework.zzab zzabVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zza(parcelZza, zzabVar);
        Parcel parcelZza2 = zza(2, parcelZza);
        com.google.android.gms.cast.framework.zzt zztVarZzd = com.google.android.gms.cast.framework.zzt.zza.zzd(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return zztVarZzd;
    }

    @Override // com.google.android.gms.internal.cast.zzh
    public final com.google.android.gms.cast.framework.zzl zza(CastOptions castOptions, IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.zzh zzhVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, castOptions);
        zzc.zza(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, zzhVar);
        Parcel parcelZza2 = zza(3, parcelZza);
        com.google.android.gms.cast.framework.zzl zzlVarZzb = com.google.android.gms.cast.framework.zzl.zza.zzb(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return zzlVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.zzh
    public final com.google.android.gms.cast.framework.zzr zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, iObjectWrapper2);
        zzc.zza(parcelZza, iObjectWrapper3);
        Parcel parcelZza2 = zza(5, parcelZza);
        com.google.android.gms.cast.framework.zzr zzrVarZzc = com.google.android.gms.cast.framework.zzr.zza.zzc(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return zzrVarZzc;
    }

    @Override // com.google.android.gms.internal.cast.zzh
    public final zzah zza(IObjectWrapper iObjectWrapper, zzaj zzajVar, int i, int i2, boolean z, long j, int i3, int i4, int i5) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iObjectWrapper);
        zzc.zza(parcelZza, zzajVar);
        parcelZza.writeInt(i);
        parcelZza.writeInt(i2);
        zzc.writeBoolean(parcelZza, z);
        parcelZza.writeLong(j);
        parcelZza.writeInt(i3);
        parcelZza.writeInt(i4);
        parcelZza.writeInt(i5);
        Parcel parcelZza2 = zza(6, parcelZza);
        zzah zzahVarZze = zzah.zza.zze(parcelZza2.readStrongBinder());
        parcelZza2.recycle();
        return zzahVarZze;
    }
}
