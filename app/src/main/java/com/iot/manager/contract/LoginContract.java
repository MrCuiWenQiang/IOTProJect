package com.iot.manager.contract;


import com.iot.manager.entity.db.UserDBEntity;
import com.iot.manager.entity.net.request.LoginRequestEntity;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/19 0019.
 */
public class LoginContract {
    public interface View{
        void login_Success();
        void login_Fail(String msg);
        void settingUser(String name, String password);
    }

    public interface Presenter{
        /**
         *
         * @param name
         * @param passWord
         * @param isAnonymous 是否匿名登录
         */
        void login(String name, String passWord,boolean isAnonymous);

        void giveUserInfo();
    }

    public interface Model{
        void saveUser(String userId,String token, LoginRequestEntity data);

        void findUserByLimit( final OnFindUser onFindUser);

        void cleanAll();

        String findTeleByToken();

         interface OnFindUser {
            void onSuccess(UserDBEntity data);
            void onfail();
        }
    }

}
