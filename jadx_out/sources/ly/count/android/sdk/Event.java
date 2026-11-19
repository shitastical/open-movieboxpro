package ly.count.android.sdk;

import j$.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
class Event {
    protected static final String COUNT_KEY = "count";
    protected static final String CV_ID_KEY = "cvid";
    protected static final String DAY_OF_WEEK_KEY = "dow";
    protected static final String DUR_KEY = "dur";
    protected static final String HOUR_KEY = "hour";
    protected static final String ID_KEY = "id";
    protected static final String KEY_KEY = "key";
    protected static final String PE_ID_KEY = "peid";
    protected static final String PV_ID_KEY = "pvid";
    protected static final String SEGMENTATION_KEY = "segmentation";
    protected static final String SUM_KEY = "sum";
    protected static final String TIMESTAMP_KEY = "timestamp";
    public int count;
    public String cvid;
    public int dow;
    public double dur;
    public int hour;
    public String id;
    public String key;
    public String peid;
    public String pvid;
    public Map<String, String> segmentation;
    public Map<String, Boolean> segmentationBoolean;
    public Map<String, Double> segmentationDouble;
    public Map<String, Integer> segmentationInt;
    public double sum;
    public long timestamp;

    Event() {
    }

    Event(String str, long j, int i, int i2) {
        this.key = str;
        this.timestamp = j;
        this.hour = i;
        this.dow = i2;
    }

