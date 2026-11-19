package com.movieboxpro.android.db.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.movieboxpro.android.db.entity.ConnectUsbDevice;
import com.movieboxpro.android.service.FileDownloadService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ConnectUsbDeviceDao_Impl implements ConnectUsbDeviceDao {
    private final RoomDatabase __db;
    private final EntityInsertionAdapter<ConnectUsbDevice> __insertionAdapterOfConnectUsbDevice;

    public ConnectUsbDeviceDao_Impl(RoomDatabase __db) {
        this.__db = __db;
        this.__insertionAdapterOfConnectUsbDevice = new EntityInsertionAdapter<ConnectUsbDevice>(this, __db) { // from class: com.movieboxpro.android.db.dao.ConnectUsbDeviceDao_Impl.1
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `connectusbdevice` (`path`,`downloadPath`,`productName`) VALUES (?,?,?)";
            }

            @Override // androidx.room.EntityInsertionAdapter
            public void bind(SupportSQLiteStatement stmt, ConnectUsbDevice value) {
                if (value.getPath() == null) {
                    stmt.bindNull(1);
                } else {
                    stmt.bindString(1, value.getPath());
                }
                if (value.getDownloadPath() == null) {
                    stmt.bindNull(2);
                } else {
                    stmt.bindString(2, value.getDownloadPath());
                }
                if (value.getProductName() == null) {
                    stmt.bindNull(3);
                } else {
                    stmt.bindString(3, value.getProductName());
                }
            }
        };
    }

    @Override // com.movieboxpro.android.db.dao.ConnectUsbDeviceDao
    public void insert(final ConnectUsbDevice log) {
        this.__db.assertNotSuspendingTransaction();
        this.__db.beginTransaction();
        try {
            this.__insertionAdapterOfConnectUsbDevice.insert(log);
            this.__db.setTransactionSuccessful();
        } finally {
            this.__db.endTransaction();
        }
    }

    @Override // com.movieboxpro.android.db.dao.ConnectUsbDeviceDao
    public ConnectUsbDevice loadByPath(String str) {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM connectusbdevice WHERE path LIKE ?", 1);
        if (str == null) {
            roomSQLiteQueryAcquire.bindNull(1);
        } else {
            roomSQLiteQueryAcquire.bindString(1, str);
        }
        this.__db.assertNotSuspendingTransaction();
        ConnectUsbDevice connectUsbDevice = null;
        String string = null;
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadPath");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "productName");
            if (cursorQuery.moveToFirst()) {
                String string2 = cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow);
                String string3 = cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2);
                if (!cursorQuery.isNull(columnIndexOrThrow3)) {
                    string = cursorQuery.getString(columnIndexOrThrow3);
                }
                connectUsbDevice = new ConnectUsbDevice(string2, string3, string);
            }
            return connectUsbDevice;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    @Override // com.movieboxpro.android.db.dao.ConnectUsbDeviceDao
    public List<ConnectUsbDevice> getAll() {
        RoomSQLiteQuery roomSQLiteQueryAcquire = RoomSQLiteQuery.acquire("SELECT * FROM connectusbdevice", 0);
        this.__db.assertNotSuspendingTransaction();
        Cursor cursorQuery = DBUtil.query(this.__db, roomSQLiteQueryAcquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(cursorQuery, FileDownloadService.PARAMS_KEY_PATH);
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "downloadPath");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(cursorQuery, "productName");
            ArrayList arrayList = new ArrayList(cursorQuery.getCount());
            while (cursorQuery.moveToNext()) {
                arrayList.add(new ConnectUsbDevice(cursorQuery.isNull(columnIndexOrThrow) ? null : cursorQuery.getString(columnIndexOrThrow), cursorQuery.isNull(columnIndexOrThrow2) ? null : cursorQuery.getString(columnIndexOrThrow2), cursorQuery.isNull(columnIndexOrThrow3) ? null : cursorQuery.getString(columnIndexOrThrow3)));
            }
            return arrayList;
        } finally {
            cursorQuery.close();
            roomSQLiteQueryAcquire.release();
        }
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }
}
