package com.movieboxpro.android.http.v2.session;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: compiled from: SessionPreferences.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u0003H&J\u001e\u0010\u0005\u001a\u00020\u00062\u0014\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\bH&J\u0016\u0010\t\u001a\u00020\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH&¨\u0006\fÀ\u0006\u0003"}, d2 = {"Lcom/movieboxpro/android/http/v2/session/SessionPreferences;", "", "get", "", "key", "replace", "", "values", "", "clear", UserMetadata.KEYDATA_FILENAME, "", "app_movie_tvRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface SessionPreferences {
    void clear(Set<String> keys);

    String get(String key);

    void replace(Map<String, String> values);
}
