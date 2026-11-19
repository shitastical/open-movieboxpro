package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
final class zzcz extends TaskApiCall<zzdb, Void> {
    private final /* synthetic */ List zzpp;
    private final /* synthetic */ String[] zzxu;
    private final /* synthetic */ String zzxv;

    zzcz(zzcx zzcxVar, String[] strArr, String str, List list) {
        this.zzxu = strArr;
        this.zzxv = str;
        this.zzpp = list;
    }

    @Override // com.google.android.gms.common.api.internal.TaskApiCall
    protected final /* synthetic */ void doExecute(Api.AnyClient anyClient, TaskCompletionSource<Void> taskCompletionSource) throws RemoteException {
        ((zzdt) ((zzdb) anyClient).getService()).zza(new zzda(this, taskCompletionSource), this.zzxu, this.zzxv, this.zzpp);
    }
}
