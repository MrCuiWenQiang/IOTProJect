package com.iot.manager.entity.net.request;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class LoginRequestEntity {
    private String name;
    private String password;
    /**
     *
     这个传递：anonymous
     标识是匿名登录
     不传的话，标识账号、密码登录
     */
    private String loginType;
    private String wxOpenId ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }
}
