package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.flags.Flag;
import com.google.android.gms.flags.FlagRegistry;
import com.google.android.gms.flags.Singletons;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdn {
    public static final Flag<Boolean> zzze = Flag.define(0, "gms:cast:remote_display_enabled", (Boolean) false);

    public static final void initialize(Context context) {
        Singletons.flagRegistry();
        FlagRegistry.initialize(context);
    }
}
