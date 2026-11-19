package com.google.android.exoplayer.upstream;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes4.dex */
public final class ContentDataSource implements UriDataSource {
    private AssetFileDescriptor assetFileDescriptor;
    private long bytesRemaining;
    private InputStream inputStream;
    private final TransferListener listener;
    private boolean opened;
    private final ContentResolver resolver;
    private String uriString;

    public static class ContentDataSourceException extends IOException {
        public ContentDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public ContentDataSource(Context context) {
        this(context, null);
    }

    public ContentDataSource(Context context, TransferListener transferListener) {
        this.resolver = context.getContentResolver();
        this.listener = transferListener;
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public long open(DataSpec dataSpec) throws ContentDataSourceException {
        try {
            this.uriString = dataSpec.uri.toString();
            this.assetFileDescriptor = this.resolver.openAssetFileDescriptor(dataSpec.uri, "r");
            FileInputStream fileInputStream = new FileInputStream(this.assetFileDescriptor.getFileDescriptor());
            this.inputStream = fileInputStream;
            if (fileInputStream.skip(dataSpec.position) < dataSpec.position) {
                throw new EOFException();
            }
            if (dataSpec.length != -1) {
                this.bytesRemaining = dataSpec.length;
            } else {
                long jAvailable = this.inputStream.available();
                this.bytesRemaining = jAvailable;
                if (jAvailable == 0) {
                    this.bytesRemaining = -1L;
                }
            }
            this.opened = true;
            TransferListener transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onTransferStart();
            }
            return this.bytesRemaining;
        } catch (IOException e) {
            throw new ContentDataSourceException(e);
        }
    }

    @Override // com.google.android.exoplayer.upstream.DataSource
    public int read(byte[] bArr, int i, int i2) throws ContentDataSourceException {
        long j = this.bytesRemaining;
        if (j == 0) {
            return -1;
        }
        if (j != -1) {
            try {
                i2 = (int) Math.min(j, i2);
            } catch (IOException e) {
                throw new ContentDataSourceException(e);
            }
        }
        int i3 = this.inputStream.read(bArr, i, i2);
        if (i3 > 0) {
            long j2 = this.bytesRemaining;
            if (j2 != -1) {
                this.bytesRemaining = j2 - ((long) i3);
            }
            TransferListener transferListener = this.listener;
            if (transferListener != null) {
                transferListener.onBytesTransferred(i3);
            }
        }
        return i3;
    }

    @Override // com.google.android.exoplayer.upstream.UriDataSource
    public String getUri() {
        return this.uriString;
    }

    /* JADX WARN: Bottom block not found for handler: all -> 0x003d */
    /* JADX WARN: Bottom block not found for handler: all -> 0x005f */
    @Override // com.google.android.exoplayer.upstream.DataSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void close() throws com.google.android.exoplayer.upstream.ContentDataSource.ContentDataSourceException {
        /*
            r4 = this;
            r0 = 0
            r4.uriString = r0
            r1 = 0
            java.io.InputStream r2 = r4.inputStream     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
            if (r2 == 0) goto Lb
            r2.close()     // Catch: java.lang.Throwable -> L3d java.io.IOException -> L3f
        Lb:
            r4.inputStream = r0
            android.content.res.AssetFileDescriptor r2 = r4.assetFileDescriptor     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
            if (r2 == 0) goto L14
            r2.close()     // Catch: java.lang.Throwable -> L24 java.io.IOException -> L26
        L14:
            r4.assetFileDescriptor = r0
            boolean r0 = r4.opened
            if (r0 == 0) goto L23
            r4.opened = r1
            com.google.android.exoplayer.upstream.TransferListener r0 = r4.listener
            if (r0 == 0) goto L23
            r0.onTransferEnd()
        L23:
            return
        L24:
            r2 = move-exception
            goto L2d
        L26:
            r2 = move-exception
            com.google.android.exoplayer.upstream.ContentDataSource$ContentDataSourceException r3 = new com.google.android.exoplayer.upstream.ContentDataSource$ContentDataSourceException     // Catch: java.lang.Throwable -> L24
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L24
            throw r3     // Catch: java.lang.Throwable -> L24
        L2d:
            r4.assetFileDescriptor = r0
            boolean r0 = r4.opened
            if (r0 == 0) goto L3c
            r4.opened = r1
            com.google.android.exoplayer.upstream.TransferListener r0 = r4.listener
            if (r0 == 0) goto L3c
            r0.onTransferEnd()
        L3c:
            throw r2
        L3d:
            r2 = move-exception
            goto L46
        L3f:
            r2 = move-exception
            com.google.android.exoplayer.upstream.ContentDataSource$ContentDataSourceException r3 = new com.google.android.exoplayer.upstream.ContentDataSource$ContentDataSourceException     // Catch: java.lang.Throwable -> L3d
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L3d
            throw r3     // Catch: java.lang.Throwable -> L3d
        L46:
            r4.inputStream = r0
            android.content.res.AssetFileDescriptor r3 = r4.assetFileDescriptor     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
            if (r3 == 0) goto L4f
            r3.close()     // Catch: java.lang.Throwable -> L5f java.io.IOException -> L61
        L4f:
            r4.assetFileDescriptor = r0
            boolean r0 = r4.opened
            if (r0 == 0) goto L5e
            r4.opened = r1
            com.google.android.exoplayer.upstream.TransferListener r0 = r4.listener
            if (r0 == 0) goto L5e
            r0.onTransferEnd()
        L5e:
            throw r2
        L5f:
            r2 = move-exception
            goto L68
        L61:
            r2 = move-exception
            com.google.android.exoplayer.upstream.ContentDataSource$ContentDataSourceException r3 = new com.google.android.exoplayer.upstream.ContentDataSource$ContentDataSourceException     // Catch: java.lang.Throwable -> L5f
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L5f
            throw r3     // Catch: java.lang.Throwable -> L5f
        L68:
            r4.assetFileDescriptor = r0
            boolean r0 = r4.opened
            if (r0 == 0) goto L77
            r4.opened = r1
            com.google.android.exoplayer.upstream.TransferListener r0 = r4.listener
            if (r0 == 0) goto L77
            r0.onTransferEnd()
        L77:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer.upstream.ContentDataSource.close():void");
    }
}
