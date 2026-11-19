package me.jahnen.libaums.core.partition;

import kotlin.Metadata;

/* JADX INFO: compiled from: PartitionTableEntry.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007R$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR$\u0010\u0002\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R$\u0010\u0006\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\n\"\u0004\b\u0012\u0010\f¨\u0006\u0013"}, d2 = {"Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "", "partitionType", "", "logicalBlockAddress", "", "totalNumberOfSectors", "(IJJ)V", "<set-?>", "getLogicalBlockAddress", "()J", "setLogicalBlockAddress$libaums_release", "(J)V", "getPartitionType", "()I", "setPartitionType$libaums_release", "(I)V", "getTotalNumberOfSectors", "setTotalNumberOfSectors$libaums_release", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PartitionTableEntry {
    private long logicalBlockAddress;
    private int partitionType;
    private long totalNumberOfSectors;

    public PartitionTableEntry(int i, long j, long j2) {
        this.partitionType = i;
        this.logicalBlockAddress = j;
        this.totalNumberOfSectors = j2;
    }

    public final int getPartitionType() {
        return this.partitionType;
    }

    public final void setPartitionType$libaums_release(int i) {
        this.partitionType = i;
    }

    public final long getLogicalBlockAddress() {
        return this.logicalBlockAddress;
    }

    public final void setLogicalBlockAddress$libaums_release(long j) {
        this.logicalBlockAddress = j;
    }

    public final long getTotalNumberOfSectors() {
        return this.totalNumberOfSectors;
    }

    public final void setTotalNumberOfSectors$libaums_release(long j) {
        this.totalNumberOfSectors = j;
    }
}
