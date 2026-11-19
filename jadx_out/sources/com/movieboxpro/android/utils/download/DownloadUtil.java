package com.movieboxpro.android.utils.download;

import android.content.Context;
import android.os.Build;
import android.os.ext.SdkExtensions;
import android.text.TextUtils;
import android.util.Log;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.TrackSelectionOverride;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.Tracks;
import androidx.media3.database.DatabaseProvider;
import androidx.media3.database.StandaloneDatabaseProvider;
import androidx.media3.datasource.DataSource;
import androidx.media3.datasource.DefaultDataSource;
import androidx.media3.datasource.cache.Cache;
import androidx.media3.datasource.cache.CacheDataSource;
import androidx.media3.datasource.cache.NoOpCacheEvictor;
import androidx.media3.datasource.cache.SimpleCache;
import androidx.media3.datasource.okhttp.OkHttpDataSource;
import androidx.media3.exoplayer.DefaultRendererCapabilitiesList;
import androidx.media3.exoplayer.DefaultRenderersFactory;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.hls.HlsMediaSource;
import androidx.media3.exoplayer.offline.DownloadHelper;
import androidx.media3.exoplayer.offline.DownloadManager;
import androidx.media3.exoplayer.offline.DownloadNotificationHelper;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.media3.exoplayer.scheduler.Requirements;
import com.movieboxpro.android.app.App;
import com.movieboxpro.android.service.MyDownloadService;
import com.movieboxpro.android.utils.DownloadFileHelper;
import com.movieboxpro.android.utils.SettingManager;
import j$.util.concurrent.ConcurrentHashMap;
import java.io.File;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/* JADX INFO: loaded from: classes5.dex */
public final class DownloadUtil {
    private static final String DOWNLOAD_CONTENT_DIRECTORY = "downloads";
    public static final String DOWNLOAD_NOTIFICATION_CHANNEL_ID = "download_channel";
    private static final String TAG = "DemoUtil";
    private static DatabaseProvider databaseProvider;
    private static File downloadDirectory;
    private static DownloadNotificationHelper downloadNotificationHelper;
    private static DataSource.Factory httpDataSourceFactory;
    private static final ConcurrentHashMap<String, DataSource.Factory> dataSourceFactories = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Cache> downloadCaches = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, DownloadManager> downloadManagers = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, DownloadTracker> downloadTrackers = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, DownloadHelper> downloadHelpers = new ConcurrentHashMap<>();

    public static boolean useExtensionRenderers() {
        return false;
    }

