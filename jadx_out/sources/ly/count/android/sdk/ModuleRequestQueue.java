package ly.count.android.sdk;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleRequestQueue extends ModuleBase implements BaseInfoProvider {
    static final String APP_KEY_KEY = "app_key";
    static final String CHECKSUM_256_KEY = "checksum256";
    static final String CHECKSUM_KEY = "checksum";
    static final String DEVICE_ID_KEY = "device_id";
    static final String DOW_KEY = "dow";
    static final String HOUR_KEY = "hour";
    static final String OLD_DEVICE_ID_KEY = "old_device_id";
    static final String OVVERIDE_KEY = "override_id";
    static final String SDK_NAME_KEY = "sdk_name";
    static final String SDK_VERSION_KEY = "sdk_version";
    static final String TZ_KEY = "tz";
    private final List<String> appCrawlerNames;
    String appKey;
    private boolean deviceIsAppCrawler;
    String[] preDefinedKeys;
    RequestQueue requestQueueInterface;
    String serverURL;
    private boolean shouldIgnoreCrawlers;

    ModuleRequestQueue(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.shouldIgnoreCrawlers = true;
        this.deviceIsAppCrawler = false;
        ArrayList arrayList = new ArrayList(Arrays.asList("Calypso AppCrawler"));
        this.appCrawlerNames = arrayList;
        this.preDefinedKeys = new String[]{APP_KEY_KEY, HOUR_KEY, DOW_KEY, TZ_KEY, SDK_VERSION_KEY, SDK_NAME_KEY, DEVICE_ID_KEY, OVVERIDE_KEY, OLD_DEVICE_ID_KEY, CHECKSUM_KEY, CHECKSUM_256_KEY};
        this.L.v("[ModuleRequestQueue] Initialising");
        countlyConfig.baseInfoProvider = this;
        this.baseInfoProvider = this;
        this.appKey = countlyConfig.appKey;
        this.serverURL = countlyConfig.serverURL;
        if (countlyConfig.shouldIgnoreAppCrawlers) {
            this.L.d("[ModuleRequestQueue] Ignoring app crawlers");
            this.shouldIgnoreCrawlers = countlyConfig.shouldIgnoreAppCrawlers;
        }
        if (countlyConfig.appCrawlerNames != null) {
            this.L.d("[ModuleRequestQueue] Adding app crawlers names");
            arrayList.addAll(Arrays.asList(countlyConfig.appCrawlerNames));
        }
        checkIfDeviceIsAppCrawler();
        this.requestQueueInterface = new RequestQueue();
    }

    @Override // ly.count.android.sdk.BaseInfoProvider
    public String getAppKey() {
        return this.appKey;
    }

    @Override // ly.count.android.sdk.BaseInfoProvider
    public String getServerURL() {
        return this.serverURL;
    }

    synchronized List<String> requestQueueReplaceWithAppKey(String[] strArr, String str) {
        try {
            ArrayList arrayList = new ArrayList();
            if (strArr != null && str != null) {
                String str2 = "app_key=" + UtilsNetworking.urlEncodeString(str);
                for (String str3 : strArr) {
                    if (str3 != null) {
                        String[] strArrSplit = str3.split("&");
                        for (int i = 0; i < strArrSplit.length; i++) {
                            if (strArrSplit[i].contains("app_key=")) {
                                strArrSplit[i] = str2;
                                break;
                            }
                        }
                        StringBuilder sb = new StringBuilder(str3.length());
                        for (int i2 = 0; i2 < strArrSplit.length; i2++) {
                            if (i2 != 0) {
                                sb.append("&");
                            }
                            sb.append(strArrSplit[i2]);
                        }
                        arrayList.add(sb.toString());
                    }
                }
                return arrayList;
            }
            return arrayList;
        } catch (Exception e) {
            this.L.e("[ModuleRequestQueue] Failed while overwriting appKeys, " + e.toString());
            return null;
        }
    }

    synchronized List<String> requestQueueRemoveWithoutAppKey(String[] strArr, String str) {
        ArrayList arrayList = new ArrayList();
        if (strArr != null && str != null) {
            String str2 = "app_key=" + str;
            for (String str3 : strArr) {
                if (str3 != null) {
                    if (!str3.contains(str2)) {
                        this.L.d("[ModuleRequestQueue] requestQueueEraseAppKeysRequests, Found a entry to remove: [" + str3 + "]");
                    } else {
                        arrayList.add(str3);
                    }
                }
            }
            return arrayList;
        }
        return arrayList;
    }

    protected void sendEventsIfNeeded(boolean z) {
        int eventQueueSize = this.storageProvider.getEventQueueSize();
        this.L.v("[ModuleRequestQueue] forceSendingEvents, forced:[" + z + "], event count:[" + eventQueueSize + "]");
        if ((!z || eventQueueSize <= 0) && eventQueueSize < Countly.EVENT_QUEUE_SIZE_THRESHOLD) {
            return;
        }
        this.requestQueueProvider.recordEvents(this.storageProvider.getEventsForRequestAndEmptyEventQueue());
    }

    boolean isHttpPostForcedInternal() {
        return this._cly.isHttpPostForced;
    }

    boolean isDeviceAppCrawlerInternal() {
        return this.deviceIsAppCrawler;
    }

    boolean ifShouldIgnoreCrawlersInternal() {
        return this.shouldIgnoreCrawlers;
    }

    private void checkIfDeviceIsAppCrawler() {
        String device = this.deviceInfo.mp.getDevice();
        for (int i = 0; i < this.appCrawlerNames.size(); i++) {
            if (device.equals(this.appCrawlerNames.get(i))) {
                this.deviceIsAppCrawler = true;
                return;
            }
        }
    }

    public void flushQueuesInternal() {
        CountlyStore countlyStore = this._cly.countlyStore;
        int i = 0;
        while (true) {
            String[] requests = countlyStore.getRequests();
            if (requests == null || requests.length == 0) {
                break;
            }
            countlyStore.removeRequest(requests[0]);
            i++;
        }
        this.L.d("[ModuleRequestQueue] flushRequestQueues removed [" + i + "] requests");
    }

    public void attemptToSendStoredRequestsInternal() {
        this.L.i("[ModuleRequestQueue] Calling attemptToSendStoredRequests");
        sendEventsIfNeeded(true);
        this.requestQueueProvider.tick();
    }

    public synchronized void requestQueueOverwriteAppKeysInternal() {
        this.L.i("[ModuleRequestQueue] Calling requestQueueOverwriteAppKeys");
        List<String> listRequestQueueReplaceWithAppKey = requestQueueReplaceWithAppKey(this.storageProvider.getRequests(), this.baseInfoProvider.getAppKey());
        if (listRequestQueueReplaceWithAppKey != null) {
            this.storageProvider.replaceRequestList(listRequestQueueReplaceWithAppKey);
            attemptToSendStoredRequestsInternal();
        }
    }

    public synchronized void requestQueueEraseAppKeysRequestsInternal() {
        this.L.i("[ModuleRequestQueue] Calling requestQueueEraseAppKeysRequests");
        this.storageProvider.replaceRequestList(requestQueueRemoveWithoutAppKey(this.storageProvider.getRequests(), this.baseInfoProvider.getAppKey()));
        attemptToSendStoredRequestsInternal();
    }

    public synchronized void addDirectRequestInternal(Map<String, String> map) {
        this.L.i("[ModuleRequestQueue] Calling addDirectRequestInternal");
        if (!this._cly.isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before adding direct request, returning");
            return;
        }
        if (!this.consentProvider.anyConsentGiven()) {
            this.L.e("[ModuleRequestQueue] addDirectRequest, no consent is given, returning");
            return;
        }
        if (map != null && !map.isEmpty()) {
            HashMap map2 = new HashMap();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                String[] strArr = this.preDefinedKeys;
                int length = strArr.length;
                int i = 0;
                while (true) {
                    if (i < length) {
                        if (strArr[i].equals(key)) {
                            this.L.w("[ModuleRequestQueue] addDirectRequest, removing provided key: [" + key + "] is a restricted key.");
                            break;
                        }
                        i++;
                    } else {
                        map2.put(key, value.toString());
                        break;
                    }
                }
            }
            if (map2.isEmpty()) {
                this.L.e("[ModuleRequestQueue] addDirectRequest, filteredRequestMap was null or empty, returning");
                return;
            }
            int size = map.size() - map2.size();
            if (size > 0) {
                this.L.w("[ModuleRequestQueue] addDirectRequest, [" + size + "] restricted keys are removed");
            }
            this.requestQueueProvider.sendDirectRequest(map2);
            return;
        }
        this.L.e("[ModuleRequestQueue] addDirectRequest, provided requestMap was null or empty, returning");
    }

    void esWriteCachesToPersistenceInternal(ExplicitStorageCallback explicitStorageCallback) {
        this.L.i("[ModuleRequestQueue] Calling esWriteCachesToPersistenceInternal");
        this.storageProvider.esWriteCacheToStorage(explicitStorageCallback);
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        this.requestQueueInterface = null;
    }

    public class RequestQueue {
        public RequestQueue() {
        }

        public boolean isHttpPostForced() {
            boolean zIsHttpPostForcedInternal;
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.v("[RequestQueue] Calling 'isHttpPostForced'");
                zIsHttpPostForcedInternal = ModuleRequestQueue.this.isHttpPostForcedInternal();
            }
            return zIsHttpPostForcedInternal;
        }

        public boolean isDeviceAppCrawler() {
            boolean zIsDeviceAppCrawlerInternal;
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.v("[RequestQueue] Calling 'isDeviceAppCrawler'");
                zIsDeviceAppCrawlerInternal = ModuleRequestQueue.this.isDeviceAppCrawlerInternal();
            }
            return zIsDeviceAppCrawlerInternal;
        }

        public boolean ifShouldIgnoreCrawlers() {
            boolean zIfShouldIgnoreCrawlersInternal;
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.v("[RequestQueue] Calling 'ifShouldIgnoreCrawlers'");
                zIfShouldIgnoreCrawlersInternal = ModuleRequestQueue.this.ifShouldIgnoreCrawlersInternal();
            }
            return zIfShouldIgnoreCrawlersInternal;
        }

        public void flushQueues() {
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.v("[RequestQueue] Calling 'flushQueues'");
                ModuleRequestQueue.this.flushQueuesInternal();
            }
        }

        public void attemptToSendStoredRequests() {
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.v("[RequestQueue] Calling 'attemptToSendStoredRequestsInternal'");
                ModuleRequestQueue.this.attemptToSendStoredRequestsInternal();
            }
        }

        public void overwriteAppKeys() {
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.i("[Countly] Calling overwriteAppKeys");
                ModuleRequestQueue.this.requestQueueOverwriteAppKeysInternal();
            }
        }

        public void eraseWrongAppKeyRequests() {
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.i("[Countly] Calling eraseWrongAppKeyRequests");
                ModuleRequestQueue.this.requestQueueEraseAppKeysRequestsInternal();
            }
        }

        public void addDirectRequest(Map<String, String> map) {
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.i("[Countly] Calling addDirectRequest");
                ModuleRequestQueue.this.addDirectRequestInternal(map);
            }
        }

        public void esWriteCachesToPersistence() {
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.i("[Countly] Calling esWriteCachesToStorage");
                ModuleRequestQueue.this.esWriteCachesToPersistenceInternal(null);
            }
        }

        public void esWriteCachesToPersistence(ExplicitStorageCallback explicitStorageCallback) {
            synchronized (ModuleRequestQueue.this._cly) {
                ModuleRequestQueue.this.L.i("[Countly] Calling esWriteCachesToStorage");
                ModuleRequestQueue.this.esWriteCachesToPersistenceInternal(explicitStorageCallback);
            }
        }
    }
}
