package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.upstream.Allocation;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;

/* JADX INFO: loaded from: classes4.dex */
final class RollingSampleBuffer {
    private static final int INITIAL_SCRATCH_SIZE = 32;
    private final int allocationLength;
    private final Allocator allocator;
    private final LinkedBlockingDeque<Allocation> dataQueue;
    private final SampleExtrasHolder extrasHolder;
    private final InfoQueue infoQueue;
    private Allocation lastAllocation;
    private int lastAllocationOffset;
    private final ParsableByteArray scratch;
    private long totalBytesDropped;
    private long totalBytesWritten;

    public RollingSampleBuffer(Allocator allocator) {
        this.allocator = allocator;
        int individualAllocationLength = allocator.getIndividualAllocationLength();
        this.allocationLength = individualAllocationLength;
        this.infoQueue = new InfoQueue();
        this.dataQueue = new LinkedBlockingDeque<>();
        this.extrasHolder = new SampleExtrasHolder();
        this.scratch = new ParsableByteArray(32);
        this.lastAllocationOffset = individualAllocationLength;
    }

    public void clear() {
        this.infoQueue.clear();
        Allocator allocator = this.allocator;
        LinkedBlockingDeque<Allocation> linkedBlockingDeque = this.dataQueue;
        allocator.release((Allocation[]) linkedBlockingDeque.toArray(new Allocation[linkedBlockingDeque.size()]));
        this.dataQueue.clear();
        this.totalBytesDropped = 0L;
        this.totalBytesWritten = 0L;
        this.lastAllocation = null;
        this.lastAllocationOffset = this.allocationLength;
    }

    public int getWriteIndex() {
        return this.infoQueue.getWriteIndex();
    }

    public void discardUpstreamSamples(int i) {
        long jDiscardUpstreamSamples = this.infoQueue.discardUpstreamSamples(i);
        this.totalBytesWritten = jDiscardUpstreamSamples;
        dropUpstreamFrom(jDiscardUpstreamSamples);
    }

    private void dropUpstreamFrom(long j) {
        int i = (int) (j - this.totalBytesDropped);
        int i2 = this.allocationLength;
        int i3 = i / i2;
        int i4 = i % i2;
        int size = this.dataQueue.size() - i3;
        int i5 = size - 1;
        if (i4 != 0) {
            size = i5;
        }
        for (int i6 = 0; i6 < size; i6++) {
            this.allocator.release(this.dataQueue.removeLast());
        }
        this.lastAllocation = this.dataQueue.peekLast();
        if (i4 == 0) {
            i4 = this.allocationLength;
        }
        this.lastAllocationOffset = i4;
    }

    public int getReadIndex() {
        return this.infoQueue.getReadIndex();
    }

    public boolean peekSample(SampleHolder sampleHolder) {
        return this.infoQueue.peekSample(sampleHolder, this.extrasHolder);
    }

    public void skipSample() {
        dropDownstreamTo(this.infoQueue.moveToNextSample());
    }

    public boolean skipToKeyframeBefore(long j) {
        long jSkipToKeyframeBefore = this.infoQueue.skipToKeyframeBefore(j);
        if (jSkipToKeyframeBefore == -1) {
            return false;
        }
        dropDownstreamTo(jSkipToKeyframeBefore);
        return true;
    }

    public boolean readSample(SampleHolder sampleHolder) {
        if (!this.infoQueue.peekSample(sampleHolder, this.extrasHolder)) {
            return false;
        }
        if (sampleHolder.isEncrypted()) {
            readEncryptionData(sampleHolder, this.extrasHolder);
        }
        sampleHolder.ensureSpaceForWrite(sampleHolder.size);
        readData(this.extrasHolder.offset, sampleHolder.data, sampleHolder.size);
        dropDownstreamTo(this.infoQueue.moveToNextSample());
        return true;
    }

