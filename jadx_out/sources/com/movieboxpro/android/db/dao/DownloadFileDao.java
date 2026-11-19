package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.DownloadFile;
import java.util.List;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: loaded from: classes5.dex */
public interface DownloadFileDao {
    void clear();

    void deleteDownloadFile(DownloadFile downloadFile);

    void deleteDownloadFiles(List<DownloadFile> downloadFiles);

    List<DownloadFile> findDownloadFileById(String id);

    DownloadFile findDownloadFileByIdSeasonEpisode(int boxType, String id, int season, int episode);

    Flow<List<DownloadFile>> findDownloadFileByIdSeasonFlow(int boxType, String id, int season);

    List<DownloadFile> findDownloadFileBySeason(int boxType, String id, int season);

    DownloadFile findDownloadFileByUrl(String url);

    DownloadFile findDownloadFileDownloadId(String downloadId);

    DownloadFile findDownloadFileDownloadIdAndId(String downloadId, String id);

    Flow<DownloadFile> findDownloadFileDownloadIdFlow(String downloadId);

    List<DownloadFile> findDownloadedByType(int status);

    Flow<List<DownloadFile>> findDownloadedByTypeFlow(int status);

    DownloadFile findDownloadedMovie(int boxType, String id, int status);

    List<DownloadFile> findDownloadedTv(int boxType, String id, int status);

    List<DownloadFile> findDownloadingFile();

    Flow<List<DownloadFile>> findDownloadingFileFlow();

    List<DownloadFile> findEpisodes(String id, int boxType, int status);

    List<DownloadFile> findHlsFiles();

    List<DownloadFile> findSeasonsFile(int boxType, String id);

    Flow<List<DownloadFile>> findSeasonsFileFlow(int boxType, String id);

    List<DownloadFile> getAllDownloadFiles();

    Flow<Long> getDownloadingNumFlow();

    void insert(DownloadFile downloadFile);

    void update(DownloadFile downloadFile);

    void updateDownloadFile(DownloadFile downloadFile);
}
