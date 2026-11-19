package com.movieboxpro.android.utils;

import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.model.BaseMediaModel;
import com.movieboxpro.android.model.BeanKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DownloadFileUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/utils/DownloadFileUtils;", "", "<init>", "()V", "toMediaDownloadFile", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "downloadFile", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadFileUtils {
    public static final DownloadFileUtils INSTANCE = new DownloadFileUtils();

    private DownloadFileUtils() {
    }

    @JvmStatic
    public static final BaseMediaModel.DownloadFile toMediaDownloadFile(DownloadFile downloadFile) {
        Intrinsics.checkNotNullParameter(downloadFile, "downloadFile");
        return BeanKt.toMediaDownloadFile(downloadFile);
    }
}
