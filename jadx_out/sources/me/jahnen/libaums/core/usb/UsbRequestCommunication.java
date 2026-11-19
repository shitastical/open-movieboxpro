package me.jahnen.libaums.core.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.hardware.usb.UsbRequest;
import android.os.Build;
import java.io.IOException;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UsbRequestCommunication.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u000e\u0010\u0015\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0010H\u0007J\u0010\u0010\u0017\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010J\u0010\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0018\u001a\u00020\u0010H\u0007R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000f\u001a\n \u0011*\u0004\u0018\u00010\u00100\u0010X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lme/jahnen/libaums/core/usb/UsbRequestCommunication;", "Lme/jahnen/libaums/core/usb/AndroidUsbCommunication;", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbInterface", "Landroid/hardware/usb/UsbInterface;", "outEndpoint", "Landroid/hardware/usb/UsbEndpoint;", "inEndpoint", "(Landroid/hardware/usb/UsbManager;Landroid/hardware/usb/UsbDevice;Landroid/hardware/usb/UsbInterface;Landroid/hardware/usb/UsbEndpoint;Landroid/hardware/usb/UsbEndpoint;)V", "inRequest", "Landroid/hardware/usb/UsbRequest;", "outRequest", "workaroundBuffer", "Ljava/nio/ByteBuffer;", "kotlin.jvm.PlatformType", "bulkInTransfer", "", "dest", "bulkInTransferApiLesserO", "bulkInTransferApiO", "bulkOutTransfer", "src", "bulkOutTransferApiLesserO", "bulkOutTransferApiO", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UsbRequestCommunication extends AndroidUsbCommunication {
    private final UsbRequest inRequest;
    private final UsbRequest outRequest;
    private final ByteBuffer workaroundBuffer;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UsbRequestCommunication(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint outEndpoint, UsbEndpoint inEndpoint) {
        super(usbManager, usbDevice, usbInterface, outEndpoint, inEndpoint);
        Intrinsics.checkNotNullParameter(usbManager, "usbManager");
        Intrinsics.checkNotNullParameter(usbDevice, "usbDevice");
        Intrinsics.checkNotNullParameter(usbInterface, "usbInterface");
        Intrinsics.checkNotNullParameter(outEndpoint, "outEndpoint");
        Intrinsics.checkNotNullParameter(inEndpoint, "inEndpoint");
        UsbRequest usbRequest = new UsbRequest();
        usbRequest.initialize(getDeviceConnection(), outEndpoint);
        this.outRequest = usbRequest;
        UsbRequest usbRequest2 = new UsbRequest();
        usbRequest2.initialize(getDeviceConnection(), inEndpoint);
        this.inRequest = usbRequest2;
        this.workaroundBuffer = ByteBuffer.allocate(131072);
    }

    public final int bulkOutTransferApiO(ByteBuffer src) throws IOException {
        Intrinsics.checkNotNullParameter(src, "src");
        int iPosition = src.position();
        if (!this.outRequest.queue(src)) {
            throw new IOException("Error queueing request.");
        }
        UsbDeviceConnection deviceConnection = getDeviceConnection();
        Intrinsics.checkNotNull(deviceConnection);
        UsbRequest usbRequestRequestWait = deviceConnection.requestWait();
        if (usbRequestRequestWait == this.outRequest) {
            return this.workaroundBuffer.position() - iPosition;
        }
        throw new IOException("requestWait failed! Request: " + usbRequestRequestWait);
    }

    public final int bulkOutTransferApiLesserO(ByteBuffer src) throws IOException {
        Intrinsics.checkNotNullParameter(src, "src");
        int iRemaining = src.remaining();
        int iPosition = src.position();
        this.workaroundBuffer.clear();
        this.workaroundBuffer.put(src);
        if (!this.outRequest.queue(this.workaroundBuffer, iRemaining)) {
            throw new IOException("Error queueing request.");
        }
        UsbDeviceConnection deviceConnection = getDeviceConnection();
        Intrinsics.checkNotNull(deviceConnection);
        UsbRequest usbRequestRequestWait = deviceConnection.requestWait();
        if (usbRequestRequestWait == this.outRequest) {
            src.position(iPosition + this.workaroundBuffer.position());
            return this.workaroundBuffer.position();
        }
        throw new IOException("requestWait failed! Request: " + usbRequestRequestWait);
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public synchronized int bulkOutTransfer(ByteBuffer src) throws IOException {
        int iBulkOutTransferApiLesserO;
        Intrinsics.checkNotNullParameter(src, "src");
        if (Build.VERSION.SDK_INT >= 26) {
            iBulkOutTransferApiLesserO = bulkOutTransferApiO(src);
        } else {
            iBulkOutTransferApiLesserO = bulkOutTransferApiLesserO(src);
        }
        return iBulkOutTransferApiLesserO;
    }

    public final int bulkInTransferApiLesserO(ByteBuffer dest) throws IOException {
        Intrinsics.checkNotNullParameter(dest, "dest");
        int iRemaining = dest.remaining();
        this.workaroundBuffer.clear();
        this.workaroundBuffer.limit(iRemaining);
        if (!this.inRequest.queue(this.workaroundBuffer, iRemaining)) {
            throw new IOException("Error queueing request.");
        }
        UsbDeviceConnection deviceConnection = getDeviceConnection();
        Intrinsics.checkNotNull(deviceConnection);
        UsbRequest usbRequestRequestWait = deviceConnection.requestWait();
        if (usbRequestRequestWait == this.inRequest) {
            this.workaroundBuffer.flip();
            dest.put(this.workaroundBuffer);
            return this.workaroundBuffer.limit();
        }
        throw new IOException("requestWait failed! Request: " + usbRequestRequestWait);
    }

    public final int bulkInTransferApiO(ByteBuffer dest) throws IOException {
        Intrinsics.checkNotNullParameter(dest, "dest");
        int iPosition = dest.position();
        if (!this.inRequest.queue(dest)) {
            throw new IOException("Error queueing request.");
        }
        UsbDeviceConnection deviceConnection = getDeviceConnection();
        Intrinsics.checkNotNull(deviceConnection);
        UsbRequest usbRequestRequestWait = deviceConnection.requestWait();
        if (usbRequestRequestWait == this.outRequest) {
            return this.workaroundBuffer.position() - iPosition;
        }
        throw new IOException("requestWait failed! Request: " + usbRequestRequestWait);
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public synchronized int bulkInTransfer(ByteBuffer dest) throws IOException {
        int iBulkInTransferApiLesserO;
        Intrinsics.checkNotNullParameter(dest, "dest");
        if (Build.VERSION.SDK_INT >= 26) {
            iBulkInTransferApiLesserO = bulkInTransferApiO(dest);
        } else {
            iBulkInTransferApiLesserO = bulkInTransferApiLesserO(dest);
        }
        return iBulkInTransferApiLesserO;
    }
}
