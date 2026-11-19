package com.movieboxpro.android.db.dao;

import com.movieboxpro.android.db.entity.ConnectUsbDevice;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface ConnectUsbDeviceDao {
    List<ConnectUsbDevice> getAll();

    void insert(ConnectUsbDevice log);

    ConnectUsbDevice loadByPath(String path);
}
