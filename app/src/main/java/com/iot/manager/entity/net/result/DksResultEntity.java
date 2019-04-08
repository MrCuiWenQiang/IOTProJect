package com.iot.manager.entity.net.result;

import java.io.Serializable;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/28 0028.
 */
public class DksResultEntity implements Serializable{

    private String stateText;
    private String createDate;
    private boolean isNewRecord;
    private String updateDate;
    private String typeKey;
    private String area;
    private String id;
    private String remark;
    private String name;
    private String state;
    private User user;
    private String typeText;

    public String getStateText() {
        return stateText;
    }

    public void setStateText(String stateText) {
        this.stateText = stateText;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public boolean isNewRecord() {
        return isNewRecord;
    }

    public void setNewRecord(boolean newRecord) {
        isNewRecord = newRecord;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTypeText() {
        return typeText;
    }

    public void setTypeText(String typeText) {
        this.typeText = typeText;
    }

    public class User implements Serializable{

        private boolean isNewRecord;
        private String id;
        private String name;
        private String loginFlag;
        private String roleNames;
        private boolean admin;

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

        public String getLoginFlag() {
            return loginFlag;
        }

        public void setLoginFlag(String loginFlag) {
            this.loginFlag = loginFlag;
        }

        public String getRoleNames() {
            return roleNames;
        }

        public void setRoleNames(String roleNames) {
            this.roleNames = roleNames;
        }

        public boolean isAdmin() {
            return admin;
        }

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }
    }
    @Override
    public String toString() {
        return name;
    }
}
