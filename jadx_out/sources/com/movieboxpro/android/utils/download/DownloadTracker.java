package com.movieboxpro.android.utils.download;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.fragment.app.FragmentManager;
import androidx.media3.common.C;
import androidx.media3.common.DrmInitData;
import androidx.media3.common.Format;
import androidx.media3.common.MediaItem;
import androidx.media3.common.TrackGroup;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.Util;
import androidx.media3.datasource.DataSource;
import androidx.media3.exoplayer.RenderersFactory;
import androidx.media3.exoplayer.drm.DrmSession;
import androidx.media3.exoplayer.drm.DrmSessionEventListener;
import androidx.media3.exoplayer.drm.OfflineLicenseHelper;
import androidx.media3.exoplayer.offline.Download;
import androidx.media3.exoplayer.offline.DownloadCursor;
import androidx.media3.exoplayer.offline.DownloadHelper;
import androidx.media3.exoplayer.offline.DownloadIndex;
import androidx.media3.exoplayer.offline.DownloadManager;
import androidx.media3.exoplayer.offline.DownloadRequest;
import androidx.media3.exoplayer.offline.DownloadService;
import androidx.media3.exoplayer.scheduler.Requirements;
import androidx.media3.exoplayer.source.TrackGroupArray;
import androidx.media3.exoplayer.trackselection.MappingTrackSelector;
import com.google.common.base.Preconditions;
import com.movieboxpro.android.service.MyDownloadService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/* JADX INFO: loaded from: classes5.dex */
public class DownloadTracker {
    private static final String TAG = "DownloadTracker";
    private final Context context;
    private final DataSource.Factory dataSourceFactory;
    private final DownloadIndex downloadIndex;
    private StartDownloadDialogHelper startDownloadDialogHelper;
    private final CopyOnWriteArraySet<Listener> listeners = new CopyOnWriteArraySet<>();
    private final HashMap<Uri, Download> downloads = new HashMap<>();

    public interface Listener {
        void onDownloadsChanged(Download download);
    }

    public DownloadTracker(Context context, DataSource.Factory dataSourceFactory, DownloadManager downloadManager) {
        this.context = context.getApplicationContext();
        this.dataSourceFactory = dataSourceFactory;
        this.downloadIndex = downloadManager.getDownloadIndex();
        downloadManager.addListener(new DownloadManagerListener());
        loadDownloads();
    }

    public void addListener(Listener listener) {
        this.listeners.add((Listener) Preconditions.checkNotNull(listener));
    }

    public void removeListener(Listener listener) {
        this.listeners.remove(listener);
    }

    public boolean isDownloaded(MediaItem mediaItem) {
        Download download = this.downloads.get(((MediaItem.LocalConfiguration) Preconditions.checkNotNull(mediaItem.localConfiguration)).uri);
        return (download == null || download.state == 4) ? false : true;
    }

    public DownloadRequest getDownloadRequest(Uri uri) {
        Download download = this.downloads.get(uri);
        if (download == null || download.state == 4) {
            return null;
        }
        return download.request;
    }

    public void toggleDownload(FragmentManager fragmentManager, MediaItem mediaItem, RenderersFactory renderersFactory) {
        Download download = this.downloads.get(((MediaItem.LocalConfiguration) Preconditions.checkNotNull(mediaItem.localConfiguration)).uri);
        if (download != null && download.state != 4) {
            DownloadService.sendRemoveDownload(this.context, MyDownloadService.class, download.request.id, false);
            return;
        }
        StartDownloadDialogHelper startDownloadDialogHelper = this.startDownloadDialogHelper;
        if (startDownloadDialogHelper != null) {
            startDownloadDialogHelper.release();
        }
        this.startDownloadDialogHelper = new StartDownloadDialogHelper(fragmentManager, DownloadHelper.forMediaItem(this.context, mediaItem, renderersFactory, this.dataSourceFactory), mediaItem);
    }

