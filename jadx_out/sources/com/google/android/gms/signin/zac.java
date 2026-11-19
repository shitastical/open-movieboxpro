package com.google.android.gms.signin;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.internal.IAccountAccessor;

/* JADX INFO: compiled from: com.google.android.gms:play-services-base@@17.1.0 */
/* JADX INFO: loaded from: classes4.dex */
public interface zac extends Api.Client {
    void connect();

    void zaa(IAccountAccessor iAccountAccessor, boolean z);

    void zaa(com.google.android.gms.signin.internal.zac zacVar);

    void zacu();
}
