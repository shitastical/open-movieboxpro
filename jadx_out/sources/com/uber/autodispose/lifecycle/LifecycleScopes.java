package com.uber.autodispose.lifecycle;

import com.uber.autodispose.AutoDisposePlugins;
import com.uber.autodispose.OutsideScopeException;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;
import java.util.Comparator;

/* JADX INFO: loaded from: classes5.dex */
public final class LifecycleScopes {
    private static final Comparator<Comparable<Object>> COMPARABLE_COMPARATOR = new Comparator() { // from class: com.uber.autodispose.lifecycle.LifecycleScopes$$ExternalSyntheticLambda2
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            return ((Comparable) obj).compareTo((Comparable) obj2);
        }
    };

    private LifecycleScopes() {
        throw new InstantiationError();
    }

    public static <E> CompletableSource resolveScopeFromLifecycle(LifecycleScopeProvider<E> lifecycleScopeProvider) throws OutsideScopeException {
        return resolveScopeFromLifecycle((LifecycleScopeProvider) lifecycleScopeProvider, true);
    }

    public static <E> CompletableSource resolveScopeFromLifecycle(LifecycleScopeProvider<E> lifecycleScopeProvider, boolean z) throws Exception {
        E ePeekLifecycle = lifecycleScopeProvider.peekLifecycle();
        CorrespondingEventsFunction<E> correspondingEventsFunctionCorrespondingEvents = lifecycleScopeProvider.correspondingEvents();
        if (ePeekLifecycle == null) {
            throw new LifecycleNotStartedException();
        }
        try {
            return resolveScopeFromLifecycle(lifecycleScopeProvider.lifecycle(), correspondingEventsFunctionCorrespondingEvents.apply(ePeekLifecycle));
        } catch (Exception e) {
            if (z && (e instanceof LifecycleEndedException)) {
                Consumer<? super OutsideScopeException> outsideScopeHandler = AutoDisposePlugins.getOutsideScopeHandler();
                if (outsideScopeHandler != null) {
                    try {
                        outsideScopeHandler.accept((LifecycleEndedException) e);
                        return Completable.complete();
                    } catch (Exception e2) {
                        return Completable.error(e2);
                    }
                }
                throw e;
            }
            return Completable.error(e);
        }
    }

    public static <E> CompletableSource resolveScopeFromLifecycle(Observable<E> observable, E e) {
        return resolveScopeFromLifecycle(observable, e, e instanceof Comparable ? COMPARABLE_COMPARATOR : null);
    }

    static /* synthetic */ boolean lambda$resolveScopeFromLifecycle$0(Comparator comparator, Object obj, Object obj2) throws Exception {
        return comparator.compare(obj2, obj) >= 0;
    }

    public static <E> CompletableSource resolveScopeFromLifecycle(Observable<E> observable, final E e, final Comparator<E> comparator) {
        Predicate<? super E> predicate;
        if (comparator != null) {
            predicate = new Predicate() { // from class: com.uber.autodispose.lifecycle.LifecycleScopes$$ExternalSyntheticLambda0
                @Override // io.reactivex.functions.Predicate
                public final boolean test(Object obj) {
                    return LifecycleScopes.lambda$resolveScopeFromLifecycle$0(comparator, e, obj);
                }
            };
        } else {
            predicate = new Predicate() { // from class: com.uber.autodispose.lifecycle.LifecycleScopes$$ExternalSyntheticLambda1
                @Override // io.reactivex.functions.Predicate
                public final boolean test(Object obj) {
                    return obj.equals(e);
                }
            };
        }
        return observable.skip(1L).takeUntil(predicate).ignoreElements();
    }
}
