package com.movieboxpro.android.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes5.dex */
class ReaderDB_AutoMigration_10_11_Impl extends Migration {
    public ReaderDB_AutoMigration_10_11_Impl() {
        super(10, 11);
    }

    @Override // androidx.room.migration.Migration
    public void migrate(SupportSQLiteDatabase database) {
        database.execSQL("ALTER TABLE `downloadfile` ADD COLUMN `speed` TEXT DEFAULT ''");
        database.execSQL("ALTER TABLE `downloadfile` ADD COLUMN `hls` INTEGER NOT NULL DEFAULT 0");
        database.execSQL("ALTER TABLE `downloadfile` ADD COLUMN `errorMsg` TEXT DEFAULT ''");
        database.execSQL("ALTER TABLE `downloadfile` ADD COLUMN `progress` INTEGER NOT NULL DEFAULT 0");
    }
}
