package com.iot.manager.contract;

import com.iot.manager.entity.net.result.TimerListResultEntity;

import java.util.ArrayList;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_TimeControlContract {
    public interface View{
        void give_Success(ArrayList<TimerListResultEntity> datas);
        void give_Fail(String msg);

        void setStatus_Success(int position, boolean b);
        void setStatus_Fail(int position, boolean b);
    }

    public interface Presenter{
        void giveDatas(int page);
        void setStatus(int position, boolean b,String id);
    }


}
