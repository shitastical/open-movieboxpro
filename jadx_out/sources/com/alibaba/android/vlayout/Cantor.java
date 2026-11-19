package com.alibaba.android.vlayout;

/* JADX INFO: loaded from: classes4.dex */
public class Cantor {
    public static long getCantor(long j, long j2) {
        long j3 = j + j2;
        return ((j3 * (1 + j3)) / 2) + j2;
    }

    public static void reverseCantor(long j, long[] jArr) {
        if (jArr == null || jArr.length < 2) {
            jArr = new long[2];
        }
        long jFloor = (long) (Math.floor(Math.sqrt((8 * j) + 1) - 1.0d) / 2.0d);
        long j2 = j - (((jFloor * jFloor) + jFloor) / 2);
        jArr[0] = jFloor - j2;
        jArr[1] = j2;
    }
}
