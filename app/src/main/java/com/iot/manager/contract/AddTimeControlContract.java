package com.iot.manager.contract;


import com.iot.manager.entity.net.request.AddTimerRequestEntity;
import com.iot.manager.entity.net.result.TimerListResultEntity;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/19 0019.
 */
public class AddTimeControlContract {
    public interface View{
        void add_Successs(String msg);
        void add_Fail(String msg);

        void info(TimerListResultEntity data);
        void  info_fail(String msg);
    }

    public interface Presenter{

        void add(AddTimerRequestEntity entity);
        void info(String id);
    }

    public interface Model{

    }
}
