package com.google.android.gms.cast.framework;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.view.MenuItemCompat;
import androidx.mediarouter.app.MediaRouteActionProvider;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdw;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public final class CastButtonFactory {
    private static final zzdw zzbf = new zzdw("CastButtonFactory");
    private static final List<WeakReference<MenuItem>> zzhb = new ArrayList();
    private static final List<WeakReference<MediaRouteButton>> zzhc = new ArrayList();

    private CastButtonFactory() {
    }

    public static MenuItem setUpMediaRouteButton(Context context, Menu menu, int i) {
        return zza(context, menu, i, null);
    }

    private static MenuItem zza(Context context, Menu menu, int i, MediaRouteDialogFactory mediaRouteDialogFactory) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(menu);
        MenuItem menuItemFindItem = menu.findItem(i);
        if (menuItemFindItem == null) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "menu doesn't contain a menu item whose ID is %d.", Integer.valueOf(i)));
        }
        try {
            zza(context, menuItemFindItem, (MediaRouteDialogFactory) null);
            zzhb.add(new WeakReference<>(menuItemFindItem));
            return menuItemFindItem;
        } catch (IllegalArgumentException unused) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "menu item with ID %d doesn't have a MediaRouteActionProvider.", Integer.valueOf(i)));
        }
    }

    public static void setUpMediaRouteButton(Context context, MediaRouteButton mediaRouteButton) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (mediaRouteButton != null) {
            zza(context, mediaRouteButton, (MediaRouteDialogFactory) null);
            zzhc.add(new WeakReference<>(mediaRouteButton));
        }
    }

    public static void zza(Context context) {
        for (WeakReference<MenuItem> weakReference : zzhb) {
            try {
                if (weakReference.get() != null) {
                    zza(context, weakReference.get(), (MediaRouteDialogFactory) null);
                }
            } catch (IllegalArgumentException e) {
                zzbf.w("Unexpected exception when refreshing MediaRouteSelectors for Cast buttons", e);
            }
        }
        for (WeakReference<MediaRouteButton> weakReference2 : zzhc) {
            if (weakReference2.get() != null) {
                zza(context, weakReference2.get(), (MediaRouteDialogFactory) null);
            }
        }
    }

    private static void zza(Context context, MenuItem menuItem, MediaRouteDialogFactory mediaRouteDialogFactory) throws IllegalArgumentException {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaRouteActionProvider mediaRouteActionProvider = (MediaRouteActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if (mediaRouteActionProvider == null) {
            throw new IllegalArgumentException();
        }
        CastContext castContextZzb = CastContext.zzb(context);
        if (castContextZzb != null) {
            mediaRouteActionProvider.setRouteSelector(castContextZzb.getMergedSelector());
        }
    }

    private static void zza(Context context, MediaRouteButton mediaRouteButton, MediaRouteDialogFactory mediaRouteDialogFactory) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        CastContext castContextZzb = CastContext.zzb(context);
        if (castContextZzb != null) {
            mediaRouteButton.setRouteSelector(castContextZzb.getMergedSelector());
        }
    }
}
