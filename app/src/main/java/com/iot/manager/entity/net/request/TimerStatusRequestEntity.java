package com.iot.manager.entity.net.request;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/23 0023.
 */
public class TimerStatusRequestEntity {
    private String id;
    private String state ; //暂停状态：0标识未暂停，1标识暂停

    public TimerStatusRequestEntity() {
    }

    public TimerStatusRequestEntity(int status,String id) {
        this.id = id;
        this.state = String.valueOf(status);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
