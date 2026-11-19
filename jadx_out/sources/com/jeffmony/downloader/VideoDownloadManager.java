package com.jeffmony.downloader;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.jeffmony.downloader.database.VideoDownloadDatabaseHelper;
import com.jeffmony.downloader.listener.DownloadListener;
import com.jeffmony.downloader.listener.IDownloadInfosCallback;
import com.jeffmony.downloader.listener.IDownloadTaskListener;
import com.jeffmony.downloader.listener.IM3U8MergeResultListener;
import com.jeffmony.downloader.listener.IVideoInfoListener;
import com.jeffmony.downloader.listener.IVideoInfoParseListener;
import com.jeffmony.downloader.m3u8.M3U8;
import com.jeffmony.downloader.model.Video;
import com.jeffmony.downloader.model.VideoTaskItem;
import com.jeffmony.downloader.task.BaseVideoDownloadTask;
import com.jeffmony.downloader.task.M3U8VideoDownloadTask;
import com.jeffmony.downloader.task.VideoDownloadTask;
import com.jeffmony.downloader.utils.ContextUtils;
import com.jeffmony.downloader.utils.DownloadExceptionUtils;
import com.jeffmony.downloader.utils.LogUtils;
import com.jeffmony.downloader.utils.VideoDownloadUtils;
import com.jeffmony.downloader.utils.VideoStorageUtils;
import com.jeffmony.downloader.utils.WorkerThreadHandler;
import com.jeffmony.m3u8library.VideoProcessManager;
import com.jeffmony.m3u8library.listener.IVideoTransformListener;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import org.joda.time.DateTimeConstants;

/* JADX INFO: loaded from: classes5.dex */
public class VideoDownloadManager {
    private static final String TAG = "VideoDownloadManager";
    private static volatile VideoDownloadManager sInstance;
    private VideoDownloadConfig mConfig;
    private VideoDownloadHandler mVideoDownloadHandler;
    private DownloadListener mGlobalDownloadListener = null;
    private VideoDownloadDatabaseHelper mVideoDatabaseHelper = null;
    private Object mQueueLock = new Object();
    private List<IDownloadInfosCallback> mDownloadInfoCallbacks = new CopyOnWriteArrayList();
    private Map<String, VideoDownloadTask> mVideoDownloadTaskMap = new ConcurrentHashMap();
    private Map<String, VideoTaskItem> mVideoItemTaskMap = new ConcurrentHashMap();
    private VideoDownloadQueue mVideoDownloadQueue = new VideoDownloadQueue();

    public static class Build {
        private String mCacheRoot;
        private int mReadTimeOut = DateTimeConstants.MILLIS_PER_MINUTE;
        private int mConnTimeOut = DateTimeConstants.MILLIS_PER_MINUTE;
        private boolean mIgnoreCertErrors = false;
        private int mConcurrentCount = 3;
        private boolean mShouldM3U8Merged = false;

        public Build(Context context) {
            ContextUtils.initApplicationContext(context);
        }

        public Build setCacheRoot(String str) {
            this.mCacheRoot = str;
            return this;
        }

        public Build setTimeOut(int i, int i2) {
            this.mReadTimeOut = i;
            this.mConnTimeOut = i2;
            return this;
        }

        public Build setConcurrentCount(int i) {
            this.mConcurrentCount = i;
            return this;
        }

        public Build setIgnoreCertErrors(boolean z) {
            this.mIgnoreCertErrors = z;
            return this;
        }

        public Build setShouldM3U8Merged(boolean z) {
            this.mShouldM3U8Merged = z;
            return this;
        }

        public VideoDownloadConfig buildConfig() {
            return new VideoDownloadConfig(this.mCacheRoot, this.mReadTimeOut, this.mConnTimeOut, this.mIgnoreCertErrors, this.mConcurrentCount, this.mShouldM3U8Merged);
        }
    }

    public void setConcurrentCount(int i) {
        VideoDownloadConfig videoDownloadConfig = this.mConfig;
        if (videoDownloadConfig != null) {
            videoDownloadConfig.setConcurrentCount(i);
        }
    }

    public void setIgnoreAllCertErrors(boolean z) {
        VideoDownloadConfig videoDownloadConfig = this.mConfig;
        if (videoDownloadConfig != null) {
            videoDownloadConfig.setIgnoreAllCertErrors(z);
        }
    }

