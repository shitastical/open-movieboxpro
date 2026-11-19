package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzdk;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbx extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbx> CREATOR = new zzby();
    private final String url;
    private final int zzgr;
    private final int zzgs;
    private final String zzn;

    public zzbx(String str, int i, int i2, String str2) {
        this.url = str;
        this.zzgr = i;
        this.zzgs = i2;
        this.zzn = str2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbx)) {
            return false;
        }
        zzbx zzbxVar = (zzbx) obj;
        return zzdk.zza(this.url, zzbxVar.url) && zzdk.zza(Integer.valueOf(this.zzgr), Integer.valueOf(zzbxVar.zzgr)) && zzdk.zza(Integer.valueOf(this.zzgs), Integer.valueOf(zzbxVar.zzgs)) && zzdk.zza(zzbxVar.zzn, this.zzn);
    }

    public final JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("url", this.url);
        jSONObject.put("protocolType", this.zzgr);
        jSONObject.put("initialTime", this.zzgs);
        jSONObject.put("hlsSegmentFormat", this.zzn);
        return jSONObject;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.url, false);
        SafeParcelWriter.writeInt(parcel, 3, this.zzgr);
        SafeParcelWriter.writeInt(parcel, 4, this.zzgs);
        SafeParcelWriter.writeString(parcel, 5, this.zzn, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final int hashCode() {
        return Objects.hashCode(this.url, Integer.valueOf(this.zzgr), Integer.valueOf(this.zzgs), this.zzn);
    }
}
