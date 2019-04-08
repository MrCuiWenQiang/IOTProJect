package com.iot.manager.entity.net.request;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class HandStatusRequestEntity {
    private String id;
    private String openState; //设备开启状态：0标识关闭，1标识开启;

    public HandStatusRequestEntity() {
    }

    public HandStatusRequestEntity(String id, String openState) {
        this.id = id;
        this.openState = openState;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOpenState() {
        return openState;
    }

    public void setOpenState(String openState) {
        this.openState = openState;
    }
}
