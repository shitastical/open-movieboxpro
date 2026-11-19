package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzdk;
import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class MediaInfo extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<MediaInfo> CREATOR = new zzaj();
    public static final int STREAM_TYPE_BUFFERED = 1;
    public static final int STREAM_TYPE_INVALID = -1;
    public static final int STREAM_TYPE_LIVE = 2;
    public static final int STREAM_TYPE_NONE = 0;
    public static final long UNKNOWN_DURATION = -1;
    private int streamType;
    private String zzde;
    private MediaMetadata zzdf;
    private long zzdg;
    private List<MediaTrack> zzdh;
    private TextTrackStyle zzdi;
    private List<AdBreakInfo> zzdj;
    private List<AdBreakClipInfo> zzdk;
    private String zzdl;
    private VastAdsRequest zzdm;
    private long zzdn;
    private String zzj;
    private final String zzk;
    private JSONObject zzp;

    MediaInfo(String str, int i, String str2, MediaMetadata mediaMetadata, long j, List<MediaTrack> list, TextTrackStyle textTrackStyle, String str3, List<AdBreakInfo> list2, List<AdBreakClipInfo> list3, String str4, VastAdsRequest vastAdsRequest, long j2) {
        this.zzk = str;
        this.streamType = i;
        this.zzde = str2;
        this.zzdf = mediaMetadata;
        this.zzdg = j;
        this.zzdh = list;
        this.zzdi = textTrackStyle;
        this.zzj = str3;
        if (str3 != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
        this.zzdj = list2;
        this.zzdk = list3;
        this.zzdl = str4;
        this.zzdm = vastAdsRequest;
        this.zzdn = j2;
    }

    public static class Builder {
        private final MediaInfo zzdo;

        public Builder(String str) throws IllegalArgumentException {
            this.zzdo = new MediaInfo(str);
        }

        public Builder(String str, String str2) throws IllegalArgumentException {
            this.zzdo = new MediaInfo(str, str2);
        }

        public Builder setStreamType(int i) throws IllegalArgumentException {
            this.zzdo.setStreamType(i);
            return this;
        }

        public Builder setContentType(String str) {
            this.zzdo.setContentType(str);
            return this;
        }

        public Builder setMetadata(MediaMetadata mediaMetadata) {
            this.zzdo.zza(mediaMetadata);
            return this;
        }

        public Builder setStreamDuration(long j) throws IllegalArgumentException {
            this.zzdo.zza(j);
            return this;
        }

        public Builder setCustomData(JSONObject jSONObject) {
            this.zzdo.setCustomData(jSONObject);
            return this;
        }

        public Builder setMediaTracks(List<MediaTrack> list) {
            this.zzdo.zza(list);
            return this;
        }

        public Builder setTextTrackStyle(TextTrackStyle textTrackStyle) {
            this.zzdo.setTextTrackStyle(textTrackStyle);
            return this;
        }

        public Builder setEntity(String str) {
            this.zzdo.zzd(str);
            return this;
        }

        public Builder setVmapAdsRequest(VastAdsRequest vastAdsRequest) {
            this.zzdo.zza(vastAdsRequest);
            return this;
        }

        public Builder setAdBreaks(List<AdBreakInfo> list) {
            this.zzdo.zzb(list);
            return this;
        }

        public Builder setAdBreakClips(List<AdBreakClipInfo> list) {
            this.zzdo.zzc(list);
            return this;
        }

        public MediaInfo build() {
            return this.zzdo;
        }
    }

    MediaInfo(String str) throws IllegalArgumentException {
        this(str, -1, null, null, -1L, null, null, null, null, null, null, null, -1L);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(String str, String str2) throws IllegalArgumentException {
        this(str, -1, null, null, -1L, null, null, null, null, null, str2, null, -1L);
        if (str == null) {
            throw new IllegalArgumentException("contentID cannot be null");
        }
    }

    MediaInfo(JSONObject jSONObject) throws JSONException {
        MediaInfo mediaInfo;
        this(jSONObject.getString("contentId"), -1, null, null, -1L, null, null, null, null, null, null, null, -1L);
        String string = jSONObject.getString("streamType");
        if ("NONE".equals(string)) {
            mediaInfo = this;
            mediaInfo.streamType = 0;
        } else {
            mediaInfo = this;
            if ("BUFFERED".equals(string)) {
                mediaInfo.streamType = 1;
            } else if ("LIVE".equals(string)) {
                mediaInfo.streamType = 2;
            } else {
                mediaInfo.streamType = -1;
            }
        }
        mediaInfo.zzde = jSONObject.optString("contentType", null);
        if (jSONObject.has("metadata")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("metadata");
            MediaMetadata mediaMetadata = new MediaMetadata(jSONObject2.getInt("metadataType"));
            mediaInfo.zzdf = mediaMetadata;
            mediaMetadata.zze(jSONObject2);
        }
        mediaInfo.zzdg = -1L;
        if (jSONObject.has("duration") && !jSONObject.isNull("duration")) {
            double dOptDouble = jSONObject.optDouble("duration", 0.0d);
            if (!Double.isNaN(dOptDouble) && !Double.isInfinite(dOptDouble)) {
                mediaInfo.zzdg = (long) (dOptDouble * 1000.0d);
            }
        }
        if (jSONObject.has("tracks")) {
            mediaInfo.zzdh = new ArrayList();
            JSONArray jSONArray = jSONObject.getJSONArray("tracks");
            for (int i = 0; i < jSONArray.length(); i++) {
                mediaInfo.zzdh.add(new MediaTrack(jSONArray.getJSONObject(i)));
            }
        } else {
            mediaInfo.zzdh = null;
        }
        if (jSONObject.has("textTrackStyle")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("textTrackStyle");
            TextTrackStyle textTrackStyle = new TextTrackStyle();
            textTrackStyle.zze(jSONObject3);
            mediaInfo.zzdi = textTrackStyle;
        } else {
            mediaInfo.zzdi = null;
        }
        zzd(jSONObject);
        mediaInfo.zzp = jSONObject.optJSONObject("customData");
        if (jSONObject.has("entity")) {
            mediaInfo.zzdl = jSONObject.getString("entity");
        }
        mediaInfo.zzdm = VastAdsRequest.fromJson(jSONObject.optJSONObject("vmapAdsRequest"));
    }

    public String getContentId() {
        return this.zzk;
    }

    final void setStreamType(int i) throws IllegalArgumentException {
        if (i < -1 || i > 2) {
            throw new IllegalArgumentException("invalid stream type");
        }
        this.streamType = i;
    }

    public int getStreamType() {
        return this.streamType;
    }

    final void setContentType(String str) {
        this.zzde = str;
    }

    public String getContentType() {
        return this.zzde;
    }

    final void zza(MediaMetadata mediaMetadata) {
        this.zzdf = mediaMetadata;
    }

    public MediaMetadata getMetadata() {
        return this.zzdf;
    }

    final void zza(long j) throws IllegalArgumentException {
        if (j < 0 && j != -1) {
            throw new IllegalArgumentException("Invalid stream duration");
        }
        this.zzdg = j;
    }

    public long getStreamDuration() {
        return this.zzdg;
    }

    final void zza(List<MediaTrack> list) {
        this.zzdh = list;
    }

    public List<MediaTrack> getMediaTracks() {
        return this.zzdh;
    }

    public void setTextTrackStyle(TextTrackStyle textTrackStyle) {
        this.zzdi = textTrackStyle;
    }

    public TextTrackStyle getTextTrackStyle() {
        return this.zzdi;
    }

    final void setCustomData(JSONObject jSONObject) {
        this.zzp = jSONObject;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public List<AdBreakInfo> getAdBreaks() {
        List<AdBreakInfo> list = this.zzdj;
        if (list == null) {
            return null;
        }
        return DesugarCollections.unmodifiableList(list);
    }

    public List<AdBreakClipInfo> getAdBreakClips() {
        List<AdBreakClipInfo> list = this.zzdk;
        if (list == null) {
            return null;
        }
        return DesugarCollections.unmodifiableList(list);
    }

    public final void zzb(List<AdBreakInfo> list) {
        this.zzdj = list;
    }

    final void zzc(List<AdBreakClipInfo> list) {
        this.zzdk = list;
    }

    final void zzd(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has("breaks")) {
            JSONArray jSONArray = jSONObject.getJSONArray("breaks");
            this.zzdj = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                AdBreakInfo adBreakInfoZzb = AdBreakInfo.zzb(jSONArray.getJSONObject(i));
                if (adBreakInfoZzb != null) {
                    this.zzdj.add(adBreakInfoZzb);
                } else {
                    this.zzdj.clear();
                    break;
                }
            }
        }
        if (jSONObject.has("breakClips")) {
            JSONArray jSONArray2 = jSONObject.getJSONArray("breakClips");
            this.zzdk = new ArrayList(jSONArray2.length());
            for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                AdBreakClipInfo adBreakClipInfoZza = AdBreakClipInfo.zza(jSONArray2.getJSONObject(i2));
                if (adBreakClipInfoZza != null) {
                    this.zzdk.add(adBreakClipInfoZza);
                } else {
                    this.zzdk.clear();
                    return;
                }
            }
        }
    }

    public String getEntity() {
        return this.zzdl;
    }

    public final void zzd(String str) {
        this.zzdl = str;
    }

    public VastAdsRequest getVmapAdsRequest() {
        return this.zzdm;
    }

    public final void zza(VastAdsRequest vastAdsRequest) {
        this.zzdm = vastAdsRequest;
    }

    public final long zzj() {
        return this.zzdn;
    }

    public final JSONObject toJson() {
        String str;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("contentId", this.zzk);
            int i = this.streamType;
            if (i == 1) {
                str = "BUFFERED";
            } else if (i == 2) {
                str = "LIVE";
            } else {
                str = "NONE";
            }
            jSONObject.put("streamType", str);
            String str2 = this.zzde;
            if (str2 != null) {
                jSONObject.put("contentType", str2);
            }
            MediaMetadata mediaMetadata = this.zzdf;
            if (mediaMetadata != null) {
                jSONObject.put("metadata", mediaMetadata.toJson());
            }
            long j = this.zzdg;
            if (j <= -1) {
                jSONObject.put("duration", JSONObject.NULL);
            } else {
                jSONObject.put("duration", j / 1000.0d);
            }
            if (this.zzdh != null) {
                JSONArray jSONArray = new JSONArray();
                Iterator<MediaTrack> it = this.zzdh.iterator();
                while (it.hasNext()) {
                    jSONArray.put(it.next().toJson());
                }
                jSONObject.put("tracks", jSONArray);
            }
            TextTrackStyle textTrackStyle = this.zzdi;
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.toJson());
            }
            JSONObject jSONObject2 = this.zzp;
            if (jSONObject2 != null) {
                jSONObject.put("customData", jSONObject2);
            }
            String str3 = this.zzdl;
            if (str3 != null) {
                jSONObject.put("entity", str3);
            }
            if (this.zzdj != null) {
                JSONArray jSONArray2 = new JSONArray();
                Iterator<AdBreakInfo> it2 = this.zzdj.iterator();
                while (it2.hasNext()) {
                    jSONArray2.put(it2.next().toJson());
                }
                jSONObject.put("breaks", jSONArray2);
            }
            if (this.zzdk != null) {
                JSONArray jSONArray3 = new JSONArray();
                Iterator<AdBreakClipInfo> it3 = this.zzdk.iterator();
                while (it3.hasNext()) {
                    jSONArray3.put(it3.next().toJson());
                }
                jSONObject.put("breakClips", jSONArray3);
            }
            VastAdsRequest vastAdsRequest = this.zzdm;
            if (vastAdsRequest != null) {
                jSONObject.put("vmapAdsRequest", vastAdsRequest.toJson());
            }
            long j2 = this.zzdn;
            if (j2 != -1) {
                jSONObject.put("startAbsoluteTime", j2 / 1000.0d);
            }
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaInfo)) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        JSONObject jSONObject = this.zzp;
        boolean z = jSONObject == null;
        JSONObject jSONObject2 = mediaInfo.zzp;
        if (z != (jSONObject2 == null)) {
            return false;
        }
        return (jSONObject == null || jSONObject2 == null || JsonUtils.areJsonValuesEquivalent(jSONObject, jSONObject2)) && zzdk.zza(this.zzk, mediaInfo.zzk) && this.streamType == mediaInfo.streamType && zzdk.zza(this.zzde, mediaInfo.zzde) && zzdk.zza(this.zzdf, mediaInfo.zzdf) && this.zzdg == mediaInfo.zzdg && zzdk.zza(this.zzdh, mediaInfo.zzdh) && zzdk.zza(this.zzdi, mediaInfo.zzdi) && zzdk.zza(this.zzdj, mediaInfo.zzdj) && zzdk.zza(this.zzdk, mediaInfo.zzdk) && zzdk.zza(this.zzdl, mediaInfo.zzdl) && zzdk.zza(this.zzdm, mediaInfo.zzdm) && this.zzdn == mediaInfo.zzdn;
    }

    public int hashCode() {
        return Objects.hashCode(this.zzk, Integer.valueOf(this.streamType), this.zzde, this.zzdf, Long.valueOf(this.zzdg), String.valueOf(this.zzp), this.zzdh, this.zzdi, this.zzdj, this.zzdk, this.zzdl, this.zzdm, Long.valueOf(this.zzdn));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, getContentId(), false);
        SafeParcelWriter.writeInt(parcel, 3, getStreamType());
        SafeParcelWriter.writeString(parcel, 4, getContentType(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getMetadata(), i, false);
        SafeParcelWriter.writeLong(parcel, 6, getStreamDuration());
        SafeParcelWriter.writeTypedList(parcel, 7, getMediaTracks(), false);
        SafeParcelWriter.writeParcelable(parcel, 8, getTextTrackStyle(), i, false);
        SafeParcelWriter.writeString(parcel, 9, this.zzj, false);
        SafeParcelWriter.writeTypedList(parcel, 10, getAdBreaks(), false);
        SafeParcelWriter.writeTypedList(parcel, 11, getAdBreakClips(), false);
        SafeParcelWriter.writeString(parcel, 12, getEntity(), false);
        SafeParcelWriter.writeParcelable(parcel, 13, getVmapAdsRequest(), i, false);
        SafeParcelWriter.writeLong(parcel, 14, this.zzdn);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
