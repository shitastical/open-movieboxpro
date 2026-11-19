package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public abstract class zzx<T> {
    private T zzjr;
    private boolean zzjs;
    private final Set<com.google.android.gms.cast.framework.zzad> zzjt;
    private SessionManagerListener<CastSession> zzju;

    public zzx() {
        this(true);
    }

    protected void zzay() {
    }

    protected void zzaz() {
    }

    protected abstract T zzg(CastSession castSession);

    private zzx(boolean z) {
        this.zzjt = new HashSet();
        this.zzju = new zzz(this);
        SessionManager sessionManager = CastContext.getSharedInstance().getSessionManager();
        sessionManager.addSessionManagerListener(this.zzju, CastSession.class);
        CastSession currentCastSession = sessionManager.getCurrentCastSession();
        if (currentCastSession != null) {
            if (currentCastSession.isConnected() || currentCastSession.isSuspended()) {
                zza(zzg(currentCastSession), currentCastSession.isSuspended());
            }
        }
    }

    public final int zzax() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (this.zzjr == null) {
            return 3;
        }
        return this.zzjs ? 2 : 1;
    }

    public final void zza(com.google.android.gms.cast.framework.zzad zzadVar) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzjt.add(zzadVar);
    }

    public final T zzba() {
        return this.zzjr;
    }

    final void zza(T t, boolean z) {
        int iZzax = zzax();
        T t2 = this.zzjr;
        if (t2 == t) {
            this.zzjs = z;
            zzj(iZzax);
            return;
        }
        zzc(t2);
        this.zzjr = t;
        this.zzjs = z;
        zzay();
        zzj(iZzax);
    }

    final void zza(T t) {
        if (this.zzjr != t) {
            return;
        }
        int iZzax = zzax();
        this.zzjs = true;
        zzj(iZzax);
    }

    final void zzb(T t) {
        int iZzax = zzax();
        zzc(t);
        zzj(iZzax);
    }

    private final void zzc(T t) {
        T t2 = this.zzjr;
        if (t2 == null || t2 != t) {
            return;
        }
        zzaz();
        this.zzjr = null;
    }

    private final void zzj(int i) {
        if (zzax() == i) {
            return;
        }
        Iterator<com.google.android.gms.cast.framework.zzad> it = this.zzjt.iterator();
        while (it.hasNext()) {
            it.next().zzap();
        }
    }
}
