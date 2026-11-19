package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class zzba implements Cast.MessageReceivedCallback {
    private static zzba zzqc;
    final List<RemoteMediaClient.Callback> zzpd = new ArrayList();
    final Map<RemoteMediaClient.ProgressListener, Long> zzqd = new HashMap();
    com.google.android.gms.internal.cast.zzx<RemoteMediaClient> zzqe = new zzbc(this);

    public static zzba zzcu() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzqc == null) {
            zzqc = new zzba();
        }
        return zzqc;
    }

    public final PendingResult<RemoteMediaClient.MediaChannelResult> zzcp() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzcv();
        return zzcw().zzcp();
    }

    public final PendingResult<RemoteMediaClient.MediaChannelResult> zzf(int[] iArr) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzcv();
        return zzcw().zzf(iArr);
    }

    public final PendingResult<RemoteMediaClient.MediaChannelResult> zza(int i, int i2, int i3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzcv();
        return zzcw().zza(i, i2, i3);
    }

    public final MediaStatus getMediaStatus() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzcv();
        return zzcw().getMediaStatus();
    }

    public final void registerCallback(RemoteMediaClient.Callback callback) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (this.zzpd.contains(callback)) {
            return;
        }
        this.zzpd.add(callback);
        if (this.zzqe.zzax() != 3) {
            zzcw().registerCallback(callback);
        }
    }

    @Override // com.google.android.gms.cast.Cast.MessageReceivedCallback
    public final void onMessageReceived(CastDevice castDevice, String str, String str2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzcv();
        zzcw().onMessageReceived(castDevice, str, str2);
    }

    private final void zzcv() {
        if (this.zzqe.zzax() != 1) {
            throw new IllegalStateException("RemoteMediaClientProxy is not active");
        }
    }

    private final RemoteMediaClient zzcw() {
        return this.zzqe.zzba();
    }
}
