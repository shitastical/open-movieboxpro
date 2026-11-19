package com.movieboxpro.android.model;

import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.entity.DownloadFile;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Bean.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¨\u0006\u0003"}, d2 = {"toMediaDownloadFile", "Lcom/movieboxpro/android/model/BaseMediaModel$DownloadFile;", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class BeanKt {
    public static final BaseMediaModel.DownloadFile toMediaDownloadFile(DownloadFile downloadFile) {
        Intrinsics.checkNotNullParameter(downloadFile, "<this>");
        BaseMediaModel.DownloadFile downloadFile2 = new BaseMediaModel.DownloadFile();
        if (downloadFile.isHls()) {
            HlsDownloadDao hlsDownloadDao = App.getDB().hlsDownloadDao();
            String downloadId = downloadFile.getDownloadId();
            Intrinsics.checkNotNullExpressionValue(downloadId, "getDownloadId(...)");
            if (hlsDownloadDao.findByDownloadId(downloadId) != null) {
                downloadFile2.path = downloadFile.getPath();
            } else {
                downloadFile2.path = downloadFile.getUrl();
            }
        } else {
            downloadFile2.path = new File(downloadFile.getPath(), downloadFile.getFileName()).getPath();
        }
        if (downloadFile.getBoxType() == 1) {
            String downloadId2 = downloadFile.getDownloadId();
            Intrinsics.checkNotNullExpressionValue(downloadId2, "getDownloadId(...)");
            downloadFile2.mmfid = Integer.parseInt(downloadId2);
        } else {
            String downloadId3 = downloadFile.getDownloadId();
            Intrinsics.checkNotNullExpressionValue(downloadId3, "getDownloadId(...)");
            downloadFile2.tmfid = Integer.parseInt(downloadId3);
        }
        downloadFile2.local = true;
        downloadFile2.real_quality = downloadFile.getQuality();
        downloadFile2.filename = downloadFile.getFileName();
        downloadFile2.hls = downloadFile.isHls();
        downloadFile2.dateline = downloadFile.getDownloadTime();
        return downloadFile2;
    }
}
