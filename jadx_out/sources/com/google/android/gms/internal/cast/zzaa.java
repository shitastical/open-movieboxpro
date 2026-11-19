package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.google.android.gms.cast.framework.media.ImageHints;

/* JADX INFO: loaded from: classes4.dex */
public final class zzaa implements zzae {
    private final Context zzhe;
    private final ImageHints zzmb;
    private Uri zzqs;
    private zzac zzqt;
    private zzaf zzqu;
    private Bitmap zzqv;
    private boolean zzqw;
    private zzab zzqx;

    public zzaa(Context context) {
        this(context, new ImageHints(-1, 0, 0));
    }

    public zzaa(Context context, ImageHints imageHints) {
        this.zzhe = context;
        this.zzmb = imageHints;
        this.zzqu = new zzaf();
        reset();
    }

    public final void zza(zzab zzabVar) {
        this.zzqx = zzabVar;
    }

    public final boolean zza(Uri uri) {
        if (uri == null) {
            reset();
            return true;
        }
        if (uri.equals(this.zzqs)) {
            return this.zzqw;
        }
        reset();
        this.zzqs = uri;
        if (this.zzmb.getWidthInPixels() == 0 || this.zzmb.getHeightInPixels() == 0) {
            this.zzqt = new zzac(this.zzhe, this);
        } else {
            this.zzqt = new zzac(this.zzhe, this.zzmb.getWidthInPixels(), this.zzmb.getHeightInPixels(), false, this);
        }
        this.zzqt.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, this.zzqs);
        return false;
    }

    public final void clear() {
        reset();
        this.zzqx = null;
    }

    @Override // com.google.android.gms.internal.cast.zzae
    public final void onPostExecute(Bitmap bitmap) {
        this.zzqv = bitmap;
        this.zzqw = true;
        zzab zzabVar = this.zzqx;
        if (zzabVar != null) {
            zzabVar.zza(bitmap);
        }
        this.zzqt = null;
    }

    private final void reset() {
        zzac zzacVar = this.zzqt;
        if (zzacVar != null) {
            zzacVar.cancel(true);
            this.zzqt = null;
        }
        this.zzqs = null;
        this.zzqv = null;
        this.zzqw = false;
    }
}
