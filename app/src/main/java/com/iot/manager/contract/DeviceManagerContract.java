package com.iot.manager.contract;

import com.iot.manager.entity.net.result.hand.HList;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class DeviceManagerContract {
    public interface View {
        void getRelayData(List<HList> lists, String type);
        void fail(String msg);

        void settingStatus_Success(int position,int childposition,String status,String msg);
        void settingStatus_Fail(int position,String msg);

        void removeName_success(String msg);
        void removeName_fail(String msg);
    }

    public interface Presenter {
        /*
         * @param page
         * @param type 查询类型，0标识私有设备，1标识他人设备
         */
        void getRelayData(int page, String type);

        void settingStatus(int position,int childposition,String id,boolean value);
        void removeName(String id,String name);
    }


}
