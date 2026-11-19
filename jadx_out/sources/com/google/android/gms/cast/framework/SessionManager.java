package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.widget.Toast;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.cast.zzdw;

/* JADX INFO: loaded from: classes4.dex */
public class SessionManager {
    private static final zzdw zzbf = new zzdw("SessionManager");
    private final zzv zziw;
    private final Context zzix;

    public SessionManager(zzv zzvVar, Context context) {
        this.zziw = zzvVar;
        this.zzix = context;
    }

    public Session getCurrentSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return (Session) ObjectWrapper.unwrap(this.zziw.zzag());
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getWrappedCurrentSession", "zzv");
            return null;
        }
    }

    public CastSession getCurrentCastSession() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Session currentSession = getCurrentSession();
        if (currentSession == null || !(currentSession instanceof CastSession)) {
            return null;
        }
        return (CastSession) currentSession;
    }

    public void endCurrentSession(boolean z) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            this.zziw.zza(true, z);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "endCurrentSession", "zzv");
        }
    }

    public void addSessionManagerListener(SessionManagerListener<Session> sessionManagerListener) throws NullPointerException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        addSessionManagerListener(sessionManagerListener, Session.class);
    }

    public <T extends Session> void addSessionManagerListener(SessionManagerListener<T> sessionManagerListener, Class<T> cls) throws NullPointerException {
        Preconditions.checkNotNull(sessionManagerListener);
        Preconditions.checkNotNull(cls);
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            this.zziw.zza(new zzaf(sessionManagerListener, cls));
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "addSessionManagerListener", "zzv");
        }
    }

    public void removeSessionManagerListener(SessionManagerListener<Session> sessionManagerListener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        removeSessionManagerListener(sessionManagerListener, Session.class);
    }

    public <T extends Session> void removeSessionManagerListener(SessionManagerListener<T> sessionManagerListener, Class cls) {
        Preconditions.checkNotNull(cls);
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (sessionManagerListener == null) {
            return;
        }
        try {
            this.zziw.zzb(new zzaf(sessionManagerListener, cls));
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "removeSessionManagerListener", "zzv");
        }
    }

    public void startSession(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null && extras.getString("CAST_INTENT_TO_CAST_ROUTE_ID_KEY") != null) {
                String string = extras.getString("CAST_INTENT_TO_CAST_DEVICE_NAME_KEY");
                if (!extras.getBoolean("CAST_INTENT_TO_CAST_NO_TOAST_KEY")) {
                    Toast.makeText(this.zzix, this.zzix.getString(R.string.cast_connecting_to_device, string), 0).show();
                }
                this.zziw.zzc(new Bundle(extras));
                intent.removeExtra("CAST_INTENT_TO_CAST_ROUTE_ID_KEY");
            }
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "startSession", "zzv");
        }
    }

    final int getCastState() {
        try {
            return this.zziw.getCastState();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "addCastStateListener", "zzv");
            return 1;
        }
    }

    final void addCastStateListener(CastStateListener castStateListener) throws NullPointerException {
        Preconditions.checkNotNull(castStateListener);
        try {
            this.zziw.zza(new zzd(castStateListener));
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "addCastStateListener", "zzv");
        }
    }

    final void removeCastStateListener(CastStateListener castStateListener) {
        if (castStateListener == null) {
            return;
        }
        try {
            this.zziw.zzb(new zzd(castStateListener));
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "removeCastStateListener", "zzv");
        }
    }

    public final IObjectWrapper zzaa() {
        try {
            return this.zziw.zzae();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getWrappedThis", "zzv");
            return null;
        }
    }
}
