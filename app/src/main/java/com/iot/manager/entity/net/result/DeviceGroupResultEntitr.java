package com.iot.manager.entity.net.result;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/29 0029.
 */
public class DeviceGroupResultEntitr {
    private boolean isNewRecord;
    private List<Deviceslist> devicesList;
    private String id;
    private String name;
    private String sort;

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public List<Deviceslist> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Deviceslist> devicesList) {
        this.devicesList = devicesList;
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

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public class Deviceslist {

        private String openState;
        private boolean isNewRecord;
        private String id;
        private String name;
        private String type;
        private String code;

        public String getOpenState() {
            return openState;
        }

        public void setOpenState(String openState) {
            this.openState = openState;
        }

        public boolean isNewRecord() {
            return isNewRecord;
        }

        public void setNewRecord(boolean newRecord) {
            isNewRecord = newRecord;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
