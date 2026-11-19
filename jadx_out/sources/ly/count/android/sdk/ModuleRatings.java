package ly.count.android.sdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RatingBar;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleRatings extends ModuleBase {
    final Ratings ratingsInterface;
    boolean showStarRatingDialogOnFirstActivity;
    StarRatingCallback starRatingCallback_;

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
    }

    ModuleRatings(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.showStarRatingDialogOnFirstActivity = false;
        this.L.v("[ModuleRatings] Initialising");
        this.starRatingCallback_ = countlyConfig.starRatingCallback;
        setStarRatingInitConfig(countlyConfig.starRatingSessionLimit, countlyConfig.starRatingTextTitle, countlyConfig.starRatingTextMessage, countlyConfig.starRatingTextDismiss);
        setIfRatingDialogIsCancellableInternal(countlyConfig.starRatingDialogIsCancellable);
        setShowDialogAutomatically(countlyConfig.starRatingShownAutomatically);
        setStarRatingDisableAskingForEachAppVersion(countlyConfig.starRatingDisableAskingForEachAppVersion);
        this.ratingsInterface = new Ratings();
    }

    void recordManualRatingInternal(String str, int i, String str2, String str3, boolean z) {
        this.L.d("[ModuleRatings] Calling recordManualRatingInternal");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.starRating)) {
            if (str == null) {
                this.L.e("[ModuleRatings] recordManualRatingInternal, provided widget ID is null, returning");
                return;
            }
            if (str.isEmpty()) {
                this.L.e("[ModuleRatings] recordManualRatingInternal, provided widget ID is empty, returning");
                return;
            }
            if (i < 1) {
                this.L.d("[ModuleRatings] recordManualRatingInternal, given rating too low, defaulting to 1");
                i = 1;
            }
            if (i > 5) {
                this.L.d("[ModuleRatings] recordManualRatingInternal, given rating too high, defaulting to 5");
                i = 5;
            }
            HashMap map = new HashMap();
            map.put("platform", "android");
            map.put("app_version", this.deviceInfo.mp.getAppVersion(this._cly.context_));
            map.put("rating", "" + i);
            map.put("widget_id", str);
            map.put("contactMe", Boolean.valueOf(z));
            if (str2 != null && !str2.isEmpty()) {
                map.put("email", str2);
            }
            if (str3 != null && !str3.isEmpty()) {
                map.put("comment", str3);
            }
            this.eventProvider.recordEventInternal("[CLY]_star_rating", map, 1, 0.0d, 0.0d, null, null);
        }
    }

    private void saveStarRatingPreferences(StarRatingPreferences starRatingPreferences) {
        this.storageProvider.setStarRatingPreferences(starRatingPreferences.toJSON().toString());
    }

    void setStarRatingInitConfig(int i, String str, String str2, String str3) {
        StarRatingPreferences starRatingPreferencesLoadStarRatingPreferences = loadStarRatingPreferences(this.storageProvider);
        if (i >= 0) {
            starRatingPreferencesLoadStarRatingPreferences.sessionLimit = i;
        }
        if (str != null) {
            starRatingPreferencesLoadStarRatingPreferences.dialogTextTitle = str;
        }
        if (str2 != null) {
            starRatingPreferencesLoadStarRatingPreferences.dialogTextMessage = str2;
        }
        if (str3 != null) {
            starRatingPreferencesLoadStarRatingPreferences.dialogTextDismiss = str3;
        }
        saveStarRatingPreferences(starRatingPreferencesLoadStarRatingPreferences);
    }

    void setShowDialogAutomatically(boolean z) {
        StarRatingPreferences starRatingPreferencesLoadStarRatingPreferences = loadStarRatingPreferences(this.storageProvider);
        starRatingPreferencesLoadStarRatingPreferences.automaticRatingShouldBeShown = z;
        saveStarRatingPreferences(starRatingPreferencesLoadStarRatingPreferences);
    }

    boolean getIfStarRatingShouldBeShownAutomatically() {
        return loadStarRatingPreferences(this._cly.countlyStore).automaticRatingShouldBeShown;
    }

    void setStarRatingDisableAskingForEachAppVersion(boolean z) {
        StarRatingPreferences starRatingPreferencesLoadStarRatingPreferences = loadStarRatingPreferences(this.storageProvider);
        starRatingPreferencesLoadStarRatingPreferences.disabledAutomaticForNewVersions = z;
        saveStarRatingPreferences(starRatingPreferencesLoadStarRatingPreferences);
    }

    void registerAppSession(Context context, StarRatingCallback starRatingCallback) {
        StarRatingPreferences starRatingPreferencesLoadStarRatingPreferences = loadStarRatingPreferences(this.storageProvider);
        String appVersion = this.deviceInfo.mp.getAppVersion(context);
        if (appVersion != null && !appVersion.equals(starRatingPreferencesLoadStarRatingPreferences.appVersion) && !starRatingPreferencesLoadStarRatingPreferences.disabledAutomaticForNewVersions) {
            starRatingPreferencesLoadStarRatingPreferences.appVersion = appVersion;
            starRatingPreferencesLoadStarRatingPreferences.isShownForCurrentVersion = false;
            starRatingPreferencesLoadStarRatingPreferences.sessionAmount = 0;
        }
        starRatingPreferencesLoadStarRatingPreferences.sessionAmount++;
        if (starRatingPreferencesLoadStarRatingPreferences.sessionAmount >= starRatingPreferencesLoadStarRatingPreferences.sessionLimit && !starRatingPreferencesLoadStarRatingPreferences.isShownForCurrentVersion && starRatingPreferencesLoadStarRatingPreferences.automaticRatingShouldBeShown && (!starRatingPreferencesLoadStarRatingPreferences.disabledAutomaticForNewVersions || !starRatingPreferencesLoadStarRatingPreferences.automaticHasBeenShown)) {
            this.showStarRatingDialogOnFirstActivity = true;
        }
        saveStarRatingPreferences(starRatingPreferencesLoadStarRatingPreferences);
    }

    static int getAutomaticStarRatingSessionLimitInternal(StorageProvider storageProvider) {
        return loadStarRatingPreferences(storageProvider).sessionLimit;
    }

    int getCurrentVersionsSessionCountInternal(StorageProvider storageProvider) {
        return loadStarRatingPreferences(storageProvider).sessionAmount;
    }

    void clearAutomaticStarRatingSessionCountInternal() {
        StarRatingPreferences starRatingPreferencesLoadStarRatingPreferences = loadStarRatingPreferences(this.storageProvider);
        starRatingPreferencesLoadStarRatingPreferences.sessionAmount = 0;
        saveStarRatingPreferences(starRatingPreferencesLoadStarRatingPreferences);
    }

    void setIfRatingDialogIsCancellableInternal(boolean z) {
        StarRatingPreferences starRatingPreferencesLoadStarRatingPreferences = loadStarRatingPreferences(this.storageProvider);
        starRatingPreferencesLoadStarRatingPreferences.isDialogCancellable = z;
        saveStarRatingPreferences(starRatingPreferencesLoadStarRatingPreferences);
    }

    static class StarRatingPreferences {
        private static final String KEY_APP_VERSION = "sr_app_version";
        private static final String KEY_AUTOMATIC_HAS_BEEN_SHOWN = "sr_automatic_has_been_shown";
        private static final String KEY_AUTOMATIC_RATING_IS_SHOWN = "sr_is_automatic_shown";
        private static final String KEY_DIALOG_IS_CANCELLABLE = "sr_automatic_dialog_is_cancellable";
        private static final String KEY_DIALOG_TEXT_DISMISS = "sr_text_dismiss";
        private static final String KEY_DIALOG_TEXT_MESSAGE = "sr_text_message";
        private static final String KEY_DIALOG_TEXT_TITLE = "sr_text_title";
        private static final String KEY_DISABLE_AUTOMATIC_NEW_VERSIONS = "sr_is_disable_automatic_new";
        private static final String KEY_IS_SHOWN_FOR_CURRENT = "sr_is_shown";
        private static final String KEY_SESSION_AMOUNT = "sr_session_amount";
        private static final String KEY_SESSION_LIMIT = "sr_session_limit";
        String appVersion = "";
        int sessionLimit = 5;
        int sessionAmount = 0;
        boolean isShownForCurrentVersion = false;
        boolean automaticRatingShouldBeShown = false;
        boolean disabledAutomaticForNewVersions = false;
        boolean automaticHasBeenShown = false;
        boolean isDialogCancellable = true;
        String dialogTextTitle = "App rating";
        String dialogTextMessage = "Please rate this app";
        String dialogTextDismiss = "Cancel";

        StarRatingPreferences() {
        }

        JSONObject toJSON() {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(KEY_APP_VERSION, this.appVersion);
                jSONObject.put(KEY_SESSION_LIMIT, this.sessionLimit);
                jSONObject.put(KEY_SESSION_AMOUNT, this.sessionAmount);
                jSONObject.put(KEY_IS_SHOWN_FOR_CURRENT, this.isShownForCurrentVersion);
                jSONObject.put(KEY_AUTOMATIC_RATING_IS_SHOWN, this.automaticRatingShouldBeShown);
                jSONObject.put(KEY_DISABLE_AUTOMATIC_NEW_VERSIONS, this.disabledAutomaticForNewVersions);
                jSONObject.put(KEY_AUTOMATIC_HAS_BEEN_SHOWN, this.automaticHasBeenShown);
                jSONObject.put(KEY_DIALOG_IS_CANCELLABLE, this.isDialogCancellable);
                jSONObject.put(KEY_DIALOG_TEXT_TITLE, this.dialogTextTitle);
                jSONObject.put(KEY_DIALOG_TEXT_MESSAGE, this.dialogTextMessage);
                jSONObject.put(KEY_DIALOG_TEXT_DISMISS, this.dialogTextDismiss);
            } catch (JSONException e) {
                Countly.sharedInstance().L.w("Got exception converting an StarRatingPreferences to JSON", e);
            }
            return jSONObject;
        }

        static StarRatingPreferences fromJSON(JSONObject jSONObject) {
            StarRatingPreferences starRatingPreferences = new StarRatingPreferences();
            if (jSONObject != null) {
                try {
                    starRatingPreferences.appVersion = jSONObject.getString(KEY_APP_VERSION);
                    starRatingPreferences.sessionLimit = jSONObject.optInt(KEY_SESSION_LIMIT, 5);
                    starRatingPreferences.sessionAmount = jSONObject.optInt(KEY_SESSION_AMOUNT, 0);
                    starRatingPreferences.isShownForCurrentVersion = jSONObject.optBoolean(KEY_IS_SHOWN_FOR_CURRENT, false);
                    starRatingPreferences.automaticRatingShouldBeShown = jSONObject.optBoolean(KEY_AUTOMATIC_RATING_IS_SHOWN, true);
                    starRatingPreferences.disabledAutomaticForNewVersions = jSONObject.optBoolean(KEY_DISABLE_AUTOMATIC_NEW_VERSIONS, false);
                    starRatingPreferences.automaticHasBeenShown = jSONObject.optBoolean(KEY_AUTOMATIC_HAS_BEEN_SHOWN, false);
                    starRatingPreferences.isDialogCancellable = jSONObject.optBoolean(KEY_DIALOG_IS_CANCELLABLE, true);
                    if (!jSONObject.isNull(KEY_DIALOG_TEXT_TITLE)) {
                        starRatingPreferences.dialogTextTitle = jSONObject.getString(KEY_DIALOG_TEXT_TITLE);
                    }
                    if (!jSONObject.isNull(KEY_DIALOG_TEXT_MESSAGE)) {
                        starRatingPreferences.dialogTextMessage = jSONObject.getString(KEY_DIALOG_TEXT_MESSAGE);
                    }
                    if (!jSONObject.isNull(KEY_DIALOG_TEXT_DISMISS)) {
                        starRatingPreferences.dialogTextDismiss = jSONObject.getString(KEY_DIALOG_TEXT_DISMISS);
                    }
                } catch (JSONException e) {
                    Countly.sharedInstance().L.w("Got exception converting JSON to a StarRatingPreferences", e);
                }
            }
            return starRatingPreferences;
        }
    }

    void showStarRatingInternal(Context context, StarRatingCallback starRatingCallback) {
        StarRatingPreferences starRatingPreferencesLoadStarRatingPreferences = loadStarRatingPreferences(this.storageProvider);
        showStarRatingCustom(context, starRatingPreferencesLoadStarRatingPreferences.dialogTextTitle, starRatingPreferencesLoadStarRatingPreferences.dialogTextMessage, starRatingPreferencesLoadStarRatingPreferences.dialogTextDismiss, starRatingPreferencesLoadStarRatingPreferences.isDialogCancellable, starRatingCallback);
    }

    static StarRatingPreferences loadStarRatingPreferences(StorageProvider storageProvider) {
        String starRatingPreferences = storageProvider.getStarRatingPreferences();
        if (!starRatingPreferences.equals("")) {
            try {
                return StarRatingPreferences.fromJSON(new JSONObject(starRatingPreferences));
            } catch (JSONException e) {
                e.printStackTrace();
                return new StarRatingPreferences();
            }
        }
        return new StarRatingPreferences();
    }

    void showStarRatingCustom(final Context context, String str, String str2, String str3, boolean z, final StarRatingCallback starRatingCallback) {
        if (!(context instanceof Activity)) {
            this.L.e("[ModuleRatings] Can't show star rating dialog, the provided context is not based off a activity");
            return;
        }
        View viewInflate = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.star_rating_layout, (ViewGroup) null);
        RatingBar ratingBar = (RatingBar) viewInflate.findViewById(R.id.ratingBar);
        final AlertDialog alertDialogShow = new AlertDialog.Builder(context).setTitle(str).setMessage(str2).setCancelable(z).setView(viewInflate).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: ly.count.android.sdk.ModuleRatings.2
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                StarRatingCallback starRatingCallback2 = starRatingCallback;
                if (starRatingCallback2 != null) {
                    starRatingCallback2.onDismiss();
                }
            }
        }).setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: ly.count.android.sdk.ModuleRatings.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                StarRatingCallback starRatingCallback2 = starRatingCallback;
                if (starRatingCallback2 != null) {
                    starRatingCallback2.onDismiss();
                }
            }
        }).show();
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() { // from class: ly.count.android.sdk.ModuleRatings.3
            @Override // android.widget.RatingBar.OnRatingBarChangeListener
            public void onRatingChanged(RatingBar ratingBar2, float f, boolean z2) {
                int i = (int) f;
                if (ModuleRatings.this.consentProvider.getConsent(Countly.CountlyFeatureNames.starRating)) {
                    HashMap map = new HashMap();
                    map.put("platform", "android");
                    map.put("app_version", ModuleRatings.this.deviceInfo.mp.getAppVersion(context));
                    map.put("rating", "" + i);
                    ModuleRatings.this.eventProvider.recordEventInternal("[CLY]_star_rating", map, 1, 0.0d, 0.0d, null, null);
                }
                alertDialogShow.dismiss();
                StarRatingCallback starRatingCallback2 = starRatingCallback;
                if (starRatingCallback2 != null) {
                    starRatingCallback2.onRate(i);
                }
            }
        });
    }

    synchronized void showFeedbackPopupInternal(String str, String str2, Activity activity, FeedbackRatingCallback feedbackRatingCallback) {
        boolean z;
        boolean zIsDeviceTablet;
        this.L.d("[ModuleRatings] Showing Feedback popup for widget id: [" + str + "]");
        if (str != null && !str.isEmpty()) {
            if (activity == null) {
                if (feedbackRatingCallback != null) {
                    feedbackRatingCallback.callback("When showing feedback popup, Activity can't be null");
                }
                this.L.e("[ModuleRatings] When showing feedback popup, Activity can't be null");
                return;
            }
            if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.starRating)) {
                if (feedbackRatingCallback != null) {
                    feedbackRatingCallback.callback("Consent is not granted");
                }
                return;
            }
            boolean zIsDeviceTv = Utils.isDeviceTv(activity);
            if (zIsDeviceTv) {
                z = false;
                zIsDeviceTablet = false;
            } else {
                z = !Utils.isDeviceTablet(activity);
                zIsDeviceTablet = Utils.isDeviceTablet(activity);
            }
            String strPrepareRatingWidgetRequest = this.requestQueueProvider.prepareRatingWidgetRequest(str);
            String str3 = this.baseInfoProvider.getServerURL() + "/feedback?widget_id=" + str + "&device_id=" + UtilsNetworking.urlEncodeString(this.deviceIdProvider.getDeviceId()) + "&app_key=" + UtilsNetworking.urlEncodeString(this.baseInfoProvider.getAppKey());
            this.L.d("[ModuleRatings] rating widget url :[" + str3 + "]");
            ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
            new ImmediateRequestMaker().doWork(strPrepareRatingWidgetRequest, "/o/feedback/widget", connectionProcessorCreateConnectionProcessor, false, connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled(), new AnonymousClass4(str, feedbackRatingCallback, z, zIsDeviceTablet, zIsDeviceTv, activity, str3, str2), this.L);
            return;
        }
        if (feedbackRatingCallback != null) {
            feedbackRatingCallback.callback("Countly widgetId cannot be null or empty");
        }
        this.L.e("[ModuleRatings] Countly widgetId cannot be null or empty");
    }

    /* JADX INFO: renamed from: ly.count.android.sdk.ModuleRatings$4, reason: invalid class name */
    class AnonymousClass4 implements ImmediateRequestMaker.InternalImmediateRequestCallback {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ String val$closeButtonText;
        final /* synthetic */ FeedbackRatingCallback val$devCallback;
        final /* synthetic */ boolean val$deviceIsPhone;
        final /* synthetic */ boolean val$deviceIsTablet;
        final /* synthetic */ boolean val$deviceIsTv;
        final /* synthetic */ String val$ratingWidgetUrl;
        final /* synthetic */ String val$widgetId;

        AnonymousClass4(String str, FeedbackRatingCallback feedbackRatingCallback, boolean z, boolean z2, boolean z3, Activity activity, String str2, String str3) {
            this.val$widgetId = str;
            this.val$devCallback = feedbackRatingCallback;
            this.val$deviceIsPhone = z;
            this.val$deviceIsTablet = z2;
            this.val$deviceIsTv = z3;
            this.val$activity = activity;
            this.val$ratingWidgetUrl = str2;
            this.val$closeButtonText = str3;
        }

        @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
        public void callback(JSONObject jSONObject) {
            if (jSONObject == null) {
                ModuleRatings.this.L.d("[ModuleRatings] Not possible to show Feedback popup for widget id: [" + this.val$widgetId + "], probably a lack of connection to the server");
                FeedbackRatingCallback feedbackRatingCallback = this.val$devCallback;
                if (feedbackRatingCallback != null) {
                    feedbackRatingCallback.callback("Not possible to show Rating popup, probably no internet connection or wrong widget id");
                    return;
                }
                return;
            }
            if (!jSONObject.has("target_devices")) {
                ModuleRatings.this.L.d("[ModuleRatings] Not possible to show Feedback popup for widget id: [" + this.val$widgetId + "], probably using a widget_id not intended for the rating widget");
                FeedbackRatingCallback feedbackRatingCallback2 = this.val$devCallback;
                if (feedbackRatingCallback2 != null) {
                    feedbackRatingCallback2.callback("Not possible to show Rating popup, probably using a widget_id not intended for the rating widget");
                    return;
                }
                return;
            }
            try {
                JSONObject jSONObject2 = jSONObject.getJSONObject("target_devices");
                boolean zOptBoolean = jSONObject2.optBoolean("desktop", false);
                boolean zOptBoolean2 = jSONObject2.optBoolean("phone", false);
                boolean zOptBoolean3 = jSONObject2.optBoolean("tablet", false);
                if ((this.val$deviceIsPhone && zOptBoolean2) || ((this.val$deviceIsTablet && zOptBoolean3) || (this.val$deviceIsTv && zOptBoolean))) {
                    ModuleRatings.this.L.d("[ModuleRatings] Showing Feedback popup for widget id: [" + this.val$widgetId + "]");
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: ly.count.android.sdk.ModuleRatings.4.1
                        @Override // java.lang.Runnable
                        public void run() {
                            ModuleRatings.this.L.d("[ModuleRatings] Calling on main thread");
                            RatingDialogWebView ratingDialogWebView = new RatingDialogWebView(AnonymousClass4.this.val$activity);
                            ratingDialogWebView.getSettings().setJavaScriptEnabled(true);
                            ratingDialogWebView.loadUrl(AnonymousClass4.this.val$ratingWidgetUrl);
                            AlertDialog.Builder builder = new AlertDialog.Builder(AnonymousClass4.this.val$activity);
                            builder.setView(ratingDialogWebView);
                            if (AnonymousClass4.this.val$closeButtonText != null && !AnonymousClass4.this.val$closeButtonText.isEmpty()) {
                                builder.setNeutralButton(AnonymousClass4.this.val$closeButtonText, new DialogInterface.OnClickListener() { // from class: ly.count.android.sdk.ModuleRatings.4.1.1
                                    @Override // android.content.DialogInterface.OnClickListener
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        ModuleRatings.this.L.d("[ModuleRatings] Calling callback from 'close' button");
                                        if (AnonymousClass4.this.val$devCallback != null) {
                                            AnonymousClass4.this.val$devCallback.callback(null);
                                        }
                                    }
                                });
                            }
                            builder.show();
                        }
                    });
                } else {
                    FeedbackRatingCallback feedbackRatingCallback3 = this.val$devCallback;
                    if (feedbackRatingCallback3 != null) {
                        feedbackRatingCallback3.callback("Rating dialog is not meant for this form factor");
                    }
                }
            } catch (JSONException e) {
                ModuleRatings.this.L.e("[ModuleRatings] Encountered a issue while trying to parse the results of the widget config", e);
            }
        }
    }

    static class RatingDialogWebView extends WebView {
        @Override // android.webkit.WebView, android.view.View
        public boolean onCheckIsTextEditor() {
            return true;
        }

        public RatingDialogWebView(Context context) {
            super(context);
        }
    }

    static class FeedbackDialogWebViewClient extends WebViewClient {
        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            return null;
        }

        @Override // android.webkit.WebViewClient
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            return null;
        }

        FeedbackDialogWebViewClient() {
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void callbackOnActivityResumed(Activity activity) {
        if (this.showStarRatingDialogOnFirstActivity) {
            StarRatingPreferences starRatingPreferencesLoadStarRatingPreferences = loadStarRatingPreferences(this.storageProvider);
            starRatingPreferencesLoadStarRatingPreferences.isShownForCurrentVersion = true;
            starRatingPreferencesLoadStarRatingPreferences.automaticHasBeenShown = true;
            showStarRatingInternal(activity, this.starRatingCallback_);
            saveStarRatingPreferences(starRatingPreferencesLoadStarRatingPreferences);
            this.showStarRatingDialogOnFirstActivity = false;
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.starRating)) {
            registerAppSession(countlyConfig.context, this.starRatingCallback_);
        }
    }

    public class Ratings {
        public Ratings() {
        }

        public void recordManualRating(String str, int i, String str2, String str3, boolean z) {
            ModuleRatings.this.L.i("[Ratings] Calling recordManualRating");
            recordRatingWidgetWithID(str, i, str2, str3, z);
        }

        public void recordRatingWidgetWithID(String str, int i, String str2, String str3, boolean z) {
            synchronized (ModuleRatings.this._cly) {
                ModuleRatings.this.L.i("[Ratings] Calling recordRatingWidgetWithID");
                if (str == null || str.isEmpty()) {
                    throw new IllegalStateException("A valid widgetID must be provided. The current one is either null or empty");
                }
                ModuleRatings.this.recordManualRatingInternal(str, i, str2, str3, z);
            }
        }

        public void showFeedbackPopup(String str, String str2, Activity activity, FeedbackRatingCallback feedbackRatingCallback) {
            ModuleRatings.this.L.i("[Ratings] Calling showFeedbackPopup");
            presentRatingWidgetWithID(str, str2, activity, feedbackRatingCallback);
        }

        public void presentRatingWidgetWithID(String str, String str2, Activity activity, FeedbackRatingCallback feedbackRatingCallback) {
            synchronized (ModuleRatings.this._cly) {
                ModuleRatings.this.L.i("[Ratings] Calling presentRatingWidgetWithID");
                ModuleRatings.this.showFeedbackPopupInternal(str, str2, activity, feedbackRatingCallback);
            }
        }

        public void showStarRating(Activity activity, StarRatingCallback starRatingCallback) {
            synchronized (ModuleRatings.this._cly) {
                ModuleRatings.this.L.i("[Ratings] Calling showStarRating");
                if (ModuleRatings.this.consentProvider.getConsent(Countly.CountlyFeatureNames.starRating)) {
                    ModuleRatings.this.showStarRatingInternal(activity, starRatingCallback);
                }
            }
        }

        public int getCurrentVersionsSessionCount() {
            int currentVersionsSessionCountInternal;
            synchronized (ModuleRatings.this._cly) {
                ModuleRatings moduleRatings = ModuleRatings.this;
                currentVersionsSessionCountInternal = moduleRatings.getCurrentVersionsSessionCountInternal(moduleRatings._cly.countlyStore);
                ModuleRatings.this.L.i("[Ratings] Getting star rating current version session count: [" + currentVersionsSessionCountInternal + "]");
            }
            return currentVersionsSessionCountInternal;
        }

        public void clearAutomaticStarRatingSessionCount() {
            synchronized (ModuleRatings.this._cly) {
                ModuleRatings.this.L.i("[Ratings] Clearing star rating session count");
                ModuleRatings.this.clearAutomaticStarRatingSessionCountInternal();
            }
        }

        public int getAutomaticStarRatingSessionLimit() {
            int automaticStarRatingSessionLimitInternal;
            synchronized (ModuleRatings.this._cly) {
                automaticStarRatingSessionLimitInternal = ModuleRatings.getAutomaticStarRatingSessionLimitInternal(ModuleRatings.this._cly.countlyStore);
                ModuleRatings.this.L.i("[Ratings] Getting automatic star rating session limit: [" + automaticStarRatingSessionLimitInternal + "]");
            }
            return automaticStarRatingSessionLimitInternal;
        }
    }
}
