package com.google.android.gms.cast.framework.media.uicontroller;

import android.widget.SeekBar;
import com.google.android.gms.internal.cast.zzbn;

/* JADX INFO: loaded from: classes4.dex */
final class zzf implements SeekBar.OnSeekBarChangeListener {
    private final /* synthetic */ UIMediaController zzsd;
    private final /* synthetic */ zzbn zzsf;
    private final /* synthetic */ SeekBar zzsg;

    zzf(UIMediaController uIMediaController, zzbn zzbnVar, SeekBar seekBar) {
        this.zzsd = uIMediaController;
        this.zzsf = zzbnVar;
        this.zzsg = seekBar;
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
        this.zzsd.onSeekBarStopTrackingTouch(seekBar);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
        this.zzsd.onSeekBarStartTrackingTouch(seekBar);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        zzbn zzbnVar = this.zzsf;
        if (zzbnVar != null) {
            zzbnVar.zzde();
        }
        if (this.zzsd.zzrw.zzdo()) {
            if (z && i < this.zzsd.zzrw.zzdr()) {
                int iZzdr = this.zzsd.zzrw.zzdr();
                this.zzsg.setProgress(iZzdr);
                this.zzsd.onSeekBarProgressChanged(seekBar, iZzdr, true);
                return;
            } else if (z && i > this.zzsd.zzrw.zzds()) {
                int iZzds = this.zzsd.zzrw.zzds();
                this.zzsg.setProgress(iZzds);
                this.zzsd.onSeekBarProgressChanged(seekBar, iZzds, true);
                return;
            }
        }
        this.zzsd.onSeekBarProgressChanged(seekBar, i, z);
    }
}
