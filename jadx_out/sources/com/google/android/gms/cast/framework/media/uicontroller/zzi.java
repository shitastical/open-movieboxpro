package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes4.dex */
final class zzi implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzsd;

    zzi(UIMediaController uIMediaController) {
        this.zzsd = uIMediaController;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzsd.onMuteToggleClicked((ImageView) view);
    }
}