    public void setShouldM3U8Merged(boolean z) {
        if (this.mConfig != null) {
            LogUtils.w(TAG, "setShouldM3U8Merged = " + z);
            this.mConfig.setShouldM3U8Merged(z);
        }
    }

    public static VideoDownloadManager getInstance() {
        if (sInstance == null) {
            synchronized (VideoDownloadManager.class) {
                if (sInstance == null) {
                    sInstance = new VideoDownloadManager();
                }
            }
        }
        return sInstance;
    }

    private VideoDownloadManager() {
    }

    public void initConfig(VideoDownloadConfig videoDownloadConfig) {
        this.mConfig = videoDownloadConfig;
        VideoDownloadUtils.setDownloadConfig(videoDownloadConfig);
        this.mVideoDatabaseHelper = new VideoDownloadDatabaseHelper(ContextUtils.getApplicationContext());
        HandlerThread handlerThread = new HandlerThread("Video_download_state_thread");
        handlerThread.start();
        this.mVideoDownloadHandler = new VideoDownloadHandler(handlerThread.getLooper());
    }

    public VideoDownloadConfig downloadConfig() {
        return this.mConfig;
    }

    public void fetchDownloadItems(IDownloadInfosCallback iDownloadInfosCallback) {
        this.mDownloadInfoCallbacks.add(iDownloadInfosCallback);
        this.mVideoDownloadHandler.obtainMessage(100).sendToTarget();
    }

    public void removeDownloadInfosCallback(IDownloadInfosCallback iDownloadInfosCallback) {
        this.mDownloadInfoCallbacks.remove(iDownloadInfosCallback);
    }

    public void setGlobalDownloadListener(DownloadListener downloadListener) {
        this.mGlobalDownloadListener = downloadListener;
    }

    public void startDownload(VideoTaskItem videoTaskItem) {
        if (videoTaskItem == null || TextUtils.isEmpty(videoTaskItem.getUrl())) {
            return;
        }
        synchronized (this.mQueueLock) {
            if (this.mVideoDownloadQueue.contains(videoTaskItem)) {
                videoTaskItem = this.mVideoDownloadQueue.getTaskItem(videoTaskItem.getUrl());
            } else {
                this.mVideoDownloadQueue.offer(videoTaskItem);
            }
        }
        videoTaskItem.setPaused(false);
        videoTaskItem.setDownloadCreateTime(videoTaskItem.getDownloadCreateTime());
        videoTaskItem.setTaskState(-1);
        this.mVideoDownloadHandler.obtainMessage(1, (VideoTaskItem) videoTaskItem.clone()).sendToTarget();
        startDownload(videoTaskItem, null);
    }

    public void startDownload(VideoTaskItem videoTaskItem, Map<String, String> map) {
        if (videoTaskItem == null || TextUtils.isEmpty(videoTaskItem.getUrl())) {
            return;
        }
        parseVideoDownloadInfo(videoTaskItem, map);
    }

    private void parseVideoDownloadInfo(VideoTaskItem videoTaskItem, Map<String, String> map) {
        videoTaskItem.setFileHash(VideoDownloadUtils.computeMD5(videoTaskItem.getUrl()));
        if (videoTaskItem.getDownloadCreateTime() != 0) {
            parseExistVideoDownloadInfo(videoTaskItem, map);
        } else {
            parseNetworkVideoInfo(videoTaskItem, map);
        }
    }

