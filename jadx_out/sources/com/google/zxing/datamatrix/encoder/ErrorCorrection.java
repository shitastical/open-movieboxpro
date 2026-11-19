package com.google.zxing.datamatrix.encoder;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.media3.extractor.ts.TsExtractor;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson.asm.Opcodes;
import com.kaopiz.kprogresshud.BuildConfig;
import me.jahnen.libaums.core.fs.fat32.FatDirectoryEntry;
import okhttp3.internal.http.HttpStatusCodesKt;
import org.joda.time.DateTimeConstants;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes5.dex */
public final class ErrorCorrection {
    private static final int MODULO_VALUE = 301;
    private static final int[] FACTOR_SETS = {5, 7, 10, 11, 12, 14, 18, 20, 24, 28, 36, 42, 48, 56, 62, 68};
    private static final int[][] FACTORS = {new int[]{228, 48, 15, 111, 62}, new int[]{23, 68, IjkMediaMeta.FF_PROFILE_H264_HIGH_444, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 240, 92, 254}, new int[]{28, 24, Opcodes.INVOKEINTERFACE, Opcodes.IF_ACMPNE, 223, 248, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 255, IjkMediaMeta.FF_PROFILE_H264_HIGH_10, 61}, new int[]{175, TsExtractor.TS_STREAM_TYPE_DTS, 205, 12, 194, DateTimeConstants.HOURS_PER_WEEK, 39, 245, 60, 97, BuildConfig.VERSION_CODE}, new int[]{41, Opcodes.IFEQ, Opcodes.IFLE, 91, 61, 42, 142, 213, 97, Opcodes.GETSTATIC, 100, 242}, new int[]{156, 97, 192, 252, 95, 9, 157, 119, TsExtractor.TS_STREAM_TYPE_DTS, 45, 18, 186, 83, Opcodes.INVOKEINTERFACE}, new int[]{83, 195, 100, 39, 188, 75, 66, 61, 241, 213, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, TsExtractor.TS_STREAM_TYPE_AC3, 94, 254, 225, 48, 90, 188}, new int[]{15, 195, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 9, 233, 71, DateTimeConstants.HOURS_PER_WEEK, 2, 188, Opcodes.IF_ICMPNE, Opcodes.IFEQ, 145, 253, 79, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 82, 27, 174, 186, TsExtractor.TS_STREAM_TYPE_AC4}, new int[]{52, 190, 88, 205, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, 39, Opcodes.ARETURN, 21, 155, 197, 251, 223, 155, 21, 5, TsExtractor.TS_STREAM_TYPE_AC4, 254, 124, 12, Opcodes.PUTFIELD, Opcodes.INVOKESTATIC, 96, 50, Opcodes.INSTANCEOF}, new int[]{211, 231, 43, 97, 71, 96, HttpStatusCodesKt.HTTP_EARLY_HINTS, 174, 37, Opcodes.DCMPL, 170, 53, 75, 34, 249, 121, 17, TsExtractor.TS_STREAM_TYPE_DTS, IjkMediaMeta.FF_PROFILE_H264_HIGH_10, 213, 141, TsExtractor.TS_STREAM_TYPE_DTS_HD, BuildConfig.VERSION_CODE, Opcodes.DCMPL, 233, DateTimeConstants.HOURS_PER_WEEK, 93, 255}, new int[]{245, 127, 242, 218, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, Opcodes.IF_ICMPGE, Opcodes.PUTFIELD, 102, BuildConfig.VERSION_CODE, 84, 179, 220, 251, 80, Opcodes.INVOKEVIRTUAL, FatDirectoryEntry.ENTRY_DELETED, 18, 2, 4, 68, 33, 101, 137, 95, 119, 115, 44, 175, Opcodes.INVOKESTATIC, 59, 25, 225, 98, 81, 112}, new int[]{77, Opcodes.INSTANCEOF, 137, 31, 19, 38, 22, Opcodes.IFEQ, 247, 105, IjkMediaMeta.FF_PROFILE_H264_HIGH_422, 2, 245, 133, 242, 8, 175, 95, 100, 9, Opcodes.GOTO, 105, 214, 111, 57, 121, 21, 1, 253, 57, 54, 101, 248, 202, 69, 50, 150, Opcodes.RETURN, 226, 5, 9, 5}, new int[]{245, 132, TsExtractor.TS_STREAM_TYPE_AC4, 223, 96, 32, 117, 22, 238, 133, 238, 231, 205, 188, 237, 87, 191, 106, 16, 147, 118, 23, 37, 90, 170, 205, 131, 88, BuildConfig.VERSION_CODE, 100, 66, TsExtractor.TS_STREAM_TYPE_DTS, 186, 240, 82, 44, Opcodes.ARETURN, 87, Opcodes.NEW, 147, Opcodes.IF_ICMPNE, 175, 69, 213, 92, 253, 225, 19}, new int[]{175, 9, 223, 238, 12, 17, 220, 208, 100, 29, 175, 170, 230, 192, 215, 235, 150, Opcodes.IF_ICMPEQ, 36, 223, 38, 200, 132, 54, 228, 146, 218, 234, 117, 203, 29, 232, IjkMediaMeta.FF_PROFILE_H264_HIGH_444, 238, 22, 150, jp.wasabeef.glide.transformations.BuildConfig.VERSION_CODE, 117, 62, 207, 164, 13, 137, 245, 127, 67, 247, 28, 155, 43, 203, 107, 233, 53, 143, 46}, new int[]{242, 93, Opcodes.RET, 50, IjkMediaMeta.FF_PROFILE_H264_HIGH_444, 210, 39, 118, 202, 188, jp.wasabeef.glide.transformations.BuildConfig.VERSION_CODE, 189, 143, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, 196, 37, Opcodes.INVOKEINTERFACE, 112, TsExtractor.TS_STREAM_TYPE_SPLICE_INFO, 230, 245, 63, 197, 190, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 106, Opcodes.INVOKEINTERFACE, 221, 175, 64, 114, 71, Opcodes.IF_ICMPLT, 44, 147, 6, 27, 218, 51, 63, 87, 10, 40, TsExtractor.TS_STREAM_TYPE_HDMV_DTS, 188, 17, Opcodes.IF_ICMPGT, 31, Opcodes.ARETURN, 170, 4, 107, 232, 7, 94, Opcodes.IF_ACMPNE, 224, 124, 86, 47, 11, 204}, new int[]{220, 228, 173, 89, 251, Opcodes.FCMPL, Opcodes.IF_ICMPEQ, 56, 89, 33, 147, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, Opcodes.IFNE, 36, 73, 127, 213, TsExtractor.TS_STREAM_TYPE_DTS_HD, 248, Opcodes.GETFIELD, 234, 197, Opcodes.IFLE, Opcodes.RETURN, 68, IjkMediaMeta.FF_PROFILE_H264_HIGH_422, 93, 213, 15, Opcodes.IF_ICMPNE, 227, 236, 66, TsExtractor.TS_STREAM_TYPE_DTS_UHD, Opcodes.IFEQ, Opcodes.INVOKEINTERFACE, 202, Opcodes.GOTO, 179, 25, 220, 232, 96, 210, 231, TsExtractor.TS_STREAM_TYPE_DTS_HD, 223, 239, Opcodes.PUTFIELD, 241, 59, 52, TsExtractor.TS_STREAM_TYPE_AC4, 25, 49, 232, 211, 189, 64, 54, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, Opcodes.IFEQ, 132, 63, 96, HttpStatusCodesKt.HTTP_EARLY_HINTS, 82, 186}};
    private static final int[] LOG = new int[256];
    private static final int[] ALOG = new int[255];

