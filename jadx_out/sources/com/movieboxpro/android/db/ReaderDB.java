package com.movieboxpro.android.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import androidx.room.RoomDatabase;
import com.movieboxpro.android.db.dao.ChartDao;
import com.movieboxpro.android.db.dao.ConnectUsbDeviceDao;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.dao.DownloadFileDao;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.dao.PlayRecodeDao;
import com.movieboxpro.android.db.dao.SubtitleDelayRecordDao;
import com.movieboxpro.android.db.dao.TestNetRecodeDao;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ReaderDB.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\b\u0010\u0012\u001a\u00020\u0013H&J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\u0006\u0010\u0016\u001a\u00020\u0015J1\u0010\u0017\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0010\u0010\u001a\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010\u001b2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002¢\u0006\u0002\u0010\u001e¨\u0006\u001f"}, d2 = {"Lcom/movieboxpro/android/db/ReaderDB;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "chartDao", "Lcom/movieboxpro/android/db/dao/ChartDao;", "downloadDao", "Lcom/movieboxpro/android/db/dao/DownloadDao;", "playRecodeDao", "Lcom/movieboxpro/android/db/dao/PlayRecodeDao;", "testnetRecodeDao", "Lcom/movieboxpro/android/db/dao/TestNetRecodeDao;", "subtitleDelayRecordDao", "Lcom/movieboxpro/android/db/dao/SubtitleDelayRecordDao;", "downloadFileDao", "Lcom/movieboxpro/android/db/dao/DownloadFileDao;", "connectUsbDeviceDao", "Lcom/movieboxpro/android/db/dao/ConnectUsbDeviceDao;", "hlsDownloadDao", "Lcom/movieboxpro/android/db/dao/HlsDownloadDao;", "clearAllTables", "", "clearPlayRecord", "addColumn", "db", "Landroid/database/sqlite/SQLiteDatabase;", "newColumnArr", "", "", "oldTableName", "(Landroid/database/sqlite/SQLiteDatabase;[Ljava/lang/String;Ljava/lang/String;)V", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public abstract class ReaderDB extends RoomDatabase {
    public abstract ChartDao chartDao();

    public abstract ConnectUsbDeviceDao connectUsbDeviceDao();

    public abstract DownloadDao downloadDao();

    public abstract DownloadFileDao downloadFileDao();

    public abstract HlsDownloadDao hlsDownloadDao();

    public abstract PlayRecodeDao playRecodeDao();

    public abstract SubtitleDelayRecordDao subtitleDelayRecordDao();

    public abstract TestNetRecodeDao testnetRecodeDao();

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        chartDao().clear();
        downloadDao().clear();
        playRecodeDao().clear();
        testnetRecodeDao().clear();
        subtitleDelayRecordDao().clear();
        downloadFileDao().clear();
    }

    public final void clearPlayRecord() {
        playRecodeDao().clear();
        testnetRecodeDao().clear();
    }

    private final void addColumn(SQLiteDatabase db, String[] newColumnArr, String oldTableName) {
        if (db == null || newColumnArr == null || newColumnArr.length < 1 || TextUtils.isEmpty(oldTableName)) {
            return;
        }
        Cursor cursorRawQuery = db.rawQuery("select * from " + oldTableName, null);
        Intrinsics.checkNotNullExpressionValue(cursorRawQuery, "rawQuery(...)");
        String[] columnNames = cursorRawQuery.getColumnNames();
        String str = "_temp_" + oldTableName;
        db.execSQL("alter table " + oldTableName + " rename to " + str);
        if (columnNames.length < 1) {
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("create table if not exists " + oldTableName + "(");
        int length = columnNames.length;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                stringBuffer.append(columnNames[i] + " integer primary key autoincrement,");
            } else {
                stringBuffer.append(columnNames[i] + ",");
            }
        }
        int length2 = newColumnArr.length;
        for (int i2 = 0; i2 < length2; i2++) {
            if (i2 == newColumnArr.length - 1) {
                stringBuffer.append(newColumnArr[i2] + ")");
            } else {
                stringBuffer.append(newColumnArr[i2] + ",");
            }
        }
        db.execSQL(stringBuffer.toString());
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("insert into " + oldTableName + " select *,");
        int length3 = newColumnArr.length;
        for (int i3 = 0; i3 < length3; i3++) {
            if (i3 == newColumnArr.length - 1) {
                stringBuffer2.append("' ' from " + str);
            } else {
                stringBuffer2.append("' ',");
            }
        }
        db.execSQL(stringBuffer2.toString());
        db.execSQL("drop table " + str);
        cursorRawQuery.close();
    }
}
