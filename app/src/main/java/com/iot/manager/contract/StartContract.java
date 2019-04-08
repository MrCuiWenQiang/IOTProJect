package com.iot.manager.contract;


import com.iot.manager.model.StartModel;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class StartContract {
    public interface View {
      void toMain();
      void toLogin();
    }

    public interface Presenter {
        void next();
    }

    public interface Model {
        void isHaveUser(StartModel.OnFindUser onFindUser);
    }

}
