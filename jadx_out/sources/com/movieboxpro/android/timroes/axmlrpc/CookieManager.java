package com.movieboxpro.android.timroes.axmlrpc;

import j$.util.concurrent.ConcurrentHashMap;
import java.net.HttpURLConnection;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
class CookieManager {
    private static final String COOKIE = "Cookie";
    private static final String SET_COOKIE = "Set-Cookie";
    private Map<String, String> cookies = new ConcurrentHashMap();
    private int flags;

    public CookieManager(int flags) {
        this.flags = flags;
    }

    public void clearCookies() {
        this.cookies.clear();
    }

    public Map<String, String> getCookies() {
        return this.cookies;
    }

    public void readCookies(HttpURLConnection http) {
        if ((this.flags & 4) == 0) {
            return;
        }
        for (int i = 0; i < http.getHeaderFields().size(); i++) {
            String headerFieldKey = http.getHeaderFieldKey(i);
            if (headerFieldKey != null && "Set-Cookie".equalsIgnoreCase(headerFieldKey.toLowerCase())) {
                String[] strArrSplit = http.getHeaderField(i).split(";")[0].split("=");
                if (strArrSplit.length >= 2) {
                    this.cookies.put(strArrSplit[0], strArrSplit[1]);
                }
            }
        }
    }

    public void setCookies(HttpURLConnection http) {
        if ((this.flags & 4) == 0) {
            return;
        }
        String str = "";
        for (Map.Entry<String, String> entry : this.cookies.entrySet()) {
            str = str + entry.getKey() + "=" + entry.getValue() + "; ";
        }
        http.setRequestProperty("Cookie", str);
    }
}
