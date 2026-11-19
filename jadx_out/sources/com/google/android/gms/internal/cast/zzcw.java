package com.google.android.gms.internal.cast;

import j$.util.DesugarCollections;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class zzcw extends zzdc {
    private final List<zzed> zzxt;

    public zzcw(String str, String str2, String str3) {
        super(str, str2, null);
        this.zzxt = DesugarCollections.synchronizedList(new ArrayList());
    }

    @Override // com.google.android.gms.internal.cast.zzdc
    public void zzeq() {
        synchronized (this.zzxt) {
            Iterator<zzed> it = this.zzxt.iterator();
            while (it.hasNext()) {
                it.next().zzv(2002);
            }
        }
    }

    protected final List<zzed> zzer() {
        return this.zzxt;
    }

    protected final void zza(zzed zzedVar) {
        this.zzxt.add(zzedVar);
    }
}
