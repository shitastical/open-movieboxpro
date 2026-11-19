package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbs extends zzbt implements RemoteMediaClient.ProgressListener {
    private boolean zzri = true;
    private final long zztc;
    private final TextView zzto;
    private final String zztr;

    public zzbs(TextView textView, long j, String str) {
        this.zzto = textView;
        this.zztc = j;
        this.zztr = str;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null) {
            remoteMediaClient.addProgressListener(this, this.zztc);
            if (remoteMediaClient.hasMediaSession()) {
                this.zzto.setText(DateUtils.formatElapsedTime(remoteMediaClient.getApproximateStreamPosition() / 1000));
            } else {
                this.zzto.setText(this.zztr);
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zzto.setText(this.zztr);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        if (zzee()) {
            TextView textView = this.zzto;
            if (j == -1000) {
                j = j2;
            }
            textView.setText(DateUtils.formatElapsedTime(j / 1000));
        }
    }

    @Override // com.google.android.gms.internal.cast.zzbt
    public final void zzg(long j) {
        this.zzto.setText(DateUtils.formatElapsedTime(j / 1000));
    }

    @Override // com.google.android.gms.internal.cast.zzbt
    public final boolean zzee() {
        return this.zzri;
    }

    @Override // com.google.android.gms.internal.cast.zzbt
    public final void zzk(boolean z) {
        this.zzri = z;
    }
}
