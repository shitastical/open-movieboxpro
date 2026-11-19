package com.google.android.gms.internal.cast;

import android.os.SystemClock;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouteProviderProtocol;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.AdBreakStatus;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaLoadOptions;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.RemoteMediaPlayer;
import com.google.android.gms.cast.TextTrackStyle;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdx extends zzcw {
    public static final String NAMESPACE = zzdk.zzq("com.google.cast.media");
    private final zzed zzaaa;
    private final zzed zzaab;
    private final zzed zzaac;
    private final zzed zzaad;
    private final zzed zzaae;
    private final zzed zzaaf;
    private final zzed zzaag;
    private final zzed zzaah;
    private final zzed zzaai;
    private final zzed zzaaj;
    private final zzed zzaak;
    private final zzed zzaal;
    private final zzed zzaam;
    private final zzed zzaan;
    private final zzed zzaao;
    private long zzzs;
    private MediaStatus zzzt;
    private Long zzzu;
    private zzdz zzzv;
    private final zzed zzzw;
    private final zzed zzzx;
    private final zzed zzzy;
    private final zzed zzzz;

    public zzdx(String str) {
        super(NAMESPACE, "MediaControlChannel", null);
        zzed zzedVar = new zzed(86400000L);
        this.zzzw = zzedVar;
        zzed zzedVar2 = new zzed(86400000L);
        this.zzzx = zzedVar2;
        zzed zzedVar3 = new zzed(86400000L);
        this.zzzy = zzedVar3;
        zzed zzedVar4 = new zzed(86400000L);
        this.zzzz = zzedVar4;
        zzed zzedVar5 = new zzed(10000L);
        this.zzaaa = zzedVar5;
        zzed zzedVar6 = new zzed(86400000L);
        this.zzaab = zzedVar6;
        zzed zzedVar7 = new zzed(86400000L);
        this.zzaac = zzedVar7;
        zzed zzedVar8 = new zzed(86400000L);
        this.zzaad = zzedVar8;
        zzed zzedVar9 = new zzed(86400000L);
        this.zzaae = zzedVar9;
        zzed zzedVar10 = new zzed(86400000L);
        this.zzaaf = zzedVar10;
        zzed zzedVar11 = new zzed(86400000L);
        this.zzaag = zzedVar11;
        zzed zzedVar12 = new zzed(86400000L);
        this.zzaah = zzedVar12;
        zzed zzedVar13 = new zzed(86400000L);
        this.zzaai = zzedVar13;
        zzed zzedVar14 = new zzed(86400000L);
        this.zzaaj = zzedVar14;
        zzed zzedVar15 = new zzed(86400000L);
        this.zzaak = zzedVar15;
        zzed zzedVar16 = new zzed(86400000L);
        this.zzaam = zzedVar16;
        this.zzaal = new zzed(86400000L);
        zzed zzedVar17 = new zzed(86400000L);
        this.zzaan = zzedVar17;
        zzed zzedVar18 = new zzed(86400000L);
        this.zzaao = zzedVar18;
        zza(zzedVar);
        zza(zzedVar2);
        zza(zzedVar3);
        zza(zzedVar4);
        zza(zzedVar5);
        zza(zzedVar6);
        zza(zzedVar7);
        zza(zzedVar8);
        zza(zzedVar9);
        zza(zzedVar10);
        zza(zzedVar11);
        zza(zzedVar12);
        zza(zzedVar13);
        zza(zzedVar14);
        zza(zzedVar15);
        zza(zzedVar16);
        zza(zzedVar16);
        zza(zzedVar17);
        zza(zzedVar18);
        zzfc();
    }

    public final void zza(zzdz zzdzVar) {
        this.zzzv = zzdzVar;
    }

    public final long zza(zzec zzecVar, MediaInfo mediaInfo, com.google.android.gms.cast.zzam zzamVar, MediaLoadOptions mediaLoadOptions) throws IllegalStateException, IllegalArgumentException {
        if (mediaInfo == null && zzamVar == null) {
            throw new IllegalArgumentException("MediaInfo should not be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "LOAD");
            if (mediaInfo != null) {
                jSONObject.put("media", mediaInfo.toJson());
            }
            if (zzamVar != null) {
                jSONObject.put("queueData", zzamVar.toJson());
            }
            jSONObject.put("autoplay", mediaLoadOptions.getAutoplay());
            jSONObject.put("currentTime", mediaLoadOptions.getPlayPosition() / 1000.0d);
            jSONObject.put("playbackRate", mediaLoadOptions.getPlaybackRate());
            if (mediaLoadOptions.getCredentials() != null) {
                jSONObject.put("credentials", mediaLoadOptions.getCredentials());
            }
            if (mediaLoadOptions.getCredentialsType() != null) {
                jSONObject.put("credentialsType", mediaLoadOptions.getCredentialsType());
            }
            long[] activeTrackIds = mediaLoadOptions.getActiveTrackIds();
            if (activeTrackIds != null) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < activeTrackIds.length; i++) {
                    jSONArray.put(i, activeTrackIds[i]);
                }
                jSONObject.put("activeTrackIds", jSONArray);
            }
            JSONObject customData = mediaLoadOptions.getCustomData();
            if (customData != null) {
                jSONObject.put("customData", customData);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzzw.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, JSONObject jSONObject) throws IllegalStateException, zzea {
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "PAUSE");
            jSONObject2.put("mediaSessionId", zzp());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzzx.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zzb(zzec zzecVar, JSONObject jSONObject) throws IllegalStateException, zzea {
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "STOP");
            jSONObject2.put("mediaSessionId", zzp());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzzz.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zzc(zzec zzecVar, JSONObject jSONObject) throws IllegalStateException, zzea {
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "PLAY");
            jSONObject2.put("mediaSessionId", zzp());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzzy.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, com.google.android.gms.cast.zzas zzasVar) throws IllegalStateException, zzea {
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        long position = zzasVar.zzn() ? 4294967296000L : zzasVar.getPosition();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "SEEK");
            jSONObject.put("mediaSessionId", zzp());
            jSONObject.put("currentTime", position / 1000.0d);
            if (zzasVar.zzm() == 1) {
                jSONObject.put("resumeState", "PLAYBACK_START");
            } else if (zzasVar.zzm() == 2) {
                jSONObject.put("resumeState", "PLAYBACK_PAUSE");
            }
            if (zzasVar.getCustomData() != null) {
                jSONObject.put("customData", zzasVar.getCustomData());
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzzu = Long.valueOf(position);
        this.zzaaa.zza(jZzes, new zzdy(this, zzecVar));
        return jZzes;
    }

    public final long zza(zzec zzecVar) throws IllegalStateException, zzea {
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "SKIP_AD");
            jSONObject.put("mediaSessionId", zzp());
        } catch (JSONException e) {
            this.zzxw.w(String.format(Locale.ROOT, "Error creating SkipAd message: %s", e.getMessage()), new Object[0]);
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzaao.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, double d, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzp());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put(FirebaseAnalytics.Param.LEVEL, d);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzaab.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, boolean z, JSONObject jSONObject) throws IllegalStateException, zzea {
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "SET_VOLUME");
            jSONObject2.put("mediaSessionId", zzp());
            JSONObject jSONObject3 = new JSONObject();
            jSONObject3.put("muted", z);
            jSONObject2.put(MediaRouteProviderProtocol.CLIENT_DATA_VOLUME, jSONObject3);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzaac.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zzb(zzec zzecVar, double d, JSONObject jSONObject) throws IllegalStateException, zzea {
        if (this.zzzt == null) {
            throw new zzea();
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "SET_PLAYBACK_RATE");
            jSONObject2.put("playbackRate", d);
            jSONObject2.put("mediaSessionId", this.zzzt.zzp());
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzaan.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zzb(zzec zzecVar) throws IllegalStateException {
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "GET_STATUS");
            MediaStatus mediaStatus = this.zzzt;
            if (mediaStatus != null) {
                jSONObject.put("mediaSessionId", mediaStatus.zzp());
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzaad.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, long[] jArr) throws IllegalStateException, zzea {
        if (jArr == null) {
            throw new IllegalArgumentException("trackIds cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "EDIT_TRACKS_INFO");
            jSONObject.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < jArr.length; i++) {
                jSONArray.put(i, jArr[i]);
            }
            jSONObject.put("activeTrackIds", jSONArray);
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzaae.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, TextTrackStyle textTrackStyle) throws IllegalStateException, zzea {
        if (textTrackStyle == null) {
            throw new IllegalArgumentException("trackStyle cannot be null");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "EDIT_TRACKS_INFO");
            if (textTrackStyle != null) {
                jSONObject.put("textTrackStyle", textTrackStyle.toJson());
            }
            jSONObject.put("mediaSessionId", zzp());
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzaaf.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long getApproximateStreamPosition() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo == null) {
            return 0L;
        }
        Long l = this.zzzu;
        if (l != null) {
            return l.longValue();
        }
        if (this.zzzs == 0) {
            return 0L;
        }
        double playbackRate = this.zzzt.getPlaybackRate();
        long streamPosition = this.zzzt.getStreamPosition();
        return (playbackRate == 0.0d || this.zzzt.getPlayerState() != 2) ? streamPosition : zza(playbackRate, streamPosition, mediaInfo.getStreamDuration());
    }

    public final long getApproximateAdBreakClipPositionMs() {
        MediaStatus mediaStatus;
        AdBreakStatus adBreakStatus;
        AdBreakClipInfo currentAdBreakClip;
        if (this.zzzs == 0 || (mediaStatus = this.zzzt) == null || (adBreakStatus = mediaStatus.getAdBreakStatus()) == null || (currentAdBreakClip = this.zzzt.getCurrentAdBreakClip()) == null) {
            return 0L;
        }
        double d = 0.0d;
        if (this.zzzt.getPlaybackRate() == 0.0d && this.zzzt.getPlayerState() == 2) {
            d = 1.0d;
        }
        return zza(d, adBreakStatus.getCurrentBreakClipTimeInMs(), currentAdBreakClip.getDurationInMs());
    }

    private final long zza(double d, long j, long j2) {
        long jElapsedRealtime = SystemClock.elapsedRealtime() - this.zzzs;
        if (jElapsedRealtime < 0) {
            jElapsedRealtime = 0;
        }
        if (jElapsedRealtime == 0) {
            return j;
        }
        long j3 = j + ((long) (jElapsedRealtime * d));
        if (j2 > 0 && j3 > j2) {
            return j2;
        }
        if (j3 < 0) {
            return 0L;
        }
        return j3;
    }

    public final long getStreamDuration() {
        MediaInfo mediaInfo = getMediaInfo();
        if (mediaInfo != null) {
            return mediaInfo.getStreamDuration();
        }
        return 0L;
    }

    public final MediaStatus getMediaStatus() {
        return this.zzzt;
    }

    public final MediaInfo getMediaInfo() {
        MediaStatus mediaStatus = this.zzzt;
        if (mediaStatus == null) {
            return null;
        }
        return mediaStatus.getMediaInfo();
    }

    public final long zza(zzec zzecVar, MediaQueueItem[] mediaQueueItemArr, com.google.android.gms.cast.zzam zzamVar, com.google.android.gms.cast.zzap zzapVar) throws IllegalStateException, IllegalArgumentException {
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("items must not be null or empty.");
        }
        if (zzapVar.getStartIndex() < 0 || zzapVar.getStartIndex() >= mediaQueueItemArr.length) {
            int startIndex = zzapVar.getStartIndex();
            StringBuilder sb = new StringBuilder(31);
            sb.append("Invalid startIndex: ");
            sb.append(startIndex);
            throw new IllegalArgumentException(sb.toString());
        }
        if (zzapVar.getPlayPosition() != -1 && zzapVar.getPlayPosition() < 0) {
            long playPosition = zzapVar.getPlayPosition();
            StringBuilder sb2 = new StringBuilder(54);
            sb2.append("playPosition can not be negative: ");
            sb2.append(playPosition);
            throw new IllegalArgumentException(sb2.toString());
        }
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "QUEUE_LOAD");
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < mediaQueueItemArr.length; i++) {
                jSONArray.put(i, mediaQueueItemArr[i].toJson());
            }
            jSONObject.put(FirebaseAnalytics.Param.ITEMS, jSONArray);
            if (zzamVar != null) {
                jSONObject.put("queueData", zzamVar.toJson());
            }
            String strZza = zzef.zza(Integer.valueOf(zzapVar.getRepeatMode()));
            if (strZza == null) {
                int repeatMode = zzapVar.getRepeatMode();
                StringBuilder sb3 = new StringBuilder(32);
                sb3.append("Invalid repeat mode: ");
                sb3.append(repeatMode);
                throw new IllegalArgumentException(sb3.toString());
            }
            jSONObject.put("repeatMode", strZza);
            jSONObject.put("startIndex", zzapVar.getStartIndex());
            if (zzapVar.getPlayPosition() != -1) {
                jSONObject.put("currentTime", zzapVar.getPlayPosition() / 1000.0d);
            }
            if (zzapVar.getCustomData() != null) {
                jSONObject.put("customData", zzapVar.getCustomData());
            }
            zza(jSONObject.toString(), jZzes, (String) null);
            this.zzzw.zza(jZzes, zzecVar);
            return jZzes;
        } catch (JSONException unused) {
        }
    }

    public final long zza(zzec zzecVar, MediaQueueItem[] mediaQueueItemArr, int i, int i2, int i3, long j, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        if (mediaQueueItemArr == null || mediaQueueItemArr.length == 0) {
            throw new IllegalArgumentException("itemsToInsert must not be null or empty.");
        }
        if (i3 != -1 && (i3 < 0 || i3 >= mediaQueueItemArr.length)) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "currentItemIndexInItemsToInsert %d out of range [0, %d).", Integer.valueOf(i3), Integer.valueOf(mediaQueueItemArr.length)));
        }
        if (j != -1 && j < 0) {
            StringBuilder sb = new StringBuilder(54);
            sb.append("playPosition can not be negative: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "QUEUE_INSERT");
            jSONObject2.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int i4 = 0; i4 < mediaQueueItemArr.length; i4++) {
                jSONArray.put(i4, mediaQueueItemArr[i4].toJson());
            }
            jSONObject2.put(FirebaseAnalytics.Param.ITEMS, jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (i3 != -1) {
                jSONObject2.put("currentItemIndex", i3);
            }
            if (j != -1) {
                jSONObject2.put("currentTime", j / 1000.0d);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzaag.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, int i, long j, MediaQueueItem[] mediaQueueItemArr, int i2, Integer num, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        if (j != -1 && j < 0) {
            StringBuilder sb = new StringBuilder(53);
            sb.append("playPosition cannot be negative: ");
            sb.append(j);
            throw new IllegalArgumentException(sb.toString());
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "QUEUE_UPDATE");
            jSONObject2.put("mediaSessionId", zzp());
            if (i != 0) {
                jSONObject2.put("currentItemId", i);
            }
            if (i2 != 0) {
                jSONObject2.put("jump", i2);
            }
            if (mediaQueueItemArr != null && mediaQueueItemArr.length > 0) {
                JSONArray jSONArray = new JSONArray();
                for (int i3 = 0; i3 < mediaQueueItemArr.length; i3++) {
                    jSONArray.put(i3, mediaQueueItemArr[i3].toJson());
                }
                jSONObject2.put(FirebaseAnalytics.Param.ITEMS, jSONArray);
            }
            String strZza = zzef.zza(num);
            if (strZza != null) {
                jSONObject2.put("repeatMode", strZza);
            }
            if (j != -1) {
                jSONObject2.put("currentTime", j / 1000.0d);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzaah.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, int[] iArr, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToRemove must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "QUEUE_REMOVE");
            jSONObject2.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < iArr.length; i++) {
                jSONArray.put(i, iArr[i]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzaai.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, int[] iArr, int i, JSONObject jSONObject) throws IllegalStateException, zzea, IllegalArgumentException {
        if (iArr == null || iArr.length == 0) {
            throw new IllegalArgumentException("itemIdsToReorder must not be null or empty.");
        }
        JSONObject jSONObject2 = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject2.put("requestId", jZzes);
            jSONObject2.put("type", "QUEUE_REORDER");
            jSONObject2.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < iArr.length; i2++) {
                jSONArray.put(i2, iArr[i2]);
            }
            jSONObject2.put("itemIds", jSONArray);
            if (i != 0) {
                jSONObject2.put("insertBefore", i);
            }
            if (jSONObject != null) {
                jSONObject2.put("customData", jSONObject);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject2.toString(), jZzes, (String) null);
        this.zzaaj.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zzc(zzec zzecVar) throws IllegalStateException, zzea {
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "QUEUE_GET_ITEM_IDS");
            jSONObject.put("mediaSessionId", zzp());
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzaak.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, int i, int i2, int i3) throws zzea, IllegalArgumentException {
        if ((i2 <= 0 || i3 != 0) && (i2 != 0 || i3 <= 0)) {
            throw new IllegalArgumentException("Exactly one of nextCount and prevCount must be positive and the other must be zero");
        }
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "QUEUE_GET_ITEM_RANGE");
            jSONObject.put("mediaSessionId", zzp());
            jSONObject.put("itemId", i);
            if (i2 > 0) {
                jSONObject.put("nextCount", i2);
            }
            if (i3 > 0) {
                jSONObject.put("prevCount", i3);
            }
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzaam.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zza(zzec zzecVar, int[] iArr) throws zzea, IllegalArgumentException {
        JSONObject jSONObject = new JSONObject();
        long jZzes = zzes();
        try {
            jSONObject.put("requestId", jZzes);
            jSONObject.put("type", "QUEUE_GET_ITEMS");
            jSONObject.put("mediaSessionId", zzp());
            JSONArray jSONArray = new JSONArray();
            for (int i : iArr) {
                jSONArray.put(i);
            }
            jSONObject.put("itemIds", jSONArray);
        } catch (JSONException unused) {
        }
        zza(jSONObject.toString(), jZzes, (String) null);
        this.zzaal.zza(jZzes, zzecVar);
        return jZzes;
    }

    public final long zzb(String str, List<com.google.android.gms.cast.zzbx> list) throws IllegalStateException {
        long jZzes = zzes();
        zza(zza(str, list, jZzes), jZzes, (String) null);
        return jZzes;
    }

    private static String zza(String str, List<com.google.android.gms.cast.zzbx> list, long j) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("requestId", j);
            jSONObject.put("type", "PRECACHE");
            if (str != null) {
                jSONObject.put("precacheData", str);
            }
            if (list != null && !list.isEmpty()) {
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < list.size(); i++) {
                    jSONArray.put(i, list.get(i).toJson());
                }
                jSONObject.put("requestItems", jSONArray);
            }
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    /* JADX WARN: Code duplicated, block: B:30:0x007c  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.google.android.gms.internal.cast.zzdc
    public final void zzo(String str) {
        int iZza;
        MediaStatus mediaStatus;
        int[] iArrZzb;
        this.zzxw.d("message received: %s", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString("type");
            long jOptLong = jSONObject.optLong("requestId", -1L);
            byte b = -1;
            switch (string) {
                case "MEDIA_STATUS":
                    JSONArray jSONArray = jSONObject.getJSONArray(NotificationCompat.CATEGORY_STATUS);
                    if (jSONArray.length() > 0) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(0);
                        boolean zTest = this.zzzw.test(jOptLong);
                        int i = ((!this.zzaab.zzfd() || this.zzaab.test(jOptLong)) && (!this.zzaac.zzfd() || this.zzaac.test(jOptLong))) ? 0 : 1;
                        if (zTest || (mediaStatus = this.zzzt) == null) {
                            this.zzzt = new MediaStatus(jSONObject2);
                            this.zzzs = SystemClock.elapsedRealtime();
                            iZza = 127;
                        } else {
                            iZza = mediaStatus.zza(jSONObject2, i);
                        }
                        if ((iZza & 1) != 0) {
                            this.zzzs = SystemClock.elapsedRealtime();
                            onStatusUpdated();
                        }
                        if ((iZza & 2) != 0) {
                            this.zzzs = SystemClock.elapsedRealtime();
                            onStatusUpdated();
                        }
                        if ((iZza & 128) != 0) {
                            this.zzzs = SystemClock.elapsedRealtime();
                        }
                        if ((iZza & 4) != 0) {
                            onMetadataUpdated();
                        }
                        if ((iZza & 8) != 0) {
                            onQueueStatusUpdated();
                        }
                        if ((iZza & 16) != 0) {
                            onPreloadStatusUpdated();
                        }
                        if ((iZza & 32) != 0) {
                            this.zzzs = SystemClock.elapsedRealtime();
                            zzdz zzdzVar = this.zzzv;
                            if (zzdzVar != null) {
                                zzdzVar.onAdBreakStatusUpdated();
                            }
                        }
                        if ((iZza & 64) != 0) {
                            this.zzzs = SystemClock.elapsedRealtime();
                            onStatusUpdated();
                        }
                    } else {
                        this.zzzt = null;
                        onStatusUpdated();
                        onMetadataUpdated();
                        onQueueStatusUpdated();
                        onPreloadStatusUpdated();
                    }
                    Iterator<zzed> it = zzer().iterator();
                    while (it.hasNext()) {
                        it.next().zzc(jOptLong, 0, null);
                    }
                    break;
                case "INVALID_PLAYER_STATE":
                    this.zzxw.w("received unexpected error: Invalid Player State.", new Object[0]);
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("customData");
                    Iterator<zzed> it2 = zzer().iterator();
                    while (it2.hasNext()) {
                        it2.next().zzc(jOptLong, 2100, jSONObjectOptJSONObject);
                    }
                    break;
                case "LOAD_FAILED":
                    this.zzzw.zzc(jOptLong, 2100, jSONObject.optJSONObject("customData"));
                    break;
                case "LOAD_CANCELLED":
                    this.zzzw.zzc(jOptLong, RemoteMediaPlayer.STATUS_CANCELED, jSONObject.optJSONObject("customData"));
                    break;
                case "INVALID_REQUEST":
                    this.zzxw.w("received unexpected error: Invalid Request.", new Object[0]);
                    JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("customData");
                    Iterator<zzed> it3 = zzer().iterator();
                    while (it3.hasNext()) {
                        it3.next().zzc(jOptLong, 2100, jSONObjectOptJSONObject2);
                    }
                    break;
                case "QUEUE_ITEM_IDS":
                    this.zzaak.zzc(jOptLong, 0, null);
                    if (this.zzzv != null && (iArrZzb = zzb(jSONObject.getJSONArray("itemIds"))) != null) {
                        this.zzzv.zza(iArrZzb);
                        break;
                    }
                    break;
                case "QUEUE_CHANGE":
                    this.zzaam.zzc(jOptLong, 0, null);
                    if (this.zzzv != null) {
                        String string2 = jSONObject.getString("changeType");
                        int[] iArrZzb2 = zzb(jSONObject.getJSONArray("itemIds"));
                        int iOptInt = jSONObject.optInt("insertBefore", 0);
                        if (iArrZzb2 != null) {
                            switch (string2.hashCode()) {
                                case -2130463047:
                                    if (string2.equals("INSERT")) {
                                        b = 0;
                                    }
                                    break;
                                case -1881281404:
                                    if (string2.equals("REMOVE")) {
                                        b = 2;
                                    }
                                    break;
                                case -1785516855:
                                    if (string2.equals("UPDATE")) {
                                        b = 3;
                                    }
                                    break;
                                case 1122976047:
                                    if (string2.equals("ITEMS_CHANGE")) {
                                        b = 1;
                                    }
                                    break;
                                case 1395699694:
                                    if (string2.equals("NO_CHANGE")) {
                                        b = 4;
                                    }
                                    break;
                            }
                            if (b == 0) {
                                this.zzzv.zza(iArrZzb2, iOptInt);
                            } else if (b == 1) {
                                this.zzzv.zzb(iArrZzb2);
                            } else if (b == 2) {
                                this.zzzv.zzc(iArrZzb2);
                            } else if (b == 3) {
                                this.zzzv.zza(iArrZzb2);
                            }
                        }
                        break;
                    }
                    break;
                case "QUEUE_ITEMS":
                    this.zzaal.zzc(jOptLong, 0, null);
                    if (this.zzzv != null) {
                        JSONArray jSONArray2 = jSONObject.getJSONArray(FirebaseAnalytics.Param.ITEMS);
                        MediaQueueItem[] mediaQueueItemArr = new MediaQueueItem[jSONArray2.length()];
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            mediaQueueItemArr[i2] = new MediaQueueItem.Builder(jSONArray2.getJSONObject(i2)).build();
                        }
                        this.zzzv.zzb(mediaQueueItemArr);
                        break;
                    }
                    break;
            }
        } catch (JSONException e) {
            this.zzxw.w("Message is malformed (%s); ignoring: %s", e.getMessage(), str);
        }
    }

    private static int[] zzb(JSONArray jSONArray) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        int[] iArr = new int[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            iArr[i] = jSONArray.getInt(i);
        }
        return iArr;
    }

    private final long zzp() throws zzea {
        MediaStatus mediaStatus = this.zzzt;
        if (mediaStatus == null) {
            throw new zzea();
        }
        return mediaStatus.zzp();
    }

    private final void onStatusUpdated() {
        zzdz zzdzVar = this.zzzv;
        if (zzdzVar != null) {
            zzdzVar.onStatusUpdated();
        }
    }

    private final void onMetadataUpdated() {
        zzdz zzdzVar = this.zzzv;
        if (zzdzVar != null) {
            zzdzVar.onMetadataUpdated();
        }
    }

    private final void onQueueStatusUpdated() {
        zzdz zzdzVar = this.zzzv;
        if (zzdzVar != null) {
            zzdzVar.onQueueStatusUpdated();
        }
    }

    private final void onPreloadStatusUpdated() {
        zzdz zzdzVar = this.zzzv;
        if (zzdzVar != null) {
            zzdzVar.onPreloadStatusUpdated();
        }
    }

    private final void zzfc() {
        this.zzzs = 0L;
        this.zzzt = null;
        Iterator<zzed> it = zzer().iterator();
        while (it.hasNext()) {
            it.next().zzv(2002);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzcw, com.google.android.gms.internal.cast.zzdc
    public final void zzeq() {
        super.zzeq();
        zzfc();
    }

    @Override // com.google.android.gms.internal.cast.zzdc
    public final void zza(long j, int i) {
        Iterator<zzed> it = zzer().iterator();
        while (it.hasNext()) {
            it.next().zzc(j, i, null);
        }
    }

    static /* synthetic */ Long zza(zzdx zzdxVar, Long l) {
        zzdxVar.zzzu = null;
        return null;
    }
}
