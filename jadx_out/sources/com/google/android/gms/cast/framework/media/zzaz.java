package com.google.android.gms.cast.framework.media;

import java.util.Set;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
final class zzaz extends TimerTask {
    private final /* synthetic */ RemoteMediaClient zzpv;
    private final /* synthetic */ RemoteMediaClient.zze zzqb;

    zzaz(RemoteMediaClient.zze zzeVar, RemoteMediaClient remoteMediaClient) {
        this.zzqb = zzeVar;
        this.zzpv = remoteMediaClient;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        RemoteMediaClient.this.zza((Set<RemoteMediaClient.ProgressListener>) this.zzqb.zzpx);
        RemoteMediaClient.this.handler.postDelayed(this, this.zzqb.zzpy);
    }
}
