package com.iot.manager.contract;

import com.iot.manager.entity.net.result.UserInfoResultEntity;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_UserContract {
    public interface View{
        void getUserInfo_fail(String msg);
        void getUserInfo_Success(UserInfoResultEntity userInfo);

        void unbild_success();
        void unbild_fail(String msg);
    }

    public interface Presenter{
        void getUserInfo();
        void unbild();
    }


}
