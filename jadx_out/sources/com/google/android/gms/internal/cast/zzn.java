package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RelativeLayout;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.R;

/* JADX INFO: loaded from: classes4.dex */
public final class zzn extends RelativeLayout implements IntroductoryOverlay {
    private int color;
    private Activity zzij;
    private View zzik;
    private String zzim;
    private IntroductoryOverlay.OnOverlayDismissedListener zzin;
    private final boolean zzje;
    private com.google.android.gms.cast.framework.internal.featurehighlight.zza zzjf;
    private boolean zzjg;

    public zzn(IntroductoryOverlay.Builder builder) {
        super(builder.getActivity());
        this.zzij = builder.getActivity();
        this.zzje = builder.zzal();
        this.zzin = builder.zzaj();
        this.zzik = builder.zzai();
        this.zzim = builder.zzam();
        this.color = builder.zzak();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reset() {
        removeAllViews();
        this.zzij = null;
        this.zzin = null;
        this.zzik = null;
        this.zzjf = null;
        this.zzim = null;
        this.color = 0;
        this.zzjg = false;
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void show() {
        Activity activity = this.zzij;
        if (activity == null || this.zzik == null || this.zzjg || zzg(activity)) {
            return;
        }
        if (this.zzje && IntroductoryOverlay.zza.zze(this.zzij)) {
            reset();
            return;
        }
        com.google.android.gms.cast.framework.internal.featurehighlight.zza zzaVar = new com.google.android.gms.cast.framework.internal.featurehighlight.zza(this.zzij);
        this.zzjf = zzaVar;
        int i = this.color;
        if (i != 0) {
            zzaVar.zzk(i);
        }
        addView(this.zzjf);
        com.google.android.gms.cast.framework.internal.featurehighlight.zzi zziVar = (com.google.android.gms.cast.framework.internal.featurehighlight.zzi) this.zzij.getLayoutInflater().inflate(R.layout.cast_help_text, (ViewGroup) this.zzjf, false);
        zziVar.setText(this.zzim, null);
        this.zzjf.zza(zziVar);
        this.zzjf.zza(this.zzik, null, true, new zzo(this));
        this.zzjg = true;
        ((ViewGroup) this.zzij.getWindow().getDecorView()).addView(this);
        this.zzjf.zza((Runnable) null);
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void remove() {
        if (this.zzjg) {
            ((ViewGroup) this.zzij.getWindow().getDecorView()).removeView(this);
            reset();
        }
    }

    static boolean zzg(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        return accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled();
    }
}
