package com.google.android.gms.internal.cast;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: loaded from: classes4.dex */
public final class zzca extends zzbt {
    private final SeekBar zzrp;
    private final zzbh zzrw;
    private final TextView zzto;
    private final RelativeLayout zzvt;

    public zzca(RelativeLayout relativeLayout, SeekBar seekBar, zzbh zzbhVar) {
        this.zzvt = relativeLayout;
        this.zzto = (TextView) relativeLayout.findViewById(R.id.tooltip);
        this.zzrp = seekBar;
        this.zzrw = zzbhVar;
    }

    @Override // com.google.android.gms.internal.cast.zzbt
    public final void zzg(long j) {
        zzdj();
    }

    @Override // com.google.android.gms.internal.cast.zzbt
    public final void zzk(boolean z) {
        super.zzk(z);
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        super.onSessionEnded();
        zzdj();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzdj();
    }

    private final void zzdj() {
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession() || zzee()) {
            this.zzvt.setVisibility(8);
            return;
        }
        this.zzvt.setVisibility(0);
        TextView textView = this.zzto;
        zzbh zzbhVar = this.zzrw;
        textView.setText(zzbhVar.zze(zzbhVar.zzo(this.zzrp.getProgress())));
        int measuredWidth = (this.zzrp.getMeasuredWidth() - this.zzrp.getPaddingLeft()) - this.zzrp.getPaddingRight();
        this.zzto.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        int measuredWidth2 = this.zzto.getMeasuredWidth();
        int iMin = Math.min(Math.max(0, ((int) (((((double) this.zzrp.getProgress()) * 1.0d) / ((double) this.zzrp.getMax())) * ((double) measuredWidth))) - (measuredWidth2 / 2)), measuredWidth - measuredWidth2);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.zzto.getLayoutParams();
        layoutParams.leftMargin = iMin;
        this.zzto.setLayoutParams(layoutParams);
    }
}
