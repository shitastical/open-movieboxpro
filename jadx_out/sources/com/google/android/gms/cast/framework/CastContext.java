package com.google.android.gms.cast.framework;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.android.gms.internal.cast.zzcx;
import com.google.android.gms.internal.cast.zzdw;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public class CastContext {
    public static final String OPTIONS_PROVIDER_CLASS_NAME_KEY = "com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME";
    private static final zzdw zzbf = new zzdw("CastContext");
    private static CastContext zzhd;
    private final Context zzhe;
    private final zzj zzhf;
    private final SessionManager zzhg;
    private final zze zzhh;
    private final PrecacheManager zzhi;
    private final MediaNotificationManager zzhj;
    private final CastOptions zzhk;
    private com.google.android.gms.internal.cast.zzw zzhl;
    private com.google.android.gms.internal.cast.zzf zzhm;
    private final List<SessionProvider> zzhn;

    public static CastContext getSharedInstance(Context context) throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (zzhd == null) {
            OptionsProvider optionsProviderZzc = zzc(context.getApplicationContext());
            zzhd = new CastContext(context, optionsProviderZzc.getCastOptions(context.getApplicationContext()), optionsProviderZzc.getAdditionalSessionProviders(context.getApplicationContext()));
        }
        return zzhd;
    }

    public static CastContext getSharedInstance() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return zzhd;
    }

    public static CastContext zzb(Context context) throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return getSharedInstance(context);
        } catch (RuntimeException e) {
            zzbf.e("Failed to load module from Google Play services. Cast will not work properly. Might due to outdated Google Play services. Ignoring this failure silently.", e);
            return null;
        }
    }

    private static OptionsProvider zzc(Context context) throws IllegalStateException {
        try {
            Bundle bundle = Wrappers.packageManager(context).getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                zzbf.e("Bundle is null", new Object[0]);
            }
            String string = bundle.getString(OPTIONS_PROVIDER_CLASS_NAME_KEY);
            if (string == null) {
                throw new IllegalStateException("The fully qualified name of the implementation of OptionsProvider must be provided as a metadata in the AndroidManifest.xml with key com.google.android.gms.cast.framework.OPTIONS_PROVIDER_CLASS_NAME.");
            }
            return (OptionsProvider) Class.forName(string).getDeclaredConstructor(null).newInstance(null);
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | NullPointerException | InvocationTargetException e) {
            throw new IllegalStateException("Failed to initialize CastContext.", e);
        }
    }

    private CastContext(Context context, CastOptions castOptions, List<SessionProvider> list) {
        zzp zzpVarZzad;
        zzv zzvVarZzac;
        Context applicationContext = context.getApplicationContext();
        this.zzhe = applicationContext;
        this.zzhk = castOptions;
        this.zzhl = new com.google.android.gms.internal.cast.zzw(MediaRouter.getInstance(applicationContext));
        this.zzhn = list;
        zzv();
        zzj zzjVarZza = com.google.android.gms.internal.cast.zze.zza(applicationContext, castOptions, this.zzhl, zzu());
        this.zzhf = zzjVarZza;
        try {
            zzpVarZzad = zzjVarZza.zzad();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getDiscoveryManagerImpl", "zzj");
            zzpVarZzad = null;
        }
        this.zzhh = zzpVarZzad == null ? null : new zze(zzpVarZzad);
        try {
            zzvVarZzac = this.zzhf.zzac();
        } catch (RemoteException e2) {
            zzbf.zza(e2, "Unable to call %s on %s.", "getSessionManagerImpl", "zzj");
            zzvVarZzac = null;
        }
        SessionManager sessionManager = zzvVarZzac == null ? null : new SessionManager(zzvVarZzac, this.zzhe);
        this.zzhg = sessionManager;
        this.zzhj = new MediaNotificationManager(sessionManager);
        this.zzhi = sessionManager != null ? new PrecacheManager(this.zzhk, sessionManager, new zzcx(this.zzhe)) : null;
    }

    private final Map<String, IBinder> zzu() {
        HashMap map = new HashMap();
        com.google.android.gms.internal.cast.zzf zzfVar = this.zzhm;
        if (zzfVar != null) {
            map.put(zzfVar.getCategory(), this.zzhm.zzaq());
        }
        List<SessionProvider> list = this.zzhn;
        if (list != null) {
            for (SessionProvider sessionProvider : list) {
                Preconditions.checkNotNull(sessionProvider, "Additional SessionProvider must not be null.");
                String strCheckNotEmpty = Preconditions.checkNotEmpty(sessionProvider.getCategory(), "Category for SessionProvider must not be null or empty string.");
                Preconditions.checkArgument(!map.containsKey(strCheckNotEmpty), String.format("SessionProvider for category %s already added", strCheckNotEmpty));
                map.put(strCheckNotEmpty, sessionProvider.zzaq());
            }
        }
        return map;
    }

    private final void zzv() {
        if (!TextUtils.isEmpty(this.zzhk.getReceiverApplicationId())) {
            this.zzhm = new com.google.android.gms.internal.cast.zzf(this.zzhe, this.zzhk, this.zzhl);
        } else {
            this.zzhm = null;
        }
    }

    public CastOptions getCastOptions() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhk;
    }

    public SessionManager getSessionManager() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhg;
    }

    public MediaRouteSelector getMergedSelector() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return MediaRouteSelector.fromBundle(this.zzhf.zzab());
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "getMergedSelectorAsBundle", "zzj");
            return null;
        }
    }

    public int getCastState() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhg.getCastState();
    }

    public PrecacheManager getPrecacheManager() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhi;
    }

    public boolean isAppVisible() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzhf.isAppVisible();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "isApplicationVisible", "zzj");
            return false;
        }
    }

    public final boolean zzw() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        try {
            return this.zzhf.zzw();
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "hasActivityInRecents", "zzj");
            return false;
        }
    }

    public MediaNotificationManager getMediaNotificationManager() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhj;
    }

    @Deprecated
    public void addAppVisibilityListener(AppVisibilityListener appVisibilityListener) throws IllegalStateException, NullPointerException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(appVisibilityListener);
        try {
            this.zzhf.zza(new zza(appVisibilityListener));
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "addVisibilityChangeListener", "zzj");
        }
    }

    @Deprecated
    public void removeAppVisibilityListener(AppVisibilityListener appVisibilityListener) throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (appVisibilityListener == null) {
            return;
        }
        try {
            this.zzhf.zzb(new zza(appVisibilityListener));
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "addVisibilityChangeListener", "zzj");
        }
    }

    public void addCastStateListener(CastStateListener castStateListener) throws IllegalStateException, NullPointerException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(castStateListener);
        this.zzhg.addCastStateListener(castStateListener);
    }

    public void removeCastStateListener(CastStateListener castStateListener) throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (castStateListener == null) {
            return;
        }
        this.zzhg.removeCastStateListener(castStateListener);
    }

    public void setReceiverApplicationId(String str) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (TextUtils.equals(str, this.zzhk.getReceiverApplicationId())) {
            return;
        }
        this.zzhk.setReceiverApplicationId(str);
        zzv();
        try {
            this.zzhf.zza(str, zzu());
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "setReceiverApplicationId", "zzj");
        }
        CastButtonFactory.zza(this.zzhe);
    }

    public final zze zzx() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzhh;
    }

    public boolean onDispatchVolumeKeyEventBeforeJellyBean(KeyEvent keyEvent) {
        CastSession currentCastSession;
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (PlatformVersion.isAtLeastJellyBean() || (currentCastSession = this.zzhg.getCurrentCastSession()) == null || !currentCastSession.isConnected()) {
            return false;
        }
        double volumeDeltaBeforeIceCreamSandwich = getCastOptions().getVolumeDeltaBeforeIceCreamSandwich();
        boolean z = keyEvent.getAction() == 0;
        int keyCode = keyEvent.getKeyCode();
        if (keyCode == 24) {
            zza(currentCastSession, volumeDeltaBeforeIceCreamSandwich, z);
            return true;
        }
        if (keyCode != 25) {
            return false;
        }
        zza(currentCastSession, -volumeDeltaBeforeIceCreamSandwich, z);
        return true;
    }

    private static boolean zza(CastSession castSession, double d, boolean z) {
        if (z) {
            try {
                double volume = castSession.getVolume() + d;
                if (volume > 1.0d) {
                    volume = 1.0d;
                }
                castSession.setVolume(volume);
            } catch (IOException | IllegalStateException e) {
                zzbf.e("Unable to call CastSession.setVolume(double).", e);
            }
        }
        return true;
    }
}
