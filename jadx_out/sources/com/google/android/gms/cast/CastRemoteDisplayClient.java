package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.content.Context;
import android.hardware.display.VirtualDisplay;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.internal.cast.zzdv;
import com.google.android.gms.internal.cast.zzdw;
import com.google.android.gms.internal.cast.zzeq;
import com.google.android.gms.internal.cast.zzeu;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: classes4.dex */
public class CastRemoteDisplayClient extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.AbstractClientBuilder<zzeq, Api.ApiOptions.NoOptions> zzad;
    private final zzdw zzbf;
    private VirtualDisplay zzbg;

    /* JADX INFO: Access modifiers changed from: private */
    static class zza extends zzeu {
        private zza() {
        }

        @Override // com.google.android.gms.internal.cast.zzet
        public void zza(int i, int i2, Surface surface) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.gms.internal.cast.zzet
        public void onError(int i) throws RemoteException {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.gms.internal.cast.zzet
        public void onDisconnected() throws RemoteException {
            throw new UnsupportedOperationException();
        }

        @Override // com.google.android.gms.internal.cast.zzet
        public void zzd() throws RemoteException {
            throw new UnsupportedOperationException();
        }

        /* synthetic */ zza(zzp zzpVar) {
            this();
        }
    }

    CastRemoteDisplayClient(Context context) {
        super(context, API, (Api.ApiOptions) null, GoogleApi.Settings.DEFAULT_SETTINGS);
        this.zzbf = new zzdw("CastRemoteDisplay");
    }

    public Task<Display> startRemoteDisplay(CastDevice castDevice, String str, int i, PendingIntent pendingIntent) {
        return doWrite(new zzq(this, i, pendingIntent, castDevice, str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int zza(int i, int i2) {
        return (Math.min(i, i2) * 320) / 1080;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc() {
        VirtualDisplay virtualDisplay = this.zzbg;
        if (virtualDisplay != null) {
            if (virtualDisplay.getDisplay() != null) {
                zzdw zzdwVar = this.zzbf;
                int displayId = this.zzbg.getDisplay().getDisplayId();
                StringBuilder sb = new StringBuilder(38);
                sb.append("releasing virtual display: ");
                sb.append(displayId);
                zzdwVar.d(sb.toString(), new Object[0]);
            }
            this.zzbg.release();
            this.zzbg = null;
        }
    }

    public Task<Void> stopRemoteDisplay() {
        return doWrite(new zzs(this));
    }

    static {
        zzp zzpVar = new zzp();
        zzad = zzpVar;
        API = new Api<>("CastRemoteDisplay.API", zzpVar, zzdv.zzzh);
    }
}