    private void parseExistVideoDownloadInfo(final VideoTaskItem videoTaskItem, final Map<String, String> map) {
        if (videoTaskItem.isHlsType()) {
            VideoInfoParserManager.getInstance().parseLocalM3U8File(videoTaskItem, new IVideoInfoParseListener() { // from class: com.jeffmony.downloader.VideoDownloadManager.1
                @Override // com.jeffmony.downloader.listener.IVideoInfoParseListener
                public void onM3U8FileParseSuccess(VideoTaskItem videoTaskItem2, M3U8 m3u8) {
                    VideoDownloadManager.this.startM3U8VideoDownloadTask(videoTaskItem, m3u8, map);
                }

                @Override // com.jeffmony.downloader.listener.IVideoInfoParseListener
                public void onM3U8FileParseFailed(VideoTaskItem videoTaskItem2, Throwable th) {
                    VideoDownloadManager.this.parseNetworkVideoInfo(videoTaskItem, map);
                }
            });
        } else {
            startBaseVideoDownloadTask(videoTaskItem, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseNetworkVideoInfo(final VideoTaskItem videoTaskItem, final Map<String, String> map) {
        VideoInfoParserManager.getInstance().parseVideoInfo(videoTaskItem, new IVideoInfoListener() { // from class: com.jeffmony.downloader.VideoDownloadManager.2
            @Override // com.jeffmony.downloader.listener.IVideoInfoListener
            public void onFinalUrl(String str) {
            }

            @Override // com.jeffmony.downloader.listener.IVideoInfoListener
            public void onBaseVideoInfoSuccess(VideoTaskItem videoTaskItem2) {
                VideoDownloadManager.this.startBaseVideoDownloadTask(videoTaskItem2, map);
            }

            @Override // com.jeffmony.downloader.listener.IVideoInfoListener
            public void onBaseVideoInfoFailed(Throwable th) {
                LogUtils.w(VideoDownloadManager.TAG, "onInfoFailed error=" + th);
                videoTaskItem.setErrorCode(DownloadExceptionUtils.getErrorCode(th));
                videoTaskItem.setTaskState(6);
                VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(7, videoTaskItem).sendToTarget();
            }

            @Override // com.jeffmony.downloader.listener.IVideoInfoListener
            public void onM3U8InfoSuccess(VideoTaskItem videoTaskItem2, M3U8 m3u8) {
                videoTaskItem.setMimeType(videoTaskItem2.getMimeType());
                VideoDownloadManager.this.startM3U8VideoDownloadTask(videoTaskItem, m3u8, map);
            }

            @Override // com.jeffmony.downloader.listener.IVideoInfoListener
            public void onLiveM3U8Callback(VideoTaskItem videoTaskItem2) {
                LogUtils.w(VideoDownloadManager.TAG, "onLiveM3U8Callback cannot be cached.");
                videoTaskItem.setErrorCode(DownloadExceptionUtils.LIVE_M3U8_ERROR);
                videoTaskItem.setTaskState(6);
                VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(7, videoTaskItem).sendToTarget();
            }

            @Override // com.jeffmony.downloader.listener.IVideoInfoListener
            public void onM3U8InfoFailed(Throwable th) {
                LogUtils.w(VideoDownloadManager.TAG, "onM3U8InfoFailed : " + th);
                videoTaskItem.setErrorCode(DownloadExceptionUtils.getErrorCode(th));
                videoTaskItem.setTaskState(6);
                VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(7, videoTaskItem).sendToTarget();
            }
        }, map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startM3U8VideoDownloadTask(VideoTaskItem videoTaskItem, M3U8 m3u8, Map<String, String> map) {
        videoTaskItem.setTaskState(1);
        this.mVideoItemTaskMap.put(videoTaskItem.getUrl(), videoTaskItem);
        this.mVideoDownloadHandler.obtainMessage(2, (VideoTaskItem) videoTaskItem.clone()).sendToTarget();
        synchronized (this.mQueueLock) {
            if (this.mVideoDownloadQueue.getDownloadingCount() >= this.mConfig.getConcurrentCount()) {
                return;
            }
            VideoDownloadTask m3U8VideoDownloadTask = this.mVideoDownloadTaskMap.get(videoTaskItem.getUrl());
            if (m3U8VideoDownloadTask == null) {
                m3U8VideoDownloadTask = new M3U8VideoDownloadTask(videoTaskItem, m3u8, map);
                this.mVideoDownloadTaskMap.put(videoTaskItem.getUrl(), m3U8VideoDownloadTask);
            }
            startDownloadTask(m3U8VideoDownloadTask, videoTaskItem);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startBaseVideoDownloadTask(VideoTaskItem videoTaskItem, Map<String, String> map) {
        videoTaskItem.setTaskState(1);
        this.mVideoItemTaskMap.put(videoTaskItem.getUrl(), videoTaskItem);
        this.mVideoDownloadHandler.obtainMessage(2, (VideoTaskItem) videoTaskItem.clone()).sendToTarget();
        synchronized (this.mQueueLock) {
            if (this.mVideoDownloadQueue.getDownloadingCount() >= this.mConfig.getConcurrentCount()) {
                return;
            }
            VideoDownloadTask baseVideoDownloadTask = this.mVideoDownloadTaskMap.get(videoTaskItem.getUrl());
            if (baseVideoDownloadTask == null) {
                baseVideoDownloadTask = new BaseVideoDownloadTask(videoTaskItem, map);
                this.mVideoDownloadTaskMap.put(videoTaskItem.getUrl(), baseVideoDownloadTask);
            }
            startDownloadTask(baseVideoDownloadTask, videoTaskItem);
        }
    }

    private void startDownloadTask(VideoDownloadTask videoDownloadTask, final VideoTaskItem videoTaskItem) {
        if (videoDownloadTask != null) {
            videoDownloadTask.setDownloadTaskListener(new IDownloadTaskListener() { // from class: com.jeffmony.downloader.VideoDownloadManager.3
                @Override // com.jeffmony.downloader.listener.IDownloadTaskListener
                public void onTaskStart(String str) {
                    videoTaskItem.setTaskState(2);
                    VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(3, videoTaskItem).sendToTarget();
                }

                @Override // com.jeffmony.downloader.listener.IDownloadTaskListener
                public void onTaskProgress(float f, long j, long j2, float f2) {
                    if (videoTaskItem.isPaused()) {
                        return;
                    }
                    if (videoTaskItem.isErrorState() && videoTaskItem.isSuccessState()) {
                        return;
                    }
                    videoTaskItem.setTaskState(3);
                    videoTaskItem.setPercent(f);
                    videoTaskItem.setSpeed(f2);
                    videoTaskItem.setDownloadSize(j);
                    videoTaskItem.setTotalSize(j2);
                    VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(4, videoTaskItem).sendToTarget();
                }

                @Override // com.jeffmony.downloader.listener.IDownloadTaskListener
                public void onTaskProgressForM3U8(float f, long j, int i, int i2, float f2) {
                    if (videoTaskItem.isPaused()) {
                        return;
                    }
                    if (videoTaskItem.isErrorState() && videoTaskItem.isSuccessState()) {
                        return;
                    }
                    videoTaskItem.setTaskState(3);
                    videoTaskItem.setPercent(f);
                    videoTaskItem.setSpeed(f2);
                    videoTaskItem.setDownloadSize(j);
                    videoTaskItem.setCurTs(i);
                    videoTaskItem.setTotalTs(i2);
                    VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(4, videoTaskItem).sendToTarget();
                }

                @Override // com.jeffmony.downloader.listener.IDownloadTaskListener
                public void onTaskPaused() {
                    if (videoTaskItem.isErrorState() && videoTaskItem.isSuccessState()) {
                        return;
                    }
                    videoTaskItem.setTaskState(7);
                    videoTaskItem.setPaused(true);
                    VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(5, videoTaskItem).sendToTarget();
                    VideoDownloadManager.this.mVideoDownloadHandler.removeMessages(4);
                }

                @Override // com.jeffmony.downloader.listener.IDownloadTaskListener
                public void onTaskFinished(long j) {
                    if (videoTaskItem.getTaskState() != 5) {
                        videoTaskItem.setTaskState(5);
                        videoTaskItem.setDownloadSize(j);
                        videoTaskItem.setIsCompleted(true);
                        videoTaskItem.setPercent(100.0f);
                        if (videoTaskItem.isHlsType()) {
                            videoTaskItem.setFilePath(videoTaskItem.getSaveDir() + File.separator + videoTaskItem.getFileHash() + "_local.m3u8");
                            VideoTaskItem videoTaskItem2 = videoTaskItem;
                            StringBuilder sb = new StringBuilder();
                            sb.append(videoTaskItem.getFileHash());
                            sb.append("_local.m3u8");
                            videoTaskItem2.setFileName(sb.toString());
                        } else {
                            videoTaskItem.setFilePath(videoTaskItem.getSaveDir() + File.separator + videoTaskItem.getFileHash() + VideoDownloadUtils.VIDEO_SUFFIX);
                            VideoTaskItem videoTaskItem3 = videoTaskItem;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(videoTaskItem.getFileHash());
                            sb2.append(VideoDownloadUtils.VIDEO_SUFFIX);
                            videoTaskItem3.setFileName(sb2.toString());
                        }
                        VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(6, videoTaskItem).sendToTarget();
                        VideoDownloadManager.this.mVideoDownloadHandler.removeMessages(4);
                    }
                }

                @Override // com.jeffmony.downloader.listener.IDownloadTaskListener
                public void onTaskFailed(Throwable th) {
                    if (videoTaskItem.isSuccessState()) {
                        return;
                    }
                    videoTaskItem.setErrorCode(DownloadExceptionUtils.getErrorCode(th));
                    videoTaskItem.setTaskState(6);
                    VideoDownloadManager.this.mVideoDownloadHandler.obtainMessage(7, videoTaskItem).sendToTarget();
                    VideoDownloadManager.this.mVideoDownloadHandler.removeMessages(4);
                }
            });
            videoDownloadTask.startDownload();
        }
    }

    public String getDownloadPath() {
        VideoDownloadConfig videoDownloadConfig = this.mConfig;
        if (videoDownloadConfig != null) {
            return videoDownloadConfig.getCacheRoot();
        }
        return null;
    }

    public void deleteAllVideoFiles() {
        try {
            VideoStorageUtils.clearVideoCacheDir();
            this.mVideoItemTaskMap.clear();
            this.mVideoDownloadTaskMap.clear();
            this.mVideoDownloadHandler.obtainMessage(101).sendToTarget();
        } catch (Exception e) {
            LogUtils.w(TAG, "clearVideoCacheDir failed, exception = " + e.getMessage());
        }
    }

    public void pauseAllDownloadTasks() {
        synchronized (this.mQueueLock) {
            List<VideoTaskItem> downloadList = this.mVideoDownloadQueue.getDownloadList();
            LogUtils.i(TAG, "pauseAllDownloadTasks queue size=" + downloadList.size());
            ArrayList arrayList = new ArrayList();
            for (VideoTaskItem videoTaskItem : downloadList) {
                if (videoTaskItem.isPendingTask()) {
                    this.mVideoDownloadQueue.remove(videoTaskItem);
                    videoTaskItem.setTaskState(7);
                    this.mVideoItemTaskMap.put(videoTaskItem.getUrl(), videoTaskItem);
                    this.mVideoDownloadHandler.obtainMessage(5, videoTaskItem).sendToTarget();
                } else {
                    arrayList.add(videoTaskItem.getUrl());
                }
            }
            pauseDownloadTask(arrayList);
        }
    }

    public void pauseDownloadTask(List<String> list) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            pauseDownloadTask(it.next());
        }
    }

    public void pauseDownloadTask(String str) {
        if (this.mVideoItemTaskMap.containsKey(str)) {
            pauseDownloadTask(this.mVideoItemTaskMap.get(str));
        }
    }

    public void pauseDownloadTask(VideoTaskItem videoTaskItem) {
        if (videoTaskItem == null || TextUtils.isEmpty(videoTaskItem.getUrl())) {
            return;
        }
        synchronized (this.mQueueLock) {
            this.mVideoDownloadQueue.remove(videoTaskItem);
        }
        VideoDownloadTask videoDownloadTask = this.mVideoDownloadTaskMap.get(videoTaskItem.getUrl());
        if (videoDownloadTask != null) {
            videoDownloadTask.pauseDownload();
        }
    }

    public void resumeDownload(String str) {
        if (this.mVideoItemTaskMap.containsKey(str)) {
            startDownload(this.mVideoItemTaskMap.get(str));
        }
    }

    public void deleteVideoTask(final VideoTaskItem videoTaskItem, boolean z) {
        String downloadPath = getDownloadPath();
        if (TextUtils.isEmpty(downloadPath)) {
            return;
        }
        pauseDownloadTask(videoTaskItem);
        File file = new File(downloadPath + File.separator + VideoDownloadUtils.computeMD5(videoTaskItem.getUrl()));
        WorkerThreadHandler.submitRunnableTask(new Runnable() { // from class: com.jeffmony.downloader.VideoDownloadManager$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m699xbc16b29(videoTaskItem);
            }
        });
        if (z) {
            try {
                VideoStorageUtils.delete(file);
            } catch (Exception e) {
                LogUtils.w(TAG, "Delete file: " + file + " failed, exception=" + e.getMessage());
                return;
            }
        }
        if (this.mVideoDownloadTaskMap.containsKey(videoTaskItem.getUrl())) {
            this.mVideoDownloadTaskMap.remove(videoTaskItem.getUrl());
        }
        videoTaskItem.reset();
        this.mVideoDownloadHandler.obtainMessage(0, videoTaskItem).sendToTarget();
    }

    /* JADX INFO: renamed from: lambda$deleteVideoTask$0$com-jeffmony-downloader-VideoDownloadManager, reason: not valid java name */
    /* synthetic */ void m699xbc16b29(VideoTaskItem videoTaskItem) {
        this.mVideoDatabaseHelper.deleteDownloadItemByUrl(videoTaskItem);
    }

    public void deleteVideoTask(String str, boolean z) {
        if (this.mVideoItemTaskMap.containsKey(str)) {
            deleteVideoTask(this.mVideoItemTaskMap.get(str), z);
            this.mVideoItemTaskMap.remove(str);
        }
    }

    public void deleteVideoTasks(List<String> list, boolean z) {
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            deleteVideoTask(it.next(), z);
        }
    }

    public void deleteVideoTasks(VideoTaskItem[] videoTaskItemArr, boolean z) {
        if (TextUtils.isEmpty(getDownloadPath())) {
            return;
        }
        for (VideoTaskItem videoTaskItem : videoTaskItemArr) {
            deleteVideoTask(videoTaskItem, z);
        }
    }

    private void removeDownloadQueue(VideoTaskItem videoTaskItem) {
        synchronized (this.mQueueLock) {
            this.mVideoDownloadQueue.remove(videoTaskItem);
            LogUtils.w(TAG, "removeDownloadQueue size=" + this.mVideoDownloadQueue.size() + "," + this.mVideoDownloadQueue.getDownloadingCount() + "," + this.mVideoDownloadQueue.getPendingCount());
            int pendingCount = this.mVideoDownloadQueue.getPendingCount();
            for (int downloadingCount = this.mVideoDownloadQueue.getDownloadingCount(); downloadingCount < this.mConfig.getConcurrentCount() && pendingCount > 0 && this.mVideoDownloadQueue.size() != 0 && downloadingCount != this.mVideoDownloadQueue.size(); downloadingCount++) {
                startDownload(this.mVideoDownloadQueue.peekPendingTask(), null);
                pendingCount--;
            }
        }
    }

    class VideoDownloadHandler extends Handler {
        public VideoDownloadHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 100) {
                dispatchDownloadInfos();
            } else if (message.what == 101) {
                WorkerThreadHandler.submitRunnableTask(new Runnable() { // from class: com.jeffmony.downloader.VideoDownloadManager$VideoDownloadHandler$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.m705xb217cfac();
                    }
                });
            } else {
                dispatchDownloadMessage(message.what, (VideoTaskItem) message.obj);
            }
        }

        /* JADX INFO: renamed from: lambda$handleMessage$0$com-jeffmony-downloader-VideoDownloadManager$VideoDownloadHandler, reason: not valid java name */
        /* synthetic */ void m705xb217cfac() {
            VideoDownloadManager.this.mVideoDatabaseHelper.deleteAllDownloadInfos();
        }

        private void dispatchDownloadInfos() {
            WorkerThreadHandler.submitRunnableTask(new Runnable() { // from class: com.jeffmony.downloader.VideoDownloadManager$VideoDownloadHandler$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() throws Throwable {
                    this.f$0.m704xddaa34f2();
                }
            });
        }

