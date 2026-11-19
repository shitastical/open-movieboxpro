package com.google.android.exoplayer.upstream;

/* JADX INFO: loaded from: classes4.dex */
public interface TransferListener {
    void onBytesTransferred(int i);

    void onTransferEnd();

    void onTransferStart();
}
