package com.movieboxpro.android.utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;
import java.io.File;

/* JADX INFO: loaded from: classes5.dex */
public class IntentUtils {
    public static Intent getInstallAppIntent(final String filePath) {
        return getInstallAppIntent(getFileByPath(filePath), false);
    }

    public static Intent getInstallAppIntent(final File file) {
        return getInstallAppIntent(file, false);
    }

    public static Intent getInstallAppIntent(final String filePath, final boolean isNewTask) {
        return getInstallAppIntent(getFileByPath(filePath), isNewTask);
    }

    public static Intent getInstallAppIntent(final File file, final boolean isNewTask) {
        Uri uriForFile;
        if (file == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uriForFile = Uri.fromFile(file);
        } else {
            intent.setFlags(1);
            uriForFile = FileProvider.getUriForFile(Utils.getApp(), "com.movieboxpro.androidtv.fileProvider", file);
        }
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        return getIntent(intent, isNewTask);
    }

    private static File getFileByPath(final String filePath) {
        if (isSpace(filePath)) {
            return null;
        }
        return new File(filePath);
    }

    private static Intent getIntent(final Intent intent, final boolean isNewTask) {
        return isNewTask ? intent.addFlags(268435456) : intent;
    }

    private static boolean isSpace(final String s) {
        if (s == null) {
            return true;
        }
        int length = s.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
