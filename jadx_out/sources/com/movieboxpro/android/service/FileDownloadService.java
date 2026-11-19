package com.movieboxpro.android.service;

import android.app.IntentService;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.movieboxpro.android.app.App$$ExternalSyntheticApiModelOutline0;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.utils.HttpHelper;
import com.movieboxpro.android.utils.IntentUtils;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.androidtv.R;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import kotlin.io.FilesKt;
import okhttp3.ResponseBody;
import retrofit2.Response;

/* JADX INFO: loaded from: classes5.dex */
public class FileDownloadService extends IntentService {
    public static final String PARAMS_KEY_ERROR = "error";
    public static final String PARAMS_KEY_MD5 = "md5";
    public static final String PARAMS_KEY_PATH = "path";
    public static final String PARAMS_KEY_PROGRESS = "progress";
    public static final String PARAMS_KEY_URL = "url";
    private static final String TAG = "FileDownloadService";
    private String channelId;
    private NotificationCompat.Builder mBuilder;
    private NotificationManager mNotifyManager;
    private CharSequence name;

    public FileDownloadService() {
        super(TAG);
        this.channelId = "foreground_id";
        this.name = "foreground_name";
    }

    @Override // android.app.IntentService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.mNotifyManager = (NotificationManager) getSystemService("notification");
        this.mBuilder = new NotificationCompat.Builder(this, this.channelId);
        if (Build.VERSION.SDK_INT >= 26) {
            App$$ExternalSyntheticApiModelOutline0.m$1();
            NotificationChannel notificationChannelM = App$$ExternalSyntheticApiModelOutline0.m(this.channelId, this.name, 3);
            notificationChannelM.enableVibration(false);
            this.mBuilder.setChannelId(this.channelId);
            NotificationManager notificationManager = this.mNotifyManager;
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannelM);
            }
        }
        this.mBuilder.setContentTitle("MovieBoxPro").setSmallIcon(R.mipmap.ic_logo).setAutoCancel(false);
    }

    @Override // android.app.IntentService
    protected void onHandleIntent(Intent intent) throws Throwable {
        String string;
        String string2;
        String string3;
        if (intent != null) {
            if (Constant.ACTION.DOWNLOAD_FILE.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    string2 = extras.getString("url", null);
                    string3 = extras.getString(PARAMS_KEY_PATH, null);
                    string = extras.getString(PARAMS_KEY_MD5, null);
                } else {
                    string = null;
                    string2 = null;
                    string3 = null;
                }
                if (!TextUtils.isEmpty(string2) && !TextUtils.isEmpty(string3)) {
                    try {
                        doDownload(string3, string2, string);
                        return;
                    } catch (IOException e) {
                        onDownloadFailure(null, e.getLocalizedMessage());
                        return;
                    }
                }
                onDownloadFailure(null, "Success");
                return;
            }
            onDownloadFailure(null, "Fail");
            return;
        }
        onDownloadFailure(null, "Fail");
    }

    private void doDownload(String path, String url, String md5) throws Throwable {
        onDownloadStarted(path);
        Response<ResponseBody> responseExecute = HttpHelper.getApiService().downloadCall(url).execute();
        if (responseExecute.isSuccessful()) {
            SaveResult saveResultSaveResponseBody = saveResponseBody(path, responseExecute.body(), md5);
            if (saveResultSaveResponseBody.isSuccess()) {
                onDownloadSuccess(path);
                return;
            } else {
                onDownloadFailure(path, saveResultSaveResponseBody.getError());
                return;
            }
        }
        MLog.d(TAG, "doDownload: error code: " + responseExecute.code());
        onDownloadFailure(path, responseExecute.code() + "");
    }

    /* JADX WARN: Code duplicated, block: B:111:? A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0134 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x0129 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0 */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARN: Type inference failed for: r7v11, types: [long] */
    /* JADX WARN: Type inference failed for: r7v12 */
    /* JADX WARN: Type inference failed for: r7v13 */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v15 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v17 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v19 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v20 */
    /* JADX WARN: Type inference failed for: r7v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v13, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v14, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v20 */
    /* JADX WARN: Type inference failed for: r9v21 */
    /* JADX WARN: Type inference failed for: r9v22 */
    /* JADX WARN: Type inference failed for: r9v23 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v26 */
    /* JADX WARN: Type inference failed for: r9v27 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    private SaveResult saveResponseBody(String str, ResponseBody responseBody, String str2) throws Throwable {
        Throwable th;
        ?? r9;
        String str3;
        FileOutputStream fileOutputStream;
        ?? r10;
        ?? r11;
        String str4 = TAG;
        MLog.d(TAG, "saveResponseBody: " + str);
        int i = 0;
        ?? ContentLength = 0;
        ContentLength = 0;
        ContentLength = 0;
        ContentLength = 0;
        ?? r7 = 0;
        ?? r8 = 0;
        SaveResult saveResult = new SaveResult(false, null);
        File file = new File(str);
        boolean zExists = file.exists();
        ?? ByteStream = zExists;
        if (zExists) {
            boolean zIsFile = file.isFile();
            ByteStream = zIsFile;
            if (zIsFile) {
                file.delete();
                ByteStream = zIsFile;
            }
        }
        try {
            try {
                ByteStream = responseBody.byteStream();
                try {
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            try {
                                ContentLength = responseBody.contentLength();
                                byte[] bArr = new byte[4096];
                                onDownloadProgress(str, 0);
                                int i2 = 0;
                                int i3 = 0;
                                try {
                                    while (true) {
                                        int i4 = ByteStream.read(bArr);
                                        if (i4 == -1) {
                                            break;
                                        }
                                        fileOutputStream.write(bArr, i, i4);
                                        i2 += i4;
                                        if (i3 % 200 == 0) {
                                            str3 = str4;
                                            try {
                                                onDownloadProgress(str, (int) (((((double) i2) * 1.0d) / ((double) ContentLength)) * 100.0d));
                                            } catch (FileNotFoundException e) {
                                                e = e;
                                                str4 = str3;
                                            } catch (IOException e2) {
                                                e = e2;
                                                r7 = ByteStream;
                                                r11 = ByteStream;
                                                try {
                                                    MLog.d(str3, "saveResponseBody: " + e.getLocalizedMessage());
                                                    saveResult.setSuccess(false);
                                                    saveResult.setError("File save failed");
                                                    if (fileOutputStream != null) {
                                                        try {
                                                            fileOutputStream.close();
                                                        } catch (IOException e3) {
                                                            e3.printStackTrace();
                                                        }
                                                    }
                                                    if (r7 != 0) {
                                                        r7.close();
                                                        ContentLength = r7;
                                                        ByteStream = r11;
                                                    }
                                                    return saveResult;
                                                } catch (Throwable th2) {
                                                    th = th2;
                                                    r10 = r7;
                                                    r8 = fileOutputStream;
                                                    r9 = r10;
                                                    if (r8 != 0) {
                                                        try {
                                                            r8.close();
                                                        } catch (IOException e4) {
                                                            e4.printStackTrace();
                                                        }
                                                    }
                                                    if (r9 != 0) {
                                                        throw th;
                                                    }
                                                    try {
                                                        r9.close();
                                                        throw th;
                                                    } catch (IOException e5) {
                                                        e5.printStackTrace();
                                                        throw th;
                                                    }
                                                }
                                            }
                                        } else {
                                            str3 = str4;
                                        }
                                        i3++;
                                        str4 = str3;
                                        i = 0;
                                        ContentLength = fileOutputStream;
                                        ByteStream = ByteStream;
                                        MLog.d(str4, "saveResponseBody: " + e.getLocalizedMessage());
                                        saveResult.setSuccess(false);
                                        saveResult.setError("File save failed");
                                        if (ContentLength != 0) {
                                            try {
                                                ContentLength.close();
                                            } catch (IOException e6) {
                                                e6.printStackTrace();
                                            }
                                        }
                                        if (ByteStream != 0) {
                                            ByteStream.close();
                                            ContentLength = ContentLength;
                                            ByteStream = ByteStream;
                                        }
                                        return saveResult;
                                    }
                                    fileOutputStream.close();
                                } catch (IOException e7) {
                                    e7.printStackTrace();
                                }
                                str3 = str4;
                                fileOutputStream.flush();
                                onDownloadProgress(str, 100);
                                saveResult.setSuccess(true);
                                if (ByteStream != 0) {
                                    ByteStream.close();
                                    ContentLength = ContentLength;
                                    ByteStream = ByteStream;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                r10 = ByteStream;
                                r8 = fileOutputStream;
                                r9 = r10;
                                if (r8 != 0) {
                                    r8.close();
                                }
                                if (r9 != 0) {
                                    throw th;
                                }
                                r9.close();
                                throw th;
                            }
                        } catch (FileNotFoundException e8) {
                            e = e8;
                        } catch (IOException e9) {
                            e = e9;
                            str3 = str4;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        r8 = ContentLength;
                        r9 = ByteStream;
                        if (r8 != 0) {
                            r8.close();
                        }
                        if (r9 != 0) {
                            throw th;
                        }
                        r9.close();
                        throw th;
                    }
                } catch (FileNotFoundException e10) {
                    e = e10;
                    ByteStream = ByteStream;
                } catch (IOException e11) {
                    e = e11;
                    str3 = TAG;
                    fileOutputStream = null;
                }
            } catch (IOException e12) {
                e12.printStackTrace();
            }
        } catch (FileNotFoundException e13) {
            e = e13;
            ByteStream = 0;
        } catch (IOException e14) {
            e = e14;
            str3 = TAG;
            fileOutputStream = null;
            r11 = ByteStream;
        } catch (Throwable th5) {
            th = th5;
            r9 = 0;
            if (r8 != 0) {
                r8.close();
            }
            if (r9 != 0) {
                throw th;
            }
            r9.close();
            throw th;
        }
        return saveResult;
    }

    private void onDownloadStarted(String path) {
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_FILE_STARTED);
        intent.putExtra("progress", 0);
        intent.putExtra(PARAMS_KEY_PATH, path);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void onDownloadProgress(String path, int progress) {
        MLog.d(TAG, "onDownloadProgress: " + progress);
        this.mBuilder.setContentText("Downloading " + progress + "%");
        this.mBuilder.setProgress(100, progress, false);
        this.mNotifyManager.notify(2000, this.mBuilder.build());
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_FILE_PROGRESS);
        intent.putExtra("progress", progress);
        intent.putExtra(PARAMS_KEY_PATH, path);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void onDownloadSuccess(String path) {
        this.mBuilder.setContentText("Download Success");
        File file = new File(path);
        if ("apk".equalsIgnoreCase(FilesKt.getExtension(file))) {
            startActivity(IntentUtils.getInstallAppIntent(file, true));
        }
        this.mNotifyManager.notify(2000, this.mBuilder.build());
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_FILE_COMPLETE);
        intent.putExtra("progress", 100);
        intent.putExtra(PARAMS_KEY_PATH, path);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private void onDownloadFailure(String path, String error) {
        this.mBuilder.setContentText("Download Error:" + error);
        this.mNotifyManager.notify(2000, this.mBuilder.build());
        Intent intent = new Intent(Constant.ACTION.DOWNLOAD_FILE_FAILURE);
        intent.putExtra("progress", 0);
        intent.putExtra(PARAMS_KEY_PATH, path);
        intent.putExtra("error", error);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private static class SaveResult {
        private String error;
        private boolean success;

        SaveResult(boolean success, String error) {
            this.success = success;
            this.error = error;
        }

        public boolean isSuccess() {
            return this.success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getError() {
            return this.error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