    private void readEncryptionData(SampleHolder sampleHolder, SampleExtrasHolder sampleExtrasHolder) {
        int unsignedShort;
        long j = sampleExtrasHolder.offset;
        readData(j, this.scratch.data, 1);
        long j2 = j + 1;
        byte b = this.scratch.data[0];
        boolean z = (b & 128) != 0;
        int i = b & 127;
        if (sampleHolder.cryptoInfo.iv == null) {
            sampleHolder.cryptoInfo.iv = new byte[16];
        }
        readData(j2, sampleHolder.cryptoInfo.iv, i);
        long j3 = j2 + ((long) i);
        if (z) {
            readData(j3, this.scratch.data, 2);
            j3 += 2;
            this.scratch.setPosition(0);
            unsignedShort = this.scratch.readUnsignedShort();
        } else {
            unsignedShort = 1;
        }
        int[] iArr = sampleHolder.cryptoInfo.numBytesOfClearData;
        if (iArr == null || iArr.length < unsignedShort) {
            iArr = new int[unsignedShort];
        }
        int[] iArr2 = iArr;
        int[] iArr3 = sampleHolder.cryptoInfo.numBytesOfEncryptedData;
        if (iArr3 == null || iArr3.length < unsignedShort) {
            iArr3 = new int[unsignedShort];
        }
        int[] iArr4 = iArr3;
        if (z) {
            int i2 = unsignedShort * 6;
            ensureCapacity(this.scratch, i2);
            readData(j3, this.scratch.data, i2);
            j3 += (long) i2;
            this.scratch.setPosition(0);
            for (int i3 = 0; i3 < unsignedShort; i3++) {
                iArr2[i3] = this.scratch.readUnsignedShort();
                iArr4[i3] = this.scratch.readUnsignedIntToInt();
            }
        } else {
            iArr2[0] = 0;
            iArr4[0] = sampleHolder.size - ((int) (j3 - sampleExtrasHolder.offset));
        }
        sampleHolder.cryptoInfo.set(unsignedShort, iArr2, iArr4, sampleExtrasHolder.encryptionKeyId, sampleHolder.cryptoInfo.iv, 1);
        int i4 = (int) (j3 - sampleExtrasHolder.offset);
        sampleExtrasHolder.offset += (long) i4;
        sampleHolder.size -= i4;
    }

    private void readData(long j, ByteBuffer byteBuffer, int i) {
        while (i > 0) {
            dropDownstreamTo(j);
            int i2 = (int) (j - this.totalBytesDropped);
            int iMin = Math.min(i, this.allocationLength - i2);
            Allocation allocationPeek = this.dataQueue.peek();
            byteBuffer.put(allocationPeek.data, allocationPeek.translateOffset(i2), iMin);
            j += (long) iMin;
            i -= iMin;
        }
    }

    private void readData(long j, byte[] bArr, int i) {
        int i2 = 0;
        while (i2 < i) {
            dropDownstreamTo(j);
            int i3 = (int) (j - this.totalBytesDropped);
            int iMin = Math.min(i - i2, this.allocationLength - i3);
            Allocation allocationPeek = this.dataQueue.peek();
            System.arraycopy(allocationPeek.data, allocationPeek.translateOffset(i3), bArr, i2, iMin);
            j += (long) iMin;
            i2 += iMin;
        }
    }

    private void dropDownstreamTo(long j) {
        int i = ((int) (j - this.totalBytesDropped)) / this.allocationLength;
        for (int i2 = 0; i2 < i; i2++) {
            this.allocator.release(this.dataQueue.remove());
            this.totalBytesDropped += (long) this.allocationLength;
        }
    }

    private static void ensureCapacity(ParsableByteArray parsableByteArray, int i) {
        if (parsableByteArray.limit() < i) {
            parsableByteArray.reset(new byte[i], i);
        }
    }

    public long getWritePosition() {
        return this.totalBytesWritten;
    }

