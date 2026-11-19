package com.google.android.gms.cast;

import android.app.PendingIntent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdo;

/* JADX INFO: loaded from: classes4.dex */
final class zzl extends zzdo {
    private final /* synthetic */ String val$sessionId;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzl(Cast.CastApi.zza zzaVar, GoogleApiClient googleApiClient, String str) {
        super(googleApiClient);
        this.val$sessionId = str;
    }

    @Override // com.google.android.gms.internal.cast.zzdo
    /* JADX INFO: renamed from: zza */
    public final void doExecute(zzdd zzddVar) throws RemoteException {
        if (TextUtils.isEmpty(this.val$sessionId)) {
            setResult(createFailedResult(new Status(2001, "IllegalArgument: sessionId cannot be null or empty", (PendingIntent) null)));
            return;
        }
        try {
            zzddVar.zza(this.val$sessionId, this);
        } catch (IllegalStateException unused) {
            zzp(2001);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzdo, com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
    public final /* synthetic */ void doExecute(Api.AnyClient anyClient) throws RemoteException {
        doExecute((zzdd) anyClient);
    }
}
