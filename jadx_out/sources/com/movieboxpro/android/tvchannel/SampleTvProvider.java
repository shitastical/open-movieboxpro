package com.movieboxpro.android.tvchannel;

import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import androidx.tvprovider.media.tv.Channel;
import androidx.tvprovider.media.tv.ChannelLogoUtils;
import androidx.tvprovider.media.tv.PreviewProgram;
import androidx.tvprovider.media.tv.TvContractCompat;
import androidx.tvprovider.media.tv.WatchNextProgram;
import com.jeffmony.downloader.database.VideoDownloadSQLiteHelper;
import com.movieboxpro.android.app.Constant;
import com.movieboxpro.android.tvchannel.scheduler.ClipData;
import com.movieboxpro.android.utils.PrefsUtils;
import com.movieboxpro.android.view.activity.MainActivity;
import com.movieboxpro.androidtv.R;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SampleTvProvider {
    private static final String APPS_LAUNCH_HOST = "com.movieboxpro.android.movieboxprotxchannels";
    static final int CHANNELS_COLUMN_BROWSABLE_INDEX = 2;
    static final int CHANNELS_COLUMN_ID_INDEX = 0;
    static final int CHANNELS_COLUMN_INTERNAL_PROVIDER_ID_INDEX = 1;
    private static final int COLUMN_WATCH_NEXT_COLUMN_BROWSABLE_INDEX = 2;
    private static final int COLUMN_WATCH_NEXT_ID_INDEX = 0;
    private static final int COLUMN_WATCH_NEXT_INTERNAL_PROVIDER_ID_INDEX = 1;
    private static final String PLAY_VIDEO_ACTION_PATH = "playvideo";
    static final int PROGRAMS_COLUMN_ID_INDEX = 0;
    static final int PROGRAMS_COLUMN_INTERNAL_INTERACTION_COUNT_INDEX = 4;
    static final int PROGRAMS_COLUMN_INTERNAL_INTERACTION_TYPE_INDEX = 3;
    static final int PROGRAMS_COLUMN_INTERNAL_PROVIDER_ID_INDEX = 1;
    static final int PROGRAMS_COLUMN_TITLE_INDEX = 2;
    private static final String SCHEME = "movieboxprotxchannels";
    private static final String START_APP_ACTION_PATH = "startapp";
    private static final String TAG = "SampleTvProvider";
    static final String[] CHANNELS_MAP_PROJECTION = {VideoDownloadSQLiteHelper.Columns._ID, "internal_provider_id", "browsable"};
    static final String[] PROGRAMS_MAP_PROJECTION = {VideoDownloadSQLiteHelper.Columns._ID, "internal_provider_id", TvContractCompat.ProgramColumns.COLUMN_TITLE, TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_TYPE, TvContractCompat.PreviewProgramColumns.COLUMN_INTERACTION_COUNT};
    private static final String[] WATCH_NEXT_MAP_PROJECTION = {VideoDownloadSQLiteHelper.Columns._ID, "internal_provider_id", "browsable"};
    private static final Uri PREVIEW_PROGRAMS_CONTENT_URI = Uri.parse("content://android.media.tv/preview_program");

    private SampleTvProvider() {
    }

    private static String createInputId(Context context) {
        return TvContractCompat.buildInputId(new ComponentName(context, MainActivity.class.getName()));
    }

    private static void writeChannelLogo(Context context, long channelId, int drawableId) {
        ChannelLogoUtils.storeChannelLogo(context, channelId, BitmapFactory.decodeResource(context.getResources(), drawableId));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void addWatchNextContinue(Context context, ClipData clipData) throws Throwable {
        String clipId = clipData.getClipId();
        String contentId = clipData.getContentId();
        Cursor cursor = null;
        try {
            Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.WatchNextPrograms.CONTENT_URI, WATCH_NEXT_MAP_PROJECTION, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    try {
                        if (!cursorQuery.isNull(1) && TextUtils.equals(clipId, cursorQuery.getString(1))) {
                            long j = cursorQuery.getLong(0);
                            if (cursorQuery.getInt(2) == 0) {
                                if (context.getContentResolver().delete(TvContractCompat.buildWatchNextProgramUri(j), null, null) < 1) {
                                    Log.e(TAG, "Delete program failed");
                                }
                            } else if (context.getContentResolver().delete(TvContractCompat.buildWatchNextProgramUri(j), null, null) < 1) {
                                Log.e(TAG, "Delete program failed");
                            }
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
            if (clipData.getCardImageUrl() == null) {
                clipData.setCardImageUrl("");
            }
            WatchNextProgram.Builder builder = new WatchNextProgram.Builder();
            ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) ((WatchNextProgram.Builder) builder.setType(4).setWatchNextType(0).setLastEngagementTimeUtcMillis(System.currentTimeMillis()).setTitle(clipData.getTitle())).setDescription(clipData.getDescription())).setPosterArtUri(Uri.parse(clipData.getCardImageUrl()))).setInternalProviderId(clipId).setIntentUri(Uri.parse("movieboxprotxchannels://com.movieboxpro.android.movieboxprotxchannels/playvideo/" + clipId + "-" + clipData.getBoxType() + "-continue")).setContentId(contentId).setLastPlaybackPositionMillis((int) clipData.getProgress()).setDurationMillis((int) clipData.getDuration());
            if (clipData.getBoxType() == 1) {
                builder.setPosterArtAspectRatio(5);
            } else {
                builder.setPosterArtAspectRatio(0);
            }
            Uri uriInsert = context.getContentResolver().insert(TvContractCompat.WatchNextPrograms.CONTENT_URI, builder.build().toContentValues());
            if (uriInsert == null || uriInsert.equals(Uri.EMPTY)) {
                Log.e(TAG, "Insert watch next program failed");
            }
            SampleContentDb.getInstance(context).updateClipProgress(clipId, clipData.getProgress());
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static void deleteWatchNextContinue(Context context, String clipId) throws Throwable {
        Cursor cursor = null;
        try {
            Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.WatchNextPrograms.CONTENT_URI, WATCH_NEXT_MAP_PROJECTION, null, null, null);
            if (cursorQuery != null) {
                while (cursorQuery.moveToNext()) {
                    try {
                        if (!cursorQuery.isNull(1) && TextUtils.equals(clipId, cursorQuery.getString(1))) {
                            if (context.getContentResolver().delete(TvContractCompat.buildWatchNextProgramUri(cursorQuery.getLong(0)), null, null) < 1) {
                                Log.e(TAG, "Delete program failed");
                            }
                            SampleContentDb.getInstance(context).deleteClipProgress(clipId);
                        }
                    } catch (Throwable th) {
                        th = th;
                        cursor = cursorQuery;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static long addChannel(Context context, Playlist playlist) {
        Uri uriInsert = context.getContentResolver().insert(TvContractCompat.Channels.CONTENT_URI, new Channel.Builder().setDisplayName(playlist.getName()).setDescription(playlist.getDescription()).setType(TvContractCompat.Channels.TYPE_PREVIEW).setInputId(createInputId(context)).setAppLinkIntentUri(Uri.parse("movieboxprotxchannels://com.movieboxpro.android.movieboxprotxchannels/startapp")).setInternalProviderId(playlist.getPlaylistId()).build().toContentValues());
        if (uriInsert == null || uriInsert.equals(Uri.EMPTY)) {
            Log.e(TAG, "Insert channel failed");
            return 0L;
        }
        long id = ContentUris.parseId(uriInsert);
        playlist.setChannelPublishedId(id);
        TvContractCompat.requestChannelBrowsable(context, id);
        writeChannelLogo(context, id, R.mipmap.ic_logo_round);
        List<Clip> clips = playlist.getClips();
        int size = clips.size();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < clips.size()) {
            Clip clip = clips.get(i);
            String clipId = clip.getClipId();
            String contentId = clip.getContentId();
            if (clip.getCardImageUrl() == null) {
                clip.setCardImageUrl("");
            }
            Uri uriInsert2 = context.getContentResolver().insert(PREVIEW_PROGRAMS_CONTENT_URI, ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) new PreviewProgram.Builder().setChannelId(id).setTitle(clip.getTitle())).setDescription(clip.getDescription())).setPosterArtUri(Uri.parse(clip.getCardImageUrl()))).setIntentUri(Uri.parse("movieboxprotxchannels://com.movieboxpro.android.movieboxprotxchannels/playvideo/" + clipId + "-" + clip.getBoxType())).setInternalProviderId(clipId).setContentId(contentId).setWeight(size).setPosterArtAspectRatio(5).setType(0).build().toContentValues());
            if (uriInsert2 == null || uriInsert2.equals(Uri.EMPTY)) {
                Log.e(TAG, "Insert program failed");
            } else {
                clip.setProgramId(ContentUris.parseId(uriInsert2));
            }
            sb.append(clip.getProgramId());
            sb.append("|");
            i++;
            size--;
        }
        PrefsUtils.getInstance().putString(Constant.Prefs.PREVIEWPROGRAM_IDS, sb.toString());
        return id;
    }

    public static void deleteChannel(Context context, long channelId) {
        if (context.getContentResolver().delete(TvContractCompat.buildChannelUri(channelId), null, null) < 1) {
            Log.e(TAG, "Delete channel failed");
        }
    }

    public static void deleteProgram(Context context, Clip clip) {
        deleteProgram(context, clip.getProgramId());
    }

    static void deleteProgram(Context context, long programId) {
        if (context.getContentResolver().delete(TvContractCompat.buildPreviewProgramUri(programId), null, null) < 1) {
            Log.e(TAG, "Delete program failed");
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void updateProgramClip(Context context, Clip clip) {
        Uri uriBuildPreviewProgramUri = TvContractCompat.buildPreviewProgramUri(clip.getProgramId());
        Cursor cursorQuery = context.getContentResolver().query(uriBuildPreviewProgramUri, null, null, null, null);
        if (cursorQuery == null) {
            if (cursorQuery != null) {
                cursorQuery.close();
                return;
            }
            return;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                Log.e(TAG, "Update program failed");
            }
            if (context.getContentResolver().update(uriBuildPreviewProgramUri, ((PreviewProgram.Builder) ((PreviewProgram.Builder) new PreviewProgram.Builder(PreviewProgram.fromCursor(cursorQuery)).setIntentUri(Uri.parse("movieboxprotxchannels://com.movieboxpro.android.movieboxprotxchannels/playvideo/" + clip.getClipId() + "-" + clip.getBoxType())).setTitle(clip.getTitle())).setPosterArtUri(Uri.parse(clip.getCardImageUrl()))).build().toContentValues(), null, null) < 1) {
                Log.e(TAG, "Update program failed");
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void updateChannelClip(Context context, Clip clip, long channelId) {
        Uri uriBuildPreviewProgramUri = TvContractCompat.buildPreviewProgramUri(clip.getProgramId());
        Cursor cursorQuery = context.getContentResolver().query(uriBuildPreviewProgramUri, null, null, null, null);
        if (cursorQuery == null) {
            if (cursorQuery != null) {
                cursorQuery.close();
                return;
            }
            return;
        }
        try {
            if (!cursorQuery.moveToFirst()) {
                Log.e(TAG, "Update program failed");
            }
            if (context.getContentResolver().update(uriBuildPreviewProgramUri, ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) new PreviewProgram.Builder(PreviewProgram.fromCursor(cursorQuery)).setChannelId(channelId).setTitle(clip.getTitle())).setDescription(clip.getDescription())).setPosterArtUri(Uri.parse(clip.getCardImageUrl()))).setIntentUri(Uri.parse("movieboxprotxchannels://com.movieboxpro.android.movieboxprotxchannels/playvideo/" + clip.getClipId() + "-" + clip.getBoxType())).setInternalProviderId(clip.getClipId()).setContentId(clip.getContentId()).setPosterArtAspectRatio(5).setType(0).build().toContentValues(), null, null) < 1) {
                Log.e(TAG, "Update program failed");
            }
            if (cursorQuery != null) {
                cursorQuery.close();
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static void publishProgram(Context context, Clip clip, long channelId, int weight) {
        String clipId = clip.getClipId();
        Uri uriInsert = context.getContentResolver().insert(PREVIEW_PROGRAMS_CONTENT_URI, ((PreviewProgram.Builder) ((PreviewProgram.Builder) ((PreviewProgram.Builder) new PreviewProgram.Builder().setChannelId(channelId).setTitle(clip.getTitle())).setDescription(clip.getDescription())).setPosterArtUri(Uri.parse(clip.getCardImageUrl()))).setIntentUri(Uri.parse("movieboxprotxchannels://com.movieboxpro.android.movieboxprotxchannels/playvideo/" + clipId)).setPreviewVideoUri(Uri.parse(clip.getPreviewVideoUrl())).setInternalProviderId(clipId).setWeight(weight).setPosterArtAspectRatio(clip.getAspectRatio()).setType(0).build().toContentValues());
        if (uriInsert == null || uriInsert.equals(Uri.EMPTY)) {
            Log.e(TAG, "Insert program failed");
        } else {
            clip.setProgramId(ContentUris.parseId(uriInsert));
        }
    }

    static String decodeVideoId(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments.size() == 2 && TextUtils.equals(pathSegments.get(0), PLAY_VIDEO_ACTION_PATH)) {
            return pathSegments.get(1);
        }
        return "";
    }

    static void setProgramViewCount(Context context, long programId, int numberOfViews) {
        Cursor cursorQuery = context.getContentResolver().query(TvContractCompat.buildPreviewProgramUri(programId), null, null, null, null);
        try {
            if (!cursorQuery.moveToFirst()) {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            } else {
                if (context.getContentResolver().update(TvContractCompat.buildPreviewProgramUri(programId), new PreviewProgram.Builder(PreviewProgram.fromCursor(cursorQuery)).setInteractionCount(numberOfViews).setInteractionType(0).build().toContentValues(), null, null) != 1) {
                    Log.e(TAG, "Update program failed");
                }
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Throwable th) {
            if (cursorQuery != null) {
                try {
                    cursorQuery.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }
}
