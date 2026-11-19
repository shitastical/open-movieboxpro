package com.google.android.gms.cast.framework.media;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.TextUtils;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;
import androidx.core.app.NotificationCompat;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.AppVisibilityListener;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzdk;
import com.google.android.gms.internal.cast.zzdw;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MediaNotificationService extends Service {
    public static final String ACTION_UPDATE_NOTIFICATION = "com.google.android.gms.cast.framework.action.UPDATE_NOTIFICATION";
    private static final zzdw zzbf = new zzdw("MediaNotificationService");
    private Notification zzbv;
    private NotificationOptions zzln;
    private ImagePicker zzlp;
    private ComponentName zzlu;
    private ComponentName zzlv;
    private int[] zzlx;
    private zzd zzly;
    private long zzlz;
    private com.google.android.gms.internal.cast.zzaa zzma;
    private ImageHints zzmb;
    private Resources zzmc;
    private AppVisibilityListener zzmd;
    private zza zzme;
    private zzb zzmf;
    private CastContext zzmg;
    private List<String> zzlw = new ArrayList();
    private final BroadcastReceiver zzmh = new zzh(this);

    private static class zza {
        public final int streamType;
        public final String zzf;
        public final MediaSessionCompat.Token zzmk;
        public final boolean zzml;
        public final String zzmm;
        public final boolean zzmn;
        public final boolean zzmo;

        public zza(boolean z, int i, String str, String str2, MediaSessionCompat.Token token, boolean z2, boolean z3) {
            this.zzml = z;
            this.streamType = i;
            this.zzf = str;
            this.zzmm = str2;
            this.zzmk = token;
            this.zzmn = z2;
            this.zzmo = z3;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class zzb {
        public final Uri zzmp;
        public Bitmap zzmq;

        public zzb(WebImage webImage) {
            this.zzmp = webImage == null ? null : webImage.getUrl();
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public void onCreate() {
        CastContext sharedInstance = CastContext.getSharedInstance(this);
        this.zzmg = sharedInstance;
        CastMediaOptions castMediaOptions = sharedInstance.getCastOptions().getCastMediaOptions();
        this.zzln = castMediaOptions.getNotificationOptions();
        this.zzlp = castMediaOptions.getImagePicker();
        this.zzmc = getResources();
        this.zzlu = new ComponentName(getApplicationContext(), castMediaOptions.getMediaIntentReceiverClassName());
        if (!TextUtils.isEmpty(this.zzln.getTargetActivityClassName())) {
            this.zzlv = new ComponentName(getApplicationContext(), this.zzln.getTargetActivityClassName());
        } else {
            this.zzlv = null;
        }
        zzd zzdVarZzcl = this.zzln.zzcl();
        this.zzly = zzdVarZzcl;
        if (zzdVarZzcl == null) {
            this.zzlw.addAll(this.zzln.getActions());
            this.zzlx = (int[]) this.zzln.getCompatActionIndices().clone();
        } else {
            this.zzlx = null;
        }
        this.zzlz = this.zzln.getSkipStepMs();
        int dimensionPixelSize = this.zzmc.getDimensionPixelSize(this.zzln.zzbz());
        this.zzmb = new ImageHints(1, dimensionPixelSize, dimensionPixelSize);
        this.zzma = new com.google.android.gms.internal.cast.zzaa(getApplicationContext(), this.zzmb);
        zzi zziVar = new zzi(this);
        this.zzmd = zziVar;
        this.zzmg.addAppVisibilityListener(zziVar);
        if (this.zzlv != null) {
            registerReceiver(this.zzmh, new IntentFilter(this.zzlv.flattenToString()));
        }
        if (PlatformVersion.isAtLeastO()) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
            NotificationChannel notificationChannelM = ComponentDialog$$ExternalSyntheticApiModelOutline0.m("cast_media_notification", "Cast", 2);
            notificationChannelM.setShowBadge(false);
            notificationManager.createNotificationChannel(notificationChannelM);
        }
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        MediaMetadata metadata;
        int intExtra;
        CastDevice castDevice;
        WebImage webImageOnPickImage;
        zza zzaVar;
        if (intent != null && ACTION_UPDATE_NOTIFICATION.equals(intent.getAction())) {
            MediaInfo mediaInfo = (MediaInfo) intent.getParcelableExtra("extra_media_info");
            if (mediaInfo != null && (metadata = mediaInfo.getMetadata()) != null && (intExtra = intent.getIntExtra("extra_remote_media_client_player_state", 0)) != 0 && (castDevice = (CastDevice) intent.getParcelableExtra("extra_cast_device")) != null) {
                zza zzaVar2 = new zza(intExtra == 2, mediaInfo.getStreamType(), metadata.getString(MediaMetadata.KEY_TITLE), castDevice.getFriendlyName(), (MediaSessionCompat.Token) intent.getParcelableExtra("extra_media_session_token"), intent.getBooleanExtra("extra_can_skip_next", false), intent.getBooleanExtra("extra_can_skip_prev", false));
                if (intent.getBooleanExtra("extra_media_notification_force_update", false) || (zzaVar = this.zzme) == null || zzaVar2.zzml != zzaVar.zzml || zzaVar2.streamType != zzaVar.streamType || !zzdk.zza(zzaVar2.zzf, zzaVar.zzf) || !zzdk.zza(zzaVar2.zzmm, zzaVar.zzmm) || zzaVar2.zzmn != zzaVar.zzmn || zzaVar2.zzmo != zzaVar.zzmo) {
                    this.zzme = zzaVar2;
                    zzbk();
                }
                ImagePicker imagePicker = this.zzlp;
                if (imagePicker != null) {
                    webImageOnPickImage = imagePicker.onPickImage(metadata, this.zzmb);
                } else {
                    webImageOnPickImage = metadata.hasImages() ? metadata.getImages().get(0) : null;
                }
                zzb zzbVar = new zzb(webImageOnPickImage);
                zzb zzbVar2 = this.zzmf;
                if (zzbVar2 == null || !zzdk.zza(zzbVar.zzmp, zzbVar2.zzmp)) {
                    this.zzma.zza(new zzj(this, zzbVar));
                    this.zzma.zza(zzbVar.zzmp);
                }
            } else {
                stopForeground(true);
            }
        }
        return 2;
    }

    @Override // android.app.Service
    public void onDestroy() {
        com.google.android.gms.internal.cast.zzaa zzaaVar = this.zzma;
        if (zzaaVar != null) {
            zzaaVar.clear();
        }
        if (this.zzlv != null) {
            try {
                unregisterReceiver(this.zzmh);
            } catch (IllegalArgumentException e) {
                zzbf.zzc(e, "Unregistering trampoline BroadcastReceiver failed", new Object[0]);
            }
        }
        ((NotificationManager) getSystemService("notification")).cancel(1);
        this.zzmg.removeAppVisibilityListener(this.zzmd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzbk() {
        int[] iArr;
        if (this.zzme == null) {
            return;
        }
        zzb zzbVar = this.zzmf;
        PendingIntent broadcast = null;
        NotificationCompat.Builder visibility = new NotificationCompat.Builder(this, "cast_media_notification").setLargeIcon(zzbVar == null ? null : zzbVar.zzmq).setSmallIcon(this.zzln.getSmallIconDrawableResId()).setContentTitle(this.zzme.zzf).setContentText(this.zzmc.getString(this.zzln.getCastingToDeviceStringResId(), this.zzme.zzmm)).setOngoing(true).setShowWhen(false).setVisibility(1);
        if (this.zzlv != null) {
            Intent intent = new Intent();
            intent.putExtra("targetActivity", this.zzlv);
            intent.setAction(this.zzlv.flattenToString());
            broadcast = PendingIntent.getBroadcast(this, 1, intent, 134217728);
        }
        if (broadcast != null) {
            visibility.setContentIntent(broadcast);
        }
        if (this.zzly != null) {
            zzdw zzdwVar = zzbf;
            zzdwVar.i("mActionsProvider != null", new Object[0]);
            try {
                List<NotificationAction> notificationActions = this.zzly.getNotificationActions();
                int[] compactViewActionIndices = this.zzly.getCompactViewActionIndices();
                if (notificationActions == null || notificationActions.isEmpty()) {
                    zzdwVar.e("NotificationActionsProvider".concat(" doesn't provide any action."), new Object[0]);
                    return;
                }
                if (notificationActions.size() > 5) {
                    zzdwVar.e("NotificationActionsProvider".concat(" provides more than 5 actions."), new Object[0]);
                    return;
                }
                int size = notificationActions.size();
                if (compactViewActionIndices == null || compactViewActionIndices.length == 0) {
                    zzdwVar.e("NotificationActionsProvider".concat(" doesn't provide any actions for compact view."), new Object[0]);
                    return;
                }
                for (int i : compactViewActionIndices) {
                    if (i < 0 || i >= size) {
                        zzbf.e("NotificationActionsProvider".concat("provides a compact view action whose index is out of bounds."), new Object[0]);
                        return;
                    }
                }
                iArr = (int[]) compactViewActionIndices.clone();
                for (NotificationAction notificationAction : notificationActions) {
                    new NotificationAction.Builder().setAction(notificationAction.getAction());
                    String action = notificationAction.getAction();
                    if (action.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK) || action.equals(MediaIntentReceiver.ACTION_SKIP_NEXT) || action.equals(MediaIntentReceiver.ACTION_SKIP_PREV) || action.equals(MediaIntentReceiver.ACTION_FORWARD) || action.equals(MediaIntentReceiver.ACTION_REWIND) || action.equals(MediaIntentReceiver.ACTION_STOP_CASTING)) {
                        zza(visibility, notificationAction.getAction());
                    } else {
                        Intent intent2 = new Intent(notificationAction.getAction());
                        intent2.setComponent(this.zzlu);
                        visibility.addAction(new NotificationCompat.Action.Builder(notificationAction.getIconResId(), notificationAction.getContentDescription(), PendingIntent.getBroadcast(this, 0, intent2, 0)).build());
                    }
                }
            } catch (RemoteException e) {
                zzbf.zzc(e, "Unable to call %s on %s.", "getNotificationActions", "zzd");
                return;
            }
        } else {
            Iterator<String> it = this.zzlw.iterator();
            while (it.hasNext()) {
                zza(visibility, it.next());
            }
            iArr = this.zzlx;
        }
        visibility.setStyle(new androidx.media.app.NotificationCompat.MediaStyle().setShowActionsInCompactView(iArr).setMediaSession(this.zzme.zzmk));
        this.zzbv = visibility.build();
        if (!this.zzmg.isAppVisible()) {
            startForeground(1, this.zzbv);
        } else {
            stopForeground(true);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void zza(NotificationCompat.Builder builder, String str) {
        int pauseDrawableResId;
        int iZzca;
        str.hashCode();
        byte b = -1;
        switch (str.hashCode()) {
            case -1699820260:
                if (str.equals(MediaIntentReceiver.ACTION_REWIND)) {
                    b = 0;
                }
                break;
            case -945151566:
                if (str.equals(MediaIntentReceiver.ACTION_SKIP_NEXT)) {
                    b = 1;
                }
                break;
            case -945080078:
                if (str.equals(MediaIntentReceiver.ACTION_SKIP_PREV)) {
                    b = 2;
                }
                break;
            case -668151673:
                if (str.equals(MediaIntentReceiver.ACTION_STOP_CASTING)) {
                    b = 3;
                }
                break;
            case -124479363:
                if (str.equals(MediaIntentReceiver.ACTION_DISCONNECT)) {
                    b = 4;
                }
                break;
            case 235550565:
                if (str.equals(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK)) {
                    b = 5;
                }
                break;
            case 1362116196:
                if (str.equals(MediaIntentReceiver.ACTION_FORWARD)) {
                    b = 6;
                }
                break;
        }
        PendingIntent broadcast = null;
        switch (b) {
            case 0:
                long j = this.zzlz;
                Intent intent = new Intent(MediaIntentReceiver.ACTION_REWIND);
                intent.setComponent(this.zzlu);
                intent.putExtra(MediaIntentReceiver.EXTRA_SKIP_STEP_MS, j);
                PendingIntent broadcast2 = PendingIntent.getBroadcast(this, 0, intent, 134217728);
                int rewindDrawableResId = this.zzln.getRewindDrawableResId();
                int iZzch = this.zzln.zzch();
                if (j == 10000) {
                    rewindDrawableResId = this.zzln.getRewind10DrawableResId();
                    iZzch = this.zzln.zzci();
                } else if (j == NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) {
                    rewindDrawableResId = this.zzln.getRewind30DrawableResId();
                    iZzch = this.zzln.zzcj();
                }
                builder.addAction(new NotificationCompat.Action.Builder(rewindDrawableResId, this.zzmc.getString(iZzch), broadcast2).build());
                break;
            case 1:
                if (this.zzme.zzmn) {
                    Intent intent2 = new Intent(MediaIntentReceiver.ACTION_SKIP_NEXT);
                    intent2.setComponent(this.zzlu);
                    broadcast = PendingIntent.getBroadcast(this, 0, intent2, 0);
                }
                builder.addAction(new NotificationCompat.Action.Builder(this.zzln.getSkipNextDrawableResId(), this.zzmc.getString(this.zzln.zzcc()), broadcast).build());
                break;
            case 2:
                if (this.zzme.zzmo) {
                    Intent intent3 = new Intent(MediaIntentReceiver.ACTION_SKIP_PREV);
                    intent3.setComponent(this.zzlu);
                    broadcast = PendingIntent.getBroadcast(this, 0, intent3, 0);
                }
                builder.addAction(new NotificationCompat.Action.Builder(this.zzln.getSkipPrevDrawableResId(), this.zzmc.getString(this.zzln.zzcd()), broadcast).build());
                break;
            case 3:
            case 4:
                Intent intent4 = new Intent(MediaIntentReceiver.ACTION_STOP_CASTING);
                intent4.setComponent(this.zzlu);
                builder.addAction(new NotificationCompat.Action.Builder(this.zzln.getDisconnectDrawableResId(), this.zzmc.getString(this.zzln.zzck()), PendingIntent.getBroadcast(this, 0, intent4, 0)).build());
                break;
            case 5:
                int i = this.zzme.streamType;
                boolean z = this.zzme.zzml;
                if (i == 2) {
                    pauseDrawableResId = this.zzln.getStopLiveStreamDrawableResId();
                    iZzca = this.zzln.getStopLiveStreamTitleResId();
                } else {
                    pauseDrawableResId = this.zzln.getPauseDrawableResId();
                    iZzca = this.zzln.zzca();
                }
                if (!z) {
                    pauseDrawableResId = this.zzln.getPlayDrawableResId();
                }
                if (!z) {
                    iZzca = this.zzln.zzcb();
                }
                Intent intent5 = new Intent(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK);
                intent5.setComponent(this.zzlu);
                builder.addAction(new NotificationCompat.Action.Builder(pauseDrawableResId, this.zzmc.getString(iZzca), PendingIntent.getBroadcast(this, 0, intent5, 0)).build());
                break;
            case 6:
                long j2 = this.zzlz;
                Intent intent6 = new Intent(MediaIntentReceiver.ACTION_FORWARD);
                intent6.setComponent(this.zzlu);
                intent6.putExtra(MediaIntentReceiver.EXTRA_SKIP_STEP_MS, j2);
                PendingIntent broadcast3 = PendingIntent.getBroadcast(this, 0, intent6, 134217728);
                int forwardDrawableResId = this.zzln.getForwardDrawableResId();
                int iZzce = this.zzln.zzce();
                if (j2 == 10000) {
                    forwardDrawableResId = this.zzln.getForward10DrawableResId();
                    iZzce = this.zzln.zzcf();
                } else if (j2 == NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) {
                    forwardDrawableResId = this.zzln.getForward30DrawableResId();
                    iZzce = this.zzln.zzcg();
                }
                builder.addAction(new NotificationCompat.Action.Builder(forwardDrawableResId, this.zzmc.getString(iZzce), broadcast3).build());
                break;
            default:
                zzbf.e("Action: %s is not a pre-defined action.", str);
                break;
        }
    }
}
