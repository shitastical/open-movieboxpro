package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdl extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdl> CREATOR = new zzdm();
    private double zzet;
    private boolean zzeu;
    private com.google.android.gms.cast.zzad zzyh;
    private int zzyi;
    private int zzyj;
    private ApplicationMetadata zzyt;

    zzdl(double d, boolean z, int i, ApplicationMetadata applicationMetadata, int i2, com.google.android.gms.cast.zzad zzadVar) {
        this.zzet = d;
        this.zzeu = z;
        this.zzyi = i;
        this.zzyt = applicationMetadata;
        this.zzyj = i2;
        this.zzyh = zzadVar;
    }

    public zzdl() {
        this(Double.NaN, false, -1, null, -1, null);
    }

    public final double getVolume() {
        return this.zzet;
    }

    public final boolean zzey() {
        return this.zzeu;
    }

    public final int getActiveInputState() {
        return this.zzyi;
    }

    public final int getStandbyState() {
        return this.zzyj;
    }

    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzyt;
    }

    public final com.google.android.gms.cast.zzad zzez() {
        return this.zzyh;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeDouble(parcel, 2, this.zzet);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzeu);
        SafeParcelWriter.writeInt(parcel, 4, this.zzyi);
        SafeParcelWriter.writeParcelable(parcel, 5, this.zzyt, i, false);
        SafeParcelWriter.writeInt(parcel, 6, this.zzyj);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzyh, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzdl)) {
            return false;
        }
        zzdl zzdlVar = (zzdl) obj;
        if (this.zzet == zzdlVar.zzet && this.zzeu == zzdlVar.zzeu && this.zzyi == zzdlVar.zzyi && zzdk.zza(this.zzyt, zzdlVar.zzyt) && this.zzyj == zzdlVar.zzyj) {
            com.google.android.gms.cast.zzad zzadVar = this.zzyh;
            if (zzdk.zza(zzadVar, zzadVar)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(Double.valueOf(this.zzet), Boolean.valueOf(this.zzeu), Integer.valueOf(this.zzyi), this.zzyt, Integer.valueOf(this.zzyj), this.zzyh);
    }
}
