package com.movieboxpro.android.timroes.axmlrpc;

import com.google.common.net.HttpHeaders;
import com.movieboxpro.android.timroes.base64.Base64;
import java.net.HttpURLConnection;

/* JADX INFO: loaded from: classes5.dex */
public class AuthenticationManager {
    private String pass;
    private String user;

    public void clearAuthData() {
        this.user = null;
        this.pass = null;
    }

    public void setAuthData(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    public void setAuthentication(HttpURLConnection http) {
        String str = this.user;
        if (str == null || this.pass == null || str.length() <= 0 || this.pass.length() <= 0) {
            return;
        }
        String strEncode = Base64.encode(this.user + ":" + this.pass);
        StringBuilder sb = new StringBuilder("Basic ");
        sb.append(strEncode);
        http.addRequestProperty(HttpHeaders.AUTHORIZATION, sb.toString());
    }
}
