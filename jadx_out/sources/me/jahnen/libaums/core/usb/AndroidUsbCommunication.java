package me.jahnen.libaums.core.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import android.util.Log;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.ErrNo;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: AndroidUsbCommunication.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0007\b \u0018\u0000 12\u00020\u0001:\u00011B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\tH\u0016J\u0019\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001f\u001a\u00020\"H\u0082 J\b\u0010#\u001a\u00020\u001eH\u0016J\b\u0010$\u001a\u00020\u001eH\u0002J8\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\"2\u0006\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020\"2\u0006\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\"H\u0016J\b\u0010-\u001a\u00020\u001eH\u0002J\b\u0010.\u001a\u00020\u001eH\u0002J\b\u0010/\u001a\u00020\u001eH\u0016J\u0011\u00100\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0082 R\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\n\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0013R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lme/jahnen/libaums/core/usb/AndroidUsbCommunication;", "Lme/jahnen/libaums/core/usb/UsbCommunication;", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbInterface", "Landroid/hardware/usb/UsbInterface;", "outEndpoint", "Landroid/hardware/usb/UsbEndpoint;", "inEndpoint", "(Landroid/hardware/usb/UsbManager;Landroid/hardware/usb/UsbDevice;Landroid/hardware/usb/UsbInterface;Landroid/hardware/usb/UsbEndpoint;Landroid/hardware/usb/UsbEndpoint;)V", "deviceConnection", "Landroid/hardware/usb/UsbDeviceConnection;", "getDeviceConnection", "()Landroid/hardware/usb/UsbDeviceConnection;", "setDeviceConnection", "(Landroid/hardware/usb/UsbDeviceConnection;)V", "getInEndpoint", "()Landroid/hardware/usb/UsbEndpoint;", "isClosed", "", "()Z", "setClosed", "(Z)V", "isNativeInited", "getOutEndpoint", "getUsbInterface", "()Landroid/hardware/usb/UsbInterface;", "clearFeatureHalt", "", "endpoint", "clearHaltNative", IjkMediaPlayer.OnNativeInvokeListener.ARG_FD, "", "close", "closeUsbConnection", "controlTransfer", "requestType", "request", "value", FirebaseAnalytics.Param.INDEX, "buffer", "", "length", "initNativeLibrary", "initUsbConnection", "resetDevice", "resetUsbDeviceNative", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public abstract class AndroidUsbCommunication implements UsbCommunication {
    private static final String TAG = "AndroidUsbCommunication";
    private UsbDeviceConnection deviceConnection;
    private final UsbEndpoint inEndpoint;
    private boolean isClosed;
    private boolean isNativeInited;
    private final UsbEndpoint outEndpoint;
    private final UsbDevice usbDevice;
    private final UsbInterface usbInterface;
    private final UsbManager usbManager;

    private final native boolean clearHaltNative(int fd, int endpoint);

    private final native boolean resetUsbDeviceNative(int fd);

    public AndroidUsbCommunication(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint outEndpoint, UsbEndpoint inEndpoint) throws IOException {
        Intrinsics.checkNotNullParameter(usbManager, "usbManager");
        Intrinsics.checkNotNullParameter(usbDevice, "usbDevice");
        Intrinsics.checkNotNullParameter(usbInterface, "usbInterface");
        Intrinsics.checkNotNullParameter(outEndpoint, "outEndpoint");
        Intrinsics.checkNotNullParameter(inEndpoint, "inEndpoint");
        this.usbManager = usbManager;
        this.usbDevice = usbDevice;
        this.usbInterface = usbInterface;
        this.outEndpoint = outEndpoint;
        this.inEndpoint = inEndpoint;
        initNativeLibrary();
        initUsbConnection();
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public UsbInterface getUsbInterface() {
        return this.usbInterface;
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public UsbEndpoint getOutEndpoint() {
        return this.outEndpoint;
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public UsbEndpoint getInEndpoint() {
        return this.inEndpoint;
    }

    public final UsbDeviceConnection getDeviceConnection() {
        return this.deviceConnection;
    }

    public final void setDeviceConnection(UsbDeviceConnection usbDeviceConnection) {
        this.deviceConnection = usbDeviceConnection;
    }

    /* JADX INFO: renamed from: isClosed, reason: from getter */
    protected final boolean getIsClosed() {
        return this.isClosed;
    }

    protected final void setClosed(boolean z) {
        this.isClosed = z;
    }

    private final void initUsbConnection() throws IOException {
        if (this.isClosed) {
            return;
        }
        Log.d(TAG, "setup device");
        UsbDeviceConnection usbDeviceConnectionOpenDevice = this.usbManager.openDevice(this.usbDevice);
        if (usbDeviceConnectionOpenDevice == null) {
            throw new IOException("deviceConnection is null!");
        }
        this.deviceConnection = usbDeviceConnectionOpenDevice;
        Intrinsics.checkNotNull(usbDeviceConnectionOpenDevice);
        if (!usbDeviceConnectionOpenDevice.claimInterface(getUsbInterface(), true)) {
            throw new IOException("could not claim interface!");
        }
    }

    private final void initNativeLibrary() {
        try {
            System.loadLibrary("usb-lib");
            this.isNativeInited = true;
        } catch (UnsatisfiedLinkError e) {
            this.isNativeInited = false;
            Log.e(TAG, "could not load usb-lib", e);
        }
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public int controlTransfer(int requestType, int request, int value, int index, byte[] buffer, int length) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (this.isClosed) {
            throw new IllegalArgumentException("device is closed".toString());
        }
        UsbDeviceConnection usbDeviceConnection = this.deviceConnection;
        Intrinsics.checkNotNull(usbDeviceConnection);
        return usbDeviceConnection.controlTransfer(requestType, request, value, index, buffer, length, 5000);
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public void resetDevice() throws IOException {
        if (this.isClosed) {
            throw new IllegalArgumentException("device is closed".toString());
        }
        String str = TAG;
        Log.d(str, "Performing native reset");
        UsbDeviceConnection usbDeviceConnection = this.deviceConnection;
        Intrinsics.checkNotNull(usbDeviceConnection);
        if (!usbDeviceConnection.releaseInterface(getUsbInterface())) {
            Log.w(str, "Failed to release interface, errno: " + ErrNo.INSTANCE.getErrno() + ' ' + ErrNo.INSTANCE.getErrstr());
        }
        UsbDeviceConnection usbDeviceConnection2 = this.deviceConnection;
        Intrinsics.checkNotNull(usbDeviceConnection2);
        if (!resetUsbDeviceNative(usbDeviceConnection2.getFileDescriptor())) {
            Log.w(str, "ioctl failed! errno " + ErrNo.INSTANCE.getErrno() + ' ' + ErrNo.INSTANCE.getErrstr());
            Log.w(str, "USB device will likely require new discovery and permissions");
        }
        UsbDeviceConnection usbDeviceConnection3 = this.deviceConnection;
        Intrinsics.checkNotNull(usbDeviceConnection3);
        if (usbDeviceConnection3.claimInterface(getUsbInterface(), true)) {
            return;
        }
        throw new IOException("Could not claim interface, errno: " + ErrNo.INSTANCE.getErrno() + ' ' + ErrNo.INSTANCE.getErrstr());
    }

    @Override // me.jahnen.libaums.core.usb.UsbCommunication
    public void clearFeatureHalt(UsbEndpoint endpoint) {
        Intrinsics.checkNotNullParameter(endpoint, "endpoint");
        if (this.isClosed) {
            throw new IllegalArgumentException("device is closed".toString());
        }
        String str = TAG;
        Log.w(str, "Clearing halt on endpoint " + endpoint + " (direction " + endpoint.getDirection() + ')');
        UsbDeviceConnection usbDeviceConnection = this.deviceConnection;
        Intrinsics.checkNotNull(usbDeviceConnection);
        if (clearHaltNative(usbDeviceConnection.getFileDescriptor(), endpoint.getAddress())) {
            return;
        }
        Log.e(str, "Clear halt failed: errno " + ErrNo.INSTANCE.getErrno() + ' ' + ErrNo.INSTANCE.getErrstr());
    }

    private final void closeUsbConnection() {
        UsbDeviceConnection usbDeviceConnection = this.deviceConnection;
        if (usbDeviceConnection == null) {
            return;
        }
        Intrinsics.checkNotNull(usbDeviceConnection);
        if (!usbDeviceConnection.releaseInterface(getUsbInterface())) {
            Log.e(TAG, "could not release interface!");
        }
        UsbDeviceConnection usbDeviceConnection2 = this.deviceConnection;
        Intrinsics.checkNotNull(usbDeviceConnection2);
        usbDeviceConnection2.close();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.isClosed) {
            throw new IllegalArgumentException("device is already closed".toString());
        }
        Log.d(TAG, "close device");
        closeUsbConnection();
        this.isClosed = true;
    }
}
