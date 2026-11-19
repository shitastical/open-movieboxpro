package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public final class zzai extends zza implements zzah {
    zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
    }

    @Override // com.google.android.gms.internal.cast.zzah
    public final Bitmap zzb(Uri uri) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zza(parcelZza, uri);
        Parcel parcelZza2 = zza(1, parcelZza);
        Bitmap bitmap = (Bitmap) zzc.zza(parcelZza2, Bitmap.CREATOR);
        parcelZza2.recycle();
        return bitmap;
    }
}
