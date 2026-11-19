package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzcj<R extends Result> extends zzcv<R> {
    protected zzec zzwq;

    public zzcj(zzcb zzcbVar) {
        super(zzcbVar.zzpb);
    }

    public abstract void execute();

    @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    protected /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        execute();
    }
}
