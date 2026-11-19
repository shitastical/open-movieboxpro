package ly.count.android.sdk;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleUserProfile extends ModuleBase {
    static final String BYEAR_KEY = "byear";
    static final String CUSTOM_KEY = "custom";
    static final String EMAIL_KEY = "email";
    static final String GENDER_KEY = "gender";
    static final String NAME_KEY = "name";
    static final String ORG_KEY = "organization";
    static final String PHONE_KEY = "phone";
    static final String PICTURE_KEY = "picture";
    static final String PICTURE_PATH_KEY = "picturePath";
    static final String USERNAME_KEY = "username";
    static String picturePath;
    int byear;
    Map<String, String> custom;
    Map<String, JSONObject> customMods;
    JSONObject dataStore;
    String email;
    String gender;
    boolean isSynced;
    String name;
    String[] namedFields;

    /* JADX INFO: renamed from: org, reason: collision with root package name */
    String f18org;
    String phone;
    String picture;
    UserProfile userProfileInterface;
    String username;

    ModuleUserProfile(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.namedFields = new String[]{"name", USERNAME_KEY, "email", ORG_KEY, PHONE_KEY, PICTURE_KEY, PICTURE_PATH_KEY, GENDER_KEY, BYEAR_KEY};
        this.isSynced = true;
        this.dataStore = new JSONObject();
        this.byear = 0;
        this.L.v("[ModuleUserProfile] Initialising");
        this.userProfileInterface = new UserProfile();
    }

    static String getPicturePathFromQuery(URL url) {
        String query = url.getQuery();
        if (query == null) {
            return "";
        }
        String[] strArrSplit = query.split("&");
        if (!url.getQuery().contains(PICTURE_PATH_KEY)) {
            return "";
        }
        for (String str : strArrSplit) {
            int iIndexOf = str.indexOf("=");
            if (str.substring(0, iIndexOf).equals(PICTURE_PATH_KEY)) {
                try {
                    return URLDecoder.decode(str.substring(iIndexOf + 1), "UTF-8");
                } catch (UnsupportedEncodingException unused) {
                    return "";
                }
            }
        }
        return "";
    }

    String getDataForRequest() {
        if (!this.isSynced) {
            this.isSynced = true;
            JSONObject json = toJSON();
            if (json != null) {
                String string = json.toString();
                try {
                    String strEncode = URLEncoder.encode(string, "UTF-8");
                    if (strEncode == null || strEncode.equals("")) {
                        try {
                            if (picturePath != null) {
                                string = "&user_details&picturePath=" + URLEncoder.encode(picturePath, "UTF-8");
                            } else {
                                string = "";
                            }
                        } catch (UnsupportedEncodingException unused) {
                        }
                    } else {
                        string = "&user_details=" + strEncode;
                        if (picturePath != null) {
                            string = string + "&picturePath=" + URLEncoder.encode(picturePath, "UTF-8");
                        }
                    }
                } catch (UnsupportedEncodingException unused2) {
                }
                if (string != null) {
                    return string;
                }
            }
        }
        return "";
    }

    protected JSONObject toJSON() {
        JSONObject jSONObject;
        JSONObject jSONObject2 = new JSONObject();
        try {
            String str = this.name;
            if (str != null) {
                if (str.equals("")) {
                    jSONObject2.put("name", JSONObject.NULL);
                } else {
                    jSONObject2.put("name", this.name);
                }
            }
            String str2 = this.username;
            if (str2 != null) {
                if (str2.equals("")) {
                    jSONObject2.put(USERNAME_KEY, JSONObject.NULL);
                } else {
                    jSONObject2.put(USERNAME_KEY, this.username);
                }
            }
            String str3 = this.email;
            if (str3 != null) {
                if (str3.equals("")) {
                    jSONObject2.put("email", JSONObject.NULL);
                } else {
                    jSONObject2.put("email", this.email);
                }
            }
            String str4 = this.f18org;
            if (str4 != null) {
                if (str4.equals("")) {
                    jSONObject2.put(ORG_KEY, JSONObject.NULL);
                } else {
                    jSONObject2.put(ORG_KEY, this.f18org);
                }
            }
            String str5 = this.phone;
            if (str5 != null) {
                if (str5.equals("")) {
                    jSONObject2.put(PHONE_KEY, JSONObject.NULL);
                } else {
                    jSONObject2.put(PHONE_KEY, this.phone);
                }
            }
            String str6 = this.picture;
            if (str6 != null) {
                if (str6.equals("")) {
                    jSONObject2.put(PICTURE_KEY, JSONObject.NULL);
                } else {
                    jSONObject2.put(PICTURE_KEY, this.picture);
                }
            }
            String str7 = this.gender;
            if (str7 != null) {
                if (str7.equals("")) {
                    jSONObject2.put(GENDER_KEY, JSONObject.NULL);
                } else {
                    jSONObject2.put(GENDER_KEY, this.gender);
                }
            }
            int i = this.byear;
            if (i != 0) {
                if (i <= 0) {
                    jSONObject2.put(BYEAR_KEY, JSONObject.NULL);
                } else {
                    jSONObject2.put(BYEAR_KEY, i);
                }
            }
            if (this.custom != null) {
                jSONObject = new JSONObject(this.custom);
            } else {
                jSONObject = new JSONObject();
            }
            Map<String, JSONObject> map = this.customMods;
            if (map != null) {
                for (Map.Entry<String, JSONObject> entry : map.entrySet()) {
                    jSONObject.put(entry.getKey(), entry.getValue());
                }
            }
            jSONObject2.put("custom", jSONObject);
        } catch (JSONException e) {
            Countly.sharedInstance().L.w("[UserData] Got exception converting an UserData to JSON", e);
        }
        return jSONObject2;
    }

    void fromJSON(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.name = jSONObject.optString("name", null);
            this.username = jSONObject.optString(USERNAME_KEY, null);
            this.email = jSONObject.optString("email", null);
            this.f18org = jSONObject.optString(ORG_KEY, null);
            this.phone = jSONObject.optString(PHONE_KEY, null);
            this.picture = jSONObject.optString(PICTURE_KEY, null);
            this.gender = jSONObject.optString(GENDER_KEY, null);
            this.byear = jSONObject.optInt(BYEAR_KEY, 0);
            if (jSONObject.isNull("custom")) {
                return;
            }
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("custom");
                if (jSONObject2.length() == 0) {
                    this.custom = null;
                    return;
                }
                this.custom = new HashMap(jSONObject2.length());
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    if (!jSONObject2.isNull(next)) {
                        this.custom.put(next, jSONObject2.getString(next));
                    }
                }
            } catch (JSONException e) {
                Countly.sharedInstance().L.w("[ModuleUserProfile] Got exception converting an Custom Json to Custom User data", e);
            }
        }
    }

    void modifyCustomData(String str, Object obj, String str2) {
        JSONObject jSONObject;
        try {
            if (!(obj instanceof Double) && !(obj instanceof Integer) && !(obj instanceof String)) {
                Countly.sharedInstance().L.w("[ModuleUserProfile] modifyCustomDataCommon, provided an unsupported type for 'value'");
                return;
            }
            if (this.customMods == null) {
                this.customMods = new HashMap();
            }
            if (!str2.equals("$pull") && !str2.equals("$push") && !str2.equals("$addToSet")) {
                jSONObject = new JSONObject();
                jSONObject.put(str2, obj);
            } else {
                if (this.customMods.containsKey(str)) {
                    jSONObject = this.customMods.get(str);
                } else {
                    jSONObject = new JSONObject();
                }
                jSONObject.accumulate(str2, obj);
            }
            this.customMods.put(str, jSONObject);
            this.isSynced = false;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void setPropertiesInternal(Map<String, Object> map) {
        if (map.size() == 0) {
            Countly.sharedInstance().L.w("[ModuleUserProfile] setPropertiesInternal, no data was provided");
            return;
        }
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
        while (true) {
            int i = 0;
            if (!it.hasNext()) {
                break;
            }
            Map.Entry<String, Object> next = it.next();
            String key = next.getKey();
            Object value = next.getValue();
            String[] strArr = this.namedFields;
            int length = strArr.length;
            while (true) {
                if (i < length) {
                    if (strArr[i].equals(key)) {
                        map2.put(key, value.toString());
                        break;
                    }
                    i++;
                } else {
                    map3.put(key, value.toString());
                    break;
                }
            }
        }
        setData(map2);
        if (this.custom == null) {
            this.custom = new HashMap();
        }
        this.custom.putAll(map3);
        this.isSynced = false;
    }

    public void setData(Map<String, String> map) {
        if (map.containsKey("name")) {
            this.name = map.get("name");
        }
        if (map.containsKey(USERNAME_KEY)) {
            this.username = map.get(USERNAME_KEY);
        }
        if (map.containsKey("email")) {
            this.email = map.get("email");
        }
        if (map.containsKey(ORG_KEY)) {
            this.f18org = map.get(ORG_KEY);
        }
        if (map.containsKey(PHONE_KEY)) {
            this.phone = map.get(PHONE_KEY);
        }
        if (map.containsKey(PICTURE_PATH_KEY)) {
            picturePath = map.get(PICTURE_PATH_KEY);
        }
        if (picturePath != null && !new File(picturePath).isFile()) {
            Countly.sharedInstance().L.w("[UserData] Provided Picture path file [" + picturePath + "] can not be opened");
            picturePath = null;
        }
        if (map.containsKey(PICTURE_KEY)) {
            this.picture = map.get(PICTURE_KEY);
        }
        if (map.containsKey(GENDER_KEY)) {
            this.gender = map.get(GENDER_KEY);
        }
        if (map.containsKey(BYEAR_KEY)) {
            try {
                this.byear = Integer.parseInt(map.get(BYEAR_KEY));
            } catch (NumberFormatException unused) {
                Countly.sharedInstance().L.w("[UserData] Incorrect byear number format");
                this.byear = 0;
            }
        }
    }

    void saveInternal() {
        Countly.sharedInstance().L.d("[ModuleUserProfile] saveInternal");
        this.requestQueueProvider.sendUserData(getDataForRequest());
        clearInternal();
    }

    void clearInternal() {
        Countly.sharedInstance().L.d("[ModuleUserProfile] clearInternal");
        this.name = null;
        this.username = null;
        this.email = null;
        this.f18org = null;
        this.phone = null;
        this.picture = null;
        picturePath = null;
        this.gender = null;
        this.custom = null;
        this.customMods = null;
        this.byear = 0;
        this.isSynced = true;
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (countlyConfig.providedUserProperties != null) {
            this.L.i("[ModuleUserProfile] Custom user properties were provided during init [" + countlyConfig.providedUserProperties.size() + "]");
            setPropertiesInternal(countlyConfig.providedUserProperties);
            saveInternal();
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        this.userProfileInterface = null;
    }

    public class UserProfile {
        public UserProfile() {
        }

        public void increment(String str) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, 1, "$inc");
            }
        }

        public void incrementBy(String str, int i) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, Integer.valueOf(i), "$inc");
            }
        }

        public void multiply(String str, int i) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, Integer.valueOf(i), "$mul");
            }
        }

        public void saveMax(String str, int i) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, Integer.valueOf(i), "$max");
            }
        }

        public void saveMin(String str, int i) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, Integer.valueOf(i), "$min");
            }
        }

        public void setOnce(String str, String str2) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, str2, "$setOnce");
            }
        }

        public void push(String str, String str2) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, str2, "$push");
            }
        }

        public void pushUnique(String str, String str2) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, str2, "$addToSet");
            }
        }

        public void pull(String str, String str2) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.modifyCustomData(str, str2, "$pull");
            }
        }

        public void setProperty(String str, Object obj) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.L.i("[UserProfile] Calling 'setProperty'");
                HashMap map = new HashMap();
                map.put(str, obj);
                ModuleUserProfile.this.setPropertiesInternal(map);
            }
        }

        public void setProperties(Map<String, Object> map) {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.L.i("[UserProfile] Calling 'setProperties'");
                if (map == null) {
                    ModuleUserProfile.this.L.i("[UserProfile] Provided data can not be 'null'");
                } else {
                    ModuleUserProfile.this.setPropertiesInternal(map);
                }
            }
        }

        public void save() {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.L.i("[UserProfile] Calling 'save'");
                ModuleUserProfile.this.saveInternal();
            }
        }

        public void clear() {
            synchronized (ModuleUserProfile.this._cly) {
                ModuleUserProfile.this.L.i("[UserProfile] Calling 'clear'");
                ModuleUserProfile.this.clearInternal();
            }
        }
    }
}
