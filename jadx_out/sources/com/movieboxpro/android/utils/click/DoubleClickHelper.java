package com.movieboxpro.android.utils.click;

import android.view.View;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class DoubleClickHelper {
    private static final int windowDuration = 1;

    public static void click(View view, Runnable r) {
        click(view, r, 1);
    }

    public static void click(View view, final Runnable r, int durationSeconds) {
        if (view == null || r == null) {
            return;
        }
        Observable.create(new ClickObservable(view)).throttleFirst(durationSeconds, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.movieboxpro.android.utils.click.DoubleClickHelper$$ExternalSyntheticLambda0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                r.run();
            }
        });
    }

    public static void click(View view, View.OnClickListener listener) {
        click(view, listener, 1);
    }

    public static void click(final View view, final View.OnClickListener listener, int durationSeconds) {
        if (view == null || listener == null) {
            return;
        }
        Observable.create(new ClickObservable(view)).throttleFirst(durationSeconds, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.movieboxpro.android.utils.click.DoubleClickHelper$$ExternalSyntheticLambda1
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                listener.onClick(view);
            }
        });
    }

    private DoubleClickHelper() {
    }
}
