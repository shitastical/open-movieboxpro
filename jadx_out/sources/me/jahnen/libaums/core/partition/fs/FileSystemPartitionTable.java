package me.jahnen.libaums.core.partition.fs;

import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.fs.FileSystem;
import me.jahnen.libaums.core.partition.PartitionTable;
import me.jahnen.libaums.core.partition.PartitionTableEntry;

/* JADX INFO: compiled from: FileSystemPartitionTable.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u000bR\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0016"}, d2 = {"Lme/jahnen/libaums/core/partition/fs/FileSystemPartitionTable;", "Lme/jahnen/libaums/core/partition/PartitionTable;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "fs", "Lme/jahnen/libaums/core/fs/FileSystem;", "(Lme/jahnen/libaums/core/driver/BlockDeviceDriver;Lme/jahnen/libaums/core/fs/FileSystem;)V", "entries", "", "Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "getEntries$libaums_release", "()Ljava/util/List;", "setEntries$libaums_release", "(Ljava/util/List;)V", "partitionTableEntries", "", "getPartitionTableEntries", "size", "", "getSize", "()I", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FileSystemPartitionTable implements PartitionTable {
    private static final String TAG = "FileSystemPartitionTable";
    private List<PartitionTableEntry> entries;

    @Override // me.jahnen.libaums.core.partition.PartitionTable
    public int getSize() {
        return 0;
    }

    public FileSystemPartitionTable(BlockDeviceDriver blockDevice, FileSystem fs) {
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        Intrinsics.checkNotNullParameter(fs, "fs");
        this.entries = new ArrayList();
        String str = TAG;
        Log.i(str, "Found a device without partition table, yay!");
        long capacity = fs.getCapacity() / ((long) blockDevice.getBlockSize());
        if (fs.getCapacity() % ((long) blockDevice.getBlockSize()) != 0) {
            Log.w(str, "fs capacity is not multiple of block size");
        }
        this.entries.add(new PartitionTableEntry(fs.getType(), 0L, capacity));
    }

    public final List<PartitionTableEntry> getEntries$libaums_release() {
        return this.entries;
    }

    public final void setEntries$libaums_release(List<PartitionTableEntry> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.entries = list;
    }

    @Override // me.jahnen.libaums.core.partition.PartitionTable
    public List<PartitionTableEntry> getPartitionTableEntries() {
        return this.entries;
    }
}
