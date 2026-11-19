package me.jingbin.web;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.FrameLayout;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes6.dex */
public class ByWebChromeClient extends WebChromeClient {
    private static final int RESULT_CODE_FILE_CHOOSER = 1;
    private static final int RESULT_CODE_FILE_CHOOSER_FOR_ANDROID_5 = 2;
    private boolean isFixScreenLandscape = false;
    private boolean isFixScreenPortrait = false;
    private WeakReference<Activity> mActivityWeakReference;
    private ByWebView mByWebView;
    private View mCustomView;
    private WebChromeClient.CustomViewCallback mCustomViewCallback;
    private View mProgressVideo;
    private ValueCallback<Uri> mUploadMessage;
    private ValueCallback<Uri[]> mUploadMessageForAndroid5;
    private OnTitleProgressCallback onByWebChromeCallback;
    private ByFullscreenHolder videoFullView;

    ByWebChromeClient(Activity activity, ByWebView byWebView) {
        this.mActivityWeakReference = new WeakReference<>(activity);
        this.mByWebView = byWebView;
    }

    void setOnByWebChromeCallback(OnTitleProgressCallback onTitleProgressCallback) {
        this.onByWebChromeCallback = onTitleProgressCallback;
    }

    public void setFixScreenLandscape(boolean z) {
        this.isFixScreenLandscape = z;
    }

    public void setFixScreenPortrait(boolean z) {
        this.isFixScreenPortrait = z;
    }

    @Override // android.webkit.WebChromeClient
    public void onShowCustomView(View view, WebChromeClient.CustomViewCallback customViewCallback) {
        OnTitleProgressCallback onTitleProgressCallback;
        Activity activity = this.mActivityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        if (!this.isFixScreenLandscape && ((onTitleProgressCallback = this.onByWebChromeCallback) == null || !onTitleProgressCallback.onHandleScreenOrientation(true))) {
            activity.setRequestedOrientation(0);
        }
        this.mByWebView.getWebView().setVisibility(4);
        if (this.mCustomView != null) {
            customViewCallback.onCustomViewHidden();
            return;
        }
        FrameLayout frameLayout = (FrameLayout) activity.getWindow().getDecorView();
        ByFullscreenHolder byFullscreenHolder = new ByFullscreenHolder(activity);
        this.videoFullView = byFullscreenHolder;
        byFullscreenHolder.addView(view);
        frameLayout.addView(this.videoFullView);
        this.mCustomView = view;
        this.mCustomViewCallback = customViewCallback;
        this.videoFullView.setVisibility(0);
    }

    @Override // android.webkit.WebChromeClient
    public void onHideCustomView() {
        OnTitleProgressCallback onTitleProgressCallback;
        Activity activity = this.mActivityWeakReference.get();
        if (activity == null || activity.isFinishing() || this.mCustomView == null) {
            return;
        }
        if (!this.isFixScreenPortrait && ((onTitleProgressCallback = this.onByWebChromeCallback) == null || !onTitleProgressCallback.onHandleScreenOrientation(false))) {
            activity.setRequestedOrientation(1);
        }
        this.mCustomView.setVisibility(8);
        ByFullscreenHolder byFullscreenHolder = this.videoFullView;
        if (byFullscreenHolder != null) {
            byFullscreenHolder.removeView(this.mCustomView);
            this.videoFullView.setVisibility(8);
        }
        this.mCustomView = null;
        this.mCustomViewCallback.onCustomViewHidden();
        this.mByWebView.getWebView().setVisibility(0);
    }

    @Override // android.webkit.WebChromeClient
    public View getVideoLoadingProgressView() {
        if (this.mProgressVideo == null) {
            this.mProgressVideo = LayoutInflater.from(this.mByWebView.getWebView().getContext()).inflate(R.layout.by_video_loading_progress, (ViewGroup) null);
        }
        return this.mProgressVideo;
    }

