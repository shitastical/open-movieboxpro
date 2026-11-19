package ly.count.android.sdk;

/* JADX INFO: loaded from: classes6.dex */
interface HealthTracker {
    void clearAndSave();

    void logError();

    void logFailedNetworkRequest(int i, String str);

    void logWarning();

    void saveState();
}
