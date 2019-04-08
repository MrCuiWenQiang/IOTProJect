package com.iot.manager.entity.net.result.hand;

import java.util.List;

/**
 * Auto-generated: 2019-03-23 12:59:44
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class HList {

    private String id;
    private boolean isNewRecord;
    private String name;
    private String placeId;
    private String placeName;
    private String useStateText;
    private String runStateText;
    private List<Deviceslist> devicesList;
    private String authFlag;
    private String sort;

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
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

    public String getRunStateText() {
        return runStateText;
    }

    public void setRunStateText(String runStateText) {
        this.runStateText = runStateText;
    }

    public List<Deviceslist> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Deviceslist> devicesList) {
        this.devicesList = devicesList;
    }

    public String getAuthFlag() {
        return authFlag;
    }

    public void setAuthFlag(String authFlag) {
        this.authFlag = authFlag;
    }
}