package com.google.android.exoplayer.upstream;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public final class DefaultAllocator implements Allocator {
    private static final int AVAILABLE_EXTRA_CAPACITY = 100;
    private int allocatedCount;
    private Allocation[] availableAllocations;
    private int availableCount;
    private final int individualAllocationSize;
    private final byte[] initialAllocationBlock;

    public DefaultAllocator(int i) {
        this(i, 0);
    }

    public DefaultAllocator(int i, int i2) {
        Assertions.checkArgument(i > 0);
        Assertions.checkArgument(i2 >= 0);
        this.individualAllocationSize = i;
        this.availableCount = i2;
        this.availableAllocations = new Allocation[i2 + 100];
        if (i2 > 0) {
            this.initialAllocationBlock = new byte[i2 * i];
            for (int i3 = 0; i3 < i2; i3++) {
                this.availableAllocations[i3] = new Allocation(this.initialAllocationBlock, i3 * i);
            }
            return;
        }
        this.initialAllocationBlock = null;
    }

    @Override // com.google.android.exoplayer.upstream.Allocator
    public synchronized Allocation allocate() {
        Allocation allocation;
        this.allocatedCount++;
        int i = this.availableCount;
        if (i > 0) {
            Allocation[] allocationArr = this.availableAllocations;
            int i2 = i - 1;
            this.availableCount = i2;
            allocation = allocationArr[i2];
            allocationArr[i2] = null;
        } else {
            allocation = new Allocation(new byte[this.individualAllocationSize], 0);
        }
        return allocation;
    }

    @Override // com.google.android.exoplayer.upstream.Allocator
    public synchronized void release(Allocation allocation) {
        Assertions.checkArgument(allocation.data == this.initialAllocationBlock || allocation.data.length == this.individualAllocationSize);
        this.allocatedCount--;
        int i = this.availableCount;
        Allocation[] allocationArr = this.availableAllocations;
        if (i == allocationArr.length) {
            this.availableAllocations = (Allocation[]) Arrays.copyOf(allocationArr, allocationArr.length * 2);
        }
        Allocation[] allocationArr2 = this.availableAllocations;
        int i2 = this.availableCount;
        this.availableCount = i2 + 1;
        allocationArr2[i2] = allocation;
        notifyAll();
    }

    @Override // com.google.android.exoplayer.upstream.Allocator
    public synchronized void release(Allocation[] allocationArr) {
        int i = this.availableCount;
        int length = allocationArr.length + i;
        Allocation[] allocationArr2 = this.availableAllocations;
        if (length >= allocationArr2.length) {
            this.availableAllocations = (Allocation[]) Arrays.copyOf(allocationArr2, Math.max(allocationArr2.length * 2, i + allocationArr.length));
        }
        for (Allocation allocation : allocationArr) {
            Assertions.checkArgument(allocation.data == this.initialAllocationBlock || allocation.data.length == this.individualAllocationSize);
            Allocation[] allocationArr3 = this.availableAllocations;
            int i2 = this.availableCount;
            this.availableCount = i2 + 1;
            allocationArr3[i2] = allocation;
        }
        this.allocatedCount -= allocationArr.length;
        notifyAll();
    }

    @Override // com.google.android.exoplayer.upstream.Allocator
    public synchronized void trim(int i) {
        int i2 = 0;
        int iMax = Math.max(0, Util.ceilDivide(i, this.individualAllocationSize) - this.allocatedCount);
        int i3 = this.availableCount;
        if (iMax >= i3) {
            return;
        }
        if (this.initialAllocationBlock != null) {
            int i4 = i3 - 1;
            while (i2 <= i4) {
                Allocation allocation = this.availableAllocations[i2];
                if (allocation.data == this.initialAllocationBlock) {
                    i2++;
                } else {
                    Allocation allocation2 = this.availableAllocations[i4];
                    if (allocation2.data != this.initialAllocationBlock) {
                        i4--;
                    } else {
                        Allocation[] allocationArr = this.availableAllocations;
                        allocationArr[i2] = allocation2;
                        allocationArr[i4] = allocation;
                        i4--;
                        i2++;
                    }
                }
            }
            iMax = Math.max(iMax, i2);
            if (iMax >= this.availableCount) {
                return;
            }
        }
        Arrays.fill(this.availableAllocations, iMax, this.availableCount, (Object) null);
        this.availableCount = iMax;
    }

    @Override // com.google.android.exoplayer.upstream.Allocator
    public synchronized int getTotalBytesAllocated() {
        return this.allocatedCount * this.individualAllocationSize;
    }

    @Override // com.google.android.exoplayer.upstream.Allocator
    public synchronized void blockWhileTotalBytesAllocatedExceeds(int i) throws InterruptedException {
        while (getTotalBytesAllocated() > i) {
            wait();
        }
    }

    @Override // com.google.android.exoplayer.upstream.Allocator
    public int getIndividualAllocationLength() {
        return this.individualAllocationSize;
    }
}
