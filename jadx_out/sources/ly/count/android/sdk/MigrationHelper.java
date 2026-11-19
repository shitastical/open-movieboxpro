package ly.count.android.sdk;

import android.content.Context;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import ly.count.android.sdk.internal.RemoteConfigValueStore;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes6.dex */
class MigrationHelper {
    static final int DATA_SCHEMA_VERSIONS = 3;
    public static final String key_from_0_to_1_custom_id_set = "0_1_custom_id_set";
    public static final String legacyCACHED_PUSH_MESSAGING_MODE = "PUSH_MESSAGING_MODE";
    public static final String legacyDeviceIDTypeValue_AdvertisingID = "ADVERTISING_ID";
    ModuleLog L;
    Context cachedContext;
    StorageProvider storage;

    public MigrationHelper(StorageProvider storageProvider, ModuleLog moduleLog, Context context) {
        this.storage = storageProvider;
        this.L = moduleLog;
        this.cachedContext = context;
        moduleLog.v("[MigrationHelper] Initialising");
    }

    public void doWork(Map<String, Object> map) {
        int currentSchemaVersion = getCurrentSchemaVersion();
        this.L.v("[MigrationHelper] doWork, current version:[" + currentSchemaVersion + "]");
        if (currentSchemaVersion < 0) {
            this.L.e("[MigrationHelper] doWork, returned schema version is negative, encountered serious issue");
            return;
        }
        while (currentSchemaVersion < 3) {
            performMigrationStep(currentSchemaVersion, map);
            currentSchemaVersion = getCurrentSchemaVersion();
        }
    }

    int getCurrentSchemaVersion() {
        int dataSchemaVersion = this.storage.getDataSchemaVersion();
        if (dataSchemaVersion != -1) {
            return dataSchemaVersion;
        }
        setInitialSchemaVersion();
        return this.storage.getDataSchemaVersion();
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0052  */
    /* JADX WARN: Code duplicated, block: B:19:? A[RETURN, SYNTHETIC] */
    void performMigrationStep(int i, Map<String, Object> map) {
        int i2;
        if (i == 0) {
            this.L.w("[MigrationHelper] performMigrationStep, performing migration from version [0] -> [1]");
            performMigration0To1(map);
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        this.L.w("[MigrationHelper] performMigrationStep, attempting to perform migration while already having the latest schema version, skipping [" + i + "]");
                    } else {
                        this.L.w("[MigrationHelper] performMigrationStep, migration is performed out of the currently expected bounds, skipping [" + i + "]");
                    }
                    i2 = i;
                } else {
                    performMigration2To3(map);
                }
                if (i2 != i) {
                    this.storage.setDataSchemaVersion(i2);
                }
            }
            performMigration1To2(map);
        }
        i2 = i + 1;
        if (i2 != i) {
            this.storage.setDataSchemaVersion(i2);
        }
    }

    void setInitialSchemaVersion() {
        if (this.storage.anythingSetInStorage()) {
            this.storage.setDataSchemaVersion(0);
        } else {
            this.storage.setDataSchemaVersion(3);
        }
    }

    void performMigration0To1(Map<String, Object> map) {
        String deviceIDType = this.storage.getDeviceIDType();
        String deviceID = this.storage.getDeviceID();
        if (deviceIDType == null && deviceID == null) {
            this.storage.setDeviceIDType(DeviceIdType.OPEN_UDID.toString());
            deviceIDType = DeviceIdType.OPEN_UDID.toString();
        } else if (deviceIDType == null) {
            Boolean bool = (Boolean) map.get(key_from_0_to_1_custom_id_set);
            if (bool == null) {
                bool = false;
            }
            if (bool.booleanValue()) {
                this.storage.setDeviceIDType(DeviceIdType.DEVELOPER_SUPPLIED.toString());
                deviceIDType = DeviceIdType.DEVELOPER_SUPPLIED.toString();
            } else {
                this.storage.setDeviceIDType(DeviceIdType.OPEN_UDID.toString());
                deviceIDType = DeviceIdType.OPEN_UDID.toString();
            }
        }
        if (!deviceIDType.equals(DeviceIdType.OPEN_UDID.toString()) && deviceIDType.equals(legacyDeviceIDTypeValue_AdvertisingID)) {
            this.storage.setDeviceIDType(DeviceIdType.OPEN_UDID.toString());
            deviceIDType = DeviceIdType.OPEN_UDID.toString();
        }
        if (deviceIDType.equals(DeviceIdType.OPEN_UDID.toString())) {
            if (deviceID == null || deviceID.isEmpty()) {
                this.storage.setDeviceID(UUID.randomUUID().toString());
            }
        }
    }

    void performMigration1To2(Map<String, Object> map) {
        try {
            JSONObject jSONObject = new JSONObject(this.storage.getRemoteConfigValues());
            JSONObject jSONObject2 = new JSONObject();
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    if (jSONObject.opt(next) != null) {
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("v", jSONObject.get(next));
                        jSONObject3.put(RemoteConfigValueStore.keyCacheFlag, 1);
                        jSONObject2.put(next, jSONObject3);
                    }
                } catch (Exception e) {
                    Countly.sharedInstance().L.e("[MigrationHelper] performMigration1To2, transforming remote config values, " + e.toString());
                }
            }
            this.storage.setRemoteConfigValues(jSONObject2.toString());
        } catch (JSONException e2) {
            this.L.w("[MigrationHelper] performMigration1To2, failed at parsing old RC data. Clearing data structure and continuing. " + e2);
            this.storage.setRemoteConfigValues("");
        }
    }

    void performMigration2To3(Map<String, Object> map) {
        CountlyStore.createPreferencesPush(this.cachedContext).edit().remove(legacyCACHED_PUSH_MESSAGING_MODE).apply();
    }
}
