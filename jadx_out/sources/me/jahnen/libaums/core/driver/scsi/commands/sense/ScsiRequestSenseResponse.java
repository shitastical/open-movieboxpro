package me.jahnen.libaums.core.driver.scsi.commands.sense;

import androidx.media3.extractor.text.ttml.TtmlNode;
import com.google.common.base.Ascii;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ScsiRequestSenseResponse.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u001c\u001a\u00020\u001dJ\b\u0010\u001e\u001a\u00020\u001dH\u0002J\b\u0010\u001f\u001a\u00020\u001dH\u0002R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u001e\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0007R\u001e\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001e\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0003\u001a\u00020\u0014@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001e\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0007R\u001e\u0010\u001a\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0007¨\u0006!"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;", "", "()V", "<set-?>", "", "additionalSenseCode", "getAdditionalSenseCode", "()B", "additionalSenseCodeQualifier", "getAdditionalSenseCodeQualifier", "additionalSenseLength", "getAdditionalSenseLength", "", "commandSpecificInformation", "getCommandSpecificInformation", "()I", "errorCode", "getErrorCode", TtmlNode.TAG_INFORMATION, "getInformation", "", "informationValid", "getInformationValid", "()Z", "segmentNumber", "getSegmentNumber", "senseKey", "getSenseKey", "checkResponseForError", "", "handleMediumError", "handleNotReady", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ScsiRequestSenseResponse {
    public static final int ABORTED = 11;
    public static final int BLANK_CHECK = 8;
    public static final int COMPLETED = 15;
    public static final int COPY_ABORTED = 10;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int DATA_PROTECT = 7;
    public static final int HARDWARE_ERROR = 4;
    public static final int ILLEGAL_REQUEST = 5;
    public static final int MEDIUM_ERROR = 3;
    public static final int MISCOMPARE = 14;
    public static final int NOT_READY = 2;
    public static final int NO_SENSE = 0;
    public static final int RECOVERED_ERROR = 1;
    public static final int UNIT_ATTENTION = 6;
    public static final int VOLUME_OVERFLOW = 13;
    private byte additionalSenseCode;
    private byte additionalSenseCodeQualifier;
    private byte additionalSenseLength;
    private int commandSpecificInformation;
    private byte errorCode;
    private int information;
    private boolean informationValid;
    private byte segmentNumber;
    private byte senseKey;

    public /* synthetic */ ScsiRequestSenseResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private ScsiRequestSenseResponse() {
    }

    public final boolean getInformationValid() {
        return this.informationValid;
    }

    public final byte getErrorCode() {
        return this.errorCode;
    }

    public final byte getSegmentNumber() {
        return this.segmentNumber;
    }

    public final byte getSenseKey() {
        return this.senseKey;
    }

    public final int getInformation() {
        return this.information;
    }

    public final byte getAdditionalSenseLength() {
        return this.additionalSenseLength;
    }

    public final int getCommandSpecificInformation() {
        return this.commandSpecificInformation;
    }

    public final byte getAdditionalSenseCode() {
        return this.additionalSenseCode;
    }

    public final byte getAdditionalSenseCodeQualifier() {
        return this.additionalSenseCodeQualifier;
    }

    public final void checkResponseForError() throws SenseException {
        switch (this.senseKey) {
            case 2:
                handleNotReady();
                break;
            case 3:
                handleMediumError();
                break;
            case 4:
                throw new HardwareError(this);
            case 5:
                throw new IllegalCommand(this);
            case 6:
                throw new UnitAttention(this);
            case 7:
                throw new DataProtect(this);
            case 8:
                throw new BlankCheck(this);
            case 10:
                throw new CopyAborted(this);
            case 11:
                throw new Aborted(this);
            case 13:
                throw new VolumeOverflow(this);
            case 14:
                throw new Miscompare(this);
        }
        throw new SenseException(this, "Sense exception: " + ((int) this.senseKey));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void handleNotReady() throws ManualIntervention, NotReady, RestartRequired, MediaNotInserted {
        byte b = this.additionalSenseCode;
        int i = 2;
        String str = null;
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        Object[] objArr3 = 0;
        if (b == 4) {
            byte b2 = this.additionalSenseCodeQualifier;
            if (b2 == 1) {
                throw new NotReadyTryAgain(this);
            }
            if (b2 == 7) {
                throw new NotReadyTryAgain(this);
            }
            if (b2 == 9) {
                throw new NotReadyTryAgain(this);
            }
            if (b2 == 18) {
                throw new NotReady(this, "Not ready; logical unit offline");
            }
            if (b2 == 34) {
                throw new RestartRequired(this);
            }
            if (b2 == 3) {
                throw new ManualIntervention(this, objArr2 == true ? 1 : 0, i, objArr == true ? 1 : 0);
            }
            if (b2 == 4) {
                throw new NotReadyTryAgain(this);
            }
        } else if (b == 58) {
            throw new MediaNotInserted(this);
        }
        throw new NotReady(this, str, i, objArr3 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void handleMediumError() throws MediumError {
        byte b = this.additionalSenseCode;
        if (b == 12) {
            throw new MediumError(this, "Write error");
        }
        if (b == 17) {
            throw new MediumError(this, "Read error");
        }
        if (b == 49) {
            throw new MediumError(this, "Storage medium corrupted");
        }
        throw new MediumError(this, null, 2, 0 == true ? 1 : 0);
    }

    /* JADX INFO: compiled from: ScsiRequestSenseResponse.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse$Companion;", "", "()V", "ABORTED", "", "BLANK_CHECK", "COMPLETED", "COPY_ABORTED", "DATA_PROTECT", "HARDWARE_ERROR", "ILLEGAL_REQUEST", "MEDIUM_ERROR", "MISCOMPARE", "NOT_READY", "NO_SENSE", "RECOVERED_ERROR", "UNIT_ATTENTION", "VOLUME_OVERFLOW", "read", "Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;", "buffer", "Ljava/nio/ByteBuffer;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ScsiRequestSenseResponse read(ByteBuffer buffer) {
            Intrinsics.checkNotNullParameter(buffer, "buffer");
            buffer.order(ByteOrder.BIG_ENDIAN);
            ScsiRequestSenseResponse scsiRequestSenseResponse = new ScsiRequestSenseResponse(null);
            scsiRequestSenseResponse.errorCode = buffer.get();
            scsiRequestSenseResponse.segmentNumber = buffer.get();
            scsiRequestSenseResponse.senseKey = buffer.get();
            scsiRequestSenseResponse.information = buffer.getInt();
            scsiRequestSenseResponse.additionalSenseLength = buffer.get();
            scsiRequestSenseResponse.commandSpecificInformation = buffer.getInt();
            scsiRequestSenseResponse.additionalSenseCode = buffer.get();
            scsiRequestSenseResponse.additionalSenseCodeQualifier = buffer.get();
            scsiRequestSenseResponse.informationValid = (scsiRequestSenseResponse.getErrorCode() & 128) > 0;
            scsiRequestSenseResponse.errorCode = (byte) (scsiRequestSenseResponse.getErrorCode() & 127);
            scsiRequestSenseResponse.senseKey = (byte) (scsiRequestSenseResponse.getSenseKey() & Ascii.SI);
            return scsiRequestSenseResponse;
        }
    }
}
