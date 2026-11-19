package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.TestNetRecode;

/* JADX INFO: loaded from: classes5.dex */
public interface TestNetRecodeDao {
    void clear();

    void delete(TestNetRecode playRecode);

    void deleteById(int ids);

    TestNetRecode findAll(int ids);

    void insert(TestNetRecode playRecode);

    void update(TestNetRecode playRecode);
}
