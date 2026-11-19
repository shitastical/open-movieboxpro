package com.movieboxpro.android.view.activity.videoplayer;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.NotificationCompat;
import com.jeffmony.downloader.model.Video;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TranslateSubtitleStreamParser.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0005\u0004\u0005\u0006\u0007\bB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0005\t\n\u000b\f\r¨\u0006\u000e"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent;", "", "<init>", "()V", "Meta", "Cues", "Done", "Error", "Ignore", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Cues;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Done;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Error;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Ignore;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Meta;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class TranslateSubtitleStreamEvent {
    public /* synthetic */ TranslateSubtitleStreamEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: TranslateSubtitleStreamParser.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\nJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J2\u0010\u0012\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0013J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u001a"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Meta;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent;", "total", "", TypedValues.TransitionType.S_FROM, "", TypedValues.TransitionType.S_TO, "<init>", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "getTotal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getFrom", "()Ljava/lang/String;", "getTo", "component1", "component2", "component3", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Meta;", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Meta extends TranslateSubtitleStreamEvent {
        private final String from;
        private final String to;
        private final Integer total;

        public static /* synthetic */ Meta copy$default(Meta meta, Integer num, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                num = meta.total;
            }
            if ((i & 2) != 0) {
                str = meta.from;
            }
            if ((i & 4) != 0) {
                str2 = meta.to;
            }
            return meta.copy(num, str, str2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getFrom() {
            return this.from;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final String getTo() {
            return this.to;
        }

        public final Meta copy(Integer total, String from, String to) {
            return new Meta(total, from, to);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Meta)) {
                return false;
            }
            Meta meta = (Meta) other;
            return Intrinsics.areEqual(this.total, meta.total) && Intrinsics.areEqual(this.from, meta.from) && Intrinsics.areEqual(this.to, meta.to);
        }

        public int hashCode() {
            Integer num = this.total;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            String str = this.from;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.to;
            return iHashCode2 + (str2 != null ? str2.hashCode() : 0);
        }

        public String toString() {
            return "Meta(total=" + this.total + ", from=" + this.from + ", to=" + this.to + ")";
        }

        public Meta(Integer num, String str, String str2) {
            super(null);
            this.total = num;
            this.from = str;
            this.to = str2;
        }

        public final String getFrom() {
            return this.from;
        }

        public final String getTo() {
            return this.to;
        }

        public final Integer getTotal() {
            return this.total;
        }
    }

    private TranslateSubtitleStreamEvent() {
    }

    /* JADX INFO: compiled from: TranslateSubtitleStreamParser.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\u000fJ\u000f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J>\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001¢\u0006\u0002\u0010\u0018J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001cHÖ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\u0010\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006 "}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Cues;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent;", "offset", "", "count", "doneCount", "cues", "", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleCue;", "<init>", "(IILjava/lang/Integer;Ljava/util/List;)V", "getOffset", "()I", "getCount", "getDoneCount", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getCues", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "copy", "(IILjava/lang/Integer;Ljava/util/List;)Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Cues;", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Cues extends TranslateSubtitleStreamEvent {
        private final int count;
        private final List<TranslateSubtitleCue> cues;
        private final Integer doneCount;
        private final int offset;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Cues copy$default(Cues cues, int i, int i2, Integer num, List list, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = cues.offset;
            }
            if ((i3 & 2) != 0) {
                i2 = cues.count;
            }
            if ((i3 & 4) != 0) {
                num = cues.doneCount;
            }
            if ((i3 & 8) != 0) {
                list = cues.cues;
            }
            return cues.copy(i, i2, num, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getOffset() {
            return this.offset;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCount() {
            return this.count;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Integer getDoneCount() {
            return this.doneCount;
        }

        public final List<TranslateSubtitleCue> component4() {
            return this.cues;
        }

        public final Cues copy(int offset, int count, Integer doneCount, List<TranslateSubtitleCue> cues) {
            Intrinsics.checkNotNullParameter(cues, "cues");
            return new Cues(offset, count, doneCount, cues);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Cues)) {
                return false;
            }
            Cues cues = (Cues) other;
            return this.offset == cues.offset && this.count == cues.count && Intrinsics.areEqual(this.doneCount, cues.doneCount) && Intrinsics.areEqual(this.cues, cues.cues);
        }

        public int hashCode() {
            int i = ((this.offset * 31) + this.count) * 31;
            Integer num = this.doneCount;
            return ((i + (num == null ? 0 : num.hashCode())) * 31) + this.cues.hashCode();
        }

        public String toString() {
            return "Cues(offset=" + this.offset + ", count=" + this.count + ", doneCount=" + this.doneCount + ", cues=" + this.cues + ")";
        }

        public final int getOffset() {
            return this.offset;
        }

        public final int getCount() {
            return this.count;
        }

        public final Integer getDoneCount() {
            return this.doneCount;
        }

        public final List<TranslateSubtitleCue> getCues() {
            return this.cues;
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Cues(int i, int i2, Integer num, List<TranslateSubtitleCue> cues) {
            super(null);
            Intrinsics.checkNotNullParameter(cues, "cues");
            this.offset = i;
            this.count = i2;
            this.doneCount = num;
            this.cues = cues;
        }
    }

    /* JADX INFO: compiled from: TranslateSubtitleStreamParser.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B/\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\fJ\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\u0017\u001a\u0004\u0018\u00010\bHÆ\u0003J>\u0010\u0018\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0019J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001f\u001a\u00020\bHÖ\u0001R\u0015\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000e\u0010\fR\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Done;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent;", "total", "", "doneCount", "srtId", "", "srtUrl", "", "<init>", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;)V", "getTotal", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getDoneCount", "getSrtId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "getSrtUrl", "()Ljava/lang/String;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Long;Ljava/lang/String;)Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Done;", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Done extends TranslateSubtitleStreamEvent {
        private final Integer doneCount;
        private final Long srtId;
        private final String srtUrl;
        private final Integer total;

        public static /* synthetic */ Done copy$default(Done done, Integer num, Integer num2, Long l, String str, int i, Object obj) {
            if ((i & 1) != 0) {
                num = done.total;
            }
            if ((i & 2) != 0) {
                num2 = done.doneCount;
            }
            if ((i & 4) != 0) {
                l = done.srtId;
            }
            if ((i & 8) != 0) {
                str = done.srtUrl;
            }
            return done.copy(num, num2, l, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final Integer getTotal() {
            return this.total;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final Integer getDoneCount() {
            return this.doneCount;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final Long getSrtId() {
            return this.srtId;
        }

        /* JADX INFO: renamed from: component4, reason: from getter */
        public final String getSrtUrl() {
            return this.srtUrl;
        }

        public final Done copy(Integer total, Integer doneCount, Long srtId, String srtUrl) {
            return new Done(total, doneCount, srtId, srtUrl);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Done)) {
                return false;
            }
            Done done = (Done) other;
            return Intrinsics.areEqual(this.total, done.total) && Intrinsics.areEqual(this.doneCount, done.doneCount) && Intrinsics.areEqual(this.srtId, done.srtId) && Intrinsics.areEqual(this.srtUrl, done.srtUrl);
        }

        public int hashCode() {
            Integer num = this.total;
            int iHashCode = (num == null ? 0 : num.hashCode()) * 31;
            Integer num2 = this.doneCount;
            int iHashCode2 = (iHashCode + (num2 == null ? 0 : num2.hashCode())) * 31;
            Long l = this.srtId;
            int iHashCode3 = (iHashCode2 + (l == null ? 0 : l.hashCode())) * 31;
            String str = this.srtUrl;
            return iHashCode3 + (str != null ? str.hashCode() : 0);
        }

        public String toString() {
            return "Done(total=" + this.total + ", doneCount=" + this.doneCount + ", srtId=" + this.srtId + ", srtUrl=" + this.srtUrl + ")";
        }

        public final Integer getTotal() {
            return this.total;
        }

        public final Integer getDoneCount() {
            return this.doneCount;
        }

        public final Long getSrtId() {
            return this.srtId;
        }

        public final String getSrtUrl() {
            return this.srtUrl;
        }

        public Done(Integer num, Integer num2, Long l, String str) {
            super(null);
            this.total = num;
            this.doneCount = num2;
            this.srtId = l;
            this.srtUrl = str;
        }
    }

    /* JADX INFO: compiled from: TranslateSubtitleStreamParser.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Error;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent;", "code", "", NotificationCompat.CATEGORY_MESSAGE, "", "<init>", "(ILjava/lang/String;)V", "getCode", "()I", "getMsg", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Error extends TranslateSubtitleStreamEvent {
        private final int code;
        private final String msg;

        public static /* synthetic */ Error copy$default(Error error, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = error.code;
            }
            if ((i2 & 2) != 0) {
                str = error.msg;
            }
            return error.copy(i, str);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final String getMsg() {
            return this.msg;
        }

        public final Error copy(int code, String msg) {
            return new Error(code, msg);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Error)) {
                return false;
            }
            Error error = (Error) other;
            return this.code == error.code && Intrinsics.areEqual(this.msg, error.msg);
        }

        public int hashCode() {
            int i = this.code * 31;
            String str = this.msg;
            return i + (str == null ? 0 : str.hashCode());
        }

        public String toString() {
            return "Error(code=" + this.code + ", msg=" + this.msg + ")";
        }

        public Error(int i, String str) {
            super(null);
            this.code = i;
            this.msg = str;
        }

        public final int getCode() {
            return this.code;
        }

        public final String getMsg() {
            return this.msg;
        }
    }

    /* JADX INFO: compiled from: TranslateSubtitleStreamParser.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent$Ignore;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleStreamEvent;", "<init>", "()V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Ignore extends TranslateSubtitleStreamEvent {
        public static final Ignore INSTANCE = new Ignore();

        private Ignore() {
            super(null);
        }
    }
}
