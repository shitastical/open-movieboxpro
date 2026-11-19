package com.movieboxpro.android.tvchannel;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public class SampleContentDb {
    private static final String CLIPS_PROGRESS_DB = "clips_progress_db";
    private static final String REMOVED_CLIPS_KEY = "removed_clips_key";
    private static final String SAMPLE_LOCAL_DB = "sample_local_db";
    private static SampleContentDb sSampleContentDb;
    private final Map<String, Long> mClipsProgress;
    private final Context mContext;
    private final Set<String> mRemovedClips;

    private SampleContentDb(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.mContext = applicationContext;
        this.mRemovedClips = new HashSet(applicationContext.getSharedPreferences(SAMPLE_LOCAL_DB, 0).getStringSet(REMOVED_CLIPS_KEY, new HashSet()));
        this.mClipsProgress = new HashMap(context.getSharedPreferences(CLIPS_PROGRESS_DB, 0).getAll());
    }

    public static SampleContentDb getInstance(Context context) {
        if (sSampleContentDb == null) {
            sSampleContentDb = new SampleContentDb(context);
        }
        return sSampleContentDb;
    }

    boolean isClipRemoved(String clipId) {
        return this.mRemovedClips.contains(clipId);
    }

    public void addRemovedClip(String clipId) {
        if (this.mRemovedClips.contains(clipId)) {
            return;
        }
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(SAMPLE_LOCAL_DB, 0);
        this.mRemovedClips.add(clipId);
        sharedPreferences.edit().putStringSet(REMOVED_CLIPS_KEY, this.mRemovedClips).apply();
    }

    void updateClipProgress(String clipId, long progress) {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(CLIPS_PROGRESS_DB, 0);
        this.mClipsProgress.put(clipId, Long.valueOf(progress));
        sharedPreferences.edit().putLong(clipId, progress).apply();
    }

    void deleteClipProgress(String clipId) {
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(CLIPS_PROGRESS_DB, 0);
        this.mClipsProgress.remove(clipId);
        sharedPreferences.edit().remove(clipId).apply();
    }

    long getClipProgress(String clipId) {
        if (this.mClipsProgress.containsKey(clipId)) {
            return this.mClipsProgress.get(clipId).longValue();
        }
        return -1L;
    }
}
