package com.google.android.gms.internal.measurement;

import java.io.Serializable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-measurement-base@@21.3.0 */
/* JADX INFO: loaded from: classes4.dex */
public final class zzir {
    public static zzim zza(zzim zzimVar) {
        if ((zzimVar instanceof zzip) || (zzimVar instanceof zzin)) {
            return zzimVar;
        }
        return zzimVar instanceof Serializable ? new zzin(zzimVar) : new zzip(zzimVar);
    }

    public static zzim zzb(Object obj) {
        return new zziq(obj);
    }
}
