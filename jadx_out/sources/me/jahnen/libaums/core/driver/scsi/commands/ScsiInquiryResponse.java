package me.jahnen.libaums.core.driver.scsi.commands;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScsiInquiryResponse.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0016\u001a\u00020\u0017H\u0016R$\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001e\u0010\n\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001e\u0010\r\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR$\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0003\u001a\u00020\t@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\f\"\u0004\b\u0015\u0010\u0012¨\u0006\u0019"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/ScsiInquiryResponse;", "", "()V", "<set-?>", "", "isRemovableMedia", "()Z", "setRemovableMedia$libaums_release", "(Z)V", "", "peripheralDeviceType", "getPeripheralDeviceType", "()B", "peripheralQualifier", "getPeripheralQualifier", "responseDataFormat", "getResponseDataFormat", "setResponseDataFormat$libaums_release", "(B)V", "spcVersion", "getSpcVersion", "setSpcVersion$libaums_release", "toString", "", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScsiInquiryResponse {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean isRemovableMedia;
    private byte peripheralDeviceType;
    private byte peripheralQualifier;
    private byte responseDataFormat;
    private byte spcVersion;

    public /* synthetic */ ScsiInquiryResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ScsiInquiryResponse() {
    }

    public final byte getPeripheralQualifier() {
        return this.peripheralQualifier;
    }

    public final byte getPeripheralDeviceType() {
        return this.peripheralDeviceType;
    }

    /* JADX INFO: renamed from: isRemovableMedia, reason: from getter */
    public final boolean getIsRemovableMedia() {
        return this.isRemovableMedia;
    }

    public final void setRemovableMedia$libaums_release(boolean z) {
        this.isRemovableMedia = z;
    }

    public final byte getSpcVersion() {
        return this.spcVersion;
    }

    public final void setSpcVersion$libaums_release(byte b) {
        this.spcVersion = b;
    }

    public final byte getResponseDataFormat() {
        return this.responseDataFormat;
    }

    public final void setResponseDataFormat$libaums_release(byte b) {
        this.responseDataFormat = b;
    }

    public String toString() {
        return "ScsiInquiryResponse [peripheralQualifier=" + ((int) this.peripheralQualifier) + ", peripheralDeviceType=" + ((int) this.peripheralDeviceType) + ", removableMedia=" + this.isRemovableMedia + ", spcVersion=" + ((int) this.spcVersion) + ", responseDataFormat=" + ((int) this.responseDataFormat) + ']';
    }

    /* JADX INFO: compiled from: ScsiInquiryResponse.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/ScsiInquiryResponse$Companion;", "", "()V", "read", "Lme/jahnen/libaums/core/driver/scsi/commands/ScsiInquiryResponse;", "buffer", "Ljava/nio/ByteBuffer;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScsiInquiryResponse read(ByteBuffer buffer) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            buffer.order(ByteOrder.LITTLE_ENDIAN);
            byte b = buffer.get();
            ScsiInquiryResponse scsiInquiryResponse = new ScsiInquiryResponse(null);
            scsiInquiryResponse.peripheralQualifier = (byte) (b & (-32));
            scsiInquiryResponse.peripheralDeviceType = (byte) (b & 31);
            scsiInquiryResponse.setRemovableMedia$libaums_release(buffer.get() == 128);
            scsiInquiryResponse.setSpcVersion$libaums_release(buffer.get());
            scsiInquiryResponse.setResponseDataFormat$libaums_release((byte) (buffer.get() & 7));
            return scsiInquiryResponse;
        }
    }
}
