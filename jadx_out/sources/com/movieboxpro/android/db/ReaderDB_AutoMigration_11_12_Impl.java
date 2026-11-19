package com.movieboxpro.android.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes5.dex */
class ReaderDB_AutoMigration_11_12_Impl extends Migration {
    public ReaderDB_AutoMigration_11_12_Impl() {
        super(11, 12);
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE `downloadfile` ADD COLUMN `percent` REAL NOT NULL DEFAULT 0");
        database.execSQL("CREATE TABLE IF NOT EXISTS `hls_download` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `m3u8Url` TEXT NOT NULL, `m3u8LocalPath` TEXT NOT NULL, `downloadId` TEXT NOT NULL, `videoUrl` TEXT NOT NULL, `audioUrl` TEXT NOT NULL, `audioEacUrl` TEXT NOT NULL, `playbackM3u8LocalPath` TEXT NOT NULL, `videoLocalPath` TEXT NOT NULL, `audioLocalPath` TEXT NOT NULL, `audioEacLocalPath` TEXT NOT NULL, `videoSize` INTEGER NOT NULL, `audioSize` INTEGER NOT NULL, `audioEacSize` INTEGER NOT NULL, `videoSpeed` REAL NOT NULL, `audioSpeed` REAL NOT NULL, `audioEacSpeed` REAL NOT NULL, `videoPercent` REAL NOT NULL, `audioPercent` REAL NOT NULL, `audioEacPercent` REAL NOT NULL)");
    }
}
