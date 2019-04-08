package com.iot.manager.entity.net.result.content;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class ContentInfoEntity {
    private String userId;
    private String remark;
    private String runState;
    private String iotDeviceName;
    private String placeName;
    private String useStateText;
    private boolean isNewRecord;
    private String authFlag;
    private String id;
    private String name;
    private String productKey;
    private String deviceCode;
    private String runStateText;
    private String placeId;
    private List<Device> devicesList;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRunState() {
        return runState;
    }

    public void setRunState(String runState) {
        this.runState = runState;
    }

    public String getIotDeviceName() {
        return iotDeviceName;
    }

    public void setIotDeviceName(String iotDeviceName) {
        this.iotDeviceName = iotDeviceName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getUseStateText() {
        return useStateText;
    }

    public void setUseStateText(String useStateText) {
        this.useStateText = useStateText;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag(String authFlag) {
        this.authFlag = authFlag;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProductKey() {
        return productKey;
    }

    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getRunStateText() {
        return runStateText;
    }

    public void setRunStateText(String runStateText) {
        this.runStateText = runStateText;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public List<Device> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Device> devicesList) {
        this.devicesList = devicesList;
    }


}
