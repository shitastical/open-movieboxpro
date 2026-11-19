package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.games.GameManagerClient;
import java.io.IOException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzcc extends zzck {
    final /* synthetic */ zzcb zzwi;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzcc(zzcb zzcbVar, GameManagerClient gameManagerClient) {
        super(zzcbVar, gameManagerClient);
        this.zzwi = zzcbVar;
    }

    @Override // com.google.android.gms.internal.cast.zzcj
    public final void execute() {
        try {
            this.zzwi.zzhz.setMessageReceivedCallbacks(this.zzwi.zzpb, this.zzwi.getNamespace(), new zzcd(this));
            this.zzwi.zzem();
            this.zzwi.zzel();
            this.zzwi.zza((String) null, 1100, (JSONObject) null, this.zzwq);
        } catch (IOException | IllegalStateException unused) {
            this.zzwq.zza(-1L, 8, null);
        }
    }
}
