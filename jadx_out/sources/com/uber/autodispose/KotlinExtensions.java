package com.uber.autodispose;

import androidx.exifinterface.media.ExifInterface;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.parallel.ParallelFlowable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: KotlinExtensions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0002H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\b2\u0006\u0010\u0005\u001a\u00020\u0002H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\t\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\t\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\n2\u0006\u0010\u0005\u001a\u00020\u0002H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000b\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\f2\u0006\u0010\u0005\u001a\u00020\u0002H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\r\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u000e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\r\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u000e2\u0006\u0010\u0005\u001a\u00020\u0002H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000f\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00102\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00070\u000f\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00070\u00102\u0006\u0010\u0005\u001a\u00020\u0002H\u0087\b¨\u0006\u0011"}, d2 = {"autoDisposable", "Lcom/uber/autodispose/CompletableSubscribeProxy;", "Lio/reactivex/Completable;", "provider", "Lcom/uber/autodispose/ScopeProvider;", "scope", "Lcom/uber/autodispose/FlowableSubscribeProxy;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/reactivex/Flowable;", "Lcom/uber/autodispose/MaybeSubscribeProxy;", "Lio/reactivex/Maybe;", "Lcom/uber/autodispose/ObservableSubscribeProxy;", "Lio/reactivex/Observable;", "Lcom/uber/autodispose/SingleSubscribeProxy;", "Lio/reactivex/Single;", "Lcom/uber/autodispose/ParallelFlowableSubscribeProxy;", "Lio/reactivex/parallel/ParallelFlowable;", "autodispose"}, k = 2, mv = {1, 1, 15})
public final class KotlinExtensions {
    @CheckReturnValue
    public static final <T> FlowableSubscribeProxy<T> autoDisposable(Flowable<T> autoDisposable, Completable scope) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(scope));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(scope))");
        return (FlowableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> ObservableSubscribeProxy<T> autoDisposable(Observable<T> autoDisposable, Completable scope) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(scope));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(scope))");
        return (ObservableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> SingleSubscribeProxy<T> autoDisposable(Single<T> autoDisposable, Completable scope) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(scope));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(scope))");
        return (SingleSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> MaybeSubscribeProxy<T> autoDisposable(Maybe<T> autoDisposable, Completable scope) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(scope));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(scope))");
        return (MaybeSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final CompletableSubscribeProxy autoDisposable(Completable autoDisposable, Completable scope) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(scope));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable<Any>(scope))");
        return (CompletableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> ParallelFlowableSubscribeProxy<T> autoDisposable(ParallelFlowable<T> autoDisposable, Completable scope) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(scope, "scope");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(scope));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(scope))");
        return (ParallelFlowableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> FlowableSubscribeProxy<T> autoDisposable(Flowable<T> autoDisposable, ScopeProvider provider) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(provider));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(provider))");
        return (FlowableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> ObservableSubscribeProxy<T> autoDisposable(Observable<T> autoDisposable, ScopeProvider provider) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(provider));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(provider))");
        return (ObservableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> SingleSubscribeProxy<T> autoDisposable(Single<T> autoDisposable, ScopeProvider provider) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(provider));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(provider))");
        return (SingleSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> MaybeSubscribeProxy<T> autoDisposable(Maybe<T> autoDisposable, ScopeProvider provider) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(provider));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(provider))");
        return (MaybeSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final CompletableSubscribeProxy autoDisposable(Completable autoDisposable, ScopeProvider provider) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(provider));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…isposable<Any>(provider))");
        return (CompletableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> ParallelFlowableSubscribeProxy<T> autoDisposable(ParallelFlowable<T> autoDisposable, ScopeProvider provider) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(provider, "provider");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(provider));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.autoDisposable(provider))");
        return (ParallelFlowableSubscribeProxy) objAs;
    }
}
