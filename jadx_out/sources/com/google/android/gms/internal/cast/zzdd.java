package com.google.android.gms.internal.cast;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes4.dex */
public final class zzdd extends GmsClient<zzdp> {
    private static final zzdw zzbf = new zzdw("CastClientImpl");
    private static final Object zzyr = new Object();
    private static final Object zzys = new Object();
    private final Bundle extras;
    private final Cast.Listener zzak;
    private double zzet;
    private boolean zzeu;
    private final CastDevice zzie;
    private ApplicationMetadata zzxz;
    private final Map<String, Cast.MessageReceivedCallback> zzya;
    private final long zzyb;
    private zzdf zzyc;
    private String zzyd;
    private boolean zzye;
    private boolean zzyf;
    private boolean zzyg;
    private com.google.android.gms.cast.zzad zzyh;
    private int zzyi;
    private int zzyj;
    private final AtomicLong zzyk;
    private String zzyl;
    private String zzym;
    private Bundle zzyn;
    private final Map<Long, BaseImplementation.ResultHolder<Status>> zzyo;
    private BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> zzyp;
    private BaseImplementation.ResultHolder<Status> zzyq;

    public zzdd(Context context, Looper looper, ClientSettings clientSettings, CastDevice castDevice, long j, Cast.Listener listener, Bundle bundle, GoogleApiClient.ConnectionCallbacks connectionCallbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 10, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzie = castDevice;
        this.zzak = listener;
        this.zzyb = j;
        this.extras = bundle;
        this.zzya = new HashMap();
        this.zzyk = new AtomicLong(0L);
        this.zzyo = new HashMap();
        zzet();
    }

