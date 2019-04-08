package com.iot.manager.view.fragment.control;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_NexusControlContract;
import com.iot.manager.entity.net.result.NexusResultEntity;
import com.iot.manager.presenter.FG_NexusControlPresenter;
import com.iot.manager.view.NexusControlEditActivity;
import com.iot.manager.view.adapter.NexusAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

import static cn.faker.repaymodel.activity.BaseToolBarActivity.SUCCESSCODE;

/**
 * Function :联动控制
 * Remarks  :
 * Created by Mr.C on 2019/3/22 0022.
 */
public class NexusControlFragment extends BaseMVPFragment<Fg_NexusControlContract.View, FG_NexusControlPresenter> implements Fg_NexusControlContract.View {

    public static NexusControlFragment newInstance() {
        Bundle args = new Bundle();
        NexusControlFragment fragment = new NexusControlFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public  int  page =1 ;

    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private ImageView iv_add;
    private NexusAdapter adapter = new NexusAdapter();

    @Override
    public int getLayoutId() {
        return R.layout.fg_nexuscontrol;
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
    }

    /**
     * 在此方法内为控件赋值 或 开启网络线程访问
     *
     * @param savedInstanceState
     */
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
        iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toAcitvity(NexusControlEditActivity.class);
            }
        });
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
        adapter.setOnClickEdit(new NexusAdapter.onClickEdit() {
            @Override
            public void onEditClick(int position, NexusResultEntity data) {
                Intent intent = new Intent(getContext(),NexusControlEditActivity.class);
                intent.putExtra(NexusControlEditActivity.IDKEY,data.getId());
                startActivityForResult(intent,6);
            }
        });
    }
    private void initList() {
        showLoading();
        page =1;
        mPresenter.list(page);
    }

    private void end(){
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
    }

    @Override
    public void list_Success(ArrayList<NexusResultEntity> datas) {
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

    @Override
    public void list_Fail(String msg) {
        end();
        ToastUtility.showToast(msg);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==SUCCESSCODE){
            initList();
        }
    }
}
