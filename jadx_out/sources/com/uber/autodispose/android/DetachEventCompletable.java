package com.uber.autodispose.android;

import android.view.View;
import com.uber.autodispose.OutsideScopeException;
import com.uber.autodispose.android.internal.AutoDisposeAndroidUtil;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.android.MainThreadDisposable;

/* JADX INFO: loaded from: classes5.dex */
final class DetachEventCompletable implements CompletableSource {
    private final View view;

    DetachEventCompletable(View view) {
        this.view = view;
    }

    @Override // io.reactivex.CompletableSource
    public void subscribe(CompletableObserver completableObserver) {
        Listener listener = new Listener(this.view, completableObserver);
        completableObserver.onSubscribe(listener);
        if (!AutoDisposeAndroidUtil.isMainThread()) {
            completableObserver.onError(new IllegalStateException("Views can only be bound to on the main thread!"));
            return;
        }
        if (!this.view.isAttachedToWindow() && this.view.getWindowToken() == null) {
            completableObserver.onError(new OutsideScopeException("View is not attached!"));
            return;
        }
        this.view.addOnAttachStateChangeListener(listener);
        if (listener.isDisposed()) {
            this.view.removeOnAttachStateChangeListener(listener);
        }
    }

    static final class Listener extends MainThreadDisposable implements View.OnAttachStateChangeListener {
        private final CompletableObserver observer;
        private final View view;

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View view) {
        }

        Listener(View view, CompletableObserver completableObserver) {
            this.view = view;
            this.observer = completableObserver;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View view) {
            if (isDisposed()) {
                return;
            }
            this.observer.onComplete();
        }

        @Override // io.reactivex.android.MainThreadDisposable
        protected void onDispose() {
            this.view.removeOnAttachStateChangeListener(this);
        }
    }
}
