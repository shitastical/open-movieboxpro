package com.uber.autodispose.android.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.uber.autodispose.OutsideScopeException;
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction;
import com.uber.autodispose.lifecycle.LifecycleEndedException;
import com.uber.autodispose.lifecycle.LifecycleScopeProvider;
import com.uber.autodispose.lifecycle.LifecycleScopes;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;

/* JADX INFO: loaded from: classes5.dex */
public final class AndroidLifecycleScopeProvider implements LifecycleScopeProvider<Lifecycle.Event> {
    private static final CorrespondingEventsFunction<Lifecycle.Event> DEFAULT_CORRESPONDING_EVENTS = new CorrespondingEventsFunction() { // from class: com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider$$ExternalSyntheticLambda0
        @Override // com.uber.autodispose.lifecycle.CorrespondingEventsFunction, io.reactivex.functions.Function
        public final Object apply(Object obj) {
            return AndroidLifecycleScopeProvider.lambda$static$0((Lifecycle.Event) obj);
        }
    };
    private final CorrespondingEventsFunction<Lifecycle.Event> boundaryResolver;
    private final LifecycleEventsObservable lifecycleObservable;

    /* JADX INFO: renamed from: com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$Event;

        static {
            int[] iArr = new int[Lifecycle.Event.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$Event = iArr;
            try {
                iArr[Lifecycle.Event.ON_CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_START.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_RESUME.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_PAUSE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_STOP.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$Event[Lifecycle.Event.ON_DESTROY.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    static /* synthetic */ Lifecycle.Event lambda$static$0(Lifecycle.Event event) throws OutsideScopeException {
        int i = AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$Event[event.ordinal()];
        if (i == 1) {
            return Lifecycle.Event.ON_DESTROY;
        }
        if (i == 2) {
            return Lifecycle.Event.ON_STOP;
        }
        if (i == 3) {
            return Lifecycle.Event.ON_PAUSE;
        }
        if (i == 4) {
            return Lifecycle.Event.ON_STOP;
        }
        throw new LifecycleEndedException("Lifecycle has ended! Last event was " + event);
    }

    public static AndroidLifecycleScopeProvider from(LifecycleOwner lifecycleOwner) {
        return from(lifecycleOwner.getLifecycle());
    }

    public static AndroidLifecycleScopeProvider from(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        return from(lifecycleOwner.getLifecycle(), event);
    }

    public static AndroidLifecycleScopeProvider from(Lifecycle lifecycle) {
        return from(lifecycle, DEFAULT_CORRESPONDING_EVENTS);
    }

    public static AndroidLifecycleScopeProvider from(Lifecycle lifecycle, Lifecycle.Event event) {
        return from(lifecycle, new UntilEventFunction(event));
    }

    public static AndroidLifecycleScopeProvider from(LifecycleOwner lifecycleOwner, CorrespondingEventsFunction<Lifecycle.Event> correspondingEventsFunction) {
        return from(lifecycleOwner.getLifecycle(), correspondingEventsFunction);
    }

    public static AndroidLifecycleScopeProvider from(Lifecycle lifecycle, CorrespondingEventsFunction<Lifecycle.Event> correspondingEventsFunction) {
        return new AndroidLifecycleScopeProvider(lifecycle, correspondingEventsFunction);
    }

    private AndroidLifecycleScopeProvider(Lifecycle lifecycle, CorrespondingEventsFunction<Lifecycle.Event> correspondingEventsFunction) {
        this.lifecycleObservable = new LifecycleEventsObservable(lifecycle);
        this.boundaryResolver = correspondingEventsFunction;
    }

    @Override // com.uber.autodispose.lifecycle.LifecycleScopeProvider
    public Observable<Lifecycle.Event> lifecycle() {
        return this.lifecycleObservable;
    }

    @Override // com.uber.autodispose.lifecycle.LifecycleScopeProvider
    public CorrespondingEventsFunction<Lifecycle.Event> correspondingEvents() {
        return this.boundaryResolver;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.uber.autodispose.lifecycle.LifecycleScopeProvider
    public Lifecycle.Event peekLifecycle() {
        this.lifecycleObservable.backfillEvents();
        return this.lifecycleObservable.getValue();
    }

    @Override // com.uber.autodispose.lifecycle.LifecycleScopeProvider, com.uber.autodispose.ScopeProvider
    public CompletableSource requestScope() {
        return LifecycleScopes.resolveScopeFromLifecycle(this);
    }

    private static class UntilEventFunction implements CorrespondingEventsFunction<Lifecycle.Event> {
        private final Lifecycle.Event untilEvent;

        UntilEventFunction(Lifecycle.Event event) {
            this.untilEvent = event;
        }

        @Override // com.uber.autodispose.lifecycle.CorrespondingEventsFunction, io.reactivex.functions.Function
        public Lifecycle.Event apply(Lifecycle.Event event) throws OutsideScopeException {
            return this.untilEvent;
        }
    }
}
