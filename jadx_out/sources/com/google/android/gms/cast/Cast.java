package com.google.android.gms.cast;

import android.os.Bundle;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzcv;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzdv;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public final class Cast {
    public static final int ACTIVE_INPUT_STATE_NO = 0;
    public static final int ACTIVE_INPUT_STATE_UNKNOWN = -1;
    public static final int ACTIVE_INPUT_STATE_YES = 1;
    public static final Api<CastOptions> API;
    public static final CastApi CastApi;
    public static final String EXTRA_APP_NO_LONGER_RUNNING = "com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING";
    public static final int MAX_MESSAGE_LENGTH = 65536;
    public static final int MAX_NAMESPACE_LENGTH = 128;
    public static final int STANDBY_STATE_NO = 0;
    public static final int STANDBY_STATE_UNKNOWN = -1;
    public static final int STANDBY_STATE_YES = 1;
    private static final Api.AbstractClientBuilder<zzdd, CastOptions> zzad;

    public interface ApplicationConnectionResult extends Result {
        ApplicationMetadata getApplicationMetadata();

        String getApplicationStatus();

        String getSessionId();

        boolean getWasLaunched();
    }

    @Deprecated
    public interface CastApi {

        public static final class zza implements CastApi {
            @Override // com.google.android.gms.cast.Cast.CastApi
            public final void requestStatus(GoogleApiClient googleApiClient) throws IllegalStateException, IOException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).requestStatus();
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2) {
                return googleApiClient.execute(new zzf(this, googleApiClient, str, str2));
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str) {
                return googleApiClient.execute(new zzg(this, googleApiClient, str));
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions) {
                return googleApiClient.execute(new zzh(this, googleApiClient, str, launchOptions));
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            @Deprecated
            public final PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z) {
                return launchApplication(googleApiClient, str, new LaunchOptions.Builder().setRelaunchIfRunning(z).build());
            }

            private final PendingResult<ApplicationConnectionResult> zza(GoogleApiClient googleApiClient, String str, String str2, zzag zzagVar) {
                return googleApiClient.execute(new zzi(this, googleApiClient, str, str2, null));
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2) {
                return zza(googleApiClient, str, str2, null);
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str) {
                return zza(googleApiClient, str, null, null);
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient) {
                return zza(googleApiClient, null, null, null);
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient) {
                return googleApiClient.execute(new zzj(this, googleApiClient));
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<Status> stopApplication(GoogleApiClient googleApiClient) {
                return googleApiClient.execute(new zzk(this, googleApiClient));
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str) {
                return googleApiClient.execute(new zzl(this, googleApiClient, str));
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final void setVolume(GoogleApiClient googleApiClient, double d) throws IllegalStateException, IOException, IllegalArgumentException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).setVolume(d);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getVolume();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final void setMute(GoogleApiClient googleApiClient, boolean z) throws IllegalStateException, IOException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).setMute(z);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).isMute();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final int getActiveInputState(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getActiveInputState();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final int getStandbyState(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getStandbyState();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getApplicationMetadata();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException {
                return ((zzdd) googleApiClient.getClient(zzdv.zzzf)).getApplicationStatus();
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).setMessageReceivedCallbacks(str, messageReceivedCallback);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }

            @Override // com.google.android.gms.cast.Cast.CastApi
            public final void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException {
                try {
                    ((zzdd) googleApiClient.getClient(zzdv.zzzf)).removeMessageReceivedCallbacks(str);
                } catch (RemoteException unused) {
                    throw new IOException("service error");
                }
            }
        }

        int getActiveInputState(GoogleApiClient googleApiClient) throws IllegalStateException;

        ApplicationMetadata getApplicationMetadata(GoogleApiClient googleApiClient) throws IllegalStateException;

        String getApplicationStatus(GoogleApiClient googleApiClient) throws IllegalStateException;

        int getStandbyState(GoogleApiClient googleApiClient) throws IllegalStateException;

        double getVolume(GoogleApiClient googleApiClient) throws IllegalStateException;

        boolean isMute(GoogleApiClient googleApiClient) throws IllegalStateException;

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> joinApplication(GoogleApiClient googleApiClient, String str, String str2);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str);

        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, LaunchOptions launchOptions);

        @Deprecated
        PendingResult<ApplicationConnectionResult> launchApplication(GoogleApiClient googleApiClient, String str, boolean z);

        PendingResult<Status> leaveApplication(GoogleApiClient googleApiClient);

        void removeMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str) throws IOException, IllegalArgumentException;

        void requestStatus(GoogleApiClient googleApiClient) throws IllegalStateException, IOException;

        PendingResult<Status> sendMessage(GoogleApiClient googleApiClient, String str, String str2);

        void setMessageReceivedCallbacks(GoogleApiClient googleApiClient, String str, MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, IOException;

        void setMute(GoogleApiClient googleApiClient, boolean z) throws IllegalStateException, IOException;

        void setVolume(GoogleApiClient googleApiClient, double d) throws IllegalStateException, IOException, IllegalArgumentException;

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient);

        PendingResult<Status> stopApplication(GoogleApiClient googleApiClient, String str);
    }

    public static class Listener {
        public void onActiveInputStateChanged(int i) {
        }

        public void onApplicationDisconnected(int i) {
        }

        public void onApplicationMetadataChanged(ApplicationMetadata applicationMetadata) {
        }

        public void onApplicationStatusChanged() {
        }

        public void onStandbyStateChanged(int i) {
        }

        public void onVolumeChanged() {
        }
    }

    public interface MessageReceivedCallback {
        void onMessageReceived(CastDevice castDevice, String str, String str2);
    }

    private Cast() {
    }

    static {
        zze zzeVar = new zze();
        zzad = zzeVar;
        API = new Api<>("Cast.API", zzeVar, zzdv.zzzf);
        CastApi = new CastApi.zza();
    }

    static abstract class zza extends zzcv<ApplicationConnectionResult> {
        public zza(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        @Override // com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl
        /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
        public void doExecute(zzdd zzddVar) throws RemoteException {
        }

        @Override // com.google.android.gms.common.api.internal.BasePendingResult
        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzm(this, status);
        }
    }

    public static final class CastOptions implements Api.ApiOptions.HasOptions {
        final Bundle extras;
        final CastDevice zzaj;
        final Listener zzak;
        private final int zzal;

        private CastOptions(Builder builder) {
            this.zzaj = builder.zzaj;
            this.zzak = builder.zzak;
            this.zzal = builder.zzal;
            this.extras = builder.extras;
        }

        @Deprecated
        public static Builder builder(CastDevice castDevice, Listener listener) {
            return new Builder(castDevice, listener);
        }

        public static final class Builder {
            private Bundle extras;
            CastDevice zzaj;
            Listener zzak;
            private int zzal;

            public Builder(CastDevice castDevice, Listener listener) {
                Preconditions.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                Preconditions.checkNotNull(listener, "CastListener parameter cannot be null");
                this.zzaj = castDevice;
                this.zzak = listener;
                this.zzal = 0;
            }

            public final Builder setVerboseLoggingEnabled(boolean z) {
                if (z) {
                    this.zzal |= 1;
                } else {
                    this.zzal &= -2;
                }
                return this;
            }

            public final Builder zza(Bundle bundle) {
                this.extras = bundle;
                return this;
            }

            public final CastOptions build() {
                return new CastOptions(this, null);
            }
        }

        /* synthetic */ CastOptions(Builder builder, zze zzeVar) {
            this(builder);
        }
    }
}
