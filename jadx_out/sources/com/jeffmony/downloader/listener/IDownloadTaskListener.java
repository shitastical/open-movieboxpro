package com.jeffmony.downloader.listener;

/* JADX INFO: loaded from: classes5.dex */
public interface IDownloadTaskListener {
    void onTaskFailed(Throwable th);

    void onTaskFinished(long j);

    void onTaskPaused();

    void onTaskProgress(float f, long j, long j2, float f2);

    void onTaskProgressForM3U8(float f, long j, int i, int i2, float f2);

    void onTaskStart(String str);
}
