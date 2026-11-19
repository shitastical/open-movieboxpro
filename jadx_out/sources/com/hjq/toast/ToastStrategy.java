package com.hjq.toast;

import android.app.Activity;
import android.app.AppOpsManager;
import android.app.Application;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.provider.Settings;
import com.google.android.gms.common.ConnectionResult;
import com.hjq.toast.config.IToast;
import com.hjq.toast.config.IToastStrategy;
import com.hjq.toast.config.IToastStyle;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
public class ToastStrategy implements IToastStrategy {
    private static final int DEFAULT_DELAY_TIMEOUT = 200;
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());
    public static final int SHOW_STRATEGY_TYPE_IMMEDIATELY = 0;
    public static final int SHOW_STRATEGY_TYPE_QUEUE = 1;
    private Application mApplication;
    private final Object mCancelMessageToken;
    private volatile long mLastShowToastMillis;
    private final Object mShowMessageToken;
    private final int mShowStrategyType;
    private WeakReference<IToast> mToastReference;

    public ToastStrategy() {
        this(0);
    }

    @Override // com.hjq.toast.config.IToastStrategy
    public void registerStrategy(Application application) {
        this.mApplication = application;
    }

    public ToastStrategy(int i) {
        this.mShowMessageToken = new Object();
        this.mCancelMessageToken = new Object();
        this.mShowStrategyType = i;
        if (i != 0 && i != 1) {
            throw new IllegalArgumentException("Please don't pass non-existent toast show strategy");
        }
    }

    @Override // com.hjq.toast.config.IToastStrategy
    public IToast createToast(ToastParams toastParams) {
        IToast systemToast;
        Activity foregroundActivity = getForegroundActivity();
        if (Build.VERSION.SDK_INT >= 23 && Settings.canDrawOverlays(this.mApplication)) {
            systemToast = new GlobalToast(this.mApplication);
        } else if (!toastParams.crossPageShow && isActivityAvailable(foregroundActivity)) {
            systemToast = new ActivityToast(foregroundActivity);
        } else if (Build.VERSION.SDK_INT == 25) {
            systemToast = new SafeToast(this.mApplication);
        } else if (Build.VERSION.SDK_INT < 29 && !areNotificationsEnabled(this.mApplication)) {
            systemToast = new NotificationToast(this.mApplication);
        } else {
            systemToast = new SystemToast(this.mApplication);
        }
        if (isSupportToastStyle(systemToast) || !onlyShowSystemToastStyle()) {
            diyToastStyle(systemToast, toastParams.style);
        }
        return systemToast;
    }

    @Override // com.hjq.toast.config.IToastStrategy
    public void showToast(ToastParams toastParams) {
        int i = this.mShowStrategyType;
        if (i == 0) {
            Handler handler = HANDLER;
            handler.removeCallbacksAndMessages(this.mShowMessageToken);
            handler.postAtTime(new ShowToastRunnable(toastParams), this.mShowMessageToken, SystemClock.uptimeMillis() + toastParams.delayMillis + ((long) (toastParams.crossPageShow ? 0 : 200)));
        } else {
            if (i != 1) {
                return;
            }
            long jUptimeMillis = SystemClock.uptimeMillis() + toastParams.delayMillis + ((long) (toastParams.crossPageShow ? 0 : 200));
            long jGenerateToastWaitMillis = generateToastWaitMillis(toastParams);
            if (jUptimeMillis < this.mLastShowToastMillis + jGenerateToastWaitMillis) {
                jUptimeMillis = this.mLastShowToastMillis + jGenerateToastWaitMillis;
            }
            HANDLER.postAtTime(new ShowToastRunnable(toastParams), this.mShowMessageToken, jUptimeMillis);
            this.mLastShowToastMillis = jUptimeMillis;
        }
    }

    @Override // com.hjq.toast.config.IToastStrategy
    public void cancelToast() {
        Handler handler = HANDLER;
        handler.removeCallbacksAndMessages(this.mCancelMessageToken);
        handler.postAtTime(new CancelToastRunnable(), this.mCancelMessageToken, SystemClock.uptimeMillis());
    }

    protected boolean isSupportToastStyle(IToast iToast) {
        return (iToast instanceof CustomToast) || Build.VERSION.SDK_INT < 30 || this.mApplication.getApplicationInfo().targetSdkVersion < 30;
    }

    protected void diyToastStyle(IToast iToast, IToastStyle<?> iToastStyle) {
        iToast.setView(iToastStyle.createView(this.mApplication));
        iToast.setGravity(iToastStyle.getGravity(), iToastStyle.getXOffset(), iToastStyle.getYOffset());
        iToast.setMargin(iToastStyle.getHorizontalMargin(), iToastStyle.getVerticalMargin());
    }

    protected int generateToastWaitMillis(ToastParams toastParams) {
        if (toastParams.duration == 0) {
            return 1000;
        }
        if (toastParams.duration == 1) {
            return ConnectionResult.DRIVE_EXTERNAL_STORAGE_REQUIRED;
        }
        return 0;
    }

    private class ShowToastRunnable implements Runnable {
        private final ToastParams mToastParams;

        private ShowToastRunnable(ToastParams toastParams) {
            this.mToastParams = toastParams;
        }

        @Override // java.lang.Runnable
        public void run() {
            IToast iToast = ToastStrategy.this.mToastReference != null ? (IToast) ToastStrategy.this.mToastReference.get() : null;
            if (iToast != null) {
                iToast.cancel();
            }
            IToast iToastCreateToast = ToastStrategy.this.createToast(this.mToastParams);
            ToastStrategy.this.mToastReference = new WeakReference(iToastCreateToast);
            iToastCreateToast.setDuration(this.mToastParams.duration);
            iToastCreateToast.setText(this.mToastParams.text);
            iToastCreateToast.show();
        }
    }

    private class CancelToastRunnable implements Runnable {
        private CancelToastRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            IToast iToast = ToastStrategy.this.mToastReference != null ? (IToast) ToastStrategy.this.mToastReference.get() : null;
            if (iToast == null) {
                return;
            }
            iToast.cancel();
        }
    }

    protected boolean onlyShowSystemToastStyle() {
        return isChangeEnabledCompat(147798919L);
    }

    protected boolean isChangeEnabledCompat(long j) {
        if (Build.VERSION.SDK_INT < 30) {
            return true;
        }
        try {
            Method method = Class.forName("android.app.compat.CompatChanges").getMethod("isChangeEnabled", Long.TYPE);
            method.setAccessible(true);
            return Boolean.parseBoolean(String.valueOf(method.invoke(null, Long.valueOf(j))));
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }

    protected boolean areNotificationsEnabled(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return ((NotificationManager) context.getSystemService(NotificationManager.class)).areNotificationsEnabled();
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        try {
            Class<?> cls = appOpsManager.getClass();
            Class<?> cls2 = Integer.TYPE;
            Method method = cls.getMethod("checkOpNoThrow", cls2, cls2, String.class);
            Integer num = (Integer) appOpsManager.getClass().getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class);
            num.intValue();
            return ((Integer) method.invoke(appOpsManager, num, Integer.valueOf(context.getApplicationInfo().uid), context.getPackageName())).intValue() == 0;
        } catch (IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException e) {
            e.printStackTrace();
            return true;
        }
    }

    protected Activity getForegroundActivity() {
        return ActivityStack.getInstance().getForegroundActivity();
    }

    protected boolean isActivityAvailable(Activity activity) {
        if (activity == null || activity.isFinishing()) {
            return false;
        }
        return !activity.isDestroyed();
    }
}
