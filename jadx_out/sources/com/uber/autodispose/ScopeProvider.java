package com.uber.autodispose;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.annotations.CheckReturnValue;

/* JADX INFO: loaded from: classes5.dex */
public interface ScopeProvider {
    public static final ScopeProvider UNBOUND = new ScopeProvider() { // from class: com.uber.autodispose.ScopeProvider$$ExternalSyntheticLambda0
        @Override // com.uber.autodispose.ScopeProvider
        public final CompletableSource requestScope() {
            return Completable.never();
        }
    };

    @CheckReturnValue
    CompletableSource requestScope() throws Exception;
}
