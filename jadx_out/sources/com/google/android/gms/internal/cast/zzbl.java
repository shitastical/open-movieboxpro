package com.google.android.gms.internal.cast;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.SeekBar;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;

/* JADX INFO: loaded from: classes4.dex */
public final class zzbl extends UIController implements RemoteMediaClient.ProgressListener {
    private boolean zzri = true;
    private final SeekBar zzrp;
    private final zzbh zzrw;
    private final long zztc;
    private final zzbn zztj;
    private Boolean zztk;
    private Drawable zztl;

    public zzbl(SeekBar seekBar, long j, zzbh zzbhVar, zzbn zzbnVar) {
        this.zztl = null;
        this.zzrp = seekBar;
        this.zztc = j;
        this.zzrw = zzbhVar;
        this.zztj = zzbnVar;
        seekBar.setEnabled(false);
        this.zztl = com.google.android.gms.cast.framework.media.widget.zze.zza(seekBar);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        if (getRemoteMediaClient() != null) {
            getRemoteMediaClient().addProgressListener(this, this.zztc);
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
        zzec();
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0035  */
    private final void zzdj() {
        zzec();
        if (this.zztj != null) {
            if (getRemoteMediaClient() != null) {
                MediaInfo mediaInfo = getRemoteMediaClient().getMediaInfo();
                if (getRemoteMediaClient().hasMediaSession() && !getRemoteMediaClient().isLoadingNextItem() && mediaInfo != null) {
                    this.zztj.zzd(mediaInfo.getAdBreaks());
                } else {
                    this.zztj.zzd(null);
                }
            } else {
                this.zztj.zzd(null);
            }
        }
        zzbn zzbnVar = this.zztj;
        if (zzbnVar != null) {
            zzbnVar.zzdf();
        }
    }

    private final void zzec() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzrp.setMax(this.zzrw.zzdm());
            this.zzrp.setProgress(this.zzrw.zzdn());
            this.zzrp.setEnabled(false);
            return;
        }
        if (this.zzri) {
            this.zzrp.setMax(this.zzrw.zzdm());
            if (remoteMediaClient.isLiveStream() && this.zzrw.zzdq()) {
                this.zzrp.setProgress(this.zzrw.zzds());
            } else {
                this.zzrp.setProgress(this.zzrw.zzdn());
            }
            if (remoteMediaClient.isPlayingAd()) {
                this.zzrp.setEnabled(false);
            } else {
                this.zzrp.setEnabled(true);
            }
            if (getRemoteMediaClient() != null) {
                Boolean bool = this.zztk;
                if (bool == null || bool.booleanValue() != this.zzrw.zzdo()) {
                    Boolean boolValueOf = Boolean.valueOf(this.zzrw.zzdo());
                    this.zztk = boolValueOf;
                    if (!boolValueOf.booleanValue()) {
                        this.zzrp.setThumb(new ColorDrawable(0));
                        this.zzrp.setClickable(false);
                        this.zzrp.setOnTouchListener(new zzbm(this));
                    } else {
                        Drawable drawable = this.zztl;
                        if (drawable != null) {
                            this.zzrp.setThumb(drawable);
                        }
                        this.zzrp.setClickable(true);
                        this.zzrp.setOnTouchListener(null);
                    }
                }
            }
        }
    }

    public final void zzk(boolean z) {
        this.zzri = z;
    }
}
