package com.jeffmony.downloader.listener;

import com.jeffmony.downloader.m3u8.M3U8;
import com.jeffmony.downloader.model.VideoTaskItem;

/* JADX INFO: loaded from: classes5.dex */
public interface IVideoInfoParseListener {
    void onM3U8FileParseFailed(VideoTaskItem videoTaskItem, Throwable th);

    void onM3U8FileParseSuccess(VideoTaskItem videoTaskItem, M3U8 m3u8);
}
