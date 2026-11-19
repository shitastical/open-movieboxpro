package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzcv<R extends Result> extends BaseImplementation.ApiMethodImpl<R, zzdd> {
    public zzcv(GoogleApiClient googleApiClient) {
        super(Cast.API, googleApiClient);
    }

    public final void zzp(int i) {
        setResult((Result) createFailedResult(new Status(2001)));
    }
}
