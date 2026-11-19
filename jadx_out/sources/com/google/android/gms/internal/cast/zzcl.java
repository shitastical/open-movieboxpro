package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import com.google.android.gms.cast.games.GameManagerClient;
import com.google.android.gms.common.api.Status;
import com.google.firebase.sessions.BuildConfig;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
final class zzcl implements zzec {
    private final /* synthetic */ zzcb zzwo;
    private final /* synthetic */ zzck zzws;

    zzcl(zzck zzckVar, zzcb zzcbVar) {
        this.zzws = zzckVar;
        this.zzwo = zzcbVar;
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zzd(long j) {
        this.zzws.setResult(zzck.zzc(new Status(2103)));
    }

    @Override // com.google.android.gms.internal.cast.zzec
    public final void zza(long j, int i, Object obj) {
        try {
            if (obj == null) {
                this.zzws.setResult(new zzcm(new Status(i, (String) null, (PendingIntent) null), this.zzws.zzwr));
                return;
            }
            zzcp zzcpVar = (zzcp) obj;
            zzco zzcoVar = zzcpVar.zzxk;
            if (zzcoVar != null && !zzdk.zza(BuildConfig.VERSION_NAME, zzcoVar.getVersion())) {
                zzcb.zza(this.zzws.zzwi, (zzco) null);
                this.zzws.setResult(zzck.zzc(new Status(GameManagerClient.STATUS_INCORRECT_VERSION, String.format(Locale.ROOT, "Incorrect Game Manager SDK version. Receiver: %s Sender: %s", zzcoVar.getVersion(), BuildConfig.VERSION_NAME))));
            } else {
                this.zzws.setResult(new zzcm(new Status(i, zzcpVar.zzxa, (PendingIntent) null), this.zzws.zzwr));
            }
        } catch (ClassCastException unused) {
            this.zzws.setResult(zzck.zzc(new Status(13)));
        }
    }
}
