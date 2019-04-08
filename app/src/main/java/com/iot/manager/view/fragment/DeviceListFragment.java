package com.iot.manager.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;

import com.iot.manager.R;
import com.iot.manager.contract.DeviceListContract;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.entity.net.result.hand.HList;
import com.iot.manager.presenter.FG_DeviceListPresenter;
import com.iot.manager.view.SelectChlidDeviceActivity;
import com.iot.manager.view.adapter.DeviceListAdapter;
import com.iot.manager.view.adapter.HandControlAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function : 子设备选择
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class DeviceListFragment extends BaseMVPFragment<DeviceListContract.View, FG_DeviceListPresenter> implements DeviceListContract.View ,View.OnClickListener{

    public static DeviceListFragment newInstance(ArrayList<Deviceslist> childs) {
        Bundle args = new Bundle();
        args.putSerializable(OLDDATAKEY,childs);
        DeviceListFragment fragment = new DeviceListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public static DeviceListFragment newInstance(Deviceslist childs) {
        Bundle args = new Bundle();
        args.putSerializable(OLDDATAKEY,childs);
        DeviceListFragment fragment = new DeviceListFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private Button bt_yes;

    private static final String OLDDATAKEY = "childs";

    public  int  page =1 ;

    private DeviceListAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.fg_devcontrol;
    }

    @Override
    public void initview(View v) {
        rv_hands = v.findViewById(R.id.rv_hands);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        bt_yes = v.findViewById(R.id.bt_yes);


        adapter = new DeviceListAdapter();
        rv_hands.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(10));
        mRefreshLayout.setEnableRefresh(true);//启用刷新
    }

    @Override
    protected void initListener() {
        super.initListener();
        bt_yes.setOnClickListener(this);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                loadData();
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
               ++page;
                loadData();
            }
        });

    }
    private boolean aItem = false;



    public DeviceListFragment setaItem(boolean aItem) {
        this.aItem = aItem;
        return this;
    }
    @Override
    public void initData(Bundle savedInstanceState) {
        Bundle b = getArguments();
        Serializable serializable = b.getSerializable(OLDDATAKEY);
        if (serializable!=null){
            if (serializable instanceof ArrayList){
                ArrayList<Deviceslist> oldDatas = (ArrayList<Deviceslist>) serializable;
                adapter.setOldDatas(oldDatas);
            }else {
                Deviceslist item = (Deviceslist) serializable;
                adapter.setoldItem(item);
            }
        }
        adapter.setaItem(aItem);
        page =1;
        loadData();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_yes:{
                ArrayList<Deviceslist> data =  adapter.getOnClickData();
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(SelectChlidDeviceActivity.DATAKEY,data);
                    intent.putExtras(bundle);
                    getActivity().setResult(SelectChlidDeviceActivity.DATACODE,intent);
                    getActivity().finish();
            }
        }
    }

    private void  loadData(){
        mPresenter.getRelayData(page);
    }



    @Override
    public void getRelayData(List<HList> lists) {
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载


        if (lists==null||lists.size()<mPresenter.SIZE){
            mRefreshLayout.setEnableLoadmore(false);
        }else {
            mRefreshLayout.setEnableLoadmore(true);//启用加载
        }
        if (page==1){
            adapter.clean();
            adapter.notifyDataSetChanged();
        }
        adapter.addLists(lists);
    }

    @Override
    public void fail(String msg) {
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
        if (!TextUtils.isEmpty(msg)){
            ToastUtility.showToast(msg);
        }
    }
}
