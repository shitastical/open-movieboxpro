package com.movieboxpro.android.view.widget;

import android.content.Context;
import androidx.leanback.widget.ListRowPresenter;
import kotlin.Metadata;

/* JADX INFO: compiled from: NoHighlightRowPresenter.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0012\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/movieboxpro/android/view/widget/NoHighlightRowPresenter;", "Landroidx/leanback/widget/ListRowPresenter;", "<init>", "()V", "isUsingDefaultShadow", "", "isUsingZOrder", "context", "Landroid/content/Context;", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class NoHighlightRowPresenter extends ListRowPresenter {
    @Override // androidx.leanback.widget.ListRowPresenter
    public boolean isUsingDefaultShadow() {
        return false;
    }

    @Override // androidx.leanback.widget.ListRowPresenter
    public boolean isUsingZOrder(Context context) {
        return false;
    }

    public NoHighlightRowPresenter() {
        super(2, false);
        setSelectEffectEnabled(false);
    }
}
