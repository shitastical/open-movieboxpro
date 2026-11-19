package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class AdBreakClipInfo extends AbstractSafeParcelable {
    public static final long AD_BREAK_CLIP_NOT_SKIPPABLE = -1;
    public static final Parcelable.Creator<AdBreakClipInfo> CREATOR = new zza();
    private final String mimeType;
    private final String zze;
    private final String zzf;
    private final long zzg;
    private final String zzh;
    private final String zzi;
    private String zzj;
    private String zzk;
    private String zzl;
    private final long zzm;
    private final String zzn;
    private final VastAdsRequest zzo;
    private JSONObject zzp;

    AdBreakClipInfo(String str, String str2, long j, String str3, String str4, String str5, String str6, String str7, String str8, long j2, String str9, VastAdsRequest vastAdsRequest) {
        this.zze = str;
        this.zzf = str2;
        this.zzg = j;
        this.zzh = str3;
        this.mimeType = str4;
        this.zzi = str5;
        this.zzj = str6;
        this.zzk = str7;
        this.zzl = str8;
        this.zzm = j2;
        this.zzn = str9;
        this.zzo = vastAdsRequest;
        if (TextUtils.isEmpty(str6)) {
            this.zzp = new JSONObject();
            return;
        }
        try {
            this.zzp = new JSONObject(str6);
        } catch (JSONException e) {
            Log.w("AdBreakClipInfo", String.format(Locale.ROOT, "Error creating AdBreakClipInfo: %s", e.getMessage()));
            this.zzj = null;
            this.zzp = new JSONObject();
        }
    }

    public static class Builder {
        private String zze;
        private String zzn;
        private String zzf = null;
        private long zzg = 0;
        private String zzh = null;
        private String mimeType = null;
        private String zzi = null;
        private String zzj = null;
        private String zzk = null;
        private String zzl = null;
        private long zzm = -1;
        private VastAdsRequest zzo = null;

        public Builder(String str) {
            this.zze = str;
        }

        public Builder setTitle(String str) {
            this.zzf = str;
            return this;
        }

        public Builder setDurationInMs(long j) {
            this.zzg = j;
            return this;
        }

        public Builder setContentUrl(String str) {
            this.zzh = str;
            return this;
        }

        public Builder setMimeType(String str) {
            this.mimeType = str;
            return this;
        }

        public Builder setClickThroughUrl(String str) {
            this.zzi = str;
            return this;
        }

        public Builder setCustomDataJsonString(String str) {
            this.zzj = str;
            return this;
        }

        public Builder setContentId(String str) {
            this.zzk = str;
            return this;
        }

        public Builder setImageUrl(String str) {
            this.zzl = str;
            return this;
        }

        public Builder setWhenSkippableInMs(long j) {
            this.zzm = j;
            return this;
        }

        public Builder setHlsSegmentFormat(String str) {
            this.zzn = str;
            return this;
        }

        public Builder setVastAdsRequest(VastAdsRequest vastAdsRequest) {
            this.zzo = vastAdsRequest;
            return this;
        }

        public AdBreakClipInfo build() {
            return new AdBreakClipInfo(this.zze, this.zzf, this.zzg, this.zzh, this.mimeType, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo);
        }
    }

    public String getId() {
        return this.zze;
    }

    public String getTitle() {
        return this.zzf;
    }

    public long getDurationInMs() {
        return this.zzg;
    }

    public String getContentUrl() {
        return this.zzh;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public String getClickThroughUrl() {
        return this.zzi;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public String getContentId() {
        return this.zzk;
    }

    public String getImageUrl() {
        return this.zzl;
    }

    public long getWhenSkippableInMs() {
        return this.zzm;
    }

    public String getHlsSegmentFormat() {
        return this.zzn;
    }

    public VastAdsRequest getVastAdsRequest() {
        return this.zzo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getId(), false);
        SafeParcelWriter.writeString(parcel, 3, getTitle(), false);
        SafeParcelWriter.writeLong(parcel, 4, getDurationInMs());
        SafeParcelWriter.writeString(parcel, 5, getContentUrl(), false);
        SafeParcelWriter.writeString(parcel, 6, getMimeType(), false);
        SafeParcelWriter.writeString(parcel, 7, getClickThroughUrl(), false);
        SafeParcelWriter.writeString(parcel, 8, this.zzj, false);
        SafeParcelWriter.writeString(parcel, 9, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 10, getImageUrl(), false);
        SafeParcelWriter.writeLong(parcel, 11, getWhenSkippableInMs());
        SafeParcelWriter.writeString(parcel, 12, getHlsSegmentFormat(), false);
        SafeParcelWriter.writeParcelable(parcel, 13, getVastAdsRequest(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public int hashCode() {
        return Objects.hashCode(this.zze, this.zzf, Long.valueOf(this.zzg), this.zzh, this.mimeType, this.zzi, this.zzj, this.zzk, this.zzl, Long.valueOf(this.zzm), this.zzn, this.zzo);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AdBreakClipInfo)) {
            return false;
        }
        AdBreakClipInfo adBreakClipInfo = (AdBreakClipInfo) obj;
        return zzdk.zza(this.zze, adBreakClipInfo.zze) && zzdk.zza(this.zzf, adBreakClipInfo.zzf) && this.zzg == adBreakClipInfo.zzg && zzdk.zza(this.zzh, adBreakClipInfo.zzh) && zzdk.zza(this.mimeType, adBreakClipInfo.mimeType) && zzdk.zza(this.zzi, adBreakClipInfo.zzi) && zzdk.zza(this.zzj, adBreakClipInfo.zzj) && zzdk.zza(this.zzk, adBreakClipInfo.zzk) && zzdk.zza(this.zzl, adBreakClipInfo.zzl) && this.zzm == adBreakClipInfo.zzm && zzdk.zza(this.zzn, adBreakClipInfo.zzn) && zzdk.zza(this.zzo, adBreakClipInfo.zzo);
    }

    static AdBreakClipInfo zza(JSONObject jSONObject) {
        if (jSONObject == null || !jSONObject.has("id")) {
            return null;
        }
        try {
            String string = jSONObject.getString("id");
            long jOptLong = (long) (jSONObject.optLong("duration") * 1000.0d);
            String strOptString = jSONObject.optString("clickThroughUrl", null);
            String strOptString2 = jSONObject.optString("contentUrl", null);
            String strOptString3 = jSONObject.optString("mimeType", null);
            if (strOptString3 == null) {
                strOptString3 = jSONObject.optString("contentType", null);
            }
            String str = strOptString3;
            String strOptString4 = jSONObject.optString(TvContractCompat.ProgramColumns.COLUMN_TITLE, null);
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("customData");
            return new AdBreakClipInfo(string, strOptString4, jOptLong, strOptString2, str, strOptString, (jSONObjectOptJSONObject == null || jSONObjectOptJSONObject.length() == 0) ? null : jSONObjectOptJSONObject.toString(), jSONObject.optString("contentId", null), jSONObject.optString("posterUrl", null), jSONObject.has("whenSkippable") ? (long) (((double) ((Integer) jSONObject.get("whenSkippable")).intValue()) * 1000.0d) : -1L, jSONObject.optString("hlsSegmentFormat", null), VastAdsRequest.fromJson(jSONObject.optJSONObject("vastAdsRequest")));
        } catch (JSONException e) {
            Log.d("AdBreakClipInfo", String.format(Locale.ROOT, "Error while creating an AdBreakClipInfo from JSON: %s", e.getMessage()));
            return null;
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("id", this.zze);
            jSONObject.put("duration", this.zzg / 1000.0d);
            long j = this.zzm;
            if (j != -1) {
                jSONObject.put("whenSkippable", j / 1000.0d);
            }
            String str = this.zzk;
            if (str != null) {
                jSONObject.put("contentId", str);
            }
            String str2 = this.mimeType;
            if (str2 != null) {
                jSONObject.put("contentType", str2);
            }
            String str3 = this.zzf;
            if (str3 != null) {
                jSONObject.put(TvContractCompat.ProgramColumns.COLUMN_TITLE, str3);
            }
            String str4 = this.zzh;
            if (str4 != null) {
                jSONObject.put("contentUrl", str4);
            }
            String str5 = this.zzi;
            if (str5 != null) {
                jSONObject.put("clickThroughUrl", str5);
            }
            JSONObject jSONObject2 = this.zzp;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str6 = this.zzl;
            if (str6 != null) {
                jSONObject.put("posterUrl", str6);
            }
            String str7 = this.zzn;
            if (str7 != null) {
                jSONObject.put("hlsSegmentFormat", str7);
            }
            VastAdsRequest vastAdsRequest = this.zzo;
            if (vastAdsRequest != null) {
                jSONObject.put("vastAdsRequest", vastAdsRequest.toJson());
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }
}
