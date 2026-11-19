package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Display;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.internal.cast.zzeq;
import com.google.android.gms.internal.cast.zzev;
import com.google.android.gms.tasks.TaskCompletionSource;

/* JADX INFO: loaded from: classes4.dex */
final class zzq extends TaskApiCall<zzeq, Display> {
    private final /* synthetic */ String zzag;
    private final /* synthetic */ int zzbh;
    private final /* synthetic */ PendingIntent zzbi;
    private final /* synthetic */ CastDevice zzbj;
    final /* synthetic */ CastRemoteDisplayClient zzbk;

    zzq(CastRemoteDisplayClient castRemoteDisplayClient, int i, PendingIntent pendingIntent, CastDevice castDevice, String str) {
        this.zzbk = castRemoteDisplayClient;
        this.zzbh = i;
        this.zzbi = pendingIntent;
        this.zzbj = castDevice;
        this.zzag = str;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Display> taskCompletionSource) throws RemoteException {
        zzeq zzeqVar = (zzeq) anyClient;
        Bundle bundle = new Bundle();
        bundle.putInt("configuration", this.zzbh);
        ((zzev) zzeqVar.getService()).zza(new zzr(this, taskCompletionSource, zzeqVar), this.zzbi, this.zzbj.getDeviceId(), this.zzag, bundle);
    }
}
