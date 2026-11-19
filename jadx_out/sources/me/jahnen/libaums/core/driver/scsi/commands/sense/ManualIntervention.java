package me.jahnen.libaums.core.driver.scsi.commands.sense;

import androidx.core.app.NotificationCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SenseException.kt */
/* JADX INFO: loaded from: classes6.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"Lme/jahnen/libaums/core/driver/scsi/commands/sense/ManualIntervention;", "Lme/jahnen/libaums/core/driver/scsi/commands/sense/SenseException;", "response", "Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;", NotificationCompat.CATEGORY_MESSAGE, "", "(Lme/jahnen/libaums/core/driver/scsi/commands/sense/ScsiRequestSenseResponse;Ljava/lang/String;)V", "libaums_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class ManualIntervention extends SenseException {
    public /* synthetic */ ManualIntervention(ScsiRequestSenseResponse scsiRequestSenseResponse, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(scsiRequestSenseResponse, (i & 2) != 0 ? "Manual intervention required" : str);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ManualIntervention(ScsiRequestSenseResponse scsiRequestSenseResponse, String msg) {
        super(scsiRequestSenseResponse, msg);
        Intrinsics.checkNotNullParameter(msg, "msg");
    }
}