        /* JADX INFO: renamed from: lambda$dispatchDownloadInfos$2$com-jeffmony-downloader-VideoDownloadManager$VideoDownloadHandler, reason: not valid java name */
        /* synthetic */ void m704xddaa34f2() throws Throwable {
            List<VideoTaskItem> downloadInfos = VideoDownloadManager.this.mVideoDatabaseHelper.getDownloadInfos();
            for (VideoTaskItem videoTaskItem : downloadInfos) {
                if (VideoDownloadManager.this.mConfig == null || !VideoDownloadManager.this.mConfig.shouldM3U8Merged() || !videoTaskItem.isHlsType()) {
                    VideoDownloadManager.this.mVideoItemTaskMap.put(videoTaskItem.getUrl(), videoTaskItem);
                } else {
                    VideoDownloadManager.this.doMergeTs(videoTaskItem, new IM3U8MergeResultListener() { // from class: com.jeffmony.downloader.VideoDownloadManager$VideoDownloadHandler$$ExternalSyntheticLambda2
                        @Override // com.jeffmony.downloader.listener.IM3U8MergeResultListener
                        public final void onCallback(VideoTaskItem videoTaskItem2) {
                            this.f$0.m706x47e72c0f(videoTaskItem2);
                        }
                    });
                }
            }
            Iterator it = VideoDownloadManager.this.mDownloadInfoCallbacks.iterator();
            while (it.hasNext()) {
                ((IDownloadInfosCallback) it.next()).onDownloadInfos(downloadInfos);
            }
        }

