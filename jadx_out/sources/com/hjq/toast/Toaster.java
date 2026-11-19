package com.hjq.toast;

import android.app.Application;
import android.content.res.Resources;
import com.hjq.toast.config.IToastInterceptor;
import com.hjq.toast.config.IToastStrategy;
import com.hjq.toast.config.IToastStyle;
import com.hjq.toast.style.BlackToastStyle;
import com.hjq.toast.style.CustomToastStyle;
import com.hjq.toast.style.LocationToastStyle;

/* JADX INFO: loaded from: classes5.dex */
public final class Toaster {
    private static Application sApplication;
    private static Boolean sDebugMode;
    private static IToastInterceptor sToastInterceptor;
    private static IToastStrategy sToastStrategy;
    private static IToastStyle<?> sToastStyle;

    private Toaster() {
    }

    public static void init(Application application) {
        init(application, sToastStyle);
    }

    public static void init(Application application, IToastStrategy iToastStrategy) {
        init(application, iToastStrategy, null);
    }

    public static void init(Application application, IToastStyle<?> iToastStyle) {
        init(application, null, iToastStyle);
    }

    public static void init(Application application, IToastStrategy iToastStrategy, IToastStyle<?> iToastStyle) {
        if (isInit()) {
            return;
        }
        sApplication = application;
        ActivityStack.getInstance().register(application);
        if (iToastStrategy == null) {
            iToastStrategy = new ToastStrategy();
        }
        setStrategy(iToastStrategy);
        if (iToastStyle == null) {
            iToastStyle = new BlackToastStyle();
        }
        setStyle(iToastStyle);
    }

    public static boolean isInit() {
        return (sApplication == null || sToastStrategy == null || sToastStyle == null) ? false : true;
    }

    public static void delayedShow(int i, long j) {
        delayedShow(stringIdToCharSequence(i), j);
    }

    public static void delayedShow(Object obj, long j) {
        delayedShow(objectToCharSequence(obj), j);
    }

    public static void delayedShow(CharSequence charSequence, long j) {
        ToastParams toastParams = new ToastParams();
        toastParams.text = charSequence;
        toastParams.delayMillis = j;
        show(toastParams);
    }

    public static void debugShow(int i) {
        debugShow(stringIdToCharSequence(i));
    }

    public static void debugShow(Object obj) {
        debugShow(objectToCharSequence(obj));
    }

    public static void debugShow(CharSequence charSequence) {
        if (isDebugMode()) {
            ToastParams toastParams = new ToastParams();
            toastParams.text = charSequence;
            show(toastParams);
        }
    }

    public static void showShort(int i) {
        showShort(stringIdToCharSequence(i));
    }

    public static void showShort(Object obj) {
        showShort(objectToCharSequence(obj));
    }

    public static void showShort(CharSequence charSequence) {
        ToastParams toastParams = new ToastParams();
        toastParams.text = charSequence;
        toastParams.duration = 0;
        show(toastParams);
    }

    public static void showLong(int i) {
        showLong(stringIdToCharSequence(i));
    }

    public static void showLong(Object obj) {
        showLong(objectToCharSequence(obj));
    }

    public static void showLong(CharSequence charSequence) {
        ToastParams toastParams = new ToastParams();
        toastParams.text = charSequence;
        toastParams.duration = 1;
        show(toastParams);
    }

    public static void show(int i) {
        show(stringIdToCharSequence(i));
    }

    public static void show(Object obj) {
        show(objectToCharSequence(obj));
    }

    public static void show(CharSequence charSequence) {
        ToastParams toastParams = new ToastParams();
        toastParams.text = charSequence;
        show(toastParams);
    }

    public static void show(ToastParams toastParams) {
        checkInitStatus();
        if (toastParams.text == null || toastParams.text.length() == 0) {
            return;
        }
        if (toastParams.strategy == null) {
            toastParams.strategy = sToastStrategy;
        }
        if (toastParams.interceptor == null) {
            if (sToastInterceptor == null) {
                sToastInterceptor = new ToastLogInterceptor();
            }
            toastParams.interceptor = sToastInterceptor;
        }
        if (toastParams.style == null) {
            toastParams.style = sToastStyle;
        }
        if (toastParams.interceptor.intercept(toastParams)) {
            return;
        }
        if (toastParams.duration == -1) {
            toastParams.duration = toastParams.text.length() > 20 ? 1 : 0;
        }
        toastParams.strategy.showToast(toastParams);
    }

    public static void cancel() {
        sToastStrategy.cancelToast();
    }

    public static void setGravity(int i) {
        setGravity(i, 0, 0);
    }

    public static void setGravity(int i, int i2, int i3) {
        setGravity(i, i2, i3, 0.0f, 0.0f);
    }

    public static void setGravity(int i, int i2, int i3, float f, float f2) {
        sToastStyle = new LocationToastStyle(sToastStyle, i, i2, i3, f, f2);
    }

    public static void setView(int i) {
        IToastStyle<?> iToastStyle;
        if (i > 0 && (iToastStyle = sToastStyle) != null) {
            setStyle(new CustomToastStyle(i, iToastStyle.getGravity(), sToastStyle.getXOffset(), sToastStyle.getYOffset(), sToastStyle.getHorizontalMargin(), sToastStyle.getVerticalMargin()));
        }
    }

    public static void setStyle(IToastStyle<?> iToastStyle) {
        if (iToastStyle == null) {
            return;
        }
        sToastStyle = iToastStyle;
    }

    public static IToastStyle<?> getStyle() {
        return sToastStyle;
    }

    public static void setStrategy(IToastStrategy iToastStrategy) {
        if (iToastStrategy == null) {
            return;
        }
        sToastStrategy = iToastStrategy;
        iToastStrategy.registerStrategy(sApplication);
    }

    public static IToastStrategy getStrategy() {
        return sToastStrategy;
    }

    public static void setInterceptor(IToastInterceptor iToastInterceptor) {
        sToastInterceptor = iToastInterceptor;
    }

    public static IToastInterceptor getInterceptor() {
        return sToastInterceptor;
    }

    public static void setDebugMode(boolean z) {
        sDebugMode = Boolean.valueOf(z);
    }

    private static void checkInitStatus() {
        if (sApplication == null) {
            throw new IllegalStateException("Toaster has not been initialized");
        }
    }

    static boolean isDebugMode() {
        if (sDebugMode == null) {
            checkInitStatus();
            sDebugMode = Boolean.valueOf((sApplication.getApplicationInfo().flags & 2) != 0);
        }
        return sDebugMode.booleanValue();
    }

    private static CharSequence stringIdToCharSequence(int i) {
        checkInitStatus();
        try {
            return sApplication.getResources().getText(i);
        } catch (Resources.NotFoundException unused) {
            return String.valueOf(i);
        }
    }

    private static CharSequence objectToCharSequence(Object obj) {
        return obj != null ? obj.toString() : "null";
    }
}
