package me.jahnen.libaums.core.fs.fat32;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Fat32BootSector.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\n\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\b\b\u0000\u0018\u0000 ,2\u00020\u0001:\u0001,B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020\u0004J\b\u0010+\u001a\u00020%H\u0016R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001e\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000bR\u001e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0007\u001a\u00020\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000bR\u001e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000fR\u001e\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000bR\u001e\u0010\u001f\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u000fR\u001e\u0010!\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u000fR\u001e\u0010#\u001a\u00020\u00102\u0006\u0010\u0007\u001a\u00020\u0010@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0013R\"\u0010&\u001a\u0004\u0018\u00010%2\b\u0010\u0007\u001a\u0004\u0018\u00010%@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(¨\u0006-"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;", "", "()V", "bytesPerCluster", "", "getBytesPerCluster", "()I", "<set-?>", "", "bytesPerSector", "getBytesPerSector", "()S", "dataAreaOffset", "", "getDataAreaOffset", "()J", "", "fatCount", "getFatCount", "()B", "fsInfoStartSector", "getFsInfoStartSector", "", "isFatMirrored", "()Z", "reservedSectors", "getReservedSectors", "rootDirStartCluster", "getRootDirStartCluster", "sectorsPerCluster", "getSectorsPerCluster", "sectorsPerFat", "getSectorsPerFat", "totalNumberOfSectors", "getTotalNumberOfSectors", "validFat", "getValidFat", "", "volumeLabel", "getVolumeLabel", "()Ljava/lang/String;", "getFatOffset", "fatNumber", "toString", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class Fat32BootSector {
    private static final int BYTES_PER_SECTOR_OFF = 11;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int FAT_COUNT_OFF = 16;
    private static final int FLAGS_OFF = 40;
    private static final int FS_INFO_SECTOR_OFF = 48;
    private static final int RESERVED_COUNT_OFF = 14;
    private static final int ROOT_DIR_CLUSTER_OFF = 44;
    private static final int SECTORS_PER_CLUSTER_OFF = 13;
    private static final int SECTORS_PER_FAT_OFF = 36;
    private static final int TOTAL_SECTORS_OFF = 32;
    private static final int VOLUME_LABEL_OFF = 48;
    private short bytesPerSector;
    private byte fatCount;
    private short fsInfoStartSector;
    private boolean isFatMirrored;
    private short reservedSectors;
    private long rootDirStartCluster;
    private short sectorsPerCluster;
    private long sectorsPerFat;
    private long totalNumberOfSectors;
    private byte validFat;
    private String volumeLabel;

    public /* synthetic */ Fat32BootSector(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @JvmStatic
    public static final Fat32BootSector read(ByteBuffer byteBuffer) {
        return INSTANCE.read(byteBuffer);
    }

    private Fat32BootSector() {
    }

    public final short getBytesPerSector() {
        return this.bytesPerSector;
    }

    public final short getSectorsPerCluster() {
        return this.sectorsPerCluster;
    }

    public final short getReservedSectors() {
        return this.reservedSectors;
    }

    public final byte getFatCount() {
        return this.fatCount;
    }

    public final long getTotalNumberOfSectors() {
        return this.totalNumberOfSectors;
    }

    public final long getSectorsPerFat() {
        return this.sectorsPerFat;
    }

    public final long getRootDirStartCluster() {
        return this.rootDirStartCluster;
    }

    public final short getFsInfoStartSector() {
        return this.fsInfoStartSector;
    }

    /* JADX INFO: renamed from: isFatMirrored, reason: from getter */
    public final boolean getIsFatMirrored() {
        return this.isFatMirrored;
    }

    public final byte getValidFat() {
        return this.validFat;
    }

    public final String getVolumeLabel() {
        return this.volumeLabel;
    }

    public final int getBytesPerCluster() {
        return this.sectorsPerCluster * this.bytesPerSector;
    }

    public final long getDataAreaOffset() {
        return getFatOffset(0) + (((long) this.fatCount) * this.sectorsPerFat * ((long) this.bytesPerSector));
    }

    public final long getFatOffset(int fatNumber) {
        return ((long) this.bytesPerSector) * (((long) this.reservedSectors) + (((long) fatNumber) * this.sectorsPerFat));
    }

    public String toString() {
        return "Fat32BootSector{bytesPerSector=" + ((int) this.bytesPerSector) + ", sectorsPerCluster=" + ((int) this.sectorsPerCluster) + ", reservedSectors=" + ((int) this.reservedSectors) + ", fatCount=" + ((int) this.fatCount) + ", totalNumberOfSectors=" + this.totalNumberOfSectors + ", sectorsPerFat=" + this.sectorsPerFat + ", rootDirStartCluster=" + this.rootDirStartCluster + ", fsInfoStartSector=" + ((int) this.fsInfoStartSector) + ", fatMirrored=" + this.isFatMirrored + ", validFat=" + ((int) this.validFat) + ", volumeLabel='" + this.volumeLabel + "'}";
    }

    /* JADX INFO: compiled from: Fat32BootSector.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector$Companion;", "", "()V", "BYTES_PER_SECTOR_OFF", "", "FAT_COUNT_OFF", "FLAGS_OFF", "FS_INFO_SECTOR_OFF", "RESERVED_COUNT_OFF", "ROOT_DIR_CLUSTER_OFF", "SECTORS_PER_CLUSTER_OFF", "SECTORS_PER_FAT_OFF", "TOTAL_SECTORS_OFF", "VOLUME_LABEL_OFF", "read", "Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;", "buffer", "Ljava/nio/ByteBuffer;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final Fat32BootSector read(ByteBuffer buffer) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            Fat32BootSector fat32BootSector = new Fat32BootSector(null);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            fat32BootSector.bytesPerSector = buffer.getShort(11);
            fat32BootSector.sectorsPerCluster = (short) (buffer.get(13) & 255);
            fat32BootSector.reservedSectors = buffer.getShort(14);
            fat32BootSector.fatCount = buffer.get(16);
            fat32BootSector.totalNumberOfSectors = ((long) buffer.getInt(32)) & 4294967295L;
            fat32BootSector.sectorsPerFat = ((long) buffer.getInt(36)) & 4294967295L;
            fat32BootSector.rootDirStartCluster = ((long) buffer.getInt(44)) & 4294967295L;
            fat32BootSector.fsInfoStartSector = buffer.getShort(48);
            short s = buffer.getShort(40);
            fat32BootSector.isFatMirrored = (s & 128) == 0;
            fat32BootSector.validFat = (byte) (s & 7);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 11; i++) {
                byte b = buffer.get(i + 48);
                if (b == 0) {
                    break;
                }
                sb.append((char) b);
            }
            fat32BootSector.volumeLabel = sb.toString();
            return fat32BootSector;
        }
    }
}
