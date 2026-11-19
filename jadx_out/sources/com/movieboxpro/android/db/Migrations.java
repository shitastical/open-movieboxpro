package com.movieboxpro.android.db;

import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

/* JADX INFO: loaded from: classes5.dex */
public class Migrations {
    public static final Migration migration1_2 = new Migration(1, 2) { // from class: com.movieboxpro.android.db.Migrations.1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("alter table 'downloads' add column 'season' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("alter table 'downloads' add column 'episode' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("alter table 'downloads' add column 'box_type' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("alter table 'downloads' add column 'tmfid' INTEGER NOT NULL DEFAULT 0");
            database.execSQL("alter table 'downloads' add column 'seasonid' TEXT");
            database.execSQL("alter table 'downloads' add column 'seasontitle' TEXT");
            database.execSQL("alter table 'downloads' add column 'seasonthumbs' TEXT");
            database.execSQL("alter table 'downloads' add column 'privateid' TEXT ");
        }
    };
    public static final Migration migration4_5;
    public static final Migration migration5_6;
    public static final Migration migration6_8;
    public static final Migration migration8_9;
    public static final Migration migration9_10;

    static {
        int i = 5;
        migration4_5 = new Migration(4, i) { // from class: com.movieboxpro.android.db.Migrations.2
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE 'subtitle_delay_record' ('delaySecond' INTEGER NOT NULL DEFAULT 0, 'sid' TEXT NOT NULL DEFAULT '0', PRIMARY KEY('sid'))");
            }
        };
        int i2 = 6;
        migration5_6 = new Migration(i, i2) { // from class: com.movieboxpro.android.db.Migrations.3
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("alter table 'downloads' add column 'fid' TEXT ");
            }
        };
        int i3 = 8;
        migration6_8 = new Migration(i2, i3) { // from class: com.movieboxpro.android.db.Migrations.4
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE IF NOT EXISTS downloadfile (downloadId TEXT PRIMARY KEY NOT NULL,id TEXT ,url TEXT,path TEXT,fileName TEXT,status INTEGER NOT NULL DEFAULT 0,mid TEXT,tid TEXT,season INTEGER NOT NULL DEFAULT 0,episode INTEGER NOT NULL DEFAULT 0,size INTEGER NOT NULL DEFAULT 0, downloadSize INTEGER NOT NULL DEFAULT 0,boxType INTEGER NOT NULL DEFAULT 0,videoName TEXT,poster TEXT,quality TEXT,thumb TEXT,episodeName TEXT,downloadTime INTEGER NOT NULL DEFAULT 0)");
            }
        };
        int i4 = 9;
        migration8_9 = new Migration(i3, i4) { // from class: com.movieboxpro.android.db.Migrations.5
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("CREATE TABLE IF NOT EXISTS 'connectusbdevice' ('path' TEXT PRIMARY KEY NOT NULL DEFAULT 0,'downloadPath' TEXT,'productName' TEXT)");
            }
        };
        migration9_10 = new Migration(i4, 10) { // from class: com.movieboxpro.android.db.Migrations.6
            @Override // androidx.room.migration.Migration
            public void migrate(SupportSQLiteDatabase database) {
                database.execSQL("alter table 'downloadfile' add column 'imdbId' TEXT");
            }
        };
    }
}
