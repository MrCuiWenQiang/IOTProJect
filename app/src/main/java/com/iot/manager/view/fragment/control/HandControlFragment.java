package com.iot.manager.view.fragment.control;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_HandControlContract;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.entity.net.result.hand.HList;
import com.iot.manager.presenter.FG_HandControlPresenter;
import com.iot.manager.view.adapter.HandControlAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class HandControlFragment extends BaseMVPFragment<Fg_HandControlContract.View, FG_HandControlPresenter> implements Fg_HandControlContract.View ,View.OnClickListener{

    public static HandControlFragment newInstance() {
        Bundle args = new Bundle();
        HandControlFragment fragment = new HandControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private TextView tv_me;
    private TextView tv_he;
    private TextView[]  tabs ;
    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;

    public  String MY_KEY_TYPE ;
    public  int  page =1 ;

    private HandControlAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.fg_handcontrol;
    }

    @Override
    public void initview(View v) {
        tv_me = v.findViewById(R.id.tv_me);
        tv_he = v.findViewById(R.id.tv_he);
        rv_hands = v.findViewById(R.id.rv_hands);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);

        tabs = new TextView[]{tv_me,tv_he};

        adapter = new HandControlAdapter();
        rv_hands.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(10));
        mRefreshLayout.setEnableRefresh(true);//启用刷新
    }

    @Override
    protected void initListener() {
        super.initListener();
        tv_me.setOnClickListener(this);
        tv_he.setOnClickListener(this);

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
        adapter.setOnCheckItem(new HandControlAdapter.onCheckItem() {
            @Override
            public void onCheckItem(int position, int childposition, String id, boolean value) {
                showLoading();
                mPresenter.settingStatus(position,childposition,id,value);
            }

            @Override
            public void onSetting(int position, int childposition, Deviceslist dv, Deviceslist b) {

            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        MY_KEY_TYPE = mPresenter.MY_KEY_TYPE;
        page =1;
        changeSelectState(0);
        loadData();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_me:{
                changeSelectState(0);
                page = 1;
                MY_KEY_TYPE = mPresenter.MY_KEY_TYPE;
                mPresenter.getRelayData(page,MY_KEY_TYPE);
                showLoading();
                loadData();
                break;
            }
            case R.id.tv_he:{
                changeSelectState(1);
                page = 1;
                MY_KEY_TYPE = mPresenter.HE_KEY_TYPE;
                showLoading();
                loadData();
                break;
            }
        }
    }

    private void  loadData(){
        mPresenter.getRelayData(page,MY_KEY_TYPE);
    }

    private void changeSelectState(int index){
        for (int i = 0; i <tabs.length ; i++) {
            TextView tv = tabs[i];
            tv.setSelected(i==index);
        }
    }

    @Override
    public void getRelayData(List<HList> lists, String type) {
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载

        MY_KEY_TYPE = type;

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

    @Override
    public void settingStatus_Success(int position,int childposition,String status ,String msg) {
        dimiss();
        HList hList = adapter.getItemData(position);
        Deviceslist data = hList.getDevicesList().get(position);
        data.setOpenState(status);
        adapter.notifyItemChanged(position);
        ToastUtility.showToast(msg);
    }

    @Override
    public void settingStatus_Fail(int position,String msg) {
        dimiss();
        adapter.notifyItemChanged(position);
        showDialog(msg);
    }




}
