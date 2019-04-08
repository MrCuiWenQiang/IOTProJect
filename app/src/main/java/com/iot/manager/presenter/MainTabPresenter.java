package com.iot.manager.presenter;


import android.support.v4.app.Fragment;

import com.iot.manager.R;
import com.iot.manager.contract.MainTabContract;
import com.iot.manager.entity.view.MainTableBean;
import com.iot.manager.view.fragment.ControlFragment;
import com.iot.manager.view.fragment.DeviceFragment;
import com.iot.manager.view.fragment.MessageFragment;
import com.iot.manager.view.fragment.MonitorFragment;
import com.iot.manager.view.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;



/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/20 0020.
 */
public class MainTabPresenter extends BaseMVPPresenter<MainTabContract.View> implements MainTabContract.Presenter {

    /**
     * 设置标签
     */
    @Override
    public void giveTable() {
        List<MainTableBean> datas = new ArrayList<>();
        datas.add(new MainTableBean( R.mipmap.bottom_device_gray,R.mipmap.bottom_device_green,"设备"));
        datas.add(new MainTableBean( R.mipmap.bottom_control_gray,R.mipmap.bottom_control_green,"控制"));
        datas.add(new MainTableBean( R.mipmap.bottom_monitor_gray,R.mipmap.bottom_monitor_green,"监测"));
        datas.add(new MainTableBean( R.mipmap.bottom_msg_gray,R.mipmap.bottom_msg_green,"消息"));
        datas.add(new MainTableBean( R.mipmap.bottom_user_gray,R.mipmap.bottom_user_green,"我的"));
        getView().settingTabs(datas);
    }

    @Override
    public void giveFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(DeviceFragment.newInstance());
        fragments.add(ControlFragment.newInstance());
        fragments.add(MonitorFragment.newInstance());
        fragments.add(MessageFragment.newInstance());
        fragments.add(UserFragment.newInstance());
        getView().settingFragments(fragments);
    }
}
