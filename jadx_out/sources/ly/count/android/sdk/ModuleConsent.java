package ly.count.android.sdk;

import android.content.Intent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleConsent extends ModuleBase implements ConsentProvider {
    protected static final String[] validFeatureNames = {Countly.CountlyFeatureNames.sessions, Countly.CountlyFeatureNames.events, Countly.CountlyFeatureNames.views, "location", Countly.CountlyFeatureNames.crashes, Countly.CountlyFeatureNames.attribution, Countly.CountlyFeatureNames.users, Countly.CountlyFeatureNames.push, Countly.CountlyFeatureNames.starRating, Countly.CountlyFeatureNames.remoteConfig, Countly.CountlyFeatureNames.apm, Countly.CountlyFeatureNames.feedback, Countly.CountlyFeatureNames.clicks, Countly.CountlyFeatureNames.scrolls};
    Consent consentInterface;
    final Map<String, Boolean> featureConsentValues;
    private final Map<String, String[]> groupedFeatures;
    protected boolean requiresConsent;

    public enum ConsentChangeSource {
        ChangeConsentCall,
        DeviceIDChangedNotMerged
    }

    ModuleConsent(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.consentInterface = null;
        int i = 0;
        this.requiresConsent = false;
        this.featureConsentValues = new HashMap();
        this.groupedFeatures = new HashMap();
        this.consentProvider = this;
        countlyConfig.consentProvider = this;
        this.L.v("[ModuleConsent] Initialising");
        this.L.i("[ModuleConsent] Is consent required? [" + countlyConfig.shouldRequireConsent + "]");
        for (String str : validFeatureNames) {
            this.featureConsentValues.put(str, false);
        }
        if (countlyConfig.shouldRequireConsent) {
            this.requiresConsent = countlyConfig.shouldRequireConsent;
            if (countlyConfig.enabledFeatureNames == null && !countlyConfig.enableAllConsents) {
                this.L.i("[ModuleConsent] Consent has been required but no consent was given during init");
            } else if (countlyConfig.enableAllConsents) {
                this.L.i("[ModuleConsent] Giving consent for all features");
                String[] strArr = validFeatureNames;
                int length = strArr.length;
                while (i < length) {
                    this.featureConsentValues.put(strArr[i], true);
                    i++;
                }
            } else {
                String[] strArr2 = countlyConfig.enabledFeatureNames;
                int length2 = strArr2.length;
                while (i < length2) {
                    this.featureConsentValues.put(strArr2[i], true);
                    i++;
                }
            }
        }
        this.consentInterface = new Consent();
    }

    @Override // ly.count.android.sdk.ConsentProvider
    public boolean getConsent(String str) {
        return getConsentInternal(str);
    }

    @Override // ly.count.android.sdk.ConsentProvider
    public boolean anyConsentGiven() {
        if (!this.requiresConsent) {
            return true;
        }
        Iterator<String> it = this.featureConsentValues.keySet().iterator();
        while (it.hasNext()) {
            if (getConsentTrue(it.next())) {
                return true;
            }
        }
        return false;
    }

    boolean getConsentInternal(String str) {
        if (str == null) {
            this.L.e("[ModuleConsent] getConsentInternal, Can't call this with a 'null' feature name!");
            return false;
        }
        if (!this.requiresConsent) {
            return true;
        }
        boolean consentTrue = getConsentTrue(str);
        this.L.v("[ModuleConsent] Returning consent for feature named: [" + str + "] [" + consentTrue + "]");
        return consentTrue;
    }

    private boolean getConsentTrue(String str) {
        Boolean bool = this.featureConsentValues.get(str);
        if (bool == null) {
            bool = false;
        }
        return bool.booleanValue();
    }

    public void checkAllConsentInternal() {
        this.L.d("[ModuleConsent] Checking and printing consent for All features");
        this.L.d("[ModuleConsent] Is consent required? [" + this.requiresConsent + "]");
        getConsent(Countly.CountlyFeatureNames.push);
        StringBuilder sb = new StringBuilder();
        for (String str : this.featureConsentValues.keySet()) {
            sb.append("Feature named [");
            sb.append(str);
            sb.append("], consent value: [");
            sb.append(this.featureConsentValues.get(str));
            sb.append("]\n");
        }
        this.L.d(sb.toString());
    }

    void doPushConsentSpecialAction(boolean z) {
        this.L.d("[ModuleConsent] Doing push consent special action: [" + z + "]");
        this._cly.countlyStore.setConsentPush(z);
        this._cly.context_.sendBroadcast(new Intent(Countly.CONSENT_BROADCAST));
    }

    private boolean isValidFeatureName(String str) {
        for (String str2 : validFeatureNames) {
            if (str2.equals(str)) {
                return true;
            }
        }
        return false;
    }

    private String formatConsentState(Map<String, Boolean> map) {
        StringBuilder sb = new StringBuilder("{");
        boolean z = false;
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (z) {
                sb.append(",");
            } else {
                z = true;
            }
            sb.append(Typography.quote);
            sb.append(entry.getKey());
            sb.append("\":");
            sb.append(entry.getValue());
        }
        sb.append("}");
        return sb.toString();
    }

    void setConsentInternal(String[] strArr, boolean z, ConsentChangeSource consentChangeSource) {
        if (this.requiresConsent) {
            if (strArr == null) {
                this.L.w("[ModuleConsent] Calling setConsent with null featureNames!");
                return;
            }
            ArrayList arrayList = new ArrayList(strArr.length);
            for (String str : strArr) {
                this.L.d("[ModuleConsent] Setting consent for feature: [" + str + "] with value: [" + z + "]");
                if (!isValidFeatureName(str)) {
                    this.L.w("[ModuleConsent] Given feature: [" + str + "] is not a valid name, ignoring it");
                } else if (getConsentTrue(str) != z) {
                    arrayList.add(str);
                    this.featureConsentValues.put(str, Boolean.valueOf(z));
                }
            }
            Iterator<ModuleBase> it = this._cly.modules.iterator();
            while (it.hasNext()) {
                it.next().onConsentChanged(arrayList, z, consentChangeSource);
            }
            this.requestQueueProvider.sendConsentChanges(formatConsentState(this.featureConsentValues));
        }
    }

    public void removeConsentInternal(String[] strArr, ConsentChangeSource consentChangeSource) {
        this.L.d("[ModuleConsent] Removing consent for features named: [" + Arrays.toString(strArr) + "]");
        setConsentInternal(strArr, false, consentChangeSource);
    }

    public void removeConsentAllInternal(ConsentChangeSource consentChangeSource) {
        this.L.d("[ModuleConsent] Removing consent for all features");
        removeConsentInternal(validFeatureNames, consentChangeSource);
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (this.requiresConsent) {
            doPushConsentSpecialAction(getConsentTrue(Countly.CountlyFeatureNames.push));
            this.requestQueueProvider.sendConsentChanges(formatConsentState(this.featureConsentValues));
            if (this.L.logEnabled()) {
                this.L.d("[ModuleConsent] [Init] Countly is initialized with the current consent state:");
                checkAllConsentInternal();
            }
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onConsentChanged(List<String> list, boolean z, ConsentChangeSource consentChangeSource) {
        if (list.contains(Countly.CountlyFeatureNames.push)) {
            doPushConsentSpecialAction(z);
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        this.consentInterface = null;
    }

    public class Consent {
        public Consent() {
        }

        public void checkAllConsent() {
            synchronized (ModuleConsent.this._cly) {
                ModuleConsent.this.L.i("[Consent] calling checkAllConsent");
                ModuleConsent.this.checkAllConsentInternal();
            }
        }

        public boolean getConsent(String str) {
            boolean consentInternal;
            synchronized (ModuleConsent.this._cly) {
                consentInternal = ModuleConsent.this.getConsentInternal(str);
            }
            return consentInternal;
        }

        public void removeConsentAll() {
            synchronized (ModuleConsent.this._cly) {
                ModuleConsent.this.removeConsentAllInternal(ConsentChangeSource.ChangeConsentCall);
            }
        }

        public void removeConsent(String[] strArr) {
            synchronized (ModuleConsent.this._cly) {
                ModuleConsent.this.removeConsentInternal(strArr, ConsentChangeSource.ChangeConsentCall);
            }
        }

        public void giveConsentAll() {
            synchronized (ModuleConsent.this._cly) {
                ModuleConsent.this.L.i("[Consent] Giving consent for all features");
                ModuleConsent.this.setConsentInternal(ModuleConsent.validFeatureNames, true, ConsentChangeSource.ChangeConsentCall);
            }
        }

        public void giveConsent(String[] strArr) {
            synchronized (ModuleConsent.this._cly) {
                ModuleConsent.this.setConsentInternal(strArr, true, ConsentChangeSource.ChangeConsentCall);
            }
        }

        public void setConsent(String[] strArr, boolean z) {
            synchronized (ModuleConsent.this._cly) {
                ModuleConsent.this.setConsentInternal(strArr, z, ConsentChangeSource.ChangeConsentCall);
            }
        }

        public void setConsentFeatureGroup(String str, boolean z) {
            synchronized (ModuleConsent.this._cly) {
                if (!ModuleConsent.this.groupedFeatures.containsKey(str)) {
                    ModuleConsent.this.L.d("[Countly] Trying to set consent for a unknown feature group: [" + str + "]");
                    return;
                }
                ModuleConsent moduleConsent = ModuleConsent.this;
                moduleConsent.setConsentInternal((String[]) moduleConsent.groupedFeatures.get(str), z, ConsentChangeSource.ChangeConsentCall);
            }
        }

        public void createFeatureGroup(String str, String[] strArr) {
            synchronized (ModuleConsent.this._cly) {
                ModuleConsent.this.groupedFeatures.put(str, strArr);
            }
        }
    }
}
