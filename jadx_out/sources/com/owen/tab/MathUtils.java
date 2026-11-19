package com.owen.tab;

/* JADX INFO: loaded from: classes5.dex */
class MathUtils {
    static float constrain(float f, float f2, float f3) {
        if (f < f2) {
            return f2;
        }
        return f > f3 ? f3 : f;
    }

    static int constrain(int i, int i2, int i3) {
        if (i < i2) {
            return i2;
        }
        return i > i3 ? i3 : i;
    }

    MathUtils() {
    }
}
