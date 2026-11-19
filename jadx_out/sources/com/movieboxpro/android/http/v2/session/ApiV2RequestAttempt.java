package com.movieboxpro.android.http.v2.session;

import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.db.entity.HlsDownload$$ExternalSyntheticBackport0;
import com.movieboxpro.android.http.v2.ApiV2EnvironmentSnapshot;
import com.movieboxpro.android.http.v2.ApiV2RequestInterceptor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ApiSessionManager.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u000b\u0010\u0010\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J)\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u001aHÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/ApiV2RequestAttempt;", "", ApiV2RequestInterceptor.SESSION_MODE, "Lcom/movieboxpro/android/http/v2/session/SessionCredentials;", "environment", "Lcom/movieboxpro/android/http/v2/ApiV2EnvironmentSnapshot;", "generation", "", "<init>", "(Lcom/movieboxpro/android/http/v2/session/SessionCredentials;Lcom/movieboxpro/android/http/v2/ApiV2EnvironmentSnapshot;J)V", "getSession", "()Lcom/movieboxpro/android/http/v2/session/SessionCredentials;", "getEnvironment", "()Lcom/movieboxpro/android/http/v2/ApiV2EnvironmentSnapshot;", "getGeneration", "()J", "component1", "component2", "component3", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "", "toString", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class ApiV2RequestAttempt {
    private final ApiV2EnvironmentSnapshot environment;
    private final long generation;
    private final SessionCredentials session;

    public static /* synthetic */ ApiV2RequestAttempt copy$default(ApiV2RequestAttempt apiV2RequestAttempt, SessionCredentials sessionCredentials, ApiV2EnvironmentSnapshot apiV2EnvironmentSnapshot, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            sessionCredentials = apiV2RequestAttempt.session;
        }
        if ((i & 2) != 0) {
            apiV2EnvironmentSnapshot = apiV2RequestAttempt.environment;
        }
        if ((i & 4) != 0) {
            j = apiV2RequestAttempt.generation;
        }
        return apiV2RequestAttempt.copy(sessionCredentials, apiV2EnvironmentSnapshot, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final SessionCredentials getSession() {
        return this.session;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ApiV2EnvironmentSnapshot getEnvironment() {
        return this.environment;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getGeneration() {
        return this.generation;
    }

    public final ApiV2RequestAttempt copy(SessionCredentials session, ApiV2EnvironmentSnapshot environment, long generation) {
        Intrinsics.checkNotNullParameter(environment, "environment");
        return new ApiV2RequestAttempt(session, environment, generation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ApiV2RequestAttempt)) {
            return false;
        }
        ApiV2RequestAttempt apiV2RequestAttempt = (ApiV2RequestAttempt) other;
        return Intrinsics.areEqual(this.session, apiV2RequestAttempt.session) && Intrinsics.areEqual(this.environment, apiV2RequestAttempt.environment) && this.generation == apiV2RequestAttempt.generation;
    }

    public int hashCode() {
        SessionCredentials sessionCredentials = this.session;
        return ((((sessionCredentials == null ? 0 : sessionCredentials.hashCode()) * 31) + this.environment.hashCode()) * 31) + HlsDownload$$ExternalSyntheticBackport0.m(this.generation);
    }

    public String toString() {
        return "ApiV2RequestAttempt(session=" + this.session + ", environment=" + this.environment + ", generation=" + this.generation + ")";
    }

    public ApiV2RequestAttempt(SessionCredentials sessionCredentials, ApiV2EnvironmentSnapshot environment, long j) {
        Intrinsics.checkNotNullParameter(environment, "environment");
        this.session = sessionCredentials;
        this.environment = environment;
        this.generation = j;
    }

    public final SessionCredentials getSession() {
        return this.session;
    }

    public final ApiV2EnvironmentSnapshot getEnvironment() {
        return this.environment;
    }

    public final long getGeneration() {
        return this.generation;
    }
}
