package com.google.android.gms.cast.framework.media.widget;

import android.os.Looper;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.internal.cast.zzez;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes4.dex */
final class zzc extends TimerTask {
    final /* synthetic */ ExpandedControllerActivity zzva;
    final /* synthetic */ AdBreakClipInfo zzvb;
    final /* synthetic */ RemoteMediaClient zzvc;

    zzc(ExpandedControllerActivity expandedControllerActivity, AdBreakClipInfo adBreakClipInfo, RemoteMediaClient remoteMediaClient) {
        this.zzva = expandedControllerActivity;
        this.zzvb = adBreakClipInfo;
        this.zzvc = remoteMediaClient;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        new zzez(Looper.getMainLooper()).post(new zzd(this));
    }
}
