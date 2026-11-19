package com.jeffmony.downloader.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* JADX INFO: loaded from: classes5.dex */
public class VideoDownloadSQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "video_download_info.db";
    private static final int DATABASE_VERSION = 4;
    public static final String TABLE_VIDEO_DOWNLOAD_INFO = "video_download_info";

    public static class Columns {
        public static final String CACHED_LENGTH = "cached_length";
        public static final String CACHED_TS = "cached_ts";
        public static final String COMPLETED = "completed";
        public static final String COVER_PATH = "cover_path";
        public static final String COVER_URL = "cover_url";
        public static final String DOWNLOAD_TIME = "download_time";
        public static final String FILE_NAME = "file_name";
        public static final String FILE_PATH = "file_path";
        public static final String GROUP_NAME = "group_name";
        public static final String MIME_TYPE = "mime_type";
        public static final String PERCENT = "percent";
        public static final String TASK_STATE = "task_state";
        public static final String TOTAL_LENGTH = "total_length";
        public static final String TOTAL_TS = "total_ts";
        public static final String VIDEO_TITLE = "video_title";
        public static final String VIDEO_TYPE = "video_type";
        public static final String VIDEO_URL = "video_url";
        public static final String _ID = "_id";
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public VideoDownloadSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, (SQLiteDatabase.CursorFactory) null, 4);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        createVideoDownloadInfoTable(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 1) {
            upgradeDatabaseToVersion2(sQLiteDatabase);
        }
        if (i == 2) {
            upgradeDatabaseToVersion3(sQLiteDatabase);
        }
        if (i == 3) {
            upgradeDatabaseToVersion4(sQLiteDatabase);
        }
    }

    private void createVideoDownloadInfoTable(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS video_download_info");
        sQLiteDatabase.execSQL("CREATE TABLE video_download_info(_id INTEGER PRIMARY KEY AUTOINCREMENT, video_url TEXT, mime_type TEXT, download_time BIGINT, percent REAL, task_state TEXT, video_type TINYINT, cached_length BIGINT, total_length BIGINT, cached_ts INTEGER, total_ts INTEGER , completed TINYINT, file_name TEXT Default 0, file_path TEXT, cover_url TEXT, cover_path TEXT,video_title TEXT,group_name TEXT);");
    }

    private void upgradeDatabaseToVersion2(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE video_download_info ADD COLUMN cover_url TEXT");
        sQLiteDatabase.execSQL("ALTER TABLE video_download_info ADD COLUMN cover_path TEXT");
    }

    private void upgradeDatabaseToVersion3(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE video_download_info ADD COLUMN video_title TEXT");
    }

    private void upgradeDatabaseToVersion4(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE video_download_info ADD COLUMN group_name TEXT");
    }
}
