package ly.count.android.sdk;

import android.content.Context;
import android.content.SharedPreferences;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class CountlyStore implements StorageProvider, EventQueueProvider {
    private static final String CACHED_ADVERTISING_ID = "ADVERTISING_ID";
    private static final String CACHED_PUSH_ACTION_ID = "PUSH_ACTION_ID";
    private static final String CACHED_PUSH_ACTION_INDEX = "PUSH_ACTION_INDEX";
    private static final String CACHED_PUSH_MESSAGING_PROVIDER = "PUSH_MESSAGING_PROVIDER";
    private static final String CONSENT_GCM_PREFERENCES = "ly.count.android.api.messaging.consent.gcm";
    static final String DELIMITER = ":::";
    private static final String EVENTS_PREFERENCE = "EVENTS";
    private static final int MAX_EVENTS = 1000;
    private static final String PREFERENCES = "COUNTLY_STORE";
    private static final String PREFERENCES_PUSH = "ly.count.android.api.messaging";
    private static final String PREFERENCE_HEALTH_CHECK_STATE = "HEALTH_CHECK";
    private static final String PREFERENCE_KEY_ID_ID = "ly.count.android.api.DeviceId.id";
    private static final String PREFERENCE_KEY_ID_TYPE = "ly.count.android.api.DeviceId.type";
    private static final String PREFERENCE_SERVER_CONFIG = "SERVER_CONFIG";
    private static final String REMOTE_CONFIG_VALUES = "REMOTE_CONFIG";
    private static final String REQUEST_PREFERENCE = "CONNECTIONS";
    private static final String STAR_RATING_PREFERENCE = "STAR_RATING";
    private static final String STORAGE_SCHEMA_VERSION = "SCHEMA_VERSION";
    ModuleLog L;
    ConfigurationProvider configurationProvider;
    boolean esDirtyFlag;
    String esEventQueueCache;
    String esHealthCheckCache;
    String esRequestQueueCache;
    boolean explicitStorageModeEnabled;
    int maxRequestQueueSize;
    private final SharedPreferences preferencesPush_;
    private final SharedPreferences preferences_;

    public CountlyStore(Context context, ModuleLog moduleLog) {
        this(context, moduleLog, false);
    }

    public CountlyStore(Context context, ModuleLog moduleLog, boolean z) {
        this.maxRequestQueueSize = 1000;
        this.esDirtyFlag = false;
        this.esRequestQueueCache = null;
        this.esEventQueueCache = null;
        this.esHealthCheckCache = null;
        if (context == null) {
            throw new IllegalArgumentException("must provide valid context");
        }
        this.explicitStorageModeEnabled = z;
        this.preferences_ = context.getSharedPreferences(PREFERENCES, 0);
        this.preferencesPush_ = createPreferencesPush(context);
        this.L = moduleLog;
    }

    public void setLimits(int i) {
        this.maxRequestQueueSize = i;
    }

    public void setConfigurationProvider(ConfigurationProvider configurationProvider) {
        this.configurationProvider = configurationProvider;
    }

    static SharedPreferences createPreferencesPush(Context context) {
        return context.getSharedPreferences(PREFERENCES_PUSH, 0);
    }

    private String storageReadRequestQueue() {
        if (this.explicitStorageModeEnabled) {
            if (this.esRequestQueueCache == null) {
                this.L.v("[CountlyStore] Reading initial RQ from storage");
                this.esRequestQueueCache = this.preferences_.getString(REQUEST_PREFERENCE, "");
            }
            return this.esRequestQueueCache;
        }
        return this.preferences_.getString(REQUEST_PREFERENCE, "");
    }

    private void storageWriteRequestQueue(String str, boolean z) {
        if (this.explicitStorageModeEnabled) {
            this.esRequestQueueCache = str;
            this.esDirtyFlag = true;
            return;
        }
        SharedPreferences.Editor editorPutString = this.preferences_.edit().putString(REQUEST_PREFERENCE, str);
        if (z) {
            editorPutString.commit();
        } else {
            editorPutString.apply();
        }
    }

    private String storageReadEventQueue() {
        if (this.explicitStorageModeEnabled) {
            if (this.esEventQueueCache == null) {
                this.L.v("[CountlyStore] Reading initial EQ from storage");
                this.esEventQueueCache = this.preferences_.getString(EVENTS_PREFERENCE, "");
            }
            return this.esEventQueueCache;
        }
        return this.preferences_.getString(EVENTS_PREFERENCE, "");
    }

    private void storageWriteEventQueue(String str, boolean z) {
        if (this.explicitStorageModeEnabled) {
            this.L.v("[CountlyStore] Writing EQ to cache");
            this.esEventQueueCache = str;
            this.esDirtyFlag = true;
        } else {
            this.L.v("[CountlyStore] Writing EQ to preferences");
            SharedPreferences.Editor editorPutString = this.preferences_.edit().putString(EVENTS_PREFERENCE, str);
            if (z) {
                editorPutString.commit();
            } else {
                editorPutString.apply();
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0054  */
    /* JADX WARN: Code duplicated, block: B:24:0x0091  */
    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void esWriteCacheToStorage(ExplicitStorageCallback explicitStorageCallback) {
        boolean z;
        this.L.v("[CountlyStore] Trying to write ES cache to storage[" + this.explicitStorageModeEnabled + "], is dirty flag:[" + this.esDirtyFlag + "]");
        if (this.explicitStorageModeEnabled) {
            if (this.esDirtyFlag) {
                SharedPreferences.Editor editorEdit = this.preferences_.edit();
                boolean z2 = true;
                if (this.esRequestQueueCache != null) {
                    if (this.esRequestQueueCache.equals(this.preferences_.getString(REQUEST_PREFERENCE, ""))) {
                        z = false;
                    } else {
                        editorEdit.putString(REQUEST_PREFERENCE, this.esRequestQueueCache);
                        z = true;
                    }
                } else {
                    z = false;
                }
                if (this.esEventQueueCache != null) {
                    if (!this.esEventQueueCache.equals(this.preferences_.getString(EVENTS_PREFERENCE, ""))) {
                        editorEdit.putString(EVENTS_PREFERENCE, this.esEventQueueCache);
                        z = true;
                    }
                }
                if (this.esHealthCheckCache != null) {
                    if (this.esEventQueueCache.equals(this.preferences_.getString(PREFERENCE_HEALTH_CHECK_STATE, ""))) {
                        z2 = z;
                    } else {
                        editorEdit.putString(PREFERENCE_HEALTH_CHECK_STATE, this.esHealthCheckCache);
                    }
                } else {
                    z2 = z;
                }
                if (z2) {
                    editorEdit.commit();
                }
                this.esDirtyFlag = false;
                if (explicitStorageCallback != null) {
                    explicitStorageCallback.WriteToStorageFinished(z2);
                }
            } else if (explicitStorageCallback != null) {
                explicitStorageCallback.WriteToStorageFinished(false);
            }
        }
    }

    @Override // ly.count.android.sdk.StorageProvider
    public void setServerConfig(String str) {
        this.preferences_.edit().putString(PREFERENCE_SERVER_CONFIG, str).apply();
    }

    @Override // ly.count.android.sdk.StorageProvider
    public String getServerConfig() {
        return this.preferences_.getString(PREFERENCE_SERVER_CONFIG, null);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized String[] getRequests() {
        String strStorageReadRequestQueue;
        strStorageReadRequestQueue = storageReadRequestQueue();
        return strStorageReadRequestQueue.length() == 0 ? new String[0] : strStorageReadRequestQueue.split(DELIMITER);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized String[] getEvents() {
        String strStorageReadEventQueue;
        strStorageReadEventQueue = storageReadEventQueue();
        return strStorageReadEventQueue.length() == 0 ? new String[0] : strStorageReadEventQueue.split(DELIMITER);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized List<Event> getEventList() {
        ArrayList arrayList;
        String[] events = getEvents();
        arrayList = new ArrayList(events.length);
        for (String str : events) {
            try {
                Event eventFromJSON = Event.fromJSON(new JSONObject(str));
                if (eventFromJSON != null) {
                    arrayList.add(eventFromJSON);
                }
            } catch (JSONException unused) {
            }
        }
        Collections.sort(arrayList, new Comparator<Event>() { // from class: ly.count.android.sdk.CountlyStore.1
            @Override // java.util.Comparator
            public int compare(Event event, Event event2) {
                return (int) (event.timestamp - event2.timestamp);
            }
        });
        return arrayList;
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized int getEventQueueSize() {
        return getEvents().length;
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized String getEventsForRequestAndEmptyEventQueue() {
        String string;
        List<Event> eventList = getEventList();
        JSONArray jSONArray = new JSONArray();
        Iterator<Event> it = eventList.iterator();
        while (it.hasNext()) {
            jSONArray.put(it.next().toJSON());
        }
        string = jSONArray.toString();
        removeEvents(eventList);
        try {
            string = URLEncoder.encode(string, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            this.L.w("[CountelyStore] getEventsForRequestAndEmptyEventQueue, why is this even happening?");
        }
        return string;
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized String getRequestQueueRaw() {
        return storageReadRequestQueue();
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void addRequest(String str, boolean z) {
        ConfigurationProvider configurationProvider = this.configurationProvider;
        if (configurationProvider != null && !configurationProvider.getTrackingEnabled()) {
            this.L.w("[CountlyStore] addRequest, Tracking config is disabled, request will not be added to the request queue.");
            return;
        }
        if (str != null && str.length() > 0) {
            ArrayList arrayList = new ArrayList(Arrays.asList(getRequests()));
            this.L.v("[CountlyStore] addRequest, s:[" + z + "] new q size:[" + (arrayList.size() + 1) + "] r:[" + str + "]");
            if (arrayList.size() < this.maxRequestQueueSize) {
                arrayList.add(str);
                storageWriteRequestQueue(Utils.joinCountlyStore(arrayList, DELIMITER), z);
            } else {
                this.L.w("[CountlyStore] Store reached it's limit, deleting oldest request");
                deleteOldestRequest();
                addRequest(str, z);
            }
        } else {
            this.L.w("[CountlyStore] addRequest, providing null or empty request string");
        }
    }

    synchronized void deleteOldestRequest() {
        ArrayList arrayList = new ArrayList(Arrays.asList(getRequests()));
        arrayList.remove(0);
        storageWriteRequestQueue(Utils.joinCountlyStore(arrayList, DELIMITER), false);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void removeRequest(String str) {
        if (str != null) {
            if (str.length() > 0) {
                ArrayList arrayList = new ArrayList(Arrays.asList(getRequests()));
                if (arrayList.remove(str)) {
                    storageWriteRequestQueue(Utils.joinCountlyStore(arrayList, DELIMITER), false);
                }
            }
        }
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void replaceRequests(String[] strArr) {
        if (strArr != null) {
            replaceRequestList(new ArrayList(Arrays.asList(strArr)));
        }
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void replaceRequestList(List<String> list) {
        if (list != null) {
            storageWriteRequestQueue(Utils.joinCountlyStore(list, DELIMITER), false);
        }
    }

    void addEvent(Event event) {
        ConfigurationProvider configurationProvider = this.configurationProvider;
        if (configurationProvider != null && !configurationProvider.getTrackingEnabled()) {
            this.L.w("[CountlyStore] addEvent, Tracking config is disabled, event will not be added to the request queue.");
            return;
        }
        List<Event> eventList = getEventList();
        if (eventList.size() < 1000) {
            eventList.add(event);
            setEventData(joinEvents(eventList, DELIMITER));
        }
    }

    void setEventData(String str) {
        storageWriteEventQueue(str, false);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void setStarRatingPreferences(String str) {
        this.preferences_.edit().putString(STAR_RATING_PREFERENCE, str).apply();
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized String getStarRatingPreferences() {
        return this.preferences_.getString(STAR_RATING_PREFERENCE, "");
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void setRemoteConfigValues(String str) {
        this.preferences_.edit().putString(REMOTE_CONFIG_VALUES, str).apply();
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized String getRemoteConfigValues() {
        return this.preferences_.getString(REMOTE_CONFIG_VALUES, "");
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void setCachedAdvertisingId(String str) {
        this.preferences_.edit().putString("ADVERTISING_ID", str).apply();
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized String getCachedAdvertisingId() {
        return this.preferences_.getString("ADVERTISING_ID", "");
    }

    void setConsentPush(boolean z) {
        this.preferencesPush_.edit().putBoolean(CONSENT_GCM_PREFERENCES, z).apply();
    }

    Boolean getConsentPush() {
        return Boolean.valueOf(this.preferencesPush_.getBoolean(CONSENT_GCM_PREFERENCES, false));
    }

    public static synchronized Boolean getConsentPushNoInit(Context context) {
        return Boolean.valueOf(createPreferencesPush(context).getBoolean(CONSENT_GCM_PREFERENCES, false));
    }

    @Override // ly.count.android.sdk.EventQueueProvider
    public void recordEventToEventQueue(String str, Map<String, Object> map, int i, double d, double d2, long j, int i2, int i3, String str2, String str3, String str4, String str5) {
        HashMap map2;
        HashMap map3;
        HashMap map4;
        HashMap map5;
        if (map == null || map.size() <= 0) {
            map2 = null;
            map3 = null;
            map4 = null;
            map5 = null;
        } else {
            map2 = new HashMap();
            map3 = new HashMap();
            map4 = new HashMap();
            map5 = new HashMap();
            Utils.fillInSegmentation(map, map2, map3, map4, map5, new HashMap());
        }
        Event event = new Event();
        event.key = str;
        event.segmentation = map2;
        event.segmentationDouble = map4;
        event.segmentationInt = map3;
        event.segmentationBoolean = map5;
        event.timestamp = j;
        event.hour = i2;
        event.dow = i3;
        event.count = i;
        event.sum = d;
        event.dur = d2;
        event.id = str2;
        event.pvid = str3;
        event.cvid = str4;
        event.peid = str5;
        addEvent(event);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public synchronized void removeEvents(Collection<Event> collection) {
        if (collection != null) {
            if (collection.size() > 0) {
                List<Event> eventList = getEventList();
                if (eventList.removeAll(collection)) {
                    storageWriteEventQueue(joinEvents(eventList, DELIMITER), false);
                }
            }
        }
    }

    static String joinEvents(Collection<Event> collection, String str) {
        ArrayList arrayList = new ArrayList();
        Iterator<Event> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().toJSON().toString());
        }
        return Utils.joinCountlyStore(arrayList, str);
    }

    public static synchronized void cachePushData(String str, String str2, Context context) {
        SharedPreferences.Editor editorEdit = createPreferencesPush(context).edit();
        editorEdit.putString(CACHED_PUSH_ACTION_ID, str);
        editorEdit.putString(CACHED_PUSH_ACTION_INDEX, str2);
        editorEdit.apply();
    }

    String[] getCachedPushData() {
        return new String[]{this.preferencesPush_.getString(CACHED_PUSH_ACTION_ID, null), this.preferencesPush_.getString(CACHED_PUSH_ACTION_INDEX, null)};
    }

    void clearCachedPushData() {
        SharedPreferences.Editor editorEdit = this.preferencesPush_.edit();
        editorEdit.remove(CACHED_PUSH_ACTION_ID);
        editorEdit.remove(CACHED_PUSH_ACTION_INDEX);
        editorEdit.apply();
    }

    public static void storeMessagingProvider(int i, Context context) {
        createPreferencesPush(context).edit().putInt(CACHED_PUSH_MESSAGING_PROVIDER, i).apply();
    }

    public static int getMessagingProvider(Context context) {
        return createPreferencesPush(context).getInt(CACHED_PUSH_MESSAGING_PROVIDER, 0);
    }

    public synchronized void clear() {
        SharedPreferences.Editor editorEdit = this.preferences_.edit();
        editorEdit.remove(EVENTS_PREFERENCE);
        editorEdit.remove(REQUEST_PREFERENCE);
        editorEdit.clear();
        editorEdit.apply();
        this.esDirtyFlag = false;
        this.esRequestQueueCache = null;
        this.esEventQueueCache = null;
        this.preferencesPush_.edit().clear().apply();
    }

    @Override // ly.count.android.sdk.StorageProvider
    public String getDeviceID() {
        return this.preferences_.getString(PREFERENCE_KEY_ID_ID, null);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public String getDeviceIDType() {
        return this.preferences_.getString(PREFERENCE_KEY_ID_TYPE, null);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public void setDeviceID(String str) {
        if (str == null) {
            this.preferences_.edit().remove(PREFERENCE_KEY_ID_ID).apply();
        } else {
            this.preferences_.edit().putString(PREFERENCE_KEY_ID_ID, str).apply();
        }
    }

    @Override // ly.count.android.sdk.StorageProvider
    public void setDeviceIDType(String str) {
        if (str == null) {
            this.preferences_.edit().remove(PREFERENCE_KEY_ID_TYPE).apply();
        } else {
            this.preferences_.edit().putString(PREFERENCE_KEY_ID_TYPE, str).apply();
        }
    }

    @Override // ly.count.android.sdk.StorageProvider
    public int getDataSchemaVersion() {
        return this.preferences_.getInt(STORAGE_SCHEMA_VERSION, -1);
    }

    @Override // ly.count.android.sdk.StorageProvider
    public void setDataSchemaVersion(int i) {
        this.preferences_.edit().putInt(STORAGE_SCHEMA_VERSION, i).apply();
    }

    @Override // ly.count.android.sdk.StorageProvider
    public boolean anythingSetInStorage() {
        return (this.preferences_.getString(REQUEST_PREFERENCE, null) == null && this.preferences_.getString(EVENTS_PREFERENCE, null) == null && this.preferences_.getString(STAR_RATING_PREFERENCE, null) == null && this.preferences_.getString("ADVERTISING_ID", null) == null && this.preferences_.getString(REMOTE_CONFIG_VALUES, null) == null && this.preferences_.getString(PREFERENCE_KEY_ID_ID, null) == null && this.preferences_.getString(PREFERENCE_KEY_ID_TYPE, null) == null && this.preferences_.getInt(STORAGE_SCHEMA_VERSION, -100) == -100 && this.preferences_.getString(PREFERENCE_SERVER_CONFIG, null) == null && this.preferences_.getString(PREFERENCE_HEALTH_CHECK_STATE, null) == null && this.preferencesPush_.getInt(CACHED_PUSH_MESSAGING_PROVIDER, -100) == -100 && this.preferencesPush_.getString(CACHED_PUSH_ACTION_ID, null) == null && this.preferencesPush_.getString(CACHED_PUSH_ACTION_INDEX, null) == null) ? false : true;
    }

    @Override // ly.count.android.sdk.StorageProvider
    public String getHealthCheckCounterState() {
        if (this.explicitStorageModeEnabled) {
            if (this.esHealthCheckCache == null) {
                this.L.v("[CountlyStore] Reading initial health check state from storage");
                this.esHealthCheckCache = this.preferences_.getString(PREFERENCE_HEALTH_CHECK_STATE, "");
            }
            return this.esHealthCheckCache;
        }
        return this.preferences_.getString(PREFERENCE_HEALTH_CHECK_STATE, "");
    }

    @Override // ly.count.android.sdk.StorageProvider
    public void setHealthCheckCounterState(String str) {
        if (this.explicitStorageModeEnabled) {
            this.L.v("[CountlyStore] Writing health check state to cache");
            this.esHealthCheckCache = str;
        } else {
            this.L.v("[CountlyStore] Writing health check state to preferences");
            this.preferences_.edit().putString(PREFERENCE_HEALTH_CHECK_STATE, str).apply();
        }
    }
}
