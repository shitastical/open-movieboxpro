package com.google.android.exoplayer.upstream.cache;

import android.net.Uri;
import android.util.Log;
import com.google.android.exoplayer.upstream.DataSink;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.exoplayer.upstream.FileDataSource;
import com.google.android.exoplayer.upstream.TeeDataSource;
import java.io.IOException;
import java.io.InterruptedIOException;

/* JADX INFO: loaded from: classes4.dex */
public final class CacheDataSource implements DataSource {
    private static final String TAG = "CacheDataSource";
    private final boolean blockOnCache;
    private long bytesRemaining;
    private final Cache cache;
    private final DataSource cacheReadDataSource;
    private final DataSource cacheWriteDataSource;
    private DataSource currentDataSource;
    private final EventListener eventListener;
    private int flags;
    private boolean ignoreCache;
    private final boolean ignoreCacheOnError;
    private String key;
    private CacheSpan lockedSpan;
    private long readPosition;
    private long totalCachedBytesRead;
    private final DataSource upstreamDataSource;
    private Uri uri;

    public interface EventListener {
        void onCachedBytesRead(long j, long j2);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, boolean z, boolean z2) {
        this(cache, dataSource, z, z2, Long.MAX_VALUE);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, boolean z, boolean z2, long j) {
        this(cache, dataSource, new FileDataSource(), new CacheDataSink(cache, j), z, z2, null);
    }

    public CacheDataSource(Cache cache, DataSource dataSource, DataSource dataSource2, DataSink dataSink, boolean z, boolean z2, EventListener eventListener) {
        this.cache = cache;
        this.cacheReadDataSource = dataSource2;
        this.blockOnCache = z;
        this.ignoreCacheOnError = z2;
        this.upstreamDataSource = dataSource;
        if (dataSink != null) {
            this.cacheWriteDataSource = new TeeDataSource(dataSource, dataSink);
        } else {
            this.cacheWriteDataSource = null;
        }
        this.eventListener = eventListener;
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public long open(DataSpec dataSpec) throws IOException {
        try {
            this.uri = dataSpec.uri;
            this.flags = dataSpec.flags;
            this.key = dataSpec.key;
            this.readPosition = dataSpec.position;
            this.bytesRemaining = dataSpec.length;
            openNextSource();
            return dataSpec.length;
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws IOException {
        try {
            int i3 = this.currentDataSource.read(bArr, i, i2);
            if (i3 >= 0) {
                if (this.currentDataSource == this.cacheReadDataSource) {
                    this.totalCachedBytesRead += (long) i3;
                }
                long j = i3;
                this.readPosition += j;
                long j2 = this.bytesRemaining;
                if (j2 != -1) {
                    this.bytesRemaining = j2 - j;
                }
            } else {
                closeCurrentSource();
                long j3 = this.bytesRemaining;
                if (j3 > 0 && j3 != -1) {
                    openNextSource();
                    return read(bArr, i, i2);
                }
            }
            return i3;
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public void close() throws IOException {
        notifyBytesRead();
        try {
            closeCurrentSource();
        } catch (IOException e) {
            handleBeforeThrow(e);
            throw e;
        }
    }

    private void openNextSource() throws IOException {
        DataSpec dataSpec;
        CacheSpan cacheSpanStartReadWrite = null;
        if (!this.ignoreCache) {
            if (this.bytesRemaining == -1) {
                Log.w(TAG, "Cache bypassed due to unbounded length.");
            } else if (this.blockOnCache) {
                try {
                    cacheSpanStartReadWrite = this.cache.startReadWrite(this.key, this.readPosition);
                } catch (InterruptedException unused) {
                    throw new InterruptedIOException();
                }
            } else {
                cacheSpanStartReadWrite = this.cache.startReadWriteNonBlocking(this.key, this.readPosition);
            }
        }
        if (cacheSpanStartReadWrite == null) {
            this.currentDataSource = this.upstreamDataSource;
            dataSpec = new DataSpec(this.uri, this.readPosition, this.bytesRemaining, this.key, this.flags);
        } else if (cacheSpanStartReadWrite.isCached) {
            Uri uriFromFile = Uri.fromFile(cacheSpanStartReadWrite.file);
            long j = this.readPosition - cacheSpanStartReadWrite.position;
            dataSpec = new DataSpec(uriFromFile, this.readPosition, j, Math.min(cacheSpanStartReadWrite.length - j, this.bytesRemaining), this.key, this.flags);
            this.currentDataSource = this.cacheReadDataSource;
        } else {
            this.lockedSpan = cacheSpanStartReadWrite;
            dataSpec = new DataSpec(this.uri, this.readPosition, cacheSpanStartReadWrite.isOpenEnded() ? this.bytesRemaining : Math.min(cacheSpanStartReadWrite.length, this.bytesRemaining), this.key, this.flags);
            DataSource dataSource = this.cacheWriteDataSource;
            if (dataSource == null) {
                dataSource = this.upstreamDataSource;
            }
            this.currentDataSource = dataSource;
        }
        this.currentDataSource.open(dataSpec);
    }

    private void closeCurrentSource() throws IOException {
        DataSource dataSource = this.currentDataSource;
        if (dataSource == null) {
            return;
        }
        try {
            dataSource.close();
            this.currentDataSource = null;
        } finally {
            CacheSpan cacheSpan = this.lockedSpan;
            if (cacheSpan != null) {
                this.cache.releaseHoleSpan(cacheSpan);
                this.lockedSpan = null;
            }
        }
    }

    private void handleBeforeThrow(IOException iOException) {
        if (this.ignoreCacheOnError) {
            if (this.currentDataSource == this.cacheReadDataSource || (iOException instanceof CacheDataSink.CacheDataSinkException)) {
                this.ignoreCache = true;
            }
        }
    }

    private void notifyBytesRead() {
        EventListener eventListener = this.eventListener;
        if (eventListener == null || this.totalCachedBytesRead <= 0) {
            return;
        }
        eventListener.onCachedBytesRead(this.cache.getCacheSpace(), this.totalCachedBytesRead);
        this.totalCachedBytesRead = 0L;
    }
}
