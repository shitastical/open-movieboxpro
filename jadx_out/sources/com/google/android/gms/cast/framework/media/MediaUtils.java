package com.google.android.gms.cast.framework.media;

import android.net.Uri;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class MediaUtils {
    public static String getImageUrl(MediaInfo mediaInfo, int i) {
        Uri imageUri = getImageUri(mediaInfo, i);
        if (imageUri == null) {
            return null;
        }
        return imageUri.toString();
    }

    public static Uri getImageUri(MediaInfo mediaInfo, int i) {
        MediaMetadata metadata;
        if (mediaInfo == null || (metadata = mediaInfo.getMetadata()) == null || metadata.getImages() == null || metadata.getImages().size() <= i) {
            return null;
        }
        return metadata.getImages().get(i).getUrl();
    }

    public static Locale getTrackLanguage(MediaTrack mediaTrack) {
        if (mediaTrack.getLanguage() == null) {
            return null;
        }
        if (PlatformVersion.isAtLeastLollipop()) {
            return Locale.forLanguageTag(mediaTrack.getLanguage());
        }
        String[] strArrSplit = mediaTrack.getLanguage().split("-");
        if (strArrSplit.length == 1) {
            return new Locale(strArrSplit[0]);
        }
        return new Locale(strArrSplit[0], strArrSplit[1]);
    }

    private MediaUtils() {
    }
}
