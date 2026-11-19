package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public interface zzb extends IInterface {
    WebImage onPickImage(MediaMetadata mediaMetadata, int i) throws RemoteException;

    WebImage zza(MediaMetadata mediaMetadata, ImageHints imageHints) throws RemoteException;

    IObjectWrapper zzbi() throws RemoteException;

    int zzs() throws RemoteException;

    public static abstract class zza extends com.google.android.gms.internal.cast.zzb implements zzb {
        public zza() {
            super("com.google.android.gms.cast.framework.media.IImagePicker");
        }

        @Override // com.google.android.gms.internal.cast.zzb
        protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                WebImage webImageOnPickImage = onPickImage((MediaMetadata) com.google.android.gms.internal.cast.zzc.zza(parcel, MediaMetadata.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                com.google.android.gms.internal.cast.zzc.zzb(parcel2, webImageOnPickImage);
            } else if (i == 2) {
                IObjectWrapper iObjectWrapperZzbi = zzbi();
                parcel2.writeNoException();
                com.google.android.gms.internal.cast.zzc.zza(parcel2, iObjectWrapperZzbi);
            } else if (i == 3) {
                int iZzs = zzs();
                parcel2.writeNoException();
                parcel2.writeInt(iZzs);
            } else {
                if (i != 4) {
                    return false;
                }
                WebImage webImageZza = zza((MediaMetadata) com.google.android.gms.internal.cast.zzc.zza(parcel, MediaMetadata.CREATOR), (ImageHints) com.google.android.gms.internal.cast.zzc.zza(parcel, ImageHints.CREATOR));
                parcel2.writeNoException();
                com.google.android.gms.internal.cast.zzc.zzb(parcel2, webImageZza);
            }
            return true;
        }
    }
}
