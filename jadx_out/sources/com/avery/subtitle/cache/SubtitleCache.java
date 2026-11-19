package com.avery.subtitle.cache;

import com.avery.subtitle.model.Subtitle;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class SubtitleCache {
    private HashMap<String, List<Subtitle>> mCache = new HashMap<>();

    public synchronized void put(String str, List<Subtitle> list) {
        String md5 = getMD5(str);
        if (md5 == null) {
            return;
        }
        this.mCache.put(md5, list);
    }

    public List<Subtitle> get(String str) {
        String md5 = getMD5(str);
        if (md5 == null) {
            return null;
        }
        return this.mCache.get(md5);
    }

    private static String getMD5(String str) {
        if (str == null) {
            return null;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            return new BigInteger(1, messageDigest.digest()).toString(16);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
