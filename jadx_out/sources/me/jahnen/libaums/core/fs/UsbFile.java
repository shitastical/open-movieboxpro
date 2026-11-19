package me.jahnen.libaums.core.fs;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.movieboxpro.android.service.FileDownloadService;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: compiled from: UsbFile.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\bf\u0018\u0000 /2\u00020\u0001:\u0001/J\b\u0010\u0018\u001a\u00020\u0019H&J\u0010\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0003H&J\u0010\u0010\u001b\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0003H&J\b\u0010\u001c\u001a\u00020\u000bH&J\b\u0010\u001d\u001a\u00020\u0019H&J\b\u0010\u001e\u001a\u00020\u0019H&J\b\u0010\u001f\u001a\u00020\u000bH&J\b\u0010 \u001a\u00020\u000bH&J\u0013\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00030\"H&¢\u0006\u0002\u0010#J\u0013\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00000\"H&¢\u0006\u0002\u0010%J\u0010\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\u0000H&J\u0018\u0010(\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010'\u001a\u00020*H&J\u0012\u0010+\u001a\u0004\u0018\u00010\u00002\u0006\u0010,\u001a\u00020\u0003H&J\u0018\u0010-\u001a\u00020\u00192\u0006\u0010)\u001a\u00020\u000b2\u0006\u0010.\u001a\u00020*H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\bR\u0012\u0010\t\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\bR\"\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b@fX¦\u000e¢\u0006\f\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\"\u0010\u0011\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u0003@fX¦\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0005\"\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0015\u001a\u0004\u0018\u00010\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u00060"}, d2 = {"Lme/jahnen/libaums/core/fs/UsbFile;", "Ljava/io/Closeable;", "absolutePath", "", "getAbsolutePath", "()Ljava/lang/String;", "isDirectory", "", "()Z", "isRoot", "<set-?>", "", "length", "getLength", "()J", "setLength", "(J)V", "name", "getName", "setName", "(Ljava/lang/String;)V", "parent", "getParent", "()Lme/jahnen/libaums/core/fs/UsbFile;", "close", "", "createDirectory", "createFile", "createdAt", "delete", "flush", "lastAccessed", "lastModified", "list", "", "()[Ljava/lang/String;", "listFiles", "()[Lme/jahnen/libaums/core/fs/UsbFile;", "moveTo", FirebaseAnalytics.Param.DESTINATION, "read", "offset", "Ljava/nio/ByteBuffer;", FirebaseAnalytics.Event.SEARCH, FileDownloadService.PARAMS_KEY_PATH, "write", FirebaseAnalytics.Param.SOURCE, "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface UsbFile extends Closeable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final String separator = "/";

    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close() throws IOException;

    UsbFile createDirectory(String name) throws IOException;

    UsbFile createFile(String name) throws IOException;

    long createdAt();

    void delete() throws IOException;

    void flush() throws IOException;

    String getAbsolutePath();

    long getLength();

    String getName();

    UsbFile getParent();

    boolean isDirectory();

    boolean isRoot();

    long lastAccessed();

    long lastModified();

    String[] list() throws IOException;

    UsbFile[] listFiles() throws IOException;

    void moveTo(UsbFile destination) throws IOException;

    void read(long offset, ByteBuffer destination) throws IOException;

    UsbFile search(String path) throws IOException;

    void setLength(long j) throws IOException;

    void setName(String str) throws IOException;

    void write(long offset, ByteBuffer source) throws IOException;

    /* JADX INFO: compiled from: UsbFile.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lme/jahnen/libaums/core/fs/UsbFile$Companion;", "", "()V", "separator", "", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final String separator = "/";

        private Companion() {
        }
    }
}
