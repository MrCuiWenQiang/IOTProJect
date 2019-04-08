package com.iot.manager.view.fragment.device;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_DKContract;
import com.iot.manager.entity.net.result.DksResultEntity;
import com.iot.manager.presenter.FG_DKPresenter;
import com.iot.manager.view.DKEditActivity;
import com.iot.manager.view.adapter.DKAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

import static cn.faker.repaymodel.activity.BaseToolBarActivity.SUCCESSCODE;

/**
 * Function : 场地管理
 * Remarks  :
 * Created by Mr.C on 2019/3/28 0028.
 */
public class DkFragment extends BaseMVPFragment<Fg_DKContract.View,FG_DKPresenter> implements Fg_DKContract.View {

    public static DkFragment newInstance() {
        
        Bundle args = new Bundle();
        
        DkFragment fragment = new DkFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private ImageView iv_add;

    private DKAdapter adapter = new DKAdapter();

    public  int  page =1 ;

    @Override
    public int getLayoutId() {
        return R.layout.fg_dklist;
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
        rv_hands.addItemDecoration(new SpaceItemDecoration(0).setDivider(0.5F, ContextCompat.getColor(getContext(),R.color.color_light),LinearLayoutManager.VERTICAL));
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
        adapter.setOnItemCheckedListener(new DKAdapter.OnItemCheckedListener() {
            @Override
            public void onChecked(int position, DksResultEntity b) {
                Intent intent = new Intent(getContext(),DKEditActivity.class);
                Bundle build = new Bundle();
                build.putSerializable(DKEditActivity.IDKEY,b);
                intent.putExtras(build);
                startActivityForResult(intent,200);
            }
        });
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),DKEditActivity.class);
                startActivityForResult(intent,200);
            }
        });
    }

    private void  loadData(){
        mPresenter.getList(page);
    }

    @Override
    public void getList(List<DksResultEntity> datas) {
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
        adapter.addDatas(datas);
    }

    @Override
    public void getList_fail(String msg) {
        end();
        ToastUtility.showToast(msg);
    }

    private void end(){
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==SUCCESSCODE){
            page = 1;
            loadData();
        }
    }
}