    JSONObject toJSON() {
        Map<String, Integer> map;
        Map<String, Double> map2;
        Map<String, Boolean> map3;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KEY_KEY, this.key);
            jSONObject.put(COUNT_KEY, this.count);
            jSONObject.put(TIMESTAMP_KEY, this.timestamp);
            jSONObject.put(HOUR_KEY, this.hour);
            jSONObject.put(DAY_OF_WEEK_KEY, this.dow);
            String str = this.id;
            if (str != null) {
                jSONObject.put("id", str);
            }
            String str2 = this.pvid;
            if (str2 != null) {
                jSONObject.put(PV_ID_KEY, str2);
            }
            String str3 = this.cvid;
            if (str3 != null) {
                jSONObject.put(CV_ID_KEY, str3);
            }
            String str4 = this.peid;
            if (str4 != null) {
                jSONObject.put(PE_ID_KEY, str4);
            }
            JSONObject jSONObject2 = new JSONObject();
            Map<String, String> map4 = this.segmentation;
            if (map4 != null) {
                for (Map.Entry<String, String> entry : map4.entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
            }
            Map<String, Integer> map5 = this.segmentationInt;
            if (map5 != null) {
                for (Map.Entry<String, Integer> entry2 : map5.entrySet()) {
                    jSONObject2.put(entry2.getKey(), entry2.getValue());
                }
            }
            Map<String, Double> map6 = this.segmentationDouble;
            if (map6 != null) {
                for (Map.Entry<String, Double> entry3 : map6.entrySet()) {
                    jSONObject2.put(entry3.getKey(), entry3.getValue());
                }
            }
            Map<String, Boolean> map7 = this.segmentationBoolean;
            if (map7 != null) {
                for (Map.Entry<String, Boolean> entry4 : map7.entrySet()) {
                    jSONObject2.put(entry4.getKey(), entry4.getValue());
                }
            }
            Map<String, String> map8 = this.segmentation;
            if ((map8 != null && !map8.isEmpty()) || (((map = this.segmentationInt) != null && !map.isEmpty()) || (((map2 = this.segmentationDouble) != null && !map2.isEmpty()) || ((map3 = this.segmentationBoolean) != null && !map3.isEmpty())))) {
                jSONObject.put(SEGMENTATION_KEY, jSONObject2);
            }
            jSONObject.put(SUM_KEY, this.sum);
            double d = this.dur;
            if (d > 0.0d) {
                jSONObject.put(DUR_KEY, d);
            }
        } catch (JSONException e) {
            Countly.sharedInstance().L.w("Got exception converting an Event to JSON", e);
        }
        return jSONObject;
    }

    static Event fromJSON(JSONObject jSONObject) {
        String str;
        Event event = new Event();
        try {
            if (!jSONObject.isNull(KEY_KEY)) {
                event.key = jSONObject.getString(KEY_KEY);
            }
            event.count = jSONObject.optInt(COUNT_KEY);
            event.sum = jSONObject.optDouble(SUM_KEY, 0.0d);
            event.dur = jSONObject.optDouble(DUR_KEY, 0.0d);
            event.timestamp = jSONObject.optLong(TIMESTAMP_KEY);
            event.hour = jSONObject.optInt(HOUR_KEY);
            event.dow = jSONObject.optInt(DAY_OF_WEEK_KEY);
            if (!jSONObject.isNull("id")) {
                event.id = jSONObject.getString("id");
            }
            if (!jSONObject.isNull(PV_ID_KEY)) {
                event.pvid = jSONObject.getString(PV_ID_KEY);
            }
            if (!jSONObject.isNull(CV_ID_KEY)) {
                event.cvid = jSONObject.getString(CV_ID_KEY);
            }
            if (!jSONObject.isNull(PE_ID_KEY)) {
                event.peid = jSONObject.getString(PE_ID_KEY);
            }
            if (!jSONObject.isNull(SEGMENTATION_KEY)) {
                JSONObject jSONObject2 = jSONObject.getJSONObject(SEGMENTATION_KEY);
                Iterator<String> itKeys = jSONObject2.keys();
                HashMap map = null;
                HashMap map2 = null;
                HashMap map3 = null;
                HashMap map4 = null;
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (!jSONObject2.isNull(next)) {
                        Object objOpt = jSONObject2.opt(next);
                        if (objOpt instanceof Double) {
                            if (map2 == null) {
                                map2 = new HashMap();
                            }
                            map2.put(next, Double.valueOf(jSONObject2.getDouble(next)));
                        } else if (objOpt instanceof Integer) {
                            if (map3 == null) {
                                map3 = new HashMap();
                            }
                            map3.put(next, Integer.valueOf(jSONObject2.getInt(next)));
                        } else if (objOpt instanceof Boolean) {
                            if (map4 == null) {
                                map4 = new HashMap();
                            }
                            map4.put(next, Boolean.valueOf(jSONObject2.getBoolean(next)));
                        } else if (objOpt instanceof String) {
                            if (map == null) {
                                map = new HashMap();
                            }
                            map.put(next, jSONObject2.getString(next));
                        }
                    }
                }
                event.segmentation = map;
                event.segmentationDouble = map2;
                event.segmentationInt = map3;
                event.segmentationBoolean = map4;
            }
        } catch (JSONException e) {
            Countly.sharedInstance().L.w("Got exception converting JSON to an Event", e);
            event = null;
        }
        if (event == null || (str = event.key) == null || str.length() <= 0) {
            return null;
        }
        return event;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        Event event = (Event) obj;
        return Objects.equals(this.key, event.key) && this.timestamp == event.timestamp && this.hour == event.hour && this.dow == event.dow && Objects.equals(this.id, event.id) && Objects.equals(this.pvid, event.pvid) && Objects.equals(this.cvid, event.cvid) && Objects.equals(this.peid, event.peid) && Objects.equals(this.segmentation, event.segmentation);
    }

    public int hashCode() {
        String str = this.key;
        int iHashCode = str != null ? str.hashCode() : 1;
        Map<String, String> map = this.segmentation;
        int iHashCode2 = iHashCode ^ (map != null ? map.hashCode() : 1);
        String str2 = this.id;
        int iHashCode3 = iHashCode2 ^ (str2 != null ? str2.hashCode() : 1);
        String str3 = this.pvid;
        int iHashCode4 = iHashCode3 ^ (str3 != null ? str3.hashCode() : 1);
        String str4 = this.cvid;
        int iHashCode5 = iHashCode4 ^ (str4 != null ? str4.hashCode() : 1);
        String str5 = this.peid;
        int iHashCode6 = iHashCode5 ^ (str5 != null ? str5.hashCode() : 1);
        long j = this.timestamp;
        return iHashCode6 ^ (j != 0 ? (int) j : 1);
    }
}
