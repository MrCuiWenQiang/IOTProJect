package com.iot.manager.entity.net.result.termlist;
import java.util.List;
/**
 * Auto-generated: 2019-03-26 23:7:14
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Lists {

    private String useStateText;
    private boolean isNewRecord;
    private String authFlag;
    private List<Deviceslist> devicesList;
    private String id;
    private String name;
    private String placeName;
    private String runStateText;
    private String placeId;

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

    public List<Deviceslist> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Deviceslist> devicesList) {
        this.devicesList = devicesList;
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

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
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
}