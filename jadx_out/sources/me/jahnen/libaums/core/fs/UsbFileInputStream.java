package me.jahnen.libaums.core.fs;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: compiled from: UsbFileInputStream.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0005\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0010\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u0006H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lme/jahnen/libaums/core/fs/UsbFileInputStream;", "Ljava/io/InputStream;", IDataSource.SCHEME_FILE_TAG, "Lme/jahnen/libaums/core/fs/UsbFile;", "(Lme/jahnen/libaums/core/fs/UsbFile;)V", "currentByteOffset", "", "available", "", "close", "", "read", "buffer", "", "byteOffset", "byteCount", "skip", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UsbFileInputStream extends InputStream {
    private static final String TAG = "UsbFileInputStream";
    private long currentByteOffset;
    private final UsbFile file;

    public UsbFileInputStream(UsbFile file) {
        Intrinsics.checkNotNullParameter(file, "file");
        this.file = file;
        if (file.isDirectory()) {
            throw new UnsupportedOperationException("UsbFileInputStream cannot be created on directory!");
        }
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        Log.d(TAG, "available");
        return 0;
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        if (this.currentByteOffset >= this.file.getLength()) {
            return -1;
        }
        ByteBuffer buffer = ByteBuffer.allocate(512);
        buffer.limit(1);
        UsbFile usbFile = this.file;
        long j = this.currentByteOffset;
        Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
        usbFile.read(j, buffer);
        this.currentByteOffset++;
        buffer.flip();
        return buffer.get();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.file.close();
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (this.currentByteOffset >= this.file.getLength()) {
            return -1;
        }
        long jMin = Math.min(buffer.length, this.file.getLength() - this.currentByteOffset);
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        int i = (int) jMin;
        byteBuffer.limit(i);
        UsbFile usbFile = this.file;
        long j = this.currentByteOffset;
        Intrinsics.checkNotNullExpressionValue(byteBuffer, "byteBuffer");
        usbFile.read(j, byteBuffer);
        this.currentByteOffset += jMin;
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (this.currentByteOffset >= this.file.getLength()) {
            return -1;
        }
        long jMin = Math.min(byteCount, this.file.getLength() - this.currentByteOffset);
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        byteBuffer.position(byteOffset);
        int i = (int) jMin;
        byteBuffer.limit(byteOffset + i);
        UsbFile usbFile = this.file;
        long j = this.currentByteOffset;
        Intrinsics.checkNotNullExpressionValue(byteBuffer, "byteBuffer");
        usbFile.read(j, byteBuffer);
        this.currentByteOffset += jMin;
        return i;
    }

    @Override // java.io.InputStream
    public long skip(long byteCount) throws IOException {
        long jMin = Math.min(byteCount, this.file.getLength() - this.currentByteOffset);
        this.currentByteOffset += jMin;
        return jMin;
    }
}
