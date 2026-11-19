package com.hjq.toast;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes5.dex */
final class NotificationServiceProxy implements InvocationHandler {
    private final Object mRealObject;

    public NotificationServiceProxy(Object obj) {
        this.mRealObject = obj;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        name.hashCode();
        switch (name) {
            case "cancelToast":
            case "enqueueToastEx":
            case "enqueueToast":
                objArr[0] = "android";
                break;
        }
        return method.invoke(this.mRealObject, objArr);
    }
}
