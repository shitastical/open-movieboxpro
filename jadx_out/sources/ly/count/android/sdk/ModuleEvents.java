package ly.count.android.sdk;

import java.util.HashMap;
import java.util.Map;
import ly.count.android.sdk.messaging.ModulePush;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleEvents extends ModuleBase implements EventProvider {
    static final String ACTION_EVENT_KEY = "[CLY]_action";
    static final Map<String, Event> timedEvents = new HashMap();
    EventQueueProvider eventQueueProvider;
    final Events eventsInterface;
    String previousEventId;
    SafeIDGenerator safeEventIDGenerator;
    ViewIdProvider viewIdProvider;

    ModuleEvents(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.previousEventId = "";
        this.L.v("[ModuleEvents] Initialising");
        this.eventProvider = this;
        countlyConfig.eventProvider = this;
        this.eventQueueProvider = countlyConfig.eventQueueProvider;
        this.safeEventIDGenerator = countlyConfig.safeEventIDGenerator;
        this.eventsInterface = new Events();
    }

    void checkCachedPushData(CountlyStore countlyStore) {
        this.L.d("[ModuleEvents] Starting cache call");
        String[] cachedPushData = countlyStore.getCachedPushData();
        if (cachedPushData != null && cachedPushData[0] != null && cachedPushData[1] != null) {
            this.L.d("[ModuleEvents] Found cached push event, recording it");
            HashMap map = new HashMap();
            map.put("p", "a");
            map.put("i", cachedPushData[0]);
            map.put(ModulePush.PUSH_EVENT_ACTION_INDEX_KEY, cachedPushData[1]);
            recordEventInternal(ModulePush.PUSH_EVENT_ACTION, map, 1, 0.0d, 0.0d, null, null);
        }
        if (cachedPushData != null) {
            if (cachedPushData[0] == null && cachedPushData[1] == null) {
                return;
            }
            countlyStore.clearCachedPushData();
        }
    }

    @Override // ly.count.android.sdk.EventProvider
    public void recordEventInternal(String str, Map<String, Object> map, int i, double d, double d2, UtilsTime.Instant instant, String str2) {
        String currentViewId;
        String previousViewId;
        String strGenerateValue = str2;
        this.L.v("[ModuleEvents] calling 'recordEventInternal'");
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Valid Countly event key is required");
        }
        if (i < 1) {
            throw new IllegalArgumentException("Countly event count should be greater than zero");
        }
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleEvents] Recording event with key: [");
        sb.append(str);
        sb.append("] and provided event ID of:[");
        sb.append(strGenerateValue);
        sb.append("] and segmentation with:[");
        sb.append(map == null ? "null" : Integer.valueOf(map.size()));
        sb.append("] keys");
        moduleLog.d(sb.toString());
        if (!this._cly.isInitialized()) {
            throw new IllegalStateException("Countly.sharedInstance().init must be called before recordEvent");
        }
        if (map != null) {
            Utils.removeUnsupportedDataTypes(map);
        }
        UtilsTime.Instant currentInstant = instant == null ? UtilsTime.getCurrentInstant() : instant;
        if (strGenerateValue == null) {
            strGenerateValue = this.safeEventIDGenerator.GenerateValue();
        } else if (str2.length() == 0) {
            this.L.w("[ModuleEvents] provided event ID override value is empty. Will generate a new one.");
            strGenerateValue = this.safeEventIDGenerator.GenerateValue();
        }
        String str3 = strGenerateValue;
        long j = currentInstant.timestampMs;
        int i2 = currentInstant.hour;
        int i3 = currentInstant.dow;
        if (str.equals("[CLY]_view")) {
            previousViewId = this.viewIdProvider.getPreviousViewId();
            currentViewId = null;
        } else {
            currentViewId = this.viewIdProvider.getCurrentViewId();
            previousViewId = null;
        }
        str.hashCode();
        switch (str) {
            case "[CLY]_view":
                if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.views)) {
                    this.eventQueueProvider.recordEventToEventQueue(str, map, i, d, d2, j, i2, i3, str3, previousViewId, currentViewId, null);
                    this._cly.moduleRequestQueue.sendEventsIfNeeded(false);
                    return;
                }
                return;
            case "[CLY]_action":
                if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.clicks) || this.consentProvider.getConsent(Countly.CountlyFeatureNames.scrolls)) {
                    this.eventQueueProvider.recordEventToEventQueue(str, map, i, d, d2, j, i2, i3, str3, previousViewId, currentViewId, null);
                    this._cly.moduleRequestQueue.sendEventsIfNeeded(false);
                    return;
                }
                return;
            case "[CLY]_star_rating":
                if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.starRating) || this.consentProvider.getConsent(Countly.CountlyFeatureNames.feedback)) {
                    this.eventQueueProvider.recordEventToEventQueue(str, map, i, d, d2, j, i2, i3, str3, previousViewId, currentViewId, null);
                    this._cly.moduleRequestQueue.sendEventsIfNeeded(false);
                    return;
                }
                return;
            case "[CLY]_survey":
            case "[CLY]_nps":
                if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.feedback)) {
                    this.eventQueueProvider.recordEventToEventQueue(str, map, i, d, d2, j, i2, i3, str3, previousViewId, currentViewId, null);
                    this._cly.moduleRequestQueue.sendEventsIfNeeded(true);
                    return;
                }
                return;
            case "[CLY]_orientation":
                if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.users)) {
                    this.eventQueueProvider.recordEventToEventQueue(str, map, i, d, d2, j, i2, i3, str3, previousViewId, currentViewId, null);
                    this._cly.moduleRequestQueue.sendEventsIfNeeded(false);
                    return;
                }
                return;
            case "[CLY]_push_action":
                if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.push)) {
                    this.eventQueueProvider.recordEventToEventQueue(str, map, i, d, d2, j, i2, i3, str3, previousViewId, currentViewId, null);
                    this._cly.moduleRequestQueue.sendEventsIfNeeded(true);
                    return;
                }
                return;
            default:
                if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.events)) {
                    this.eventQueueProvider.recordEventToEventQueue(str, map, i, d, d2, j, i2, i3, str3, previousViewId, currentViewId, this.previousEventId);
                    this.previousEventId = str3;
                    this._cly.moduleRequestQueue.sendEventsIfNeeded(false);
                    return;
                }
                return;
        }
    }

    boolean startEventInternal(String str) {
        if (str == null || str.length() == 0) {
            this.L.e("[ModuleEvents] Can't start event with a null or empty key");
            return false;
        }
        Map<String, Event> map = timedEvents;
        if (map.containsKey(str)) {
            return false;
        }
        this.L.d("[ModuleEvents] Starting event: [" + str + "]");
        UtilsTime.Instant currentInstant = UtilsTime.getCurrentInstant();
        map.put(str, new Event(str, currentInstant.timestampMs, currentInstant.hour, currentInstant.dow));
        return true;
    }

    boolean endEventInternal(String str, Map<String, Object> map, int i, double d) {
        this.L.d("[ModuleEvents] Ending event: [" + str + "]");
        if (str == null || str.length() == 0) {
            this.L.e("[ModuleEvents] Can't end event with a null or empty key");
            return false;
        }
        Event eventRemove = timedEvents.remove(str);
        if (eventRemove == null) {
            return false;
        }
        if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.events)) {
            return true;
        }
        if (i < 1) {
            throw new IllegalArgumentException("Countly event count should be greater than zero");
        }
        this.L.d("[ModuleEvents] Ending event: [" + str + "]");
        this.eventProvider.recordEventInternal(str, map, i, d, ((double) (UtilsTime.currentTimestampMs() - eventRemove.timestamp)) / 1000.0d, new UtilsTime.Instant(eventRemove.timestamp, eventRemove.hour, eventRemove.dow), null);
        return true;
    }

    boolean cancelEventInternal(String str) {
        if (str != null && str.length() != 0) {
            return timedEvents.remove(str) != null;
        }
        this.L.e("[ModuleEvents] Can't cancel event with a null or empty key");
        return false;
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        checkCachedPushData(this._cly.countlyStore);
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        timedEvents.clear();
    }

    public class Events {
        public Events() {
        }

        public void recordPastEvent(String str, Map<String, Object> map, long j) {
            synchronized (ModuleEvents.this._cly) {
                try {
                    if (j == 0) {
                        throw new IllegalStateException("Provided timestamp has to be greater that zero");
                    }
                    recordPastEvent(str, map, 1, 0.0d, 0.0d, j);
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public void recordPastEvent(String str, Map<String, Object> map, int i, double d, double d2, long j) {
            synchronized (ModuleEvents.this._cly) {
                ModuleEvents.this.L.i("[Events] Calling recordPastEvent: [" + str + "]");
                if (j == 0) {
                    throw new IllegalStateException("Provided timestamp has to be greater that zero");
                }
                ModuleEvents.this.recordEventInternal(str, map, i, d, d2, UtilsTime.Instant.get(j), null);
            }
        }

        public boolean startEvent(String str) {
            boolean zStartEventInternal;
            synchronized (ModuleEvents.this._cly) {
                if (!ModuleEvents.this._cly.isInitialized()) {
                    throw new IllegalStateException("Countly.sharedInstance().init must be called before startEvent");
                }
                zStartEventInternal = ModuleEvents.this.startEventInternal(str);
            }
            return zStartEventInternal;
        }

        public boolean endEvent(String str) {
            boolean zEndEvent;
            synchronized (ModuleEvents.this._cly) {
                zEndEvent = endEvent(str, null, 1, 0.0d);
            }
            return zEndEvent;
        }

        public boolean endEvent(String str, Map<String, Object> map, int i, double d) {
            boolean zEndEventInternal;
            synchronized (ModuleEvents.this._cly) {
                if (!ModuleEvents.this._cly.isInitialized()) {
                    throw new IllegalStateException("Countly.sharedInstance().init must be called before endEvent");
                }
                zEndEventInternal = ModuleEvents.this.endEventInternal(str, map, i, d);
            }
            return zEndEventInternal;
        }

        public boolean cancelEvent(String str) {
            boolean zCancelEventInternal;
            synchronized (ModuleEvents.this._cly) {
                ModuleEvents.this.L.i("[Events] Calling cancelEvent: [" + str + "]");
                zCancelEventInternal = ModuleEvents.this.cancelEventInternal(str);
            }
            return zCancelEventInternal;
        }

        public void recordEvent(String str) {
            synchronized (ModuleEvents.this._cly) {
                recordEvent(str, null, 1, 0.0d);
            }
        }

        public void recordEvent(String str, int i) {
            synchronized (ModuleEvents.this._cly) {
                recordEvent(str, null, i, 0.0d);
            }
        }

        public void recordEvent(String str, int i, double d) {
            synchronized (ModuleEvents.this._cly) {
                recordEvent(str, null, i, d);
            }
        }

        public void recordEvent(String str, Map<String, Object> map) {
            synchronized (ModuleEvents.this._cly) {
                recordEvent(str, map, 1, 0.0d);
            }
        }

        public void recordEvent(String str, Map<String, Object> map, int i) {
            synchronized (ModuleEvents.this._cly) {
                recordEvent(str, map, i, 0.0d);
            }
        }

        public void recordEvent(String str, Map<String, Object> map, int i, double d) {
            synchronized (ModuleEvents.this._cly) {
                recordEvent(str, map, i, d, 0.0d);
            }
        }

        public void recordEvent(String str, Map<String, Object> map, int i, double d, double d2) {
            synchronized (ModuleEvents.this._cly) {
                if (!ModuleEvents.this._cly.isInitialized()) {
                    throw new IllegalStateException("Countly.sharedInstance().init must be called before recordEvent");
                }
                ModuleEvents.this.L.i("[Events] Calling recordEvent: [" + str + "]");
                Utils.truncateSegmentationValues(map, ModuleEvents.this._cly.config_.maxSegmentationValues.intValue(), "[Events] recordEvent,", ModuleEvents.this.L);
                ModuleEvents.this.eventProvider.recordEventInternal(str, map, i, d, d2, null, null);
            }
        }
    }
}
