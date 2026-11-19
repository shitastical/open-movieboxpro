package me.jahnen.libaums.core.fs;

import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.jeffmony.downloader.model.Video;
import com.movieboxpro.android.service.FileDownloadService;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* JADX INFO: compiled from: AbstractUsbFile.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b&\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\u0013\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0002J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0010\u001a\u00020\u0004H\u0002J\b\u0010\u0011\u001a\u00020\u0004H\u0016R\u0014\u0010\u0003\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0013"}, d2 = {"Lme/jahnen/libaums/core/fs/AbstractUsbFile;", "Lme/jahnen/libaums/core/fs/UsbFile;", "()V", "absolutePath", "", "getAbsolutePath", "()Ljava/lang/String;", "equals", "", Video.TypeInfo.OTHER, "", "hashCode", "", FirebaseAnalytics.Event.SEARCH, FileDownloadService.PARAMS_KEY_PATH, "searchThis", "name", "toString", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class AbstractUsbFile implements UsbFile {
    private static final String TAG = "AbstractUsbFile";

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public String getAbsolutePath() {
        String str;
        if (isRoot()) {
            return "/";
        }
        UsbFile parent = getParent();
        if (parent == null) {
            str = null;
        } else if (parent.isRoot()) {
            str = "/" + getName();
        } else {
            str = parent.getAbsolutePath() + '/' + getName();
        }
        return str == null ? "" : str;
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public UsbFile search(String path) throws IOException {
        Intrinsics.checkNotNullParameter(path, "path");
        if (!isDirectory()) {
            throw new UnsupportedOperationException("This is a file!");
        }
        String str = TAG;
        Log.d(str, "search file: " + path);
        if (isRoot() && Intrinsics.areEqual(path, "/")) {
            return this;
        }
        if (isRoot() && StringsKt.startsWith$default(path, "/", false, 2, (Object) null)) {
            path = path.substring(1);
            Intrinsics.checkNotNullExpressionValue(path, "this as java.lang.String).substring(startIndex)");
        }
        if (StringsKt.endsWith$default(path, "/", false, 2, (Object) null)) {
            path = path.substring(0, path.length() - 1);
            Intrinsics.checkNotNullExpressionValue(path, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        int iIndexOf$default = StringsKt.indexOf$default((CharSequence) path, "/", 0, false, 6, (Object) null);
        if (iIndexOf$default < 0) {
            Log.d(str, "search entry: " + path);
            return searchThis(path);
        }
        String strSubstring = path.substring(iIndexOf$default + 1);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
        String strSubstring2 = path.substring(0, iIndexOf$default);
        Intrinsics.checkNotNullExpressionValue(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
        Log.d(str, "search recursively " + strSubstring + " in " + strSubstring2);
        UsbFile usbFileSearchThis = searchThis(strSubstring2);
        if (usbFileSearchThis != null && usbFileSearchThis.isDirectory()) {
            Log.d(str, "found directory " + strSubstring2);
            return usbFileSearchThis.search(strSubstring);
        }
        Log.d(str, "not found " + path);
        return null;
    }

    private final UsbFile searchThis(String name) throws IOException {
        for (UsbFile usbFile : listFiles()) {
            if (Intrinsics.areEqual(usbFile.getName(), name)) {
                return usbFile;
            }
        }
        return null;
    }

    public int hashCode() {
        return getAbsolutePath().hashCode();
    }

    public String toString() {
        return getName();
    }

    public boolean equals(Object other) {
        return (other instanceof UsbFile) && Intrinsics.areEqual(getAbsolutePath(), ((UsbFile) other).getAbsolutePath());
    }
}
