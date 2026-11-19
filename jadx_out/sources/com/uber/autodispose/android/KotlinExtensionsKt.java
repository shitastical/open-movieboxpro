package com.uber.autodispose.android;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.CompletableSubscribeProxy;
import com.uber.autodispose.FlowableSubscribeProxy;
import com.uber.autodispose.MaybeSubscribeProxy;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.uber.autodispose.ParallelFlowableSubscribeProxy;
import com.uber.autodispose.ScopeProvider;
import com.uber.autodispose.SingleSubscribeProxy;
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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00060\b\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\t2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00060\n\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u000b2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00060\f\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a'\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00060\u000e\"\u0004\b\u0000\u0010\u0006*\b\u0012\u0004\u0012\u0002H\u00060\u000f2\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\b\u001a\r\u0010\u0010\u001a\u00020\u0011*\u00020\u0004H\u0087\b¨\u0006\u0012"}, d2 = {"autoDisposable", "Lcom/uber/autodispose/CompletableSubscribeProxy;", "Lio/reactivex/Completable;", "view", "Landroid/view/View;", "Lcom/uber/autodispose/FlowableSubscribeProxy;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/reactivex/Flowable;", "Lcom/uber/autodispose/MaybeSubscribeProxy;", "Lio/reactivex/Maybe;", "Lcom/uber/autodispose/ObservableSubscribeProxy;", "Lio/reactivex/Observable;", "Lcom/uber/autodispose/SingleSubscribeProxy;", "Lio/reactivex/Single;", "Lcom/uber/autodispose/ParallelFlowableSubscribeProxy;", "Lio/reactivex/parallel/ParallelFlowable;", "scope", "Lcom/uber/autodispose/ScopeProvider;", "autodispose-android_release"}, k = 2, mv = {1, 1, 15})
public final class KotlinExtensionsKt {
    @CheckReturnValue
    public static final ScopeProvider scope(View scope) {
        Intrinsics.checkParameterIsNotNull(scope, "$this$scope");
        ScopeProvider scopeProviderFrom = ViewScopeProvider.from(scope);
        Intrinsics.checkExpressionValueIsNotNull(scopeProviderFrom, "ViewScopeProvider.from(this)");
        return scopeProviderFrom;
    }

    @CheckReturnValue
    public static final <T> FlowableSubscribeProxy<T> autoDisposable(Flowable<T> autoDisposable, View view) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(ViewScopeProvider.from(view)));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…copeProvider.from(view)))");
        return (FlowableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> ObservableSubscribeProxy<T> autoDisposable(Observable<T> autoDisposable, View view) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(ViewScopeProvider.from(view)));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…copeProvider.from(view)))");
        return (ObservableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> SingleSubscribeProxy<T> autoDisposable(Single<T> autoDisposable, View view) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(ViewScopeProvider.from(view)));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…copeProvider.from(view)))");
        return (SingleSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> MaybeSubscribeProxy<T> autoDisposable(Maybe<T> autoDisposable, View view) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(ViewScopeProvider.from(view)));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…copeProvider.from(view)))");
        return (MaybeSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final CompletableSubscribeProxy autoDisposable(Completable autoDisposable, View view) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(ViewScopeProvider.from(view)));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…copeProvider.from(view)))");
        return (CompletableSubscribeProxy) objAs;
    }

    @CheckReturnValue
    public static final <T> ParallelFlowableSubscribeProxy<T> autoDisposable(ParallelFlowable<T> autoDisposable, View view) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(view, "view");
        Object objAs = autoDisposable.as(AutoDispose.autoDisposable(ViewScopeProvider.from(view)));
        Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…copeProvider.from(view)))");
        return (ParallelFlowableSubscribeProxy) objAs;
    }
}
