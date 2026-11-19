package com.jeffmony.downloader.model;

import com.jeffmony.downloader.m3u8.M3U8;
import com.jeffmony.downloader.utils.VideoDownloadUtils;
import com.jeffmony.downloader.utils.VideoStorageUtils;

/* JADX INFO: loaded from: classes5.dex */
public class VideoTaskItem implements Cloneable {
    private String mCoverPath;
    private String mCoverUrl;
    private int mCurTs;
    private long mDownloadCreateTime;
    private long mDownloadSize;
    private int mErrorCode;
    private String mFileHash;
    private String mFileName;
    private String mFilePath;
    private String mFinalUrl;
    private String mGroupName;
    private boolean mIsCompleted;
    private boolean mIsInDatabase;
    private long mLastUpdateTime;
    private M3U8 mM3U8;
    private String mMimeType;
    private boolean mPaused;
    private float mPercent;
    private String mSaveDir;
    private float mSpeed;
    private int mTaskState;
    private String mTitle;
    private long mTotalSize;
    private int mTotalTs;
    private String mUrl;
    private int mVideoType;

    public VideoTaskItem(String str) {
        this(str, "", "", "");
    }

    public VideoTaskItem(String str, String str2, String str3, String str4) {
        this.mUrl = str;
        this.mCoverUrl = str2;
        this.mTitle = str3;
        this.mGroupName = str4;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setCoverUrl(String str) {
        this.mCoverUrl = str;
    }

    public String getCoverUrl() {
        return this.mCoverUrl;
    }

    public void setCoverPath(String str) {
        this.mCoverPath = str;
    }

    public String getCoverPath() {
        return this.mCoverPath;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setGroupName(String str) {
        this.mGroupName = str;
    }

    public String getGroupName() {
        return this.mGroupName;
    }

    public void setDownloadCreateTime(long j) {
        this.mDownloadCreateTime = j;
    }

    public long getDownloadCreateTime() {
        return this.mDownloadCreateTime;
    }

    public void setTaskState(int i) {
        this.mTaskState = i;
    }

    public int getTaskState() {
        return this.mTaskState;
    }

    public void setMimeType(String str) {
        this.mMimeType = str;
    }

    public String getMimeType() {
        return this.mMimeType;
    }

    public void setFinalUrl(String str) {
        this.mFinalUrl = str;
    }

    public String getFinalUrl() {
        return this.mFinalUrl;
    }

    public void setErrorCode(int i) {
        this.mErrorCode = i;
    }

    public int getErrorCode() {
        return this.mErrorCode;
    }

    public void setVideoType(int i) {
        this.mVideoType = i;
    }

    public int getVideoType() {
        return this.mVideoType;
    }

    public void setM3U8(M3U8 m3u8) {
        this.mM3U8 = m3u8;
    }

    public M3U8 getM3U8() {
        return this.mM3U8;
    }

    public void setTotalTs(int i) {
        this.mTotalTs = i;
    }

    public int getTotalTs() {
        return this.mTotalTs;
    }

    public void setCurTs(int i) {
        this.mCurTs = i;
    }

    public int getCurTs() {
        return this.mCurTs;
    }

    public void setSpeed(float f) {
        this.mSpeed = f;
    }

    public float getSpeed() {
        return this.mSpeed;
    }

    public String getSpeedString() {
        return VideoStorageUtils.getSizeStr((long) this.mSpeed) + "/s";
    }

    public void setPercent(float f) {
        this.mPercent = f;
    }

    public float getPercent() {
        return this.mPercent;
    }

    public String getPercentString() {
        return VideoDownloadUtils.getPercent(this.mPercent);
    }

    public void setDownloadSize(long j) {
        this.mDownloadSize = j;
    }

    public long getDownloadSize() {
        return this.mDownloadSize;
    }

    public String getDownloadSizeString() {
        return VideoStorageUtils.getSizeStr(this.mDownloadSize);
    }

    public void setTotalSize(long j) {
        this.mTotalSize = j;
    }

    public long getTotalSize() {
        return this.mTotalSize;
    }

    public void setFileHash(String str) {
        this.mFileHash = str;
    }

    public String getFileHash() {
        return this.mFileHash;
    }

    public void setSaveDir(String str) {
        this.mSaveDir = str;
    }

    public String getSaveDir() {
        return this.mSaveDir;
    }

    public void setIsCompleted(boolean z) {
        this.mIsCompleted = z;
    }

    public boolean isCompleted() {
        return this.mIsCompleted;
    }

    public void setIsInDatabase(boolean z) {
        this.mIsInDatabase = z;
    }

    public boolean isInDatabase() {
        return this.mIsInDatabase;
    }

    public void setLastUpdateTime(long j) {
        this.mLastUpdateTime = j;
    }

    public long getLastUpdateTime() {
        return this.mLastUpdateTime;
    }

    public void setFileName(String str) {
        this.mFileName = str;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public void setFilePath(String str) {
        this.mFilePath = str;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public void setPaused(boolean z) {
        this.mPaused = z;
    }

    public boolean isPaused() {
        return this.mPaused;
    }

    public boolean isRunningTask() {
        return this.mTaskState == 3;
    }

    public boolean isPendingTask() {
        int i = this.mTaskState;
        return i == -1 || i == 1;
    }

    public boolean isErrorState() {
        return this.mTaskState == 6;
    }

    public boolean isSuccessState() {
        return this.mTaskState == 5;
    }

    public boolean isInterruptTask() {
        int i = this.mTaskState;
        return i == 7 || i == 6;
    }

    public boolean isInitialTask() {
        return this.mTaskState == 0;
    }

    public boolean isHlsType() {
        return this.mVideoType == 1;
    }

    public void reset() {
        this.mDownloadCreateTime = 0L;
        this.mMimeType = null;
        this.mErrorCode = 0;
        this.mVideoType = 0;
        this.mTaskState = 0;
        this.mM3U8 = null;
        this.mSpeed = 0.0f;
        this.mPercent = 0.0f;
        this.mDownloadSize = 0L;
        this.mTotalSize = 0L;
        this.mFileName = "";
        this.mFilePath = "";
        this.mCoverUrl = "";
        this.mCoverPath = "";
        this.mTitle = "";
        this.mGroupName = "";
    }

    public Object clone() {
        VideoTaskItem videoTaskItem = new VideoTaskItem(this.mUrl);
        videoTaskItem.setDownloadCreateTime(this.mDownloadCreateTime);
        videoTaskItem.setTaskState(this.mTaskState);
        videoTaskItem.setMimeType(this.mMimeType);
        videoTaskItem.setErrorCode(this.mErrorCode);
        videoTaskItem.setVideoType(this.mVideoType);
        videoTaskItem.setPercent(this.mPercent);
        videoTaskItem.setDownloadSize(this.mDownloadSize);
        videoTaskItem.setSpeed(this.mSpeed);
        videoTaskItem.setTotalSize(this.mTotalSize);
        videoTaskItem.setFileHash(this.mFileHash);
        videoTaskItem.setFilePath(this.mFilePath);
        videoTaskItem.setFileName(this.mFileName);
        videoTaskItem.setCoverUrl(this.mCoverUrl);
        videoTaskItem.setCoverPath(this.mCoverPath);
        videoTaskItem.setTitle(this.mTitle);
        videoTaskItem.setGroupName(this.mGroupName);
        return videoTaskItem;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof VideoTaskItem)) {
            return false;
        }
        return this.mUrl.equals(((VideoTaskItem) obj).getUrl());
    }

    public String toString() {
        return "VideoTaskItem[Url=" + this.mUrl + ", Type=" + this.mVideoType + ", Percent=" + this.mPercent + ", DownloadSize=" + this.mDownloadSize + ", State=" + this.mTaskState + ", FilePath=" + this.mFileName + ", LocalFile=" + this.mFilePath + ", CoverUrl=" + this.mCoverUrl + ", CoverPath=" + this.mCoverPath + ", Title=" + this.mTitle + "]";
    }
}
