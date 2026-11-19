package com.google.android.gms.cast.framework;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.cast.zzdw;

/* JADX INFO: loaded from: classes4.dex */
public class ReconnectionService extends Service {
    private static final zzdw zzbf = new zzdw("ReconnectionService");
    private zzr zzis;

    @Override // android.app.Service
    public void onCreate() {
        CastContext sharedInstance = CastContext.getSharedInstance(this);
        zzr zzrVarZza = com.google.android.gms.internal.cast.zze.zza(this, sharedInstance.getSessionManager().zzaa(), sharedInstance.zzx().zzaa());
        this.zzis = zzrVarZza;
        try {
            zzrVarZza.onCreate();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onCreate", "zzr");
        }
        super.onCreate();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        try {
            return this.zzis.onStartCommand(intent, i, i2);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onStartCommand", "zzr");
            return 1;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        try {
            return this.zzis.onBind(intent);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onBind", "zzr");
            return null;
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        try {
            this.zzis.onDestroy();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onDestroy", "zzr");
        }
        super.onDestroy();
    }
}
