package com.google.android.gms.internal.cast;

import android.view.Display;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.Status;

/* JADX INFO: loaded from: classes4.dex */
final class zzep implements CastRemoteDisplay.CastRemoteDisplaySessionResult {
    private final Display zzbz;
    private final Status zzgq;

    public zzep(Display display) {
        this.zzgq = Status.RESULT_SUCCESS;
        this.zzbz = display;
    }

    public zzep(Status status) {
        this.zzgq = status;
        this.zzbz = null;
    }

    @Override // com.google.android.gms.common.api.Result
    public final Status getStatus() {
        return this.zzgq;
    }

    @Override // com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionResult
    public final Display getPresentationDisplay() {
        return this.zzbz;
    }
}
