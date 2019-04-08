package com.iot.manager.entity.net.result;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 *  "id": "6091c58c55864076a7ba8a3c0d7cb434",
 "isNewRecord": false,
 "createDate": "2018-08-03 19:33:37",
 "updateDate": "2019-03-20 15:01:45",
 "name": "东方云谷体验账号",
 "token": "8b8da5e2-77ec-42ee-9e74-cf7c227f0830",
 "wxOpenId": "oQ-mo5WAKpepWzYqgYra4QNRRjA4",
 "wxSessionKey": "X3TrKFdXVuDK1pyYnUzObg==",
 "appId": "50cd8c37eeb74b6eb9dc8bb6d9b22353",
 "state": "1",
 "userId": "c831216172454e1aa6d3e16a67cf74c1",
 "tokenGenerateTime": 1553065305616
 */
public class LoginResultEntity {
    private String userId;
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
