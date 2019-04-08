package com.iot.manager.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_ControlContract;
import com.iot.manager.contract.Fg_DeviceContract;
import com.iot.manager.presenter.FG_ControlPresenter;
import com.iot.manager.presenter.FG_DevicePresenter;
import com.iot.manager.view.adapter.MainPageAdapter;
import com.iot.manager.view.fragment.device.ControlManagerFragment;
import com.iot.manager.view.fragment.device.DeviceGroupFragment;
import com.iot.manager.view.fragment.device.DeviceManagerFragment;
import com.iot.manager.view.fragment.device.DkFragment;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;

/**
 * Function :设备
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class DeviceFragment extends BaseMVPFragment<Fg_DeviceContract.View,FG_DevicePresenter> implements Fg_DeviceContract.View{
    public static DeviceFragment newInstance() {
        Bundle args = new Bundle();
        DeviceFragment fragment = new DeviceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView toolbar_tv_title;

    private QMUITabSegment mTabSegment;
    private CurrentViewPager contentViewPager;
    @Override
    public int getLayoutId() {
        return R.layout.fg_device;
    }


    @Override
    public void initview(View v) {
        mTabSegment = v.findViewById(R.id.tabSegment);
        contentViewPager = v.findViewById(R.id.contentViewPager);

        toolbar_tv_title = v.findViewById(R.id.toolbar_tv_title);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_tv_title.setText("设备中心");
    }

    /**
     * 在此方法内为控件赋值 或 开启网络线程访问
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(DkFragment.newInstance());
        fragments.add(ControlManagerFragment.newInstance());
        fragments.add(DeviceManagerFragment.newInstance());
        fragments.add(DeviceGroupFragment.newInstance());
        MainPageAdapter pageAdapter = new MainPageAdapter(getChildFragmentManager(), fragments);
        contentViewPager.setAdapter(pageAdapter);
        initTab();
    }

    private void initTab() {
        QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab("场地管理");
        tab1.setTextColor(ContextCompat.getColor(getContext(),R.color.black),ContextCompat.getColor(getContext(),R.color.select_color));
        QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab("控制器管理");
        tab2.setTextColor(ContextCompat.getColor(getContext(),R.color.black),ContextCompat.getColor(getContext(),R.color.select_color));
        QMUITabSegment.Tab tab3 = new QMUITabSegment.Tab("设备管理");
        tab3.setTextColor(ContextCompat.getColor(getContext(),R.color.black),ContextCompat.getColor(getContext(),R.color.select_color));
        QMUITabSegment.Tab tab4 = new QMUITabSegment.Tab("设备分组");
        tab4.setTextColor(ContextCompat.getColor(getContext(),R.color.black),ContextCompat.getColor(getContext(),R.color.select_color));
        mTabSegment.addTab(tab1);
        mTabSegment.addTab(tab2);
        mTabSegment.addTab(tab3);
        mTabSegment.addTab(tab4);
        mTabSegment.setupWithViewPager(contentViewPager, false);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorPosition(false);
        mTabSegment.setIndicatorWidthAdjustContent(true);
        mTabSegment.notifyDataChanged();
    }
}
