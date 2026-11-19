package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.HlsDownload;
import kotlin.Metadata;

/* JADX INFO: compiled from: HlsDownloadDao.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0007\u001a\u00020\bH'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H'¨\u0006\nÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/db/dao/HlsDownloadDao;", "", "insertOrReplace", "", "hlsDownload", "Lcom/movieboxpro/android/db/entity/HlsDownload;", "findByDownloadId", "downloadId", "", "delete", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface HlsDownloadDao {
    void delete(HlsDownload hlsDownload);

    HlsDownload findByDownloadId(String downloadId);

    void insertOrReplace(HlsDownload hlsDownload);
}
