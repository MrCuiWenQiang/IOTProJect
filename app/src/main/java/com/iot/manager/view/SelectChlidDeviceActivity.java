package com.iot.manager.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.iot.manager.R;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.view.adapter.MainPageAdapter;
import com.iot.manager.view.fragment.DeviceGroupListFragment;
import com.iot.manager.view.fragment.DeviceListFragment;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.io.Serializable;
import java.util.ArrayList;

import cn.faker.repaymodel.activity.BaseToolBarActivity;

/**
 * Function :选择子设备
 * Remarks  :
 * Created by Mr.C on 2019/3/26 0026.
 */
public class SelectChlidDeviceActivity extends BaseToolBarActivity {

    private QMUITabSegment mTabSegment;
    private ViewPager  contentViewPager;

    public static final int DATACODE= 520;
    public static final String DATAKEY= "GG";
    public static final String AITEM= "AITEM";

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_selectchild;
    }

    @Override
    protected void initContentView() {
        mTabSegment = findViewById(R.id.tabSegment);
        contentViewPager = findViewById(R.id.contentViewPager);

        isShowCut(false);
        setLeftTitle("选择子设备");
        setToolBarBackgroundColor(R.color.white);
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        Intent intent = getIntent();
        Serializable serializable = intent.getSerializableExtra(DATAKEY);
        ArrayList<Deviceslist> clids = null;
        Deviceslist item = null;
        if (serializable!=null){
            if (serializable instanceof ArrayList){
                clids = (ArrayList<Deviceslist>) serializable;
                initFragment(clids);
            }else {
                item = (Deviceslist) serializable;
                initFragmentc(item);
            }
        }
        initFragment(null);
        initTab();
    }

    private void initFragment(ArrayList<Deviceslist> childs) {
        boolean aitem = getIntent().getBooleanExtra(AITEM,false);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(DeviceListFragment.newInstance(childs).setaItem(aitem));
        fragments.add(DeviceGroupListFragment.newInstance(childs).setaItem(aitem));
        MainPageAdapter pageAdapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        contentViewPager.setAdapter(pageAdapter);
    }
    private void initFragmentc(Deviceslist childs) {
        boolean aitem = getIntent().getBooleanExtra(AITEM,false);
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(DeviceListFragment.newInstance(childs).setaItem(aitem));
        fragments.add(DeviceListFragment.newInstance(childs).setaItem(aitem));
        MainPageAdapter pageAdapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        contentViewPager.setAdapter(pageAdapter);
    }

    private void initTab() {
        QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab("控制器");
        tab1.setTextColor(ContextCompat.getColor(this,R.color.black),ContextCompat.getColor(this,R.color.select_color));
        QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab("设备组");
        tab2.setTextColor(ContextCompat.getColor(this,R.color.black),ContextCompat.getColor(this,R.color.select_color));

        mTabSegment.addTab(tab1);
        mTabSegment.addTab(tab2);
        mTabSegment.setupWithViewPager(contentViewPager, false);
        mTabSegment.setMode(QMUITabSegment.MODE_FIXED);
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorPosition(false);
        mTabSegment.setIndicatorWidthAdjustContent(true);
        mTabSegment.notifyDataChanged();
    }

}
