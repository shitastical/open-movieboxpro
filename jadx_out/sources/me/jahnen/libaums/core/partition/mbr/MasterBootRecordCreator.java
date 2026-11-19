package me.jahnen.libaums.core.partition.mbr;

import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.partition.PartitionTable;
import me.jahnen.libaums.core.partition.PartitionTableFactory;

/* JADX INFO: compiled from: MasterBootRecordCreator.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lme/jahnen/libaums/core/partition/mbr/MasterBootRecordCreator;", "Lme/jahnen/libaums/core/partition/PartitionTableFactory$PartitionTableCreator;", "()V", "read", "Lme/jahnen/libaums/core/partition/PartitionTable;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MasterBootRecordCreator implements PartitionTableFactory.PartitionTableCreator {
    @Override // me.jahnen.libaums.core.partition.PartitionTableFactory.PartitionTableCreator
    public PartitionTable read(BlockDeviceDriver blockDevice) throws IOException {
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        ByteBuffer buffer = ByteBuffer.allocate(Math.max(512, blockDevice.getBlockSize()));
        Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
        blockDevice.read(0L, buffer);
        return MasterBootRecord.INSTANCE.read(buffer);
    }
}
