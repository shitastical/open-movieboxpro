package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.framework.CastSession;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class zzbc extends com.google.android.gms.internal.cast.zzx<RemoteMediaClient> {
    private final /* synthetic */ zzba zzqf;

    private zzbc(zzba zzbaVar) {
        this.zzqf = zzbaVar;
    }

    @Override // com.google.android.gms.internal.cast.zzx
    public final void zzay() {
        Iterator<RemoteMediaClient.Callback> it = this.zzqf.zzpd.iterator();
        while (it.hasNext()) {
            zzba().registerCallback(it.next());
        }
        for (Map.Entry<RemoteMediaClient.ProgressListener, Long> entry : this.zzqf.zzqd.entrySet()) {
            zzba().addProgressListener(entry.getKey(), entry.getValue().longValue());
        }
        zzba().setParseAdsInfoCallback(null);
    }

    @Override // com.google.android.gms.internal.cast.zzx
    public final void zzaz() {
        Iterator<RemoteMediaClient.Callback> it = this.zzqf.zzpd.iterator();
        while (it.hasNext()) {
            zzba().unregisterCallback(it.next());
        }
        Iterator<Map.Entry<RemoteMediaClient.ProgressListener, Long>> it2 = this.zzqf.zzqd.entrySet().iterator();
        while (it2.hasNext()) {
            zzba().removeProgressListener(it2.next().getKey());
        }
        zzba().setParseAdsInfoCallback(null);
    }

    @Override // com.google.android.gms.internal.cast.zzx
    protected final /* synthetic */ RemoteMediaClient zzg(CastSession castSession) {
        return castSession.getRemoteMediaClient();
    }
}
