package com.google.android.gms.cast.framework;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzal;
import com.google.android.gms.internal.cast.zzdw;
import com.google.android.gms.internal.cast.zzdx;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class CastSession extends Session {
    private static final zzdw zzbf = new zzdw("CastSession");
    private final Context zzhe;
    private final CastOptions zzhk;
    private final Set<Cast.Listener> zzhx;
    private final zzl zzhy;
    private final Cast.CastApi zzhz;
    private final com.google.android.gms.internal.cast.zzg zzia;
    private final zzal zzib;
    private GoogleApiClient zzic;
    private RemoteMediaClient zzid;
    private CastDevice zzie;
    private Cast.ApplicationConnectionResult zzif;

    private class zzc extends Cast.Listener {
        private zzc() {
        }

        @Override // com.google.android.gms.cast.Cast.Listener
        public final void onApplicationDisconnected(int i) {
            CastSession.this.zzh(i);
            CastSession.this.notifySessionEnded(i);
            Iterator it = new HashSet(CastSession.this.zzhx).iterator();
            while (it.hasNext()) {
                ((Cast.Listener) it.next()).onApplicationDisconnected(i);
            }
        }

        @Override // com.google.android.gms.cast.Cast.Listener
        public final void onApplicationStatusChanged() {
            Iterator it = new HashSet(CastSession.this.zzhx).iterator();
            while (it.hasNext()) {
                ((Cast.Listener) it.next()).onApplicationStatusChanged();
            }
        }

        @Override // com.google.android.gms.cast.Cast.Listener
        public final void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
            Iterator it = new HashSet(CastSession.this.zzhx).iterator();
            while (it.hasNext()) {
                ((Cast.Listener) it.next()).onApplicationMetadataChanged(applicationMetadata);
            }
        }

        @Override // com.google.android.gms.cast.Cast.Listener
        public final void onActiveInputStateChanged(int i) {
            Iterator it = new HashSet(CastSession.this.zzhx).iterator();
            while (it.hasNext()) {
                ((Cast.Listener) it.next()).onActiveInputStateChanged(i);
            }
        }

        @Override // com.google.android.gms.cast.Cast.Listener
        public final void onStandbyStateChanged(int i) {
            Iterator it = new HashSet(CastSession.this.zzhx).iterator();
            while (it.hasNext()) {
                ((Cast.Listener) it.next()).onStandbyStateChanged(i);
            }
        }

        @Override // com.google.android.gms.cast.Cast.Listener
        public final void onVolumeChanged() {
            Iterator it = new HashSet(CastSession.this.zzhx).iterator();
            while (it.hasNext()) {
                ((Cast.Listener) it.next()).onVolumeChanged();
            }
        }
    }

    private class zzd implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
        private zzd() {
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public final void onConnected(Bundle bundle) {
            try {
                if (CastSession.this.zzid != null) {
                    try {
                        CastSession.this.zzid.zzco();
                        CastSession.this.zzid.requestStatus();
                    } catch (IOException e) {
                        CastSession.zzbf.zzc(e, "Exception when setting GoogleApiClient.", new Object[0]);
                        CastSession.this.zzid = null;
                    }
                }
                CastSession.this.zzhy.onConnected(bundle);
            } catch (RemoteException e2) {
                CastSession.zzbf.zza(e2, "Unable to call %s on %s.", "onConnected", "zzl");
            }
        }

        @Override // com.google.android.gms.common.api.internal.ConnectionCallbacks
        public final void onConnectionSuspended(int i) {
            try {
                CastSession.this.zzhy.onConnectionSuspended(i);
            } catch (RemoteException e) {
                CastSession.zzbf.zza(e, "Unable to call %s on %s.", "onConnectionSuspended", "zzl");
            }
        }

        @Override // com.google.android.gms.common.api.internal.OnConnectionFailedListener
        public final void onConnectionFailed(ConnectionResult connectionResult) {
            try {
                CastSession.this.zzhy.onConnectionFailed(connectionResult);
            } catch (RemoteException e) {
                CastSession.zzbf.zza(e, "Unable to call %s on %s.", "onConnectionFailed", "zzl");
            }
        }
    }

    public CastSession(Context context, String str, String str2, CastOptions castOptions, Cast.CastApi castApi, com.google.android.gms.internal.cast.zzg zzgVar, zzal zzalVar) {
        super(context, str, str2);
        this.zzhx = new HashSet();
        this.zzhe = context.getApplicationContext();
        this.zzhk = castOptions;
        this.zzhz = castApi;
        this.zzia = zzgVar;
        this.zzib = zzalVar;
        this.zzhy = com.google.android.gms.internal.cast.zze.zza(context, castOptions, zzaf(), new zzb());
    }

    private class zzb extends zzi {
        private zzb() {
        }

        @Override // com.google.android.gms.cast.framework.zzh
        public final int zzs() {
            return 12451009;
        }

        @Override // com.google.android.gms.cast.framework.zzh
        public final void zza(String str, String str2) {
            if (CastSession.this.zzic != null) {
                CastSession.this.zzhz.joinApplication(CastSession.this.zzic, str, str2).setResultCallback(CastSession.this.new zza("joinApplication"));
            }
        }

        @Override // com.google.android.gms.cast.framework.zzh
        public final void zza(String str, LaunchOptions launchOptions) {
            if (CastSession.this.zzic != null) {
                CastSession.this.zzhz.launchApplication(CastSession.this.zzic, str, launchOptions).setResultCallback(CastSession.this.new zza("launchApplication"));
            }
        }

        @Override // com.google.android.gms.cast.framework.zzh
        public final void zzj(String str) {
            if (CastSession.this.zzic != null) {
                CastSession.this.zzhz.stopApplication(CastSession.this.zzic, str);
            }
        }

        @Override // com.google.android.gms.cast.framework.zzh
        public final void zzh(int i) {
            CastSession.this.zzh(i);
        }
    }

    private class zza implements ResultCallback<Cast.ApplicationConnectionResult> {
        private String command;

        zza(String str) {
            this.command = str;
        }

        @Override // com.google.android.gms.common.api.ResultCallback
        public final /* synthetic */ void onResult(Result result) {
            Cast.ApplicationConnectionResult applicationConnectionResult = (Cast.ApplicationConnectionResult) result;
            CastSession.this.zzif = applicationConnectionResult;
            try {
                if (applicationConnectionResult.getStatus().isSuccess()) {
                    CastSession.zzbf.d("%s() -> success result", this.command);
                    CastSession.this.zzid = new RemoteMediaClient(new zzdx(null), CastSession.this.zzhz);
                    try {
                        CastSession.this.zzid.zzb(CastSession.this.zzic);
                        CastSession.this.zzid.zzco();
                        CastSession.this.zzid.requestStatus();
                        CastSession.this.zzib.zza(CastSession.this.zzid, CastSession.this.getCastDevice());
                    } catch (IOException e) {
                        CastSession.zzbf.zzc(e, "Exception when setting GoogleApiClient.", new Object[0]);
                        CastSession.this.zzid = null;
                    }
                    CastSession.this.zzhy.zza(applicationConnectionResult.getApplicationMetadata(), applicationConnectionResult.getApplicationStatus(), applicationConnectionResult.getSessionId(), applicationConnectionResult.getWasLaunched());
                    return;
                }
                CastSession.zzbf.d("%s() -> failure result", this.command);
                CastSession.this.zzhy.zzi(applicationConnectionResult.getStatus().getStatusCode());
            } catch (RemoteException e2) {
                CastSession.zzbf.zza(e2, "Unable to call %s on %s.", "methods", "zzl");
            }
        }
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void onStarting(Bundle bundle) {
        this.zzie = CastDevice.getFromBundle(bundle);
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void onResuming(Bundle bundle) {
        this.zzie = CastDevice.getFromBundle(bundle);
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void start(Bundle bundle) {
        zzb(bundle);
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void resume(Bundle bundle) {
        zzb(bundle);
    }

    @Override // com.google.android.gms.cast.framework.Session
    protected void end(boolean z) {
        try {
            this.zzhy.zza(z, 0);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "disconnectFromDevice", "zzl");
        }
        notifySessionEnded(0);
    }

    public RemoteMediaClient getRemoteMediaClient() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzid;
    }

    public CastDevice getCastDevice() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzie;
    }

    public void requestStatus() throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            this.zzhz.requestStatus(googleApiClient);
        }
    }

    public int getActiveInputState() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            return this.zzhz.getActiveInputState(googleApiClient);
        }
        return -1;
    }

    public int getStandbyState() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            return this.zzhz.getStandbyState(googleApiClient);
        }
        return -1;
    }

    public ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            return this.zzhz.getApplicationMetadata(googleApiClient);
        }
        return null;
    }

    public String getApplicationStatus() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            return this.zzhz.getApplicationStatus(googleApiClient);
        }
        return null;
    }

    public void setVolume(double d) throws IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            this.zzhz.setVolume(googleApiClient, d);
        }
    }

    public double getVolume() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            return this.zzhz.getVolume(googleApiClient);
        }
        return 0.0d;
    }

    public final zzal zzy() {
        return this.zzib;
    }

    public void setMute(boolean z) throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            this.zzhz.setMute(googleApiClient, z);
        }
    }

    public boolean isMute() throws IllegalStateException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            return this.zzhz.isMute(googleApiClient);
        }
        return false;
    }

    public Cast.ApplicationConnectionResult getApplicationConnectionResult() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzif;
    }

    public void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            this.zzhz.setMessageReceivedCallbacks(googleApiClient, str, messageReceivedCallback);
        }
    }

    public void removeMessageReceivedCallbacks(String str) throws IOException, IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            this.zzhz.removeMessageReceivedCallbacks(googleApiClient, str);
        }
    }

    public PendingResult<Status> sendMessage(String str, String str2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            return this.zzhz.sendMessage(googleApiClient, str, str2);
        }
        return null;
    }

    public void addCastListener(Cast.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzhx.add(listener);
        }
    }

    public void removeCastListener(Cast.Listener listener) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (listener != null) {
            this.zzhx.remove(listener);
        }
    }

    private final void zzb(Bundle bundle) {
        CastDevice fromBundle = CastDevice.getFromBundle(bundle);
        this.zzie = fromBundle;
        if (fromBundle == null) {
            if (isResuming()) {
                notifyFailedToResumeSession(8);
                return;
            } else {
                notifyFailedToStartSession(8);
                return;
            }
        }
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
            this.zzic = null;
        }
        zzbf.d("Acquiring a connection to Google Play Services for %s", this.zzie);
        zzd zzdVar = new zzd();
        Context context = this.zzhe;
        CastDevice castDevice = this.zzie;
        CastOptions castOptions = this.zzhk;
        zzc zzcVar = new zzc();
        Bundle bundle2 = new Bundle();
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_FRAMEWORK_NOTIFICATION_ENABLED", (castOptions == null || castOptions.getCastMediaOptions() == null || castOptions.getCastMediaOptions().getNotificationOptions() == null) ? false : true);
        bundle2.putBoolean("com.google.android.gms.cast.EXTRA_CAST_REMOTE_CONTROL_NOTIFICATION_ENABLED", (castOptions == null || castOptions.getCastMediaOptions() == null || !castOptions.getCastMediaOptions().zzbh()) ? false : true);
        GoogleApiClient googleApiClientBuild = new GoogleApiClient.Builder(context).addApi(Cast.API, new Cast.CastOptions.Builder(castDevice, zzcVar).zza(bundle2).build()).addConnectionCallbacks(zzdVar).addOnConnectionFailedListener(zzdVar).build();
        this.zzic = googleApiClientBuild;
        googleApiClientBuild.connect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzh(int i) {
        this.zzib.zzn(i);
        GoogleApiClient googleApiClient = this.zzic;
        if (googleApiClient != null) {
            googleApiClient.disconnect();
            this.zzic = null;
        }
        this.zzie = null;
        RemoteMediaClient remoteMediaClient = this.zzid;
        if (remoteMediaClient != null) {
            remoteMediaClient.zzb((GoogleApiClient) null);
            this.zzid = null;
        }
        this.zzif = null;
    }

    @Override // com.google.android.gms.cast.framework.Session
    public long getSessionRemainingTimeMs() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        RemoteMediaClient remoteMediaClient = this.zzid;
        if (remoteMediaClient == null) {
            return 0L;
        }
        return remoteMediaClient.getStreamDuration() - this.zzid.getApproximateStreamPosition();
    }
}
