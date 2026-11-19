package me.jahnen.libaums.core.driver;

import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ByteBlockDevice.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0016\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0012H\u0016R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lme/jahnen/libaums/core/driver/ByteBlockDevice;", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "targetBlockDevice", "logicalOffsetToAdd", "", "(Lme/jahnen/libaums/core/driver/BlockDeviceDriver;J)V", "blockSize", "", "getBlockSize", "()I", "blocks", "getBlocks", "()J", "init", "", "read", "byteOffset", "dest", "Ljava/nio/ByteBuffer;", "write", "src", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class ByteBlockDevice implements BlockDeviceDriver {
    private static final String TAG = "ByteBlockDevice";
    private final long logicalOffsetToAdd;
    private final BlockDeviceDriver targetBlockDevice;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ByteBlockDevice(BlockDeviceDriver targetBlockDevice) {
        this(targetBlockDevice, 0L, 2, null);
        Intrinsics.checkNotNullParameter(targetBlockDevice, "targetBlockDevice");
    }

    public ByteBlockDevice(BlockDeviceDriver targetBlockDevice, long j) {
        Intrinsics.checkNotNullParameter(targetBlockDevice, "targetBlockDevice");
        this.targetBlockDevice = targetBlockDevice;
        this.logicalOffsetToAdd = j;
    }

    public /* synthetic */ ByteBlockDevice(BlockDeviceDriver blockDeviceDriver, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(blockDeviceDriver, (i & 2) != 0 ? 0L : j);
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public int getBlockSize() {
        return this.targetBlockDevice.getBlockSize();
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public long getBlocks() {
        return this.targetBlockDevice.getBlocks();
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public void init() throws IOException {
        this.targetBlockDevice.init();
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public void read(long byteOffset, ByteBuffer dest) throws IOException {
        ByteBuffer byteBufferAllocate;
        Intrinsics.checkNotNullParameter(dest, "dest");
        long blockSize = (byteOffset / ((long) getBlockSize())) + this.logicalOffsetToAdd;
        if (byteOffset % ((long) getBlockSize()) != 0) {
            ByteBuffer tmp = ByteBuffer.allocate(getBlockSize());
            BlockDeviceDriver blockDeviceDriver = this.targetBlockDevice;
            Intrinsics.checkNotNullExpressionValue(tmp, "tmp");
            blockDeviceDriver.read(blockSize, tmp);
            tmp.clear();
            tmp.position((int) (byteOffset % ((long) getBlockSize())));
            tmp.limit(tmp.position() + Math.min(dest.remaining(), tmp.remaining()));
            dest.put(tmp);
            blockSize++;
        }
        if (dest.remaining() > 0) {
            if (dest.remaining() % getBlockSize() != 0) {
                int blockSize2 = (getBlockSize() - (dest.remaining() % getBlockSize())) + dest.remaining();
                byteBufferAllocate = ByteBuffer.allocate(blockSize2);
                Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(rounded)");
                byteBufferAllocate.limit(blockSize2);
            } else {
                byteBufferAllocate = dest;
            }
            this.targetBlockDevice.read(blockSize, byteBufferAllocate);
            if (dest.remaining() % getBlockSize() != 0) {
                System.arraycopy(byteBufferAllocate.array(), 0, dest.array(), dest.position(), dest.remaining());
            }
            dest.position(dest.limit());
        }
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public void write(long byteOffset, ByteBuffer src) throws IOException {
        Intrinsics.checkNotNullParameter(src, "src");
        long blockSize = (byteOffset / ((long) getBlockSize())) + this.logicalOffsetToAdd;
        if (byteOffset % ((long) getBlockSize()) != 0) {
            ByteBuffer tmp = ByteBuffer.allocate(getBlockSize());
            BlockDeviceDriver blockDeviceDriver = this.targetBlockDevice;
            Intrinsics.checkNotNullExpressionValue(tmp, "tmp");
            blockDeviceDriver.read(blockSize, tmp);
            tmp.clear();
            tmp.position((int) (byteOffset % ((long) getBlockSize())));
            int iMin = Math.min(tmp.remaining(), src.remaining());
            tmp.put(src.array(), src.position(), iMin);
            src.position(src.position() + iMin);
            tmp.clear();
            this.targetBlockDevice.write(blockSize, tmp);
            blockSize++;
        }
        if (src.remaining() > 0) {
            if (src.remaining() % getBlockSize() != 0) {
                int blockSize2 = (getBlockSize() - (src.remaining() % getBlockSize())) + src.remaining();
                ByteBuffer byteBufferAllocate = ByteBuffer.allocate(blockSize2);
                Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(rounded)");
                byteBufferAllocate.limit(blockSize2);
                System.arraycopy(src.array(), src.position(), byteBufferAllocate.array(), 0, src.remaining());
                src.position(src.limit());
                src = byteBufferAllocate;
            }
            this.targetBlockDevice.write(blockSize, src);
        }
    }
}
