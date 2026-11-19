package com.jeffmony.downloader.listener;

import com.jeffmony.downloader.model.VideoTaskItem;

/* JADX INFO: loaded from: classes5.dex */
public interface IDownloadListener {
    void onDownloadDefault(VideoTaskItem videoTaskItem);

    void onDownloadError(VideoTaskItem videoTaskItem);

    void onDownloadPause(VideoTaskItem videoTaskItem);

    void onDownloadPending(VideoTaskItem videoTaskItem);

    void onDownloadPrepare(VideoTaskItem videoTaskItem);

    void onDownloadProgress(VideoTaskItem videoTaskItem);

    void onDownloadSpeed(VideoTaskItem videoTaskItem);

    void onDownloadStart(VideoTaskItem videoTaskItem);

    void onDownloadSuccess(VideoTaskItem videoTaskItem);
}