    static {
        int i = 1;
        for (int i2 = 0; i2 < 255; i2++) {
            ALOG[i2] = i;
            LOG[i] = i2;
            i <<= 1;
            if (i >= 256) {
                i ^= 301;
            }
        }
    }

    private ErrorCorrection() {
    }

    public static String encodeECC200(String str, SymbolInfo symbolInfo) {
        if (str.length() != symbolInfo.getDataCapacity()) {
            throw new IllegalArgumentException("The number of codewords does not match the selected symbol");
        }
        StringBuilder sb = new StringBuilder(symbolInfo.getDataCapacity() + symbolInfo.getErrorCodewords());
        sb.append(str);
        int interleavedBlockCount = symbolInfo.getInterleavedBlockCount();
        if (interleavedBlockCount == 1) {
            sb.append(createECCBlock(str, symbolInfo.getErrorCodewords()));
        } else {
            sb.setLength(sb.capacity());
            int[] iArr = new int[interleavedBlockCount];
            int[] iArr2 = new int[interleavedBlockCount];
            int[] iArr3 = new int[interleavedBlockCount];
            int i = 0;
            while (i < interleavedBlockCount) {
                int i2 = i + 1;
                iArr[i] = symbolInfo.getDataLengthForInterleavedBlock(i2);
                iArr2[i] = symbolInfo.getErrorLengthForInterleavedBlock(i2);
                iArr3[i] = 0;
                if (i > 0) {
                    iArr3[i] = iArr3[i - 1] + iArr[i];
                }
                i = i2;
            }
            for (int i3 = 0; i3 < interleavedBlockCount; i3++) {
                StringBuilder sb2 = new StringBuilder(iArr[i3]);
                for (int i4 = i3; i4 < symbolInfo.getDataCapacity(); i4 += interleavedBlockCount) {
                    sb2.append(str.charAt(i4));
                }
                String strCreateECCBlock = createECCBlock(sb2.toString(), iArr2[i3]);
                int i5 = i3;
                int i6 = 0;
                while (i5 < iArr2[i3] * interleavedBlockCount) {
                    sb.setCharAt(symbolInfo.getDataCapacity() + i5, strCreateECCBlock.charAt(i6));
                    i5 += interleavedBlockCount;
                    i6++;
                }
            }
        }
        return sb.toString();
    }

