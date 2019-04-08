package com.iot.manager.contract;


import com.iot.manager.entity.db.UserDBEntity;
import com.iot.manager.entity.net.request.LoginRequestEntity;
import com.iot.manager.entity.net.result.heweather.Heweather5;
import com.iot.manager.entity.view.MainTableBean;

import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/19 0019.
 */
public class MainContract {
    public interface View{
        void settingTable(List<MainTableBean> datas);
        void settingWeatherData(Heweather5 data);
        void giveWeatherData_fail(String msg);
    }

    public interface Presenter{
        /**
         * 获取天气数据
         */
        void giveWeatherData(String city);
        /**
         * 设置标签
         */
        void giveTable();

    }

    public interface Model{

    }
}
