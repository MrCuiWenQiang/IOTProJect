package com.iot.manager.entity.net.result;

import com.iot.manager.entity.net.result.hand.Deviceslist;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/4/1 0001.
 */
public class AddDeviceEntity {
    private String id;
    private String name;
    private String sort;
    private List<Deviceslist> devicesList;

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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public List<Deviceslist> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Deviceslist> devicesList) {
        this.devicesList = devicesList;
    }
}
