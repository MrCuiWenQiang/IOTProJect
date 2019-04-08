package com.iot.manager.contract;

import com.iot.manager.entity.net.result.hand.HList;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class DeviceGroupListContract {
    public interface View{
        void getRelayData(List<HList> lists);
        void fail(String msg);

    }

    public interface Presenter{

        void getRelayData(int page);
    }


}
