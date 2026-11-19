package me.jahnen.libaums.core.fs.fat32;

import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;

/* JADX INFO: compiled from: ClusterChain.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 )2\u00020\u0001:\u0001)B'\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020\u0010H\u0002J\u001d\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020$H\u0000¢\u0006\u0002\b%J\u001d\u0010&\u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00032\u0006\u0010'\u001a\u00020$H\u0000¢\u0006\u0002\b(R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00030\fX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u00108B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00038@@@X\u0080\u000e¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001c¨\u0006*"}, d2 = {"Lme/jahnen/libaums/core/fs/fat32/ClusterChain;", "", "startCluster", "", "blockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "fat", "Lme/jahnen/libaums/core/fs/fat32/FAT;", "bootSector", "Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;", "(JLme/jahnen/libaums/core/driver/BlockDeviceDriver;Lme/jahnen/libaums/core/fs/fat32/FAT;Lme/jahnen/libaums/core/fs/fat32/Fat32BootSector;)V", "chain", "", "[Ljava/lang/Long;", "clusterSize", "newNumberOfClusters", "", "clusters", "getClusters", "()I", "setClusters", "(I)V", "dataAreaOffset", "newLength", "length", "getLength$libaums_release", "()J", "setLength$libaums_release", "(J)V", "getFileSystemOffset", "cluster", "clusterOffset", "read", "", "offset", "dest", "Ljava/nio/ByteBuffer;", "read$libaums_release", "write", FirebaseAnalytics.Param.SOURCE, "write$libaums_release", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ClusterChain {
    private static final String TAG = "ClusterChain";
    private final BlockDeviceDriver blockDevice;
    private Long[] chain;
    private final long clusterSize;
    private final long dataAreaOffset;
    private final FAT fat;

    public ClusterChain(long j, BlockDeviceDriver blockDevice, FAT fat, Fat32BootSector bootSector) throws IOException {
        Intrinsics.checkNotNullParameter(blockDevice, "blockDevice");
        Intrinsics.checkNotNullParameter(fat, "fat");
        Intrinsics.checkNotNullParameter(bootSector, "bootSector");
        this.blockDevice = blockDevice;
        this.fat = fat;
        String str = TAG;
        Log.d(str, "Init a cluster chain, reading from FAT");
        this.chain = fat.getChain$libaums_release(j);
        this.clusterSize = bootSector.getBytesPerCluster();
        this.dataAreaOffset = bootSector.getDataAreaOffset();
        Log.d(str, "Finished init of a cluster chain");
    }

    private final int getClusters() {
        return this.chain.length;
    }

    private final void setClusters(int i) throws IOException {
        Long[] lArrFree$libaums_release;
        int clusters = getClusters();
        if (i == clusters) {
            return;
        }
        if (i > clusters) {
            Log.d(TAG, "grow chain");
            lArrFree$libaums_release = this.fat.alloc$libaums_release(this.chain, i - clusters);
        } else {
            Log.d(TAG, "shrink chain");
            lArrFree$libaums_release = this.fat.free$libaums_release(this.chain, clusters - i);
        }
        this.chain = lArrFree$libaums_release;
    }

    public final long getLength$libaums_release() {
        return ((long) this.chain.length) * this.clusterSize;
    }

    public final void setLength$libaums_release(long j) throws IOException {
        long j2 = this.clusterSize;
        setClusters((int) (((j + j2) - 1) / j2));
    }

    public final void read$libaums_release(long offset, ByteBuffer dest) throws IOException {
        Intrinsics.checkNotNullParameter(dest, "dest");
        int iRemaining = dest.remaining();
        long j = this.clusterSize;
        int i = (int) (offset / j);
        if (offset % j != 0) {
            int i2 = (int) (offset % j);
            int iMin = Math.min(iRemaining, (int) (j - ((long) i2)));
            dest.limit(dest.position() + iMin);
            this.blockDevice.read(getFileSystemOffset(this.chain[i].longValue(), i2), dest);
            i++;
            iRemaining -= iMin;
        }
        while (iRemaining > 0) {
            int iMin2 = (int) Math.min(this.clusterSize, iRemaining);
            dest.limit(dest.position() + iMin2);
            this.blockDevice.read(getFileSystemOffset(this.chain[i].longValue(), 0), dest);
            i++;
            iRemaining -= iMin2;
        }
    }

    public final void write$libaums_release(long offset, ByteBuffer source) throws IOException {
        int iMin;
        Intrinsics.checkNotNullParameter(source, "source");
        int iRemaining = source.remaining();
        long j = this.clusterSize;
        int i = (int) (offset / j);
        if (offset % j != 0) {
            int i2 = (int) (offset % j);
            int iMin2 = Math.min(iRemaining, (int) (j - ((long) i2)));
            source.limit(source.position() + iMin2);
            this.blockDevice.write(getFileSystemOffset(this.chain[i].longValue(), i2), source);
            i++;
            iRemaining -= iMin2;
        }
        long j2 = ((long) iRemaining) / this.clusterSize;
        while (iRemaining > 0) {
            int iMin3 = 1;
            int length = this.chain.length - 1;
            int i3 = i;
            int i4 = 1;
            while (i3 < length) {
                long jLongValue = this.chain[i3].longValue() + 1;
                i3++;
                if (jLongValue != this.chain[i3].longValue()) {
                    break;
                } else {
                    i4++;
                }
            }
            int iMin4 = Math.min(i4, 4);
            long j3 = iMin4;
            if (j2 > j3) {
                iMin = (int) (this.clusterSize * j3);
                j2 -= j3;
                iMin3 = iMin4;
            } else if (j2 > 0) {
                int i5 = (int) j2;
                iMin = (int) (this.clusterSize * ((long) Math.min(i5, iMin4)));
                iMin3 = Math.min(i5, iMin4);
                j2 -= (long) iMin3;
            } else {
                iMin = iRemaining;
            }
            source.limit(source.position() + iMin);
            this.blockDevice.write(getFileSystemOffset(this.chain[i].longValue(), 0), source);
            i += iMin3;
            iRemaining -= iMin;
        }
    }

    private final long getFileSystemOffset(long cluster, int clusterOffset) {
        return this.dataAreaOffset + ((long) clusterOffset) + ((cluster - ((long) 2)) * this.clusterSize);
    }
}
