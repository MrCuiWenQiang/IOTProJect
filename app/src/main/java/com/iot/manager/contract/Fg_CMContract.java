package com.iot.manager.contract;

import com.iot.manager.entity.net.result.ControlEntity;
import com.iot.manager.entity.net.result.DksResultEntity;
import com.iot.manager.entity.net.result.hand.HList;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_CMContract {
    public interface View{
        void getList_fail(String msg);
        void getList_Success(List<ControlEntity> datas);
    }

    public interface Presenter{
        void getList(int page);
    }


}
