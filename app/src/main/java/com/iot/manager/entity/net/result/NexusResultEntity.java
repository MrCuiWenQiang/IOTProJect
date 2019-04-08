package com.iot.manager.entity.net.result;

import java.util.Date;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/27 0027.
 *
 * "actionState":"1",
 "createDate":"2018-08-13 22:07:19",
 "passiveDeviceNames":"风扇（3号大棚）,照明灯（3号大棚）",
 "isNewRecord":false,
 "updateDate":"2018-08-13 22:07:19",
 "id":"dface08b-5998-419e-8692-d11e09d26019",
 "remark":"",
 "name":"test002",
 "state":"1",
 "masterDeviceNames":"电扇（2号棚）,照明灯（2号棚）"
 */
public class NexusResultEntity {
    private String id;
    private String name;
    private String actionState;//1标识正向动作，0标识反向动作
    private Date createDate;
    private String  passiveDeviceNames;
    private boolean  isNewRecord;
    private Date updateDate;
    private String remark;
    private String state;
    private String masterDeviceNames;

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

    public String getActionState() {
        return actionState;
    }

    public void setActionState(String actionState) {
        this.actionState = actionState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPassiveDeviceNames() {
        return passiveDeviceNames;
    }

    public void setPassiveDeviceNames(String passiveDeviceNames) {
        this.passiveDeviceNames = passiveDeviceNames;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMasterDeviceNames() {
        return masterDeviceNames;
    }

    public void setMasterDeviceNames(String masterDeviceNames) {
        this.masterDeviceNames = masterDeviceNames;
    }
}
