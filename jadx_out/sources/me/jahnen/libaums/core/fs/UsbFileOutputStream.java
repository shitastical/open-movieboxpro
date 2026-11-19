package me.jahnen.libaums.core.fs;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: compiled from: UsbFileOutputStream.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\f\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\f\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u0010H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lme/jahnen/libaums/core/fs/UsbFileOutputStream;", "Ljava/io/OutputStream;", IDataSource.SCHEME_FILE_TAG, "Lme/jahnen/libaums/core/fs/UsbFile;", "append", "", "(Lme/jahnen/libaums/core/fs/UsbFile;Z)V", "currentByteOffset", "", "close", "", "flush", "write", "buffer", "", "offset", "", "count", "oneByte", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UsbFileOutputStream extends OutputStream {
    private long currentByteOffset;
    private final UsbFile file;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public UsbFileOutputStream(UsbFile file) {
        this(file, false, 2, null);
        Intrinsics.checkNotNullParameter(file, "file");
    }

    public UsbFileOutputStream(UsbFile file, boolean z) {
        Intrinsics.checkNotNullParameter(file, "file");
        this.file = file;
        if (file.isDirectory()) {
            throw new UnsupportedOperationException("UsbFileOutputStream cannot be created on directory!");
        }
        if (z) {
            this.currentByteOffset = file.getLength();
        }
    }

    public /* synthetic */ UsbFileOutputStream(UsbFile usbFile, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(usbFile, (i & 2) != 0 ? false : z);
    }

    @Override // java.io.OutputStream
    public void write(int oneByte) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(new byte[]{(byte) oneByte});
        UsbFile usbFile = this.file;
        long j = this.currentByteOffset;
        Intrinsics.checkNotNullExpressionValue(byteBuffer, "byteBuffer");
        usbFile.write(j, byteBuffer);
        this.currentByteOffset++;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.file.setLength(this.currentByteOffset);
        this.file.close();
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() throws IOException {
        this.file.flush();
    }

    @Override // java.io.OutputStream
    public void write(byte[] buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        UsbFile usbFile = this.file;
        long j = this.currentByteOffset;
        Intrinsics.checkNotNullExpressionValue(byteBuffer, "byteBuffer");
        usbFile.write(j, byteBuffer);
        this.currentByteOffset += (long) buffer.length;
    }

    @Override // java.io.OutputStream
    public void write(byte[] buffer, int offset, int count) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
        byteBuffer.position(offset);
        byteBuffer.limit(offset + count);
        UsbFile usbFile = this.file;
        long j = this.currentByteOffset;
        Intrinsics.checkNotNullExpressionValue(byteBuffer, "byteBuffer");
        usbFile.write(j, byteBuffer);
        this.currentByteOffset += (long) count;
    }
}
