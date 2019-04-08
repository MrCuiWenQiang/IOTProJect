package com.iot.manager.contract;

import com.iot.manager.entity.net.result.NexusResultEntity;

import java.util.ArrayList;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_NexusControlContract {
    public interface View{
        void list_Success(ArrayList<NexusResultEntity> datas );
        void list_Fail(String msg);
    }

    public interface Presenter{
        void list(int page);
    }


}
