package com.dl7.player.danmaku;

/* JADX INFO: loaded from: classes4.dex */
public abstract class BaseDanmakuData {
    private String content;
    private int textColor;
    private float textSize;
    private long time;
    private int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }

    public float getTextSize() {
        return this.textSize;
    }

    public void setTextSize(float f) {
        this.textSize = f;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public String toString() {
        return "BaseDanmakuData{type=" + this.type + ", content='" + this.content + "', time=" + this.time + ", textSize=" + this.textSize + ", textColor=" + this.textColor + '}';
    }
}
