package com.movieboxpro.android.view.widget;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.service.FileDownloadService;
import com.movieboxpro.android.utils.MLog;
import com.movieboxpro.android.utils.ParamsUtils;
import com.movieboxpro.android.utils.ToastUtils;
import com.movieboxpro.androidtv.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FileDownloader.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001:\u000201B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020\u0005J\u0018\u0010'\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0005H\u0002J\u0010\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020\u0007H\u0002J\b\u0010*\u001a\u00020#H\u0002J\u0010\u0010+\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0010\u0010,\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002J\u0018\u0010-\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0012\u001a\u00020\u0005H\u0002J\u0018\u0010.\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010/\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\r\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001c\u0010\u0012\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0018\u00010\u001bR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!¨\u00062"}, d2 = {"Lcom/movieboxpro/android/view/widget/FileDownloader;", "", "<init>", "()V", "tag", "", "id", "", "getId", "()Ljava/lang/Integer;", "setId", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "url", "getUrl", "()Ljava/lang/String;", "setUrl", "(Ljava/lang/String;)V", FileDownloadService.PARAMS_KEY_PATH, "getPath", "setPath", FileDownloadService.PARAMS_KEY_MD5, "getMd5", "setMd5", "mDialog", "Landroid/app/ProgressDialog;", "mReceiver", "Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadReceiver;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadListener;", "getListener", "()Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadListener;", "setListener", "(Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadListener;)V", "download", "", "context", "Landroid/content/Context;", TvContractCompat.ProgramColumns.COLUMN_TITLE, "showProgress", "updateProgress", "progress", "hideProgress", "registerReceiver", "unregisterReceiver", "onDownloadSuccess", "onDownloadFailure", "error", "DownloadReceiver", "DownloadListener", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class FileDownloader {
    private Integer id;
    private DownloadListener listener;
    private ProgressDialog mDialog;
    private DownloadReceiver mReceiver;
    private String md5;
    private String path;
    private final String tag = "FileDownloader";
    private String url;

    public final Integer getId() {
        return this.id;
    }

    public final void setId(Integer num) {
        this.id = num;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        this.url = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        this.path = str;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        this.md5 = str;
    }

    public final DownloadListener getListener() {
        return this.listener;
    }

    public final void setListener(DownloadListener downloadListener) {
        this.listener = downloadListener;
    }

    public static /* synthetic */ void download$default(FileDownloader fileDownloader, Context context, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = "DOWNLOADING";
        }
        fileDownloader.download(context, str);
    }

    public final void download(Context context, String title) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(title, "title");
        if (this.url == null) {
            throw new RuntimeException("url not specified");
        }
        if (this.path == null) {
            throw new RuntimeException("path not specified");
        }
        Intent intent = new Intent(context, (Class<?>) FileDownloadService.class);
        intent.setAction(Constant.ACTION.DOWNLOAD_FILE);
        intent.putExtras(ParamsUtils.newBuilder().addParam(FileDownloadService.PARAMS_KEY_PATH, this.path).addParam("url", this.url).addParam(FileDownloadService.PARAMS_KEY_MD5, this.md5).build());
        context.startService(intent);
        showProgress(context, title);
        registerReceiver(context);
        DownloadListener downloadListener = this.listener;
        if (downloadListener != null) {
            Integer num = this.id;
            Intrinsics.checkNotNull(num);
            downloadListener.onStarted(num.intValue());
        }
    }

    private final void showProgress(final Context context, String title) {
        ProgressDialog progressDialog = this.mDialog;
        if (progressDialog != null) {
            Intrinsics.checkNotNull(progressDialog);
            if (progressDialog.isShowing()) {
                return;
            }
        }
        ProgressDialog progressDialog2 = new ProgressDialog(context, R.style.AppTheme_DialogStyle);
        this.mDialog = progressDialog2;
        Intrinsics.checkNotNull(progressDialog2);
        progressDialog2.setMax(100);
        ProgressDialog progressDialog3 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog3);
        progressDialog3.setIndeterminate(false);
        ProgressDialog progressDialog4 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog4);
        progressDialog4.setTitle(title);
        ProgressDialog progressDialog5 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog5);
        progressDialog5.setProgressStyle(1);
        ProgressDialog progressDialog6 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog6);
        progressDialog6.setCancelable(false);
        ProgressDialog progressDialog7 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog7);
        progressDialog7.setCanceledOnTouchOutside(false);
        ProgressDialog progressDialog8 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog8);
        progressDialog8.setProgressNumberFormat("");
        ProgressDialog progressDialog9 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog9);
        progressDialog9.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.movieboxpro.android.view.widget.FileDownloader$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.unregisterReceiver(context);
            }
        });
        ProgressDialog progressDialog10 = this.mDialog;
        Intrinsics.checkNotNull(progressDialog10);
        progressDialog10.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateProgress(int progress) {
        MLog.d(this.tag, "updateProgress: " + progress);
        ProgressDialog progressDialog = this.mDialog;
        if (progressDialog != null) {
            progressDialog.setProgress(progress);
        }
    }

    private final void hideProgress() {
        ProgressDialog progressDialog = this.mDialog;
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        this.mDialog = null;
    }

    private final void registerReceiver(Context context) {
        if (this.mReceiver != null) {
            return;
        }
        this.mReceiver = new DownloadReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_FILE_COMPLETE);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_FILE_STARTED);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_FILE_PROGRESS);
        intentFilter.addAction(Constant.ACTION.DOWNLOAD_FILE_FAILURE);
        DownloadReceiver downloadReceiver = this.mReceiver;
        Intrinsics.checkNotNull(downloadReceiver, "null cannot be cast to non-null type com.movieboxpro.android.view.widget.FileDownloader.DownloadReceiver");
        LocalBroadcastManager.getInstance(context).registerReceiver(downloadReceiver, intentFilter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void unregisterReceiver(Context context) {
        DownloadReceiver downloadReceiver = this.mReceiver;
        if (downloadReceiver != null) {
            Intrinsics.checkNotNull(downloadReceiver, "null cannot be cast to non-null type com.movieboxpro.android.view.widget.FileDownloader.DownloadReceiver");
            LocalBroadcastManager.getInstance(context).unregisterReceiver(downloadReceiver);
        }
        this.mReceiver = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDownloadSuccess(Context context, String path) {
        hideProgress();
        unregisterReceiver(context);
        DownloadListener downloadListener = this.listener;
        if (downloadListener != null) {
            Integer num = this.id;
            Intrinsics.checkNotNull(num);
            downloadListener.onSuccess(num.intValue(), path);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onDownloadFailure(Context context, String error) {
        hideProgress();
        unregisterReceiver(context);
        ToastUtils.showShort(error, new Object[0]);
        DownloadListener downloadListener = this.listener;
        if (downloadListener != null) {
            Integer num = this.id;
            Intrinsics.checkNotNull(num);
            downloadListener.onFailure(num.intValue(), error);
        }
    }

    /* JADX INFO: compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadReceiver;", "Landroid/content/BroadcastReceiver;", "<init>", "(Lcom/movieboxpro/android/view/widget/FileDownloader;)V", "tag", "", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private final class DownloadReceiver extends BroadcastReceiver {
        private final String tag = "DownloadReceiver";

        public DownloadReceiver() {
        }

        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            Bundle extras;
            Bundle extras2;
            Bundle extras3;
            Intrinsics.checkNotNullParameter(context, "context");
            Integer numValueOf = null;
            string = null;
            String string = null;
            string = null;
            String string2 = null;
            numValueOf = null;
            String action = intent != null ? intent.getAction() : null;
            if (action != null) {
                switch (action.hashCode()) {
                    case -217429761:
                        if (action.equals(Constant.ACTION.DOWNLOAD_FILE_PROGRESS)) {
                            if (intent != null && (extras = intent.getExtras()) != null) {
                                numValueOf = Integer.valueOf(extras.getInt("progress"));
                            }
                            FileDownloader.this.updateProgress(numValueOf != null ? numValueOf.intValue() : 0);
                            break;
                        }
                        break;
                    case 184203275:
                        if (action.equals(Constant.ACTION.DOWNLOAD_FILE_COMPLETE)) {
                            if (intent != null && (extras2 = intent.getExtras()) != null) {
                                string2 = extras2.getString(FileDownloadService.PARAMS_KEY_PATH);
                            }
                            FileDownloader.this.onDownloadSuccess(context, string2 != null ? string2 : "");
                            break;
                        }
                        break;
                    case 483398479:
                        if (action.equals(Constant.ACTION.DOWNLOAD_FILE_STARTED)) {
                            MLog.d(this.tag, "START");
                        }
                        break;
                    case 1294009432:
                        if (action.equals(Constant.ACTION.DOWNLOAD_FILE_FAILURE)) {
                            if (intent != null && (extras3 = intent.getExtras()) != null) {
                                string = extras3.getString("error");
                            }
                            FileDownloader.this.onDownloadFailure(context, string != null ? string : "");
                            break;
                        }
                        break;
                }
            }
        }
    }

    /* JADX INFO: compiled from: FileDownloader.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\nH\u0016¨\u0006\rÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/view/widget/FileDownloader$DownloadListener;", "", "onStarted", "", "id", "", "onProgress", "progress", "onSuccess", FileDownloadService.PARAMS_KEY_PATH, "", "onFailure", "error", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface DownloadListener {
        void onFailure(int id, String error);

        void onProgress(int id, int progress);

        void onStarted(int id);

        void onSuccess(int id, String path);

        /* JADX INFO: renamed from: com.movieboxpro.android.view.widget.FileDownloader$DownloadListener$-CC, reason: invalid class name */
        /* JADX INFO: compiled from: FileDownloader.kt */
        public final /* synthetic */ class CC {
            public static void $default$onProgress(DownloadListener _this, int i, int i2) {
            }

            public static void $default$onStarted(DownloadListener _this, int i) {
            }
        }

        /* JADX INFO: compiled from: FileDownloader.kt */
        @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
        public static final class DefaultImpls {
            @Deprecated
            public static void onStarted(DownloadListener downloadListener, int i) {
                CC.$default$onStarted(downloadListener, i);
            }

            @Deprecated
            public static void onProgress(DownloadListener downloadListener, int i, int i2) {
                CC.$default$onProgress(downloadListener, i, i2);
            }

            @Deprecated
            public static void onSuccess(DownloadListener downloadListener, int i, String path) {
                Intrinsics.checkNotNullParameter(path, "path");
                Intrinsics.checkNotNullParameter(path, "path");
            }

            @Deprecated
            public static void onFailure(DownloadListener downloadListener, int i, String error) {
                Intrinsics.checkNotNullParameter(error, "error");
                Intrinsics.checkNotNullParameter(error, "error");
            }
        }
    }
}
