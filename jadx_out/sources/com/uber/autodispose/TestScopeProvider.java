package com.uber.autodispose;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.subjects.CompletableSubject;

/* JADX INFO: loaded from: classes5.dex */
public final class TestScopeProvider implements ScopeProvider {
    private final CompletableSubject innerScope;

    public static TestScopeProvider create() {
        return create(CompletableSubject.create());
    }

    public static TestScopeProvider create(Completable completable) {
        return new TestScopeProvider(completable);
    }

    private TestScopeProvider(Completable completable) {
        CompletableSubject completableSubjectCreate = CompletableSubject.create();
        this.innerScope = completableSubjectCreate;
        completable.subscribe(completableSubjectCreate);
    }

    @Override // com.uber.autodispose.ScopeProvider
    public CompletableSource requestScope() {
        return this.innerScope;
    }

    public void emit() {
        this.innerScope.onComplete();
    }
}
