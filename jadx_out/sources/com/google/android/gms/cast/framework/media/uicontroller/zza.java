package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes4.dex */
final class zza implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzsd;

    zza(UIMediaController uIMediaController) {
        this.zzsd = uIMediaController;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzsd.onPlayPauseToggleClicked((ImageView) view);
    }
}
