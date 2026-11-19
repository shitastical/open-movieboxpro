package me.jahnen.libaums.core.driver.scsi.commands.sense;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.scsi.commands.CommandBlockWrapper;

/* JADX INFO: compiled from: ScsiRequestSense.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSense;", "Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper;", "allocationLength", "", "lun", "(BB)V", "dynamicSizeFromPartialResponse", "", "buffer", "Ljava/nio/ByteBuffer;", "serialize", "", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScsiRequestSense extends CommandBlockWrapper {
    private static final byte LENGTH = 6;
    private static final byte OPCODE = 3;
    private static final int SENSE_BYTE_ADDR = 7;
    private final byte allocationLength;

    public ScsiRequestSense(byte b, byte b2) {
        super(b, CommandBlockWrapper.Direction.IN, b2, (byte) 6, true);
        this.allocationLength = b;
    }

    @Override // me.jahnen.libaums.core.driver.scsi.commands.CommandBlockWrapper
    public void serialize(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        super.serialize(buffer);
        buffer.put((byte) 3);
        buffer.put((byte) 0);
        buffer.put((byte) 0);
        buffer.put((byte) 0);
        buffer.put(this.allocationLength);
    }

    @Override // me.jahnen.libaums.core.driver.scsi.commands.CommandBlockWrapper
    public int dynamicSizeFromPartialResponse(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        buffer.order(ByteOrder.BIG_ENDIAN);
        return buffer.get(7) + 8;
    }
}
