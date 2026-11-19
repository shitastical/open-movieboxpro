package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzdk;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class MediaQueueItem extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MediaQueueItem> CREATOR = new zzao();
    public static final double DEFAULT_PLAYBACK_DURATION = Double.POSITIVE_INFINITY;
    public static final int INVALID_ITEM_ID = 0;
    private boolean zzdp;
    private long[] zzds;
    private MediaInfo zzed;
    private int zzee;
    private double zzef;
    private double zzeg;
    private double zzeh;
    private String zzj;
    private JSONObject zzp;

    MediaQueueItem(MediaInfo mediaInfo, int i, boolean z, double d, double d2, double d3, long[] jArr, String str) {
        this.zzed = mediaInfo;
        this.zzee = i;
        this.zzdp = z;
        this.zzef = d;
        this.zzeg = d2;
        this.zzeh = d3;
        this.zzds = jArr;
        this.zzj = str;
        if (str == null) {
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
        private final MediaQueueItem zzei;

        public Builder(MediaInfo mediaInfo) throws IllegalArgumentException {
            this.zzei = new MediaQueueItem(mediaInfo);
        }

        public Builder(JSONObject jSONObject) throws JSONException {
            this.zzei = new MediaQueueItem(jSONObject);
        }

        public Builder(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
            this.zzei = new MediaQueueItem();
        }

        public Builder clearItemId() {
            this.zzei.zza(0);
            return this;
        }

        public Builder setAutoplay(boolean z) {
            this.zzei.zze(z);
            return this;
        }

        public Builder setStartTime(double d) throws IllegalArgumentException {
            this.zzei.zza(d);
            return this;
        }

        public Builder setPlaybackDuration(double d) {
            this.zzei.zzb(d);
            return this;
        }

        public Builder setPreloadTime(double d) throws IllegalArgumentException {
            this.zzei.zzc(d);
            return this;
        }

        public Builder setActiveTrackIds(long[] jArr) {
            this.zzei.zza(jArr);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzei.setCustomData(jSONObject);
            return this;
        }

        public MediaQueueItem build() {
            this.zzei.zzk();
            return this.zzei;
        }
    }

    private MediaQueueItem(MediaInfo mediaInfo) throws IllegalArgumentException {
        this(mediaInfo, 0, true, 0.0d, Double.POSITIVE_INFINITY, 0.0d, null, null);
        if (mediaInfo == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
    }

    MediaQueueItem(JSONObject jSONObject) throws JSONException {
        this(null, 0, true, 0.0d, Double.POSITIVE_INFINITY, 0.0d, null, null);
        zzf(jSONObject);
    }

    private MediaQueueItem(MediaQueueItem mediaQueueItem) throws IllegalArgumentException {
        this(mediaQueueItem.getMedia(), mediaQueueItem.getItemId(), mediaQueueItem.getAutoplay(), mediaQueueItem.getStartTime(), mediaQueueItem.getPlaybackDuration(), mediaQueueItem.getPreloadTime(), mediaQueueItem.getActiveTrackIds(), null);
        if (this.zzed == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
        this.zzp = mediaQueueItem.getCustomData();
    }

    public final boolean zzf(JSONObject jSONObject) throws JSONException {
        boolean z;
        long[] jArr;
        boolean z2;
        int i;
        boolean z3 = false;
        if (jSONObject.has("media")) {
            this.zzed = new MediaInfo(jSONObject.getJSONObject("media"));
            z = true;
        } else {
            z = false;
        }
        if (jSONObject.has("itemId") && this.zzee != (i = jSONObject.getInt("itemId"))) {
            this.zzee = i;
            z = true;
        }
        if (jSONObject.has("autoplay") && this.zzdp != (z2 = jSONObject.getBoolean("autoplay"))) {
            this.zzdp = z2;
            z = true;
        }
        if (jSONObject.has("startTime")) {
            double d = jSONObject.getDouble("startTime");
            if (Math.abs(d - this.zzef) > 1.0E-7d) {
                this.zzef = d;
                z = true;
            }
        }
        if (jSONObject.has("playbackDuration")) {
            double d2 = jSONObject.getDouble("playbackDuration");
            if (Math.abs(d2 - this.zzeg) > 1.0E-7d) {
                this.zzeg = d2;
                z = true;
            }
        }
        if (jSONObject.has("preloadTime")) {
            double d3 = jSONObject.getDouble("preloadTime");
            if (Math.abs(d3 - this.zzeh) > 1.0E-7d) {
                this.zzeh = d3;
                z = true;
            }
        }
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            jArr = new long[length];
            for (int i2 = 0; i2 < length; i2++) {
                jArr[i2] = jSONArray.getLong(i2);
            }
            long[] jArr2 = this.zzds;
            if (jArr2 == null || jArr2.length != length) {
                z3 = true;
                break;
            }
            for (int i3 = 0; i3 < length; i3++) {
                if (this.zzds[i3] != jArr[i3]) {
                    z3 = true;
                    break;
                }
            }
        } else {
            jArr = null;
        }
        if (z3) {
            this.zzds = jArr;
            z = true;
        }
        if (!jSONObject.has("customData")) {
            return z;
        }
        this.zzp = jSONObject.getJSONObject("customData");
        return true;
    }

    public MediaInfo getMedia() {
        return this.zzed;
    }

    public int getItemId() {
        return this.zzee;
    }

    final void zza(int i) {
        this.zzee = 0;
    }

    public boolean getAutoplay() {
        return this.zzdp;
    }

    final void zze(boolean z) {
        this.zzdp = z;
    }

    public double getStartTime() {
        return this.zzef;
    }

    final void zza(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < 0.0d) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        }
        this.zzef = d;
    }

    public double getPlaybackDuration() {
        return this.zzeg;
    }

    final void zzb(double d) throws IllegalArgumentException {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        }
        this.zzeg = d;
    }

    public double getPreloadTime() {
        return this.zzeh;
    }

    final void zzc(double d) throws IllegalArgumentException {
        if (Double.isNaN(d) || d < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or NaN.");
        }
        this.zzeh = d;
    }

    public long[] getActiveTrackIds() {
        return this.zzds;
    }

    final void zza(long[] jArr) {
        this.zzds = jArr;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    final void zzk() throws IllegalArgumentException {
        if (this.zzed == null) {
            throw new IllegalArgumentException("media cannot be null.");
        }
        if (Double.isNaN(this.zzef) || this.zzef < 0.0d) {
            throw new IllegalArgumentException("startTime cannot be negative or NaN.");
        }
        if (Double.isNaN(this.zzeg)) {
            throw new IllegalArgumentException("playbackDuration cannot be NaN.");
        }
        if (Double.isNaN(this.zzeh) || this.zzeh < 0.0d) {
            throw new IllegalArgumentException("preloadTime cannot be negative or Nan.");
        }
    }

    public final JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("media", this.zzed.toJson());
            int i = this.zzee;
            if (i != 0) {
                jSONObject.put("itemId", i);
            }
            jSONObject.put("autoplay", this.zzdp);
            jSONObject.put("startTime", this.zzef);
            double d = this.zzeg;
            if (d != Double.POSITIVE_INFINITY) {
                jSONObject.put("playbackDuration", d);
            }
            jSONObject.put("preloadTime", this.zzeh);
            if (this.zzds != null) {
                JSONArray jSONArray = new JSONArray();
                for (long j : this.zzds) {
                    jSONArray.put(j);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            JSONObject jSONObject2 = this.zzp;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaQueueItem)) {
            return false;
        }
        MediaQueueItem mediaQueueItem = (MediaQueueItem) obj;
        JSONObject jSONObject = this.zzp;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaQueueItem.zzp;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || JsonUtils.areJsonValuesEquivalent(jSONObject, jSONObject2)) && zzdk.zza(this.zzed, mediaQueueItem.zzed) && this.zzee == mediaQueueItem.zzee && this.zzdp == mediaQueueItem.zzdp && this.zzef == mediaQueueItem.zzef && this.zzeg == mediaQueueItem.zzeg && this.zzeh == mediaQueueItem.zzeh && Arrays.equals(this.zzds, mediaQueueItem.zzds);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzed, Integer.valueOf(this.zzee), Boolean.valueOf(this.zzdp), Double.valueOf(this.zzef), Double.valueOf(this.zzeg), Double.valueOf(this.zzeh), Integer.valueOf(Arrays.hashCode(this.zzds)), String.valueOf(this.zzp));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMedia(), i, false);
        SafeParcelWriter.writeInt(parcel, 3, getItemId());
        SafeParcelWriter.writeBoolean(parcel, 4, getAutoplay());
        SafeParcelWriter.writeDouble(parcel, 5, getStartTime());
        SafeParcelWriter.writeDouble(parcel, 6, getPlaybackDuration());
        SafeParcelWriter.writeDouble(parcel, 7, getPreloadTime());
        SafeParcelWriter.writeLongArray(parcel, 8, getActiveTrackIds(), false);
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
