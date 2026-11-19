package com.movieboxpro.android.app;

import android.app.Activity;
import com.movieboxpro.android.utils.MLog;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class AppManager {
    private static final String TAG = "AppManager";
    public static Map<String, WeakReference<Activity>> map = new ConcurrentHashMap();

    public static boolean contains(Class<? extends Activity> actClass) {
        if (actClass == null) {
            return false;
        }
        return map.containsKey(actClass.getSimpleName());
    }

    public static void add(Activity activity) {
        if (activity != null) {
            String simpleName = activity.getClass().getSimpleName();
            if (map.containsKey(simpleName)) {
                return;
            }
            MLog.d(TAG, "add activity:" + simpleName);
            map.put(simpleName, new WeakReference<>(activity));
        }
    }

    public static void remove(Activity activity) {
        if (activity != null) {
            String simpleName = activity.getClass().getSimpleName();
            if (map.containsKey(simpleName)) {
                MLog.d(TAG, "remove activity:" + simpleName);
                map.remove(simpleName);
            }
        }
    }

    public static void finish(Class<? extends Activity> cls) {
        if (cls == null) {
            return;
        }
        Iterator<Map.Entry<String, WeakReference<Activity>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Activity activity = it.next().getValue().get();
            if (activity != null && activity.getClass() == cls) {
                MLog.d(TAG, "finishing " + activity.getClass().getSimpleName());
                activity.finish();
                remove(activity);
                return;
            }
        }
    }

    @SafeVarargs
    public static void finish(Class<? extends Activity>... clses) {
        if (clses == null || clses.length == 0) {
            return;
        }
        Iterator<Map.Entry<String, WeakReference<Activity>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Activity activity = it.next().getValue().get();
            if (activity != null) {
                Class<?> cls = activity.getClass();
                for (Class<? extends Activity> cls2 : clses) {
                    if (cls == cls2) {
                        MLog.d(TAG, "finishing " + activity.getClass().getSimpleName());
                        activity.finish();
                        remove(activity);
                        break;
                    }
                }
            }
        }
    }

    public static void finishAllExcept(Class<? extends Activity> cls) {
        Iterator<Map.Entry<String, WeakReference<Activity>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Activity activity = it.next().getValue().get();
            if (activity != null && activity.getClass() != cls) {
                MLog.d(TAG, "finishing " + activity.getClass().getSimpleName());
                activity.finish();
                remove(activity);
            }
        }
    }

    public static void finishAll() {
        Iterator<Map.Entry<String, WeakReference<Activity>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Activity activity = it.next().getValue().get();
            if (activity != null) {
                MLog.d(TAG, "finishing " + activity.getClass().getSimpleName());
                activity.finish();
                remove(activity);
            }
        }
    }
}
