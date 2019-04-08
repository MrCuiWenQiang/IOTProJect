package com.iot.manager.contract;

import com.iot.manager.entity.net.result.DeviceGroupResultEntitr;

import java.util.List;

/**
 * Function : 设备分组
 * Remarks  :
 * Created by Mr.C on 2019/3/29 0029.
 */
public class FG_DeviceGroupContrat {
    public interface View{
        void getList_fail(String msg);
        void getList_Success( List<DeviceGroupResultEntitr> datas );
    }

    public interface Presenter{
        void getList(int page);
    }
}
