package com.google.android.gms.internal.cast;

import com.google.android.gms.cast.MediaMetadata;

/* JADX INFO: loaded from: classes4.dex */
public final class zzaq {
    /* JADX WARN: Code duplicated, block: B:17:0x0029 A[PHI: r1
      0x0029: PHI (r1v5 java.lang.String) = (r1v3 java.lang.String), (r1v4 java.lang.String) binds: [B:16:0x0027, B:19:0x0031] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:21:0x0034  */
    public static String zzb(MediaMetadata mediaMetadata) {
        String str = MediaMetadata.KEY_SUBTITLE;
        if (!mediaMetadata.containsKey(MediaMetadata.KEY_SUBTITLE)) {
            int mediaType = mediaMetadata.getMediaType();
            if (mediaType == 1) {
                str = MediaMetadata.KEY_STUDIO;
            } else if (mediaType == 2) {
                str = MediaMetadata.KEY_SERIES_TITLE;
            } else if (mediaType != 3) {
                if (mediaType == 4) {
                    str = MediaMetadata.KEY_ARTIST;
                }
            } else if (mediaMetadata.containsKey(MediaMetadata.KEY_ARTIST)) {
                str = MediaMetadata.KEY_ARTIST;
            } else {
                String str2 = MediaMetadata.KEY_ALBUM_ARTIST;
                if (mediaMetadata.containsKey(MediaMetadata.KEY_ALBUM_ARTIST)) {
                    str = str2;
                } else {
                    str2 = MediaMetadata.KEY_COMPOSER;
                    if (mediaMetadata.containsKey(MediaMetadata.KEY_COMPOSER)) {
                        str = str2;
                    }
                }
            }
        }
        return mediaMetadata.getString(str);
    }
}
