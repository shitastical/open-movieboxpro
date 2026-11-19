package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class AdBreakStatus extends AbstractSafeParcelable {
    public static final int AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakStatus> CREATOR = new zzc();
    private final long zzm;
    private final long zzu;
    private final long zzv;
    private final String zzw;
    private final String zzx;

    AdBreakStatus(long j, long j2, String str, String str2, long j3) {
        this.zzu = j;
        this.zzv = j2;
        this.zzw = str;
        this.zzx = str2;
        this.zzm = j3;
    }

    public String getBreakId() {
        return this.zzw;
    }

    public String getBreakClipId() {
        return this.zzx;
    }

    public long getCurrentBreakTimeInMs() {
        return this.zzu;
    }

    public long getCurrentBreakClipTimeInMs() {
        return this.zzv;
    }

    public long getWhenSkippableInMs() {
        return this.zzm;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getCurrentBreakTimeInMs());
        SafeParcelWriter.writeLong(parcel, 3, getCurrentBreakClipTimeInMs());
        SafeParcelWriter.writeString(parcel, 4, getBreakId(), false);
        SafeParcelWriter.writeString(parcel, 5, getBreakClipId(), false);
        SafeParcelWriter.writeLong(parcel, 6, getWhenSkippableInMs());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zzu), Long.valueOf(this.zzv), this.zzw, this.zzx, Long.valueOf(this.zzm));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakStatus)) {
            return false;
        }
        AdBreakStatus adBreakStatus = (AdBreakStatus) obj;
        return this.zzu == adBreakStatus.zzu && this.zzv == adBreakStatus.zzv && zzdk.zza(this.zzw, adBreakStatus.zzw) && zzdk.zza(this.zzx, adBreakStatus.zzx) && this.zzm == adBreakStatus.zzm;
    }

    static AdBreakStatus zzc(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("currentBreakTime") || !jSONObject.has("currentBreakClipTime")) {
            return null;
        }
        try {
            long j = (long) (jSONObject.getLong("currentBreakTime") * 1000.0d);
            long j2 = (long) (jSONObject.getLong("currentBreakClipTime") * 1000.0d);
            String strOptString = jSONObject.optString("breakId", null);
            String strOptString2 = jSONObject.optString("breakClipId", null);
            long jOptLong = jSONObject.optLong("whenSkippable", -1L);
            return new AdBreakStatus(j, j2, strOptString, strOptString2, jOptLong != -1 ? (long) (jOptLong * 1000.0d) : jOptLong);
        } catch (JSONException e) {
            Log.d("AdBreakInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", e.getMessage()));
            return null;
        }
    }
}
