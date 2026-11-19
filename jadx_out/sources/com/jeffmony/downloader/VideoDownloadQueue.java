package com.jeffmony.downloader;

import com.jeffmony.downloader.model.VideoTaskItem;
import com.jeffmony.downloader.utils.LogUtils;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes5.dex */
public class VideoDownloadQueue {
    private static final String TAG = "VideoDownloadQueue";
    private CopyOnWriteArrayList<VideoTaskItem> mQueue = new CopyOnWriteArrayList<>();

    public List<VideoTaskItem> getDownloadList() {
        return this.mQueue;
    }

    public void offer(VideoTaskItem videoTaskItem) {
        this.mQueue.add(videoTaskItem);
    }

    public VideoTaskItem poll() {
        try {
            if (this.mQueue.size() >= 2) {
                this.mQueue.remove(0);
                return this.mQueue.get(0);
            }
            if (this.mQueue.size() != 1) {
                return null;
            }
            this.mQueue.remove(0);
            return null;
        } catch (Exception unused) {
            LogUtils.w(TAG, "DownloadQueue remove failed.");
            return null;
        }
    }

    public VideoTaskItem peek() {
        try {
            if (this.mQueue.size() >= 1) {
                return this.mQueue.get(0);
            }
            return null;
        } catch (Exception unused) {
            LogUtils.w(TAG, "DownloadQueue get failed.");
            return null;
        }
    }

    public boolean remove(VideoTaskItem videoTaskItem) {
        if (contains(videoTaskItem)) {
            return this.mQueue.remove(videoTaskItem);
        }
        return false;
    }

    public boolean contains(VideoTaskItem videoTaskItem) {
        return this.mQueue.contains(videoTaskItem);
    }

    public VideoTaskItem getTaskItem(String str) {
        for (int i = 0; i < this.mQueue.size(); i++) {
            try {
                VideoTaskItem videoTaskItem = this.mQueue.get(i);
                if (videoTaskItem != null && videoTaskItem.getUrl() != null && videoTaskItem.getUrl().equals(str)) {
                    return videoTaskItem;
                }
            } catch (Exception unused) {
                LogUtils.w(TAG, "DownloadQueue getTaskItem failed.");
                return null;
            }
        }
        return null;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return this.mQueue.size();
    }

    public boolean isHead(VideoTaskItem videoTaskItem) {
        if (videoTaskItem == null) {
            return false;
        }
        return videoTaskItem.equals(peek());
    }

    public int getDownloadingCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.mQueue.size(); i2++) {
            try {
                if (isTaskRunnig(this.mQueue.get(i2))) {
                    i++;
                }
            } catch (Exception unused) {
                LogUtils.w(TAG, "DownloadQueue getDownloadingCount failed.");
            }
        }
        return i;
    }

    public int getPendingCount() {
        int i = 0;
        for (int i2 = 0; i2 < this.mQueue.size(); i2++) {
            try {
                if (isTaskPending(this.mQueue.get(i2))) {
                    i++;
                }
            } catch (Exception unused) {
                LogUtils.w(TAG, "DownloadQueue getDownloadingCount failed.");
            }
        }
        return i;
    }

    public VideoTaskItem peekPendingTask() {
        for (int i = 0; i < this.mQueue.size(); i++) {
            try {
                VideoTaskItem videoTaskItem = this.mQueue.get(i);
                if (isTaskPending(videoTaskItem)) {
                    return videoTaskItem;
                }
            } catch (Exception unused) {
                LogUtils.w(TAG, "DownloadQueue getDownloadingCount failed.");
                return null;
            }
        }
        return null;
    }

    public boolean isTaskPending(VideoTaskItem videoTaskItem) {
        if (videoTaskItem == null) {
            return false;
        }
        int taskState = videoTaskItem.getTaskState();
        return taskState == -1 || taskState == 1;
    }

    public boolean isTaskRunnig(VideoTaskItem videoTaskItem) {
        if (videoTaskItem == null) {
            return false;
        }
        int taskState = videoTaskItem.getTaskState();
        return taskState == 2 || taskState == 3;
    }
}
