package com.google.android.gms.internal.cast;

import android.hardware.display.DisplayManager;
import android.os.RemoteException;
import android.view.Display;
import android.view.Surface;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
public final class zzen extends zzel {
    private final zzer zzabb;
    private final /* synthetic */ zzem zzabc;

    public zzen(zzem zzemVar, zzer zzerVar) {
        this.zzabc = zzemVar;
        this.zzabb = zzerVar;
    }

    @Override // com.google.android.gms.internal.cast.zzel, com.google.android.gms.internal.cast.zzet
    public final void zza(int i, int i2, Surface surface) {
        zzeh.zzbf.d("onConnected", new Object[0]);
        DisplayManager displayManager = (DisplayManager) this.zzabb.getContext().getSystemService("display");
        if (displayManager == null) {
            zzeh.zzbf.e("Unable to get the display manager", new Object[0]);
            this.zzabc.setResult(new zzep(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        this.zzabc.zzaaz.zzc();
        this.zzabc.zzaaz.zzbg = displayManager.createVirtualDisplay("private_display", i, i2, ((i < i2 ? i : i2) * 320) / 1080, surface, 2);
        if (this.zzabc.zzaaz.zzbg == null) {
            zzeh.zzbf.e("Unable to create virtual display", new Object[0]);
            this.zzabc.setResult(new zzep(Status.RESULT_INTERNAL_ERROR));
        } else {
            if (this.zzabc.zzaaz.zzbg.getDisplay() == null) {
                zzeh.zzbf.e("Virtual display does not have a display", new Object[0]);
                this.zzabc.setResult(new zzep(Status.RESULT_INTERNAL_ERROR));
                return;
            }
            try {
                ((zzev) this.zzabb.getService()).zza(this, this.zzabc.zzaaz.zzbg.getDisplay().getDisplayId());
            } catch (RemoteException | IllegalStateException unused) {
                zzeh.zzbf.e("Unable to provision the route's new virtual Display", new Object[0]);
                this.zzabc.setResult(new zzep(Status.RESULT_INTERNAL_ERROR));
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzel, com.google.android.gms.internal.cast.zzet
    public final void zzd() {
        zzeh.zzbf.d("onConnectedWithDisplay", new Object[0]);
        if (this.zzabc.zzaaz.zzbg == null) {
            zzeh.zzbf.e("There is no virtual display", new Object[0]);
            this.zzabc.setResult(new zzep(Status.RESULT_INTERNAL_ERROR));
            return;
        }
        Display display = this.zzabc.zzaaz.zzbg.getDisplay();
        if (display == null) {
            zzeh.zzbf.e("Virtual display no longer has a display", new Object[0]);
            this.zzabc.setResult(new zzep(Status.RESULT_INTERNAL_ERROR));
        } else {
            this.zzabc.setResult(new zzep(display));
        }
    }

    @Override // com.google.android.gms.internal.cast.zzel, com.google.android.gms.internal.cast.zzet
    public final void onError(int i) throws RemoteException {
        zzeh.zzbf.d("onError: %d", Integer.valueOf(i));
        this.zzabc.zzaaz.zzc();
        this.zzabc.setResult(new zzep(Status.RESULT_INTERNAL_ERROR));
    }
}
