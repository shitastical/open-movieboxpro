package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzde implements Cast.ApplicationConnectionResult {
    private final Status zzgq;
    private final ApplicationMetadata zzyt;
    private final String zzyu;
    private final String zzyv;
    private final boolean zzyw;

    public zzde(Status status, ApplicationMetadata applicationMetadata, String str, String str2, boolean z) {
        this.zzgq = status;
        this.zzyt = applicationMetadata;
        this.zzyu = str;
        this.zzyv = str2;
        this.zzyw = z;
    }

    public zzde(Status status) {
        this(status, null, null, null, false);
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzgq;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final ApplicationMetadata getApplicationMetadata() {
        return this.zzyt;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final String getApplicationStatus() {
        return this.zzyu;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final String getSessionId() {
        return this.zzyv;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final boolean getWasLaunched() {
        return this.zzyw;
    }
}
