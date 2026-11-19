package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.Download;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface DownloadDao {
    void clear();

    void delete(Download download);

    void deleteById(String movieId);

    void deleteByTid(String privateid);

    void deleteDownloads(List<Download> downloads);

    List<Download> findBySeason(String movieId, int type, int statue);

    List<Download> findByTid(String movieId, int type, int statue);

    Download findByType(int type, String privateid);

    List<Download> findByType(int type);

    Download findByTypes(int statue);

    List<Download> findId(int type, int statue);

    List<Download> getAll();

    void insert(Download download);

    List<Download> selectByTvId(String movie_id, int box_type);

    void update(Download download);
}
