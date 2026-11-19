package com.uber.autodispose.lifecycle;

import com.uber.autodispose.OutsideScopeException;
import io.reactivex.functions.Function;

/* JADX INFO: loaded from: classes5.dex */
public interface CorrespondingEventsFunction<E> extends Function<E, E> {
    @Override // io.reactivex.functions.Function
    E apply(E e) throws OutsideScopeException;
}
