package com.movieboxpro.android.db;

import androidx.core.app.NotificationCompat;
import androidx.media3.exoplayer.upstream.CmcdConfiguration;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.jeffmony.downloader.database.VideoDownloadSQLiteHelper;
import com.movieboxpro.android.db.dao.ChartDao;
import com.movieboxpro.android.db.dao.ChartDao_Impl;
import com.movieboxpro.android.db.dao.ConnectUsbDeviceDao;
import com.movieboxpro.android.db.dao.ConnectUsbDeviceDao_Impl;
import com.movieboxpro.android.db.dao.DownloadDao;
import com.movieboxpro.android.db.dao.DownloadDao_Impl;
import com.movieboxpro.android.db.dao.DownloadFileDao;
import com.movieboxpro.android.db.dao.DownloadFileDao_Impl;
import com.movieboxpro.android.db.dao.HlsDownloadDao;
import com.movieboxpro.android.db.dao.HlsDownloadDao_Impl;
import com.movieboxpro.android.db.dao.PlayRecodeDao;
import com.movieboxpro.android.db.dao.PlayRecodeDao_Impl;
import com.movieboxpro.android.db.dao.SubtitleDelayRecordDao;
import com.movieboxpro.android.db.dao.SubtitleDelayRecordDao_Impl;
import com.movieboxpro.android.db.dao.TestNetRecodeDao;
import com.movieboxpro.android.db.dao.TestNetRecodeDao_Impl;
import com.movieboxpro.android.service.DealWatchNextService;
import com.movieboxpro.android.service.FileDownloadService;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: loaded from: classes5.dex */
public final class ReaderDB_Impl extends ReaderDB {
    private volatile ChartDao _chartDao;
    private volatile ConnectUsbDeviceDao _connectUsbDeviceDao;
    private volatile DownloadDao _downloadDao;
    private volatile DownloadFileDao _downloadFileDao;
    private volatile HlsDownloadDao _hlsDownloadDao;
    private volatile PlayRecodeDao _playRecodeDao;
    private volatile SubtitleDelayRecordDao _subtitleDelayRecordDao;
    private volatile TestNetRecodeDao _testNetRecodeDao;

