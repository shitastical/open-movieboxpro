package master.flame.danmaku.controller;

/* JADX INFO: loaded from: classes6.dex */
public class UpdateThread extends Thread {
    volatile boolean mIsQuited;

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
    }

    public UpdateThread(String str) {
        super(str);
    }

    public void quit() {
        this.mIsQuited = true;
    }

    public boolean isQuited() {
        return this.mIsQuited;
    }
}
