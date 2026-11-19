package com.movieboxpro.android.utils;

import android.net.Uri;
import androidx.documentfile.provider.DocumentFile;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.entity.ConnectUsbDevice;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.view.fragment.OtgFragment;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: DownloadLocationHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \b2\u00020\u0001:\u0001\bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/utils/DownloadLocationHelper;", "", "<init>", "()V", "hasWritePermission", "", FileDownloadService.PARAMS_KEY_PATH, "", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadLocationHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DownloadLocationHelper> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new Function0() { // from class: com.movieboxpro.android.utils.DownloadLocationHelper$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DownloadLocationHelper.instance_delegate$lambda$0();
        }
    });

    private DownloadLocationHelper() {
    }

    /* JADX INFO: compiled from: DownloadLocationHelper.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\u0004\u001a\u00020\u00058FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\u0006\u0010\u0007¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/utils/DownloadLocationHelper$Companion;", "", "<init>", "()V", "instance", "Lcom/movieboxpro/android/utils/DownloadLocationHelper;", "getInstance", "()Lcom/movieboxpro/android/utils/DownloadLocationHelper;", "instance$delegate", "Lkotlin/Lazy;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadLocationHelper getInstance() {
            return (DownloadLocationHelper) DownloadLocationHelper.instance$delegate.getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DownloadLocationHelper instance_delegate$lambda$0() {
        return new DownloadLocationHelper();
    }

    public final boolean hasWritePermission(String path) {
        Intrinsics.checkNotNullParameter(path, "path");
        ConnectUsbDevice connectUsbDeviceLoadByPath = App.getDB().connectUsbDeviceDao().loadByPath(path);
        if (connectUsbDeviceLoadByPath == null) {
            return false;
        }
        String downloadPath = connectUsbDeviceLoadByPath.getDownloadPath();
        Intrinsics.checkNotNullExpressionValue(downloadPath, "getDownloadPath(...)");
        if (StringsKt.isBlank(downloadPath)) {
            return false;
        }
        try {
            DocumentFile documentFileFromTreeUri = DocumentFile.fromTreeUri(App.getContext(), Uri.parse(connectUsbDeviceLoadByPath.getDownloadPath()));
            if (documentFileFromTreeUri == null || !documentFileFromTreeUri.canWrite()) {
                return false;
            }
            OtgFragment.INSTANCE.setOtgRoot(Uri.parse(connectUsbDeviceLoadByPath.getDownloadPath()));
            return true;
        } catch (Exception unused) {
            return false;
        }
    }
}
