package com.google.android.gms.cast.framework.media;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes4.dex */
public class ImageHints extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ImageHints> CREATOR = new zzf();
    private final int type;
    private final int zzlq;
    private final int zzlr;

    public ImageHints(int i, int i2, int i3) {
        this.type = i;
        this.zzlq = i2;
        this.zzlr = i3;
    }

    public int getType() {
        return this.type;
    }

    public int getWidthInPixels() {
        return this.zzlq;
    }

    public int getHeightInPixels() {
        return this.zzlr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getType());
        SafeParcelWriter.writeInt(parcel, 3, getWidthInPixels());
        SafeParcelWriter.writeInt(parcel, 4, getHeightInPixels());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
