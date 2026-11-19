package com.uber.autodispose.lifecycle;

import com.uber.autodispose.OutsideScopeException;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

/* JADX INFO: loaded from: classes5.dex */
public final class TestLifecycleScopeProvider implements LifecycleScopeProvider<TestLifecycle> {
    private final BehaviorSubject<TestLifecycle> lifecycleSubject;

    public enum TestLifecycle {
        STARTED,
        STOPPED
    }

    private TestLifecycleScopeProvider(TestLifecycle testLifecycle) {
        if (testLifecycle == null) {
            this.lifecycleSubject = BehaviorSubject.create();
        } else {
            this.lifecycleSubject = BehaviorSubject.createDefault(testLifecycle);
        }
    }

    public static TestLifecycleScopeProvider create() {
        return new TestLifecycleScopeProvider(null);
    }

    public static TestLifecycleScopeProvider createInitial(TestLifecycle testLifecycle) {
        return new TestLifecycleScopeProvider(testLifecycle);
    }

    @Override // com.uber.autodispose.lifecycle.LifecycleScopeProvider
    public Observable<TestLifecycle> lifecycle() {
        return this.lifecycleSubject.hide();
    }

    /* JADX INFO: renamed from: com.uber.autodispose.lifecycle.TestLifecycleScopeProvider$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$uber$autodispose$lifecycle$TestLifecycleScopeProvider$TestLifecycle;

        static {
            int[] iArr = new int[TestLifecycle.values().length];
            $SwitchMap$com$uber$autodispose$lifecycle$TestLifecycleScopeProvider$TestLifecycle = iArr;
            try {
                iArr[TestLifecycle.STARTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$uber$autodispose$lifecycle$TestLifecycleScopeProvider$TestLifecycle[TestLifecycle.STOPPED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.uber.autodispose.lifecycle.LifecycleScopeProvider
    public CorrespondingEventsFunction<TestLifecycle> correspondingEvents() {
        return new CorrespondingEventsFunction() { // from class: com.uber.autodispose.lifecycle.TestLifecycleScopeProvider$$ExternalSyntheticLambda0
            @Override // com.uber.autodispose.lifecycle.CorrespondingEventsFunction, io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return TestLifecycleScopeProvider.lambda$correspondingEvents$0((TestLifecycleScopeProvider.TestLifecycle) obj);
            }
        };
    }

    static /* synthetic */ TestLifecycle lambda$correspondingEvents$0(TestLifecycle testLifecycle) throws OutsideScopeException {
        int i = AnonymousClass1.$SwitchMap$com$uber$autodispose$lifecycle$TestLifecycleScopeProvider$TestLifecycle[testLifecycle.ordinal()];
        if (i == 1) {
            return TestLifecycle.STOPPED;
        }
        if (i == 2) {
            throw new LifecycleEndedException();
        }
        throw new IllegalStateException("Unknown lifecycle event.");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.uber.autodispose.lifecycle.LifecycleScopeProvider
    public TestLifecycle peekLifecycle() {
        return this.lifecycleSubject.getValue();
    }

    @Override // com.uber.autodispose.lifecycle.LifecycleScopeProvider, com.uber.autodispose.ScopeProvider
    public CompletableSource requestScope() {
        return LifecycleScopes.resolveScopeFromLifecycle(this);
    }

    public void start() {
        this.lifecycleSubject.onNext(TestLifecycle.STARTED);
    }

    public void stop() {
        if (this.lifecycleSubject.getValue() != TestLifecycle.STARTED) {
            throw new IllegalStateException("Attempting to stop lifecycle before starting it.");
        }
        this.lifecycleSubject.onNext(TestLifecycle.STOPPED);
    }
}
