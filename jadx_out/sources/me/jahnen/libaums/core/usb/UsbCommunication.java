package me.jahnen.libaums.core.usb;

import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;

/* JADX INFO: compiled from: UsbCommunication.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u0012\n\u0002\b\u0004\bf\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000fH&J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003H&J8\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\rH&J\b\u0010\u001d\u001a\u00020\u0013H&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001f"}, d2 = {"Lme/jahnen/libaums/core/usb/UsbCommunication;", "Ljava/io/Closeable;", "inEndpoint", "Landroid/hardware/usb/UsbEndpoint;", "getInEndpoint", "()Landroid/hardware/usb/UsbEndpoint;", "outEndpoint", "getOutEndpoint", "usbInterface", "Landroid/hardware/usb/UsbInterface;", "getUsbInterface", "()Landroid/hardware/usb/UsbInterface;", "bulkInTransfer", "", "dest", "Ljava/nio/ByteBuffer;", "bulkOutTransfer", "src", "clearFeatureHalt", "", "endpoint", "controlTransfer", "requestType", "request", "value", FirebaseAnalytics.Param.INDEX, "buffer", "", "length", "resetDevice", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface UsbCommunication extends Closeable {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = Companion.$$INSTANCE;
    public static final int TRANSFER_TIMEOUT = 5000;

    int bulkInTransfer(ByteBuffer dest) throws IOException;

    int bulkOutTransfer(ByteBuffer src) throws IOException;

    void clearFeatureHalt(UsbEndpoint endpoint) throws IOException;

    int controlTransfer(int requestType, int request, int value, int index, byte[] buffer, int length) throws IOException;

    UsbEndpoint getInEndpoint();

    UsbEndpoint getOutEndpoint();

    UsbInterface getUsbInterface();

    void resetDevice() throws IOException;

    /* JADX INFO: compiled from: UsbCommunication.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lme/jahnen/libaums/core/usb/UsbCommunication$Companion;", "", "()V", "TRANSFER_TIMEOUT", "", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        public static final int TRANSFER_TIMEOUT = 5000;

        private Companion() {
        }
    }
}
