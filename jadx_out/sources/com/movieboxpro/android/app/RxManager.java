package com.movieboxpro.android.app;

import com.movieboxpro.android.utils.MLog;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class RxManager {
    private static final String TAG = "RxManager";
    private static Map<String, WeakReference<CompositeDisposable>> map = new ConcurrentHashMap();

    public static void addDisposable(String key, Disposable disposable) {
        CompositeDisposable compositeDisposable;
        MLog.d(TAG, "addDisposable: " + key);
        if (map.containsKey(key)) {
            WeakReference<CompositeDisposable> weakReference = map.get(key);
            if (weakReference == null || (compositeDisposable = weakReference.get()) == null) {
                return;
            }
            compositeDisposable.add(disposable);
            return;
        }
        CompositeDisposable compositeDisposable2 = new CompositeDisposable();
        compositeDisposable2.add(disposable);
        map.put(key, new WeakReference<>(compositeDisposable2));
    }

    public static void remove(String key) {
        CompositeDisposable compositeDisposable;
        MLog.d(TAG, "remove: " + key);
        if (map.containsKey(key)) {
            WeakReference<CompositeDisposable> weakReference = map.get(key);
            if (weakReference != null && (compositeDisposable = weakReference.get()) != null) {
                compositeDisposable.clear();
            }
            map.remove(key);
        }
    }

    static void removeAll() {
        MLog.d(TAG, "removeAll");
        for (String str : map.keySet()) {
            MLog.d(TAG, "removeAll: key: " + str);
            remove(str);
        }
        map.clear();
    }
}
