package com.iot.manager.view.fragment.monitor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_TemperatureMonitor;
import com.iot.manager.entity.net.result.TemperatureResultEntity;
import com.iot.manager.presenter.FG_TemperatureMonitorPressenter;
import com.iot.manager.view.ControllerSensorDataActivity;
import com.iot.manager.view.adapter.TemperatureAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;

/**
 * Function : 传感器列表
 * Remarks  :
 * Created by Mr.C on 2019/3/28 0028.
 */
public class TemperatureMonitorFragment extends BaseMVPFragment<Fg_TemperatureMonitor.View,FG_TemperatureMonitorPressenter> implements Fg_TemperatureMonitor.View{

    public static TemperatureMonitorFragment newInstance() {
        Bundle args = new Bundle();
        TemperatureMonitorFragment fragment = new TemperatureMonitorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private String id = null;
    private TemperatureAdapter adapter = new TemperatureAdapter();

    @Override
    public int getLayoutId() {
        return R.layout.fg_temperaturemonitor;
    }


    @Override
    public void initview(View v) {
        rv_hands = v.findViewById(R.id.rv_hands);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);

        rv_hands.setLayoutManager(new GridLayoutManager(getContext(),2));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(0).setDivider(0.5F, ContextCompat.getColor(getContext(),R.color.color_light),GridLayoutManager.VERTICAL));
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        mRefreshLayout.setEnableLoadmore(false);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        loadData();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                loadData();
            }
        });
        adapter.setOnClickLinear(new TemperatureAdapter.onClickLinear() {
            @Override
            public void onClick(int position, TemperatureResultEntity data) {
                Intent intent = new Intent(getContext(),ControllerSensorDataActivity.class);
                intent.putExtra(ControllerSensorDataActivity.CODEKEY, data.getType());
                intent.putExtra(ControllerSensorDataActivity.DEVICEIDKEY,data.getDeviceId());
                startActivity(intent);
            }
        });
    }

    private void  loadData(){
        loadData(id);
    }

    public void loadData(String id){
       loadData(id,false);
    }
    public void loadData(String id,boolean isLoad){
        if (isLoad){
            showLoading();
        }
        this.id=id;
        mPresenter.getList(id);
    }
    @Override
    public void getList_Success(List<TemperatureResultEntity> datas) {
        end();
        adapter.setDatas(datas);
    }

    @Override
    public void getList_Fail(String msg) {
        end();
    }

    private void end(){
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
    }
}
