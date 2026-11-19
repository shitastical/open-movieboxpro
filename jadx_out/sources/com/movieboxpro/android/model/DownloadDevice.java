package com.movieboxpro.android.model;

import android.hardware.usb.UsbDevice;

/* JADX INFO: loaded from: classes5.dex */
public class DownloadDevice {
    private String name;
    private boolean select;
    private String status;
    private UsbDevice usbDevice;

    public DownloadDevice(UsbDevice usbDevice, String name, boolean select) {
        this.usbDevice = usbDevice;
        this.name = name;
        this.select = select;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isSelect() {
        return this.select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

    public UsbDevice getUsbDevice() {
        return this.usbDevice;
    }

    public void setUsbDevice(UsbDevice usbDevice) {
        this.usbDevice = usbDevice;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
