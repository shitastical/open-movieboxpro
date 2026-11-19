package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class MediaTrack extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaTrack> CREATOR = new zzaw();
    public static final int SUBTYPE_CAPTIONS = 2;
    public static final int SUBTYPE_CHAPTERS = 4;
    public static final int SUBTYPE_DESCRIPTIONS = 3;
    public static final int SUBTYPE_METADATA = 5;
    public static final int SUBTYPE_NONE = 0;
    public static final int SUBTYPE_SUBTITLES = 1;
    public static final int SUBTYPE_UNKNOWN = -1;
    public static final int TYPE_AUDIO = 2;
    public static final int TYPE_TEXT = 1;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_VIDEO = 3;
    private long id;
    private String name;
    private int type;
    private String zzdc;
    private String zzde;
    private int zzfd;
    private String zzj;
    private String zzk;
    private JSONObject zzp;

    MediaTrack(long j, int i, String str, String str2, String str3, String str4, int i2, String str5) {
        this.id = j;
        this.type = i;
        this.zzk = str;
        this.zzde = str2;
        this.name = str3;
        this.zzdc = str4;
        this.zzfd = i2;
        this.zzj = str5;
        if (str5 == null) {
            this.zzp = null;
            return;
        }
        try {
            this.zzp = new JSONObject(this.zzj);
        } catch (JSONException unused) {
            this.zzp = null;
            this.zzj = null;
        }
    }

    public static class Builder {
        private final MediaTrack zzfe;

        public Builder(long j, int i) throws IllegalArgumentException {
            this.zzfe = new MediaTrack(j, i);
        }

        public Builder setContentId(String str) {
            this.zzfe.setContentId(str);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzfe.setContentType(str);
            return this;
        }

        public Builder setName(String str) {
            this.zzfe.setName(str);
            return this;
        }

        public Builder setLanguage(String str) {
            this.zzfe.setLanguage(str);
            return this;
        }

        public Builder setLanguage(Locale locale) {
            this.zzfe.setLanguage(zzdk.zza(locale));
            return this;
        }

        public Builder setSubtype(int i) throws IllegalArgumentException {
            this.zzfe.zze(i);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzfe.setCustomData(jSONObject);
            return this;
        }

        public MediaTrack build() {
            return this.zzfe;
        }
    }

    MediaTrack(JSONObject jSONObject) throws JSONException {
        this(0L, 0, null, null, null, null, -1, null);
        this.id = jSONObject.getLong("trackId");
        String string = jSONObject.getString("type");
        if ("TEXT".equals(string)) {
            this.type = 1;
        } else if ("AUDIO".equals(string)) {
            this.type = 2;
        } else if ("VIDEO".equals(string)) {
            this.type = 3;
        } else {
            String strValueOf = String.valueOf(string);
            throw new JSONException(strValueOf.length() != 0 ? "invalid type: ".concat(strValueOf) : new String("invalid type: "));
        }
        this.zzk = jSONObject.optString("trackContentId", null);
        this.zzde = jSONObject.optString("trackContentType", null);
        this.name = jSONObject.optString("name", null);
        this.zzdc = jSONObject.optString("language", null);
        if (jSONObject.has("subtype")) {
            String string2 = jSONObject.getString("subtype");
            if ("SUBTITLES".equals(string2)) {
                this.zzfd = 1;
            } else if ("CAPTIONS".equals(string2)) {
                this.zzfd = 2;
            } else if ("DESCRIPTIONS".equals(string2)) {
                this.zzfd = 3;
            } else if ("CHAPTERS".equals(string2)) {
                this.zzfd = 4;
            } else if ("METADATA".equals(string2)) {
                this.zzfd = 5;
            } else {
                this.zzfd = -1;
            }
        } else {
            this.zzfd = 0;
        }
        this.zzp = jSONObject.optJSONObject("customData");
    }

    MediaTrack(long j, int i) throws IllegalArgumentException {
        this(0L, 0, null, null, null, null, -1, null);
        this.id = j;
        if (i <= 0 || i > 3) {
            StringBuilder sb = new StringBuilder(24);
            sb.append("invalid type ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        this.type = i;
    }

    public final long getId() {
        return this.id;
    }

    public final int getType() {
        return this.type;
    }

    public final String getContentId() {
        return this.zzk;
    }

    public final void setContentId(String str) {
        this.zzk = str;
    }

    public final String getContentType() {
        return this.zzde;
    }

    public final void setContentType(String str) {
        this.zzde = str;
    }

    public final String getName() {
        return this.name;
    }

    final void setName(String str) {
        this.name = str;
    }

    public final String getLanguage() {
        return this.zzdc;
    }

    final void setLanguage(String str) {
        this.zzdc = str;
    }

    public final int getSubtype() {
        return this.zzfd;
    }

    final void zze(int i) throws IllegalArgumentException {
        if (i < 0 || i > 5) {
            StringBuilder sb = new StringBuilder(27);
            sb.append("invalid subtype ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i != 0 && this.type != 1) {
            throw new IllegalArgumentException("subtypes are only valid for text tracks");
        }
        this.zzfd = i;
    }

    public final JSONObject getCustomData() {
        return this.zzp;
    }

    final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("trackId", this.id);
            int i = this.type;
            if (i == 1) {
                jSONObject.put("type", "TEXT");
            } else if (i == 2) {
                jSONObject.put("type", "AUDIO");
            } else if (i == 3) {
                jSONObject.put("type", "VIDEO");
            }
            String str = this.zzk;
            if (str != null) {
                jSONObject.put("trackContentId", str);
            }
            String str2 = this.zzde;
            if (str2 != null) {
                jSONObject.put("trackContentType", str2);
            }
            String str3 = this.name;
            if (str3 != null) {
                jSONObject.put("name", str3);
            }
            if (!TextUtils.isEmpty(this.zzdc)) {
                jSONObject.put("language", this.zzdc);
            }
            int i2 = this.zzfd;
            if (i2 == 1) {
                jSONObject.put("subtype", "SUBTITLES");
            } else if (i2 == 2) {
                jSONObject.put("subtype", "CAPTIONS");
            } else if (i2 == 3) {
                jSONObject.put("subtype", "DESCRIPTIONS");
            } else if (i2 == 4) {
                jSONObject.put("subtype", "CHAPTERS");
            } else if (i2 == 5) {
                jSONObject.put("subtype", "METADATA");
            }
            JSONObject jSONObject2 = this.zzp;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaTrack)) {
            return false;
        }
        MediaTrack mediaTrack = (MediaTrack) obj;
        JSONObject jSONObject = this.zzp;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaTrack.zzp;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || JsonUtils.areJsonValuesEquivalent(jSONObject, jSONObject2)) && this.id == mediaTrack.id && this.type == mediaTrack.type && zzdk.zza(this.zzk, mediaTrack.zzk) && zzdk.zza(this.zzde, mediaTrack.zzde) && zzdk.zza(this.name, mediaTrack.name) && zzdk.zza(this.zzdc, mediaTrack.zzdc) && this.zzfd == mediaTrack.zzfd;
    }

    public final int hashCode() {
        return Objects.hashCode(Long.valueOf(this.id), Integer.valueOf(this.type), this.zzk, this.zzde, this.name, this.zzdc, Integer.valueOf(this.zzfd), String.valueOf(this.zzp));
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, getId());
        SafeParcelWriter.writeInt(parcel, 3, getType());
        SafeParcelWriter.writeString(parcel, 4, getContentId(), false);
        SafeParcelWriter.writeString(parcel, 5, getContentType(), false);
        SafeParcelWriter.writeString(parcel, 6, getName(), false);
        SafeParcelWriter.writeString(parcel, 7, getLanguage(), false);
        SafeParcelWriter.writeInt(parcel, 8, getSubtype());
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
