package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.Chart;

/* JADX INFO: loaded from: classes5.dex */
public interface ChartDao {
    void clear();

    void clearNull();

    void delete(Chart log);

    void deleteByBookID(String bookId);

    void insert(Chart log);

    Chart loadByID(String bookId);

    void update(Chart log);
}
