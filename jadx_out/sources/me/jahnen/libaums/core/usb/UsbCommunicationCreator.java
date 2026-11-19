package me.jahnen.libaums.core.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import kotlin.Metadata;

/* JADX INFO: compiled from: UsbCommunicationFactory.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J2\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&¨\u0006\r"}, d2 = {"Lme/jahnen/libaums/core/usb/UsbCommunicationCreator;", "", "create", "Lme/jahnen/libaums/core/usb/UsbCommunication;", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbInterface", "Landroid/hardware/usb/UsbInterface;", "outEndpoint", "Landroid/hardware/usb/UsbEndpoint;", "inEndpoint", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface UsbCommunicationCreator {
    UsbCommunication create(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint outEndpoint, UsbEndpoint inEndpoint);
}