    private static String createECCBlock(CharSequence charSequence, int i) {
        return createECCBlock(charSequence, 0, charSequence.length(), i);
    }

    private static String createECCBlock(CharSequence charSequence, int i, int i2, int i3) {
        int i4;
        int i5;
        int i6 = 0;
        while (true) {
            int[] iArr = FACTOR_SETS;
            if (i6 >= iArr.length) {
                i6 = -1;
                break;
            }
            if (iArr[i6] == i3) {
                break;
            }
            i6++;
        }
        if (i6 < 0) {
            throw new IllegalArgumentException("Illegal number of error correction codewords specified: " + i3);
        }
        int[] iArr2 = FACTORS[i6];
        char[] cArr = new char[i3];
        for (int i7 = 0; i7 < i3; i7++) {
            cArr[i7] = 0;
        }
        for (int i8 = i; i8 < i + i2; i8++) {
            int i9 = i3 - 1;
            int iCharAt = cArr[i9] ^ charSequence.charAt(i8);
            while (i9 > 0) {
                if (iCharAt != 0 && (i5 = iArr2[i9]) != 0) {
                    char c = cArr[i9 - 1];
                    int[] iArr3 = ALOG;
                    int[] iArr4 = LOG;
                    cArr[i9] = (char) (iArr3[(iArr4[iCharAt] + iArr4[i5]) % 255] ^ c);
                } else {
                    cArr[i9] = cArr[i9 - 1];
                }
                i9--;
            }
            if (iCharAt != 0 && (i4 = iArr2[0]) != 0) {
                int[] iArr5 = ALOG;
                int[] iArr6 = LOG;
                cArr[0] = (char) iArr5[(iArr6[iCharAt] + iArr6[i4]) % 255];
            } else {
                cArr[0] = 0;
            }
        }
        char[] cArr2 = new char[i3];
        for (int i10 = 0; i10 < i3; i10++) {
            cArr2[i10] = cArr[(i3 - i10) - 1];
        }
        return String.valueOf(cArr2);
    }
}
