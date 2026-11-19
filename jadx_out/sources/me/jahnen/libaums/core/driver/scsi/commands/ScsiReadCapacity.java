package me.jahnen.libaums.core.driver.scsi.commands;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScsiReadCapacity.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/ScsiReadCapacity;", "Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper;", "lun", "", "(B)V", "serialize", "", "buffer", "Ljava/nio/ByteBuffer;", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScsiReadCapacity extends CommandBlockWrapper {
    private static final byte LENGTH = 10;
    private static final byte OPCODE = 37;
    private static final int RESPONSE_LENGTH = 8;

    public ScsiReadCapacity(byte b) {
        super(8, CommandBlockWrapper.Direction.IN, b, (byte) 10, false, 16, null);
    }

    @Override // me.jahnen.libaums.core.driver.scsi.commands.CommandBlockWrapper
    public void serialize(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        super.serialize(buffer);
        buffer.put((byte) 37);
    }
}