        /* JADX INFO: renamed from: lambda$null$1$com-jeffmony-downloader-VideoDownloadManager$VideoDownloadHandler, reason: not valid java name */
        /* synthetic */ void m706x47e72c0f(VideoTaskItem videoTaskItem) {
            VideoDownloadManager.this.mVideoItemTaskMap.put(videoTaskItem.getUrl(), videoTaskItem);
            VideoDownloadManager.this.markDownloadFinishEvent(videoTaskItem);
        }

        private void dispatchDownloadMessage(int i, VideoTaskItem videoTaskItem) {
            switch (i) {
                case 0:
                    VideoDownloadManager.this.handleOnDownloadDefault(videoTaskItem);
                    break;
                case 1:
                    VideoDownloadManager.this.handleOnDownloadPending(videoTaskItem);
                    break;
                case 2:
                    VideoDownloadManager.this.handleOnDownloadPrepare(videoTaskItem);
                    break;
                case 3:
                    VideoDownloadManager.this.handleOnDownloadStart(videoTaskItem);
                    break;
                case 4:
                    VideoDownloadManager.this.handleOnDownloadProcessing(videoTaskItem);
                    break;
                case 5:
                    VideoDownloadManager.this.handleOnDownloadPause(videoTaskItem);
                    break;
                case 6:
                    VideoDownloadManager.this.handleOnDownloadSuccess(videoTaskItem);
                    break;
                case 7:
                    VideoDownloadManager.this.handleOnDownloadError(videoTaskItem);
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnDownloadDefault(VideoTaskItem videoTaskItem) {
        this.mGlobalDownloadListener.onDownloadDefault(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnDownloadPending(VideoTaskItem videoTaskItem) {
        this.mGlobalDownloadListener.onDownloadPending(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnDownloadPrepare(VideoTaskItem videoTaskItem) {
        this.mGlobalDownloadListener.onDownloadPrepare(videoTaskItem);
        markDownloadInfoAddEvent(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnDownloadStart(VideoTaskItem videoTaskItem) {
        this.mGlobalDownloadListener.onDownloadStart(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnDownloadProcessing(VideoTaskItem videoTaskItem) {
        this.mGlobalDownloadListener.onDownloadProgress(videoTaskItem);
        markDownloadProgressInfoUpdateEvent(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnDownloadPause(VideoTaskItem videoTaskItem) {
        this.mGlobalDownloadListener.onDownloadPause(videoTaskItem);
        removeDownloadQueue(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnDownloadError(VideoTaskItem videoTaskItem) {
        this.mGlobalDownloadListener.onDownloadError(videoTaskItem);
        removeDownloadQueue(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleOnDownloadSuccess(VideoTaskItem videoTaskItem) {
        removeDownloadQueue(videoTaskItem);
        LogUtils.i(TAG, "handleOnDownloadSuccess shouldM3U8Merged=" + this.mConfig.shouldM3U8Merged() + ", isHlsType=" + videoTaskItem.isHlsType());
        if (this.mConfig.shouldM3U8Merged() && videoTaskItem.isHlsType()) {
            doMergeTs(videoTaskItem, new IM3U8MergeResultListener() { // from class: com.jeffmony.downloader.VideoDownloadManager$$ExternalSyntheticLambda0
                @Override // com.jeffmony.downloader.listener.IM3U8MergeResultListener
                public final void onCallback(VideoTaskItem videoTaskItem2) {
                    this.f$0.m700xa98dffe9(videoTaskItem2);
                }
            });
        } else {
            this.mGlobalDownloadListener.onDownloadSuccess(videoTaskItem);
            markDownloadFinishEvent(videoTaskItem);
        }
    }

    /* JADX INFO: renamed from: lambda$handleOnDownloadSuccess$1$com-jeffmony-downloader-VideoDownloadManager, reason: not valid java name */
    /* synthetic */ void m700xa98dffe9(VideoTaskItem videoTaskItem) {
        this.mGlobalDownloadListener.onDownloadSuccess(videoTaskItem);
        markDownloadFinishEvent(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doMergeTs(final VideoTaskItem videoTaskItem, final IM3U8MergeResultListener iM3U8MergeResultListener) {
        if (videoTaskItem == null || TextUtils.isEmpty(videoTaskItem.getFilePath())) {
            iM3U8MergeResultListener.onCallback(videoTaskItem);
            return;
        }
        LogUtils.i(TAG, "VideoMerge doMergeTs taskItem=" + videoTaskItem);
        String filePath = videoTaskItem.getFilePath();
        if (TextUtils.isEmpty(videoTaskItem.getFileHash())) {
            videoTaskItem.setFileHash(VideoDownloadUtils.computeMD5(videoTaskItem.getUrl()));
        }
        final String str = filePath.substring(0, filePath.lastIndexOf("/")) + File.separator + videoTaskItem.getFileHash() + "_merged.mp4";
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        VideoProcessManager.getInstance().transformM3U8ToMp4(filePath, str, new IVideoTransformListener() { // from class: com.jeffmony.downloader.VideoDownloadManager.4
            public void onTransformProgress(float f) {
            }

            public void onTransformFailed(Exception exc) {
                LogUtils.i(VideoDownloadManager.TAG, "VideoMerge onTransformFailed e=" + exc);
                File file2 = new File(str);
                if (file2.exists()) {
                    file2.delete();
                }
                iM3U8MergeResultListener.onCallback(videoTaskItem);
            }

            public void onTransformFinished() {
                LogUtils.i(VideoDownloadManager.TAG, "VideoMerge onTransformFinished outputPath=" + str);
                videoTaskItem.setFileName(VideoDownloadUtils.OUPUT_VIDEO);
                videoTaskItem.setFilePath(str);
                videoTaskItem.setMimeType(Video.Mime.MIME_TYPE_MP4);
                videoTaskItem.setVideoType(3);
                iM3U8MergeResultListener.onCallback(videoTaskItem);
                for (File file2 : new File(str).getParentFile().listFiles()) {
                    if (!file2.getAbsolutePath().endsWith(VideoDownloadUtils.OUPUT_VIDEO)) {
                        file2.delete();
                    }
                }
            }
        });
    }

    private void markDownloadInfoAddEvent(final VideoTaskItem videoTaskItem) {
        WorkerThreadHandler.submitRunnableTask(new Runnable() { // from class: com.jeffmony.downloader.VideoDownloadManager$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m702xeeb96a06(videoTaskItem);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$markDownloadInfoAddEvent$2$com-jeffmony-downloader-VideoDownloadManager, reason: not valid java name */
    /* synthetic */ void m702xeeb96a06(VideoTaskItem videoTaskItem) {
        this.mVideoDatabaseHelper.markDownloadInfoAddEvent(videoTaskItem);
    }

    private void markDownloadProgressInfoUpdateEvent(final VideoTaskItem videoTaskItem) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (videoTaskItem.getLastUpdateTime() + 1000 < jCurrentTimeMillis) {
            WorkerThreadHandler.submitRunnableTask(new Runnable() { // from class: com.jeffmony.downloader.VideoDownloadManager$$ExternalSyntheticLambda3
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m703xeb0a439a(videoTaskItem);
                }
            });
            videoTaskItem.setLastUpdateTime(jCurrentTimeMillis);
        }
    }

    /* JADX INFO: renamed from: lambda$markDownloadProgressInfoUpdateEvent$3$com-jeffmony-downloader-VideoDownloadManager, reason: not valid java name */
    /* synthetic */ void m703xeb0a439a(VideoTaskItem videoTaskItem) {
        this.mVideoDatabaseHelper.markDownloadProgressInfoUpdateEvent(videoTaskItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markDownloadFinishEvent(final VideoTaskItem videoTaskItem) {
        WorkerThreadHandler.submitRunnableTask(new Runnable() { // from class: com.jeffmony.downloader.VideoDownloadManager$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m701x9f21a628(videoTaskItem);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$markDownloadFinishEvent$4$com-jeffmony-downloader-VideoDownloadManager, reason: not valid java name */
    /* synthetic */ void m701x9f21a628(VideoTaskItem videoTaskItem) {
        this.mVideoDatabaseHelper.markDownloadProgressInfoUpdateEvent(videoTaskItem);
    }
}
