package master.flame.danmaku.danmaku.model;

/* JADX INFO: loaded from: classes6.dex */
public class Duration {
    private float factor = 1.0f;
    private long mInitialDuration;
    public long value;

    public Duration(long j) {
        this.mInitialDuration = j;
        this.value = j;
    }

    public void setValue(long j) {
        this.mInitialDuration = j;
        this.value = (long) (j * this.factor);
    }

    public void setFactor(float f) {
        if (this.factor != f) {
            this.factor = f;
            this.value = (long) (this.mInitialDuration * f);
        }
    }
}
