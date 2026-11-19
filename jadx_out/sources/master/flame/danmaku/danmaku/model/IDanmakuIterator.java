package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes6.dex */
public interface IDanmakuIterator {
    boolean hasNext();

    BaseDanmaku next();

    void remove();

    void reset();
}
