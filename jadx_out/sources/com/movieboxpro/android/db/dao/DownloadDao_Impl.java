package com.movieboxpro.android.db.dao;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.movieboxpro.android.db.entity.Download;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.service.FileDownloadService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadDao_Impl implements DownloadDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Download> __deletionAdapterOfDownload;
    private final EntityInsertionAdapter<Download> __insertionAdapterOfDownload;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByTid;
    private final EntityDeletionOrUpdateAdapter<Download> __updateAdapterOfDownload;

    public DownloadDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfDownload = new EntityInsertionAdapter<Download>(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `downloads` (`id`,`movie_id`,`mb_id`,`title`,`vip_only`,`display`,`poster`,`runtime`,`add_time`,`is_collect`,`download`,`fileName`,`path`,`quality`,`fileLength`,`size`,`statue`,`progress`,`speed`,`format`,`count`,`dateline`,`mmfid`,`season`,`episode`,`box_type`,`tmfid`,`seasonid`,`seasontitle`,`seasonthumbs`,`privateid`,`fid`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Download value) {
                stmt.bindLong(1, value.getId());
                if (value.getMovieId() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getMovieId());
                }
                if (value.getMbId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getMbId());
                }
                if (value.getTitle() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getTitle());
                }
                if (value.getVip_only() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getVip_only());
                }
                if (value.getDisplay() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getDisplay());
                }
                if (value.getPoster() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPoster());
                }
                if (value.getRuntime() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getRuntime());
                }
                if (value.getAdd_time() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getAdd_time());
                }
                stmt.bindLong(10, value.getIs_collect());
                if (value.getDownload() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getDownload());
                }
                if (value.getFileName() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getFileName());
                }
                if (value.getPath() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.getPath());
                }
                if (value.getQuality() == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindString(14, value.getQuality());
                }
                stmt.bindLong(15, value.getFileLength());
                if (value.getSize() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindString(16, value.getSize());
                }
                stmt.bindLong(17, value.getStatue());
                stmt.bindLong(18, value.getProgress());
                stmt.bindLong(19, value.getSpeed());
                if (value.getFormat() == null) {
                    stmt.bindNull(20);
                } else {
                    stmt.bindString(20, value.getFormat());
                }
                if (value.getCount() == null) {
                    stmt.bindNull(21);
                } else {
                    stmt.bindString(21, value.getCount());
                }
                stmt.bindLong(22, value.getDateline());
                if (value.getMmfid() == null) {
                    stmt.bindNull(23);
                } else {
                    stmt.bindString(23, value.getMmfid());
                }
                stmt.bindLong(24, value.getSeason());
                stmt.bindLong(25, value.getEpisode());
                stmt.bindLong(26, value.getBox_type());
                stmt.bindLong(27, value.getTmfid());
                if (value.getSeasonid() == null) {
                    stmt.bindNull(28);
                } else {
                    stmt.bindString(28, value.getSeasonid());
                }
                if (value.getSeasontitle() == null) {
                    stmt.bindNull(29);
                } else {
                    stmt.bindString(29, value.getSeasontitle());
                }
                if (value.getSeasonthumbs() == null) {
                    stmt.bindNull(30);
                } else {
                    stmt.bindString(30, value.getSeasonthumbs());
                }
                if (value.getPrivateid() == null) {
                    stmt.bindNull(31);
                } else {
                    stmt.bindString(31, value.getPrivateid());
                }
                if (value.getFid() == null) {
                    stmt.bindNull(32);
                } else {
                    stmt.bindString(32, value.getFid());
                }
            }
        };
        this.__deletionAdapterOfDownload = new EntityDeletionOrUpdateAdapter<Download>(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `downloads` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Download value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfDownload = new EntityDeletionOrUpdateAdapter<Download>(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `downloads` SET `id` = ?,`movie_id` = ?,`mb_id` = ?,`title` = ?,`vip_only` = ?,`display` = ?,`poster` = ?,`runtime` = ?,`add_time` = ?,`is_collect` = ?,`download` = ?,`fileName` = ?,`path` = ?,`quality` = ?,`fileLength` = ?,`size` = ?,`statue` = ?,`progress` = ?,`speed` = ?,`format` = ?,`count` = ?,`dateline` = ?,`mmfid` = ?,`season` = ?,`episode` = ?,`box_type` = ?,`tmfid` = ?,`seasonid` = ?,`seasontitle` = ?,`seasonthumbs` = ?,`privateid` = ?,`fid` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Download value) {
                stmt.bindLong(1, value.getId());
                if (value.getMovieId() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getMovieId());
                }
                if (value.getMbId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getMbId());
                }
                if (value.getTitle() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getTitle());
                }
                if (value.getVip_only() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getVip_only());
                }
                if (value.getDisplay() == null) {
                    stmt.bindNull(6);
                } else {
                    stmt.bindString(6, value.getDisplay());
                }
                if (value.getPoster() == null) {
                    stmt.bindNull(7);
                } else {
                    stmt.bindString(7, value.getPoster());
                }
                if (value.getRuntime() == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.getRuntime());
                }
                if (value.getAdd_time() == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindString(9, value.getAdd_time());
                }
                stmt.bindLong(10, value.getIs_collect());
                if (value.getDownload() == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.getDownload());
                }
                if (value.getFileName() == null) {
                    stmt.bindNull(12);
                } else {
                    stmt.bindString(12, value.getFileName());
                }
                if (value.getPath() == null) {
                    stmt.bindNull(13);
                } else {
                    stmt.bindString(13, value.getPath());
                }
                if (value.getQuality() == null) {
                    stmt.bindNull(14);
                } else {
                    stmt.bindString(14, value.getQuality());
                }
                stmt.bindLong(15, value.getFileLength());
                if (value.getSize() == null) {
                    stmt.bindNull(16);
                } else {
                    stmt.bindString(16, value.getSize());
                }
                stmt.bindLong(17, value.getStatue());
                stmt.bindLong(18, value.getProgress());
                stmt.bindLong(19, value.getSpeed());
                if (value.getFormat() == null) {
                    stmt.bindNull(20);
                } else {
                    stmt.bindString(20, value.getFormat());
                }
                if (value.getCount() == null) {
                    stmt.bindNull(21);
                } else {
                    stmt.bindString(21, value.getCount());
                }
                stmt.bindLong(22, value.getDateline());
                if (value.getMmfid() == null) {
                    stmt.bindNull(23);
                } else {
                    stmt.bindString(23, value.getMmfid());
                }
                stmt.bindLong(24, value.getSeason());
                stmt.bindLong(25, value.getEpisode());
                stmt.bindLong(26, value.getBox_type());
                stmt.bindLong(27, value.getTmfid());
                if (value.getSeasonid() == null) {
                    stmt.bindNull(28);
                } else {
                    stmt.bindString(28, value.getSeasonid());
                }
                if (value.getSeasontitle() == null) {
                    stmt.bindNull(29);
                } else {
                    stmt.bindString(29, value.getSeasontitle());
                }
                if (value.getSeasonthumbs() == null) {
                    stmt.bindNull(30);
                } else {
                    stmt.bindString(30, value.getSeasonthumbs());
                }
                if (value.getPrivateid() == null) {
                    stmt.bindNull(31);
                } else {
                    stmt.bindString(31, value.getPrivateid());
                }
                if (value.getFid() == null) {
                    stmt.bindNull(32);
                } else {
                    stmt.bindString(32, value.getFid());
                }
                stmt.bindLong(33, value.getId());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads WHERE movie_id = ?";
            }
        };
        this.__preparedStmtOfDeleteByTid = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads WHERE privateid== ? ";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.DownloadDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM downloads";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void insert(final Download download) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfDownload.insert(download);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void deleteDownloads(final List<Download> downloads) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDownload.handleMultiple(downloads);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void delete(final Download download) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfDownload.handle(download);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void update(final Download download) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfDownload.handle(download);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void deleteById(final String movieId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteById.acquire();
        if (movieId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, movieId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteById.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public void deleteByTid(final String privateid) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteByTid.acquire();
        if (privateid == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, privateid);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByTid.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.DownloadDao
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> getAll() throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "download");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fid");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(cursorQuery.getInt(columnIndexOrThrow));
                    download.setMovieId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    download.setMbId(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    download.setTitle(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    download.setVip_only(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    download.setDisplay(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    download.setPoster(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    download.setRuntime(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    download.setAdd_time(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    download.setIs_collect(cursorQuery.getInt(columnIndexOrThrow10));
                    download.setDownload(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    download.setFileName(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    download.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    int i2 = i;
                    download.setQuality(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                    i = i2;
                    int i3 = columnIndexOrThrow15;
                    int i4 = columnIndexOrThrow13;
                    download.setFileLength(cursorQuery.getLong(i3));
                    int i5 = columnIndexOrThrow16;
                    download.setSize(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    int i6 = columnIndexOrThrow17;
                    download.setStatue(cursorQuery.getInt(i6));
                    int i7 = columnIndexOrThrow18;
                    download.setProgress(cursorQuery.getInt(i7));
                    int i8 = columnIndexOrThrow2;
                    int i9 = columnIndexOrThrow19;
                    download.setSpeed(cursorQuery.getLong(i9));
                    int i10 = columnIndexOrThrow20;
                    download.setFormat(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    download.setCount(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    int i12 = columnIndexOrThrow22;
                    download.setDateline(cursorQuery.getLong(i12));
                    int i13 = columnIndexOrThrow23;
                    download.setMmfid(cursorQuery.isNull(i13) ? null : cursorQuery.getString(i13));
                    int i14 = columnIndexOrThrow24;
                    download.setSeason(cursorQuery.getInt(i14));
                    int i15 = columnIndexOrThrow25;
                    download.setEpisode(cursorQuery.getInt(i15));
                    columnIndexOrThrow25 = i15;
                    int i16 = columnIndexOrThrow26;
                    download.setBox_type(cursorQuery.getInt(i16));
                    columnIndexOrThrow26 = i16;
                    int i17 = columnIndexOrThrow27;
                    download.setTmfid(cursorQuery.getInt(i17));
                    int i18 = columnIndexOrThrow28;
                    download.setSeasonid(cursorQuery.isNull(i18) ? null : cursorQuery.getString(i18));
                    columnIndexOrThrow29 = columnIndexOrThrow29;
                    download.setSeasontitle(cursorQuery.isNull(columnIndexOrThrow29) ? null : cursorQuery.getString(columnIndexOrThrow29));
                    columnIndexOrThrow30 = columnIndexOrThrow30;
                    download.setSeasonthumbs(cursorQuery.isNull(columnIndexOrThrow30) ? null : cursorQuery.getString(columnIndexOrThrow30));
                    columnIndexOrThrow31 = columnIndexOrThrow31;
                    download.setPrivateid(cursorQuery.isNull(columnIndexOrThrow31) ? null : cursorQuery.getString(columnIndexOrThrow31));
                    columnIndexOrThrow32 = columnIndexOrThrow32;
                    download.setFid(cursorQuery.isNull(columnIndexOrThrow32) ? null : cursorQuery.getString(columnIndexOrThrow32));
                    arrayList2.add(download);
                    columnIndexOrThrow27 = i17;
                    columnIndexOrThrow28 = i18;
                    arrayList = arrayList2;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow24 = i14;
                    columnIndexOrThrow13 = i4;
                    columnIndexOrThrow15 = i3;
                    columnIndexOrThrow16 = i5;
                    columnIndexOrThrow17 = i6;
                    columnIndexOrThrow19 = i9;
                    columnIndexOrThrow20 = i10;
                    columnIndexOrThrow22 = i12;
                    columnIndexOrThrow23 = i13;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow2 = i8;
                    columnIndexOrThrow18 = i7;
                }
                ArrayList arrayList3 = arrayList;
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList3;
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findByType(final int type) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE box_type== ? LIMIT 1", 1);
        roomSQLiteQueryAcquire.bindLong(1, type);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "download");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fid");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(cursorQuery.getInt(columnIndexOrThrow));
                    download.setMovieId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    download.setMbId(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    download.setTitle(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    download.setVip_only(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    download.setDisplay(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    download.setPoster(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    download.setRuntime(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    download.setAdd_time(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    download.setIs_collect(cursorQuery.getInt(columnIndexOrThrow10));
                    download.setDownload(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    download.setFileName(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    download.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    int i2 = i;
                    download.setQuality(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                    i = i2;
                    int i3 = columnIndexOrThrow15;
                    int i4 = columnIndexOrThrow13;
                    download.setFileLength(cursorQuery.getLong(i3));
                    int i5 = columnIndexOrThrow16;
                    download.setSize(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    int i6 = columnIndexOrThrow17;
                    download.setStatue(cursorQuery.getInt(i6));
                    int i7 = columnIndexOrThrow18;
                    download.setProgress(cursorQuery.getInt(i7));
                    int i8 = columnIndexOrThrow2;
                    int i9 = columnIndexOrThrow19;
                    download.setSpeed(cursorQuery.getLong(i9));
                    int i10 = columnIndexOrThrow20;
                    download.setFormat(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    download.setCount(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    int i12 = columnIndexOrThrow22;
                    download.setDateline(cursorQuery.getLong(i12));
                    int i13 = columnIndexOrThrow23;
                    download.setMmfid(cursorQuery.isNull(i13) ? null : cursorQuery.getString(i13));
                    int i14 = columnIndexOrThrow24;
                    download.setSeason(cursorQuery.getInt(i14));
                    int i15 = columnIndexOrThrow25;
                    download.setEpisode(cursorQuery.getInt(i15));
                    columnIndexOrThrow25 = i15;
                    int i16 = columnIndexOrThrow26;
                    download.setBox_type(cursorQuery.getInt(i16));
                    columnIndexOrThrow26 = i16;
                    int i17 = columnIndexOrThrow27;
                    download.setTmfid(cursorQuery.getInt(i17));
                    int i18 = columnIndexOrThrow28;
                    download.setSeasonid(cursorQuery.isNull(i18) ? null : cursorQuery.getString(i18));
                    columnIndexOrThrow29 = columnIndexOrThrow29;
                    download.setSeasontitle(cursorQuery.isNull(columnIndexOrThrow29) ? null : cursorQuery.getString(columnIndexOrThrow29));
                    columnIndexOrThrow30 = columnIndexOrThrow30;
                    download.setSeasonthumbs(cursorQuery.isNull(columnIndexOrThrow30) ? null : cursorQuery.getString(columnIndexOrThrow30));
                    columnIndexOrThrow31 = columnIndexOrThrow31;
                    download.setPrivateid(cursorQuery.isNull(columnIndexOrThrow31) ? null : cursorQuery.getString(columnIndexOrThrow31));
                    columnIndexOrThrow32 = columnIndexOrThrow32;
                    download.setFid(cursorQuery.isNull(columnIndexOrThrow32) ? null : cursorQuery.getString(columnIndexOrThrow32));
                    arrayList2.add(download);
                    columnIndexOrThrow27 = i17;
                    columnIndexOrThrow28 = i18;
                    arrayList = arrayList2;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow24 = i14;
                    columnIndexOrThrow13 = i4;
                    columnIndexOrThrow15 = i3;
                    columnIndexOrThrow16 = i5;
                    columnIndexOrThrow17 = i6;
                    columnIndexOrThrow19 = i9;
                    columnIndexOrThrow20 = i10;
                    columnIndexOrThrow22 = i12;
                    columnIndexOrThrow23 = i13;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow2 = i8;
                    columnIndexOrThrow18 = i7;
                }
                ArrayList arrayList3 = arrayList;
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList3;
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public Download findByType(final int type, final String privateid) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        Download download;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE box_type== ? AND privateid = ? LIMIT 1 ", 2);
        roomSQLiteQueryAcquire.bindLong(1, type);
        if (privateid == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, privateid);
        }
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "download");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fid");
                if (cursorQuery.moveToFirst()) {
                    Download download2 = new Download();
                    download2.setId(cursorQuery.getInt(columnIndexOrThrow));
                    download2.setMovieId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    download2.setMbId(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    download2.setTitle(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    download2.setVip_only(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    download2.setDisplay(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    download2.setPoster(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    download2.setRuntime(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    download2.setAdd_time(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    download2.setIs_collect(cursorQuery.getInt(columnIndexOrThrow10));
                    download2.setDownload(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    download2.setFileName(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    download2.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    download2.setQuality(cursorQuery.isNull(columnIndexOrThrow14) ? null : cursorQuery.getString(columnIndexOrThrow14));
                    download2.setFileLength(cursorQuery.getLong(columnIndexOrThrow15));
                    download2.setSize(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    download2.setStatue(cursorQuery.getInt(columnIndexOrThrow17));
                    download2.setProgress(cursorQuery.getInt(columnIndexOrThrow18));
                    download2.setSpeed(cursorQuery.getLong(columnIndexOrThrow19));
                    download2.setFormat(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                    download2.setCount(cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                    download2.setDateline(cursorQuery.getLong(columnIndexOrThrow22));
                    download2.setMmfid(cursorQuery.isNull(columnIndexOrThrow23) ? null : cursorQuery.getString(columnIndexOrThrow23));
                    download2.setSeason(cursorQuery.getInt(columnIndexOrThrow24));
                    download2.setEpisode(cursorQuery.getInt(columnIndexOrThrow25));
                    download2.setBox_type(cursorQuery.getInt(columnIndexOrThrow26));
                    download2.setTmfid(cursorQuery.getInt(columnIndexOrThrow27));
                    download2.setSeasonid(cursorQuery.isNull(columnIndexOrThrow28) ? null : cursorQuery.getString(columnIndexOrThrow28));
                    download2.setSeasontitle(cursorQuery.isNull(columnIndexOrThrow29) ? null : cursorQuery.getString(columnIndexOrThrow29));
                    download2.setSeasonthumbs(cursorQuery.isNull(columnIndexOrThrow30) ? null : cursorQuery.getString(columnIndexOrThrow30));
                    download2.setPrivateid(cursorQuery.isNull(columnIndexOrThrow31) ? null : cursorQuery.getString(columnIndexOrThrow31));
                    download2.setFid(cursorQuery.isNull(columnIndexOrThrow32) ? null : cursorQuery.getString(columnIndexOrThrow32));
                    download = download2;
                } else {
                    download = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return download;
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public Download findByTypes(final int statue) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        Download download;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE statue != ?  LIMIT 1 ", 1);
        roomSQLiteQueryAcquire.bindLong(1, statue);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "download");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fid");
                if (cursorQuery.moveToFirst()) {
                    Download download2 = new Download();
                    download2.setId(cursorQuery.getInt(columnIndexOrThrow));
                    download2.setMovieId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    download2.setMbId(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    download2.setTitle(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    download2.setVip_only(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    download2.setDisplay(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    download2.setPoster(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    download2.setRuntime(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    download2.setAdd_time(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    download2.setIs_collect(cursorQuery.getInt(columnIndexOrThrow10));
                    download2.setDownload(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    download2.setFileName(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    download2.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    download2.setQuality(cursorQuery.isNull(columnIndexOrThrow14) ? null : cursorQuery.getString(columnIndexOrThrow14));
                    download2.setFileLength(cursorQuery.getLong(columnIndexOrThrow15));
                    download2.setSize(cursorQuery.isNull(columnIndexOrThrow16) ? null : cursorQuery.getString(columnIndexOrThrow16));
                    download2.setStatue(cursorQuery.getInt(columnIndexOrThrow17));
                    download2.setProgress(cursorQuery.getInt(columnIndexOrThrow18));
                    download2.setSpeed(cursorQuery.getLong(columnIndexOrThrow19));
                    download2.setFormat(cursorQuery.isNull(columnIndexOrThrow20) ? null : cursorQuery.getString(columnIndexOrThrow20));
                    download2.setCount(cursorQuery.isNull(columnIndexOrThrow21) ? null : cursorQuery.getString(columnIndexOrThrow21));
                    download2.setDateline(cursorQuery.getLong(columnIndexOrThrow22));
                    download2.setMmfid(cursorQuery.isNull(columnIndexOrThrow23) ? null : cursorQuery.getString(columnIndexOrThrow23));
                    download2.setSeason(cursorQuery.getInt(columnIndexOrThrow24));
                    download2.setEpisode(cursorQuery.getInt(columnIndexOrThrow25));
                    download2.setBox_type(cursorQuery.getInt(columnIndexOrThrow26));
                    download2.setTmfid(cursorQuery.getInt(columnIndexOrThrow27));
                    download2.setSeasonid(cursorQuery.isNull(columnIndexOrThrow28) ? null : cursorQuery.getString(columnIndexOrThrow28));
                    download2.setSeasontitle(cursorQuery.isNull(columnIndexOrThrow29) ? null : cursorQuery.getString(columnIndexOrThrow29));
                    download2.setSeasonthumbs(cursorQuery.isNull(columnIndexOrThrow30) ? null : cursorQuery.getString(columnIndexOrThrow30));
                    download2.setPrivateid(cursorQuery.isNull(columnIndexOrThrow31) ? null : cursorQuery.getString(columnIndexOrThrow31));
                    download2.setFid(cursorQuery.isNull(columnIndexOrThrow32) ? null : cursorQuery.getString(columnIndexOrThrow32));
                    download = download2;
                } else {
                    download = null;
                }
                cursorQuery.close();
                roomSQLiteQuery.release();
                return download;
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findByTid(final String movieId, final int type, final int statue) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads  WHERE box_type== ? AND movie_id =  ? AND statue = ? AND season", 3);
        roomSQLiteQueryAcquire.bindLong(1, type);
        if (movieId == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, movieId);
        }
        roomSQLiteQueryAcquire.bindLong(3, statue);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "download");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fid");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(cursorQuery.getInt(columnIndexOrThrow));
                    download.setMovieId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    download.setMbId(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    download.setTitle(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    download.setVip_only(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    download.setDisplay(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    download.setPoster(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    download.setRuntime(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    download.setAdd_time(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    download.setIs_collect(cursorQuery.getInt(columnIndexOrThrow10));
                    download.setDownload(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    download.setFileName(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    download.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    int i2 = i;
                    download.setQuality(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                    int i3 = columnIndexOrThrow15;
                    int i4 = columnIndexOrThrow13;
                    int i5 = columnIndexOrThrow2;
                    download.setFileLength(cursorQuery.getLong(i3));
                    int i6 = columnIndexOrThrow16;
                    download.setSize(cursorQuery.isNull(i6) ? null : cursorQuery.getString(i6));
                    int i7 = columnIndexOrThrow17;
                    download.setStatue(cursorQuery.getInt(i7));
                    int i8 = columnIndexOrThrow18;
                    download.setProgress(cursorQuery.getInt(i8));
                    columnIndexOrThrow16 = i6;
                    int i9 = columnIndexOrThrow19;
                    download.setSpeed(cursorQuery.getLong(i9));
                    int i10 = columnIndexOrThrow20;
                    download.setFormat(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    download.setCount(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    columnIndexOrThrow20 = i10;
                    int i12 = columnIndexOrThrow22;
                    download.setDateline(cursorQuery.getLong(i12));
                    int i13 = columnIndexOrThrow23;
                    download.setMmfid(cursorQuery.isNull(i13) ? null : cursorQuery.getString(i13));
                    int i14 = columnIndexOrThrow24;
                    download.setSeason(cursorQuery.getInt(i14));
                    int i15 = columnIndexOrThrow25;
                    download.setEpisode(cursorQuery.getInt(i15));
                    columnIndexOrThrow25 = i15;
                    int i16 = columnIndexOrThrow26;
                    download.setBox_type(cursorQuery.getInt(i16));
                    columnIndexOrThrow26 = i16;
                    int i17 = columnIndexOrThrow27;
                    download.setTmfid(cursorQuery.getInt(i17));
                    int i18 = columnIndexOrThrow28;
                    download.setSeasonid(cursorQuery.isNull(i18) ? null : cursorQuery.getString(i18));
                    columnIndexOrThrow29 = columnIndexOrThrow29;
                    download.setSeasontitle(cursorQuery.isNull(columnIndexOrThrow29) ? null : cursorQuery.getString(columnIndexOrThrow29));
                    columnIndexOrThrow30 = columnIndexOrThrow30;
                    download.setSeasonthumbs(cursorQuery.isNull(columnIndexOrThrow30) ? null : cursorQuery.getString(columnIndexOrThrow30));
                    columnIndexOrThrow31 = columnIndexOrThrow31;
                    download.setPrivateid(cursorQuery.isNull(columnIndexOrThrow31) ? null : cursorQuery.getString(columnIndexOrThrow31));
                    columnIndexOrThrow32 = columnIndexOrThrow32;
                    download.setFid(cursorQuery.isNull(columnIndexOrThrow32) ? null : cursorQuery.getString(columnIndexOrThrow32));
                    arrayList2.add(download);
                    columnIndexOrThrow27 = i17;
                    columnIndexOrThrow28 = i18;
                    arrayList = arrayList2;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow2 = i5;
                    columnIndexOrThrow15 = i3;
                    columnIndexOrThrow17 = i7;
                    columnIndexOrThrow19 = i9;
                    columnIndexOrThrow24 = i14;
                    columnIndexOrThrow23 = i13;
                    columnIndexOrThrow13 = i4;
                    i = i2;
                    columnIndexOrThrow18 = i8;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow22 = i12;
                }
                ArrayList arrayList3 = arrayList;
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList3;
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findBySeason(final String movieId, final int type, final int statue) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads  WHERE box_type== ? AND movie_id =  ? AND statue = ? group by season", 3);
        roomSQLiteQueryAcquire.bindLong(1, type);
        if (movieId == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, movieId);
        }
        roomSQLiteQueryAcquire.bindLong(3, statue);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "download");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fid");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(cursorQuery.getInt(columnIndexOrThrow));
                    download.setMovieId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    download.setMbId(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    download.setTitle(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    download.setVip_only(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    download.setDisplay(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    download.setPoster(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    download.setRuntime(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    download.setAdd_time(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    download.setIs_collect(cursorQuery.getInt(columnIndexOrThrow10));
                    download.setDownload(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    download.setFileName(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    download.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    int i2 = i;
                    download.setQuality(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                    int i3 = columnIndexOrThrow15;
                    int i4 = columnIndexOrThrow13;
                    int i5 = columnIndexOrThrow2;
                    download.setFileLength(cursorQuery.getLong(i3));
                    int i6 = columnIndexOrThrow16;
                    download.setSize(cursorQuery.isNull(i6) ? null : cursorQuery.getString(i6));
                    int i7 = columnIndexOrThrow17;
                    download.setStatue(cursorQuery.getInt(i7));
                    int i8 = columnIndexOrThrow18;
                    download.setProgress(cursorQuery.getInt(i8));
                    columnIndexOrThrow16 = i6;
                    int i9 = columnIndexOrThrow19;
                    download.setSpeed(cursorQuery.getLong(i9));
                    int i10 = columnIndexOrThrow20;
                    download.setFormat(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    download.setCount(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    columnIndexOrThrow20 = i10;
                    int i12 = columnIndexOrThrow22;
                    download.setDateline(cursorQuery.getLong(i12));
                    int i13 = columnIndexOrThrow23;
                    download.setMmfid(cursorQuery.isNull(i13) ? null : cursorQuery.getString(i13));
                    int i14 = columnIndexOrThrow24;
                    download.setSeason(cursorQuery.getInt(i14));
                    int i15 = columnIndexOrThrow25;
                    download.setEpisode(cursorQuery.getInt(i15));
                    columnIndexOrThrow25 = i15;
                    int i16 = columnIndexOrThrow26;
                    download.setBox_type(cursorQuery.getInt(i16));
                    columnIndexOrThrow26 = i16;
                    int i17 = columnIndexOrThrow27;
                    download.setTmfid(cursorQuery.getInt(i17));
                    int i18 = columnIndexOrThrow28;
                    download.setSeasonid(cursorQuery.isNull(i18) ? null : cursorQuery.getString(i18));
                    columnIndexOrThrow29 = columnIndexOrThrow29;
                    download.setSeasontitle(cursorQuery.isNull(columnIndexOrThrow29) ? null : cursorQuery.getString(columnIndexOrThrow29));
                    columnIndexOrThrow30 = columnIndexOrThrow30;
                    download.setSeasonthumbs(cursorQuery.isNull(columnIndexOrThrow30) ? null : cursorQuery.getString(columnIndexOrThrow30));
                    columnIndexOrThrow31 = columnIndexOrThrow31;
                    download.setPrivateid(cursorQuery.isNull(columnIndexOrThrow31) ? null : cursorQuery.getString(columnIndexOrThrow31));
                    columnIndexOrThrow32 = columnIndexOrThrow32;
                    download.setFid(cursorQuery.isNull(columnIndexOrThrow32) ? null : cursorQuery.getString(columnIndexOrThrow32));
                    arrayList2.add(download);
                    columnIndexOrThrow27 = i17;
                    columnIndexOrThrow28 = i18;
                    arrayList = arrayList2;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow2 = i5;
                    columnIndexOrThrow15 = i3;
                    columnIndexOrThrow17 = i7;
                    columnIndexOrThrow19 = i9;
                    columnIndexOrThrow24 = i14;
                    columnIndexOrThrow23 = i13;
                    columnIndexOrThrow13 = i4;
                    i = i2;
                    columnIndexOrThrow18 = i8;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow22 = i12;
                }
                ArrayList arrayList3 = arrayList;
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList3;
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> findId(final int type, final int statue) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE box_type== ? AND statue = ? group by movie_id ", 2);
        roomSQLiteQueryAcquire.bindLong(1, type);
        roomSQLiteQueryAcquire.bindLong(2, statue);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "download");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fid");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(cursorQuery.getInt(columnIndexOrThrow));
                    download.setMovieId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    download.setMbId(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    download.setTitle(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    download.setVip_only(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    download.setDisplay(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    download.setPoster(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    download.setRuntime(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    download.setAdd_time(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    download.setIs_collect(cursorQuery.getInt(columnIndexOrThrow10));
                    download.setDownload(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    download.setFileName(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    download.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    int i2 = i;
                    download.setQuality(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                    int i3 = columnIndexOrThrow15;
                    i = i2;
                    int i4 = columnIndexOrThrow13;
                    download.setFileLength(cursorQuery.getLong(i3));
                    int i5 = columnIndexOrThrow16;
                    download.setSize(cursorQuery.isNull(i5) ? null : cursorQuery.getString(i5));
                    int i6 = columnIndexOrThrow17;
                    download.setStatue(cursorQuery.getInt(i6));
                    int i7 = columnIndexOrThrow18;
                    download.setProgress(cursorQuery.getInt(i7));
                    int i8 = columnIndexOrThrow2;
                    int i9 = columnIndexOrThrow19;
                    download.setSpeed(cursorQuery.getLong(i9));
                    int i10 = columnIndexOrThrow20;
                    download.setFormat(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    download.setCount(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    int i12 = columnIndexOrThrow22;
                    download.setDateline(cursorQuery.getLong(i12));
                    int i13 = columnIndexOrThrow23;
                    download.setMmfid(cursorQuery.isNull(i13) ? null : cursorQuery.getString(i13));
                    int i14 = columnIndexOrThrow24;
                    download.setSeason(cursorQuery.getInt(i14));
                    int i15 = columnIndexOrThrow25;
                    download.setEpisode(cursorQuery.getInt(i15));
                    columnIndexOrThrow25 = i15;
                    int i16 = columnIndexOrThrow26;
                    download.setBox_type(cursorQuery.getInt(i16));
                    columnIndexOrThrow26 = i16;
                    int i17 = columnIndexOrThrow27;
                    download.setTmfid(cursorQuery.getInt(i17));
                    int i18 = columnIndexOrThrow28;
                    download.setSeasonid(cursorQuery.isNull(i18) ? null : cursorQuery.getString(i18));
                    columnIndexOrThrow29 = columnIndexOrThrow29;
                    download.setSeasontitle(cursorQuery.isNull(columnIndexOrThrow29) ? null : cursorQuery.getString(columnIndexOrThrow29));
                    columnIndexOrThrow30 = columnIndexOrThrow30;
                    download.setSeasonthumbs(cursorQuery.isNull(columnIndexOrThrow30) ? null : cursorQuery.getString(columnIndexOrThrow30));
                    columnIndexOrThrow31 = columnIndexOrThrow31;
                    download.setPrivateid(cursorQuery.isNull(columnIndexOrThrow31) ? null : cursorQuery.getString(columnIndexOrThrow31));
                    columnIndexOrThrow32 = columnIndexOrThrow32;
                    download.setFid(cursorQuery.isNull(columnIndexOrThrow32) ? null : cursorQuery.getString(columnIndexOrThrow32));
                    arrayList2.add(download);
                    columnIndexOrThrow27 = i17;
                    columnIndexOrThrow28 = i18;
                    arrayList = arrayList2;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow13 = i4;
                    columnIndexOrThrow15 = i3;
                    columnIndexOrThrow16 = i5;
                    columnIndexOrThrow17 = i6;
                    columnIndexOrThrow19 = i9;
                    columnIndexOrThrow20 = i10;
                    columnIndexOrThrow22 = i12;
                    columnIndexOrThrow23 = i13;
                    columnIndexOrThrow24 = i14;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow2 = i8;
                    columnIndexOrThrow18 = i7;
                }
                ArrayList arrayList3 = arrayList;
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList3;
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

    @Override // com.movieboxpro.android.db.dao.DownloadDao
    public List<Download> selectByTvId(final String movie_id, final int box_type) throws Throwable {
        RoomSQLiteQuery roomSQLiteQuery;
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM downloads WHERE movie_id==? AND box_type = ? ", 2);
        if (movie_id == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, movie_id);
        }
        roomSQLiteQueryAcquire.bindLong(2, box_type);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "vip_only");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "poster");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "runtime");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "add_time");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "is_collect");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "download");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileName");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            roomSQLiteQuery = roomSQLiteQueryAcquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fileLength");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "size");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "statue");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "progress");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "speed");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(cursorQuery, IjkMediaMeta.IJKM_KEY_FORMAT);
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "count");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "dateline");
                int columnIndexOrThrow23 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "mmfid");
                int columnIndexOrThrow24 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
                int columnIndexOrThrow25 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
                int columnIndexOrThrow26 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
                int columnIndexOrThrow27 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "tmfid");
                int columnIndexOrThrow28 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonid");
                int columnIndexOrThrow29 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasontitle");
                int columnIndexOrThrow30 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "seasonthumbs");
                int columnIndexOrThrow31 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
                int columnIndexOrThrow32 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "fid");
                int i = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(cursorQuery.getCount());
                while (cursorQuery.moveToNext()) {
                    Download download = new Download();
                    ArrayList arrayList2 = arrayList;
                    download.setId(cursorQuery.getInt(columnIndexOrThrow));
                    download.setMovieId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                    download.setMbId(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                    download.setTitle(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                    download.setVip_only(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                    download.setDisplay(cursorQuery.isNull(columnIndexOrThrow6) ? null : cursorQuery.getString(columnIndexOrThrow6));
                    download.setPoster(cursorQuery.isNull(columnIndexOrThrow7) ? null : cursorQuery.getString(columnIndexOrThrow7));
                    download.setRuntime(cursorQuery.isNull(columnIndexOrThrow8) ? null : cursorQuery.getString(columnIndexOrThrow8));
                    download.setAdd_time(cursorQuery.isNull(columnIndexOrThrow9) ? null : cursorQuery.getString(columnIndexOrThrow9));
                    download.setIs_collect(cursorQuery.getInt(columnIndexOrThrow10));
                    download.setDownload(cursorQuery.isNull(columnIndexOrThrow11) ? null : cursorQuery.getString(columnIndexOrThrow11));
                    download.setFileName(cursorQuery.isNull(columnIndexOrThrow12) ? null : cursorQuery.getString(columnIndexOrThrow12));
                    download.setPath(cursorQuery.isNull(columnIndexOrThrow13) ? null : cursorQuery.getString(columnIndexOrThrow13));
                    int i2 = i;
                    download.setQuality(cursorQuery.isNull(i2) ? null : cursorQuery.getString(i2));
                    int i3 = columnIndexOrThrow15;
                    int i4 = columnIndexOrThrow13;
                    int i5 = columnIndexOrThrow2;
                    download.setFileLength(cursorQuery.getLong(i3));
                    int i6 = columnIndexOrThrow16;
                    download.setSize(cursorQuery.isNull(i6) ? null : cursorQuery.getString(i6));
                    int i7 = columnIndexOrThrow17;
                    download.setStatue(cursorQuery.getInt(i7));
                    int i8 = columnIndexOrThrow18;
                    download.setProgress(cursorQuery.getInt(i8));
                    int i9 = columnIndexOrThrow19;
                    download.setSpeed(cursorQuery.getLong(i9));
                    int i10 = columnIndexOrThrow20;
                    download.setFormat(cursorQuery.isNull(i10) ? null : cursorQuery.getString(i10));
                    int i11 = columnIndexOrThrow21;
                    download.setCount(cursorQuery.isNull(i11) ? null : cursorQuery.getString(i11));
                    columnIndexOrThrow20 = i10;
                    int i12 = columnIndexOrThrow22;
                    download.setDateline(cursorQuery.getLong(i12));
                    int i13 = columnIndexOrThrow23;
                    download.setMmfid(cursorQuery.isNull(i13) ? null : cursorQuery.getString(i13));
                    int i14 = columnIndexOrThrow24;
                    download.setSeason(cursorQuery.getInt(i14));
                    int i15 = columnIndexOrThrow25;
                    download.setEpisode(cursorQuery.getInt(i15));
                    columnIndexOrThrow25 = i15;
                    int i16 = columnIndexOrThrow26;
                    download.setBox_type(cursorQuery.getInt(i16));
                    columnIndexOrThrow26 = i16;
                    int i17 = columnIndexOrThrow27;
                    download.setTmfid(cursorQuery.getInt(i17));
                    int i18 = columnIndexOrThrow28;
                    download.setSeasonid(cursorQuery.isNull(i18) ? null : cursorQuery.getString(i18));
                    columnIndexOrThrow29 = columnIndexOrThrow29;
                    download.setSeasontitle(cursorQuery.isNull(columnIndexOrThrow29) ? null : cursorQuery.getString(columnIndexOrThrow29));
                    columnIndexOrThrow30 = columnIndexOrThrow30;
                    download.setSeasonthumbs(cursorQuery.isNull(columnIndexOrThrow30) ? null : cursorQuery.getString(columnIndexOrThrow30));
                    columnIndexOrThrow31 = columnIndexOrThrow31;
                    download.setPrivateid(cursorQuery.isNull(columnIndexOrThrow31) ? null : cursorQuery.getString(columnIndexOrThrow31));
                    columnIndexOrThrow32 = columnIndexOrThrow32;
                    download.setFid(cursorQuery.isNull(columnIndexOrThrow32) ? null : cursorQuery.getString(columnIndexOrThrow32));
                    arrayList2.add(download);
                    columnIndexOrThrow27 = i17;
                    columnIndexOrThrow28 = i18;
                    arrayList = arrayList2;
                    columnIndexOrThrow = columnIndexOrThrow;
                    columnIndexOrThrow2 = i5;
                    columnIndexOrThrow15 = i3;
                    columnIndexOrThrow16 = i6;
                    columnIndexOrThrow18 = i8;
                    columnIndexOrThrow21 = i11;
                    columnIndexOrThrow22 = i12;
                    columnIndexOrThrow23 = i13;
                    columnIndexOrThrow13 = i4;
                    i = i2;
                    columnIndexOrThrow17 = i7;
                    columnIndexOrThrow19 = i9;
                    columnIndexOrThrow24 = i14;
                }
                ArrayList arrayList3 = arrayList;
                cursorQuery.close();
                roomSQLiteQuery.release();
                return arrayList3;
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
