package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes4.dex */
public final class zzab extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzab> CREATOR = new zzac();
    private final float zzct;
    private final float zzcu;
    private final float zzcv;

    public zzab(float f, float f2, float f3) {
        this.zzct = f;
        this.zzcu = f2;
        this.zzcv = f3;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeFloat(parcel, 2, this.zzct);
        SafeParcelWriter.writeFloat(parcel, 3, this.zzcu);
        SafeParcelWriter.writeFloat(parcel, 4, this.zzcv);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzab)) {
            return false;
        }
        zzab zzabVar = (zzab) obj;
        return this.zzct == zzabVar.zzct && this.zzcu == zzabVar.zzcu && this.zzcv == zzabVar.zzcv;
    }

    public final int hashCode() {
        return Objects.hashCode(Float.valueOf(this.zzct), Float.valueOf(this.zzcu), Float.valueOf(this.zzcv));
    }
}
