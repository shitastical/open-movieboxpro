package com.movieboxpro.android.view.widget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import com.adorkable.iosdialog.AlertDialog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.config.ConfigKey;
import com.movieboxpro.android.config.ConfigUtils;
import com.movieboxpro.android.model.common.UpdateInfo;
import com.movieboxpro.android.utils.SystemUtils;
import com.movieboxpro.android.view.dialog.DialogAction;
import com.movieboxpro.android.view.dialog.UpdateSheetDialog;
import java.io.File;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: UpdateChecker.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0014H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/movieboxpro/android/view/widget/UpdateChecker;", "", "<init>", "()V", "id", "", "isUpdateShowing", "", "dialog", "Lcom/adorkable/iosdialog/AlertDialog;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "com/movieboxpro/android/view/widget/UpdateChecker$listener$1", "Lcom/movieboxpro/android/view/widget/UpdateChecker$listener$1;", "checkUpdate", "", "context", "Landroid/content/Context;", "checkWebUpdate", "showUpdateDialog2", "updateInfo", "Lcom/movieboxpro/android/model/common/UpdateInfo;", "checkDownloadedAPk", "showUpdateDialog", "downloadApk", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class UpdateChecker {
    private static AlertDialog dialog = null;
    private static final int id = 1000;
    private static boolean isUpdateShowing;
    public static final UpdateChecker INSTANCE = new UpdateChecker();
    private static final UpdateChecker$listener$1 listener = new FileDownloader.DownloadListener() { // from class: com.movieboxpro.android.view.widget.UpdateChecker$listener$1
        @Override // com.movieboxpro.android.view.widget.FileDownloader.DownloadListener
        public void onFailure(int i, String str) {
            Intrinsics.checkNotNullParameter(str, "error");
        }

        @Override // com.movieboxpro.android.view.widget.FileDownloader.DownloadListener
        public void onProgress(int i, int i2) {
            FileDownloader.DownloadListener.CC.$default$onProgress(this, i, i2);
        }

        @Override // com.movieboxpro.android.view.widget.FileDownloader.DownloadListener
        public void onStarted(int i) {
            FileDownloader.DownloadListener.CC.$default$onStarted(this, i);
        }

        @Override // com.movieboxpro.android.view.widget.FileDownloader.DownloadListener
        public void onSuccess(int id2, String path) {
            Intrinsics.checkNotNullParameter(path, "path");
            File file = new File(path);
            String extension = FilesKt.getExtension(file);
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String lowerCase = extension.toLowerCase(locale);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            if (Intrinsics.areEqual(lowerCase, "apk")) {
                SystemUtils.installAPK(file);
            }
        }
    };

    private UpdateChecker() {
    }

    public final void checkUpdate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (ConfigUtils.containsConfig(ConfigKey.APK_LATEST_VERSION) && ConfigUtils.containsConfig(ConfigKey.APK_VERSION) && ConfigUtils.readIntegerConfig(ConfigKey.APK_LATEST_VERSION, App.versionCode) > App.versionCode) {
            UpdateInfo updateInfo = new UpdateInfo();
            updateInfo.title = ConfigUtils.readStringConfig(ConfigKey.APK_TITLE);
            updateInfo.version = ConfigUtils.readStringConfig(ConfigKey.APK_VERSION);
            updateInfo.apkSize = ConfigUtils.readStringConfig(ConfigKey.APK_SIZE);
            updateInfo.updateTime = ConfigUtils.readStringConfig(ConfigKey.APK_TIME);
            updateInfo.apkUrl = ConfigUtils.readStringConfig(ConfigKey.APK_URL);
            updateInfo.md5 = ConfigUtils.readStringConfig(ConfigKey.APK_MD5);
            updateInfo.description = ConfigUtils.readStringConfig(ConfigKey.APK_INFO);
            updateInfo.apkPath = Constant.DIR_DOWNLOAD + "MovieBoxPro-v" + updateInfo.version + ".apk";
            showUpdateDialog2(context, updateInfo);
        }
    }

    public final void checkWebUpdate(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (ConfigUtils.containsConfig(ConfigKey.WEB_APK_LATEST_VERSION) && ConfigUtils.containsConfig(ConfigKey.APK_VERSION) && ConfigUtils.readIntegerConfig(ConfigKey.WEB_APK_LATEST_VERSION, App.versionCode) > App.versionCode) {
            UpdateInfo updateInfo = new UpdateInfo();
            updateInfo.title = ConfigUtils.readStringConfig(ConfigKey.APK_TITLE);
            updateInfo.version = ConfigUtils.readStringConfig(ConfigKey.APK_VERSION);
            updateInfo.apkSize = ConfigUtils.readStringConfig(ConfigKey.APK_SIZE);
            updateInfo.updateTime = ConfigUtils.readStringConfig(ConfigKey.APK_TIME);
            updateInfo.apkUrl = ConfigUtils.readStringConfig(ConfigKey.APK_URL);
            updateInfo.md5 = ConfigUtils.readStringConfig(ConfigKey.APK_MD5);
            updateInfo.description = ConfigUtils.readStringConfig(ConfigKey.APK_INFO);
            updateInfo.apkPath = Constant.DIR_DOWNLOAD + "MovieBoxPro-v" + updateInfo.version + ".apk";
            showUpdateDialog2(context, updateInfo);
        }
    }

    private final void showUpdateDialog2(final Context context, final UpdateInfo updateInfo) {
        if (isUpdateShowing) {
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("Version：%s, Size：%s, Update Time：%s\nDescription：\n%s", Arrays.copyOf(new Object[]{updateInfo.version, updateInfo.apkSize, updateInfo.updateTime, updateInfo.description}, 4));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        AlertDialog cancelable = new AlertDialog(context).builder().setTitle(updateInfo.title).setMsg(str).setNegativeButton(checkDownloadedAPk(updateInfo) ? "Immediate Installation" : "Download Now", new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.UpdateChecker$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpdateChecker.showUpdateDialog2$lambda$0(context, updateInfo, view);
            }
        }).setPositiveButton("Next Time", new View.OnClickListener() { // from class: com.movieboxpro.android.view.widget.UpdateChecker$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UpdateChecker.isUpdateShowing = false;
            }
        }).setCancelable(false);
        dialog = cancelable;
        if (cancelable != null) {
            cancelable.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showUpdateDialog2$lambda$0(Context context, UpdateInfo updateInfo, View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            if (context.getPackageManager().canRequestPackageInstalls()) {
                UpdateChecker updateChecker = INSTANCE;
                isUpdateShowing = false;
                if (updateChecker.checkDownloadedAPk(updateInfo)) {
                    SystemUtils.installAPK(context, new File(updateInfo.apkPath));
                } else {
                    updateChecker.downloadApk(context, updateInfo);
                }
                AlertDialog alertDialog = dialog;
                if (alertDialog != null) {
                    alertDialog.dismiss();
                    return;
                }
                return;
            }
            try {
                Uri uri = Uri.parse("package:" + context.getPackageName());
                Intrinsics.checkNotNullExpressionValue(uri, "parse(...)");
                context.startActivity(new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", uri));
                Unit unit = Unit.INSTANCE;
                return;
            } catch (Exception unused) {
                UpdateChecker updateChecker2 = INSTANCE;
                isUpdateShowing = false;
                if (updateChecker2.checkDownloadedAPk(updateInfo)) {
                    SystemUtils.installAPK(context, new File(updateInfo.apkPath));
                } else {
                    updateChecker2.downloadApk(context, updateInfo);
                }
                AlertDialog alertDialog2 = dialog;
                if (alertDialog2 != null) {
                    alertDialog2.dismiss();
                    Unit unit2 = Unit.INSTANCE;
                    return;
                }
                return;
            }
        }
        UpdateChecker updateChecker3 = INSTANCE;
        isUpdateShowing = false;
        if (updateChecker3.checkDownloadedAPk(updateInfo)) {
            SystemUtils.installAPK(context, new File(updateInfo.apkPath));
        } else {
            updateChecker3.downloadApk(context, updateInfo);
        }
        AlertDialog alertDialog3 = dialog;
        if (alertDialog3 != null) {
            alertDialog3.dismiss();
        }
    }

    private final boolean checkDownloadedAPk(UpdateInfo updateInfo) {
        File file = new File(updateInfo.apkPath);
        if (!file.exists() || file.isDirectory()) {
            return false;
        }
        file.delete();
        return false;
    }

    private final void showUpdateDialog(final Context context, final UpdateInfo updateInfo) {
        if (isUpdateShowing) {
            return;
        }
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String str = String.format("Version：%s<br>Size：%s<br>Time：%s<br><br>Introduce：<br><br>%s", Arrays.copyOf(new Object[]{updateInfo.version, updateInfo.apkSize, updateInfo.updateTime, updateInfo.description}, 4));
        Intrinsics.checkNotNullExpressionValue(str, "format(...)");
        new UpdateSheetDialog.Builder(context).setTitle(updateInfo.title).setContent(str).setPositiveText(checkDownloadedAPk(updateInfo) ? "Immediate Installation" : "Downlaod Now").setActionListener(new DialogAction.ActionListener() { // from class: com.movieboxpro.android.view.widget.UpdateChecker$$ExternalSyntheticLambda3
            @Override // com.movieboxpro.android.view.dialog.DialogAction.ActionListener
            public final void onClick() {
                UpdateChecker.showUpdateDialog$lambda$2(updateInfo, context);
            }
        }).create().show();
        isUpdateShowing = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void showUpdateDialog$lambda$2(UpdateInfo updateInfo, Context context) {
        UpdateChecker updateChecker = INSTANCE;
        isUpdateShowing = false;
        if (updateChecker.checkDownloadedAPk(updateInfo)) {
            SystemUtils.installAPK(context, new File(updateInfo.apkPath));
        } else {
            updateChecker.downloadApk(context, updateInfo);
        }
    }

    private final void downloadApk(Context context, UpdateInfo updateInfo) {
        FileDownloader fileDownloader = new FileDownloader();
        fileDownloader.setId(1000);
        fileDownloader.setUrl(updateInfo.apkUrl);
        fileDownloader.setPath(updateInfo.apkPath);
        fileDownloader.setMd5(updateInfo.md5);
        fileDownloader.setListener(listener);
        fileDownloader.download(context, "DOWNLOADING");
    }
}
