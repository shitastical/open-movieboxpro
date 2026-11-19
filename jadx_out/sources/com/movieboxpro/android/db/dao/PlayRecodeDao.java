package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.PlayRecode;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface PlayRecodeDao {
    void clear();

    void delete(PlayRecode playRecode);

    void deleteById(String movieId);

    void deleteByTid(String privateid);

    PlayRecode findByEpisode(int type, String id, int season, int episode);

    PlayRecode findByType(int type, String privateid);

    List<PlayRecode> findByType(int type);

    PlayRecode findByTypeid(int type, String id);

    List<PlayRecode> getAll();

    void insert(PlayRecode playRecode);

    void update(PlayRecode playRecode);
}
