package com.google.android.exoplayer.util.extensions;

import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.extensions.InputBuffer;
import com.google.android.exoplayer.util.extensions.OutputBuffer;
import java.lang.Exception;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes4.dex */
public abstract class SimpleDecoder<I extends InputBuffer, O extends OutputBuffer, E extends Exception> extends Thread implements Decoder<I, O, E> {
    private int availableInputBufferCount;
    private final I[] availableInputBuffers;
    private int availableOutputBufferCount;
    private final O[] availableOutputBuffers;
    private I dequeuedInputBuffer;
    private E exception;
    private boolean flushed;
    private final Object lock = new Object();
    private final LinkedList<I> queuedInputBuffers = new LinkedList<>();
    private final LinkedList<O> queuedOutputBuffers = new LinkedList<>();
    private boolean released;

    public interface EventListener<E> {
        void onDecoderError(E e);
    }

    protected abstract I createInputBuffer();

    protected abstract O createOutputBuffer();

    protected abstract E decode(I i, O o, boolean z);

    protected SimpleDecoder(I[] iArr, O[] oArr) {
        this.availableInputBuffers = iArr;
        this.availableInputBufferCount = iArr.length;
        for (int i = 0; i < this.availableInputBufferCount; i++) {
            ((I[]) this.availableInputBuffers)[i] = createInputBuffer();
        }
        this.availableOutputBuffers = oArr;
        this.availableOutputBufferCount = oArr.length;
        for (int i2 = 0; i2 < this.availableOutputBufferCount; i2++) {
            ((O[]) this.availableOutputBuffers)[i2] = createOutputBuffer();
        }
    }

    protected final void setInitialInputBufferSize(int i) {
        int i2 = 0;
        Assertions.checkState(this.availableInputBufferCount == this.availableInputBuffers.length);
        while (true) {
            I[] iArr = this.availableInputBuffers;
            if (i2 >= iArr.length) {
                return;
            }
            iArr[i2].sampleHolder.ensureSpaceForWrite(i);
            i2++;
        }
    }

    @Override // com.google.android.exoplayer.util.extensions.Decoder
    public final I dequeueInputBuffer() throws Exception {
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkState(this.dequeuedInputBuffer == null);
            int i = this.availableInputBufferCount;
            if (i == 0) {
                return null;
            }
            I[] iArr = this.availableInputBuffers;
            int i2 = i - 1;
            this.availableInputBufferCount = i2;
            I i3 = iArr[i2];
            i3.reset();
            this.dequeuedInputBuffer = i3;
            return i3;
        }
    }

    @Override // com.google.android.exoplayer.util.extensions.Decoder
    public final void queueInputBuffer(I i) throws Exception {
        synchronized (this.lock) {
            maybeThrowException();
            Assertions.checkArgument(i == this.dequeuedInputBuffer);
            this.queuedInputBuffers.addLast(i);
            maybeNotifyDecodeLoop();
            this.dequeuedInputBuffer = null;
        }
    }

    @Override // com.google.android.exoplayer.util.extensions.Decoder
    public final O dequeueOutputBuffer() throws Exception {
        synchronized (this.lock) {
            maybeThrowException();
            if (this.queuedOutputBuffers.isEmpty()) {
                return null;
            }
            return this.queuedOutputBuffers.removeFirst();
        }
    }

    protected void releaseOutputBuffer(O o) {
        synchronized (this.lock) {
            O[] oArr = this.availableOutputBuffers;
            int i = this.availableOutputBufferCount;
            this.availableOutputBufferCount = i + 1;
            oArr[i] = o;
            maybeNotifyDecodeLoop();
        }
    }

    @Override // com.google.android.exoplayer.util.extensions.Decoder
    public final void flush() {
        synchronized (this.lock) {
            this.flushed = true;
            I i = this.dequeuedInputBuffer;
            if (i != null) {
                I[] iArr = this.availableInputBuffers;
                int i2 = this.availableInputBufferCount;
                this.availableInputBufferCount = i2 + 1;
                iArr[i2] = i;
                this.dequeuedInputBuffer = null;
            }
            while (!this.queuedInputBuffers.isEmpty()) {
                I[] iArr2 = this.availableInputBuffers;
                int i3 = this.availableInputBufferCount;
                this.availableInputBufferCount = i3 + 1;
                iArr2[i3] = this.queuedInputBuffers.removeFirst();
            }
            while (!this.queuedOutputBuffers.isEmpty()) {
                O[] oArr = this.availableOutputBuffers;
                int i4 = this.availableOutputBufferCount;
                this.availableOutputBufferCount = i4 + 1;
                oArr[i4] = this.queuedOutputBuffers.removeFirst();
            }
        }
    }

    @Override // com.google.android.exoplayer.util.extensions.Decoder
    public void release() {
        synchronized (this.lock) {
            this.released = true;
            this.lock.notify();
        }
        try {
            join();
        } catch (InterruptedException unused) {
            Thread.currentThread().interrupt();
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: E extends java.lang.Exception */
    private void maybeThrowException() throws Exception {
        E e = this.exception;
        if (e != null) {
            throw e;
        }
    }

    private void maybeNotifyDecodeLoop() {
        if (canDecodeBuffer()) {
            this.lock.notify();
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public final void run() {
        do {
            try {
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
        } while (decode());
    }

    private boolean decode() throws InterruptedException {
        synchronized (this.lock) {
            while (!this.released && !canDecodeBuffer()) {
                this.lock.wait();
            }
            if (this.released) {
                return false;
            }
            I iRemoveFirst = this.queuedInputBuffers.removeFirst();
            O[] oArr = this.availableOutputBuffers;
            int i = this.availableOutputBufferCount - 1;
            this.availableOutputBufferCount = i;
            O o = oArr[i];
            boolean z = this.flushed;
            this.flushed = false;
            o.reset();
            if (iRemoveFirst.getFlag(1)) {
                o.setFlag(1);
            } else {
                if (iRemoveFirst.getFlag(2)) {
                    o.setFlag(2);
                }
                E e = (E) decode(iRemoveFirst, o, z);
                this.exception = e;
                if (e != null) {
                    synchronized (this.lock) {
                    }
                    return false;
                }
            }
            synchronized (this.lock) {
                if (this.flushed || o.getFlag(2)) {
                    O[] oArr2 = this.availableOutputBuffers;
                    int i2 = this.availableOutputBufferCount;
                    this.availableOutputBufferCount = i2 + 1;
                    oArr2[i2] = o;
                } else {
                    this.queuedOutputBuffers.addLast(o);
                }
                I[] iArr = this.availableInputBuffers;
                int i3 = this.availableInputBufferCount;
                this.availableInputBufferCount = i3 + 1;
                iArr[i3] = iRemoveFirst;
            }
            return true;
        }
    }

    private boolean canDecodeBuffer() {
        return !this.queuedInputBuffers.isEmpty() && this.availableOutputBufferCount > 0;
    }
}
