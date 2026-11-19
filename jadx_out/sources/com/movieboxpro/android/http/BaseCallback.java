package com.movieboxpro.android.http;

import com.movieboxpro.android.view.listener.IViewController;
import java.lang.ref.WeakReference;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseCallback<T> implements Callback<T> {
    private WeakReference<Call> callWeakRef;
    private WeakReference<IViewController> viewWeakRef;

    public abstract boolean onResponse(T body);

    public BaseCallback() {
        this(null, null);
    }

    public BaseCallback(Call<T> call) {
        this(call, null);
    }

    public BaseCallback(Call<T> call, IViewController controller) {
        if (call != null) {
            this.callWeakRef = new WeakReference<>(call);
        }
        if (controller != null) {
            this.viewWeakRef = new WeakReference<>(controller);
        }
        onStart();
    }

    private void onStart() {
        Call call;
        WeakReference<Call> weakReference = this.callWeakRef;
        if (weakReference != null && (call = weakReference.get()) != null) {
            CallManager.add(call);
        }
        WeakReference<IViewController> weakReference2 = this.viewWeakRef;
        if (weakReference2 != null) {
            weakReference2.get();
        }
    }

    @Override // retrofit2.Callback
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (onResponse(response.body())) {
                onFinish(true, "请求成功");
                return;
            } else {
                onFailure(call, new Throwable("数据错误"));
                return;
            }
        }
        onFailure(call, new Throwable("请求失败"));
    }

    @Override // retrofit2.Callback
    public void onFailure(Call<T> call, Throwable t) {
        onFinish(false, t.getLocalizedMessage());
    }

    public void onFinish(boolean success, String msg) {
        Call call;
        WeakReference<Call> weakReference = this.callWeakRef;
        if (weakReference != null && (call = weakReference.get()) != null) {
            CallManager.remove(call);
        }
        WeakReference<IViewController> weakReference2 = this.viewWeakRef;
        if (weakReference2 != null) {
            weakReference2.get();
        }
    }
}
