package me.jingbin.web;

import android.text.TextUtils;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ByLoadJsHolder {
    private WebView mWebView;

    ByLoadJsHolder(WebView webView) {
        this.mWebView = webView;
    }

    public void loadJs(String str, ValueCallback<String> valueCallback) {
        evaluateJs(str, valueCallback);
    }

    public void loadJs(String str) {
        loadJs(str, null);
    }

    private void evaluateJs(String str, final ValueCallback<String> valueCallback) {
        this.mWebView.evaluateJavascript(str, new ValueCallback<String>() { // from class: me.jingbin.web.ByLoadJsHolder.1
            @Override // android.webkit.ValueCallback
            public void onReceiveValue(String str2) {
                ValueCallback valueCallback2 = valueCallback;
                if (valueCallback2 != null) {
                    valueCallback2.onReceiveValue(str2);
                }
            }
        });
    }

    public void quickCallJs(String str, ValueCallback<String> valueCallback, String... strArr) {
        StringBuilder sb = new StringBuilder("javascript:");
        sb.append(str);
        if (strArr == null || strArr.length == 0) {
            sb.append("()");
        } else {
            sb.append("(");
            sb.append(concat(strArr));
            sb.append(")");
        }
        loadJs(sb.toString(), valueCallback);
    }

    private String concat(String... strArr) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strArr.length; i++) {
            String str = strArr[i];
            if (!isJson(str)) {
                sb.append("\"");
                sb.append(str);
                sb.append("\"");
            } else {
                sb.append(str);
            }
            if (i != strArr.length - 1) {
                sb.append(" , ");
            }
        }
        return sb.toString();
    }

    public void quickCallJs(String str, String... strArr) {
        quickCallJs(str, null, strArr);
    }

    public void quickCallJs(String str) {
        quickCallJs(str, null);
    }

    static boolean isJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (str.startsWith("[")) {
                new JSONArray(str);
            } else {
                new JSONObject(str);
            }
            return true;
        } catch (JSONException unused) {
            return false;
        }
    }
}
