package com.iot.manager.presenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iot.manager.URLs;
import com.iot.manager.contract.Fg_DKContract;
import com.iot.manager.contract.Fg_DeviceContract;
import com.iot.manager.entity.net.result.DksResultEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.faker.repaymodel.mvp.BaseMVPPresenter;
import cn.faker.repaymodel.net.json.JsonUtil;
import cn.faker.repaymodel.net.okhttp3.HttpHelper;
import cn.faker.repaymodel.net.okhttp3.callback.HttpResponseCallback;

/**
 * Function :
 * Remarks  :
 * Created by Mr.C on 2018/12/18 0018.
 */
public class FG_DKPresenter extends BaseMVPPresenter<Fg_DKContract.View> implements Fg_DKContract.Presenter {

    @Override
    public void getList(int page) {
        HashMap hashMap = new HashMap<String, Object>();
        hashMap.put("pageSize", SIZE);
        hashMap.put("pageNo", page);
        HttpHelper.get(URLs.PLACEGETPAGEDATA, hashMap, new HttpResponseCallback() {
            @Override
            public void onSuccess(String data) {
                JSONObject jsonObj = JSON.parseObject(data);
                if (!jsonObj.containsKey("data")) {
                    if (getView() != null) {
                        getView().getList(null);
                    }
                    return;
                } else {
                    JSONArray jsonObject = (JSONArray) jsonObj.get("data");
                    String datajson = jsonObject.toJSONString();
                    List<DksResultEntity> datas = JsonUtil.fromList(datajson,DksResultEntity.class);
                    if (getView() != null) {
                        getView().getList(datas);
                    }
                }
            }

            @Override
            public void onFailed(int status, String message) {
                if (getView() != null) {
                    getView().getList_fail(message);
                }
            }
        });
    }
}
