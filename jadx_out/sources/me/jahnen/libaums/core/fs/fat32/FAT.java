package me.jahnen.libaums.core.fs.fat32;

import android.util.Log;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.util.LRUCache;

/* JADX INFO: compiled from: FAT.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\u0016\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 \u001d2\u00020\u0001:\u0001\u001dB\u001f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ+\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000b0\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0015\u0010\u0016J+\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000b0\f2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0018\u0010\u0016J\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\f2\u0006\u0010\u001a\u001a\u00020\u000bH\u0000¢\u0006\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\t\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/FAT;", "", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "bootSector", "Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;", "fsInfoStructure", "Lme/jahnen/libaums/core/fs/fat32/FsInfoStructure;", "(Lme/jahnen/libaums/core/driver/BlockDeviceDriver;Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;Lme/jahnen/libaums/core/fs/fat32/FsInfoStructure;)V", "cache", "Lme/jahnen/libaums/core/util/LRUCache;", "", "", "fatNumbers", "", "fatOffset", "", "alloc", "chain", "numberOfClusters", "", "alloc$libaums_release", "([Ljava/lang/Long;I)[Ljava/lang/Long;", "free", "free$libaums_release", "getChain", "startCluster", "getChain$libaums_release", "(J)[Ljava/lang/Long;", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class FAT {
    private static final int FAT32_EOF_CLUSTER = 268435448;
    private final BlockDeviceDriver blockDevice;
    private final LRUCache<Long, Long[]> cache;
    private int[] fatNumbers;
    private final long[] fatOffset;
    private final FsInfoStructure fsInfoStructure;
    private static final String TAG = "FAT";

    public FAT(BlockDeviceDriver blockDevice, Fat32BootSector bootSector, FsInfoStructure fsInfoStructure) {
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        Intrinsics.checkNotNullParameter(bootSector, "bootSector");
        Intrinsics.checkNotNullParameter(fsInfoStructure, "fsInfoStructure");
        this.blockDevice = blockDevice;
        this.fsInfoStructure = fsInfoStructure;
        this.cache = new LRUCache<>(64);
        if (!bootSector.getIsFatMirrored()) {
            int validFat = bootSector.getValidFat();
            this.fatNumbers = new int[]{validFat};
            Log.i(TAG, "fat is not mirrored, fat " + validFat + " is valid");
        } else {
            int fatCount = bootSector.getFatCount();
            this.fatNumbers = new int[fatCount];
            for (int i = 0; i < fatCount; i++) {
                this.fatNumbers[i] = i;
            }
            Log.i(TAG, "fat is mirrored, fat count: " + fatCount);
        }
        long[] jArr = new long[this.fatNumbers.length];
        this.fatOffset = jArr;
        int length = jArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            this.fatOffset[i2] = bootSector.getFatOffset(this.fatNumbers[i2]);
        }
    }

    public final Long[] getChain$libaums_release(long startCluster) throws IOException {
        if (startCluster == 0) {
            return new Long[0];
        }
        Long[] lArr = this.cache.get(Long.valueOf(startCluster));
        if (lArr != null) {
            return lArr;
        }
        ArrayList arrayList = new ArrayList();
        int blockSize = this.blockDevice.getBlockSize() * 2;
        ByteBuffer buffer = ByteBuffer.allocate(blockSize);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        long j = -1;
        long j2 = startCluster;
        do {
            arrayList.add(Long.valueOf(j2));
            long j3 = this.fatOffset[0];
            long j4 = j2 * ((long) 4);
            long j5 = blockSize;
            long j6 = ((j3 + j4) / j5) * j5;
            long j7 = (j3 + j4) % j5;
            if (j != j6) {
                buffer.clear();
                BlockDeviceDriver blockDeviceDriver = this.blockDevice;
                Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                blockDeviceDriver.read(j6, buffer);
                j = j6;
            }
            j2 = buffer.getInt((int) j7) & 268435455;
        } while (j2 < 268435448);
        Long[] lArr2 = (Long[]) arrayList.toArray(new Long[0]);
        this.cache.put(Long.valueOf(startCluster), lArr2);
        return lArr2;
    }

    public final Long[] alloc$libaums_release(Long[] chain, int numberOfClusters) throws IOException {
        String str;
        long j;
        Intrinsics.checkNotNullParameter(chain, "chain");
        ArrayList arrayList = new ArrayList(chain.length + numberOfClusters);
        arrayList.addAll(Arrays.asList(Arrays.copyOf(chain, chain.length)));
        int blockSize = this.blockDevice.getBlockSize() * 2;
        ByteBuffer buffer = ByteBuffer.allocate(blockSize);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        char c = 0;
        long jLongValue = !(chain.length == 0) ? chain[chain.length - 1].longValue() : -1L;
        long lastAllocatedClusterHint = this.fsInfoStructure.getLastAllocatedClusterHint();
        if (lastAllocatedClusterHint == FsInfoStructure.INSTANCE.getINVALID_VALUE()) {
            lastAllocatedClusterHint = 2;
        }
        int i = numberOfClusters;
        long j2 = -1;
        while (true) {
            str = "buffer";
            if (i <= 0) {
                break;
            }
            lastAllocatedClusterHint++;
            long j3 = this.fatOffset[c];
            long j4 = j2;
            long j5 = ((long) 4) * lastAllocatedClusterHint;
            ArrayList arrayList2 = arrayList;
            long j6 = blockSize;
            long j7 = jLongValue;
            long j8 = ((j3 + j5) / j6) * j6;
            long j9 = (j3 + j5) % j6;
            if (j4 != j8) {
                buffer.clear();
                BlockDeviceDriver blockDeviceDriver = this.blockDevice;
                Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                blockDeviceDriver.read(j8, buffer);
                j4 = j8;
            }
            if (buffer.getInt((int) j9) == 0) {
                arrayList2.add(Long.valueOf(lastAllocatedClusterHint));
                i--;
            }
            arrayList = arrayList2;
            j2 = j4;
            jLongValue = j7;
            c = 0;
        }
        long j10 = j2;
        ArrayList arrayList3 = arrayList;
        if (((int) jLongValue) != -1) {
            long j11 = this.fatOffset[0];
            long j12 = jLongValue * ((long) 4);
            long j13 = blockSize;
            j = ((j11 + j12) / j13) * j13;
            long j14 = (j11 + j12) % j13;
            if (j10 != j) {
                buffer.clear();
                BlockDeviceDriver blockDeviceDriver2 = this.blockDevice;
                Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                blockDeviceDriver2.read(j, buffer);
            } else {
                j = j10;
            }
            buffer.putInt((int) j14, (int) ((Number) arrayList3.get(chain.length)).longValue());
        } else {
            j = j10;
        }
        int length = chain.length;
        int size = arrayList3.size() - 1;
        while (length < size) {
            Object obj = arrayList3.get(length);
            Intrinsics.checkNotNullExpressionValue(obj, "result[i]");
            long jLongValue2 = ((Number) obj).longValue();
            long j15 = this.fatOffset[0];
            long j16 = jLongValue2 * ((long) 4);
            int i2 = size;
            String str2 = str;
            long j17 = blockSize;
            long j18 = ((j15 + j16) / j17) * j17;
            long j19 = (j15 + j16) % j17;
            if (j != j18) {
                buffer.clear();
                BlockDeviceDriver blockDeviceDriver3 = this.blockDevice;
                str = str2;
                Intrinsics.checkNotNullExpressionValue(buffer, str);
                blockDeviceDriver3.write(j, buffer);
                buffer.clear();
                this.blockDevice.read(j18, buffer);
                j = j18;
            } else {
                str = str2;
            }
            length++;
            buffer.putInt((int) j19, (int) ((Number) arrayList3.get(length)).longValue());
            size = i2;
        }
        Object obj2 = arrayList3.get(arrayList3.size() - 1);
        Intrinsics.checkNotNullExpressionValue(obj2, "result[result.size - 1]");
        long jLongValue3 = ((Number) obj2).longValue();
        long j20 = this.fatOffset[0];
        long j21 = ((long) 4) * jLongValue3;
        long j22 = blockSize;
        String str3 = str;
        long j23 = ((j20 + j21) / j22) * j22;
        long j24 = (j20 + j21) % j22;
        if (j != j23) {
            buffer.clear();
            BlockDeviceDriver blockDeviceDriver4 = this.blockDevice;
            Intrinsics.checkNotNullExpressionValue(buffer, str3);
            blockDeviceDriver4.write(j, buffer);
            buffer.clear();
            this.blockDevice.read(j23, buffer);
        }
        buffer.putInt((int) j24, FAT32_EOF_CLUSTER);
        buffer.clear();
        BlockDeviceDriver blockDeviceDriver5 = this.blockDevice;
        Intrinsics.checkNotNullExpressionValue(buffer, str3);
        blockDeviceDriver5.write(j23, buffer);
        this.fsInfoStructure.setLastAllocatedClusterHint(jLongValue3);
        this.fsInfoStructure.decreaseClusterCount(numberOfClusters);
        this.fsInfoStructure.write();
        Log.i(TAG, "allocating clusters finished");
        Long[] lArr = (Long[]) arrayList3.toArray(new Long[0]);
        this.cache.put(lArr[0], lArr);
        return lArr;
    }

    public final Long[] free$libaums_release(Long[] chain, int numberOfClusters) throws IOException {
        Long[] lArr;
        Long[] chain2 = chain;
        Intrinsics.checkNotNullParameter(chain2, "chain");
        int length = chain2.length - numberOfClusters;
        int blockSize = this.blockDevice.getBlockSize() * 2;
        ByteBuffer buffer = ByteBuffer.allocate(blockSize);
        buffer.order(ByteOrder.LITTLE_ENDIAN);
        if (length < 0) {
            throw new IllegalStateException("trying to remove more clusters in chain than currently exist!".toString());
        }
        int length2 = chain2.length;
        long j = -1;
        int i = length;
        while (i < length2) {
            long jLongValue = chain2[i].longValue();
            long j2 = this.fatOffset[0];
            long j3 = jLongValue * ((long) 4);
            long j4 = blockSize;
            long j5 = ((j2 + j3) / j4) * j4;
            long j6 = (j2 + j3) % j4;
            if (j != j5) {
                if (((int) j) != -1) {
                    buffer.clear();
                    BlockDeviceDriver blockDeviceDriver = this.blockDevice;
                    Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                    blockDeviceDriver.write(j, buffer);
                }
                buffer.clear();
                BlockDeviceDriver blockDeviceDriver2 = this.blockDevice;
                Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                blockDeviceDriver2.read(j5, buffer);
                j = j5;
            }
            buffer.putInt((int) j6, 0);
            i++;
            chain2 = chain;
        }
        if (length > 0) {
            lArr = chain;
            long jLongValue2 = lArr[length - 1].longValue();
            long j7 = this.fatOffset[0];
            long j8 = jLongValue2 * ((long) 4);
            long j9 = blockSize;
            long j10 = ((j7 + j8) / j9) * j9;
            long j11 = (j7 + j8) % j9;
            if (j != j10) {
                buffer.clear();
                BlockDeviceDriver blockDeviceDriver3 = this.blockDevice;
                Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
                blockDeviceDriver3.write(j, buffer);
                buffer.clear();
                this.blockDevice.read(j10, buffer);
            }
            buffer.putInt((int) j11, FAT32_EOF_CLUSTER);
            buffer.clear();
            BlockDeviceDriver blockDeviceDriver4 = this.blockDevice;
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            blockDeviceDriver4.write(j10, buffer);
        } else {
            lArr = chain;
            buffer.clear();
            BlockDeviceDriver blockDeviceDriver5 = this.blockDevice;
            Intrinsics.checkNotNullExpressionValue(buffer, "buffer");
            blockDeviceDriver5.write(j, buffer);
        }
        Log.i(TAG, "freed " + numberOfClusters + " clusters");
        this.fsInfoStructure.decreaseClusterCount((long) (-numberOfClusters));
        this.fsInfoStructure.write();
        Long[] arr = (Long[]) Arrays.copyOfRange(lArr, 0, length);
        Intrinsics.checkNotNullExpressionValue(arr, "arr");
        if (!(arr.length == 0)) {
            this.cache.put(arr[0], arr);
        }
        return arr;
    }
}
