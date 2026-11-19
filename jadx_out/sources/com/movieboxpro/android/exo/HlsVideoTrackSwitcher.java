package com.movieboxpro.android.exo;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import com.movieboxpro.android.player.MediaQualityInfo;
import com.movieboxpro.android.service.FileDownloadService;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.RegexOption;
import kotlin.text.StringsKt;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: HlsVideoTrackSwitcher.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\u00020\u0001:\u0004@ABCB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0019\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007J\"\u0010\u0010\u001a\u00020\u00052\u000e\u0010\r\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007J$\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\f2\b\u0010\u000f\u001a\u0004\u0018\u00010\fH\u0007J\u001c\u0010\u0014\u001a\u00020\u00052\b\u0010\u0015\u001a\u0004\u0018\u00010\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\fH\u0007J\"\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u001e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000eH\u0007J\u001e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u000e2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u000eH\u0007J'\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!2\b\u0010#\u001a\u0004\u0018\u00010\u0005H\u0007¢\u0006\u0002\u0010$J\u0018\u0010%\u001a\u00020\u00052\u0006\u0010&\u001a\u00020\t2\u0006\u0010'\u001a\u00020\tH\u0007J'\u0010(\u001a\u0004\u0018\u00010\t2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000e2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¢\u0006\u0002\u0010*J\u0012\u0010+\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010,\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\"\u0010-\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0012\u0010.\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007J\u0018\u00100\u001a\u00020\u00052\u0006\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\u001bH\u0007J(\u00103\u001a\u00020\t2\u0006\u00104\u001a\u00020!2\f\u00105\u001a\b\u0012\u0004\u0012\u0002060\u000e2\b\b\u0002\u00107\u001a\u00020!H\u0007J\u0010\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020\fH\u0002J\u0010\u0010:\u001a\u00020\u00052\u0006\u00109\u001a\u00020\fH\u0002J\u0018\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020\u001b2\u0006\u0010=\u001a\u00020\tH\u0002J\u0014\u0010>\u001a\u0004\u0018\u00010\u00072\b\u0010?\u001a\u0004\u0018\u00010\u0007H\u0002R\u000e\u0010/\u001a\u00020!X\u0086T¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher;", "", "<init>", "()V", "isAutoLabel", "", "quality", "", "parseHeight", "", "(Ljava/lang/String;)Ljava/lang/Integer;", "findAutoItem", "Lcom/movieboxpro/android/player/MediaQualityInfo;", "list", "", TypedValues.AttributesType.S_TARGET, "shouldStartOnAutoMaster", "canSwitchInPlace", "playingMaster", "current", "sameHlsMaster", "left", "right", "shouldConfirmSwitchHint", "width", "height", "selectableFormatsForPin", "Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;", "groups", "Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$PinTrackGroup;", "formatsAvailableToPin", "shouldAcceptTracksEvent", "eventRealtimeMs", "", "prepareStartedAtRealtimeMs", "eventPeriodInCurrentTimeline", "(JJLjava/lang/Boolean;)Z", "shouldCommitQualityHintHide", "hideGeneration", "currentGeneration", "matchVideoTrack", "formats", "(Ljava/util/List;Ljava/lang/String;)Ljava/lang/Integer;", "switchingHint", "switchedHint", "videoSizeMatchesQuality", "shouldRetainBufferForPin", "PIN_RETAIN_DURATION_US", "isSameVariant", "chunk", "pinned", "preferredQueueSizeWhenPinned", "playbackPositionUs", "chunks", "Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$BufferedChunk;", "retainDurationUs", "isHlsVariantTarget", "item", "isHlsItem", "formatMatchesQualityHeight", IjkMediaMeta.IJKM_KEY_FORMAT, "qualityHeight", "playlistKey", FileDownloadService.PARAMS_KEY_PATH, "VideoTrackFormat", "PinTrack", "PinTrackGroup", "BufferedChunk", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HlsVideoTrackSwitcher {
    public static final HlsVideoTrackSwitcher INSTANCE = new HlsVideoTrackSwitcher();
    public static final long PIN_RETAIN_DURATION_US = 3000000;

    @JvmStatic
    public static final MediaQualityInfo findAutoItem(List<MediaQualityInfo> list) {
        return findAutoItem$default(list, null, 2, null);
    }

    @JvmStatic
    public static final int preferredQueueSizeWhenPinned(long j, List<BufferedChunk> chunks) {
        Intrinsics.checkNotNullParameter(chunks, "chunks");
        return preferredQueueSizeWhenPinned$default(j, chunks, 0L, 4, null);
    }

    @JvmStatic
    public static final boolean shouldCommitQualityHintHide(int hideGeneration, int currentGeneration) {
        return hideGeneration == currentGeneration;
    }

    private HlsVideoTrackSwitcher() {
    }

    /* JADX INFO: compiled from: HlsVideoTrackSwitcher.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J?\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001c\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\f¨\u0006\u001d"}, d2 = {"Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;", "", "height", "", "id", "", "label", "width", IjkMediaMeta.IJKM_KEY_BITRATE, "<init>", "(ILjava/lang/String;Ljava/lang/String;II)V", "getHeight", "()I", "getId", "()Ljava/lang/String;", "getLabel", "getWidth", "getBitrate", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class VideoTrackFormat {
        private final int bitrate;
        private final int height;
        private final String id;
        private final String label;
        private final int width;

        public static /* synthetic */ VideoTrackFormat copy$default(VideoTrackFormat videoTrackFormat, int i, String str, String str2, int i2, int i3, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                i = videoTrackFormat.height;
            }
            if ((i4 & 2) != 0) {
                str = videoTrackFormat.id;
            }
            String str3 = str;
            if ((i4 & 4) != 0) {
                str2 = videoTrackFormat.label;
            }
            String str4 = str2;
            if ((i4 & 8) != 0) {
                i2 = videoTrackFormat.width;
            }
            int i5 = i2;
            if ((i4 & 16) != 0) {
                i3 = videoTrackFormat.bitrate;
            }
            return videoTrackFormat.copy(i, str3, str4, i5, i3);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getHeight() {
            return this.height;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getId() {
            return this.id;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getLabel() {
            return this.label;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final int getWidth() {
            return this.width;
        }

        /* JADX INFO: renamed from: component5, reason: from getter */
        public final int getBitrate() {
            return this.bitrate;
        }

        public final VideoTrackFormat copy(int height, String id, String label, int width, int bitrate) {
            return new VideoTrackFormat(height, id, label, width, bitrate);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof VideoTrackFormat)) {
                return false;
            }
            VideoTrackFormat videoTrackFormat = (VideoTrackFormat) other;
            return this.height == videoTrackFormat.height && Intrinsics.areEqual(this.id, videoTrackFormat.id) && Intrinsics.areEqual(this.label, videoTrackFormat.label) && this.width == videoTrackFormat.width && this.bitrate == videoTrackFormat.bitrate;
        }

        public int hashCode() {
            int i = this.height * 31;
            String str = this.id;
            int iHashCode = (i + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.label;
            return ((((iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + this.width) * 31) + this.bitrate;
        }

        public String toString() {
            return "VideoTrackFormat(height=" + this.height + ", id=" + this.id + ", label=" + this.label + ", width=" + this.width + ", bitrate=" + this.bitrate + ")";
        }

        public VideoTrackFormat(int i, String str, String str2, int i2, int i3) {
            this.height = i;
            this.id = str;
            this.label = str2;
            this.width = i2;
            this.bitrate = i3;
        }

        public /* synthetic */ VideoTrackFormat(int i, String str, String str2, int i2, int i3, int i4, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, str, str2, (i4 & 8) != 0 ? 0 : i2, (i4 & 16) != 0 ? 0 : i3);
        }

        public final int getHeight() {
            return this.height;
        }

        public final String getId() {
            return this.id;
        }

        public final String getLabel() {
            return this.label;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getBitrate() {
            return this.bitrate;
        }
    }

    @JvmStatic
    public static final boolean isAutoLabel(String quality) {
        return StringsKt.equals(quality, "AUTO", true);
    }

    @JvmStatic
    public static final Integer parseHeight(String quality) {
        List<String> groupValues;
        String str;
        String str2 = quality;
        if (str2 == null || StringsKt.isBlank(str2)) {
            return null;
        }
        String string = StringsKt.trim((CharSequence) str2).toString();
        if (StringsKt.equals(string, "4K", true) || StringsKt.equals(string, "2160p", true)) {
            return 2160;
        }
        MatchResult matchResultFind$default = Regex.find$default(new Regex("(\\d+)\\s*p", RegexOption.IGNORE_CASE), string, 0, 2, null);
        if (matchResultFind$default == null || (groupValues = matchResultFind$default.getGroupValues()) == null || (str = groupValues.get(1)) == null) {
            return null;
        }
        return StringsKt.toIntOrNull(str);
    }

    public static /* synthetic */ MediaQualityInfo findAutoItem$default(List list, MediaQualityInfo mediaQualityInfo, int i, Object obj) {
        if ((i & 2) != 0) {
            mediaQualityInfo = null;
        }
        return findAutoItem(list, mediaQualityInfo);
    }

    @JvmStatic
    public static final MediaQualityInfo findAutoItem(List<MediaQualityInfo> list, MediaQualityInfo target) {
        Object obj = null;
        if (list == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : list) {
            MediaQualityInfo mediaQualityInfo = (MediaQualityInfo) obj2;
            if (isAutoLabel(mediaQualityInfo.getReal_quality()) && INSTANCE.isHlsItem(mediaQualityInfo)) {
                arrayList.add(obj2);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return null;
        }
        if (target == null) {
            return (MediaQualityInfo) CollectionsKt.first((List) arrayList2);
        }
        for (Object obj3 : arrayList2) {
            if (sameHlsMaster((MediaQualityInfo) obj3, target)) {
                obj = obj3;
                break;
            }
        }
        return (MediaQualityInfo) obj;
    }

    @JvmStatic
    public static final boolean shouldStartOnAutoMaster(List<MediaQualityInfo> list, MediaQualityInfo target) {
        return (target == null || !INSTANCE.isHlsVariantTarget(target) || findAutoItem(list, target) == null) ? false : true;
    }

    @JvmStatic
    public static final boolean canSwitchInPlace(boolean playingMaster, MediaQualityInfo current, MediaQualityInfo target) {
        if (!playingMaster || current == null || target == null || !INSTANCE.isHlsVariantTarget(target)) {
            return false;
        }
        return sameHlsMaster(current, target);
    }

    @JvmStatic
    public static final boolean sameHlsMaster(MediaQualityInfo left, MediaQualityInfo right) {
        if (left == null || right == null) {
            return false;
        }
        if (left.getMmfid() > 0 && left.getMmfid() == right.getMmfid()) {
            return true;
        }
        if (left.getTmfid() > 0 && left.getTmfid() == right.getTmfid()) {
            return true;
        }
        HlsVideoTrackSwitcher hlsVideoTrackSwitcher = INSTANCE;
        String strPlaylistKey = hlsVideoTrackSwitcher.playlistKey(left.getPath());
        return strPlaylistKey != null && Intrinsics.areEqual(strPlaylistKey, hlsVideoTrackSwitcher.playlistKey(right.getPath()));
    }

    @JvmStatic
    public static final boolean shouldConfirmSwitchHint(int width, int height, String quality) {
        return videoSizeMatchesQuality(width, height, quality);
    }

    /* JADX INFO: compiled from: HlsVideoTrackSwitcher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$PinTrack;", "", IjkMediaMeta.IJKM_KEY_FORMAT, "Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;", "supported", "", "<init>", "(Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;Z)V", "getFormat", "()Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$VideoTrackFormat;", "getSupported", "()Z", "component1", "component2", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class PinTrack {
        private final VideoTrackFormat format;
        private final boolean supported;

        public static /* synthetic */ PinTrack copy$default(PinTrack pinTrack, VideoTrackFormat videoTrackFormat, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                videoTrackFormat = pinTrack.format;
            }
            if ((i & 2) != 0) {
                z = pinTrack.supported;
            }
            return pinTrack.copy(videoTrackFormat, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final VideoTrackFormat getFormat() {
            return this.format;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getSupported() {
            return this.supported;
        }

        public final PinTrack copy(VideoTrackFormat format, boolean supported) {
            Intrinsics.checkNotNullParameter(format, "format");
            return new PinTrack(format, supported);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PinTrack)) {
                return false;
            }
            PinTrack pinTrack = (PinTrack) other;
            return Intrinsics.areEqual(this.format, pinTrack.format) && this.supported == pinTrack.supported;
        }

        public int hashCode() {
            return (this.format.hashCode() * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.supported);
        }

        public String toString() {
            return "PinTrack(format=" + this.format + ", supported=" + this.supported + ")";
        }

        public PinTrack(VideoTrackFormat format, boolean z) {
            Intrinsics.checkNotNullParameter(format, "format");
            this.format = format;
            this.supported = z;
        }

        public final VideoTrackFormat getFormat() {
            return this.format;
        }

        public final boolean getSupported() {
            return this.supported;
        }
    }

    /* JADX INFO: compiled from: HlsVideoTrackSwitcher.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J#\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$PinTrackGroup;", "", "isSelected", "", "tracks", "", "Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$PinTrack;", "<init>", "(ZLjava/util/List;)V", "()Z", "getTracks", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class PinTrackGroup {
        private final boolean isSelected;
        private final List<PinTrack> tracks;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ PinTrackGroup copy$default(PinTrackGroup pinTrackGroup, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = pinTrackGroup.isSelected;
            }
            if ((i & 2) != 0) {
                list = pinTrackGroup.tracks;
            }
            return pinTrackGroup.copy(z, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getIsSelected() {
            return this.isSelected;
        }

        public final List<PinTrack> component2() {
            return this.tracks;
        }

        public final PinTrackGroup copy(boolean isSelected, List<PinTrack> tracks) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            return new PinTrackGroup(isSelected, tracks);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PinTrackGroup)) {
                return false;
            }
            PinTrackGroup pinTrackGroup = (PinTrackGroup) other;
            return this.isSelected == pinTrackGroup.isSelected && Intrinsics.areEqual(this.tracks, pinTrackGroup.tracks);
        }

        public int hashCode() {
            return (HlsDownload$$ExternalSyntheticBackport0.m(this.isSelected) * 31) + this.tracks.hashCode();
        }

        public String toString() {
            return "PinTrackGroup(isSelected=" + this.isSelected + ", tracks=" + this.tracks + ")";
        }

        public PinTrackGroup(boolean z, List<PinTrack> tracks) {
            Intrinsics.checkNotNullParameter(tracks, "tracks");
            this.isSelected = z;
            this.tracks = tracks;
        }

        public final boolean isSelected() {
            return this.isSelected;
        }

        public final List<PinTrack> getTracks() {
            return this.tracks;
        }
    }

    @JvmStatic
    public static final List<VideoTrackFormat> selectableFormatsForPin(List<PinTrackGroup> groups) {
        Intrinsics.checkNotNullParameter(groups, "groups");
        ArrayList arrayList = new ArrayList();
        for (Object obj : groups) {
            if (((PinTrackGroup) obj).isSelected()) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = arrayList;
        if (arrayList2.isEmpty()) {
            return null;
        }
        ArrayList arrayList3 = new ArrayList();
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            List<PinTrack> tracks = ((PinTrackGroup) it.next()).getTracks();
            ArrayList arrayList4 = new ArrayList();
            for (Object obj2 : tracks) {
                if (((PinTrack) obj2).getSupported()) {
                    arrayList4.add(obj2);
                }
            }
            ArrayList arrayList5 = arrayList4;
            ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayList5, 10));
            Iterator it2 = arrayList5.iterator();
            while (it2.hasNext()) {
                arrayList6.add(((PinTrack) it2.next()).getFormat());
            }
            CollectionsKt.addAll(arrayList3, arrayList6);
        }
        return arrayList3;
    }

    @JvmStatic
    public static final List<VideoTrackFormat> formatsAvailableToPin(List<PinTrackGroup> groups) {
        Intrinsics.checkNotNullParameter(groups, "groups");
        List<PinTrackGroup> list = groups;
        if ((list instanceof Collection) && list.isEmpty()) {
            return null;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (((PinTrackGroup) it.next()).isSelected()) {
                ArrayList arrayList = new ArrayList();
                Iterator<T> it2 = list.iterator();
                while (it2.hasNext()) {
                    List<PinTrack> tracks = ((PinTrackGroup) it2.next()).getTracks();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(tracks, 10));
                    Iterator<T> it3 = tracks.iterator();
                    while (it3.hasNext()) {
                        arrayList2.add(((PinTrack) it3.next()).getFormat());
                    }
                    CollectionsKt.addAll(arrayList, arrayList2);
                }
                return arrayList;
            }
        }
        return null;
    }

    @JvmStatic
    public static final boolean shouldAcceptTracksEvent(long eventRealtimeMs, long prepareStartedAtRealtimeMs, Boolean eventPeriodInCurrentTimeline) {
        return eventRealtimeMs >= prepareStartedAtRealtimeMs && !Intrinsics.areEqual((Object) eventPeriodInCurrentTimeline, (Object) false);
    }

    @JvmStatic
    public static final Integer matchVideoTrack(List<VideoTrackFormat> formats, String quality) {
        Intrinsics.checkNotNullParameter(formats, "formats");
        String str = quality;
        Integer num = null;
        if (str == null || StringsKt.isBlank(str) || isAutoLabel(quality) || formats.isEmpty()) {
            return null;
        }
        Integer height = parseHeight(quality);
        if (height != null) {
            Iterator<VideoTrackFormat> it = formats.iterator();
            int i = 0;
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                if (INSTANCE.formatMatchesQualityHeight(it.next(), height.intValue())) {
                    break;
                }
                i++;
            }
            if (i >= 0) {
                return Integer.valueOf(i);
            }
        }
        String lowerCase = StringsKt.trim((CharSequence) str).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        String str2 = height != null ? height.intValue() + "p" : null;
        for (Integer num2 : CollectionsKt.getIndices(formats)) {
            int iIntValue = num2.intValue();
            String lowerCase2 = CollectionsKt.joinToString$default(CollectionsKt.listOfNotNull((Object[]) new String[]{formats.get(iIntValue).getId(), formats.get(iIntValue).getLabel()}), " ", null, null, 0, null, null, 62, null).toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            String str3 = lowerCase2;
            if (StringsKt.contains$default((CharSequence) str3, (CharSequence) lowerCase, false, 2, (Object) null) || (str2 != null && StringsKt.contains$default((CharSequence) str3, (CharSequence) str2, false, 2, (Object) null))) {
                num = num2;
                break;
            }
        }
        return num;
    }

    @JvmStatic
    public static final String switchingHint(String quality) {
        if (quality == null) {
            quality = "";
        }
        return "Switching to " + quality;
    }

    @JvmStatic
    public static final String switchedHint(String quality) {
        if (quality == null) {
            quality = "";
        }
        return "Switched to " + quality;
    }

    @JvmStatic
    public static final boolean videoSizeMatchesQuality(int width, int height, String quality) {
        HlsVideoTrackSwitcher hlsVideoTrackSwitcher = INSTANCE;
        if (isAutoLabel(quality)) {
            return true;
        }
        Integer height2 = parseHeight(quality);
        if (height2 == null) {
            return false;
        }
        return hlsVideoTrackSwitcher.formatMatchesQualityHeight(new VideoTrackFormat(height, null, null, width, 0, 16, null), height2.intValue());
    }

    @JvmStatic
    public static final boolean shouldRetainBufferForPin(String quality) {
        String str = quality;
        return (str == null || StringsKt.isBlank(str) || isAutoLabel(quality)) ? false : true;
    }

    /* JADX INFO: compiled from: HlsVideoTrackSwitcher.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/exo/HlsVideoTrackSwitcher$BufferedChunk;", "", "startTimeUs", "", "isPinnedQuality", "", "<init>", "(JZ)V", "getStartTimeUs", "()J", "()Z", "component1", "component2", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class BufferedChunk {
        private final boolean isPinnedQuality;
        private final long startTimeUs;

        public static /* synthetic */ BufferedChunk copy$default(BufferedChunk bufferedChunk, long j, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                j = bufferedChunk.startTimeUs;
            }
            if ((i & 2) != 0) {
                z = bufferedChunk.isPinnedQuality;
            }
            return bufferedChunk.copy(j, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getStartTimeUs() {
            return this.startTimeUs;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getIsPinnedQuality() {
            return this.isPinnedQuality;
        }

        public final BufferedChunk copy(long startTimeUs, boolean isPinnedQuality) {
            return new BufferedChunk(startTimeUs, isPinnedQuality);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof BufferedChunk)) {
                return false;
            }
            BufferedChunk bufferedChunk = (BufferedChunk) other;
            return this.startTimeUs == bufferedChunk.startTimeUs && this.isPinnedQuality == bufferedChunk.isPinnedQuality;
        }

        public int hashCode() {
            return (HlsDownload$$ExternalSyntheticBackport0.m(this.startTimeUs) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.isPinnedQuality);
        }

        public String toString() {
            return "BufferedChunk(startTimeUs=" + this.startTimeUs + ", isPinnedQuality=" + this.isPinnedQuality + ")";
        }

        public BufferedChunk(long j, boolean z) {
            this.startTimeUs = j;
            this.isPinnedQuality = z;
        }

        public final long getStartTimeUs() {
            return this.startTimeUs;
        }

        public final boolean isPinnedQuality() {
            return this.isPinnedQuality;
        }
    }

    @JvmStatic
    public static final boolean isSameVariant(VideoTrackFormat chunk, VideoTrackFormat pinned) {
        String id;
        Intrinsics.checkNotNullParameter(chunk, "chunk");
        Intrinsics.checkNotNullParameter(pinned, "pinned");
        String id2 = chunk.getId();
        if (id2 == null || StringsKt.isBlank(id2) || (id = pinned.getId()) == null || StringsKt.isBlank(id)) {
            return chunk.getWidth() == pinned.getWidth() && chunk.getHeight() == pinned.getHeight() && chunk.getBitrate() == pinned.getBitrate();
        }
        return Intrinsics.areEqual(chunk.getId(), pinned.getId());
    }

    public static /* synthetic */ int preferredQueueSizeWhenPinned$default(long j, List list, long j2, int i, Object obj) {
        if ((i & 4) != 0) {
            j2 = PIN_RETAIN_DURATION_US;
        }
        return preferredQueueSizeWhenPinned(j, list, j2);
    }

    @JvmStatic
    public static final int preferredQueueSizeWhenPinned(long playbackPositionUs, List<BufferedChunk> chunks, long retainDurationUs) {
        Intrinsics.checkNotNullParameter(chunks, "chunks");
        int i = 0;
        if (chunks.isEmpty()) {
            return 0;
        }
        for (Object obj : chunks) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            BufferedChunk bufferedChunk = (BufferedChunk) obj;
            if (bufferedChunk.getStartTimeUs() - playbackPositionUs >= retainDurationUs && !bufferedChunk.isPinnedQuality()) {
                return i;
            }
            i = i2;
        }
        return chunks.size();
    }

    private final boolean isHlsVariantTarget(MediaQualityInfo item) {
        if (item.getOriginal() == 1 || item.isLocalFile()) {
            return false;
        }
        return isHlsItem(item);
    }

    private final boolean isHlsItem(MediaQualityInfo item) {
        if (item.isHls()) {
            return true;
        }
        String path = item.getPath();
        if (path == null) {
            return false;
        }
        return StringsKt.contains((CharSequence) path, (CharSequence) "m3u8", true);
    }

    private final boolean formatMatchesQualityHeight(VideoTrackFormat format, int qualityHeight) {
        if (format.getHeight() == qualityHeight) {
            return true;
        }
        if (qualityHeight == 2160) {
            return format.getHeight() >= 1600 || format.getWidth() >= 3840;
        }
        return false;
    }

    private final String playlistKey(String path) {
        String str = path;
        if (str == null || StringsKt.isBlank(str)) {
            return null;
        }
        String lowerCase = StringsKt.trim((CharSequence) StringsKt.substringBefore$default(path, '?', (String) null, 2, (Object) null)).toString().toLowerCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return StringsKt.trimEnd(lowerCase, '/');
    }
}
