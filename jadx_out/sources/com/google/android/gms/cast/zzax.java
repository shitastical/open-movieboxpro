package com.google.android.gms.cast;

import com.google.android.gms.internal.cast.zzdz;

/* JADX INFO: loaded from: classes4.dex */
final class zzax implements zzdz {
    private final /* synthetic */ RemoteMediaPlayer zzfl;

    zzax(RemoteMediaPlayer remoteMediaPlayer) {
        this.zzfl = remoteMediaPlayer;
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onAdBreakStatusUpdated() {
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zza(int[] iArr) {
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zza(int[] iArr, int i) {
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zzb(int[] iArr) {
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zzb(MediaQueueItem[] mediaQueueItemArr) {
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void zzc(int[] iArr) {
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onStatusUpdated() {
        this.zzfl.onStatusUpdated();
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onMetadataUpdated() {
        this.zzfl.onMetadataUpdated();
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onQueueStatusUpdated() {
        this.zzfl.onQueueStatusUpdated();
    }

    @Override // com.google.android.gms.internal.cast.zzdz
    public final void onPreloadStatusUpdated() {
        this.zzfl.onPreloadStatusUpdated();
    }
}
