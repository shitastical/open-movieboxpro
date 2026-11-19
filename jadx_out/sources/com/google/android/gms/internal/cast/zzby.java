package com.google.android.gms.internal.cast;

import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzby extends UIController implements RemoteMediaClient.ProgressListener {
    private final zzbh zzrw;
    private final TextView zzvs;

    public zzby(TextView textView, zzbh zzbhVar) {
        this.zzvs = textView;
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
            TextView textView = this.zzvs;
            textView.setText(textView.getContext().getString(R.string.cast_invalid_stream_duration_text));
            return;
        }
        int i = zzbz.zztf[this.zzrw.zzdz() - 1];
        if (i == 1) {
            TextView textView2 = this.zzvs;
            zzbh zzbhVar = this.zzrw;
            textView2.setText(zzbhVar.zze(zzbhVar.zzo(zzbhVar.zzdn())));
        } else {
            if (i != 2) {
                return;
            }
            TextView textView3 = this.zzvs;
            zzbh zzbhVar2 = this.zzrw;
            textView3.setText(zzbhVar2.zze(zzbhVar2.zzdt()));
        }
    }
}
