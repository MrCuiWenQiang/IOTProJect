package com.iot.manager.entity.net.request;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class SubDevices{
    private String name;
    private String code;
    private String type;
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}