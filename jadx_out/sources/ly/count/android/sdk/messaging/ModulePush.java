package ly.count.android.sdk.messaging;

import android.content.Context;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import ly.count.android.sdk.Countly;
import ly.count.android.sdk.CountlyStore;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ModulePush {
    static final String KEY_BADGE = "badge";
    static final String KEY_BUTTONS = "c.b";
    static final String KEY_BUTTONS_LINK = "l";
    static final String KEY_BUTTONS_TITLE = "t";
    static final String KEY_ID = "c.i";
    static final String KEY_LINK = "c.l";
    static final String KEY_MEDIA = "c.m";
    static final String KEY_MESSAGE = "message";
    static final String KEY_SOUND = "sound";
    static final String KEY_TITLE = "title";
    public static final String PUSH_EVENT_ACTION = "[CLY]_push_action";
    public static final String PUSH_EVENT_ACTION_ID_KEY = "i";
    public static final String PUSH_EVENT_ACTION_INDEX_KEY = "b";
    public static final String PUSH_EVENT_ACTION_PLATFORM_KEY = "p";
    public static final String PUSH_EVENT_ACTION_PLATFORM_VALUE = "a";

    static class MessageImpl implements CountlyPush.Message {
        public static final Parcelable.Creator<MessageImpl> CREATOR = new Parcelable.Creator<MessageImpl>() { // from class: ly.count.android.sdk.messaging.ModulePush.MessageImpl.1
            @Override // android.os.Parcelable.Creator
            public MessageImpl createFromParcel(Parcel parcel) {
                HashMap map = new HashMap();
                parcel.readMap(map, ClassLoader.getSystemClassLoader());
                Countly.sharedInstance().L.d("[MessageImpl] read: " + ((String) map.get(ModulePush.KEY_ID)));
                return new MessageImpl(map);
            }

            @Override // android.os.Parcelable.Creator
            public MessageImpl[] newArray(int i) {
                return new MessageImpl[i];
            }
        };
        private final Integer badge;
        private final List<CountlyPush.Button> buttons;
        private final Map<String, String> data;
        final String id;
        private final Uri link;
        private final URL media;
        private final String message;
        private final String sound;
        private final String title;

        static class Button implements CountlyPush.Button {
            private final int icon;
            private final int index;
            private final Uri link;
            private final CountlyPush.Message message;
            private final String title;

            Button(CountlyPush.Message message, int i, String str, Uri uri) {
                this.message = message;
                this.index = i;
                this.title = str;
                this.link = uri;
                this.icon = 0;
            }

            Button(CountlyPush.Message message, int i, String str, Uri uri, int i2) {
                this.message = message;
                this.index = i;
                this.title = str;
                this.link = uri;
                this.icon = i2;
            }

            @Override // ly.count.android.sdk.messaging.CountlyPush.Button
            public int index() {
                return this.index;
            }

            @Override // ly.count.android.sdk.messaging.CountlyPush.Button
            public String title() {
                return this.title;
            }

            @Override // ly.count.android.sdk.messaging.CountlyPush.Button
            public Uri link() {
                return this.link;
            }

            @Override // ly.count.android.sdk.messaging.CountlyPush.Button
            public void recordAction(Context context) {
                this.message.recordAction(context, this.index);
            }

            @Override // ly.count.android.sdk.messaging.CountlyPush.Button
            public int icon() {
                return this.icon;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof Button)) {
                    return false;
                }
                Button button = (Button) obj;
                if (button.index != this.index) {
                    return false;
                }
                String str = button.title;
                if (str == null) {
                    if (this.title != null) {
                        return false;
                    }
                } else if (!str.equals(this.title)) {
                    return false;
                }
                Uri uri = button.link;
                if (uri == null) {
                    if (this.link != null) {
                        return false;
                    }
                } else if (!uri.equals(this.link) || button.icon != this.icon) {
                    return false;
                }
                return true;
            }
        }

        MessageImpl(Map<String, String> map) {
            Integer numValueOf;
            Uri uri;
            URL url;
            Uri uri2;
            this.data = map;
            String str = map.get(ModulePush.KEY_ID);
            this.id = str;
            this.title = map.get("title");
            this.message = map.get(ModulePush.KEY_MESSAGE);
            this.sound = map.get(ModulePush.KEY_SOUND);
            Countly.sharedInstance().L.d("[MessageImpl] constructed: " + str);
            try {
                numValueOf = map.containsKey(ModulePush.KEY_BADGE) ? Integer.valueOf(Integer.parseInt(map.get(ModulePush.KEY_BADGE))) : null;
            } catch (NumberFormatException unused) {
                Countly.sharedInstance().L.w("[MessageImpl] Bad badge value received, ignoring");
            }
            this.badge = numValueOf;
            if (map.get(ModulePush.KEY_LINK) != null) {
                try {
                    uri = Uri.parse(map.get(ModulePush.KEY_LINK));
                } catch (Throwable th) {
                    Countly.sharedInstance().L.w("[MessageImpl] Cannot parse message link", th);
                    uri = null;
                }
            } else {
                uri = null;
            }
            this.link = uri;
            try {
                url = map.containsKey(ModulePush.KEY_MEDIA) ? new URL(map.get(ModulePush.KEY_MEDIA)) : null;
            } catch (MalformedURLException unused2) {
                Countly.sharedInstance().L.w("[MessageImpl] Bad media value received, ignoring");
            }
            this.media = url;
            this.buttons = new ArrayList();
            String str2 = map.get(ModulePush.KEY_BUTTONS);
            if (str2 != null) {
                try {
                    JSONArray jSONArray = new JSONArray(str2);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        if (jSONObject.has(ModulePush.KEY_BUTTONS_TITLE) && jSONObject.has("l")) {
                            if (jSONObject.getString("l") != null) {
                                try {
                                    uri2 = Uri.parse(jSONObject.getString("l"));
                                } catch (Throwable th2) {
                                    Countly.sharedInstance().L.w("[MessageImpl] Cannot parse message link", th2);
                                    uri2 = null;
                                }
                            } else {
                                uri2 = null;
                            }
                            this.buttons.add(new Button(this, i + 1, jSONObject.getString(ModulePush.KEY_BUTTONS_TITLE), uri2));
                        }
                    }
                } catch (Throwable th3) {
                    Countly.sharedInstance().L.w("[MessageImpl] Failed to parse buttons JSON", th3);
                }
            }
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public String id() {
            return this.id;
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public String title() {
            return this.title;
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public String message() {
            return this.message;
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public String sound() {
            return this.sound;
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public Integer badge() {
            return this.badge;
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public Uri link() {
            return this.link;
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public URL media() {
            return this.media;
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public List<CountlyPush.Button> buttons() {
            return this.buttons;
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public Set<String> dataKeys() {
            return this.data.keySet();
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public boolean has(String str) {
            return this.data.containsKey(str);
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public String data(String str) {
            return this.data.get(str);
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public void recordAction(Context context) {
            recordAction(context, 0);
        }

        @Override // ly.count.android.sdk.messaging.CountlyPush.Message
        public void recordAction(Context context, int i) {
            if (Countly.sharedInstance().isInitialized()) {
                HashMap map = new HashMap();
                map.put("p", "a");
                map.put("i", this.id);
                map.put(ModulePush.PUSH_EVENT_ACTION_INDEX_KEY, String.valueOf(i));
                Countly.sharedInstance().events().recordEvent(ModulePush.PUSH_EVENT_ACTION, map, 1);
                return;
            }
            CountlyStore.cachePushData(this.id, String.valueOf(i), context);
        }

        public int hashCode() {
            return this.id.hashCode();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return this.id.hashCode();
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeMap(this.data);
            Countly.sharedInstance().L.d("[MessageImpl] written: " + this.data.get(ModulePush.KEY_ID));
        }
    }
}
