package com.google.android.gms.internal.cast;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzce extends zzch {
    private final /* synthetic */ zzcb zzwi;
    private final /* synthetic */ int zzwk;
    private final /* synthetic */ String zzwl;
    private final /* synthetic */ JSONObject zzwm;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzce(zzcb zzcbVar, int i, String str, JSONObject jSONObject) {
        super(zzcbVar);
        this.zzwi = zzcbVar;
        this.zzwk = i;
        this.zzwl = str;
        this.zzwm = jSONObject;
    }

    @Override // com.google.android.gms.internal.cast.zzcj
    public final void execute() {
        int i = this.zzwk;
        int i2 = 5;
        if (i != 2) {
            if (i == 3) {
                i2 = 1;
            } else if (i == 4) {
                i2 = 2;
            } else if (i != 5) {
                i2 = i != 6 ? 0 : 4;
            } else {
                i2 = 3;
            }
        }
        if (i2 != 0) {
            this.zzwi.zza(this.zzwl, i2, this.zzwm, this.zzwq);
        } else {
            this.zzwq.zza(-1L, 2001, null);
            zzcb.zzbf.w("sendPlayerRequest for unsupported playerState: %d", Integer.valueOf(this.zzwk));
        }
    }
}
