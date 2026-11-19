package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.cast.zzdw;

/* JADX INFO: loaded from: classes4.dex */
public final class zze {
    private static final zzdw zzbf = new zzdw("DiscoveryManager");
    private final zzp zzii;

    zze(zzp zzpVar) {
        this.zzii = zzpVar;
    }

    public final IObjectWrapper zzaa() {
        try {
            return this.zzii.zzae();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getWrappedThis", "zzp");
            return null;
        }
    }
}
