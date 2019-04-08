package com.iot.manager.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_TimeControlContract;
import com.iot.manager.entity.net.request.TimerStatusRequestEntity;
import com.iot.manager.entity.net.result.TimerListResultEntity;

import java.util.ArrayList;
import java.util.HashMap;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class FG_TimeControlPresenter extends BaseMVPPresenter<Fg_TimeControlContract.View> implements Fg_TimeControlContract.Presenter {

    @Override
    public void giveDatas(int page) {
        HashMap hashMap = new HashMap<String, Object>();
        hashMap.put("pageSize", SIZE);
        hashMap.put("pageNo", page);
        HttpHelper.get(URLs.GETTASKDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObj = JSON.parseObject(data);
                if (!jsonObj.containsKey("list")) {
                    if (getView() != null) {
                        getView().give_Success(null);
                    }
                    return;
                } else {
                    JSONArray jsonObject = (JSONArray) jsonObj.get("list");
                    String datajson = jsonObject.toJSONString();
                    ArrayList<TimerListResultEntity> datas = JsonUtil.fromList(datajson, TimerListResultEntity.class);
                    if (getView() != null) {
                        getView().give_Success(datas);
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().give_Fail(message);
                }
            }
        });
    }

    @Override
    public void setStatus(final int position, final boolean b, String id) {
        TimerStatusRequestEntity entity = new TimerStatusRequestEntity(b?1:2,id);
        HttpHelper.post(URLs.PAUSEORRESTART, entity, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                if (getView()!=null){
                    getView().setStatus_Success(position,b);
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView()!=null){
                    getView().setStatus_Fail(position,b);
                }
            }
        });


    }
}
