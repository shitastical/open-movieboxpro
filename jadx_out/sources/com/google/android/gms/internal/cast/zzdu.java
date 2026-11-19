package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdu extends zza implements zzdt {
    zzdu(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastService");
    }

    @Override // com.google.android.gms.internal.cast.zzdt
    public final void zza(IStatusCallback iStatusCallback, String[] strArr, String str, List<com.google.android.gms.cast.zzbx> list) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, iStatusCallback);
        parcelZza.writeStringArray(strArr);
        parcelZza.writeString(str);
        parcelZza.writeTypedList(list);
        zzc(2, parcelZza);
    }
}
