package com.iot.manager.entity.net.result.hand;

import java.io.Serializable;

/**
 * Auto-generated: 2019-03-23 12:59:44
 *
 * @author www.jsons.cn 
 * @website http://www.jsons.cn/json2java/ 
 */
public class Deviceslist implements Serializable{

    private String id;
    private boolean isNewRecord;
    private String name;
    private String code;
    private String sort;
    private String openState;
    private String type;
    private String conditionSetState;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOpenState() {
        return openState;
    }

    public void setOpenState(String openState) {
        this.openState = openState;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getConditionSetState() {
        return conditionSetState;
    }

    public void setConditionSetState(String conditionSetState) {
        this.conditionSetState = conditionSetState;
    }
}