package me.jahnen.libaums.core.driver.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import master.flame.danmaku.danmaku.parser.IDataSource;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;

/* JADX INFO: compiled from: FileBlockDeviceDriver.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B#\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007B#\b\u0017\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\nJ\b\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u0018H\u0016R\u001e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lme/jahnen/libaums/core/driver/file/FileBlockDeviceDriver;", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", IDataSource.SCHEME_FILE_TAG, "Ljava/io/File;", "byteOffset", "", "blockSize", "(Ljava/io/File;II)V", "url", "Ljava/net/URL;", "(Ljava/net/URL;II)V", "<set-?>", "getBlockSize", "()I", "blocks", "", "getBlocks", "()J", "Ljava/io/RandomAccessFile;", "init", "", "read", "deviceOffset", "buffer", "Ljava/nio/ByteBuffer;", "write", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FileBlockDeviceDriver implements BlockDeviceDriver {
    private int blockSize;
    private int byteOffset;
    private RandomAccessFile file;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileBlockDeviceDriver(File file) throws FileNotFoundException {
        this(file, 0, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(file, "file");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileBlockDeviceDriver(File file, int i) throws FileNotFoundException {
        this(file, i, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(file, "file");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileBlockDeviceDriver(URL url) throws IOException {
        this(url, 0, 0, 6, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(url, "url");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public FileBlockDeviceDriver(URL url, int i) throws IOException {
        this(url, i, 0, 4, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(url, "url");
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public void init() throws IOException {
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public int getBlockSize() {
        return this.blockSize;
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public long getBlocks() {
        return this.file.length() / ((long) getBlockSize());
    }

    public FileBlockDeviceDriver(File file, int i, int i2) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "file");
        this.file = new RandomAccessFile(file, "rw");
        this.blockSize = i2;
        this.byteOffset = i;
    }

    public /* synthetic */ FileBlockDeviceDriver(File file, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) throws FileNotFoundException {
        this(file, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 512 : i2);
    }

    public FileBlockDeviceDriver(URL url, int i, int i2) throws IOException {
        Intrinsics.checkNotNullParameter(url, "url");
        this.byteOffset = i;
        ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(url.openStream());
        File fileCreateTempFile = File.createTempFile("libaums_file_blockdevice", ".bin");
        fileCreateTempFile.deleteOnExit();
        new FileOutputStream(fileCreateTempFile).getChannel().transferFrom(readableByteChannelNewChannel, 0L, Long.MAX_VALUE);
        this.file = new RandomAccessFile(fileCreateTempFile, "rw");
        this.blockSize = i2;
    }

    public /* synthetic */ FileBlockDeviceDriver(URL url, int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) throws IOException {
        this(url, (i3 & 2) != 0 ? 0 : i, (i3 & 4) != 0 ? 512 : i2);
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public void read(long deviceOffset, ByteBuffer buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.file.seek((deviceOffset * ((long) getBlockSize())) + ((long) this.byteOffset));
        int i = this.file.read(buffer.array(), buffer.position(), buffer.remaining());
        if (i == -1) {
            throw new IOException("EOF");
        }
        buffer.position(buffer.position() + i);
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public void write(long deviceOffset, ByteBuffer buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        this.file.seek((deviceOffset * ((long) getBlockSize())) + ((long) this.byteOffset));
        this.file.write(buffer.array(), buffer.position(), buffer.remaining());
        buffer.position(buffer.limit());
    }
}
