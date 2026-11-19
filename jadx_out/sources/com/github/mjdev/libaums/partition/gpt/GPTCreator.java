package com.github.mjdev.libaums.partition.gpt;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.partition.PartitionTable;
import me.jahnen.libaums.core.partition.PartitionTableFactory;

/* JADX INFO: compiled from: GPTCreator.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/github/mjdev/libaums/partition/gpt/GPTCreator;", "Lme/jahnen/libaums/core/partition/PartitionTableFactory$PartitionTableCreator;", "()V", "read", "Lme/jahnen/libaums/core/partition/PartitionTable;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GPTCreator implements PartitionTableFactory.PartitionTableCreator {
    @Override // me.jahnen.libaums.core.partition.PartitionTableFactory.PartitionTableCreator
    public PartitionTable read(BlockDeviceDriver blockDevice) {
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        return GPT.INSTANCE.read(blockDevice);
    }
}
