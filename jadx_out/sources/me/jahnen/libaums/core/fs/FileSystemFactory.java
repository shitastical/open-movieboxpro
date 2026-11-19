package me.jahnen.libaums.core.fs;

import com.jeffmony.downloader.model.Video;
import java.io.IOException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.fs.fat32.Fat32FileSystemCreator;
import me.jahnen.libaums.core.partition.PartitionTableEntry;

/* JADX INFO: compiled from: FileSystemFactory.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001d\u001eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0011\u001a\u00020\u0012H\u0007J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001bH\u0007J\u0018\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\f\u0012\n \t*\u0004\u0018\u00010\b0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\n\u001a\n \t*\u0004\u0018\u00010\u000b0\u000b8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\u0002\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, d2 = {"Lme/jahnen/libaums/core/fs/FileSystemFactory;", "", "()V", "DEFAULT_PRIORITY", "", "count", "fileSystems", "Ljava/util/TreeSet;", "Lme/jahnen/libaums/core/fs/FileSystemFactory$PrioritizedFileSystemCreator;", "kotlin.jvm.PlatformType", "timeZone", "Ljava/util/TimeZone;", "getTimeZone$annotations", "getTimeZone", "()Ljava/util/TimeZone;", "setTimeZone", "(Ljava/util/TimeZone;)V", "clearFileSystems", "", "createFileSystem", "Lme/jahnen/libaums/core/fs/FileSystem;", "entry", "Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "registerFileSystem", "creator", "Lme/jahnen/libaums/core/fs/FileSystemCreator;", "priority", "PrioritizedFileSystemCreator", "UnsupportedFileSystemException", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FileSystemFactory {
    public static final int DEFAULT_PRIORITY = 0;
    public static final FileSystemFactory INSTANCE = new FileSystemFactory();
    private static int count;
    private static final TreeSet<PrioritizedFileSystemCreator> fileSystems;
    private static TimeZone timeZone;

    /* JADX INFO: compiled from: FileSystemFactory.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lme/jahnen/libaums/core/fs/FileSystemFactory$UnsupportedFileSystemException;", "Ljava/io/IOException;", "()V", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class UnsupportedFileSystemException extends IOException {
    }

    @JvmStatic
    public static /* synthetic */ void getTimeZone$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: FileSystemFactory.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0017"}, d2 = {"Lme/jahnen/libaums/core/fs/FileSystemFactory$PrioritizedFileSystemCreator;", "", "priority", "", "count", "creator", "Lme/jahnen/libaums/core/fs/FileSystemCreator;", "(IILme/jahnen/libaums/core/fs/FileSystemCreator;)V", "getCount", "()I", "getCreator", "()Lme/jahnen/libaums/core/fs/FileSystemCreator;", "getPriority", "component1", "component2", "component3", "copy", "equals", "", Video.TypeInfo.OTHER, "hashCode", "toString", "", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final /* data */ class PrioritizedFileSystemCreator {
        private final int count;
        private final FileSystemCreator creator;
        private final int priority;

        public static /* synthetic */ PrioritizedFileSystemCreator copy$default(PrioritizedFileSystemCreator prioritizedFileSystemCreator, int i, int i2, FileSystemCreator fileSystemCreator, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = prioritizedFileSystemCreator.priority;
            }
            if ((i3 & 2) != 0) {
                i2 = prioritizedFileSystemCreator.count;
            }
            if ((i3 & 4) != 0) {
                fileSystemCreator = prioritizedFileSystemCreator.creator;
            }
            return prioritizedFileSystemCreator.copy(i, i2, fileSystemCreator);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final int getPriority() {
            return this.priority;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final int getCount() {
            return this.count;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final FileSystemCreator getCreator() {
            return this.creator;
        }

        public final PrioritizedFileSystemCreator copy(int priority, int count, FileSystemCreator creator) {
            Intrinsics.checkNotNullParameter(creator, "creator");
            return new PrioritizedFileSystemCreator(priority, count, creator);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PrioritizedFileSystemCreator)) {
                return false;
            }
            PrioritizedFileSystemCreator prioritizedFileSystemCreator = (PrioritizedFileSystemCreator) other;
            return this.priority == prioritizedFileSystemCreator.priority && this.count == prioritizedFileSystemCreator.count && Intrinsics.areEqual(this.creator, prioritizedFileSystemCreator.creator);
        }

        public int hashCode() {
            return (((this.priority * 31) + this.count) * 31) + this.creator.hashCode();
        }

        public String toString() {
            return "PrioritizedFileSystemCreator(priority=" + this.priority + ", count=" + this.count + ", creator=" + this.creator + ')';
        }

        public PrioritizedFileSystemCreator(int i, int i2, FileSystemCreator creator) {
            Intrinsics.checkNotNullParameter(creator, "creator");
            this.priority = i;
            this.count = i2;
            this.creator = creator;
        }

        public final int getCount() {
            return this.count;
        }

        public final FileSystemCreator getCreator() {
            return this.creator;
        }

        public final int getPriority() {
            return this.priority;
        }
    }

    private FileSystemFactory() {
    }

    static {
        final Comparator comparator = new Comparator() { // from class: me.jahnen.libaums.core.fs.FileSystemFactory$special$$inlined$compareBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                return ComparisonsKt.compareValues(Integer.valueOf(((FileSystemFactory.PrioritizedFileSystemCreator) t).getPriority()), Integer.valueOf(((FileSystemFactory.PrioritizedFileSystemCreator) t2).getPriority()));
            }
        };
        fileSystems = new TreeSet<>(new Comparator() { // from class: me.jahnen.libaums.core.fs.FileSystemFactory$special$$inlined$thenBy$1
            /* JADX WARN: Multi-variable type inference failed */
            @Override // java.util.Comparator
            public final int compare(T t, T t2) {
                int iCompare = comparator.compare(t, t2);
                return iCompare != 0 ? iCompare : ComparisonsKt.compareValues(Integer.valueOf(((FileSystemFactory.PrioritizedFileSystemCreator) t).getCount()), Integer.valueOf(((FileSystemFactory.PrioritizedFileSystemCreator) t2).getCount()));
            }
        });
        timeZone = TimeZone.getDefault();
        registerFileSystem(new Fat32FileSystemCreator(), 1);
    }

    public static final TimeZone getTimeZone() {
        return timeZone;
    }

    public static final void setTimeZone(TimeZone timeZone2) {
        timeZone = timeZone2;
    }

    public final synchronized FileSystem createFileSystem(PartitionTableEntry entry, BlockDeviceDriver blockDevice) throws IOException {
        FileSystem fileSystem;
        Intrinsics.checkNotNullParameter(entry, "entry");
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        Iterator<T> it = fileSystems.iterator();
        while (it.hasNext()) {
            fileSystem = ((PrioritizedFileSystemCreator) it.next()).getCreator().read(entry, blockDevice);
            if (fileSystem != null) {
            }
        }
        throw new UnsupportedFileSystemException();
        return fileSystem;
    }

    @JvmStatic
    public static final synchronized void registerFileSystem(FileSystemCreator creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        registerFileSystem(creator, 0);
    }

    @JvmStatic
    public static final synchronized void registerFileSystem(FileSystemCreator creator, int priority) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        TreeSet<PrioritizedFileSystemCreator> treeSet = fileSystems;
        int i = count;
        count = i + 1;
        treeSet.add(new PrioritizedFileSystemCreator(priority, i, creator));
    }

    @JvmStatic
    public static final synchronized void clearFileSystems() {
        fileSystems.clear();
    }
}
