package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes4.dex */
public final class zzag extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzag> CREATOR = new zzah();
    private int zzda;

    zzag(int i) {
        this.zzda = i;
    }

    public zzag() {
        this(0);
    }

    public final String toString() {
        String str;
        int i = this.zzda;
        if (i == 0) {
            str = "STRONG";
        } else if (i == 2) {
            str = "INVISIBLE";
        } else {
            str = "UNKNOWN";
        }
        return String.format("joinOptions(connectionType=%s)", str);
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zzda);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof zzag) && this.zzda == ((zzag) obj).zzda;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.zzda));
    }
}
