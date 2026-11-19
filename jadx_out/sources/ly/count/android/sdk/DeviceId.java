package ly.count.android.sdk;

/* JADX INFO: loaded from: classes6.dex */
public class DeviceId {
    protected static final String temporaryCountlyDeviceId = "CLYTemporaryDeviceID";
    ModuleLog L;
    private String id;
    OpenUDIDProvider openUDIDProvider;
    StorageProvider storageProvider;
    private DeviceIdType type;

    protected DeviceId(String str, StorageProvider storageProvider, ModuleLog moduleLog, OpenUDIDProvider openUDIDProvider) {
        if ("".equals(str)) {
            throw new IllegalStateException("Empty device ID is not a valid value, [" + str + "]");
        }
        this.storageProvider = storageProvider;
        this.openUDIDProvider = openUDIDProvider;
        this.L = moduleLog;
        moduleLog.d("[DeviceId-int] initialising with values, device ID:[" + str + "]");
        String deviceID = this.storageProvider.getDeviceID();
        DeviceIdType deviceIdTypeRetrieveStoredType = retrieveStoredType();
        this.L.d("[DeviceId-int] The following values were stored, device ID:[" + deviceID + "] type:[" + deviceIdTypeRetrieveStoredType + "]");
        if (deviceID != null && deviceIdTypeRetrieveStoredType != null) {
            this.id = deviceID;
            this.type = deviceIdTypeRetrieveStoredType;
            return;
        }
        if (deviceIdTypeRetrieveStoredType == null && deviceID != null) {
            this.L.e("[DeviceId-int] init, device id type currently is 'null', falling back to OPEN_UDID");
            setAndStoreId(DeviceIdType.OPEN_UDID, deviceID);
        }
        if (deviceID == null) {
            if (str == null) {
                this.L.i("[DeviceId-int] Using OpenUDID");
                setAndStoreId(DeviceIdType.OPEN_UDID, openUDIDProvider.getOpenUDID());
            } else if (str.equals(temporaryCountlyDeviceId)) {
                this.L.i("[DeviceId-int] Entering temp ID mode");
                setAndStoreId(DeviceIdType.TEMPORARY_ID, str);
            } else {
                this.L.i("[DeviceId-int] Using dev provided ID");
                setAndStoreId(DeviceIdType.DEVELOPER_SUPPLIED, str);
            }
        }
    }

    private DeviceIdType retrieveStoredType() {
        String deviceIDType = this.storageProvider.getDeviceIDType();
        if (deviceIDType == null) {
            return null;
        }
        if (deviceIDType.equals(DeviceIdType.DEVELOPER_SUPPLIED.toString())) {
            return DeviceIdType.DEVELOPER_SUPPLIED;
        }
        if (deviceIDType.equals(DeviceIdType.OPEN_UDID.toString())) {
            return DeviceIdType.OPEN_UDID;
        }
        if (deviceIDType.equals(DeviceIdType.TEMPORARY_ID.toString())) {
            return DeviceIdType.TEMPORARY_ID;
        }
        this.L.e("[DeviceId-int] device ID type can't be determined, [" + deviceIDType + "]");
        return null;
    }

    protected String getCurrentId() {
        if (this.id == null && this.type == DeviceIdType.OPEN_UDID) {
            this.id = this.openUDIDProvider.getOpenUDID();
        }
        return this.id;
    }

    protected void setId(DeviceIdType deviceIdType, String str) {
        this.L.v("[DeviceId-int] setId, Device ID is " + str + " (type " + deviceIdType + ")");
        this.type = deviceIdType;
        this.id = str;
    }

    protected void changeToCustomId(String str) {
        this.L.v("[DeviceId-int] changeToCustomId, current Device ID is [" + this.id + "] new ID is[" + str + "]");
        setAndStoreId(DeviceIdType.DEVELOPER_SUPPLIED, str);
    }

    protected void enterTempIDMode() {
        this.L.v("[DeviceId-int] enterTempIDMode");
        setAndStoreId(DeviceIdType.DEVELOPER_SUPPLIED, temporaryCountlyDeviceId);
    }

    void setAndStoreId(DeviceIdType deviceIdType, String str) {
        this.id = str;
        this.type = deviceIdType;
        this.storageProvider.setDeviceID(str);
        this.storageProvider.setDeviceIDType(deviceIdType.toString());
    }

    protected DeviceIdType getType() {
        if (isTemporaryIdModeEnabled()) {
            return DeviceIdType.TEMPORARY_ID;
        }
        return this.type;
    }

    protected boolean isTemporaryIdModeEnabled() {
        String currentId = getCurrentId();
        if (currentId == null) {
            return false;
        }
        return currentId.equals(temporaryCountlyDeviceId);
    }
}
