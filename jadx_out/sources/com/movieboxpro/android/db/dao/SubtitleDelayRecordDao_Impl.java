package com.movieboxpro.android.db.dao;

import android.database.Cursor;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.movieboxpro.android.db.entity.SubtitleDelayRecord;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class SubtitleDelayRecordDao_Impl implements SubtitleDelayRecordDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<SubtitleDelayRecord> __insertionAdapterOfSubtitleDelayRecord;
    private final SharedSQLiteStatement __preparedStmtOfClear;

    public SubtitleDelayRecordDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfSubtitleDelayRecord = new EntityInsertionAdapter<SubtitleDelayRecord>(this, __db) { // from class: com.movieboxpro.android.db.dao.SubtitleDelayRecordDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `subtitle_delay_record` (`delaySecond`,`sid`) VALUES (?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, SubtitleDelayRecord value) {
                stmt.bindLong(1, value.getDelaySecond());
                if (value.getSid() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getSid());
                }
            }
        };
        this.__preparedStmtOfClear = new SharedSQLiteStatement(this, __db) { // from class: com.movieboxpro.android.db.dao.SubtitleDelayRecordDao_Impl.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "DELETE FROM subtitle_delay_record";
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.SubtitleDelayRecordDao
    public long insert(final SubtitleDelayRecord subtitleDelayRecord) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            long jInsertAndReturnId = this.__insertionAdapterOfSubtitleDelayRecord.insertAndReturnId(subtitleDelayRecord);
            this.__db.setTransactionSuccessful();
            return jInsertAndReturnId;
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.SubtitleDelayRecordDao
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

    @Override // com.movieboxpro.android.db.dao.SubtitleDelayRecordDao
    public SubtitleDelayRecord findDelayRecordBySid(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM subtitle_delay_record WHERE sid == ? LIMIT 1", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        SubtitleDelayRecord subtitleDelayRecord = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, "delaySecond");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, CmcdConfiguration.KEY_SESSION_ID);
            if (cursorQuery.moveToFirst()) {
                SubtitleDelayRecord subtitleDelayRecord2 = new SubtitleDelayRecord();
                subtitleDelayRecord2.setDelaySecond(cursorQuery.getInt(columnIndexOrThrow));
                if (!cursorQuery.isNull(columnIndexOrThrow2)) {
                    string = cursorQuery.getString(columnIndexOrThrow2);
                }
                subtitleDelayRecord2.setSid(string);
                subtitleDelayRecord = subtitleDelayRecord2;
            }
            return subtitleDelayRecord;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
