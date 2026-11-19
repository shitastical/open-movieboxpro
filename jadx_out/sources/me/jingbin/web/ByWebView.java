package me.jingbin.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.FrameLayout;

/* JADX INFO: loaded from: classes6.dex */
public class ByWebView {
    private Activity activity;
    private ByLoadJsHolder byLoadJsHolder;
    private int mErrorLayoutId;
    private String mErrorTitle;
    private View mErrorView;
    private WebProgress mProgressBar;
    private ByWebChromeClient mWebChromeClient;
    private WebView mWebView;

    private ByWebView(Builder builder) {
        this.activity = builder.mActivity;
        this.mErrorTitle = builder.mErrorTitle;
        this.mErrorLayoutId = builder.mErrorLayoutId;
        FrameLayout frameLayout = new FrameLayout(this.activity);
        setWebView(builder.mCustomWebView);
        frameLayout.addView(this.mWebView, new FrameLayout.LayoutParams(-1, -1));
        handleWebProgress(builder, frameLayout);
        if (builder.mIndex != -1) {
            builder.mWebContainer.addView(frameLayout, builder.mIndex, builder.mLayoutParams);
        } else {
            builder.mWebContainer.addView(frameLayout, builder.mLayoutParams);
        }
        handleSetting();
        ByWebChromeClient byWebChromeClient = new ByWebChromeClient(this.activity, this);
        this.mWebChromeClient = byWebChromeClient;
        byWebChromeClient.setOnByWebChromeCallback(builder.mOnTitleProgressCallback);
        this.mWebView.setWebChromeClient(this.mWebChromeClient);
        ByWebViewClient byWebViewClient = new ByWebViewClient(this.activity, this);
        byWebViewClient.setOnByWebClientCallback(builder.mOnByWebClientCallback);
        this.mWebView.setWebViewClient(byWebViewClient);
        handleJsInterface(builder);
    }

    private void setWebView(WebView webView) {
        if (webView != null) {
            this.mWebView = webView;
        } else {
            this.mWebView = new WebView(this.activity);
        }
    }

    private void handleJsInterface(Builder builder) {
        if (TextUtils.isEmpty(builder.mInterfaceName) || builder.mInterfaceObj == null) {
            return;
        }
        this.mWebView.addJavascriptInterface(builder.mInterfaceObj, builder.mInterfaceName);
    }

    public ByLoadJsHolder getLoadJsHolder() {
        if (this.byLoadJsHolder == null) {
            this.byLoadJsHolder = new ByLoadJsHolder(this.mWebView);
        }
        return this.byLoadJsHolder;
    }

    private void handleSetting() {
        WebSettings settings = this.mWebView.getSettings();
        settings.setSaveFormData(true);
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setDisplayZoomControls(false);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(-1);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        settings.setJavaScriptEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setDomStorageEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setMixedContentMode(0);
    }

    public void setTextZoom(int i) {
        this.mWebView.getSettings().setTextZoom(i);
    }

    private void handleWebProgress(Builder builder, FrameLayout frameLayout) {
        if (builder.mUseWebProgress) {
            this.mProgressBar = new WebProgress(this.activity);
            if (builder.mProgressStartColor == 0 || builder.mProgressEndColor == 0) {
                if (builder.mProgressStartColor != 0) {
                    this.mProgressBar.setColor(builder.mProgressStartColor, builder.mProgressStartColor);
                } else if (TextUtils.isEmpty(builder.mProgressStartColorString) || TextUtils.isEmpty(builder.mProgressEndColorString)) {
                    if (!TextUtils.isEmpty(builder.mProgressStartColorString) && TextUtils.isEmpty(builder.mProgressEndColorString)) {
                        this.mProgressBar.setColor(builder.mProgressStartColorString, builder.mProgressStartColorString);
                    }
                } else {
                    this.mProgressBar.setColor(builder.mProgressStartColorString, builder.mProgressEndColorString);
                }
            } else {
                this.mProgressBar.setColor(builder.mProgressStartColor, builder.mProgressEndColor);
            }
            int iDip2px = ByWebTools.dip2px(frameLayout.getContext(), WebProgress.WEB_PROGRESS_DEFAULT_HEIGHT);
            if (builder.mProgressHeightDp != 0) {
                this.mProgressBar.setHeight(builder.mProgressHeightDp);
                iDip2px = ByWebTools.dip2px(frameLayout.getContext(), builder.mProgressHeightDp);
            }
            this.mProgressBar.setVisibility(8);
            frameLayout.addView(this.mProgressBar, new FrameLayout.LayoutParams(-1, iDip2px));
        }
    }

