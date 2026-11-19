package com.google.android.gms.internal.cast;

import android.content.Intent;
import android.support.v4.media.session.MediaSessionCompat;
import android.view.KeyEvent;

/* JADX INFO: loaded from: classes4.dex */
final class zzap extends MediaSessionCompat.Callback {
    private final /* synthetic */ zzal zzrk;

    zzap(zzal zzalVar) {
        this.zzrk = zzalVar;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final boolean onMediaButtonEvent(Intent intent) {
        KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
        if (keyEvent == null) {
            return true;
        }
        if (keyEvent.getKeyCode() != 127 && keyEvent.getKeyCode() != 126) {
            return true;
        }
        this.zzrk.zzid.togglePlayback();
        return true;
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onPlay() {
        this.zzrk.zzid.togglePlayback();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onPause() {
        this.zzrk.zzid.togglePlayback();
    }

    @Override // android.support.v4.media.session.MediaSessionCompat.Callback
    public final void onStop() {
        if (this.zzrk.zzid.isLiveStream()) {
            this.zzrk.zzid.togglePlayback();
        }
    }
}
