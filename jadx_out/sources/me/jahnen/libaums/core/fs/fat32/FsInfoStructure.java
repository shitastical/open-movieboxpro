package me.jahnen.libaums.core.fs.fat32;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;

/* JADX INFO: compiled from: FsInfoStructure.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\nJ\u0006\u0010\u0016\u001a\u00020\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR$\u0010\u0010\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0011\u0010\r\"\u0004\b\u0012\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FsInfoStructure;", "", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "offset", "", "(Lme/jahnen/libaums/core/driver/BlockDeviceDriver;I)V", "buffer", "Ljava/nio/ByteBuffer;", "value", "", "freeClusterCount", "getFreeClusterCount", "()J", "setFreeClusterCount", "(J)V", "lastAllocatedClusterHint", "getLastAllocatedClusterHint", "setLastAllocatedClusterHint", "decreaseClusterCount", "", "numberOfClusters", "write", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FsInfoStructure {
    private static final int FREE_COUNT_OFF = 488;
    private static final int LEAD_SIGNATURE = 1096897106;
    private static final int LEAD_SIGNATURE_OFF = 0;
    private static final int NEXT_FREE_OFFSET = 492;
    private static final int STRUCT_SIGNATURE = 1631679090;
    private static final int STRUCT_SIGNATURE_OFF = 484;
    private static final int TRAIL_SIGNATURE = -1437270016;
    private static final int TRAIL_SIGNATURE_OFF = 508;
    private final BlockDeviceDriver blockDevice;
    private final ByteBuffer buffer;
    private final int offset;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static int INVALID_VALUE = -1;
    private static final String TAG = "FsInfoStructure";

    public /* synthetic */ FsInfoStructure(BlockDeviceDriver blockDeviceDriver, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(blockDeviceDriver, i);
    }

    public static final int getINVALID_VALUE() {
        return INSTANCE.getINVALID_VALUE();
    }

    @JvmStatic
    public static final FsInfoStructure read(BlockDeviceDriver blockDeviceDriver, int i) throws IOException {
        return INSTANCE.read(blockDeviceDriver, i);
    }

    public static final void setINVALID_VALUE(int i) {
        INSTANCE.setINVALID_VALUE(i);
    }

    private FsInfoStructure(BlockDeviceDriver blockDeviceDriver, int i) throws IOException {
        this.blockDevice = blockDeviceDriver;
        this.offset = i;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(512);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(512)");
        this.buffer = byteBufferAllocate;
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        blockDeviceDriver.read(i, byteBufferAllocate);
        byteBufferAllocate.clear();
        if (byteBufferAllocate.getInt(0) != LEAD_SIGNATURE || byteBufferAllocate.getInt(STRUCT_SIGNATURE_OFF) != STRUCT_SIGNATURE || byteBufferAllocate.getInt(508) != TRAIL_SIGNATURE) {
            throw new IOException("invalid fs info structure!");
        }
    }

    public final long getFreeClusterCount() {
        return this.buffer.getInt(FREE_COUNT_OFF);
    }

    public final void setFreeClusterCount(long j) {
        this.buffer.putInt(FREE_COUNT_OFF, (int) j);
    }

    public final long getLastAllocatedClusterHint() {
        return this.buffer.getInt(NEXT_FREE_OFFSET);
    }

    public final void setLastAllocatedClusterHint(long j) {
        this.buffer.putInt(NEXT_FREE_OFFSET, (int) j);
    }

    public final void decreaseClusterCount(long numberOfClusters) {
        if (getFreeClusterCount() != INVALID_VALUE) {
            setFreeClusterCount(getFreeClusterCount() - numberOfClusters);
        }
    }

    public final void write() throws IOException {
        Log.d(TAG, "writing to device");
        this.blockDevice.write(this.offset, this.buffer);
        this.buffer.clear();
    }

    /* JADX INFO: compiled from: FsInfoStructure.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R$\u0010\u0005\u001a\u00020\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0006\u0010\u0002\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u0012*\u0004\u0018\u00010\u00110\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FsInfoStructure$Companion;", "", "()V", "FREE_COUNT_OFF", "", "INVALID_VALUE", "getINVALID_VALUE$annotations", "getINVALID_VALUE", "()I", "setINVALID_VALUE", "(I)V", "LEAD_SIGNATURE", "LEAD_SIGNATURE_OFF", "NEXT_FREE_OFFSET", "STRUCT_SIGNATURE", "STRUCT_SIGNATURE_OFF", "TAG", "", "kotlin.jvm.PlatformType", "TRAIL_SIGNATURE", "TRAIL_SIGNATURE_OFF", "read", "Lme/jahnen/libaums/core/fs/fat32/FsInfoStructure;", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "offset", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getINVALID_VALUE$annotations() {
        }

        private Companion() {
        }

        public final int getINVALID_VALUE() {
            return FsInfoStructure.INVALID_VALUE;
        }

        public final void setINVALID_VALUE(int i) {
            FsInfoStructure.INVALID_VALUE = i;
        }

        @JvmStatic
        public final FsInfoStructure read(BlockDeviceDriver blockDevice, int offset) throws IOException {
            Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
            return new FsInfoStructure(blockDevice, offset, null);
        }
    }
}
