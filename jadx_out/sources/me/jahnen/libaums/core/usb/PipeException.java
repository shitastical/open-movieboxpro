package me.jahnen.libaums.core.usb;

import java.io.IOException;
import kotlin.Metadata;

/* JADX INFO: compiled from: PipeException.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lme/jahnen/libaums/core/usb/PipeException;", "Ljava/io/IOException;", "()V", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class PipeException extends IOException {
    public PipeException() {
        super("EPIPE, endpoint seems to be stalled");
    }
}
