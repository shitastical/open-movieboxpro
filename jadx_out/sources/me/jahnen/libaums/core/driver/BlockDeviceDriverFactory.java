package me.jahnen.libaums.core.driver;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.scsi.ScsiBlockDevice;
import me.jahnen.libaums.core.usb.UsbCommunication;

/* JADX INFO: compiled from: BlockDeviceDriverFactory.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b¨\u0006\t"}, d2 = {"Lme/jahnen/libaums/core/driver/BlockDeviceDriverFactory;", "", "()V", "createBlockDevice", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "usbCommunication", "Lme/jahnen/libaums/core/usb/UsbCommunication;", "lun", "", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class BlockDeviceDriverFactory {
    public static final BlockDeviceDriverFactory INSTANCE = new BlockDeviceDriverFactory();

    private BlockDeviceDriverFactory() {
    }

    public final BlockDeviceDriver createBlockDevice(UsbCommunication usbCommunication, byte lun) {
        Intrinsics.checkNotNullParameter(usbCommunication, "usbCommunication");
        return new ScsiBlockDevice(usbCommunication, lun);
    }
}
