package me.jahnen.libaums.core.partition.mbr;

import android.util.Log;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.app.FrameMetricsAggregator;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.partition.PartitionTable;
import me.jahnen.libaums.core.partition.PartitionTableEntry;
import me.jingbin.web.WebProgress;

/* JADX INFO: compiled from: MasterBootRecord.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Lme/jahnen/libaums/core/partition/mbr/MasterBootRecord;", "Lme/jahnen/libaums/core/partition/PartitionTable;", "()V", "partitionTableEntries", "", "Lme/jahnen/libaums/core/partition/PartitionTableEntry;", "getPartitionTableEntries", "()Ljava/util/List;", "partitions", "Ljava/util/ArrayList;", "size", "", "getSize", "()I", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class MasterBootRecord implements PartitionTable {
    private static final int TABLE_ENTRY_SIZE = 16;
    private static final int TABLE_OFFSET = 446;
    private final ArrayList<PartitionTableEntry> partitions;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final MasterBootRecord$Companion$partitionTypes$1 partitionTypes = new MasterBootRecord$Companion$partitionTypes$1();
    private static final String TAG = "MasterBootRecord";

    public /* synthetic */ MasterBootRecord(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Override // me.jahnen.libaums.core.partition.PartitionTable
    public int getSize() {
        return 512;
    }

    private MasterBootRecord() {
        this.partitions = new ArrayList<>();
    }

    @Override // me.jahnen.libaums.core.partition.PartitionTable
    public List<PartitionTableEntry> getPartitionTableEntries() {
        return this.partitions;
    }

    /* JADX INFO: compiled from: MasterBootRecord.kt */
    @Metadata(d1 = {"\u0000/\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000b¨\u0006\u0010"}, d2 = {"Lme/jahnen/libaums/core/partition/mbr/MasterBootRecord$Companion;", "", "()V", "TABLE_ENTRY_SIZE", "", "TABLE_OFFSET", "TAG", "", "kotlin.jvm.PlatformType", "partitionTypes", "me/jahnen/libaums/core/partition/mbr/MasterBootRecord$Companion$partitionTypes$1", "Lme/jahnen/libaums/core/partition/mbr/MasterBootRecord$Companion$partitionTypes$1;", "read", "Lme/jahnen/libaums/core/partition/mbr/MasterBootRecord;", "buffer", "Ljava/nio/ByteBuffer;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MasterBootRecord read(ByteBuffer buffer) throws IOException {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            MasterBootRecord masterBootRecord = new MasterBootRecord(null);
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            if (buffer.limit() < 512) {
                throw new IOException("Size mismatch!");
            }
            if (buffer.get(TypedValues.PositionType.TYPE_POSITION_TYPE) != 85 || buffer.get(FrameMetricsAggregator.EVERY_DURATION) != -86) {
                Log.i(MasterBootRecord.TAG, "not a valid mbr partition table!");
                return null;
            }
            for (int i = 0; i < 4; i++) {
                int i2 = i * 16;
                byte b = buffer.get(i2 + WebProgress.MAX_DECELERATE_SPEED_DURATION);
                if (b != 0) {
                    if (b == 5 || b == 15) {
                        Log.w(MasterBootRecord.TAG, "extended partitions are currently unsupported!");
                    } else {
                        Integer num = (Integer) MasterBootRecord.partitionTypes.get((Object) Integer.valueOf(b & 255));
                        if (num == null) {
                            Log.d(MasterBootRecord.TAG, "Unknown partition type" + ((int) b));
                            num = -1;
                        }
                        masterBootRecord.partitions.add(new PartitionTableEntry(num.intValue(), buffer.getInt(i2 + 454), buffer.getInt(i2 + 458)));
                    }
                }
            }
            return masterBootRecord;
        }
    }
}
