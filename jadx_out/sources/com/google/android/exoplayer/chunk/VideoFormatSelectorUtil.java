package com.google.android.exoplayer.chunk;

import android.content.Context;
import android.graphics.Point;
import com.google.android.exoplayer.MediaCodecUtil;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.Util;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class VideoFormatSelectorUtil {
    private static final float FRACTION_TO_CONSIDER_FULLSCREEN = 0.98f;

    public static int[] selectVideoFormatsForDefaultDisplay(Context context, List<? extends FormatWrapper> list, String[] strArr, boolean z) throws MediaCodecUtil.DecoderQueryException {
        Point physicalDisplaySize = Util.getPhysicalDisplaySize(context);
        return selectVideoFormats(list, strArr, z, true, false, physicalDisplaySize.x, physicalDisplaySize.y);
    }

    /* JADX WARN: Code duplicated, block: B:18:0x0069  */
    public static int[] selectVideoFormats(List<? extends FormatWrapper> list, String[] strArr, boolean z, boolean z2, boolean z3, int i, int i2) throws MediaCodecUtil.DecoderQueryException {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < size; i4++) {
            Format format = list.get(i4).getFormat();
            if (isFormatPlayable(format, strArr, z, z3)) {
                arrayList.add(Integer.valueOf(i4));
                if (format.width > 0 && format.height > 0 && i > 0 && i2 > 0) {
                    Point maxVideoSizeInViewport = getMaxVideoSizeInViewport(z2, i, i2, format.width, format.height);
                    int i5 = format.width * format.height;
                    if (format.width >= ((int) (maxVideoSizeInViewport.x * FRACTION_TO_CONSIDER_FULLSCREEN)) && format.height >= ((int) (maxVideoSizeInViewport.y * FRACTION_TO_CONSIDER_FULLSCREEN)) && i5 < i3) {
                        i3 = i5;
                    }
                }
            }
        }
        if (i3 != Integer.MAX_VALUE) {
            for (int size2 = arrayList.size() - 1; size2 >= 0; size2--) {
                Format format2 = list.get(((Integer) arrayList.get(size2)).intValue()).getFormat();
                if (format2.width > 0 && format2.height > 0 && format2.width * format2.height > i3) {
                    arrayList.remove(size2);
                }
            }
        }
        return Util.toArray(arrayList);
    }

    private static boolean isFormatPlayable(Format format, String[] strArr, boolean z, boolean z2) throws MediaCodecUtil.DecoderQueryException {
        if (strArr != null && !Util.contains(strArr, format.mimeType)) {
            return false;
        }
        if (z && (format.width >= 1280 || format.height >= 720)) {
            return false;
        }
        if (format.width <= 0 || format.height <= 0) {
            return true;
        }
        if (Util.SDK_INT < 21) {
            return format.width * format.height <= MediaCodecUtil.maxH264DecodableFrameSize();
        }
        String videoMediaMimeType = MimeTypes.getVideoMediaMimeType(format.codecs);
        if ("video/x-unknown".equals(videoMediaMimeType)) {
            videoMediaMimeType = "video/avc";
        }
        String str = videoMediaMimeType;
        if (format.frameRate > 0.0f) {
            return MediaCodecUtil.isSizeAndRateSupportedV21(str, z2, format.width, format.height, format.frameRate);
        }
        return MediaCodecUtil.isSizeSupportedV21(str, z2, format.width, format.height);
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0010  */
    private static Point getMaxVideoSizeInViewport(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            if ((i3 > i4) == (i > i2)) {
                i2 = i;
                i = i2;
            }
        } else {
            i2 = i;
            i = i2;
        }
        int i5 = i3 * i;
        int i6 = i4 * i2;
        if (i5 >= i6) {
            return new Point(i2, Util.ceilDivide(i6, i3));
        }
        return new Point(Util.ceilDivide(i5, i4), i);
    }

    private VideoFormatSelectorUtil() {
    }
}
