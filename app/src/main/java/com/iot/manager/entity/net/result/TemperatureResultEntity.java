package com.iot.manager.entity.net.result;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/28 0028.
 */
public class TemperatureResultEntity {

    private String deviceId;
    private String icon;
    private boolean isNewRecord;
    private String readTime;
    private String unitKey;
    private String id;
    private String value;
    private String sensorName;
    private String type;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getReadTime() {
        return readTime;
    }

    public void setReadTime(String readTime) {
        this.readTime = readTime;
    }

    public String getUnitKey() {
        return unitKey;
    }

    public void setUnitKey(String unitKey) {
        this.unitKey = unitKey;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
