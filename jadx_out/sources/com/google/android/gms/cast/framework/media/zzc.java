package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public final class zzc extends com.google.android.gms.internal.cast.zza implements zzb {
    zzc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.IImagePicker");
    }

    @Override // com.google.android.gms.cast.framework.media.zzb
    public final int zzs() throws RemoteException {
        Parcel parcelZza = zza(3, zza());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.media.zzb
    public final WebImage onPickImage(MediaMetadata mediaMetadata, int i) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, mediaMetadata);
        parcelZza.writeInt(i);
        Parcel parcelZza2 = zza(1, parcelZza);
        WebImage webImage = (WebImage) com.google.android.gms.internal.cast.zzc.zza(parcelZza2, WebImage.CREATOR);
        parcelZza2.recycle();
        return webImage;
    }

    @Override // com.google.android.gms.cast.framework.media.zzb
    public final IObjectWrapper zzbi() throws RemoteException {
        Parcel parcelZza = zza(2, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.media.zzb
    public final WebImage zza(MediaMetadata mediaMetadata, ImageHints imageHints) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, mediaMetadata);
        com.google.android.gms.internal.cast.zzc.zza(parcelZza, imageHints);
        Parcel parcelZza2 = zza(4, parcelZza);
        WebImage webImage = (WebImage) com.google.android.gms.internal.cast.zzc.zza(parcelZza2, WebImage.CREATOR);
        parcelZza2.recycle();
        return webImage;
    }
}
