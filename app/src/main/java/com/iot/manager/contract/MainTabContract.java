package com.iot.manager.contract;


import android.support.v4.app.Fragment;

import com.iot.manager.entity.view.MainTableBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/19 0019.
 */
public class MainTabContract {
    public interface View{
        void settingTabs(List<MainTableBean> datas);
        void settingFragments(ArrayList<Fragment> fragments);
    }

    public interface Presenter{
        /**
         * 设置标签
         */
        void giveTable();

        void giveFragments();
    }

    public interface Model{

    }
}
