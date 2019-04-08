package com.iot.manager.view.fragment.device;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.iot.manager.R;
import com.iot.manager.contract.FG_DeviceGroupContrat;
import com.iot.manager.entity.net.result.DeviceGroupResultEntitr;
import com.iot.manager.presenter.FG_DeviceGroupPresenter;
import com.iot.manager.view.AddDeviceActivity;
import com.iot.manager.view.adapter.DeviceGroupAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;


import java.util.List;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function : 设备分组
 * Remarks  :
 * Created by Mr.C on 2019/3/29 0029.
 */
public class DeviceGroupFragment extends BaseMVPFragment<FG_DeviceGroupContrat.View,FG_DeviceGroupPresenter> implements FG_DeviceGroupContrat.View {

    public static DeviceGroupFragment newInstance() {
        Bundle args = new Bundle();
        DeviceGroupFragment fragment = new DeviceGroupFragment();
        fragment.setArguments(args);
        return fragment;
    }
    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private ImageView iv_add;
    public  int  page =1 ;

    private DeviceGroupAdapter adapter = new DeviceGroupAdapter();

    @Override
    public int getLayoutId() {
        return R.layout.fg_dg;
    }

    @Override
    public void initview(View v) {
        rv_hands = v.findViewById(R.id.rv_hands);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        iv_add = v.findViewById(R.id.iv_add);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        rv_hands.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(10));
        mRefreshLayout.setEnableRefresh(true);//启用刷新

        page = 1;
        loadData();
    }
    @Override
    protected void initListener() {
        super.initListener();
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
        adapter.setmOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DeviceGroupResultEntitr entitr = adapter.getItemData(i);
                Intent intent = new Intent(getContext(), AddDeviceActivity.class);
                intent.putExtra(AddDeviceActivity.KEYID,entitr.getId());
                startActivityForResult(intent,200);
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddDeviceActivity.class);
                startActivityForResult(intent,200);
            }
        });
    }
    private void  loadData(){
        mPresenter.getList(page);
    }
    @Override
    public void getList_fail(String msg) {
        end();
        ToastUtility.showToast(msg);
    }

    @Override
    public void getList_Success(List<DeviceGroupResultEntitr> datas) {
        end();
        if (datas==null||datas.size()<mPresenter.SIZE){
            mRefreshLayout.setEnableLoadmore(false);
        }else {
            mRefreshLayout.setEnableLoadmore(true);//启用加载
        }
        if (page==1){
            adapter.clean();
            adapter.notifyDataSetChanged();
        }
        adapter.addLists(datas);
    }



    private void end(){
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== BaseToolBarActivity.SUCCESSCODE){
            page = 1;
            loadData();
        }
    }
}
