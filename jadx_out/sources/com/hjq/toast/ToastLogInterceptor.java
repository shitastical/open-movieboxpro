package com.hjq.toast;

import android.util.Log;
import com.hjq.toast.config.IToastInterceptor;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes5.dex */
public class ToastLogInterceptor implements IToastInterceptor {
    @Override // com.hjq.toast.config.IToastInterceptor
    public boolean intercept(ToastParams toastParams) {
        printToast(toastParams.text);
        return false;
    }

    protected void printToast(CharSequence charSequence) {
        if (isLogEnable()) {
            for (StackTraceElement stackTraceElement : new Throwable().getStackTrace()) {
                int lineNumber = stackTraceElement.getLineNumber();
                if (lineNumber > 0) {
                    try {
                        if (!filterClass(Class.forName(stackTraceElement.getClassName()))) {
                            printLog("(" + stackTraceElement.getFileName() + ":" + lineNumber + ") " + charSequence.toString());
                            return;
                        }
                        continue;
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    protected boolean isLogEnable() {
        return Toaster.isDebugMode();
    }

    protected void printLog(String str) {
        Log.i("Toaster", str);
    }

    protected boolean filterClass(Class<?> cls) {
        return IToastInterceptor.class.isAssignableFrom(cls) || Toaster.class.equals(cls) || cls.isInterface() || Modifier.isAbstract(cls.getModifiers());
    }
}
