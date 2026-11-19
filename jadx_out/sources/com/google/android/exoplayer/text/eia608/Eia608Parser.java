package com.google.android.exoplayer.text.eia608;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.location.LocationRequestCompat;
import androidx.media3.extractor.metadata.dvbsi.AppInfoTableDecoder;
import androidx.recyclerview.widget.ItemTouchHelper;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.common.base.Ascii;
import com.jeffmony.downloader.utils.HttpUtils;
import com.kaopiz.kprogresshud.BuildConfig;
import java.util.ArrayList;
import me.jahnen.libaums.core.fs.fat32.FatDirectoryEntry;
import okhttp3.internal.http.HttpStatusCodesKt;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes4.dex */
public final class Eia608Parser {
    private static final int COUNTRY_CODE = 181;
    private static final int PAYLOAD_TYPE_CC = 4;
    private static final int PROVIDER_CODE = 49;
    private static final int USER_DATA_TYPE_CODE = 3;
    private static final int USER_ID = 1195456820;
    private static final int[] BASIC_CHARACTER_SET = {32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 97, 98, 99, 100, 101, 102, HttpStatusCodesKt.HTTP_EARLY_HINTS, LocationRequestCompat.QUALITY_LOW_POWER, 105, 106, 107, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR, AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY, IjkMediaMeta.FF_PROFILE_H264_HIGH_10, 111, 112, 113, 114, 115, AppInfoTableDecoder.APPLICATION_INFORMATION_TABLE_ID, 117, 118, 119, BuildConfig.VERSION_CODE, 121, IjkMediaMeta.FF_PROFILE_H264_HIGH_422, 231, 247, 209, 241, 9632};
    private static final int[] SPECIAL_CHARACTER_SET = {174, Opcodes.ARETURN, 189, 191, 8482, Opcodes.IF_ICMPGE, Opcodes.IF_ICMPGT, 9834, 224, 32, 232, 226, 234, 238, IjkMediaMeta.FF_PROFILE_H264_HIGH_444_PREDICTIVE, 251};
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = {Opcodes.INSTANCEOF, jp.wasabeef.glide.transformations.BuildConfig.VERSION_CODE, 211, 218, 220, 252, 8216, Opcodes.IF_ICMPLT, 42, 39, 8212, Opcodes.RET, 8480, 8226, 8220, 8221, 192, 194, Opcodes.IFNONNULL, 200, 202, 203, 235, HttpUtils.RESPONSE_206, 207, 239, 212, 217, 249, 219, 171, Opcodes.NEW};
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = {195, 227, 205, 204, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, 214, 246, 223, Opcodes.IF_ACMPEQ, 164, 9474, 197, FatDirectoryEntry.ENTRY_DELETED, 216, 248, 9484, 9488, 9492, 9496};
    private final ParsableBitArray seiBuffer = new ParsableBitArray();
    private final StringBuilder stringBuilder = new StringBuilder();
    private final ArrayList<ClosedCaption> captions = new ArrayList<>();

    Eia608Parser() {
    }

    boolean canParse(String str) {
        return str.equals(MimeTypes.APPLICATION_EIA608);
    }

    ClosedCaptionList parse(SampleHolder sampleHolder) {
        if (sampleHolder.size < 10) {
            return null;
        }
        this.captions.clear();
        this.stringBuilder.setLength(0);
        this.seiBuffer.reset(sampleHolder.data.array());
        this.seiBuffer.skipBits(67);
        int bits = this.seiBuffer.readBits(5);
        this.seiBuffer.skipBits(8);
        for (int i = 0; i < bits; i++) {
            this.seiBuffer.skipBits(5);
            if (!this.seiBuffer.readBit()) {
                this.seiBuffer.skipBits(18);
            } else if (this.seiBuffer.readBits(2) != 0) {
                this.seiBuffer.skipBits(16);
            } else {
                this.seiBuffer.skipBits(1);
                byte bits2 = (byte) this.seiBuffer.readBits(7);
                this.seiBuffer.skipBits(1);
                byte bits3 = (byte) this.seiBuffer.readBits(7);
                if (bits2 != 0 || bits3 != 0) {
                    if ((bits2 == 17 || bits2 == 25) && (bits3 & 112) == 48) {
                        this.stringBuilder.append(getSpecialChar(bits3));
                    } else if ((bits2 == 18 || bits2 == 26) && (bits3 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedEsFrChar(bits3));
                    } else if ((bits2 == 19 || bits2 == 27) && (bits3 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedPtDeChar(bits3));
                    } else if (bits2 < 32) {
                        addCtrl(bits2, bits3);
                    } else {
                        this.stringBuilder.append(getChar(bits2));
                        if (bits3 >= 32) {
                            this.stringBuilder.append(getChar(bits3));
                        }
                    }
                }
            }
        }
        addBufferedText();
        if (this.captions.isEmpty()) {
            return null;
        }
        ClosedCaption[] closedCaptionArr = new ClosedCaption[this.captions.size()];
        this.captions.toArray(closedCaptionArr);
        return new ClosedCaptionList(sampleHolder.timeUs, sampleHolder.isDecodeOnly(), closedCaptionArr);
    }

    private static char getChar(byte b) {
        return (char) BASIC_CHARACTER_SET[(b & 127) - 32];
    }

    private static char getSpecialChar(byte b) {
        return (char) SPECIAL_CHARACTER_SET[b & Ascii.SI];
    }

    private static char getExtendedEsFrChar(byte b) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[b & 31];
    }

    private static char getExtendedPtDeChar(byte b) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[b & 31];
    }

    private void addBufferedText() {
        if (this.stringBuilder.length() > 0) {
            this.captions.add(new ClosedCaptionText(this.stringBuilder.toString()));
            this.stringBuilder.setLength(0);
        }
    }

    private void addCtrl(byte b, byte b2) {
        addBufferedText();
        this.captions.add(new ClosedCaptionCtrl(b, b2));
    }

    private void backspace() {
        addCtrl((byte) 20, ClosedCaptionCtrl.BACKSPACE);
    }

    public static boolean isSeiMessageEia608(int i, int i2, ParsableByteArray parsableByteArray) {
        if (i != 4 || i2 < 8) {
            return false;
        }
        int position = parsableByteArray.getPosition();
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int unsignedShort = parsableByteArray.readUnsignedShort();
        int i3 = parsableByteArray.readInt();
        int unsignedByte2 = parsableByteArray.readUnsignedByte();
        parsableByteArray.setPosition(position);
        return unsignedByte == 181 && unsignedShort == 49 && i3 == 1195456820 && unsignedByte2 == 3;
    }
}
