package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzaa extends com.google.android.gms.internal.cast.zzb implements zzz {
    public zzaa() {
        super("com.google.android.gms.cast.framework.ISessionProvider");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            IObjectWrapper iObjectWrapperZzk = zzk(parcel.readString());
            parcel2.writeNoException();
            com.google.android.gms.internal.cast.zzc.zza(parcel2, iObjectWrapperZzk);
        } else if (i == 2) {
            boolean zIsSessionRecoverable = isSessionRecoverable();
            parcel2.writeNoException();
            com.google.android.gms.internal.cast.zzc.writeBoolean(parcel2, zIsSessionRecoverable);
        } else if (i == 3) {
            String category = getCategory();
            parcel2.writeNoException();
            parcel2.writeString(category);
        } else {
            if (i != 4) {
                return false;
            }
            zzs();
            parcel2.writeNoException();
            parcel2.writeInt(12451009);
        }
        return true;
    }
}
