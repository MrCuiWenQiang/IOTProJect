package com.iot.manager.contract;

import com.iot.manager.entity.net.request.CMRequestEntity;
import com.iot.manager.entity.net.result.DksResultEntity;
import com.iot.manager.entity.net.result.content.ContentInfoEntity;
import com.iot.manager.entity.net.result.content.Device;
import com.iot.manager.entity.net.result.hand.HList;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class ContentManagerContract {
    public interface View{
        void getList_success( List<DksResultEntity> datas);
        void getList_fail(String msg);

        void info_success(ContentInfoEntity entity);
        void info_fail(String msg);

        void initSuccess( List<Device> ds);
        void init_fail( String msg);

        void saveSuccess( String msg);
        void savefail( String msg);
    }

    public interface Presenter{
        void  init(String code);
        void  info(String ID);
        void  save(CMRequestEntity data);
    }


}
