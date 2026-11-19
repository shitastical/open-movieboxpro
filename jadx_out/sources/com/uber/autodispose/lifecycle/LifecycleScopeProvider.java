package com.uber.autodispose.lifecycle;

import com.uber.autodispose.ScopeProvider;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.annotations.CheckReturnValue;

/* JADX INFO: loaded from: classes5.dex */
public interface LifecycleScopeProvider<E> extends ScopeProvider {
    @CheckReturnValue
    CorrespondingEventsFunction<E> correspondingEvents();

    @CheckReturnValue
    Observable<E> lifecycle();

    E peekLifecycle();

    @Override // com.uber.autodispose.ScopeProvider
    CompletableSource requestScope();

    /* JADX INFO: renamed from: com.uber.autodispose.lifecycle.LifecycleScopeProvider$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
    }
}
