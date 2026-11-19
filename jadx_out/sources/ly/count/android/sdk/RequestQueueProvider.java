package ly.count.android.sdk;

import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
interface RequestQueueProvider {
    void beginSession(boolean z, String str, String str2, String str3, String str4, String str5);

    void changeDeviceId(String str, int i);

    ConnectionProcessor createConnectionProcessor();

    void endSession(int i);

    void endSession(int i, String str);

    String prepareEnrollVariant(String str, String str2);

    String prepareEnrollmentParameters(String[] strArr);

    String prepareFeedbackListRequest();

    String prepareFetchAllVariants();

    String prepareHealthCheckRequest(String str);

    String prepareRatingWidgetRequest(String str);

    String prepareRemoteConfigRequest(String str, String str2, String str3, boolean z);

    String prepareRemoteConfigRequestLegacy(String str, String str2, String str3);

    String prepareRemovalParameters(String[] strArr);

    String prepareServerConfigRequest();

    boolean queueContainsTemporaryIdItems();

    void recordEvents(String str);

    void sendAPMAppStart(long j, Long l, Long l2);

    void sendAPMCustomTrace(String str, Long l, Long l2, Long l3, String str2);

    void sendAPMNetworkTrace(String str, Long l, int i, int i2, int i3, Long l2, Long l3);

    void sendAPMScreenTime(boolean z, long j, Long l, Long l2);

    void sendConsentChanges(String str);

    void sendCrashReport(String str, boolean z);

    void sendDirectAttributionLegacy(String str, String str2);

    void sendDirectAttributionTest(String str);

    void sendDirectRequest(Map<String, String> map);

    void sendIndirectAttribution(String str);

    void sendLocation(boolean z, String str, String str2, String str3, String str4);

    void sendUserData(String str);

    void tick();

    void tokenSession(String str, Countly.CountlyMessagingProvider countlyMessagingProvider);

    void updateSession(int i);
}
