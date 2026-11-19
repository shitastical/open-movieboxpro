package com.google.android.gms.cast.framework;

import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.zzbx;
import com.google.android.gms.internal.cast.zzcx;
import com.google.android.gms.internal.cast.zzdw;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PrecacheManager {
    private final zzdw zzbf = new zzdw("PrecacheManager");
    private final SessionManager zzhg;
    private final CastOptions zzhk;
    private final zzcx zzir;

    public PrecacheManager(CastOptions castOptions, SessionManager sessionManager, zzcx zzcxVar) {
        this.zzhk = castOptions;
        this.zzhg = sessionManager;
        this.zzir = zzcxVar;
    }

    public void precache(String str) {
        Session currentSession = this.zzhg.getCurrentSession();
        if (str == null) {
            throw new IllegalArgumentException("No precache data found to be precached");
        }
        if (currentSession == null) {
            this.zzir.zza(new String[]{this.zzhk.getReceiverApplicationId()}, str, null);
            return;
        }
        if (currentSession instanceof CastSession) {
            RemoteMediaClient remoteMediaClient = ((CastSession) currentSession).getRemoteMediaClient();
            if (remoteMediaClient != null) {
                remoteMediaClient.zza(str, (List<zzbx>) null);
                return;
            } else {
                this.zzbf.e("Failed to get RemoteMediaClient from current cast session.", new Object[0]);
                return;
            }
        }
        this.zzbf.e("Current session is not a CastSession. Precache is not supported.", new Object[0]);
    }
}
