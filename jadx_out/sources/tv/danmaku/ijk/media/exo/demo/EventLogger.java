package tv.danmaku.ijk.media.exo.demo;

import android.media.MediaCodec;
import android.os.SystemClock;
import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.google.android.exoplayer.MediaCodecTrackRenderer;
import com.google.android.exoplayer.TimeRange;
import com.google.android.exoplayer.audio.AudioTrack;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.util.VerboseLogUtil;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;
import tv.danmaku.ijk.media.exo.demo.player.DemoPlayer;

/* JADX INFO: loaded from: classes6.dex */
public class EventLogger implements DemoPlayer.Listener, DemoPlayer.InfoListener, DemoPlayer.InternalErrorListener {
    private static final String TAG = "EventLogger";
    private static final NumberFormat TIME_FORMAT;
    private long[] availableRangeValuesUs;
    private long[] loadStartTimeMs = new long[4];
    private long sessionStartTimeMs;

    static {
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        TIME_FORMAT = numberFormat;
        numberFormat.setMinimumFractionDigits(2);
        numberFormat.setMaximumFractionDigits(2);
    }

    public void startSession() {
        this.sessionStartTimeMs = SystemClock.elapsedRealtime();
        Log.d(TAG, "start [0]");
    }

    public void endSession() {
        Log.d(TAG, "end [" + getSessionTimeString() + "]");
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.Listener
    public void onStateChanged(boolean z, int i) {
        Log.d(TAG, "state [" + getSessionTimeString() + ", " + z + ", " + getStateString(i) + "]");
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.Listener
    public void onError(Exception exc) {
        Log.e(TAG, "playerFailed [" + getSessionTimeString() + "]", exc);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.Listener
    public void onVideoSizeChanged(int i, int i2, int i3, float f) {
        Log.d(TAG, "videoSizeChanged [" + i + ", " + i2 + ", " + i3 + ", " + f + "]");
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InfoListener
    public void onBandwidthSample(int i, long j, long j2) {
        Log.d(TAG, "bandwidth [" + getSessionTimeString() + ", " + j + ", " + getTimeString(i) + ", " + j2 + "]");
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InfoListener
    public void onDroppedFrames(int i, long j) {
        Log.d(TAG, "droppedFrames [" + getSessionTimeString() + ", " + i + "]");
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InfoListener
    public void onLoadStarted(int i, long j, int i2, int i3, Format format, long j2, long j3) {
        this.loadStartTimeMs[i] = SystemClock.elapsedRealtime();
        if (VerboseLogUtil.isTagEnabled(TAG)) {
            Log.v(TAG, "loadStart [" + getSessionTimeString() + ", " + i + ", " + i2 + ", " + j2 + ", " + j3 + "]");
        }
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InfoListener
    public void onLoadCompleted(int i, long j, int i2, int i3, Format format, long j2, long j3, long j4, long j5) {
        if (VerboseLogUtil.isTagEnabled(TAG)) {
            Log.v(TAG, "loadEnd [" + getSessionTimeString() + ", " + i + ", " + (SystemClock.elapsedRealtime() - this.loadStartTimeMs[i]) + "]");
        }
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InfoListener
    public void onVideoFormatEnabled(Format format, int i, long j) {
        Log.d(TAG, "videoFormat [" + getSessionTimeString() + ", " + format.id + ", " + Integer.toString(i) + "]");
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InfoListener
    public void onAudioFormatEnabled(Format format, int i, long j) {
        Log.d(TAG, "audioFormat [" + getSessionTimeString() + ", " + format.id + ", " + Integer.toString(i) + "]");
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InternalErrorListener
    public void onLoadError(int i, IOException iOException) {
        printInternalError("loadError", iOException);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InternalErrorListener
    public void onRendererInitializationError(Exception exc) {
        printInternalError("rendererInitError", exc);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InternalErrorListener
    public void onDrmSessionManagerError(Exception exc) {
        printInternalError("drmSessionManagerError", exc);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InternalErrorListener
    public void onDecoderInitializationError(MediaCodecTrackRenderer.DecoderInitializationException decoderInitializationException) {
        printInternalError("decoderInitializationError", decoderInitializationException);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InternalErrorListener
    public void onAudioTrackInitializationError(AudioTrack.InitializationException initializationException) {
        printInternalError("audioTrackInitializationError", initializationException);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InternalErrorListener
    public void onAudioTrackWriteError(AudioTrack.WriteException writeException) {
        printInternalError("audioTrackWriteError", writeException);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InternalErrorListener
    public void onAudioTrackUnderrun(int i, long j, long j2) {
        printInternalError("audioTrackUnderrun [" + i + ", " + j + ", " + j2 + "]", null);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InternalErrorListener
    public void onCryptoError(MediaCodec.CryptoException cryptoException) {
        printInternalError("cryptoError", cryptoException);
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InfoListener
    public void onDecoderInitialized(String str, long j, long j2) {
        Log.d(TAG, "decoderInitialized [" + getSessionTimeString() + ", " + str + "]");
    }

    @Override // tv.danmaku.ijk.media.exo.demo.player.DemoPlayer.InfoListener
    public void onAvailableRangeChanged(int i, TimeRange timeRange) {
        this.availableRangeValuesUs = timeRange.getCurrentBoundsUs(this.availableRangeValuesUs);
        Log.d(TAG, "availableRange [" + timeRange.isStatic() + ", " + this.availableRangeValuesUs[0] + ", " + this.availableRangeValuesUs[1] + "]");
    }

    private void printInternalError(String str, Exception exc) {
        Log.e(TAG, "internalError [" + getSessionTimeString() + ", " + str + "]", exc);
    }

    private String getStateString(int i) {
        if (i == 1) {
            return "I";
        }
        if (i == 2) {
            return "P";
        }
        if (i == 3) {
            return "B";
        }
        if (i == 4) {
            return "R";
        }
        if (i == 5) {
            return ExifInterface.LONGITUDE_EAST;
        }
        return "?";
    }

    private String getSessionTimeString() {
        return getTimeString(SystemClock.elapsedRealtime() - this.sessionStartTimeMs);
    }

    private String getTimeString(long j) {
        return TIME_FORMAT.format(j / 1000.0f);
    }
}
