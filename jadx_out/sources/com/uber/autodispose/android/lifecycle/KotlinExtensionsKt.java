package com.uber.autodispose.android.lifecycle;

import androidx.exifinterface.media.ExifInterface;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.CompletableSubscribeProxy;
import com.uber.autodispose.FlowableSubscribeProxy;
import com.uber.autodispose.MaybeSubscribeProxy;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.uber.autodispose.ParallelFlowableSubscribeProxy;
import com.uber.autodispose.ScopeProvider;
import com.uber.autodispose.SingleSubscribeProxy;
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction;
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
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\b0\u0007\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\t2\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\b0\n\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000b2\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\b0\f\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\r2\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\b0\u000e\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u000f2\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a3\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\b0\u0010\"\u0004\b\u0000\u0010\b*\b\u0012\u0004\u0012\u0002H\b0\u00112\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0014H\u0087\b\u001a\u0015\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u001b\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016H\u0087\b\u001a\r\u0010\u0012\u001a\u00020\u0013*\u00020\u0004H\u0087\b\u001a\u0015\u0010\u0012\u001a\u00020\u0013*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0087\b\u001a\u001b\u0010\u0012\u001a\u00020\u0013*\u00020\u00042\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00060\u0016H\u0087\b¨\u0006\u0017"}, d2 = {"autoDisposable", "Lcom/uber/autodispose/CompletableSubscribeProxy;", "Lio/reactivex/Completable;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "untilEvent", "Landroidx/lifecycle/Lifecycle$Event;", "Lcom/uber/autodispose/FlowableSubscribeProxy;", ExifInterface.GPS_DIRECTION_TRUE, "Lio/reactivex/Flowable;", "Lcom/uber/autodispose/MaybeSubscribeProxy;", "Lio/reactivex/Maybe;", "Lcom/uber/autodispose/ObservableSubscribeProxy;", "Lio/reactivex/Observable;", "Lcom/uber/autodispose/SingleSubscribeProxy;", "Lio/reactivex/Single;", "Lcom/uber/autodispose/ParallelFlowableSubscribeProxy;", "Lio/reactivex/parallel/ParallelFlowable;", "scope", "Lcom/uber/autodispose/ScopeProvider;", "Landroidx/lifecycle/Lifecycle;", "boundaryResolver", "Lcom/uber/autodispose/lifecycle/CorrespondingEventsFunction;", "autodispose-android-archcomponents_release"}, k = 2, mv = {1, 1, 15})
public final class KotlinExtensionsKt {
    @CheckReturnValue
    public static final ScopeProvider scope(LifecycleOwner scope) {
        Intrinsics.checkParameterIsNotNull(scope, "$this$scope");
        AndroidLifecycleScopeProvider androidLifecycleScopeProviderFrom = AndroidLifecycleScopeProvider.from(scope);
        Intrinsics.checkExpressionValueIsNotNull(androidLifecycleScopeProviderFrom, "AndroidLifecycleScopeProvider.from(this)");
        return androidLifecycleScopeProviderFrom;
    }

    @CheckReturnValue
    public static final ScopeProvider scope(LifecycleOwner scope, Lifecycle.Event untilEvent) {
        Intrinsics.checkParameterIsNotNull(scope, "$this$scope");
        Intrinsics.checkParameterIsNotNull(untilEvent, "untilEvent");
        AndroidLifecycleScopeProvider androidLifecycleScopeProviderFrom = AndroidLifecycleScopeProvider.from(scope, untilEvent);
        Intrinsics.checkExpressionValueIsNotNull(androidLifecycleScopeProviderFrom, "AndroidLifecycleScopePro…er.from(this, untilEvent)");
        return androidLifecycleScopeProviderFrom;
    }

    @CheckReturnValue
    public static final ScopeProvider scope(LifecycleOwner scope, CorrespondingEventsFunction<Lifecycle.Event> boundaryResolver) {
        Intrinsics.checkParameterIsNotNull(scope, "$this$scope");
        Intrinsics.checkParameterIsNotNull(boundaryResolver, "boundaryResolver");
        AndroidLifecycleScopeProvider androidLifecycleScopeProviderFrom = AndroidLifecycleScopeProvider.from(scope, boundaryResolver);
        Intrinsics.checkExpressionValueIsNotNull(androidLifecycleScopeProviderFrom, "AndroidLifecycleScopePro…m(this, boundaryResolver)");
        return androidLifecycleScopeProviderFrom;
    }

    @CheckReturnValue
    public static final ScopeProvider scope(Lifecycle scope) {
        Intrinsics.checkParameterIsNotNull(scope, "$this$scope");
        AndroidLifecycleScopeProvider androidLifecycleScopeProviderFrom = AndroidLifecycleScopeProvider.from(scope);
        Intrinsics.checkExpressionValueIsNotNull(androidLifecycleScopeProviderFrom, "AndroidLifecycleScopeProvider.from(this)");
        return androidLifecycleScopeProviderFrom;
    }

    @CheckReturnValue
    public static final ScopeProvider scope(Lifecycle scope, Lifecycle.Event untilEvent) {
        Intrinsics.checkParameterIsNotNull(scope, "$this$scope");
        Intrinsics.checkParameterIsNotNull(untilEvent, "untilEvent");
        AndroidLifecycleScopeProvider androidLifecycleScopeProviderFrom = AndroidLifecycleScopeProvider.from(scope, untilEvent);
        Intrinsics.checkExpressionValueIsNotNull(androidLifecycleScopeProviderFrom, "AndroidLifecycleScopePro…om(\n    this, untilEvent)");
        return androidLifecycleScopeProviderFrom;
    }

    @CheckReturnValue
    public static final ScopeProvider scope(Lifecycle scope, CorrespondingEventsFunction<Lifecycle.Event> boundaryResolver) {
        Intrinsics.checkParameterIsNotNull(scope, "$this$scope");
        Intrinsics.checkParameterIsNotNull(boundaryResolver, "boundaryResolver");
        AndroidLifecycleScopeProvider androidLifecycleScopeProviderFrom = AndroidLifecycleScopeProvider.from(scope, boundaryResolver);
        Intrinsics.checkExpressionValueIsNotNull(androidLifecycleScopeProviderFrom, "AndroidLifecycleScopePro…  this, boundaryResolver)");
        return androidLifecycleScopeProviderFrom;
    }

    @CheckReturnValue
    public static /* synthetic */ FlowableSubscribeProxy autoDisposable$default(Flowable autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event, int i, Object obj) {
        if ((i & 2) != 0) {
            event = null;
        }
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (FlowableSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (FlowableSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static final <T> FlowableSubscribeProxy<T> autoDisposable(Flowable<T> autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (FlowableSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (FlowableSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static /* synthetic */ ObservableSubscribeProxy autoDisposable$default(Observable autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event, int i, Object obj) {
        if ((i & 2) != 0) {
            event = null;
        }
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (ObservableSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (ObservableSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static final <T> ObservableSubscribeProxy<T> autoDisposable(Observable<T> autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (ObservableSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (ObservableSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static /* synthetic */ SingleSubscribeProxy autoDisposable$default(Single autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event, int i, Object obj) {
        if ((i & 2) != 0) {
            event = null;
        }
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (SingleSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (SingleSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static final <T> SingleSubscribeProxy<T> autoDisposable(Single<T> autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (SingleSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (SingleSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static /* synthetic */ MaybeSubscribeProxy autoDisposable$default(Maybe autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event, int i, Object obj) {
        if ((i & 2) != 0) {
            event = null;
        }
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (MaybeSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (MaybeSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static final <T> MaybeSubscribeProxy<T> autoDisposable(Maybe<T> autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (MaybeSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (MaybeSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static /* synthetic */ CompletableSubscribeProxy autoDisposable$default(Completable autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event, int i, Object obj) {
        if ((i & 2) != 0) {
            event = null;
        }
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (CompletableSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (CompletableSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static final CompletableSubscribeProxy autoDisposable(Completable autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (CompletableSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (CompletableSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static /* synthetic */ ParallelFlowableSubscribeProxy autoDisposable$default(ParallelFlowable autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event, int i, Object obj) {
        if ((i & 2) != 0) {
            event = null;
        }
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (ParallelFlowableSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (ParallelFlowableSubscribeProxy) objAs2;
    }

    @CheckReturnValue
    public static final <T> ParallelFlowableSubscribeProxy<T> autoDisposable(ParallelFlowable<T> autoDisposable, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkParameterIsNotNull(autoDisposable, "$this$autoDisposable");
        Intrinsics.checkParameterIsNotNull(lifecycleOwner, "lifecycleOwner");
        if (event == null) {
            Object objAs = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner)));
            Intrinsics.checkExpressionValueIsNotNull(objAs, "this.`as`(AutoDispose.au…er.from(lifecycleOwner)))");
            return (ParallelFlowableSubscribeProxy) objAs;
        }
        Object objAs2 = autoDisposable.as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner, event)));
        Intrinsics.checkExpressionValueIsNotNull(objAs2, "this.`as`(AutoDispose.au…cycleOwner, untilEvent)))");
        return (ParallelFlowableSubscribeProxy) objAs2;
    }
}
