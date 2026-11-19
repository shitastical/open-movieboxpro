package ly.count.android.sdk;

import java.util.Collection;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
interface StorageProvider {
    void addRequest(String str, boolean z);

    boolean anythingSetInStorage();

    void esWriteCacheToStorage(ExplicitStorageCallback explicitStorageCallback);

    String getCachedAdvertisingId();

    int getDataSchemaVersion();

    String getDeviceID();

    String getDeviceIDType();

    List<Event> getEventList();

    int getEventQueueSize();

    String[] getEvents();

    String getEventsForRequestAndEmptyEventQueue();

    String getHealthCheckCounterState();

    String getRemoteConfigValues();

    String getRequestQueueRaw();

    String[] getRequests();

    String getServerConfig();

    String getStarRatingPreferences();

    void removeEvents(Collection<Event> collection);

    void removeRequest(String str);

    void replaceRequestList(List<String> list);

    void replaceRequests(String[] strArr);

    void setCachedAdvertisingId(String str);

    void setDataSchemaVersion(int i);

    void setDeviceID(String str);

    void setDeviceIDType(String str);

    void setHealthCheckCounterState(String str);

    void setRemoteConfigValues(String str);

    void setServerConfig(String str);

    void setStarRatingPreferences(String str);
}
