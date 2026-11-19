package com.kingja.loadsir.core;

import com.kingja.loadsir.LoadSirUtil;
import com.kingja.loadsir.callback.Callback;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class LoadSir {
    private static volatile LoadSir loadSir;
    private Builder builder;

    public static LoadSir getDefault() {
        if (loadSir == null) {
            synchronized (LoadSir.class) {
                if (loadSir == null) {
                    loadSir = new LoadSir();
                }
            }
        }
        return loadSir;
    }

    private LoadSir() {
        this.builder = new Builder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBuilder(Builder builder) {
        this.builder = builder;
    }

    private LoadSir(Builder builder) {
        this.builder = builder;
    }

    public LoadService register(Object obj) {
        return register(obj, null, null);
    }

    public LoadService register(Object obj, Callback.OnReloadListener onReloadListener) {
        return register(obj, onReloadListener, null);
    }

    public <T> LoadService register(Object obj, Callback.OnReloadListener onReloadListener, Convertor<T> convertor) {
        return new LoadService(convertor, LoadSirUtil.getTargetContext(obj), onReloadListener, this.builder);
    }

    public static Builder beginBuilder() {
        return new Builder();
    }

    public static class Builder {
        private List<Callback> callbacks = new ArrayList();
        private Class<? extends Callback> defaultCallback;

        public Builder addCallback(Callback callback) {
            this.callbacks.add(callback);
            return this;
        }

        public Builder setDefaultCallback(Class<? extends Callback> cls) {
            this.defaultCallback = cls;
            return this;
        }

        List<Callback> getCallbacks() {
            return this.callbacks;
        }

        Class<? extends Callback> getDefaultCallback() {
            return this.defaultCallback;
        }

        public void commit() {
            LoadSir.getDefault().setBuilder(this);
        }

        public LoadSir build() {
            return new LoadSir(this);
        }
    }
}
