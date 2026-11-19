package com.google.android.gms.cast;

import android.view.Display;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

/* JADX INFO: loaded from: classes4.dex */
final class zzaa implements OnCompleteListener<Void> {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzaa(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task<Void> task) {
        if (task.isSuccessful()) {
            this.zzci.zzb("remote display stopped");
        } else {
            this.zzci.zzb("Unable to stop the remote display, result unsuccessful");
            if (this.zzci.zzbs.get() != null) {
                ((CastRemoteDisplayLocalService.Callbacks) this.zzci.zzbs.get()).onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_STOPPING_SERVICE_FAILED));
            }
        }
        CastRemoteDisplayLocalService.zzb(this.zzci, (Display) null);
    }
}
