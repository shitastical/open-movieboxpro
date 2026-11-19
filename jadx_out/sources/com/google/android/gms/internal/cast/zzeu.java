package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzeu extends zzb implements zzet {
    public zzeu() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplayCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zza(parcel.readInt(), parcel.readInt(), (Surface) zzc.zza(parcel, Surface.CREATOR));
        } else if (i == 2) {
            onError(parcel.readInt());
        } else if (i == 3) {
            onDisconnected();
        } else {
            if (i != 4) {
                return false;
            }
            zzd();
        }
        parcel2.writeNoException();
        return true;
    }
}
