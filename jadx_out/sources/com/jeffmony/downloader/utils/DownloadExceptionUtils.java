package com.jeffmony.downloader.utils;

import com.jeffmony.downloader.VideoDownloadException;
import java.io.FileNotFoundException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes5.dex */
public class DownloadExceptionUtils {
    public static final String CREATE_CONNECTION_ERROR = "Create connection failed";
    private static final int FILE_LENGTH_FETCHED_ERROR = 5100;
    public static final String FILE_LENGTH_FETCHED_ERROR_STRING = "File Length Cannot be fetched";
    private static final int FILE_NOT_FOUND_ERROR = 5001;
    public static final String FINAL_URL_EMPTY = "FinalUrl is null";
    public static final int LIVE_M3U8_ERROR = 5104;
    private static final int M3U8_FILE_CONTENT_ERROR = 5101;
    public static final String M3U8_FILE_CONTENT_ERROR_STRING = "M3U8 File content error";
    private static final int MIMETYPE_NOT_FOUND = 5103;
    public static final String MIMETYPE_NOT_FOUND_STRING = "MimeType not found";
    private static final int MIMETYPE_NULL_ERROR = 5102;
    public static final String MIMETYPE_NULL_ERROR_STRING = "MimeType is null";
    public static final String PROTOCOL_UNEXPECTED_END_OF_STREAM = "unexpected end of stream";
    public static final String REMOTE_M3U8_EMPTY = "Cannot find remote.m3u8 file";
    public static final String RETRY_COUNT_EXCEED_WITH_THREAD_CONTROL_STRING = "Retry count exceeding with thread control";
    private static final int SOCKET_TIMEOUT_ERROR = 5000;
    private static final int UNKNOWN_ERROR = -1;
    private static final int UNKNOWN_HOST_ERROR = 5002;
    public static final String URL_SCHEMA_ERROR = "Cannot parse the request resource's schema";
    public static final String VIDEO_INFO_EMPTY = "Video info is null";
    public static final String VIDEO_REQUEST_FAILED = "Video request failed";

    public static int getErrorCode(Throwable th) {
        if (th instanceof SocketTimeoutException) {
            return 5000;
        }
        if (th instanceof FileNotFoundException) {
            return 5001;
        }
        if (!(th instanceof VideoDownloadException)) {
            return th instanceof UnknownHostException ? 5002 : -1;
        }
        VideoDownloadException videoDownloadException = (VideoDownloadException) th;
        if (videoDownloadException.getMsg().equals(FILE_LENGTH_FETCHED_ERROR_STRING)) {
            return FILE_LENGTH_FETCHED_ERROR;
        }
        if (videoDownloadException.getMsg().equals(M3U8_FILE_CONTENT_ERROR_STRING)) {
            return M3U8_FILE_CONTENT_ERROR;
        }
        if (videoDownloadException.getMsg().equals(MIMETYPE_NULL_ERROR_STRING)) {
            return MIMETYPE_NULL_ERROR;
        }
        if (videoDownloadException.getMsg().equals(MIMETYPE_NOT_FOUND_STRING)) {
            return MIMETYPE_NOT_FOUND;
        }
        return -1;
    }
}
