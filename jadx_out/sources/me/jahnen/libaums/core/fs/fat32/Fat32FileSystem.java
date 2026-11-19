package me.jahnen.libaums.core.fs.fat32;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.WeakHashMap;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.fs.FileSystem;
import me.jahnen.libaums.core.fs.UsbFile;

/* JADX INFO: compiled from: Fat32FileSystem.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 (2\u00020\u0001:\u0001(B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\fR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\fR\u0014\u0010\u001f\u001a\u00020 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0014\u0010#\u001a\u00020\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u0010R\u0014\u0010%\u001a\u00020\u00158VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006)"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/Fat32FileSystem;", "Lme/jahnen/libaums/core/fs/FileSystem;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "first512Bytes", "Ljava/nio/ByteBuffer;", "(Lme/jahnen/libaums/core/driver/BlockDeviceDriver;Ljava/nio/ByteBuffer;)V", "bootSector", "Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;", "capacity", "", "getCapacity", "()J", "chunkSize", "", "getChunkSize", "()I", "fat", "Lme/jahnen/libaums/core/fs/fat32/FAT;", "fileCache", "Ljava/util/WeakHashMap;", "", "Lme/jahnen/libaums/core/fs/UsbFile;", "getFileCache$libaums_release", "()Ljava/util/WeakHashMap;", "freeSpace", "getFreeSpace", "fsInfoStructure", "Lme/jahnen/libaums/core/fs/fat32/FsInfoStructure;", "occupiedSpace", "getOccupiedSpace", "rootDirectory", "Lme/jahnen/libaums/core/fs/fat32/FatDirectory;", "getRootDirectory", "()Lme/jahnen/libaums/core/fs/fat32/FatDirectory;", "type", "getType", "volumeLabel", "getVolumeLabel", "()Ljava/lang/String;", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Fat32FileSystem implements FileSystem {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "Fat32FileSystem";
    private final Fat32BootSector bootSector;
    private final FAT fat;
    private final WeakHashMap<String, UsbFile> fileCache;
    private final FsInfoStructure fsInfoStructure;
    private final FatDirectory rootDirectory;

    public /* synthetic */ Fat32FileSystem(BlockDeviceDriver blockDeviceDriver, ByteBuffer byteBuffer, DefaultConstructorMarker defaultConstructorMarker) {
        this(blockDeviceDriver, byteBuffer);
    }

    @JvmStatic
    public static final Fat32FileSystem read(BlockDeviceDriver blockDeviceDriver) throws IOException {
        return INSTANCE.read(blockDeviceDriver);
    }

    @Override // me.jahnen.libaums.core.fs.FileSystem
    public int getType() {
        return 2;
    }

    private Fat32FileSystem(BlockDeviceDriver blockDeviceDriver, ByteBuffer byteBuffer) throws IOException {
        Fat32BootSector fat32BootSector = Fat32BootSector.INSTANCE.read(byteBuffer);
        this.bootSector = fat32BootSector;
        this.fileCache = new WeakHashMap<>();
        FsInfoStructure fsInfoStructure = FsInfoStructure.INSTANCE.read(blockDeviceDriver, fat32BootSector.getFsInfoStartSector() * fat32BootSector.getBytesPerSector());
        this.fsInfoStructure = fsInfoStructure;
        FAT fat = new FAT(blockDeviceDriver, fat32BootSector, fsInfoStructure);
        this.fat = fat;
        this.rootDirectory = FatDirectory.INSTANCE.readRoot$libaums_release(this, blockDeviceDriver, fat, fat32BootSector);
        Log.d(TAG, fat32BootSector.toString());
    }

    @Override // me.jahnen.libaums.core.fs.FileSystem
    public FatDirectory getRootDirectory() {
        return this.rootDirectory;
    }

    public final WeakHashMap<String, UsbFile> getFileCache$libaums_release() {
        return this.fileCache;
    }

    @Override // me.jahnen.libaums.core.fs.FileSystem
    public String getVolumeLabel() {
        String volumeLabel = getRootDirectory().getVolumeLabel();
        if (volumeLabel == null) {
            volumeLabel = null;
        }
        return volumeLabel == null ? "" : volumeLabel;
    }

    @Override // me.jahnen.libaums.core.fs.FileSystem
    public long getCapacity() {
        return this.bootSector.getTotalNumberOfSectors() * ((long) this.bootSector.getBytesPerSector());
    }

    @Override // me.jahnen.libaums.core.fs.FileSystem
    public long getOccupiedSpace() {
        return getCapacity() - getFreeSpace();
    }

    @Override // me.jahnen.libaums.core.fs.FileSystem
    public long getFreeSpace() {
        return this.fsInfoStructure.getFreeClusterCount() * ((long) this.bootSector.getBytesPerCluster());
    }

    @Override // me.jahnen.libaums.core.fs.FileSystem
    public int getChunkSize() {
        return this.bootSector.getBytesPerCluster();
    }

    /* JADX INFO: compiled from: Fat32FileSystem.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/Fat32FileSystem$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "read", "Lme/jahnen/libaums/core/fs/fat32/Fat32FileSystem;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Fat32FileSystem read(BlockDeviceDriver blockDevice) throws IOException {
            Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
            ByteBuffer buffer = ByteBuffer.allocate(512);
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            blockDevice.read(0L, buffer);
            buffer.flip();
            DefaultConstructorMarker defaultConstructorMarker = null;
            if (((char) buffer.get(82)) == 'F' && ((char) buffer.get(83)) == 'A' && ((char) buffer.get(84)) == 'T' && ((char) buffer.get(85)) == '3' && ((char) buffer.get(86)) == '2' && ((char) buffer.get(87)) == ' ' && ((char) buffer.get(88)) == ' ' && ((char) buffer.get(89)) == ' ') {
                return new Fat32FileSystem(blockDevice, buffer, defaultConstructorMarker);
            }
            return null;
        }
    }
}
