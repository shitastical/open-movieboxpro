package com.google.android.gms.cast.framework.media;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzaj extends RemoteMediaClient.zzc {
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzgc;
    private final /* synthetic */ int zzgd;
    private final /* synthetic */ RemoteMediaClient zzph;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzaj(RemoteMediaClient remoteMediaClient, GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        super(remoteMediaClient, googleApiClient);
        this.zzph = remoteMediaClient;
        this.zzgc = i;
        this.zzgd = i2;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.zzc
    protected final void zzb(zzdd zzddVar) throws zzea {
        int iZzf = this.zzph.zzf(this.zzgc);
        int i = this.zzgd;
        if (i < 0) {
            setResult((RemoteMediaClient.MediaChannelResult) createFailedResult(new Status(2001, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", Integer.valueOf(this.zzgd)))));
        } else {
            if (iZzf == i) {
                setResult((RemoteMediaClient.MediaChannelResult) createFailedResult(new Status(0)));
                return;
            }
            if (i > iZzf) {
                i++;
            }
            this.zzph.zzff.zza(this.zzgm, new int[]{this.zzgc}, this.zzph.zzm(i), this.zzfs);
        }
    }
}
