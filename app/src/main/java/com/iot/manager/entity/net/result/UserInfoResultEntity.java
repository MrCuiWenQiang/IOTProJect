package com.iot.manager.entity.net.result;

import java.io.Serializable;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class UserInfoResultEntity implements Serializable{
    private String id;
    private boolean isnewrecord;
    private String remarks;
    private String createdate;
    private String updatedate;
    private String loginname;
    private String name;
    private String email;
    private String address;
    private String phone;
    private String mobile;
    private String usertype;
    private String loginip;
    private String logindate;
    private String loginflag;
    private String photo;
    private String oldloginip;
    private String oldlogindate;
    private String province;
    private String provincename;
    private String city;
    private String cityname;
    private String area;
    private String areaname;
    private String superflag;
    private Role role;
    private String qrcode;
    private boolean admin;
    private String rolenames;
    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public void setIsnewrecord(boolean isnewrecord) {
        this.isnewrecord = isnewrecord;
    }
    public boolean getIsnewrecord() {
        return isnewrecord;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    public String getRemarks() {
        return remarks;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }
    public String getCreatedate() {
        return createdate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }
    public String getUpdatedate() {
        return updatedate;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    public String getLoginname() {
        return loginname;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getPhone() {
        return phone;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getMobile() {
        return mobile;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }
    public String getUsertype() {
        return usertype;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }
    public String getLoginip() {
        return loginip;
    }

    public void setLogindate(String logindate) {
        this.logindate = logindate;
    }
    public String getLogindate() {
        return logindate;
    }

    public void setLoginflag(String loginflag) {
        this.loginflag = loginflag;
    }
    public String getLoginflag() {
        return loginflag;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public String getPhoto() {
        return photo;
    }

    public void setOldloginip(String oldloginip) {
        this.oldloginip = oldloginip;
    }
    public String getOldloginip() {
        return oldloginip;
    }

    public void setOldlogindate(String oldlogindate) {
        this.oldlogindate = oldlogindate;
    }
    public String getOldlogindate() {
        return oldlogindate;
    }

    public void setProvince(String province) {
        this.province = province;
    }
    public String getProvince() {
        return province;
    }

    public void setProvincename(String provincename) {
        this.provincename = provincename;
    }
    public String getProvincename() {
        return provincename;
    }

    public void setCity(String city) {
        this.city = city;
    }
    public String getCity() {
        return city;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }
    public String getCityname() {
        return cityname;
    }

    public void setArea(String area) {
        this.area = area;
    }
    public String getArea() {
        return area;
    }

    public void setAreaname(String areaname) {
        this.areaname = areaname;
    }
    public String getAreaname() {
        return areaname;
    }

    public void setSuperflag(String superflag) {
        this.superflag = superflag;
    }
    public String getSuperflag() {
        return superflag;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    public Role getRole() {
        return role;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }
    public String getQrcode() {
        return qrcode;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    public boolean getAdmin() {
        return admin;
    }

    public void setRolenames(String rolenames) {
        this.rolenames = rolenames;
    }
    public String getRolenames() {
        return rolenames;
    }

    public class Role implements Serializable{

        private String id;
        private boolean isnewrecord;
        private String remarks;
        private String name;
        private String enname;
        private String roletype;
        private String menuids;
        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setIsnewrecord(boolean isnewrecord) {
            this.isnewrecord = isnewrecord;
        }
        public boolean getIsnewrecord() {
            return isnewrecord;
        }

        public void setRemarks(String remarks) {
            this.remarks = remarks;
        }
        public String getRemarks() {
            return remarks;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setEnname(String enname) {
            this.enname = enname;
        }
        public String getEnname() {
            return enname;
        }

        public void setRoletype(String roletype) {
            this.roletype = roletype;
        }
        public String getRoletype() {
            return roletype;
        }

        public void setMenuids(String menuids) {
            this.menuids = menuids;
        }
        public String getMenuids() {
            return menuids;
        }

    }
}
