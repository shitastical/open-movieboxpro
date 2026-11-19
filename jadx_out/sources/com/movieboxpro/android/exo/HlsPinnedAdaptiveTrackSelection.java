package com.movieboxpro.android.exo;

import androidx.media3.common.Format;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Clock;
import androidx.media3.exoplayer.source.chunk.MediaChunk;
import androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection;
import androidx.media3.exoplayer.upstream.BandwidthMeter;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: HlsPinnedAdaptiveTrackSelection.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u0001:\u0001/B}\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u0007\u0012\u0006\u0010\u000f\u001a\u00020\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019¢\u0006\u0004\b\u001a\u0010\u001bJ\b\u0010\u001c\u001a\u00020\u0007H\u0016J\b\u0010\u001d\u001a\u00020\u0007H\u0016J \u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u000b2\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\"0!H\u0016J(\u0010%\u001a\u00020&2\u000e\u0010 \u001a\n\u0012\u0006\b\u0001\u0012\u00020\"0!2\u0006\u0010'\u001a\u00020\u00072\u0006\u0010(\u001a\u00020\u0007H\u0002J\u000f\u0010)\u001a\u0004\u0018\u00010\u0007H\u0002¢\u0006\u0002\u0010*J\u0010\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020.H\u0002R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010$X\u0082\u000e¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/movieboxpro/android/exo/HlsPinnedAdaptiveTrackSelection;", "Landroidx/media3/exoplayer/trackselection/AdaptiveTrackSelection;", "group", "Landroidx/media3/common/TrackGroup;", "tracks", "", "type", "", "bandwidthMeter", "Landroidx/media3/exoplayer/upstream/BandwidthMeter;", "minDurationForQualityIncreaseMs", "", "maxDurationForQualityDecreaseMs", "minDurationToRetainAfterDiscardMs", "maxWidthToDiscard", "maxHeightToDiscard", "bandwidthFraction", "", "bufferedFractionToLiveEdgeForQualityIncrease", "adaptationCheckpoints", "", "Landroidx/media3/exoplayer/trackselection/AdaptiveTrackSelection$AdaptationCheckpoint;", "clock", "Landroidx/media3/common/util/Clock;", "pinHolder", "Lcom/movieboxpro/android/exo/HlsQualityPinHolder;", "<init>", "(Landroidx/media3/common/TrackGroup;[IILandroidx/media3/exoplayer/upstream/BandwidthMeter;JJJIIFFLjava/util/List;Landroidx/media3/common/util/Clock;Lcom/movieboxpro/android/exo/HlsQualityPinHolder;)V", "getSelectedIndex", "getSelectionReason", "evaluateQueueSize", "playbackPositionUs", "queue", "", "Landroidx/media3/exoplayer/source/chunk/MediaChunk;", "lastPinnedQueueLog", "", "logPinnedQueueIfShrinking", "", "pinned", "keep", "pinnedIndex", "()Ljava/lang/Integer;", "toVideoTrackFormat", "Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;", IjkMediaMeta.IJKM_KEY_FORMAT, "Landroidx/media3/common/Format;", "Factory", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HlsPinnedAdaptiveTrackSelection extends AdaptiveTrackSelection {
    private String lastPinnedQueueLog;
    private final HlsQualityPinHolder pinHolder;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HlsPinnedAdaptiveTrackSelection(TrackGroup group, int[] tracks, int i, BandwidthMeter bandwidthMeter, long j, long j2, long j3, int i2, int i3, float f, float f2, List<AdaptiveTrackSelection.AdaptationCheckpoint> adaptationCheckpoints, Clock clock, HlsQualityPinHolder pinHolder) {
        super(group, tracks, i, bandwidthMeter, j, j2, j3, i2, i3, f, f2, adaptationCheckpoints, clock);
        Intrinsics.checkNotNullParameter(group, "group");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(bandwidthMeter, "bandwidthMeter");
        Intrinsics.checkNotNullParameter(adaptationCheckpoints, "adaptationCheckpoints");
        Intrinsics.checkNotNullParameter(clock, "clock");
        Intrinsics.checkNotNullParameter(pinHolder, "pinHolder");
        this.pinHolder = pinHolder;
    }

    /* JADX INFO: compiled from: HlsPinnedAdaptiveTrackSelection.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J6\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/movieboxpro/android/exo/HlsPinnedAdaptiveTrackSelection$Factory;", "Landroidx/media3/exoplayer/trackselection/AdaptiveTrackSelection$Factory;", "pinHolder", "Lcom/movieboxpro/android/exo/HlsQualityPinHolder;", "<init>", "(Lcom/movieboxpro/android/exo/HlsQualityPinHolder;)V", "createAdaptiveTrackSelection", "Landroidx/media3/exoplayer/trackselection/AdaptiveTrackSelection;", "group", "Landroidx/media3/common/TrackGroup;", "tracks", "", "type", "", "bandwidthMeter", "Landroidx/media3/exoplayer/upstream/BandwidthMeter;", "adaptationCheckpoints", "Lcom/google/common/collect/ImmutableList;", "Landroidx/media3/exoplayer/trackselection/AdaptiveTrackSelection$AdaptationCheckpoint;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Factory extends AdaptiveTrackSelection.Factory {
        private final HlsQualityPinHolder pinHolder;

        public Factory(HlsQualityPinHolder pinHolder) {
            Intrinsics.checkNotNullParameter(pinHolder, "pinHolder");
            this.pinHolder = pinHolder;
        }

        @Override // androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection.Factory
        protected AdaptiveTrackSelection createAdaptiveTrackSelection(TrackGroup group, int[] tracks, int type, BandwidthMeter bandwidthMeter, ImmutableList<AdaptiveTrackSelection.AdaptationCheckpoint> adaptationCheckpoints) {
            Intrinsics.checkNotNullParameter(group, "group");
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            Intrinsics.checkNotNullParameter(bandwidthMeter, "bandwidthMeter");
            Intrinsics.checkNotNullParameter(adaptationCheckpoints, "adaptationCheckpoints");
            Clock DEFAULT = Clock.DEFAULT;
            Intrinsics.checkNotNullExpressionValue(DEFAULT, "DEFAULT");
            return new HlsPinnedAdaptiveTrackSelection(group, tracks, type, bandwidthMeter, 10000L, 25000L, 25000L, AdaptiveTrackSelection.DEFAULT_MAX_WIDTH_TO_DISCARD, AdaptiveTrackSelection.DEFAULT_MAX_HEIGHT_TO_DISCARD, 0.7f, 0.75f, adaptationCheckpoints, DEFAULT, this.pinHolder);
        }
    }

    @Override // androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectedIndex() {
        Integer numPinnedIndex = pinnedIndex();
        return numPinnedIndex != null ? numPinnedIndex.intValue() : super.getSelectedIndex();
    }

    @Override // androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int getSelectionReason() {
        if (pinnedIndex() != null) {
            return 2;
        }
        return super.getSelectionReason();
    }

    @Override // androidx.media3.exoplayer.trackselection.AdaptiveTrackSelection, androidx.media3.exoplayer.trackselection.BaseTrackSelection, androidx.media3.exoplayer.trackselection.ExoTrackSelection
    public int evaluateQueueSize(long playbackPositionUs, List<? extends MediaChunk> queue) {
        Intrinsics.checkNotNullParameter(queue, "queue");
        Integer numPinnedIndex = pinnedIndex();
        if (numPinnedIndex == null) {
            return super.evaluateQueueSize(playbackPositionUs, queue);
        }
        int iIntValue = numPinnedIndex.intValue();
        Format format = getFormat(iIntValue);
        Intrinsics.checkNotNullExpressionValue(format, "getFormat(...)");
        HlsVideoTrackSwitcher.VideoTrackFormat videoTrackFormat = toVideoTrackFormat(format);
        List<? extends MediaChunk> list = queue;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        for (MediaChunk mediaChunk : list) {
            long j = mediaChunk.startTimeUs;
            Format trackFormat = mediaChunk.trackFormat;
            Intrinsics.checkNotNullExpressionValue(trackFormat, "trackFormat");
            arrayList.add(new HlsVideoTrackSwitcher.BufferedChunk(j, HlsVideoTrackSwitcher.isSameVariant(toVideoTrackFormat(trackFormat), videoTrackFormat)));
        }
        int iPreferredQueueSizeWhenPinned = HlsVideoTrackSwitcher.preferredQueueSizeWhenPinned(playbackPositionUs, arrayList, HlsVideoTrackSwitcher.PIN_RETAIN_DURATION_US);
        logPinnedQueueIfShrinking(queue, iIntValue, iPreferredQueueSizeWhenPinned);
        return iPreferredQueueSizeWhenPinned;
    }

    private final void logPinnedQueueIfShrinking(List<? extends MediaChunk> queue, int pinned, int keep) {
        Object next;
        Format trackFormat;
        if (keep >= queue.size()) {
            this.lastPinnedQueueLog = null;
            return;
        }
        Format format = getFormat(pinned);
        Intrinsics.checkNotNullExpressionValue(format, "getFormat(...)");
        HlsVideoTrackSwitcher.VideoTrackFormat videoTrackFormat = toVideoTrackFormat(format);
        List<? extends MediaChunk> list = queue;
        int i = 0;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                Format trackFormat2 = ((MediaChunk) it.next()).trackFormat;
                Intrinsics.checkNotNullExpressionValue(trackFormat2, "trackFormat");
                if (!HlsVideoTrackSwitcher.isSameVariant(toVideoTrackFormat(trackFormat2), videoTrackFormat) && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        Iterator<T> it2 = list.iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            } else {
                next = it2.next();
                trackFormat = ((MediaChunk) next).trackFormat;
                Intrinsics.checkNotNullExpressionValue(trackFormat, "trackFormat");
            }
        } while (HlsVideoTrackSwitcher.isSameVariant(toVideoTrackFormat(trackFormat), videoTrackFormat));
        MediaChunk mediaChunk = (MediaChunk) next;
        Format format2 = mediaChunk != null ? mediaChunk.trackFormat : null;
        String str = "queue pin=" + this.pinHolder.getQualityLabel() + " size=" + queue.size() + " keep=" + keep + " unmatched=" + i + " pinned=" + videoTrackFormat.getWidth() + "x" + videoTrackFormat.getHeight() + " id=" + videoTrackFormat.getId() + " chunk=" + (format2 != null ? Integer.valueOf(format2.width) : null) + "x" + (format2 != null ? Integer.valueOf(format2.height) : null) + " chunkId=" + (format2 != null ? format2.id : null);
        if (Intrinsics.areEqual(str, this.lastPinnedQueueLog)) {
            return;
        }
        this.lastPinnedQueueLog = str;
        HlsDebugLog.INSTANCE.i(str);
    }

    private final Integer pinnedIndex() {
        String qualityLabel = this.pinHolder.getQualityLabel();
        if (!HlsVideoTrackSwitcher.shouldRetainBufferForPin(qualityLabel)) {
            return null;
        }
        IntRange intRangeUntil = RangesKt.until(0, length());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRangeUntil, 10));
        Iterator<Integer> it = intRangeUntil.iterator();
        while (it.hasNext()) {
            Format format = getFormat(((IntIterator) it).nextInt());
            Intrinsics.checkNotNullExpressionValue(format, "getFormat(...)");
            arrayList.add(toVideoTrackFormat(format));
        }
        return HlsVideoTrackSwitcher.matchVideoTrack(arrayList, qualityLabel);
    }

    private final HlsVideoTrackSwitcher.VideoTrackFormat toVideoTrackFormat(Format format) {
        return new HlsVideoTrackSwitcher.VideoTrackFormat(format.height, format.id, format.label, format.width, format.bitrate);
    }
}