    private void loadDownloads() {
        try {
            DownloadCursor downloads = this.downloadIndex.getDownloads(new int[0]);
            while (downloads.moveToNext()) {
                try {
                    Download download = downloads.getDownload();
                    this.downloads.put(download.request.uri, download);
                } catch (Throwable th) {
                    if (downloads != null) {
                        try {
                            downloads.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                    }
                    throw th;
                }
            }
            if (downloads != null) {
                downloads.close();
            }
        } catch (IOException e) {
            Log.w(TAG, "Failed to query downloads", e);
        }
    }

    private class DownloadManagerListener implements DownloadManager.Listener {
        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onDownloadsPausedChanged(DownloadManager downloadManager, boolean z) {
            DownloadManager.Listener.CC.$default$onDownloadsPausedChanged(this, downloadManager, z);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onIdle(DownloadManager downloadManager) {
            DownloadManager.Listener.CC.$default$onIdle(this, downloadManager);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onInitialized(DownloadManager downloadManager) {
            DownloadManager.Listener.CC.$default$onInitialized(this, downloadManager);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onRequirementsStateChanged(DownloadManager downloadManager, Requirements requirements, int i) {
            DownloadManager.Listener.CC.$default$onRequirementsStateChanged(this, downloadManager, requirements, i);
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public /* synthetic */ void onWaitingForRequirementsChanged(DownloadManager downloadManager, boolean z) {
            DownloadManager.Listener.CC.$default$onWaitingForRequirementsChanged(this, downloadManager, z);
        }

        private DownloadManagerListener() {
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public void onDownloadChanged(DownloadManager downloadManager, Download download, Exception finalException) {
            DownloadTracker.this.downloads.put(download.request.uri, download);
            Log.d("DownloadManagerListener", String.valueOf(download.state));
            int i = download.state;
            Iterator it = DownloadTracker.this.listeners.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onDownloadsChanged(download);
            }
        }

        @Override // androidx.media3.exoplayer.offline.DownloadManager.Listener
        public void onDownloadRemoved(DownloadManager downloadManager, Download download) {
            DownloadTracker.this.downloads.remove(download.request.uri);
            Iterator it = DownloadTracker.this.listeners.iterator();
            while (it.hasNext()) {
                ((Listener) it.next()).onDownloadsChanged(download);
            }
        }
    }

    private final class StartDownloadDialogHelper implements DownloadHelper.Callback, DialogInterface.OnDismissListener {
        private final DownloadHelper downloadHelper;
        private final FragmentManager fragmentManager;
        private byte[] keySetId;
        private final MediaItem mediaItem;
        private boolean tracksInfoAvailable;
        private WidevineOfflineLicenseFetchTask widevineOfflineLicenseFetchTask;

        public StartDownloadDialogHelper(FragmentManager fragmentManager, DownloadHelper downloadHelper, MediaItem mediaItem) {
            this.fragmentManager = fragmentManager;
            this.downloadHelper = downloadHelper;
            this.mediaItem = mediaItem;
            downloadHelper.prepare(this);
        }

        public void release() {
            this.downloadHelper.release();
            WidevineOfflineLicenseFetchTask widevineOfflineLicenseFetchTask = this.widevineOfflineLicenseFetchTask;
            if (widevineOfflineLicenseFetchTask != null) {
                widevineOfflineLicenseFetchTask.cancel();
            }
        }

        @Override // androidx.media3.exoplayer.offline.DownloadHelper.Callback
        public void onPrepared(DownloadHelper helper, boolean tracksInfoAvailable) {
            this.tracksInfoAvailable = tracksInfoAvailable;
            Format firstFormatWithDrmInitData = getFirstFormatWithDrmInitData(helper);
            if (firstFormatWithDrmInitData == null) {
                onDownloadPrepared(helper);
                return;
            }
            if (!hasNonNullWidevineSchemaData(firstFormatWithDrmInitData.drmInitData)) {
                Toast.makeText(DownloadTracker.this.context, "download_start_error_offline_license", 1).show();
                Log.e(DownloadTracker.TAG, "Downloading content where DRM scheme data is not located in the manifest is not supported");
            } else {
                WidevineOfflineLicenseFetchTask widevineOfflineLicenseFetchTask = new WidevineOfflineLicenseFetchTask(firstFormatWithDrmInitData, this.mediaItem.localConfiguration.drmConfiguration, DownloadTracker.this.dataSourceFactory, this, helper);
                this.widevineOfflineLicenseFetchTask = widevineOfflineLicenseFetchTask;
                widevineOfflineLicenseFetchTask.execute();
            }
        }

        @Override // androidx.media3.exoplayer.offline.DownloadHelper.Callback
        public void onPrepareError(DownloadHelper helper, IOException e) {
            boolean z = e instanceof DownloadHelper.LiveContentUnsupportedException;
            String str = z ? "download_live_unsupported" : "download_start_error";
            String str2 = z ? "Downloading live content unsupported" : "Failed to start download";
            Toast.makeText(DownloadTracker.this.context, str, 1).show();
            Log.e(DownloadTracker.TAG, str2, e);
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            this.downloadHelper.release();
        }

        private Format getFirstFormatWithDrmInitData(DownloadHelper helper) {
            if (!this.tracksInfoAvailable) {
                return null;
            }
            for (int i = 0; i < helper.getPeriodCount(); i++) {
                MappingTrackSelector.MappedTrackInfo mappedTrackInfo = helper.getMappedTrackInfo(i);
                for (int i2 = 0; i2 < mappedTrackInfo.getRendererCount(); i2++) {
                    TrackGroupArray trackGroups = mappedTrackInfo.getTrackGroups(i2);
                    for (int i3 = 0; i3 < trackGroups.length; i3++) {
                        TrackGroup trackGroup = trackGroups.get(i3);
                        for (int i4 = 0; i4 < trackGroup.length; i4++) {
                            Format format = trackGroup.getFormat(i4);
                            if (format.drmInitData != null) {
                                return format;
                            }
                        }
                    }
                }
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onOfflineLicenseFetched(DownloadHelper helper, byte[] keySetId) {
            this.keySetId = keySetId;
            onDownloadPrepared(helper);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onOfflineLicenseFetchedError(DrmSession.DrmSessionException e) {
            Toast.makeText(DownloadTracker.this.context, "download_start_error_offline_license", 1).show();
            Log.e(DownloadTracker.TAG, "Failed to fetch offline DRM license", e);
        }

        private void onDownloadPrepared(DownloadHelper helper) {
            if (helper.getPeriodCount() == 0) {
                Log.d(DownloadTracker.TAG, "No periods found. Downloading entire stream.");
                startDownload();
                this.downloadHelper.release();
            } else {
                if (this.tracksInfoAvailable) {
                    return;
                }
                Log.d(DownloadTracker.TAG, "Tracks info is unavailable. Downloading entire stream.");
                startDownload();
                this.downloadHelper.release();
            }
        }

        private boolean hasNonNullWidevineSchemaData(DrmInitData drmInitData) {
            for (int i = 0; i < drmInitData.schemeDataCount; i++) {
                DrmInitData.SchemeData schemeData = drmInitData.get(i);
                if (schemeData.matches(C.WIDEVINE_UUID) && schemeData.hasData()) {
                    return true;
                }
            }
            return false;
        }

        private void startDownload() {
            startDownload(buildDownloadRequest());
        }

        private void startDownload(DownloadRequest downloadRequest) {
            DownloadService.sendAddDownload(DownloadTracker.this.context, MyDownloadService.class, downloadRequest, false);
        }

        private DownloadRequest buildDownloadRequest() {
            return this.downloadHelper.getDownloadRequest(Util.getUtf8Bytes((String) Preconditions.checkNotNull(this.mediaItem.mediaMetadata.title.toString()))).copyWithKeySetId(this.keySetId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class WidevineOfflineLicenseFetchTask {
        private final DataSource.Factory dataSourceFactory;
        private final StartDownloadDialogHelper dialogHelper;
        private final DownloadHelper downloadHelper;
        private final MediaItem.DrmConfiguration drmConfiguration;
        private DrmSession.DrmSessionException drmSessionException;
        private final ExecutorService executorService;
        private final Format format;
        Future<?> future;
        private byte[] keySetId;

        public WidevineOfflineLicenseFetchTask(Format format, MediaItem.DrmConfiguration drmConfiguration, DataSource.Factory dataSourceFactory, StartDownloadDialogHelper dialogHelper, DownloadHelper downloadHelper) {
            Preconditions.checkState(drmConfiguration.scheme.equals(C.WIDEVINE_UUID));
            this.executorService = Executors.newSingleThreadExecutor();
            this.format = format;
            this.drmConfiguration = drmConfiguration;
            this.dataSourceFactory = dataSourceFactory;
            this.dialogHelper = dialogHelper;
            this.downloadHelper = downloadHelper;
        }

        public void cancel() {
            Future<?> future = this.future;
            if (future != null) {
                future.cancel(false);
            }
        }

        public void execute() {
            this.future = this.executorService.submit(new Runnable() { // from class: com.movieboxpro.android.utils.download.DownloadTracker$WidevineOfflineLicenseFetchTask$$ExternalSyntheticLambda1
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$execute$1();
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [androidx.media3.exoplayer.drm.OfflineLicenseHelper] */
        /* JADX WARN: Type inference failed for: r0v5, types: [android.os.Handler] */
        /* JADX WARN: Type inference failed for: r0v7 */
        /* JADX WARN: Type inference failed for: r0v8 */
        public /* synthetic */ void lambda$execute$1() {
            Runnable runnable;
            OfflineLicenseHelper offlineLicenseHelperNewWidevineInstance = OfflineLicenseHelper.newWidevineInstance(this.drmConfiguration, this.dataSourceFactory, new DrmSessionEventListener.EventDispatcher());
            try {
                try {
                    this.keySetId = offlineLicenseHelperNewWidevineInstance.downloadLicense(this.format);
                    offlineLicenseHelperNewWidevineInstance.release();
                    Handler handler = new Handler(Looper.getMainLooper());
                    runnable = new Runnable() { // from class: com.movieboxpro.android.utils.download.DownloadTracker$WidevineOfflineLicenseFetchTask$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$execute$0();
                        }
                    };
                    offlineLicenseHelperNewWidevineInstance = handler;
                } catch (DrmSession.DrmSessionException e) {
                    this.drmSessionException = e;
                    offlineLicenseHelperNewWidevineInstance.release();
                    Handler handler2 = new Handler(Looper.getMainLooper());
                    runnable = new Runnable() { // from class: com.movieboxpro.android.utils.download.DownloadTracker$WidevineOfflineLicenseFetchTask$$ExternalSyntheticLambda0
                        @Override // java.lang.Runnable
                        public final void run() {
                            this.f$0.lambda$execute$0();
                        }
                    };
                    offlineLicenseHelperNewWidevineInstance = handler2;
                }
                offlineLicenseHelperNewWidevineInstance.post(runnable);
            } catch (Throwable th) {
                offlineLicenseHelperNewWidevineInstance.release();
                new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.movieboxpro.android.utils.download.DownloadTracker$WidevineOfflineLicenseFetchTask$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$execute$0();
                    }
                });
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$execute$0() {
            DrmSession.DrmSessionException drmSessionException = this.drmSessionException;
            if (drmSessionException != null) {
                this.dialogHelper.onOfflineLicenseFetchedError(drmSessionException);
            } else {
                this.dialogHelper.onOfflineLicenseFetched(this.downloadHelper, (byte[]) Preconditions.checkNotNull(this.keySetId));
            }
        }
    }
}
