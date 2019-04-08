package com.iot.manager.entity.net.request;

import android.text.TextUtils;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/30 0030.
 */
public class AddDkEntity {
    private String id ;
    private String name;
    private String typeKey;
    private String area;
    private String remark;

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

    public String getTypeKey() {
        return typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isNull(){
        return TextUtils.isEmpty(name)||TextUtils.isEmpty(typeKey)||TextUtils.isEmpty(area);
    }

}
