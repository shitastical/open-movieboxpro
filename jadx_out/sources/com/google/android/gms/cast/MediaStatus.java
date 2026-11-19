package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.JsonUtils;
import com.google.android.gms.internal.cast.zzdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public class MediaStatus extends AbstractSafeParcelable {
    public static final long COMMAND_PAUSE = 1;
    public static final long COMMAND_SEEK = 2;
    public static final long COMMAND_SET_VOLUME = 4;
    public static final long COMMAND_SKIP_BACKWARD = 32;
    public static final long COMMAND_SKIP_FORWARD = 16;
    public static final long COMMAND_TOGGLE_MUTE = 8;
    public static final Parcelable.Creator<MediaStatus> CREATOR = new zzav();
    public static final int IDLE_REASON_CANCELED = 2;
    public static final int IDLE_REASON_ERROR = 4;
    public static final int IDLE_REASON_FINISHED = 1;
    public static final int IDLE_REASON_INTERRUPTED = 3;
    public static final int IDLE_REASON_NONE = 0;
    public static final int PLAYER_STATE_BUFFERING = 4;
    public static final int PLAYER_STATE_IDLE = 1;
    public static final int PLAYER_STATE_PAUSED = 3;
    public static final int PLAYER_STATE_PLAYING = 2;
    public static final int PLAYER_STATE_UNKNOWN = 0;
    public static final int REPEAT_MODE_REPEAT_ALL = 1;
    public static final int REPEAT_MODE_REPEAT_ALL_AND_SHUFFLE = 3;
    public static final int REPEAT_MODE_REPEAT_OFF = 0;
    public static final int REPEAT_MODE_REPEAT_SINGLE = 2;
    private MediaInfo zzdo;
    private double zzdr;
    private long[] zzds;
    private long zzen;
    private int zzeo;
    private int zzep;
    private int zzeq;
    private long zzer;
    private long zzes;
    private double zzet;
    private boolean zzeu;
    private int zzev;
    private int zzew;
    private int zzex;
    private final ArrayList<MediaQueueItem> zzey;
    private boolean zzez;
    private AdBreakStatus zzfa;
    private VideoInfo zzfb;
    private final SparseArray<Integer> zzfc;
    private String zzj;
    private JSONObject zzp;

    MediaStatus(MediaInfo mediaInfo, long j, int i, double d, int i2, int i3, long j2, long j3, double d2, boolean z, long[] jArr, int i4, int i5, String str, int i6, List<MediaQueueItem> list, boolean z2, AdBreakStatus adBreakStatus, VideoInfo videoInfo) {
        this.zzey = new ArrayList<>();
        this.zzfc = new SparseArray<>();
        this.zzdo = mediaInfo;
        this.zzen = j;
        this.zzeo = i;
        this.zzdr = d;
        this.zzep = i2;
        this.zzeq = i3;
        this.zzer = j2;
        this.zzes = j3;
        this.zzet = d2;
        this.zzeu = z;
        this.zzds = jArr;
        this.zzev = i4;
        this.zzew = i5;
        this.zzj = str;
        if (str != null) {
            try {
                this.zzp = new JSONObject(this.zzj);
            } catch (JSONException unused) {
                this.zzp = null;
                this.zzj = null;
            }
        } else {
            this.zzp = null;
        }
        this.zzex = i6;
        if (list != null && !list.isEmpty()) {
            zza((MediaQueueItem[]) list.toArray(new MediaQueueItem[list.size()]));
        }
        this.zzez = z2;
        this.zzfa = adBreakStatus;
        this.zzfb = videoInfo;
    }

    private static boolean zza(int i, int i2, int i3, int i4) {
        if (i != 1) {
            return false;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                return i4 != 2;
            }
            if (i2 != 3) {
                return true;
            }
        }
        return i3 == 0;
    }

    public MediaStatus(JSONObject jSONObject) throws JSONException {
        this(null, 0L, 0, 0.0d, 0, 0, 0L, 0L, 0.0d, false, null, 0, 0, null, 0, null, false, null, null);
        zza(jSONObject, 0);
    }

    public final long zzp() {
        return this.zzen;
    }

    public int getPlayerState() {
        return this.zzep;
    }

    public int getIdleReason() {
        return this.zzeq;
    }

    public double getPlaybackRate() {
        return this.zzdr;
    }

    public MediaInfo getMediaInfo() {
        return this.zzdo;
    }

    public long getStreamPosition() {
        return this.zzer;
    }

    public boolean isMediaCommandSupported(long j) {
        return (j & this.zzes) != 0;
    }

    public double getStreamVolume() {
        return this.zzet;
    }

    public boolean isMute() {
        return this.zzeu;
    }

    public long[] getActiveTrackIds() {
        return this.zzds;
    }

    public JSONObject getCustomData() {
        return this.zzp;
    }

    public int getCurrentItemId() {
        return this.zzeo;
    }

    public int getLoadingItemId() {
        return this.zzev;
    }

    public int getPreloadedItemId() {
        return this.zzew;
    }

    public int getQueueRepeatMode() {
        return this.zzex;
    }

    public List<MediaQueueItem> getQueueItems() {
        return this.zzey;
    }

    public int getQueueItemCount() {
        return this.zzey.size();
    }

    public MediaQueueItem getQueueItemById(int i) {
        return getItemById(i);
    }

    public MediaQueueItem getQueueItem(int i) {
        return getItemByIndex(i);
    }

    public boolean isPlayingAd() {
        return this.zzez;
    }

    public final void zzf(boolean z) {
        this.zzez = z;
    }

    public AdBreakStatus getAdBreakStatus() {
        return this.zzfa;
    }

    public VideoInfo getVideoInfo() {
        return this.zzfb;
    }

    /* JADX WARN: Code duplicated, block: B:161:0x0252  */
    public final int zza(JSONObject jSONObject, int i) throws JSONException {
        int i2;
        long[] jArr;
        boolean z;
        MediaInfo mediaInfo;
        boolean zZzf;
        MediaInfo mediaInfo2;
        int i3;
        int i4;
        long j = jSONObject.getLong("mediaSessionId");
        if (j != this.zzen) {
            this.zzen = j;
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (jSONObject.has("playerState")) {
            String string = jSONObject.getString("playerState");
            int i5 = 4;
            if (string.equals("IDLE")) {
                i4 = 1;
            } else if (string.equals("PLAYING")) {
                i4 = 2;
            } else if (string.equals("PAUSED")) {
                i4 = 3;
            } else {
                i4 = string.equals("BUFFERING") ? 4 : 0;
            }
            if (i4 != this.zzep) {
                this.zzep = i4;
                i2 |= 2;
            }
            if (i4 == 1 && jSONObject.has("idleReason")) {
                String string2 = jSONObject.getString("idleReason");
                if (string2.equals("CANCELLED")) {
                    i5 = 2;
                } else if (string2.equals("INTERRUPTED")) {
                    i5 = 3;
                } else if (string2.equals("FINISHED")) {
                    i5 = 1;
                } else if (!string2.equals("ERROR")) {
                    i5 = 0;
                }
                if (i5 != this.zzeq) {
                    this.zzeq = i5;
                    i2 |= 2;
                }
            }
        }
        if (jSONObject.has("playbackRate")) {
            double d = jSONObject.getDouble("playbackRate");
            if (this.zzdr != d) {
                this.zzdr = d;
                i2 |= 2;
            }
        }
        if (jSONObject.has("currentTime")) {
            long j2 = (long) (jSONObject.getDouble("currentTime") * 1000.0d);
            if (j2 != this.zzer) {
                this.zzer = j2;
                i2 |= 2;
            }
            i2 |= 128;
        }
        if (jSONObject.has("supportedMediaCommands")) {
            long j3 = jSONObject.getLong("supportedMediaCommands");
            if (j3 != this.zzes) {
                this.zzes = j3;
                i2 |= 2;
            }
        }
        if (jSONObject.has(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME) && (i & 1) == 0) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME);
            double d2 = jSONObject2.getDouble(FirebaseAnalytics.Param.LEVEL);
            if (d2 != this.zzet) {
                this.zzet = d2;
                i2 |= 2;
            }
            boolean z2 = jSONObject2.getBoolean("muted");
            if (z2 != this.zzeu) {
                this.zzeu = z2;
                i2 |= 2;
            }
        }
        Integer num = null;
        if (jSONObject.has("activeTrackIds")) {
            JSONArray jSONArray = jSONObject.getJSONArray("activeTrackIds");
            int length = jSONArray.length();
            jArr = new long[length];
            for (int i6 = 0; i6 < length; i6++) {
                jArr[i6] = jSONArray.getLong(i6);
            }
            long[] jArr2 = this.zzds;
            if (jArr2 == null || jArr2.length != length) {
                z = true;
                break;
            }
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    z = false;
                    break;
                }
                if (this.zzds[i7] != jArr[i7]) {
                    z = true;
                    break;
                }
                i7++;
            }
            if (z) {
                this.zzds = jArr;
            }
        } else {
            jArr = null;
            z = this.zzds != null;
        }
        if (z) {
            this.zzds = jArr;
            i2 |= 2;
        }
        if (jSONObject.has("customData")) {
            this.zzp = jSONObject.getJSONObject("customData");
            this.zzj = null;
            i2 |= 2;
        }
        if (jSONObject.has("media")) {
            JSONObject jSONObject3 = jSONObject.getJSONObject("media");
            MediaInfo mediaInfo3 = new MediaInfo(jSONObject3);
            MediaInfo mediaInfo4 = this.zzdo;
            if (mediaInfo4 == null || (mediaInfo4 != null && !mediaInfo4.equals(mediaInfo3))) {
                this.zzdo = mediaInfo3;
                i2 |= 2;
            }
            if (jSONObject3.has("metadata")) {
                i2 |= 4;
            }
        }
        if (jSONObject.has("currentItemId") && this.zzeo != (i3 = jSONObject.getInt("currentItemId"))) {
            this.zzeo = i3;
            i2 |= 2;
        }
        int iOptInt = jSONObject.optInt("preloadedItemId", 0);
        if (this.zzew != iOptInt) {
            this.zzew = iOptInt;
            i2 |= 16;
        }
        int iOptInt2 = jSONObject.optInt("loadingItemId", 0);
        if (this.zzev != iOptInt2) {
            this.zzev = iOptInt2;
            i2 |= 2;
        }
        MediaInfo mediaInfo5 = this.zzdo;
        if (!zza(this.zzep, this.zzeq, this.zzev, mediaInfo5 == null ? -1 : mediaInfo5.getStreamType())) {
            if (jSONObject.has("repeatMode")) {
                String string3 = jSONObject.getString("repeatMode");
                if (string3 != null) {
                    string3.hashCode();
                    switch (string3) {
                        case "REPEAT_ALL_AND_SHUFFLE":
                            num = 3;
                            break;
                        case "REPEAT_SINGLE":
                            num = 2;
                            break;
                        case "REPEAT_ALL":
                            num = 1;
                            break;
                        case "REPEAT_OFF":
                            num = 0;
                            break;
                    }
                }
                int iIntValue = num == null ? this.zzex : num.intValue();
                Integer numValueOf = Integer.valueOf(iIntValue);
                int i8 = this.zzex;
                numValueOf.getClass();
                if (i8 != iIntValue) {
                    numValueOf.getClass();
                    this.zzex = iIntValue;
                    zZzf = true;
                } else {
                    zZzf = false;
                }
            } else {
                zZzf = false;
            }
            if (jSONObject.has(FirebaseAnalytics.Param.ITEMS)) {
                JSONArray jSONArray2 = jSONObject.getJSONArray(FirebaseAnalytics.Param.ITEMS);
                int length2 = jSONArray2.length();
                SparseArray sparseArray = new SparseArray();
                for (int i9 = 0; i9 < length2; i9++) {
                    sparseArray.put(i9, Integer.valueOf(jSONArray2.getJSONObject(i9).getInt("itemId")));
                }
                MediaQueueItem[] mediaQueueItemArr = new MediaQueueItem[length2];
                for (int i10 = 0; i10 < length2; i10++) {
                    Integer num2 = (Integer) sparseArray.get(i10);
                    JSONObject jSONObject4 = jSONArray2.getJSONObject(i10);
                    MediaQueueItem itemById = getItemById(num2.intValue());
                    if (itemById != null) {
                        zZzf |= itemById.zzf(jSONObject4);
                        mediaQueueItemArr[i10] = itemById;
                        if (i10 != getIndexById(num2.intValue()).intValue()) {
                        }
                    } else if (num2.intValue() == this.zzeo && (mediaInfo2 = this.zzdo) != null) {
                        MediaQueueItem mediaQueueItemBuild = new MediaQueueItem.Builder(mediaInfo2).build();
                        mediaQueueItemArr[i10] = mediaQueueItemBuild;
                        mediaQueueItemBuild.zzf(jSONObject4);
                    } else {
                        mediaQueueItemArr[i10] = new MediaQueueItem(jSONObject4);
                    }
                    zZzf = true;
                }
                if (this.zzey.size() != length2) {
                    zZzf = true;
                }
                zza(mediaQueueItemArr);
            }
            if (zZzf) {
                i2 |= 8;
            }
        } else {
            this.zzeo = 0;
            this.zzev = 0;
            this.zzew = 0;
            if (!this.zzey.isEmpty()) {
                this.zzex = 0;
                this.zzey.clear();
                this.zzfc.clear();
                i2 |= 8;
            }
        }
        AdBreakStatus adBreakStatusZzc = AdBreakStatus.zzc(jSONObject.optJSONObject("breakStatus"));
        AdBreakStatus adBreakStatus = this.zzfa;
        if ((adBreakStatus == null && adBreakStatusZzc != null) || (adBreakStatus != null && !adBreakStatus.equals(adBreakStatusZzc))) {
            this.zzez = adBreakStatusZzc != null;
            this.zzfa = adBreakStatusZzc;
            i2 |= 32;
        }
        VideoInfo videoInfoZzi = VideoInfo.zzi(jSONObject.optJSONObject("videoInfo"));
        VideoInfo videoInfo = this.zzfb;
        if ((videoInfo == null && videoInfoZzi != null) || (videoInfo != null && !videoInfo.equals(videoInfoZzi))) {
            this.zzfb = videoInfoZzi;
            i2 |= 64;
        }
        if (!jSONObject.has("breakInfo") || (mediaInfo = this.zzdo) == null) {
            return i2;
        }
        mediaInfo.zzd(jSONObject.getJSONObject("breakInfo"));
        return i2 | 2;
    }

    public final boolean zzq() {
        MediaInfo mediaInfo = this.zzdo;
        return zza(this.zzep, this.zzeq, this.zzev, mediaInfo == null ? -1 : mediaInfo.getStreamType());
    }

    public MediaQueueItem getItemById(int i) {
        Integer num = this.zzfc.get(i);
        if (num == null) {
            return null;
        }
        return this.zzey.get(num.intValue());
    }

    public MediaQueueItem getItemByIndex(int i) {
        if (i < 0 || i >= this.zzey.size()) {
            return null;
        }
        return this.zzey.get(i);
    }

    public Integer getIndexById(int i) {
        return this.zzfc.get(i);
    }

    private final void zza(MediaQueueItem[] mediaQueueItemArr) {
        this.zzey.clear();
        this.zzfc.clear();
        for (int i = 0; i < mediaQueueItemArr.length; i++) {
            MediaQueueItem mediaQueueItem = mediaQueueItemArr[i];
            this.zzey.add(mediaQueueItem);
            this.zzfc.put(mediaQueueItem.getItemId(), Integer.valueOf(i));
        }
    }

    public boolean equals(Object obj) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaStatus)) {
            return false;
        }
        MediaStatus mediaStatus = (MediaStatus) obj;
        return (this.zzp == null) == (mediaStatus.zzp == null) && this.zzen == mediaStatus.zzen && this.zzeo == mediaStatus.zzeo && this.zzdr == mediaStatus.zzdr && this.zzep == mediaStatus.zzep && this.zzeq == mediaStatus.zzeq && this.zzer == mediaStatus.zzer && this.zzet == mediaStatus.zzet && this.zzeu == mediaStatus.zzeu && this.zzev == mediaStatus.zzev && this.zzew == mediaStatus.zzew && this.zzex == mediaStatus.zzex && Arrays.equals(this.zzds, mediaStatus.zzds) && zzdk.zza(Long.valueOf(this.zzes), Long.valueOf(mediaStatus.zzes)) && zzdk.zza(this.zzey, mediaStatus.zzey) && zzdk.zza(this.zzdo, mediaStatus.zzdo) && ((jSONObject = this.zzp) == null || (jSONObject2 = mediaStatus.zzp) == null || JsonUtils.areJsonValuesEquivalent(jSONObject, jSONObject2)) && this.zzez == mediaStatus.isPlayingAd() && zzdk.zza(this.zzfa, mediaStatus.zzfa) && zzdk.zza(this.zzfb, mediaStatus.zzfb) && zzdk.zza(null, null) && Objects.equal(null, null);
    }

    public int hashCode() {
        return Objects.hashCode(this.zzdo, Long.valueOf(this.zzen), Integer.valueOf(this.zzeo), Double.valueOf(this.zzdr), Integer.valueOf(this.zzep), Integer.valueOf(this.zzeq), Long.valueOf(this.zzer), Long.valueOf(this.zzes), Double.valueOf(this.zzet), Boolean.valueOf(this.zzeu), Integer.valueOf(Arrays.hashCode(this.zzds)), Integer.valueOf(this.zzev), Integer.valueOf(this.zzew), String.valueOf(this.zzp), Integer.valueOf(this.zzex), this.zzey, Boolean.valueOf(this.zzez), this.zzfa, this.zzfb, null, null);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        JSONObject jSONObject = this.zzp;
        this.zzj = jSONObject == null ? null : jSONObject.toString();
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, getMediaInfo(), i, false);
        SafeParcelWriter.writeLong(parcel, 3, this.zzen);
        SafeParcelWriter.writeInt(parcel, 4, getCurrentItemId());
        SafeParcelWriter.writeDouble(parcel, 5, getPlaybackRate());
        SafeParcelWriter.writeInt(parcel, 6, getPlayerState());
        SafeParcelWriter.writeInt(parcel, 7, getIdleReason());
        SafeParcelWriter.writeLong(parcel, 8, getStreamPosition());
        SafeParcelWriter.writeLong(parcel, 9, this.zzes);
        SafeParcelWriter.writeDouble(parcel, 10, getStreamVolume());
        SafeParcelWriter.writeBoolean(parcel, 11, isMute());
        SafeParcelWriter.writeLongArray(parcel, 12, getActiveTrackIds(), false);
        SafeParcelWriter.writeInt(parcel, 13, getLoadingItemId());
        SafeParcelWriter.writeInt(parcel, 14, getPreloadedItemId());
        SafeParcelWriter.writeString(parcel, 15, this.zzj, false);
        SafeParcelWriter.writeInt(parcel, 16, this.zzex);
        SafeParcelWriter.writeTypedList(parcel, 17, this.zzey, false);
        SafeParcelWriter.writeBoolean(parcel, 18, isPlayingAd());
        SafeParcelWriter.writeParcelable(parcel, 19, getAdBreakStatus(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 20, getVideoInfo(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public AdBreakInfo getCurrentAdBreak() {
        List<AdBreakInfo> adBreaks;
        AdBreakStatus adBreakStatus = this.zzfa;
        if (adBreakStatus != null && this.zzdo != null) {
            String breakId = adBreakStatus.getBreakId();
            if (!TextUtils.isEmpty(breakId) && (adBreaks = this.zzdo.getAdBreaks()) != null && !adBreaks.isEmpty()) {
                for (AdBreakInfo adBreakInfo : adBreaks) {
                    if (breakId.equals(adBreakInfo.getId())) {
                        return adBreakInfo;
                    }
                }
            }
        }
        return null;
    }

    public AdBreakClipInfo getCurrentAdBreakClip() {
        List<AdBreakClipInfo> adBreakClips;
        AdBreakStatus adBreakStatus = this.zzfa;
        if (adBreakStatus != null && this.zzdo != null) {
            String breakClipId = adBreakStatus.getBreakClipId();
            if (!TextUtils.isEmpty(breakClipId) && (adBreakClips = this.zzdo.getAdBreakClips()) != null && !adBreakClips.isEmpty()) {
                for (AdBreakClipInfo adBreakClipInfo : adBreakClips) {
                    if (breakClipId.equals(adBreakClipInfo.getId())) {
                        return adBreakClipInfo;
                    }
                }
            }
        }
        return null;
    }
}
