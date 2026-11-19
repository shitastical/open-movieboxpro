package com.movieboxpro.android.utils;

import android.util.Base64;
import com.movieboxpro.android.http.CipherKeys;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes5.dex */
public class CipherUtils {
    private static final String ALGORITHM = "DESede";
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";

    public static String encrypt(String data, CipherKeys keys) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] bArr = new byte[24];
            byte[] bytes = keys.getKey().getBytes();
            int length = bytes.length <= 24 ? bytes.length : 24;
            System.arraycopy(bytes, 0, bArr, 0, length);
            while (length < 24) {
                bArr[length] = 0;
                length++;
            }
            cipher.init(1, new SecretKeySpec(bArr, ALGORITHM), new IvParameterSpec(keys.getIv().getBytes()));
            byte[] bArrEncode = Base64.encode(cipher.doFinal(data.getBytes()), 2);
            return new String(bArrEncode, 0, bArrEncode.length, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String data, CipherKeys keys) {
        try {
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            byte[] bArr = new byte[24];
            byte[] bytes = keys.getKey().getBytes();
            int length = bytes.length <= 24 ? bytes.length : 24;
            System.arraycopy(bytes, 0, bArr, 0, length);
            while (length < 24) {
                bArr[length] = 0;
                length++;
            }
            cipher.init(2, new SecretKeySpec(bArr, ALGORITHM), new IvParameterSpec(keys.getIv().getBytes()));
            return new String(cipher.doFinal(Base64.decode(data, 2)), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
