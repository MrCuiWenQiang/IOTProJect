package com.iot.manager.contract;

import com.iot.manager.entity.net.result.DksResultEntity;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class Fg_DKContract {
    public interface View{
        void getList( List<DksResultEntity> datas);
        void getList_fail(String msg);
    }

    public interface Presenter{
        void getList(int page);
    }


}
