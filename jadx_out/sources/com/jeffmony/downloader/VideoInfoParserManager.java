package com.jeffmony.downloader;

import android.text.TextUtils;
import com.jeffmony.downloader.listener.IVideoInfoListener;
import com.jeffmony.downloader.listener.IVideoInfoParseListener;
import com.jeffmony.downloader.m3u8.M3U8;
import com.jeffmony.downloader.m3u8.M3U8Utils;
import com.jeffmony.downloader.model.Video;
import com.jeffmony.downloader.model.VideoTaskItem;
import com.jeffmony.downloader.utils.DownloadExceptionUtils;
import com.jeffmony.downloader.utils.HttpUtils;
import com.jeffmony.downloader.utils.LogUtils;
import com.jeffmony.downloader.utils.VideoDownloadUtils;
import com.jeffmony.downloader.utils.WorkerThreadHandler;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class VideoInfoParserManager {
    private static final String TAG = "VideoInfoParserManager";
    private static volatile VideoInfoParserManager sInstance;

    public static VideoInfoParserManager getInstance() {
        if (sInstance == null) {
            synchronized (VideoInfoParserManager.class) {
                if (sInstance == null) {
                    sInstance = new VideoInfoParserManager();
                }
            }
        }
        return sInstance;
    }

    public synchronized void parseVideoInfo(final VideoTaskItem videoTaskItem, final IVideoInfoListener iVideoInfoListener, final Map<String, String> map) {
        WorkerThreadHandler.submitRunnableTask(new Runnable() { // from class: com.jeffmony.downloader.VideoInfoParserManager$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() throws Throwable {
                this.f$0.m707x8a91d89(videoTaskItem, iVideoInfoListener, map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: doParseVideoInfoTask, reason: merged with bridge method [inline-methods] */
    public void m707x8a91d89(VideoTaskItem videoTaskItem, IVideoInfoListener iVideoInfoListener, Map<String, String> map) throws Throwable {
        HttpURLConnection connection;
        int responseCode;
        try {
            if (videoTaskItem == null) {
                iVideoInfoListener.onBaseVideoInfoFailed(new VideoDownloadException(DownloadExceptionUtils.VIDEO_INFO_EMPTY));
                return;
            }
            if (!HttpUtils.matchHttpSchema(videoTaskItem.getUrl())) {
                iVideoInfoListener.onBaseVideoInfoFailed(new VideoDownloadException(DownloadExceptionUtils.URL_SCHEMA_ERROR));
                return;
            }
            if (TextUtils.isEmpty(videoTaskItem.getCoverPath()) && !TextUtils.isEmpty(videoTaskItem.getCoverUrl()) && ((responseCode = (connection = HttpUtils.getConnection(videoTaskItem.getCoverUrl(), map, VideoDownloadUtils.getDownloadConfig().shouldIgnoreCertErrors())).getResponseCode()) == 200 || responseCode == 206)) {
                InputStream inputStream = connection.getInputStream();
                File file = new File(VideoDownloadUtils.getDownloadConfig().getCacheRoot(), videoTaskItem.getFileHash() + ".jpg");
                if (saveCoverFile(inputStream, file)) {
                    videoTaskItem.setCoverPath(file.getAbsolutePath());
                }
            }
            String url = videoTaskItem.getUrl();
            LogUtils.i(TAG, "doParseVideoInfoTask url=" + url);
            try {
                HttpURLConnection connection2 = HttpUtils.getConnection(url, map, VideoDownloadUtils.getDownloadConfig().shouldIgnoreCertErrors());
                if (connection2 == null) {
                    iVideoInfoListener.onBaseVideoInfoFailed(new VideoDownloadException(DownloadExceptionUtils.CREATE_CONNECTION_ERROR));
                    return;
                }
                String string = connection2.getURL().toString();
                if (TextUtils.isEmpty(string)) {
                    iVideoInfoListener.onBaseVideoInfoFailed(new VideoDownloadException(DownloadExceptionUtils.FINAL_URL_EMPTY));
                    HttpUtils.closeConnection(connection2);
                    return;
                }
                videoTaskItem.setFinalUrl(string);
                String contentType = connection2.getContentType();
                if (!string.contains(Video.TypeInfo.M3U8) && !VideoDownloadUtils.isM3U8Mimetype(contentType)) {
                    long contentLength = getContentLength(videoTaskItem, map, connection2, false);
                    if (contentLength == -1) {
                        iVideoInfoListener.onBaseVideoInfoFailed(new VideoDownloadException(DownloadExceptionUtils.FILE_LENGTH_FETCHED_ERROR_STRING));
                        HttpUtils.closeConnection(connection2);
                        return;
                    } else {
                        videoTaskItem.setTotalSize(contentLength);
                        iVideoInfoListener.onBaseVideoInfoSuccess(videoTaskItem);
                        return;
                    }
                }
                videoTaskItem.setMimeType(Video.TypeInfo.M3U8);
                parseNetworkM3U8Info(videoTaskItem, map, iVideoInfoListener);
            } catch (Exception unused) {
                iVideoInfoListener.onBaseVideoInfoFailed(new VideoDownloadException(DownloadExceptionUtils.CREATE_CONNECTION_ERROR));
                HttpUtils.closeConnection(null);
            }
        } catch (Exception e) {
            iVideoInfoListener.onBaseVideoInfoFailed(e);
        }
    }

    private long getContentLength(VideoTaskItem videoTaskItem, Map<String, String> map, HttpURLConnection httpURLConnection, boolean z) {
        if (z) {
            try {
                httpURLConnection = HttpUtils.getConnection(videoTaskItem.getFinalUrl(), map, VideoDownloadUtils.getDownloadConfig().shouldIgnoreCertErrors());
                if (httpURLConnection == null) {
                    return -1L;
                }
            } catch (Exception unused) {
                HttpUtils.closeConnection(httpURLConnection);
                return -1L;
            }
        }
        String headerField = httpURLConnection.getHeaderField("content-length");
        if (TextUtils.isEmpty(headerField)) {
            if (map == null) {
                map = new HashMap<>();
            } else if (map.containsKey("Range")) {
                HttpUtils.closeConnection(httpURLConnection);
                return -1L;
            }
            map.put("Range", "bytes=0-");
            HttpUtils.closeConnection(httpURLConnection);
            return getContentLength(videoTaskItem, map, httpURLConnection, true);
        }
        long j = Long.parseLong(headerField);
        if (j > 0) {
            return j;
        }
        HttpUtils.closeConnection(httpURLConnection);
        return -1L;
    }

    private void parseNetworkM3U8Info(VideoTaskItem videoTaskItem, Map<String, String> map, IVideoInfoListener iVideoInfoListener) throws Throwable {
        try {
            M3U8 networkM3U8Info = M3U8Utils.parseNetworkM3U8Info(videoTaskItem.getUrl(), map, 0);
            if (networkM3U8Info.hasEndList()) {
                File file = new File(VideoDownloadUtils.getDownloadConfig().getCacheRoot(), VideoDownloadUtils.computeMD5(videoTaskItem.getUrl()));
                if (!file.exists()) {
                    file.mkdir();
                }
                M3U8Utils.createRemoteM3U8(file, networkM3U8Info);
                videoTaskItem.setSaveDir(file.getAbsolutePath());
                videoTaskItem.setVideoType(1);
                iVideoInfoListener.onM3U8InfoSuccess(videoTaskItem, networkM3U8Info);
                return;
            }
            videoTaskItem.setVideoType(2);
            iVideoInfoListener.onLiveM3U8Callback(videoTaskItem);
        } catch (Exception e) {
            e.printStackTrace();
            iVideoInfoListener.onM3U8InfoFailed(e);
        }
    }

    public void parseLocalM3U8File(VideoTaskItem videoTaskItem, IVideoInfoParseListener iVideoInfoParseListener) {
        File file = new File(videoTaskItem.getSaveDir(), VideoDownloadUtils.REMOTE_M3U8);
        if (!file.exists()) {
            iVideoInfoParseListener.onM3U8FileParseFailed(videoTaskItem, new VideoDownloadException(DownloadExceptionUtils.REMOTE_M3U8_EMPTY));
            return;
        }
        try {
            iVideoInfoParseListener.onM3U8FileParseSuccess(videoTaskItem, M3U8Utils.parseLocalM3U8File(file));
        } catch (Exception e) {
            e.printStackTrace();
            iVideoInfoParseListener.onM3U8FileParseFailed(videoTaskItem, e);
        }
    }

    private boolean saveCoverFile(InputStream inputStream, File file) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i != -1) {
                        fileOutputStream2.write(bArr, 0, i);
                    } else {
                        VideoDownloadUtils.close(inputStream);
                        VideoDownloadUtils.close(fileOutputStream2);
                        return true;
                    }
                }
            } catch (IOException unused) {
                fileOutputStream = fileOutputStream2;
                VideoDownloadUtils.close(inputStream);
                VideoDownloadUtils.close(fileOutputStream);
                return false;
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                VideoDownloadUtils.close(inputStream);
                VideoDownloadUtils.close(fileOutputStream);
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
