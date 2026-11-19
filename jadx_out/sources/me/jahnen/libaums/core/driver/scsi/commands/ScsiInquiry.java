package me.jahnen.libaums.core.driver.scsi.commands;

import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScsiInquiry.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/ScsiInquiry;", "Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper;", "allocationLength", "", "lun", "(BB)V", "serialize", "", "buffer", "Ljava/nio/ByteBuffer;", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScsiInquiry extends CommandBlockWrapper {
    private static final byte LENGTH = 6;
    private static final byte OPCODE = 18;
    private final byte allocationLength;

    public ScsiInquiry(byte b, byte b2) {
        super(b, CommandBlockWrapper.Direction.IN, b2, (byte) 6, false, 16, null);
        this.allocationLength = b;
    }

    @Override // me.jahnen.libaums.core.driver.scsi.commands.CommandBlockWrapper
    public void serialize(ByteBuffer buffer) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        super.serialize(buffer);
        buffer.put((byte) 18);
        buffer.put((byte) 0);
        buffer.put((byte) 0);
        buffer.put((byte) 0);
        buffer.put(this.allocationLength);
    }
}