    @Override // androidx.room.RoomDatabase
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
        return configuration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(configuration.context).name(configuration.name).callback(new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(12) { // from class: com.movieboxpro.android.db.ReaderDB_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase _db) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("CREATE TABLE IF NOT EXISTS `Char_list` (`id` TEXT NOT NULL, `user_id` TEXT, `book_id` TEXT, PRIMARY KEY(`id`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `downloads` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `movie_id` TEXT, `mb_id` TEXT, `title` TEXT, `vip_only` TEXT, `display` TEXT, `poster` TEXT, `runtime` TEXT, `add_time` TEXT, `is_collect` INTEGER NOT NULL, `download` TEXT, `fileName` TEXT, `path` TEXT, `quality` TEXT, `fileLength` INTEGER NOT NULL, `size` TEXT, `statue` INTEGER NOT NULL, `progress` INTEGER NOT NULL, `speed` INTEGER NOT NULL, `format` TEXT, `count` TEXT, `dateline` INTEGER NOT NULL, `mmfid` TEXT, `season` INTEGER NOT NULL, `episode` INTEGER NOT NULL, `box_type` INTEGER NOT NULL, `tmfid` INTEGER NOT NULL, `seasonid` TEXT, `seasontitle` TEXT, `seasonthumbs` TEXT, `privateid` TEXT, `fid` TEXT)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `playrecode` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `box_type` INTEGER NOT NULL, `imdb_id` TEXT, `movie_id` TEXT, `title` TEXT, `start_time` INTEGER NOT NULL, `quality` INTEGER NOT NULL, `season` INTEGER NOT NULL, `episode` INTEGER NOT NULL, `privateid` TEXT)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `testnetrecode` (`ids` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `id` INTEGER NOT NULL, `country` TEXT, `description` TEXT, `domain` TEXT, `display_order` INTEGER NOT NULL, `ratio` REAL NOT NULL, `url` TEXT, `startTime` INTEGER, `endTime` INTEGER, `state` TEXT)");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `subtitle_delay_record` (`delaySecond` INTEGER NOT NULL, `sid` TEXT NOT NULL, PRIMARY KEY(`sid`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `downloadfile` (`downloadId` TEXT NOT NULL, `id` TEXT, `url` TEXT, `path` TEXT, `fileName` TEXT, `status` INTEGER NOT NULL, `mid` TEXT, `tid` TEXT, `season` INTEGER NOT NULL, `episode` INTEGER NOT NULL, `size` INTEGER NOT NULL, `downloadSize` INTEGER NOT NULL, `boxType` INTEGER NOT NULL, `videoName` TEXT, `poster` TEXT, `quality` TEXT, `thumb` TEXT, `episodeName` TEXT, `downloadTime` INTEGER NOT NULL, `imdbId` TEXT, `speed` TEXT DEFAULT '', `errorMsg` TEXT DEFAULT '', `progress` INTEGER NOT NULL DEFAULT 0, `hls` INTEGER NOT NULL DEFAULT 0, `percent` REAL NOT NULL DEFAULT 0, PRIMARY KEY(`downloadId`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `connectusbdevice` (`path` TEXT NOT NULL, `downloadPath` TEXT, `productName` TEXT, PRIMARY KEY(`path`))");
                _db.execSQL("CREATE TABLE IF NOT EXISTS `hls_download` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `m3u8Url` TEXT NOT NULL, `m3u8LocalPath` TEXT NOT NULL, `downloadId` TEXT NOT NULL, `videoUrl` TEXT NOT NULL, `audioUrl` TEXT NOT NULL, `audioEacUrl` TEXT NOT NULL, `playbackM3u8LocalPath` TEXT NOT NULL, `videoLocalPath` TEXT NOT NULL, `audioLocalPath` TEXT NOT NULL, `audioEacLocalPath` TEXT NOT NULL, `videoSize` INTEGER NOT NULL, `audioSize` INTEGER NOT NULL, `audioEacSize` INTEGER NOT NULL, `videoSpeed` REAL NOT NULL, `audioSpeed` REAL NOT NULL, `audioEacSpeed` REAL NOT NULL, `videoPercent` REAL NOT NULL, `audioPercent` REAL NOT NULL, `audioEacPercent` REAL NOT NULL)");
                _db.execSQL(RoomMasterTable.CREATE_QUERY);
                _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'c692e550faf7c5f48d0feb12f2951fb7')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase _db) {
                _db.execSQL("DROP TABLE IF EXISTS `Char_list`");
                _db.execSQL("DROP TABLE IF EXISTS `downloads`");
                _db.execSQL("DROP TABLE IF EXISTS `playrecode`");
                _db.execSQL("DROP TABLE IF EXISTS `testnetrecode`");
                _db.execSQL("DROP TABLE IF EXISTS `subtitle_delay_record`");
                _db.execSQL("DROP TABLE IF EXISTS `downloadfile`");
                _db.execSQL("DROP TABLE IF EXISTS `connectusbdevice`");
                _db.execSQL("DROP TABLE IF EXISTS `hls_download`");
                if (ReaderDB_Impl.this.mCallbacks != null) {
                    int size = ReaderDB_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ReaderDB_Impl.this.mCallbacks.get(i)).onDestructiveMigration(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected void onCreate(SupportSQLiteDatabase _db) {
                if (ReaderDB_Impl.this.mCallbacks != null) {
                    int size = ReaderDB_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ReaderDB_Impl.this.mCallbacks.get(i)).onCreate(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase _db) {
                ReaderDB_Impl.this.mDatabase = _db;
                ReaderDB_Impl.this.internalInitInvalidationTracker(_db);
                if (ReaderDB_Impl.this.mCallbacks != null) {
                    int size = ReaderDB_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) ReaderDB_Impl.this.mCallbacks.get(i)).onOpen(_db);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase _db) {
                DBUtil.dropFtsSyncTriggers(_db);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
                HashMap map = new HashMap(3);
                map.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                map.put("user_id", new TableInfo.Column("user_id", "TEXT", false, 0, null, 1));
                map.put("book_id", new TableInfo.Column("book_id", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("Char_list", map, new HashSet(0), new HashSet(0));
                TableInfo tableInfo2 = TableInfo.read(_db, "Char_list");
                if (!tableInfo.equals(tableInfo2)) {
                    return new RoomOpenHelper.ValidationResult(false, "Char_list(com.movieboxpro.android.db.entity.Chart).\n Expected:\n" + tableInfo + "\n Found:\n" + tableInfo2);
                }
                HashMap map2 = new HashMap(32);
                map2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                map2.put("movie_id", new TableInfo.Column("movie_id", "TEXT", false, 0, null, 1));
                map2.put("mb_id", new TableInfo.Column("mb_id", "TEXT", false, 0, null, 1));
                map2.put(TvContractCompat.ProgramColumns.COLUMN_TITLE, new TableInfo.Column(TvContractCompat.ProgramColumns.COLUMN_TITLE, "TEXT", false, 0, null, 1));
                map2.put("vip_only", new TableInfo.Column("vip_only", "TEXT", false, 0, null, 1));
                map2.put("display", new TableInfo.Column("display", "TEXT", false, 0, null, 1));
                map2.put("poster", new TableInfo.Column("poster", "TEXT", false, 0, null, 1));
                map2.put("runtime", new TableInfo.Column("runtime", "TEXT", false, 0, null, 1));
                map2.put("add_time", new TableInfo.Column("add_time", "TEXT", false, 0, null, 1));
                map2.put("is_collect", new TableInfo.Column("is_collect", "INTEGER", true, 0, null, 1));
                map2.put("download", new TableInfo.Column("download", "TEXT", false, 0, null, 1));
                map2.put("fileName", new TableInfo.Column("fileName", "TEXT", false, 0, null, 1));
                map2.put(FileDownloadService.PARAMS_KEY_PATH, new TableInfo.Column(FileDownloadService.PARAMS_KEY_PATH, "TEXT", false, 0, null, 1));
                map2.put("quality", new TableInfo.Column("quality", "TEXT", false, 0, null, 1));
                map2.put("fileLength", new TableInfo.Column("fileLength", "INTEGER", true, 0, null, 1));
                map2.put("size", new TableInfo.Column("size", "TEXT", false, 0, null, 1));
                map2.put("statue", new TableInfo.Column("statue", "INTEGER", true, 0, null, 1));
                map2.put("progress", new TableInfo.Column("progress", "INTEGER", true, 0, null, 1));
                map2.put("speed", new TableInfo.Column("speed", "INTEGER", true, 0, null, 1));
                map2.put(IjkMediaMeta.IJKM_KEY_FORMAT, new TableInfo.Column(IjkMediaMeta.IJKM_KEY_FORMAT, "TEXT", false, 0, null, 1));
                map2.put("count", new TableInfo.Column("count", "TEXT", false, 0, null, 1));
                map2.put("dateline", new TableInfo.Column("dateline", "INTEGER", true, 0, null, 1));
                map2.put("mmfid", new TableInfo.Column("mmfid", "TEXT", false, 0, null, 1));
                map2.put(DealWatchNextService.SEASON, new TableInfo.Column(DealWatchNextService.SEASON, "INTEGER", true, 0, null, 1));
                map2.put(DealWatchNextService.EPISODE, new TableInfo.Column(DealWatchNextService.EPISODE, "INTEGER", true, 0, null, 1));
                map2.put("box_type", new TableInfo.Column("box_type", "INTEGER", true, 0, null, 1));
                map2.put("tmfid", new TableInfo.Column("tmfid", "INTEGER", true, 0, null, 1));
                map2.put("seasonid", new TableInfo.Column("seasonid", "TEXT", false, 0, null, 1));
                map2.put("seasontitle", new TableInfo.Column("seasontitle", "TEXT", false, 0, null, 1));
                map2.put("seasonthumbs", new TableInfo.Column("seasonthumbs", "TEXT", false, 0, null, 1));
                map2.put("privateid", new TableInfo.Column("privateid", "TEXT", false, 0, null, 1));
                map2.put("fid", new TableInfo.Column("fid", "TEXT", false, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("downloads", map2, new HashSet(0), new HashSet(0));
                TableInfo tableInfo4 = TableInfo.read(_db, "downloads");
                if (!tableInfo3.equals(tableInfo4)) {
                    return new RoomOpenHelper.ValidationResult(false, "downloads(com.movieboxpro.android.db.entity.Download).\n Expected:\n" + tableInfo3 + "\n Found:\n" + tableInfo4);
                }
                HashMap map3 = new HashMap(10);
                map3.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                map3.put("box_type", new TableInfo.Column("box_type", "INTEGER", true, 0, null, 1));
                map3.put("imdb_id", new TableInfo.Column("imdb_id", "TEXT", false, 0, null, 1));
                map3.put("movie_id", new TableInfo.Column("movie_id", "TEXT", false, 0, null, 1));
                map3.put(TvContractCompat.ProgramColumns.COLUMN_TITLE, new TableInfo.Column(TvContractCompat.ProgramColumns.COLUMN_TITLE, "TEXT", false, 0, null, 1));
                map3.put(TvContractCompat.PARAM_START_TIME, new TableInfo.Column(TvContractCompat.PARAM_START_TIME, "INTEGER", true, 0, null, 1));
                map3.put("quality", new TableInfo.Column("quality", "INTEGER", true, 0, null, 1));
                map3.put(DealWatchNextService.SEASON, new TableInfo.Column(DealWatchNextService.SEASON, "INTEGER", true, 0, null, 1));
                map3.put(DealWatchNextService.EPISODE, new TableInfo.Column(DealWatchNextService.EPISODE, "INTEGER", true, 0, null, 1));
                map3.put("privateid", new TableInfo.Column("privateid", "TEXT", false, 0, null, 1));
                TableInfo tableInfo5 = new TableInfo("playrecode", map3, new HashSet(0), new HashSet(0));
                TableInfo tableInfo6 = TableInfo.read(_db, "playrecode");
                if (!tableInfo5.equals(tableInfo6)) {
                    return new RoomOpenHelper.ValidationResult(false, "playrecode(com.movieboxpro.android.db.entity.PlayRecode).\n Expected:\n" + tableInfo5 + "\n Found:\n" + tableInfo6);
                }
                HashMap map4 = new HashMap(11);
                map4.put("ids", new TableInfo.Column("ids", "INTEGER", true, 1, null, 1));
                map4.put("id", new TableInfo.Column("id", "INTEGER", true, 0, null, 1));
                map4.put("country", new TableInfo.Column("country", "TEXT", false, 0, null, 1));
                map4.put(TvContractCompat.Channels.COLUMN_DESCRIPTION, new TableInfo.Column(TvContractCompat.Channels.COLUMN_DESCRIPTION, "TEXT", false, 0, null, 1));
                map4.put("domain", new TableInfo.Column("domain", "TEXT", false, 0, null, 1));
                map4.put("display_order", new TableInfo.Column("display_order", "INTEGER", true, 0, null, 1));
                map4.put("ratio", new TableInfo.Column("ratio", "REAL", true, 0, null, 1));
                map4.put("url", new TableInfo.Column("url", "TEXT", false, 0, null, 1));
                map4.put("startTime", new TableInfo.Column("startTime", "INTEGER", false, 0, null, 1));
                map4.put("endTime", new TableInfo.Column("endTime", "INTEGER", false, 0, null, 1));
                map4.put("state", new TableInfo.Column("state", "TEXT", false, 0, null, 1));
                TableInfo tableInfo7 = new TableInfo("testnetrecode", map4, new HashSet(0), new HashSet(0));
                TableInfo tableInfo8 = TableInfo.read(_db, "testnetrecode");
                if (!tableInfo7.equals(tableInfo8)) {
                    return new RoomOpenHelper.ValidationResult(false, "testnetrecode(com.movieboxpro.android.db.entity.TestNetRecode).\n Expected:\n" + tableInfo7 + "\n Found:\n" + tableInfo8);
                }
                HashMap map5 = new HashMap(2);
                map5.put("delaySecond", new TableInfo.Column("delaySecond", "INTEGER", true, 0, null, 1));
                map5.put(CmcdConfiguration.KEY_SESSION_ID, new TableInfo.Column(CmcdConfiguration.KEY_SESSION_ID, "TEXT", true, 1, null, 1));
                TableInfo tableInfo9 = new TableInfo("subtitle_delay_record", map5, new HashSet(0), new HashSet(0));
                TableInfo tableInfo10 = TableInfo.read(_db, "subtitle_delay_record");
                if (!tableInfo9.equals(tableInfo10)) {
                    return new RoomOpenHelper.ValidationResult(false, "subtitle_delay_record(com.movieboxpro.android.db.entity.SubtitleDelayRecord).\n Expected:\n" + tableInfo9 + "\n Found:\n" + tableInfo10);
                }
                HashMap map6 = new HashMap(25);
                map6.put("downloadId", new TableInfo.Column("downloadId", "TEXT", true, 1, null, 1));
                map6.put("id", new TableInfo.Column("id", "TEXT", false, 0, null, 1));
                map6.put("url", new TableInfo.Column("url", "TEXT", false, 0, null, 1));
                map6.put(FileDownloadService.PARAMS_KEY_PATH, new TableInfo.Column(FileDownloadService.PARAMS_KEY_PATH, "TEXT", false, 0, null, 1));
                map6.put("fileName", new TableInfo.Column("fileName", "TEXT", false, 0, null, 1));
                map6.put(NotificationCompat.CATEGORY_STATUS, new TableInfo.Column(NotificationCompat.CATEGORY_STATUS, "INTEGER", true, 0, null, 1));
                map6.put("mid", new TableInfo.Column("mid", "TEXT", false, 0, null, 1));
                map6.put("tid", new TableInfo.Column("tid", "TEXT", false, 0, null, 1));
                map6.put(DealWatchNextService.SEASON, new TableInfo.Column(DealWatchNextService.SEASON, "INTEGER", true, 0, null, 1));
                map6.put(DealWatchNextService.EPISODE, new TableInfo.Column(DealWatchNextService.EPISODE, "INTEGER", true, 0, null, 1));
                map6.put("size", new TableInfo.Column("size", "INTEGER", true, 0, null, 1));
                map6.put("downloadSize", new TableInfo.Column("downloadSize", "INTEGER", true, 0, null, 1));
                map6.put("boxType", new TableInfo.Column("boxType", "INTEGER", true, 0, null, 1));
                map6.put("videoName", new TableInfo.Column("videoName", "TEXT", false, 0, null, 1));
                map6.put("poster", new TableInfo.Column("poster", "TEXT", false, 0, null, 1));
                map6.put("quality", new TableInfo.Column("quality", "TEXT", false, 0, null, 1));
                map6.put("thumb", new TableInfo.Column("thumb", "TEXT", false, 0, null, 1));
                map6.put("episodeName", new TableInfo.Column("episodeName", "TEXT", false, 0, null, 1));
                map6.put("downloadTime", new TableInfo.Column("downloadTime", "INTEGER", true, 0, null, 1));
                map6.put("imdbId", new TableInfo.Column("imdbId", "TEXT", false, 0, null, 1));
                map6.put("speed", new TableInfo.Column("speed", "TEXT", false, 0, "''", 1));
                map6.put("errorMsg", new TableInfo.Column("errorMsg", "TEXT", false, 0, "''", 1));
                map6.put("progress", new TableInfo.Column("progress", "INTEGER", true, 0, "0", 1));
                map6.put("hls", new TableInfo.Column("hls", "INTEGER", true, 0, "0", 1));
                map6.put(VideoDownloadSQLiteHelper.Columns.PERCENT, new TableInfo.Column(VideoDownloadSQLiteHelper.Columns.PERCENT, "REAL", true, 0, "0", 1));
                TableInfo tableInfo11 = new TableInfo("downloadfile", map6, new HashSet(0), new HashSet(0));
                TableInfo tableInfo12 = TableInfo.read(_db, "downloadfile");
                if (!tableInfo11.equals(tableInfo12)) {
                    return new RoomOpenHelper.ValidationResult(false, "downloadfile(com.movieboxpro.android.db.entity.DownloadFile).\n Expected:\n" + tableInfo11 + "\n Found:\n" + tableInfo12);
                }
                HashMap map7 = new HashMap(3);
                map7.put(FileDownloadService.PARAMS_KEY_PATH, new TableInfo.Column(FileDownloadService.PARAMS_KEY_PATH, "TEXT", true, 1, null, 1));
                map7.put("downloadPath", new TableInfo.Column("downloadPath", "TEXT", false, 0, null, 1));
                map7.put("productName", new TableInfo.Column("productName", "TEXT", false, 0, null, 1));
                TableInfo tableInfo13 = new TableInfo("connectusbdevice", map7, new HashSet(0), new HashSet(0));
                TableInfo tableInfo14 = TableInfo.read(_db, "connectusbdevice");
                if (!tableInfo13.equals(tableInfo14)) {
                    return new RoomOpenHelper.ValidationResult(false, "connectusbdevice(com.movieboxpro.android.db.entity.ConnectUsbDevice).\n Expected:\n" + tableInfo13 + "\n Found:\n" + tableInfo14);
                }
                HashMap map8 = new HashMap(20);
                map8.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                map8.put("m3u8Url", new TableInfo.Column("m3u8Url", "TEXT", true, 0, null, 1));
                map8.put("m3u8LocalPath", new TableInfo.Column("m3u8LocalPath", "TEXT", true, 0, null, 1));
                map8.put("downloadId", new TableInfo.Column("downloadId", "TEXT", true, 0, null, 1));
                map8.put("videoUrl", new TableInfo.Column("videoUrl", "TEXT", true, 0, null, 1));
                map8.put("audioUrl", new TableInfo.Column("audioUrl", "TEXT", true, 0, null, 1));
                map8.put("audioEacUrl", new TableInfo.Column("audioEacUrl", "TEXT", true, 0, null, 1));
                map8.put("playbackM3u8LocalPath", new TableInfo.Column("playbackM3u8LocalPath", "TEXT", true, 0, null, 1));
                map8.put("videoLocalPath", new TableInfo.Column("videoLocalPath", "TEXT", true, 0, null, 1));
                map8.put("audioLocalPath", new TableInfo.Column("audioLocalPath", "TEXT", true, 0, null, 1));
                map8.put("audioEacLocalPath", new TableInfo.Column("audioEacLocalPath", "TEXT", true, 0, null, 1));
                map8.put("videoSize", new TableInfo.Column("videoSize", "INTEGER", true, 0, null, 1));
                map8.put("audioSize", new TableInfo.Column("audioSize", "INTEGER", true, 0, null, 1));
                map8.put("audioEacSize", new TableInfo.Column("audioEacSize", "INTEGER", true, 0, null, 1));
                map8.put("videoSpeed", new TableInfo.Column("videoSpeed", "REAL", true, 0, null, 1));
                map8.put("audioSpeed", new TableInfo.Column("audioSpeed", "REAL", true, 0, null, 1));
                map8.put("audioEacSpeed", new TableInfo.Column("audioEacSpeed", "REAL", true, 0, null, 1));
                map8.put("videoPercent", new TableInfo.Column("videoPercent", "REAL", true, 0, null, 1));
                map8.put("audioPercent", new TableInfo.Column("audioPercent", "REAL", true, 0, null, 1));
                map8.put("audioEacPercent", new TableInfo.Column("audioEacPercent", "REAL", true, 0, null, 1));
                TableInfo tableInfo15 = new TableInfo("hls_download", map8, new HashSet(0), new HashSet(0));
                TableInfo tableInfo16 = TableInfo.read(_db, "hls_download");
                if (!tableInfo15.equals(tableInfo16)) {
                    return new RoomOpenHelper.ValidationResult(false, "hls_download(com.movieboxpro.android.db.entity.HlsDownload).\n Expected:\n" + tableInfo15 + "\n Found:\n" + tableInfo16);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "c692e550faf7c5f48d0feb12f2951fb7", "21805cbdf2520a5414e1e8044ec0753b")).build());
    }

    @Override // androidx.room.RoomDatabase
    protected InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "Char_list", "downloads", "playrecode", "testnetrecode", "subtitle_delay_record", "downloadfile", "connectusbdevice", "hls_download");
    }

    @Override // com.movieboxpro.android.db.ReaderDB, androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `Char_list`");
            writableDatabase.execSQL("DELETE FROM `downloads`");
            writableDatabase.execSQL("DELETE FROM `playrecode`");
            writableDatabase.execSQL("DELETE FROM `testnetrecode`");
            writableDatabase.execSQL("DELETE FROM `subtitle_delay_record`");
            writableDatabase.execSQL("DELETE FROM `downloadfile`");
            writableDatabase.execSQL("DELETE FROM `connectusbdevice`");
            writableDatabase.execSQL("DELETE FROM `hls_download`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap map = new HashMap();
        map.put(ChartDao.class, ChartDao_Impl.getRequiredConverters());
        map.put(DownloadDao.class, DownloadDao_Impl.getRequiredConverters());
        map.put(PlayRecodeDao.class, PlayRecodeDao_Impl.getRequiredConverters());
        map.put(TestNetRecodeDao.class, TestNetRecodeDao_Impl.getRequiredConverters());
        map.put(SubtitleDelayRecordDao.class, SubtitleDelayRecordDao_Impl.getRequiredConverters());
        map.put(DownloadFileDao.class, DownloadFileDao_Impl.getRequiredConverters());
        map.put(ConnectUsbDeviceDao.class, ConnectUsbDeviceDao_Impl.getRequiredConverters());
        map.put(HlsDownloadDao.class, HlsDownloadDao_Impl.getRequiredConverters());
        return map;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
        return Arrays.asList(new ReaderDB_AutoMigration_10_11_Impl(), new ReaderDB_AutoMigration_11_12_Impl());
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public ChartDao chartDao() {
        ChartDao chartDao;
        if (this._chartDao != null) {
            return this._chartDao;
        }
        synchronized (this) {
            if (this._chartDao == null) {
                this._chartDao = new ChartDao_Impl(this);
            }
            chartDao = this._chartDao;
        }
        return chartDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public DownloadDao downloadDao() {
        DownloadDao downloadDao;
        if (this._downloadDao != null) {
            return this._downloadDao;
        }
        synchronized (this) {
            if (this._downloadDao == null) {
                this._downloadDao = new DownloadDao_Impl(this);
            }
            downloadDao = this._downloadDao;
        }
        return downloadDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public PlayRecodeDao playRecodeDao() {
        PlayRecodeDao playRecodeDao;
        if (this._playRecodeDao != null) {
            return this._playRecodeDao;
        }
        synchronized (this) {
            if (this._playRecodeDao == null) {
                this._playRecodeDao = new PlayRecodeDao_Impl(this);
            }
            playRecodeDao = this._playRecodeDao;
        }
        return playRecodeDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public TestNetRecodeDao testnetRecodeDao() {
        TestNetRecodeDao testNetRecodeDao;
        if (this._testNetRecodeDao != null) {
            return this._testNetRecodeDao;
        }
        synchronized (this) {
            if (this._testNetRecodeDao == null) {
                this._testNetRecodeDao = new TestNetRecodeDao_Impl(this);
            }
            testNetRecodeDao = this._testNetRecodeDao;
        }
        return testNetRecodeDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public SubtitleDelayRecordDao subtitleDelayRecordDao() {
        SubtitleDelayRecordDao subtitleDelayRecordDao;
        if (this._subtitleDelayRecordDao != null) {
            return this._subtitleDelayRecordDao;
        }
        synchronized (this) {
            if (this._subtitleDelayRecordDao == null) {
                this._subtitleDelayRecordDao = new SubtitleDelayRecordDao_Impl(this);
            }
            subtitleDelayRecordDao = this._subtitleDelayRecordDao;
        }
        return subtitleDelayRecordDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public DownloadFileDao downloadFileDao() {
        DownloadFileDao downloadFileDao;
        if (this._downloadFileDao != null) {
            return this._downloadFileDao;
        }
        synchronized (this) {
            if (this._downloadFileDao == null) {
                this._downloadFileDao = new DownloadFileDao_Impl(this);
            }
            downloadFileDao = this._downloadFileDao;
        }
        return downloadFileDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public ConnectUsbDeviceDao connectUsbDeviceDao() {
        ConnectUsbDeviceDao connectUsbDeviceDao;
        if (this._connectUsbDeviceDao != null) {
            return this._connectUsbDeviceDao;
        }
        synchronized (this) {
            if (this._connectUsbDeviceDao == null) {
                this._connectUsbDeviceDao = new ConnectUsbDeviceDao_Impl(this);
            }
            connectUsbDeviceDao = this._connectUsbDeviceDao;
        }
        return connectUsbDeviceDao;
    }

    @Override // com.movieboxpro.android.db.ReaderDB
    public HlsDownloadDao hlsDownloadDao() {
        HlsDownloadDao hlsDownloadDao;
        if (this._hlsDownloadDao != null) {
            return this._hlsDownloadDao;
        }
        synchronized (this) {
            if (this._hlsDownloadDao == null) {
                this._hlsDownloadDao = new HlsDownloadDao_Impl(this);
            }
            hlsDownloadDao = this._hlsDownloadDao;
        }
        return hlsDownloadDao;
    }
}
