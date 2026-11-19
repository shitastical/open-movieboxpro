package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
final class zzc implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzsd;

    zzc(UIMediaController uIMediaController) {
        this.zzsd = uIMediaController;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzsd.onSkipPrevClicked(view);
    }
}
