package com.google.android.gms.internal.cast;

import android.os.Handler;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes4.dex */
final class zzdf extends zzds {
    private final Handler handler;
    private final AtomicReference<zzdd> zzyx;

    public zzdf(zzdd zzddVar) {
        this.zzyx = new AtomicReference<>(zzddVar);
        this.handler = new zzez(zzddVar.getLooper());
    }

    public final zzdd zzex() {
        zzdd andSet = this.zzyx.getAndSet(null);
        if (andSet == null) {
            return null;
        }
        andSet.zzet();
        return andSet;
    }

    public final boolean isDisposed() {
        return this.zzyx.get() == null;
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzs(int i) {
        zzdd zzddVarZzex = zzex();
        if (zzddVarZzex == null) {
            return;
        }
        zzdd.zzbf.d("ICastDeviceControllerListener.onDisconnected: %d", Integer.valueOf(i));
        if (i != 0) {
            zzddVarZzex.triggerConnectionSuspended(2);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zza(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzddVar.zzxz = applicationMetadata;
        zzddVar.zzyl = applicationMetadata.getApplicationId();
        zzddVar.zzym = str2;
        zzddVar.zzyd = str;
        synchronized (zzdd.zzyr) {
            if (zzddVar.zzyp != null) {
                zzddVar.zzyp.setResult(new zzde(new Status(0), applicationMetadata, str, str2, z));
                zzdd.zza(zzddVar, (BaseImplementation.ResultHolder) null);
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzi(int i) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzddVar.zzq(i);
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzt(int i) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzddVar.zzr(i);
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzu(int i) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzddVar.zzr(i);
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void onApplicationDisconnected(int i) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzddVar.zzyl = null;
        zzddVar.zzym = null;
        zzddVar.zzr(i);
        if (zzddVar.zzak != null) {
            this.handler.post(new zzdg(this, zzddVar, i));
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zza(String str, double d, boolean z) {
        zzdd.zzbf.d("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzb(zzdl zzdlVar) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzdd.zzbf.d("onDeviceStatusChanged", new Object[0]);
        this.handler.post(new zzdh(this, zzddVar, zzdlVar));
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzb(zzct zzctVar) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzdd.zzbf.d("onApplicationStatusChanged", new Object[0]);
        this.handler.post(new zzdi(this, zzddVar, zzctVar));
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzb(String str, String str2) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzdd.zzbf.d("Receive (type=text, ns=%s) %s", str, str2);
        this.handler.post(new zzdj(this, zzddVar, str, str2));
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zza(String str, byte[] bArr) {
        if (this.zzyx.get() == null) {
            return;
        }
        zzdd.zzbf.d("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", str, Integer.valueOf(bArr.length));
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zza(String str, long j, int i) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzddVar.zzb(j, i);
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zza(String str, long j) {
        zzdd zzddVar = this.zzyx.get();
        if (zzddVar == null) {
            return;
        }
        zzddVar.zzb(j, 0);
    }
}
