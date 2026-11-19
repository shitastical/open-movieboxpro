package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzds extends zzb implements zzdr {
    public zzds() {
        super("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                zzs(parcel.readInt());
                return true;
            case 2:
                zza((ApplicationMetadata) zzc.zza(parcel, ApplicationMetadata.CREATOR), parcel.readString(), parcel.readString(), zzc.zza(parcel));
                return true;
            case 3:
                zzi(parcel.readInt());
                return true;
            case 4:
                zza(parcel.readString(), parcel.readDouble(), zzc.zza(parcel));
                return true;
            case 5:
                zzb(parcel.readString(), parcel.readString());
                return true;
            case 6:
                zza(parcel.readString(), parcel.createByteArray());
                return true;
            case 7:
                zzu(parcel.readInt());
                return true;
            case 8:
                zzt(parcel.readInt());
                return true;
            case 9:
                onApplicationDisconnected(parcel.readInt());
                return true;
            case 10:
                zza(parcel.readString(), parcel.readLong(), parcel.readInt());
                return true;
            case 11:
                zza(parcel.readString(), parcel.readLong());
                return true;
            case 12:
                zzb((zzct) zzc.zza(parcel, zzct.CREATOR));
                return true;
            case 13:
                zzb((zzdl) zzc.zza(parcel, zzdl.CREATOR));
                return true;
            default:
                return false;
        }
    }
}
