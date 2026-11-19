package com.jeffmony.downloader.listener;

import com.jeffmony.downloader.m3u8.M3U8;
import com.jeffmony.downloader.model.VideoTaskItem;

/* JADX INFO: loaded from: classes5.dex */
public interface IVideoInfoListener {
    void onBaseVideoInfoFailed(Throwable th);

    void onBaseVideoInfoSuccess(VideoTaskItem videoTaskItem);

    void onFinalUrl(String str);

    void onLiveM3U8Callback(VideoTaskItem videoTaskItem);

    void onM3U8InfoFailed(Throwable th);

    void onM3U8InfoSuccess(VideoTaskItem videoTaskItem, M3U8 m3u8);
}
