package me.jahnen.libaums.core.driver.scsi.commands;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScsiReadCapacityResponse.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007¨\u0006\u000b"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/ScsiReadCapacityResponse;", "", "()V", "<set-?>", "", "blockLength", "getBlockLength", "()I", "logicalBlockAddress", "getLogicalBlockAddress", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScsiReadCapacityResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int blockLength;
    private int logicalBlockAddress;

    public /* synthetic */ ScsiReadCapacityResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ScsiReadCapacityResponse() {
    }

    public final int getLogicalBlockAddress() {
        return this.logicalBlockAddress;
    }

    public final int getBlockLength() {
        return this.blockLength;
    }

    /* JADX INFO: compiled from: ScsiReadCapacityResponse.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/ScsiReadCapacityResponse$Companion;", "", "()V", "read", "Lme/jahnen/libaums/core/driver/scsi/commands/ScsiReadCapacityResponse;", "buffer", "Ljava/nio/ByteBuffer;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScsiReadCapacityResponse read(ByteBuffer buffer) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            buffer.order(ByteOrder.BIG_ENDIAN);
            ScsiReadCapacityResponse scsiReadCapacityResponse = new ScsiReadCapacityResponse(null);
            scsiReadCapacityResponse.logicalBlockAddress = buffer.getInt();
            scsiReadCapacityResponse.blockLength = buffer.getInt();
            return scsiReadCapacityResponse;
        }
    }
}
