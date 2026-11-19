package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.RemoteException;
import android.support.v4.media.session.PlaybackStateCompat;
import com.dl7.player.media.MediaPlayerParams;

/* JADX INFO: loaded from: classes4.dex */
public final class zzac extends AsyncTask<Uri, Long, Bitmap> {
    private static final zzdw zzbf = new zzdw("FetchBitmapTask");
    private final zzah zzqy;
    private final zzae zzqz;

    public zzac(Context context, zzae zzaeVar) {
        this(context, 0, 0, false, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, MediaPlayerParams.STATE_PREPARED, 10000, zzaeVar);
    }

    public zzac(Context context, int i, int i2, boolean z, zzae zzaeVar) {
        this(context, i, i2, false, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, MediaPlayerParams.STATE_PREPARED, 10000, zzaeVar);
    }

    private zzac(Context context, int i, int i2, boolean z, long j, int i3, int i4, int i5, zzae zzaeVar) {
        this.zzqy = zze.zza(context.getApplicationContext(), this, new zzag(this), i, i2, z, PlaybackStateCompat.ACTION_SET_SHUFFLE_MODE, 5, MediaPlayerParams.STATE_PREPARED, 10000);
        this.zzqz = zzaeVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // android.os.AsyncTask
    /* JADX INFO: renamed from: zza, reason: merged with bridge method [inline-methods] */
    public final Bitmap doInBackground(Uri... uriArr) {
        Uri uri;
        if (uriArr.length == 1 && (uri = uriArr[0]) != null) {
            try {
                return this.zzqy.zzb(uri);
            } catch (RemoteException e) {
                zzbf.zza(e, "Unable to call %s on %s.", "doFetch", "zzah");
            }
        }
        return null;
    }

    @Override // android.os.AsyncTask
    protected final /* synthetic */ void onPostExecute(Bitmap bitmap) {
        Bitmap bitmap2 = bitmap;
        zzae zzaeVar = this.zzqz;
        if (zzaeVar != null) {
            zzaeVar.onPostExecute(bitmap2);
        }
    }
}
