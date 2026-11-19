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
import com.movieboxpro.android.db.entity.PlayRecode;
import com.movieboxpro.android.service.DealWatchNextService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class PlayRecodeDao_Impl implements PlayRecodeDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<PlayRecode> __deletionAdapterOfPlayRecode;
    private final EntityInsertionAdapter<PlayRecode> __insertionAdapterOfPlayRecode;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByTid;
    private final EntityDeletionOrUpdateAdapter<PlayRecode> __updateAdapterOfPlayRecode;

    public PlayRecodeDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfPlayRecode = new EntityInsertionAdapter<PlayRecode>(this, __db) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `playrecode` (`id`,`box_type`,`imdb_id`,`movie_id`,`title`,`start_time`,`quality`,`season`,`episode`,`privateid`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, PlayRecode value) {
                stmt.bindLong(1, value.getId());
                stmt.bindLong(2, value.getBox_type());
                if (value.getImdb_id() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getImdb_id());
                }
                if (value.getMovieId() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getMovieId());
                }
                if (value.getTitle() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getTitle());
                }
                stmt.bindLong(6, value.getStart_time());
                stmt.bindLong(7, value.getQuality());
                stmt.bindLong(8, value.getSeason());
                stmt.bindLong(9, value.getEpisode());
                if (value.getPrivateid() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getPrivateid());
                }
            }
        };
        this.__deletionAdapterOfPlayRecode = new EntityDeletionOrUpdateAdapter<PlayRecode>(this, __db) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `playrecode` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, PlayRecode value) {
                stmt.bindLong(1, value.getId());
            }
        };
        this.__updateAdapterOfPlayRecode = new EntityDeletionOrUpdateAdapter<PlayRecode>(this, __db) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `playrecode` SET `id` = ?,`box_type` = ?,`imdb_id` = ?,`movie_id` = ?,`title` = ?,`start_time` = ?,`quality` = ?,`season` = ?,`episode` = ?,`privateid` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, PlayRecode value) {
                stmt.bindLong(1, value.getId());
                stmt.bindLong(2, value.getBox_type());
                if (value.getImdb_id() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getImdb_id());
                }
                if (value.getMovieId() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getMovieId());
                }
                if (value.getTitle() == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.getTitle());
                }
                stmt.bindLong(6, value.getStart_time());
                stmt.bindLong(7, value.getQuality());
                stmt.bindLong(8, value.getSeason());
                stmt.bindLong(9, value.getEpisode());
                if (value.getPrivateid() == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindString(10, value.getPrivateid());
                }
                stmt.bindLong(11, value.getId());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM playrecode WHERE movie_id = ?";
            }
        };
        this.__preparedStmtOfDeleteByTid = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM playrecode WHERE privateid== ? ";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.PlayRecodeDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM playrecode";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public void insert(final PlayRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfPlayRecode.insert(playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public void delete(final PlayRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfPlayRecode.handle(playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public void update(final PlayRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfPlayRecode.handle(playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
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

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
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

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
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

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public List<PlayRecode> getAll() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.PARAM_START_TIME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                PlayRecode playRecode = new PlayRecode();
                playRecode.setId(cursorQuery.getInt(columnIndexOrThrow));
                playRecode.setBox_type(cursorQuery.getInt(columnIndexOrThrow2));
                playRecode.setImdb_id(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                playRecode.setMovieId(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                playRecode.setTitle(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                playRecode.setStart_time(cursorQuery.getInt(columnIndexOrThrow6));
                playRecode.setQuality(cursorQuery.getInt(columnIndexOrThrow7));
                playRecode.setSeason(cursorQuery.getInt(columnIndexOrThrow8));
                playRecode.setEpisode(cursorQuery.getInt(columnIndexOrThrow9));
                playRecode.setPrivateid(cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10));
                arrayList.add(playRecode);
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public List<PlayRecode> findByType(final int type) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode WHERE box_type== ? LIMIT 1", 1);
        roomSQLiteQueryAcquire.bindLong(1, type);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.PARAM_START_TIME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                PlayRecode playRecode = new PlayRecode();
                playRecode.setId(cursorQuery.getInt(columnIndexOrThrow));
                playRecode.setBox_type(cursorQuery.getInt(columnIndexOrThrow2));
                playRecode.setImdb_id(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                playRecode.setMovieId(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                playRecode.setTitle(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                playRecode.setStart_time(cursorQuery.getInt(columnIndexOrThrow6));
                playRecode.setQuality(cursorQuery.getInt(columnIndexOrThrow7));
                playRecode.setSeason(cursorQuery.getInt(columnIndexOrThrow8));
                playRecode.setEpisode(cursorQuery.getInt(columnIndexOrThrow9));
                playRecode.setPrivateid(cursorQuery.isNull(columnIndexOrThrow10) ? null : cursorQuery.getString(columnIndexOrThrow10));
                arrayList.add(playRecode);
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public PlayRecode findByType(int i, String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode WHERE box_type== ? AND privateid = ? LIMIT 1 ", 2);
        roomSQLiteQueryAcquire.bindLong(1, i);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        PlayRecode playRecode = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.PARAM_START_TIME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
            if (cursorQuery.moveToFirst()) {
                PlayRecode playRecode2 = new PlayRecode();
                playRecode2.setId(cursorQuery.getInt(columnIndexOrThrow));
                playRecode2.setBox_type(cursorQuery.getInt(columnIndexOrThrow2));
                playRecode2.setImdb_id(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                playRecode2.setMovieId(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                playRecode2.setTitle(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                playRecode2.setStart_time(cursorQuery.getInt(columnIndexOrThrow6));
                playRecode2.setQuality(cursorQuery.getInt(columnIndexOrThrow7));
                playRecode2.setSeason(cursorQuery.getInt(columnIndexOrThrow8));
                playRecode2.setEpisode(cursorQuery.getInt(columnIndexOrThrow9));
                if (!cursorQuery.isNull(columnIndexOrThrow10)) {
                    string = cursorQuery.getString(columnIndexOrThrow10);
                }
                playRecode2.setPrivateid(string);
                playRecode = playRecode2;
            }
            return playRecode;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public PlayRecode findByTypeid(int i, String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode WHERE box_type== ? AND movie_id = ? LIMIT 1 ", 2);
        roomSQLiteQueryAcquire.bindLong(1, i);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        this.__db.assertNotSuspendingTransaction();
        PlayRecode playRecode = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.PARAM_START_TIME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
            if (cursorQuery.moveToFirst()) {
                PlayRecode playRecode2 = new PlayRecode();
                playRecode2.setId(cursorQuery.getInt(columnIndexOrThrow));
                playRecode2.setBox_type(cursorQuery.getInt(columnIndexOrThrow2));
                playRecode2.setImdb_id(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                playRecode2.setMovieId(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                playRecode2.setTitle(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                playRecode2.setStart_time(cursorQuery.getInt(columnIndexOrThrow6));
                playRecode2.setQuality(cursorQuery.getInt(columnIndexOrThrow7));
                playRecode2.setSeason(cursorQuery.getInt(columnIndexOrThrow8));
                playRecode2.setEpisode(cursorQuery.getInt(columnIndexOrThrow9));
                if (!cursorQuery.isNull(columnIndexOrThrow10)) {
                    string = cursorQuery.getString(columnIndexOrThrow10);
                }
                playRecode2.setPrivateid(string);
                playRecode = playRecode2;
            }
            return playRecode;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.PlayRecodeDao
    public PlayRecode findByEpisode(int i, String str, int i2, int i3) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM playrecode WHERE box_type== ? AND movie_id = ? AND season= ? AND episode= ?   LIMIT 1 ", 4);
        roomSQLiteQueryAcquire.bindLong(1, i);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(2);
        } else {
            roomSQLiteQueryAcquire.bindString(2, str);
        }
        roomSQLiteQueryAcquire.bindLong(3, i2);
        roomSQLiteQueryAcquire.bindLong(4, i3);
        this.__db.assertNotSuspendingTransaction();
        PlayRecode playRecode = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "box_type");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "imdb_id");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "movie_id");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.ProgramColumns.COLUMN_TITLE);
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.PARAM_START_TIME);
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "quality");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.SEASON);
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, DealWatchNextService.EPISODE);
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "privateid");
            if (cursorQuery.moveToFirst()) {
                PlayRecode playRecode2 = new PlayRecode();
                playRecode2.setId(cursorQuery.getInt(columnIndexOrThrow));
                playRecode2.setBox_type(cursorQuery.getInt(columnIndexOrThrow2));
                playRecode2.setImdb_id(cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3));
                playRecode2.setMovieId(cursorQuery.isNull(columnIndexOrThrow4) ? null : cursorQuery.getString(columnIndexOrThrow4));
                playRecode2.setTitle(cursorQuery.isNull(columnIndexOrThrow5) ? null : cursorQuery.getString(columnIndexOrThrow5));
                playRecode2.setStart_time(cursorQuery.getInt(columnIndexOrThrow6));
                playRecode2.setQuality(cursorQuery.getInt(columnIndexOrThrow7));
                playRecode2.setSeason(cursorQuery.getInt(columnIndexOrThrow8));
                playRecode2.setEpisode(cursorQuery.getInt(columnIndexOrThrow9));
                if (!cursorQuery.isNull(columnIndexOrThrow10)) {
                    string = cursorQuery.getString(columnIndexOrThrow10);
                }
                playRecode2.setPrivateid(string);
                playRecode = playRecode2;
            }
            return playRecode;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
