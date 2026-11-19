package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbw extends UIController implements RemoteMediaClient.ProgressListener {
    private final zzbh zzrw;
    private final TextView zzvo;

    public zzbw(TextView textView, zzbh zzbhVar) {
        this.zzvo = textView;
        this.zzrw = zzbhVar;
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().addProgressListener(this, 1000L);
        }
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().removeProgressListener(this);
        }
        super.onSessionEnded();
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.ProgressListener
    public final void onProgressUpdated(long j, long j2) {
        zzdj();
    }

    private final void zzdj() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            TextView textView = this.zzvo;
            textView.setText(textView.getContext().getString(R.string.cast_invalid_stream_duration_text));
        } else {
            if (remoteMediaClient.isLiveStream() && this.zzrw.zzdw() == null) {
                this.zzvo.setVisibility(8);
                return;
            }
            this.zzvo.setVisibility(0);
            TextView textView2 = this.zzvo;
            zzbh zzbhVar = this.zzrw;
            textView2.setText(zzbhVar.zze(zzbhVar.zzdu()));
        }
    }
}
