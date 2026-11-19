package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zze extends com.google.android.gms.internal.cast.zza implements zzd {
    zze(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.media.INotificationActionsProvider");
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final int zzs() throws RemoteException {
        Parcel parcelZza = zza(1, zza());
        int i = parcelZza.readInt();
        parcelZza.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final IObjectWrapper zzbi() throws RemoteException {
        Parcel parcelZza = zza(2, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZza.readStrongBinder());
        parcelZza.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final List<NotificationAction> getNotificationActions() throws RemoteException {
        Parcel parcelZza = zza(3, zza());
        ArrayList arrayListCreateTypedArrayList = parcelZza.createTypedArrayList(NotificationAction.CREATOR);
        parcelZza.recycle();
        return arrayListCreateTypedArrayList;
    }

    @Override // com.google.android.gms.cast.framework.media.zzd
    public final int[] getCompactViewActionIndices() throws RemoteException {
        Parcel parcelZza = zza(4, zza());
        int[] iArrCreateIntArray = parcelZza.createIntArray();
        parcelZza.recycle();
        return iArrCreateIntArray;
    }
}
