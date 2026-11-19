package com.hjq.gson.factory.constructor;

import com.google.gson.internal.ObjectConstructor;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX INFO: loaded from: classes5.dex */
public final class QueueConstructor implements ObjectConstructor<Queue<?>> {
    private static final QueueConstructor INSTANCE = new QueueConstructor();

    public static <T extends ObjectConstructor<?>> T getInstance() {
        return INSTANCE;
    }

    @Override // com.google.gson.internal.ObjectConstructor
    public Queue<?> construct() {
        return new ArrayDeque();
    }
}
