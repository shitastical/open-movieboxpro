package com.movieboxpro.android.livedata;

import com.kunminx.architecture.ui.callback.UnPeekLiveData;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadRuleChangedLiveData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00052\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0005B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0006"}, d2 = {"Lcom/movieboxpro/android/livedata/DownloadRuleChangedLiveData;", "Lcom/kunminx/architecture/ui/callback/UnPeekLiveData;", "", "<init>", "()V", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadRuleChangedLiveData extends UnPeekLiveData<String> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static DownloadRuleChangedLiveData sInstance;

    /* JADX INFO: compiled from: DownloadRuleChangedLiveData.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0006\u001a\u00020\u0005H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/movieboxpro/android/livedata/DownloadRuleChangedLiveData$Companion;", "", "<init>", "()V", "sInstance", "Lcom/movieboxpro/android/livedata/DownloadRuleChangedLiveData;", "get", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadRuleChangedLiveData get() {
            DownloadRuleChangedLiveData downloadRuleChangedLiveData;
            if (DownloadRuleChangedLiveData.sInstance != null) {
                downloadRuleChangedLiveData = DownloadRuleChangedLiveData.sInstance;
                if (downloadRuleChangedLiveData == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("sInstance");
                    downloadRuleChangedLiveData = null;
                }
            } else {
                downloadRuleChangedLiveData = new DownloadRuleChangedLiveData();
            }
            DownloadRuleChangedLiveData.sInstance = downloadRuleChangedLiveData;
            DownloadRuleChangedLiveData downloadRuleChangedLiveData2 = DownloadRuleChangedLiveData.sInstance;
            if (downloadRuleChangedLiveData2 != null) {
                return downloadRuleChangedLiveData2;
            }
            Intrinsics.throwUninitializedPropertyAccessException("sInstance");
            return null;
        }
    }
}
