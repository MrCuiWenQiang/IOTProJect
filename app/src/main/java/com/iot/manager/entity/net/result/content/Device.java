package com.iot.manager.entity.net.result.content;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class Device {

    /**
     * {
     "deviceId":"df496fe2-4a9f-42e8-9386-6dcfaad9e854",
     "openState":"0",
     "msgState":"1",
     "userId":"562750a9b0b847be99c51e9675e07d87",
     "controllerCode":"0010531006",
     "iotDeviceName":"15662701031-0010531006",
     "controllerName":"test002",
     "controllerRunState":"0",
     "code":"kqwd_cgq",
     "placeNameTemp":"测试地",
     "isNewRecord":false,
     "status":"0",
     "id":"651c74c3-7c47-46ef-9b8b-568c1ce0d220",
     "name":"空气温度计",
     "msgSendState":"0",
     "sort":"1",
     "subDefaultName":"空气温度计",
     "type":"0"
     }
     */

    private String deviceId;
    private String openState;
    private String msgState;
    private String userId;
    private String controllerCode;
    private String iotDeviceName;
    private String controllerName;
    private String controllerRunState;
    private String code;
    private String placeNameTemp;
    private boolean isNewRecord;
    private String status;
    private String id;
    private String name;
    private String msgSendState;
    private String sort;
    private String subDefaultName;
    private String type;
    private String subSuggestName;

    private String ruleCode;
    private String ruleId;
    private String remark;
    private String unit;
    private String subRuleCode;

    public String getRuleCode() {
        return ruleCode;
    }

    public void setRuleCode(String ruleCode) {
        this.ruleCode = ruleCode;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSubRuleCode() {
        return subRuleCode;
    }

    public void setSubRuleCode(String subRuleCode) {
        this.subRuleCode = subRuleCode;
    }

    public String getSubSuggestName() {
        return subSuggestName;
    }

    public void setSubSuggestName(String subSuggestName) {
        this.subSuggestName = subSuggestName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOpenState() {
        return openState;
    }

    public void setOpenState(String openState) {
        this.openState = openState;
    }

    public String getMsgState() {
        return msgState;
    }

    public void setMsgState(String msgState) {
        this.msgState = msgState;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getControllerCode() {
        return controllerCode;
    }

    public void setControllerCode(String controllerCode) {
        this.controllerCode = controllerCode;
    }

    public String getIotDeviceName() {
        return iotDeviceName;
    }

    public void setIotDeviceName(String iotDeviceName) {
        this.iotDeviceName = iotDeviceName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getControllerRunState() {
        return controllerRunState;
    }

    public void setControllerRunState(String controllerRunState) {
        this.controllerRunState = controllerRunState;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPlaceNameTemp() {
        return placeNameTemp;
    }

    public void setPlaceNameTemp(String placeNameTemp) {
        this.placeNameTemp = placeNameTemp;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getMsgSendState() {
        return msgSendState;
    }

    public void setMsgSendState(String msgSendState) {
        this.msgSendState = msgSendState;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getSubDefaultName() {
        return subDefaultName;
    }

    public void setSubDefaultName(String subDefaultName) {
        this.subDefaultName = subDefaultName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
