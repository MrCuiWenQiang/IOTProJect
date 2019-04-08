package com.iot.manager.contract;

import com.iot.manager.entity.net.result.DksResultEntity;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_MonitorContract {
    public interface View{
        void getList_success( List<DksResultEntity> datas);
        void getList_fail(String msg);
    }

    public interface Presenter{
        void getDkList();
    }


}
