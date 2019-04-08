package com.iot.manager.entity.db;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * Function :用户表
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class UserDBEntity extends LitePalSupport {
    public UserDBEntity() {
    }


    public UserDBEntity(String token, Date logTimer) {
        this.token = token;
        this.logTimer = logTimer;
    }

    @Column(unique = true, nullable = false)
    private String name;
    @Column(unique = true, nullable = false)
    private String userId;

    private String password;

    private String token;

    private Date logTimer;
    private boolean isanonymous;//是否是匿名登录

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLogTimer() {
        return logTimer;
    }

    public void setLogTimer(Date logTimer) {
        this.logTimer = logTimer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isIsanonymous() {
        return isanonymous;
    }

    public void setIsanonymous(boolean isanonymous) {
        this.isanonymous = isanonymous;
    }
}
