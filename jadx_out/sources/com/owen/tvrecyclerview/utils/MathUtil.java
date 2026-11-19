package com.owen.tvrecyclerview.utils;

/* JADX INFO: loaded from: classes5.dex */
public class MathUtil {
    public static int commonDivisor(int i, int i2) {
        while (true) {
            int i3 = i % i2;
            if (i3 == 0) {
                return i2;
            }
            int i4 = i2;
            i2 = i3;
            i = i4;
        }
    }

    public static int commonMultiple(int i, int i2) {
        return (i * i2) / commonDivisor(i, i2);
    }

    public static int commonMultiple(int[] iArr) {
        int iCommonMultiple = iArr[0];
        for (int i = 1; i < iArr.length; i++) {
            iCommonMultiple = commonMultiple(iCommonMultiple, iArr[i]);
        }
        return iCommonMultiple;
    }
}
