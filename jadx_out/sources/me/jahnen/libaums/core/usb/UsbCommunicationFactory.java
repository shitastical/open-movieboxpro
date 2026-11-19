package me.jahnen.libaums.core.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: UsbCommunicationFactory.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0019J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\bH\u0007R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f¨\u0006 "}, d2 = {"Lme/jahnen/libaums/core/usb/UsbCommunicationFactory;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "communications", "Ljava/util/ArrayList;", "Lme/jahnen/libaums/core/usb/UsbCommunicationCreator;", "underlyingUsbCommunication", "Lme/jahnen/libaums/core/usb/UsbCommunicationFactory$UnderlyingUsbCommunication;", "getUnderlyingUsbCommunication$annotations", "getUnderlyingUsbCommunication", "()Lme/jahnen/libaums/core/usb/UsbCommunicationFactory$UnderlyingUsbCommunication;", "setUnderlyingUsbCommunication", "(Lme/jahnen/libaums/core/usb/UsbCommunicationFactory$UnderlyingUsbCommunication;)V", "createUsbCommunication", "Lme/jahnen/libaums/core/usb/UsbCommunication;", "usbManager", "Landroid/hardware/usb/UsbManager;", "usbDevice", "Landroid/hardware/usb/UsbDevice;", "usbInterface", "Landroid/hardware/usb/UsbInterface;", "outEndpoint", "Landroid/hardware/usb/UsbEndpoint;", "inEndpoint", "registerCommunication", "", "creator", "NoUsbCommunicationFound", "UnderlyingUsbCommunication", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class UsbCommunicationFactory {
    public static final UsbCommunicationFactory INSTANCE = new UsbCommunicationFactory();
    private static final String TAG = "UsbCommunicationFactory";
    private static final ArrayList<UsbCommunicationCreator> communications = new ArrayList<>();
    private static UnderlyingUsbCommunication underlyingUsbCommunication = UnderlyingUsbCommunication.DEVICE_CONNECTION_SYNC;

    /* JADX INFO: compiled from: UsbCommunicationFactory.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lme/jahnen/libaums/core/usb/UsbCommunicationFactory$NoUsbCommunicationFound;", "Ljava/io/IOException;", "()V", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class NoUsbCommunicationFound extends IOException {
    }

    /* JADX INFO: compiled from: UsbCommunicationFactory.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lme/jahnen/libaums/core/usb/UsbCommunicationFactory$UnderlyingUsbCommunication;", "", "(Ljava/lang/String;I)V", "USB_REQUEST_ASYNC", "DEVICE_CONNECTION_SYNC", "OTHER", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public enum UnderlyingUsbCommunication {
        USB_REQUEST_ASYNC,
        DEVICE_CONNECTION_SYNC,
        OTHER
    }

    /* JADX INFO: compiled from: UsbCommunicationFactory.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[UnderlyingUsbCommunication.values().length];
            try {
                iArr[UnderlyingUsbCommunication.DEVICE_CONNECTION_SYNC.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[UnderlyingUsbCommunication.USB_REQUEST_ASYNC.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[UnderlyingUsbCommunication.OTHER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @JvmStatic
    public static /* synthetic */ void getUnderlyingUsbCommunication$annotations() {
    }

    private UsbCommunicationFactory() {
    }

    public static final UnderlyingUsbCommunication getUnderlyingUsbCommunication() {
        return underlyingUsbCommunication;
    }

    public static final void setUnderlyingUsbCommunication(UnderlyingUsbCommunication underlyingUsbCommunication2) {
        Intrinsics.checkNotNullParameter(underlyingUsbCommunication2, "<set-?>");
        underlyingUsbCommunication = underlyingUsbCommunication2;
    }

    @JvmStatic
    public static final synchronized void registerCommunication(UsbCommunicationCreator creator) {
        Intrinsics.checkNotNullParameter(creator, "creator");
        communications.add(creator);
    }

    public final UsbCommunication createUsbCommunication(UsbManager usbManager, UsbDevice usbDevice, UsbInterface usbInterface, UsbEndpoint outEndpoint, UsbEndpoint inEndpoint) throws NoUsbCommunicationFound {
        Intrinsics.checkNotNullParameter(usbManager, "usbManager");
        Intrinsics.checkNotNullParameter(usbDevice, "usbDevice");
        Intrinsics.checkNotNullParameter(usbInterface, "usbInterface");
        Intrinsics.checkNotNullParameter(outEndpoint, "outEndpoint");
        Intrinsics.checkNotNullParameter(inEndpoint, "inEndpoint");
        int i = WhenMappings.$EnumSwitchMapping$0[underlyingUsbCommunication.ordinal()];
        if (i == 1) {
            return new JellyBeanMr2Communication(usbManager, usbDevice, usbInterface, outEndpoint, inEndpoint);
        }
        if (i == 2) {
            return new UsbRequestCommunication(usbManager, usbDevice, usbInterface, outEndpoint, inEndpoint);
        }
        if (i == 3) {
            Iterator<UsbCommunicationCreator> it = communications.iterator();
            while (it.hasNext()) {
                UsbCommunication usbCommunicationCreate = it.next().create(usbManager, usbDevice, usbInterface, outEndpoint, inEndpoint);
                if (usbCommunicationCreate != null) {
                    return usbCommunicationCreate;
                }
            }
        }
        throw new NoUsbCommunicationFound();
    }
}
