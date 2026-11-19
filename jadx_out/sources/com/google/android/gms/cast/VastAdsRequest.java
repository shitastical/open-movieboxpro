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
public class VastAdsRequest extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VastAdsRequest> CREATOR = new zzca();
    private final String zzgx;
    private final String zzgy;

    VastAdsRequest(String str, String str2) {
        this.zzgx = str;
        this.zzgy = str2;
    }

    public static class Builder {
        private String zzgx = null;
        private String zzgy = null;

        public Builder setAdTagUrl(String str) {
            this.zzgx = str;
            return this;
        }

        public Builder setAdsResponse(String str) {
            this.zzgy = str;
            return this;
        }

        public VastAdsRequest build() {
            return new VastAdsRequest(this.zzgx, this.zzgy);
        }
    }

    public String getAdTagUrl() {
        return this.zzgx;
    }

    public String getAdsResponse() {
        return this.zzgy;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getAdTagUrl(), false);
        SafeParcelWriter.writeString(parcel, 3, getAdsResponse(), false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzgx, this.zzgy);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VastAdsRequest)) {
            return false;
        }
        VastAdsRequest vastAdsRequest = (VastAdsRequest) obj;
        return zzdk.zza(this.zzgx, vastAdsRequest.zzgx) && zzdk.zza(this.zzgy, vastAdsRequest.zzgy);
    }

    public static VastAdsRequest fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new VastAdsRequest(jSONObject.optString("adTagUrl", null), jSONObject.optString("adsResponse", null));
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            String str = this.zzgx;
            if (str != null) {
                jSONObject.put("adTagUrl", str);
            }
            String str2 = this.zzgy;
            if (str2 != null) {
                jSONObject.put("adsResponse", str2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
