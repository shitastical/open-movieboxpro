package com.google.android.gms.internal.cast;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbr extends UIController implements RemoteMediaClient.ProgressListener {
    private final TextView zzto;
    private final String zztp;
    private final View zztq;

    public zzbr(TextView textView, String str, View view) {
        this.zzto = textView;
        this.zztp = str;
        this.zztq = view;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().addProgressListener(this, 1000L);
        }
        zza(-1L, true);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zzto.setText(this.zztp);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zza(-1L, true);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        zza(j2, false);
    }

    private final void zza(long j, boolean z) {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient != null && remoteMediaClient.hasMediaSession()) {
            if (!remoteMediaClient.isLiveStream()) {
                if (z) {
                    j = remoteMediaClient.getStreamDuration();
                }
                this.zzto.setVisibility(0);
                this.zzto.setText(DateUtils.formatElapsedTime(j / 1000));
                View view = this.zztq;
                if (view != null) {
                    view.setVisibility(4);
                    return;
                }
                return;
            }
            this.zzto.setText(this.zztp);
            if (this.zztq != null) {
                this.zzto.setVisibility(4);
                this.zztq.setVisibility(0);
                return;
            }
            return;
        }
        this.zzto.setVisibility(0);
        this.zzto.setText(this.zztp);
        View view2 = this.zztq;
        if (view2 != null) {
            view2.setVisibility(4);
        }
    }
}
