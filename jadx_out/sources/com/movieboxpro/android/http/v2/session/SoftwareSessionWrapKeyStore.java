package com.movieboxpro.android.http.v2.session;

import java.io.File;
import java.io.FileOutputStream;
import java.security.SecureRandom;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.io.CloseableKt;
import kotlin.io.FilesKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SoftwareSessionWrapKeyStore.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0003\b\u0000\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\b\u001a\u00020\u0007R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SoftwareSessionWrapKeyStore;", "", "directory", "Ljava/io/File;", "<init>", "(Ljava/io/File;)V", "read", "", "loadOrCreate", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SoftwareSessionWrapKeyStore {
    private static final Object KEY_FILE_LOCK = new Object();
    private static final String KEY_FILE_NAME = "api_v2_session_wrap.key";
    public static final int KEY_SIZE_BYTES = 32;
    private final File directory;

    public SoftwareSessionWrapKeyStore(File directory) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        this.directory = directory;
    }

    public final byte[] read() {
        byte[] bytes;
        synchronized (KEY_FILE_LOCK) {
            bytes = FilesKt.readBytes(new File(this.directory, KEY_FILE_NAME));
            if (bytes.length != 32) {
                ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                throw new IllegalStateException("Invalid software session wrap key file");
            }
        }
        return bytes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r8v2, types: [java.io.File] */
    /* JADX WARN: Type inference failed for: r8v3 */
    public final byte[] loadOrCreate() {
        ?? file;
        byte[] bArr;
        FileOutputStream fileOutputStream;
        byte[] bytes;
        synchronized (KEY_FILE_LOCK) {
            File file2 = new File(this.directory, KEY_FILE_NAME);
            try {
                try {
                    try {
                        if (file2.exists()) {
                            bytes = FilesKt.readBytes(file2);
                            if (bytes.length != 32) {
                                file = 6;
                                ArraysKt.fill$default(bytes, (byte) 0, 0, 0, 6, (Object) null);
                            }
                        }
                        FileOutputStream fileOutputStream2 = fileOutputStream;
                        fileOutputStream2.write(bArr);
                        fileOutputStream2.getFD().sync();
                        Unit unit = Unit.INSTANCE;
                        CloseableKt.closeFinally(fileOutputStream, null);
                        if (!file.renameTo(file2)) {
                            throw new IllegalStateException("Cannot persist software session wrap key".toString());
                        }
                        file.delete();
                        bytes = bArr;
                    } catch (Throwable th) {
                        try {
                            throw th;
                        } catch (Throwable th2) {
                            CloseableKt.closeFinally(fileOutputStream, th);
                            throw th2;
                        }
                    }
                    fileOutputStream = new FileOutputStream((File) file);
                } catch (Exception e) {
                    ArraysKt.fill$default(bArr, (byte) 0, 0, 0, 6, (Object) null);
                    throw e;
                }
                bArr = new byte[32];
                new SecureRandom().nextBytes(bArr);
                file = new File(this.directory, "api_v2_session_wrap.key.tmp");
            } catch (Throwable th3) {
                file.delete();
                throw th3;
            }
            if (!this.directory.isDirectory() && !this.directory.mkdirs()) {
                throw new IllegalStateException("Cannot create session key directory".toString());
            }
        }
        return bytes;
    }
}
