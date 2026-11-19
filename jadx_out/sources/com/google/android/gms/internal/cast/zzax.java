package com.google.android.gms.internal.cast;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.cast.MediaInfo;
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
public final class zzax extends UIController {
    private final ImagePicker zzlp;
    private final zzaa zzma;
    private final ImageHints zzmb;
    private final ImageView zzsi;
    private final Bitmap zzsl;
    private final View zzsm;

    public zzax(ImageView imageView, Context context, ImageHints imageHints, int i, View view) {
        this.zzsi = imageView;
        this.zzmb = imageHints;
        this.zzsl = i != 0 ? BitmapFactory.decodeResource(context.getResources(), i) : null;
        this.zzsm = view;
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
        this.zzma.zza(new zzay(this));
        zzdl();
        zzdk();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        this.zzma.clear();
        zzdl();
        super.onSessionEnded();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzdk();
    }

    private final void zzdk() {
        Uri imageUri;
        WebImage webImageOnPickImage;
        RemoteMediaClient remoteMediaClient = getRemoteMediaClient();
        if (remoteMediaClient == null || !remoteMediaClient.hasMediaSession()) {
            zzdl();
            return;
        }
        MediaInfo mediaInfo = remoteMediaClient.getMediaInfo();
        if (mediaInfo == null) {
            imageUri = null;
        } else {
            ImagePicker imagePicker = this.zzlp;
            if (imagePicker != null && (webImageOnPickImage = imagePicker.onPickImage(mediaInfo.getMetadata(), this.zzmb)) != null && webImageOnPickImage.getUrl() != null) {
                imageUri = webImageOnPickImage.getUrl();
            } else {
                imageUri = MediaUtils.getImageUri(mediaInfo, 0);
            }
        }
        if (imageUri == null) {
            zzdl();
        } else {
            this.zzma.zza(imageUri);
        }
    }

    private final void zzdl() {
        View view = this.zzsm;
        if (view != null) {
            view.setVisibility(0);
            this.zzsi.setVisibility(4);
        }
        Bitmap bitmap = this.zzsl;
        if (bitmap != null) {
            this.zzsi.setImageBitmap(bitmap);
        }
    }
}
