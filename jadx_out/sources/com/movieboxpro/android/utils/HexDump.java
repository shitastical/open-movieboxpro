package com.movieboxpro.android.utils;

import com.google.common.base.Ascii;

/* JADX INFO: loaded from: classes5.dex */
public class HexDump {
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String dumpHexString(byte[] array) {
        return dumpHexString(array, 0, array.length);
    }

    public static String dumpHexString(byte[] array, int offset, int length) {
        StringBuilder sb = new StringBuilder("\n0x");
        byte[] bArr = new byte[16];
        sb.append(toHexString(offset));
        int i = offset;
        int i2 = 0;
        while (i < offset + length) {
            if (i2 == 16) {
                sb.append(" ");
                for (int i3 = 0; i3 < 16; i3++) {
                    byte b = bArr[i3];
                    if (b > 32 && b < 126) {
                        sb.append(new String(bArr, i3, 1));
                    } else {
                        sb.append(".");
                    }
                }
                sb.append("\n0x");
                sb.append(toHexString(i));
                i2 = 0;
            }
            byte b2 = array[i];
            sb.append(" ");
            char[] cArr = HEX_DIGITS;
            sb.append(cArr[(b2 >>> 4) & 15]);
            sb.append(cArr[b2 & Ascii.SI]);
            bArr[i2] = b2;
            i++;
            i2++;
        }
        if (i2 != 16) {
            int i4 = ((16 - i2) * 3) + 1;
            for (int i5 = 0; i5 < i4; i5++) {
                sb.append(" ");
            }
            for (int i6 = 0; i6 < i2; i6++) {
                byte b3 = bArr[i6];
                if (b3 > 32 && b3 < 126) {
                    sb.append(new String(bArr, i6, 1));
                } else {
                    sb.append(".");
                }
            }
        }
        return sb.toString();
    }

    public static String toHexString(byte b) {
        return toHexString(toByteArray(b));
    }

    public static String toHexString(byte[] array) {
        return toHexString(array, 0, array.length);
    }

    public static String toHexString(byte[] array, int offset, int length) {
        char[] cArr = new char[length * 2];
        int i = 0;
        for (int i2 = offset; i2 < offset + length; i2++) {
            byte b = array[i2];
            int i3 = i + 1;
            char[] cArr2 = HEX_DIGITS;
            cArr[i] = cArr2[(b >>> 4) & 15];
            i += 2;
            cArr[i3] = cArr2[b & Ascii.SI];
        }
        return new String(cArr);
    }

    public static String toHexString(int i) {
        return toHexString(toByteArray(i));
    }

    public static byte[] toByteArray(byte b) {
        return new byte[]{b};
    }

    public static byte[] toByteArray(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    private static int toByte(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'A' && c <= 'F') {
            return c - '7';
        }
        if (c >= 'a' && c <= 'f') {
            return c - 'W';
        }
        throw new RuntimeException("Invalid hex char '" + c + "'");
    }

    public static byte[] hexStringToByteArray(String hexString) {
        int length = hexString.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((toByte(hexString.charAt(i)) << 4) | toByte(hexString.charAt(i + 1)));
        }
        return bArr;
    }
}
