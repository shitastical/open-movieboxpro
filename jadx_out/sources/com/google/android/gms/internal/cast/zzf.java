package com.google.android.gms.internal.cast;

import android.content.Context;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastMediaControlIntent;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionProvider;

/* JADX INFO: loaded from: classes4.dex */
public final class zzf extends SessionProvider {
    private final CastOptions zzhk;
    private final zzw zzjd;

    public zzf(Context context, CastOptions castOptions, zzw zzwVar) {
        super(context, castOptions.getSupportedNamespaces().isEmpty() ? CastMediaControlIntent.categoryForCast(castOptions.getReceiverApplicationId()) : CastMediaControlIntent.categoryForCast(castOptions.getReceiverApplicationId(), castOptions.getSupportedNamespaces()));
        this.zzhk = castOptions;
        this.zzjd = zzwVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionProvider
    public final Session createSession(String str) {
        return new CastSession(getContext(), getCategory(), str, this.zzhk, Cast.CastApi, new zzg(), new zzal(getContext(), this.zzhk, this.zzjd));
    }

    @Override // com.google.android.gms.cast.framework.SessionProvider
    public final boolean isSessionRecoverable() {
        return this.zzhk.getResumeSavedSession();
    }
}
