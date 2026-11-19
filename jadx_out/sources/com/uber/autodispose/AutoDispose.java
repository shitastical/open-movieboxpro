package com.uber.autodispose;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.TestObserver;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.subscribers.TestSubscriber;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/* JADX INFO: loaded from: classes5.dex */
public final class AutoDispose {
    public static <T> AutoDisposeConverter<T> autoDisposable(final ScopeProvider scopeProvider) {
        AutoDisposeUtil.checkNotNull(scopeProvider, "provider == null");
        return autoDisposable(Completable.defer(new Callable() { // from class: com.uber.autodispose.AutoDispose$$ExternalSyntheticLambda0
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return AutoDispose.lambda$autoDisposable$0(scopeProvider);
            }
        }));
    }

    static /* synthetic */ CompletableSource lambda$autoDisposable$0(ScopeProvider scopeProvider) throws Exception {
        try {
            return scopeProvider.requestScope();
        } catch (OutsideScopeException e) {
            Consumer<? super OutsideScopeException> outsideScopeHandler = AutoDisposePlugins.getOutsideScopeHandler();
            if (outsideScopeHandler != null) {
                outsideScopeHandler.accept(e);
                return Completable.complete();
            }
            return Completable.error(e);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: com.uber.autodispose.AutoDispose$1, reason: invalid class name */
    class AnonymousClass1<T> implements AutoDisposeConverter<T> {
        final /* synthetic */ CompletableSource val$scope;

        AnonymousClass1(CompletableSource completableSource) {
            this.val$scope = completableSource;
        }

        @Override // io.reactivex.parallel.ParallelFlowableConverter
        public ParallelFlowableSubscribeProxy<T> apply(final ParallelFlowable<T> parallelFlowable) {
            final CompletableSource completableSource = this.val$scope;
            return new ParallelFlowableSubscribeProxy() { // from class: com.uber.autodispose.AutoDispose$1$$ExternalSyntheticLambda0
                @Override // com.uber.autodispose.ParallelFlowableSubscribeProxy
                public final void subscribe(Subscriber[] subscriberArr) {
                    new AutoDisposeParallelFlowable(parallelFlowable, completableSource).subscribe(subscriberArr);
                }
            };
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.reactivex.CompletableConverter
        public CompletableSubscribeProxy apply(final Completable completable) {
            return new CompletableSubscribeProxy() { // from class: com.uber.autodispose.AutoDispose.1.1
                @Override // com.uber.autodispose.CompletableSubscribeProxy
                public Disposable subscribe() {
                    return new AutoDisposeCompletable(completable, AnonymousClass1.this.val$scope).subscribe();
                }

                @Override // com.uber.autodispose.CompletableSubscribeProxy
                public Disposable subscribe(Action action) {
                    return new AutoDisposeCompletable(completable, AnonymousClass1.this.val$scope).subscribe(action);
                }

                @Override // com.uber.autodispose.CompletableSubscribeProxy
                public Disposable subscribe(Action action, Consumer<? super Throwable> consumer) {
                    return new AutoDisposeCompletable(completable, AnonymousClass1.this.val$scope).subscribe(action, consumer);
                }

                @Override // com.uber.autodispose.CompletableSubscribeProxy
                public void subscribe(CompletableObserver completableObserver) {
                    new AutoDisposeCompletable(completable, AnonymousClass1.this.val$scope).subscribe(completableObserver);
                }

                @Override // com.uber.autodispose.CompletableSubscribeProxy
                public <E extends CompletableObserver> E subscribeWith(E e) {
                    return (E) new AutoDisposeCompletable(completable, AnonymousClass1.this.val$scope).subscribeWith(e);
                }

                @Override // com.uber.autodispose.CompletableSubscribeProxy
                public TestObserver<Void> test() {
                    TestObserver<Void> testObserver = new TestObserver<>();
                    subscribe(testObserver);
                    return testObserver;
                }

                @Override // com.uber.autodispose.CompletableSubscribeProxy
                public TestObserver<Void> test(boolean z) {
                    TestObserver<Void> testObserver = new TestObserver<>();
                    if (z) {
                        testObserver.cancel();
                    }
                    subscribe(testObserver);
                    return testObserver;
                }
            };
        }

        @Override // io.reactivex.FlowableConverter
        public FlowableSubscribeProxy<T> apply(final Flowable<T> flowable) {
            return new FlowableSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.1.2
                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public Disposable subscribe() {
                    return new AutoDisposeFlowable(flowable, AnonymousClass1.this.val$scope).subscribe();
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer) {
                    return new AutoDisposeFlowable(flowable, AnonymousClass1.this.val$scope).subscribe(consumer);
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
                    return new AutoDisposeFlowable(flowable, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2);
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
                    return new AutoDisposeFlowable(flowable, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2, action);
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Consumer<? super Subscription> consumer3) {
                    return new AutoDisposeFlowable(flowable, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2, action, consumer3);
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public void subscribe(Subscriber<? super T> subscriber) {
                    new AutoDisposeFlowable(flowable, AnonymousClass1.this.val$scope).subscribe(subscriber);
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public <E extends Subscriber<? super T>> E subscribeWith(E e) {
                    return (E) new AutoDisposeFlowable(flowable, AnonymousClass1.this.val$scope).subscribeWith(e);
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public TestSubscriber<T> test() {
                    TestSubscriber<T> testSubscriber = new TestSubscriber<>();
                    subscribe(testSubscriber);
                    return testSubscriber;
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public TestSubscriber<T> test(long j) {
                    TestSubscriber<T> testSubscriber = new TestSubscriber<>(j);
                    subscribe(testSubscriber);
                    return testSubscriber;
                }

                @Override // com.uber.autodispose.FlowableSubscribeProxy
                public TestSubscriber<T> test(long j, boolean z) {
                    TestSubscriber<T> testSubscriber = new TestSubscriber<>(j);
                    if (z) {
                        testSubscriber.cancel();
                    }
                    subscribe(testSubscriber);
                    return testSubscriber;
                }
            };
        }

        @Override // io.reactivex.MaybeConverter
        public MaybeSubscribeProxy<T> apply(final Maybe<T> maybe) {
            return new MaybeSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.1.3
                @Override // com.uber.autodispose.MaybeSubscribeProxy
                public Disposable subscribe() {
                    return new AutoDisposeMaybe(maybe, AnonymousClass1.this.val$scope).subscribe();
                }

                @Override // com.uber.autodispose.MaybeSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer) {
                    return new AutoDisposeMaybe(maybe, AnonymousClass1.this.val$scope).subscribe(consumer);
                }

                @Override // com.uber.autodispose.MaybeSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
                    return new AutoDisposeMaybe(maybe, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2);
                }

                @Override // com.uber.autodispose.MaybeSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
                    return new AutoDisposeMaybe(maybe, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2, action);
                }

                @Override // com.uber.autodispose.MaybeSubscribeProxy
                public void subscribe(MaybeObserver<? super T> maybeObserver) {
                    new AutoDisposeMaybe(maybe, AnonymousClass1.this.val$scope).subscribe(maybeObserver);
                }

                @Override // com.uber.autodispose.MaybeSubscribeProxy
                public <E extends MaybeObserver<? super T>> E subscribeWith(E e) {
                    return (E) new AutoDisposeMaybe(maybe, AnonymousClass1.this.val$scope).subscribeWith(e);
                }

                @Override // com.uber.autodispose.MaybeSubscribeProxy
                public TestObserver<T> test() {
                    TestObserver<T> testObserver = new TestObserver<>();
                    subscribe(testObserver);
                    return testObserver;
                }

                @Override // com.uber.autodispose.MaybeSubscribeProxy
                public TestObserver<T> test(boolean z) {
                    TestObserver<T> testObserver = new TestObserver<>();
                    if (z) {
                        testObserver.cancel();
                    }
                    subscribe(testObserver);
                    return testObserver;
                }
            };
        }

        @Override // io.reactivex.ObservableConverter
        public ObservableSubscribeProxy<T> apply(final Observable<T> observable) {
            return new ObservableSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.1.4
                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public Disposable subscribe() {
                    return new AutoDisposeObservable(observable, AnonymousClass1.this.val$scope).subscribe();
                }

                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer) {
                    return new AutoDisposeObservable(observable, AnonymousClass1.this.val$scope).subscribe(consumer);
                }

                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
                    return new AutoDisposeObservable(observable, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2);
                }

                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action) {
                    return new AutoDisposeObservable(observable, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2, action);
                }

                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2, Action action, Consumer<? super Disposable> consumer3) {
                    return new AutoDisposeObservable(observable, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2, action, consumer3);
                }

                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public void subscribe(Observer<? super T> observer) {
                    new AutoDisposeObservable(observable, AnonymousClass1.this.val$scope).subscribe(observer);
                }

                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public <E extends Observer<? super T>> E subscribeWith(E e) {
                    return (E) new AutoDisposeObservable(observable, AnonymousClass1.this.val$scope).subscribeWith(e);
                }

                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public TestObserver<T> test() {
                    TestObserver<T> testObserver = new TestObserver<>();
                    subscribe(testObserver);
                    return testObserver;
                }

                @Override // com.uber.autodispose.ObservableSubscribeProxy
                public TestObserver<T> test(boolean z) {
                    TestObserver<T> testObserver = new TestObserver<>();
                    if (z) {
                        testObserver.dispose();
                    }
                    subscribe(testObserver);
                    return testObserver;
                }
            };
        }

        @Override // io.reactivex.SingleConverter
        public SingleSubscribeProxy<T> apply(final Single<T> single) {
            return new SingleSubscribeProxy<T>() { // from class: com.uber.autodispose.AutoDispose.1.5
                @Override // com.uber.autodispose.SingleSubscribeProxy
                public Disposable subscribe() {
                    return new AutoDisposeSingle(single, AnonymousClass1.this.val$scope).subscribe();
                }

                @Override // com.uber.autodispose.SingleSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer) {
                    return new AutoDisposeSingle(single, AnonymousClass1.this.val$scope).subscribe(consumer);
                }

                @Override // com.uber.autodispose.SingleSubscribeProxy
                public Disposable subscribe(BiConsumer<? super T, ? super Throwable> biConsumer) {
                    return new AutoDisposeSingle(single, AnonymousClass1.this.val$scope).subscribe(biConsumer);
                }

                @Override // com.uber.autodispose.SingleSubscribeProxy
                public Disposable subscribe(Consumer<? super T> consumer, Consumer<? super Throwable> consumer2) {
                    return new AutoDisposeSingle(single, AnonymousClass1.this.val$scope).subscribe(consumer, consumer2);
                }

                @Override // com.uber.autodispose.SingleSubscribeProxy
                public void subscribe(SingleObserver<? super T> singleObserver) {
                    new AutoDisposeSingle(single, AnonymousClass1.this.val$scope).subscribe(singleObserver);
                }

                @Override // com.uber.autodispose.SingleSubscribeProxy
                public <E extends SingleObserver<? super T>> E subscribeWith(E e) {
                    return (E) new AutoDisposeSingle(single, AnonymousClass1.this.val$scope).subscribeWith(e);
                }

                @Override // com.uber.autodispose.SingleSubscribeProxy
                public TestObserver<T> test() {
                    TestObserver<T> testObserver = new TestObserver<>();
                    subscribe(testObserver);
                    return testObserver;
                }

                @Override // com.uber.autodispose.SingleSubscribeProxy
                public TestObserver<T> test(boolean z) {
                    TestObserver<T> testObserver = new TestObserver<>();
                    if (z) {
                        testObserver.dispose();
                    }
                    subscribe(testObserver);
                    return testObserver;
                }
            };
        }
    }

    public static <T> AutoDisposeConverter<T> autoDisposable(CompletableSource completableSource) {
        AutoDisposeUtil.checkNotNull(completableSource, "scope == null");
        return new AnonymousClass1(completableSource);
    }

    private AutoDispose() {
        throw new AssertionError("No instances");
    }
}
