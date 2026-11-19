package com.movieboxpro.android.utils;

import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes5.dex */
public class HexUtil {
    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static char[] encodeHex(byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(byte[] data, boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(byte[] data, char[] toDigits) {
        char[] cArr = new char[data.length << 1];
        int i = 0;
        for (byte b : data) {
            int i2 = i + 1;
            cArr[i] = toDigits[(b & 240) >>> 4];
            i += 2;
            cArr[i2] = toDigits[b & Ascii.SI];
        }
        return cArr;
    }

    public static String encodeHexStr(byte[] data) {
        return encodeHexStr(data, true);
    }

    public static String encodeHexStr(byte[] data, boolean toLowerCase) {
        return encodeHexStr(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static String encodeHexStr(byte[] data, char[] toDigits) {
        return new String(encodeHex(data, toDigits));
    }

    public static byte[] decodeHex(char[] data) {
        int length = data.length;
        if ((length & 1) != 0) {
            throw new RuntimeException("Odd number of characters.");
        }
        byte[] bArr = new byte[length >> 1];
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i + 1;
            int digit = (toDigit(data[i], i) << 4) | toDigit(data[i3], i3);
            i += 2;
            bArr[i2] = (byte) (digit & 255);
            i2++;
        }
        return bArr;
    }

    protected static int toDigit(char ch, int index) {
        int iDigit = Character.digit(ch, 16);
        if (iDigit != -1) {
            return iDigit;
        }
        throw new RuntimeException("Illegal hexadecimal character " + ch + " at index " + index);
    }

    public static void main(String[] args) {
        String strEncodeHexStr = encodeHexStr("待转换字符串".getBytes());
        String str = new String(decodeHex(strEncodeHexStr.toCharArray()));
        System.out.println("转换前：待转换字符串");
        System.out.println("转换后：" + strEncodeHexStr);
        System.out.println("还原后：".concat(str));
    }
}
