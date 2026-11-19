package com.movieboxpro.android.db.entity;

/* JADX INFO: loaded from: classes5.dex */
public class ConnectUsbDevice {
    private String downloadPath;
    private String path;
    private String productName;

    public ConnectUsbDevice(String path, String downloadPath, String productName) {
        this.path = path;
        this.downloadPath = downloadPath;
        this.productName = productName;
    }

    public String getDownloadPath() {
        return this.downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}
