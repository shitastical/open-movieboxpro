package com.kingja.loadsir.core;

import com.kingja.loadsir.callback.Callback;

/* JADX INFO: loaded from: classes5.dex */
public interface Convertor<T> {
    Class<? extends Callback> map(T t);
}
