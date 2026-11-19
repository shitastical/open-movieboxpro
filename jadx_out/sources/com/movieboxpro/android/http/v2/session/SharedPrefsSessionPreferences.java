package com.movieboxpro.android.http.v2.session;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SharedPrefsSessionPreferences.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u001e\u0010\f\u001a\u00020\r2\u0014\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000fH\u0016J\u0016\u0010\u0010\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\n0\u0012H\u0016R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SharedPrefsSessionPreferences;", "Lcom/movieboxpro/android/http/v2/session/SessionPreferences;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "preferences", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "get", "", "key", "replace", "", "values", "", "clear", UserMetadata.KEYDATA_FILENAME, "", "Companion", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class SharedPrefsSessionPreferences implements SessionPreferences {
    public static final String FILE_NAME = "api_v2_session";
    private final SharedPreferences preferences;

    public SharedPrefsSessionPreferences(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.preferences = context.getSharedPreferences(FILE_NAME, 0);
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionPreferences
    public String get(String key) {
        Intrinsics.checkNotNullParameter(key, "key");
        return this.preferences.getString(key, null);
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionPreferences
    public void replace(Map<String, String> values) {
        Intrinsics.checkNotNullParameter(values, "values");
        SharedPreferences.Editor editorEdit = this.preferences.edit();
        for (Map.Entry<String, String> entry : values.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                editorEdit.remove(key);
            } else {
                editorEdit.putString(key, value);
            }
        }
        if (!editorEdit.commit()) {
            throw new IllegalStateException("Failed to persist API v2 session credentials".toString());
        }
    }

    @Override // com.movieboxpro.android.http.v2.session.SessionPreferences
    public void clear(Set<String> keys) {
        Intrinsics.checkNotNullParameter(keys, "keys");
        SharedPreferences.Editor editorEdit = this.preferences.edit();
        Iterator<T> it = keys.iterator();
        while (it.hasNext()) {
            editorEdit.remove((String) it.next());
        }
        if (!editorEdit.commit()) {
            throw new IllegalStateException("Failed to clear API v2 session credentials".toString());
        }
    }
}
