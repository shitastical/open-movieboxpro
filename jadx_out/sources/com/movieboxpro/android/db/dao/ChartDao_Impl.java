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
import com.movieboxpro.android.db.entity.Chart;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ChartDao_Impl implements ChartDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<Chart> __deletionAdapterOfChart;
    private final EntityInsertionAdapter<Chart> __insertionAdapterOfChart;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfClearNull;
    private final SharedSQLiteStatement __preparedStmtOfDeleteByBookID;
    private final EntityDeletionOrUpdateAdapter<Chart> __updateAdapterOfChart;

    public ChartDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfChart = new EntityInsertionAdapter<Chart>(this, __db) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `Char_list` (`id`,`user_id`,`book_id`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, Chart value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getUserId() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getUserId());
                }
                if (value.getBookId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getBookId());
                }
            }
        };
        this.__deletionAdapterOfChart = new EntityDeletionOrUpdateAdapter<Chart>(this, __db) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `Char_list` WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Chart value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
            }
        };
        this.__updateAdapterOfChart = new EntityDeletionOrUpdateAdapter<Chart>(this, __db) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `Char_list` SET `id` = ?,`user_id` = ?,`book_id` = ? WHERE `id` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, Chart value) {
                if (value.getId() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getId());
                }
                if (value.getUserId() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getUserId());
                }
                if (value.getBookId() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getBookId());
                }
                if (value.getId() == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.getId());
                }
            }
        };
        this.__preparedStmtOfClearNull = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Char_list WHERE book_id IS NULL";
            }
        };
        this.__preparedStmtOfDeleteByBookID = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Char_list WHERE book_id = ?";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.ChartDao_Impl.6
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM Char_list";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void insert(final Chart log) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfChart.insert(log);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void delete(final Chart log) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfChart.handle(log);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void update(final Chart log) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfChart.handle(log);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void clearNull() {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfClearNull.acquire();
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfClearNull.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public void deleteByBookID(final String bookId) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteByBookID.acquire();
        if (bookId == null) {
            supportSQLiteStatementAcquire.bindNull(1);
        } else {
            supportSQLiteStatementAcquire.bindString(1, bookId);
        }
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteByBookID.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.ChartDao
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

    @Override // com.movieboxpro.android.db.dao.ChartDao
    public Chart loadByID(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM Char_list WHERE book_id = ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        Chart chart = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "user_id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "book_id");
            if (cursorQuery.moveToFirst()) {
                Chart chart2 = new Chart();
                chart2.setId(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow));
                chart2.setUserId(cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2));
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                chart2.setBookId(string);
                chart = chart2;
            }
            return chart;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
