package com.github.mjdev.libaums.partition.gpt;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.partition.PartitionTable;
import me.jahnen.libaums.core.partition.PartitionTableEntry;

/* JADX INFO: compiled from: GPT.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lcom/github/mjdev/libaums/partition/gpt/GPT;", "Lme/jahnen/libaums/core/partition/PartitionTable;", "()V", "partitionTableEntries", "", "Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "getPartitionTableEntries", "()Ljava/util/List;", "partitions", "Ljava/util/ArrayList;", "size", "", "getSize", "()I", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class GPT implements PartitionTable {
    public static final String EFI_PART = "EFI PART";
    public static final int ENTRY_SIZE = 128;
    public static final int FIRST_LBA_OFFSET = 32;
    public static final int GPT_OFFSET = 512;
    public static final int LAST_LBA_OFFSET = 40;
    private final ArrayList<PartitionTableEntry> partitions;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "GPT";

    public /* synthetic */ GPT(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private GPT() {
        this.partitions = new ArrayList<>();
    }

    @Override // me.jahnen.libaums.core.partition.PartitionTable
    public int getSize() {
        return this.partitions.size() * 128;
    }

    @Override // me.jahnen.libaums.core.partition.PartitionTable
    public List<PartitionTableEntry> getPartitionTableEntries() {
        return this.partitions;
    }

    /* JADX INFO: compiled from: GPT.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n \u000b*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/github/mjdev/libaums/partition/gpt/GPT$Companion;", "", "()V", "EFI_PART", "", "ENTRY_SIZE", "", "FIRST_LBA_OFFSET", "GPT_OFFSET", "LAST_LBA_OFFSET", "TAG", "kotlin.jvm.PlatformType", "read", "Lcom/github/mjdev/libaums/partition/gpt/GPT;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final GPT read(BlockDeviceDriver blockDevice) throws IOException {
            Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
            GPT gpt = new GPT(null);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            blockDevice.read(0L, buffer);
            byte[] bArrArray = buffer.array();
            Intrinsics.checkNotNullExpressionValue(bArrArray, "buffer.array()");
            String str = new String(bArrArray, 512, 8, Charsets.US_ASCII);
            Log.d(GPT.TAG, "EFI test string ".concat(str));
            if (!Intrinsics.areEqual(str, GPT.EFI_PART)) {
                return null;
            }
            Log.d(GPT.TAG, "EFI test string matches!");
            ByteBuffer buffer2 = ByteBuffer.allocate(17408);
            Intrinsics.checkNotNullExpressionValue(buffer2, "buffer");
            blockDevice.read(0L, buffer2);
            buffer2.order(ByteOrder.LITTLE_ENDIAN);
            for (int i = 1024; buffer2.get(i) != 0; i += 128) {
                long j = buffer2.getLong(i + 32);
                gpt.partitions.add(new PartitionTableEntry(-1, j, buffer2.getLong(i + 40) - j));
            }
            return gpt;
        }
    }
}
