package me.jingbin.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.text.TextUtils;
import master.flame.danmaku.danmaku.parser.IDataSource;

/* JADX INFO: loaded from: classes6.dex */
public class ByWebTools {
    static String getVideoHtmlBody(String str) {
        return "<html><head><meta name=\"viewport\" content=\"width=device-width\"><style type=\"text/css\" abt=\"234\"></style></head><body><video controls=\"\" autoplay=\"\" name=\"media\" style=\"display:block;width:100%;position:absolute;left:0;top:20%;\"><source src=\"" + str + "\" type=\"video/mp4\"></video></body></html>";
    }

    public static boolean hasPackage(Context context, String str) {
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                context.getPackageManager().getPackageInfo(str, 256);
                return true;
            } catch (PackageManager.NameNotFoundException unused) {
            }
        }
        return false;
    }

    public static boolean handleThirdApp(Activity activity, String str) {
        if (str.startsWith(IDataSource.SCHEME_HTTP_TAG)) {
            if (!str.contains(".apk")) {
                return false;
            }
            startActivity(activity, str);
            return true;
        }
        if (str.contains("alipays")) {
            if (hasPackage(activity, "com.eg.android.AlipayGphone")) {
                startActivity(activity, str);
            }
        } else if (str.contains("weixin://wap/pay")) {
            if (hasPackage(activity, "com.tencent.mm")) {
                startActivity(activity, str);
            }
        } else if (!str.contains("tbopen:") && !str.contains("openapp.jdmobile:") && !str.contains("jdmobile:") && !str.contains("zhihu:") && !str.contains("vipshop:") && !str.contains("youku:") && !str.contains("uclink:") && !str.contains("ucbrowser:") && !str.contains("newsapp:") && !str.contains("sinaweibo:") && !str.contains("suning:") && !str.contains("pinduoduo:") && !str.contains("qtt:") && !str.contains("baiduboxapp:") && !str.contains("baiduhaokan:")) {
            startActivity(activity, str);
        }
        return true;
    }

    private static void startActivity(Context context, String str) {
        try {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse(str));
            intent.addFlags(268435456);
            context.startActivity(intent);
        } catch (Exception unused) {
        }
    }

    static boolean isNetworkConnected(Context context) {
        if (context != null) {
            try {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    static int dip2px(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static String getUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.startsWith(IDataSource.SCHEME_HTTP_TAG)) {
            return str;
        }
        if (str.contains(IDataSource.SCHEME_HTTP_TAG)) {
            return str.substring(str.indexOf(IDataSource.SCHEME_HTTP_TAG));
        }
        if (str.startsWith("www")) {
            return "http://" + str;
        }
        if (str.contains(".me") || str.contains(".com") || str.contains(".cn")) {
            return "http://www." + str;
        }
        if (str.contains("www")) {
            return "";
        }
        return "http://m5.baidu.com/s?from=124n&word=" + str;
    }
}
