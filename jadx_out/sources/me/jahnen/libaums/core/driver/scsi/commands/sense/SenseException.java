package me.jahnen.libaums.core.driver.scsi.commands.sense;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SenseException.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\b\b\b\u0016\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\r\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u0010"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/sense/SenseException;", "Ljava/io/IOException;", "response", "Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;", NotificationCompat.CATEGORY_MESSAGE, "", "(Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;Ljava/lang/String;)V", "additionalSenseCode", "", "getAdditionalSenseCode", "()B", "additionalSenseCodeQualifier", "getAdditionalSenseCodeQualifier", "senseKey", "getSenseKey", "Companion", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public class SenseException extends IOException {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final byte additionalSenseCode;
    private final byte additionalSenseCodeQualifier;
    private final byte senseKey;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SenseException(ScsiRequestSenseResponse scsiRequestSenseResponse, String msg) {
        super(msg + INSTANCE.responseToString(scsiRequestSenseResponse));
        Intrinsics.checkNotNullParameter(msg, "msg");
        this.senseKey = scsiRequestSenseResponse != null ? scsiRequestSenseResponse.getSenseKey() : (byte) 0;
        this.additionalSenseCode = scsiRequestSenseResponse != null ? scsiRequestSenseResponse.getAdditionalSenseCode() : (byte) 0;
        this.additionalSenseCodeQualifier = scsiRequestSenseResponse != null ? scsiRequestSenseResponse.getAdditionalSenseCodeQualifier() : (byte) 0;
    }

    public final byte getSenseKey() {
        return this.senseKey;
    }

    public final byte getAdditionalSenseCode() {
        return this.additionalSenseCode;
    }

    public final byte getAdditionalSenseCodeQualifier() {
        return this.additionalSenseCodeQualifier;
    }

    /* JADX INFO: compiled from: SenseException.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002¨\u0006\u0007"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/sense/SenseException$Companion;", "", "()V", "responseToString", "", "response", "Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String responseToString(ScsiRequestSenseResponse response) {
            if (response == null) {
                return "";
            }
            return " (ASC: " + ((int) response.getAdditionalSenseCode()) + ", ASCQ: " + ((int) response.getAdditionalSenseCodeQualifier()) + ')';
        }
    }
}
