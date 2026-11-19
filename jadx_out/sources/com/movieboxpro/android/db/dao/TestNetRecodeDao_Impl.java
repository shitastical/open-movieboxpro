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
import com.movieboxpro.android.db.entity.TestNetRecode;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class TestNetRecodeDao_Impl implements TestNetRecodeDao {
    private final RoomDatabase __db;
    private final EntityDeletionOrUpdateAdapter<TestNetRecode> __deletionAdapterOfTestNetRecode;
    private final EntityInsertionAdapter<TestNetRecode> __insertionAdapterOfTestNetRecode;
    private final SharedSQLiteStatement __preparedStmtOfClear;
    private final SharedSQLiteStatement __preparedStmtOfDeleteById;
    private final EntityDeletionOrUpdateAdapter<TestNetRecode> __updateAdapterOfTestNetRecode;

    public TestNetRecodeDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfTestNetRecode = new EntityInsertionAdapter<TestNetRecode>(this, __db) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `testnetrecode` (`ids`,`id`,`country`,`description`,`domain`,`display_order`,`ratio`,`url`,`startTime`,`endTime`,`state`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, TestNetRecode value) {
                stmt.bindLong(1, value.getIds());
                stmt.bindLong(2, value.id);
                if (value.country == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.country);
                }
                if (value.description == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.description);
                }
                if (value.domain == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.domain);
                }
                stmt.bindLong(6, value.display_order);
                stmt.bindDouble(7, value.ratio);
                if (value.url == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.url);
                }
                if (value.startTime == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindLong(9, value.startTime.longValue());
                }
                if (value.endTime == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindLong(10, value.endTime.longValue());
                }
                if (value.state == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.state);
                }
            }
        };
        this.__deletionAdapterOfTestNetRecode = new EntityDeletionOrUpdateAdapter<TestNetRecode>(this, __db) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.2
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM `testnetrecode` WHERE `ids` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, TestNetRecode value) {
                stmt.bindLong(1, value.getIds());
            }
        };
        this.__updateAdapterOfTestNetRecode = new EntityDeletionOrUpdateAdapter<TestNetRecode>(this, __db) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.3
            @Override // androidx.room.EntityDeletionOrUpdateAdapter, androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "UPDATE OR ABORT `testnetrecode` SET `ids` = ?,`id` = ?,`country` = ?,`description` = ?,`domain` = ?,`display_order` = ?,`ratio` = ?,`url` = ?,`startTime` = ?,`endTime` = ?,`state` = ? WHERE `ids` = ?";
            }

            @Override // androidx.room.EntityDeletionOrUpdateAdapter
            public void bind(SupportSQLiteStatement stmt, TestNetRecode value) {
                stmt.bindLong(1, value.getIds());
                stmt.bindLong(2, value.id);
                if (value.country == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.country);
                }
                if (value.description == null) {
                    stmt.bindNull(4);
                } else {
                    stmt.bindString(4, value.description);
                }
                if (value.domain == null) {
                    stmt.bindNull(5);
                } else {
                    stmt.bindString(5, value.domain);
                }
                stmt.bindLong(6, value.display_order);
                stmt.bindDouble(7, value.ratio);
                if (value.url == null) {
                    stmt.bindNull(8);
                } else {
                    stmt.bindString(8, value.url);
                }
                if (value.startTime == null) {
                    stmt.bindNull(9);
                } else {
                    stmt.bindLong(9, value.startTime.longValue());
                }
                if (value.endTime == null) {
                    stmt.bindNull(10);
                } else {
                    stmt.bindLong(10, value.endTime.longValue());
                }
                if (value.state == null) {
                    stmt.bindNull(11);
                } else {
                    stmt.bindString(11, value.state);
                }
                stmt.bindLong(12, value.getIds());
            }
        };
        this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM testnetrecode WHERE ids = ?";
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM testnetrecode";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void insert(final TestNetRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfTestNetRecode.insert(playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void delete(final TestNetRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__deletionAdapterOfTestNetRecode.handle(playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void update(final TestNetRecode playRecode) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__updateAdapterOfTestNetRecode.handle(playRecode);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public void deleteById(final int ids) {
        this.__db.assertNotSuspendingTransaction();
        SupportSQLiteStatement supportSQLiteStatementAcquire = this.__preparedStmtOfDeleteById.acquire();
        supportSQLiteStatementAcquire.bindLong(1, ids);
        this.__db.beginTransaction();
        try {
            supportSQLiteStatementAcquire.executeUpdateDelete();
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
            this.__preparedStmtOfDeleteById.release(supportSQLiteStatementAcquire);
        }
    }

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
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

    @Override // com.movieboxpro.android.db.dao.TestNetRecodeDao
    public TestNetRecode findAll(final int ids) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM testnetrecode WHERE ids== ? LIMIT 1 ", 1);
        roomSQLiteQueryAcquire.bindLong(1, ids);
        this.__db.assertNotSuspendingTransaction();
        TestNetRecode testNetRecode = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "ids");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "id");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "country");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(cursorQuery, TvContractCompat.Channels.COLUMN_DESCRIPTION);
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "domain");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "display_order");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "ratio");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "url");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "startTime");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "endTime");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "state");
            if (cursorQuery.moveToFirst()) {
                TestNetRecode testNetRecode2 = new TestNetRecode();
                testNetRecode2.setIds(cursorQuery.getInt(columnIndexOrThrow));
                testNetRecode2.id = cursorQuery.getInt(columnIndexOrThrow2);
                if (cursorQuery.isNull(columnIndexOrThrow3)) {
                    testNetRecode2.country = null;
                } else {
                    testNetRecode2.country = cursorQuery.getString(columnIndexOrThrow3);
                }
                if (cursorQuery.isNull(columnIndexOrThrow4)) {
                    testNetRecode2.description = null;
                } else {
                    testNetRecode2.description = cursorQuery.getString(columnIndexOrThrow4);
                }
                if (cursorQuery.isNull(columnIndexOrThrow5)) {
                    testNetRecode2.domain = null;
                } else {
                    testNetRecode2.domain = cursorQuery.getString(columnIndexOrThrow5);
                }
                testNetRecode2.display_order = cursorQuery.getInt(columnIndexOrThrow6);
                testNetRecode2.ratio = cursorQuery.getFloat(columnIndexOrThrow7);
                if (cursorQuery.isNull(columnIndexOrThrow8)) {
                    testNetRecode2.url = null;
                } else {
                    testNetRecode2.url = cursorQuery.getString(columnIndexOrThrow8);
                }
                if (cursorQuery.isNull(columnIndexOrThrow9)) {
                    testNetRecode2.startTime = null;
                } else {
                    testNetRecode2.startTime = Long.valueOf(cursorQuery.getLong(columnIndexOrThrow9));
                }
                if (cursorQuery.isNull(columnIndexOrThrow10)) {
                    testNetRecode2.endTime = null;
                } else {
                    testNetRecode2.endTime = Long.valueOf(cursorQuery.getLong(columnIndexOrThrow10));
                }
                if (cursorQuery.isNull(columnIndexOrThrow11)) {
                    testNetRecode2.state = null;
                } else {
                    testNetRecode2.state = cursorQuery.getString(columnIndexOrThrow11);
                }
                testNetRecode = testNetRecode2;
            }
            return testNetRecode;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
