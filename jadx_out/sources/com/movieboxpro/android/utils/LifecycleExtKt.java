package com.movieboxpro.android.utils;

import androidx.lifecycle.Lifecycle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LifecycleExt.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¨\u0006\u0005"}, d2 = {"addObserver", "", "Landroidx/lifecycle/Lifecycle;", "destroyed", "Lkotlin/Function0;", "app_movie_tvRelease"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class LifecycleExtKt {
    public static final void addObserver(Lifecycle lifecycle, Function0<Unit> destroyed) {
        Intrinsics.checkNotNullParameter(lifecycle, "<this>");
        Intrinsics.checkNotNullParameter(destroyed, "destroyed");
        lifecycle.addObserver(new LifecycleObserver(lifecycle, destroyed));
    }
}
