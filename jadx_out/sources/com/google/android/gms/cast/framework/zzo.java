package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzo extends com.google.android.gms.internal.cast.zzb implements zzn {
    public zzo() {
        super("com.google.android.gms.cast.framework.ICastStateListener");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IObjectWrapper iObjectWrapperZzt = zzt();
            parcel2.writeNoException();
            com.google.android.gms.internal.cast.zzc.zza(parcel2, iObjectWrapperZzt);
        } else if (i == 2) {
            onCastStateChanged(parcel.readInt());
            parcel2.writeNoException();
        } else {
            if (i != 3) {
                return false;
            }
            zzs();
            parcel2.writeNoException();
            parcel2.writeInt(12451009);
        }
        return true;
    }
}
