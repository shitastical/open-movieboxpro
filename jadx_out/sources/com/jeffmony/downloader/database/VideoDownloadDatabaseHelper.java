package com.jeffmony.downloader.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.jeffmony.downloader.model.VideoTaskItem;
import com.jeffmony.downloader.utils.LogUtils;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class VideoDownloadDatabaseHelper {
    private static final String TAG = "VideoDownloadDatabaseHelper";
    private VideoDownloadSQLiteHelper mSQLiteHelper;

    public VideoDownloadDatabaseHelper(Context context) {
        if (context == null) {
            return;
        }
        this.mSQLiteHelper = new VideoDownloadSQLiteHelper(context);
    }

    public void markDownloadInfoAddEvent(VideoTaskItem videoTaskItem) {
        SQLiteDatabase writableDatabase = this.mSQLiteHelper.getWritableDatabase();
        if (writableDatabase == null) {
            return;
        }
        synchronized (this) {
            if (!videoTaskItem.isInDatabase() && !isTaskInfoExistInTable(writableDatabase, videoTaskItem)) {
                insertVideoDownloadInfo(writableDatabase, videoTaskItem);
            }
        }
    }

    public void markDownloadProgressInfoUpdateEvent(VideoTaskItem videoTaskItem) {
        SQLiteDatabase writableDatabase = this.mSQLiteHelper.getWritableDatabase();
        if (writableDatabase == null) {
            return;
        }
        if (videoTaskItem.isInDatabase() || isTaskInfoExistInTable(writableDatabase, videoTaskItem)) {
            updateDownloadProgressInfo(writableDatabase, videoTaskItem);
        } else {
            insertVideoDownloadInfo(writableDatabase, videoTaskItem);
        }
    }

    public void markDownloadInfoPauseEvent(VideoTaskItem videoTaskItem) {
        SQLiteDatabase writableDatabase = this.mSQLiteHelper.getWritableDatabase();
        if (writableDatabase == null) {
            return;
        }
        if (videoTaskItem.isInDatabase() || isTaskInfoExistInTable(writableDatabase, videoTaskItem)) {
            updateDownloadProgressInfo(writableDatabase, videoTaskItem);
        } else {
            insertVideoDownloadInfo(writableDatabase, videoTaskItem);
        }
    }

    public void markDownloadInfoErrorEvent(VideoTaskItem videoTaskItem) {
        SQLiteDatabase writableDatabase = this.mSQLiteHelper.getWritableDatabase();
        if (writableDatabase == null) {
            return;
        }
        if (videoTaskItem.isInDatabase() || isTaskInfoExistInTable(writableDatabase, videoTaskItem)) {
            updateDownloadProgressInfo(writableDatabase, videoTaskItem);
        } else {
            insertVideoDownloadInfo(writableDatabase, videoTaskItem);
        }
    }

    public void updateDownloadProgressInfo(SQLiteDatabase sQLiteDatabase, VideoTaskItem videoTaskItem) {
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(VideoDownloadSQLiteHelper.Columns.MIME_TYPE, videoTaskItem.getMimeType());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.TASK_STATE, Integer.valueOf(videoTaskItem.getTaskState()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.VIDEO_TYPE, Integer.valueOf(videoTaskItem.getVideoType()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.PERCENT, Float.valueOf(videoTaskItem.getPercent()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.CACHED_LENGTH, Long.valueOf(videoTaskItem.getDownloadSize()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.TOTAL_LENGTH, Long.valueOf(videoTaskItem.getTotalSize()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.CACHED_TS, Integer.valueOf(videoTaskItem.getCurTs()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.TOTAL_TS, Integer.valueOf(videoTaskItem.getTotalTs()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.COMPLETED, Boolean.valueOf(videoTaskItem.isCompleted()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.FILE_NAME, videoTaskItem.getFileName());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.FILE_PATH, videoTaskItem.getFilePath());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.COVER_URL, videoTaskItem.getCoverUrl());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.COVER_PATH, videoTaskItem.getCoverPath());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.VIDEO_TITLE, videoTaskItem.getTitle());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.GROUP_NAME, videoTaskItem.getGroupName());
                sQLiteDatabase.update(VideoDownloadSQLiteHelper.TABLE_VIDEO_DOWNLOAD_INFO, contentValues, "video_url = ?", new String[]{videoTaskItem.getUrl()});
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                LogUtils.w(TAG, "updateVideoDownloadInfo failed, exception = " + e.getMessage());
            }
        } finally {
            sQLiteDatabase.endTransaction();
        }
    }

    private void insertVideoDownloadInfo(SQLiteDatabase sQLiteDatabase, VideoTaskItem videoTaskItem) {
        if (sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.beginTransaction();
        try {
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put(VideoDownloadSQLiteHelper.Columns.VIDEO_URL, videoTaskItem.getUrl());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.MIME_TYPE, videoTaskItem.getMimeType());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.DOWNLOAD_TIME, Long.valueOf(videoTaskItem.getDownloadCreateTime()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.PERCENT, Float.valueOf(videoTaskItem.getPercent()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.TASK_STATE, Integer.valueOf(videoTaskItem.getTaskState()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.VIDEO_TYPE, Integer.valueOf(videoTaskItem.getVideoType()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.CACHED_LENGTH, Long.valueOf(videoTaskItem.getDownloadSize()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.TOTAL_LENGTH, Long.valueOf(videoTaskItem.getTotalSize()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.CACHED_TS, Integer.valueOf(videoTaskItem.getCurTs()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.TOTAL_TS, Integer.valueOf(videoTaskItem.getTotalTs()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.COMPLETED, Boolean.valueOf(videoTaskItem.isCompleted()));
                contentValues.put(VideoDownloadSQLiteHelper.Columns.COVER_URL, videoTaskItem.getCoverUrl());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.COVER_PATH, videoTaskItem.getCoverPath());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.VIDEO_TITLE, videoTaskItem.getTitle());
                contentValues.put(VideoDownloadSQLiteHelper.Columns.GROUP_NAME, videoTaskItem.getGroupName());
                sQLiteDatabase.insert(VideoDownloadSQLiteHelper.TABLE_VIDEO_DOWNLOAD_INFO, null, contentValues);
                sQLiteDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                LogUtils.w(TAG, "insertVideoDownloadInfo failed, exception = " + e.getMessage());
            }
        } finally {
            sQLiteDatabase.endTransaction();
            videoTaskItem.setIsInDatabase(true);
        }
    }

    private boolean isTaskInfoExistInTable(SQLiteDatabase sQLiteDatabase, VideoTaskItem videoTaskItem) {
        if (sQLiteDatabase == null) {
            return false;
        }
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = sQLiteDatabase.query(VideoDownloadSQLiteHelper.TABLE_VIDEO_DOWNLOAD_INFO, null, "video_url = ?", new String[]{videoTaskItem.getUrl() + ""}, null, null, null, null);
                if (cursorQuery == null || !cursorQuery.moveToFirst() || cursorQuery.getLong(0) <= 0) {
                    return false;
                }
                if (cursorQuery == null) {
                    return true;
                }
                cursorQuery.close();
                return true;
            } catch (Exception e) {
                LogUtils.w(TAG, "isTaskInfoExistInTable query failed, exception = " + e.getMessage());
                return false;
            }
        } finally {
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:37:0x015d  */
    public List<VideoTaskItem> getDownloadInfos() throws Throwable {
        Cursor cursorQuery;
        Throwable th;
        SQLiteDatabase readableDatabase = this.mSQLiteHelper.getReadableDatabase();
        if (readableDatabase == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            cursorQuery = readableDatabase.query(VideoDownloadSQLiteHelper.TABLE_VIDEO_DOWNLOAD_INFO, null, null, null, null, null, null, null);
            if (cursorQuery != null) {
                try {
                    try {
                        if (cursorQuery.moveToFirst()) {
                            do {
                                VideoTaskItem videoTaskItem = new VideoTaskItem(cursorQuery.getString(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.VIDEO_URL)));
                                videoTaskItem.setMimeType(cursorQuery.getString(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.MIME_TYPE)));
                                videoTaskItem.setDownloadCreateTime(cursorQuery.getLong(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.DOWNLOAD_TIME)));
                                videoTaskItem.setPercent(cursorQuery.getFloat(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.PERCENT)));
                                videoTaskItem.setTaskState(cursorQuery.getInt(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.TASK_STATE)));
                                videoTaskItem.setVideoType(cursorQuery.getInt(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.VIDEO_TYPE)));
                                videoTaskItem.setDownloadSize(cursorQuery.getLong(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.CACHED_LENGTH)));
                                videoTaskItem.setTotalSize(cursorQuery.getLong(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.TOTAL_LENGTH)));
                                videoTaskItem.setCurTs(cursorQuery.getInt(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.CACHED_TS)));
                                videoTaskItem.setTotalTs(cursorQuery.getInt(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.TOTAL_TS)));
                                boolean z = true;
                                if (cursorQuery.getInt(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.COMPLETED)) != 1) {
                                    z = false;
                                }
                                videoTaskItem.setIsCompleted(z);
                                videoTaskItem.setFileName(cursorQuery.getString(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.FILE_NAME)));
                                videoTaskItem.setFilePath(cursorQuery.getString(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.FILE_PATH)));
                                videoTaskItem.setCoverUrl(cursorQuery.getString(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.COVER_URL)));
                                videoTaskItem.setCoverPath(cursorQuery.getString(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.COVER_PATH)));
                                videoTaskItem.setTitle(cursorQuery.getString(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.VIDEO_TITLE)));
                                videoTaskItem.setGroupName(cursorQuery.getString(cursorQuery.getColumnIndex(VideoDownloadSQLiteHelper.Columns.GROUP_NAME)));
                                if (videoTaskItem.isRunningTask() && Math.abs(videoTaskItem.getSpeed()) < 1.0E-4f) {
                                    videoTaskItem.setTaskState(7);
                                }
                                arrayList.add(videoTaskItem);
                            } while (cursorQuery.moveToNext());
                        }
                    } catch (Exception e) {
                        e = e;
                        LogUtils.w(TAG, "getDownloadInfos failed, exception = " + e.getMessage());
                        if (cursorQuery != null) {
                            cursorQuery.close();
                        }
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursorQuery != null) {
                        cursorQuery.close();
                    }
                    throw th;
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            return arrayList;
        } catch (Exception e2) {
            e = e2;
            cursorQuery = null;
        } catch (Throwable th3) {
            cursorQuery = null;
            th = th3;
            if (cursorQuery != null) {
                cursorQuery.close();
            }
            throw th;
        }
    }

    public void deleteAllDownloadInfos() {
        SQLiteDatabase writableDatabase = this.mSQLiteHelper.getWritableDatabase();
        if (writableDatabase == null) {
            return;
        }
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.delete(VideoDownloadSQLiteHelper.TABLE_VIDEO_DOWNLOAD_INFO, null, null);
                writableDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                LogUtils.w(TAG, "deleteAllDownloadInfos failed, exception = " + e.getMessage());
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }

    public void deleteDownloadItemByUrl(VideoTaskItem videoTaskItem) {
        SQLiteDatabase writableDatabase = this.mSQLiteHelper.getWritableDatabase();
        if (writableDatabase == null) {
            return;
        }
        writableDatabase.beginTransaction();
        try {
            try {
                writableDatabase.delete(VideoDownloadSQLiteHelper.TABLE_VIDEO_DOWNLOAD_INFO, "video_url = ? ", new String[]{videoTaskItem.getUrl()});
                writableDatabase.setTransactionSuccessful();
            } catch (Exception e) {
                LogUtils.w(TAG, "deleteDownloadItemByUrl failed, exception = " + e.getMessage());
            }
        } finally {
            writableDatabase.endTransaction();
        }
    }
}
