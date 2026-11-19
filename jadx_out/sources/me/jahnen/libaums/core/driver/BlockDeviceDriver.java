package me.jahnen.libaums.core.driver;

import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: compiled from: BlockDeviceDriver.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\n\u001a\u00020\u000bH&J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0018\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u0011"}, d2 = {"Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "", "blockSize", "", "getBlockSize", "()I", "blocks", "", "getBlocks", "()J", "init", "", "read", "deviceOffset", "buffer", "Ljava/nio/ByteBuffer;", "write", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface BlockDeviceDriver {
    int getBlockSize();

    long getBlocks();

    void init() throws IOException;

    void read(long deviceOffset, ByteBuffer buffer) throws IOException;

    void write(long deviceOffset, ByteBuffer buffer) throws IOException;
}