    public static RenderersFactory buildRenderersFactory(Context context, boolean preferExtensionRenderer) {
        int i;
        if (useExtensionRenderers()) {
            i = preferExtensionRenderer ? 2 : 1;
        } else {
            i = 0;
        }
        return new DefaultRenderersFactory(context.getApplicationContext()).setExtensionRendererMode(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onDownloadStart(String id) {
        DownloadFileHelper.INSTANCE.getInstance().updateDownloadStatus(4, id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void onDownloadFailed(String id, String msg) {
        DownloadFileHelper.INSTANCE.getInstance().updateDownloadError(id, msg);
    }

    private static ArrayList<String> getStrings(List<String> languages) {
        ArrayList<String> arrayList = new ArrayList<>();
        Iterator<String> it = languages.iterator();
        while (it.hasNext()) {
            String str = it.next().split("->")[0];
            if ("zh-CN".equals(str)) {
                arrayList.add("zh");
            } else {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static void doDownload(final String id, String url) {
        onDownloadStart(id);
        final ArrayList<String> strings = getStrings(SettingManager.INSTANCE.getDownloadAudioLanguage(App.getContext()));
        MediaItem mediaItemBuild = new MediaItem.Builder().setUri(url).setMimeType("application/x-mpegURL").build();
        DownloadHelper downloadHelper = new DownloadHelper(mediaItemBuild, new HlsMediaSource.Factory(getHttpDataSourceFactory(App.getContext())).setAllowChunklessPreparation(false).createMediaSource(mediaItemBuild), TrackSelectionParameters.DEFAULT, new DefaultRendererCapabilitiesList.Factory(App.getContext()).createRendererCapabilitiesList(), false);
        downloadHelper.prepare(new DownloadHelper.Callback() { // from class: com.movieboxpro.android.utils.download.DownloadUtil.1
            @Override // androidx.media3.exoplayer.offline.DownloadHelper.Callback
            public void onPrepared(DownloadHelper helper, boolean tracksInfoAvailable) {
                if (helper.getPeriodCount() == 0) {
                    DownloadUtil.onDownloadFailed(id, "No tracks,PeriodCount:0");
                    return;
                }
                Tracks tracks = helper.getTracks(0);
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < tracks.getGroups().size(); i++) {
                    TrackGroup mediaTrackGroup = tracks.getGroups().get(i).getMediaTrackGroup();
                    if (mediaTrackGroup.type == 1) {
                        arrayList.add(mediaTrackGroup);
                    }
                }
                String str = null;
                int i2 = -1;
                for (String str2 : strings) {
                    for (int i3 = 0; i3 < arrayList.size(); i3++) {
                        TrackGroup trackGroup = (TrackGroup) arrayList.get(i3);
                        for (int i4 = 0; i4 < trackGroup.length; i4++) {
                            if (str2.equalsIgnoreCase(trackGroup.getFormat(i4).language)) {
                                str = str2;
                                i2 = i3;
                                break;
                            }
                        }
                        if (i2 != -1) {
                            break;
                        }
                    }
                    if (i2 != -1) {
                        break;
                    }
                }
                if (i2 != -1) {
                    Log.d("HLS", "找到目标语言: " + str);
                } else {
                    if (arrayList.isEmpty()) {
                        Log.d("HLS", "没有可用的音轨组");
                        return;
                    }
                    TrackGroup trackGroup2 = (TrackGroup) arrayList.get(0);
                    if (trackGroup2.length <= 0) {
                        Log.d("HLS", "找不到音轨");
                        return;
                    }
                    Format format = trackGroup2.getFormat(0);
                    Log.d("HLS", "找不到匹配语言，使用第一个音轨，语言: " + (format.language != null ? format.language : "unknown"));
                    i2 = 0;
                }
                helper.clearTrackSelections(0);
                ArrayList arrayList2 = new ArrayList();
                TrackGroup trackGroup3 = (TrackGroup) arrayList.get(i2);
                for (int i5 = 0; i5 < trackGroup3.length; i5++) {
                    arrayList2.add(Integer.valueOf(i5));
                }
                helper.addTrackSelection(0, new TrackSelectionParameters.Builder().setOverrideForType(new TrackSelectionOverride(trackGroup3, arrayList2)).build());
                DownloadService.sendAddDownload(App.getContext(), MyDownloadService.class, helper.getDownloadRequest(id, null), false);
                DownloadUtil.onDownloadStart(id);
                helper.release();
                DownloadUtil.downloadHelpers.remove(id);
            }

            @Override // androidx.media3.exoplayer.offline.DownloadHelper.Callback
            public void onPrepareError(DownloadHelper helper, IOException e) {
                String message = e.getMessage();
                DownloadUtil.onDownloadFailed(id, e.getMessage());
                if (message != null) {
                    Log.d("onPrepareError", message);
                }
                helper.release();
                DownloadUtil.downloadHelpers.remove(id);
            }
        });
        downloadHelpers.put(id, downloadHelper);
    }

    public static void pauseDownload(String id) {
        ConcurrentHashMap<String, DownloadHelper> concurrentHashMap = downloadHelpers;
        DownloadHelper downloadHelper = concurrentHashMap.get(id);
        if (downloadHelper != null) {
            downloadHelper.release();
            concurrentHashMap.remove(id);
        }
    }

    public static synchronized DataSource.Factory getHttpDataSourceFactory(Context context) {
        DataSource.Factory factory = httpDataSourceFactory;
        if (factory != null) {
            return factory;
        }
        context.getApplicationContext();
        final OkHttpClient okHttpClient = OkHttpProvider.get();
        if (Build.VERSION.SDK_INT >= 30 && SdkExtensions.getExtensionVersion(31) >= 7) {
            OkHttpDataSource.Factory factory2 = new OkHttpDataSource.Factory(new Call.Factory() { // from class: com.movieboxpro.android.utils.download.DownloadUtil.2
                @Override // okhttp3.Call.Factory
                public Call newCall(Request request) {
                    return okHttpClient.newCall(request);
                }
            });
            httpDataSourceFactory = factory2;
            return factory2;
        }
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
        CookieHandler.setDefault(cookieManager);
        OkHttpDataSource.Factory factory3 = new OkHttpDataSource.Factory(new Call.Factory() { // from class: com.movieboxpro.android.utils.download.DownloadUtil.3
            @Override // okhttp3.Call.Factory
            public Call newCall(Request request) {
                return okHttpClient.newCall(request);
            }
        });
        httpDataSourceFactory = factory3;
        return factory3;
    }

    public static synchronized DataSource.Factory getDataSourceFactory(Context context, String path) {
        DataSource.Factory factoryBuildReadOnlyCacheDataSource;
        String strNormalizePathKey = normalizePathKey(context, path);
        ConcurrentHashMap<String, DataSource.Factory> concurrentHashMap = dataSourceFactories;
        factoryBuildReadOnlyCacheDataSource = concurrentHashMap.get(strNormalizePathKey);
        if (factoryBuildReadOnlyCacheDataSource == null) {
            Context applicationContext = context.getApplicationContext();
            factoryBuildReadOnlyCacheDataSource = buildReadOnlyCacheDataSource(new DefaultDataSource.Factory(applicationContext, getHttpDataSourceFactory(applicationContext)), getDownloadCache(applicationContext, strNormalizePathKey));
            concurrentHashMap.put(strNormalizePathKey, factoryBuildReadOnlyCacheDataSource);
        }
        return factoryBuildReadOnlyCacheDataSource;
    }

    private static String normalizePathKey(Context context, String path) {
        if (TextUtils.isEmpty(path)) {
            return getDownloadDirectory(context).getAbsolutePath();
        }
        return new File(path).getAbsolutePath();
    }

    public static synchronized DownloadNotificationHelper getDownloadNotificationHelper(Context context) {
        if (downloadNotificationHelper == null) {
            downloadNotificationHelper = new DownloadNotificationHelper(context, DOWNLOAD_NOTIFICATION_CHANNEL_ID);
        }
        return downloadNotificationHelper;
    }

    public static synchronized DownloadManager getDownloadManager(Context context, String path) {
        String strNormalizePathKey;
        strNormalizePathKey = normalizePathKey(context, path);
        ensureDownloadManagerInitialized(context, strNormalizePathKey);
        return downloadManagers.get(strNormalizePathKey);
    }

    public static synchronized DownloadTracker getDownloadTracker(Context context, String path) {
        String strNormalizePathKey;
        strNormalizePathKey = normalizePathKey(context, path);
        ensureDownloadManagerInitialized(context, strNormalizePathKey);
        return downloadTrackers.get(strNormalizePathKey);
    }

    public static synchronized Collection<DownloadTracker> getAllDownloadTrackers(Context context) {
        ensureDownloadManagerInitialized(context, normalizePathKey(context, null));
        return new ArrayList(downloadTrackers.values());
    }

    private static synchronized Cache getDownloadCache(Context context, String pathKey) {
        Cache cache;
        ConcurrentHashMap<String, Cache> concurrentHashMap = downloadCaches;
        cache = concurrentHashMap.get(pathKey);
        if (cache == null) {
            SimpleCache simpleCache = new SimpleCache(new File(pathKey, DOWNLOAD_CONTENT_DIRECTORY), new NoOpCacheEvictor(), getDatabaseProvider(context));
            concurrentHashMap.put(pathKey, simpleCache);
            cache = simpleCache;
        }
        return cache;
    }

    private static synchronized void ensureDownloadManagerInitialized(Context context, String pathKey) {
        ConcurrentHashMap<String, DownloadManager> concurrentHashMap = downloadManagers;
        if (concurrentHashMap.containsKey(pathKey)) {
            return;
        }
        DownloadManager downloadManager = new DownloadManager(context, getDatabaseProvider(context), getDownloadCache(context, pathKey), getHttpDataSourceFactory(context), Executors.newFixedThreadPool(6));
        downloadManager.setRequirements(new Requirements(1));
        DownloadTracker downloadTracker = new DownloadTracker(context, getHttpDataSourceFactory(context), downloadManager);
        concurrentHashMap.put(pathKey, downloadManager);
        downloadTrackers.put(pathKey, downloadTracker);
    }

    private static synchronized DatabaseProvider getDatabaseProvider(Context context) {
        if (databaseProvider == null) {
            databaseProvider = new StandaloneDatabaseProvider(context);
        }
        return databaseProvider;
    }

    private static synchronized File getDownloadDirectory(Context context) {
        if (downloadDirectory == null) {
            File externalFilesDir = context.getExternalFilesDir(null);
            downloadDirectory = externalFilesDir;
            if (externalFilesDir == null) {
                downloadDirectory = context.getFilesDir();
            }
        }
        return downloadDirectory;
    }

    private static CacheDataSource.Factory buildReadOnlyCacheDataSource(DataSource.Factory upstreamFactory, Cache cache) {
        return new CacheDataSource.Factory().setCache(cache).setUpstreamDataSourceFactory(upstreamFactory).setCacheWriteDataSinkFactory(null).setFlags(2);
    }

    private DownloadUtil() {
    }
}
