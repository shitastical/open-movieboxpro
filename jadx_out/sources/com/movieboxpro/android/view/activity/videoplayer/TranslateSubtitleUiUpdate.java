package com.movieboxpro.android.view.activity.videoplayer;

import com.dl7.player.utils.SRT;
import com.jeffmony.downloader.model.Video;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TranslateSubtitleStreamReducer.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b0\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate;", "", "<init>", "()V", "Append", "Finished", "Failed", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate$Append;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate$Failed;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate$Finished;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class TranslateSubtitleUiUpdate {
    public /* synthetic */ TranslateSubtitleUiUpdate(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: TranslateSubtitleStreamReducer.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0006HÆ\u0003J#\u0010\u000f\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate$Append;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate;", "models", "", "Lcom/dl7/player/utils/SRT;", "doneCount", "", "<init>", "(Ljava/util/List;I)V", "getModels", "()Ljava/util/List;", "getDoneCount", "()I", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Append extends TranslateSubtitleUiUpdate {
        private final int doneCount;
        private final List<SRT> models;

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ Append copy$default(Append append, List list, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                list = append.models;
            }
            if ((i2 & 2) != 0) {
                i = append.doneCount;
            }
            return append.copy(list, i);
        }

        public final List<SRT> component1() {
            return this.models;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getDoneCount() {
            return this.doneCount;
        }

        public final Append copy(List<? extends SRT> models, int doneCount) {
            Intrinsics.checkNotNullParameter(models, "models");
            return new Append(models, doneCount);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Append)) {
                return false;
            }
            Append append = (Append) other;
            return Intrinsics.areEqual(this.models, append.models) && this.doneCount == append.doneCount;
        }

        public int hashCode() {
            return (this.models.hashCode() * 31) + this.doneCount;
        }

        public String toString() {
            return "Append(models=" + this.models + ", doneCount=" + this.doneCount + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        public Append(List<? extends SRT> models, int i) {
            super(null);
            Intrinsics.checkNotNullParameter(models, "models");
            this.models = models;
            this.doneCount = i;
        }

        public final int getDoneCount() {
            return this.doneCount;
        }

        public final List<SRT> getModels() {
            return this.models;
        }
    }

    private TranslateSubtitleUiUpdate() {
    }

    /* JADX INFO: compiled from: TranslateSubtitleStreamReducer.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate$Finished;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate;", "<init>", "()V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Finished extends TranslateSubtitleUiUpdate {
        public static final Finished INSTANCE = new Finished();

        private Finished() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: TranslateSubtitleStreamReducer.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate$Failed;", "Lcom/movieboxpro/android/view/activity/videoplayer/TranslateSubtitleUiUpdate;", "message", "", "code", "", "<init>", "(Ljava/lang/String;I)V", "getMessage", "()Ljava/lang/String;", "getCode", "()I", "component1", "component2", "copy", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Failed extends TranslateSubtitleUiUpdate {
        private final int code;
        private final String message;

        public static /* synthetic */ Failed copy$default(Failed failed, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = failed.message;
            }
            if ((i2 & 2) != 0) {
                i = failed.code;
            }
            return failed.copy(str, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCode() {
            return this.code;
        }

        public final Failed copy(String message, int code) {
            Intrinsics.checkNotNullParameter(message, "message");
            return new Failed(message, code);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Failed)) {
                return false;
            }
            Failed failed = (Failed) other;
            return Intrinsics.areEqual(this.message, failed.message) && this.code == failed.code;
        }

        public int hashCode() {
            return (this.message.hashCode() * 31) + this.code;
        }

        public String toString() {
            return "Failed(message=" + this.message + ", code=" + this.code + ")";
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Failed(String message, int i) {
            super(null);
            Intrinsics.checkNotNullParameter(message, "message");
            this.message = message;
            this.code = i;
        }

        public final int getCode() {
            return this.code;
        }

        public final String getMessage() {
            return this.message;
        }
    }
}
