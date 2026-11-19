package com.google.android.gms.internal.cast;

import android.animation.ObjectAnimator;
import android.view.View;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: loaded from: classes4.dex */
final class zzs implements View.OnClickListener {
    final /* synthetic */ zzr zzjl;

    zzs(zzr zzrVar) {
        this.zzjl = zzrVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (!PlatformVersion.isAtLeastJellyBean()) {
            this.zzjl.zzaw();
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "alpha", 0.0f);
        objectAnimatorOfFloat.setDuration(400L).addListener(new zzt(this));
        objectAnimatorOfFloat.start();
    }
}
