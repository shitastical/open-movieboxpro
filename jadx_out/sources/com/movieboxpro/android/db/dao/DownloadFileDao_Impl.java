package com.movieboxpro.android.db.dao;

import android.database.Cursor;
import androidx.core.app.NotificationCompat;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.jeffmony.downloader.database.VideoDownloadSQLiteHelper;
import com.movieboxpro.android.db.entity.DownloadFile;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.service.FileDownloadService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadFileDao_Impl implements DownloadFileDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<DownloadFile> __deletionAdapterOfDownloadFile;
    private final EntityInsertionAdapter<DownloadFile> __insertionAdapterOfDownloadFile;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final EntityDeletionOrUpdateAdapter<DownloadFile> __updateAdapterOfDownloadFile;

    public DownloadFileDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfDownloadFile = new EntityInsertionAdapter<DownloadFile>(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `downloadfile` (`downloadId`,`id`,`url`,`path`,`fileName`,`status`,`mid`,`tid`,`season`,`episode`,`size`,`downloadSize`,`boxType`,`videoName`,`poster`,`quality`,`thumb`,`episodeName`,`downloadTime`,`imdbId`,`speed`,`errorMsg`,`progress`,`hls`,`percent`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, DownloadFile downloadFile) {
                if (downloadFile.getDownloadId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, downloadFile.getDownloadId());
                }
                if (downloadFile.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, downloadFile.getId());
                }
                if (downloadFile.getUrl() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, downloadFile.getUrl());
                }
                if (downloadFile.getPath() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, downloadFile.getPath());
                }
                if (downloadFile.getFileName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, downloadFile.getFileName());
                }
                supportSQLiteStatement.bindLong(6, downloadFile.getStatus());
                if (downloadFile.getMid() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, downloadFile.getMid());
                }
                if (downloadFile.getTid() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, downloadFile.getTid());
                }
                supportSQLiteStatement.bindLong(9, downloadFile.getSeason());
                supportSQLiteStatement.bindLong(10, downloadFile.getEpisode());
                supportSQLiteStatement.bindLong(11, downloadFile.getSize());
                supportSQLiteStatement.bindLong(12, downloadFile.getDownloadSize());
                supportSQLiteStatement.bindLong(13, downloadFile.getBoxType());
                if (downloadFile.getVideoName() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, downloadFile.getVideoName());
                }
                if (downloadFile.getPoster() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, downloadFile.getPoster());
                }
                if (downloadFile.getQuality() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, downloadFile.getQuality());
                }
                if (downloadFile.getThumb() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, downloadFile.getThumb());
                }
                if (downloadFile.getEpisodeName() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, downloadFile.getEpisodeName());
                }
                supportSQLiteStatement.bindLong(19, downloadFile.getDownloadTime());
                if (downloadFile.getImdbId() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, downloadFile.getImdbId());
                }
                if (downloadFile.getSpeed() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, downloadFile.getSpeed());
                }
                if (downloadFile.getErrorMsg() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, downloadFile.getErrorMsg());
                }
                supportSQLiteStatement.bindLong(23, downloadFile.getProgress());
                supportSQLiteStatement.bindLong(24, downloadFile.isHls() ? 1L : 0L);
                supportSQLiteStatement.bindDouble(25, downloadFile.getPercent());
            }
        };
        this.__deletionAdapterOfDownloadFile = new EntityDeletionOrUpdateAdapter<DownloadFile>(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `downloadfile` WHERE `downloadId` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, DownloadFile value) {
                if (value.getDownloadId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getDownloadId());
                }
            }
        };
        this.__updateAdapterOfDownloadFile = new EntityDeletionOrUpdateAdapter<DownloadFile>(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `downloadfile` SET `downloadId` = ?,`id` = ?,`url` = ?,`path` = ?,`fileName` = ?,`status` = ?,`mid` = ?,`tid` = ?,`season` = ?,`episode` = ?,`size` = ?,`downloadSize` = ?,`boxType` = ?,`videoName` = ?,`poster` = ?,`quality` = ?,`thumb` = ?,`episodeName` = ?,`downloadTime` = ?,`imdbId` = ?,`speed` = ?,`errorMsg` = ?,`progress` = ?,`hls` = ?,`percent` = ? WHERE `downloadId` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement supportSQLiteStatement, DownloadFile downloadFile) {
                if (downloadFile.getDownloadId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, downloadFile.getDownloadId());
                }
                if (downloadFile.getId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, downloadFile.getId());
                }
                if (downloadFile.getUrl() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, downloadFile.getUrl());
                }
                if (downloadFile.getPath() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, downloadFile.getPath());
                }
                if (downloadFile.getFileName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, downloadFile.getFileName());
                }
                supportSQLiteStatement.bindLong(6, downloadFile.getStatus());
                if (downloadFile.getMid() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, downloadFile.getMid());
                }
                if (downloadFile.getTid() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, downloadFile.getTid());
                }
                supportSQLiteStatement.bindLong(9, downloadFile.getSeason());
                supportSQLiteStatement.bindLong(10, downloadFile.getEpisode());
                supportSQLiteStatement.bindLong(11, downloadFile.getSize());
                supportSQLiteStatement.bindLong(12, downloadFile.getDownloadSize());
                supportSQLiteStatement.bindLong(13, downloadFile.getBoxType());
                if (downloadFile.getVideoName() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, downloadFile.getVideoName());
                }
                if (downloadFile.getPoster() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, downloadFile.getPoster());
                }
                if (downloadFile.getQuality() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, downloadFile.getQuality());
                }
                if (downloadFile.getThumb() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, downloadFile.getThumb());
                }
                if (downloadFile.getEpisodeName() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, downloadFile.getEpisodeName());
                }
                supportSQLiteStatement.bindLong(19, downloadFile.getDownloadTime());
                if (downloadFile.getImdbId() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, downloadFile.getImdbId());
                }
                if (downloadFile.getSpeed() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, downloadFile.getSpeed());
                }
                if (downloadFile.getErrorMsg() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, downloadFile.getErrorMsg());
                }
                supportSQLiteStatement.bindLong(23, downloadFile.getProgress());
                supportSQLiteStatement.bindLong(24, downloadFile.isHls() ? 1L : 0L);
                supportSQLiteStatement.bindDouble(25, downloadFile.getPercent());
                if (downloadFile.getDownloadId() == null) {
                    supportSQLiteStatement.bindNull(26);
                } else {
                    supportSQLiteStatement.bindString(26, downloadFile.getDownloadId());
                }
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloadfile";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public void insert(final DownloadFile downloadFile) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDownloadFile.insert(downloadFile);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public void deleteDownloadFile(final DownloadFile downloadFile) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDownloadFile.handle(downloadFile);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public void deleteDownloadFiles(final List<DownloadFile> downloadFiles) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDownloadFile.handleMultiple(downloadFiles);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public void update(final DownloadFile downloadFile) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfDownloadFile.handle(downloadFile);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public void updateDownloadFile(final DownloadFile downloadFile) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfDownloadFile.handle(downloadFile);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public void clear() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfClear.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfClear.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> getAllDownloadFiles() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i6 = columnIndexOrThrow19;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i6));
                    int i7 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                    int i8 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    int i9 = columnIndexOrThrow12;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i10 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i10) != 0);
                    columnIndexOrThrow24 = i10;
                    int i11 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i11));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i11;
                    columnIndexOrThrow12 = i9;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow20 = i7;
                    columnIndexOrThrow2 = i2;
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow19 = i6;
                    columnIndexOrThrow21 = i8;
                    columnIndexOrThrow3 = i3;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public DownloadFile findDownloadFileDownloadId(final String downloadId) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        DownloadFile downloadFile;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE downloadId == ? LIMIT 1", 1);
        if (downloadId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, downloadId);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                if (cursorQuery.moveToFirst()) {
                    DownloadFile downloadFile2 = new DownloadFile();
                    downloadFile2.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile2.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile2.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile2.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile2.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile2.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile2.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile2.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile2.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile2.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    downloadFile2.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile2.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile2.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    downloadFile2.setVideoName(cursorQuery.isNull(columnIndexOrThrow14) ? null : cursorQuery.getString(columnIndexOrThrow14));
                    downloadFile2.setPoster(cursorQuery.isNull(columnIndexOrThrow15) ? null : cursorQuery.getString(columnIndexOrThrow15));
                    downloadFile2.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    downloadFile2.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    downloadFile2.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    downloadFile2.setDownloadTime(cursorQuery.getLong(columnIndexOrThrow19));
                    downloadFile2.setImdbId(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                    downloadFile2.setSpeed(cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                    downloadFile2.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    downloadFile2.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    downloadFile2.setHls(cursorQuery.getInt(columnIndexOrThrow24) != 0);
                    downloadFile2.setPercent(cursorQuery.getFloat(columnIndexOrThrow25));
                    downloadFile = downloadFile2;
                } else {
                    downloadFile = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return downloadFile;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public DownloadFile findDownloadFileByUrl(final String url) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        DownloadFile downloadFile;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE url == ? LIMIT 1", 1);
        if (url == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, url);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                if (cursorQuery.moveToFirst()) {
                    DownloadFile downloadFile2 = new DownloadFile();
                    downloadFile2.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile2.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile2.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile2.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile2.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile2.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile2.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile2.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile2.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile2.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    downloadFile2.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile2.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile2.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    downloadFile2.setVideoName(cursorQuery.isNull(columnIndexOrThrow14) ? null : cursorQuery.getString(columnIndexOrThrow14));
                    downloadFile2.setPoster(cursorQuery.isNull(columnIndexOrThrow15) ? null : cursorQuery.getString(columnIndexOrThrow15));
                    downloadFile2.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    downloadFile2.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    downloadFile2.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    downloadFile2.setDownloadTime(cursorQuery.getLong(columnIndexOrThrow19));
                    downloadFile2.setImdbId(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                    downloadFile2.setSpeed(cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                    downloadFile2.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    downloadFile2.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    downloadFile2.setHls(cursorQuery.getInt(columnIndexOrThrow24) != 0);
                    downloadFile2.setPercent(cursorQuery.getFloat(columnIndexOrThrow25));
                    downloadFile = downloadFile2;
                } else {
                    downloadFile = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return downloadFile;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public Flow<DownloadFile> findDownloadFileDownloadIdFlow(final String downloadId) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE downloadId == ? LIMIT 1", 1);
        if (downloadId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, downloadId);
        }
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"downloadfile"}, new Callable<DownloadFile>() { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.5
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public DownloadFile call() throws Exception {
                DownloadFile downloadFile;
                Cursor cursorQuery = DBUtil.query(DownloadFileDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                    if (cursorQuery.moveToFirst()) {
                        DownloadFile downloadFile2 = new DownloadFile();
                        downloadFile2.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                        downloadFile2.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                        downloadFile2.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                        downloadFile2.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                        downloadFile2.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                        downloadFile2.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                        downloadFile2.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                        downloadFile2.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                        downloadFile2.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                        downloadFile2.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                        downloadFile2.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                        downloadFile2.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                        downloadFile2.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                        downloadFile2.setVideoName(cursorQuery.isNull(columnIndexOrThrow14) ? null : cursorQuery.getString(columnIndexOrThrow14));
                        downloadFile2.setPoster(cursorQuery.isNull(columnIndexOrThrow15) ? null : cursorQuery.getString(columnIndexOrThrow15));
                        downloadFile2.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                        downloadFile2.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                        downloadFile2.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                        downloadFile2.setDownloadTime(cursorQuery.getLong(columnIndexOrThrow19));
                        downloadFile2.setImdbId(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                        downloadFile2.setSpeed(cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                        downloadFile2.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                        downloadFile2.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                        downloadFile2.setHls(cursorQuery.getInt(columnIndexOrThrow24) != 0);
                        downloadFile2.setPercent(cursorQuery.getFloat(columnIndexOrThrow25));
                        downloadFile = downloadFile2;
                    } else {
                        downloadFile = null;
                    }
                    return downloadFile;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public DownloadFile findDownloadFileDownloadIdAndId(final String downloadId, final String id) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        DownloadFile downloadFile;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE downloadId == ? AND id == ? LIMIT 1", 2);
        if (downloadId == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, downloadId);
        }
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                if (cursorQuery.moveToFirst()) {
                    DownloadFile downloadFile2 = new DownloadFile();
                    downloadFile2.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile2.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile2.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile2.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile2.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile2.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile2.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile2.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile2.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile2.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    downloadFile2.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile2.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile2.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    downloadFile2.setVideoName(cursorQuery.isNull(columnIndexOrThrow14) ? null : cursorQuery.getString(columnIndexOrThrow14));
                    downloadFile2.setPoster(cursorQuery.isNull(columnIndexOrThrow15) ? null : cursorQuery.getString(columnIndexOrThrow15));
                    downloadFile2.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    downloadFile2.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    downloadFile2.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    downloadFile2.setDownloadTime(cursorQuery.getLong(columnIndexOrThrow19));
                    downloadFile2.setImdbId(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                    downloadFile2.setSpeed(cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                    downloadFile2.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    downloadFile2.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    downloadFile2.setHls(cursorQuery.getInt(columnIndexOrThrow24) != 0);
                    downloadFile2.setPercent(cursorQuery.getFloat(columnIndexOrThrow25));
                    downloadFile = downloadFile2;
                } else {
                    downloadFile = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return downloadFile;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public DownloadFile findDownloadFileByIdSeasonEpisode(final int boxType, final String id, final int season, final int episode) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        DownloadFile downloadFile;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE boxType == ? AND id == ? AND season == ? AND episode == ? LIMIT 1", 4);
        roomSQLiteQueryAcquire.bindLong(1, boxType);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, id);
        }
        roomSQLiteQueryAcquire.bindLong(3, season);
        roomSQLiteQueryAcquire.bindLong(4, episode);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                if (cursorQuery.moveToFirst()) {
                    DownloadFile downloadFile2 = new DownloadFile();
                    downloadFile2.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile2.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile2.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile2.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile2.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile2.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile2.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile2.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile2.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile2.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    downloadFile2.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile2.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile2.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    downloadFile2.setVideoName(cursorQuery.isNull(columnIndexOrThrow14) ? null : cursorQuery.getString(columnIndexOrThrow14));
                    downloadFile2.setPoster(cursorQuery.isNull(columnIndexOrThrow15) ? null : cursorQuery.getString(columnIndexOrThrow15));
                    downloadFile2.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    downloadFile2.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    downloadFile2.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    downloadFile2.setDownloadTime(cursorQuery.getLong(columnIndexOrThrow19));
                    downloadFile2.setImdbId(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                    downloadFile2.setSpeed(cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                    downloadFile2.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    downloadFile2.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    downloadFile2.setHls(cursorQuery.getInt(columnIndexOrThrow24) != 0);
                    downloadFile2.setPercent(cursorQuery.getFloat(columnIndexOrThrow25));
                    downloadFile = downloadFile2;
                } else {
                    downloadFile = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return downloadFile;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public Flow<List<DownloadFile>> findDownloadFileByIdSeasonFlow(final int boxType, final String id, final int season) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE boxType == ? AND id == ? AND season == ?", 3);
        roomSQLiteQueryAcquire.bindLong(1, boxType);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, id);
        }
        roomSQLiteQueryAcquire.bindLong(3, season);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"downloadfile"}, new Callable<List<DownloadFile>>() { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.6
            @Override // java.util.concurrent.Callable
            public List<DownloadFile> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(DownloadFileDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                    int i = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        DownloadFile downloadFile = new DownloadFile();
                        downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                        downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                        downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                        downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                        downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                        downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                        downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                        downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                        downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                        downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                        int i2 = columnIndexOrThrow2;
                        int i3 = columnIndexOrThrow3;
                        downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                        downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                        downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                        int i4 = i;
                        downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                        int i5 = columnIndexOrThrow15;
                        downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                        columnIndexOrThrow16 = columnIndexOrThrow16;
                        downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                        columnIndexOrThrow17 = columnIndexOrThrow17;
                        downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                        columnIndexOrThrow18 = columnIndexOrThrow18;
                        downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                        int i6 = columnIndexOrThrow19;
                        downloadFile.setDownloadTime(cursorQuery.getLong(i6));
                        int i7 = columnIndexOrThrow20;
                        downloadFile.setImdbId(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                        int i8 = columnIndexOrThrow21;
                        downloadFile.setSpeed(cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8));
                        columnIndexOrThrow22 = columnIndexOrThrow22;
                        downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                        int i9 = columnIndexOrThrow13;
                        columnIndexOrThrow23 = columnIndexOrThrow23;
                        downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                        int i10 = columnIndexOrThrow24;
                        downloadFile.setHls(cursorQuery.getInt(i10) != 0);
                        columnIndexOrThrow24 = i10;
                        int i11 = columnIndexOrThrow25;
                        downloadFile.setPercent(cursorQuery.getFloat(i11));
                        arrayList.add(downloadFile);
                        columnIndexOrThrow25 = i11;
                        columnIndexOrThrow13 = i9;
                        columnIndexOrThrow = columnIndexOrThrow;
                        columnIndexOrThrow20 = i7;
                        columnIndexOrThrow2 = i2;
                        i = i4;
                        columnIndexOrThrow15 = i5;
                        columnIndexOrThrow19 = i6;
                        columnIndexOrThrow21 = i8;
                        columnIndexOrThrow3 = i3;
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> findDownloadFileBySeason(final int boxType, final String id, final int season) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE boxType == ? AND id == ? AND season == ?", 3);
        roomSQLiteQueryAcquire.bindLong(1, boxType);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, id);
        }
        roomSQLiteQueryAcquire.bindLong(3, season);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    int i6 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(i6) ? null : cursorQuery.getString(i6));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i7 = columnIndexOrThrow11;
                    int i8 = columnIndexOrThrow19;
                    int i9 = columnIndexOrThrow12;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i8));
                    int i10 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    columnIndexOrThrow20 = i10;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i12 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i12) != 0);
                    columnIndexOrThrow24 = i12;
                    int i13 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i13));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i13;
                    columnIndexOrThrow11 = i7;
                    columnIndexOrThrow16 = i6;
                    columnIndexOrThrow = columnIndexOrThrow;
                    i = i4;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow3 = i3;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow12 = i9;
                    columnIndexOrThrow19 = i8;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> findSeasonsFile(final int boxType, final String id) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE boxType == ? AND id == ? group by season ORDER BY season DESC", 2);
        roomSQLiteQueryAcquire.bindLong(1, boxType);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i6 = columnIndexOrThrow11;
                    int i7 = columnIndexOrThrow19;
                    int i8 = columnIndexOrThrow12;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i7));
                    int i9 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i9) ? null : cursorQuery.getString(i9));
                    int i10 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    columnIndexOrThrow20 = i9;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i11 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i11) != 0);
                    columnIndexOrThrow24 = i11;
                    int i12 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i12));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i12;
                    columnIndexOrThrow11 = i6;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow3 = i3;
                    columnIndexOrThrow21 = i10;
                    columnIndexOrThrow12 = i8;
                    columnIndexOrThrow19 = i7;
                    i = i4;
                    columnIndexOrThrow2 = i2;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public Flow<List<DownloadFile>> findSeasonsFileFlow(final int boxType, final String id) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE boxType == ? AND id == ? group by season ORDER BY season DESC", 2);
        roomSQLiteQueryAcquire.bindLong(1, boxType);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, id);
        }
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"downloadfile"}, new Callable<List<DownloadFile>>() { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.7
            @Override // java.util.concurrent.Callable
            public List<DownloadFile> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(DownloadFileDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                    int i = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        DownloadFile downloadFile = new DownloadFile();
                        downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                        downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                        downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                        downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                        downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                        downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                        downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                        downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                        downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                        downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                        int i2 = columnIndexOrThrow2;
                        int i3 = columnIndexOrThrow3;
                        downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                        downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                        downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                        int i4 = i;
                        downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                        int i5 = columnIndexOrThrow15;
                        downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                        columnIndexOrThrow16 = columnIndexOrThrow16;
                        downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                        columnIndexOrThrow17 = columnIndexOrThrow17;
                        downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                        columnIndexOrThrow18 = columnIndexOrThrow18;
                        downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                        int i6 = columnIndexOrThrow19;
                        downloadFile.setDownloadTime(cursorQuery.getLong(i6));
                        int i7 = columnIndexOrThrow20;
                        downloadFile.setImdbId(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                        int i8 = columnIndexOrThrow21;
                        downloadFile.setSpeed(cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8));
                        columnIndexOrThrow22 = columnIndexOrThrow22;
                        downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                        int i9 = columnIndexOrThrow13;
                        columnIndexOrThrow23 = columnIndexOrThrow23;
                        downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                        int i10 = columnIndexOrThrow24;
                        downloadFile.setHls(cursorQuery.getInt(i10) != 0);
                        columnIndexOrThrow24 = i10;
                        int i11 = columnIndexOrThrow25;
                        downloadFile.setPercent(cursorQuery.getFloat(i11));
                        arrayList.add(downloadFile);
                        columnIndexOrThrow25 = i11;
                        columnIndexOrThrow13 = i9;
                        columnIndexOrThrow = columnIndexOrThrow;
                        columnIndexOrThrow20 = i7;
                        columnIndexOrThrow2 = i2;
                        i = i4;
                        columnIndexOrThrow15 = i5;
                        columnIndexOrThrow19 = i6;
                        columnIndexOrThrow21 = i8;
                        columnIndexOrThrow3 = i3;
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> findDownloadedTv(final int boxType, final String id, final int status) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE boxType == ? AND id == ? AND status = ?", 3);
        roomSQLiteQueryAcquire.bindLong(1, boxType);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, id);
        }
        roomSQLiteQueryAcquire.bindLong(3, status);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    int i6 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(i6) ? null : cursorQuery.getString(i6));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i7 = columnIndexOrThrow11;
                    int i8 = columnIndexOrThrow19;
                    int i9 = columnIndexOrThrow12;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i8));
                    int i10 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    columnIndexOrThrow20 = i10;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i12 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i12) != 0);
                    columnIndexOrThrow24 = i12;
                    int i13 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i13));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i13;
                    columnIndexOrThrow11 = i7;
                    columnIndexOrThrow16 = i6;
                    columnIndexOrThrow = columnIndexOrThrow;
                    i = i4;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow3 = i3;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow12 = i9;
                    columnIndexOrThrow19 = i8;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public DownloadFile findDownloadedMovie(final int boxType, final String id, final int status) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        DownloadFile downloadFile;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE boxType == ? AND id == ? AND status = ? LIMIT 1", 3);
        roomSQLiteQueryAcquire.bindLong(1, boxType);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, id);
        }
        roomSQLiteQueryAcquire.bindLong(3, status);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                if (cursorQuery.moveToFirst()) {
                    DownloadFile downloadFile2 = new DownloadFile();
                    downloadFile2.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile2.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile2.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile2.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile2.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile2.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile2.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile2.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile2.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile2.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    downloadFile2.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile2.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile2.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    downloadFile2.setVideoName(cursorQuery.isNull(columnIndexOrThrow14) ? null : cursorQuery.getString(columnIndexOrThrow14));
                    downloadFile2.setPoster(cursorQuery.isNull(columnIndexOrThrow15) ? null : cursorQuery.getString(columnIndexOrThrow15));
                    downloadFile2.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    downloadFile2.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    downloadFile2.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    downloadFile2.setDownloadTime(cursorQuery.getLong(columnIndexOrThrow19));
                    downloadFile2.setImdbId(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                    downloadFile2.setSpeed(cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                    downloadFile2.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    downloadFile2.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    downloadFile2.setHls(cursorQuery.getInt(columnIndexOrThrow24) != 0);
                    downloadFile2.setPercent(cursorQuery.getFloat(columnIndexOrThrow25));
                    downloadFile = downloadFile2;
                } else {
                    downloadFile = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return downloadFile;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> findDownloadFileById(final String id) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE id == ?", 1);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i6 = columnIndexOrThrow11;
                    int i7 = columnIndexOrThrow19;
                    int i8 = columnIndexOrThrow12;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i7));
                    int i9 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i9) ? null : cursorQuery.getString(i9));
                    int i10 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    columnIndexOrThrow20 = i9;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i11 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i11) != 0);
                    columnIndexOrThrow24 = i11;
                    int i12 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i12));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i12;
                    columnIndexOrThrow11 = i6;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow3 = i3;
                    columnIndexOrThrow21 = i10;
                    columnIndexOrThrow12 = i8;
                    columnIndexOrThrow19 = i7;
                    i = i4;
                    columnIndexOrThrow2 = i2;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> findDownloadedByType(final int status) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE status = ? group by id", 1);
        roomSQLiteQueryAcquire.bindLong(1, status);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i6 = columnIndexOrThrow12;
                    int i7 = columnIndexOrThrow19;
                    int i8 = columnIndexOrThrow13;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i7));
                    int i9 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i9) ? null : cursorQuery.getString(i9));
                    int i10 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    int i11 = columnIndexOrThrow11;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i12 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i12) != 0);
                    columnIndexOrThrow24 = i12;
                    int i13 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i13));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i13;
                    columnIndexOrThrow11 = i11;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow20 = i9;
                    columnIndexOrThrow12 = i6;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow3 = i3;
                    columnIndexOrThrow21 = i10;
                    columnIndexOrThrow13 = i8;
                    columnIndexOrThrow19 = i7;
                    i = i4;
                    columnIndexOrThrow2 = i2;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public Flow<List<DownloadFile>> findDownloadedByTypeFlow(final int status) {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE status = ? group by id", 1);
        roomSQLiteQueryAcquire.bindLong(1, status);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"downloadfile"}, new Callable<List<DownloadFile>>() { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.8
            @Override // java.util.concurrent.Callable
            public List<DownloadFile> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(DownloadFileDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                    int i = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        DownloadFile downloadFile = new DownloadFile();
                        downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                        downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                        downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                        downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                        downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                        downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                        downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                        downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                        downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                        downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                        int i2 = columnIndexOrThrow2;
                        int i3 = columnIndexOrThrow3;
                        downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                        downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                        downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                        int i4 = i;
                        downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                        int i5 = columnIndexOrThrow15;
                        downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                        columnIndexOrThrow16 = columnIndexOrThrow16;
                        downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                        columnIndexOrThrow17 = columnIndexOrThrow17;
                        downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                        columnIndexOrThrow18 = columnIndexOrThrow18;
                        downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                        int i6 = columnIndexOrThrow19;
                        downloadFile.setDownloadTime(cursorQuery.getLong(i6));
                        int i7 = columnIndexOrThrow20;
                        downloadFile.setImdbId(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                        int i8 = columnIndexOrThrow21;
                        downloadFile.setSpeed(cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8));
                        columnIndexOrThrow22 = columnIndexOrThrow22;
                        downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                        int i9 = columnIndexOrThrow13;
                        columnIndexOrThrow23 = columnIndexOrThrow23;
                        downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                        int i10 = columnIndexOrThrow24;
                        downloadFile.setHls(cursorQuery.getInt(i10) != 0);
                        columnIndexOrThrow24 = i10;
                        int i11 = columnIndexOrThrow25;
                        downloadFile.setPercent(cursorQuery.getFloat(i11));
                        arrayList.add(downloadFile);
                        columnIndexOrThrow25 = i11;
                        columnIndexOrThrow13 = i9;
                        columnIndexOrThrow = columnIndexOrThrow;
                        columnIndexOrThrow20 = i7;
                        columnIndexOrThrow2 = i2;
                        i = i4;
                        columnIndexOrThrow15 = i5;
                        columnIndexOrThrow19 = i6;
                        columnIndexOrThrow21 = i8;
                        columnIndexOrThrow3 = i3;
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> findHlsFiles() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE hls = 1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i6 = columnIndexOrThrow19;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i6));
                    int i7 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                    int i8 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    int i9 = columnIndexOrThrow12;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i10 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i10) != 0);
                    columnIndexOrThrow24 = i10;
                    int i11 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i11));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i11;
                    columnIndexOrThrow12 = i9;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow20 = i7;
                    columnIndexOrThrow2 = i2;
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow19 = i6;
                    columnIndexOrThrow21 = i8;
                    columnIndexOrThrow3 = i3;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> findDownloadingFile() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE status !=  1", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    columnIndexOrThrow16 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i6 = columnIndexOrThrow19;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i6));
                    int i7 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                    int i8 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    int i9 = columnIndexOrThrow12;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i10 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i10) != 0);
                    columnIndexOrThrow24 = i10;
                    int i11 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i11));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i11;
                    columnIndexOrThrow12 = i9;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow20 = i7;
                    columnIndexOrThrow2 = i2;
                    i = i4;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow19 = i6;
                    columnIndexOrThrow21 = i8;
                    columnIndexOrThrow3 = i3;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public Flow<List<DownloadFile>> findDownloadingFileFlow() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE status !=  1", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"downloadfile"}, new Callable<List<DownloadFile>>() { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.9
            @Override // java.util.concurrent.Callable
            public List<DownloadFile> call() throws Exception {
                Cursor cursorQuery = DBUtil.query(DownloadFileDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
                    int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
                    int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
                    int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
                    int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
                    int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
                    int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
                    int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
                    int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                    int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                    int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                    int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
                    int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
                    int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
                    int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                    int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                    int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                    int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                    int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                    int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                    int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                    int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                    int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                    int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                    int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                    int i = columnIndexOrThrow14;
                    ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                    while (cursorQuery.moveToNext()) {
                        DownloadFile downloadFile = new DownloadFile();
                        downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                        downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                        downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                        downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                        downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                        downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                        downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                        downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                        downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                        downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                        int i2 = columnIndexOrThrow2;
                        int i3 = columnIndexOrThrow3;
                        downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                        downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                        downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                        int i4 = i;
                        downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                        int i5 = columnIndexOrThrow15;
                        downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                        columnIndexOrThrow16 = columnIndexOrThrow16;
                        downloadFile.setQuality(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                        columnIndexOrThrow17 = columnIndexOrThrow17;
                        downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                        columnIndexOrThrow18 = columnIndexOrThrow18;
                        downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                        int i6 = columnIndexOrThrow19;
                        downloadFile.setDownloadTime(cursorQuery.getLong(i6));
                        int i7 = columnIndexOrThrow20;
                        downloadFile.setImdbId(cursorQuery.isNull(i7) ? null : cursorQuery.getString(i7));
                        int i8 = columnIndexOrThrow21;
                        downloadFile.setSpeed(cursorQuery.isNull(i8) ? null : cursorQuery.getString(i8));
                        columnIndexOrThrow22 = columnIndexOrThrow22;
                        downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                        int i9 = columnIndexOrThrow13;
                        columnIndexOrThrow23 = columnIndexOrThrow23;
                        downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                        int i10 = columnIndexOrThrow24;
                        downloadFile.setHls(cursorQuery.getInt(i10) != 0);
                        columnIndexOrThrow24 = i10;
                        int i11 = columnIndexOrThrow25;
                        downloadFile.setPercent(cursorQuery.getFloat(i11));
                        arrayList.add(downloadFile);
                        columnIndexOrThrow25 = i11;
                        columnIndexOrThrow13 = i9;
                        columnIndexOrThrow = columnIndexOrThrow;
                        columnIndexOrThrow20 = i7;
                        columnIndexOrThrow2 = i2;
                        i = i4;
                        columnIndexOrThrow15 = i5;
                        columnIndexOrThrow19 = i6;
                        columnIndexOrThrow21 = i8;
                        columnIndexOrThrow3 = i3;
                    }
                    return arrayList;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public List<DownloadFile> findEpisodes(final String id, final int boxType, final int status) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloadfile WHERE id == ? AND boxType == ? AND status = ? ", 3);
        if (id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, id);
        }
        roomSQLiteQueryAcquire.bindLong(2, boxType);
        roomSQLiteQueryAcquire.bindLong(3, status);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadId");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, NotificationCompat.CATEGORY_STATUS);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mid");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tid");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadSize");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "boxType");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "videoName");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "thumb");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "episodeName");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadTime");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdbId");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "errorMsg");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "hls");
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, VideoDownloadSQLiteHelper.Columns.PERCENT);
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    DownloadFile downloadFile = new DownloadFile();
                    downloadFile.setDownloadId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                    downloadFile.setId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    downloadFile.setUrl(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    downloadFile.setPath(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    downloadFile.setFileName(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    downloadFile.setStatus(cursorQuery.getInt(columnIndexOrThrow6));
                    downloadFile.setMid(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    downloadFile.setTid(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    downloadFile.setSeason(cursorQuery.getInt(columnIndexOrThrow9));
                    downloadFile.setEpisode(cursorQuery.getInt(columnIndexOrThrow10));
                    int i2 = columnIndexOrThrow2;
                    int i3 = columnIndexOrThrow3;
                    downloadFile.setSize(cursorQuery.getLong(columnIndexOrThrow11));
                    downloadFile.setDownloadSize(cursorQuery.getLong(columnIndexOrThrow12));
                    downloadFile.setBoxType(cursorQuery.getInt(columnIndexOrThrow13));
                    int i4 = i;
                    downloadFile.setVideoName(cursorQuery.isNull(i4) ? null : cursorQuery.getString(i4));
                    int i5 = columnIndexOrThrow15;
                    downloadFile.setPoster(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    int i6 = columnIndexOrThrow16;
                    downloadFile.setQuality(cursorQuery.isNull(i6) ? null : cursorQuery.getString(i6));
                    columnIndexOrThrow17 = columnIndexOrThrow17;
                    downloadFile.setThumb(cursorQuery.isNull(columnIndexOrThrow17) ? null : cursorQuery.getString(columnIndexOrThrow17));
                    columnIndexOrThrow18 = columnIndexOrThrow18;
                    downloadFile.setEpisodeName(cursorQuery.isNull(columnIndexOrThrow18) ? null : cursorQuery.getString(columnIndexOrThrow18));
                    int i7 = columnIndexOrThrow11;
                    int i8 = columnIndexOrThrow19;
                    int i9 = columnIndexOrThrow12;
                    downloadFile.setDownloadTime(cursorQuery.getLong(i8));
                    int i10 = columnIndexOrThrow20;
                    downloadFile.setImdbId(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    downloadFile.setSpeed(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    columnIndexOrThrow22 = columnIndexOrThrow22;
                    downloadFile.setErrorMsg(cursorQuery.isNull(columnIndexOrThrow22) ? null : cursorQuery.getString(columnIndexOrThrow22));
                    columnIndexOrThrow20 = i10;
                    columnIndexOrThrow23 = columnIndexOrThrow23;
                    downloadFile.setProgress(cursorQuery.getInt(columnIndexOrThrow23));
                    int i12 = columnIndexOrThrow24;
                    downloadFile.setHls(cursorQuery.getInt(i12) != 0);
                    columnIndexOrThrow24 = i12;
                    int i13 = columnIndexOrThrow25;
                    downloadFile.setPercent(cursorQuery.getFloat(i13));
                    arrayList.add(downloadFile);
                    columnIndexOrThrow25 = i13;
                    columnIndexOrThrow11 = i7;
                    columnIndexOrThrow16 = i6;
                    columnIndexOrThrow = columnIndexOrThrow;
                    i = i4;
                    columnIndexOrThrow2 = i2;
                    columnIndexOrThrow15 = i5;
                    columnIndexOrThrow3 = i3;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow12 = i9;
                    columnIndexOrThrow19 = i8;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.movieboxpro.android.db.dao.DownloadFileDao
    public Flow<Long> getDownloadingNumFlow() {
        final RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("select count(*) from downloadfile where status = 6 ", 0);
        return CoroutinesRoom.createFlow(this.__db, false, new String[]{"downloadfile"}, new Callable<Long>() { // from class: com.movieboxpro.android.db.dao.DownloadFileDao_Impl.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.util.concurrent.Callable
            public Long call() throws Exception {
                Long lValueOf = null;
                Cursor cursorQuery = DBUtil.query(DownloadFileDao_Impl.this.__db, roomSQLiteQueryAcquire, false, null);
                try {
                    if (cursorQuery.moveToFirst() && !cursorQuery.isNull(0)) {
                        lValueOf = Long.valueOf(cursorQuery.getLong(0));
                    }
                    return lValueOf;
                } finally {
                    cursorQuery.close();
                }
            }

            protected void finalize() {
                roomSQLiteQueryAcquire.release();
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
