package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import androidx.mediarouter.media.MediaRouteSelector;
import androidx.mediarouter.media.MediaRouter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class zzw extends zzk {
    private final MediaRouter zzcc;
    private final Map<MediaRouteSelector, Set<MediaRouter.Callback>> zzjq = new HashMap();

    public zzw(MediaRouter mediaRouter) {
        this.zzcc = mediaRouter;
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final int zzs() {
        return 12451009;
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final void zza(Bundle bundle, zzl zzlVar) {
        MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        if (!this.zzjq.containsKey(mediaRouteSelectorFromBundle)) {
            this.zzjq.put(mediaRouteSelectorFromBundle, new HashSet());
        }
        this.zzjq.get(mediaRouteSelectorFromBundle).add(new zzv(zzlVar));
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final void zzau() {
        Iterator<Set<MediaRouter.Callback>> it = this.zzjq.values().iterator();
        while (it.hasNext()) {
            Iterator<MediaRouter.Callback> it2 = it.next().iterator();
            while (it2.hasNext()) {
                this.zzcc.removeCallback(it2.next());
            }
        }
        this.zzjq.clear();
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final void zza(Bundle bundle, int i) {
        MediaRouteSelector mediaRouteSelectorFromBundle = MediaRouteSelector.fromBundle(bundle);
        Iterator<MediaRouter.Callback> it = this.zzjq.get(mediaRouteSelectorFromBundle).iterator();
        while (it.hasNext()) {
            this.zzcc.addCallback(mediaRouteSelectorFromBundle, it.next(), i);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final void zzd(Bundle bundle) {
        Iterator<MediaRouter.Callback> it = this.zzjq.get(MediaRouteSelector.fromBundle(bundle)).iterator();
        while (it.hasNext()) {
            this.zzcc.removeCallback(it.next());
        }
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final boolean zzb(Bundle bundle, int i) {
        return this.zzcc.isRouteAvailable(MediaRouteSelector.fromBundle(bundle), i);
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final void zzl(String str) {
        for (MediaRouter.RouteInfo routeInfo : this.zzcc.getRoutes()) {
            if (routeInfo.getId().equals(str)) {
                this.zzcc.selectRoute(routeInfo);
                return;
            }
        }
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final void zzar() {
        MediaRouter mediaRouter = this.zzcc;
        mediaRouter.selectRoute(mediaRouter.getDefaultRoute());
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final boolean zzas() {
        return this.zzcc.getSelectedRoute().getId().equals(this.zzcc.getDefaultRoute().getId());
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final Bundle zzm(String str) {
        for (MediaRouter.RouteInfo routeInfo : this.zzcc.getRoutes()) {
            if (routeInfo.getId().equals(str)) {
                return routeInfo.getExtras();
            }
        }
        return null;
    }

    @Override // com.google.android.gms.internal.cast.zzj
    public final String zzat() {
        return this.zzcc.getSelectedRoute().getId();
    }

    public final void setMediaSessionCompat(MediaSessionCompat mediaSessionCompat) {
        this.zzcc.setMediaSessionCompat(mediaSessionCompat);
    }
}
