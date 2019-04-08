package com.iot.manager.entity.net.request;

import android.text.TextUtils;

import java.util.List;

/**{
 "name": "string(控制器名称-必填)",
 "placeId": "string(所属场地主键-必填)",
 "deviceCode": "string(控制器编号-必填)",
 "remark": "string",
 "subDevices": [
 {
 "name": "string(设备名称-必填)",
 "code": "string(设备编号-必填)",
 "type": "string(设备类型:0标识为传感器，1标识为寄存器，必填项)",
 "remark": “string”
 }
 ]
 }
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class CMRequestEntity {
    private String id;
    private String placeId;
    private String deviceCode;
    private String remark;
    private String name;
    private List<SubDevices> subDevices;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<SubDevices> getSubDevices() {
        return subDevices;
    }

    public void setSubDevices(List<SubDevices> subDevices) {
        this.subDevices = subDevices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNull(){
        return TextUtils.isEmpty(name)||TextUtils.isEmpty(placeId)||TextUtils.isEmpty(deviceCode)||subDevices==null||subDevices.size()==0;
    }

}
