package com.iot.manager.contract;

import com.iot.manager.entity.net.result.TemperatureResultEntity;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_TemperatureMonitor {
    public interface View{
        void getList_Success( List<TemperatureResultEntity> datas);
        void getList_Fail(String msg);

    }

    public interface Presenter{
        void getList(String id);
    }


}
