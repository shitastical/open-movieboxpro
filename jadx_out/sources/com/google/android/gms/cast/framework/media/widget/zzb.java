package com.google.android.gms.cast.framework.media.widget;

import android.view.View;

/* JADX INFO: loaded from: classes4.dex */
final class zzb implements View.OnClickListener {
    private final /* synthetic */ ExpandedControllerActivity zzva;

    zzb(ExpandedControllerActivity expandedControllerActivity) {
        this.zzva = expandedControllerActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.zzva.zzut.isClickable()) {
            this.zzva.getRemoteMediaClient().skipAd();
        }
    }
}
