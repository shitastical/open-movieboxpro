package com.movieboxpro.android.livedata;

import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.service.FFmpegDownloadService;
import com.movieboxpro.android.service.FileDownloadService;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadLiveData.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tJ\u001a\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eJ\u0016\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0016\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eJ\u0018\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u001b\u001a\u0004\u0018\u00010\u000eJ\u000e\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eJ\u000e\u0010\u001e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000eR\u001e\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00060\u0005j\b\u0012\u0004\u0012\u00020\u0006`\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/movieboxpro/android/livedata/DownloadLiveData;", "", "<init>", "()V", "listeners", "Ljava/util/ArrayList;", "Lcom/movieboxpro/android/service/FFmpegDownloadService$DownloadListener;", "Lkotlin/collections/ArrayList;", "downloadActionListener", "Lcom/movieboxpro/android/service/FFmpegDownloadService$DownloadActionListener;", "registerActionListener", "", "cancelDownload", FileDownloadService.PARAMS_KEY_PATH, "", "fid", "registerListener", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "onStartLoading", "onStart", "onProgress", "progress", "", "onComplete", "onFailed", NotificationCompat.CATEGORY_MESSAGE, "onPause", "onDelete", "onWaiting", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadLiveData {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DownloadLiveData> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new Function0() { // from class: com.movieboxpro.android.livedata.DownloadLiveData$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DownloadLiveData.instance_delegate$lambda$8();
        }
    });
    private FFmpegDownloadService.DownloadActionListener downloadActionListener;
    private final ArrayList<FFmpegDownloadService.DownloadListener> listeners = new ArrayList<>();

    public static final DownloadLiveData getInstance() {
        return INSTANCE.getInstance();
    }

    private DownloadLiveData() {
    }

    /* JADX INFO: compiled from: DownloadLiveData.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R!\u0010\u0004\u001a\u00020\u00058FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\t\u0010\n\u0012\u0004\b\u0006\u0010\u0003\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/movieboxpro/android/livedata/DownloadLiveData$Companion;", "", "<init>", "()V", "instance", "Lcom/movieboxpro/android/livedata/DownloadLiveData;", "getInstance$annotations", "getInstance", "()Lcom/movieboxpro/android/livedata/DownloadLiveData;", "instance$delegate", "Lkotlin/Lazy;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getInstance$annotations() {
        }

        private Companion() {
        }

        public final DownloadLiveData getInstance() {
            return (DownloadLiveData) DownloadLiveData.instance$delegate.getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DownloadLiveData instance_delegate$lambda$8() {
        return new DownloadLiveData();
    }

    public final void registerActionListener(FFmpegDownloadService.DownloadActionListener downloadActionListener) {
        Intrinsics.checkNotNullParameter(downloadActionListener, "downloadActionListener");
        this.downloadActionListener = downloadActionListener;
    }

    public final void cancelDownload(String path, String fid) {
        FFmpegDownloadService.DownloadActionListener downloadActionListener = this.downloadActionListener;
        if (downloadActionListener != null) {
            downloadActionListener.cancelDownload(path, fid);
        }
    }

    public final void registerListener(LifecycleOwner lifecycleOwner, final FFmpegDownloadService.DownloadListener listener) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "lifecycleOwner");
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listeners.add(listener);
        lifecycleOwner.getLifecycle().addObserver(new LifecycleEventObserver() { // from class: com.movieboxpro.android.livedata.DownloadLiveData.registerListener.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
                Intrinsics.checkNotNullParameter(source, "source");
                Intrinsics.checkNotNullParameter(event, "event");
                if (source.getLifecycle().getState() == Lifecycle.State.DESTROYED) {
                    DownloadLiveData.this.listeners.remove(listener);
                }
            }
        });
    }

    public final void onStartLoading(String fid) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FFmpegDownloadService.DownloadListener) it.next()).onStartLoading(fid);
        }
    }

    public final void onStart(String fid) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FFmpegDownloadService.DownloadListener) it.next()).onStart(fid);
        }
    }

    public final void onProgress(String fid, int progress) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FFmpegDownloadService.DownloadListener) it.next()).onProgress(fid, progress);
        }
    }

    public final void onComplete(String fid) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FFmpegDownloadService.DownloadListener) it.next()).onComplete(fid);
        }
    }

    public final void onFailed(String fid, String msg) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FFmpegDownloadService.DownloadListener) it.next()).onFailed(fid, msg);
        }
    }

    public final void onPause(String fid) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FFmpegDownloadService.DownloadListener) it.next()).onPause(fid);
        }
    }

    public final void onDelete(String fid) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FFmpegDownloadService.DownloadListener) it.next()).onDelete(fid);
        }
    }

    public final void onWaiting(String fid) {
        Intrinsics.checkNotNullParameter(fid, "fid");
        Iterator<T> it = this.listeners.iterator();
        while (it.hasNext()) {
            ((FFmpegDownloadService.DownloadListener) it.next()).onWaiting(fid);
        }
    }
}
