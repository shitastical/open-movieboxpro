package me.jahnen.libaums.core.fs;

import java.io.IOException;
import kotlin.Metadata;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.partition.PartitionTableEntry;

/* JADX INFO: compiled from: FileSystemCreator.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lme/jahnen/libaums/core/fs/FileSystemCreator;", "", "read", "Lme/jahnen/libaums/core/fs/FileSystem;", "entry", "Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface FileSystemCreator {
    FileSystem read(PartitionTableEntry entry, BlockDeviceDriver blockDevice) throws IOException;
}
