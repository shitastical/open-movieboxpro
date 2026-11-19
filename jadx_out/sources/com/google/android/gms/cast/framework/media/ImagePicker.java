package com.google.android.gms.cast.framework.media;

import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* JADX INFO: loaded from: classes4.dex */
public class ImagePicker {
    public static final int IMAGE_TYPE_EXPANDED_CONTROLLER_BACKGROUND = 4;
    public static final int IMAGE_TYPE_LOCK_SCREEN_BACKGROUND = 3;
    public static final int IMAGE_TYPE_MEDIA_ROUTE_CONTROLLER_DIALOG_BACKGROUND = 0;
    public static final int IMAGE_TYPE_MINI_CONTROLLER_THUMBNAIL = 2;
    public static final int IMAGE_TYPE_NOTIFICATION_THUMBNAIL = 1;
    public static final int IMAGE_TYPE_UNKNOWN = -1;
    private final zzb zzls = new zza();

    private class zza extends zzb.zza {
        private zza() {
        }

        @Override // com.google.android.gms.cast.framework.media.zzb
        public final int zzs() {
            return 12451009;
        }

        @Override // com.google.android.gms.cast.framework.media.zzb
        public final WebImage onPickImage(MediaMetadata mediaMetadata, int i) {
            return ImagePicker.this.onPickImage(mediaMetadata, i);
        }

        @Override // com.google.android.gms.cast.framework.media.zzb
        public final WebImage zza(MediaMetadata mediaMetadata, ImageHints imageHints) {
            return ImagePicker.this.onPickImage(mediaMetadata, imageHints);
        }

        @Override // com.google.android.gms.cast.framework.media.zzb
        public final IObjectWrapper zzbi() {
            return ObjectWrapper.wrap(ImagePicker.this);
        }
    }

    @Deprecated
    public WebImage onPickImage(MediaMetadata mediaMetadata, int i) {
        if (mediaMetadata == null || !mediaMetadata.hasImages()) {
            return null;
        }
        return mediaMetadata.getImages().get(0);
    }

    public WebImage onPickImage(MediaMetadata mediaMetadata, ImageHints imageHints) {
        return onPickImage(mediaMetadata, imageHints.getType());
    }

    public final zzb zzbj() {
        return this.zzls;
    }
}
