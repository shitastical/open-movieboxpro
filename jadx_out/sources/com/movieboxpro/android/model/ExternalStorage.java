package com.movieboxpro.android.model;

import android.net.Uri;
import android.os.storage.StorageVolume;

/* JADX INFO: loaded from: classes5.dex */
public class ExternalStorage {
    private String desc;
    private String name;
    private String path;
    private boolean select;
    private String spaceDesc;
    private StorageVolume storageVolume;
    private Uri uri;

    public ExternalStorage(Uri uri, String desc) {
        this.uri = uri;
        this.path = uri.getPath();
        this.desc = desc;
        this.name = uri.getLastPathSegment();
    }

    public StorageVolume getStorageVolume() {
        return this.storageVolume;
    }

    public void setStorageVolume(StorageVolume storageVolume) {
        this.storageVolume = storageVolume;
    }

    public String getSpaceDesc() {
        return this.spaceDesc;
    }

    public void setSpaceDesc(String spaceDesc) {
        this.spaceDesc = spaceDesc;
    }

    public ExternalStorage() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public Uri getUri() {
        return this.uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
