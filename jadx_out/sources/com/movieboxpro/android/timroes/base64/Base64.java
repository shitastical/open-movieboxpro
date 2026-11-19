package com.movieboxpro.android.timroes.base64;

import com.google.common.base.Ascii;
import com.movieboxpro.android.utils.ShellUtil;
import java.util.HashMap;
import okio.Utf8;

/* JADX INFO: loaded from: classes5.dex */
public class Base64 {
    private static final char[] code = "=ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    private static final HashMap<Character, Byte> map = new HashMap<>();

    static {
        int i = 0;
        while (true) {
            char[] cArr = code;
            if (i >= cArr.length) {
                return;
            }
            map.put(Character.valueOf(cArr[i]), Byte.valueOf((byte) i));
            i++;
        }
    }

    private Base64() {
    }

    public static byte[] decode(String in) {
        String strReplaceAll = in.replaceAll("\\r|\\n", "");
        if (strReplaceAll.length() % 4 != 0) {
            throw new IllegalArgumentException("The length of the input string must be a multiple of four.");
        }
        if (!strReplaceAll.matches("^[A-Za-z0-9+/]*[=]{0,3}$")) {
            throw new IllegalArgumentException("The argument contains illegal characters.");
        }
        int length = (strReplaceAll.length() * 3) / 4;
        byte[] bArr = new byte[length];
        char[] charArray = strReplaceAll.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < charArray.length; i2 += 4) {
            HashMap<Character, Byte> map2 = map;
            int iByteValue = map2.get(Character.valueOf(charArray[i2])).byteValue() - 1;
            int iByteValue2 = map2.get(Character.valueOf(charArray[i2 + 1])).byteValue() - 1;
            int iByteValue3 = map2.get(Character.valueOf(charArray[i2 + 2])).byteValue() - 1;
            int iByteValue4 = map2.get(Character.valueOf(charArray[i2 + 3])).byteValue() - 1;
            bArr[i] = (byte) ((iByteValue << 2) | (iByteValue2 >>> 4));
            int i3 = i + 2;
            bArr[i + 1] = (byte) (((iByteValue2 & 15) << 4) | (iByteValue3 >>> 2));
            i += 3;
            bArr[i3] = (byte) ((iByteValue4 & 63) | ((iByteValue3 & 3) << 6));
        }
        if (!strReplaceAll.endsWith("=")) {
            return bArr;
        }
        int length2 = length - (strReplaceAll.length() - strReplaceAll.indexOf("="));
        byte[] bArr2 = new byte[length2];
        System.arraycopy(bArr, 0, bArr2, 0, length2);
        return bArr2;
    }

    public static String decodeAsString(String in) {
        return new String(decode(in));
    }

    public static String encode(String in) {
        return encode(in.getBytes());
    }

    public static String encode(Byte[] in) {
        int length = in.length;
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = in[i].byteValue();
        }
        return encode(bArr);
    }

    public static String encode(byte[] in) {
        StringBuilder sb = new StringBuilder(((in.length + 2) / 3) * 4);
        byte[] bArrEncodeAsBytes = encodeAsBytes(in);
        for (int i = 0; i < bArrEncodeAsBytes.length; i++) {
            sb.append(code[bArrEncodeAsBytes[i] + 1]);
            if (i % 72 == 71) {
                sb.append(ShellUtil.COMMAND_LINE_END);
            }
        }
        return sb.toString();
    }

    public static byte[] encodeAsBytes(String in) {
        return encodeAsBytes(in.getBytes());
    }

    public static byte[] encodeAsBytes(byte[] inArray) {
        int length = ((inArray.length + 2) / 3) * 4;
        byte[] bArr = new byte[length];
        int length2 = ((inArray.length + 2) / 3) * 3;
        byte[] bArr2 = new byte[length2];
        System.arraycopy(inArray, 0, bArr2, 0, inArray.length);
        int i = 0;
        for (int i2 = 0; i2 < length2; i2 += 3) {
            bArr[i] = (byte) ((bArr2[i2] & 255) >>> 2);
            int i3 = i2 + 1;
            bArr[i + 1] = (byte) (((bArr2[i2] & 3) << 4) | ((bArr2[i3] & 255) >>> 4));
            int i4 = i + 3;
            int i5 = (bArr2[i3] & Ascii.SI) << 2;
            int i6 = i2 + 2;
            bArr[i + 2] = (byte) (i5 | ((bArr2[i6] & 255) >>> 6));
            i += 4;
            bArr[i4] = (byte) (bArr2[i6] & Utf8.REPLACEMENT_BYTE);
        }
        for (int length3 = length2 - inArray.length; length3 > 0; length3--) {
            bArr[length - length3] = -1;
        }
        return bArr;
    }
}
