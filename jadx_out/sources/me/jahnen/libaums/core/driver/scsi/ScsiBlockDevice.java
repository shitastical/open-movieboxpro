package me.jahnen.libaums.core.driver.scsi;

import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import me.jahnen.libaums.core.driver.BlockDeviceDriver;
import me.jahnen.libaums.core.driver.scsi.commands.CommandBlockWrapper;
import me.jahnen.libaums.core.driver.scsi.commands.CommandStatusWrapper;
import me.jahnen.libaums.core.driver.scsi.commands.ScsiInquiry;
import me.jahnen.libaums.core.driver.scsi.commands.ScsiInquiryResponse;
import me.jahnen.libaums.core.driver.scsi.commands.ScsiRead10;
import me.jahnen.libaums.core.driver.scsi.commands.ScsiReadCapacity;
import me.jahnen.libaums.core.driver.scsi.commands.ScsiReadCapacityResponse;
import me.jahnen.libaums.core.driver.scsi.commands.ScsiTestUnitReady;
import me.jahnen.libaums.core.driver.scsi.commands.ScsiWrite10;
import me.jahnen.libaums.core.driver.scsi.commands.sense.InitRequired;
import me.jahnen.libaums.core.driver.scsi.commands.sense.NotReadyTryAgain;
import me.jahnen.libaums.core.driver.scsi.commands.sense.ScsiRequestSense;
import me.jahnen.libaums.core.driver.scsi.commands.sense.ScsiRequestSenseResponse;
import me.jahnen.libaums.core.driver.scsi.commands.sense.SenseException;
import me.jahnen.libaums.core.usb.PipeException;
import me.jahnen.libaums.core.usb.UsbCommunication;

