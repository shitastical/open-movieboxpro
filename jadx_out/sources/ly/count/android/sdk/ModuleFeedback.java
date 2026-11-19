package ly.count.android.sdk;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import androidx.media3.extractor.metadata.icy.IcyHeaders;
import com.jeffmony.downloader.database.VideoDownloadSQLiteHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleFeedback extends ModuleBase {
    static final String NPS_EVENT_KEY = "[CLY]_nps";
    static final String RATING_EVENT_KEY = "[CLY]_star_rating";
    static final String SURVEY_EVENT_KEY = "[CLY]_survey";
    final String cachedAppVersion;
    Feedback feedbackInterface;

    public static class CountlyFeedbackWidget {
        public String name;
        public String[] tags;
        public FeedbackWidgetType type;
        public String widgetId;
    }

    public interface FeedbackCallback {
        void onClosed();

        void onFinished(String str);
    }

    public enum FeedbackWidgetType {
        survey,
        nps,
        rating
    }

    public interface RetrieveFeedbackWidgetData {
        void onFinished(JSONObject jSONObject, String str);
    }

    public interface RetrieveFeedbackWidgets {
        void onFinished(List<CountlyFeedbackWidget> list, String str);
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
    }

    ModuleFeedback(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.feedbackInterface = null;
        this.L.v("[ModuleFeedback] Initialising");
        this.cachedAppVersion = this.deviceInfo.mp.getAppVersion(countlyConfig.context);
        this.feedbackInterface = new Feedback();
    }

    void getAvailableFeedbackWidgetsInternal(final RetrieveFeedbackWidgets retrieveFeedbackWidgets) {
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleFeedback] calling 'getAvailableFeedbackWidgetsInternal', callback set:[");
        sb.append(retrieveFeedbackWidgets != null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (retrieveFeedbackWidgets == null) {
            this.L.e("[ModuleFeedback] available feedback widget list can't be retrieved without a callback");
            return;
        }
        if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.feedback)) {
            retrieveFeedbackWidgets.onFinished(null, "Consent is not granted");
            return;
        }
        if (this.deviceIdProvider.isTemporaryIdEnabled()) {
            this.L.e("[ModuleFeedback] available feedback widget list can't be retrieved when in temporary device ID mode");
            retrieveFeedbackWidgets.onFinished(null, "[ModuleFeedback] available feedback widget list can't be retrieved when in temporary device ID mode");
        } else {
            ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
            new ImmediateRequestMaker().doWork(this.requestQueueProvider.prepareFeedbackListRequest(), "/o/sdk", connectionProcessorCreateConnectionProcessor, false, connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled(), new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleFeedback.1
                @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
                public void callback(JSONObject jSONObject) {
                    if (jSONObject == null) {
                        ModuleFeedback.this.L.d("[ModuleFeedback] Not possible to retrieve widget list. Probably due to lack of connection to the server");
                        retrieveFeedbackWidgets.onFinished(null, "Not possible to retrieve widget list. Probably due to lack of connection to the server");
                        return;
                    }
                    ModuleFeedback.this.L.d("[ModuleFeedback] Retrieved request: [" + jSONObject.toString() + "]");
                    retrieveFeedbackWidgets.onFinished(ModuleFeedback.parseFeedbackList(jSONObject), null);
                }
            }, this.L);
        }
    }

    static List<CountlyFeedbackWidget> parseFeedbackList(JSONObject jSONObject) {
        FeedbackWidgetType feedbackWidgetType;
        Countly.sharedInstance().L.d("[ModuleFeedback] calling 'parseFeedbackList'");
        ArrayList arrayList = new ArrayList();
        if (jSONObject != null) {
            try {
                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("result");
                if (jSONArrayOptJSONArray == null) {
                    Countly.sharedInstance().L.w("[ModuleFeedback] parseFeedbackList, response does not have a valid 'result' entry. No widgets retrieved.");
                    return arrayList;
                }
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    try {
                        JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
                        String strOptString = jSONObject2.optString(VideoDownloadSQLiteHelper.Columns._ID, "");
                        String strOptString2 = jSONObject2.optString("type", "");
                        String strOptString3 = jSONObject2.optString("name", "");
                        ArrayList arrayList2 = new ArrayList();
                        JSONArray jSONArrayOptJSONArray2 = jSONObject2.optJSONArray("tg");
                        if (jSONArrayOptJSONArray2 == null) {
                            Countly.sharedInstance().L.w("[ModuleFeedback] parseFeedbackList, no tags received");
                        } else {
                            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                                arrayList2.add(jSONArrayOptJSONArray2.getString(i2));
                            }
                        }
                        if (strOptString.isEmpty()) {
                            Countly.sharedInstance().L.e("[ModuleFeedback] parseFeedbackList, retrieved invalid entry with null or empty widget id, dropping");
                        } else if (strOptString2.isEmpty()) {
                            Countly.sharedInstance().L.e("[ModuleFeedback] parseFeedbackList, retrieved invalid entry with null or empty widget type, dropping");
                        } else {
                            if (strOptString2.equals("survey")) {
                                feedbackWidgetType = FeedbackWidgetType.survey;
                            } else if (strOptString2.equals("nps")) {
                                feedbackWidgetType = FeedbackWidgetType.nps;
                            } else if (strOptString2.equals("rating")) {
                                feedbackWidgetType = FeedbackWidgetType.rating;
                            } else {
                                Countly.sharedInstance().L.e("[ModuleFeedback] parseFeedbackList, retrieved unknown widget type, dropping");
                            }
                            CountlyFeedbackWidget countlyFeedbackWidget = new CountlyFeedbackWidget();
                            countlyFeedbackWidget.type = feedbackWidgetType;
                            countlyFeedbackWidget.widgetId = strOptString;
                            countlyFeedbackWidget.name = strOptString3;
                            countlyFeedbackWidget.tags = (String[]) arrayList2.toArray(new String[0]);
                            arrayList.add(countlyFeedbackWidget);
                        }
                    } catch (Exception e) {
                        Countly.sharedInstance().L.e("[ModuleFeedback] parseFeedbackList, failed to parse json, [" + e.toString() + "]");
                    }
                }
            } catch (Exception e2) {
                Countly.sharedInstance().L.e("[ModuleFeedback] parseFeedbackList, Encountered exception while parsing feedback list, [" + e2.toString() + "]");
            }
        }
        return arrayList;
    }

    void presentFeedbackWidgetInternal(final CountlyFeedbackWidget countlyFeedbackWidget, final Context context, final String str, final FeedbackCallback feedbackCallback) {
        if (countlyFeedbackWidget == null) {
            this.L.e("[ModuleFeedback] Can't present widget with null widget info");
            if (feedbackCallback != null) {
                feedbackCallback.onFinished("Can't present widget with null widget info");
                return;
            }
            return;
        }
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleFeedback] presentFeedbackWidgetInternal, callback set:[");
        sb.append(feedbackCallback != null);
        sb.append(", widget id:[");
        sb.append(countlyFeedbackWidget.widgetId);
        sb.append("], widget type:[");
        sb.append(countlyFeedbackWidget.type);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (context == null) {
            this.L.e("[ModuleFeedback] Can't show feedback, provided context is null");
            if (feedbackCallback != null) {
                feedbackCallback.onFinished("Can't show feedback, provided context is null");
                return;
            }
            return;
        }
        if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.feedback)) {
            if (feedbackCallback != null) {
                feedbackCallback.onFinished("Consent is not granted");
                return;
            }
            return;
        }
        if (this.deviceIdProvider.isTemporaryIdEnabled()) {
            this.L.e("[ModuleFeedback] available feedback widget list can't be retrieved when in temporary device ID mode");
            if (feedbackCallback != null) {
                feedbackCallback.onFinished("[ModuleFeedback] available feedback widget list can't be retrieved when in temporary device ID mode");
                return;
            }
            return;
        }
        StringBuilder sb2 = new StringBuilder();
        int i = AnonymousClass5.$SwitchMap$ly$count$android$sdk$ModuleFeedback$FeedbackWidgetType[countlyFeedbackWidget.type.ordinal()];
        if (i == 1) {
            sb2.append(this.baseInfoProvider.getServerURL());
            sb2.append("/feedback/survey?widget_id=");
            sb2.append(UtilsNetworking.urlEncodeString(countlyFeedbackWidget.widgetId));
        } else if (i == 2) {
            sb2.append(this.baseInfoProvider.getServerURL());
            sb2.append("/feedback/nps?widget_id=");
            sb2.append(UtilsNetworking.urlEncodeString(countlyFeedbackWidget.widgetId));
        } else if (i == 3) {
            sb2.append(this.baseInfoProvider.getServerURL());
            sb2.append("/feedback/rating?widget_id=");
            sb2.append(UtilsNetworking.urlEncodeString(countlyFeedbackWidget.widgetId));
        }
        sb2.append("&device_id=");
        sb2.append(UtilsNetworking.urlEncodeString(this.deviceIdProvider.getDeviceId()));
        sb2.append("&app_key=");
        sb2.append(UtilsNetworking.urlEncodeString(this.baseInfoProvider.getAppKey()));
        sb2.append("&sdk_version=");
        sb2.append(Countly.sharedInstance().COUNTLY_SDK_VERSION_STRING);
        sb2.append("&sdk_name=");
        sb2.append(Countly.sharedInstance().COUNTLY_SDK_NAME);
        sb2.append("&platform=android");
        final String string = sb2.toString();
        this.L.d("[ModuleFeedback] Using following url for widget:[" + ((Object) sb2) + "]");
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: ly.count.android.sdk.ModuleFeedback.2
            @Override // java.lang.Runnable
            public void run() {
                ModuleFeedback.this.L.d("[ModuleFeedback] Calling on main thread");
                try {
                    ModuleRatings.RatingDialogWebView ratingDialogWebView = new ModuleRatings.RatingDialogWebView(context);
                    ratingDialogWebView.getSettings().setJavaScriptEnabled(true);
                    ratingDialogWebView.setWebViewClient(new ModuleRatings.FeedbackDialogWebViewClient());
                    ratingDialogWebView.loadUrl(string);
                    ratingDialogWebView.requestFocus();
                    AlertDialog.Builder builderPrepareAlertDialog = ModuleFeedback.this.prepareAlertDialog(context, ratingDialogWebView, str, countlyFeedbackWidget, feedbackCallback);
                    ModuleFeedback.this.L.d("[ModuleFeedback] Creating standalone Alert dialog");
                    builderPrepareAlertDialog.show();
                    FeedbackCallback feedbackCallback2 = feedbackCallback;
                    if (feedbackCallback2 != null) {
                        feedbackCallback2.onFinished(null);
                    }
                } catch (Exception e) {
                    ModuleFeedback.this.L.e("[ModuleFeedback] Failed at displaying feedback widget dialog, [" + e.toString() + "]");
                    FeedbackCallback feedbackCallback3 = feedbackCallback;
                    if (feedbackCallback3 != null) {
                        feedbackCallback3.onFinished("Failed at displaying feedback widget dialog, [" + e.toString() + "]");
                    }
                }
            }
        });
    }

    /* JADX INFO: renamed from: ly.count.android.sdk.ModuleFeedback$5, reason: invalid class name */
    static /* synthetic */ class AnonymousClass5 {
        static final /* synthetic */ int[] $SwitchMap$ly$count$android$sdk$ModuleFeedback$FeedbackWidgetType;

        static {
            int[] iArr = new int[FeedbackWidgetType.values().length];
            $SwitchMap$ly$count$android$sdk$ModuleFeedback$FeedbackWidgetType = iArr;
            try {
                iArr[FeedbackWidgetType.survey.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ly$count$android$sdk$ModuleFeedback$FeedbackWidgetType[FeedbackWidgetType.nps.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$ly$count$android$sdk$ModuleFeedback$FeedbackWidgetType[FeedbackWidgetType.rating.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    AlertDialog.Builder prepareAlertDialog(final Context context, WebView webView, String str, final CountlyFeedbackWidget countlyFeedbackWidget, final FeedbackCallback feedbackCallback) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(webView);
        builder.setCancelable(false);
        if (str == null || str.isEmpty()) {
            str = "Close";
        }
        builder.setNeutralButton(str, new DialogInterface.OnClickListener() { // from class: ly.count.android.sdk.ModuleFeedback.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                ModuleFeedback.this.L.d("[ModuleFeedback] Cancel button clicked for the feedback widget");
                ModuleFeedback moduleFeedback = ModuleFeedback.this;
                moduleFeedback.reportFeedbackWidgetCancelButton(countlyFeedbackWidget, moduleFeedback.deviceInfo.mp.getAppVersion(context));
                FeedbackCallback feedbackCallback2 = feedbackCallback;
                if (feedbackCallback2 != null) {
                    feedbackCallback2.onClosed();
                }
            }
        });
        return builder;
    }

    void reportFeedbackWidgetCancelButton(CountlyFeedbackWidget countlyFeedbackWidget, String str) {
        String str2;
        this.L.d("[reportFeedbackWidgetCancelButton] Cancel button event");
        if (this.consentProvider.getConsent(Countly.CountlyFeatureNames.feedback)) {
            HashMap map = new HashMap();
            map.put("platform", "android");
            map.put("app_version", str);
            map.put("widget_id", "" + countlyFeedbackWidget.widgetId);
            map.put("closed", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
            if (countlyFeedbackWidget.type == FeedbackWidgetType.survey) {
                str2 = SURVEY_EVENT_KEY;
            } else if (countlyFeedbackWidget.type == FeedbackWidgetType.rating) {
                str2 = RATING_EVENT_KEY;
            } else {
                str2 = NPS_EVENT_KEY;
            }
            this.eventProvider.recordEventInternal(str2, map, 1, 0.0d, 0.0d, null, null);
        }
    }

    void getFeedbackWidgetDataInternal(CountlyFeedbackWidget countlyFeedbackWidget, final RetrieveFeedbackWidgetData retrieveFeedbackWidgetData) {
        String str;
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleFeedback] calling 'getFeedbackWidgetDataInternal', callback set:[");
        sb.append(retrieveFeedbackWidgetData != null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (retrieveFeedbackWidgetData == null) {
            this.L.e("[ModuleFeedback] Feedback widget data can't be retrieved without a callback");
            return;
        }
        if (countlyFeedbackWidget == null) {
            this.L.e("[ModuleFeedback] Feedback widget data if provided widget is 'null'");
            return;
        }
        if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.feedback)) {
            retrieveFeedbackWidgetData.onFinished(null, "Consent is not granted");
            return;
        }
        if (this.deviceIdProvider.isTemporaryIdEnabled()) {
            this.L.e("[ModuleFeedback] Feedback widget data can't be retrieved when in temporary device ID mode");
            retrieveFeedbackWidgetData.onFinished(null, "[ModuleFeedback] Feedback widget data can't be retrieved when in temporary device ID mode");
            return;
        }
        StringBuilder sb2 = new StringBuilder("widget_id=");
        int i = AnonymousClass5.$SwitchMap$ly$count$android$sdk$ModuleFeedback$FeedbackWidgetType[countlyFeedbackWidget.type.ordinal()];
        if (i == 1) {
            str = "/o/surveys/survey/widget";
        } else if (i == 2) {
            str = "/o/surveys/nps/widget";
        } else {
            str = i != 3 ? "" : "/o/surveys/rating/widget";
        }
        String str2 = str;
        sb2.append(UtilsNetworking.urlEncodeString(countlyFeedbackWidget.widgetId));
        sb2.append("&shown=1&sdk_version=");
        sb2.append(Countly.sharedInstance().COUNTLY_SDK_VERSION_STRING);
        sb2.append("&sdk_name=");
        sb2.append(Countly.sharedInstance().COUNTLY_SDK_NAME);
        sb2.append("&platform=android&app_version=");
        sb2.append(this.cachedAppVersion);
        ConnectionProcessor connectionProcessorCreateConnectionProcessor = this.requestQueueProvider.createConnectionProcessor();
        boolean networkingEnabled = connectionProcessorCreateConnectionProcessor.configProvider_.getNetworkingEnabled();
        String string = sb2.toString();
        this.L.d("[ModuleFeedback] Using following request params for retrieving widget data:[" + string + "]");
        new ImmediateRequestMaker().doWork(string, str2, connectionProcessorCreateConnectionProcessor, false, networkingEnabled, new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleFeedback.4
            @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
            public void callback(JSONObject jSONObject) {
                if (jSONObject == null) {
                    ModuleFeedback.this.L.d("[ModuleFeedback] Not possible to retrieve widget data. Probably due to lack of connection to the server");
                    retrieveFeedbackWidgetData.onFinished(null, "Not possible to retrieve widget data. Probably due to lack of connection to the server");
                    return;
                }
                ModuleFeedback.this.L.d("[ModuleFeedback] Retrieved widget data request: [" + jSONObject.toString() + "]");
                retrieveFeedbackWidgetData.onFinished(jSONObject, null);
            }
        }, this.L);
    }

    void reportFeedbackWidgetManuallyInternal(CountlyFeedbackWidget countlyFeedbackWidget, JSONObject jSONObject, Map<String, Object> map) {
        String str;
        if (countlyFeedbackWidget == null) {
            this.L.e("[ModuleFeedback] Can't report feedback widget data manually with 'null' widget info");
            return;
        }
        ModuleLog moduleLog = this.L;
        StringBuilder sb = new StringBuilder("[ModuleFeedback] reportFeedbackWidgetManuallyInternal, widgetData set:[");
        sb.append(jSONObject != null);
        sb.append(", widget id:[");
        sb.append(countlyFeedbackWidget.widgetId);
        sb.append("], widget type:[");
        sb.append(countlyFeedbackWidget.type);
        sb.append("], widget result set:[");
        sb.append(map != null);
        sb.append("]");
        moduleLog.d(sb.toString());
        if (!this.consentProvider.getConsent(Countly.CountlyFeatureNames.feedback)) {
            this.L.w("[ModuleFeedback] Can't report feedback widget data, consent is not granted");
            return;
        }
        if (this.deviceIdProvider.isTemporaryIdEnabled()) {
            this.L.e("[ModuleFeedback] feedback widget result can't be reported when in temporary device ID mode");
            return;
        }
        if (map != null) {
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> next = it.next();
                if (next.getKey() == null) {
                    this.L.w("[ModuleFeedback] provided feedback widget result contains a 'null' key, it will be removed, value[" + next.getValue() + "]");
                    it.remove();
                } else if (next.getKey().isEmpty()) {
                    this.L.w("[ModuleFeedback] provided feedback widget result contains an empty string key, it will be removed, value[" + next.getValue() + "]");
                    it.remove();
                } else if (next.getValue() == null) {
                    this.L.w("[ModuleFeedback] provided feedback widget result contains a 'null' value, it will be removed, key[" + next.getKey() + "]");
                    it.remove();
                }
            }
            if (countlyFeedbackWidget.type == FeedbackWidgetType.nps) {
                if (!map.containsKey("rating")) {
                    this.L.e("Provided NPS widget result does not have a 'rating' field, result can't be reported");
                    return;
                }
                Object obj = map.get("rating");
                if (!(obj instanceof Integer)) {
                    this.L.e("Provided NPS widget 'rating' field is not an integer, result can't be reported");
                    return;
                }
                int iIntValue = ((Integer) obj).intValue();
                if (iIntValue < 0 || iIntValue > 10) {
                    this.L.e("Provided NPS widget 'rating' value is out of bounds of the required value '[0;10]', it is probably an error");
                }
                if (!map.containsKey("comment")) {
                    this.L.w("Provided NPS widget result does not have a 'comment' field");
                }
            } else if (countlyFeedbackWidget.type != FeedbackWidgetType.survey && countlyFeedbackWidget.type == FeedbackWidgetType.rating) {
                if (!map.containsKey("rating")) {
                    this.L.e("Provided Rating widget result does not have a 'rating' field, result can't be reported");
                    return;
                }
                Object obj2 = map.get("rating");
                if (!(obj2 instanceof Integer)) {
                    this.L.e("Provided Rating widget 'rating' field is not an integer, result can't be reported");
                    return;
                }
                int iIntValue2 = ((Integer) obj2).intValue();
                if (iIntValue2 < 1 || iIntValue2 > 5) {
                    this.L.e("Provided Rating widget 'rating' value is out of bounds of the required value '[1;5]', it is probably an error");
                }
            }
        }
        if (jSONObject == null) {
            this.L.d("[ModuleFeedback] reportFeedbackWidgetManuallyInternal, widgetInfo is 'null', no validation will be done");
        } else {
            if (!countlyFeedbackWidget.widgetId.equals(jSONObject.optString(VideoDownloadSQLiteHelper.Columns._ID))) {
                this.L.w("[ModuleFeedback] id in widget info does not match the id in widget data");
            }
            String strOptString = jSONObject.optString("type");
            if (countlyFeedbackWidget.type == FeedbackWidgetType.nps) {
                if (!"nps".equals(strOptString)) {
                    this.L.w("[ModuleFeedback] type in widget info [" + strOptString + "] does not match the type in widget data [nps]");
                }
            } else if (countlyFeedbackWidget.type == FeedbackWidgetType.survey) {
                if (!"survey".equals(strOptString)) {
                    this.L.w("[ModuleFeedback] type in widget info [" + strOptString + "] does not match the type in widget data [survey]");
                }
            } else if (countlyFeedbackWidget.type == FeedbackWidgetType.rating && !"rating".equals(strOptString)) {
                this.L.w("[ModuleFeedback] type in widget info [" + strOptString + "] does not match the type in widget data [rating]");
            }
        }
        if (countlyFeedbackWidget.type == FeedbackWidgetType.nps) {
            str = NPS_EVENT_KEY;
        } else if (countlyFeedbackWidget.type == FeedbackWidgetType.survey) {
            str = SURVEY_EVENT_KEY;
        } else if (countlyFeedbackWidget.type == FeedbackWidgetType.rating) {
            str = RATING_EVENT_KEY;
        } else {
            str = "";
        }
        String str2 = str;
        HashMap map2 = new HashMap();
        map2.put("platform", "android");
        map2.put("app_version", this.cachedAppVersion);
        map2.put("widget_id", countlyFeedbackWidget.widgetId);
        if (map == null) {
            map2.put("closed", IcyHeaders.REQUEST_HEADER_ENABLE_METADATA_VALUE);
        } else {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                map2.put(entry.getKey(), entry.getValue());
            }
        }
        this.eventProvider.recordEventInternal(str2, map2, 1, 0.0d, 0.0d, null, null);
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        this.feedbackInterface = null;
    }

    public class Feedback {
        public Feedback() {
        }

        public void getAvailableFeedbackWidgets(RetrieveFeedbackWidgets retrieveFeedbackWidgets) {
            synchronized (ModuleFeedback.this._cly) {
                ModuleFeedback.this.L.i("[Feedback] Trying to retrieve feedback widget list");
                ModuleFeedback.this.getAvailableFeedbackWidgetsInternal(retrieveFeedbackWidgets);
            }
        }

        public void presentFeedbackWidget(CountlyFeedbackWidget countlyFeedbackWidget, Context context, String str, FeedbackCallback feedbackCallback) {
            synchronized (ModuleFeedback.this._cly) {
                ModuleFeedback.this.L.i("[Feedback] Trying to present feedback widget in an alert dialog");
                ModuleFeedback.this.presentFeedbackWidgetInternal(countlyFeedbackWidget, context, str, feedbackCallback);
            }
        }

        public void getFeedbackWidgetData(CountlyFeedbackWidget countlyFeedbackWidget, RetrieveFeedbackWidgetData retrieveFeedbackWidgetData) {
            synchronized (ModuleFeedback.this._cly) {
                ModuleFeedback.this.L.i("[Feedback] Trying to retrieve feedback widget data");
                ModuleFeedback.this.getFeedbackWidgetDataInternal(countlyFeedbackWidget, retrieveFeedbackWidgetData);
            }
        }

        public void reportFeedbackWidgetManually(CountlyFeedbackWidget countlyFeedbackWidget, JSONObject jSONObject, Map<String, Object> map) {
            synchronized (ModuleFeedback.this._cly) {
                ModuleFeedback.this.L.i("[Feedback] Trying to report feedback widget manually");
                ModuleFeedback.this.reportFeedbackWidgetManuallyInternal(countlyFeedbackWidget, jSONObject, map);
            }
        }
    }
}
