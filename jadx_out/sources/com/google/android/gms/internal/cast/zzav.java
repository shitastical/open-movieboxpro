package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaQueueItem;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.ImagePicker;
import com.google.android.gms.cast.framework.media.MediaUtils;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.uicontroller.UIController;
import com.google.android.gms.common.images.WebImage;

/* JADX INFO: loaded from: classes4.dex */
public final class zzav extends UIController {
    private final ImagePicker zzlp;
    private final zzaa zzma;
    private final ImageHints zzmb;
    private final ImageView zzsi;
    private final Bitmap zzsj;

    public zzav(ImageView imageView, Context context, ImageHints imageHints, int i) {
        this.zzsi = imageView;
        this.zzmb = imageHints;
        this.zzsj = BitmapFactory.decodeResource(context.getResources(), i);
        CastContext castContextZzb = CastContext.zzb(context);
        if (castContextZzb != null) {
            CastMediaOptions castMediaOptions = castContextZzb.getCastOptions().getCastMediaOptions();
            this.zzlp = castMediaOptions != null ? castMediaOptions.getImagePicker() : null;
        } else {
            this.zzlp = null;
        }
        this.zzma = new zzaa(context.getApplicationContext());
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession castSession) {
        super.onSessionConnected(castSession);
        this.zzma.zza(new zzaw(this));
        this.zzsi.setImageBitmap(this.zzsj);
        zzdk();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zzma.clear();
        this.zzsi.setImageBitmap(this.zzsj);
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzdk();
    }

    private final void zzdk() {
        MediaInfo media;
        WebImage webImageOnPickImage;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            this.zzsi.setImageBitmap(this.zzsj);
            return;
        }
        MediaQueueItem preloadedItem = remoteMediaClient.getPreloadedItem();
        Uri imageUri = null;
        if (preloadedItem != null && (media = preloadedItem.getMedia()) != null) {
            ImagePicker imagePicker = this.zzlp;
            if (imagePicker != null && (webImageOnPickImage = imagePicker.onPickImage(media.getMetadata(), this.zzmb)) != null && webImageOnPickImage.getUrl() != null) {
                imageUri = webImageOnPickImage.getUrl();
            } else {
                imageUri = MediaUtils.getImageUri(media, 0);
            }
        }
        if (imageUri == null) {
            this.zzsi.setImageBitmap(this.zzsj);
        } else {
            this.zzma.zza(imageUri);
        }
    }
}
