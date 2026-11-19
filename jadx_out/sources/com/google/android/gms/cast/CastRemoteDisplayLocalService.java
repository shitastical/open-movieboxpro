package com.google.android.gms.cast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.text.TextUtils;
import android.view.Display;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import androidx.core.app.NotificationCompat;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzdw;
import com.google.android.gms.internal.cast.zzez;
import java.lang.ref.WeakReference;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CastRemoteDisplayLocalService extends Service {
    private static final zzdw zzbf = new zzdw("CastRemoteDisplayLocalService");
    private static final int zzbp = R.id.cast_notification_id;
    private static final Object zzbq = new Object();
    private static AtomicBoolean zzbr = new AtomicBoolean(false);
    private static CastRemoteDisplayLocalService zzcg;
    private Handler handler;
    private WeakReference<Callbacks> zzbs;
    private zzb zzbt;
    private NotificationSettings zzbu;
    private Notification zzbv;
    private boolean zzbw;
    private PendingIntent zzbx;
    private CastDevice zzby;
    private Display zzbz;
    private Context zzca;
    private ServiceConnection zzcb;
    private MediaRouter zzcc;
    private CastRemoteDisplayClient zzce;
    private String zzy;
    private boolean zzcd = false;
    private final MediaRouter.Callback zzcf = new zzu(this);
    private final IBinder zzch = new zza();

    public interface Callbacks {
        void onRemoteDisplaySessionEnded(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onRemoteDisplaySessionError(Status status);

        void onRemoteDisplaySessionStarted(CastRemoteDisplayLocalService castRemoteDisplayLocalService);

        void onServiceCreated(CastRemoteDisplayLocalService castRemoteDisplayLocalService);
    }

    public static final class NotificationSettings {
        private Notification zzbv;
        private PendingIntent zzcp;
        private String zzcq;
        private String zzcr;

        private NotificationSettings() {
        }

        private NotificationSettings(NotificationSettings notificationSettings) {
            this.zzbv = notificationSettings.zzbv;
            this.zzcp = notificationSettings.zzcp;
            this.zzcq = notificationSettings.zzcq;
            this.zzcr = notificationSettings.zzcr;
        }

        public static final class Builder {
            private NotificationSettings zzcs = new NotificationSettings((zzu) null);

            public final Builder setNotification(Notification notification) {
                this.zzcs.zzbv = notification;
                return this;
            }

            public final Builder setNotificationPendingIntent(PendingIntent pendingIntent) {
                this.zzcs.zzcp = pendingIntent;
                return this;
            }

            public final Builder setNotificationTitle(String str) {
                this.zzcs.zzcq = str;
                return this;
            }

            public final Builder setNotificationText(String str) {
                this.zzcs.zzcr = str;
                return this;
            }

            public final NotificationSettings build() {
                if (this.zzcs.zzbv != null) {
                    if (!TextUtils.isEmpty(this.zzcs.zzcq)) {
                        throw new IllegalArgumentException("notificationTitle requires using the default notification");
                    }
                    if (TextUtils.isEmpty(this.zzcs.zzcr)) {
                        if (this.zzcs.zzcp != null) {
                            throw new IllegalArgumentException("notificationPendingIntent requires using the default notification");
                        }
                    } else {
                        throw new IllegalArgumentException("notificationText requires using the default notification");
                    }
                } else if (TextUtils.isEmpty(this.zzcs.zzcq) && TextUtils.isEmpty(this.zzcs.zzcr) && this.zzcs.zzcp == null) {
                    throw new IllegalArgumentException("At least an argument must be provided");
                }
                return this.zzcs;
            }
        }

        /* synthetic */ NotificationSettings(zzu zzuVar) {
            this();
        }

        /* synthetic */ NotificationSettings(NotificationSettings notificationSettings, zzu zzuVar) {
            this(notificationSettings);
        }
    }

    class zza extends Binder {
        zza() {
        }
    }

    private static final class zzb extends BroadcastReceiver {
        private zzb() {
        }

        @Override // android.content.BroadcastReceiver
        public final void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT")) {
                CastRemoteDisplayLocalService.stopService();
            } else if (intent.getAction().equals("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED")) {
                CastRemoteDisplayLocalService.zzb(false);
            }
        }

        /* synthetic */ zzb(zzu zzuVar) {
            this();
        }
    }

    public abstract void onCreatePresentation(Display display);

    public abstract void onDismissPresentation();

    public static class Options {
        private int zzbd = 2;

        public void setConfigPreset(int i) {
            this.zzbd = i;
        }

        public int getConfigPreset() {
            return this.zzbd;
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        zzb("onBind");
        return this.zzch;
    }

    @Override // android.app.Service
    public void onCreate() {
        zzb("onCreate");
        super.onCreate();
        zzez zzezVar = new zzez(getMainLooper());
        this.handler = zzezVar;
        zzezVar.postDelayed(new zzv(this), 100L);
        if (this.zzce == null) {
            this.zzce = CastRemoteDisplay.getClient(this);
        }
        if (PlatformVersion.isAtLeastO()) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            ComponentDialog$$ExternalSyntheticApiModelOutline0.m22m();
            NotificationChannel notificationChannelM = ComponentDialog$$ExternalSyntheticApiModelOutline0.m("cast_remote_display_local_service", getString(R.string.cast_notification_default_channel_name), 2);
            notificationChannelM.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannelM);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        zzb("onStartCommand");
        this.zzcd = true;
        return 2;
    }

    protected static void setDebugEnabled() {
        zzbf.zzl(true);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.content.ContextWrapper, android.content.Context
    public Display getDisplay() {
        return this.zzbz;
    }

    public static CastRemoteDisplayLocalService getInstance() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService;
        synchronized (zzbq) {
            castRemoteDisplayLocalService = zzcg;
        }
        return castRemoteDisplayLocalService;
    }

    public static void startService(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, NotificationSettings notificationSettings, Callbacks callbacks) {
        startServiceWithOptions(context, cls, str, castDevice, new Options(), notificationSettings, callbacks);
    }

    public static void startServiceWithOptions(Context context, Class<? extends CastRemoteDisplayLocalService> cls, String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Callbacks callbacks) {
        zzdw zzdwVar = zzbf;
        zzdwVar.d("Starting Service", new Object[0]);
        synchronized (zzbq) {
            if (zzcg != null) {
                zzdwVar.w("An existing service had not been stopped before starting one", new Object[0]);
                zzb(true);
            }
        }
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, cls), 128);
            if (serviceInfo != null && serviceInfo.exported) {
                throw new IllegalStateException("The service must not be exported, verify the manifest configuration");
            }
            Preconditions.checkNotNull(context, "activityContext is required.");
            Preconditions.checkNotNull(cls, "serviceClass is required.");
            Preconditions.checkNotNull(str, "applicationId is required.");
            Preconditions.checkNotNull(castDevice, "device is required.");
            Preconditions.checkNotNull(options, "options is required.");
            Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
            Preconditions.checkNotNull(callbacks, "callbacks is required.");
            if (notificationSettings.zzbv == null && notificationSettings.zzcp == null) {
                throw new IllegalArgumentException("notificationSettings: Either the notification or the notificationPendingIntent must be provided");
            }
            if (zzbr.getAndSet(true)) {
                zzdwVar.e("Service is already being started, startService has been called twice", new Object[0]);
                return;
            }
            Intent intent = new Intent(context, cls);
            context.startService(intent);
            context.bindService(intent, new zzw(str, castDevice, options, notificationSettings, context, callbacks), 64);
        } catch (PackageManager.NameNotFoundException unused) {
            throw new IllegalStateException("Service not found, did you forget to configure it in the manifest?");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(boolean z) {
        ServiceConnection serviceConnection;
        zzb("Stopping Service");
        Preconditions.checkMainThread("stopServiceInstanceInternal must be called on the main thread");
        if (!z && this.zzcc != null) {
            zzb("Setting default route");
            MediaRouter mediaRouter = this.zzcc;
            mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
        }
        if (this.zzbt != null) {
            zzb("Unregistering notification receiver");
            unregisterReceiver(this.zzbt);
        }
        zzb("stopRemoteDisplaySession");
        zzb("stopRemoteDisplay");
        this.zzce.stopRemoteDisplay().addOnCompleteListener(new zzaa(this));
        if (this.zzbs.get() != null) {
            this.zzbs.get().onRemoteDisplaySessionEnded(this);
        }
        onDismissPresentation();
        zzb("Stopping the remote display Service");
        stopForeground(true);
        stopSelf();
        if (this.zzcc != null) {
            Preconditions.checkMainThread("CastRemoteDisplayLocalService calls must be done on the main thread");
            zzb("removeMediaRouterCallback");
            this.zzcc.removeCallback(this.zzcf);
        }
        Context context = this.zzca;
        if (context != null && (serviceConnection = this.zzcb) != null) {
            try {
                context.unbindService(serviceConnection);
            } catch (IllegalArgumentException unused) {
                zzb("No need to unbind service, already unbound");
            }
            this.zzcb = null;
            this.zzca = null;
        }
        this.zzy = null;
        this.zzbv = null;
        this.zzbz = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(boolean z) {
        zzdw zzdwVar = zzbf;
        zzdwVar.d("Stopping Service", new Object[0]);
        zzbr.set(false);
        synchronized (zzbq) {
            CastRemoteDisplayLocalService castRemoteDisplayLocalService = zzcg;
            if (castRemoteDisplayLocalService == null) {
                zzdwVar.e("Service is already being stopped", new Object[0]);
                return;
            }
            zzcg = null;
            if (castRemoteDisplayLocalService.handler != null) {
                if (Looper.myLooper() != Looper.getMainLooper()) {
                    castRemoteDisplayLocalService.handler.post(new zzx(castRemoteDisplayLocalService, z));
                } else {
                    castRemoteDisplayLocalService.zza(z);
                }
            }
        }
    }

    public static void stopService() {
        zzb(false);
    }

    public void updateNotificationSettings(NotificationSettings notificationSettings) {
        Preconditions.checkNotNull(notificationSettings, "notificationSettings is required.");
        Preconditions.checkNotNull(this.handler, "Service is not ready yet.");
        this.handler.post(new zzy(this, notificationSettings));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(NotificationSettings notificationSettings) {
        Preconditions.checkMainThread("updateNotificationSettingsInternal must be called on the main thread");
        if (this.zzbu == null) {
            throw new IllegalStateException("No current notification settings to update");
        }
        if (this.zzbw) {
            if (notificationSettings.zzbv == null) {
                if (notificationSettings.zzcp != null) {
                    this.zzbu.zzcp = notificationSettings.zzcp;
                }
                if (!TextUtils.isEmpty(notificationSettings.zzcq)) {
                    this.zzbu.zzcq = notificationSettings.zzcq;
                }
                if (!TextUtils.isEmpty(notificationSettings.zzcr)) {
                    this.zzbu.zzcr = notificationSettings.zzcr;
                }
                this.zzbv = zzc(true);
            } else {
                throw new IllegalStateException("Current mode is default notification, notification attribute must not be provided");
            }
        } else {
            Preconditions.checkNotNull(notificationSettings.zzbv, "notification is required.");
            Notification notification = notificationSettings.zzbv;
            this.zzbv = notification;
            this.zzbu.zzbv = notification;
        }
        startForeground(zzbp, this.zzbv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Display display) {
        this.zzbz = display;
        if (this.zzbw) {
            Notification notificationZzc = zzc(true);
            this.zzbv = notificationZzc;
            startForeground(zzbp, notificationZzc);
        }
        if (this.zzbs.get() != null) {
            this.zzbs.get().onRemoteDisplaySessionStarted(this);
        }
        onCreatePresentation(this.zzbz);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean zza(String str, CastDevice castDevice, Options options, NotificationSettings notificationSettings, Context context, ServiceConnection serviceConnection, Callbacks callbacks) {
        zzb("startRemoteDisplaySession");
        Preconditions.checkMainThread("Starting the Cast Remote Display must be done on the main thread");
        synchronized (zzbq) {
            if (zzcg != null) {
                zzbf.w("An existing service had not been stopped before starting one", new Object[0]);
                return false;
            }
            zzcg = this;
            this.zzbs = new WeakReference<>(callbacks);
            this.zzy = str;
            this.zzby = castDevice;
            this.zzca = context;
            this.zzcb = serviceConnection;
            if (this.zzcc == null) {
                this.zzcc = MediaRouter.getInstance(getApplicationContext());
            }
            MediaRouteSelector mediaRouteSelectorBuild = new MediaRouteSelector.Builder().addControlCategory(CastMediaControlIntent.categoryForCast(this.zzy)).build();
            zzb("addMediaRouterCallback");
            this.zzcc.addCallback(mediaRouteSelectorBuild, this.zzcf, 4);
            this.zzbv = notificationSettings.zzbv;
            zzu zzuVar = null;
            this.zzbt = new zzb(zzuVar);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intentFilter.addAction("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
            registerReceiver(this.zzbt, intentFilter);
            NotificationSettings notificationSettings2 = new NotificationSettings(notificationSettings, zzuVar);
            this.zzbu = notificationSettings2;
            if (notificationSettings2.zzbv == null) {
                this.zzbw = true;
                this.zzbv = zzc(false);
            } else {
                this.zzbw = false;
                this.zzbv = this.zzbu.zzbv;
            }
            startForeground(zzbp, this.zzbv);
            zzb("startRemoteDisplay");
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_SESSION_ENDED");
            intent.setPackage(this.zzca.getPackageName());
            this.zzce.startRemoteDisplay(castDevice, this.zzy, options.getConfigPreset(), PendingIntent.getBroadcast(this, 0, intent, 0)).addOnCompleteListener(new zzz(this));
            if (this.zzbs.get() != null) {
                this.zzbs.get().onServiceCreated(this);
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zze() {
        if (this.zzbs.get() != null) {
            this.zzbs.get().onRemoteDisplaySessionError(new Status(CastStatusCodes.ERROR_SERVICE_CREATION_FAILED));
        }
        stopService();
    }

    private final Notification zzc(boolean z) {
        int i;
        int i2;
        zzb("createDefaultNotification");
        String str = this.zzbu.zzcq;
        String string = this.zzbu.zzcr;
        if (z) {
            i = R.string.cast_notification_connected_message;
            i2 = R.drawable.cast_ic_notification_on;
        } else {
            i = R.string.cast_notification_connecting_message;
            i2 = R.drawable.cast_ic_notification_connecting;
        }
        if (TextUtils.isEmpty(str)) {
            str = (String) getPackageManager().getApplicationLabel(getApplicationInfo());
        }
        if (TextUtils.isEmpty(string)) {
            string = getString(i, new Object[]{this.zzby.getFriendlyName()});
        }
        NotificationCompat.Builder ongoing = new NotificationCompat.Builder(this, "cast_remote_display_local_service").setContentTitle(str).setContentText(string).setContentIntent(this.zzbu.zzcp).setSmallIcon(i2).setOngoing(true);
        String string2 = getString(R.string.cast_notification_disconnect);
        if (this.zzbx == null) {
            Intent intent = new Intent("com.google.android.gms.cast.remote_display.ACTION_NOTIFICATION_DISCONNECT");
            intent.setPackage(this.zzca.getPackageName());
            this.zzbx = PendingIntent.getBroadcast(this, 0, intent, 134217728);
        }
        return ongoing.addAction(android.R.drawable.ic_menu_close_clear_cancel, string2, this.zzbx).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(String str) {
        zzbf.d("[Instance: %s] %s", this, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzc(String str) {
        zzbf.e("[Instance: %s] %s", this, str);
    }

    static /* synthetic */ ServiceConnection zza(CastRemoteDisplayLocalService castRemoteDisplayLocalService, ServiceConnection serviceConnection) {
        castRemoteDisplayLocalService.zzcb = null;
        return null;
    }

    static /* synthetic */ Context zza(CastRemoteDisplayLocalService castRemoteDisplayLocalService, Context context) {
        castRemoteDisplayLocalService.zzca = null;
        return null;
    }

    static /* synthetic */ Display zzb(CastRemoteDisplayLocalService castRemoteDisplayLocalService, Display display) {
        castRemoteDisplayLocalService.zzbz = null;
        return null;
    }
}
