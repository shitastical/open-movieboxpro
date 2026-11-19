package com.uber.autodispose;

/* JADX INFO: loaded from: classes5.dex */
public class OutsideScopeException extends RuntimeException {
    public OutsideScopeException(String str) {
        super(str);
    }

    @Override // java.lang.Throwable
    public final synchronized Throwable fillInStackTrace() {
        if (!AutoDisposePlugins.getFillInOutsideScopeExceptionStacktraces()) {
            return this;
        }
        return super.fillInStackTrace();
    }
}