    public int appendData(DataSource dataSource, int i, boolean z) throws IOException {
        int i2 = dataSource.read(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), prepareForAppend(i));
        if (i2 == -1) {
            if (z) {
                return -1;
            }
            throw new EOFException();
        }
        this.lastAllocationOffset += i2;
        this.totalBytesWritten += (long) i2;
        return i2;
    }

    public int appendData(ExtractorInput extractorInput, int i, boolean z) throws InterruptedException, IOException {
        int i2 = extractorInput.read(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), prepareForAppend(i));
        if (i2 == -1) {
            if (z) {
                return -1;
            }
            throw new EOFException();
        }
        this.lastAllocationOffset += i2;
        this.totalBytesWritten += (long) i2;
        return i2;
    }

    public void appendData(ParsableByteArray parsableByteArray, int i) {
        while (i > 0) {
            int iPrepareForAppend = prepareForAppend(i);
            parsableByteArray.readBytes(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), iPrepareForAppend);
            this.lastAllocationOffset += iPrepareForAppend;
            this.totalBytesWritten += (long) iPrepareForAppend;
            i -= iPrepareForAppend;
        }
    }

    public void commitSample(long j, int i, long j2, int i2, byte[] bArr) {
        this.infoQueue.commitSample(j, i, j2, i2, bArr);
    }

    private int prepareForAppend(int i) {
        if (this.lastAllocationOffset == this.allocationLength) {
            this.lastAllocationOffset = 0;
            Allocation allocationAllocate = this.allocator.allocate();
            this.lastAllocation = allocationAllocate;
            this.dataQueue.add(allocationAllocate);
        }
        return Math.min(i, this.allocationLength - this.lastAllocationOffset);
    }

    private static final class InfoQueue {
        private static final int SAMPLE_CAPACITY_INCREMENT = 1000;
        private int absoluteReadIndex;
        private int queueSize;
        private int relativeReadIndex;
        private int relativeWriteIndex;
        private int capacity = 1000;
        private long[] offsets = new long[1000];
        private long[] timesUs = new long[1000];
        private int[] flags = new int[1000];
        private int[] sizes = new int[1000];
        private byte[][] encryptionKeys = new byte[1000][];

        public void clear() {
            this.absoluteReadIndex = 0;
            this.relativeReadIndex = 0;
            this.relativeWriteIndex = 0;
            this.queueSize = 0;
        }

        public int getWriteIndex() {
            return this.absoluteReadIndex + this.queueSize;
        }

        public long discardUpstreamSamples(int i) {
            int writeIndex = getWriteIndex() - i;
            Assertions.checkArgument(writeIndex >= 0 && writeIndex <= this.queueSize);
            if (writeIndex == 0) {
                if (this.absoluteReadIndex == 0) {
                    return 0L;
                }
                int i2 = this.relativeWriteIndex;
                if (i2 == 0) {
                    i2 = this.capacity;
                }
                int i3 = i2 - 1;
                return this.offsets[i3] + ((long) this.sizes[i3]);
            }
            this.queueSize -= writeIndex;
            int i4 = this.relativeWriteIndex;
            int i5 = this.capacity;
            int i6 = ((i4 + i5) - writeIndex) % i5;
            this.relativeWriteIndex = i6;
            return this.offsets[i6];
        }

        public int getReadIndex() {
            return this.absoluteReadIndex;
        }

        public synchronized boolean peekSample(SampleHolder sampleHolder, SampleExtrasHolder sampleExtrasHolder) {
            if (this.queueSize == 0) {
                return false;
            }
            sampleHolder.timeUs = this.timesUs[this.relativeReadIndex];
            sampleHolder.size = this.sizes[this.relativeReadIndex];
            sampleHolder.flags = this.flags[this.relativeReadIndex];
            sampleExtrasHolder.offset = this.offsets[this.relativeReadIndex];
            sampleExtrasHolder.encryptionKeyId = this.encryptionKeys[this.relativeReadIndex];
            return true;
        }

        public synchronized long moveToNextSample() {
            int i;
            int i2;
            i = this.queueSize - 1;
            this.queueSize = i;
            i2 = this.relativeReadIndex;
            int i3 = i2 + 1;
            this.relativeReadIndex = i3;
            this.absoluteReadIndex++;
            if (i3 == this.capacity) {
                this.relativeReadIndex = 0;
            }
            return i > 0 ? this.offsets[this.relativeReadIndex] : ((long) this.sizes[i2]) + this.offsets[i2];
        }

        public synchronized long skipToKeyframeBefore(long j) {
            if (this.queueSize != 0) {
                long[] jArr = this.timesUs;
                int i = this.relativeReadIndex;
                if (j >= jArr[i]) {
                    int i2 = this.relativeWriteIndex;
                    if (i2 == 0) {
                        i2 = this.capacity;
                    }
                    if (j > jArr[i2 - 1]) {
                        return -1L;
                    }
                    int i3 = 0;
                    int i4 = -1;
                    while (i != this.relativeWriteIndex && this.timesUs[i] <= j) {
                        if ((this.flags[i] & 1) != 0) {
                            i4 = i3;
                        }
                        i = (i + 1) % this.capacity;
                        i3++;
                    }
                    if (i4 == -1) {
                        return -1L;
                    }
                    this.queueSize -= i4;
                    int i5 = (this.relativeReadIndex + i4) % this.capacity;
                    this.relativeReadIndex = i5;
                    this.absoluteReadIndex += i4;
                    return this.offsets[i5];
                }
            }
            return -1L;
        }

        public synchronized void commitSample(long j, int i, long j2, int i2, byte[] bArr) {
            long[] jArr = this.timesUs;
            int i3 = this.relativeWriteIndex;
            jArr[i3] = j;
            long[] jArr2 = this.offsets;
            jArr2[i3] = j2;
            this.sizes[i3] = i2;
            this.flags[i3] = i;
            this.encryptionKeys[i3] = bArr;
            int i4 = this.queueSize + 1;
            this.queueSize = i4;
            int i5 = this.capacity;
            if (i4 == i5) {
                int i6 = i5 + 1000;
                long[] jArr3 = new long[i6];
                long[] jArr4 = new long[i6];
                int[] iArr = new int[i6];
                int[] iArr2 = new int[i6];
                byte[][] bArr2 = new byte[i6][];
                int i7 = this.relativeReadIndex;
                int i8 = i5 - i7;
                System.arraycopy(jArr2, i7, jArr3, 0, i8);
                System.arraycopy(this.timesUs, this.relativeReadIndex, jArr4, 0, i8);
                System.arraycopy(this.flags, this.relativeReadIndex, iArr, 0, i8);
                System.arraycopy(this.sizes, this.relativeReadIndex, iArr2, 0, i8);
                System.arraycopy(this.encryptionKeys, this.relativeReadIndex, bArr2, 0, i8);
                int i9 = this.relativeReadIndex;
                System.arraycopy(this.offsets, 0, jArr3, i8, i9);
                System.arraycopy(this.timesUs, 0, jArr4, i8, i9);
                System.arraycopy(this.flags, 0, iArr, i8, i9);
                System.arraycopy(this.sizes, 0, iArr2, i8, i9);
                System.arraycopy(this.encryptionKeys, 0, bArr2, i8, i9);
                this.offsets = jArr3;
                this.timesUs = jArr4;
                this.flags = iArr;
                this.sizes = iArr2;
                this.encryptionKeys = bArr2;
                this.relativeReadIndex = 0;
                int i10 = this.capacity;
                this.relativeWriteIndex = i10;
                this.queueSize = i10;
                this.capacity = i6;
            } else {
                int i11 = i3 + 1;
                this.relativeWriteIndex = i11;
                if (i11 == i5) {
                    this.relativeWriteIndex = 0;
                }
            }
        }
    }

    private static final class SampleExtrasHolder {
        public byte[] encryptionKeyId;
        public long offset;

        private SampleExtrasHolder() {
        }
    }
}
