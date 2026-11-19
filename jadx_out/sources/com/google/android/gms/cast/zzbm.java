package com.google.android.gms.cast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzea;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes4.dex */
final class zzbm extends RemoteMediaPlayer.zzb {
    private final /* synthetic */ RemoteMediaPlayer zzfl;
    private final /* synthetic */ JSONObject zzfs;
    private final /* synthetic */ int zzgc;
    private final /* synthetic */ int zzgd;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzbm(RemoteMediaPlayer remoteMediaPlayer, GoogleApiClient googleApiClient, int i, int i2, JSONObject jSONObject) {
        super(googleApiClient);
        this.zzfl = remoteMediaPlayer;
        this.zzgc = i;
        this.zzgd = i2;
        this.zzfs = jSONObject;
    }

    @Override // com.google.android.gms.cast.RemoteMediaPlayer.zzb
    protected final void zzb(zzdd zzddVar) throws zzea {
        int iZzf = this.zzfl.zzf(this.zzgc);
        if (iZzf == -1) {
            setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(0)));
            return;
        }
        int i = this.zzgd;
        if (i < 0) {
            setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(2001, String.format(Locale.ROOT, "Invalid request: Invalid newIndex %d.", Integer.valueOf(this.zzgd)))));
            return;
        }
        if (iZzf == i) {
            setResult((RemoteMediaPlayer.MediaChannelResult) createFailedResult(new Status(0)));
            return;
        }
        if (i > iZzf) {
            i++;
        }
        MediaQueueItem queueItem = this.zzfl.getMediaStatus().getQueueItem(i);
        this.zzfl.zzff.zza(this.zzgm, new int[]{this.zzgc}, queueItem != null ? queueItem.getItemId() : 0, this.zzfs);
    }
}
