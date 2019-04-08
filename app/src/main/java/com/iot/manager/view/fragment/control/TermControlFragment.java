package com.iot.manager.view.fragment.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_TermControlContract;
import com.iot.manager.entity.net.result.termlist.Lists;
import com.iot.manager.presenter.FG_TermControlPresenter;
import com.iot.manager.view.SettingTernActivity;
import com.iot.manager.view.adapter.TermControlAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class TermControlFragment extends BaseMVPFragment<Fg_TermControlContract.View,FG_TermControlPresenter> implements Fg_TermControlContract.View{

    public static TermControlFragment newInstance() {
        Bundle args = new Bundle();
        TermControlFragment fragment = new TermControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;

    private TermControlAdapter adapter = new TermControlAdapter();

    public  int  page =1 ;

    @Override
    public int getLayoutId() {
        return R.layout.fg_termcontrol;
    }


    @Override
    public void initview(View v) {
        rv_hands = v.findViewById(R.id.rv_hands);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        mRefreshLayout.setEnableRefresh(true);//启用刷新
        rv_hands.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(10));

        initList();
    }

    @Override
    protected void initListener() {
        super.initListener();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page = 1;
                mPresenter.list(page);
            }
        });
        mRefreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                mPresenter.list(page);
            }
        });
        adapter.setOnCheckItem(new TermControlAdapter.onCheckItem() {
            @Override
            public void onCheckItem(int position, int childposition, String id) {
                Intent intent = new Intent(getContext(), SettingTernActivity.class);
                intent.putExtra(SettingTernActivity.IDKEY,id);
                startActivity(intent);
            }
        });
    }

    private void initList() {
        showLoading();
        page =1;
        mPresenter.list(page);
    }


    @Override
    public void list_success(List<Lists> list) {
        end();

        if (list==null||list.size()<mPresenter.SIZE){
            mRefreshLayout.setEnableLoadmore(false);
        }else {
            mRefreshLayout.setEnableLoadmore(true);//启用加载
        }
        if (page==1){
            adapter.clean();
            adapter.notifyDataSetChanged();
        }
        adapter.addLists(list);
    }

    private void end(){
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
    }

    @Override
    public void list_finsh(String msg) {
        end();
        ToastUtility.showToast(msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode== BaseToolBarActivity.SUCCESSCODE){
            initList();
        }
    }
}
