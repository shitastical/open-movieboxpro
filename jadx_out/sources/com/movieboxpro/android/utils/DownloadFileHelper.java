package com.movieboxpro.android.utils;

import androidx.core.app.NotificationCompat;
import com.jeffmony.downloader.database.VideoDownloadSQLiteHelper;
import com.liulishuo.okdownload.StatusUtil;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.db.dao.DownloadFileDao;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.service.FileDownloadService;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: DatabaseHelper.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0014\u0018\u0000 D2\u00020\u0001:\u0001DB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J¬\u0001\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\t2\b\u0010\f\u001a\u0004\u0018\u00010\t2\b\u0010\r\u001a\u0004\u0018\u00010\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\t2\b\u0010\u0010\u001a\u0004\u0018\u00010\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\t2\b\b\u0002\u0010\u0016\u001a\u00020\u00072\b\b\u0002\u0010\u0017\u001a\u00020\u00072\b\b\u0002\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0005J\u0018\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u0018\u001a\u00020\u00072\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0018\u0010\u001f\u001a\u00020\u001c2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010 \u001a\u00020\u0007J(\u0010!\u001a\u00020\u001c2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\"\u001a\u00020\u00142\u0006\u0010#\u001a\u00020\u00142\u0006\u0010$\u001a\u00020\tJ\u0018\u0010%\u001a\u00020\u001c2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010&\u001a\u00020\tJ\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00140(J\u0018\u0010)\u001a\u00020\u001c2\b\u0010\n\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0013\u001a\u00020\u0014J\u0012\u0010*\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0012\u0010+\u001a\u0004\u0018\u00010\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\tJ\u0018\u0010,\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010(2\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0016\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00050.2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0016\u0010/\u001a\b\u0012\u0004\u0012\u00020\u00050.2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u001c\u00100\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0005010(2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u001c\u00102\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\t2\b\u00103\u001a\u0004\u0018\u00010\tJ\"\u00104\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0007J$\u00105\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0005010(2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\u0007J\u001e\u00106\u001a\b\u0012\u0004\u0012\u00020\u00050.2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0016\u001a\u00020\u0007J\u0010\u00107\u001a\u00020\u001c2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00050.J\u0012\u00109\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0005010(J\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000501J\f\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00050.J\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\u00050.J\u0012\u0010=\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u0005010(J&\u0010>\u001a\b\u0012\u0004\u0012\u00020\u00050.2\b\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u0007J\u0016\u0010?\u001a\b\u0012\u0004\u0012\u00020\u00050.2\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0012\u0010@\u001a\u0004\u0018\u00010\u00052\b\u0010\b\u001a\u0004\u0018\u00010\tJ\u0010\u0010A\u001a\u00020\u001c2\b\u0010\n\u001a\u0004\u0018\u00010\tJ\u0014\u0010B\u001a\u00020\u001c2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\u000501¨\u0006E"}, d2 = {"Lcom/movieboxpro/android/utils/DownloadFileHelper;", "", "<init>", "()V", "addDownloadFile", "Lcom/movieboxpro/android/db/entity/DownloadFile;", "boxType", "", "id", "", "downloadId", "url", FileDownloadService.PARAMS_KEY_PATH, "fileName", "videoName", "episodeName", "poster", "thumb", "quality", "size", "", "imdbId", DealWatchNextService.SEASON, DealWatchNextService.EPISODE, NotificationCompat.CATEGORY_STATUS, "hls", "", "updateDownloadFile", "", "downloadFile", "updateDownloadStatus", "updateDownloadPercentProgress", VideoDownloadSQLiteHelper.Columns.PERCENT, "updateDownloadProgress", "downloadSize", "totalSize", "speed", "updateDownloadError", NotificationCompat.CATEGORY_MESSAGE, "getDownloadingNumFlow", "Lkotlinx/coroutines/flow/Flow;", "updateFileSize", "findDownloadFileByDownloadId", "findDownloadFileByUrl", "findDownloadFileByDownloadIdFlow", "findSeasonsFile", "", "findMovieFile", "findMovieFileFlow", "", "findDownloadFileByDownloadIdAndTid", "tid", "findDownloadFileByTidSeasonEpisode", "findDownloadFileByIdSeasonFlow", "findDownloadFileBySeason", "deleteDownloadFileById", "getDownloadedFiles", "getDownloadedFilesFlow", "getHlsDownloadFiles", "getDownloadFiles", "getDownloadingFiles", "getDownloadingFilesFlow", "findEpisodeNum", "findDownloadedTv", "findDownloadedMovie", "deleteDownloadFileByDownloadId", "deleteDownloadFiles", "downloadFiles", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class DownloadFileHelper {
    private static DownloadFileDao downloadFileDao;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<DownloadFileHelper> instance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, new Function0() { // from class: com.movieboxpro.android.utils.DownloadFileHelper$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return DownloadFileHelper.instance_delegate$lambda$2();
        }
    });

    private DownloadFileHelper() {
    }

    /* JADX INFO: compiled from: DatabaseHelper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/utils/DownloadFileHelper$Companion;", "", "<init>", "()V", "downloadFileDao", "Lcom/movieboxpro/android/db/dao/DownloadFileDao;", "instance", "Lcom/movieboxpro/android/utils/DownloadFileHelper;", "getInstance", "()Lcom/movieboxpro/android/utils/DownloadFileHelper;", "instance$delegate", "Lkotlin/Lazy;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final DownloadFileHelper getInstance() {
            return (DownloadFileHelper) DownloadFileHelper.instance$delegate.getValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final DownloadFileHelper instance_delegate$lambda$2() {
        downloadFileDao = App.getDB().downloadFileDao();
        return new DownloadFileHelper();
    }

    public final DownloadFile addDownloadFile(int boxType, String id, String downloadId, String url, String path, String fileName, String videoName, String episodeName, String poster, String thumb, String quality, long size, String imdbId, int season, int episode, int status, boolean hls) {
        DownloadFile downloadFile = new DownloadFile();
        downloadFile.setId(id);
        downloadFile.setBoxType(boxType);
        downloadFile.setMid(downloadFile.getMid());
        downloadFile.setTid(downloadFile.getTid());
        downloadFile.setUrl(url);
        downloadFile.setPath(path);
        downloadFile.setFileName(fileName);
        downloadFile.setThumb(thumb);
        downloadFile.setVideoName(videoName);
        downloadFile.setPoster(poster);
        downloadFile.setSeason(season);
        downloadFile.setEpisode(episode);
        downloadFile.setDownloadId(downloadId == null ? "" : downloadId);
        downloadFile.setQuality(quality);
        downloadFile.setEpisodeName(episodeName);
        downloadFile.setStatus(status);
        downloadFile.setSize(size);
        downloadFile.setHls(hls);
        downloadFile.setImdbId(imdbId);
        downloadFile.setDownloadTime(System.currentTimeMillis() / ((long) 1000));
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        downloadFileDao2.insert(downloadFile);
        return downloadFile;
    }

    public final void updateDownloadFile(DownloadFile downloadFile) {
        Intrinsics.checkNotNullParameter(downloadFile, "downloadFile");
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        downloadFileDao2.update(downloadFile);
    }

    public final void updateDownloadStatus(int status, String downloadId) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        DownloadFileDao downloadFileDao3 = null;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        DownloadFile downloadFileFindDownloadFileDownloadId = downloadFileDao2.findDownloadFileDownloadId(downloadId);
        if (downloadFileFindDownloadFileDownloadId != null) {
            downloadFileFindDownloadFileDownloadId.setStatus(status);
            DownloadFileDao downloadFileDao4 = downloadFileDao;
            if (downloadFileDao4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            } else {
                downloadFileDao3 = downloadFileDao4;
            }
            downloadFileDao3.updateDownloadFile(downloadFileFindDownloadFileDownloadId);
        }
    }

    public final void updateDownloadPercentProgress(String downloadId, int percent) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        DownloadFileDao downloadFileDao3 = null;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        DownloadFile downloadFileFindDownloadFileDownloadId = downloadFileDao2.findDownloadFileDownloadId(downloadId);
        if (downloadFileFindDownloadFileDownloadId != null) {
            downloadFileFindDownloadFileDownloadId.setStatus(6);
            downloadFileFindDownloadFileDownloadId.setProgress(percent);
            DownloadFileDao downloadFileDao4 = downloadFileDao;
            if (downloadFileDao4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            } else {
                downloadFileDao3 = downloadFileDao4;
            }
            downloadFileDao3.updateDownloadFile(downloadFileFindDownloadFileDownloadId);
        }
    }

    public final void updateDownloadProgress(String downloadId, long downloadSize, long totalSize, String speed) {
        Intrinsics.checkNotNullParameter(speed, "speed");
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        DownloadFileDao downloadFileDao3 = null;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        DownloadFile downloadFileFindDownloadFileDownloadId = downloadFileDao2.findDownloadFileDownloadId(downloadId);
        if (downloadFileFindDownloadFileDownloadId != null) {
            downloadFileFindDownloadFileDownloadId.setStatus(6);
            downloadFileFindDownloadFileDownloadId.setSpeed(speed);
            if (downloadSize != 0) {
                downloadFileFindDownloadFileDownloadId.setDownloadSize(downloadSize);
            }
            if (totalSize != 0) {
                downloadFileFindDownloadFileDownloadId.setSize(totalSize);
            }
            DownloadFileDao downloadFileDao4 = downloadFileDao;
            if (downloadFileDao4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            } else {
                downloadFileDao3 = downloadFileDao4;
            }
            downloadFileDao3.updateDownloadFile(downloadFileFindDownloadFileDownloadId);
        }
    }

    public final void updateDownloadError(String downloadId, String msg) {
        Intrinsics.checkNotNullParameter(msg, "msg");
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        DownloadFileDao downloadFileDao3 = null;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        DownloadFile downloadFileFindDownloadFileDownloadId = downloadFileDao2.findDownloadFileDownloadId(downloadId);
        if (downloadFileFindDownloadFileDownloadId != null) {
            downloadFileFindDownloadFileDownloadId.setStatus(7);
            downloadFileFindDownloadFileDownloadId.setErrorMsg(msg);
            DownloadFileDao downloadFileDao4 = downloadFileDao;
            if (downloadFileDao4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            } else {
                downloadFileDao3 = downloadFileDao4;
            }
            downloadFileDao3.updateDownloadFile(downloadFileFindDownloadFileDownloadId);
        }
    }

    public final Flow<Long> getDownloadingNumFlow() {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        Flow<Long> downloadingNumFlow = downloadFileDao2.getDownloadingNumFlow();
        Intrinsics.checkNotNullExpressionValue(downloadingNumFlow, "getDownloadingNumFlow(...)");
        return downloadingNumFlow;
    }

    public final void updateFileSize(String downloadId, long size) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        DownloadFileDao downloadFileDao3 = null;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        DownloadFile downloadFileFindDownloadFileDownloadId = downloadFileDao2.findDownloadFileDownloadId(downloadId);
        if (downloadFileFindDownloadFileDownloadId != null) {
            downloadFileFindDownloadFileDownloadId.setSize(size);
            downloadFileFindDownloadFileDownloadId.setStatus(6);
            DownloadFileDao downloadFileDao4 = downloadFileDao;
            if (downloadFileDao4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            } else {
                downloadFileDao3 = downloadFileDao4;
            }
            downloadFileDao3.updateDownloadFile(downloadFileFindDownloadFileDownloadId);
        }
    }

    public final DownloadFile findDownloadFileByDownloadId(String downloadId) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        return downloadFileDao2.findDownloadFileDownloadId(downloadId);
    }

    public final DownloadFile findDownloadFileByUrl(String url) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        return downloadFileDao2.findDownloadFileByUrl(url);
    }

    public final Flow<DownloadFile> findDownloadFileByDownloadIdFlow(String downloadId) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        return downloadFileDao2.findDownloadFileDownloadIdFlow(downloadId);
    }

    public final List<DownloadFile> findSeasonsFile(String id) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindSeasonsFile = downloadFileDao2.findSeasonsFile(2, id);
        Intrinsics.checkNotNullExpressionValue(listFindSeasonsFile, "findSeasonsFile(...)");
        return listFindSeasonsFile;
    }

    public final List<DownloadFile> findMovieFile(String id) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindSeasonsFile = downloadFileDao2.findSeasonsFile(1, id);
        Intrinsics.checkNotNullExpressionValue(listFindSeasonsFile, "findSeasonsFile(...)");
        return listFindSeasonsFile;
    }

    public final Flow<List<DownloadFile>> findMovieFileFlow(String id) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        Flow<List<DownloadFile>> flowFindSeasonsFileFlow = downloadFileDao2.findSeasonsFileFlow(1, id);
        Intrinsics.checkNotNullExpressionValue(flowFindSeasonsFileFlow, "findSeasonsFileFlow(...)");
        return flowFindSeasonsFileFlow;
    }

    public final DownloadFile findDownloadFileByDownloadIdAndTid(String downloadId, String tid) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        return downloadFileDao2.findDownloadFileDownloadIdAndId(downloadId, tid);
    }

    public final DownloadFile findDownloadFileByTidSeasonEpisode(String id, int season, int episode) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        return downloadFileDao2.findDownloadFileByIdSeasonEpisode(2, id, season, episode);
    }

    public final Flow<List<DownloadFile>> findDownloadFileByIdSeasonFlow(String id, int season) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        Flow<List<DownloadFile>> flowFindDownloadFileByIdSeasonFlow = downloadFileDao2.findDownloadFileByIdSeasonFlow(2, id, season);
        Intrinsics.checkNotNullExpressionValue(flowFindDownloadFileByIdSeasonFlow, "findDownloadFileByIdSeasonFlow(...)");
        return flowFindDownloadFileByIdSeasonFlow;
    }

    public final List<DownloadFile> findDownloadFileBySeason(String id, int season) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindDownloadFileBySeason = downloadFileDao2.findDownloadFileBySeason(2, id, season);
        Intrinsics.checkNotNullExpressionValue(listFindDownloadFileBySeason, "findDownloadFileBySeason(...)");
        return listFindDownloadFileBySeason;
    }

    public final void deleteDownloadFileById(String id) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        DownloadFileDao downloadFileDao3 = null;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindDownloadFileById = downloadFileDao2.findDownloadFileById(id);
        if (listFindDownloadFileById != null) {
            DownloadFileDao downloadFileDao4 = downloadFileDao;
            if (downloadFileDao4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            } else {
                downloadFileDao3 = downloadFileDao4;
            }
            downloadFileDao3.deleteDownloadFiles(listFindDownloadFileById);
        }
    }

    public final List<DownloadFile> getDownloadedFiles() {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindDownloadedByType = downloadFileDao2.findDownloadedByType(1);
        Intrinsics.checkNotNullExpressionValue(listFindDownloadedByType, "findDownloadedByType(...)");
        return listFindDownloadedByType;
    }

    public final Flow<List<DownloadFile>> getDownloadedFilesFlow() {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        Flow<List<DownloadFile>> flowFindDownloadedByTypeFlow = downloadFileDao2.findDownloadedByTypeFlow(1);
        Intrinsics.checkNotNullExpressionValue(flowFindDownloadedByTypeFlow, "findDownloadedByTypeFlow(...)");
        return flowFindDownloadedByTypeFlow;
    }

    public final List<DownloadFile> getHlsDownloadFiles() {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindHlsFiles = downloadFileDao2.findHlsFiles();
        Intrinsics.checkNotNullExpressionValue(listFindHlsFiles, "findHlsFiles(...)");
        return listFindHlsFiles;
    }

    public final List<DownloadFile> getDownloadFiles() {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> allDownloadFiles = downloadFileDao2.getAllDownloadFiles();
        Intrinsics.checkNotNullExpressionValue(allDownloadFiles, "getAllDownloadFiles(...)");
        return allDownloadFiles;
    }

    public final List<DownloadFile> getDownloadingFiles() {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindDownloadingFile = downloadFileDao2.findDownloadingFile();
        Intrinsics.checkNotNull(listFindDownloadingFile);
        for (DownloadFile downloadFile : listFindDownloadingFile) {
            BreakpointInfo currentInfo = StatusUtil.getCurrentInfo(downloadFile.getUrl(), downloadFile.getPath(), downloadFile.getFileName());
            long totalOffset = 0;
            if ((currentInfo != null ? currentInfo.getTotalLength() : 0L) != 0) {
                downloadFile.setSize(currentInfo != null ? currentInfo.getTotalLength() : 0L);
            }
            if (currentInfo != null) {
                totalOffset = currentInfo.getTotalOffset();
            }
            downloadFile.setDownloadSize(totalOffset);
        }
        return listFindDownloadingFile;
    }

    public final Flow<List<DownloadFile>> getDownloadingFilesFlow() {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        Flow<List<DownloadFile>> flowFindDownloadingFileFlow = downloadFileDao2.findDownloadingFileFlow();
        Intrinsics.checkNotNullExpressionValue(flowFindDownloadingFileFlow, "findDownloadingFileFlow(...)");
        return flowFindDownloadingFileFlow;
    }

    public final List<DownloadFile> findEpisodeNum(String id, int boxType, int status) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindEpisodes = downloadFileDao2.findEpisodes(id, boxType, status);
        Intrinsics.checkNotNullExpressionValue(listFindEpisodes, "findEpisodes(...)");
        return listFindEpisodes;
    }

    public final List<DownloadFile> findDownloadedTv(String id) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        List<DownloadFile> listFindDownloadedTv = downloadFileDao2.findDownloadedTv(2, id, 1);
        Intrinsics.checkNotNullExpressionValue(listFindDownloadedTv, "findDownloadedTv(...)");
        return listFindDownloadedTv;
    }

    public final DownloadFile findDownloadedMovie(String id) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        return downloadFileDao2.findDownloadedMovie(1, id, 1);
    }

    public final void deleteDownloadFileByDownloadId(String downloadId) {
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        DownloadFileDao downloadFileDao3 = null;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        DownloadFile downloadFileFindDownloadFileDownloadId = downloadFileDao2.findDownloadFileDownloadId(downloadId);
        if (downloadFileFindDownloadFileDownloadId != null) {
            DownloadFileDao downloadFileDao4 = downloadFileDao;
            if (downloadFileDao4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            } else {
                downloadFileDao3 = downloadFileDao4;
            }
            downloadFileDao3.deleteDownloadFile(downloadFileFindDownloadFileDownloadId);
        }
    }

    public final void deleteDownloadFiles(List<? extends DownloadFile> downloadFiles) {
        Intrinsics.checkNotNullParameter(downloadFiles, "downloadFiles");
        DownloadFileDao downloadFileDao2 = downloadFileDao;
        if (downloadFileDao2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("downloadFileDao");
            downloadFileDao2 = null;
        }
        downloadFileDao2.deleteDownloadFiles(downloadFiles);
    }
}
