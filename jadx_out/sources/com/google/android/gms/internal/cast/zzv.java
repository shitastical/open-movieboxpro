package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import androidx.mediarouter.media.MediaRouter;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: loaded from: classes4.dex */
public final class zzv extends MediaRouter.Callback {
    private static final zzdw zzbf = new zzdw("MediaRouterCallback");
    private final zzl zzjp;

    public zzv(zzl zzlVar) {
        this.zzjp = (zzl) Preconditions.checkNotNull(zzlVar);
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteSelected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzjp.zzd(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onRouteSelected", "zzl");
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo, int i) {
        try {
            this.zzjp.zza(routeInfo.getId(), routeInfo.getExtras(), i);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onRouteUnselected", "zzl");
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteAdded(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzjp.zza(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onRouteAdded", "zzl");
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteChanged(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzjp.zzb(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onRouteChanged", "zzl");
        }
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteRemoved(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        try {
            this.zzjp.zzc(routeInfo.getId(), routeInfo.getExtras());
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "onRouteRemoved", "zzl");
        }
    }
}