    public void loadUrl(String str) {
        if (!TextUtils.isEmpty(str) && str.endsWith("mp4") && Build.VERSION.SDK_INT <= 22) {
            this.mWebView.loadData(ByWebTools.getVideoHtmlBody(str), "text/html", "UTF-8");
        } else {
            this.mWebView.loadUrl(str);
        }
        WebProgress webProgress = this.mProgressBar;
        if (webProgress != null) {
            webProgress.show();
        }
        hideErrorView();
    }

    public void reload() {
        hideErrorView();
        this.mWebView.reload();
    }

    public void onResume() {
        this.mWebView.onResume();
        this.mWebView.resumeTimers();
    }

    public void onPause() {
        this.mWebView.onPause();
        this.mWebView.pauseTimers();
    }

    public void onDestroy() {
        ByWebChromeClient byWebChromeClient = this.mWebChromeClient;
        if (byWebChromeClient != null && byWebChromeClient.getVideoFullView() != null) {
            this.mWebChromeClient.getVideoFullView().removeAllViews();
        }
        WebView webView = this.mWebView;
        if (webView != null) {
            ViewGroup viewGroup = (ViewGroup) webView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(this.mWebView);
            }
            this.mWebView.removeAllViews();
            this.mWebView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            this.mWebView.stopLoading();
            this.mWebView.setWebChromeClient(null);
            this.mWebView.setWebViewClient(null);
            this.mWebView.destroy();
            this.mWebView = null;
        }
    }

    public void handleFileChooser(int i, int i2, Intent intent) {
        ByWebChromeClient byWebChromeClient = this.mWebChromeClient;
        if (byWebChromeClient != null) {
            byWebChromeClient.handleFileChooser(i, i2, intent);
        }
    }

    public boolean handleKeyEvent(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return isBack();
        }
        return false;
    }

    public boolean isBack() {
        if (this.mWebChromeClient.inCustomView()) {
            this.mWebChromeClient.onHideCustomView();
            return true;
        }
        if (!this.mWebView.canGoBack()) {
            return false;
        }
        hideErrorView();
        this.mWebView.goBack();
        return true;
    }

    public WebView getWebView() {
        return this.mWebView;
    }

    public WebProgress getProgressBar() {
        return this.mProgressBar;
    }

    public void showErrorView() {
        try {
            View view = this.mErrorView;
            if (view == null) {
                FrameLayout frameLayout = (FrameLayout) this.mWebView.getParent();
                LayoutInflater layoutInflaterFrom = LayoutInflater.from(frameLayout.getContext());
                int i = this.mErrorLayoutId;
                if (i == 0) {
                    i = R.layout.by_load_url_error;
                }
                View viewInflate = layoutInflaterFrom.inflate(i, (ViewGroup) null);
                this.mErrorView = viewInflate;
                viewInflate.setOnClickListener(new View.OnClickListener() { // from class: me.jingbin.web.ByWebView.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        ByWebView.this.reload();
                    }
                });
                frameLayout.addView(this.mErrorView, new FrameLayout.LayoutParams(-1, -1));
            } else {
                view.setVisibility(0);
            }
            this.mWebView.setVisibility(4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideErrorView() {
        View view = this.mErrorView;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public View getErrorView() {
        return this.mErrorView;
    }

    String getErrorTitle() {
        return this.mErrorTitle;
    }

    public static Builder with(Activity activity) {
        if (activity == null) {
            throw new NullPointerException("activity can not be null .");
        }
        return new Builder(activity);
    }

    public void setFixScreenPortrait(boolean z) {
        ByWebChromeClient byWebChromeClient = this.mWebChromeClient;
        if (byWebChromeClient != null) {
            byWebChromeClient.setFixScreenPortrait(z);
        }
    }

    public void setFixScreenLandscape(boolean z) {
        ByWebChromeClient byWebChromeClient = this.mWebChromeClient;
        if (byWebChromeClient != null) {
            byWebChromeClient.setFixScreenLandscape(z);
        }
    }

    public static class Builder {
        private Activity mActivity;
        private WebView mCustomWebView;
        private int mErrorLayoutId;
        private String mErrorTitle;
        private String mInterfaceName;
        private Object mInterfaceObj;
        private ViewGroup.LayoutParams mLayoutParams;
        private OnByWebClientCallback mOnByWebClientCallback;
        private OnTitleProgressCallback mOnTitleProgressCallback;
        private int mProgressEndColor;
        private String mProgressEndColorString;
        private int mProgressHeightDp;
        private int mProgressStartColor;
        private String mProgressStartColorString;
        private ViewGroup mWebContainer;
        private boolean mUseWebProgress = true;
        private int mIndex = -1;

        public Builder(Activity activity) {
            this.mActivity = activity;
        }

        public Builder setWebParent(ViewGroup viewGroup, ViewGroup.LayoutParams layoutParams) {
            this.mWebContainer = viewGroup;
            this.mLayoutParams = layoutParams;
            return this;
        }

        public Builder setWebParent(ViewGroup viewGroup, int i, ViewGroup.LayoutParams layoutParams) {
            this.mWebContainer = viewGroup;
            this.mIndex = i;
            this.mLayoutParams = layoutParams;
            return this;
        }

        public Builder useWebProgress(boolean z) {
            this.mUseWebProgress = z;
            return this;
        }

        public Builder useWebProgress(int i) {
            return useWebProgress(i, i, 3);
        }

        public Builder useWebProgress(String str) {
            return useWebProgress(str, str, 3);
        }

        public Builder useWebProgress(int i, int i2, int i3) {
            this.mProgressStartColor = i;
            this.mProgressEndColor = i2;
            this.mProgressHeightDp = i3;
            return this;
        }

        public Builder useWebProgress(String str, String str2, int i) {
            this.mProgressStartColorString = str;
            this.mProgressEndColorString = str2;
            this.mProgressHeightDp = i;
            return this;
        }

        public Builder setCustomWebView(WebView webView) {
            this.mCustomWebView = webView;
            return this;
        }

        public Builder setErrorLayout(int i) {
            this.mErrorLayoutId = i;
            return this;
        }

        public Builder setErrorLayout(int i, String str) {
            this.mErrorLayoutId = i;
            this.mErrorTitle = str;
            return this;
        }

        public Builder addJavascriptInterface(String str, Object obj) {
            this.mInterfaceName = str;
            this.mInterfaceObj = obj;
            return this;
        }

        public Builder setOnTitleProgressCallback(OnTitleProgressCallback onTitleProgressCallback) {
            this.mOnTitleProgressCallback = onTitleProgressCallback;
            return this;
        }

        public Builder setOnByWebClientCallback(OnByWebClientCallback onByWebClientCallback) {
            this.mOnByWebClientCallback = onByWebClientCallback;
            return this;
        }

        public ByWebView get() {
            return new ByWebView(this);
        }

        public ByWebView loadUrl(String str) {
            ByWebView byWebView = get();
            byWebView.loadUrl(str);
            return byWebView;
        }
    }
}
