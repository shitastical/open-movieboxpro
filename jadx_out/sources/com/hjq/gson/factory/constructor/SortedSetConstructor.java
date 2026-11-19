package com.hjq.gson.factory.constructor;

import com.google.gson.internal.ObjectConstructor;
import java.util.SortedSet;
import java.util.TreeSet;

/* JADX INFO: loaded from: classes5.dex */
public final class SortedSetConstructor implements ObjectConstructor<SortedSet<?>> {
    private static final SortedSetConstructor INSTANCE = new SortedSetConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public SortedSet<?> construct() {
        return new TreeSet();
    }
}
