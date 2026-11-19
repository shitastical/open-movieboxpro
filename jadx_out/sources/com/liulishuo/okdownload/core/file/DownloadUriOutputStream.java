package com.liulishuo.okdownload.core.file;

import android.content.Context;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import j$.util.Objects;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: classes5.dex */
public class DownloadUriOutputStream implements DownloadOutputStream {
    private final FileChannel channel;
    final FileOutputStream fos;
    final BufferedOutputStream out;
    final ParcelFileDescriptor pdf;
    private RandomAccessFile randomAccessFile;
    final String tag = "DownloadUriOutputStream";

    public DownloadUriOutputStream(Context context, Uri uri, int i) throws FileNotFoundException {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = context.getContentResolver().openFileDescriptor(uri, "rw");
        if (parcelFileDescriptorOpenFileDescriptor == null) {
            throw new FileNotFoundException("result of " + uri + " is null!");
        }
        this.pdf = parcelFileDescriptorOpenFileDescriptor;
        FileOutputStream fileOutputStream = new FileOutputStream(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
        this.fos = fileOutputStream;
        this.channel = fileOutputStream.getChannel();
        this.out = new BufferedOutputStream(fileOutputStream, i);
        this.randomAccessFile = new RandomAccessFile(new File(((String) Objects.requireNonNull(uri.getPath())).replace("file://", "")), "rw");
    }

    DownloadUriOutputStream(FileChannel fileChannel, ParcelFileDescriptor parcelFileDescriptor, FileOutputStream fileOutputStream, BufferedOutputStream bufferedOutputStream) {
        this.channel = fileChannel;
        this.pdf = parcelFileDescriptor;
        this.fos = fileOutputStream;
        this.out = bufferedOutputStream;
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.randomAccessFile.write(bArr, i, i2);
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void close() throws IOException {
        this.randomAccessFile.close();
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void flushAndSync() throws IOException {
        Log.d("DownloadUriOutputStream", "flushAndSync");
        if (this.randomAccessFile.getFD() != null) {
            this.randomAccessFile.getFD().sync();
        }
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void seek(long j) throws IOException {
        Log.d("DownloadUriOutputStream", "seek:$offset ");
        this.randomAccessFile.seek(j);
    }

    @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream
    public void setLength(long j) {
        Log.d("DownloadUriOutputStream", "setLength");
    }

    public static class Factory implements DownloadOutputStream.Factory {
        @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream.Factory
        public boolean supportSeek() {
            return true;
        }

        @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream.Factory
        public DownloadOutputStream create(Context context, File file, int i) throws FileNotFoundException {
            return new DownloadUriOutputStream(context, Uri.fromFile(file), i);
        }

        @Override // com.liulishuo.okdownload.core.file.DownloadOutputStream.Factory
        public DownloadOutputStream create(Context context, Uri uri, int i) throws FileNotFoundException {
            return new DownloadUriOutputStream(context, uri, i);
        }
    }
}
