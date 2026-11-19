package com.movieboxpro.android.http;

import com.movieboxpro.android.utils.MLog;
import j$.util.concurrent.ConcurrentHashMap;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import retrofit2.Call;

/* JADX INFO: loaded from: classes5.dex */
public class CallManager {
    private static final String TAG = "CallManager";
    private static List<WeakReference<Call>> list = new ArrayList();
    private static Map<String, List<WeakReference<Call>>> map = new ConcurrentHashMap();

    public static void add(Call call) {
        add(list, call);
    }

    public static void add(String name, Call call) {
        MLog.d(TAG, "add: " + name);
        List<WeakReference<Call>> list2 = map.get(name);
        if (list2 == null) {
            ArrayList arrayList = new ArrayList();
            add(arrayList, call);
            map.put(name, arrayList);
            return;
        }
        add(list2, call);
    }

    private static void add(List<WeakReference<Call>> list2, Call call) {
        Call call2;
        synchronized (TAG) {
            if (call != null) {
                for (WeakReference<Call> weakReference : list2) {
                    if (weakReference == null || (call2 = weakReference.get()) == null || call2 != call) {
                    }
                }
                list2.add(new WeakReference<>(call));
            }
        }
    }

    public static void remove(Call call) {
        remove(list, call);
    }

    public static void remove(String name, Call call) {
        MLog.d(TAG, "remove: " + name);
        List<WeakReference<Call>> list2 = map.get(name);
        if (list2 == null || list2.isEmpty()) {
            return;
        }
        remove(list2, call);
    }

    private static void remove(List<WeakReference<Call>> list2, Call call) {
        Call call2;
        synchronized (TAG) {
            if (call != null) {
                Iterator<WeakReference<Call>> it = list2.iterator();
                while (it.hasNext()) {
                    WeakReference<Call> next = it.next();
                    if (next != null && (call2 = next.get()) != null && call2 == call && !call.isCanceled()) {
                        call.cancel();
                        it.remove();
                        break;
                    }
                }
            }
        }
    }

    public static void cancelAll() {
        cancelAll(list);
    }

    public static void cancelAll(String name) {
        MLog.d(TAG, "cancelAll: " + name);
        List<WeakReference<Call>> list2 = map.get(name);
        if (list2 != null) {
            cancelAll(list2);
        }
    }

    private static void cancelAll(List<WeakReference<Call>> list2) {
        synchronized (TAG) {
            if (!list2.isEmpty()) {
                Iterator<WeakReference<Call>> it = list2.iterator();
                while (it.hasNext()) {
                    WeakReference<Call> next = it.next();
                    if (next != null) {
                        Call call = next.get();
                        if (call != null && !call.isCanceled()) {
                            call.cancel();
                        }
                        it.remove();
                    }
                }
            }
        }
    }
}
