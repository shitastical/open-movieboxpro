package com.franmontiel.persistentcookiejar.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import master.flame.danmaku.danmaku.parser.IDataSource;
import okhttp3.Cookie;

/* JADX INFO: loaded from: classes4.dex */
public class SharedPrefsCookiePersistor implements CookiePersistor {
    private final SharedPreferences sharedPreferences;

    public SharedPrefsCookiePersistor(Context context) {
        this(context.getSharedPreferences("CookiePersistence", 0));
    }

    public SharedPrefsCookiePersistor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override // com.franmontiel.persistentcookiejar.persistence.CookiePersistor
    public List<Cookie> loadAll() {
        ArrayList arrayList = new ArrayList(this.sharedPreferences.getAll().size());
        Iterator<Map.Entry<String, ?>> it = this.sharedPreferences.getAll().entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(new SerializableCookie().decode((String) it.next().getValue()));
        }
        return arrayList;
    }

    @Override // com.franmontiel.persistentcookiejar.persistence.CookiePersistor
    public void saveAll(Collection<Cookie> collection) {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        for (Cookie cookie : collection) {
            if (cookie.persistent()) {
                editorEdit.putString(createCookieKey(cookie), new SerializableCookie().encode(cookie));
            }
        }
        editorEdit.apply();
    }

    @Override // com.franmontiel.persistentcookiejar.persistence.CookiePersistor
    public void removeAll(Collection<Cookie> collection) {
        SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
        Iterator<Cookie> it = collection.iterator();
        while (it.hasNext()) {
            editorEdit.remove(createCookieKey(it.next()));
        }
        editorEdit.apply();
    }

    private static String createCookieKey(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.secure() ? IDataSource.SCHEME_HTTPS_TAG : IDataSource.SCHEME_HTTP_TAG);
        sb.append("://");
        sb.append(cookie.domain());
        sb.append(cookie.path());
        sb.append("|");
        sb.append(cookie.name());
        return sb.toString();
    }

    @Override // com.franmontiel.persistentcookiejar.persistence.CookiePersistor
    public void clear() {
        this.sharedPreferences.edit().clear().apply();
    }
}
