package com.movieboxpro.android.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.movieboxpro.android.db.entity.HlsDownload;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class HlsDownloadDao_Impl implements HlsDownloadDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<HlsDownload> __deletionAdapterOfHlsDownload;
    private final EntityInsertionAdapter<HlsDownload> __insertionAdapterOfHlsDownload;

    public HlsDownloadDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfHlsDownload = new EntityInsertionAdapter<HlsDownload>(this, __db) { // from class: com.movieboxpro.android.db.dao.HlsDownloadDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `hls_download` (`id`,`m3u8Url`,`m3u8LocalPath`,`downloadId`,`videoUrl`,`audioUrl`,`audioEacUrl`,`playbackM3u8LocalPath`,`videoLocalPath`,`audioLocalPath`,`audioEacLocalPath`,`videoSize`,`audioSize`,`audioEacSize`,`videoSpeed`,`audioSpeed`,`audioEacSpeed`,`videoPercent`,`audioPercent`,`audioEacPercent`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, HlsDownload value) {
                stmt.bindLong(1, value.getId());
                if (value.getM3u8Url() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getM3u8Url());
                }
                if (value.getM3u8LocalPath() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getM3u8LocalPath());
                }
                if (value.getDownloadId() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getDownloadId());
                }
                if (value.getVideoUrl() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getVideoUrl());
                }
                if (value.getAudioUrl() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getAudioUrl());
                }
                if (value.getAudioEacUrl() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getAudioEacUrl());
                }
                if (value.getPlaybackM3u8LocalPath() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getPlaybackM3u8LocalPath());
                }
                if (value.getVideoLocalPath() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getVideoLocalPath());
                }
                if (value.getAudioLocalPath() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getAudioLocalPath());
                }
                if (value.getAudioEacLocalPath() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getAudioEacLocalPath());
                }
                stmt.bindLong(12, value.getVideoSize());
                stmt.bindLong(13, value.getAudioSize());
                stmt.bindLong(14, value.getAudioEacSize());
                stmt.bindDouble(15, value.getVideoSpeed());
                stmt.bindDouble(16, value.getAudioSpeed());
                stmt.bindDouble(17, value.getAudioEacSpeed());
                stmt.bindDouble(18, value.getVideoPercent());
                stmt.bindDouble(19, value.getAudioPercent());
                stmt.bindDouble(20, value.getAudioEacPercent());
            }
        };
        this.__deletionAdapterOfHlsDownload = new EntityDeletionOrUpdateAdapter<HlsDownload>(this, __db) { // from class: com.movieboxpro.android.db.dao.HlsDownloadDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `hls_download` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, HlsDownload value) {
                stmt.bindLong(1, value.getId());
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.HlsDownloadDao
    public void insertOrReplace(final HlsDownload hlsDownload) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfHlsDownload.insert(hlsDownload);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.HlsDownloadDao
    public void delete(final HlsDownload hlsDownload) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfHlsDownload.handle(hlsDownload);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.HlsDownloadDao
    public HlsDownload findByDownloadId(final String downloadId) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        HlsDownload hlsDownload;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM hls_download WHERE downloadId==?", 1);
        if (downloadId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, downloadId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "m3u8Url");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "m3u8LocalPath");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoUrl");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioUrl");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioEacUrl");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "playbackM3u8LocalPath");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoLocalPath");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioLocalPath");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioEacLocalPath");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioSize");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioEacSize");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoSpeed");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioSpeed");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioEacSpeed");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoPercent");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioPercent");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "audioEacPercent");
                if (cursorQuery.moveToFirst()) {
                    hlsDownload = new HlsDownload(cursorQuery.getLong(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3), cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4), cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5), cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6), cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7), cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8), cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9), cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10), cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11), cursorQuery.getLong(columnIndexOrThrow12), cursorQuery.getLong(columnIndexOrThrow13), cursorQuery.getLong(columnIndexOrThrow14), cursorQuery.getFloat(columnIndexOrThrow15), cursorQuery.getFloat(columnIndexOrThrow16), cursorQuery.getFloat(columnIndexOrThrow17), cursorQuery.getFloat(columnIndexOrThrow18), cursorQuery.getFloat(columnIndexOrThrow19), cursorQuery.getFloat(columnIndexOrThrow20));
                } else {
                    hlsDownload = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return hlsDownload;
            } catch (Throwable th) {
                th = th;
                cursorQuery.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = roomSQLiteQueryAcquire;
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
