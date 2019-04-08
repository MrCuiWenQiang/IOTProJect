package com.iot.manager.entity.net.result.settingtern;
import java.util.List;

/**
 * Auto-generated: 2019-03-27 12:9:57
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class TernEntity {

    private String id;
    private boolean isNewRecord;
    private String conditionRelayId;
    private String deviceName;
    private String placeName;
    private String controllerName;
    private List<Openconditiondetails> openConditionDetails;
    private List<Closeconditiondetails> closeConditionDetails;
    private Controlconditionrelaydetailpo controlConditionRelayDetailPo;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getConditionRelayId() {
        return conditionRelayId;
    }

    public void setConditionRelayId(String conditionRelayId) {
        this.conditionRelayId = conditionRelayId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public List<Openconditiondetails> getOpenConditionDetails() {
        return openConditionDetails;
    }

    public void setOpenConditionDetails(List<Openconditiondetails> openConditionDetails) {
        this.openConditionDetails = openConditionDetails;
    }

    public List<Closeconditiondetails> getCloseConditionDetails() {
        return closeConditionDetails;
    }

    public void setCloseConditionDetails(List<Closeconditiondetails> closeConditionDetails) {
        this.closeConditionDetails = closeConditionDetails;
    }

    public Controlconditionrelaydetailpo getControlConditionRelayDetailPo() {
        return controlConditionRelayDetailPo;
    }

    public void setControlConditionRelayDetailPo(Controlconditionrelaydetailpo controlConditionRelayDetailPo) {
        this.controlConditionRelayDetailPo = controlConditionRelayDetailPo;
    }
}