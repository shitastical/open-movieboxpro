package com.google.android.gms.internal.cast;

import android.widget.ProgressBar;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbg extends UIController implements RemoteMediaClient.ProgressListener {
    private final ProgressBar zztb;
    private final long zztc;

    public zzbg(ProgressBar progressBar, long j) {
        this.zztb = progressBar;
        this.zztc = j;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.zztc);
            if (remoteMediaClient.hasMediaSession()) {
                this.zztb.setMax((int) remoteMediaClient.getStreamDuration());
                this.zztb.setProgress((int) remoteMediaClient.getApproximateStreamPosition());
            } else {
                this.zztb.setMax(1);
                this.zztb.setProgress(0);
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        this.zztb.setMax(1);
        this.zztb.setProgress(0);
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zztb.setMax(1);
            this.zztb.setProgress(0);
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        this.zztb.setMax((int) j2);
        this.zztb.setProgress((int) j);
    }
}
