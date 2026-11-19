package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public interface zzh extends IInterface {
    com.google.android.gms.cast.framework.zzj zza(IObjectWrapper iObjectWrapper, CastOptions castOptions, zzj zzjVar, Map map) throws RemoteException;

    com.google.android.gms.cast.framework.zzl zza(CastOptions castOptions, IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.zzh zzhVar) throws RemoteException;

    com.google.android.gms.cast.framework.zzr zza(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    com.google.android.gms.cast.framework.zzt zza(String str, String str2, com.google.android.gms.cast.framework.zzab zzabVar) throws RemoteException;

    zzah zza(IObjectWrapper iObjectWrapper, zzaj zzajVar, int i, int i2, boolean z, long j, int i3, int i4, int i5) throws RemoteException;
}
