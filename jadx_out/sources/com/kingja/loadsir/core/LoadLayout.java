package com.kingja.loadsir.core;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import com.kingja.loadsir.LoadSirUtil;
import com.kingja.loadsir.callback.Callback;
import com.kingja.loadsir.callback.SuccessCallback;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class LoadLayout extends FrameLayout {
    private static final int CALLBACK_CUSTOM_INDEX = 1;
    private Map<Class<? extends Callback>, Callback> callbacks;
    private Context context;
    private Class<? extends Callback> curCallback;
    private Callback.OnReloadListener onReloadListener;
    private Class<? extends Callback> preCallback;

    public LoadLayout(Context context) {
        super(context);
        this.callbacks = new HashMap();
    }

    public LoadLayout(Context context, Callback.OnReloadListener onReloadListener) {
        this(context);
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public void setupSuccessLayout(Callback callback) {
        addCallback(callback);
        View rootView = callback.getRootView();
        rootView.setVisibility(8);
        addView(rootView);
        this.curCallback = SuccessCallback.class;
    }

    public void setupCallback(Callback callback) {
        Callback callbackCopy = callback.copy();
        callbackCopy.setCallback(null, this.context, this.onReloadListener);
        addCallback(callbackCopy);
    }

    public void addCallback(Callback callback) {
        if (this.callbacks.containsKey(callback.getClass())) {
            return;
        }
        this.callbacks.put((Class<? extends Callback>) callback.getClass(), callback);
    }

    public void showCallback(Class<? extends Callback> cls) {
        checkCallbackExist(cls);
        if (LoadSirUtil.isMainThread()) {
            showCallbackView(cls);
        } else {
            postToMainThread(cls);
        }
    }

    public Class<? extends Callback> getCurrentCallback() {
        return this.curCallback;
    }

    private void postToMainThread(final Class<? extends Callback> cls) {
        post(new Runnable() { // from class: com.kingja.loadsir.core.LoadLayout.1
            @Override // java.lang.Runnable
            public void run() {
                LoadLayout.this.showCallbackView(cls);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showCallbackView(Class<? extends Callback> cls) {
        Class<? extends Callback> cls2 = this.preCallback;
        if (cls2 != null) {
            if (cls2 == cls) {
                return;
            } else {
                this.callbacks.get(cls2).onDetach();
            }
        }
        if (getChildCount() > 1) {
            removeViewAt(1);
        }
        for (Class<? extends Callback> cls3 : this.callbacks.keySet()) {
            if (cls3 == cls) {
                SuccessCallback successCallback = (SuccessCallback) this.callbacks.get(SuccessCallback.class);
                if (cls3 == SuccessCallback.class) {
                    successCallback.show();
                } else {
                    successCallback.showWithCallback(this.callbacks.get(cls3).getSuccessVisible());
                    View rootView = this.callbacks.get(cls3).getRootView();
                    addView(rootView);
                    this.callbacks.get(cls3).onAttach(this.context, rootView);
                }
                this.preCallback = cls;
            }
        }
        this.curCallback = cls;
    }

    public void setCallBack(Class<? extends Callback> cls, Transport transport) {
        if (transport == null) {
            return;
        }
        checkCallbackExist(cls);
        transport.order(this.context, this.callbacks.get(cls).obtainRootView());
    }

    private void checkCallbackExist(Class<? extends Callback> cls) {
        if (!this.callbacks.containsKey(cls)) {
            throw new IllegalArgumentException(String.format("The Callback (%s) is nonexistent.", cls.getSimpleName()));
        }
    }
}
