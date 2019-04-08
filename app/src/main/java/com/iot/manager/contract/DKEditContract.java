package com.iot.manager.contract;


import com.iot.manager.entity.net.request.AddDkEntity;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class DKEditContract {
    public interface View{
        void update_success(String msg);
        void update_Fail(String data);

        void delete_success(String msg);
        void delete_fail(String msg);
    }

    public interface Presenter{
        void update(AddDkEntity data);
        void delete(String id);
    }


}
