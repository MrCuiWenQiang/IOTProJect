package com.iot.manager.entity.net.result;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * "id": "string(联动主键-添加逻辑的时候不需传入，修改逻辑的时候需要传入)",
 "name": "string(场景名称-必填)",
 "masterDeviceNames": "string(主控设备名称，用作冗余-必填)",
 "passiveDeviceNames": "string(被控设备名称，用作冗余-必填)",
 "actionState": "string(关联动作，0标识反向，1标识正向)",
 "remark": "string（备注）",
 * Created by Mr.C on 2019/4/1 0001.
 */
public class NexusEditResultEntity {
    private String id;
    private String name;
    private String masterDeviceNames;
    private String passiveDeviceNames;
    private String actionState;
    private String remark;
    private List<LinkControlConfigs> linkControlConfigs;

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

    public String getMasterDeviceNames() {
        return masterDeviceNames;
    }

    public void setMasterDeviceNames(String masterDeviceNames) {
        this.masterDeviceNames = masterDeviceNames;
    }

    public String getPassiveDeviceNames() {
        return passiveDeviceNames;
    }

    public void setPassiveDeviceNames(String passiveDeviceNames) {
        this.passiveDeviceNames = passiveDeviceNames;
    }

    public String getActionState() {
        return actionState;
    }

    public void setActionState(String actionState) {
        this.actionState = actionState;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<LinkControlConfigs> getLinkControlConfigs() {
        return linkControlConfigs;
    }

    public void setLinkControlConfigs(List<LinkControlConfigs> linkControlConfigs) {
        this.linkControlConfigs = linkControlConfigs;
    }
}
