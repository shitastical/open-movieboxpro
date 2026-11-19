package com.google.android.gms.internal.cast;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcf extends zzch {
    private final /* synthetic */ zzcb zzwi;
    private final /* synthetic */ String zzwl;
    private final /* synthetic */ JSONObject zzwm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcf(zzcb zzcbVar, String str, JSONObject jSONObject) {
        super(zzcbVar);
        this.zzwi = zzcbVar;
        this.zzwl = str;
        this.zzwm = jSONObject;
    }

    @Override // com.google.android.gms.internal.cast.zzcj
    public final void execute() {
        this.zzwi.zza(this.zzwl, 6, this.zzwm, this.zzwq);
    }
}
