package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class VideoInfo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<VideoInfo> CREATOR = new zzcb();
    public static final int HDR_TYPE_DV = 3;
    public static final int HDR_TYPE_HDR = 4;
    public static final int HDR_TYPE_HDR10 = 2;
    public static final int HDR_TYPE_SDR = 1;
    public static final int HDR_TYPE_UNKNOWN = 0;
    private int height;
    private int width;
    private int zzgz;

    VideoInfo(int i, int i2, int i3) {
        this.width = i;
        this.height = i2;
        this.zzgz = i3;
    }

    public final int getWidth() {
        return this.width;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getHdrType() {
        return this.zzgz;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, getWidth());
        SafeParcelWriter.writeInt(parcel, 3, getHeight());
        SafeParcelWriter.writeInt(parcel, 4, getHdrType());
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VideoInfo)) {
            return false;
        }
        VideoInfo videoInfo = (VideoInfo) obj;
        return this.height == videoInfo.getHeight() && this.width == videoInfo.getWidth() && this.zzgz == videoInfo.getHdrType();
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.height), Integer.valueOf(this.width), Integer.valueOf(this.zzgz));
    }

    /* JADX WARN: Code duplicated, block: B:27:0x0050  */
    static VideoInfo zzi(JSONObject jSONObject) {
        byte b;
        if (jSONObject == null) {
            return null;
        }
        try {
            String string = jSONObject.getString("hdrType");
            int iHashCode = string.hashCode();
            int i = 3;
            if (iHashCode != 3218) {
                if (iHashCode != 103158) {
                    if (iHashCode != 113729) {
                        if (iHashCode == 99136405 && string.equals("hdr10")) {
                            b = 1;
                        } else {
                            b = -1;
                        }
                    } else if (string.equals("sdr")) {
                        b = 3;
                    } else {
                        b = -1;
                    }
                } else if (string.equals("hdr")) {
                    b = 2;
                } else {
                    b = -1;
                }
            } else if (string.equals("dv")) {
                b = 0;
            } else {
                b = -1;
            }
            if (b != 0) {
                if (b == 1) {
                    i = 2;
                } else if (b == 2) {
                    i = 4;
                } else if (b != 3) {
                    Log.d("VideoInfo", String.format(Locale.ROOT, "Unknown HDR type: %s", string));
                    i = 0;
                } else {
                    i = 1;
                }
            }
            return new VideoInfo(jSONObject.getInt("width"), jSONObject.getInt("height"), i);
        } catch (JSONException e) {
            Log.d("VideoInfo", String.format(Locale.ROOT, "Error while creating a VideoInfo instance from JSON: %s", e.getMessage()));
            return null;
        }
    }
}
