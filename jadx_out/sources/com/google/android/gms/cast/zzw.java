package com.google.android.gms.cast;

import android.content.ComponentName;
import android.content.Context;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzw implements ServiceConnection {
    private final /* synthetic */ String zzag;
    private final /* synthetic */ CastDevice zzcj;
    private final /* synthetic */ CastRemoteDisplayLocalService.Options zzck;
    private final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zzcl;
    private final /* synthetic */ Context zzcm;
    private final /* synthetic */ CastRemoteDisplayLocalService.Callbacks zzcn;

    zzw(String str, CastDevice castDevice, CastRemoteDisplayLocalService.Options options, CastRemoteDisplayLocalService.NotificationSettings notificationSettings, Context context, CastRemoteDisplayLocalService.Callbacks callbacks) {
        this.zzag = str;
        this.zzcj = castDevice;
        this.zzck = options;
        this.zzcl = notificationSettings;
        this.zzcm = context;
        this.zzcn = callbacks;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = CastRemoteDisplayLocalService.this;
        if (castRemoteDisplayLocalService == null || !castRemoteDisplayLocalService.zza(this.zzag, this.zzcj, this.zzck, this.zzcl, this.zzcm, this, this.zzcn)) {
            CastRemoteDisplayLocalService.zzbf.e("Connected but unable to get the service instance", new Object[0]);
            this.zzcn.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
            CastRemoteDisplayLocalService.zzbr.set(false);
            try {
                this.zzcm.unbindService(this);
            } catch (IllegalArgumentException unused) {
                CastRemoteDisplayLocalService.zzbf.d("No need to unbind service, already unbound", new Object[0]);
            }
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        CastRemoteDisplayLocalService.zzbf.d("onServiceDisconnected", new Object[0]);
        this.zzcn.onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_DISCONNECTED, "Service Disconnected"));
        CastRemoteDisplayLocalService.zzbr.set(false);
        try {
            this.zzcm.unbindService(this);
        } catch (IllegalArgumentException unused) {
            CastRemoteDisplayLocalService.zzbf.d("No need to unbind service, already unbound", new Object[0]);
        }
    }
}
