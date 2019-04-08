package com.iot.manager.contract;



/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/19 0019.
 */
public class EditPasswordContract {
    public interface View{
        void settingSuccess(String msg);
        void settingFail(String msg);
    }

    public interface Presenter{
        void srttingPassword(String oldPassword,String newPassword,String newPassword_k);
    }


}
