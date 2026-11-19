package me.jahnen.libaums.core.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.system.OsConstants;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.ErrNo;

/* JADX INFO: compiled from: JellyBeanMr2Communication.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u000fH\u0016¨\u0006\u0012"}, d2 = {"Lme/jahnen/libaums/core/usb/JellyBeanMr2Communication;", "Lme/jahnen/libaums/core/usb/AndroidUsbCommunication;", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbInterface", "Landroid/hardware/usb/UsbInterface;", "outEndpoint", "Landroid/hardware/usb/UsbEndpoint;", "inEndpoint", "(Landroid/hardware/usb/UsbManager;Landroid/hardware/usb/UsbDevice;Landroid/hardware/usb/UsbInterface;Landroid/hardware/usb/UsbEndpoint;Landroid/hardware/usb/UsbEndpoint;)V", "bulkInTransfer", "", "dest", "Ljava/nio/ByteBuffer;", "bulkOutTransfer", "src", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class JellyBeanMr2Communication extends AndroidUsbCommunication {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JellyBeanMr2Communication(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint outEndpoint, UsbEndpoint inEndpoint) {
        super(usbManager, usbDevice, usbInterface, outEndpoint, inEndpoint);
        Intrinsics.checkNotNullParameter(usbManager, "usbManager");
        Intrinsics.checkNotNullParameter(usbDevice, "usbDevice");
        Intrinsics.checkNotNullParameter(usbInterface, "usbInterface");
        Intrinsics.checkNotNullParameter(outEndpoint, "outEndpoint");
        Intrinsics.checkNotNullParameter(inEndpoint, "inEndpoint");
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public int bulkOutTransfer(ByteBuffer src) throws IOException {
        Intrinsics.checkNotNullParameter(src, "src");
        if (getIsClosed()) {
            throw new IllegalArgumentException("device is closed".toString());
        }
        UsbDeviceConnection deviceConnection = getDeviceConnection();
        Intrinsics.checkNotNull(deviceConnection);
        int iBulkTransfer = deviceConnection.bulkTransfer(getOutEndpoint(), src.array(), src.position(), src.remaining(), 5000);
        if (iBulkTransfer == -1) {
            if (ErrNo.INSTANCE.getErrno() == OsConstants.EPIPE) {
                throw new PipeException();
            }
            throw new IOException("Could not read from device, result == -1 errno " + ErrNo.INSTANCE.getErrno() + ' ' + ErrNo.INSTANCE.getErrstr());
        }
        src.position(src.position() + iBulkTransfer);
        return iBulkTransfer;
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public int bulkInTransfer(ByteBuffer dest) throws IOException {
        Intrinsics.checkNotNullParameter(dest, "dest");
        if (getIsClosed()) {
            throw new IllegalArgumentException("device is closed".toString());
        }
        UsbDeviceConnection deviceConnection = getDeviceConnection();
        Intrinsics.checkNotNull(deviceConnection);
        int iBulkTransfer = deviceConnection.bulkTransfer(getInEndpoint(), dest.array(), dest.position(), dest.remaining(), 5000);
        if (iBulkTransfer == -1) {
            if (ErrNo.INSTANCE.getErrno() == OsConstants.EPIPE) {
                throw new PipeException();
            }
            throw new IOException("Could not read from device, result == -1 errno " + ErrNo.INSTANCE.getErrno() + ' ' + ErrNo.INSTANCE.getErrstr());
        }
        dest.position(dest.position() + iBulkTransfer);
        return iBulkTransfer;
    }
}
