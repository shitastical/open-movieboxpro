package com.movieboxpro.android.http.v2.session;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: ApiSessionManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0004\u0005\u0006B\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003\u0082\u0001\u0003\u0007\b\t¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult;", "", "<init>", "()V", "Success", "StaleGeneration", "LocalCommitFailure", "Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult$LocalCommitFailure;", "Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult$StaleGeneration;", "Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult$Success;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class RefreshCommitResult {
    public /* synthetic */ RefreshCommitResult(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    /* JADX INFO: compiled from: ApiSessionManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult$Success;", "Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult;", "<init>", "()V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Success extends RefreshCommitResult {
        public static final Success INSTANCE = new Success();

        private Success() {
            super(null);
        }
    }

    private RefreshCommitResult() {
    }

    /* JADX INFO: compiled from: ApiSessionManager.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult$StaleGeneration;", "Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult;", "<init>", "()V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class StaleGeneration extends RefreshCommitResult {
        public static final StaleGeneration INSTANCE = new StaleGeneration();

        private StaleGeneration() {
            super(null);
        }
    }

    /* JADX INFO: compiled from: ApiSessionManager.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult$LocalCommitFailure;", "Lcom/movieboxpro/android/http/v2/session/RefreshCommitResult;", "generation", "", "<init>", "(J)V", "getGeneration", "()J", "component1", "copy", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final /* data */ class LocalCommitFailure extends RefreshCommitResult {
        private final long generation;

        public static /* synthetic */ LocalCommitFailure copy$default(LocalCommitFailure localCommitFailure, long j, int i, Object obj) {
            if ((i & 1) != 0) {
                j = localCommitFailure.generation;
            }
            return localCommitFailure.copy(j);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final long getGeneration() {
            return this.generation;
        }

        public final LocalCommitFailure copy(long generation) {
            return new LocalCommitFailure(generation);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof LocalCommitFailure) && this.generation == ((LocalCommitFailure) other).generation;
        }

        public int hashCode() {
            return HlsDownload$$ExternalSyntheticBackport0.m(this.generation);
        }

        public String toString() {
            return "LocalCommitFailure(generation=" + this.generation + ")";
        }

        public LocalCommitFailure(long j) {
            super(null);
            this.generation = j;
        }

        public final long getGeneration() {
            return this.generation;
        }
    }
}
