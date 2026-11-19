package me.jahnen.libaums.core.fs;

import kotlin.Metadata;

/* JADX INFO: compiled from: FileSystem.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0012\u0010\n\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\u0005R\u0012\u0010\f\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u0005R\u0012\u0010\u000e\u001a\u00020\u000fX¦\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0012\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\tR\u0012\u0010\u0014\u001a\u00020\u0015X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lme/jahnen/libaums/core/fs/FileSystem;", "", "capacity", "", "getCapacity", "()J", "chunkSize", "", "getChunkSize", "()I", "freeSpace", "getFreeSpace", "occupiedSpace", "getOccupiedSpace", "rootDirectory", "Lme/jahnen/libaums/core/fs/UsbFile;", "getRootDirectory", "()Lme/jahnen/libaums/core/fs/UsbFile;", "type", "getType", "volumeLabel", "", "getVolumeLabel", "()Ljava/lang/String;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface FileSystem {
    long getCapacity();

    int getChunkSize();

    long getFreeSpace();

    long getOccupiedSpace();

    UsbFile getRootDirectory();

    int getType();

    String getVolumeLabel();
}
