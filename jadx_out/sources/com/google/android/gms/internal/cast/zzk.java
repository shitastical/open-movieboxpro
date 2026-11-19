package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzk extends zzb implements zzj {
    public zzk() {
        super("com.google.android.gms.cast.framework.internal.IMediaRouter");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzl zzmVar;
        switch (i) {
            case 1:
                Bundle bundle = (Bundle) zzc.zza(parcel, Bundle.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zzmVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
                    if (iInterfaceQueryLocalInterface instanceof zzl) {
                        zzmVar = (zzl) iInterfaceQueryLocalInterface;
                    } else {
                        zzmVar = new zzm(strongBinder);
                    }
                }
                zza(bundle, zzmVar);
                parcel2.writeNoException();
                return true;
            case 2:
                zza((Bundle) zzc.zza(parcel, Bundle.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                return true;
            case 3:
                zzd((Bundle) zzc.zza(parcel, Bundle.CREATOR));
                parcel2.writeNoException();
                return true;
            case 4:
                boolean zZzb = zzb((Bundle) zzc.zza(parcel, Bundle.CREATOR), parcel.readInt());
                parcel2.writeNoException();
                zzc.writeBoolean(parcel2, zZzb);
                return true;
            case 5:
                zzl(parcel.readString());
                parcel2.writeNoException();
                return true;
            case 6:
                zzar();
                parcel2.writeNoException();
                return true;
            case 7:
                boolean zZzas = zzas();
                parcel2.writeNoException();
                zzc.writeBoolean(parcel2, zZzas);
                return true;
            case 8:
                Bundle bundleZzm = zzm(parcel.readString());
                parcel2.writeNoException();
                zzc.zzb(parcel2, bundleZzm);
                return true;
            case 9:
                String strZzat = zzat();
                parcel2.writeNoException();
                parcel2.writeString(strZzat);
                return true;
            case 10:
                zzs();
                parcel2.writeNoException();
                parcel2.writeInt(12451009);
                return true;
            case 11:
                zzau();
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
