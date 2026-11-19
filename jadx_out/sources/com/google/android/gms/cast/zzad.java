package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzdk;

/* JADX INFO: loaded from: classes4.dex */
public final class zzad extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzad> CREATOR = new zzae();
    private final zzab zzcw;
    private final zzab zzcx;

    public zzad(zzab zzabVar, zzab zzabVar2) {
        this.zzcw = zzabVar;
        this.zzcx = zzabVar2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcw, i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzcx, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzad)) {
            return false;
        }
        zzad zzadVar = (zzad) obj;
        return zzdk.zza(this.zzcw, zzadVar.zzcw) && zzdk.zza(this.zzcx, zzadVar.zzcx);
    }

    public final int hashCode() {
        return Objects.hashCode(this.zzcw, this.zzcx);
    }
}
