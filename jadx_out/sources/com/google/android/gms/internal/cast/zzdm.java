package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdm implements Parcelable.Creator<zzdl> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdl[] newArray(int i) {
        return new zzdl[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ zzdl createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        double d = 0.0d;
        ApplicationMetadata applicationMetadata = null;
        com.google.android.gms.cast.zzad zzadVar = null;
        boolean z = false;
        int i = 0;
        int i2 = 0;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(header)) {
                case 2:
                    d = SafeParcelReader.readDouble(parcel, header);
                    break;
                case 3:
                    z = SafeParcelReader.readBoolean(parcel, header);
                    break;
                case 4:
                    i = SafeParcelReader.readInt(parcel, header);
                    break;
                case 5:
                    applicationMetadata = (ApplicationMetadata) SafeParcelReader.createParcelable(parcel, header, ApplicationMetadata.CREATOR);
                    break;
                case 6:
                    i2 = SafeParcelReader.readInt(parcel, header);
                    break;
                case 7:
                    zzadVar = (com.google.android.gms.cast.zzad) SafeParcelReader.createParcelable(parcel, header, com.google.android.gms.cast.zzad.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, header);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new zzdl(d, z, i, applicationMetadata, i2, zzadVar);
    }
}
