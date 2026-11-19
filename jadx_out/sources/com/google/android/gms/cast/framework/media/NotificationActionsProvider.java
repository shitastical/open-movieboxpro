package com.google.android.gms.cast.framework.media;

import android.content.Context;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class NotificationActionsProvider {
    private final Context zzhe;
    private final zzd zznq = new zza();

    public NotificationActionsProvider(Context context) {
        this.zzhe = context.getApplicationContext();
    }

    public abstract int[] getCompactViewActionIndices();

    public abstract List<NotificationAction> getNotificationActions();

    private class zza extends zzd.zza {
        private zza() {
        }

        @Override // com.google.android.gms.cast.framework.media.zzd
        public final int zzs() {
            return 12451009;
        }

        @Override // com.google.android.gms.cast.framework.media.zzd
        public final IObjectWrapper zzbi() {
            return ObjectWrapper.wrap(NotificationActionsProvider.this);
        }

        @Override // com.google.android.gms.cast.framework.media.zzd
        public final List<NotificationAction> getNotificationActions() {
            return NotificationActionsProvider.this.getNotificationActions();
        }

        @Override // com.google.android.gms.cast.framework.media.zzd
        public final int[] getCompactViewActionIndices() {
            return NotificationActionsProvider.this.getCompactViewActionIndices();
        }
    }

    public Context getApplicationContext() {
        return this.zzhe;
    }

    public final zzd zzby() {
        return this.zznq;
    }
}
