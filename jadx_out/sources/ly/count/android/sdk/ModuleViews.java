package ly.count.android.sdk;

import android.app.Activity;
import android.content.res.Configuration;
import android.content.res.Resources;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleViews extends ModuleBase implements ViewIdProvider {
    static final String ORIENTATION_EVENT_KEY = "[CLY]_orientation";
    static final String VIEW_EVENT_KEY = "[CLY]_view";
    Class[] autoTrackingActivityExceptions;
    boolean autoViewTracker;
    boolean automaticTrackingShouldUseShortName;
    Map<String, Object> automaticViewSegmentation;
    int currentOrientation;
    private String currentViewID;
    private boolean firstView;
    private String previousViewID;
    String[] reservedSegmentationKeysViews;
    SafeIDGenerator safeViewIDGenerator;
    boolean trackOrientationChanges;
    Map<String, ViewData> viewDataMap;
    final Views viewsInterface;

    @Override // ly.count.android.sdk.ViewIdProvider
    public String getCurrentViewId() {
        String str = this.currentViewID;
        return str == null ? "" : str;
    }

    @Override // ly.count.android.sdk.ViewIdProvider
    public String getPreviousViewId() {
        String str = this.previousViewID;
        return str == null ? "" : str;
    }

    static class ViewData {
        String viewID;
        String viewName;
        long viewStartTimeSeconds;
        boolean isAutoStoppedView = false;
        boolean isAutoPaused = false;

        ViewData() {
        }
    }

    ModuleViews(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.currentViewID = null;
        this.previousViewID = null;
        this.firstView = true;
        this.autoViewTracker = false;
        this.automaticTrackingShouldUseShortName = false;
        this.currentOrientation = -1;
        this.autoTrackingActivityExceptions = null;
        this.automaticViewSegmentation = new HashMap();
        this.viewDataMap = new HashMap();
        this.reservedSegmentationKeysViews = new String[]{"name", "visit", "start", "segment"};
        this.L.v("[ModuleViews] Initializing");
        if (countlyConfig.enableAutomaticViewTracking) {
            this.L.d("[ModuleViews] Enabling automatic view tracking");
            this.autoViewTracker = countlyConfig.enableAutomaticViewTracking;
        }
        if (countlyConfig.autoTrackingUseShortName) {
            this.L.d("[ModuleViews] Enabling automatic view tracking short names");
            this.automaticTrackingShouldUseShortName = countlyConfig.autoTrackingUseShortName;
        }
        countlyConfig.viewIdProvider = this;
        this.safeViewIDGenerator = countlyConfig.safeViewIDGenerator;
        setGlobalViewSegmentationInternal(countlyConfig.globalViewSegmentation);
        this.autoTrackingActivityExceptions = countlyConfig.automaticViewTrackingExceptions;
        this.trackOrientationChanges = countlyConfig.trackOrientationChange;
        this.viewsInterface = new Views();
    }

    void setGlobalViewSegmentationInternal(Map<String, Object> map) {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleViews] Calling setGlobalViewSegmentationInternal with[");
        sb.append(map == null ? "null" : Integer.valueOf(map.size()));
        sb.append("] entries");
        moduleLog.d(sb.toString());
        this.automaticViewSegmentation.clear();
        if (map != null) {
            Utils.removeReservedKeysFromSegmentation(map, this.reservedSegmentationKeysViews, "[ModuleViews] setGlobalViewSegmentationInternal, ", this.L);
            if (Utils.removeUnsupportedDataTypes(map)) {
                this.L.w("[ModuleViews] setGlobalViewSegmentationInternal, You have provided an unsupported data type in your View Segmentation. Removing the unsupported values.");
            }
            this.automaticViewSegmentation.putAll(map);
        }
    }

    public void updateGlobalViewSegmentationInternal(Map<String, Object> map) {
        if (Utils.removeUnsupportedDataTypes(map)) {
            this.L.w("[ModuleViews] updateGlobalViewSegmentationInternal, You have provided an unsupported data type in your View Segmentation. Removing the unsupported values.");
        }
        Utils.removeReservedKeysFromSegmentation(map, this.reservedSegmentationKeysViews, "[ModuleViews] updateGlobalViewSegmentationInternal, ", this.L);
        this.automaticViewSegmentation.putAll(map);
    }

    boolean isActivityInExceptionList(Activity activity) {
        Class[] clsArr = this.autoTrackingActivityExceptions;
        if (clsArr == null) {
            return false;
        }
        for (Class cls : clsArr) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public void resetFirstView() {
        this.firstView = true;
    }

    Map<String, Object> CreateViewEventSegmentation(ViewData viewData, boolean z, boolean z2, Map<String, Object> map) {
        HashMap map2 = new HashMap();
        if (map != null) {
            map2.putAll(map);
        }
        map2.put("name", viewData.viewName);
        if (z2) {
            map2.put("visit", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
        if (z) {
            map2.put("start", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        }
        map2.put("segment", "Android");
        return map2;
    }

    void autoCloseRequiredViews(boolean z, Map<String, Object> map) {
        this.L.d("[ModuleViews] autoCloseRequiredViews");
        ArrayList arrayList = new ArrayList(1);
        Iterator<Map.Entry<String, ViewData>> it = this.viewDataMap.entrySet().iterator();
        while (it.hasNext()) {
            ViewData value = it.next().getValue();
            if (z || value.isAutoStoppedView) {
                arrayList.add(value.viewID);
            }
        }
        if (arrayList.size() > 0) {
            this.L.d("[ModuleViews] autoCloseRequiredViews, about to close [" + arrayList.size() + "] views");
        }
        Utils.removeReservedKeysFromSegmentation(map, this.reservedSegmentationKeysViews, "[ModuleViews] autoCloseRequiredViews, ", this.L);
        for (int i = 0; i < arrayList.size(); i++) {
            stopViewWithIDInternal((String) arrayList.get(i), map);
        }
    }

    String startViewInternal(String str, Map<String, Object> map, boolean z) {
        if (!this._cly.isInitialized()) {
            this.L.e("Countly.sharedInstance().init must be called before startViewInternal");
            return null;
        }
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleViews] startViewInternal, Trying to record view with null or empty view name, ignoring request");
            return null;
        }
        Utils.truncateSegmentationValues(map, this._cly.config_.maxSegmentationValues.intValue(), "[ModuleViews] startViewInternal", this.L);
        Utils.removeReservedKeysFromSegmentation(map, this.reservedSegmentationKeysViews, "[ModuleViews] autoCloseRequiredViews, ", this.L);
        if (this.L.logEnabled()) {
            int size = map != null ? map.size() : 0;
            this.L.d("[ModuleViews] Recording view with name: [" + str + "], previous view ID:[" + this.currentViewID + "] custom view segment count:[" + size + "], first:[" + this.firstView + "], autoStop:[" + z + "]");
        }
        autoCloseRequiredViews(false, null);
        ViewData viewData = new ViewData();
        viewData.viewID = this.safeViewIDGenerator.GenerateValue();
        viewData.viewName = str;
        viewData.viewStartTimeSeconds = UtilsTime.currentTimestampSeconds();
        viewData.isAutoStoppedView = z;
        this.viewDataMap.put(viewData.viewID, viewData);
        this.previousViewID = this.currentViewID;
        this.currentViewID = viewData.viewID;
        HashMap map2 = new HashMap(this.automaticViewSegmentation);
        if (map != null) {
            map2.putAll(map);
        }
        Map<String, Object> mapCreateViewEventSegmentation = CreateViewEventSegmentation(viewData, this.firstView, true, map2);
        if (this.firstView) {
            this.L.d("[ModuleViews] Recording view as the first one in the session. [" + str + "]");
            this.firstView = false;
        }
        this.eventProvider.recordEventInternal(VIEW_EVENT_KEY, mapCreateViewEventSegmentation, 1, 0.0d, 0.0d, null, viewData.viewID);
        return viewData.viewID;
    }

    void stopViewWithNameInternal(String str, Map<String, Object> map) {
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleViews] stopViewWithNameInternal, Trying to record view with null or empty view name, ignoring request");
            return;
        }
        String key = null;
        for (Map.Entry<String, ViewData> entry : this.viewDataMap.entrySet()) {
            ViewData value = entry.getValue();
            if (value != null && str.equals(value.viewName)) {
                key = entry.getKey();
            }
        }
        if (key == null) {
            this.L.e("[ModuleViews] stopViewWithNameInternal, No view entry found with the provided name :[" + str + "]");
            return;
        }
        stopViewWithIDInternal(key, map);
    }

    void stopViewWithIDInternal(String str, Map<String, Object> map) {
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleViews] stopViewWithNameInternal, Trying to record view with null or empty view ID, ignoring request");
            return;
        }
        if (!this.viewDataMap.containsKey(str)) {
            this.L.w("[ModuleViews] stopViewWithIDInternal, there is no view with the provided view id to close");
            return;
        }
        ViewData viewData = this.viewDataMap.get(str);
        if (viewData == null) {
            this.L.e("[ModuleViews] stopViewWithIDInternal, view id:[" + str + "] has a 'null' value. This should not be happening");
            return;
        }
        this.L.d("[ModuleViews] View [" + viewData.viewName + "], id:[" + viewData.viewID + "] is getting closed, reporting duration: [" + (((long) UtilsTime.currentTimestampSeconds()) - viewData.viewStartTimeSeconds) + "] s, current timestamp: [" + UtilsTime.currentTimestampSeconds() + "]");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.views)) {
            Utils.truncateSegmentationValues(map, this._cly.config_.maxSegmentationValues.intValue(), "[ModuleViews] stopViewWithIDInternal", this.L);
            recordViewEndEvent(viewData, map, "stopViewWithIDInternal");
            this.viewDataMap.remove(viewData.viewID);
        }
    }

    void recordViewEndEvent(ViewData viewData, Map<String, Object> map, String str) {
        long jCurrentTimestampSeconds = 0;
        if (viewData.viewStartTimeSeconds < 0) {
            this.L.e("[ModuleViews] " + str + ", view start time value is not normal: [" + viewData.viewStartTimeSeconds + "], ignoring that duration");
        } else if (viewData.viewStartTimeSeconds == 0) {
            this.L.i("[ModuleViews] " + str + ", view is either paused or didn't run, ignoring start timestamp");
        } else {
            jCurrentTimestampSeconds = ((long) UtilsTime.currentTimestampSeconds()) - viewData.viewStartTimeSeconds;
        }
        if (viewData.viewName == null) {
            this.L.e("[ModuleViews] stopViewWithIDInternal, view has no internal name, ignoring it");
            return;
        }
        HashMap map2 = new HashMap(this.automaticViewSegmentation);
        if (map != null) {
            map2.putAll(map);
        }
        this.eventProvider.recordEventInternal(VIEW_EVENT_KEY, CreateViewEventSegmentation(viewData, false, false, map2), 1, 0.0d, jCurrentTimestampSeconds, null, viewData.viewID);
    }

    void pauseViewWithIDInternal(String str, boolean z) {
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleViews] pauseViewWithIDInternal, Trying to record view with null or empty view ID, ignoring request");
            return;
        }
        if (!this.viewDataMap.containsKey(str)) {
            this.L.w("[ModuleViews] pauseViewWithIDInternal, there is no view with the provided view id to close");
            return;
        }
        ViewData viewData = this.viewDataMap.get(str);
        if (viewData == null) {
            this.L.e("[ModuleViews] pauseViewWithIDInternal, view id:[" + str + "] has a 'null' value. This should not be happening, auto paused:[" + z + "]");
            return;
        }
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.views)) {
            this.L.d("[ModuleViews] pauseViewWithIDInternal, pausing view for ID:[" + str + "], name:[" + viewData.viewName + "]");
            if (viewData.viewStartTimeSeconds == 0) {
                this.L.w("[ModuleViews] pauseViewWithIDInternal, pausing a view that is already paused. ID:[" + str + "], name:[" + viewData.viewName + "]");
                return;
            }
            viewData.isAutoPaused = z;
            recordViewEndEvent(viewData, null, "pauseViewWithIDInternal");
            viewData.viewStartTimeSeconds = 0L;
        }
    }

    void resumeViewWithIDInternal(String str) {
        if (str == null || str.isEmpty()) {
            this.L.e("[ModuleViews] resumeViewWithIDInternal, Trying to record view with null or empty view ID, ignoring request");
            return;
        }
        if (!this.viewDataMap.containsKey(str)) {
            this.L.w("[ModuleViews] resumeViewWithIDInternal, there is no view with the provided view id to close");
            return;
        }
        ViewData viewData = this.viewDataMap.get(str);
        if (viewData == null) {
            this.L.e("[ModuleViews] resumeViewWithIDInternal, view id:[" + str + "] has a 'null' value. This should not be happening");
            return;
        }
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.views)) {
            this.L.d("[ModuleViews] resumeViewWithIDInternal, resuming view for ID:[" + str + "], name:[" + viewData.viewName + "]");
            if (viewData.viewStartTimeSeconds > 0) {
                this.L.w("[ModuleViews] resumeViewWithIDInternal, resuming a view that is already running. ID:[" + str + "], name:[" + viewData.viewName + "]");
                return;
            }
            viewData.viewStartTimeSeconds = UtilsTime.currentTimestampSeconds();
            viewData.isAutoPaused = false;
        }
    }

    void stopAllViewsInternal(Map<String, Object> map) {
        this.L.d("[ModuleViews] stopAllViewsInternal");
        autoCloseRequiredViews(true, map);
    }

    void updateOrientation(int i) {
        this.L.d("[ModuleViews] Calling [updateOrientation], new orientation:[" + i + "]");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.users) && this.currentOrientation != i) {
            this.currentOrientation = i;
            HashMap map = new HashMap();
            if (this.currentOrientation == 1) {
                map.put("mode", "portrait");
            } else {
                map.put("mode", "landscape");
            }
            this.eventProvider.recordEventInternal(ORIENTATION_EVENT_KEY, map, 1, 0.0d, 0.0d, null, null);
        }
    }

    void pauseRunningViewsAndSend() {
        this.L.d("[ModuleViews] pauseRunningViewsAndSend, going to the background and pausing");
        Iterator<Map.Entry<String, ViewData>> it = this.viewDataMap.entrySet().iterator();
        while (it.hasNext()) {
            ViewData value = it.next().getValue();
            if (value.viewStartTimeSeconds > 0) {
                pauseViewWithIDInternal(value.viewID, true);
            }
        }
    }

    void resumeAutoPausedViews() {
        this.L.d("[ModuleViews] resumeAutoPausedViews, going to the foreground and resuming");
        Iterator<Map.Entry<String, ViewData>> it = this.viewDataMap.entrySet().iterator();
        while (it.hasNext()) {
            ViewData value = it.next().getValue();
            if (value.isAutoPaused) {
                resumeViewWithIDInternal(value.viewID);
            }
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onConfigurationChanged(Configuration configuration) {
        Integer orientationFromConfiguration;
        if (!this.trackOrientationChanges || (orientationFromConfiguration = getOrientationFromConfiguration(configuration)) == null) {
            return;
        }
        updateOrientation(orientationFromConfiguration.intValue());
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onActivityStopped(int i) {
        if (this.autoViewTracker && i <= 0) {
            stopViewWithIDInternal(this.currentViewID, null);
        }
        if (i <= 0) {
            pauseRunningViewsAndSend();
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onActivityStarted(Activity activity, int i) {
        Integer orientationFromActivity;
        String name;
        if (this.autoViewTracker) {
            if (!isActivityInExceptionList(activity)) {
                if (activity == null) {
                    name = "NULL ACTIVITY";
                } else if (this.automaticTrackingShouldUseShortName) {
                    name = activity.getClass().getSimpleName();
                } else {
                    name = activity.getClass().getName();
                }
                startViewInternal(name, this.automaticViewSegmentation, true);
            } else {
                this.L.d("[ModuleViews] [onStart] Ignoring activity because it's in the exception list");
            }
        }
        if (this.trackOrientationChanges && (orientationFromActivity = getOrientationFromActivity(activity)) != null) {
            updateOrientation(orientationFromActivity.intValue());
        }
        if (i == 1) {
            resumeAutoPausedViews();
        }
    }

    Integer getOrientationFromConfiguration(Configuration configuration) {
        if (configuration == null) {
            return null;
        }
        return Integer.valueOf(configuration.orientation);
    }

    Integer getOrientationFromActivity(Activity activity) {
        Resources resources;
        if (activity == null || (resources = activity.getResources()) == null) {
            return null;
        }
        return Integer.valueOf(resources.getConfiguration().orientation);
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        Map<String, Object> map = this.automaticViewSegmentation;
        if (map != null) {
            map.clear();
            this.automaticViewSegmentation = null;
        }
        this.autoTrackingActivityExceptions = null;
    }

    public class Views {
        public Views() {
        }

        public boolean isAutomaticViewTrackingEnabled() {
            boolean z;
            synchronized (ModuleViews.this._cly) {
                ModuleViews.this.L.i("[Views] Calling isAutomaticViewTrackingEnabled");
                z = ModuleViews.this.autoViewTracker;
            }
            return z;
        }

        public Countly recordView(String str) {
            Countly countlyRecordView;
            synchronized (ModuleViews.this._cly) {
                countlyRecordView = recordView(str, null);
            }
            return countlyRecordView;
        }

        public Countly recordView(String str, Map<String, Object> map) {
            synchronized (ModuleViews.this._cly) {
                ModuleViews.this.L.i("[Views] Calling recordView [" + str + "]");
                if (ModuleViews.this.autoViewTracker) {
                    ModuleViews.this.L.e("[Views] recordView, manual view call will be ignored since automatic tracking is enabled.");
                    return ModuleViews.this._cly;
                }
                ModuleViews.this.startViewInternal(str, map, true);
                return ModuleViews.this._cly;
            }
        }

        public String startAutoStoppedView(String str) {
            String strStartAutoStoppedView;
            synchronized (ModuleViews.this._cly) {
                strStartAutoStoppedView = startAutoStoppedView(str, null);
            }
            return strStartAutoStoppedView;
        }

        public String startAutoStoppedView(String str, Map<String, Object> map) {
            synchronized (ModuleViews.this._cly) {
                ModuleViews.this.L.i("[Views] Calling startAutoStoppedView [" + str + "]");
                if (ModuleViews.this.autoViewTracker) {
                    ModuleViews.this.L.e("[Views] startAutoStoppedView, manual view call will be ignored since automatic tracking is enabled.");
                    return null;
                }
                return ModuleViews.this.startViewInternal(str, map, true);
            }
        }

        public String startView(String str) {
            synchronized (ModuleViews.this._cly) {
                ModuleViews.this.L.i("[Views] Calling startView vn[" + str + "]");
                if (ModuleViews.this.autoViewTracker) {
                    ModuleViews.this.L.e("[Views] startView, manual view call will be ignored since automatic tracking is enabled.");
                    return null;
                }
                return ModuleViews.this.startViewInternal(str, null, false);
            }
        }

        public String startView(String str, Map<String, Object> map) {
            synchronized (ModuleViews.this._cly) {
                ModuleLog moduleLog = ModuleViews.this.L;
                StringBuilder sb = new StringBuilder("[Views] Calling startView vn[");
                sb.append(str);
                sb.append("] sg[");
                sb.append(map == null ? map : Integer.valueOf(map.size()));
                sb.append("]");
                moduleLog.i(sb.toString());
                if (ModuleViews.this.autoViewTracker) {
                    ModuleViews.this.L.e("[Views] startView, manual view call will be ignored since automatic tracking is enabled.");
                    return null;
                }
                return ModuleViews.this.startViewInternal(str, map, false);
            }
        }

        public void stopViewWithName(String str) {
            synchronized (ModuleViews.this._cly) {
                ModuleViews.this.L.i("[startAutoClosedView] Calling stopViewWithName vn[" + str + "]");
                ModuleViews.this.stopViewWithNameInternal(str, null);
            }
        }

        public void stopViewWithName(String str, Map<String, Object> map) {
            synchronized (ModuleViews.this._cly) {
                ModuleLog moduleLog = ModuleViews.this.L;
                StringBuilder sb = new StringBuilder("[Views] Calling stopViewWithName vn[");
                sb.append(str);
                sb.append("] sg[");
                sb.append(map == null ? map : Integer.valueOf(map.size()));
                sb.append("]");
                moduleLog.i(sb.toString());
                ModuleViews.this.stopViewWithNameInternal(str, map);
            }
        }

        public void stopViewWithID(String str) {
            synchronized (ModuleViews.this._cly) {
                ModuleViews.this.L.i("[Views] Calling stopViewWithID vi[" + str + "]");
                ModuleViews.this.stopViewWithIDInternal(str, null);
            }
        }

        public void stopViewWithID(String str, Map<String, Object> map) {
            synchronized (ModuleViews.this._cly) {
                ModuleLog moduleLog = ModuleViews.this.L;
                StringBuilder sb = new StringBuilder("[Views] Calling stopViewWithName vi[");
                sb.append(str);
                sb.append("] sg[");
                sb.append(map == null ? map : Integer.valueOf(map.size()));
                sb.append("]");
                moduleLog.i(sb.toString());
                ModuleViews.this.stopViewWithIDInternal(str, map);
            }
        }

        public void pauseViewWithID(String str) {
            synchronized (ModuleViews.this._cly) {
                ModuleViews.this.L.i("[Views] Calling pauseViewWithID vi[" + str + "]");
                ModuleViews.this.pauseViewWithIDInternal(str, false);
            }
        }

        public void resumeViewWithID(String str) {
            synchronized (ModuleViews.this._cly) {
                ModuleViews.this.L.i("[Views] Calling resumeViewWithID vi[" + str + "]");
                ModuleViews.this.resumeViewWithIDInternal(str);
            }
        }

        public void setGlobalViewSegmentation(Map<String, Object> map) {
            synchronized (ModuleViews.this._cly) {
                ModuleLog moduleLog = ModuleViews.this.L;
                StringBuilder sb = new StringBuilder("[Views] Calling setGlobalViewSegmentation sg[");
                sb.append(map == null ? map : Integer.valueOf(map.size()));
                sb.append("]");
                moduleLog.i(sb.toString());
                ModuleViews.this.setGlobalViewSegmentationInternal(map);
            }
        }

        public void updateGlobalViewSegmentation(Map<String, Object> map) {
            synchronized (ModuleViews.this._cly) {
                ModuleLog moduleLog = ModuleViews.this.L;
                StringBuilder sb = new StringBuilder("[Views] Calling updateGlobalViewSegmentation sg[");
                sb.append(map == null ? map : Integer.valueOf(map.size()));
                sb.append("]");
                moduleLog.i(sb.toString());
                if (map == null) {
                    ModuleViews.this.L.w("[View] When updating segmentation values, they can't be 'null'.");
                } else {
                    ModuleViews.this.updateGlobalViewSegmentationInternal(map);
                }
            }
        }

        public void stopAllViews(Map<String, Object> map) {
            synchronized (ModuleViews.this._cly) {
                ModuleLog moduleLog = ModuleViews.this.L;
                StringBuilder sb = new StringBuilder("[Views] Calling stopAllViews sg[");
                sb.append(map == null ? map : Integer.valueOf(map.size()));
                sb.append("]");
                moduleLog.i(sb.toString());
                ModuleViews.this.stopAllViewsInternal(map);
            }
        }
    }
}
