package com.google.android.gms.cast;

import android.content.Context;
import android.content.ServiceConnection;
import android.view.Display;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: classes4.dex */
final class zzz implements OnCompleteListener<Display> {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzz(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<Display> task) {
        if (!task.isSuccessful()) {
            CastRemoteDisplayLocalService.zzbf.e("Connection was not successful", new Object[0]);
            this.zzci.zze();
            return;
        }
        CastRemoteDisplayLocalService.zzbf.d("startRemoteDisplay successful", new Object[0]);
        synchronized (CastRemoteDisplayLocalService.zzbq) {
            if (CastRemoteDisplayLocalService.zzcg == null) {
                CastRemoteDisplayLocalService.zzbf.d("Remote Display started but session already cancelled", new Object[0]);
                this.zzci.zze();
                return;
            }
            Display result = task.getResult();
            if (result != null) {
                this.zzci.zza(result);
            } else {
                CastRemoteDisplayLocalService.zzbf.e("Cast Remote Display session created without display", new Object[0]);
            }
            CastRemoteDisplayLocalService.zzbr.set(false);
            if (this.zzci.zzca == null || this.zzci.zzcb == null) {
                return;
            }
            try {
                this.zzci.zzca.unbindService(this.zzci.zzcb);
            } catch (IllegalArgumentException unused) {
                CastRemoteDisplayLocalService.zzbf.d("No need to unbind service, already unbound", new Object[0]);
            }
            CastRemoteDisplayLocalService.zza(this.zzci, (ServiceConnection) null);
            CastRemoteDisplayLocalService.zza(this.zzci, (Context) null);
        }
    }
}
