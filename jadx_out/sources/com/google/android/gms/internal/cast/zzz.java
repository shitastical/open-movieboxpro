package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;

/* JADX INFO: loaded from: classes4.dex */
final class zzz implements SessionManagerListener<CastSession> {
    private final /* synthetic */ zzx zzjv;

    private zzz(zzx zzxVar) {
        this.zzjv = zzxVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session session, int i) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session session, int i) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionSuspended(Session session, int i) {
        zzx zzxVar = this.zzjv;
        zzxVar.zza(zzxVar.zzg((CastSession) session));
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionResumed(Session session, boolean z) {
        zzx zzxVar = this.zzjv;
        zzxVar.zza(zzxVar.zzg((CastSession) session), false);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionEnded(Session session, int i) {
        zzx zzxVar = this.zzjv;
        zzxVar.zzb(zzxVar.zzg((CastSession) session));
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionEnding(Session session) {
        zzx zzxVar = this.zzjv;
        zzxVar.zzb(zzxVar.zzg((CastSession) session));
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionStarted(Session session, String str) {
        zzx zzxVar = this.zzjv;
        zzxVar.zza(zzxVar.zzg((CastSession) session), false);
    }
}
