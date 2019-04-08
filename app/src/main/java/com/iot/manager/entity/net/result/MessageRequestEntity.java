package com.iot.manager.entity.net.result;

import java.io.Serializable;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/27 0027.
 *
 * {
 "deviceId":"bf85ccc0-4efc-4459-84fc-c5b3f830b35a",
 "readUser":"562750a9b0b847be99c51e9675e07d87",
 "isNewRecord":false,
 "status":0,
 "id":"0376830b-f44a-48ef-a64a-576237abfac0",
 "createTime":"2019-03-25 05:48",
 "subject":"设备通过定时任务打开",
 "content":"风扇通过定时任务被打开了",
 "type":"device_open"
 }
 */
public class MessageRequestEntity implements Serializable{

    private String deviceId;
    private String readUser;
    private boolean isNewRecord;
    private int status;
    private String id;
    private String createTime;
    private String subject;
    private String content;
    private String type;

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getReadUser() {
        return readUser;
    }

    public void setReadUser(String readUser) {
        this.readUser = readUser;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
