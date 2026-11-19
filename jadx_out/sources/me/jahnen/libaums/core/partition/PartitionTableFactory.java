package me.jahnen.libaums.core.partition;

import android.util.Log;
import com.github.mjdev.libaums.partition.gpt.GPTCreator;
import java.io.IOException;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.partition.fs.FileSystemPartitionTableCreator;
import me.jahnen.libaums.core.partition.mbr.MasterBootRecordCreator;

/* JADX INFO: compiled from: PartitionTableFactory.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u0010\u0011B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lme/jahnen/libaums/core/partition/PartitionTableFactory;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "partitionTables", "Ljava/util/ArrayList;", "Lme/jahnen/libaums/core/partition/PartitionTableFactory$PartitionTableCreator;", "createPartitionTable", "Lme/jahnen/libaums/core/partition/PartitionTable;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "registerPartitionTable", "", "creator", "PartitionTableCreator", "UnsupportedPartitionTableException", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PartitionTableFactory {
    public static final PartitionTableFactory INSTANCE;
    private static final String TAG;
    private static final ArrayList<PartitionTableCreator> partitionTables;

    /* JADX INFO: compiled from: PartitionTableFactory.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lme/jahnen/libaums/core/partition/PartitionTableFactory$PartitionTableCreator;", "", "read", "Lme/jahnen/libaums/core/partition/PartitionTable;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public interface PartitionTableCreator {
        PartitionTable read(BlockDeviceDriver blockDevice) throws IOException;
    }

    /* JADX INFO: compiled from: PartitionTableFactory.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lme/jahnen/libaums/core/partition/PartitionTableFactory$UnsupportedPartitionTableException;", "Ljava/io/IOException;", "()V", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UnsupportedPartitionTableException extends IOException {
    }

    private PartitionTableFactory() {
    }

    static {
        PartitionTableFactory partitionTableFactory = new PartitionTableFactory();
        INSTANCE = partitionTableFactory;
        TAG = "PartitionTableFactory";
        partitionTables = new ArrayList<>();
        partitionTableFactory.registerPartitionTable(new FileSystemPartitionTableCreator());
        partitionTableFactory.registerPartitionTable(new GPTCreator());
        partitionTableFactory.registerPartitionTable(new MasterBootRecordCreator());
    }

    public final PartitionTable createPartitionTable(BlockDeviceDriver blockDevice) throws IOException {
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        for (PartitionTableCreator partitionTableCreator : partitionTables) {
            PartitionTable partitionTable = partitionTableCreator.read(blockDevice);
            if (partitionTable != null) {
                Log.d(TAG, "Found partition table " + partitionTableCreator.getClass().getSimpleName());
                return partitionTable;
            }
            Log.d(TAG, partitionTableCreator.getClass().getSimpleName() + " returned null");
        }
        throw new UnsupportedPartitionTableException();
    }

    public final synchronized void registerPartitionTable(PartitionTableCreator creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        partitionTables.add(creator);
    }
}
