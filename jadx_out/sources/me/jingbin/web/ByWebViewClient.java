package me.jingbin.web;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AlertDialog;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes6.dex */
public class ByWebViewClient extends WebViewClient {
    private WeakReference<Activity> mActivityWeakReference;
    private ByWebView mByWebView;
    private OnByWebClientCallback onByWebClientCallback;

    ByWebViewClient(Activity activity, ByWebView byWebView) {
        this.mActivityWeakReference = null;
        this.mActivityWeakReference = new WeakReference<>(activity);
        this.mByWebView = byWebView;
    }

    void setOnByWebClientCallback(OnByWebClientCallback onByWebClientCallback) {
        this.onByWebClientCallback = onByWebClientCallback;
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        String string = webResourceRequest.getUrl().toString();
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        OnByWebClientCallback onByWebClientCallback = this.onByWebClientCallback;
        if (onByWebClientCallback != null) {
            return onByWebClientCallback.isOpenThirdApp(string);
        }
        Activity activity = this.mActivityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return (string.startsWith("http:") || string.startsWith("https:")) ? false : true;
        }
        return ByWebTools.handleThirdApp(activity, string);
    }

    @Override // android.webkit.WebViewClient
    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        OnByWebClientCallback onByWebClientCallback = this.onByWebClientCallback;
        if (onByWebClientCallback != null) {
            return onByWebClientCallback.isOpenThirdApp(str);
        }
        Activity activity = this.mActivityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return (str.startsWith("http:") || str.startsWith("https:")) ? false : true;
        }
        return ByWebTools.handleThirdApp(activity, str);
    }

    @Override // android.webkit.WebViewClient
    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        OnByWebClientCallback onByWebClientCallback = this.onByWebClientCallback;
        if (onByWebClientCallback != null) {
            onByWebClientCallback.onPageStarted(webView, str, bitmap);
        }
        super.onPageStarted(webView, str, bitmap);
    }

    @Override // android.webkit.WebViewClient
    public void onPageFinished(WebView webView, String str) {
        Activity activity = this.mActivityWeakReference.get();
        if (activity != null && !activity.isFinishing() && !ByWebTools.isNetworkConnected(activity) && this.mByWebView.getProgressBar() != null) {
            this.mByWebView.getProgressBar().hide();
        }
        OnByWebClientCallback onByWebClientCallback = this.onByWebClientCallback;
        if (onByWebClientCallback != null) {
            onByWebClientCallback.onPageFinished(webView, str);
        }
        super.onPageFinished(webView, str);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        if (Build.VERSION.SDK_INT >= 23) {
            return;
        }
        this.mByWebView.showErrorView();
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        super.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if (webResourceRequest.isForMainFrame()) {
            this.mByWebView.showErrorView();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onReceivedSslError(WebView webView, final SslErrorHandler sslErrorHandler, SslError sslError) {
        OnByWebClientCallback onByWebClientCallback = this.onByWebClientCallback;
        if (onByWebClientCallback == null || !onByWebClientCallback.onReceivedSslError(webView, sslErrorHandler, sslError)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(webView.getContext());
            builder.setMessage("SSL认证失败，是否继续访问？");
            builder.setPositiveButton("继续", new DialogInterface.OnClickListener() { // from class: me.jingbin.web.ByWebViewClient.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    sslErrorHandler.proceed();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { // from class: me.jingbin.web.ByWebViewClient.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    sslErrorHandler.cancel();
                }
            });
            builder.create().show();
        }
    }

    @Override // android.webkit.WebViewClient
    public void onScaleChanged(WebView webView, float f, float f2) {
        super.onScaleChanged(webView, f, f2);
        if (f2 - f > 7.0f) {
            webView.setInitialScale((int) ((f / f2) * 100.0f));
        }
    }
}
