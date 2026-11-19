package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

/* JADX INFO: loaded from: classes4.dex */
public final class zza implements Parcelable.Creator<CastMediaOptions> {
    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CastMediaOptions[] newArray(int i) {
        return new CastMediaOptions[i];
    }

    @Override // android.os.Parcelable.Creator
    public final /* synthetic */ CastMediaOptions createFromParcel(Parcel parcel) {
        int iValidateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        String strCreateString = null;
        String strCreateString2 = null;
        IBinder iBinder = null;
        NotificationOptions notificationOptions = null;
        boolean z = false;
        while (parcel.dataPosition() < iValidateObjectHeader) {
            int header = SafeParcelReader.readHeader(parcel);
            int fieldId = SafeParcelReader.getFieldId(header);
            if (fieldId == 2) {
                strCreateString = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 3) {
                strCreateString2 = SafeParcelReader.createString(parcel, header);
            } else if (fieldId == 4) {
                iBinder = SafeParcelReader.readIBinder(parcel, header);
            } else if (fieldId == 5) {
                notificationOptions = (NotificationOptions) SafeParcelReader.createParcelable(parcel, header, NotificationOptions.CREATOR);
            } else if (fieldId == 6) {
                z = SafeParcelReader.readBoolean(parcel, header);
            } else {
                SafeParcelReader.skipUnknownField(parcel, header);
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, iValidateObjectHeader);
        return new CastMediaOptions(strCreateString, strCreateString2, iBinder, notificationOptions, z);
    }
}
