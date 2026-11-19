package me.jahnen.libaums.core.partition;

import kotlin.Metadata;

/* JADX INFO: compiled from: PartitionTypes.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lme/jahnen/libaums/core/partition/PartitionTypes;", "", "()V", "APPLE_HFS_HFS_PLUS", "", "FAT12", "FAT16", "FAT32", "ISO9660", "LINUX_EXT", "NTFS_EXFAT", "UNKNOWN", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PartitionTypes {
    public static final int APPLE_HFS_HFS_PLUS = 4;
    public static final int FAT12 = 0;
    public static final int FAT16 = 1;
    public static final int FAT32 = 2;
    public static final PartitionTypes INSTANCE = new PartitionTypes();
    public static final int ISO9660 = 5;
    public static final int LINUX_EXT = 3;
    public static final int NTFS_EXFAT = 6;
    public static final int UNKNOWN = -1;

    private PartitionTypes() {
    }
}
