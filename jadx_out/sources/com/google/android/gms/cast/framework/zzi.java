package com.google.android.gms.cast.framework;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzi extends com.google.android.gms.internal.cast.zzb implements zzh {
    public zzi() {
        super("com.google.android.gms.cast.framework.ICastConnectionController");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza(parcel.readString(), parcel.readString());
            parcel2.writeNoException();
        } else if (i == 2) {
            zza(parcel.readString(), (LaunchOptions) com.google.android.gms.internal.cast.zzc.zza(parcel, LaunchOptions.CREATOR));
            parcel2.writeNoException();
        } else if (i == 3) {
            zzj(parcel.readString());
            parcel2.writeNoException();
        } else if (i == 4) {
            zzh(parcel.readInt());
            parcel2.writeNoException();
        } else {
            if (i != 5) {
                return false;
            }
            zzs();
            parcel2.writeNoException();
            parcel2.writeInt(12451009);
        }
        return true;
    }
}
