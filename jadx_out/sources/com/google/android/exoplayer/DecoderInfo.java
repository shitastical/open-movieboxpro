package com.google.android.exoplayer;

import android.media.MediaCodecInfo;
import com.google.android.exoplayer.util.Util;

/* JADX INFO: loaded from: classes4.dex */
public final class DecoderInfo {
    public final boolean adaptive;
    public final MediaCodecInfo.CodecCapabilities capabilities;
    public final String name;

    DecoderInfo(String str, MediaCodecInfo.CodecCapabilities codecCapabilities) {
        this.name = str;
        this.capabilities = codecCapabilities;
        this.adaptive = isAdaptive(codecCapabilities);
    }

    private static boolean isAdaptive(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities != null && Util.SDK_INT >= 19 && isAdaptiveV19(codecCapabilities);
    }

    private static boolean isAdaptiveV19(MediaCodecInfo.CodecCapabilities codecCapabilities) {
        return codecCapabilities.isFeatureSupported("adaptive-playback");
    }
}
