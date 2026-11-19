package com.jeffmony.downloader.task;

import android.text.TextUtils;
import com.google.common.net.HttpHeaders;
import com.jeffmony.downloader.VideoDownloadException;
import com.jeffmony.downloader.m3u8.M3U8;
import com.jeffmony.downloader.m3u8.M3U8Constants;
import com.jeffmony.downloader.m3u8.M3U8Seg;
import com.jeffmony.downloader.model.VideoTaskItem;
import com.jeffmony.downloader.utils.DownloadExceptionUtils;
import com.jeffmony.downloader.utils.HttpUtils;
import com.jeffmony.downloader.utils.LogUtils;
import com.jeffmony.downloader.utils.VideoDownloadUtils;
import com.jeffmony.downloader.utils.VideoStorageUtils;
import com.movieboxpro.android.utils.ShellUtil;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class M3U8VideoDownloadTask extends VideoDownloadTask {
    private static final int CONTINUOUS_SUCCESS_TS_THRESHOLD = 6;
    private static final String TAG = "M3U8VideoDownloadTask";
    private volatile int mContinuousSuccessTsCount;
    private volatile int mCurTs;
    private final Object mFileLock;
    private final M3U8 mM3U8;
    private volatile int mM3U8DownloadPoolCount;
    private long mTotalSize;
    private int mTotalTs;
    private List<M3U8Seg> mTsList;

    public M3U8VideoDownloadTask(VideoTaskItem videoTaskItem, M3U8 m3u8, Map<String, String> map) {
        super(videoTaskItem, map);
        this.mFileLock = new Object();
        this.mCurTs = 0;
        this.mM3U8 = m3u8;
        List<M3U8Seg> tsList = m3u8.getTsList();
        this.mTsList = tsList;
        this.mTotalTs = tsList.size();
        this.mPercent = videoTaskItem.getPercent();
        if (this.mHeaders == null) {
            this.mHeaders = new HashMap();
        }
        this.mHeaders.put(HttpHeaders.CONNECTION, "close");
        videoTaskItem.setTotalTs(this.mTotalTs);
        videoTaskItem.setCurTs(this.mCurTs);
    }

    private void initM3U8Ts() {
        int i = 0;
        for (M3U8Seg m3U8Seg : this.mTsList) {
            File file = new File(this.mSaveDir, m3U8Seg.getIndexName());
            if (!file.exists() || file.length() <= 0) {
                break;
            }
            m3U8Seg.setTsSize(file.length());
            i++;
        }
        this.mCurTs = i;
        this.mCurrentCachedSize = 0L;
        if (this.mCurTs == this.mTotalTs) {
            this.mTaskItem.setIsCompleted(true);
        }
    }

    @Override // com.jeffmony.downloader.task.VideoDownloadTask
    public void startDownload() {
        this.mDownloadTaskListener.onTaskStart(this.mTaskItem.getUrl());
        initM3U8Ts();
        startDownload(this.mCurTs);
    }

    private void startDownload(int i) {
        if (this.mTaskItem.isCompleted()) {
            LogUtils.i(TAG, "M3U8VideoDownloadTask local file.");
            notifyDownloadFinish();
            return;
        }
        this.mCurTs = i;
        LogUtils.i(TAG, "startDownload curDownloadTs = " + i);
        this.mDownloadExecutor = new ThreadPoolExecutor(6, 6, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.DiscardOldestPolicy());
        while (i < this.mTotalTs) {
            final M3U8Seg m3U8Seg = this.mTsList.get(i);
            this.mDownloadExecutor.execute(new Runnable() { // from class: com.jeffmony.downloader.task.M3U8VideoDownloadTask$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.m709x3b782c1(m3U8Seg);
                }
            });
            i++;
        }
        notifyDownloadFinish(this.mCurrentCachedSize);
    }

    /* JADX INFO: renamed from: lambda$startDownload$0$com-jeffmony-downloader-task-M3U8VideoDownloadTask, reason: not valid java name */
    /* synthetic */ void m709x3b782c1(M3U8Seg m3U8Seg) {
        try {
            downloadTsTask(m3U8Seg);
        } catch (Exception e) {
            LogUtils.w(TAG, "M3U8TsDownloadThread download failed, exception=" + e);
            notifyDownloadError(e);
        }
    }

    private void downloadTsTask(M3U8Seg m3U8Seg) throws Exception {
        if (m3U8Seg.hasInitSegment()) {
            File file = new File(this.mSaveDir, m3U8Seg.getInitSegmentName());
            if (!file.exists()) {
                downloadFile(m3U8Seg, file, m3U8Seg.getInitSegmentUri());
            }
        }
        File file2 = new File(this.mSaveDir, m3U8Seg.getIndexName());
        if (!file2.exists()) {
            downloadFile(m3U8Seg, file2, m3U8Seg.getUrl());
        }
        if (file2.exists() && file2.length() == m3U8Seg.getContentLength()) {
            m3U8Seg.setName(m3U8Seg.getIndexName());
            m3U8Seg.setTsSize(file2.length());
            notifyDownloadProgress();
        }
    }

    @Override // com.jeffmony.downloader.task.VideoDownloadTask
    public void resumeDownload() {
        initM3U8Ts();
        startDownload(this.mCurTs);
    }

    @Override // com.jeffmony.downloader.task.VideoDownloadTask
    public void pauseDownload() {
        if (this.mDownloadExecutor == null || this.mDownloadExecutor.isShutdown()) {
            return;
        }
        this.mDownloadExecutor.shutdownNow();
        notifyOnTaskPaused();
    }

    private void notifyDownloadProgress() {
        updateM3U8TsInfo();
        if (this.mCurrentCachedSize == 0) {
            this.mCurrentCachedSize = VideoStorageUtils.countTotalSize(this.mSaveDir);
        }
        if (this.mTaskItem.isCompleted()) {
            this.mCurTs = this.mTotalTs;
            synchronized (this.mDownloadLock) {
                if (!this.mDownloadFinished) {
                    this.mDownloadTaskListener.onTaskProgressForM3U8(100.0f, this.mCurrentCachedSize, this.mCurTs, this.mTotalTs, this.mSpeed);
                    this.mPercent = 100.0f;
                    this.mTotalSize = this.mCurrentCachedSize;
                    this.mDownloadTaskListener.onTaskFinished(this.mTotalSize);
                    this.mDownloadFinished = true;
                }
            }
            return;
        }
        int i = this.mCurTs;
        int i2 = this.mTotalTs;
        if (i >= i2) {
            this.mCurTs = i2;
        }
        synchronized (this.mDownloadLock) {
            float f = ((this.mCurTs * 1.0f) * 100.0f) / this.mTotalTs;
            if (!VideoDownloadUtils.isFloatEqual(f, this.mPercent)) {
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (this.mCurrentCachedSize > this.mLastCachedSize && jCurrentTimeMillis > this.mLastInvokeTime) {
                    this.mSpeed = (((this.mCurrentCachedSize - this.mLastCachedSize) * 1000) * 1.0f) / (jCurrentTimeMillis - this.mLastInvokeTime);
                }
                if (!this.mDownloadFinished) {
                    this.mDownloadTaskListener.onTaskProgressForM3U8(f, this.mCurrentCachedSize, this.mCurTs, this.mTotalTs, this.mSpeed);
                }
                this.mPercent = f;
                this.mLastCachedSize = this.mCurrentCachedSize;
                this.mLastInvokeTime = jCurrentTimeMillis;
            }
        }
        Iterator<M3U8Seg> it = this.mTsList.iterator();
        while (it.hasNext()) {
            if (!new File(this.mSaveDir, it.next().getIndexName()).exists()) {
                return;
            }
        }
        try {
            createLocalM3U8File();
        } catch (Exception e) {
            notifyDownloadError(e);
        }
        synchronized (this.mDownloadLock) {
            if (!this.mDownloadFinished) {
                this.mTotalSize = this.mCurrentCachedSize;
                this.mDownloadTaskListener.onTaskProgressForM3U8(100.0f, this.mCurrentCachedSize, this.mCurTs, this.mTotalTs, this.mSpeed);
                this.mDownloadTaskListener.onTaskFinished(this.mTotalSize);
                this.mDownloadFinished = true;
            }
        }
    }

    private void updateM3U8TsInfo() {
        int i = 0;
        for (M3U8Seg m3U8Seg : this.mTsList) {
            File file = new File(this.mSaveDir, m3U8Seg.getIndexName());
            if (file.exists() && file.length() > 0) {
                m3U8Seg.setTsSize(file.length());
                i++;
            }
        }
        this.mCurTs = i;
        this.mCurrentCachedSize = 0L;
    }

    private void notifyDownloadFinish() {
        notifyDownloadProgress();
        notifyDownloadFinish(this.mTotalSize);
    }

    private void notifyDownloadFinish(long j) {
        if (this.mTaskItem.isCompleted()) {
            synchronized (this.mDownloadLock) {
                if (!this.mDownloadFinished) {
                    this.mDownloadTaskListener.onTaskFinished(j);
                    this.mDownloadFinished = true;
                }
            }
        }
    }

    private void notifyDownloadError(Exception exc) {
        notifyOnTaskFailed(exc);
    }

    public void downloadFile(M3U8Seg m3U8Seg, File file, String str) throws Exception {
        InputStream inputStream;
        HttpURLConnection httpURLConnection = null;
        inputStream = null;
        InputStream inputStream2 = null;
        httpURLConnection = null;
        try {
            try {
                try {
                    HttpURLConnection connection = HttpUtils.getConnection(str, this.mHeaders, VideoDownloadUtils.getDownloadConfig().shouldIgnoreCertErrors());
                    try {
                        int responseCode = connection.getResponseCode();
                        if (responseCode == 200 || responseCode == 206) {
                            m3U8Seg.setRetryCount(0);
                            this.mContinuousSuccessTsCount++;
                            if (this.mContinuousSuccessTsCount > 6 && this.mM3U8DownloadPoolCount < 6) {
                                this.mM3U8DownloadPoolCount++;
                                this.mContinuousSuccessTsCount--;
                                setThreadPoolArgument(this.mM3U8DownloadPoolCount, this.mM3U8DownloadPoolCount);
                            }
                            inputStream = connection.getInputStream();
                            try {
                                saveFile(inputStream, file, connection.getContentLength(), m3U8Seg, str);
                                inputStream2 = inputStream;
                            } catch (Exception e) {
                                e = e;
                                httpURLConnection = connection;
                                try {
                                    this.mContinuousSuccessTsCount = 0;
                                    if (!(e instanceof IOException)) {
                                    }
                                    LogUtils.w(TAG, "downloadFile failed, exception=" + e.getMessage());
                                    throw e;
                                } catch (Throwable th) {
                                    th = th;
                                    HttpUtils.closeConnection(httpURLConnection);
                                    VideoDownloadUtils.close(inputStream);
                                    throw th;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                httpURLConnection = connection;
                                HttpUtils.closeConnection(httpURLConnection);
                                VideoDownloadUtils.close(inputStream);
                                throw th;
                            }
                        } else {
                            this.mContinuousSuccessTsCount = 0;
                            if (responseCode == 503) {
                                if (this.mM3U8DownloadPoolCount > 1) {
                                    this.mM3U8DownloadPoolCount--;
                                    setThreadPoolArgument(this.mM3U8DownloadPoolCount, this.mM3U8DownloadPoolCount);
                                    downloadFile(m3U8Seg, file, str);
                                } else {
                                    m3U8Seg.setRetryCount(m3U8Seg.getRetryCount() + 1);
                                    if (m3U8Seg.getRetryCount() < 100) {
                                        downloadFile(m3U8Seg, file, str);
                                    } else {
                                        throw new VideoDownloadException(DownloadExceptionUtils.RETRY_COUNT_EXCEED_WITH_THREAD_CONTROL_STRING);
                                    }
                                }
                            } else {
                                throw new VideoDownloadException(DownloadExceptionUtils.VIDEO_REQUEST_FAILED);
                            }
                        }
                        HttpUtils.closeConnection(connection);
                        VideoDownloadUtils.close(inputStream2);
                    } catch (Exception e2) {
                        e = e2;
                        inputStream = null;
                    } catch (Throwable th3) {
                        th = th3;
                        inputStream = null;
                    }
                } catch (Exception e3) {
                    e = e3;
                    inputStream = null;
                    this.mContinuousSuccessTsCount = 0;
                    if (!(e instanceof IOException) && e.getMessage().contains(DownloadExceptionUtils.PROTOCOL_UNEXPECTED_END_OF_STREAM)) {
                        if (this.mM3U8DownloadPoolCount > 1) {
                            this.mM3U8DownloadPoolCount--;
                            setThreadPoolArgument(this.mM3U8DownloadPoolCount, this.mM3U8DownloadPoolCount);
                            downloadFile(m3U8Seg, file, str);
                        } else {
                            m3U8Seg.setRetryCount(m3U8Seg.getRetryCount() + 1);
                            if (m3U8Seg.getRetryCount() < 100) {
                                downloadFile(m3U8Seg, file, str);
                            } else {
                                throw e;
                            }
                        }
                        HttpUtils.closeConnection(httpURLConnection);
                        VideoDownloadUtils.close(inputStream);
                        return;
                    }
                    LogUtils.w(TAG, "downloadFile failed, exception=" + e.getMessage());
                    throw e;
                }
            } catch (Throwable th4) {
                th = th4;
                inputStream = null;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }

    /* JADX WARN: Code duplicated, block: B:45:0x007f A[Catch: all -> 0x0138, TRY_LEAVE, TryCatch #4 {all -> 0x0138, blocks: (B:32:0x0059, B:36:0x0063, B:42:0x0076, B:45:0x007f, B:48:0x0089, B:50:0x0093, B:54:0x00a3, B:56:0x00ab, B:58:0x00b3), top: B:82:0x0059 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x0132 A[Catch: all -> 0x0136, TryCatch #3 {all -> 0x0136, blocks: (B:62:0x00c8, B:63:0x00cc, B:65:0x00f7, B:66:0x00fa, B:67:0x00fb, B:71:0x0109, B:73:0x0132, B:74:0x0135), top: B:81:0x0087 }] */
    private void saveFile(InputStream inputStream, File file, long j, M3U8Seg m3U8Seg, String str) throws Exception {
        long j2;
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[8192];
                j2 = 0;
                while (true) {
                    try {
                        int i = inputStream.read(bArr);
                        if (i == -1) {
                            break;
                        }
                        j2 += (long) i;
                        fileOutputStream2.write(bArr, 0, i);
                    } catch (IOException e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        try {
                            if (!file.exists()) {
                                try {
                                    if ((e instanceof ProtocolException) || TextUtils.isEmpty(e.getMessage()) || !e.getMessage().contains(DownloadExceptionUtils.PROTOCOL_UNEXPECTED_END_OF_STREAM) || j <= j2 || j2 != file.length()) {
                                        LogUtils.w(TAG, file.getAbsolutePath() + ", length=" + file.length() + ", saveFile failed, exception=" + e);
                                        if (file.exists()) {
                                            file.delete();
                                        }
                                        throw e;
                                    }
                                    if (file.length() == 0) {
                                        m3U8Seg.setRetryCount(m3U8Seg.getRetryCount() + 1);
                                        if (m3U8Seg.getRetryCount() < 100) {
                                            downloadFile(m3U8Seg, file, str);
                                        } else {
                                            LogUtils.w(TAG, file.getAbsolutePath() + ", length=" + file.length() + ", saveFile failed, exception=" + e);
                                            if (file.exists()) {
                                                file.delete();
                                            }
                                            throw e;
                                        }
                                    } else {
                                        m3U8Seg.setContentLength(j2);
                                    }
                                } catch (Throwable th) {
                                    th = th;
                                    VideoDownloadUtils.close(inputStream);
                                    VideoDownloadUtils.close(fileOutputStream);
                                    throw th;
                                }
                                VideoDownloadUtils.close(inputStream);
                                VideoDownloadUtils.close(fileOutputStream);
                                throw th;
                            }
                            if (j <= 0 || j != file.length()) {
                                if (j != -1 || j2 != file.length()) {
                                    if (e instanceof ProtocolException) {
                                    }
                                    LogUtils.w(TAG, file.getAbsolutePath() + ", length=" + file.length() + ", saveFile failed, exception=" + e);
                                    if (file.exists()) {
                                        file.delete();
                                    }
                                    throw e;
                                }
                            }
                            VideoDownloadUtils.close(inputStream);
                            VideoDownloadUtils.close(fileOutputStream);
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            VideoDownloadUtils.close(inputStream);
                            VideoDownloadUtils.close(fileOutputStream);
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        fileOutputStream = fileOutputStream2;
                        VideoDownloadUtils.close(inputStream);
                        VideoDownloadUtils.close(fileOutputStream);
                        throw th;
                    }
                }
                if (j > 0 && j == j2) {
                    m3U8Seg.setContentLength(j);
                } else {
                    m3U8Seg.setContentLength(j2);
                }
                VideoDownloadUtils.close(inputStream);
                VideoDownloadUtils.close(fileOutputStream2);
            } catch (IOException e2) {
                e = e2;
                j2 = 0;
            } catch (Throwable th4) {
                th = th4;
            }
        } catch (IOException e3) {
            e = e3;
            j2 = 0;
        } catch (Throwable th5) {
            th = th5;
            VideoDownloadUtils.close(inputStream);
            VideoDownloadUtils.close(fileOutputStream);
            throw th;
        }
    }

    private void createLocalM3U8File() throws IOException {
        synchronized (this.mFileLock) {
            File file = new File(this.mSaveDir, "temp.m3u8");
            if (file.exists()) {
                file.delete();
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false));
            bufferedWriter.write("#EXTM3U\n");
            bufferedWriter.write("#EXT-X-VERSION:" + this.mM3U8.getVersion() + ShellUtil.COMMAND_LINE_END);
            bufferedWriter.write("#EXT-X-MEDIA-SEQUENCE:" + this.mM3U8.getInitSequence() + ShellUtil.COMMAND_LINE_END);
            bufferedWriter.write("#EXT-X-TARGETDURATION:" + this.mM3U8.getTargetDuration() + ShellUtil.COMMAND_LINE_END);
            for (M3U8Seg m3U8Seg : this.mTsList) {
                if (m3U8Seg.hasInitSegment()) {
                    String str = this.mSaveDir.getAbsolutePath() + File.separator + m3U8Seg.getInitSegmentName();
                    bufferedWriter.write("#EXT-X-MAP:" + (m3U8Seg.getSegmentByteRange() != null ? "URI=\"" + str + "\",BYTERANGE=\"" + m3U8Seg.getSegmentByteRange() + "\"" : "URI=\"" + str + "\"") + ShellUtil.COMMAND_LINE_END);
                }
                if (m3U8Seg.hasKey() && m3U8Seg.getMethod() != null) {
                    String str2 = "METHOD=" + m3U8Seg.getMethod();
                    if (m3U8Seg.getKeyUri() != null) {
                        File file2 = new File(this.mSaveDir, m3U8Seg.getLocalKeyUri());
                        str2 = (!m3U8Seg.isMessyKey() && file2.exists()) ? str2 + ",URI=\"" + file2.getAbsolutePath() + "\"" : str2 + ",URI=\"" + m3U8Seg.getKeyUri() + "\"";
                    }
                    if (m3U8Seg.getKeyIV() != null) {
                        str2 = str2 + ",IV=" + m3U8Seg.getKeyIV();
                    }
                    bufferedWriter.write("#EXT-X-KEY:" + str2 + ShellUtil.COMMAND_LINE_END);
                }
                if (m3U8Seg.hasDiscontinuity()) {
                    bufferedWriter.write("#EXT-X-DISCONTINUITY\n");
                }
                bufferedWriter.write("#EXTINF:" + m3U8Seg.getDuration() + ",\n");
                bufferedWriter.write(this.mSaveDir.getAbsolutePath() + File.separator + m3U8Seg.getIndexName());
                bufferedWriter.newLine();
            }
            bufferedWriter.write(M3U8Constants.TAG_ENDLIST);
            bufferedWriter.flush();
            bufferedWriter.close();
            File file3 = new File(this.mSaveDir, this.mSaveName + "_" + VideoDownloadUtils.LOCAL_M3U8);
            if (file3.exists()) {
                file3.delete();
            }
            file.renameTo(file3);
        }
    }
}
