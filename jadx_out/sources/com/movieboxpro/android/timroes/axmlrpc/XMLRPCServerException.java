package com.movieboxpro.android.timroes.axmlrpc;

/* JADX INFO: loaded from: classes5.dex */
public class XMLRPCServerException extends XMLRPCException {
    private final int errornr;

    public XMLRPCServerException(String ex, int errnr) {
        super(ex);
        this.errornr = errnr;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        return super.getMessage() + " [" + this.errornr + "]";
    }

    public int getErrorNr() {
        return this.errornr;
    }
}
