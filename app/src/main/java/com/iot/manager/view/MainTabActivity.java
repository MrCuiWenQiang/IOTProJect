package com.iot.manager.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;

import com.iot.manager.R;
import com.iot.manager.contract.MainTabContract;
import com.iot.manager.entity.view.MainTableBean;
import com.iot.manager.presenter.MainTabPresenter;
import com.iot.manager.view.adapter.MainPageAdapter;
import com.qmuiteam.qmui.util.QMUIResHelper;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPAcivity;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;
import cn.faker.repaymodel.widget.viewgroup.NoScrollViewPager;

/**
 * Function :列表布局
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class MainTabActivity extends BaseMVPAcivity<MainTabContract.View, MainTabPresenter> implements MainTabContract.View  {

    private CurrentViewPager mContentViewPager;
    private QMUITabSegment mTabSegment;

    public static final String POSITION_KEY = "POSITION";

    private int select_position = 0;

    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_maintab;
    }


    @Override
    protected void initContentView() {
        isShowToolView(false);

        mContentViewPager = findViewById(R.id.contentViewPager);
        mTabSegment = findViewById(R.id.tabs);

        int normalColor = QMUIResHelper.getAttrColor(this, R.attr.qmui_config_color_gray_6);
        mTabSegment.setDefaultNormalColor(ContextCompat.getColor(this,R.color.black));
        mTabSegment.setDefaultSelectedColor(ContextCompat.getColor(this,R.color.select_color));
        mTabSegment.setHasIndicator(true);
        mTabSegment.setIndicatorPosition(false);
        mTabSegment.setIndicatorWidthAdjustContent(true);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        select_position = getIntent().getIntExtra(POSITION_KEY,0);

        mPresenter.giveTable();
        mPresenter.giveFragments();
    }

    @Override
    public void settingTabs(List<MainTableBean> datas) {
        for (MainTableBean tab : datas) {
            mTabSegment.addTab(new QMUITabSegment.Tab(ContextCompat.getDrawable(this, tab.getIcon()), ContextCompat.getDrawable(this, tab.getSelectIcon()), tab.getName(), false));
        }

    }

    @Override
    public void settingFragments(ArrayList<Fragment> fragments) {
        MainPageAdapter pageAdapter = new MainPageAdapter(getSupportFragmentManager(), fragments);
        mContentViewPager.setAdapter(pageAdapter);
        mTabSegment.setupWithViewPager(mContentViewPager,false);
        mTabSegment.notifyDataChanged();
        if (select_position<fragments.size()){
            mContentViewPager.setCurrentItem(select_position);
        }
    }
}
