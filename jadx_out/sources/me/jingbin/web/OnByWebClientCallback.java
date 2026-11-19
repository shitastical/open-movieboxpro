package me.jingbin.web;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;

/* JADX INFO: loaded from: classes6.dex */
public abstract class OnByWebClientCallback {
    public void onPageFinished(WebView webView, String str) {
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
    }

    public boolean onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        return false;
    }

    public boolean isOpenThirdApp(String str) {
        return (str.startsWith("http:") || str.startsWith("https:")) ? false : true;
    }
}
