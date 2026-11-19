package ly.count.android.sdk;

import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class ModuleLocation extends ModuleBase {
    String locationCity;
    String locationCountryCode;
    boolean locationDisabled;
    String locationGpsCoordinates;
    Location locationInterface;
    String locationIpAddress;

    ModuleLocation(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.locationDisabled = false;
        this.locationCountryCode = null;
        this.locationCity = null;
        this.locationGpsCoordinates = null;
        this.locationIpAddress = null;
        this.locationInterface = null;
        this.L.v("[ModuleLocation] Initialising");
        this.locationInterface = new Location();
    }

    void resetLocationValues() {
        this.locationCity = null;
        this.locationCountryCode = null;
        this.locationGpsCoordinates = null;
        this.locationIpAddress = null;
    }

    void sendCurrentLocationIfValid() {
        this.L.d("[ModuleLocation] Calling 'sendCurrentLocationIfValid'");
        if (this.locationDisabled) {
            return;
        }
        if (this.locationCountryCode == null && this.locationCity == null && this.locationIpAddress == null && this.locationGpsCoordinates == null) {
            return;
        }
        this.requestQueueProvider.sendLocation(this.locationDisabled, this.locationCountryCode, this.locationCity, this.locationGpsCoordinates, this.locationIpAddress);
    }

    void disableLocationInternal() {
        this.L.d("[ModuleLocation] Calling 'disableLocationInternal'");
        if (this.consentProvider.getConsent("location")) {
            this.locationDisabled = true;
            performLocationErasure();
        }
    }

    void performLocationErasure() {
        resetLocationValues();
        this.requestQueueProvider.sendLocation(true, null, null, null, null);
    }

    void setLocationInternal(String str, String str2, String str3, String str4) {
        this.L.d("[ModuleLocation] Calling 'setLocationInternal'");
        this.L.d("[ModuleLocation] Setting location parameters, cc[" + str + "] cy[" + str2 + "] gps[" + str3 + "] ip[" + str4 + "]");
        if (this.consentProvider.getConsent("location")) {
            this.locationCountryCode = str;
            this.locationCity = str2;
            this.locationGpsCoordinates = str3;
            this.locationIpAddress = str4;
            if ((str == null && str2 != null) || (str2 == null && str != null)) {
                this.L.w("[ModuleLocation] In \"setLocation\" both city and country code need to be set at the same time to be sent");
            }
            if (str != null || str2 != null || str3 != null || str4 != null) {
                this.locationDisabled = false;
            }
            if (this._cly.isBeginSessionSent || !this.consentProvider.getConsent(Countly.CountlyFeatureNames.sessions)) {
                this.requestQueueProvider.sendLocation(this.locationDisabled, this.locationCountryCode, this.locationCity, this.locationGpsCoordinates, this.locationIpAddress);
            }
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (!this.consentProvider.getConsent("location")) {
            performLocationErasure();
            return;
        }
        if (countlyConfig.disableLocation) {
            disableLocationInternal();
        } else {
            if (countlyConfig.locationIpAddress == null && countlyConfig.locationLocation == null && countlyConfig.locationCity == null && countlyConfig.locationCountyCode == null) {
                return;
            }
            setLocationInternal(countlyConfig.locationCountyCode, countlyConfig.locationCity, countlyConfig.locationLocation, countlyConfig.locationIpAddress);
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void onConsentChanged(List<String> list, boolean z, ModuleConsent.ConsentChangeSource consentChangeSource) {
        if (!list.contains("location") || z) {
            return;
        }
        performLocationErasure();
    }

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
        this.locationInterface = null;
    }

    public class Location {
        public Location() {
        }

        public void disableLocation() {
            synchronized (ModuleLocation.this._cly) {
                ModuleLocation.this.L.i("[Location] Calling 'disableLocation'");
                ModuleLocation.this.disableLocationInternal();
            }
        }

        public void setLocation(String str, String str2, String str3, String str4) {
            synchronized (ModuleLocation.this._cly) {
                ModuleLocation.this.L.i("[Location] Calling 'setLocation'");
                ModuleLocation.this.setLocationInternal(str, str2, str3, str4);
            }
        }
    }
}