    @Override // android.webkit.WebChromeClient
    public void onProgressChanged(WebView webView, int i) {
        super.onProgressChanged(webView, i);
        if (this.mByWebView.getProgressBar() != null) {
            this.mByWebView.getProgressBar().setWebProgress(i);
        }
        if (this.mByWebView.getWebView() != null && this.mByWebView.getWebView().getVisibility() == 4 && ((this.mByWebView.getErrorView() == null || this.mByWebView.getErrorView().getVisibility() == 8) && i == 100)) {
            this.mByWebView.getWebView().setVisibility(0);
        }
        OnTitleProgressCallback onTitleProgressCallback = this.onByWebChromeCallback;
        if (onTitleProgressCallback != null) {
            onTitleProgressCallback.onProgressChanged(i);
        }
    }

    boolean inCustomView() {
        return this.mCustomView != null;
    }

    @Override // android.webkit.WebChromeClient
    public void onReceivedTitle(WebView webView, String str) {
        super.onReceivedTitle(webView, str);
        if (this.onByWebChromeCallback != null) {
            if (this.mByWebView.getErrorView() != null && this.mByWebView.getErrorView().getVisibility() == 0) {
                this.onByWebChromeCallback.onReceivedTitle(TextUtils.isEmpty(this.mByWebView.getErrorTitle()) ? "网页无法打开" : this.mByWebView.getErrorTitle());
            } else {
                this.onByWebChromeCallback.onReceivedTitle(str);
            }
        }
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
        openFileChooserImpl(valueCallback);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback) {
        openFileChooserImpl(valueCallback);
    }

    public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        openFileChooserImpl(valueCallback);
    }

    @Override // android.webkit.WebChromeClient
    public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, WebChromeClient.FileChooserParams fileChooserParams) {
        openFileChooserImplForAndroid5(valueCallback);
        return true;
    }

    private void openFileChooserImpl(ValueCallback<Uri> valueCallback) {
        Activity activity = this.mActivityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        this.mUploadMessage = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        activity.startActivityForResult(Intent.createChooser(intent, "文件选择"), 1);
    }

    private void openFileChooserImplForAndroid5(ValueCallback<Uri[]> valueCallback) {
        Activity activity = this.mActivityWeakReference.get();
        if (activity == null || activity.isFinishing()) {
            return;
        }
        this.mUploadMessageForAndroid5 = valueCallback;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("image/*");
        Intent intent2 = new Intent("android.intent.action.CHOOSER");
        intent2.putExtra("android.intent.extra.INTENT", intent);
        intent2.putExtra("android.intent.extra.TITLE", "图片选择");
        activity.startActivityForResult(intent2, 2);
    }

    private void uploadMessage(Intent intent, int i) {
        if (this.mUploadMessage == null) {
            return;
        }
        this.mUploadMessage.onReceiveValue((intent == null || i != -1) ? null : intent.getData());
        this.mUploadMessage = null;
    }

    private void uploadMessageForAndroid5(Intent intent, int i) {
        if (this.mUploadMessageForAndroid5 == null) {
            return;
        }
        Uri data = (intent == null || i != -1) ? null : intent.getData();
        if (data != null) {
            this.mUploadMessageForAndroid5.onReceiveValue(new Uri[]{data});
        } else {
            this.mUploadMessageForAndroid5.onReceiveValue(new Uri[0]);
        }
        this.mUploadMessageForAndroid5 = null;
    }

    public void handleFileChooser(int i, int i2, Intent intent) {
        if (i == 1) {
            uploadMessage(intent, i2);
        } else if (i == 2) {
            uploadMessageForAndroid5(intent, i2);
        }
    }

    @Override // android.webkit.WebChromeClient
    public void onPermissionRequest(PermissionRequest permissionRequest) {
        super.onPermissionRequest(permissionRequest);
    }

    ByFullscreenHolder getVideoFullView() {
        return this.videoFullView;
    }

    @Override // android.webkit.WebChromeClient
    public Bitmap getDefaultVideoPoster() {
        if (super.getDefaultVideoPoster() == null) {
            return BitmapFactory.decodeResource(this.mByWebView.getWebView().getResources(), R.drawable.by_icon_video);
        }
        return super.getDefaultVideoPoster();
    }
}
