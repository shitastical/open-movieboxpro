package com.google.android.gms.cast;

import androidx.mediarouter.media.MediaRouter;

/* JADX INFO: loaded from: classes4.dex */
final class zzu extends MediaRouter.Callback {
    private final /* synthetic */ CastRemoteDisplayLocalService zzci;

    zzu(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zzci = castRemoteDisplayLocalService;
    }

    @Override // androidx.mediarouter.media.MediaRouter.Callback
    public final void onRouteUnselected(MediaRouter mediaRouter, MediaRouter.RouteInfo routeInfo) {
        this.zzci.zzb("onRouteUnselected");
        if (this.zzci.zzby == null) {
            this.zzci.zzb("onRouteUnselected, no device was selected");
        } else if (CastDevice.getFromBundle(routeInfo.getExtras()).getDeviceId().equals(this.zzci.zzby.getDeviceId())) {
            CastRemoteDisplayLocalService.stopService();
        } else {
            this.zzci.zzb("onRouteUnselected, device does not match");
        }
    }
}
