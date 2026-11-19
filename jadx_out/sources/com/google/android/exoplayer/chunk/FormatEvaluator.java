package com.google.android.exoplayer.chunk;

import com.google.android.exoplayer.upstream.BandwidthMeter;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: classes4.dex */
public interface FormatEvaluator {

    public static final class Evaluation {
        public Format format;
        public int queueSize;
        public int trigger = 1;
    }

    void disable();

    void enable();

    void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation);

    public static final class FixedEvaluator implements FormatEvaluator {
        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void disable() {
        }

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void enable() {
        }

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation) {
            evaluation.format = formatArr[0];
        }
    }

    public static final class RandomEvaluator implements FormatEvaluator {
        private final Random random;

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void disable() {
        }

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void enable() {
        }

        public RandomEvaluator() {
            this.random = new Random();
        }

        public RandomEvaluator(int i) {
            this.random = new Random(i);
        }

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation) {
            Format format = formatArr[this.random.nextInt(formatArr.length)];
            if (evaluation.format != null && !evaluation.format.equals(format)) {
                evaluation.trigger = 3;
            }
            evaluation.format = format;
        }
    }

    public static final class AdaptiveEvaluator implements FormatEvaluator {
        public static final float DEFAULT_BANDWIDTH_FRACTION = 0.75f;
        public static final int DEFAULT_MAX_DURATION_FOR_QUALITY_DECREASE_MS = 25000;
        public static final int DEFAULT_MAX_INITIAL_BITRATE = 800000;
        public static final int DEFAULT_MIN_DURATION_FOR_QUALITY_INCREASE_MS = 10000;
        public static final int DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS = 25000;
        private final float bandwidthFraction;
        private final BandwidthMeter bandwidthMeter;
        private final long maxDurationForQualityDecreaseUs;
        private final int maxInitialBitrate;
        private final long minDurationForQualityIncreaseUs;
        private final long minDurationToRetainAfterDiscardUs;

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void disable() {
        }

        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void enable() {
        }

        public AdaptiveEvaluator(BandwidthMeter bandwidthMeter) {
            this(bandwidthMeter, DEFAULT_MAX_INITIAL_BITRATE, 10000, 25000, 25000, 0.75f);
        }

        public AdaptiveEvaluator(BandwidthMeter bandwidthMeter, int i, int i2, int i3, int i4, float f) {
            this.bandwidthMeter = bandwidthMeter;
            this.maxInitialBitrate = i;
            this.minDurationForQualityIncreaseUs = ((long) i2) * 1000;
            this.maxDurationForQualityDecreaseUs = ((long) i3) * 1000;
            this.minDurationToRetainAfterDiscardUs = ((long) i4) * 1000;
            this.bandwidthFraction = f;
        }

        /* JADX WARN: Code duplicated, block: B:42:0x0091  */
        @Override // com.google.android.exoplayer.chunk.FormatEvaluator
        public void evaluate(List<? extends MediaChunk> list, long j, Format[] formatArr, Evaluation evaluation) {
            long j2 = list.isEmpty() ? 0L : list.get(list.size() - 1).endTimeUs - j;
            Format format = evaluation.format;
            Format formatDetermineIdealFormat = determineIdealFormat(formatArr, this.bandwidthMeter.getBitrateEstimate());
            boolean z = false;
            boolean z2 = (formatDetermineIdealFormat == null || format == null || formatDetermineIdealFormat.bitrate <= format.bitrate) ? false : true;
            if (formatDetermineIdealFormat != null && format != null && formatDetermineIdealFormat.bitrate < format.bitrate) {
                z = true;
            }
            if (z2) {
                if (j2 < this.minDurationForQualityIncreaseUs) {
                    formatDetermineIdealFormat = format;
                } else if (j2 >= this.minDurationToRetainAfterDiscardUs) {
                    for (int i = 1; i < list.size(); i++) {
                        MediaChunk mediaChunk = list.get(i);
                        if (mediaChunk.startTimeUs - j >= this.minDurationToRetainAfterDiscardUs && mediaChunk.format.bitrate < formatDetermineIdealFormat.bitrate && mediaChunk.format.height < formatDetermineIdealFormat.height && mediaChunk.format.height < 720 && mediaChunk.format.width < 1280) {
                            evaluation.queueSize = i;
                            break;
                        }
                    }
                }
            } else if (z && format != null && j2 >= this.maxDurationForQualityDecreaseUs) {
                formatDetermineIdealFormat = format;
            }
            if (format != null && formatDetermineIdealFormat != format) {
                evaluation.trigger = 3;
            }
            evaluation.format = formatDetermineIdealFormat;
        }

        private Format determineIdealFormat(Format[] formatArr, long j) {
            long j2 = j == -1 ? this.maxInitialBitrate : (long) (j * this.bandwidthFraction);
            for (Format format : formatArr) {
                if (format.bitrate <= j2) {
                    return format;
                }
            }
            return formatArr[formatArr.length - 1];
        }
    }
}
