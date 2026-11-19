package me.jahnen.libaums.core.fs.fat32;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.fs.FileSystem;
import me.jahnen.libaums.core.fs.FileSystemCreator;
import me.jahnen.libaums.core.partition.PartitionTableEntry;

/* JADX INFO: compiled from: Fat32FileSystemCreator.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/Fat32FileSystemCreator;", "Lme/jahnen/libaums/core/fs/FileSystemCreator;", "()V", "read", "Lme/jahnen/libaums/core/fs/FileSystem;", "entry", "Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Fat32FileSystemCreator implements FileSystemCreator {
    @Override // me.jahnen.libaums.core.fs.FileSystemCreator
    public FileSystem read(PartitionTableEntry entry, BlockDeviceDriver blockDevice) throws IOException {
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        return Fat32FileSystem.INSTANCE.read(blockDevice);
    }
}
