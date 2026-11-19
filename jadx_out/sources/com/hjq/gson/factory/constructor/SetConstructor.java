package com.hjq.gson.factory.constructor;

import com.google.gson.internal.ObjectConstructor;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class SetConstructor implements ObjectConstructor<Set<?>> {
    private static final SetConstructor INSTANCE = new SetConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public Set<?> construct() {
        return new LinkedHashSet();
    }
}
