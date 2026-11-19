package com.google.android.gms.internal.cast;

import android.content.Context;
import android.widget.ImageView;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbd extends UIController {
    private Cast.Listener zzak;
    private final Context zzhe;
    private final ImageView zzsi;
    private final String zzsq;
    private final String zzsr;

    public zzbd(ImageView imageView, Context context) {
        this.zzsi = imageView;
        Context applicationContext = context.getApplicationContext();
        this.zzhe = applicationContext;
        this.zzsq = applicationContext.getString(R.string.cast_mute);
        this.zzsr = applicationContext.getString(R.string.cast_unmute);
        imageView.setEnabled(false);
        this.zzak = null;
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        if (this.zzak == null) {
            this.zzak = new zzbe(this);
        }
        super.onSessionConnected(castSession);
        castSession.addCastListener(this.zzak);
        zzdk();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        Cast.Listener listener;
        this.zzsi.setEnabled(false);
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzhe).getSessionManager().getCurrentCastSession();
        if (currentCastSession != null && (listener = this.zzak) != null) {
            currentCastSession.removeCastListener(listener);
        }
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzdk();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        this.zzsi.setEnabled(false);
    }

    private final void zzi(boolean z) {
        this.zzsi.setSelected(z);
        this.zzsi.setContentDescription(z ? this.zzsq : this.zzsr);
    }

    protected final void zzdk() {
        CastSession currentCastSession = CastContext.getSharedInstance(this.zzhe).getSessionManager().getCurrentCastSession();
        if (currentCastSession != null && currentCastSession.isConnected()) {
            RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
            if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
                this.zzsi.setEnabled(false);
            } else {
                this.zzsi.setEnabled(true);
            }
            if (currentCastSession.isMute()) {
                zzi(true);
                return;
            } else {
                zzi(false);
                return;
            }
        }
        this.zzsi.setEnabled(false);
    }
}
