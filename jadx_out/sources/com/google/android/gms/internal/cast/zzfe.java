package com.google.android.gms.internal.cast;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzfe {
    private static final ThreadLocal<zzfe> zzabo = new zzff();

    public abstract void zza(zzfg zzfgVar);

    public static zzfe zzfh() {
        return zzabo.get();
    }
}
