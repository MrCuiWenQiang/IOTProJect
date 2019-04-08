package com.iot.manager.entity.net.request;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/4/1 0001.
 */
public class SaveTernEntity {
    private String deviceId;
    private String sensorId;
    private String branchJudgmentOpen;//开始设置：判断条件，1标识大于，2标识小于
    private String branchJudgmentClose;//关闭设置：判断条件，1标识大于，2标识小于
    private String sensorValueOpen;//开启设置：条件值
    private String sensorValueClose;//开启设置：条件值
    private String openStateOpen;//开启设置：动作，0标识关闭，1标识开启
    private String openStateClose;//关闭设置：动作，0标识关闭，1标识开启

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getBranchJudgmentOpen() {
        return branchJudgmentOpen;
    }

    public void setBranchJudgmentOpen(String branchJudgmentOpen) {
        this.branchJudgmentOpen = branchJudgmentOpen;
    }

    public String getBranchJudgmentClose() {
        return branchJudgmentClose;
    }

    public void setBranchJudgmentClose(String branchJudgmentClose) {
        this.branchJudgmentClose = branchJudgmentClose;
    }

    public String getSensorValueOpen() {
        return sensorValueOpen;
    }

    public void setSensorValueOpen(String sensorValueOpen) {
        this.sensorValueOpen = sensorValueOpen;
    }

    public String getSensorValueClose() {
        return sensorValueClose;
    }

    public void setSensorValueClose(String sensorValueClose) {
        this.sensorValueClose = sensorValueClose;
    }

    public String getOpenStateOpen() {
        return openStateOpen;
    }

    public void setOpenStateOpen(String openStateOpen) {
        this.openStateOpen = openStateOpen;
    }

    public String getOpenStateClose() {
        return openStateClose;
    }

    public void setOpenStateClose(String openStateClose) {
        this.openStateClose = openStateClose;
    }
}
