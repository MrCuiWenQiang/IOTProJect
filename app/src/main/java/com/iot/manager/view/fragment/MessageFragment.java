package com.iot.manager.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iot.manager.R;
import com.iot.manager.contract.Fg_MessageContract;
import com.iot.manager.entity.net.result.MessageRequestEntity;
import com.iot.manager.presenter.FG_MessagePresenter;
import com.iot.manager.view.MessageInfoActivity;
import com.iot.manager.view.adapter.MessageAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function :消息
 * Remarks  :
 * Created by Mr.C on 2019/3/21 0021.
 */
public class MessageFragment extends BaseMVPFragment<Fg_MessageContract.View,FG_MessagePresenter> implements Fg_MessageContract.View {
    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }


    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private TextView tv_search;
    private EditText et_search;

    public  int  page =1 ;
    public MessageAdapter adapter =new MessageAdapter();

    @Override
    public int getLayoutId() {
        return R.layout.fg_message;
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
        tv_search = v.findViewById(R.id.tv_search);
        et_search = v.findViewById(R.id.et_search);
    }

    /**
     * 在此方法内为控件赋值 或 开启网络线程访问
     *
     * @param savedInstanceState
     */
    @Override
    public void initData(Bundle savedInstanceState) {
        rv_hands.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(20));
        mRefreshLayout.setEnableRefresh(true);//启用刷新

        page = 1;
        loadData();
    }
    @Override
    protected void initListener() {
        super.initListener();
        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page = 1;
                loadData();
            }
        });

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
        adapter.setOnClickEdit(new MessageAdapter.OnClickItem() {
            @Override
            public void onClick(int position, MessageRequestEntity data) {
                Intent intent = new Intent(getContext(), MessageInfoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(MessageInfoActivity.KEY,data);
                intent.putExtras(bundle);
                startActivityForResult(intent,200);
            }
        });
    }


    private void  loadData(){
        mPresenter.getMessageList(page,et_search.getText());
    }

    @Override
    public void getMessageList_success(List<MessageRequestEntity> datas) {
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
    public void getMessageList_fail(String msg) {
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
        if (resultCode== BaseToolBarActivity.SUCCESSCODE){
            page = 1;
            loadData();
        }
    }
}
