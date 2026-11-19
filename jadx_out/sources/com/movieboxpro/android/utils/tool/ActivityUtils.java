package com.movieboxpro.android.utils.tool;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public final class ActivityUtils {
    private ActivityUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void addActivityLifecycleCallbacks(final Utils.ActivityLifecycleCallbacks callbacks) {
        UtilsBridge.addActivityLifecycleCallbacks(callbacks);
    }

    public static void addActivityLifecycleCallbacks(final Activity activity, final Utils.ActivityLifecycleCallbacks callbacks) {
        UtilsBridge.addActivityLifecycleCallbacks(activity, callbacks);
    }

    public static void removeActivityLifecycleCallbacks(final Utils.ActivityLifecycleCallbacks callbacks) {
        UtilsBridge.removeActivityLifecycleCallbacks(callbacks);
    }

    public static void removeActivityLifecycleCallbacks(final Activity activity) {
        UtilsBridge.removeActivityLifecycleCallbacks(activity);
    }

    public static void removeActivityLifecycleCallbacks(final Activity activity, final Utils.ActivityLifecycleCallbacks callbacks) {
        UtilsBridge.removeActivityLifecycleCallbacks(activity, callbacks);
    }

    public static Activity getActivityByContext(Context context) {
        Activity activityByContextInner = getActivityByContextInner(context);
        if (isActivityAlive(activityByContextInner)) {
            return activityByContextInner;
        }
        return null;
    }

    private static Activity getActivityByContextInner(Context context) {
        if (context == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            Activity activityFromDecorContext = getActivityFromDecorContext(context);
            if (activityFromDecorContext == null) {
                arrayList.add(context);
                context = ((ContextWrapper) context).getBaseContext();
                if (context == null || arrayList.contains(context)) {
                    break;
                }
            } else {
                return activityFromDecorContext;
            }
        }
        return null;
    }

    private static Activity getActivityFromDecorContext(Context context) {
        if (context != null && context.getClass().getName().equals("com.android.internal.policy.DecorContext")) {
            try {
                Field declaredField = context.getClass().getDeclaredField("mActivityContext");
                declaredField.setAccessible(true);
                return (Activity) ((WeakReference) declaredField.get(context)).get();
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static boolean isActivityExists(final String pkg, final String cls) {
        Intent intent = new Intent();
        intent.setClassName(pkg, cls);
        PackageManager packageManager = Utils.getApp().getPackageManager();
        return (packageManager.resolveActivity(intent, 0) == null || intent.resolveActivity(packageManager) == null || packageManager.queryIntentActivities(intent, 0).size() == 0) ? false : true;
    }

    public static void startActivity(final Class<? extends Activity> clz) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), clz.getName(), (Bundle) null);
    }

    public static void startActivity(final Class<? extends Activity> clz, final Bundle options) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), clz.getName(), options);
    }

    public static void startActivity(final Class<? extends Activity> clz, final int enterAnim, final int exitAnim) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, topActivityOrApp.getPackageName(), clz.getName(), getOptionsBundle(topActivityOrApp, enterAnim, exitAnim));
    }

    public static void startActivity(final Activity activity, final Class<? extends Activity> clz) {
        startActivity(activity, (Bundle) null, activity.getPackageName(), clz.getName(), (Bundle) null);
    }

    public static void startActivity(final Activity activity, final Class<? extends Activity> clz, final Bundle options) {
        startActivity(activity, (Bundle) null, activity.getPackageName(), clz.getName(), options);
    }

    public static void startActivity(final Activity activity, final Class<? extends Activity> clz, final View... sharedElements) {
        startActivity(activity, (Bundle) null, activity.getPackageName(), clz.getName(), getOptionsBundle(activity, sharedElements));
    }

    public static void startActivity(final Activity activity, final Class<? extends Activity> clz, final int enterAnim, final int exitAnim) {
        startActivity(activity, (Bundle) null, activity.getPackageName(), clz.getName(), getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startActivity(final Bundle extras, final Class<? extends Activity> clz) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, extras, topActivityOrApp.getPackageName(), clz.getName(), (Bundle) null);
    }

    public static void startActivity(final Bundle extras, final Class<? extends Activity> clz, final Bundle options) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, extras, topActivityOrApp.getPackageName(), clz.getName(), options);
    }

    public static void startActivity(final Bundle extras, final Class<? extends Activity> clz, final int enterAnim, final int exitAnim) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, extras, topActivityOrApp.getPackageName(), clz.getName(), getOptionsBundle(topActivityOrApp, enterAnim, exitAnim));
    }

    public static void startActivity(final Bundle extras, final Activity activity, final Class<? extends Activity> clz) {
        startActivity(activity, extras, activity.getPackageName(), clz.getName(), (Bundle) null);
    }

    public static void startActivity(final Bundle extras, final Activity activity, final Class<? extends Activity> clz, final Bundle options) {
        startActivity(activity, extras, activity.getPackageName(), clz.getName(), options);
    }

    public static void startActivity(final Bundle extras, final Activity activity, final Class<? extends Activity> clz, final View... sharedElements) {
        startActivity(activity, extras, activity.getPackageName(), clz.getName(), getOptionsBundle(activity, sharedElements));
    }

    public static void startActivity(final Bundle extras, final Activity activity, final Class<? extends Activity> clz, final int enterAnim, final int exitAnim) {
        startActivity(activity, extras, activity.getPackageName(), clz.getName(), getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startActivity(final String pkg, final String cls) {
        startActivity(getTopActivityOrApp(), (Bundle) null, pkg, cls, (Bundle) null);
    }

    public static void startActivity(final String pkg, final String cls, final Bundle options) {
        startActivity(getTopActivityOrApp(), (Bundle) null, pkg, cls, options);
    }

    public static void startActivity(final String pkg, final String cls, final int enterAnim, final int exitAnim) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, (Bundle) null, pkg, cls, getOptionsBundle(topActivityOrApp, enterAnim, exitAnim));
    }

    public static void startActivity(final Activity activity, final String pkg, final String cls) {
        startActivity(activity, (Bundle) null, pkg, cls, (Bundle) null);
    }

    public static void startActivity(final Activity activity, final String pkg, final String cls, final Bundle options) {
        startActivity(activity, (Bundle) null, pkg, cls, options);
    }

    public static void startActivity(final Activity activity, final String pkg, final String cls, final View... sharedElements) {
        startActivity(activity, (Bundle) null, pkg, cls, getOptionsBundle(activity, sharedElements));
    }

    public static void startActivity(final Activity activity, final String pkg, final String cls, final int enterAnim, final int exitAnim) {
        startActivity(activity, (Bundle) null, pkg, cls, getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startActivity(final Bundle extras, final String pkg, final String cls) {
        startActivity(getTopActivityOrApp(), extras, pkg, cls, (Bundle) null);
    }

    public static void startActivity(final Bundle extras, final String pkg, final String cls, final Bundle options) {
        startActivity(getTopActivityOrApp(), extras, pkg, cls, options);
    }

    public static void startActivity(final Bundle extras, final String pkg, final String cls, final int enterAnim, final int exitAnim) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivity(topActivityOrApp, extras, pkg, cls, getOptionsBundle(topActivityOrApp, enterAnim, exitAnim));
    }

    public static void startActivity(final Bundle extras, final Activity activity, final String pkg, final String cls) {
        startActivity(activity, extras, pkg, cls, (Bundle) null);
    }

    public static void startActivity(final Bundle extras, final Activity activity, final String pkg, final String cls, final Bundle options) {
        startActivity(activity, extras, pkg, cls, options);
    }

    public static void startActivity(final Bundle extras, final Activity activity, final String pkg, final String cls, final View... sharedElements) {
        startActivity(activity, extras, pkg, cls, getOptionsBundle(activity, sharedElements));
    }

    public static void startActivity(final Bundle extras, final Activity activity, final String pkg, final String cls, final int enterAnim, final int exitAnim) {
        startActivity(activity, extras, pkg, cls, getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static boolean startActivity(final Intent intent) {
        return startActivity(intent, getTopActivityOrApp(), (Bundle) null);
    }

    public static boolean startActivity(final Intent intent, final Bundle options) {
        return startActivity(intent, getTopActivityOrApp(), options);
    }

    public static boolean startActivity(final Intent intent, final int enterAnim, final int exitAnim) {
        Context topActivityOrApp = getTopActivityOrApp();
        return startActivity(intent, topActivityOrApp, getOptionsBundle(topActivityOrApp, enterAnim, exitAnim));
    }

    public static void startActivity(final Activity activity, final Intent intent) {
        startActivity(intent, activity, (Bundle) null);
    }

    public static void startActivity(final Activity activity, final Intent intent, final Bundle options) {
        startActivity(intent, activity, options);
    }

    public static void startActivity(final Activity activity, final Intent intent, final View... sharedElements) {
        startActivity(intent, activity, getOptionsBundle(activity, sharedElements));
    }

    public static void startActivity(final Activity activity, final Intent intent, final int enterAnim, final int exitAnim) {
        startActivity(intent, activity, getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startActivityForResult(final Activity activity, final Class<? extends Activity> clz, final int requestCode) {
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), clz.getName(), requestCode, (Bundle) null);
    }

    public static void startActivityForResult(final Activity activity, final Class<? extends Activity> clz, final int requestCode, final Bundle options) {
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), clz.getName(), requestCode, options);
    }

    public static void startActivityForResult(final Activity activity, final Class<? extends Activity> clz, final int requestCode, final View... sharedElements) {
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), clz.getName(), requestCode, getOptionsBundle(activity, sharedElements));
    }

    public static void startActivityForResult(final Activity activity, final Class<? extends Activity> clz, final int requestCode, final int enterAnim, final int exitAnim) {
        startActivityForResult(activity, (Bundle) null, activity.getPackageName(), clz.getName(), requestCode, getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startActivityForResult(final Bundle extras, final Activity activity, final Class<? extends Activity> clz, final int requestCode) {
        startActivityForResult(activity, extras, activity.getPackageName(), clz.getName(), requestCode, (Bundle) null);
    }

    public static void startActivityForResult(final Bundle extras, final Activity activity, final Class<? extends Activity> clz, final int requestCode, final Bundle options) {
        startActivityForResult(activity, extras, activity.getPackageName(), clz.getName(), requestCode, options);
    }

    public static void startActivityForResult(final Bundle extras, final Activity activity, final Class<? extends Activity> clz, final int requestCode, final View... sharedElements) {
        startActivityForResult(activity, extras, activity.getPackageName(), clz.getName(), requestCode, getOptionsBundle(activity, sharedElements));
    }

    public static void startActivityForResult(final Bundle extras, final Activity activity, final Class<? extends Activity> clz, final int requestCode, final int enterAnim, final int exitAnim) {
        startActivityForResult(activity, extras, activity.getPackageName(), clz.getName(), requestCode, getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startActivityForResult(final Bundle extras, final Activity activity, final String pkg, final String cls, final int requestCode) {
        startActivityForResult(activity, extras, pkg, cls, requestCode, (Bundle) null);
    }

    public static void startActivityForResult(final Bundle extras, final Activity activity, final String pkg, final String cls, final int requestCode, final Bundle options) {
        startActivityForResult(activity, extras, pkg, cls, requestCode, options);
    }

    public static void startActivityForResult(final Bundle extras, final Activity activity, final String pkg, final String cls, final int requestCode, final View... sharedElements) {
        startActivityForResult(activity, extras, pkg, cls, requestCode, getOptionsBundle(activity, sharedElements));
    }

    public static void startActivityForResult(final Bundle extras, final Activity activity, final String pkg, final String cls, final int requestCode, final int enterAnim, final int exitAnim) {
        startActivityForResult(activity, extras, pkg, cls, requestCode, getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startActivityForResult(final Activity activity, final Intent intent, final int requestCode) {
        startActivityForResult(intent, activity, requestCode, (Bundle) null);
    }

    public static void startActivityForResult(final Activity activity, final Intent intent, final int requestCode, final Bundle options) {
        startActivityForResult(intent, activity, requestCode, options);
    }

    public static void startActivityForResult(final Activity activity, final Intent intent, final int requestCode, final View... sharedElements) {
        startActivityForResult(intent, activity, requestCode, getOptionsBundle(activity, sharedElements));
    }

    public static void startActivityForResult(final Activity activity, final Intent intent, final int requestCode, final int enterAnim, final int exitAnim) {
        startActivityForResult(intent, activity, requestCode, getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startActivityForResult(final Fragment fragment, final Class<? extends Activity> clz, final int requestCode) {
        startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), clz.getName(), requestCode, (Bundle) null);
    }

    public static void startActivityForResult(final Fragment fragment, final Class<? extends Activity> clz, final int requestCode, final Bundle options) {
        startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), clz.getName(), requestCode, options);
    }

    public static void startActivityForResult(final Fragment fragment, final Class<? extends Activity> clz, final int requestCode, final View... sharedElements) {
        startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), clz.getName(), requestCode, getOptionsBundle(fragment, sharedElements));
    }

    public static void startActivityForResult(final Fragment fragment, final Class<? extends Activity> clz, final int requestCode, final int enterAnim, final int exitAnim) {
        startActivityForResult(fragment, (Bundle) null, Utils.getApp().getPackageName(), clz.getName(), requestCode, getOptionsBundle(fragment, enterAnim, exitAnim));
    }

    public static void startActivityForResult(final Bundle extras, final Fragment fragment, final Class<? extends Activity> clz, final int requestCode) {
        startActivityForResult(fragment, extras, Utils.getApp().getPackageName(), clz.getName(), requestCode, (Bundle) null);
    }

    public static void startActivityForResult(final Bundle extras, final Fragment fragment, final Class<? extends Activity> clz, final int requestCode, final Bundle options) {
        startActivityForResult(fragment, extras, Utils.getApp().getPackageName(), clz.getName(), requestCode, options);
    }

    public static void startActivityForResult(final Bundle extras, final Fragment fragment, final Class<? extends Activity> clz, final int requestCode, final View... sharedElements) {
        startActivityForResult(fragment, extras, Utils.getApp().getPackageName(), clz.getName(), requestCode, getOptionsBundle(fragment, sharedElements));
    }

    public static void startActivityForResult(final Bundle extras, final Fragment fragment, final Class<? extends Activity> clz, final int requestCode, final int enterAnim, final int exitAnim) {
        startActivityForResult(fragment, extras, Utils.getApp().getPackageName(), clz.getName(), requestCode, getOptionsBundle(fragment, enterAnim, exitAnim));
    }

    public static void startActivityForResult(final Bundle extras, final Fragment fragment, final String pkg, final String cls, final int requestCode) {
        startActivityForResult(fragment, extras, pkg, cls, requestCode, (Bundle) null);
    }

    public static void startActivityForResult(final Bundle extras, final Fragment fragment, final String pkg, final String cls, final int requestCode, final Bundle options) {
        startActivityForResult(fragment, extras, pkg, cls, requestCode, options);
    }

    public static void startActivityForResult(final Bundle extras, final Fragment fragment, final String pkg, final String cls, final int requestCode, final View... sharedElements) {
        startActivityForResult(fragment, extras, pkg, cls, requestCode, getOptionsBundle(fragment, sharedElements));
    }

    public static void startActivityForResult(final Bundle extras, final Fragment fragment, final String pkg, final String cls, final int requestCode, final int enterAnim, final int exitAnim) {
        startActivityForResult(fragment, extras, pkg, cls, requestCode, getOptionsBundle(fragment, enterAnim, exitAnim));
    }

    public static void startActivityForResult(final Fragment fragment, final Intent intent, final int requestCode) {
        startActivityForResult(intent, fragment, requestCode, (Bundle) null);
    }

    public static void startActivityForResult(final Fragment fragment, final Intent intent, final int requestCode, final Bundle options) {
        startActivityForResult(intent, fragment, requestCode, options);
    }

    public static void startActivityForResult(final Fragment fragment, final Intent intent, final int requestCode, final View... sharedElements) {
        startActivityForResult(intent, fragment, requestCode, getOptionsBundle(fragment, sharedElements));
    }

    public static void startActivityForResult(final Fragment fragment, final Intent intent, final int requestCode, final int enterAnim, final int exitAnim) {
        startActivityForResult(intent, fragment, requestCode, getOptionsBundle(fragment, enterAnim, exitAnim));
    }

    public static void startActivities(final Intent[] intents) {
        startActivities(intents, getTopActivityOrApp(), (Bundle) null);
    }

    public static void startActivities(final Intent[] intents, final Bundle options) {
        startActivities(intents, getTopActivityOrApp(), options);
    }

    public static void startActivities(final Intent[] intents, final int enterAnim, final int exitAnim) {
        Context topActivityOrApp = getTopActivityOrApp();
        startActivities(intents, topActivityOrApp, getOptionsBundle(topActivityOrApp, enterAnim, exitAnim));
    }

    public static void startActivities(final Activity activity, final Intent[] intents) {
        startActivities(intents, activity, (Bundle) null);
    }

    public static void startActivities(final Activity activity, final Intent[] intents, final Bundle options) {
        startActivities(intents, activity, options);
    }

    public static void startActivities(final Activity activity, final Intent[] intents, final int enterAnim, final int exitAnim) {
        startActivities(intents, activity, getOptionsBundle(activity, enterAnim, exitAnim));
    }

    public static void startHomeActivity() {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        intent.setFlags(268435456);
        startActivity(intent);
    }

    public static void startLauncherActivity() {
        startLauncherActivity(Utils.getApp().getPackageName());
    }

    public static void startLauncherActivity(final String pkg) {
        String launcherActivity = getLauncherActivity(pkg);
        if (TextUtils.isEmpty(launcherActivity)) {
            return;
        }
        startActivity(pkg, launcherActivity);
    }

    public static List<Activity> getActivityList() {
        return UtilsBridge.getActivityList();
    }

    public static String getLauncherActivity() {
        return getLauncherActivity(Utils.getApp().getPackageName());
    }

    public static String getLauncherActivity(final String pkg) {
        if (UtilsBridge.isSpace(pkg)) {
            return "";
        }
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setPackage(pkg);
        List<ResolveInfo> listQueryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
        return (listQueryIntentActivities == null || listQueryIntentActivities.size() == 0) ? "" : listQueryIntentActivities.get(0).activityInfo.name;
    }

    public static List<String> getMainActivities() {
        return getMainActivities(Utils.getApp().getPackageName());
    }

    public static List<String> getMainActivities(final String pkg) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.setPackage(pkg);
        List<ResolveInfo> listQueryIntentActivities = Utils.getApp().getPackageManager().queryIntentActivities(intent, 0);
        int size = listQueryIntentActivities.size();
        if (size == 0) {
            return arrayList;
        }
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
            if (resolveInfo.activityInfo.processName.equals(pkg)) {
                arrayList.add(resolveInfo.activityInfo.name);
            }
        }
        return arrayList;
    }

    public static Activity getTopActivity() {
        return UtilsBridge.getTopActivity();
    }

    public static boolean isActivityAlive(final Context context) {
        return isActivityAlive(getActivityByContext(context));
    }

    public static boolean isActivityAlive(final Activity activity) {
        return (activity == null || activity.isFinishing() || activity.isDestroyed()) ? false : true;
    }

    public static boolean isActivityExistsInStack(final Activity activity) {
        Iterator<Activity> it = UtilsBridge.getActivityList().iterator();
        while (it.hasNext()) {
            if (it.next().equals(activity)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isActivityExistsInStack(final Class<? extends Activity> clz) {
        Iterator<Activity> it = UtilsBridge.getActivityList().iterator();
        while (it.hasNext()) {
            if (it.next().getClass().equals(clz)) {
                return true;
            }
        }
        return false;
    }

    public static void finishActivity(final Activity activity) {
        finishActivity(activity, false);
    }

    public static void finishActivity(final Activity activity, final boolean isLoadAnim) {
        activity.finish();
        if (isLoadAnim) {
            return;
        }
        activity.overridePendingTransition(0, 0);
    }

    public static void finishActivity(final Activity activity, final int enterAnim, final int exitAnim) {
        activity.finish();
        activity.overridePendingTransition(enterAnim, exitAnim);
    }

    public static void finishActivity(final Class<? extends Activity> clz) {
        finishActivity(clz, false);
    }

    public static void finishActivity(final Class<? extends Activity> clz, final boolean isLoadAnim) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (activity.getClass().equals(clz)) {
                activity.finish();
                if (!isLoadAnim) {
                    activity.overridePendingTransition(0, 0);
                }
            }
        }
    }

    public static void finishActivity(final Class<? extends Activity> clz, final int enterAnim, final int exitAnim) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (activity.getClass().equals(clz)) {
                activity.finish();
                activity.overridePendingTransition(enterAnim, exitAnim);
            }
        }
    }

    public static boolean finishToActivity(final Activity activity, final boolean isIncludeSelf) {
        return finishToActivity(activity, isIncludeSelf, false);
    }

    public static boolean finishToActivity(final Activity activity, final boolean isIncludeSelf, final boolean isLoadAnim) {
        for (Activity activity2 : UtilsBridge.getActivityList()) {
            if (activity2.equals(activity)) {
                if (!isIncludeSelf) {
                    return true;
                }
                finishActivity(activity2, isLoadAnim);
                return true;
            }
            finishActivity(activity2, isLoadAnim);
        }
        return false;
    }

    public static boolean finishToActivity(final Activity activity, final boolean isIncludeSelf, final int enterAnim, final int exitAnim) {
        for (Activity activity2 : UtilsBridge.getActivityList()) {
            if (activity2.equals(activity)) {
                if (!isIncludeSelf) {
                    return true;
                }
                finishActivity(activity2, enterAnim, exitAnim);
                return true;
            }
            finishActivity(activity2, enterAnim, exitAnim);
        }
        return false;
    }

    public static boolean finishToActivity(final Class<? extends Activity> clz, final boolean isIncludeSelf) {
        return finishToActivity(clz, isIncludeSelf, false);
    }

    public static boolean finishToActivity(final Class<? extends Activity> clz, final boolean isIncludeSelf, final boolean isLoadAnim) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (activity.getClass().equals(clz)) {
                if (!isIncludeSelf) {
                    return true;
                }
                finishActivity(activity, isLoadAnim);
                return true;
            }
            finishActivity(activity, isLoadAnim);
        }
        return false;
    }

    public static boolean finishToActivity(final Class<? extends Activity> clz, final boolean isIncludeSelf, final int enterAnim, final int exitAnim) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (activity.getClass().equals(clz)) {
                if (!isIncludeSelf) {
                    return true;
                }
                finishActivity(activity, enterAnim, exitAnim);
                return true;
            }
            finishActivity(activity, enterAnim, exitAnim);
        }
        return false;
    }

    public static void finishOtherActivities(final Class<? extends Activity> clz) {
        finishOtherActivities(clz, false);
    }

    public static void finishOtherActivities(final Class<? extends Activity> clz, final boolean isLoadAnim) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (!activity.getClass().equals(clz)) {
                finishActivity(activity, isLoadAnim);
            }
        }
    }

    public static void finishOtherActivities(final Class<? extends Activity> clz, final int enterAnim, final int exitAnim) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            if (!activity.getClass().equals(clz)) {
                finishActivity(activity, enterAnim, exitAnim);
            }
        }
    }

    public static void finishAllActivities() {
        finishAllActivities(false);
    }

    public static void finishAllActivities(final boolean isLoadAnim) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            activity.finish();
            if (!isLoadAnim) {
                activity.overridePendingTransition(0, 0);
            }
        }
    }

    public static void finishAllActivities(final int enterAnim, final int exitAnim) {
        for (Activity activity : UtilsBridge.getActivityList()) {
            activity.finish();
            activity.overridePendingTransition(enterAnim, exitAnim);
        }
    }

    public static void finishAllActivitiesExceptNewest() {
        finishAllActivitiesExceptNewest(false);
    }

    public static void finishAllActivitiesExceptNewest(final boolean isLoadAnim) {
        List<Activity> activityList = UtilsBridge.getActivityList();
        for (int i = 1; i < activityList.size(); i++) {
            finishActivity(activityList.get(i), isLoadAnim);
        }
    }

    public static void finishAllActivitiesExceptNewest(final int enterAnim, final int exitAnim) {
        List<Activity> activityList = UtilsBridge.getActivityList();
        for (int i = 1; i < activityList.size(); i++) {
            finishActivity(activityList.get(i), enterAnim, exitAnim);
        }
    }

    public static Drawable getActivityIcon(final Activity activity) {
        return getActivityIcon(activity.getComponentName());
    }

    public static Drawable getActivityIcon(final Class<? extends Activity> clz) {
        return getActivityIcon(new ComponentName(Utils.getApp(), clz));
    }

    public static Drawable getActivityIcon(final ComponentName activityName) {
        try {
            return Utils.getApp().getPackageManager().getActivityIcon(activityName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Drawable getActivityLogo(final Activity activity) {
        return getActivityLogo(activity.getComponentName());
    }

    public static Drawable getActivityLogo(final Class<? extends Activity> clz) {
        return getActivityLogo(new ComponentName(Utils.getApp(), clz));
    }

    public static Drawable getActivityLogo(final ComponentName activityName) {
        try {
            return Utils.getApp().getPackageManager().getActivityLogo(activityName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void startActivity(final Context context, final Bundle extras, final String pkg, final String cls, final Bundle options) {
        Intent intent = new Intent();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.setComponent(new ComponentName(pkg, cls));
        startActivity(intent, context, options);
    }

    private static boolean startActivity(final Intent intent, final Context context, final Bundle options) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (options != null) {
            context.startActivity(intent, options);
            return true;
        }
        context.startActivity(intent);
        return true;
    }

    private static boolean isIntentAvailable(final Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private static boolean startActivityForResult(final Activity activity, final Bundle extras, final String pkg, final String cls, final int requestCode, final Bundle options) {
        Intent intent = new Intent();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.setComponent(new ComponentName(pkg, cls));
        return startActivityForResult(intent, activity, requestCode, options);
    }

    private static boolean startActivityForResult(final Intent intent, final Activity activity, final int requestCode, final Bundle options) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (options != null) {
            activity.startActivityForResult(intent, requestCode, options);
            return true;
        }
        activity.startActivityForResult(intent, requestCode);
        return true;
    }

    private static void startActivities(final Intent[] intents, final Context context, final Bundle options) {
        if (!(context instanceof Activity)) {
            for (Intent intent : intents) {
                intent.addFlags(268435456);
            }
        }
        if (options != null) {
            context.startActivities(intents, options);
        } else {
            context.startActivities(intents);
        }
    }

    private static boolean startActivityForResult(final Fragment fragment, final Bundle extras, final String pkg, final String cls, final int requestCode, final Bundle options) {
        Intent intent = new Intent();
        if (extras != null) {
            intent.putExtras(extras);
        }
        intent.setComponent(new ComponentName(pkg, cls));
        return startActivityForResult(intent, fragment, requestCode, options);
    }

    private static boolean startActivityForResult(final Intent intent, final Fragment fragment, final int requestCode, final Bundle options) {
        if (!isIntentAvailable(intent)) {
            Log.e("ActivityUtils", "intent is unavailable");
            return false;
        }
        if (fragment.getActivity() == null) {
            Log.e("ActivityUtils", "Fragment " + fragment + " not attached to Activity");
            return false;
        }
        if (options != null) {
            fragment.startActivityForResult(intent, requestCode, options);
            return true;
        }
        fragment.startActivityForResult(intent, requestCode);
        return true;
    }

    private static Bundle getOptionsBundle(final Fragment fragment, final int enterAnim, final int exitAnim) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return null;
        }
        return ActivityOptionsCompat.makeCustomAnimation(activity, enterAnim, exitAnim).toBundle();
    }

    private static Bundle getOptionsBundle(final Context context, final int enterAnim, final int exitAnim) {
        return ActivityOptionsCompat.makeCustomAnimation(context, enterAnim, exitAnim).toBundle();
    }

    private static Bundle getOptionsBundle(final Fragment fragment, final View[] sharedElements) {
        FragmentActivity activity = fragment.getActivity();
        if (activity == null) {
            return null;
        }
        return getOptionsBundle(activity, sharedElements);
    }

    private static Bundle getOptionsBundle(final Activity activity, final View[] sharedElements) {
        int length;
        if (sharedElements == null || (length = sharedElements.length) <= 0) {
            return null;
        }
        Pair[] pairArr = new Pair[length];
        for (int i = 0; i < length; i++) {
            View view = sharedElements[i];
            pairArr[i] = Pair.create(view, view.getTransitionName());
        }
        return ActivityOptionsCompat.makeSceneTransitionAnimation(activity, pairArr).toBundle();
    }

    public static Context getTopActivityOrApp() {
        if (UtilsBridge.isAppForeground()) {
            Activity topActivity = getTopActivity();
            return topActivity == null ? Utils.getApp() : topActivity;
        }
        return Utils.getApp();
    }
}
