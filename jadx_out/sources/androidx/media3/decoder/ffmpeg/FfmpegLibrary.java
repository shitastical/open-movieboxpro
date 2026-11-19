package androidx.media3.decoder.ffmpeg;

import androidx.media3.common.MediaLibraryInfo;
import androidx.media3.common.MimeTypes;
import androidx.media3.common.util.LibraryLoader;
import androidx.media3.common.util.Log;
import com.google.android.gms.cast.HlsSegmentFormat;
import com.google.common.base.Ascii;
import tv.danmaku.ijk.media.player.misc.IjkMediaFormat;

/* JADX INFO: loaded from: classes.dex */
public final class FfmpegLibrary {
    private static final LibraryLoader LOADER;
    private static final String TAG = "FfmpegLibrary";
    private static int inputBufferPaddingSize;
    private static String version;

    private static native int ffmpegGetInputBufferPaddingSize();

    private static native String ffmpegGetVersion();

    private static native boolean ffmpegHasDecoder(String codecName);

    static {
        MediaLibraryInfo.registerModule("media3.decoder.ffmpeg");
        LOADER = new LibraryLoader("ffmpegJNI") { // from class: androidx.media3.decoder.ffmpeg.FfmpegLibrary.1
            @Override // androidx.media3.common.util.LibraryLoader
            protected void loadLibrary(String name) {
                System.loadLibrary(name);
            }
        };
        inputBufferPaddingSize = -1;
    }

    private FfmpegLibrary() {
    }

    public static void setLibraries(String... libraries) {
        LOADER.setLibraries(libraries);
    }

    public static boolean isAvailable() {
        return LOADER.isAvailable();
    }

    public static String getVersion() {
        if (!isAvailable()) {
            return null;
        }
        if (version == null) {
            version = ffmpegGetVersion();
        }
        return version;
    }

    public static int getInputBufferPaddingSize() {
        if (!isAvailable()) {
            return -1;
        }
        if (inputBufferPaddingSize == -1) {
            inputBufferPaddingSize = ffmpegGetInputBufferPaddingSize();
        }
        return inputBufferPaddingSize;
    }

    public static boolean supportsFormat(String mimeType) {
        String codecName;
        if (!isAvailable() || (codecName = getCodecName(mimeType)) == null) {
            return false;
        }
        if (ffmpegHasDecoder(codecName)) {
            return true;
        }
        Log.w(TAG, "No " + codecName + " decoder available. Check the FFmpeg build configuration.");
        return false;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    static String getCodecName(String mimeType) {
        mimeType.hashCode();
        byte b = -1;
        switch (mimeType.hashCode()) {
            case -2123537834:
                if (mimeType.equals(MimeTypes.AUDIO_E_AC3_JOC)) {
                    b = 0;
                }
                break;
            case -1662541442:
                if (mimeType.equals("video/hevc")) {
                    b = 1;
                }
                break;
            case -1606874997:
                if (mimeType.equals("audio/amr-wb")) {
                    b = 2;
                }
                break;
            case -1095064472:
                if (mimeType.equals("audio/vnd.dts")) {
                    b = 3;
                }
                break;
            case -1003765268:
                if (mimeType.equals("audio/vorbis")) {
                    b = 4;
                }
                break;
            case -432837260:
                if (mimeType.equals("audio/mpeg-L1")) {
                    b = 5;
                }
                break;
            case -432837259:
                if (mimeType.equals("audio/mpeg-L2")) {
                    b = 6;
                }
                break;
            case -53558318:
                if (mimeType.equals("audio/mp4a-latm")) {
                    b = 7;
                }
                break;
            case 187078296:
                if (mimeType.equals("audio/ac3")) {
                    b = 8;
                }
                break;
            case 1331836730:
                if (mimeType.equals("video/avc")) {
                    b = 9;
                }
                break;
            case 1503095341:
                if (mimeType.equals("audio/3gpp")) {
                    b = 10;
                }
                break;
            case 1504470054:
                if (mimeType.equals(MimeTypes.AUDIO_ALAC)) {
                    b = Ascii.VT;
                }
                break;
            case 1504578661:
                if (mimeType.equals("audio/eac3")) {
                    b = Ascii.FF;
                }
                break;
            case 1504619009:
                if (mimeType.equals(MimeTypes.AUDIO_FLAC)) {
                    b = Ascii.CR;
                }
                break;
            case 1504831518:
                if (mimeType.equals("audio/mpeg")) {
                    b = Ascii.SO;
                }
                break;
            case 1504891608:
                if (mimeType.equals("audio/opus")) {
                    b = Ascii.SI;
                }
                break;
            case 1505942594:
                if (mimeType.equals("audio/vnd.dts.hd")) {
                    b = Ascii.DLE;
                }
                break;
            case 1556697186:
                if (mimeType.equals("audio/true-hd")) {
                    b = 17;
                }
                break;
            case 1903231877:
                if (mimeType.equals(MimeTypes.AUDIO_ALAW)) {
                    b = Ascii.DC2;
                }
                break;
            case 1903589369:
                if (mimeType.equals(MimeTypes.AUDIO_MLAW)) {
                    b = 19;
                }
                break;
        }
        switch (b) {
            case 0:
            case 12:
                return "eac3";
            case 1:
                return "hevc";
            case 2:
                return "amrwb";
            case 3:
            case 16:
                return "dca";
            case 4:
                return "vorbis";
            case 5:
            case 6:
            case 14:
                return HlsSegmentFormat.MP3;
            case 7:
                return HlsSegmentFormat.AAC;
            case 8:
                return HlsSegmentFormat.AC3;
            case 9:
                return IjkMediaFormat.CODEC_NAME_H264;
            case 10:
                return "amrnb";
            case 11:
                return "alac";
            case 13:
                return "flac";
            case 15:
                return "opus";
            case 17:
                return "truehd";
            case 18:
                return "pcm_alaw";
            case 19:
                return "pcm_mulaw";
            default:
                return null;
        }
    }
}
