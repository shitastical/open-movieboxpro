package com.google.android.gms.internal.cast;

import android.text.TextUtils;

/* JADX INFO: loaded from: classes4.dex */
public class zzdc {
    protected final zzdw zzxw;
    private final String zzxx;
    private zzeb zzxy;

    protected zzdc(String str, String str2, String str3) {
        zzdk.zzp(str);
        this.zzxx = str;
        this.zzxw = new zzdw(str2);
        setSessionLabel(str3);
    }

    public void zza(long j, int i) {
    }

    public void zzeq() {
    }

    public void zzo(String str) {
    }

    public final void setSessionLabel(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.zzxw.zzu(str);
    }

    public final String getNamespace() {
        return this.zzxx;
    }

    public final void zza(zzeb zzebVar) {
        this.zzxy = zzebVar;
        if (zzebVar == null) {
            zzeq();
        }
    }

    protected final void zza(String str, long j, String str2) throws IllegalStateException {
        this.zzxy.zza(this.zzxx, str, j, null);
    }

    protected final long zzes() {
        return this.zzxy.zzr();
    }
}
