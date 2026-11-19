package me.jahnen.libaums.core.partition;

import android.util.Log;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.driver.ByteBlockDevice;
import me.jahnen.libaums.core.fs.FileSystem;
import me.jahnen.libaums.core.fs.FileSystemFactory;

/* JADX INFO: compiled from: Partition.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0011"}, d2 = {"Lme/jahnen/libaums/core/partition/Partition;", "Lme/jahnen/libaums/core/driver/ByteBlockDevice;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "entry", "Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "(Lme/jahnen/libaums/core/driver/BlockDeviceDriver;Lme/jahnen/libaums/core/partition/PartitionTableEntry;)V", "<set-?>", "Lme/jahnen/libaums/core/fs/FileSystem;", "fileSystem", "getFileSystem", "()Lme/jahnen/libaums/core/fs/FileSystem;", "volumeLabel", "", "getVolumeLabel", "()Ljava/lang/String;", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Partition extends ByteBlockDevice {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "Partition";
    private FileSystem fileSystem;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Partition(BlockDeviceDriver blockDevice, PartitionTableEntry entry) {
        super(blockDevice, entry.getLogicalBlockAddress());
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        Intrinsics.checkNotNullParameter(entry, "entry");
    }

    public final FileSystem getFileSystem() {
        FileSystem fileSystem = this.fileSystem;
        if (fileSystem != null) {
            return fileSystem;
        }
        Intrinsics.throwUninitializedPropertyAccessException("fileSystem");
        return null;
    }

    public final String getVolumeLabel() {
        return getFileSystem().getVolumeLabel();
    }

    /* JADX INFO: compiled from: Partition.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lme/jahnen/libaums/core/partition/Partition$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "createPartition", "Lme/jahnen/libaums/core/partition/Partition;", "entry", "Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final Partition createPartition(PartitionTableEntry entry, BlockDeviceDriver blockDevice) throws IOException {
            Intrinsics.checkNotNullParameter(entry, "entry");
            Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
            try {
                Partition partition = new Partition(blockDevice, entry);
                partition.fileSystem = FileSystemFactory.INSTANCE.createFileSystem(entry, partition);
                return partition;
            } catch (FileSystemFactory.UnsupportedFileSystemException unused) {
                Log.w(Partition.TAG, "Unsupported fs on partition");
                return null;
            }
        }
    }
}
