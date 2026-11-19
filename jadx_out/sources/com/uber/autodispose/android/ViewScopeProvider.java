package com.uber.autodispose.android;

import android.view.View;
import com.uber.autodispose.ScopeProvider;
import io.reactivex.CompletableSource;

/* JADX INFO: loaded from: classes5.dex */
public final class ViewScopeProvider implements ScopeProvider {
    private final View view;

    public static ScopeProvider from(View view) {
        if (view == null) {
            throw new NullPointerException("view == null");
        }
        return new ViewScopeProvider(view);
    }

    private ViewScopeProvider(View view) {
        this.view = view;
    }

    @Override // com.uber.autodispose.ScopeProvider
    public CompletableSource requestScope() {
        return new DetachEventCompletable(this.view);
    }
}
