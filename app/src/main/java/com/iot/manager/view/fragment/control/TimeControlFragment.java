package com.iot.manager.view.fragment.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_TimeControlContract;
import com.iot.manager.entity.net.result.TimerListResultEntity;
import com.iot.manager.presenter.FG_TimeControlPresenter;
import com.iot.manager.view.AddTimeControlActivity;
import com.iot.manager.view.adapter.HandControlAdapter;
import com.iot.manager.view.adapter.TimeControlAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;

/**
 * Function :定时控制
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class TimeControlFragment extends BaseMVPFragment<Fg_TimeControlContract.View,FG_TimeControlPresenter> implements Fg_TimeControlContract.View{

    public static TimeControlFragment newInstance() {
        Bundle args = new Bundle();
        TimeControlFragment fragment = new TimeControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public  int  page =1 ;

    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private TimeControlAdapter adapter;
    private ImageView iv_add;

    @Override
    public int getLayoutId() {
        return R.layout.fg_timecontrol;
    }

    /**
     * 在此方法内初始化控件
     *
     * @param v
     */
    @Override
    public void initview(View v) {
        rv_hands = v.findViewById(R.id.rv_hands);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
        iv_add = v.findViewById(R.id.iv_add);

        adapter = new TimeControlAdapter();
        rv_hands.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(10));
        mRefreshLayout.setEnableRefresh(true);//启用刷新
    }

    @Override
    public void initData(Bundle savedInstanceState) {
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
        adapter.setmOnItemCheckedChangeListener(new TimeControlAdapter.OnItemCheckedChangeListener() {
            @Override
            public void onCheckedChanged(int position, boolean b) {
                showLoading();
                TimerListResultEntity data = adapter.getDatas().get(position);
                mPresenter.setStatus(position,b,data.getId());
            }

            @Override
            public void onCheckedEdit(int position, TimerListResultEntity entity) {
                Intent intent = new Intent(getContext(),AddTimeControlActivity.class);
                intent.putExtra(AddTimeControlActivity.IDKEY,entity.getId());
                startActivityForResult(intent,200);
            }
        });
        showLoading();
        loadData();
    }

    private void loadData() {
        mPresenter.giveDatas(page);
    }

    @Override
    protected void initListener() {
        super.initListener();
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAcitvity(AddTimeControlActivity.class);
            }
        });
    }

    @Override
    public void give_Success(ArrayList<TimerListResultEntity> datas) {
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
        if (datas==null||datas.size()<mPresenter.SIZE){
            mRefreshLayout.setEnableLoadmore(false);
        }else {
            mRefreshLayout.setEnableLoadmore(true);//启用加载
        }
        if (page==1){
            adapter.clean();
        }
        adapter.addDatas(datas);
    }

    @Override
    public void give_Fail(String msg) {
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
        showDialog(msg);
    }

    @Override
    public void setStatus_Success(int position, boolean b) {
        dimiss();
        adapter.getDatas().get(position).setRunState(b?1:0);
    }

    @Override
    public void setStatus_Fail(int position, boolean b) {
        dimiss();
        adapter.notifyItemChanged(position);

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
