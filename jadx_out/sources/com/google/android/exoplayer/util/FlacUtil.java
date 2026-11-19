package com.google.android.exoplayer.util;

/* JADX INFO: loaded from: classes4.dex */
public final class FlacUtil {
    private static final int FRAME_HEADER_SAMPLE_NUMBER_OFFSET = 4;

    private FlacUtil() {
    }

    public static long extractSampleTimestamp(FlacStreamInfo flacStreamInfo, ParsableByteArray parsableByteArray) {
        parsableByteArray.skipBytes(4);
        long uTF8EncodedLong = parsableByteArray.readUTF8EncodedLong();
        if (flacStreamInfo.minBlockSize == flacStreamInfo.maxBlockSize) {
            uTF8EncodedLong *= (long) flacStreamInfo.minBlockSize;
        }
        return (uTF8EncodedLong * 1000000) / ((long) flacStreamInfo.sampleRate);
    }
}
