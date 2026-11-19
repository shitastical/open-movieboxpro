package com.movieboxpro.android.listener;

import com.movieboxpro.android.service.DealWatchNextService;
import kotlin.Metadata;

/* JADX INFO: compiled from: AddWatchPlanListener.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J'\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\n¨\u0006\u000bÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/listener/AddWatchPlanListener;", "", "onWatchedChange", "", "watched", "", "onEpisodeOverChanged", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, "over", "(ILjava/lang/Integer;I)V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface AddWatchPlanListener {
    void onEpisodeOverChanged(int season, Integer episode, int over);

    void onWatchedChange(int watched);

    /* JADX INFO: renamed from: com.movieboxpro.android.listener.AddWatchPlanListener$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: AddWatchPlanListener.kt */
    public final /* synthetic */ class CC {
        public static void $default$onEpisodeOverChanged(AddWatchPlanListener _this, int i, Integer num, int i2) {
        }
    }

    /* JADX INFO: compiled from: AddWatchPlanListener.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static void onEpisodeOverChanged(AddWatchPlanListener addWatchPlanListener, int i, Integer num, int i2) {
            CC.$default$onEpisodeOverChanged(addWatchPlanListener, i, num, i2);
        }
    }
}
