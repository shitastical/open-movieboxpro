package com.google.android.gms.internal.cast;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public interface zzah extends IInterface {
    Bitmap zzb(Uri uri) throws RemoteException;

    public static abstract class zza extends zzb implements zzah {
        public static zzah zze(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.framework.media.internal.IFetchBitmapTask");
            if (iInterfaceQueryLocalInterface instanceof zzah) {
                return (zzah) iInterfaceQueryLocalInterface;
            }
            return new zzai(iBinder);
        }
    }
}
