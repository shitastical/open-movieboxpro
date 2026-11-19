package com.google.android.gms.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.cast.zzdd;

/* JADX INFO: loaded from: classes4.dex */
final class zzg extends Cast.zza {
    private final /* synthetic */ String zzag;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzg(Cast.CastApi.zza zzaVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.zzag = str;
    }

    @Override // com.google.android.gms.cast.Cast.zza
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzdd zzddVar) throws RemoteException {
        try {
            String str = this.zzag;
            LaunchOptions launchOptions = new LaunchOptions();
            launchOptions.setRelaunchIfRunning(false);
            zzddVar.zza(str, launchOptions, this);
        } catch (IllegalStateException unused) {
            zzp(2001);
        }
    }

    @Override // com.google.android.gms.cast.Cast.zza, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzdd) anyClient);
    }
}
