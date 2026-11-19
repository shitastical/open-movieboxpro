package com.google.android.gms.cast;

import android.app.Presentation;
import android.content.Context;
import android.view.Display;
import android.view.Window;

/* JADX INFO: loaded from: classes4.dex */
public class CastPresentation extends Presentation {
    public CastPresentation(Context context, Display display) {
        super(context, display);
        zzb();
    }

    public CastPresentation(Context context, Display display, int i) {
        super(context, display, i);
        zzb();
    }

    private final void zzb() {
        Window window = getWindow();
        if (window != null) {
            window.setType(2030);
            window.addFlags(268435456);
            window.addFlags(16777216);
            window.addFlags(1024);
        }
    }
}
