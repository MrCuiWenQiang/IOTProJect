package com.iot.manager.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.R;
import com.iot.manager.URLs;
import com.iot.manager.entity.net.result.SorDataSendEntity;
import com.iot.manager.view.adapter.CntrollerSensorAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.activity.BaseToolBarActivity;
import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;
import cn.faker.repaymodel.util.SpaceItemDecoration;
import cn.faker.repaymodel.util.ToastUtility;

/**
 * Function : 设备历史数据 未使用mvp
 * Remarks  :
 * Created by Mr.C on 2019/3/31 0031.
 */
public class ControllerSensorDataActivity extends BaseToolBarActivity {
    private String deviceId;
    private String code;
    public static final String DEVICEIDKEY = "sxz";
    public static final String CODEKEY = "sxwsz";

    private int page = 1;
    private RecyclerView rv_hands;
    private RefreshLayout mRefreshLayout;
    private CntrollerSensorAdapter adapter = new CntrollerSensorAdapter();
    @Override
    protected int getLayoutContentId() {
        return R.layout.ac_csd;
    }


    @Override
    protected void initContentView() {
        isShowCut(false);
        setLeftTitle("历史数据");
        setToolBarBackgroundColor(R.color.white);

        rv_hands = findViewById(R.id.rv_hands);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        rv_hands.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        rv_hands.setAdapter(adapter);
        rv_hands.addItemDecoration(new SpaceItemDecoration(10));
        mRefreshLayout.setEnableRefresh(true);//启用刷新
    }


    @Override
    public void initData(Bundle savedInstanceState) {
        deviceId = getIntent().getStringExtra(DEVICEIDKEY);
        code = getIntent().getStringExtra(CODEKEY);
        page = 1;
        showLoading();
        loadData();
    }
    @Override
    protected void initListener() {
        super.initListener();
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                showLoading();
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
    public void loadData() {
        HashMap hashMap = new HashMap<String, Object>();
        hashMap.put("pageNo", page);
        hashMap.put("pageSize", BaseMVPPresenter.SIZE);
        hashMap.put("deviceId", deviceId);
        hashMap.put("sensorCode", code);
        HttpHelper.get(URLs.GETSENSORDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObj = JSON.parseObject(data);
                if (!jsonObj.containsKey("data")) {
                    adapter.setDatas(null);
                    end();
                    return;
                } else {
                    JSONArray jsonObject = (JSONArray) jsonObj.get("data");
                    String datajson = jsonObject.toJSONString();
                    List<SorDataSendEntity> lists = JsonUtil.fromList(datajson,SorDataSendEntity.class);
                    if (lists==null||lists.size()<BaseMVPPresenter.SIZE){
                        mRefreshLayout.setEnableLoadmore(false);
                    }else {
                        mRefreshLayout.setEnableLoadmore(true);//启用加载
                    }
                    if (page==1){
                        adapter.clean();
                        adapter.notifyDataSetChanged();
                    }
                    adapter.setDatas(lists);
                    end();
                }
            }

            @Override
            public void onFailed(int status, String message) {
                ToastUtility.showToast(message);
                end();
            }
        });
    }
    private void end(){
        dimiss();
        mRefreshLayout.finishRefresh();//完成刷新
        mRefreshLayout.finishLoadmore();//完成加载
    }
}