/* JADX INFO: compiled from: ScsiBlockDevice.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 -2\u00020\u0001:\u0001-B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u001b\u001a\u00020\u001cH\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\bH\u0002J\b\u0010 \u001a\u00020\u001cH\u0016J\b\u0010!\u001a\u00020\u001cH\u0002J\u0018\u0010\"\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u0014H\u0016J\b\u0010%\u001a\u00020\u001cH\u0002J\u0018\u0010&\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0014H\u0002J\u0010\u0010*\u001a\u00020\u001c2\u0006\u0010'\u001a\u00020(H\u0002J\u0018\u0010+\u001a\u00020\b2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u0014H\u0002J\u0018\u0010,\u001a\u00020\u001c2\u0006\u0010#\u001a\u00020\r2\u0006\u0010$\u001a\u00020\u0014H\u0016R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@RX\u0096\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/ScsiBlockDevice;", "Lme/jahnen/libaums/core/driver/BlockDeviceDriver;", "usbCommunication", "Lme/jahnen/libaums/core/usb/UsbCommunication;", "lun", "", "(Lme/jahnen/libaums/core/usb/UsbCommunication;B)V", "<set-?>", "", "blockSize", "getBlockSize", "()I", "blocks", "", "getBlocks", "()J", "cbwTagCounter", "csw", "Lme/jahnen/libaums/core/driver/scsi/commands/CommandStatusWrapper;", "cswBuffer", "Ljava/nio/ByteBuffer;", "lastBlockAddress", "outBuffer", "readCommand", "Lme/jahnen/libaums/core/driver/scsi/commands/ScsiRead10;", "writeCommand", "Lme/jahnen/libaums/core/driver/scsi/commands/ScsiWrite10;", "bulkOnlyMassStorageReset", "", "handleCommandResult", "", NotificationCompat.CATEGORY_STATUS, "init", "initAttempt", "read", "deviceOffset", "buffer", "requestSense", "transferCommand", "command", "Lme/jahnen/libaums/core/driver/scsi/commands/CommandBlockWrapper;", "inBuffer", "transferCommandWithoutDataPhase", "transferOneCommand", "write", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScsiBlockDevice implements BlockDeviceDriver {
    private static final int MAX_RECOVERY_ATTEMPTS = 5;
    private int blockSize;
    private int cbwTagCounter;
    private final CommandStatusWrapper csw;
    private final ByteBuffer cswBuffer;
    private int lastBlockAddress;
    private final byte lun;
    private final ByteBuffer outBuffer;
    private final ScsiRead10 readCommand;
    private final UsbCommunication usbCommunication;
    private final ScsiWrite10 writeCommand;
    private static final String TAG = "ScsiBlockDevice";

    public ScsiBlockDevice(UsbCommunication usbCommunication, byte b) {
        Intrinsics.checkNotNullParameter(usbCommunication, "usbCommunication");
        this.usbCommunication = usbCommunication;
        this.lun = b;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(31);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(31)");
        this.outBuffer = byteBufferAllocate;
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(13);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate2, "allocate(CommandStatusWrapper.SIZE)");
        this.cswBuffer = byteBufferAllocate2;
        this.writeCommand = new ScsiWrite10(b);
        this.readCommand = new ScsiRead10(b);
        this.csw = new CommandStatusWrapper();
        this.cbwTagCounter = 1;
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public int getBlockSize() {
        return this.blockSize;
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public long getBlocks() {
        return this.lastBlockAddress;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [me.jahnen.libaums.core.driver.scsi.commands.sense.NotReadyTryAgain] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6, types: [me.jahnen.libaums.core.driver.scsi.commands.sense.InitRequired] */
    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public void init() throws InterruptedException, IOException {
        Exception exc = null;
        for (int i = 0; i < 6; i++) {
            try {
                initAttempt();
                return;
            } catch (InitRequired e) {
                e = e;
                String str = TAG;
                String message = e.getMessage();
                if (message == null) {
                    message = "Reinitializing device";
                }
                Log.i(str, message);
                exc = (Exception) e;
                Thread.sleep(100L);
            } catch (NotReadyTryAgain e2) {
                e = e2;
                String str2 = TAG;
                String message2 = e.getMessage();
                if (message2 == null) {
                    message2 = "Reinitializing device";
                }
                Log.i(str2, message2);
                exc = (Exception) e;
                Thread.sleep(100L);
            }
        }
        throw new IOException("MAX_RECOVERY_ATTEMPTS Exceeded while trying to init communication with USB device, please reattach device and try again", exc);
    }

    private final void initAttempt() throws InterruptedException, IOException {
        ByteBuffer inBuffer = ByteBuffer.allocate(36);
        ScsiInquiry scsiInquiry = new ScsiInquiry((byte) inBuffer.array().length, this.lun);
        Intrinsics.checkNotNullExpressionValue(inBuffer, "inBuffer");
        transferCommand(scsiInquiry, inBuffer);
        inBuffer.clear();
        ScsiInquiryResponse scsiInquiryResponse = ScsiInquiryResponse.INSTANCE.read(inBuffer);
        String str = TAG;
        Log.d(str, "inquiry response: " + scsiInquiryResponse);
        if (scsiInquiryResponse.getPeripheralQualifier() != 0 || scsiInquiryResponse.getPeripheralDeviceType() != 0) {
            throw new IOException("unsupported PeripheralQualifier or PeripheralDeviceType");
        }
        transferCommandWithoutDataPhase(new ScsiTestUnitReady(this.lun));
        ScsiReadCapacity scsiReadCapacity = new ScsiReadCapacity(this.lun);
        inBuffer.clear();
        transferCommand(scsiReadCapacity, inBuffer);
        inBuffer.clear();
        ScsiReadCapacityResponse scsiReadCapacityResponse = ScsiReadCapacityResponse.INSTANCE.read(inBuffer);
        this.blockSize = scsiReadCapacityResponse.getBlockLength();
        this.lastBlockAddress = scsiReadCapacityResponse.getLogicalBlockAddress();
        Log.i(str, "Block size: " + getBlockSize());
        Log.i(str, "Last block address: " + this.lastBlockAddress);
    }

    private final void transferCommand(CommandBlockWrapper command, ByteBuffer inBuffer) throws InterruptedException, IOException {
        IOException iOException = null;
        for (int i = 0; i < 6; i++) {
            try {
                if (handleCommandResult(transferOneCommand(command, inBuffer)) || command.getDirection() == CommandBlockWrapper.Direction.NONE) {
                    return;
                }
                Thread.sleep(100L);
            } catch (SenseException e) {
                String str = TAG;
                String message = e.getMessage();
                if (message == null) {
                    message = "SenseException";
                }
                Log.w(str, message);
                if (e instanceof InitRequired) {
                    init();
                } else if (!(e instanceof NotReadyTryAgain)) {
                    throw e;
                }
                iOException = e;
            } catch (PipeException e2) {
                String str2 = TAG;
                StringBuilder sb = new StringBuilder();
                String message2 = e2.getMessage();
                if (message2 == null) {
                    message2 = "PipeException";
                }
                sb.append(message2);
                sb.append(", try bulk storage reset and retry");
                Log.w(str2, sb.toString());
                bulkOnlyMassStorageReset();
                iOException = e2;
            } catch (IOException e3) {
                String str3 = TAG;
                StringBuilder sb2 = new StringBuilder();
                String message3 = e3.getMessage();
                if (message3 == null) {
                    message3 = "IOException";
                }
                sb2.append(message3);
                sb2.append(", retrying...");
                Log.w(str3, sb2.toString());
                iOException = e3;
            }
        }
        throw new IOException("MAX_RECOVERY_ATTEMPTS Exceeded while trying to transfer command to device, please reattach device and try again", iOException);
    }

    private final void transferCommandWithoutDataPhase(CommandBlockWrapper command) throws InterruptedException, IOException {
        if (command.getDirection() != CommandBlockWrapper.Direction.NONE) {
            throw new IllegalArgumentException("Command has a data phase".toString());
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(0);
        Intrinsics.checkNotNullExpressionValue(byteBufferAllocate, "allocate(0)");
        transferCommand(command, byteBufferAllocate);
    }

    private final boolean handleCommandResult(int status) throws IOException {
        if (status == 0) {
            return true;
        }
        if (status == 1) {
            requestSense();
            return false;
        }
        if (status == 2) {
            bulkOnlyMassStorageReset();
            throw new IOException("phase error, please reattach device and try again");
        }
        throw new IllegalStateException("CommandStatus wrapper illegal status " + status);
    }

    private final void requestSense() throws IOException {
        ByteBuffer inBuffer = ByteBuffer.allocate(18);
        ScsiRequestSense scsiRequestSense = new ScsiRequestSense((byte) inBuffer.array().length, this.lun);
        Intrinsics.checkNotNullExpressionValue(inBuffer, "inBuffer");
        int iTransferOneCommand = transferOneCommand(scsiRequestSense, inBuffer);
        if (iTransferOneCommand == 0) {
            inBuffer.clear();
            ScsiRequestSenseResponse.INSTANCE.read(inBuffer).checkResponseForError();
        } else {
            if (iTransferOneCommand == 1) {
                throw new IOException("requesting sense failed");
            }
            if (iTransferOneCommand == 2) {
                bulkOnlyMassStorageReset();
                throw new IOException("phase error, please reattach device and try again");
            }
            throw new IllegalStateException("CommandStatus wrapper illegal status " + iTransferOneCommand);
        }
    }

    private final void bulkOnlyMassStorageReset() throws IOException {
        String str = TAG;
        Log.w(str, "sending bulk only mass storage request");
        UsbCommunication usbCommunication = this.usbCommunication;
        if (usbCommunication.controlTransfer(33, 255, 0, usbCommunication.getUsbInterface().getId(), new byte[2], 0) == -1) {
            throw new IOException("bulk only mass storage reset failed!");
        }
        Log.d(str, "Trying to clear halt on both endpoints");
        UsbCommunication usbCommunication2 = this.usbCommunication;
        usbCommunication2.clearFeatureHalt(usbCommunication2.getInEndpoint());
        UsbCommunication usbCommunication3 = this.usbCommunication;
        usbCommunication3.clearFeatureHalt(usbCommunication3.getOutEndpoint());
    }

    private final int transferOneCommand(CommandBlockWrapper command, ByteBuffer inBuffer) throws IOException {
        byte[] bArrArray = this.outBuffer.array();
        int iBulkOutTransfer = 0;
        Arrays.fill(bArrArray, (byte) 0);
        command.setDCbwTag(this.cbwTagCounter);
        this.cbwTagCounter++;
        this.outBuffer.clear();
        command.serialize(this.outBuffer);
        this.outBuffer.clear();
        if (this.usbCommunication.bulkOutTransfer(this.outBuffer) != bArrArray.length) {
            throw new IOException("Writing all bytes on command " + command + " failed!");
        }
        int dCbwDataTransferLength = command.getDCbwDataTransferLength();
        inBuffer.limit(inBuffer.position() + dCbwDataTransferLength);
        if (dCbwDataTransferLength > 0) {
            if (command.getDirection() == CommandBlockWrapper.Direction.IN) {
                do {
                    iBulkOutTransfer += this.usbCommunication.bulkInTransfer(inBuffer);
                    if (command.getBCbwDynamicSize()) {
                        dCbwDataTransferLength = command.dynamicSizeFromPartialResponse(inBuffer);
                        inBuffer.limit(inBuffer.position() + dCbwDataTransferLength);
                    }
                } while (iBulkOutTransfer < dCbwDataTransferLength);
                if (iBulkOutTransfer != dCbwDataTransferLength) {
                    throw new IOException("Unexpected command size (" + iBulkOutTransfer + ") on response to " + command);
                }
            } else {
                do {
                    iBulkOutTransfer += this.usbCommunication.bulkOutTransfer(inBuffer);
                } while (iBulkOutTransfer < dCbwDataTransferLength);
                if (iBulkOutTransfer != dCbwDataTransferLength) {
                    throw new IOException("Could not write all bytes: " + command);
                }
            }
        }
        this.cswBuffer.clear();
        if (this.usbCommunication.bulkInTransfer(this.cswBuffer) != 13) {
            throw new IOException("Unexpected command size while expecting csw");
        }
        this.cswBuffer.clear();
        this.csw.read(this.cswBuffer);
        if (this.csw.getDCswTag() != command.getDCbwTag()) {
            throw new IOException("wrong csw tag!");
        }
        return this.csw.getBCswStatus();
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public synchronized void read(long deviceOffset, ByteBuffer buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (buffer.remaining() % getBlockSize() != 0) {
            throw new IllegalArgumentException("buffer.remaining() must be multiple of blockSize!".toString());
        }
        this.readCommand.init((int) deviceOffset, buffer.remaining(), getBlockSize());
        transferCommand(this.readCommand, buffer);
        buffer.position(buffer.limit());
    }

    @Override // me.jahnen.libaums.core.driver.BlockDeviceDriver
    public synchronized void write(long deviceOffset, ByteBuffer buffer) throws IOException {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (buffer.remaining() % getBlockSize() != 0) {
            throw new IllegalArgumentException("buffer.remaining() must be multiple of blockSize!".toString());
        }
        this.writeCommand.init((int) deviceOffset, buffer.remaining(), getBlockSize());
        transferCommand(this.writeCommand, buffer);
        buffer.position(buffer.limit());
    }
}
