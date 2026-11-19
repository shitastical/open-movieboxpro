package com.google.android.gms.internal.cast;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import com.dl7.player.media.MediaPlayerParams;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class zze {
    private static final zzdw zzbf = new zzdw("CastDynamiteModule");

    public static com.google.android.gms.cast.framework.zzj zza(Context context, CastOptions castOptions, zzj zzjVar, Map<String, IBinder> map) {
        try {
            return zzf(context).zza(ObjectWrapper.wrap(context.getApplicationContext()), castOptions, zzjVar, map);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "newCastContextImpl", "zzh");
            return null;
        }
    }

    public static com.google.android.gms.cast.framework.zzt zza(Context context, String str, String str2, com.google.android.gms.cast.framework.zzab zzabVar) {
        try {
            return zzf(context).zza(str, str2, zzabVar);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "newSessionImpl", "zzh");
            return null;
        }
    }

    public static com.google.android.gms.cast.framework.zzl zza(Context context, CastOptions castOptions, IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.zzh zzhVar) {
        try {
            return zzf(context).zza(castOptions, iObjectWrapper, zzhVar);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "newCastSessionImpl", "zzh");
            return null;
        }
    }

    public static com.google.android.gms.cast.framework.zzr zza(Service service, IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        try {
            return zzf(service.getApplicationContext()).zza(ObjectWrapper.wrap(service), iObjectWrapper, iObjectWrapper2);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "newReconnectionServiceImpl", "zzh");
            return null;
        }
    }

    public static zzah zza(Context context, AsyncTask<Uri, Long, Bitmap> asyncTask, zzaj zzajVar, int i, int i2, boolean z, long j, int i3, int i4, int i5) {
        try {
            return zzf(context.getApplicationContext()).zza(ObjectWrapper.wrap(asyncTask), zzajVar, i, i2, z, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, MediaPlayerParams.STATE_PREPARED, 10000);
        } catch (RemoteException e) {
            zzbf.zza(e, "Unable to call %s on %s.", "newFetchBitmapTaskImpl", "zzh");
            return null;
        }
    }

    private static zzh zzf(Context context) {
        try {
            IBinder iBinderInstantiate = DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, "com.google.android.gms.cast.framework.dynamite").instantiate("com.google.android.gms.cast.framework.internal.CastDynamiteModuleImpl");
            if (iBinderInstantiate == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinderInstantiate.queryLocalInterface("com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
            if (iInterfaceQueryLocalInterface instanceof zzh) {
                return (zzh) iInterfaceQueryLocalInterface;
            }
            return new zzi(iBinderInstantiate);
        } catch (DynamiteModule.LoadingException e) {
            throw new RuntimeException(e);
        }
    }
}
