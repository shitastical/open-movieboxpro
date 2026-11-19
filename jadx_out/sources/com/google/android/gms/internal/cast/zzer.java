package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import com.dl7.player.utils.NetWorkUtils;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;

/* JADX INFO: loaded from: classes4.dex */
@Deprecated
public final class zzer extends GmsClient<zzev> implements IBinder.DeathRecipient {
    private static final zzdw zzbf = new zzdw("CastRemoteDisplayClientImpl");
    private CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzabd;
    private Bundle zzabe;
    private CastDevice zzaj;

    public zzer(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, Bundle bundle, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 83, clientSettings, connectionCallbacks, onConnectionFailedListener);
        zzbf.d("instance created", new Object[0]);
        this.zzabd = castRemoteDisplaySessionCallbacks;
        this.zzaj = castDevice;
        this.zzabe = bundle;
    }

    @Override // android.os.IBinder.DeathRecipient
    public final void binderDied() {
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12451000;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        zzbf.d(NetWorkUtils.NETWORK_TYPE_DISCONNECT, new Object[0]);
        this.zzabd = null;
        this.zzaj = null;
        try {
            ((zzev) getService()).disconnect();
        } catch (RemoteException | IllegalStateException unused) {
        } finally {
            super.disconnect();
        }
    }

    public final void zza(zzet zzetVar, zzex zzexVar, String str) throws RemoteException {
        zzbf.d("startRemoteDisplay", new Object[0]);
        ((zzev) getService()).zza(zzetVar, new zzes(this, zzexVar), this.zzaj.getDeviceId(), str, this.zzabe);
    }

    public final void zza(zzet zzetVar) throws RemoteException {
        zzbf.d("stopRemoteDisplay", new Object[0]);
        ((zzev) getService()).zza(zzetVar);
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.cast.remote_display.service.START";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        if (iInterfaceQueryLocalInterface instanceof zzev) {
            return (zzev) iInterfaceQueryLocalInterface;
        }
        return new zzew(iBinder);
    }
}
