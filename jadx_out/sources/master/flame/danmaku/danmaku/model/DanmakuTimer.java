package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes6.dex */
public class DanmakuTimer {
    public long currMillisecond;
    private long lastInterval;

    public long update(long j) {
        long j2 = j - this.currMillisecond;
        this.lastInterval = j2;
        this.currMillisecond = j;
        return j2;
    }

    public long add(long j) {
        return update(this.currMillisecond + j);
    }

    public long lastInterval() {
        return this.lastInterval;
    }
}
