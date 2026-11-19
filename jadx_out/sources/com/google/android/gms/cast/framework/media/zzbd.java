package com.google.android.gms.cast.framework.media;

import android.app.Dialog;
import android.content.DialogInterface;

/* JADX INFO: loaded from: classes4.dex */
final class zzbd implements DialogInterface.OnClickListener {
    private final /* synthetic */ TracksChooserDialogFragment zzqm;

    zzbd(TracksChooserDialogFragment tracksChooserDialogFragment) {
        this.zzqm = tracksChooserDialogFragment;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        if (this.zzqm.zzqj != null) {
            this.zzqm.zzqj.cancel();
            TracksChooserDialogFragment.zza(this.zzqm, (Dialog) null);
        }
    }
}
