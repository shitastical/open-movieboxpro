package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.AdBreakInfo;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.internal.cast.zzdz;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class zzs implements zzdz {
    private final /* synthetic */ RemoteMediaClient zzph;

    zzs(RemoteMediaClient remoteMediaClient) {
        this.zzph = remoteMediaClient;
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onStatusUpdated() {
        zzcs();
        this.zzph.zzcr();
        Iterator it = this.zzph.zzpc.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onStatusUpdated();
        }
        Iterator<RemoteMediaClient.Callback> it2 = this.zzph.zzpd.iterator();
        while (it2.hasNext()) {
            it2.next().onStatusUpdated();
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onMetadataUpdated() {
        zzcs();
        Iterator it = this.zzph.zzpc.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onMetadataUpdated();
        }
        Iterator<RemoteMediaClient.Callback> it2 = this.zzph.zzpd.iterator();
        while (it2.hasNext()) {
            it2.next().onMetadataUpdated();
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onQueueStatusUpdated() {
        Iterator it = this.zzph.zzpc.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onQueueStatusUpdated();
        }
        Iterator<RemoteMediaClient.Callback> it2 = this.zzph.zzpd.iterator();
        while (it2.hasNext()) {
            it2.next().onQueueStatusUpdated();
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onPreloadStatusUpdated() {
        Iterator it = this.zzph.zzpc.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onPreloadStatusUpdated();
        }
        Iterator<RemoteMediaClient.Callback> it2 = this.zzph.zzpd.iterator();
        while (it2.hasNext()) {
            it2.next().onPreloadStatusUpdated();
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onAdBreakStatusUpdated() {
        Iterator it = this.zzph.zzpc.iterator();
        while (it.hasNext()) {
            ((RemoteMediaClient.Listener) it.next()).onAdBreakStatusUpdated();
        }
        Iterator<RemoteMediaClient.Callback> it2 = this.zzph.zzpd.iterator();
        while (it2.hasNext()) {
            it2.next().onAdBreakStatusUpdated();
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zza(int[] iArr) {
        Iterator<RemoteMediaClient.Callback> it = this.zzph.zzpd.iterator();
        while (it.hasNext()) {
            it.next().zza(iArr);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zza(int[] iArr, int i) {
        Iterator<RemoteMediaClient.Callback> it = this.zzph.zzpd.iterator();
        while (it.hasNext()) {
            it.next().zza(iArr, i);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zzb(int[] iArr) {
        Iterator<RemoteMediaClient.Callback> it = this.zzph.zzpd.iterator();
        while (it.hasNext()) {
            it.next().zzb(iArr);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zzc(int[] iArr) {
        Iterator<RemoteMediaClient.Callback> it = this.zzph.zzpd.iterator();
        while (it.hasNext()) {
            it.next().zzc(iArr);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zzb(MediaQueueItem[] mediaQueueItemArr) {
        Iterator<RemoteMediaClient.Callback> it = this.zzph.zzpd.iterator();
        while (it.hasNext()) {
            it.next().zzb(mediaQueueItemArr);
        }
    }

    private final void zzcs() {
        MediaStatus mediaStatus;
        if (this.zzph.zzpg == null || (mediaStatus = this.zzph.getMediaStatus()) == null) {
            return;
        }
        mediaStatus.zzf(this.zzph.zzpg.parseIsPlayingAdFromMediaStatus(mediaStatus));
        List<AdBreakInfo> adBreaksFromMediaStatus = this.zzph.zzpg.parseAdBreaksFromMediaStatus(mediaStatus);
        MediaInfo mediaInfo = this.zzph.getMediaInfo();
        if (mediaInfo != null) {
            mediaInfo.zzb(adBreaksFromMediaStatus);
        }
    }
}
