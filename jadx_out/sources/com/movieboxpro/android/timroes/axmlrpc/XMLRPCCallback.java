package com.movieboxpro.android.timroes.axmlrpc;

/* JADX INFO: loaded from: classes5.dex */
public interface XMLRPCCallback {
    void onError(long id, XMLRPCException error);

    void onResponse(long id, Object result);

    void onServerError(long id, XMLRPCServerException error);
}
