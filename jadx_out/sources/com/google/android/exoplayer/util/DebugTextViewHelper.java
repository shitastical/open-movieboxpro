package com.google.android.exoplayer.util;

import android.widget.TextView;
import com.google.android.exoplayer.CodecCounters;
import com.google.android.exoplayer.chunk.Format;
import com.google.android.exoplayer.upstream.BandwidthMeter;

/* JADX INFO: loaded from: classes4.dex */
public final class DebugTextViewHelper implements Runnable {
    private static final int REFRESH_INTERVAL_MS = 1000;
    private final Provider debuggable;
    private final TextView textView;

    public interface Provider {
        BandwidthMeter getBandwidthMeter();

        CodecCounters getCodecCounters();

        long getCurrentPosition();

        Format getFormat();
    }

    public DebugTextViewHelper(Provider provider, TextView textView) {
        this.debuggable = provider;
        this.textView = textView;
    }

    public void start() {
        stop();
        run();
    }

    public void stop() {
        this.textView.removeCallbacks(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        this.textView.setText(getRenderString());
        this.textView.postDelayed(this, 1000L);
    }

    private String getRenderString() {
        return getTimeString() + " " + getQualityString() + " " + getBandwidthString() + " " + getVideoCodecCountersString();
    }

    private String getTimeString() {
        return "ms(" + this.debuggable.getCurrentPosition() + ")";
    }

    private String getQualityString() {
        Format format = this.debuggable.getFormat();
        if (format == null) {
            return "id:? br:? h:?";
        }
        return "id:" + format.id + " br:" + format.bitrate + " h:" + format.height;
    }

    private String getBandwidthString() {
        BandwidthMeter bandwidthMeter = this.debuggable.getBandwidthMeter();
        if (bandwidthMeter == null || bandwidthMeter.getBitrateEstimate() == -1) {
            return "bw:?";
        }
        return "bw:" + (bandwidthMeter.getBitrateEstimate() / 1000);
    }

    private String getVideoCodecCountersString() {
        CodecCounters codecCounters = this.debuggable.getCodecCounters();
        return codecCounters == null ? "" : codecCounters.getDebugString();
    }
}
