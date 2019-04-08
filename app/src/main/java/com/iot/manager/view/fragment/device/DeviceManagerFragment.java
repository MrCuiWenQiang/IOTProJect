package com.iot.manager.view.fragment.device;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iot.manager.R;
import com.iot.manager.contract.DeviceManagerContract;
import com.iot.manager.entity.net.result.hand.Deviceslist;
import com.iot.manager.entity.net.result.hand.HList;
import com.iot.manager.presenter.FG_DeviceManagerPresenter;
import com.iot.manager.view.ControllerSensorDataActivity;
import com.iot.manager.view.adapter.HandControlAdapter;
import com.qmuiteam.qmui.widget.dialog.QMUIDialog;
import com.qmuiteam.qmui.widget.dialog.QMUIDialogAction;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPFragment;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function :设备管理
 * Remarks  :
 * Created by Mr.C on 2019/3/29 0029.
 */
public class DeviceManagerFragment extends BaseMVPFragment<DeviceManagerContract.View,FG_DeviceManagerPresenter> implements DeviceManagerContract.View,View.OnClickListener {

    public static DeviceManagerFragment newInstance() {
        Bundle args = new Bundle();
        DeviceManagerFragment fragment = new DeviceManagerFragment();
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

    private int mCurrentDialogStyle = com.qmuiteam.qmui.R.style.QMUI_Dialog;
    final String[] items1 = new String[]{"修改名称", "设备数据"};
    final String[] items2 = new String[]{"修改名称", "分享设备"};

    private Deviceslist itemdata;
    private HList hList;

    @Override
    public int getLayoutId() {
        return R.layout.fg_dm;
    }

    @Override
    public void initview(View v) {
        tv_me = v.findViewById(R.id.tv_me);
        tv_he = v.findViewById(R.id.tv_he);
        rv_hands = v.findViewById(R.id.rv_hands);
        mRefreshLayout = v.findViewById(R.id.refreshLayout);

        tabs = new TextView[]{tv_me,tv_he};
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        adapter = new HandControlAdapter();
        rv_hands.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(10));
        mRefreshLayout.setEnableRefresh(true);//启用刷新

        MY_KEY_TYPE = mPresenter.MY_KEY_TYPE;
        page =1;
        adapter.setShowSetting(true);
        changeSelectState(0);
        loadData();
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
                itemdata = b;
                hList = adapter.getItemData(position);
                if ("1".equals(b.getType())){
                    showMenuDialog(items2);
                }else {
                    showMenuDialog(items1);
                }
            }
        });
    }


    private void showMenuDialog(final String[] items) {
        new QMUIDialog.MenuDialogBuilder(getActivity())
                .addItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if (which==0){
                            showEditTextDialog();
                        }else {
                            if ("1".equals(itemdata.getType())){//继电器

                            }else {
                                Intent intent = new Intent(getContext(),ControllerSensorDataActivity.class);
//                                intent.putExtra(ControllerSensorDataActivity.DEVICEIDKEY,itemdata.getId());
//                                intent.putExtra(ControllerSensorDataActivity.CODEKEY,itemdata.getCode());

                                intent.putExtra(ControllerSensorDataActivity.CODEKEY, itemdata.getCode());
                                intent.putExtra(ControllerSensorDataActivity.DEVICEIDKEY,hList.getId());
                                startActivity(intent);
                            }
                        }

                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void showEditTextDialog() {
        final QMUIDialog.EditTextDialogBuilder builder = new QMUIDialog.EditTextDialogBuilder(getActivity());
        builder.setTitle("请输入设备名称")
                .setPlaceholder(itemdata.getName())
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .addAction("取消", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        dialog.dismiss();
                    }
                })
                .addAction("确定", new QMUIDialogAction.ActionListener() {
                    @Override
                    public void onClick(QMUIDialog dialog, int index) {
                        CharSequence text = builder.getEditText().getText();
                        if (text != null && text.length() > 0) {
                            showLoading();
                            mPresenter.removeName(itemdata.getId(),text.toString());
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getActivity(), "请输入设备名称", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .create(mCurrentDialogStyle).show();
    }

    private void  loadData(){
        mPresenter.getRelayData(page,MY_KEY_TYPE);
    }

    private void end(){
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_me:{
                changeSelectState(0);
                page = 1;
                MY_KEY_TYPE = mPresenter.MY_KEY_TYPE;
                adapter.setShowSetting(true);
                mPresenter.getRelayData(page,MY_KEY_TYPE);
                showLoading();
                loadData();
                break;
            }
            case R.id.tv_he:{
                changeSelectState(1);
                page = 1;
                MY_KEY_TYPE = mPresenter.HE_KEY_TYPE;
                adapter.setShowSetting(false);
                showLoading();
                loadData();
                break;
            }
        }
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

    @Override
    public void removeName_success(String msg) {
        end();
        ToastUtility.showToast(msg);
        page=1;
        showLoading();
        loadData();
    }

    @Override
    public void removeName_fail(String msg) {
        end();
        showDialog(msg);
    }
}
