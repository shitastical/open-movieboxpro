package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.SubtitleDelayRecord;

/* JADX INFO: loaded from: classes5.dex */
public interface SubtitleDelayRecordDao {
    void clear();

    SubtitleDelayRecord findDelayRecordBySid(String sid);

    long insert(SubtitleDelayRecord subtitleDelayRecord);
}
