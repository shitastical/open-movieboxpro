package com.google.android.gms.internal.cast;

import android.os.Handler;
import android.os.Looper;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class zzed {
    private long zzaar;
    private zzec zzaas;
    private Runnable zzpz;
    private static final zzdw zzbf = new zzdw("RequestTracker");
    private static final Object zzaat = new Object();
    private long zzwu = -1;
    private final Handler handler = new zzez(Looper.getMainLooper());

    public zzed(long j) {
        this.zzaar = j;
    }

    public final void zza(long j, zzec zzecVar) {
        zzec zzecVar2;
        long j2;
        Object obj = zzaat;
        synchronized (obj) {
            zzecVar2 = this.zzaas;
            j2 = this.zzwu;
            this.zzwu = j;
            this.zzaas = zzecVar;
        }
        if (zzecVar2 != null) {
            zzecVar2.zzd(j2);
        }
        synchronized (obj) {
            Runnable runnable = this.zzpz;
            if (runnable != null) {
                this.handler.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable(this) { // from class: com.google.android.gms.internal.cast.zzee
                private final zzed zzaau;

                {
                    this.zzaau = this;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    this.zzaau.zzfe();
                }
            };
            this.zzpz = runnable2;
            this.handler.postDelayed(runnable2, this.zzaar);
        }
    }

    public final boolean zzfd() {
        boolean z;
        synchronized (zzaat) {
            z = this.zzwu != -1;
        }
        return z;
    }

    public final boolean test(long j) {
        boolean z;
        synchronized (zzaat) {
            long j2 = this.zzwu;
            z = j2 != -1 && j2 == j;
        }
        return z;
    }

    public final boolean zzc(long j, int i, Object obj) {
        synchronized (zzaat) {
            long j2 = this.zzwu;
            if (j2 == -1 || j2 != j) {
                return false;
            }
            zza(i, obj, String.format(Locale.ROOT, "request %d completed", Long.valueOf(j)));
            return true;
        }
    }

    public final boolean zzv(int i) {
        return zza(2002, (Object) null);
    }

    private final boolean zza(int i, Object obj) {
        synchronized (zzaat) {
            if (this.zzwu == -1) {
                return false;
            }
            zza(i, null, String.format(Locale.ROOT, "clearing request %d", Long.valueOf(this.zzwu)));
            return true;
        }
    }

    private final void zza(int i, Object obj, String str) {
        zzbf.d(str, new Object[0]);
        Object obj2 = zzaat;
        synchronized (obj2) {
            zzec zzecVar = this.zzaas;
            if (zzecVar != null) {
                zzecVar.zza(this.zzwu, i, obj);
            }
            this.zzwu = -1L;
            this.zzaas = null;
            synchronized (obj2) {
                Runnable runnable = this.zzpz;
                if (runnable != null) {
                    this.handler.removeCallbacks(runnable);
                    this.zzpz = null;
                }
            }
        }
    }

    final /* synthetic */ void zzfe() {
        synchronized (zzaat) {
            if (this.zzwu == -1) {
                return;
            }
            zza(15, (Object) null);
        }
    }
}
