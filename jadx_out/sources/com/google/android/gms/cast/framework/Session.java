package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.cast.zzdw;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Session {
    private static final zzdw zzbf = new zzdw("Session");
    private final zzt zzit;
    private final zza zziu;

    protected Session(Context context, String str, String str2) {
        zza zzaVar = new zza();
        this.zziu = zzaVar;
        this.zzit = com.google.android.gms.internal.cast.zze.zza(context, str, str2, zzaVar);
    }

    protected abstract void end(boolean z);

    protected void onResuming(Bundle bundle) {
    }

    protected void onStarting(Bundle bundle) {
    }

    protected abstract void resume(Bundle bundle);

    protected abstract void start(Bundle bundle);

    private class zza extends zzac {
        private zza() {
        }

        @Override // com.google.android.gms.cast.framework.zzab
        public final int zzs() {
            return 12451009;
        }

        @Override // com.google.android.gms.cast.framework.zzab
        public final IObjectWrapper zzah() {
            return ObjectWrapper.wrap(Session.this);
        }

        @Override // com.google.android.gms.cast.framework.zzab
        public final void onStarting(Bundle bundle) {
            Session.this.onStarting(bundle);
        }

        @Override // com.google.android.gms.cast.framework.zzab
        public final void start(Bundle bundle) {
            Session.this.start(bundle);
        }

        @Override // com.google.android.gms.cast.framework.zzab
        public final void onResuming(Bundle bundle) {
            Session.this.onResuming(bundle);
        }

        @Override // com.google.android.gms.cast.framework.zzab
        public final void resume(Bundle bundle) {
            Session.this.resume(bundle);
        }

        @Override // com.google.android.gms.cast.framework.zzab
        public final void end(boolean z) {
            Session.this.end(z);
        }

        @Override // com.google.android.gms.cast.framework.zzab
        public final long getSessionRemainingTimeMs() {
            return Session.this.getSessionRemainingTimeMs();
        }
    }

    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return 0L;
    }

    public final String getCategory() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzit.getCategory();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getCategory", "zzt");
            return null;
        }
    }

    public final String getSessionId() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzit.getSessionId();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getSessionId", "zzt");
            return null;
        }
    }

    public boolean isConnected() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzit.isConnected();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "isConnected", "zzt");
            return false;
        }
    }

    public boolean isConnecting() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzit.isConnecting();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "isConnecting", "zzt");
            return false;
        }
    }

    public boolean isDisconnecting() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzit.isDisconnecting();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "isDisconnecting", "zzt");
            return false;
        }
    }

    public boolean isDisconnected() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzit.isDisconnected();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "isDisconnected", "zzt");
            return true;
        }
    }

    public boolean isResuming() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzit.isResuming();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "isResuming", "zzt");
            return false;
        }
    }

    public boolean isSuspended() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzit.isSuspended();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "isSuspended", "zzt");
            return false;
        }
    }

    protected final void notifySessionStarted(String str) {
        try {
            this.zzit.notifySessionStarted(str);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "notifySessionStarted", "zzt");
        }
    }

    protected final void notifyFailedToStartSession(int i) {
        try {
            this.zzit.notifyFailedToStartSession(i);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "notifyFailedToStartSession", "zzt");
        }
    }

    protected final void notifySessionEnded(int i) {
        try {
            this.zzit.notifySessionEnded(i);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "notifySessionEnded", "zzt");
        }
    }

    protected final void notifySessionResumed(boolean z) {
        try {
            this.zzit.notifySessionResumed(z);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "notifySessionResumed", "zzt");
        }
    }

    protected final void notifyFailedToResumeSession(int i) {
        try {
            this.zzit.notifyFailedToResumeSession(i);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "notifyFailedToResumeSession", "zzt");
        }
    }

    protected final void notifySessionSuspended(int i) {
        try {
            this.zzit.notifySessionSuspended(i);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "notifySessionSuspended", "zzt");
        }
    }

    public final IObjectWrapper zzaf() {
        try {
            return this.zzit.zzaf();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getWrappedObject", "zzt");
            return null;
        }
    }
}
