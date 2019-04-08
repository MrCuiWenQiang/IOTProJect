package com.iot.manager.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_ControlContract;
import com.iot.manager.presenter.FG_ControlPresenter;
import com.iot.manager.view.adapter.MainPageAdapter;
import com.qmuiteam.qmui.widget.QMUITabSegment;

import java.util.ArrayList;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;

/**
 * Function :控制
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class ControlFragment extends BaseMVPFragment<Fg_ControlContract.View, FG_ControlPresenter> implements Fg_ControlContract.View {

    public static ControlFragment newInstance() {
        Bundle args = new Bundle();
        ControlFragment fragment = new ControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private QMUITabSegment mTabSegment;
    private CurrentViewPager contentViewPager;
    private TextView toolbar_tv_title;

    @Override
    public int getLayoutId() {
        return R.layout.fg_control;
    }

    @Override
    public void initview(View v) {
        toolbar_tv_title = v.findViewById(R.id.toolbar_tv_title);
        toolbar_tv_title.setVisibility(View.VISIBLE);
        toolbar_tv_title.setText("控制中心");

        mTabSegment = v.findViewById(R.id.tabSegment);
        contentViewPager = v.findViewById(R.id.contentViewPager);
        mPresenter.giveFragments();

    }


    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public void settingFragments(ArrayList<Fragment> fragments) {
        MainPageAdapter pageAdapter = new MainPageAdapter(getChildFragmentManager(), fragments);
        contentViewPager.setAdapter(pageAdapter);
        initTab();

    }

    private void initTab() {
        QMUITabSegment.Tab tab1 = new QMUITabSegment.Tab("手动控制");
        tab1.setTextColor(ContextCompat.getColor(getContext(),R.color.black),ContextCompat.getColor(getContext(),R.color.select_color));
        QMUITabSegment.Tab tab2 = new QMUITabSegment.Tab("定时控制");
        tab2.setTextColor(ContextCompat.getColor(getContext(),R.color.black),ContextCompat.getColor(getContext(),R.color.select_color));
        QMUITabSegment.Tab tab3 = new QMUITabSegment.Tab("条件控制");
        tab3.setTextColor(ContextCompat.getColor(getContext(),R.color.black),ContextCompat.getColor(getContext(),R.color.select_color));
        QMUITabSegment.Tab tab4 = new QMUITabSegment.Tab("联动控制");
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
