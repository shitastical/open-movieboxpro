package com.google.android.gms.cast;

import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzm implements Cast.ApplicationConnectionResult {
    private final /* synthetic */ Status zzam;

    zzm(Cast.zza zzaVar, Status status) {
        this.zzam = status;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final ApplicationMetadata getApplicationMetadata() {
        return null;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final String getApplicationStatus() {
        return null;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final String getSessionId() {
        return null;
    }

    @Override // com.google.android.gms.cast.Cast.ApplicationConnectionResult
    public final boolean getWasLaunched() {
        return false;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzam;
    }
}
