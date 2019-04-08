package com.iot.manager.entity.net.result;

/**
 * Function :
 * Remarks  :
 * "masterDeviceIds": "string(主控设备的主键，多设备之间用逗号隔开-必填)",
 "masterDeviceNames": "string(主控设备的名称，多设备之间用逗号隔开-必填)"
 "passiveDeviceIds": "string(被控设备的主键，多设备之间用逗号隔开-必填)",
 "passiveDeviceNames": "string(被控设备的名称，多设备之间用逗号隔开-必填)"
 "action": "string(关联动作，0标识反向，1标识正向-必填)",
 * Created by Mr.C on 2019/4/1 0001.
 */
public class LinkControlConfigs {
    private String masterDeviceIds;
    private String masterDeviceNames;
    private String passiveDeviceIds;
    private String passiveDeviceNames;
    private String action;

    public String getMasterDeviceIds() {
        return masterDeviceIds;
    }

    public void setMasterDeviceIds(String masterDeviceIds) {
        this.masterDeviceIds = masterDeviceIds;
    }

    public String getMasterDeviceNames() {
        return masterDeviceNames;
    }

    public void setMasterDeviceNames(String masterDeviceNames) {
        this.masterDeviceNames = masterDeviceNames;
    }

    public String getPassiveDeviceIds() {
        return passiveDeviceIds;
    }

    public void setPassiveDeviceIds(String passiveDeviceIds) {
        this.passiveDeviceIds = passiveDeviceIds;
    }

    public String getPassiveDeviceNames() {
        return passiveDeviceNames;
    }

    public void setPassiveDeviceNames(String passiveDeviceNames) {
        this.passiveDeviceNames = passiveDeviceNames;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
