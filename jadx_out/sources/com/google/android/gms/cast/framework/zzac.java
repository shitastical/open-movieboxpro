package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzac extends com.google.android.gms.internal.cast.zzb implements zzab {
    public zzac() {
        super("com.google.android.gms.cast.framework.ISessionProxy");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                IObjectWrapper iObjectWrapperZzah = zzah();
                parcel2.writeNoException();
                com.google.android.gms.internal.cast.zzc.zza(parcel2, iObjectWrapperZzah);
                return true;
            case 2:
                start((Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 3:
                resume((Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 4:
                end(com.google.android.gms.internal.cast.zzc.zza(parcel));
                parcel2.writeNoException();
                return true;
            case 5:
                long sessionRemainingTimeMs = getSessionRemainingTimeMs();
                parcel2.writeNoException();
                parcel2.writeLong(sessionRemainingTimeMs);
                return true;
            case 6:
                zzs();
                parcel2.writeNoException();
                parcel2.writeInt(12451009);
                return true;
            case 7:
                onStarting((Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 8:
                onResuming((Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
