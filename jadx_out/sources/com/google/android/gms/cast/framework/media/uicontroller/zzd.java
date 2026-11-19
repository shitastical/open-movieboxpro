package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
final class zzd implements View.OnClickListener {
    private final /* synthetic */ UIMediaController zzsd;
    private final /* synthetic */ long zzse;

    zzd(UIMediaController uIMediaController, long j) {
        this.zzsd = uIMediaController;
        this.zzse = j;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzsd.onForwardClicked(view, this.zzse);
    }
}
