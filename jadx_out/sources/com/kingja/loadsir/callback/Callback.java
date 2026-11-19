package com.kingja.loadsir.callback;

import android.content.Context;
import android.view.View;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class Callback implements Serializable {
    private Context context;
    private OnReloadListener onReloadListener;
    private View rootView;
    private boolean successViewVisible;

    public interface OnReloadListener extends Serializable {
        void onReload(View view);
    }

    public void onAttach(Context context, View view) {
    }

    protected View onBuildView(Context context) {
        return null;
    }

    protected abstract int onCreateView();

    public void onDetach() {
    }

    protected boolean onReloadEvent(Context context, View view) {
        return false;
    }

    protected boolean onRetry(Context context, View view) {
        return false;
    }

    protected void onViewCreate(Context context, View view) {
    }

    public Callback() {
    }

    Callback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
    }

    public Callback setCallback(View view, Context context, OnReloadListener onReloadListener) {
        this.rootView = view;
        this.context = context;
        this.onReloadListener = onReloadListener;
        return this;
    }

    public View getRootView() {
        View view;
        if (onCreateView() == 0 && (view = this.rootView) != null) {
            return view;
        }
        if (onBuildView(this.context) != null) {
            this.rootView = onBuildView(this.context);
        }
        if (this.rootView == null) {
            this.rootView = View.inflate(this.context, onCreateView(), null);
        }
        this.rootView.setOnClickListener(new View.OnClickListener() { // from class: com.kingja.loadsir.callback.Callback.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                Callback callback = Callback.this;
                if (callback.onReloadEvent(callback.context, Callback.this.rootView) || Callback.this.onReloadListener == null) {
                    return;
                }
                Callback.this.onReloadListener.onReload(view2);
            }
        });
        onViewCreate(this.context, this.rootView);
        return this.rootView;
    }

    public boolean getSuccessVisible() {
        return this.successViewVisible;
    }

    void setSuccessVisible(boolean z) {
        this.successViewVisible = z;
    }

    public Callback copy() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Object object = null;
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            object = objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (Callback) object;
    }

    public View obtainRootView() {
        if (this.rootView == null) {
            this.rootView = View.inflate(this.context, onCreateView(), null);
        }
        return this.rootView;
    }
}
