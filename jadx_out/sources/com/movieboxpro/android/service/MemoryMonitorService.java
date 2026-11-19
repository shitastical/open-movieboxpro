package com.movieboxpro.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import com.bumptech.glide.Glide;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.event.LowMemoryEvent;
import com.movieboxpro.android.utils.RxUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes5.dex */
public class MemoryMonitorService extends Service {
    private static final int MEMORY_MONITOR_INTERVAL = 60;

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return new Binder();
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        startMonitorLowMemory();
    }

    private void startMonitorLowMemory() {
        Observable.interval(1L, 60L, TimeUnit.SECONDS).compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<Long>(this) { // from class: com.movieboxpro.android.service.MemoryMonitorService.1
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
                if (Double.compare(Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory(), Runtime.getRuntime().maxMemory() * 0.8d) == 1) {
                    Glide.get(App.getContext()).clearMemory();
                    EventBus.getDefault().post(new LowMemoryEvent());
                }
            }
        });
    }
}
