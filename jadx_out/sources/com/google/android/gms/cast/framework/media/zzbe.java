package com.google.android.gms.cast.framework.media;

import android.content.DialogInterface;

/* JADX INFO: loaded from: classes4.dex */
final class zzbe implements DialogInterface.OnClickListener {
    private final /* synthetic */ TracksChooserDialogFragment zzqm;
    private final /* synthetic */ zzbf zzqn;
    private final /* synthetic */ zzbf zzqo;

    zzbe(TracksChooserDialogFragment tracksChooserDialogFragment, zzbf zzbfVar, zzbf zzbfVar2) {
        this.zzqm = tracksChooserDialogFragment;
        this.zzqn = zzbfVar;
        this.zzqo = zzbfVar2;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzqm.zza(this.zzqn, this.zzqo);
    }
}
