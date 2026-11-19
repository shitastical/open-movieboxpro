package com.movieboxpro.android.view.dialog;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import androidx.fragment.app.DialogFragment;
import androidx.media3.extractor.text.ttml.TtmlNode;
import com.adorkable.iosdialog.R;
import com.movieboxpro.android.utils.CommonExtKt;
import com.movieboxpro.androidtv.databinding.FragmentWebViewDialogBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: WebViewDialogFragment.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\n\u001a\u00020\u0007H\u0016J&\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\u001a\u0010\u0011\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\f2\b\u0010\b\u001a\u0004\u0018\u00010\tH\u0016J\b\u0010\u0013\u001a\u00020\u0007H\u0002J\u0012\u0010\u0014\u001a\u00020\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\b\u0010\u0017\u001a\u00020\u0007H\u0016J\b\u0010\u0018\u001a\u00020\u0007H\u0016J\b\u0010\u0019\u001a\u00020\u0007H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/WebViewDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "<init>", "()V", "binding", "Lcom/movieboxpro/androidtv/databinding/FragmentWebViewDialogBinding;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", TtmlNode.RUBY_CONTAINER, "Landroid/view/ViewGroup;", "onViewCreated", "view", "initData", "initWebVIew", "webUrl", "", "onPause", "onDestroy", "onResume", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class WebViewDialogFragment extends DialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentWebViewDialogBinding binding;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(1, R.style.DialogStyle);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        Window window = dialog != null ? dialog.getWindow() : null;
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            attributes.width = (int) (((double) displayMetrics.widthPixels) * 0.5d);
            attributes.height = (int) (((double) displayMetrics.heightPixels) * 0.8d);
            attributes.gravity = 17;
            attributes.dimAmount = 0.6f;
            window.setAttributes(attributes);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWebViewDialogBinding fragmentWebViewDialogBindingInflate = FragmentWebViewDialogBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentWebViewDialogBindingInflate, "inflate(...)");
        this.binding = fragmentWebViewDialogBindingInflate;
        if (fragmentWebViewDialogBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWebViewDialogBindingInflate = null;
        }
        return fragmentWebViewDialogBindingInflate.getRoot();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initData();
    }

    private final void initData() {
        Bundle arguments = getArguments();
        initWebVIew(arguments != null ? arguments.getString("url") : null);
    }

    private final void initWebVIew(String webUrl) {
        final FragmentWebViewDialogBinding fragmentWebViewDialogBinding = this.binding;
        if (fragmentWebViewDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWebViewDialogBinding = null;
        }
        WebView webView = fragmentWebViewDialogBinding.webView;
        if (webUrl == null) {
            webUrl = "";
        }
        webView.loadUrl(webUrl);
        fragmentWebViewDialogBinding.webView.getSettings().setSupportZoom(true);
        fragmentWebViewDialogBinding.webView.getSettings().setJavaScriptEnabled(true);
        fragmentWebViewDialogBinding.webView.setWebViewClient(new WebViewClient() { // from class: com.movieboxpro.android.view.dialog.WebViewDialogFragment$initWebVIew$1$1
            @Override // android.webkit.WebViewClient
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intrinsics.checkNotNullParameter(view, "view");
                Intrinsics.checkNotNullParameter(url, "url");
                if (Build.VERSION.SDK_INT >= 26) {
                    return false;
                }
                view.loadUrl(url);
                return true;
            }

            @Override // android.webkit.WebViewClient
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                ProgressBar progressBar = fragmentWebViewDialogBinding.progressBar;
                Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
                CommonExtKt.visible(progressBar);
                fragmentWebViewDialogBinding.progressBar.setProgress(0);
            }

            @Override // android.webkit.WebViewClient
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                ProgressBar progressBar = fragmentWebViewDialogBinding.progressBar;
                Intrinsics.checkNotNullExpressionValue(progressBar, "progressBar");
                CommonExtKt.gone(progressBar);
                fragmentWebViewDialogBinding.progressBar.setProgress(100);
            }
        });
        fragmentWebViewDialogBinding.webView.setWebChromeClient(new WebChromeClient() { // from class: com.movieboxpro.android.view.dialog.WebViewDialogFragment$initWebVIew$1$2
            @Override // android.webkit.WebChromeClient
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                fragmentWebViewDialogBinding.progressBar.setProgress(newProgress);
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        FragmentWebViewDialogBinding fragmentWebViewDialogBinding = this.binding;
        if (fragmentWebViewDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWebViewDialogBinding = null;
        }
        fragmentWebViewDialogBinding.webView.onPause();
        super.onPause();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        FragmentWebViewDialogBinding fragmentWebViewDialogBinding = this.binding;
        if (fragmentWebViewDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWebViewDialogBinding = null;
        }
        fragmentWebViewDialogBinding.webView.destroy();
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        FragmentWebViewDialogBinding fragmentWebViewDialogBinding = this.binding;
        if (fragmentWebViewDialogBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWebViewDialogBinding = null;
        }
        fragmentWebViewDialogBinding.webView.onResume();
        super.onResume();
    }

    /* JADX INFO: compiled from: WebViewDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lcom/movieboxpro/android/view/dialog/WebViewDialogFragment$Companion;", "", "<init>", "()V", "newInstance", "Lcom/movieboxpro/android/view/dialog/WebViewDialogFragment;", "url", "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WebViewDialogFragment newInstance(String url) {
            Intrinsics.checkNotNullParameter(url, "url");
            WebViewDialogFragment webViewDialogFragment = new WebViewDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("url", url);
            webViewDialogFragment.setArguments(bundle);
            return webViewDialogFragment;
        }
    }
}
