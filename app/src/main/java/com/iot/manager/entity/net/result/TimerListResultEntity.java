package com.iot.manager.entity.net.result;

import java.util.Date;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/24 0024.
 */
public class TimerListResultEntity {

    private String id;
    private boolean isNewRecord;
    private String name;
    private String deviceIds;
    private String deviceNames;
    private Date startDate;
    private Date endDate;
    private String startTime;
    private String endTime;
    private String weeks;
    private int intervalTime;
    private int state;
    private String createTime;
    private String updateTime;
    private String remark;
    private int runState;
    private String stateText;

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

    public String getDeviceIds() {
        return deviceIds;
    }

    public void setDeviceIds(String deviceIds) {
        this.deviceIds = deviceIds;
    }

    public String getDeviceNames() {
        return deviceNames;
    }

    public void setDeviceNames(String deviceNames) {
        this.deviceNames = deviceNames;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getRunState() {
        return runState;
    }

    public void setRunState(int runState) {
        this.runState = runState;
    }

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }
}
