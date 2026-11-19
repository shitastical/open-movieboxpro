package com.google.android.exoplayer.util.extensions;

import java.lang.Exception;

/* JADX INFO: loaded from: classes4.dex */
public interface Decoder<I, O, E extends Exception> {
    I dequeueInputBuffer() throws Exception;

    O dequeueOutputBuffer() throws Exception;

    void flush();

    void queueInputBuffer(I i) throws Exception;

    void release();
}
