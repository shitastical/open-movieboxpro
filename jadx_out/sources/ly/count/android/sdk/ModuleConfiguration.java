package ly.count.android.sdk;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
class ModuleConfiguration extends ModuleBase implements ConfigurationProvider {
    static final boolean defaultVNetworking = true;
    static final boolean defaultVTracking = true;
    static final String keyNetworking = "networking";
    static final String keyRConfig = "c";
    static final String keyRTimestamp = "t";
    static final String keyRVersion = "v";
    static final String keyTracking = "tracking";
    boolean currentVNetworking;
    boolean currentVTracking;
    ImmediateRequestGenerator immediateRequestGenerator;
    JSONObject latestRetrievedConfiguration;
    JSONObject latestRetrievedConfigurationFull;
    boolean serverConfigEnabled;

    @Override // ly.count.android.sdk.ModuleBase
    void halt() {
    }

    ModuleConfiguration(Countly countly, CountlyConfig countlyConfig) {
        super(countly, countlyConfig);
        this.serverConfigEnabled = false;
        this.latestRetrievedConfigurationFull = null;
        this.latestRetrievedConfiguration = null;
        this.currentVTracking = true;
        this.currentVNetworking = true;
        this.L.v("[ModuleConfiguration] Initialising");
        countlyConfig.configProvider = this;
        this.configProvider = this;
        this.serverConfigEnabled = countlyConfig.serverConfigurationEnabled;
        this.immediateRequestGenerator = countlyConfig.immediateRequestGenerator;
        countlyConfig.countlyStore.setConfigurationProvider(this);
        if (this.serverConfigEnabled) {
            loadConfigFromStorage();
            updateConfigVariables();
        }
    }

    @Override // ly.count.android.sdk.ModuleBase
    void initFinished(CountlyConfig countlyConfig) {
        if (this.serverConfigEnabled) {
            fetchConfigFromServer();
        }
    }

    void loadConfigFromStorage() {
        String serverConfig = this.storageProvider.getServerConfig();
        this.L.v("[ModuleConfiguration] loadConfigFromStorage, [" + serverConfig + "]");
        if (serverConfig == null || serverConfig.isEmpty()) {
            this.L.d("[ModuleConfiguration] loadStoredConfig, no configs persistently stored");
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(serverConfig);
            this.latestRetrievedConfigurationFull = jSONObject;
            this.latestRetrievedConfiguration = jSONObject.getJSONObject("c");
            this.L.d("[ModuleConfiguration] loadStoredConfig, stored config loaded [" + serverConfig + "]");
        } catch (JSONException e) {
            this.L.w("[ModuleConfiguration] loadStoredConfig, failed to parse, " + e);
            this.latestRetrievedConfigurationFull = null;
            this.latestRetrievedConfiguration = null;
        }
    }

    void updateConfigVariables() {
        this.L.v("[ModuleConfiguration] updateConfigVariables");
        this.currentVNetworking = true;
        this.currentVTracking = true;
        JSONObject jSONObject = this.latestRetrievedConfiguration;
        if (jSONObject == null) {
            return;
        }
        if (jSONObject.has(keyNetworking)) {
            try {
                this.currentVNetworking = this.latestRetrievedConfiguration.getBoolean(keyNetworking);
            } catch (JSONException e) {
                this.L.w("[ModuleConfiguration] updateConfigs, failed to load 'networking', " + e);
            }
        }
        if (this.latestRetrievedConfiguration.has(keyTracking)) {
            try {
                this.currentVTracking = this.latestRetrievedConfiguration.getBoolean(keyTracking);
            } catch (JSONException e2) {
                this.L.w("[ModuleConfiguration] updateConfigs, failed to load 'tracking', " + e2);
            }
        }
    }

    void saveAndStoreDownloadedConfig(JSONObject jSONObject) {
        this.L.v("[ModuleConfiguration] saveAndStoreDownloadedConfig");
        if (!jSONObject.has("v")) {
            this.L.w("[ModuleConfiguration] saveAndStoreDownloadedConfig, Retrieved configuration does not has a 'version' field. Config will be ignored.");
            return;
        }
        if (!jSONObject.has(keyRTimestamp)) {
            this.L.w("[ModuleConfiguration] saveAndStoreDownloadedConfig, Retrieved configuration does not has a 'timestamp' field. Config will be ignored.");
            return;
        }
        if (!jSONObject.has("c")) {
            this.L.w("[ModuleConfiguration] saveAndStoreDownloadedConfig, Retrieved configuration does not has a 'configuration' field. Config will be ignored.");
            return;
        }
        this.latestRetrievedConfigurationFull = jSONObject;
        try {
            this.latestRetrievedConfiguration = jSONObject.getJSONObject("c");
            this.storageProvider.setServerConfig(jSONObject.toString());
            updateConfigVariables();
        } catch (JSONException e) {
            this.latestRetrievedConfigurationFull = null;
            this.latestRetrievedConfiguration = null;
            this.L.w("[ModuleConfiguration] saveAndStoreDownloadedConfig, Failed retrieving internal config, " + e);
        }
    }

    void fetchConfigFromServer() {
        this.L.v("[ModuleConfiguration] fetchConfigFromServer");
        this.immediateRequestGenerator.CreateImmediateRequestMaker().doWork(this.requestQueueProvider.prepareServerConfigRequest(), "/o/sdk", this.requestQueueProvider.createConnectionProcessor(), false, true, new ImmediateRequestMaker.InternalImmediateRequestCallback() { // from class: ly.count.android.sdk.ModuleConfiguration$$ExternalSyntheticLambda0
            @Override // ly.count.android.sdk.ImmediateRequestMaker.InternalImmediateRequestCallback
            public final void callback(JSONObject jSONObject) {
                this.f$0.m3507x158050c7(jSONObject);
            }
        }, this.L);
    }

    /* JADX INFO: renamed from: lambda$fetchConfigFromServer$0$ly-count-android-sdk-ModuleConfiguration, reason: not valid java name */
    /* synthetic */ void m3507x158050c7(JSONObject jSONObject) {
        if (jSONObject == null) {
            this.L.w("[ModuleConfiguration] Not possible to retrieve configuration data. Probably due to lack of connection to the server");
            return;
        }
        this.L.d("[ModuleConfiguration] Retrieved configuration response: [" + jSONObject.toString() + "]");
        saveAndStoreDownloadedConfig(jSONObject);
    }

    @Override // ly.count.android.sdk.ConfigurationProvider
    public boolean getNetworkingEnabled() {
        if (this.serverConfigEnabled) {
            return this.currentVNetworking;
        }
        return true;
    }

    @Override // ly.count.android.sdk.ConfigurationProvider
    public boolean getTrackingEnabled() {
        if (this.serverConfigEnabled) {
            return this.currentVTracking;
        }
        return true;
    }
}
