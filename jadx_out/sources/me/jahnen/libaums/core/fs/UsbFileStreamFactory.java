package me.jahnen.libaums.core.fs;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: compiled from: UsbFileStreamFactory.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\u000b"}, d2 = {"Lme/jahnen/libaums/core/fs/UsbFileStreamFactory;", "", "()V", "createBufferedInputStream", "Ljava/io/BufferedInputStream;", IDataSource.SCHEME_FILE_TAG, "Lme/jahnen/libaums/core/fs/UsbFile;", "fs", "Lme/jahnen/libaums/core/fs/FileSystem;", "createBufferedOutputStream", "Ljava/io/BufferedOutputStream;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UsbFileStreamFactory {
    public static final UsbFileStreamFactory INSTANCE = new UsbFileStreamFactory();

    private UsbFileStreamFactory() {
    }

    @JvmStatic
    public static final BufferedOutputStream createBufferedOutputStream(UsbFile file, FileSystem fs) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(fs, "fs");
        return new BufferedOutputStream(new UsbFileOutputStream(file, false, 2, null), fs.getChunkSize());
    }

    @JvmStatic
    public static final BufferedInputStream createBufferedInputStream(UsbFile file, FileSystem fs) {
        Intrinsics.checkNotNullParameter(file, "file");
        Intrinsics.checkNotNullParameter(fs, "fs");
        return new BufferedInputStream(new UsbFileInputStream(file), fs.getChunkSize());
    }
}
