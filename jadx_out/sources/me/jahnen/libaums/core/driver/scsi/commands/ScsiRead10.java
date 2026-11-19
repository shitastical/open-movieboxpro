package me.jahnen.libaums.core.driver.scsi.commands;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScsiRead10.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tJ\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006J\u0010\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/ScsiRead10;", "Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper;", "lun", "", "(B)V", "blockAddress", "", "transferBytes", "blockSize", "(III)V", "transferBlocks", "", "init", "", "serialize", "buffer", "Ljava/nio/ByteBuffer;", "toString", "", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScsiRead10 extends CommandBlockWrapper {
    private static final byte LENGTH = 10;
    private static final byte OPCODE = 40;
    private int blockAddress;
    private int blockSize;
    private short transferBlocks;
    private int transferBytes;

    public ScsiRead10(byte b) {
        super(0, CommandBlockWrapper.Direction.IN, b, (byte) 10, false, 16, null);
    }

    public ScsiRead10(int i, int i2, int i3) {
        super(i2, CommandBlockWrapper.Direction.IN, (byte) 0, (byte) 10, false, 16, null);
        init(i, i2, i3);
    }

    public final void init(int blockAddress, int transferBytes, int blockSize) {
        super.setDCbwDataTransferLength(transferBytes);
        this.blockAddress = blockAddress;
        this.transferBytes = transferBytes;
        this.blockSize = blockSize;
        short s = (short) (transferBytes / blockSize);
        if (transferBytes % blockSize != 0) {
            throw new IllegalArgumentException("transfer bytes is not a multiple of block size".toString());
        }
        this.transferBlocks = s;
    }

    @Override // me.jahnen.libaums.core.driver.scsi.commands.CommandBlockWrapper
    public void serialize(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        super.serialize(buffer);
        buffer.order(ByteOrder.BIG_ENDIAN);
        buffer.put(OPCODE);
        buffer.put((byte) 0);
        buffer.putInt(this.blockAddress);
        buffer.put((byte) 0);
        buffer.putShort(this.transferBlocks);
    }

    public String toString() {
        return "ScsiRead10 [blockAddress=" + this.blockAddress + ", transferBytes=" + this.transferBytes + ", blockSize=" + this.blockSize + ", transferBlocks=" + ((int) this.transferBlocks) + ", getdCbwDataTransferLength()=" + getDCbwDataTransferLength() + ']';
    }
}
