package com.movieboxpro.android.utils;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.measurement.api.AppMeasurementSdk;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import com.movieboxpro.android.service.DownloadService;
import com.movieboxpro.android.service.FileDownloadService;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: VlcPlaybackRecovery.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000f\b\u0000\u0018\u00002\u00020\u0001:\u0002%&BI\u0012\u001e\u0010\u0002\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\t\u0012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\nJ\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0004J\u0006\u0010\u001f\u001a\u00020\u0006J\u0006\u0010 \u001a\u00020\u000fJ\u0006\u0010!\u001a\u00020\u0006J\b\u0010\"\u001a\u0004\u0018\u00010\nJ\b\u0010#\u001a\u00020\u0006H\u0002J\b\u0010$\u001a\u00020\u0006H\u0002R&\u0010\u0002\u001a\u001a\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0004\u0012\u00020\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00060\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/movieboxpro/android/utils/VlcPlaybackRecovery;", "", "schedule", "Lkotlin/Function2;", "", "Lkotlin/Function0;", "", "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;", "reopen", "Lkotlin/Function1;", "Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;", "onExhausted", "<init>", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;)V", "value", "", "isExhausted", "()Z", FirebaseAnalytics.Param.SOURCE, "pending", "generation", AppMeasurementSdk.ConditionalUserProperty.ACTIVE, "needsRecovery", "attempts", "", "lastPositionMs", "continuousProgressMs", "startSource", "onPrematureEnd", "positionMs", "onProgress", DownloadService.PAUSE, "resume", "stop", "sourceForRetry", "scheduleRecovery", "cancelPending", "Source", "Cancellation", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VlcPlaybackRecovery {
    private boolean active;
    private int attempts;
    private long continuousProgressMs;
    private long generation;
    private volatile boolean isExhausted;
    private long lastPositionMs;
    private boolean needsRecovery;
    private final Function0<Unit> onExhausted;
    private Cancellation pending;
    private final Function1<Source, Unit> reopen;
    private final Function2<Long, Function0<Unit>, Cancellation> schedule;
    private Source source;

    /* JADX INFO: compiled from: VlcPlaybackRecovery.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004À\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Cancellation;", "", "cancel", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Cancellation {
        void cancel();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VlcPlaybackRecovery(Function2<? super Long, ? super Function0<Unit>, ? extends Cancellation> schedule, Function1<? super Source, Unit> reopen, Function0<Unit> onExhausted) {
        Intrinsics.checkNotNullParameter(schedule, "schedule");
        Intrinsics.checkNotNullParameter(reopen, "reopen");
        Intrinsics.checkNotNullParameter(onExhausted, "onExhausted");
        this.schedule = schedule;
        this.reopen = reopen;
        this.onExhausted = onExhausted;
    }

    /* JADX INFO: compiled from: VlcPlaybackRecovery.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J'\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0019"}, d2 = {"Lcom/movieboxpro/android/utils/VlcPlaybackRecovery$Source;", "", FileDownloadService.PARAMS_KEY_PATH, "", "positionMs", "", "useSurfaceView", "", "<init>", "(Ljava/lang/String;JZ)V", "getPath", "()Ljava/lang/String;", "getPositionMs", "()J", "getUseSurfaceView", "()Z", "component1", "component2", "component3", "copy", "equals", Video.TypeInfo.OTHER, "hashCode", "", "toString", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class Source {
        private final String path;
        private final long positionMs;
        private final boolean useSurfaceView;

        public static /* synthetic */ Source copy$default(Source source, String str, long j, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = source.path;
            }
            if ((i & 2) != 0) {
                j = source.positionMs;
            }
            if ((i & 4) != 0) {
                z = source.useSurfaceView;
            }
            return source.copy(str, j, z);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final String getPath() {
            return this.path;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final long getPositionMs() {
            return this.positionMs;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final boolean getUseSurfaceView() {
            return this.useSurfaceView;
        }

        public final Source copy(String path, long positionMs, boolean useSurfaceView) {
            Intrinsics.checkNotNullParameter(path, "path");
            return new Source(path, positionMs, useSurfaceView);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Source)) {
                return false;
            }
            Source source = (Source) other;
            return Intrinsics.areEqual(this.path, source.path) && this.positionMs == source.positionMs && this.useSurfaceView == source.useSurfaceView;
        }

        public int hashCode() {
            return (((this.path.hashCode() * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.positionMs)) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.useSurfaceView);
        }

        public String toString() {
            return "Source(path=" + this.path + ", positionMs=" + this.positionMs + ", useSurfaceView=" + this.useSurfaceView + ")";
        }

        public Source(String path, long j, boolean z) {
            Intrinsics.checkNotNullParameter(path, "path");
            this.path = path;
            this.positionMs = j;
            this.useSurfaceView = z;
        }

        public final String getPath() {
            return this.path;
        }

        public final long getPositionMs() {
            return this.positionMs;
        }

        public final boolean getUseSurfaceView() {
            return this.useSurfaceView;
        }
    }

    /* JADX INFO: renamed from: isExhausted, reason: from getter */
    public final boolean getIsExhausted() {
        return this.isExhausted;
    }

    public final synchronized void startSource(Source source) {
        Intrinsics.checkNotNullParameter(source, "source");
        cancelPending();
        this.source = source;
        this.active = true;
        this.needsRecovery = false;
        this.isExhausted = false;
        this.attempts = 0;
        this.lastPositionMs = source.getPositionMs();
        this.continuousProgressMs = 0L;
    }

    public final synchronized void onPrematureEnd(long positionMs) {
        if (this.active && !this.isExhausted && this.pending == null) {
            this.lastPositionMs = RangesKt.coerceAtLeast(positionMs, 0L);
            this.continuousProgressMs = 0L;
            this.needsRecovery = true;
            scheduleRecovery();
        }
    }

    public final synchronized void onProgress(long positionMs) {
        if (this.active && !this.isExhausted && !this.needsRecovery) {
            long j = positionMs - this.lastPositionMs;
            this.lastPositionMs = positionMs;
            if (1 <= j && j < 5001) {
                this.continuousProgressMs += j;
            } else if (j != 0) {
                this.continuousProgressMs = 0L;
            }
            if (this.continuousProgressMs >= NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS) {
                this.attempts = 0;
                this.continuousProgressMs = 0L;
            }
        }
    }

    public final synchronized void pause() {
        cancelPending();
        this.active = false;
    }

    public final synchronized boolean resume() {
        if (this.isExhausted) {
            return false;
        }
        this.active = true;
        if (!this.needsRecovery) {
            return true;
        }
        scheduleRecovery();
        return false;
    }

    public final synchronized void stop() {
        pause();
        this.needsRecovery = false;
        this.source = null;
    }

    public final synchronized Source sourceForRetry() {
        Source source;
        source = this.source;
        return source != null ? Source.copy$default(source, null, this.lastPositionMs, false, 5, null) : null;
    }

    private final void scheduleRecovery() {
        Source source = this.source;
        if (source != null && this.pending == null) {
            if (this.attempts >= 2) {
                this.isExhausted = true;
                this.active = false;
                this.needsRecovery = false;
                this.onExhausted.invoke();
                return;
            }
            final long j = this.generation + 1;
            this.generation = j;
            final Source sourceCopy$default = Source.copy$default(source, null, this.lastPositionMs, false, 5, null);
            this.pending = this.schedule.invoke(Long.valueOf(1000 << this.attempts), new Function0() { // from class: com.movieboxpro.android.utils.VlcPlaybackRecovery$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return VlcPlaybackRecovery.scheduleRecovery$lambda$1(this.f$0, j, sourceCopy$default);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit scheduleRecovery$lambda$1(VlcPlaybackRecovery vlcPlaybackRecovery, long j, Source source) {
        synchronized (vlcPlaybackRecovery) {
            if (j == vlcPlaybackRecovery.generation && vlcPlaybackRecovery.active && !vlcPlaybackRecovery.isExhausted && vlcPlaybackRecovery.pending != null) {
                vlcPlaybackRecovery.pending = null;
                vlcPlaybackRecovery.generation++;
                vlcPlaybackRecovery.attempts++;
                vlcPlaybackRecovery.needsRecovery = false;
                vlcPlaybackRecovery.reopen.invoke(source);
            }
            Unit unit = Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    private final void cancelPending() {
        this.generation++;
        Cancellation cancellation = this.pending;
        if (cancellation != null) {
            cancellation.cancel();
        }
        this.pending = null;
    }
}
