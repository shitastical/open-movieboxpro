package com.jeffmony.downloader.task;

import com.jeffmony.downloader.listener.IDownloadTaskListener;
import com.jeffmony.downloader.model.VideoTaskItem;
import com.jeffmony.downloader.utils.VideoDownloadUtils;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX INFO: loaded from: classes5.dex */
public abstract class VideoDownloadTask {
    protected static final int BUFFER_SIZE = 8192;
    protected static final int THREAD_COUNT = 6;
    protected ThreadPoolExecutor mDownloadExecutor;
    protected IDownloadTaskListener mDownloadTaskListener;
    protected final String mFinalUrl;
    protected Map<String, String> mHeaders;
    protected File mSaveDir;
    protected String mSaveName;
    protected final VideoTaskItem mTaskItem;
    protected volatile boolean mDownloadFinished = false;
    protected final Object mDownloadLock = new Object();
    protected long mLastCachedSize = 0;
    protected long mCurrentCachedSize = 0;
    protected long mLastInvokeTime = 0;
    protected float mSpeed = 0.0f;
    protected float mPercent = 0.0f;

    public abstract void pauseDownload();

    public abstract void resumeDownload();

    public abstract void startDownload();

    protected VideoDownloadTask(VideoTaskItem videoTaskItem, Map<String, String> map) {
        this.mTaskItem = videoTaskItem;
        this.mHeaders = map;
        this.mFinalUrl = videoTaskItem.getFinalUrl();
        this.mSaveName = VideoDownloadUtils.computeMD5(videoTaskItem.getUrl());
        File file = new File(VideoDownloadUtils.getDownloadConfig().getCacheRoot(), this.mSaveName);
        this.mSaveDir = file;
        if (!file.exists()) {
            this.mSaveDir.mkdir();
        }
        videoTaskItem.setSaveDir(this.mSaveDir.getAbsolutePath());
    }

    public void setDownloadTaskListener(IDownloadTaskListener iDownloadTaskListener) {
        this.mDownloadTaskListener = iDownloadTaskListener;
    }

    protected void notifyOnTaskPaused() {
        IDownloadTaskListener iDownloadTaskListener = this.mDownloadTaskListener;
        if (iDownloadTaskListener != null) {
            iDownloadTaskListener.onTaskPaused();
        }
    }

    protected void notifyOnTaskFailed(Exception exc) {
        ThreadPoolExecutor threadPoolExecutor = this.mDownloadExecutor;
        if (threadPoolExecutor == null || !threadPoolExecutor.isShutdown()) {
            this.mDownloadExecutor.shutdownNow();
            this.mDownloadTaskListener.onTaskFailed(exc);
        }
    }

    protected void setThreadPoolArgument(int i, int i2) {
        ThreadPoolExecutor threadPoolExecutor = this.mDownloadExecutor;
        if (threadPoolExecutor == null || threadPoolExecutor.isShutdown()) {
            return;
        }
        this.mDownloadExecutor.setCorePoolSize(i);
        this.mDownloadExecutor.setMaximumPoolSize(i2);
    }
}
