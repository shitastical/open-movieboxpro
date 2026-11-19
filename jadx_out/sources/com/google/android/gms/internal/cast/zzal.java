package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.text.TextUtils;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.ReconnectionService;
import com.google.android.gms.cast.framework.media.MediaNotificationService;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: loaded from: classes4.dex */
public final class zzal implements RemoteMediaClient.Listener {
    private final Handler handler;
    private CastDevice zzaj;
    private final Context zzhe;
    private RemoteMediaClient zzid;
    private final zzw zzjd;
    private final CastOptions zzrb;
    private final ComponentName zzrc;
    private final zzaa zzrd;
    private final zzaa zzre;
    private final Runnable zzrf;
    private MediaSessionCompat zzrg;
    private MediaSessionCompat.Callback zzrh;
    private boolean zzri;

    public zzal(Context context, CastOptions castOptions, zzw zzwVar) {
        this.zzhe = context;
        this.zzrb = castOptions;
        this.zzjd = zzwVar;
        if (castOptions.getCastMediaOptions() == null || TextUtils.isEmpty(castOptions.getCastMediaOptions().getExpandedControllerActivityClassName())) {
            this.zzrc = null;
        } else {
            this.zzrc = new ComponentName(context, castOptions.getCastMediaOptions().getExpandedControllerActivityClassName());
        }
        zzaa zzaaVar = new zzaa(context);
        this.zzrd = zzaaVar;
        zzaaVar.zza(new zzan(this));
        zzaa zzaaVar2 = new zzaa(context);
        this.zzre = zzaaVar2;
        zzaaVar2.zza(new zzao(this));
        this.handler = new zzez(Looper.getMainLooper());
        this.zzrf = new Runnable(this) { // from class: com.google.android.gms.internal.cast.zzam
            private final zzal zzrj;

            {
                this.zzrj = this;
            }

            @Override // java.lang.Runnable
            public final void run() {
                this.zzrj.zzdc();
            }
        };
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onSendingRemoteMediaRequest() {
    }

    public final void zza(RemoteMediaClient remoteMediaClient, CastDevice castDevice) {
        CastOptions castOptions;
        if (this.zzri || (castOptions = this.zzrb) == null || castOptions.getCastMediaOptions() == null || remoteMediaClient == null || castDevice == null) {
            return;
        }
        this.zzid = remoteMediaClient;
        remoteMediaClient.addListener(this);
        this.zzaj = castDevice;
        if (!PlatformVersion.isAtLeastLollipop()) {
            ((AudioManager) this.zzhe.getSystemService("audio")).requestAudioFocus(null, 3, 3);
        }
        ComponentName componentName = new ComponentName(this.zzhe, this.zzrb.getCastMediaOptions().getMediaIntentReceiverClassName());
        Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
        intent.setComponent(componentName);
        MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(this.zzhe, "CastMediaSession", componentName, PendingIntent.getBroadcast(this.zzhe, 0, intent, 0));
        this.zzrg = mediaSessionCompat;
        mediaSessionCompat.setFlags(3);
        zza(0, (MediaInfo) null);
        CastDevice castDevice2 = this.zzaj;
        if (castDevice2 != null && !TextUtils.isEmpty(castDevice2.getFriendlyName())) {
            this.zzrg.setMetadata(new MediaMetadataCompat.Builder().putString("android.media.metadata.ALBUM_ARTIST", this.zzhe.getResources().getString(R.string.cast_casting_to_device, this.zzaj.getFriendlyName())).build());
        }
        zzap zzapVar = new zzap(this);
        this.zzrh = zzapVar;
        this.zzrg.setCallback(zzapVar);
        this.zzrg.setActive(true);
        this.zzjd.setMediaSessionCompat(this.zzrg);
        this.zzri = true;
        zzg(false);
    }

    public final void zzn(int i) {
        if (this.zzri) {
            this.zzri = false;
            RemoteMediaClient remoteMediaClient = this.zzid;
            if (remoteMediaClient != null) {
                remoteMediaClient.removeListener(this);
            }
            if (!PlatformVersion.isAtLeastLollipop()) {
                ((AudioManager) this.zzhe.getSystemService("audio")).abandonAudioFocus(null);
            }
            this.zzjd.setMediaSessionCompat(null);
            zzaa zzaaVar = this.zzrd;
            if (zzaaVar != null) {
                zzaaVar.clear();
            }
            zzaa zzaaVar2 = this.zzre;
            if (zzaaVar2 != null) {
                zzaaVar2.clear();
            }
            MediaSessionCompat mediaSessionCompat = this.zzrg;
            if (mediaSessionCompat != null) {
                mediaSessionCompat.setSessionActivity(null);
                this.zzrg.setCallback(null);
                this.zzrg.setMetadata(new MediaMetadataCompat.Builder().build());
                zza(0, (MediaInfo) null);
                this.zzrg.setActive(false);
                this.zzrg.release();
                this.zzrg = null;
            }
            this.zzid = null;
            this.zzaj = null;
            this.zzrh = null;
            zzda();
            if (i == 0) {
                zzdb();
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onQueueStatusUpdated() {
        zzg(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onStatusUpdated() {
        zzg(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onMetadataUpdated() {
        zzg(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onPreloadStatusUpdated() {
        zzg(false);
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public final void onAdBreakStatusUpdated() {
        zzg(false);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x006a  */
    public final void zzg(boolean z) {
        boolean z2;
        int i;
        boolean z3;
        RemoteMediaClient remoteMediaClient = this.zzid;
        if (remoteMediaClient == null) {
            return;
        }
        MediaStatus mediaStatus = remoteMediaClient.getMediaStatus();
        MediaInfo mediaInfo = mediaStatus == null ? null : mediaStatus.getMediaInfo();
        MediaMetadata metadata = mediaInfo == null ? null : mediaInfo.getMetadata();
        boolean z4 = false;
        if (mediaStatus == null || mediaInfo == null || metadata == null) {
            z2 = false;
            i = 0;
        } else {
            int playerState = this.zzid.getPlayerState();
            i = 6;
            if (playerState == 1) {
                int idleReason = mediaStatus.getIdleReason();
                boolean z5 = this.zzid.isLiveStream() && idleReason == 2;
                int loadingItemId = mediaStatus.getLoadingItemId();
                z2 = loadingItemId != 0 && (idleReason == 1 || idleReason == 3);
                if (z5) {
                    i = 2;
                } else {
                    MediaQueueItem queueItemById = mediaStatus.getQueueItemById(loadingItemId);
                    if (queueItemById != null) {
                        mediaInfo = queueItemById.getMedia();
                    } else {
                        i = 0;
                    }
                }
            } else if (playerState == 2) {
                z2 = false;
                i = 3;
            } else if (playerState == 3) {
                z2 = false;
                i = 2;
            } else if (playerState != 4) {
                z2 = false;
                i = 0;
            } else {
                z2 = false;
            }
        }
        zza(i, mediaInfo);
        if (i == 0) {
            zzda();
            zzdb();
            return;
        }
        if (this.zzrb.getCastMediaOptions().getNotificationOptions() != null && this.zzid != null) {
            Intent intent = new Intent(this.zzhe, (Class<?>) MediaNotificationService.class);
            intent.putExtra("extra_media_notification_force_update", z);
            intent.setPackage(this.zzhe.getPackageName());
            intent.setAction(MediaNotificationService.ACTION_UPDATE_NOTIFICATION);
            intent.putExtra("extra_media_info", this.zzid.getMediaInfo());
            intent.putExtra("extra_remote_media_client_player_state", this.zzid.getPlayerState());
            intent.putExtra("extra_cast_device", this.zzaj);
            MediaSessionCompat mediaSessionCompat = this.zzrg;
            intent.putExtra("extra_media_session_token", mediaSessionCompat != null ? mediaSessionCompat.getSessionToken() : null);
            MediaStatus mediaStatus2 = this.zzid.getMediaStatus();
            if (mediaStatus2 != null) {
                int queueRepeatMode = mediaStatus2.getQueueRepeatMode();
                if (queueRepeatMode == 1 || queueRepeatMode == 2 || queueRepeatMode == 3) {
                    z3 = true;
                } else {
                    Integer indexById = mediaStatus2.getIndexById(mediaStatus2.getCurrentItemId());
                    if (indexById != null) {
                        z3 = indexById.intValue() > 0;
                        if (indexById.intValue() < mediaStatus2.getQueueItemCount() - 1) {
                        }
                    } else {
                        z3 = false;
                    }
                    intent.putExtra("extra_can_skip_next", z4);
                    intent.putExtra("extra_can_skip_prev", z3);
                }
                z4 = true;
                intent.putExtra("extra_can_skip_next", z4);
                intent.putExtra("extra_can_skip_prev", z3);
            }
            if (Build.VERSION.SDK_INT >= 26 && !CastContext.getSharedInstance(this.zzhe).isAppVisible()) {
                this.zzhe.startForegroundService(intent);
            } else {
                this.zzhe.startService(intent);
            }
        }
        if (z2) {
            return;
        }
        zzh(true);
    }

    private final void zza(int i, MediaInfo mediaInfo) {
        PendingIntent activity;
        if (i == 0) {
            this.zzrg.setPlaybackState(new PlaybackStateCompat.Builder().setState(0, 0L, 1.0f).build());
            this.zzrg.setMetadata(new MediaMetadataCompat.Builder().build());
            return;
        }
        this.zzrg.setPlaybackState(new PlaybackStateCompat.Builder().setState(i, 0L, 1.0f).setActions(mediaInfo.getStreamType() == 2 ? 5L : 512L).build());
        MediaSessionCompat mediaSessionCompat = this.zzrg;
        if (this.zzrc == null) {
            activity = null;
        } else {
            Intent intent = new Intent();
            intent.setComponent(this.zzrc);
            activity = PendingIntent.getActivity(this.zzhe, 0, intent, 134217728);
        }
        mediaSessionCompat.setSessionActivity(activity);
        MediaMetadata metadata = mediaInfo.getMetadata();
        this.zzrg.setMetadata(zzcz().putString("android.media.metadata.TITLE", metadata.getString(MediaMetadata.KEY_TITLE)).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, metadata.getString(MediaMetadata.KEY_TITLE)).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, metadata.getString(MediaMetadata.KEY_SUBTITLE)).putLong("android.media.metadata.DURATION", mediaInfo.getStreamDuration()).build());
        Uri uriZza = zza(metadata, 0);
        if (uriZza != null) {
            this.zzrd.zza(uriZza);
        } else {
            zza((Bitmap) null, 0);
        }
        Uri uriZza2 = zza(metadata, 3);
        if (uriZza2 != null) {
            this.zzre.zza(uriZza2);
        } else {
            zza((Bitmap) null, 3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(Bitmap bitmap, int i) {
        if (i != 0) {
            if (i == 3) {
                this.zzrg.setMetadata(zzcz().putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, bitmap).build());
            }
        } else {
            if (bitmap != null) {
                this.zzrg.setMetadata(zzcz().putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmap).build());
                return;
            }
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
            bitmapCreateBitmap.eraseColor(0);
            this.zzrg.setMetadata(zzcz().putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmapCreateBitmap).build());
        }
    }

    private final Uri zza(MediaMetadata mediaMetadata, int i) {
        WebImage webImageOnPickImage;
        if (this.zzrb.getCastMediaOptions().getImagePicker() != null) {
            webImageOnPickImage = this.zzrb.getCastMediaOptions().getImagePicker().onPickImage(mediaMetadata, i);
        } else {
            webImageOnPickImage = mediaMetadata.hasImages() ? mediaMetadata.getImages().get(0) : null;
        }
        if (webImageOnPickImage == null) {
            return null;
        }
        return webImageOnPickImage.getUrl();
    }

    private final MediaMetadataCompat.Builder zzcz() {
        MediaMetadataCompat metadata = this.zzrg.getController().getMetadata();
        if (metadata == null) {
            return new MediaMetadataCompat.Builder();
        }
        return new MediaMetadataCompat.Builder(metadata);
    }

    private final void zzda() {
        if (this.zzrb.getCastMediaOptions().getNotificationOptions() == null) {
            return;
        }
        Intent intent = new Intent(this.zzhe, (Class<?>) MediaNotificationService.class);
        intent.setPackage(this.zzhe.getPackageName());
        intent.setAction(MediaNotificationService.ACTION_UPDATE_NOTIFICATION);
        this.zzhe.stopService(intent);
    }

    private final void zzh(boolean z) {
        if (this.zzrb.getEnableReconnectionService()) {
            this.handler.removeCallbacks(this.zzrf);
            Intent intent = new Intent(this.zzhe, (Class<?>) ReconnectionService.class);
            intent.setPackage(this.zzhe.getPackageName());
            try {
                this.zzhe.startService(intent);
            } catch (IllegalStateException unused) {
                if (z) {
                    this.handler.postDelayed(this.zzrf, 1000L);
                }
            }
        }
    }

    private final void zzdb() {
        if (this.zzrb.getEnableReconnectionService()) {
            this.handler.removeCallbacks(this.zzrf);
            Intent intent = new Intent(this.zzhe, (Class<?>) ReconnectionService.class);
            intent.setPackage(this.zzhe.getPackageName());
            this.zzhe.stopService(intent);
        }
    }

    final /* synthetic */ void zzdc() {
        zzh(false);
    }
}
