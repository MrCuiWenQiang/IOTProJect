package com.iot.manager.contract;


import android.support.v4.app.Fragment;

import com.iot.manager.entity.net.request.SaveTernEntity;
import com.iot.manager.entity.net.result.settingtern.TernEntity;
import com.iot.manager.entity.view.MainTableBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/19 0019.
 */
public class SettingTernContract {
    public interface View{
        void getInfo_Success(TernEntity data);
        void getInfo_fail(String msg);

        void save_success(String msg);
        void savefail(String msg);

        void delete(String msg);
        void deletefail(String msg);
    }

    public interface Presenter{
        void getInfo(String id);
        void save(SaveTernEntity data);
        void delete(String  id);
    }

    public interface Model{

    }
}
