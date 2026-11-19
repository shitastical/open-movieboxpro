package com.google.android.exoplayer.util;

import androidx.core.location.LocationRequestCompat;
import androidx.media3.exoplayer.RendererCapabilities;
import androidx.media3.extractor.AacUtil;
import androidx.media3.extractor.OpusUtil;
import androidx.media3.extractor.ts.TsExtractor;
import com.alibaba.fastjson.asm.Opcodes;
import com.google.android.exoplayer.MediaFormat;
import java.nio.ByteBuffer;
import okio.Utf8;
import org.videolan.libvlc.MediaPlayer;

/* JADX INFO: loaded from: classes4.dex */
public final class Ac3Util {
    private static final int AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT = 1536;
    private static final int AUDIO_SAMPLES_PER_AUDIO_BLOCK = 256;
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = {1, 2, 3, 6};
    private static final int[] SAMPLE_RATE_BY_FSCOD = {OpusUtil.SAMPLE_RATE, 44100, 32000};
    private static final int[] SAMPLE_RATE_BY_FSCOD2 = {24000, 22050, AacUtil.AAC_HE_V1_MAX_RATE_BYTES_PER_SECOND};
    private static final int[] CHANNEL_COUNT_BY_ACMOD = {2, 1, 2, 3, 3, 4, 4, 5};
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD = {32, 40, 48, 56, 64, 80, 96, 112, 128, Opcodes.IF_ICMPNE, 192, 224, 256, 320, RendererCapabilities.DECODER_SUPPORT_MASK, 448, 512, 576, 640};
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = {69, 87, LocationRequestCompat.QUALITY_LOW_POWER, 121, TsExtractor.TS_STREAM_TYPE_DTS_UHD, 174, 208, 243, MediaPlayer.Event.ESSelected, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393};

    public static int getAc3SyncframeAudioSampleCount() {
        return AC3_SYNCFRAME_AUDIO_SAMPLE_COUNT;
    }

    public static MediaFormat parseAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, long j, String str2) {
        int i = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 56) >> 3];
        if ((unsignedByte & 4) != 0) {
            i2++;
        }
        return MediaFormat.createAudioFormat(str, "audio/ac3", -1, -1, j, i2, i, null, str2);
    }

    public static MediaFormat parseEAc3AnnexFFormat(ParsableByteArray parsableByteArray, String str, long j, String str2) {
        parsableByteArray.skipBytes(2);
        int i = SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 192) >> 6];
        int unsignedByte = parsableByteArray.readUnsignedByte();
        int i2 = CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 14) >> 1];
        if ((unsignedByte & 1) != 0) {
            i2++;
        }
        return MediaFormat.createAudioFormat(str, "audio/eac3", -1, -1, j, i2, i, null, str2);
    }

    public static MediaFormat parseAc3SyncframeFormat(ParsableBitArray parsableBitArray, String str, long j, String str2) {
        parsableBitArray.skipBits(32);
        int bits = parsableBitArray.readBits(2);
        parsableBitArray.skipBits(14);
        int bits2 = parsableBitArray.readBits(3);
        if ((bits2 & 1) != 0 && bits2 != 1) {
            parsableBitArray.skipBits(2);
        }
        if ((bits2 & 4) != 0) {
            parsableBitArray.skipBits(2);
        }
        if (bits2 == 2) {
            parsableBitArray.skipBits(2);
        }
        return MediaFormat.createAudioFormat(str, "audio/ac3", -1, -1, j, CHANNEL_COUNT_BY_ACMOD[bits2] + (parsableBitArray.readBit() ? 1 : 0), SAMPLE_RATE_BY_FSCOD[bits], null, str2);
    }

    public static MediaFormat parseEac3SyncframeFormat(ParsableBitArray parsableBitArray, String str, long j, String str2) {
        int i;
        parsableBitArray.skipBits(32);
        int bits = parsableBitArray.readBits(2);
        if (bits == 3) {
            i = SAMPLE_RATE_BY_FSCOD2[parsableBitArray.readBits(2)];
        } else {
            parsableBitArray.skipBits(2);
            i = SAMPLE_RATE_BY_FSCOD[bits];
        }
        return MediaFormat.createAudioFormat(str, "audio/eac3", -1, -1, j, CHANNEL_COUNT_BY_ACMOD[parsableBitArray.readBits(3)] + (parsableBitArray.readBit() ? 1 : 0), i, null, str2);
    }

    public static int parseAc3SyncframeSize(byte[] bArr) {
        byte b = bArr[4];
        return getAc3SyncframeSize((b & 192) >> 6, b & Utf8.REPLACEMENT_BYTE);
    }

    public static int parseEAc3SyncframeSize(byte[] bArr) {
        return (((bArr[2] & 7) << 8) + (bArr[3] & 255) + 1) * 2;
    }

    public static int parseEAc3SyncframeAudioSampleCount(byte[] bArr) {
        byte b = bArr[4];
        return (((b & 192) >> 6) != 3 ? BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(b & 48) >> 4] : 6) * 256;
    }

    public static int parseEAc3SyncframeAudioSampleCount(ByteBuffer byteBuffer) {
        return (((byteBuffer.get(byteBuffer.position() + 4) & 192) >> 6) != 3 ? BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(byteBuffer.get(byteBuffer.position() + 4) & 48) >> 4] : 6) * 256;
    }

    private static int getAc3SyncframeSize(int i, int i2) {
        int i3 = SAMPLE_RATE_BY_FSCOD[i];
        if (i3 == 44100) {
            return (SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1[i2 / 2] + (i2 % 2)) * 2;
        }
        int i4 = BITRATE_BY_HALF_FRMSIZECOD[i2 / 2];
        return i3 == 32000 ? i4 * 6 : i4 * 4;
    }

    private Ac3Util() {
    }
}