    @Override // com.google.android.gms.common.internal.GmsClient, com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final int getMinApkVersion() {
        return 12800000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzet() {
        this.zzyg = false;
        this.zzyi = -1;
        this.zzyj = -1;
        this.zzxz = null;
        this.zzyd = null;
        this.zzet = 0.0d;
        this.zzeu = false;
        this.zzyh = null;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        zzbf.d("in onPostInitHandler; statusCode=%d", Integer.valueOf(i));
        if (i == 0 || i == 1001) {
            this.zzyg = true;
            this.zzye = true;
            this.zzyf = true;
        } else {
            this.zzyg = false;
        }
        if (i == 1001) {
            Bundle bundle2 = new Bundle();
            this.zzyn = bundle2;
            bundle2.putBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING, true);
            i = 0;
        }
        super.onPostInitHandler(i, iBinder, bundle, i2);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.api.Api.Client
    public final void disconnect() {
        zzdw zzdwVar = zzbf;
        zzdwVar.d("disconnect(); ServiceListener=%s, isConnected=%b", this.zzyc, Boolean.valueOf(isConnected()));
        zzdf zzdfVar = this.zzyc;
        this.zzyc = null;
        if (zzdfVar == null || zzdfVar.zzex() == null) {
            zzdwVar.d("already disposed, so short-circuiting", new Object[0]);
            return;
        }
        zzeu();
        try {
            try {
                ((zzdp) getService()).disconnect();
            } finally {
                super.disconnect();
            }
        } catch (RemoteException | IllegalStateException e) {
            zzbf.zza(e, "Error while disconnecting the controller interface: %s", e.getMessage());
            super.disconnect();
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient, com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState
    public final Bundle getConnectionHint() {
        Bundle bundle = this.zzyn;
        if (bundle != null) {
            this.zzyn = null;
            return bundle;
        }
        return super.getConnectionHint();
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getStartServiceAction() {
        return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final String getServiceDescriptor() {
        return "com.google.android.gms.cast.internal.ICastDeviceController";
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final Bundle getGetServiceRequestExtraArgs() {
        Bundle bundle = new Bundle();
        zzbf.d("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", this.zzyl, this.zzym);
        this.zzie.putInBundle(bundle);
        bundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzyb);
        Bundle bundle2 = this.extras;
        if (bundle2 != null) {
            bundle.putAll(bundle2);
        }
        this.zzyc = new zzdf(this);
        bundle.putParcelable(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, new BinderWrapper(this.zzyc.asBinder()));
        String str = this.zzyl;
        if (str != null) {
            bundle.putString("last_application_id", str);
            String str2 = this.zzym;
            if (str2 != null) {
                bundle.putString("last_session_id", str2);
            }
        }
        return bundle;
    }

    public final void zza(String str, String str2, BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException, IllegalArgumentException {
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("The message payload cannot be null or empty");
        }
        if (str2.length() > 524288) {
            zzbf.w("Message send failed. Message exceeds maximum size", new Object[0]);
            throw new IllegalArgumentException("Message exceeds maximum size");
        }
        zzdk.zzp(str);
        long jIncrementAndGet = this.zzyk.incrementAndGet();
        try {
            this.zzyo.put(Long.valueOf(jIncrementAndGet), resultHolder);
            zzdp zzdpVar = (zzdp) getService();
            if (zzev()) {
                zzdpVar.zza(str, str2, jIncrementAndGet);
            } else {
                zzb(jIncrementAndGet, CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
            }
        } catch (Throwable th) {
            this.zzyo.remove(Long.valueOf(jIncrementAndGet));
            throw th;
        }
    }

    public final void zza(String str, LaunchOptions launchOptions, BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) throws IllegalStateException, RemoteException {
        zza(resultHolder);
        zzdp zzdpVar = (zzdp) getService();
        if (zzev()) {
            zzdpVar.zzb(str, launchOptions);
        } else {
            zzq(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, String str2, com.google.android.gms.cast.zzag zzagVar, BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) throws IllegalStateException, RemoteException {
        zza(resultHolder);
        if (zzagVar == null) {
            zzagVar = new com.google.android.gms.cast.zzag();
        }
        zzdp zzdpVar = (zzdp) getService();
        if (zzev()) {
            zzdpVar.zza(str, str2, zzagVar);
        } else {
            zzq(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    private final void zza(BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder) {
        synchronized (zzyr) {
            BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder2 = this.zzyp;
            if (resultHolder2 != null) {
                resultHolder2.setResult(new zzde(new Status(2002)));
            }
            this.zzyp = resultHolder;
        }
    }

    public final void zzb(BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException {
        zzc(resultHolder);
        zzdp zzdpVar = (zzdp) getService();
        if (zzev()) {
            zzdpVar.zzfa();
        } else {
            zzr(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    public final void zza(String str, BaseImplementation.ResultHolder<Status> resultHolder) throws IllegalStateException, RemoteException {
        zzc(resultHolder);
        zzdp zzdpVar = (zzdp) getService();
        if (zzev()) {
            zzdpVar.zzj(str);
        } else {
            zzr(CastStatusCodes.DEVICE_CONNECTION_SUSPENDED);
        }
    }

    private final void zzc(BaseImplementation.ResultHolder<Status> resultHolder) {
        synchronized (zzys) {
            if (this.zzyq != null) {
                resultHolder.setResult(new Status(2001));
            } else {
                this.zzyq = resultHolder;
            }
        }
    }

    public final void requestStatus() throws IllegalStateException, RemoteException {
        zzdp zzdpVar = (zzdp) getService();
        if (zzev()) {
            zzdpVar.requestStatus();
        }
    }

    public final void setVolume(double d) throws IllegalStateException, RemoteException, IllegalArgumentException {
        if (Double.isInfinite(d) || Double.isNaN(d)) {
            StringBuilder sb = new StringBuilder(41);
            sb.append("Volume cannot be ");
            sb.append(d);
            throw new IllegalArgumentException(sb.toString());
        }
        zzdp zzdpVar = (zzdp) getService();
        if (zzev()) {
            zzdpVar.zza(d, this.zzet, this.zzeu);
        }
    }

    public final void setMute(boolean z) throws IllegalStateException, RemoteException {
        zzdp zzdpVar = (zzdp) getService();
        if (zzev()) {
            zzdpVar.zza(z, this.zzet, this.zzeu);
        }
    }

    public final double getVolume() throws IllegalStateException {
        checkConnected();
        return this.zzet;
    }

    public final boolean isMute() throws IllegalStateException {
        checkConnected();
        return this.zzeu;
    }

    public final int getActiveInputState() throws IllegalStateException {
        checkConnected();
        return this.zzyi;
    }

    public final int getStandbyState() throws IllegalStateException {
        checkConnected();
        return this.zzyj;
    }

    public final void setMessageReceivedCallbacks(String str, Cast.MessageReceivedCallback messageReceivedCallback) throws IllegalStateException, RemoteException, IllegalArgumentException {
        zzdk.zzp(str);
        removeMessageReceivedCallbacks(str);
        if (messageReceivedCallback != null) {
            synchronized (this.zzya) {
                this.zzya.put(str, messageReceivedCallback);
            }
            zzdp zzdpVar = (zzdp) getService();
            if (zzev()) {
                zzdpVar.zzs(str);
            }
        }
    }

    public final void removeMessageReceivedCallbacks(String str) throws RemoteException, IllegalArgumentException {
        Cast.MessageReceivedCallback messageReceivedCallbackRemove;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Channel namespace cannot be null or empty");
        }
        synchronized (this.zzya) {
            messageReceivedCallbackRemove = this.zzya.remove(str);
        }
        if (messageReceivedCallbackRemove != null) {
            try {
                ((zzdp) getService()).zzt(str);
            } catch (IllegalStateException e) {
                zzbf.zza(e, "Error unregistering namespace (%s): %s", str, e.getMessage());
            }
        }
    }

    public final ApplicationMetadata getApplicationMetadata() throws IllegalStateException {
        checkConnected();
        return this.zzxz;
    }

    public final String getApplicationStatus() throws IllegalStateException {
        checkConnected();
        return this.zzyd;
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    public final void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        zzeu();
    }

    private final void zzeu() {
        zzbf.d("removing all MessageReceivedCallbacks", new Object[0]);
        synchronized (this.zzya) {
            this.zzya.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzdl zzdlVar) {
        boolean z;
        boolean z2;
        boolean z3;
        ApplicationMetadata applicationMetadata = zzdlVar.getApplicationMetadata();
        if (!zzdk.zza(applicationMetadata, this.zzxz)) {
            this.zzxz = applicationMetadata;
            this.zzak.onApplicationMetadataChanged(applicationMetadata);
        }
        double volume = zzdlVar.getVolume();
        if (Double.isNaN(volume) || Math.abs(volume - this.zzet) <= 1.0E-7d) {
            z = false;
        } else {
            this.zzet = volume;
            z = true;
        }
        boolean zZzey = zzdlVar.zzey();
        if (zZzey != this.zzeu) {
            this.zzeu = zZzey;
            z = true;
        }
        zzdw zzdwVar = zzbf;
        zzdwVar.d("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzyf));
        Cast.Listener listener = this.zzak;
        if (listener != null && (z || this.zzyf)) {
            listener.onVolumeChanged();
        }
        int activeInputState = zzdlVar.getActiveInputState();
        if (activeInputState != this.zzyi) {
            this.zzyi = activeInputState;
            z2 = true;
        } else {
            z2 = false;
        }
        zzdwVar.d("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z2), Boolean.valueOf(this.zzyf));
        Cast.Listener listener2 = this.zzak;
        if (listener2 != null && (z2 || this.zzyf)) {
            listener2.onActiveInputStateChanged(this.zzyi);
        }
        int standbyState = zzdlVar.getStandbyState();
        if (standbyState != this.zzyj) {
            this.zzyj = standbyState;
            z3 = true;
        } else {
            z3 = false;
        }
        zzdwVar.d("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", Boolean.valueOf(z3), Boolean.valueOf(this.zzyf));
        Cast.Listener listener3 = this.zzak;
        if (listener3 != null && (z3 || this.zzyf)) {
            listener3.onStandbyStateChanged(this.zzyj);
        }
        if (!zzdk.zza(this.zzyh, zzdlVar.zzez())) {
            this.zzyh = zzdlVar.zzez();
        }
        this.zzyf = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zza(zzct zzctVar) {
        boolean z;
        String strZzep = zzctVar.zzep();
        if (zzdk.zza(strZzep, this.zzyd)) {
            z = false;
        } else {
            this.zzyd = strZzep;
            z = true;
        }
        zzbf.d("hasChanged=%b, mFirstApplicationStatusUpdate=%b", Boolean.valueOf(z), Boolean.valueOf(this.zzye));
        Cast.Listener listener = this.zzak;
        if (listener != null && (z || this.zzye)) {
            listener.onApplicationStatusChanged();
        }
        this.zzye = false;
    }

    private final boolean zzev() {
        zzdf zzdfVar;
        return (!this.zzyg || (zzdfVar = this.zzyc) == null || zzdfVar.isDisposed()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzb(long j, int i) {
        BaseImplementation.ResultHolder<Status> resultHolderRemove;
        synchronized (this.zzyo) {
            resultHolderRemove = this.zzyo.remove(Long.valueOf(j));
        }
        if (resultHolderRemove != null) {
            resultHolderRemove.setResult(new Status(i));
        }
    }

    public final void zzq(int i) {
        synchronized (zzyr) {
            BaseImplementation.ResultHolder<Cast.ApplicationConnectionResult> resultHolder = this.zzyp;
            if (resultHolder != null) {
                resultHolder.setResult(new zzde(new Status(i)));
                this.zzyp = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzr(int i) {
        synchronized (zzys) {
            BaseImplementation.ResultHolder<Status> resultHolder = this.zzyq;
            if (resultHolder != null) {
                resultHolder.setResult(new Status(i));
                this.zzyq = null;
            }
        }
    }

    @Override // com.google.android.gms.common.internal.BaseGmsClient
    protected final /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.cast.internal.ICastDeviceController");
        if (iInterfaceQueryLocalInterface instanceof zzdp) {
            return (zzdp) iInterfaceQueryLocalInterface;
        }
        return new zzdq(iBinder);
    }

    static /* synthetic */ BaseImplementation.ResultHolder zza(zzdd zzddVar, BaseImplementation.ResultHolder resultHolder) {
        zzddVar.zzyp = null;
        return null;
    }
}
