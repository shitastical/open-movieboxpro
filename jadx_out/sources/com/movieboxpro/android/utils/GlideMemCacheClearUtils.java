package com.movieboxpro.android.utils;

import android.util.Log;
import com.movieboxpro.android.app.App;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class GlideMemCacheClearUtils {
    private static final int MEMORY_MONITOR_INTERVAL = 60;

    public static void startMonitorLowMemory() {
        Observable.interval(60L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Long>() { // from class: com.movieboxpro.android.utils.GlideMemCacheClearUtils.1
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable e) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable d) {
            }

            @Override // io.reactivex.Observer
            public void onNext(Long aLong) {
                try {
                    if (Double.compare(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), Runtime.getRuntime().maxMemory() * 0.8d) == 1) {
                        GlideUtils.clearImageMemoryCache(App.getContext());
                        Log.d("GlideMemCacheClear", "GlideMemCacheClear");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
