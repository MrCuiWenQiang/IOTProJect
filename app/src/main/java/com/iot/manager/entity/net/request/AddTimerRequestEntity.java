package com.iot.manager.entity.net.request;

import android.text.TextUtils;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/26 0026.
 */
public class AddTimerRequestEntity {
    private String id;//任务主键-添加逻辑的时候不需传入，修改逻辑的时候需要传入
    private String name;
    private String deviceIds; //本任务所牵扯到的设备主键，多设备之间用逗号隔开-必填
    private String deviceNames;//本任务所牵扯到的设备名称，多设备之间用逗号隔开-必填
    private String startDate;//开始日期-必填
    private String endDate;
    private String remark;
    private String weeks;
    private List<TimeEntity> taskDetailList;

    public String getWeeks() {
        return weeks;
    }

    public void setWeeks(String weeks) {
        this.weeks = weeks;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<TimeEntity> getTaskDetailList() {
        return taskDetailList;
    }

    public void setTaskDetailList(List<TimeEntity> taskDetailList) {
        this.taskDetailList = taskDetailList;
    }

    public boolean isNull(){
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(deviceIds)
                ||TextUtils.isEmpty(endDate)
                ||TextUtils.isEmpty(deviceNames)
                ||TextUtils.isEmpty(startDate)||taskDetailList==null||taskDetailList.size()!=2){
            return true;
        }
        return false;
    }

    public static class TimeEntity{
        /**
         * "execTime": "string(执行时间-必填)",
         "execAction": "string(执行动作<00标识开启，01标识关闭>）-必填)"
         */
        private String execTime;
        private String execAction;

        public String getExecTime() {
            return execTime;
        }

        public void setExecTime(String execTime) {
            this.execTime = execTime;
        }

        public String getExecAction() {
            return execAction;
        }

        public void setExecAction(String execAction) {
            this.execAction = execAction;
        }
    }

}
