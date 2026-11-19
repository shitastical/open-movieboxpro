package me.jahnen.libaums.core.fs.fat32;

import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.fs.AbstractUsbFile;
import me.jahnen.libaums.core.fs.UsbFile;

/* JADX INFO: compiled from: FatFile.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B1\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\b\u0010%\u001a\u00020&H\u0016J\u0010\u0010'\u001a\u00020(2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\u0010\u0010)\u001a\u00020(2\u0006\u0010\u001c\u001a\u00020\u001bH\u0016J\b\u0010*\u001a\u00020\u0014H\u0016J\b\u0010+\u001a\u00020&H\u0016J\b\u0010,\u001a\u00020&H\u0016J\b\u0010-\u001a\u00020&H\u0002J\b\u0010.\u001a\u00020\u0014H\u0016J\b\u0010/\u001a\u00020\u0014H\u0016J\u0013\u00100\u001a\b\u0012\u0004\u0012\u00020\u001b01H\u0016¢\u0006\u0002\u00102J\u0013\u00103\u001a\b\u0012\u0004\u0012\u00020(01H\u0016¢\u0006\u0002\u00104J\u0010\u00105\u001a\u00020&2\u0006\u00106\u001a\u00020(H\u0016J\u0018\u00107\u001a\u00020&2\u0006\u00108\u001a\u00020\u00142\u0006\u00106\u001a\u000209H\u0016J\u0018\u0010:\u001a\u00020&2\u0006\u00108\u001a\u00020\u00142\u0006\u0010;\u001a\u000209H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00108VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0011R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u00148V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00020\u001b8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$¨\u0006<"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FatFile;", "Lme/jahnen/libaums/core/fs/AbstractUsbFile;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "fat", "Lme/jahnen/libaums/core/fs/fat32/FAT;", "bootSector", "Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;", "entry", "Lme/jahnen/libaums/core/fs/fat32/FatLfnDirectoryEntry;", "parent", "Lme/jahnen/libaums/core/fs/fat32/FatDirectory;", "(Lme/jahnen/libaums/core/driver/BlockDeviceDriver;Lme/jahnen/libaums/core/fs/fat32/FAT;Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;Lme/jahnen/libaums/core/fs/fat32/FatLfnDirectoryEntry;Lme/jahnen/libaums/core/fs/fat32/FatDirectory;)V", "chain", "Lme/jahnen/libaums/core/fs/fat32/ClusterChain;", "isDirectory", "", "()Z", "isRoot", "newLength", "", "length", "getLength", "()J", "setLength", "(J)V", "newName", "", "name", "getName", "()Ljava/lang/String;", "setName", "(Ljava/lang/String;)V", "getParent", "()Lme/jahnen/libaums/core/fs/fat32/FatDirectory;", "setParent", "(Lme/jahnen/libaums/core/fs/fat32/FatDirectory;)V", "close", "", "createDirectory", "Lme/jahnen/libaums/core/fs/UsbFile;", "createFile", "createdAt", "delete", "flush", "initChain", "lastAccessed", "lastModified", "list", "", "()[Ljava/lang/String;", "listFiles", "()[Lme/jahnen/libaums/core/fs/UsbFile;", "moveTo", FirebaseAnalytics.Param.DESTINATION, "read", "offset", "Ljava/nio/ByteBuffer;", "write", FirebaseAnalytics.Param.SOURCE, "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FatFile extends AbstractUsbFile {
    private final BlockDeviceDriver blockDevice;
    private final Fat32BootSector bootSector;
    private ClusterChain chain;
    private final FatLfnDirectoryEntry entry;
    private final FAT fat;
    private FatDirectory parent;

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public boolean isDirectory() {
        return false;
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public boolean isRoot() {
        return false;
    }

    public FatFile(BlockDeviceDriver blockDevice, FAT fat, Fat32BootSector bootSector, FatLfnDirectoryEntry entry, FatDirectory fatDirectory) {
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        Intrinsics.checkNotNullParameter(fat, "fat");
        Intrinsics.checkNotNullParameter(bootSector, "bootSector");
        Intrinsics.checkNotNullParameter(entry, "entry");
        this.blockDevice = blockDevice;
        this.fat = fat;
        this.bootSector = bootSector;
        this.entry = entry;
        this.parent = fatDirectory;
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public FatDirectory getParent() {
        return this.parent;
    }

    public void setParent(FatDirectory fatDirectory) {
        this.parent = fatDirectory;
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public String getName() {
        return this.entry.getName$libaums_release();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void setName(String newName) throws IOException {
        Intrinsics.checkNotNullParameter(newName, "newName");
        FatDirectory parent = getParent();
        Intrinsics.checkNotNull(parent);
        parent.renameEntry$libaums_release(this.entry, newName);
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public long getLength() {
        return this.entry.getFileSize();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void setLength(long j) throws IOException {
        initChain();
        ClusterChain clusterChain = this.chain;
        if (clusterChain == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
            clusterChain = null;
        }
        clusterChain.setLength$libaums_release(j);
        this.entry.setFileSize(j);
    }

    private final void initChain() throws IOException {
        if (this.chain == null) {
            this.chain = new ClusterChain(this.entry.getStartCluster(), this.blockDevice, this.fat, this.bootSector);
        }
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public long createdAt() {
        return this.entry.getActualEntry().getCreatedDateTime();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public long lastModified() {
        return this.entry.getActualEntry().getLastModifiedDateTime();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public long lastAccessed() {
        return this.entry.getActualEntry().getLastAccessedDateTime();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public String[] list() {
        throw new UnsupportedOperationException("This is a file!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public UsbFile[] listFiles() throws IOException {
        throw new UnsupportedOperationException("This is a file!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void read(long offset, ByteBuffer destination) throws IOException {
        Intrinsics.checkNotNullParameter(destination, "destination");
        initChain();
        this.entry.setLastAccessedTimeToNow();
        ClusterChain clusterChain = this.chain;
        if (clusterChain == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
            clusterChain = null;
        }
        clusterChain.read$libaums_release(offset, destination);
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void write(long offset, ByteBuffer source) throws IOException {
        Intrinsics.checkNotNullParameter(source, "source");
        initChain();
        long jRemaining = ((long) source.remaining()) + offset;
        if (jRemaining > getLength()) {
            setLength(jRemaining);
        }
        this.entry.setLastModifiedTimeToNow();
        ClusterChain clusterChain = this.chain;
        if (clusterChain == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
            clusterChain = null;
        }
        clusterChain.write$libaums_release(offset, source);
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void flush() throws IOException {
        FatDirectory parent = getParent();
        Intrinsics.checkNotNull(parent);
        parent.write$libaums_release();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        flush();
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public UsbFile createDirectory(String name) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        throw new UnsupportedOperationException("This is a file!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public UsbFile createFile(String name) throws IOException {
        Intrinsics.checkNotNullParameter(name, "name");
        throw new UnsupportedOperationException("This is a file!");
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void moveTo(UsbFile destination) throws IOException {
        Intrinsics.checkNotNullParameter(destination, "destination");
        FatDirectory parent = getParent();
        Intrinsics.checkNotNull(parent);
        parent.move$libaums_release(this.entry, destination);
        setParent((FatDirectory) destination);
    }

    @Override // me.jahnen.libaums.core.fs.UsbFile
    public void delete() throws IOException {
        initChain();
        FatDirectory parent = getParent();
        Intrinsics.checkNotNull(parent);
        parent.removeEntry$libaums_release(this.entry);
        FatDirectory parent2 = getParent();
        Intrinsics.checkNotNull(parent2);
        parent2.write$libaums_release();
        ClusterChain clusterChain = this.chain;
        if (clusterChain == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chain");
            clusterChain = null;
        }
        clusterChain.setLength$libaums_release(0L);
    }
}
