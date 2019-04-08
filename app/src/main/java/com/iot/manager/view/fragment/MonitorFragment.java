package com.iot.manager.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_ControlContract;
import com.iot.manager.contract.Fg_MonitorContract;
import com.iot.manager.entity.net.result.DksResultEntity;
import com.iot.manager.presenter.FG_ControlPresenter;
import com.iot.manager.presenter.FG_MonitorPresenter;
import com.iot.manager.view.adapter.MainPageAdapter;
import com.iot.manager.view.fragment.monitor.TemperatureMonitorFragment;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;
import cn.faker.repaymodel.widget.viewgroup.CurrentViewPager;
import cn.qqtheme.framework.picker.SinglePicker;

/**
 * Function :监测
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class MonitorFragment extends BaseMVPFragment<Fg_MonitorContract.View, FG_MonitorPresenter> implements Fg_MonitorContract.View, View.OnClickListener {
    public static MonitorFragment newInstance() {
        Bundle args = new Bundle();
        MonitorFragment fragment = new MonitorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private CurrentViewPager mContentViewPager;
    private TextView tv_dk;

    private TextView tv_c;
    private TextView tv_v;

    private TextView[] tvs;

    private int mCurrentPosition = 0;

    private List<DksResultEntity> datas =null;
    private List<String> names =null;
    private HashMap<String,String> idmap =null;
    ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fg_monitor;
    }

    /**
     * 在此方法内初始化控件
     *
     * @param v
     */
    @Override
    public void initview(View v) {
        mContentViewPager = v.findViewById(R.id.contentViewPager);
        tv_dk = v.findViewById(R.id.tv_dk);
        tv_c = v.findViewById(R.id.tv_c);
        tv_v = v.findViewById(R.id.tv_v);
        tvs = new TextView[]{tv_c, tv_v};
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        fragments.add(TemperatureMonitorFragment.newInstance());
        fragments.add(TemperatureMonitorFragment.newInstance());
        MainPageAdapter pageAdapter = new MainPageAdapter(getChildFragmentManager(), fragments);
        mContentViewPager.setAdapter(pageAdapter);
        tv_c.setSelected(true);

        mPresenter.getDkList();
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_dk.setOnClickListener(this);
        tv_c.setOnClickListener(this);
        tv_v.setOnClickListener(this);

        mContentViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                mCurrentPosition = i;
                if (i==0){
                    setSelectStatus(tv_c,-1);
                }else{
                    setSelectStatus(tv_v,-1);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_dk: {
                showDk();
                break;
            }
            case R.id.tv_c: {
                setSelectStatus(tv_c,0);
                mContentViewPager.setCurrentItem(0);
                break;
            }
            case R.id.tv_v: {
                setSelectStatus(tv_v,1);
                break;
            }
        }
    }

    public void setSelectStatus(TextView tv,int current){
        for (TextView v : tvs){
            if (v.equals(tv)){
                if (!v.isSelected()){
                    v.setSelected(true);
                    if (current!=-1){
                        mContentViewPager.setCurrentItem(current);
                    }
                }
            }else {
                v.setSelected(false);
            }
        }
    }

    @Override
    public void getList_success(List<DksResultEntity> datas) {
        this.datas = datas;
        if (datas!=null){
            names = new ArrayList<>();
            idmap = new HashMap<>();
            for (DksResultEntity e:datas ) {
                if (!TextUtils.isEmpty(e.getName())){
                    names.add(e.getName());
                    idmap.put(e.getName(),e.getId());
                }
            }
        }
    }

    @Override
    public void getList_fail(String msg) {
        showDialog(msg);
    }

    private void showDk(){
        if (datas==null||datas.size()<0){
            ToastUtility.showToast("地块列表未加载完成");
            return;
        }
        SinglePicker<String> picker = new SinglePicker<String>(getActivity(), names);
        picker.setCanceledOnTouchOutside(false);
        picker.setSelectedIndex(1);
        picker.setCycleDisable(false);
        picker.setOnItemPickListener(new SinglePicker.OnItemPickListener<String>() {
            @Override
            public void onItemPicked(int index, String item) {
                Fragment fragment = fragments.get(mCurrentPosition);
                if (fragment instanceof TemperatureMonitorFragment){
                    TemperatureMonitorFragment tf = (TemperatureMonitorFragment) fragment;
                    tf.loadData(idmap.get(item),true);
                    tv_dk.setText(item);
                    tv_dk.setTextColor(ContextCompat.getColor(getContext(),R.color.text_title_color));
                }
            }
        });
        picker.show();
    }

}
