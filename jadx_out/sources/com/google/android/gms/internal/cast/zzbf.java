package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbf extends UIController {
    private final ImageView zzsi;
    private final View zzst;
    private final boolean zzsu;
    private final Drawable zzsv;
    private final String zzsw;
    private final Drawable zzsx;
    private final String zzsy;
    private final Drawable zzsz;
    private final String zzta;

    public zzbf(ImageView imageView, Context context, Drawable drawable, Drawable drawable2, Drawable drawable3, View view, boolean z) {
        this.zzsi = imageView;
        this.zzsv = drawable;
        this.zzsx = drawable2;
        this.zzsz = drawable3 != null ? drawable3 : drawable2;
        this.zzsw = context.getString(R.string.cast_play);
        this.zzsy = context.getString(R.string.cast_pause);
        this.zzta = context.getString(R.string.cast_stop);
        this.zzst = view;
        this.zzsu = z;
        imageView.setEnabled(false);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzdk();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zzsi.setEnabled(false);
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzdk();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSendingRemoteMediaRequest() {
        zzj(true);
    }

    private final void zzdk() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzsi.setEnabled(false);
            return;
        }
        if (remoteMediaClient.isPaused()) {
            zza(this.zzsv, this.zzsw);
            return;
        }
        if (remoteMediaClient.isPlaying()) {
            if (remoteMediaClient.isLiveStream()) {
                zza(this.zzsz, this.zzta);
                return;
            } else {
                zza(this.zzsx, this.zzsy);
                return;
            }
        }
        if (remoteMediaClient.isBuffering()) {
            zzj(false);
        } else if (remoteMediaClient.isLoadingNextItem()) {
            zzj(true);
        }
    }

    private final void zza(Drawable drawable, String str) {
        this.zzsi.setImageDrawable(drawable);
        this.zzsi.setContentDescription(str);
        this.zzsi.setVisibility(0);
        this.zzsi.setEnabled(true);
        View view = this.zzst;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    private final void zzj(boolean z) {
        View view = this.zzst;
        if (view != null) {
            view.setVisibility(0);
        }
        this.zzsi.setVisibility(this.zzsu ? 4 : 0);
        this.zzsi.setEnabled(!z);
    }
}
