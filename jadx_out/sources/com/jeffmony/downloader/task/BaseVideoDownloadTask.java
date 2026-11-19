package com.jeffmony.downloader.task;

import com.jeffmony.downloader.listener.IDownloadTaskListener;
import com.jeffmony.downloader.model.VideoTaskItem;
import com.jeffmony.downloader.utils.HttpUtils;
import com.jeffmony.downloader.utils.LogUtils;
import com.jeffmony.downloader.utils.VideoDownloadUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class BaseVideoDownloadTask extends VideoDownloadTask {
    private static final String TAG = "BaseVideoDownloadTask";
    private long mTotalLength;

    public BaseVideoDownloadTask(VideoTaskItem videoTaskItem, Map<String, String> map) {
        super(videoTaskItem, map);
        if (this.mHeaders == null) {
            this.mHeaders = new HashMap();
        }
        this.mCurrentCachedSize = videoTaskItem.getDownloadSize();
        this.mTotalLength = videoTaskItem.getTotalSize();
    }

    @Override // com.jeffmony.downloader.task.VideoDownloadTask
    public void startDownload() {
        this.mDownloadTaskListener.onTaskStart(this.mTaskItem.getUrl());
        startDownload(this.mCurrentCachedSize);
    }

    private void startDownload(long j) {
        if (this.mTaskItem.isCompleted()) {
            LogUtils.i(TAG, "BaseVideoDownloadTask local file.");
            notifyDownloadFinish();
        } else {
            this.mCurrentCachedSize = j;
            this.mDownloadExecutor = new ThreadPoolExecutor(6, 6, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
            this.mDownloadExecutor.execute(new Runnable() { // from class: com.jeffmony.downloader.task.BaseVideoDownloadTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    this.f$0.m708xbfd5af59();
                }
            });
        }
    }

    /* JADX INFO: renamed from: lambda$startDownload$0$com-jeffmony-downloader-task-BaseVideoDownloadTask, reason: not valid java name */
    /* synthetic */ void m708xbfd5af59() throws Throwable {
        RandomAccessFile randomAccessFile;
        try {
            File file = new File(this.mSaveDir, this.mSaveName + VideoDownloadUtils.VIDEO_SUFFIX);
            if (!file.exists()) {
                file.createNewFile();
                this.mCurrentCachedSize = 0L;
            } else {
                this.mCurrentCachedSize = file.length();
            }
            InputStream inputStream = null;
            try {
                InputStream responseBody = getResponseBody(this.mFinalUrl, this.mCurrentCachedSize, this.mTotalLength);
                try {
                    byte[] bArr = new byte[8192];
                    randomAccessFile = new RandomAccessFile(file.getAbsolutePath(), "rw");
                    try {
                        randomAccessFile.seek(this.mCurrentCachedSize);
                        while (true) {
                            int i = responseBody.read(bArr);
                            if (i == -1) {
                                break;
                            }
                            long j = i;
                            long j2 = this.mCurrentCachedSize + j;
                            long j3 = this.mTotalLength;
                            if (j2 > j3) {
                                randomAccessFile.write(bArr, 0, (int) (j3 - this.mCurrentCachedSize));
                                this.mCurrentCachedSize = this.mTotalLength;
                            } else {
                                randomAccessFile.write(bArr, 0, i);
                                this.mCurrentCachedSize += j;
                            }
                            notifyDownloadProgress();
                        }
                        VideoDownloadUtils.close(responseBody);
                    } catch (Exception e) {
                        e = e;
                        inputStream = responseBody;
                        try {
                            LogUtils.w(TAG, "FAILED, exception=" + e.getMessage());
                            e.printStackTrace();
                            notifyDownloadError(e);
                            VideoDownloadUtils.close(inputStream);
                        } catch (Throwable th) {
                            th = th;
                            VideoDownloadUtils.close(inputStream);
                            VideoDownloadUtils.close(randomAccessFile);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = responseBody;
                        VideoDownloadUtils.close(inputStream);
                        VideoDownloadUtils.close(randomAccessFile);
                        throw th;
                    }
                } catch (Exception e2) {
                    e = e2;
                    randomAccessFile = null;
                } catch (Throwable th3) {
                    th = th3;
                    randomAccessFile = null;
                }
            } catch (Exception e3) {
                e = e3;
                randomAccessFile = null;
            } catch (Throwable th4) {
                th = th4;
                randomAccessFile = null;
            }
            VideoDownloadUtils.close(randomAccessFile);
        } catch (Exception e4) {
            LogUtils.w(TAG, "BaseDownloadTask createNewFile failed, exception=" + e4.getMessage());
        }
    }

    @Override // com.jeffmony.downloader.task.VideoDownloadTask
    public void pauseDownload() {
        if (this.mDownloadExecutor == null || this.mDownloadExecutor.isShutdown()) {
            return;
        }
        this.mDownloadExecutor.shutdownNow();
        notifyOnTaskPaused();
    }

    @Override // com.jeffmony.downloader.task.VideoDownloadTask
    public void resumeDownload() {
        startDownload(this.mCurrentCachedSize);
    }

    private void notifyDownloadProgress() {
        if (this.mCurrentCachedSize >= this.mTotalLength) {
            IDownloadTaskListener iDownloadTaskListener = this.mDownloadTaskListener;
            long j = this.mTotalLength;
            iDownloadTaskListener.onTaskProgress(100.0f, j, j, this.mSpeed);
            this.mPercent = 100.0f;
            notifyDownloadFinish();
            return;
        }
        float f = ((this.mCurrentCachedSize * 1.0f) * 100.0f) / this.mTotalLength;
        if (VideoDownloadUtils.isFloatEqual(f, this.mPercent)) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (this.mCurrentCachedSize > this.mLastCachedSize && jCurrentTimeMillis > this.mLastInvokeTime) {
            this.mSpeed = (((this.mCurrentCachedSize - this.mLastCachedSize) * 1000) * 1.0f) / (jCurrentTimeMillis - this.mLastInvokeTime);
        }
        this.mDownloadTaskListener.onTaskProgress(f, this.mCurrentCachedSize, this.mTotalLength, this.mSpeed);
        this.mPercent = f;
        this.mLastInvokeTime = jCurrentTimeMillis;
        this.mLastCachedSize = this.mCurrentCachedSize;
    }

    private void notifyDownloadError(Exception exc) {
        notifyOnTaskFailed(exc);
    }

    private void notifyDownloadFinish() {
        synchronized (this.mDownloadLock) {
            if (!this.mDownloadFinished) {
                this.mDownloadTaskListener.onTaskFinished(this.mTotalLength);
                this.mDownloadFinished = true;
            }
        }
    }

    private InputStream getResponseBody(String str, long j, long j2) throws IOException {
        if (j2 == this.mTotalLength) {
            this.mHeaders.put("Range", "bytes=" + j + "-");
        } else {
            this.mHeaders.put("Range", "bytes=" + j + "-" + j2);
        }
        return HttpUtils.getConnection(str, this.mHeaders, VideoDownloadUtils.getDownloadConfig().shouldIgnoreCertErrors()).getInputStream();
    }
}
