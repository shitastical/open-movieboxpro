package com.hjq.toast;

import android.app.Application;
import android.widget.Toast;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* JADX INFO: loaded from: classes5.dex */
public class NotificationToast extends SystemToast {
    private static boolean sHookService;

    public NotificationToast(Application application) {
        super(application);
    }

    @Override // android.widget.Toast, com.hjq.toast.config.IToast
    public void show() {
        hookNotificationService();
        super.show();
    }

    private static void hookNotificationService() {
        if (sHookService) {
            return;
        }
        sHookService = true;
        try {
            Method declaredMethod = Toast.class.getDeclaredMethod("getService", null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, null);
            if (objInvoke == null) {
                return;
            }
            if (Proxy.isProxyClass(objInvoke.getClass()) && (Proxy.getInvocationHandler(objInvoke) instanceof NotificationServiceProxy)) {
                return;
            }
            Object objNewProxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{Class.forName("android.app.INotificationManager")}, new NotificationServiceProxy(objInvoke));
            Field declaredField = Toast.class.getDeclaredField("sService");
            declaredField.setAccessible(true);
            declaredField.set(null, objNewProxyInstance);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
