package com.google.android.gms.cast.framework.media;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.TaskStackBuilder;

/* JADX INFO: loaded from: classes4.dex */
final class zzh extends BroadcastReceiver {
    private final /* synthetic */ MediaNotificationService zzmi;

    zzh(MediaNotificationService mediaNotificationService) {
        this.zzmi = mediaNotificationService;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        PendingIntent pendingIntent;
        ComponentName componentName = (ComponentName) intent.getParcelableExtra("targetActivity");
        Intent intent2 = new Intent();
        intent2.setComponent(componentName);
        if (this.zzmi.zzmg.zzw()) {
            intent2.setFlags(603979776);
            pendingIntent = PendingIntent.getActivity(context, 1, intent2, 134217728);
        } else {
            TaskStackBuilder taskStackBuilderCreate = TaskStackBuilder.create(this.zzmi);
            taskStackBuilderCreate.addParentStack(componentName);
            taskStackBuilderCreate.addNextIntent(intent2);
            pendingIntent = taskStackBuilderCreate.getPendingIntent(1, 134217728);
        }
        try {
            pendingIntent.send(context, 1, new Intent().setFlags(268435456));
        } catch (PendingIntent.CanceledException e) {
            MediaNotificationService.zzbf.zza(e, "Sending PendingIntent failed", new Object[0]);
        }
    }
}
