package com.google.android.gms.cast.framework;

import android.os.RemoteException;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public final class zzaf<T extends Session> extends zzy {
    private final SessionManagerListener<T> zziy;
    private final Class<T> zziz;

    public zzaf(SessionManagerListener<T> sessionManagerListener, Class<T> cls) {
        this.zziy = sessionManagerListener;
        this.zziz = cls;
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final int zzs() {
        return 12451009;
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final IObjectWrapper zzt() {
        return ObjectWrapper.wrap(this.zziy);
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zza(IObjectWrapper iObjectWrapper) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionStarting(this.zziz.cast(session));
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zza(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionStarted(this.zziz.cast(session), str);
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zza(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionStartFailed(this.zziz.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zzb(IObjectWrapper iObjectWrapper) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionEnding(this.zziz.cast(session));
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zzb(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionEnded(this.zziz.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zzb(IObjectWrapper iObjectWrapper, String str) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionResuming(this.zziz.cast(session), str);
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zza(IObjectWrapper iObjectWrapper, boolean z) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionResumed(this.zziz.cast(session), z);
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zzc(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionResumeFailed(this.zziz.cast(session), i);
    }

    @Override // com.google.android.gms.cast.framework.zzx
    public final void zzd(IObjectWrapper iObjectWrapper, int i) throws RemoteException {
        SessionManagerListener<T> sessionManagerListener;
        Session session = (Session) ObjectWrapper.unwrap(iObjectWrapper);
        if (!this.zziz.isInstance(session) || (sessionManagerListener = this.zziy) == null) {
            return;
        }
        sessionManagerListener.onSessionSuspended(this.zziz.cast(session), i);
    }
}
