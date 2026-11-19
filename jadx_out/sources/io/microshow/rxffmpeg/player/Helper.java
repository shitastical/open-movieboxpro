package io.microshow.rxffmpeg.player;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import androidx.core.view.InputDeviceCompat;
import org.joda.time.DateTimeConstants;

/* JADX INFO: loaded from: classes5.dex */
public class Helper {
    private static long lastClickTime;

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getFullScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        scanForActivity(context).getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static Activity scanForActivity(Context context) {
        if (context == null) {
            return null;
        }
        if (context instanceof Activity) {
            return (Activity) context;
        }
        if (context instanceof ContextWrapper) {
            return scanForActivity(((ContextWrapper) context).getBaseContext());
        }
        return null;
    }

    public static ViewGroup getDecorView(Context context) {
        Activity activityScanForActivity = scanForActivity(context);
        if (activityScanForActivity == null) {
            return null;
        }
        return (ViewGroup) activityScanForActivity.getWindow().getDecorView();
    }

    public static ViewGroup getDecorView(Activity activity) {
        if (activity == null) {
            return null;
        }
        return (ViewGroup) activity.getWindow().getDecorView();
    }

    public static void showSysBar(Activity activity, ViewGroup viewGroup) {
        viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() & (-4099));
        activity.getWindow().clearFlags(1024);
    }

    public static void hideSysBar(Activity activity, ViewGroup viewGroup) {
        viewGroup.setSystemUiVisibility(viewGroup.getSystemUiVisibility() | InputDeviceCompat.SOURCE_TOUCHSCREEN);
        activity.getWindow().setFlags(1024, 1024);
    }

    public static ViewGroup setFullScreen(Context context, boolean z) {
        Activity activityScanForActivity = scanForActivity(context);
        ViewGroup decorView = getDecorView(activityScanForActivity);
        if (decorView == null) {
            return null;
        }
        if (z) {
            hideSysBar(activityScanForActivity, decorView);
            activityScanForActivity.setRequestedOrientation(0);
        } else {
            showSysBar(activityScanForActivity, decorView);
            activityScanForActivity.setRequestedOrientation(1);
        }
        return decorView;
    }

    public static String secdsToDateFormat(int i, int i2) {
        String str;
        String str2;
        long j = i / DateTimeConstants.SECONDS_PER_HOUR;
        long j2 = (i % DateTimeConstants.SECONDS_PER_HOUR) / 60;
        long j3 = i % 60;
        String str3 = "00";
        if (j <= 0) {
            str = "00";
        } else if (j < 10) {
            str = "0" + j;
        } else {
            str = j + "";
        }
        if (j2 <= 0) {
            str2 = "00";
        } else if (j2 < 10) {
            str2 = "0" + j2;
        } else {
            str2 = j2 + "";
        }
        if (j3 > 0) {
            if (j3 < 10) {
                str3 = "0" + j3;
            } else {
                str3 = j3 + "";
            }
        }
        if (i2 >= 3600) {
            return str + ":" + str2 + ":" + str3;
        }
        return str2 + ":" + str3;
    }

    public static synchronized boolean isFastClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = jCurrentTimeMillis;
        return false;
    }
}
