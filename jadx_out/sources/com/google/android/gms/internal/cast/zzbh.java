package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbh extends UIController {
    private static int zzte = zzbj.zzth;
    private SimpleDateFormat zztd;

    public final int zzdm() {
        return Math.max((int) (zzdu() - zzdt()), 1);
    }

    public final int zzdn() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return 0;
        }
        if (!remoteMediaClient.isLiveStream() && remoteMediaClient.isLoadingNextItem()) {
            return 0;
        }
        int approximateStreamPosition = (int) (remoteMediaClient.getApproximateStreamPosition() - zzdt());
        if (zzdo()) {
            approximateStreamPosition = zzdk.zzb(approximateStreamPosition, zzdr(), zzds());
        }
        return zzdk.zzb(approximateStreamPosition, 0, zzdm());
    }

    public final boolean zzdo() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            if (!remoteMediaClient.isLiveStream()) {
                return true;
            }
            MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
            if (mediaStatus == null) {
                return false;
            }
            mediaStatus.isMediaCommandSupported(2L);
        }
        return false;
    }

    public final boolean zzdp() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        return remoteMediaClient != null && remoteMediaClient.hasMediaSession() && zzdo() && (((long) zzdn()) + zzdt()) - (((long) zzdr()) + zzdt()) < 10000;
    }

    public final boolean zzdq() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && zzdo()) {
            return (((long) zzds()) + zzdt()) - (((long) zzdn()) + zzdt()) < 10000;
        }
        return false;
    }

    public final int zzdr() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && remoteMediaClient.isLiveStream()) {
            return zzdk.zzb((int) (zzdx().longValue() - zzdt()), 0, zzdm());
        }
        return 0;
    }

    public final int zzds() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !remoteMediaClient.isLiveStream()) {
            return zzdm();
        }
        return zzdk.zzb((int) (zzdy().longValue() - zzdt()), 0, zzdm());
    }

    public final long zzo(int i) {
        return ((long) i) + zzdt();
    }

    public final long zzdt() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !remoteMediaClient.isLiveStream()) {
            return 0L;
        }
        Long lZzdv = zzdv();
        if (lZzdv != null) {
            return lZzdv.longValue();
        }
        Long lZzdx = zzdx();
        if (lZzdx != null) {
            return lZzdx.longValue();
        }
        return remoteMediaClient.getApproximateStreamPosition();
    }

    public final long zzdu() {
        MediaInfo media;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return 1L;
        }
        if (!remoteMediaClient.isLiveStream()) {
            if (remoteMediaClient.isLoadingNextItem()) {
                MediaQueueItem loadingItem = remoteMediaClient.getLoadingItem();
                if (loadingItem == null || (media = loadingItem.getMedia()) == null) {
                    return 1L;
                }
                return Math.max(media.getStreamDuration(), 1L);
            }
            return Math.max(remoteMediaClient.getStreamDuration(), 1L);
        }
        Long lZzdw = zzdw();
        if (lZzdw != null) {
            return lZzdw.longValue();
        }
        Long lZzdy = zzdy();
        if (lZzdy != null) {
            return lZzdy.longValue();
        }
        return Math.max(remoteMediaClient.getApproximateStreamPosition(), 1L);
    }

    private final Long zzdv() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && remoteMediaClient.isLiveStream()) {
            MediaInfo mediaInfo = remoteMediaClient.getMediaInfo();
            MediaMetadata metadata = getMetadata();
            if (mediaInfo != null && metadata != null && metadata.containsKey("com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA")) {
                return Long.valueOf(metadata.zze("com.google.android.gms.cast.metadata.SECTION_START_TIME_IN_MEDIA"));
            }
        }
        return null;
    }

    public final Long zzdw() {
        MediaMetadata metadata;
        Long lZzdv;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !remoteMediaClient.isLiveStream() || (metadata = getMetadata()) == null || !metadata.containsKey("com.google.android.gms.cast.metadata.SECTION_DURATION") || (lZzdv = zzdv()) == null) {
            return null;
        }
        return Long.valueOf(lZzdv.longValue() + metadata.zze("com.google.android.gms.cast.metadata.SECTION_DURATION"));
    }

    private final Long zzdx() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && remoteMediaClient.isLiveStream()) {
            remoteMediaClient.getMediaStatus();
        }
        return null;
    }

    private final Long zzdy() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession() && remoteMediaClient.isLiveStream()) {
            remoteMediaClient.getMediaStatus();
        }
        return null;
    }

    private final MediaMetadata getMetadata() {
        MediaInfo mediaInfo;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || (mediaInfo = remoteMediaClient.getMediaInfo()) == null) {
            return null;
        }
        return mediaInfo.getMetadata();
    }

    public final String zze(long j) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return null;
        }
        int i = zzbi.zztf[zzdz() - 1];
        if (i == 1) {
            long jLongValue = zzea().longValue() + j;
            if (this.zztd == null) {
                this.zztd = new SimpleDateFormat("hh:mm:ss", Locale.US);
            }
            return this.zztd.format(new Date(jLongValue));
        }
        if (i != 2) {
            return null;
        }
        if (remoteMediaClient.isLiveStream() && zzdv() == null) {
            return zzf(j);
        }
        return zzf(j - zzdt());
    }

    public final int zzdz() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            return zzbj.zztg;
        }
        if (!remoteMediaClient.isLiveStream() || zzte == zzbj.zztg) {
            return zzbj.zztg;
        }
        if (zzea() != null) {
            return zzbj.zzth;
        }
        return zzbj.zztg;
    }

    private final Long zzea() {
        MediaInfo mediaInfo;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || !remoteMediaClient.isLiveStream() || (mediaInfo = remoteMediaClient.getMediaInfo()) == null || mediaInfo.zzj() == -1) {
            return null;
        }
        return Long.valueOf(mediaInfo.zzj());
    }

    private static String zzf(long j) {
        if (j >= 0) {
            return DateUtils.formatElapsedTime(j / 1000);
        }
        String strValueOf = String.valueOf(DateUtils.formatElapsedTime((-j) / 1000));
        return strValueOf.length() != 0 ? "-".concat(strValueOf) : new String("-");
    }
}
