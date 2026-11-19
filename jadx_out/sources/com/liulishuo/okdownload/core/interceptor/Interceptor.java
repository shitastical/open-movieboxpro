package com.liulishuo.okdownload.core.interceptor;

import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.download.DownloadChain;
import java.io.IOException;

/* JADX INFO: loaded from: classes5.dex */
public interface Interceptor {

    public interface Connect {
        DownloadConnection.Connected interceptConnect(DownloadChain downloadChain) throws IOException;
    }

    public interface Fetch {
        long interceptFetch(DownloadChain downloadChain) throws IOException;
    }
}
