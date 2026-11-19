package com.movieboxpro.android.http;

/* JADX INFO: loaded from: classes5.dex */
public class ServerException extends RuntimeException {
    private int errCode;
    private String message;

    public ServerException(int errCode, String msg) {
        super(msg);
        this.errCode = errCode;
        this.message = msg;
    }

    public int getErrCode() {
        return this.errCode;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return this.message;
    }
}
