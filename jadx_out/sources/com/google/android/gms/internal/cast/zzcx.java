package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class zzcx extends GoogleApi<Api.ApiOptions.NoOptions> {
    private static final Api<Api.ApiOptions.NoOptions> API;
    private static final Api.ClientKey<zzdb> CLIENT_KEY;
    private static final Api.AbstractClientBuilder<zzdb, Api.ApiOptions.NoOptions> zzad;

    public zzcx(Context context) {
        super(context, API, (Api.ApiOptions) null, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    public final Task<Void> zza(String[] strArr, String str, List<com.google.android.gms.cast.zzbx> list) {
        return doWrite(new zzcz(this, strArr, str, null));
    }

    static {
        Api.ClientKey<zzdb> clientKey = new Api.ClientKey<>();
        CLIENT_KEY = clientKey;
        zzcy zzcyVar = new zzcy();
        zzad = zzcyVar;
        API = new Api<>("CastApi.API", zzcyVar, clientKey);
    }
}
