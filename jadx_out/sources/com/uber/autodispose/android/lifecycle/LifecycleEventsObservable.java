package com.uber.autodispose.android.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.uber.autodispose.android.internal.AutoDisposeAndroidUtil;
import com.uber.autodispose.android.internal.MainThreadDisposable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.subjects.BehaviorSubject;

/* JADX INFO: loaded from: classes5.dex */
class LifecycleEventsObservable extends Observable<Lifecycle.Event> {
    private final BehaviorSubject<Lifecycle.Event> eventsObservable = BehaviorSubject.create();
    private final Lifecycle lifecycle;

    LifecycleEventsObservable(Lifecycle lifecycle) {
        this.lifecycle = lifecycle;
    }

    Lifecycle.Event getValue() {
        return this.eventsObservable.getValue();
    }

    /* JADX INFO: renamed from: com.uber.autodispose.android.lifecycle.LifecycleEventsObservable$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$State = iArr;
            try {
                iArr[Lifecycle.State.INITIALIZED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.STARTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.RESUMED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.DESTROYED.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    void backfillEvents() {
        Lifecycle.Event event;
        int i = AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State[this.lifecycle.getState().ordinal()];
        if (i == 1) {
            event = Lifecycle.Event.ON_CREATE;
        } else if (i == 2) {
            event = Lifecycle.Event.ON_START;
        } else if (i == 3 || i == 4) {
            event = Lifecycle.Event.ON_RESUME;
        } else {
            event = Lifecycle.Event.ON_DESTROY;
        }
        this.eventsObservable.onNext(event);
    }

    @Override // io.reactivex.Observable
    protected void subscribeActual(Observer<? super Lifecycle.Event> observer) {
        ArchLifecycleObserver archLifecycleObserver = new ArchLifecycleObserver(this.lifecycle, observer, this.eventsObservable);
        observer.onSubscribe(archLifecycleObserver);
        if (!AutoDisposeAndroidUtil.isMainThread()) {
            observer.onError(new IllegalStateException("Lifecycles can only be bound to on the main thread!"));
            return;
        }
        this.lifecycle.addObserver(archLifecycleObserver);
        if (archLifecycleObserver.isDisposed()) {
            this.lifecycle.removeObserver(archLifecycleObserver);
        }
    }

    static final class ArchLifecycleObserver extends MainThreadDisposable implements LifecycleObserver {
        private final BehaviorSubject<Lifecycle.Event> eventsObservable;
        private final Lifecycle lifecycle;
        private final Observer<? super Lifecycle.Event> observer;

        ArchLifecycleObserver(Lifecycle lifecycle, Observer<? super Lifecycle.Event> observer, BehaviorSubject<Lifecycle.Event> behaviorSubject) {
            this.lifecycle = lifecycle;
            this.observer = observer;
            this.eventsObservable = behaviorSubject;
        }

        @Override // com.uber.autodispose.android.internal.MainThreadDisposable
        protected void onDispose() {
            this.lifecycle.removeObserver(this);
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
        void onStateChange(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
            if (isDisposed()) {
                return;
            }
            if (event != Lifecycle.Event.ON_CREATE || this.eventsObservable.getValue() != event) {
                this.eventsObservable.onNext(event);
            }
            this.observer.onNext(event);
        }
    }
}
